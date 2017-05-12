package com.kk.imgod.knowgirl.fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.activity.MainActivity;
import com.kk.imgod.knowgirl.anim.SlideInLeftAnimation;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.app.Constant;
import com.kk.imgod.knowgirl.customerclass.MyStringCallBack;
import com.kk.imgod.knowgirl.model.SatinBean;
import com.kk.imgod.knowgirl.model.response.SatinResponse;
import com.kk.imgod.knowgirl.utils.DBUtils;
import com.kk.imgod.knowgirl.utils.GsonUtils;
import com.kk.imgod.knowgirl.utils.ImageLoader;
import com.kk.imgod.knowgirl.utils.ShareUtils;
import com.kk.imgod.knowgirl.utils.Ts;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.realm.Sort;
import okhttp3.Call;

/**
 * 项目名称：KnowGirl
 * 类描述：段子
 * 创建人：imgod
 * 创建时间：2017/2/24 16:20
 * 修改人：imgod
 * 修改时间：2017/2/24 16:20
 * 修改备注：
 */
public class SatinFragment extends NormalRecyclerViewFragment {
    @BindView(R.id.srl_main)
    SwipeRefreshLayout srl_main;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private String willLoadDate;
    private List<SatinBean> satinList;

    public static SatinFragment newInstance() {
        Bundle args = new Bundle();
        SatinFragment fragment = new SatinFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private boolean isFirstLoad = true;

    protected void initData() {
        willLoadDate = System.currentTimeMillis() + "";
        satinList = new ArrayList<>();
        //先取数据库
        List<SatinBean> tempList = MainActivity.realm.where(SatinBean.class).findAllSorted("_updated_at", Sort.DESCENDING);
        satinList.addAll(tempList);
        initAdapter();
        initEvent();
        isFirstLoad = true;
        getData(true);
    }

    @Override
    public void initValue() {
        initData();
    }

    @Override
    public void initEvent() {
        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                long currentTime = Long.parseLong(willLoadDate);
                int randomDay = (int) (Math.random() * 100);//0-100
                willLoadDate = "" + (currentTime - Constant.DAY_MILL_SECONDS * randomDay);
                isFirstLoad = false;
                getData(true);
            }
        });
    }

    private void initAdapter() {
        CommonAdapter<SatinBean> satinAdapter = new CommonAdapter<SatinBean>(getActivity(), R.layout.item_satin, satinList) {
            @Override
            protected void convert(ViewHolder holder, final SatinBean satinBean, int position) {
                ImageView img_user_logo = holder.getView(R.id.img_user_logo);
                TextView txt_user_name = holder.getView(R.id.txt_user_name);
                TextView txt_content = holder.getView(R.id.txt_content);
                TextView txt_share = holder.getView(R.id.txt_share);

                ImageLoader.load(getContext(), satinBean.getAvatar(), img_user_logo);
                txt_user_name.setText(satinBean.getUser_name());
                txt_content.setText(satinBean.getContent());
                txt_share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShareUtils.shareText(getContext(), satinBean.getContent());
                    }
                });

                View view = holder.getConvertView();
                SlideInLeftAnimation slideInLeftAnimation = new SlideInLeftAnimation();
                Animator[] animators = slideInLeftAnimation.getAnimators(view);
                animators[0].start();
            }
        };

        LoadMoreWrapper loadMoreWrapper = new LoadMoreWrapper(satinAdapter);
        loadMoreWrapper.setLoadMoreView(R.layout.layout_loading_more);
        loadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData(false);
            }
        });

        recyclerview.setAdapter(loadMoreWrapper);

    }

    private RequestCall requestCall;

    public void getData(final boolean isRefresh) {
        String url = API.SATIN_URL + willLoadDate;
        requestCall = OkHttpUtils.get().url(url).build();
        requestCall.execute(new MyStringCallBack(getActivity(), ((MainActivity) getActivity()).getMainCoordinatorLayout()) {
            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                recyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srl_main.setRefreshing(false);
                    }
                }, Constant.DELAYTIME);
            }

            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                srl_main.setRefreshing(false);
                if (!TextUtils.isEmpty(response)) {
                    SatinResponse satinResponse = GsonUtils.getGson().fromJson(response, SatinResponse.class);
                    if (satinResponse != null) {
                        if (isRefresh) {
                            satinList.clear();
                        }
                        long currentTime = Long.parseLong(willLoadDate);
                        willLoadDate = "" + (currentTime - Constant.DAY_MILL_SECONDS);
                        //数据保存
                        List<SatinBean> tempList = satinResponse.getData();
                        DBUtils.saveList(MainActivity.realm, tempList);
                        //更新视图
                        satinList.addAll(tempList);
                        if (isRefresh) {
                            recyclerview.getAdapter().notifyDataSetChanged();
                            if (!isFirstLoad) {
                                Ts.showShort(getContext(), R.string.refresh_satin);
                            }
                        } else {
                            if (isFirstLoad) {
                                recyclerview.getAdapter().notifyDataSetChanged();
                            } else {
                                recyclerview.getAdapter().notifyItemRangeInserted(satinList.size() - tempList.size(), tempList.size());
                            }
                        }
                        isFirstLoad = false;
                    }

                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != requestCall) {
            requestCall.cancel();
        }
    }
}
