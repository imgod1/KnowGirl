package com.kk.imgod.knowgirl.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.utils
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016-05-16 16:37
 * 修改人：gaokang
 * 修改时间：2016-05-16 16:37
 * 修改备注：
 */
public class DateUtils {
    /**
     * Parse Date to String
     *
     * @param date Date object
     * @return "yyyyMMdd" String
     */
    public static String parseStandardDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        formatter.setLenient(false);
        return formatter.format(date);
    }
}
