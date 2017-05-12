package com.kk.imgod.knowgirl.customerclass;

import android.app.Activity;
import android.view.View;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.utils.Lg;
import com.kk.imgod.knowgirl.utils.NetWorkUtils;
import com.kk.imgod.knowgirl.utils.SnackBarUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.customerclass
 * 类描述：网络请求的回调
 * 创建人：gaokang
 * 创建时间：2016-05-23 9:53
 * 修改人：gaokang
 * 修改时间：2016-05-23 9:53
 * 修改备注：
 */
public abstract class LogStringCallBack extends StringCallback {
    public Activity activity;
    public View view;

    public LogStringCallBack(Activity activity, View view) {
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void onResponse(String response) {
        Lg.e("onResponse", "response:" + response);
    }

    @Override
    public void onError(Call call, Exception e) {
        String message = "";
        if (null != e) {
            message = e.getMessage();
        }
        Lg.e("onError", "onError:" + message);
    }
}
