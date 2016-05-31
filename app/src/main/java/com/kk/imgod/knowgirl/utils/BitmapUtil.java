package com.kk.imgod.knowgirl.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;

import com.kk.imgod.knowgirl.R;

/**
 * Bitmap convert utils
 */
public class BitmapUtil {

    public static Bitmap drawableToBitmap(Context appContext, Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    public static Uri bitmapToUri(Context appContext, Bitmap bitmap) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(appContext.getContentResolver(), bitmap, appContext.getString(R.string.knowgirl_girl), null);
        return Uri.parse(path);
    }

    public static Uri drawableToUri(Context appContext, Drawable drawable) {
        return bitmapToUri(appContext, drawableToBitmap(appContext, drawable));
    }
}
