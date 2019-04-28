package com.example.iLive.liveapp.presenter.iview;

import com.example.iLive.liveapp.base.IView;
import com.example.iLive.liveapp.network.entity.ResponseLiveRoomInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiabo
 * Data : 2019/4/25
 * Description : 正在直播的房间列表页面的数据接口
 * Modify by
 */

public interface ILiveListView extends IView{

    void onError(int errorCode, String errMsg);

    void loadLiveRoomList(List<ResponseLiveRoomInfo.Room> roomList);

}
