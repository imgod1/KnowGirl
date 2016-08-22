package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.adapter.FragmentViewPagerAdapter;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.fragment.LazyPictureFragment;
import com.kk.imgod.knowgirl.fragment.LazyPictureFragment1;
import com.kk.imgod.knowgirl.fragment.PictureDetailFragment;
import com.kk.imgod.knowgirl.model.ImageBean;
import com.kk.imgod.knowgirl.utils.Lg;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import ooo.oxo.library.widget.PullBackLayout;

public class PictureDetailActivity extends BaseActivity implements PullBackLayout.Callback {
    @BindView(R.id.flayout_picture_main)
    PullBackLayout flayout_picture_main;
    @BindView(R.id.vp_pic_detail)
    ViewPager vp_pic_detail;

    private List<Fragment> fragmentList;
    private FragmentViewPagerAdapter fragmentViewPagerAdapter;
    private static final String IMGPOSITION = "ImgPosition";
    private String imgUrl;
    private PictureDetailFragment pictureDetailFragment;

    private int position;

    public static void actionStart(Activity activity, int position) {
        Intent intent = new Intent(activity, PictureDetailActivity.class);
        intent.putExtra(IMGPOSITION, position);
        Lg.e("PictureDetailActivity", "PictureDetailActivity actionStart得到的图片为:" + position);
        activity.startActivity(intent);
    }


    @Override
    public int getLayoutResID() {
        setTheme(R.style.ViewerTheme_TransNav);
        return R.layout.activity_picture_detail;
    }


    @Override
    public void initView() {
        flayout_picture_main.setBackgroundColor(Color.BLACK);
//        getWindow().getDecorView().getBackground().setAlpha(0xff);
//        imgUrl = getIntent().getStringExtra(IMGURL);
        position = getIntent().getIntExtra(IMGPOSITION, 0);
        flayout_picture_main.setCallback(this);
        vp_pic_detail.setOffscreenPageLimit(2);
    }

    @Override
    public void initValue() {
        fragmentList = new ArrayList<>();
        for (int i = 0; i < LazyPictureFragment1.detailImageBeanList.size(); i++) {
            ImageBean imageBean = LazyPictureFragment1.detailImageBeanList.get(i);
            Fragment fragment = PictureDetailFragment.newInstance(API.PICTURE_BASE_URL + imageBean.getImg());
            fragmentList.add(fragment);
        }
        fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), null, fragmentList);
        vp_pic_detail.setAdapter(fragmentViewPagerAdapter);
        vp_pic_detail.setCurrentItem(position);

//        pictureDetailFragment = PictureDetailFragment.newInstance(API.PICTUR E_BASE_URL + imgUrl);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.flayout_picture_main, pictureDetailFragment, "pic");
//        fragmentTransaction.show(pictureDetailFragment);
//        fragmentTransaction.commit();
        Lg.e("PictureDetailActivity", "PictureDetailActivity initvalue over");
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onPullStart() {

    }

    @Override
    public void onPull(float v) {
//        flayout_picture_main.setAlpha(0xff - (int) Math.floor(0xff * v));
//        getWindow().getDecorView().getBackground().setAlpha(0xff - (int) Math.floor(0xff * v));
        flayout_picture_main.setBackgroundColor(Color.argb(0xff - (int) Math.floor(0xff * v), 0x00, 0x00, 0x00));
    }

    @Override
    public void onPullCancel() {

    }

    @Override
    public void onPullComplete() {
        onBackPressed();
    }
}
