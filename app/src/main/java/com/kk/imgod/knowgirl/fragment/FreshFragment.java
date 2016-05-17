package com.kk.imgod.knowgirl.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kk.imgod.knowgirl.activity.FreshDetailActivity;
import com.kk.imgod.knowgirl.adapter.FreshListAdapter;
import com.kk.imgod.knowgirl.adapter.UlimateBaseAdapter;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.app.Constant;
import com.kk.imgod.knowgirl.model.FreshBean;
import com.kk.imgod.knowgirl.model.FreshResponse;
import com.kk.imgod.knowgirl.utils.GsonUtils;
import com.kk.imgod.knowgirl.utils.Lg;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


public class FreshFragment extends RecyclerViewFragment {
    private int page = 1;
    private FreshListAdapter freshListAdapter;
    private List<FreshBean> freshBeanList;

    @Override
    protected void initData() {
        recyclerview.enableDefaultSwipeRefresh(true);
        freshBeanList = new ArrayList<>();
        freshListAdapter = new FreshListAdapter(getActivity(), freshBeanList);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(freshListAdapter);
        recyclerview.reenableLoadmore();
        recyclerview.mRecyclerView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_INSET);
        freshListAdapter.setOnItemClickListener(new UlimateBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //goto
                FreshDetailActivity.actionStart(getActivity(), freshBeanList.get(position));
            }
        });
        recyclerview.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Lg.e("FreshFragment", "触发了下拉刷新操作");
                page = 1;
                getFreshData(page);
            }
        });

        recyclerview.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {
                Lg.e("FreshFragment", "触发了上拉加载更多操作");
                getFreshData(page);
            }
        });
        recyclerview.mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                recyclerview.mSwipeRefreshLayout.setRefreshing(true);
                getFreshData(page);
            }
        });

    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }

    private RequestCall requestCall;

    public void getFreshData(final int tempPage) {
        requestCall = OkHttpUtils.get().url(API.JIANDAN_FRESH_NEWS + tempPage).build();
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
                    FreshResponse freshResponse = GsonUtils.getGson().fromJson(response, FreshResponse.class);
                    if (freshResponse != null && freshResponse.getStatus().equals(Constant.OK)) {
                        page++;
                        if (1 == tempPage) {
                            freshBeanList.clear();
                            freshBeanList.addAll(freshResponse.getPosts());
                            freshListAdapter.notifyDataSetChanged();
                        } else {
                            freshListAdapter.insert(freshResponse.getPosts());
                        }
                    } else {
                        Log.e("pictureFragment", "onResponse:zhihuResponse 为 null");
                    }
                }
            }
        });
    }


}
