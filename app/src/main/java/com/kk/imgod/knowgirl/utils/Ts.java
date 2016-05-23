package com.kk.imgod.knowgirl.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.utils
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016-05-23 14:24
 * 修改人：gaokang
 * 修改时间：2016-05-23 14:24
 * 修改备注：
 */
public class Ts {
    public static void showShort(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context, @StringRes int contentRes) {
        Toast.makeText(context, context.getString(contentRes), Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, @StringRes int contentRes) {
        Toast.makeText(context, context.getString(contentRes), Toast.LENGTH_SHORT).show();
    }
}
