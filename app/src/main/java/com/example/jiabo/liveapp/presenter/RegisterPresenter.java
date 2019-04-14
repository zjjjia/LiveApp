package com.example.jiabo.liveapp.presenter;

import android.content.Context;

import com.example.jiabo.liveapp.Utils.LogUtil;
import com.example.jiabo.liveapp.base.BasePresenter;
import com.example.jiabo.liveapp.callBack.HttpRequestCallback;
import com.example.jiabo.liveapp.model.RegisterAndLoginModel;
import com.example.jiabo.liveapp.network.entity.RequestBackInfo;
import com.example.jiabo.liveapp.presenter.iview.IRegisterView;

/**
 * @author jiabo
 *         Date: 2019/3/15 & 11:21
 *         Version : 1.0
 *         description : 注册的prenter
 *         * Modify by
 */
public class RegisterPresenter extends BasePresenter<IRegisterView> {

    private static final String TAG = "RegisterPresenter";
    private RegisterAndLoginModel model;

    public RegisterPresenter(Context context, IRegisterView iView) {
        super(context, iView);
        model = new RegisterAndLoginModel();
    }

    public void register(final String username, String password) {
        model.register(username, password, new HttpRequestCallback<RequestBackInfo>() {
            @Override
            public void onSuccess(RequestBackInfo response) {
                if (mIView == null) {
                    LogUtil.e(TAG, "onSuccess: mIView = null!");
                    return;
                }
                if (response.getErrorCode() == 0) {
                    mIView.onSuccessInRegister(username);
                } else {
                    LogUtil.e(TAG, "onError: register failed! errorCode: " + response.getErrorCode()
                            + " | errorInfo: " + response.getErrorInfo());
                    mIView.onFailureInRegister(response.getErrorCode(), response.getErrorInfo());
                }
            }

            @Override
            public void onError(int errorCode, String errorInfo) {
                LogUtil.e(TAG, "onError: errorCode: " + errorCode + ", errorInfo: " + errorInfo);
                if (mIView == null) {
                    LogUtil.e(TAG, "onError: mIView = null");
                    return;
                }
                mIView.onFailureInRegister(errorCode, errorInfo);
            }
        });
    }
}
