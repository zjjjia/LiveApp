package com.example.iLive.liveapp.presenter.iview;

import com.example.iLive.liveapp.base.IView;

/**
 * Created by jiabo
 * Data : 2019/4/22
 * Description : 直播界面的数据接口
 * Modify by
 */

public interface ILiveView extends IView {

    void onEnterRoom(long elapsed);

    void onUserEnter(String userId);

    /**
     * 用户离开房间
     *
     * @param reason 离开原因代码，区分用户是正常离开，还是由于网络断线等原因离开
     */
    void onUserExit(String userId, int reason);

    /**
     * 收到弹幕消息的回调
     *
     * @param userId  发送消息的用户的ID
     * @param cmdID   消息类型；目前只有文字一种，可拓展为其他消息类型，如图片表情等
     * @param message 弹幕内容
     */
    void onRecvCustomCmdMsg(String userId, int cmdID, String message);

    void onExitRoom(int reason);

    void hostExitRoom();

    /**
     * 首帧视频画面到达，此时结束loading
     */
    void onFirstVideoFrame(String userId, int width, int height);

    /**
     * 进入房间失败
     */
    void onError(int errCoe, String errMsg);
}
