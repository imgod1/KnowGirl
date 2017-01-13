package com.kk.imgod.knowgirl.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kk.imgod.knowgirl.interf.initInterFace;

import butterknife.ButterKnife;

/**
 * 项目名称：KnowGirl
 * 类描述fragment基类
 * 创建人：imgod
 * 创建时间：2016/4/24 16:32
 * 修改人：imgod
 * 修改时间：2016/4/24 16:32
 * 修改备注：
 */
public abstract class BaseFragment extends Fragment implements initInterFace {
    public View parentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(getLayoutResID(), container, false);
        ButterKnife.bind(this, parentView);
        initView();
        initValue();
        initEvent();
        return parentView;
    }

    public abstract int getLayoutResID();

    @Override
    public void onDestroy() {
        super.onDestroy();
        parentView = null;
    }
}
