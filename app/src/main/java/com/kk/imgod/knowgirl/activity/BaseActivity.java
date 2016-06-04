package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.bugtags.library.Bugtags;
import com.kk.imgod.knowgirl.interf.initInterFace;
import com.umeng.analytics.MobclickAgent;

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

    @Override
    protected void onResume() {
        super.onResume();
        Bugtags.onResume(this);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //注：回调 2
        Bugtags.onPause(this);
        MobclickAgent.onPause(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //注：回调 3
        Bugtags.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }
}
