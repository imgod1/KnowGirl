package com.kk.imgod.knowgirl.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.activity.MainActivity;
import com.kk.imgod.knowgirl.adapter.FragmentViewPagerAdapter;
import com.kk.imgod.knowgirl.app.API;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 项目名称：other_demo
 * 包名称：com.example.gaokang.other_demo
 * 类描述：TabFragment
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

    public static String TYPE = "type";

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
        int type = getArguments().getInt(TYPE, 0x00);
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        if (type == MainActivity.KNOWLEDGE_FRAGMENT) {
            titles.add(getString(R.string.zhihu_day_news));
            titles.add(getString(R.string.jiandan_news));
            fragments.add(new ZhihuFragment());
            fragments.add(new FreshFragment());
        } else {
            tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
            titles.add(getString(R.string.pure_girl));
            titles.add(getString(R.string.nice_leg));
            titles.add(getString(R.string.sex_stock));
            titles.add(getString(R.string.big_breast));
            titles.add(getString(R.string.big_hip));
            titles.add(getString(R.string.blend));
            fragments.add(LazyPictureFragment.newInstance(API.DBMEIZI_TYPE_NICE));
            fragments.add(LazyPictureFragment.newInstance(API.DBMEIZI_TYPE_LEG));
            fragments.add(LazyPictureFragment.newInstance(API.DBMEIZI_TYPE_STOCK));
            fragments.add(LazyPictureFragment.newInstance(API.DBMEIZI_TYPE_BIG_BREAST));
            fragments.add(LazyPictureFragment.newInstance(API.DBMEIZI_TYPE_HIP));
            fragments.add(LazyPictureFragment.newInstance(API.DBMEIZI_TYPE_QIPA));
        }

        FragmentViewPagerAdapter fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getChildFragmentManager(), titles, fragments);
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
