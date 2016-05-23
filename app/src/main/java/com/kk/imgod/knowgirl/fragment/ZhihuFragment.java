package com.kk.imgod.knowgirl.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.activity.MainActivity;
import com.kk.imgod.knowgirl.activity.ZhiHuDetailActivity;
import com.kk.imgod.knowgirl.adapter.UlimateBaseAdapter;
import com.kk.imgod.knowgirl.adapter.ZhihuListAdapter;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.app.Constant;
import com.kk.imgod.knowgirl.customerclass.MyStringCallBack;
import com.kk.imgod.knowgirl.model.ZhihuResponse;
import com.kk.imgod.knowgirl.model.ZhihuStory;
import com.kk.imgod.knowgirl.utils.DateUtils;
import com.kk.imgod.knowgirl.utils.GsonUtils;
import com.kk.imgod.knowgirl.utils.Lg;
import com.kk.imgod.knowgirl.utils.SnackBarUtils;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.ui.emptyview.emptyViewOnShownListener;
import com.squareup.haha.perflib.Main;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;


public class ZhihuFragment extends RecyclerViewFragment {
    private String willLoadDate;
    private ZhihuListAdapter zhihuListAdapter;
    private List<ZhihuStory> zhihuStories;

    @Override
    protected void initData() {
        willLoadDate = DateUtils.parseStandardDate(new Date());
        recyclerview.enableDefaultSwipeRefresh(true);
        zhihuStories = new ArrayList<>();
        zhihuListAdapter = new ZhihuListAdapter(getActivity(), zhihuStories);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(zhihuListAdapter);
        recyclerview.reenableLoadmore();
//        recyclerview.setEmptyView(R.layout.item_news, UltimateRecyclerView.EMPTY_CLEAR_ALL, new emptyViewOnShownListener() {
//            @Override
//            public void onEmptyViewShow(View mView) {
//                Lg.e("zhihufragment", "触发了setEmptyView Listener1111111111111");
//                mView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Lg.e("zhihufragment", "触发了setEmptyView Listener222222222222222");
//                    }
//                });
//            }
//        });
//        recyclerview.hideEmptyView();

//        zhihuListAdapter.enableLoadMore();
//        zhihuListAdapter.setCustomLoadMoreView(View.inflate(getActivity(), R.layout.layout_loading_more, null));
        zhihuListAdapter.setOnItemClickListener(new UlimateBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                ZhiHuDetailActivity.actionStart(getActivity(), zhihuStories.get(position));
            }
        });

        recyclerview.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Lg.e("zhihufragment", "触发了下拉刷新操作");
                getLastData();
            }
        });

        recyclerview.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {
                Lg.e("zhihufragment", "触发了上拉加载更多操作");
                getBeforeData();
            }
        });
        recyclerview.mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                recyclerview.mSwipeRefreshLayout.setRefreshing(true);
                getLastData();
            }
        });
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }

    private RequestCall requestCall;

    public void getLastData() {
        requestCall = OkHttpUtils.get().url(API.ZHIHU_NEWS_LATEST).build();
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
                recyclerview.setRefreshing(false);
                Log.e("getLastData", "response:" + response);
                if (!TextUtils.isEmpty(response)) {
                    ZhihuResponse zhihuResponse = GsonUtils.getGson().fromJson(response, ZhihuResponse.class);
                    if (zhihuResponse != null) {
                        willLoadDate = zhihuResponse.getDate();
                        zhihuStories.clear();
                        zhihuStories.addAll(zhihuResponse.getStories());
                        zhihuListAdapter.notifyDataSetChanged();
//                        zhihuListAdapter.insert(zhihuResponse.getStories());
                    } else {
                        Log.e("pictureFragment", "onResponse:zhihuResponse 为 null");
                    }

                }
            }
        });


    }

    public void getBeforeData() {
        requestCall = OkHttpUtils.get().url(API.ZHIHU_NEWS_BEFORE + willLoadDate).build();
        requestCall.execute(new MyStringCallBack(getActivity(), ((MainActivity) getActivity()).getMainCoordinatorLayout()) {
            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                recyclerview.setRefreshing(false);
                Log.e("pictureFragment", "onError:" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                recyclerview.setRefreshing(false);
                Log.e("getLastData", "response:" + response);
                if (!TextUtils.isEmpty(response)) {
                    ZhihuResponse zhihuResponse = GsonUtils.getGson().fromJson(response, ZhihuResponse.class);
                    if (zhihuResponse != null) {
                        willLoadDate = zhihuResponse.getDate();
                        zhihuListAdapter.insert(zhihuResponse.getStories());
                    } else {
                        Log.e("pictureFragment", "onResponse:zhihuResponse 为 null");
                    }

                }
            }
        });
    }
}
