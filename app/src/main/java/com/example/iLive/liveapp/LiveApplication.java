package com.example.iLive.liveapp;

import android.app.Application;

import com.example.iLive.liveapp.Utils.LogUtil;
import com.tencent.trtc.TRTCCloudListener;

/**
 * @author jiabo
 * Date: 2019/3/18 & 8:34
 * Version : 1.0
 * description : 初始化app
 * * Modify by
 */
public class LiveApplication extends Application {

    private static final String TAG = "LiveApplication";
    private TRTCLogListenerImpl listener;

    @Override
    public void onCreate(){
        super.onCreate();
        listener = new TRTCLogListenerImpl();
    }

    static class TRTCLogListenerImpl extends TRTCCloudListener.TRTCLogListener{
        @Override
        public void onLog(String s, int i, String s1) {
            LogUtil.d(TAG, "onLog: " + s + "; level: " + i);
        }
    }
}
