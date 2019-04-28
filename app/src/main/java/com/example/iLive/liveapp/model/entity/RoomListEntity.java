package com.example.iLive.liveapp.model.entity;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by jiabo
 * Data : 2019/3/25
 * Description : 主页房间列表item的实体类
 * Modify by
 */

public class RoomListEntity {

    private String title;
    private int roomnum;
    private String type;
    private int groupid;
    private String cover;
    private int thumbup;
    private int memsize;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(int roomnum) {
        this.roomnum = roomnum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getThumbup() {
        return thumbup;
    }

    public void setThumbup(int thumbup) {
        this.thumbup = thumbup;
    }

    public int getMemsize() {
        return memsize;
    }

    public void setMemsize(int memsize) {
        this.memsize = memsize;
    }
}

