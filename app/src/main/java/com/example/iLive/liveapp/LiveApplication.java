package com.example.iLive.liveapp;

import android.app.Application;

import com.example.iLive.liveapp.constant.Constants;
import com.example.iLive.liveapp.model.MessageObservable;
import com.tencent.ilivesdk.ILiveConstants;
import com.tencent.ilivesdk.ILiveSDK;
import com.tencent.ilivesdk.core.ILiveLog;
import com.tencent.ilivesdk.core.ILiveRoomConfig;
import com.tencent.ilivesdk.core.ILiveRoomManager;
import com.tencent.qalsdk.sdk.MsfSdkUtils;

/**
 * @author jiabo
 * Date: 2019/3/18 & 8:34
 * Version : 1.0
 * description : 初始化app
 * * Modify by
 */
public class LiveApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        if(MsfSdkUtils.isMainProcess(this)){   //仅在主线程初始化
            //初始化LiveSdk
            ILiveSDK.getInstance().setCaptureMode(ILiveConstants.CAPTURE_MODE_SURFACETEXTURE);
            ILiveLog.setLogLevel(ILiveLog.TILVBLogLevel.DEBUG);
            ILiveSDK.getInstance().initSdk(getApplicationContext(), Constants.SDK_APP_ID, Constants.ACCOUNT_TYPE);
            ILiveRoomManager.getInstance().init(new ILiveRoomConfig()
                    .setRoomMsgListener(MessageObservable.getInstance()));
        }
    }
}
