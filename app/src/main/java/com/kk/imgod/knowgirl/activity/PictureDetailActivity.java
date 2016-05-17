package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.fragment.PictureDetailFragment;
import com.kk.imgod.knowgirl.model.ImageBean;
import com.kk.imgod.knowgirl.utils.Lg;

import java.util.List;

import butterknife.BindView;

public class PictureDetailActivity extends BaseActivity {

    private static final String IMGURL = "imgurl";
    private String imgUrl;
    private PictureDetailFragment pictureDetailFragment;

    public static void actionStart(Activity activity, String tempImgUrl) {
        Intent intent = new Intent(activity, PictureDetailActivity.class);
        intent.putExtra(IMGURL, tempImgUrl);
        Lg.e("PictureDetailActivity", "PictureDetailActivity actionStart得到的图片为:" + tempImgUrl);
        activity.startActivity(intent);
    }


    @Override
    public int getLayoutResID() {
        return R.layout.activity_picture_detail;
    }


    @Override
    public void initView() {
        imgUrl = getIntent().getStringExtra(IMGURL);
        Lg.e("PictureDetailActivity", "PictureDetailActivity initView得到的图片为:" + imgUrl);
    }

    @Override
    public void initValue() {
        pictureDetailFragment = PictureDetailFragment.newInstance(API.PICTURE_BASE_URL + imgUrl);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.flayout_picture_main, pictureDetailFragment, "pic");
        fragmentTransaction.show(pictureDetailFragment);
        fragmentTransaction.commit();
        Lg.e("PictureDetailActivity", "PictureDetailActivity initvalue over");
    }

    @Override
    public void initEvent() {

    }

}
