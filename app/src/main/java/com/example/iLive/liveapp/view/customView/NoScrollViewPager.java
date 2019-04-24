package com.example.iLive.liveapp.view.customView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.iLive.liveapp.Utils.LogUtil;

/**
 * Created by jiabo
 * Data : 2019/3/24
 * Description : 重写ViewPager类，添加控制viewPager是否可以滑动的方法
 * Modify by
 */

public class NoScrollViewPager extends ViewPager {

    private static final String TAG = "NoScrollViewPager";

    private boolean mNoScroll = false;

    public NoScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public NoScrollViewPager(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param mNoScroll 为true的时候viewpager不支持滑动
     */
    public void setNoScroll(boolean mNoScroll) {
        this.mNoScroll = mNoScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (mNoScroll) {
            LogUtil.i(TAG, "onTouchEvent: return false! no sliding with viewpager!");
            return false;
        } else {
            return super.onTouchEvent(arg0);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (mNoScroll) {
            LogUtil.i(TAG, "onInterceptTouchEvent: return false! no sliding with viewpager!");
            return false;
        } else {
            return super.onInterceptTouchEvent(arg0);
        }
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }
}
