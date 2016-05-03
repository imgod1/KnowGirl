package com.kk.imgod.knowgirl.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.app.MyApp;
import com.kk.imgod.knowgirl.utils.FileUtil;

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
        initPreferenceEvent();
    }

    private void initPreferenceEvent() {
        clear_cache.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getActivity(), "清除缓存", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        original_splash.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean isSelect = (Boolean) newValue;
                if (isSelect) {
                    Toast.makeText(getActivity(), "splash true", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "splash false", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        check_version.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean isSelect = (Boolean) newValue;
                if (isSelect) {
                    Toast.makeText(getActivity(), "check_version true", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "check_version false", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        feedback.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getActivity(), "反馈", Toast.LENGTH_SHORT).show();
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
        clear_cache.setSummary(getString(R.string.set_current_cache) + FileUtil.getFileSize(MyApp.getAppContext().getCacheDir()));
    }

    private void goQQ2Chat() {
        String url = "mqqwpa://im/chat?chat_type=wpa&uin=88888888";
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            Toast.makeText(getActivity(), "当前设备没有安装QQ", Toast.LENGTH_SHORT).show();
        }

    }
}
