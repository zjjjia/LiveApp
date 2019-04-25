package com.example.jiabo.liveapp.presenter.iview;

import com.example.jiabo.liveapp.base.IView;
import com.tencent.trtc.TRTCCloud;

/**
 * Created by jiabo
 * Data : 2019/4/22
 * Description : 直播界面的数据接口
 * Modify by
 */

public interface ILiveView extends IView {

    void onEnterRoom(long elapsed);

    /**
     * 进入房间失败
     */
    void onError(int errCoe, String errMsg);
}
