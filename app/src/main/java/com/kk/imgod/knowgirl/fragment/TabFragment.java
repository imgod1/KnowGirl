package com.kk.imgod.knowgirl.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.activity.MainActivity;
import com.kk.imgod.knowgirl.adapter.FragmentViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 项目名称：other_demo
 * 包名称：com.example.gaokang.other_demo
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016/4/26 15:27
 * 修改人：gaokang
 * 修改时间：2016/4/26 15:27
 * 修改备注：
 */
public class TabFragment extends BaseFragment {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.vp_tabs)
    ViewPager vp_tabs;
    private List<String> titles;
    private List<Fragment> fragments;
    private FragmentViewPagerAdapter fragmentViewPagerAdapter;

    public static String TYPE = "type";
    private int type;

    public static TabFragment newInstance(int type) {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }


    @Override
    public int getLayoutResID() {
        return R.layout.fragment_tab;
    }

    @Override
    public void initValue() {
        type = getArguments().getInt(TYPE, 0x00);
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
        if (type == MainActivity.KNOWLEDGE_FRAGMENT) {
            titles.add(getString(R.string.zhihu_day_news));
            titles.add(getString(R.string.jiandan_news));
            fragments.add(new ZhihuFragment());
            fragments.add(new FreshFragment());
        } else {
            tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
            titles.add(getString(R.string.pure_girl));
            titles.add(getString(R.string.photo_nice_girl));
            titles.add(getString(R.string.write_real_girl));
            titles.add(getString(R.string.japan_girl));
            titles.add(getString(R.string.stock_girl));
            titles.add(getString(R.string.sex_car_girl));
            titles.add(getString(R.string.sex_girl));
            fragments.add(LazyPictureFragment.newInstance(4));
            fragments.add(LazyPictureFragment.newInstance(3));
            fragments.add(LazyPictureFragment.newInstance(2));
            fragments.add(LazyPictureFragment.newInstance(1));
            fragments.add(LazyPictureFragment.newInstance(3));
            fragments.add(LazyPictureFragment.newInstance(2));
            fragments.add(LazyPictureFragment.newInstance(1));
        }

        fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getChildFragmentManager(), titles, fragments);
        vp_tabs.setAdapter(fragmentViewPagerAdapter);
        vp_tabs.setOffscreenPageLimit(fragments.size());
        tabs.setupWithViewPager(vp_tabs);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initView() {
    }
}
