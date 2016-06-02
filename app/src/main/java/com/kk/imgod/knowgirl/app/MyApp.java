package com.kk.imgod.knowgirl.app;

import android.app.Application;
import android.content.Context;

import com.bugtags.library.Bugtags;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by imgod on 2016/4/23.
 */
public class MyApp extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        initAllSdk();
    }

    private void initAllSdk() {
        initLeakCanary();
        initBugTags();
    }

    private void initLeakCanary() {
        LeakCanary.install(this);
    }

    private void initBugTags() {
        //在这里初始化
        Bugtags.start(Constant.BUGTAGS_KEY, this, Bugtags.BTGInvocationEventBubble);
    }

    public static Context getAppContext() {
        return appContext;
    }
}
