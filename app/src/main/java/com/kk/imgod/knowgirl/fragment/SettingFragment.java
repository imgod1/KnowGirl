package com.kk.imgod.knowgirl.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.activity.SettingActivity;
import com.kk.imgod.knowgirl.app.Constant;
import com.kk.imgod.knowgirl.app.MyApp;
import com.kk.imgod.knowgirl.utils.DataCleanManager;
import com.kk.imgod.knowgirl.utils.Lg;
import com.kk.imgod.knowgirl.utils.Ts;

import org.polaric.colorful.Colorful;

public class SettingFragment extends PreferenceFragment {
    public static final String CLEAR_CACHE = "clear_cache";//清除缓存
    public static final String ORIGINAL_SPLASH = "original_splash";//开屏设置
    public static final String ORIGINAL_NIGHT_MODE = "original_night_mode";//夜间模式
    public static final String CHECK_VERSION = "check_version";//检查版本
    public static final String FEEDBACK = "feedback";//反馈
    private Preference clear_cache;
    private Preference original_splash;
    private Preference original_night_mode;
    private Preference check_version;
    private Preference feedback;
    private SettingActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        initPreference();
        initPreferenceTitle();
        initPreferenceEvent();
        activity = (SettingActivity) getActivity();
    }

    private void initPreferenceEvent() {
        clear_cache.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                clearCache();
                return true;
            }
        });
        original_splash.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean isSelect = (Boolean) newValue;
                return true;
            }
        });

        original_night_mode.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean isSelect = (Boolean) newValue;
                if (isSelect) {
                    Colorful.config(activity).primaryColor(Colorful.ThemeColor.BLACK).accentColor(Colorful.ThemeColor.BLACK).dark(isSelect).apply();
                } else {
                    Colorful.config(activity).primaryColor(Colorful.ThemeColor.PINK).accentColor(Colorful.ThemeColor.PINK).dark(isSelect).apply();
                }


//                Animator animator = createCheckoutRevealAnimator(getView(), 200, 200, 10, 1000);
//                if (null != animator) {
//                    animator.start();
//                } else {
                activity.onResumeCall();
//                }
                return true;
            }
        });

        check_version.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean isSelect = (Boolean) newValue;
                return true;
            }
        });

        feedback.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Ts.showShort(getActivity(), R.string.goqq2chat);
                goQQ2Chat();
                return true;
            }
        });

    }

    private void initPreference() {
        clear_cache = findPreference(CLEAR_CACHE);
        original_splash = findPreference(ORIGINAL_SPLASH);
        original_night_mode = findPreference(ORIGINAL_NIGHT_MODE);
        check_version = findPreference(CHECK_VERSION);
        feedback = findPreference(FEEDBACK);
    }

    private void initPreferenceTitle() {
//        String title = FileUtil.getFileSize(MyApp.getAppContext().getCacheDir());
        String title = null;
        try {
            title = DataCleanManager.getCacheSize(MyApp.getAppContext().getCacheDir());
        } catch (Exception e) {
            e.printStackTrace();
            Lg.e("settingfragment", "获取缓存大小时发生异常");
        }
        clear_cache.setSummary(getString(R.string.set_current_cache) + title);
        Lg.e("settingfragment", "当前的缓存大小为:" + title);
    }

    private void goQQ2Chat() {
        String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + getString(R.string.my_qq);
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            Toast.makeText(getActivity(), R.string.no_install_qq, Toast.LENGTH_SHORT).show();
        }
    }


    public void clearCache() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showProgress();
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(Constant.DELAYTIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DataCleanManager.cleanInternalCache(MyApp.getAppContext());
//                Glide.get(getActivity()).clearDiskCache();//用glide的清除缓存能清掉缓存.但是不想用了
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                hideProgress();
                clear_cache.setSummary(getString(R.string.set_current_cache) + "0.00M");
            }
        }.execute();

    }

    private ProgressDialog progressDialog;

    private void showProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("清除缓存中..");
        }
        progressDialog.show();
    }

    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

    }

    protected Animator createCheckoutRevealAnimator(final View view, int x, int y, float startRadius, float endRadius) {
        setMenuVisibility(false);
        Animator retAnimator = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            retAnimator = ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, endRadius);
            retAnimator.setDuration(300);
            retAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    activity.onResumeCall();
                }
            });
            retAnimator.setInterpolator(new AccelerateInterpolator(2.0f));
        }

        return retAnimator;
    }
}
