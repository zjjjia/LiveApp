package com.example.jiabo.liveapp.model.entity;

import com.example.iLive.liveapp.model.entity.RecordInfo;

/**
 * Created by jiabo
 * Data : 2019/4/11
 * Description : 当前直播房间的信息
 * Modify by
 */

public class CurrentLiveInfo {
    private static int members;
    private static int admires;
    private static String title;
    private static double lat1;
    private static double long1;
    private static String address = "";
    private static String coverUrl = "";
    private static int curRole;

    private static RecordInfo mRecordInfo;

    public static int roomNum;

    public static String hostID;
    public static String hostName;
    public static String hostAvator;

    public static int currentRequestCount = 0;

    public static RecordInfo getmRecordInfo() {
        return mRecordInfo;
    }

    public static void setmRecordInfo(RecordInfo mRecordInfo) {
        CurrentLiveInfo.mRecordInfo = mRecordInfo;
    }

    public static int getRoomNum() {
        return roomNum;
    }

    public static void setRoomNum(int roomNum) {
        CurrentLiveInfo.roomNum = roomNum;
    }

    public static String getHostID() {
        return hostID;
    }

    public static void setHostID(String hostID) {
        CurrentLiveInfo.hostID = hostID;
    }

    public static String getHostName() {
        return hostName;
    }

    public static void setHostName(String hostName) {
        CurrentLiveInfo.hostName = hostName;
    }

    public static String getHostAvator() {
        return hostAvator;
    }

    public static void setHostAvator(String hostAvator) {
        CurrentLiveInfo.hostAvator = hostAvator;
    }

    public static int getCurrentRequestCount() {
        return currentRequestCount;
    }

    public static void setCurrentRequestCount(int currentRequestCount) {
        CurrentLiveInfo.currentRequestCount = currentRequestCount;
    }

    public static int getMembers() {
        return members;
    }

    public static void setMembers(int members) {
        CurrentLiveInfo.members = members;
    }

    public static int getAdmires() {
        return admires;
    }

    public static void setAdmires(int admires) {
        CurrentLiveInfo.admires = admires;
    }

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        CurrentLiveInfo.title = title;
    }

    public static double getLat1() {
        return lat1;
    }

    public static void setLat1(double lat1) {
        CurrentLiveInfo.lat1 = lat1;
    }

    public static double getLong1() {
        return long1;
    }

    public static void setLong1(double long1) {
        CurrentLiveInfo.long1 = long1;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        CurrentLiveInfo.address = address;
    }

    public static String getCoverUrl() {
        return coverUrl;
    }

    public static void setCoverUrl(String coverUrl) {
        CurrentLiveInfo.coverUrl = coverUrl;
    }

    public static int getCurRole() {
        return curRole;
    }

    public static void setCurRole(int curRole) {
        CurrentLiveInfo.curRole = curRole;
    }
}
