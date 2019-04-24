package com.example.iLive.liveapp.presenter.iview;

import android.content.Intent;
import android.net.Uri;

import com.example.iLive.liveapp.base.IView;

/**
 * Created by jiabo
 * Data : 2019/3/27
 * Description : 创建直播页面（CreateLiveActivity）的数据接口
 * Modify by
 */

public interface ICreateLiveView extends IView {

    void loadCover(Uri fileUri, int type);

    void startPhotoZoom(Intent intent);

    void startLive();

    void onError(int errorCode, String errorInfo);

}
