package com.example.iLive.liveapp.presenter;

import android.content.Context;
import android.os.Bundle;

import com.example.iLive.liveapp.Utils.LogUtil;
import com.example.iLive.liveapp.base.BasePresenter;
import com.example.iLive.liveapp.constant.Constants;
import com.example.iLive.liveapp.model.entity.MyselfInfo;
import com.example.iLive.liveapp.presenter.iview.ILiveView;
import com.example.jiabo.liveapp.model.entity.CurrentLiveInfo;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.trtc.TRTCCloud;
import com.tencent.trtc.TRTCCloudDef;

/**
 * Created by jiabo
 * Data : 2019/4/22
 * Description :  直播界面的presenter
 * Modify by
 */

public class LivePresenter extends BasePresenter<ILiveView> {

    private static final String TAG = "LivePresenter";

    private TRTCCloud mTrtcCloud;

    public LivePresenter(Context context, ILiveView iLiveView) {
        super(context, iLiveView);

        mTrtcCloud = TRTCCloud.sharedInstance(context);
        mTrtcCloud.setListener(this);
    }

    @Override
    public void onDestroy() {
        if (mTrtcCloud != null) {
            mTrtcCloud.setListener(null);
            mTrtcCloud.exitRoom();
            mTrtcCloud.stopLocalPreview();
        }
        mTrtcCloud = null;
    }

    public void enterRoom(TXCloudVideoView localVideoView) {

        setTRTCCloudParam();
        TRTCCloudDef.TRTCParams trtcParams = new TRTCCloudDef.TRTCParams(Constants.SDK_APP_ID,
                MyselfInfo.getInstance().getId(), MyselfInfo.getInstance().getUserSign(),
                CurrentLiveInfo.getRoomNum(), "", "");
        //开启视频采集预览
        //设置TRTC SDK的状态，开启该模式sdk只保留编码和发送能力，视频采集流程需要用sendCustomVideoData()
        // 不断想sdk塞入自己采集的视频画面 true：启用；false：关闭
        mTrtcCloud.enableCustomAudioCapture(true);

        //启动sdk摄像头采集和渲染
        startLocalPreview(true, localVideoView);

        mTrtcCloud.enterRoom(trtcParams, TRTCCloudDef.TRTC_APP_SCENE_LIVE);
    }

    /**
     * 设置视频参数，需要提供分辨率、帧率和流畅模式等参数
     */
    private void setTRTCCloudParam() {

        // 大画面的编码器参数设置
        // 设置视频编码参数，包括分辨率、帧率、码率等等，这些编码参数来自于 TRTCSettingDialog 的设置
        // 注意（1）：不要在码率很低的情况下设置很高的分辨率，会出现较大的马赛克
        // 注意（2）：不要设置超过25FPS以上的帧率，因为电影才使用24FPS，我们一般推荐15FPS，这样能将更多的码率分配给画质
        TRTCCloudDef.TRTCVideoEncParam encParam = new TRTCCloudDef.TRTCVideoEncParam();
        encParam.videoResolution = CurrentLiveInfo.getCurRole();          //分辨率
        encParam.videoFps = 15;                  //帧率
        encParam.videoBitrate = 600;            //码率
        encParam.videoResolutionMode = TRTCCloudDef.TRTC_VIDEO_RESOLUTION_MODE_PORTRAIT;
        mTrtcCloud.setVideoEncoderParam(encParam);

        TRTCCloudDef.TRTCNetworkQosParam qosParam = new TRTCCloudDef.TRTCNetworkQosParam();
        qosParam.controlMode = TRTCCloudDef.VIDEO_QOS_CONTROL_SERVER;                 //流控模式
        qosParam.preference = TRTCCloudDef.TRTC_VIDEO_QOS_PREFERENCE_CLEAR;               //画质偏好
        mTrtcCloud.setNetworkQosParam(qosParam);

    }

    /**
     * 打开本地摄像头预览画面
     */
    private void startLocalPreview(boolean frontCamera, TXCloudVideoView localViewView) {
        mTrtcCloud.setLocalViewFillMode(TRTCCloudDef.TRTC_VIDEO_RENDER_MODE_FIT);
        mTrtcCloud.startLocalPreview(frontCamera, localViewView);
    }

    /*-----------------------sdk的房间事件回调-------------------------------*/
    @Override
    public void onError(int errCode, String errMsg, Bundle extraInfo) {
        LogUtil.e(TAG, "onError; errorCode: " + errCode + "; errorMessage: " + errMsg
                + "; extraInfo: " + extraInfo);
        if (mIView == null) {
            LogUtil.e(TAG, "onError: mIView = null");
            return;
        }
        mIView.onError(errCode, errMsg);
    }

    @Override
    public void onEnterRoom(long elapsed) {
        LogUtil.d(TAG, "onEnterRoom: enter room success!");
        mIView.onEnterRoom(elapsed);
    }

    @Override
    public void onExitRoom(int reason) {

    }

    /*-----------------------sdk的音视频事件回调-------------------------------*/
    @Override
    public void onFirstVideoFrame(String userId, int width, int height){

    }
}
