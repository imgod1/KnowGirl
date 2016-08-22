package com.kk.imgod.knowgirl.fragment;

import android.graphics.Bitmap;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.activity.MainActivity;
import com.kk.imgod.knowgirl.activity.PictureDetailActivity;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.customerclass.MyStringCallBack;
import com.kk.imgod.knowgirl.model.ImageBean;
import com.kk.imgod.knowgirl.model.ImageResponse;
import com.kk.imgod.knowgirl.utils.GsonUtils;
import com.kk.imgod.knowgirl.utils.Lg;
import com.kk.imgod.knowgirl.utils.ScreenUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;


/**
 * Created by imgod on 2016/4/24.
 */
public class LazyPictureFragment1 extends BaseLazyFragment {
    public final static String URL = "url";
    public final static String IMGCLASSID = "imgclassid";
    private String url;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipe_refresh_layout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    private CommonAdapter pictureAdapter;
    LoadMoreWrapper loadMoreWrapper;
    /**
     * 静态的变量来存储详情界面需要的数据
     */
    public static List<ImageBean> detailImageBeanList;
    private List<ImageBean> imgList;

    private RequestCall requestCall;
    //服务器上的图片总数
    private int allPictureCount = -1;
    //当前的页码
    private int page = 1;

    /**
     * @param url 图片网址
     * @return 返回fragment
     */
    public static LazyPictureFragment1 newInstance(String url, int imgClassId) {
        LazyPictureFragment1 pictureFragment = new LazyPictureFragment1();
        Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        bundle.putInt(IMGCLASSID, imgClassId);
        pictureFragment.setArguments(bundle);
        return pictureFragment;
    }

    public void initValue() {
        swipe_refresh_layout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    private int hasLoadedPicCount = 0;//本页已经加载出来的图片的数量

    public void initEvent() {
        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPicture(page);
            }
        });

        pictureAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                detailImageBeanList = imgList;
                PictureDetailActivity.actionStart(getActivity(), position);
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

//                if(allPictureCount==-1) {//第一次的时候
//                    showLoadingMoreView();
//                    Lg.e("test", "setOnLoadMoreListener loadmore:" + page);
//                    getPicture(page);
//                    return;
//                }
//
//                if(imgList.size()<allPictureCount) {
//                    if(hasLoadedPicCount>6) {
//
//                    }
//                }
                if (allPictureCount == -1 || (imgList.size() < allPictureCount && hasLoadedPicCount > 5)) {
                    showLoadingMoreView();
                    Lg.e("test", "setOnLoadMoreListener loadmore:" + page);
                    getPicture(page);
                } else {
                    hideLoadingMoreView();
                    Lg.e("test", "setOnLoadMoreListener loadmore ELSE:" + page);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (requestCall != null) {
            requestCall.cancel();
        }
    }


    private int use_width;//瀑布流真实的宽度

    private View loadMoreView;//加载更多的view

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        use_width = (ScreenUtils.getWindowsWidth(getActivity()) - 20) / 2;
        url = bundle.getString(URL);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(staggeredGridLayoutManager);
        imgList = new ArrayList<>();

        pictureAdapter = new CommonAdapter<ImageBean>(getActivity(), R.layout.item_stag, imgList) {
            @Override
            protected void convert(final ViewHolder holder, ImageBean imageBean, int position) {
                final String img_url = API.PICTURE_BASE_URL + imageBean.getImg();
                Glide.with(getActivity())//activty
                        .load(img_url)
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                            @Override
                            public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                                // Do something with bitmap here.
                                bitmap.getHeight(); //获取bitmap信息，可赋值给外部变量操作，也可在此时行操作。
                                bitmap.getWidth();
                                ImageView img_stag = holder.getView(R.id.img_stag);
                                int use_height = use_width * bitmap.getHeight() / bitmap.getWidth();
                                ViewGroup.LayoutParams layoutParams = img_stag.getLayoutParams();
                                layoutParams.height = use_height;
                                layoutParams.width = use_width;
                                img_stag.setLayoutParams(layoutParams);
                                img_stag.setImageBitmap(bitmap);
                                hasLoadedPicCount++;
                            }
                        });
            }
        };

        loadMoreWrapper = new LoadMoreWrapper(pictureAdapter);
        loadMoreView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_loading_more, recyclerview, false);
        loadMoreWrapper.setLoadMoreView(loadMoreView);
        recyclerview.setAdapter(loadMoreWrapper);
        initValue();
        initEvent();
    }

    /**
     * 显示加载的view
     */
    private void showLoadingMoreView() {
        if (null != loadMoreView) {
            loadMoreView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏加载的view
     */
    private void hideLoadingMoreView() {
        if (null != loadMoreView) {
            loadMoreView.setVisibility(View.GONE);
        }
    }

    private boolean isLoading = false;

    public void getPicture(final int temppage) {
        if (isLoading) {
            return;
        }
        isLoading = true;
        hasLoadedPicCount = 0;
        String useUrl = url + page;
        Log.e("pictureFragment", "请求图片的地址为:" + useUrl);
        requestCall = OkHttpUtils.get().url(useUrl).build();
        requestCall.execute(new MyStringCallBack(getActivity(), ((MainActivity) getActivity()).getMainCoordinatorLayout()) {
            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                isLoading = false;
                swipe_refresh_layout.setRefreshing(false);
                hideLoadingMoreView();
                Log.e("pictureFragment", "onError:" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                isLoading = false;
                swipe_refresh_layout.setRefreshing(false);
                hideLoadingMoreView();
                if (!TextUtils.isEmpty(response)) {
                    Log.e("onResponse", "onResponse:" + url + "\t" + response);
                    ImageResponse imageResponse = GsonUtils.getGson().fromJson(response, ImageResponse.class);
                    if (imageResponse != null && imageResponse.getTngou() != null) {
                        if (page == 1) {
                            imgList.clear();
                            allPictureCount = imageResponse.getTotal();
                        }
                        imgList.addAll(imageResponse.getTngou());
                        recyclerview.getAdapter().notifyDataSetChanged();
                        page++;
                    } else {
                        Toast.makeText(getActivity(), "没有更多图片了...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }
}
