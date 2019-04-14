package com.example.jiabo.liveapp.network.entity;

import com.example.jiabo.liveapp.constant.Constants;

/**
 * Created by jiabo
 * Data : 2019/4/9
 * Description : 创建的房间信息
 * Modify by
 */

public class ReportRoomInfo {
    private String title;
    private String type;
    private Integer roomnum;
    private String groupid;
    private String cover;
    /**
     * 主播ID
     */
    private String host;
    /**
     * 0：IOS
     * 1：Android
     * 2：PC
     */
    private Integer device = 1;
    /**
     * 0：摄像头
     * 1：屏幕分享
     */
    private Integer videoType = 0;
    private Integer appid = Constants.SDK_APP_ID;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getDevice() {
        return device;
    }

    public void setDevice(Integer device) {
        this.device = device;
    }

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    @Override
    public String toString() {
        return "ReportRoomInfo{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", roomnum=" + roomnum +
                ", groupid='" + groupid + '\'' +
                ", cover='" + cover + '\'' +
                ", host='" + host + '\'' +
                ", device=" + device +
                ", videoType=" + videoType +
                ", appid=" + appid +
                '}';
    }

    public String toJsonString() {
        return "\"title\": \"" + title + "\", " +
                "\"roomnum\": " + roomnum + ", " +
                "\"type\": \"" + type + "\", " +
                "\"groupid\": \"" + groupid + "\", " +
                "\"cover\": \"" + cover + "\", " +
                "\"appid\": " + appid + ", " +
                "\"device\": " + device + ", " +
                "\"videotype\": " + videoType;
    }
}
