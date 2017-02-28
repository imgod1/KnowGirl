package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.app.Constant;
import com.kk.imgod.knowgirl.customerclass.MyStringCallBack;
import com.kk.imgod.knowgirl.fragment.GifGroupFragment;
import com.kk.imgod.knowgirl.fragment.SatinFragment;
import com.kk.imgod.knowgirl.fragment.SettingFragment;
import com.kk.imgod.knowgirl.fragment.TabFragment;
import com.kk.imgod.knowgirl.model.AppVersion;
import com.kk.imgod.knowgirl.utils.AppUtils;
import com.kk.imgod.knowgirl.utils.DialogUtils;
import com.kk.imgod.knowgirl.utils.GsonUtils;
import com.kk.imgod.knowgirl.utils.IntentUtils;
import com.kk.imgod.knowgirl.utils.SPUtils;
import com.kk.imgod.knowgirl.utils.ShareUtils;
import com.kk.imgod.knowgirl.utils.SnackBarUtils;
import com.kk.imgod.knowgirl.utils.Ts;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.File;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.Call;

/**
 * 项目名称：KnowGirl
 * 类描述：主界面
 * 创建人：imgod
 * 创建时间：2016/4/24 16:20
 * 修改人：imgod
 * 修改时间：2016/4/24 16:20
 * 修改备注：
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final int KNOWLEDGE_FRAGMENT = 0x00;//知乎日报
    public static final int PICTURE_FRAGMENT = 0x01;//美图
    public static Realm realm;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.dlayout_main)
    DrawerLayout dlayout_main;
    @BindView(R.id.nview_left)
    NavigationView nview_left;
    @BindView(R.id.cLayout_main)
    CoordinatorLayout cLayout_main;

    private Fragment knowledgeFragment;
    private Fragment pictureFragment;
    private Fragment satinFragment;
    private Fragment gifGroupFragment;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(MainActivity.this).name(Constant.REALMNAME).build());
        realm = Realm.getDefaultInstance();
        initAppBar();
        knowledgeFragment = TabFragment.newInstance(MainActivity.KNOWLEDGE_FRAGMENT);
        showCurrentFragment(knowledgeFragment);
    }

    public Realm getRealm() {
        return realm;
    }


    //实践证明此处不管是传递DrawLayout还是CoordinatorLayout 都可以让fragment的snackbar很好的使用
    public CoordinatorLayout getMainCoordinatorLayout() {
        return cLayout_main;
    }

    private void initAppBar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (null != getSupportActionBar()) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(mActivity, dlayout_main, toolbar, R.string.draw_open, R.string.draw_close);
        dlayout_main.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        nview_left.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void initValue() {
        if ((boolean) SPUtils.getValueFromDefaulatSP(mActivity, SettingFragment.CHECK_VERSION, Boolean.TRUE)) {
            checkAppVersion();
        }
    }

    @Override
    public void initEvent() {
        nview_left.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        dlayout_main.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.menu_news:
                if (null == knowledgeFragment) {
                    knowledgeFragment = TabFragment.newInstance(MainActivity.KNOWLEDGE_FRAGMENT);
                }
                showCurrentFragment(knowledgeFragment);
                break;
            case R.id.menu_satin:
                if (null == satinFragment) {
                    satinFragment = SatinFragment.newInstance();
                }
                showCurrentFragment(satinFragment);
                break;
            case R.id.menu_girl:
                if (null == pictureFragment) {
                    pictureFragment = TabFragment.newInstance(MainActivity.PICTURE_FRAGMENT);
                }
                showCurrentFragment(pictureFragment);
                break;
            case R.id.menu_gif:
                if (null == gifGroupFragment) {
                    gifGroupFragment = GifGroupFragment.newInstance();
                }
                showCurrentFragment(gifGroupFragment);
                break;
            case R.id.menu_share:
                ShareUtils.shareText(mActivity, getString(R.string.share_app_description));
                break;
            case R.id.menu_setting:
                SettingActivity.actionStart(this);
                break;
            case R.id.menu_about:
                AboutActivity.actionStart(this);
                break;
        }
        return true;
    }

    private void showCurrentFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        Add2ShowFragment(fragmentTransaction, fragment);
        fragmentTransaction.commit();
    }

    private void Add2ShowFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.flayout_content, fragment);
        }
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        hideFragment(fragmentTransaction, knowledgeFragment);
        hideFragment(fragmentTransaction, pictureFragment);
        hideFragment(fragmentTransaction, satinFragment);
        hideFragment(fragmentTransaction, gifGroupFragment);
    }

    /**
     * 隐藏某个fragment
     *
     * @param fragmentTransaction 事务
     * @param fragment            隐藏的fragment
     */
    private void hideFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        if (null != fragment && fragment.isAdded()) {
            fragmentTransaction.hide(fragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_option_about:
                AboutActivity.actionStart(this);
                break;
            case R.id.menu_option_exit:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (dlayout_main.isDrawerOpen(GravityCompat.START)) {
            dlayout_main.closeDrawer(GravityCompat.START);
            return;
        }

        dubbleBack2Exit();
    }

    private long exitTime = 0;

    private void dubbleBack2Exit() {
        long pressTime = System.currentTimeMillis();
        if (pressTime - exitTime > 2000) {
            exitTime = pressTime;
            SnackBarUtils.showShort(dlayout_main, R.string.press_back_twice);
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != realm) {
            realm.close();
        }
        if (null != PictureDetailActivity.detailImageBeanList) {
            PictureDetailActivity.detailImageBeanList.clear();
            PictureDetailActivity.detailImageBeanList = null;
        }
        if (null != requestCall) {
            requestCall.cancel();
        }
    }

    //1.先检查服务器端的版本信息
    //2.如果有更新,判断本地是否已经下载好了安装包
    //2.1 如果已经下载好了,那就弹窗询问用户是否安装
    //2.2 如果没有下载好.那就网络请求,下载安装包,下载完成之后 询问是否安装

    private String newAppFileName;//新版本app的名字
    private RequestCall requestCall;

    /**
     * 检查app版本信息
     */
    private void checkAppVersion() {
        requestCall = OkHttpUtils.get().url(API.CHECK_APP_VERSION_URL).build();
        requestCall.execute(new MyStringCallBack(MainActivity.this, dlayout_main) {
            @Override
            public void onResponse(String response) {
                if (!TextUtils.isEmpty(response)) {
                    AppVersion appVersion = GsonUtils.getGson().fromJson(response, AppVersion.class);
                    int localVersion = AppUtils.getVersionCode(MainActivity.this);
                    if (localVersion < appVersion.getVersionCode()) {//小于服务器版本的话 说明有更新
                        newAppFileName = Constant.APK_NAME + "_" + AppUtils.getAppMetaData(MainActivity.this, "UMENG_CHANNEL") + "_v" + appVersion.getVersionName() + Constant.APK_FILE_TYPE;
                        File apkFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), newAppFileName);
                        if (apkFile.exists()) {//如果文件存在
                            showInstallDialog();
                        } else {
                            showShouldDownloadDialog();
                        }
                    }
                }
            }
        });
    }

    /**
     * 网络请求 下载apk
     *
     * @param fileName 文件名字
     */
    private void downloadApp(String fileName) {
        showProgressDialog();
        String downloadPath = API.DOWNLOAD_APP_VERSION_URL + newAppFileName;
        requestCall = OkHttpUtils.get().url(downloadPath).build();
        requestCall.execute(new FileCallBack(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), fileName) {
            @Override
            public void inProgress(float progress, long total) {
                updateProgressDialog((int) (progress * 100));
            }

            @Override
            public void onError(Call call, Exception e) {
                hideProgressDialog();
                Ts.showShort(getApplicationContext(), R.string.download_file_error);
            }

            @Override
            public void onResponse(File response) {
                hideProgressDialog();
                showInstallDialog();
            }
        });
    }

    /**
     * 询问是否安装的对话框
     */
    private void showInstallDialog() {
        DialogUtils.showDialog(MainActivity.this, getString(R.string.remind), getString(R.string.apk_download_success), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //确定
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), newAppFileName);
                Intent installIntent = IntentUtils.getInstallAppIntent(getApplicationContext(), file);
                startActivity(installIntent);

            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //取消
            }
        });
    }

    /**
     * 询问是否下载的对话框
     */
    private void showShouldDownloadDialog() {
        DialogUtils.showDialog(MainActivity.this, getString(R.string.remind), getString(R.string.app_have_new_version), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //确定
                downloadApp(newAppFileName);
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //取消
            }
        });
    }

    private ProgressDialog progressDialog;

    /**
     * 展示对话框
     */
    private void showProgressDialog() {
        if (null == progressDialog) {//没有则创建
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle(getString(R.string.download_progress));
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setProgress(0);
        }
        if (!progressDialog.isShowing()) {//不在显示中.则显示出来
            progressDialog.show();
        }
    }

    /**
     * 更新对话框进度
     *
     * @param progress 进度
     */
    private void updateProgressDialog(int progress) {
        if (null != progressDialog && progressDialog.isShowing()) {
            progressDialog.setProgress(progress);
        }
    }

    /**
     * 隐藏进度对话框
     */
    private void hideProgressDialog() {
        if (null != progressDialog) {
            progressDialog.cancel();
        }
    }
}
