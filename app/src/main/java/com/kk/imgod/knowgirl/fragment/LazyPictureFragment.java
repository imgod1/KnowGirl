package com.kk.imgod.knowgirl.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.activity.MainActivity;
import com.kk.imgod.knowgirl.activity.PictureDetailActivity;
import com.kk.imgod.knowgirl.anim.ScaleInAnimation;
import com.kk.imgod.knowgirl.anim.SlideInBottomAnimation;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.customerclass.MyStringCallBack;
import com.kk.imgod.knowgirl.model.ImageBean;
import com.kk.imgod.knowgirl.utils.DBUtils;
import com.kk.imgod.knowgirl.utils.ImageLoader;
import com.kk.imgod.knowgirl.utils.JsoupUtils;
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

import okhttp3.Call;


/**
 * Created by imgod on 2016/4/24.
 */
public class LazyPictureFragment extends RecyclerViewFragment {
    public final static String IMGCLASSID = "imgclassid";
    private String imgclassid;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    private CommonAdapter pictureAdapter;
    LoadMoreWrapper loadMoreWrapper;
    private List<ImageBean> imgList;

    private RequestCall requestCall;
    //当前的页码
    private int page = 1;

    /**
     * @param imgClassId 图片类别
     * @return
     */
    public static LazyPictureFragment newInstance(int imgClassId) {
        LazyPictureFragment pictureFragment = new LazyPictureFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMGCLASSID, "" + imgClassId);
        pictureFragment.setArguments(bundle);
        return pictureFragment;
    }

    public void initValue() {
    }

    public void initEvent() {
        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPicture(page);
            }
        });
        pictureAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                PictureDetailActivity.detailImageBeanList = imgList;
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
                getPicture(page);
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
    private int use_height;//瀑布流真实的高度
    private View loadMoreView;//加载更多的view

    private void initAdapter() {
        use_width = (ScreenUtils.getWindowsWidth(getActivity()) - 20) / 2;
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(staggeredGridLayoutManager);
        imgList = new ArrayList<>();

        //先从数据库取一下数据
//        List<ImageBean> tempImageList = MainActivity.realm.where(ImageBean.class).equalTo("cid", imgclassid).findAllSorted("id", Sort.DESCENDING);
//        imgList.addAll(tempImageList);

        pictureAdapter = new CommonAdapter<ImageBean>(getActivity(), R.layout.item_stag, imgList) {
            @Override
            protected void convert(final ViewHolder holder, final ImageBean imageBean, final int position) {
                ImageView img_stag = holder.getView(R.id.img_stag);
                final String img_url = imageBean.getImg();
                if (imageBean.getImg_height() == 0 || imageBean.getImg_width() == 0) {//没有尺寸信息的话就给一个固定的尺寸
                    ViewGroup.LayoutParams layoutParams = img_stag.getLayoutParams();
                    layoutParams.width = use_width;
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeResource(getResources(), R.drawable.splash, options);
                    int use_height = use_width * options.outHeight / options.outWidth;
                    layoutParams.height = use_height;
                    Lg.e("test", "加载splash:height:" + use_height);
                    img_stag.setLayoutParams(layoutParams);
                    img_stag.setImageResource(R.drawable.splash);

                    //加载图片
                    Glide.with(getActivity())//activty
                            .load(img_url)
                            .asBitmap()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                                @Override
                                public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                                    if (position > -1 && position < imgList.size()) {
                                        ImageBean tempBean = imgList.get(position);
                                        tempBean.setImg_height(bitmap.getHeight());
                                        tempBean.setImg_width(bitmap.getWidth());
                                        DBUtils.copyOrUpdateRealm(MainActivity.realm, tempBean);//存储起来
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
                    int use_height = use_width * imageBean.getImg_height() / imageBean.getImg_width();
                    layoutParams.height = use_height;
                    Lg.e("test", "已经有尺寸信息:" + position + "height::" + use_height);
                    img_stag.setLayoutParams(layoutParams);
                    ImageLoader.load(getActivity(), img_url, img_stag);
                }

                ScaleInAnimation scaleInAnimation = new ScaleInAnimation();
                Animator[] animators = scaleInAnimation.getAnimators(img_stag);
                animators[0].start();
            }
        };

        loadMoreWrapper = new LoadMoreWrapper(pictureAdapter);
        loadMoreView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_loading_more, recyclerview, false);
        loadMoreWrapper.setLoadMoreView(loadMoreView);
        recyclerview.setAdapter(loadMoreWrapper);
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getArguments();
        imgclassid = bundle.getString(IMGCLASSID);
        initAdapter();
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


    public void getPicture(final int temppage) {
        final String useUrl = API.DBMEIZI_BASE_URL + imgclassid + "&pager_offset=" + temppage;
        Log.e("pictureFragment", "请求图片的地址为:" + useUrl);
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
                    Log.e("onResponse", "onResponse:" + useUrl + "\t" + response);
                    if (page == 1) {
                        imgList.clear();
                    }
                    List<ImageBean> tempImageList = JsoupUtils.getImgBeanListFromHtml(response, imgclassid);
//                    DBUtils.saveList(MainActivity.realm, tempImageList);
                    imgList.addAll(tempImageList);
                    if (page == 1) {
                        recyclerview.getAdapter().notifyDataSetChanged();
                    } else {
                        recyclerview.getAdapter().notifyItemRangeInserted(imgList.size() - tempImageList.size(), tempImageList.size());
                    }
                    page++;
                }
            }
        });
    }
}
