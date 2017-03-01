package com.kk.imgod.knowgirl.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 项目名称：KnowGirl
 * 类描述：assets操作集合类
 * 创建人：gk
 * 创建时间：2017/3/1 10:46
 * 修改人：gk
 * 修改时间：2017/3/1 10:46
 * 修改备注：
 */
public class AssetsUtils {
    /**
     * 从assets中拿到字符串
     *
     * @param context  上下文对象
     * @param filePath 文件路径 例如:css/news.css
     * @return
     */
    public static String getFromAssets(Context context, String filePath) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(filePath));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
