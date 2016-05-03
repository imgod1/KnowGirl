package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kk.imgod.knowgirl.R;

public class PictureDetailActivity extends BaseActivity {


    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, PictureDetailActivity.class);
        activity.startActivity(intent);
    }


    @Override
    public int getLayoutResID() {
        return R.layout.activity_picture_detail;
    }


    @Override
    public void initView() {

    }

    @Override
    public void initValue() {

    }

    @Override
    public void initEvent() {

    }

}
