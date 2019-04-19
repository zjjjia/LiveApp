package com.example.jiabo.liveapp.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.example.jiabo.liveapp.Utils.LogUtil;
import com.example.jiabo.liveapp.Utils.UIUtils;
import com.example.jiabo.liveapp.base.BaseObserver;
import com.example.jiabo.liveapp.base.BasePresenter;
import com.example.jiabo.liveapp.constant.Constants;
import com.example.jiabo.liveapp.constant.ErrorCode;
import com.example.jiabo.liveapp.model.entity.CurrentLiveInfo;
import com.example.jiabo.liveapp.model.entity.MyselfInfo;
import com.example.jiabo.liveapp.network.RetrofitFactory;
import com.example.jiabo.liveapp.network.entity.CreateRoomResponse;
import com.example.jiabo.liveapp.network.entity.ReportRoomInfo;
import com.example.jiabo.liveapp.network.entity.RequestBackInfo;
import com.example.jiabo.liveapp.presenter.iview.ICreateLiveView;
import com.tencent.av.sdk.AVRoomMulti;
import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.ILiveConstants;
import com.tencent.ilivesdk.core.ILiveLoginManager;
import com.tencent.ilivesdk.core.ILiveRoomManager;
import com.tencent.ilivesdk.core.ILiveRoomOption;

import java.io.File;
import java.io.IOException;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jiabo
 * Data : 2019/3/27
 * Description : 创建直播界面（CreateLiveActivity）的presenter
 * Modify by
 */

public class CreateLivePresenter extends BasePresenter<ICreateLiveView> implements ILiveRoomOption.onRoomDisconnectListener {

    private static final String TAG = "CreateLivePresenter";
    private static final int CHOOSE_COVER_BY_CAMERA = 100;
    private static final int CHOOSE_COVER_BY_ALBUM = 200;

    public CreateLivePresenter(Context context, ICreateLiveView iView) {
        super(context, iView);
    }

    public void createRoom(String liveTitle, Uri coverUri, String roleShow) {
        CurrentLiveInfo.setTitle(liveTitle);
        CurrentLiveInfo.setCoverUrl(coverUri.getPath());
        CurrentLiveInfo.setCurRole(roleShow);
        String token = MyselfInfo.getInstance().getToken();
        RetrofitFactory.createRoom(token)
                .subscribe(new BaseObserver<RequestBackInfo<CreateRoomResponse>>() {
                    @Override
                    public void onHttpSuccess(RequestBackInfo<CreateRoomResponse> response) {
                        LogUtil.d(TAG, "onHttpSuccess: " + response.toString());
                        CurrentLiveInfo.setRoomNum(response.getData().getRoomnum());
                        createRoomInTencent();
                        //reportRoomInfo(response.getData().getRoomnum(), response.getData().getGroupid());
                    }

                    @Override
                    public void onHttpError(int errorCode, String errorInfo) {
                        if (mIView == null) {
                            LogUtil.e(TAG, "onHttpError: mIView = null");
                            return;
                        }
                        mIView.onError(errorCode, errorInfo);
                    }
                });
    }

    private void createRoomInTencent(){
        ILiveRoomOption hostOption = new ILiveRoomOption(ILiveLoginManager.getInstance().getMyUserId())
                .autoCamera(true)
                .authBits(AVRoomMulti.AUTH_BITS_DEFAULT)
                .videoMode(ILiveConstants.VIDEOMODE_NORMAL)
                .controlRole(CurrentLiveInfo.getCurRole())
                .autoFocus(true);

        ILiveRoomManager.getInstance().createRoom(MyselfInfo.getInstance().getMyRoomNum(), hostOption,
                new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                LogUtil.e(TAG, "onSuccess: crate room success in tencent!");
                mIView.startLive();
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                LogUtil.e(TAG, "onError: module==>" + module + "----errorCode==>" + errCode
                        + "----errorMessage==>" + errMsg);
                if(mIView == null){
                    LogUtil.e(TAG, "onError: mIView = null");
                    return;
                }
                mIView.onError(errCode, errMsg);
            }
        });

    }

    /**
     * 选择图片作为封面
     *
     * @param type 获取封面图片的途径，100：拍照，200：从相册选择
     */
    public void loadCover(final int type) {
        Observable.create(new Observable.OnSubscribe<Uri>() {
            @Override
            public void call(Subscriber<? super Uri> subscriber) {
                Uri fileUri;
                switch (type) {
                    case CHOOSE_COVER_BY_CAMERA:
                        fileUri = createCoverUri("", false);
                        subscriber.onNext(fileUri);
                        break;
                    case CHOOSE_COVER_BY_ALBUM:
                        fileUri = createCoverUri("_select", false);
                        subscriber.onNext(fileUri);
                        break;
                }
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Uri>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "onError: " + e);
                    }

                    @Override
                    public void onNext(Uri fileUri) {
                        if (mIView == null) {
                            LogUtil.e(TAG, "onNext: mIView = null");
                            return;
                        }
                        mIView.loadCover(fileUri, type);
                    }
                });
    }

    /**
     * 裁剪图片
     */
    public void startPhotoZoom(Uri uri) {
        Uri fileUri = createCoverUri("_crop", true);

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 500);
        intent.putExtra("aspectY", 309);
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 309);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        mIView.startPhotoZoom(intent);
    }

    private Uri createCoverUri(String type, boolean bCrop) {
        String filename = MyselfInfo.getInstance().getId() + type + ".jpg";
        File outputImage = new File(Environment.getExternalStorageDirectory(), filename);

        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            LogUtil.e(TAG, "createCoverUri: " + e);
            e.printStackTrace();
        }
        if (bCrop) {
            return Uri.fromFile(outputImage);
        } else {
            return UIUtils.getUriFromFile(mContext, outputImage);
        }
    }

    /**
     * 上报创建房间结果
     */
    private void reportRoomInfo(int roomnum, String groupid) {
        ReportRoomInfo reportRoomInfo = new ReportRoomInfo();
        reportRoomInfo.setTitle(CurrentLiveInfo.getTitle());
        reportRoomInfo.setRoomnum(roomnum);
        reportRoomInfo.setType("live");
        reportRoomInfo.setGroupid(groupid);
        reportRoomInfo.setCover(CurrentLiveInfo.getCoverUrl());
        reportRoomInfo.setAppid(Constants.SDK_APP_ID);
        reportRoomInfo.setDevice(1);
        reportRoomInfo.setVideoType(0);

        RetrofitFactory.reportRoomInfo(MyselfInfo.getInstance().getToken(), reportRoomInfo)
                .subscribe(new BaseObserver<RequestBackInfo>() {
                    @Override
                    public void onHttpSuccess(RequestBackInfo response) {
                        if (mIView == null) {
                            LogUtil.e(TAG, "onHttpSuccess: mIView = null");
                            return;
                        }
                        if (response.getErrorCode() == 0) {
                            LogUtil.d(TAG, "onHttpSuccess: " + response.toString());
                           createRoomInTencent();
                        } else {
                            LogUtil.e(TAG, "onError: " + response.toString());
                            mIView.onError(response.getErrorCode(), response.getErrorInfo());
                        }
                    }

                    @Override
                    public void onHttpError(int errorCode, String errorInfo) {
                        if (mIView == null) {
                            LogUtil.e(TAG, "onHttpError: mIView = null");
                            return;
                        }
                        mIView.onError(errorCode, errorInfo);
                    }
                });
    }

    @Override
    public void onRoomDisconnect(int errCode, String errMsg) {

    }
}
