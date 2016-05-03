package com.kk.imgod.knowgirl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kk.imgod.knowgirl.interf.initInterFace;

import butterknife.ButterKnife;

/**
 * Created by imgod on 2016/4/24.
 */
public abstract class BaseFragment extends Fragment implements initInterFace {
    public View parentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
