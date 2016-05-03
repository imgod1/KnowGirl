package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.utils.AppUtils;
import com.kk.imgod.knowgirl.utils.QQUtils;

import butterknife.BindView;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.txt_qq)
    TextView txt_qq;
    @BindView(R.id.versionName)
    TextView versionName;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, AboutActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_about;
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

    private void goQQ2Chat() {
        try {
            QQUtils.goQQ2Chat(mActivity, getString(R.string.my_qq));
        } catch (Exception e) {
            Toast.makeText(this, "当前设备没有安装QQ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txt_qq.setText(getString(R.string.qq_title) + getString(R.string.my_qq));
        versionName.setText(getString(R.string.version) + AppUtils.getVersionName(getApplicationContext()));
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initEvent() {
        txt_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goQQ2Chat();
            }
        });
    }
}
