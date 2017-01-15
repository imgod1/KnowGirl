package com.kk.imgod.knowgirl.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by imgod on 2017/1/15.
 */

public class DialogUtils {
    /**
     * 弹出框
     *
     * @param context 上下文
     * @param title   标题
     * @param content 内容
     * @param sure    确定
     * @param cancel  取消
     */
    public static void showDialog(Context context, String title, String content, DialogInterface.OnClickListener sure, DialogInterface.OnClickListener cancel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(content);
        builder.setTitle(title);
        builder.setNegativeButton("取消", cancel);
        builder.setPositiveButton("确定", sure);
        builder.show();
    }
}
