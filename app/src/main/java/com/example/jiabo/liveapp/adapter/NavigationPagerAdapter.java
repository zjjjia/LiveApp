package com.example.jiabo.liveapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author jiabo
 * Date: 2019/3/20 & 14:20
 * Version : 1.0
 * description : 主页面viewPager中三个Fragment的Adaper
 * * Modify by
 */
public class NavigationPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    public NavigationPagerAdapter(FragmentManager manager, List<Fragment> fragmentList) {
        super(manager);

        this.mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
