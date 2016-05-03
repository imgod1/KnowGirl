package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.utils.ImageLoader;

import java.lang.ref.WeakReference;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.img_splash)
    ImageView img_splash;

    public static final int SPLASH_SHOW_TIME = 2000;

    private SplashHandler splashHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        splashHandler = new SplashHandler(mActivity);
        ImageLoader.load(mActivity,"http://tnfs.tngou.net/img/ext/160415/797873b2b7095918bc3c6beb24780042.jpg",R.anim.splash_anim,img_splash);
//        Glide.with(mActivity).load(R.drawable.splash).crossFade(SPLASH_SHOW_TIME).into(img_splash);
        splashHandler.sendEmptyMessageDelayed(0, SPLASH_SHOW_TIME);
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initEvent() {

    }

    public void goMain() {
        MainActivity.actionStart(mActivity);
        finish();
    }

    @Override
    public void onBackPressed() {
        return;
    }

    public static class SplashHandler extends Handler {
        WeakReference<Activity> weakReferenceActivity;

        public SplashHandler(Activity activity) {
            weakReferenceActivity = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SplashActivity splashActivity = (SplashActivity) weakReferenceActivity.get();
            if (splashActivity != null) {
                splashActivity.goMain();
            } else {
                Log.e("splash", "weakReferenceActivity null");
            }
        }
    }


}
