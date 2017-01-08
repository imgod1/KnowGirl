package com.kk.imgod.knowgirl.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.activity.FreshDetailActivity;
import com.kk.imgod.knowgirl.activity.MainActivity;
import com.kk.imgod.knowgirl.adapter.FreshListAdapter;
import com.kk.imgod.knowgirl.adapter.UlimateBaseAdapter;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.app.Constant;
import com.kk.imgod.knowgirl.customerclass.MyStringCallBack;
import com.kk.imgod.knowgirl.model.FreshBean;
import com.kk.imgod.knowgirl.model.FreshResponse;
import com.kk.imgod.knowgirl.utils.DBUtils;
import com.kk.imgod.knowgirl.utils.GsonUtils;
import com.kk.imgod.knowgirl.utils.ImageLoader;
import com.kk.imgod.knowgirl.utils.Lg;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


public class FreshFragment extends RecyclerViewFragment {
    private int page = 1;
    private List<FreshBean> freshBeanList = new ArrayList<>();
    private CommonAdapter<FreshBean> freshAdapter;
    @Override
    protected void initData() {
        super.initData();
        initAdapter();
        initEvent();
//        freshListAdapter.setOnItemClickListener(new UlimateBaseAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                //goto
//                FreshDetailActivity.actionStart(getActivity(), freshBeanList.get(position).getId());
//            }
//        });
//        recyclerview.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                Lg.e("FreshFragment", "触发了下拉刷新操作");
//                page = 1;
//                getFreshData(page);
//            }
//        });

//        recyclerview.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
//            @Override
//            public void loadMore(int itemsCount, int maxLastVisiblePosition) {
//                Lg.e("FreshFragment", "触发了上拉加载更多操作");
//                getFreshData(page);
//            }
//        });
//        recyclerview.mSwipeRefreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                recyclerview.mSwipeRefreshLayout.setRefreshing(true);
//                getFreshData(page);
//            }
//        });

    }

    private void initEvent() {
        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Lg.e("FreshFragment", "触发了下拉刷新操作");
                page = 1;
                getFreshData(page);
            }
        });
    }

    private void initAdapter() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        freshAdapter = new CommonAdapter<FreshBean>(getActivity(), R.layout.item_fresh,freshBeanList) {
            @Override
            protected void convert(ViewHolder holder, FreshBean freshBean, int position) {
                ImageView img_fresh_news = holder.getView(R.id.img_fresh_news);
                TextView txt_fresh_title = holder.getView(R.id.txt_fresh_title);
                ImageLoader.load(getActivity(), freshBean.getCustom_fields().getThumb_c().get(0).getVal(), img_fresh_news, R.drawable.icon_app);
                txt_fresh_title.setText(freshBean.getTitle());
            }
        };

        freshAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                FreshDetailActivity.actionStart(getActivity(), freshBeanList.get(position).getId());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        LoadMoreWrapper loadMoreWrapper = new LoadMoreWrapper(freshAdapter);
        loadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Lg.e("FreshFragment", "触发了上拉加载更多操作");
                getFreshData(page);
            }
        });

        recyclerview.setAdapter(loadMoreWrapper);

    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }

    private RequestCall requestCall;

    public void getFreshData(final int tempPage) {
        requestCall = OkHttpUtils.get().url(API.JIANDAN_FRESH_NEWS + tempPage).build();
        requestCall.execute(new MyStringCallBack(getActivity(), ((MainActivity) getActivity()).getMainCoordinatorLayout()) {
            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                recyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showOrHideRefresh(false);
                    }
                }, Constant.DELAYTIME);
                Log.e("pictureFragment", "onError:" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                showOrHideRefresh(false);
                Log.e("getLastData", "response:" + response);
                if (!TextUtils.isEmpty(response)) {
                    FreshResponse freshResponse = GsonUtils.getGson().fromJson(response, FreshResponse.class);
                    if (freshResponse != null && freshResponse.getStatus().equals(Constant.OK)) {
                        DBUtils.saveList(MainActivity.realm, freshResponse.getPosts());
                        page++;
                        if (1 == tempPage) {
                            freshBeanList.clear();
                            freshBeanList.addAll(freshResponse.getPosts());
                        }
                        recyclerview.getAdapter().notifyDataSetChanged();
                    } else {
                        Log.e("pictureFragment", "onResponse:zhihuResponse 为 null");
                    }
                }
            }
        });
    }


}
