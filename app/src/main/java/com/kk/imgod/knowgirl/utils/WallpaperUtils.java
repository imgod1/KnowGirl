package com.kk.imgod.knowgirl.utils;

import android.app.Activity;
import android.app.WallpaperManager;
import android.graphics.Bitmap;

import java.io.IOException;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.utils
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016-05-26 11:25
 * 修改人：gaokang
 * 修改时间：2016-05-26 11:25
 * 修改备注：
 */
public class WallpaperUtils {
    public static boolean setWallpaperByBitmap(Activity activity, Bitmap bitmap) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(activity);
        try {
            wallpaperManager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
