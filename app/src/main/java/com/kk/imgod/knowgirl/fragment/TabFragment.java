package com.kk.imgod.knowgirl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.activity.MainActivity;
import com.kk.imgod.knowgirl.adapter.FragmentViewPagerAdapter;
import com.kk.imgod.knowgirl.app.API;

import java.util.ArrayList;
import java.util.List;

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
public class TabFragment extends Fragment {
    private View parentView;
    private TabLayout tabs;
    private ViewPager vp_tabs;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_tab, container, false);
        initView();
        initValue();
        return parentView;
    }

    private void initValue() {
        type = getArguments().getInt(TYPE, 0x00);
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
        if (type == MainActivity.KNOWLEDGE_FRAGMENT) {
            titles.add(getString(R.string.zhihu_day_news));
            titles.add(getString(R.string.jiandan_news));
            fragments.add(new ZhihuFragment());
            fragments.add(TestFragment.newInstance("Tab2"));
        } else {
            tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
            titles.add(getString(R.string.pure_girl));
            titles.add(getString(R.string.photo_nice_girl));
            titles.add(getString(R.string.write_real_girl));
            titles.add(getString(R.string.japan_girl));
            titles.add(getString(R.string.stock_girl));
            titles.add(getString(R.string.sex_car_girl));
            titles.add(getString(R.string.sex_girl));
            fragments.add(LazyPictureFragment.newInstance(API.PURE_GIRL_URL));
            fragments.add(LazyPictureFragment.newInstance(API.PHOTO_GIRL_URL));
            fragments.add(LazyPictureFragment.newInstance(API.WRITE_REAL_GIRL_URL));
            fragments.add(LazyPictureFragment.newInstance(API.JAPAN_GIRL_URL));
            fragments.add(LazyPictureFragment.newInstance(API.STOCK_GIRL_URL));
            fragments.add(LazyPictureFragment.newInstance(API.SEX_CAR_GIRL_URL));
            fragments.add(LazyPictureFragment.newInstance(API.SEX_GIRL_URL));
        }

        fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getChildFragmentManager(), titles, fragments);
        vp_tabs.setAdapter(fragmentViewPagerAdapter);
        vp_tabs.setOffscreenPageLimit(fragments.size());
        tabs.setupWithViewPager(vp_tabs);
    }

    private void initView() {
        tabs = (TabLayout) parentView.findViewById(R.id.tabs);
        vp_tabs = (ViewPager) parentView.findViewById(R.id.vp_tabs);
    }
}
