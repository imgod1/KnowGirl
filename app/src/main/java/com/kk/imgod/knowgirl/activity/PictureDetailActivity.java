package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.adapter.FragmentViewPagerAdapter;
import com.kk.imgod.knowgirl.fragment.PictureDetailFragment;
import com.kk.imgod.knowgirl.model.ImageBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import ooo.oxo.library.widget.PullBackLayout;

/**
 * 项目名称：KnowGirl
 * 类描述：图片详情界面
 * 创建人：imgod
 * 创建时间：2016/4/24 16:20
 * 修改人：imgod
 * 修改时间：2016/4/24 16:20
 * 修改备注：
 */
public class PictureDetailActivity extends BaseActivity implements PullBackLayout.Callback {
    /**
     * 静态的变量来存储详情界面需要的数据
     */
    public static List<ImageBean> detailImageBeanList;
    @BindView(R.id.flayout_picture_main)
    PullBackLayout flayout_picture_main;
    @BindView(R.id.vp_pic_detail)
    ViewPager vp_pic_detail;

    private static final String IMGPOSITION = "ImgPosition";

    private int position;

    public static void actionStart(Activity activity, int position) {
        Intent intent = new Intent(activity, PictureDetailActivity.class);
        intent.putExtra(IMGPOSITION, position);
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
        position = getIntent().getIntExtra(IMGPOSITION, 0);
        flayout_picture_main.setCallback(this);
        vp_pic_detail.setOffscreenPageLimit(2);
    }

    @Override
    public void initValue() {
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < PictureDetailActivity.detailImageBeanList.size(); i++) {
            ImageBean imageBean = PictureDetailActivity.detailImageBeanList.get(i);
            Fragment fragment = PictureDetailFragment.newInstance(imageBean.getImg());
            fragmentList.add(fragment);
        }
        FragmentViewPagerAdapter fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), null, fragmentList);
        vp_pic_detail.setAdapter(fragmentViewPagerAdapter);
        vp_pic_detail.setCurrentItem(position);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onPullStart() {

    }

    @Override
    public void onPull(float v) {
        flayout_picture_main.setBackgroundColor(Color.argb(0xff - (int) Math.floor(0xff * v), 0x00, 0x00, 0x00));
    }

    @Override
    public void onPullCancel() {

    }

    @Override
    public void onPullComplete() {
        //activity style明明禁用了动画效果.但是在flyme os上依然有动画
        //那么只好.如果是滑动退出那就设置动画效果为不存在的0,如果是点击了back 键,那就跟随系统吧
        finish();
        overridePendingTransition(0, 0);
    }

}
