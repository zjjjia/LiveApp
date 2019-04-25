package com.example.iLive.liveapp.view.Framgent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iLive.liveapp.R;
import com.example.iLive.liveapp.Utils.LogUtil;
import com.example.iLive.liveapp.adapter.LiveListAdapter;
import com.example.iLive.liveapp.model.entity.LiveListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiabo
 *         Date: 2019/3/20 & 11:55
 *         Version : 1.0
 *         description : 直播列表的fragment页面
 *         * Modify by
 */
public class LiveListFragment extends Fragment {

    private static final String TAG = "LiveListFragment";
    private List<LiveListEntity> liveList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_pager, null);

        LogUtil.d(TAG, "onCreateView: home fragment was created");

        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView liveListRecycler = view.findViewById(R.id.live_list_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        liveListRecycler.setLayoutManager(layoutManager);
        liveListRecycler.setHasFixedSize(true);
        LiveListAdapter liveListAdapter = new LiveListAdapter();
        liveListRecycler.setAdapter(liveListAdapter);

        initData();
        liveListAdapter.fillList(liveList);
        liveListAdapter.notifyDataSetChanged();
    }

    private void initData() {
        LiveListEntity data = new LiveListEntity();
        data.setUrl("storage/sdcard/DCIM/1.jpg");
        data.setLiveTitle("直播标题");
        data.setLiveBrieIntroduction("直播简介");

        for (int i = 0; i < 5; i++) {
            liveList.add(data);
        }
    }
}
