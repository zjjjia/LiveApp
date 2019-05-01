package com.example.iLive.liveapp.model.entity;

/**
 * Created by jiabo
 * Data : 2019/4/29
 * Description : 弹幕消息的实体类
 * Modify by
 */
public class BarrageMsg {

    String userName;
    String barrageMsg;

    public BarrageMsg(String userName, String barrageMsg) {
        this.userName = userName;
        this.barrageMsg = barrageMsg;
    }

    public String getUserName() {
        return userName;
    }

    public String getBarrageMsg() {
        return barrageMsg;
    }

}
