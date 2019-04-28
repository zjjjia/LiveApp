package com.example.iLive.liveapp.presenter;

import android.content.Context;
import android.os.Bundle;

import com.example.iLive.liveapp.Utils.LogUtil;
import com.example.iLive.liveapp.base.BaseObserver;
import com.example.iLive.liveapp.base.BasePresenter;
import com.example.iLive.liveapp.constant.Constants;
import com.example.iLive.liveapp.constant.ErrorCode;
import com.example.iLive.liveapp.model.entity.CurrentLiveInfo;
import com.example.iLive.liveapp.model.entity.MyselfInfo;
import com.example.iLive.liveapp.network.RetrofitFactory;
import com.example.iLive.liveapp.network.entity.RequestBackInfo;
import com.example.iLive.liveapp.presenter.iview.ILiveView;
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
    private int mUserRole;
    private TXCloudVideoView mLocalVideoPreview;
    private int mRoomNum;
    private String mAnchorId;

    public LivePresenter(Context context, ILiveView iLiveView, int userRole) {
        super(context, iLiveView);

        mUserRole = userRole;
        mTrtcCloud = TRTCCloud.sharedInstance(context);
        mTrtcCloud.setListener(this);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        if (mUserRole == Constants.ROLE_ANCHOR) {
            exitRoom();
        } else if (mUserRole == Constants.ROLE_AUDIENCE) {
            if (mTrtcCloud != null) {
                mTrtcCloud.exitRoom();
            }
        }
        TRTCCloud.destroySharedInstance();
        mTrtcCloud = null;
    }

    public void enterRoom(TXCloudVideoView localVideoView, int roomNum, String anchorId) {
        mLocalVideoPreview = localVideoView;
        mRoomNum = roomNum;
        mAnchorId = anchorId;
        setTRTCCloudParam();
        TRTCCloudDef.TRTCParams trtcParams;
        if (mUserRole == Constants.ROLE_ANCHOR) {                   //用户创建房间
            trtcParams = new TRTCCloudDef.TRTCParams(Constants.SDK_APP_ID,
                    MyselfInfo.getInstance().getId(), MyselfInfo.getInstance().getUserSign(),
                    CurrentLiveInfo.getRoomNum(), "", "");
            startLocalPreview();
        } else if (mUserRole == Constants.ROLE_AUDIENCE) {             //用户进入别人创建的房间
            trtcParams = new TRTCCloudDef.TRTCParams(Constants.SDK_APP_ID,
                    MyselfInfo.getInstance().getId(), MyselfInfo.getInstance().getUserSign(),
                    mRoomNum, "", "");
        } else {
            if (mIView == null) {
                LogUtil.e(TAG, "enterRoom: mIView = null");
                return;
            }
            mIView.onError(ErrorCode.UNKNOW_ERROR, "unKnow error");
            return;
        }
        mTrtcCloud.enterRoom(trtcParams, TRTCCloudDef.TRTC_APP_SCENE_LIVE);

    }

    /**
     * 开始视频采集预览
     * 设置TRTC SDK的状态，开启该模式sdk只保留编码和发送能力，视频采集流程需要用sendCustomVideoData()
     * 不断向sdk塞入自己采集的视频画面 true：启用；false：关闭
     */
    private void startLocalPreview() {
        mTrtcCloud.enableCustomAudioCapture(true);
        //启动sdk摄像头采集和渲染
        mTrtcCloud.setLocalViewFillMode(TRTCCloudDef.TRTC_VIDEO_RENDER_MODE_FIT);
        mTrtcCloud.startLocalPreview(true, mLocalVideoPreview);      //开启本地视频采集和上行
        mTrtcCloud.startLocalAudio();                             //开启本地音频的采集和上行

    }

    private void exitRoom() {
        RetrofitFactory.exitRoom(MyselfInfo.getInstance().getToken(), CurrentLiveInfo.getRoomNum())
                .subscribe(new BaseObserver<RequestBackInfo>() {
                    @Override
                    public void onHttpSuccess(RequestBackInfo response) {
                        if (response.getErrorCode() == 0) {
                            if (mTrtcCloud != null) {
                                LogUtil.d(TAG, "onHttpSuccess: stop local preview");
                                mTrtcCloud.exitRoom();
                                mTrtcCloud.setListener(null);
                                mTrtcCloud.stopLocalPreview();                   //停止本地视频采集和上行
                                mTrtcCloud.stopLocalAudio();                   //停止本地音频采集和上行
                            }
                        } else {
                            if (mIView == null) {
                                LogUtil.e(TAG, "onHttpError: mIView = null");
                                return;
                            }
                            mIView.onError(response.getErrorCode(), response.getErrorInfo());
                        }
                    }

                    @Override
                    public void onHttpError(int errorCode, String errorInfo) {
                        LogUtil.e(TAG, "onHttpError; errorCode: " + errorCode + "; errorMsg: " + errorInfo);
                        if (mIView == null) {
                            LogUtil.e(TAG, "onHttpError: mIView = null");
                            return;
                        }
                        mIView.onError(errorCode, errorInfo);
                    }
                });
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
        if (mUserRole == Constants.ROLE_AUDIENCE) {
            encParam.videoResolution = TRTCCloudDef.TRTC_VIDEO_RESOLUTION_640_480;          //分辨率
        } else {
            encParam.videoResolution = CurrentLiveInfo.getCurRole();          //分辨率
        }
        encParam.videoFps = 18;                  //帧率
        encParam.videoBitrate = 600;            //码率
        encParam.videoResolutionMode = TRTCCloudDef.TRTC_VIDEO_RESOLUTION_MODE_PORTRAIT;
        mTrtcCloud.setVideoEncoderParam(encParam);

        TRTCCloudDef.TRTCNetworkQosParam qosParam = new TRTCCloudDef.TRTCNetworkQosParam();
        qosParam.controlMode = TRTCCloudDef.VIDEO_QOS_CONTROL_SERVER;                 //流控模式
        qosParam.preference = TRTCCloudDef.TRTC_VIDEO_QOS_PREFERENCE_CLEAR;               //画质偏好
        mTrtcCloud.setNetworkQosParam(qosParam);

        //小画面的编码器参数设置
        //TRTC SDK 支持大小两路画面的同时编码和传输，这样网速不理想的用户可以选择观看小画面
        //注意：iPhone & Android 不要开启大小双路画面，非常浪费流量，大小路画面适合 Windows 和 MAC 这样的有线网络环境
        TRTCCloudDef.TRTCVideoEncParam smallParam = new TRTCCloudDef.TRTCVideoEncParam();
        smallParam.videoResolution = TRTCCloudDef.TRTC_VIDEO_RESOLUTION_160_90;
        smallParam.videoFps = 18;
        smallParam.videoBitrate = 100;
        smallParam.videoResolutionMode = TRTCCloudDef.TRTC_VIDEO_RESOLUTION_MODE_PORTRAIT;
        mTrtcCloud.enableEncSmallVideoStream(false, smallParam);

        mTrtcCloud.setPriorRemoteVideoStreamType(TRTCCloudDef.TRTC_VIDEO_STREAM_TYPE_SMALL);
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
        if (mIView == null) {
            LogUtil.e(TAG, "onError: mIView = null");
            return;
        }
        mIView.onEnterRoom(elapsed);
    }

    @Override
    public void onUserEnter(String userId) {
        LogUtil.d(TAG, "onUserEnter: " + userId + " enter room");

        if (mIView == null) {
            LogUtil.e(TAG, "onError: mIView = null");
            return;
        }
        mIView.onUserEnter(userId);
    }

    @Override
    public void onUserVideoAvailable(String userId, boolean available) {
        LogUtil.d(TAG, "onUserVideoAvailable: " + available + "; " + userId + " enter room");
        if (available) {
            mTrtcCloud.setRemoteViewFillMode(MyselfInfo.getInstance().getId(), TRTCCloudDef.TRTC_VIDEO_RENDER_MODE_FIT);
            mTrtcCloud.startRemoteView(userId, mLocalVideoPreview);                       //开始显示远端用户的画面，即显示主播的画面
            mTrtcCloud.setAudioRoute(TRTCCloudDef.TRTC_AUDIO_ROUTE_SPEAKER);           //设置音频路由，即选择扬声器或提供播放声音，这里默认扬声器
        }
    }

    @Override
    public void onUserExit(String userId, int reason) {
        LogUtil.d(TAG, "onUserExit: " + userId + " exit room, reason: " + reason);
        mTrtcCloud.stopAllRemoteView();
        if (mIView == null) {
            LogUtil.e(TAG, "onError: mIView = null");
            return;
        }
        mIView.onUserExit(userId, reason);
    }

    @Override
    public void onExitRoom(int reason) {
        LogUtil.d(TAG, "onExitRoom: exit room");
        if (mIView == null) {
            LogUtil.e(TAG, "onError: mIView = null");
            return;
        }

        mIView.onExitRoom(reason);
    }

    /*-----------------------sdk的音视频事件回调-------------------------------*/

    /**
     * 首帧画面达到，界面此时可以结束loading，并开始显示视频画面
     */
    @Override
    public void onFirstVideoFrame(String userId, int width, int height) {
        LogUtil.d(TAG, "onFirstVideoFrame: first video frame");
        if (mIView == null) {
            LogUtil.e(TAG, "onError: mIView = null");
            return;
        }
        mIView.onFirstVideoFrame(userId, width, height);
    }
}
