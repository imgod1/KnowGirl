package com.kk.imgod.knowgirl.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kk.imgod.knowgirl.customerclass.LazyFragment;

import butterknife.ButterKnife;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.fragment
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016-05-16 14:45
 * 修改人：gaokang
 * 修改时间：2016-05-16 14:45
 * 修改备注：
 */
public abstract class BaseLazyFragment extends LazyFragment {
    public View parentView;
    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(getLayoutResID(), container, false);
        ButterKnife.bind(this, parentView);
        return parentView;
    }
    public abstract int getLayoutResID();
    @Override
    protected void setDefaultFragmentTitle(String title) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        parentView = null;
    }
}
