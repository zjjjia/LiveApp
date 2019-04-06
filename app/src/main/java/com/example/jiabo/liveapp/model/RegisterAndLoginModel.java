package com.example.jiabo.liveapp.model;

import com.example.jiabo.liveapp.callBack.HttpRequestCallback;
import com.example.jiabo.liveapp.model.entity.LoginResponseEntity;
import com.example.jiabo.liveapp.model.entity.RequestBackInfo;
import com.example.jiabo.liveapp.network.BaseObserver;
import com.example.jiabo.liveapp.network.RetrofitFactory;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
                      final HttpRequestCallback<RequestBackInfo<LoginResponseEntity>> httpRequestCallback) {
        RetrofitFactory.login(username, password)
                .subscribe(new BaseObserver<RequestBackInfo<LoginResponseEntity>>() {
                    @Override
                    public void onHttpSuccess(RequestBackInfo<LoginResponseEntity> response) {
                        httpRequestCallback.onSuccess(response);
                    }

                    @Override
                    public void onHttpError(int errorCode, String errorInfo) {
                        httpRequestCallback.onError(errorCode, errorInfo);
                    }
                });
    }

}
