package com.example.iLive.liveapp.network.entity;

/**
 * Created by jiabo
 * Data : 2019/4/9
 * Description : 请求创建房间的response
 * Modify by
 */

public class CreateRoomResponse {
    Integer roomnum;
    String groupid;

    public Integer getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(Integer roomnum) {
        this.roomnum = roomnum;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    @Override
    public String toString() {
        return "CreateRoomResponse{" +
                "roomnum=" + roomnum +
                ", groupid='" + groupid + '\'' +
                '}';
    }
}
