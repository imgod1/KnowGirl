package com.kk.imgod.knowgirl.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.adapter.ZhihuListAdapter;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.model.ZhihuResponse;
import com.kk.imgod.knowgirl.model.ZhihuStory;
import com.kk.imgod.knowgirl.utils.DateUtils;
import com.kk.imgod.knowgirl.utils.GsonUtils;
import com.kk.imgod.knowgirl.utils.Lg;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;


public class ZhihuFragment extends BaseLazyFragment {
    private String willLoadDate;
    @BindView(R.id.recyclerview)
    UltimateRecyclerView recyclerview;
    private ZhihuListAdapter zhihuListAdapter;
    private List<ZhihuStory> zhihuStories;

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initData() {
        willLoadDate = DateUtils.parseStandardDate(new Date());
        recyclerview.enableDefaultSwipeRefresh(true);
        zhihuStories = new ArrayList<>();
        zhihuListAdapter = new ZhihuListAdapter(getActivity(), zhihuStories);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(zhihuListAdapter);
        recyclerview.reenableLoadmore();
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
            }
        });

        getLastData();

    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }

    private RequestCall requestCall;

    public void getLastData() {
        requestCall = OkHttpUtils.get().url(API.ZHIHU_NEWS_LATEST).build();
        requestCall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                recyclerview.setRefreshing(false);
                Log.e("pictureFragment", "onError:" + e.getMessage());
                Toast.makeText(getActivity(), "onError:" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
        requestCall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                recyclerview.setRefreshing(false);
                Log.e("pictureFragment", "onError:" + e.getMessage());
                Toast.makeText(getActivity(), "onError:" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
