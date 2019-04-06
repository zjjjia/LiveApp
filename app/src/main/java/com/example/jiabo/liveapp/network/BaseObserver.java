package com.example.jiabo.liveapp.network;

import com.example.jiabo.liveapp.Utils.LogUtil;
import com.example.jiabo.liveapp.constant.ErrorCode;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;
import rx.Observer;

/**
 * Created by jiabo
 * Data : 2019/4/5
 * Description :
 * Modify by
 */

public abstract class BaseObserver<T> implements Observer<T> {

    private static final String TAG = "BaseObserver";

     public abstract void onHttpSuccess(T response);

     public abstract void onHttpError(int errorCode, String errorInfo);

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof SocketTimeoutException){
            LogUtil.e(TAG, "onError: request time out! errorCode: " + ErrorCode.REQUEST_TIME_OUT);
            onHttpError(ErrorCode.REQUEST_TIME_OUT, "request time out!");
        }else if(e instanceof ConnectException){
            LogUtil.e(TAG, "onError: connect time out! errorCode: " + ErrorCode.CONNECT_TIME_OUT);
            onHttpError(ErrorCode.CONNECT_TIME_OUT, "connect time out!");
        }else if(e instanceof HttpException){
            int errorCode = ((HttpException)e).code();
            if(errorCode == ErrorCode.INTERNET_ERROR){
                LogUtil.e(TAG, "onError: internet error! check internet states! errorCode: " + errorCode);
                onHttpError(ErrorCode.INTERNET_ERROR, "internet error!");
            }else if(errorCode == ErrorCode.URL_NOT_EXISTED){
                LogUtil.e(TAG, "onError: url is not existed! errorCode: " + ErrorCode.URL_NOT_EXISTED);
                onHttpError(ErrorCode.URL_NOT_EXISTED, "url is not existed!");
            }else{
                LogUtil.e(TAG, "onError: unknow error! errorCode: " + errorCode);
                onHttpError(errorCode, "unknow error!");
            }
        }else if(e instanceof UnknownHostException){
            LogUtil.e(TAG, "onError: DNS resolution failed! errorCode: " + ErrorCode.DNS_resolution_failed);
            onHttpError(ErrorCode.DNS_resolution_failed, "DNS resolution failed!");
        }else{
            LogUtil.e(TAG,  e.toString());
            onHttpError(ErrorCode.UNKNOW_ERROR, "unknow error!");
        }
    }

    @Override
    public void onNext(T t) {
        onHttpSuccess(t);
    }
}
