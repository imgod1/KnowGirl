package com.kk.imgod.knowgirl.utils;

import com.google.gson.Gson;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.utils
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016-05-16 16:13
 * 修改人：gaokang
 * 修改时间：2016-05-16 16:13
 * 修改备注：
 */
public class GsonUtils {
    private static GsonUtils gsonUtils;
    private static Gson gson;
    private GsonUtils() {
        gson = new Gson();
    }
    public static Gson getGson() {
        if (gsonUtils == null) {
            gsonUtils = new GsonUtils();
        }
        return gson;
    }
}
