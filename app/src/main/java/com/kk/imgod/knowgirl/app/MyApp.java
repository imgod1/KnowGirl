package com.kk.imgod.knowgirl.app;

import android.app.Application;
import android.content.Context;

import com.bugtags.library.Bugtags;
import com.kk.imgod.knowgirl.BuildConfig;
import com.kk.imgod.knowgirl.utils.Lg;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.analytics.MobclickAgent;

import org.polaric.colorful.Colorful;

/**
 * 项目名称：KnowGirl
 * 类描述：application类
 * 创建人：imgod
 * 创建时间：2016/4/23 16:34
 * 修改人：imgod
 * 修改时间：2016/4/23 16:34
 * 修改备注：
 */
public class MyApp extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        initAllSdk();
        Lg.isDebug = BuildConfig.ISDEBUG;
    }

    private void initAllSdk() {
        initColorful();
        initLeakCanary();
        initBugTags();
        initUmeng();
    }

    private void initColorful() {
        Colorful.defaults()
                .primaryColor(Colorful.ThemeColor.PINK)
                .accentColor(Colorful.ThemeColor.PINK)
                .translucent(false)
                .dark(false);
        Colorful.init(this);
    }

    private void initLeakCanary() {
        LeakCanary.install(this);
    }

    private void initBugTags() {
        //在这里初始化 BTGInvocationEventShake:摇一摇调出反馈界面
        Bugtags.start(Constant.BUGTAGS_KEY, this, Bugtags.BTGInvocationEventShake);
    }

    private void initUmeng() {
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }

    public static Context getAppContext() {
        return appContext;
    }
}
