package com.kk.imgod.knowgirl.activity;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.utils.SnackBarUtils;

import butterknife.BindView;

public class TestActivity extends BaseActivity {

    @BindView(R.id.txt_test)
    TextView txt_test;
    @BindView(R.id.btn_test)
    AppCompatButton btn_test;
    @BindView(R.id.v_test)
    View v_test;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {
        String text = "<a href=http://www.baidu.com>花火我最美</a>";
        txt_test.setText(Html.fromHtml(text));
        txt_test.setMovementMethod(LinkMovementMethod.getInstance());

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackBarUtils.showShort(v, "Button");
            }
        });

        v_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackBarUtils.showShort(v, "View");
            }
        });

    }

    @Override
    public void initValue() {

    }

    @Override
    public void initEvent() {

    }
}
