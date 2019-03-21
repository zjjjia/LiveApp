package com.example.jiabo.liveapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.jiabo.liveapp.R;
import com.example.jiabo.liveapp.adapter.NavigationPagerAdapter;
import com.example.jiabo.liveapp.view.Framgent.CenterFragment;
import com.example.jiabo.liveapp.view.Framgent.HomeFragment;
import com.example.jiabo.liveapp.view.Framgent.LiveFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.NavigationClickListener,
        ViewPager.OnPageChangeListener {

    private static final String TAG = "HomeActivity";

    private ViewPager mMainViewPager;
    private NavigationView mNavigationView;
    private final static int HOME_PAGER_POSITION = 0;
    private final static int LIVE_PAGER_POSITION = 1;
    private final static int CENTER_PAGER_POSITION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
    }

    private void initView() {
        mNavigationView = findViewById(R.id.bottom_navigation);
        mMainViewPager = findViewById(R.id.main_viewpager);

        initFragmentView();
        mNavigationView.setOnClickListener(this);

    }

    private void initFragmentView() {
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new HomeFragment());
        fragments.add(new LiveFragment());
        fragments.add(new CenterFragment());

        NavigationPagerAdapter navigationAdapter = new NavigationPagerAdapter(getSupportFragmentManager(),
                fragments);
        mMainViewPager.setAdapter(navigationAdapter);
        mMainViewPager.setCurrentItem(0);
        mMainViewPager.addOnPageChangeListener(this);
    }

    /*---------------------这里是接口的实现----------------------------*/

    /*-----事件监听接口的实现---------*/
    @Override
    public void homeClickListener() {
        mMainViewPager.setCurrentItem(HOME_PAGER_POSITION);
        mNavigationView.changeButtonIco(HOME_PAGER_POSITION);
    }

    @Override
    public void liveClickListener() {
        mMainViewPager.setCurrentItem(LIVE_PAGER_POSITION);
        mNavigationView.changeButtonIco(LIVE_PAGER_POSITION);
    }

    @Override
    public void centerClickListener() {
        mMainViewPager.setCurrentItem(CENTER_PAGER_POSITION);
        mNavigationView.changeButtonIco(CENTER_PAGER_POSITION);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (mMainViewPager.getCurrentItem()) {
            case HOME_PAGER_POSITION:
                mNavigationView.changeButtonIco(HOME_PAGER_POSITION);
                break;
            case LIVE_PAGER_POSITION:
                mNavigationView.changeButtonIco(LIVE_PAGER_POSITION);
                break;
            case CENTER_PAGER_POSITION:
                mNavigationView.changeButtonIco(CENTER_PAGER_POSITION);
                break;
            default:
        }
    }
}
