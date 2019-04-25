package com.example.jiabo.liveapp.presenter;

import android.content.Context;

import com.example.jiabo.liveapp.Utils.LogUtil;
import com.example.jiabo.liveapp.base.BasePresenter;
import com.example.jiabo.liveapp.callBack.HttpRequestCallback;
import com.example.jiabo.liveapp.model.RegisterAndLoginModel;
import com.example.jiabo.liveapp.model.entity.MyselfInfo;
import com.example.jiabo.liveapp.network.entity.LoginResponse;
import com.example.jiabo.liveapp.network.entity.RequestBackInfo;
import com.example.jiabo.liveapp.presenter.iview.ILoginView;

/**
 * @author jiabo
 *         Date: 2019/3/15 & 10:06
 *         Version : 1.0
 *         description : 登录的Presenter
 *         * Modify by
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    private static final String TAG = "LoginPresenter";
    RegisterAndLoginModel model;

    public LoginPresenter(Context context, ILoginView iView) {
        super(context, iView);
        model = new RegisterAndLoginModel();
    }

    /**
     * 本地服务器进行登录验证的操作
     *
     * @param username
     * @param password
     */
    public void login(final String username, String password) {
        model.login(username, password, new HttpRequestCallback<RequestBackInfo<LoginResponse>>() {
            @Override
            public void onSuccess(RequestBackInfo<LoginResponse> response) {
                if (mIView == null) {
                    LogUtil.e(TAG, "onSuccess: iIView = null!");
                    return;
                }
                if (response.getErrorCode() == 0) {
                    LogUtil.d(TAG, "onSuccess: " + response.getData().toString());
                    ofterLoginSuccess(username, response.getData().getUserSig(), response.getData().getToken());
                    mIView.onSuccessInLogin();
                } else {
                    LogUtil.d(TAG, "onError: " + response.toString());
                    mIView.onFailureInLogin(response.getErrorCode(), response.getErrorInfo());
                }
            }

            @Override
            public void onError(int errorCode, String errorInfo) {
                LogUtil.e(TAG, "onError: errorCode:" + errorCode + ", errorInfo: " + errorInfo);
                if (mIView == null) {
                    LogUtil.e(TAG, "onError: mIView = null");
                    return;
                }
                mIView.onFailureInLogin(errorCode, errorInfo);
            }
        });
    }

    private void ofterLoginSuccess(String username, String userSign, String token) {
        MyselfInfo.getInstance().setId(username);
        MyselfInfo.getInstance().setUserSign(userSign);
        MyselfInfo.getInstance().setToken(token);
    }
}
