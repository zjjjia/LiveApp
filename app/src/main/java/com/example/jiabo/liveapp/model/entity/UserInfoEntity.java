package com.example.jiabo.liveapp.model.entity;

/**
 * Created by jiabo
 * Data : 2019/4/5
 * Description : 用户个人信息的实习类
 * Modify by
 */

public class UserInfoEntity {

    private int errorCode;
    private String username;
    private String password;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
