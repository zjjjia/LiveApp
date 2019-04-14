package com.example.jiabo.liveapp.network.entity;

/**
 * Created by jiabo
 * Data : 2019/4/6
 * Description : 登录的response中data的实体类
 * Modify by
 */

public class LoginResponse {
    private String userSig;
    private String token;

    public String getUserSig() {
        return userSig;
    }

    public void setUserSig(String userSig) {
        this.userSig = userSig;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "data{" +
                "userSig=" + userSig +
                ", token='" + token + '\'' +
                '}';
    }


}
