package com.kk.imgod.knowgirl.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.utils
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016-05-23 9:47
 * 修改人：gaokang
 * 修改时间：2016-05-23 9:47
 * 修改备注：
 */
public class NetWorkUtils {
    public static boolean isNetworkAvailable(Activity activity) {

        ConnectivityManager manager = (ConnectivityManager) activity
                .getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnected() && info.isAvailable()) {
            return true;
        }
        return false;

    }
}
