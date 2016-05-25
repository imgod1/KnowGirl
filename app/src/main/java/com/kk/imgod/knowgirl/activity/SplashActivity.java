package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.app.Constant;
import com.kk.imgod.knowgirl.fragment.SettingFragment;
import com.kk.imgod.knowgirl.utils.ImageLoader;
import com.kk.imgod.knowgirl.utils.Lg;
import com.kk.imgod.knowgirl.utils.SPUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.img_splash)
    ImageView img_splash;
    //http://tnfs.tngou.net/img/ext/160415/797873b2b7095918bc3c6beb24780042.jpg
    private String imgurl = "http://up.boohee.cn/house/u/one/wallpaper/319_big.jpg";
    private boolean original_splash = true;
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
        original_splash = (boolean) SPUtils.get(mActivity, SettingFragment.ORIGINAL_SPLASH, Boolean.TRUE);
        Lg.e("original_splash:" + original_splash);
        if (original_splash) {
            ImageLoader.load(mActivity, imgurl, R.anim.splash_anim, img_splash);
        } else {
            ImageLoader.load(mActivity, R.drawable.splash, R.anim.splash_anim, img_splash);
        }
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
