package com.kk.imgod.knowgirl.activity;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.kk.imgod.knowgirl.R;

import butterknife.BindView;

public class TestActivity extends BaseActivity {

    @BindView(R.id.txt_test)
    TextView txt_test;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {
       String text = "<a href=http://www.baidu.com>花火我最美</a>";
        txt_test.setText(Html.fromHtml(text));
        txt_test.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initEvent() {

    }
}
