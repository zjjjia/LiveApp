package com.example.jiabo.liveapp.model.entity;

/**
 * Created by jiabo
 * Data : 2019/4/6
 * Description : 登录的response中data的实体类
 * Modify by
 */

public class LoginResponseEntity {
    private int roomnum;
    private String groundid;

    public int getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(int roomnum) {
        this.roomnum = roomnum;
    }

    public String getGroundid() {
        return groundid;
    }

    public void setGroundid(String groundid) {
        this.groundid = groundid;
    }

    @Override
    public String toString() {
        return "data{" +
                "roomnum=" + roomnum +
                ", groundid='" + groundid + '\'' +
                '}';
    }


}
