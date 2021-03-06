package com.kk.imgod.knowgirl.customerclass;

import android.app.Activity;
import android.view.View;

import com.kk.imgod.knowgirl.R;
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
public abstract class MyStringCallBack extends LogStringCallBack {
    public MyStringCallBack(Activity activity, View view) {
        super(activity, view);
    }

    @Override
    public void onError(Call call, Exception e) {
        super.onError(call, e);
        if (NetWorkUtils.isNetworkAvailable(activity)) {
            SnackBarUtils.showShort(view, R.string.net_work_error);
        } else {
            SnackBarUtils.showShort(view, R.string.no_net_work);
        }
    }
}
