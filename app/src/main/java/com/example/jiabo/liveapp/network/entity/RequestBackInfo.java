package com.example.jiabo.liveapp.network.entity;

/**
 * Created by jiabo
 * Data : 2019/4/5
 * Description : 请求返回数据实体类
 * Modify by
 */

public class RequestBackInfo<T> {

    private int errorCode;
    private String errorInfo;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RequestBackInfo{" +
                "errorCode=" + errorCode +
                ", errorInfo='" + errorInfo + '\'' +
                ", data=" + data +
                '}';
    }
}
