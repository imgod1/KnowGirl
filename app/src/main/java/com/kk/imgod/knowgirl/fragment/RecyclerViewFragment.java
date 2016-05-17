package com.kk.imgod.knowgirl.fragment;

import com.kk.imgod.knowgirl.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

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
    @BindView(R.id.recyclerview)
    UltimateRecyclerView recyclerview;

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_news;
    }
}
