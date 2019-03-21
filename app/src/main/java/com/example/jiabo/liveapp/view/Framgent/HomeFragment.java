package com.example.jiabo.liveapp.view.Framgent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jiabo.liveapp.R;
import com.example.jiabo.liveapp.Utils.LogUtil;

/**
 * @author jiabo
 * Date: 2019/3/20 & 11:55
 * Version : 1.0
 * description : 直播列表的fragment页面
 * * Modify by
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_pager, null);

        LogUtil.d(TAG, "onCreateView: home fragment was created");

        return view;
    }
}
