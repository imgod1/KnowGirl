package com.kk.imgod.knowgirl.utils;

import android.text.TextUtils;

/**
 * 项目名称：KnowGirl
 * 类描述：字符串操作
 * 创建人：gk
 * 创建时间：2017/2/28 10:52
 * 修改人：gk
 * 修改时间：2017/2/28 10:52
 * 修改备注：
 */
public class StringUtils {
    public static final int JSONP_START_POSITION = 7;//开始截取的下标

    /**
     * 从字符串中拿出json部分
     *
     * @param response 字符串 jsonp1(xxxx)
     * @return json串
     */
    public static String getJsonContentFromResponse(String response) {
        String content = null;
        if (!TextUtils.isEmpty(response) && response.length() > 10) {
            int endPosition = response.lastIndexOf(")");
            content = response.substring(JSONP_START_POSITION, endPosition);
        }
        return content;
    }

    //裁剪title
    public static String subTitle(String fullTitle) {
        String title = null;
        if (!TextUtils.isEmpty(fullTitle) && fullTitle.length() > 9) {
            title = fullTitle.substring(9, fullTitle.length());
        }
        return title;
    }
}
