package com.kk.imgod.knowgirl.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by imgod on 2016/4/24.
 */
public class SnackBarUtils {
    public static void showShort(View parentView, int resId) {
        Snackbar.make(parentView, resId, Snackbar.LENGTH_SHORT).show();
    }

    public static void showShort(View parentView, String content) {
        Snackbar.make(parentView, content, Snackbar.LENGTH_SHORT).show();
    }

    public static void showLong(View parentView, int resId) {
        Snackbar.make(parentView, resId, Snackbar.LENGTH_LONG).show();
    }

    public static void showLong(View parentView, String contnet) {
        Snackbar.make(parentView, contnet, Snackbar.LENGTH_LONG).show();
    }
}
