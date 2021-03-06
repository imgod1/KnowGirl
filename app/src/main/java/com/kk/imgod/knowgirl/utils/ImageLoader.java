package com.kk.imgod.knowgirl.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

import java.util.concurrent.ExecutionException;

/**
 * 对第三方框架进行二次封装.方便以后框架的改变以及代码中的大体修改
 */
public class ImageLoader {

    public static void load(Context context, String url, ImageView view) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(view);
    }

    public static void load(Context context, String url, ImageView view, int placeHolder) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeHolder)
                .crossFade()
                .into(view);
    }

    public static void loadWithHolder(Context context, String url, int imgRes, ImageView view) {
        Glide.with(context)
                .load(url)
                .placeholder(imgRes)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(view);
    }

    public static void load(Context context, int resourceId, ImageView view) {
        Glide.with(context)
                .load(resourceId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(view);
    }

    public static void loadWithHighPriority(Context context, String url, ImageView view) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(view);
    }

    public static void load(Context context, String url, int animationId, ImageView view) {
        Glide.with(context)
                .load(url)
                .animate(animationId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }

    public static void load(Context context, int imgRes, int animationId, ImageView view) {
        Glide.with(context)
                .load(imgRes)
                .animate(animationId)
                .into(view);
    }

    public static Bitmap load2cacheImg(Activity activity, String imgUrl) {
        Bitmap bitmap = null;
        try {
            if (activity != null) {
                bitmap = Glide.with(activity)
                        .load(imgUrl)
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
