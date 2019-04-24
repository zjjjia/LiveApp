package com.example.iLive.liveapp.callBack;

/**
 * Created by jiabo
 * Data : 2019/4/5
 * Description :
 * Modify by
 */

public interface HttpRequestCallback<T> {

    /**
     * 请求成功后的回调接口
     */
    void onSuccess(T response);

    /**
     * 请求失败后的回调接口
     */
    void onError(int errorCode, String errorInfo);
}
