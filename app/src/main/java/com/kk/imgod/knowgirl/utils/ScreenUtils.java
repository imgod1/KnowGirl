package com.kk.imgod.knowgirl.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by imgod on 2016/4/24.
 */
public class ScreenUtils {
    /**
     * 获取屏幕设备信息描述对象
     *
     * @param activity
     * @return
     */
    public final static DisplayMetrics getWindowsDisplayMetrics(
            Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /**
     * 获取屏幕的宽度
     *
     * @param activity
     * @return
     */
    public final static int getWindowsWidth(Activity activity) {
        return getWindowsDisplayMetrics(activity).widthPixels;
    }

    /**
     * 获取屏幕的高度
     *
     * @param activity
     * @return
     */
    public final static int getWindowsHeight(Activity activity) {
        return getWindowsDisplayMetrics(activity).heightPixels;
    }
}
