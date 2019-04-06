package com.example.jiabo.liveapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.jiabo.liveapp.R;
import com.example.jiabo.liveapp.adapter.NavigationPagerAdapter;
import com.example.jiabo.liveapp.base.BaseActivity;
import com.example.jiabo.liveapp.view.Framgent.PersonalCenterFragment;
import com.example.jiabo.liveapp.view.Framgent.LiveListFragment;
import com.example.jiabo.liveapp.view.customView.CustomNavigationView;
import com.example.jiabo.liveapp.view.customView.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements CustomNavigationView.NavigationClickListener {

    private static final String TAG = "HomeActivity";

    private NoScrollViewPager mMainViewPager;
    private CustomNavigationView mCustomNavigationView;
    private final static int HOME_PAGER_POSITION = 0;
    private final static int CENTER_PAGER_POSITION = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
    }

    public void initView() {
        mCustomNavigationView = findViewById(R.id.bottom_navigation);
        mMainViewPager = findViewById(R.id.main_viewpager);
        mMainViewPager.setNoScroll(true);

        initFragmentView();
        mCustomNavigationView.setOnClickListener(this);

    }

    private void initFragmentView() {
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new LiveListFragment());
        fragments.add(new PersonalCenterFragment());

        NavigationPagerAdapter navigationAdapter = new NavigationPagerAdapter(getSupportFragmentManager(),
                fragments);
        mMainViewPager.setAdapter(navigationAdapter);
        mMainViewPager.setCurrentItem(0);
    }

    /**
     * 打开创建直播的页面
     */
    private void stepIntoCreateLive() {
        Intent intent = new Intent();
        intent.setClass(this, CreateLiveActivity.class);
        startActivity(intent);
    }

    /*---------------------这里是接口的实现----------------------------*/

    /*-----事件监听接口的实现---------*/
    @Override
    public void homeClickListener() {
        mMainViewPager.setCurrentItem(HOME_PAGER_POSITION, false);
        mCustomNavigationView.changeButtonIco(HOME_PAGER_POSITION);
    }

    @Override
    public void liveClickListener() {
        stepIntoCreateLive();
    }

    @Override
    public void centerClickListener() {
        mMainViewPager.setCurrentItem(CENTER_PAGER_POSITION, false);
        mCustomNavigationView.changeButtonIco(CENTER_PAGER_POSITION);
    }
}
