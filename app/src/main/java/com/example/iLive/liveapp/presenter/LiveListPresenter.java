package com.example.iLive.liveapp.presenter;

import android.content.Context;

import com.example.iLive.liveapp.Utils.LogUtil;
import com.example.iLive.liveapp.base.BaseObserver;
import com.example.iLive.liveapp.base.BasePresenter;
import com.example.iLive.liveapp.model.entity.MyselfInfo;
import com.example.iLive.liveapp.network.RetrofitFactory;
import com.example.iLive.liveapp.network.entity.RequestBackInfo;
import com.example.iLive.liveapp.network.entity.ResponseLiveRoomInfo;
import com.example.iLive.liveapp.presenter.iview.ILiveListView;

/**
 * Created by jiabo
 * Data : 2019/4/25
 * Description : 正在直播的列表的presenter
 * Modify by
 */

public class LiveListPresenter extends BasePresenter<ILiveListView> {

    private static final String TAG = "LiveListPresenter";

    public LiveListPresenter(Context context, ILiveListView iView) {
        super(context, iView);

    }

    public void loadLiveRoomList() {
        RetrofitFactory.loadLiveRoomInfo(MyselfInfo.getInstance().getToken(), 0, 10)
                .subscribe(new BaseObserver<RequestBackInfo<ResponseLiveRoomInfo>>() {
                    @Override
                    public void onHttpSuccess(RequestBackInfo<ResponseLiveRoomInfo> response) {
                        if (response.getErrorCode() == 0) {
                            LogUtil.d(TAG, "onHttpSuccess: " + response.getData().toString());
                            if (mIView == null) {
                                LogUtil.e(TAG, "onHttpSuccess: mIView = null");
                                return;
                            }
                            mIView.loadLiveRoomList(response.getData().getRooms());
                        } else {
                            LogUtil.e(TAG, "onHttpError; errorCode: " + response.getErrorCode()
                                    + "; errorMessage: " + response.getErrorInfo());
                            if (mIView == null) {
                                LogUtil.e(TAG, "onHttpSuccess: mIView = null");
                                return;
                            }
                            mIView.onError(response.getErrorCode(), response.getErrorInfo());
                        }
                    }

                    @Override
                    public void onHttpError(int errorCode, String errorInfo) {
                        if (mIView == null) {
                            LogUtil.e(TAG, "onHttpSuccess: mIView = null");
                            return;
                        }
                        mIView.onError(errorCode, errorInfo);
                    }
                });

    }
}
