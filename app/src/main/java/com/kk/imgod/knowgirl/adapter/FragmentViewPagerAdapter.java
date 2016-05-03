package com.kk.imgod.knowgirl.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 项目名称：other_demo
 * 包名称：com.example.gaokang.other_demo
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016/4/26 14:34
 * 修改人：gaokang
 * 修改时间：2016/4/26 14:34
 * 修改备注：
 */
public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
    private List<String> tab_titles;
    private List<Fragment> tab_fragments;

    public FragmentViewPagerAdapter(FragmentManager fm, List<String> tab_titles, List<Fragment> tab_fragments) {
        super(fm);
        this.tab_titles = tab_titles;
        this.tab_fragments = tab_fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return tab_fragments.get(position);
    }

    @Override
    public int getCount() {
        return tab_fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab_titles.get(position);
    }
}
