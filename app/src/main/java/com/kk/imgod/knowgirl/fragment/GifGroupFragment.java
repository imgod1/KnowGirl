package com.kk.imgod.knowgirl.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.activity.GifDetailActivity;
import com.kk.imgod.knowgirl.activity.MainActivity;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.customerclass.MyStringCallBack;
import com.kk.imgod.knowgirl.model.GifGroupBean;
import com.kk.imgod.knowgirl.model.GifGroupResponse;
import com.kk.imgod.knowgirl.utils.DBUtils;
import com.kk.imgod.knowgirl.utils.GsonUtils;
import com.kk.imgod.knowgirl.utils.ImageLoader;
import com.kk.imgod.knowgirl.utils.Lg;
import com.kk.imgod.knowgirl.utils.ScreenUtils;
import com.kk.imgod.knowgirl.utils.StringUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

import io.realm.Sort;
import okhttp3.Call;


/**
 * 项目名称：KnowGirl
 * 类描述：懒加载的图片Fragment
 * 创建人：imgod
 * 创建时间：2016/4/24 16:20
 * 修改人：imgod
 * 修改时间：2016/4/24 16:20
 * 修改备注：
 */
public class GifGroupFragment extends NormalRecyclerViewFragment {
    public static final String Gallery = "gallery";
    public static final String Scoll = "scroll";
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    private CommonAdapter pictureAdapter;
    LoadMoreWrapper loadMoreWrapper;
    private List<GifGroupBean> imgList;
    private RequestCall requestCall;
    //当前的页码
    private int page = 1;

    /**
     * @return 返回一个GifGroupFragment
     */
    public static GifGroupFragment newInstance() {
        GifGroupFragment pictureFragment = new GifGroupFragment();
        Bundle bundle = new Bundle();
        pictureFragment.setArguments(bundle);
        return pictureFragment;
    }

    @Override
    public void initValue() {
        initAdapter();
        reFresh();
    }

