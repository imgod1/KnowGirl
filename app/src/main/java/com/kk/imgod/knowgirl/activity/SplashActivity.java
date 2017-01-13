package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
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
    //http://tnfs.tngou.net/img/ext/160415/797873b2b7095918bc3c6beb24780042.jpg
    private String imgurl = "http://up.boohee.cn/house/u/one/wallpaper/319_big.jpg";
    private boolean original_splash = true;
    public static final int SPLASH_SHOW_TIME = 2000;
    private String splash_date;
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
        original_splash = (boolean) SPUtils.getValueFromDefaulatSP(mActivity, SettingFragment.ORIGINAL_SPLASH, Boolean.TRUE);
        if (original_splash) {
            imgurl = (String) SPUtils.get(mActivity, Constant.SPLASHIMGURL, "");
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
        splash_date = (String) SPUtils.get(mActivity, Constant.SPLASHDATE, "");
        String nowDate = DateUtils.parseStandardDateWith_(new Date());
        if (!splash_date.equals(nowDate)) {
            getBooheeImgAndSave();
        } else {
        }
    }

    private void getBooheeImgAndSave() {
        if (NetWorkUtils.isNetworkAvailable(mActivity)) {
            getData();
        }  else {
            Lg.e(mActivity.getClass().getSimpleName(),"没有网络,不去获取图片数据");
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
                Log.e("getLastData", "getData response:" + response);
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
