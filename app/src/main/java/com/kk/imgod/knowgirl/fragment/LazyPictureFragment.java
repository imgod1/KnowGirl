package com.kk.imgod.knowgirl.fragment;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.activity.MainActivity;
import com.kk.imgod.knowgirl.activity.PictureDetailActivity;
import com.kk.imgod.knowgirl.adapter.UlimateBaseAdapter;
import com.kk.imgod.knowgirl.adapter.UltimateStagAdapter;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.app.Constant;
import com.kk.imgod.knowgirl.customerclass.LazyFragment;
import com.kk.imgod.knowgirl.customerclass.MyStringCallBack;
import com.kk.imgod.knowgirl.model.ImageBean;
import com.kk.imgod.knowgirl.model.ImageResponse;
import com.kk.imgod.knowgirl.utils.GsonUtils;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;


/**
 * Created by imgod on 2016/4/24.
 */
public class LazyPictureFragment extends RecyclerViewFragment {
    public static final int row = 40;
    public final static String URL = "url";
    private String url;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    private UltimateStagAdapter ultimateStagAdapter;
    private List<ImageBean> imgList;

    private RequestCall requestCall;
    //服务器上的图片总数
    private int allPictureCount;
    //缓存图片尺寸的时候,发生异常的图片数量
    private int errPicture;
    //当前的页码
    private int page = 1;

    /**
     * @param url 图片网址
     * @return 返回fragment
     */
    public static LazyPictureFragment newInstance(String url) {
        LazyPictureFragment pictureFragment = new LazyPictureFragment();
        Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        pictureFragment.setArguments(bundle);
        return pictureFragment;
    }

    public void initValue() {
        recyclerview.mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                recyclerview.mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        getPicture(page);
    }

    public void initEvent() {
        recyclerview.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e("initevent", "initevent:触发了下拉刷新操作:" + url);
                page = 1;
                getPicture(page);
            }
        });
        recyclerview.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {
                recyclerview.mSwipeRefreshLayout.setRefreshing(true);
                getPicture(page);
                Log.e("initevent", "initevent:触发了上拉加载更多操作:" + url);
            }
        });

        ultimateStagAdapter.setOnItemClickListener(new UlimateBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                PictureDetailActivity.actionStart(getActivity(), imgList.get(position).getImg());
            }
        });
    }

    public void getPicture(final int temppage) {
//        if (!ultimateStagAdapter.enableLoadMore()) {
//            Log.e("initevent", "因为当前已经没有更多了.所以不请求网络");
//        } else {
        String useUrl = url + temppage;
        Log.e("pictureFragment", "请求图片的地址为:" + useUrl);
        requestCall = OkHttpUtils.get().url(useUrl).build();
        requestCall.execute(new MyStringCallBack(getActivity(), ((MainActivity) getActivity()).getMainCoordinatorLayout()) {
            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                recyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerview.setRefreshing(false);
                    }
                }, Constant.DELAYTIME);
                Log.e("pictureFragment", "onError:" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                if (!TextUtils.isEmpty(response)) {
                    Log.e("onResponse", "onResponse:" + url + "\t" + response);
                    Log.e("onResponse", "onResponse:当前集合数量" + imgList.size());
                    ImageResponse imageResponse = GsonUtils.getGson().fromJson(response, ImageResponse.class);
                    allPictureCount = imageResponse.getTotal();
                    if (imageResponse != null && imageResponse.getTngou() != null && imageResponse.getTngou().size() != 0) {
                        Log.e("onResponse", "onResponse:数组大小:" + imageResponse.getTngou().size());
                        getImageSize(imageResponse.getTngou());
                    } else {
                        recyclerview.setRefreshing(false);
                        Toast.makeText(getActivity(), "没有更多图片了...", Toast.LENGTH_SHORT).show();
                        ultimateStagAdapter.enableLoadMore(false);
//                            ultimateStagAdapter.getCustomLoadMoreView().setVisibility(View.GONE);
                    }

                }
            }
        });
//        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (requestCall != null) {
            requestCall.cancel();
        }
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        Glide.with(getContext()).pauseRequests();
//        Log.e("onPause", "暂停加载该页图片");
//    }

    public void getImageSize(final List<ImageBean> tempImgList) {
        Log.e("onResponse", "getImageSize 方法执行:" + tempImgList.size());
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                for (int i = 0; i < tempImgList.size(); i++) {
                    final String img_url = API.PICTURE_BASE_URL + tempImgList.get(i).getImg();
                    Bitmap bitmap = null;
                    try {
                        bitmap = Glide.with(getActivity())
                                .load(img_url)
                                .asBitmap()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
//                        Log.e("getWidth", "" + bitmap.getWidth());
//                        Log.e("getHeight", "" + bitmap.getHeight());
                        tempImgList.get(i).setImg_width(bitmap.getWidth());
                        tempImgList.get(i).setImg_height(bitmap.getHeight());
//                        imgList.add(tempImgList.get(i));
                    } catch (Exception e) {
                        tempImgList.remove(i);//发生异常的图片就移除掉
                        Log.e("getWidth", "doInBackground1 发生异常" + e.getMessage() + ",图片网址:" + img_url);
                        errPicture++;
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.e("onResponse", "getImageSize 异步完毕,加载成功的图片数量:" + tempImgList.size());
                recyclerview.setRefreshing(false);
                if (1 == page) {
                    imgList.clear();
                    imgList.addAll(tempImgList);
                    ultimateStagAdapter.notifyDataSetChanged();
                } else {
                    ultimateStagAdapter.insert(tempImgList);
                }
                Log.e("onResponse", "getImageSize 异步完毕,当前页码:" + page + ",当前集合数量:" + imgList.size());
                page++;
            }
        }.execute();
    }


    @Override
    protected void initData() {
        url = getArguments().getString(URL);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(staggeredGridLayoutManager);
        recyclerview.enableDefaultSwipeRefresh(true);
        recyclerview.setHasFixedSize(true);
        imgList = new ArrayList<>();
        ultimateStagAdapter = new UltimateStagAdapter(getActivity(), imgList);
//        ultimateStagAdapter.setCustomLoadMoreView(LayoutInflater.from(getActivity()).inflate(R.layout.custom_bottom_progressbar, null));
        ultimateStagAdapter.enableLoadMore(true);
        recyclerview.reenableLoadmore();
        recyclerview.setAdapter(ultimateStagAdapter);
        initValue();
        initEvent();
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }
}
