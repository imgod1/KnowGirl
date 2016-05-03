package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kk.imgod.knowgirl.interf.initInterFace;

import butterknife.ButterKnife;

/**
 * Created by imgod on 2016/4/23.
 */
public abstract class BaseActivity extends AppCompatActivity implements initInterFace {

    public Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        mActivity = this;
        ButterKnife.bind(mActivity);
        initView();
        initValue();
        initEvent();
    }

    public abstract int getLayoutResID();
}
