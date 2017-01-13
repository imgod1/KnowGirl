package com.kk.imgod.knowgirl.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.customerclass.WrapContentLinearLayoutManager;

import butterknife.BindView;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.fragment
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016-05-17 11:37
 * 修改人：gaokang
 * 修改时间：2016-05-17 11:37
 * 修改备注：
 */
public abstract class RecyclerViewFragment extends BaseLazyFragment {
    @BindView(R.id.srl_main)
    SwipeRefreshLayout srl_main;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    protected void initData() {
        //设置刷新时动画的颜色，可以设置4个
        srl_main.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(getContext());
        recyclerview.setLayoutManager(wrapContentLinearLayoutManager);
        recyclerview.setHasFixedSize(true);
    }

    /**
     * 展示或者隐藏刷新视图
     *
     * @param isShow 展示或者隐藏刷新视图
     */
    public void showOrHideRefresh(boolean isShow) {
        srl_main.setRefreshing(isShow);
    }
}
