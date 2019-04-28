package com.example.iLive.liveapp.view.Framgent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iLive.liveapp.R;
import com.example.iLive.liveapp.Utils.LogUtil;
import com.example.iLive.liveapp.adapter.LiveRoomListAdapter;
import com.example.iLive.liveapp.constant.Constants;
import com.example.iLive.liveapp.network.entity.ResponseLiveRoomInfo;
import com.example.iLive.liveapp.presenter.LiveListPresenter;
import com.example.iLive.liveapp.presenter.iview.ILiveListView;
import com.example.iLive.liveapp.view.LiveActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiabo
 *         Date: 2019/3/20 & 11:55
 *         Version : 1.0
 *         description : 直播列表的fragment页面
 *         * Modify by
 */
public class LiveListFragment extends Fragment implements ILiveListView {

    private static final String TAG = "LiveListFragment";
    private LiveListPresenter mLiveListPresenter;
    private LiveRoomListAdapter mLiveRoomListAdapter;
    private List<ResponseLiveRoomInfo.Room> mLiveRoomList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_pager, null);

        mLiveListPresenter = new LiveListPresenter(getContext(), this);
        mLiveListPresenter.loadLiveRoomList();
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView liveListRecycler = view.findViewById(R.id.live_list_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        liveListRecycler.setLayoutManager(layoutManager);
        liveListRecycler.setHasFixedSize(true);
        mLiveRoomListAdapter = new LiveRoomListAdapter();
        liveListRecycler.setAdapter(mLiveRoomListAdapter);

        mLiveRoomListAdapter.setOnItemClickListten(new LiveRoomListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                if (mLiveRoomList != null) {
                    stepIntoLive(mLiveRoomList.get(position).getInfo().getRoomnum(),
                            mLiveRoomList.get(position).getUid());
                }
            }
        });
    }

    /**
     * 以观众的身份进入房间
     */
    public void stepIntoLive(int roomNum, String anchorId) {
        LogUtil.d(TAG, "stepIntoLive: enter live room");
        Intent intent = new Intent(getContext(), LiveActivity.class);
        intent.putExtra("role", Constants.ROLE_AUDIENCE);
        intent.putExtra("roomNum", roomNum);
        intent.putExtra("anchorId", anchorId);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.d(TAG, "onActivityResult: reload live list");
        mLiveListPresenter.loadLiveRoomList();
        mLiveRoomListAdapter.notifyDataSetChanged();
    }

    /*---------------------数据接口的实现-------------------------------*/

    @Override
    public void onError(int errorCode, String errMsg) {

    }

    @Override
    public void loadLiveRoomList(List<ResponseLiveRoomInfo.Room> listRoomList) {
        mLiveRoomList = listRoomList;
        mLiveRoomListAdapter.fillList(listRoomList);
        mLiveRoomListAdapter.notifyDataSetChanged();
    }
}
