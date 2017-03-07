package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.app.Constant;
import com.kk.imgod.knowgirl.utils.AppUtils;
import com.kk.imgod.knowgirl.utils.Lg;
import com.kk.imgod.knowgirl.utils.QQUtils;
import com.kk.imgod.knowgirl.utils.SPUtils;

import butterknife.BindView;

/**
 * 项目名称：KnowGirl
 * 类描述：关于界面
 * 创建人：imgod
 * 创建时间：2016/4/24 16:20
 * 修改人：imgod
 * 修改时间：2016/4/24 16:20
 * 修改备注：
 */
public class AboutActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.llayout_about)
    LinearLayout llayout_about;
    @BindView(R.id.txt_qq)
    TextView txt_qq;
    @BindView(R.id.versionName)
    TextView versionName;

    private Toast toast;

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
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        String qqTitle = getString(R.string.qq_title) + getString(R.string.my_qq);
        txt_qq.setText(qqTitle);
        String versionTitle = getString(R.string.version) + AppUtils.getVersionName(getApplicationContext());
        versionName.setText(versionTitle);
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initEvent() {
        llayout_about.setOnClickListener(this);
        txt_qq.setOnClickListener(this);
    }

    private int clickTimes = 0;//已经连续点击的次数
    private long lastClickTime = 0;//上次点击的时间

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llayout_about:
                boolean private_mode = (boolean) SPUtils.get(this, Constant.PRIVATE_MODE, false);
                if (private_mode) {
                    //已经处于私密模式
                    showToast("你已经处于私密模式,无需重复点击");
                } else {
                    //尚未处于私密模式
                    long clickTime = System.currentTimeMillis();
                    if (clickTime - lastClickTime > 1000) {//距离上次点击已经很久.不是连续点击
                        clickTime = 1;
                    } else {
                        clickTimes++;
                    }
                    lastClickTime = clickTime;
                    if (clickTimes >= 7) {
                        SPUtils.put(this, Constant.PRIVATE_MODE, true);
                        showToast("已经进入私密模式,主界面看看侧滑看看吧");
                    } else {
                        showToast("还差" + (7 - clickTimes) + "步操作进入私密模式");
                    }
                }
                break;
            case R.id.txt_qq:
                goQQ2Chat();
                break;
            default:
                break;
        }
    }

    /**
     * toast
     *
     * @param content 内容
     */
    private void showToast(String content) {
        toast.setText(content);
        toast.show();
    }
}
