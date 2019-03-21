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
 * Date: 2019/3/20 & 14:40
 * Version : 1.0
 * description :
 * * Modify by
 */
public class CenterFragment extends Fragment {

    private static final String TAG = "CenterFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_center_pager, null);

        LogUtil.d(TAG, "onCreateView: center fragment was created");

        return view;
    }
}
