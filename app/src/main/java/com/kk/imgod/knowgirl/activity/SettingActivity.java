package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.kk.imgod.knowgirl.R;

public class SettingActivity extends BaseActivity {

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, SettingActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
