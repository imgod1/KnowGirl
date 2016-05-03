package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.fragment.PictureFragment;
import com.kk.imgod.knowgirl.fragment.TabFragment;
import com.kk.imgod.knowgirl.utils.ShareUtils;
import com.kk.imgod.knowgirl.utils.SnackBarUtils;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final int KNOWLEDGE_FRAGMENT = 0x00;
    public static final int PICTURE_FRAGMENT = 0x01;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.dlayout_main)
    DrawerLayout dlayout_main;
    @BindView(R.id.nview_left)
    NavigationView nview_left;

    private Fragment knowledgeFragment;
    private Fragment pictureFragment;

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
        initAppBar();
        knowledgeFragment = TabFragment.newInstance(MainActivity.KNOWLEDGE_FRAGMENT);
        pictureFragment = TabFragment.newInstance(MainActivity.PICTURE_FRAGMENT);
        showFragment(KNOWLEDGE_FRAGMENT);
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

    }

    @Override
    public void initEvent() {
        nview_left.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        dlayout_main.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.menu_news:
                showFragment(KNOWLEDGE_FRAGMENT);
                break;
            case R.id.menu_girl:
                showFragment(PICTURE_FRAGMENT);
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

    /**
     * @param type 0:新闻知识界面 1:图片界面
     */
    private void showFragment(int type) {
        if (type == KNOWLEDGE_FRAGMENT) {
            showKnowlodgeFragment();
        } else {
            showPictureFragment();
        }
    }

    private void showKnowlodgeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (pictureFragment.isAdded()) {
            fragmentTransaction.hide(pictureFragment);
        }
        if (knowledgeFragment.isAdded()) {
            fragmentTransaction.show(knowledgeFragment);
        } else {
            fragmentTransaction.add(R.id.flayout_content, knowledgeFragment);
        }
        fragmentTransaction.commit();
    }

    private void showPictureFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (knowledgeFragment.isAdded()) {
            fragmentTransaction.hide(knowledgeFragment);
        }
        if (pictureFragment.isAdded()) {
            fragmentTransaction.show(pictureFragment);
        } else {
            fragmentTransaction.add(R.id.flayout_content, pictureFragment);
        }
        fragmentTransaction.commit();
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

}
