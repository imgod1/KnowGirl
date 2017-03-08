package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.app.Constant;
import com.kk.imgod.knowgirl.fragment.SettingFragment;
import com.kk.imgod.knowgirl.model.BooHeeModel;
import com.kk.imgod.knowgirl.utils.DateUtils;
import com.kk.imgod.knowgirl.utils.GsonUtils;
import com.kk.imgod.knowgirl.utils.ImageLoader;
import com.kk.imgod.knowgirl.utils.Lg;
import com.kk.imgod.knowgirl.utils.NetWorkUtils;
import com.kk.imgod.knowgirl.utils.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.lang.ref.WeakReference;
import java.util.Date;

import butterknife.BindView;
import okhttp3.Call;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.img_splash)
    ImageView img_splash;
    public static final int SPLASH_SHOW_TIME = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        transparentStatusBar();
        super.onCreate(savedInstanceState);
    }

    /**
     * 检测系统版本并使状态栏全透明
     */
    protected void transparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        SplashHandler splashHandler = new SplashHandler(mActivity);
        boolean original_splash = (boolean) SPUtils.getValueFromDefaulatSP(mActivity, SettingFragment.ORIGINAL_SPLASH, Boolean.TRUE);
        if (original_splash) {
            String imgurl = (String) SPUtils.get(mActivity, Constant.SPLASHIMGURL, "");
            if (TextUtils.isEmpty(imgurl)) {
                ImageLoader.load(mActivity, R.drawable.splash, R.anim.splash_anim, img_splash);
            } else {
                ImageLoader.load(mActivity, imgurl, R.anim.splash_anim, img_splash);
            }
            calcSplash();
        } else {
            ImageLoader.load(mActivity, R.drawable.splash, R.anim.splash_anim, img_splash);
        }
//        Glide.with(mActivity).load(R.drawable.splash).crossFade(SPLASH_SHOW_TIME).into(img_splash);
        splashHandler.sendEmptyMessageDelayed(0, SPLASH_SHOW_TIME);
    }

    /**
     * 计算看需要不需要加载下次的启动图
     */
    private void calcSplash() {
        String splash_date = (String) SPUtils.get(mActivity, Constant.SPLASHDATE, "");
        String nowDate = DateUtils.parseStandardDateWith_(new Date());
        if (!splash_date.equals(nowDate)) {
            getBooheeImgAndSave();
        }
    }

    private void getBooheeImgAndSave() {
        if (NetWorkUtils.isNetworkAvailable(mActivity)) {
            getData();
        } else {
            Lg.e(mActivity.getClass().getSimpleName(), "没有网络,不去获取图片数据");
        }
    }


    private RequestCall requestCall;
    private BooHeeModel booHeeModel;

    private void getData() {
        requestCall = OkHttpUtils.get().url(API.BOOHEE_WELCOME_IMG).build();
        requestCall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.e("pictureFragment", "onError:" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                if (!TextUtils.isEmpty(response)) {
                    booHeeModel = GsonUtils.getGson().fromJson(response, BooHeeModel.class);
                    if (booHeeModel != null) {
                        BooHeeModel.WelcomeImgBean.WeekImagesBean imagesBean = booHeeModel.getWelcome_img().getWeek_images().get(booHeeModel.getWelcome_img().getWeek_images().size() - 1);
                        SPUtils.put(mActivity, Constant.SPLASHDATE, imagesBean.getDate());
                        SPUtils.put(mActivity, Constant.SPLASHIMGURL, imagesBean.getBack_img());
                        cacheImage(mActivity, imagesBean.getBack_img());
                    }
                }
            }
        });
    }


    public void cacheImage(final Activity activity, final String imgurl) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                ImageLoader.load2cacheImg(activity, imgurl);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (requestCall != null) {
            requestCall.cancel();
        }
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
    }

    public static class SplashHandler extends Handler {
        WeakReference<Activity> weakReferenceActivity;

        SplashHandler(Activity activity) {
            weakReferenceActivity = new WeakReference<>(activity);
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
