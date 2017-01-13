package com.kk.imgod.knowgirl.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.app.Constant;
import com.kk.imgod.knowgirl.app.MyApp;
import com.kk.imgod.knowgirl.utils.DataCleanManager;
import com.kk.imgod.knowgirl.utils.Lg;
import com.kk.imgod.knowgirl.utils.Ts;

public class SettingFragment extends PreferenceFragment {
    public static final String CLEAR_CACHE = "clear_cache";//清除缓存
    public static final String ORIGINAL_SPLASH = "original_splash";//开屏设置
    public static final String CHECK_VERSION = "check_version";//检查版本
    public static final String FEEDBACK = "feedback";//反馈
    private Preference clear_cache;
    private Preference original_splash;
    private Preference check_version;
    private Preference feedback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        initPreference();
        initPreferenceTitle();
        initPreferenceEvent();
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
        String url = "mqqwpa://im/chat?chat_type=wpa&uin="+getString(R.string.my_qq);
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

}