    @Override
    public void initEvent() {
        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reFresh();
            }
        });
        pictureAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                String realUrl = imgList.get(position).getUrl();
                realUrl = realUrl.replace(Gallery, Scoll);
                GifDetailActivity.actionStart(getActivity(), realUrl);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        //设置加载更多的监听
        loadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPicture();
            }
        });
    }

    private void reFresh() {
        page = 1;
        getPicture();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (requestCall != null) {
            requestCall.cancel();
        }

        if (null != imgList) {
            imgList.clear();
            imgList = null;
        }
        if (null != recyclerview) {
            recyclerview = null;
        }
    }


    private int use_width;//瀑布流真实的宽度
    private View loadMoreView;//加载更多的view

    private void initAdapter() {
        use_width = (ScreenUtils.getWindowsWidth(getActivity()) - 20) / 2;
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) {
            @Override
            public void collectAdjacentPrefetchPositions(int dx, int dy, RecyclerView.State state, LayoutPrefetchRegistry layoutPrefetchRegistry) {
                try {
                    super.collectAdjacentPrefetchPositions(dx, dy, state, layoutPrefetchRegistry);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        recyclerview.setLayoutManager(staggeredGridLayoutManager);
        imgList = new ArrayList<>();

        //先从数据库取一下数据
        List<GifGroupBean> tempImageList = MainActivity.realm.where(GifGroupBean.class).findAllSorted("gallery_id", Sort.DESCENDING);
        page = tempImageList.size() / 10 + 1;//初始化页面
        imgList.addAll(tempImageList);

        pictureAdapter = new CommonAdapter<GifGroupBean>(getActivity(), R.layout.item_gif_stag, imgList) {
            @Override
            protected void convert(final ViewHolder holder, final GifGroupBean imageBean, final int position) {
                ImageView img_stag = holder.getView(R.id.img_stag);
                String title = StringUtils.subTitle(imageBean.getTitle());
                holder.setText(R.id.txt_title, title);
                holder.setText(R.id.txt_date, imageBean.getUpdated());
                final String img_url = imageBean.getCover_url();
                if (imageBean.getImg_height() == 0 || imageBean.getImg_width() == 0) {//没有尺寸信息的话就给一个固定的尺寸
                    ViewGroup.LayoutParams layoutParams = img_stag.getLayoutParams();
                    layoutParams.width = use_width;
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeResource(getResources(), R.drawable.splash, options);
                    layoutParams.height = use_width * options.outHeight / options.outWidth;
//                    Lg.e("test", "加载splash:height:" + use_height);
                    img_stag.setLayoutParams(layoutParams);
                    img_stag.setImageResource(R.drawable.splash);

                    //加载图片
                    Glide.with(getActivity())//activty
                            .load(img_url)
                            .asBitmap()
                            .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                                @Override
                                public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                                    if (position > -1 && position < imgList.size()) {
                                        GifGroupBean tempBean = imgList.get(position);
                                        //在事务中进行设置
                                        MainActivity.realm.beginTransaction();
                                        tempBean.setImg_height(bitmap.getHeight());
                                        tempBean.setImg_width(bitmap.getWidth());
                                        MainActivity.realm.copyToRealmOrUpdate(tempBean);
                                        MainActivity.realm.commitTransaction();

//                                        DBUtils.copyOrUpdateRealm(MainActivity.realm, tempBean);//存储起来
                                        ImageView img_stag = holder.getView(R.id.img_stag);
                                        int use_height = use_width * bitmap.getHeight() / bitmap.getWidth();
                                        ViewGroup.LayoutParams layoutParams = img_stag.getLayoutParams();
                                        layoutParams.height = use_height;
                                        layoutParams.width = use_width;
                                        img_stag.setLayoutParams(layoutParams);
                                        img_stag.setImageBitmap(bitmap);
                                        notifyItemChanged(position);
                                    }
                                }
                            });
                } else {//有尺寸信息的话,那就直接计算并设置
                    ViewGroup.LayoutParams layoutParams = img_stag.getLayoutParams();
                    layoutParams.width = use_width;
                    layoutParams.height = use_width * imageBean.getImg_height() / imageBean.getImg_width();
                    img_stag.setLayoutParams(layoutParams);
                    ImageLoader.load(getActivity(), img_url, img_stag);
                }

//                ScaleInAnimation scaleInAnimation = new ScaleInAnimation();
//                Animator[] animators = scaleInAnimation.getAnimators(img_stag);
//                animators[0].start();
            }
        };

        loadMoreWrapper = new LoadMoreWrapper(pictureAdapter);
        loadMoreView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_loading_more, recyclerview, false);
        loadMoreWrapper.setLoadMoreView(loadMoreView);
        recyclerview.setAdapter(loadMoreWrapper);
    }

    /**
     * 隐藏加载的view
     */
    private void hideLoadingMoreView() {
        if (null != loadMoreView) {
            loadMoreView.setVisibility(View.GONE);
        }
    }

    public void getPicture() {
        final String useUrl = API.GIF_URL + page;
        requestCall = OkHttpUtils.get().url(useUrl).build();
        requestCall.execute(new MyStringCallBack(getActivity(), ((MainActivity) getActivity()).getMainCoordinatorLayout()) {
            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                srl_main.setRefreshing(false);
                hideLoadingMoreView();
                Log.e("pictureFragment", "onError:" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                srl_main.setRefreshing(false);
                hideLoadingMoreView();
                if (!TextUtils.isEmpty(response)) {
                    if (page == 1) {
                        imgList.clear();
                    }
                    String jsonContent = StringUtils.getJsonContentFromResponse(response);
                    if (!TextUtils.isEmpty(jsonContent)) {
                        GifGroupResponse gifGroupResponse = GsonUtils.getGson().fromJson(jsonContent, GifGroupResponse.class);
                        if (null != gifGroupResponse) {
                            List<GifGroupBean> tempImageList = gifGroupResponse.getGallerys();
                            DBUtils.saveList(MainActivity.realm, tempImageList);
                            imgList.addAll(tempImageList);
                            if (page == 1) {
                                recyclerview.getAdapter().notifyDataSetChanged();
                            } else {
                                recyclerview.getAdapter().notifyItemRangeInserted(imgList.size() - tempImageList.size(), tempImageList.size());
                            }
                            page++;
                        }
                    }
                }
            }
        });
    }
}
