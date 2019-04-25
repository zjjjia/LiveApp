package com.example.iLive.liveapp.model;

import com.example.iLive.liveapp.callBack.HttpRequestCallback;
import com.example.iLive.liveapp.network.entity.LoginResponse;
import com.example.iLive.liveapp.network.entity.RequestBackInfo;
import com.example.iLive.liveapp.base.BaseObserver;
import com.example.iLive.liveapp.network.RetrofitFactory;

/**
 * Created by jiabo
 * Data : 2019/4/5
 * Description : 注册和登录的Model层
 * Modify by
 */

public class RegisterAndLoginModel {

    private static final String TAG = "RegisterAndLoginModel";

    /**
     * 注册账号
     */
    public void register(String username, String password,
                         final HttpRequestCallback<RequestBackInfo> httpRequestCallback) {
        RetrofitFactory.register(username, password)
                .subscribe(new BaseObserver<RequestBackInfo>() {
                    @Override
                    public void onHttpSuccess(RequestBackInfo response) {
                        httpRequestCallback.onSuccess(response);
                    }

                    @Override
                    public void onHttpError(int errorCode, String errorInfo) {
                        httpRequestCallback.onError(errorCode, errorInfo);
                    }
                });
    }

    /**
     * 账号登录
     */
    public void login(String username, String password,
                      final HttpRequestCallback<RequestBackInfo<LoginResponse>> httpRequestCallback) {
        RetrofitFactory.login(username, password)
                .subscribe(new BaseObserver<RequestBackInfo<LoginResponse>>() {
                    @Override
                    public void onHttpSuccess(RequestBackInfo<LoginResponse> response) {
                        httpRequestCallback.onSuccess(response);
                    }

                    @Override
                    public void onHttpError(int errorCode, String errorInfo) {
                        httpRequestCallback.onError(errorCode, errorInfo);
                    }
                });
    }

}
