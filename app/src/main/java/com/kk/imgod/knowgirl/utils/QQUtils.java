package com.kk.imgod.knowgirl.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.utils
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016-04-29 10:11
 * 修改人：gaokang
 * 修改时间：2016-04-29 10:11
 * 修改备注：
 */
public class QQUtils {
    //"mqqwpa://im/chat?chat_type=wpa&uin=970720947"
    private static String QQURL = "mqqwpa://im/chat?chat_type=wpa&uin=";
    public static void goQQ2Chat(Activity activity, String qqNum) throws Exception {
        String url = QQURL + qqNum;
        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
