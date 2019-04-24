package com.example.iLive.liveapp.model.entity;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.iLive.liveapp.constant.Constants;

/**
 * Created by jiabo
 * Data : 2019/4/7
 * Description : 自己的数据状态
 * Modify by
 */

public class MyselfInfo {
    private static final String TAG = "MyselfInfo";

    private String id;
    private String userSign;
    private String nickName;   //昵称
    private String avatar;    //头像
    private String sign;         //签名
    private String COsSig;
    private String token;
    private static boolean isCreateRoom = false;

    private int myRoomNum;
    private String groupid;    //Im群组ID

    private static MyselfInfo ourInstance = new MyselfInfo();

    public static MyselfInfo getInstance(){
        return ourInstance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCOsSig() {
        return COsSig;
    }

    public void setCOsSig(String COsSig) {
        this.COsSig = COsSig;
    }

    public static boolean isIsCreateRoom() {
        return isCreateRoom;
    }

    public static void setJoinRoomWay(boolean isCreateRoom) {
        MyselfInfo.isCreateRoom = isCreateRoom;
    }

    public boolean isCreateRoom(){
        return  isCreateRoom;
    }

    public int getMyRoomNum() {
        return myRoomNum;
    }

    public void setMyRoomNum(int myRoomNum) {
        this.myRoomNum = myRoomNum;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public void writeToCache(Context context){
        if(context != null){
            SharedPreferences settings = context.getSharedPreferences(Constants.USER_INFO, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(Constants.USER_ID, id);
            editor.putString(Constants.USER_SIG, userSign);
            editor.putString(Constants.USER_TOKEN, token);
            editor.putString(Constants.USER_NICK, nickName);
            editor.putString(Constants.USER_AVATAR, avatar);
            editor.putString(Constants.USER_SIGN, sign);
            editor.putInt(Constants.USER_ROOM_NUM, myRoomNum);
            editor.commit();
        }
    }

    public void clearCache(Context context){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_INFO, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public void getCache(Context context){
        SharedPreferences shareData = context.getSharedPreferences(Constants.USER_INFO, 0);
        id = shareData.getString(Constants.USER_INFO,null);
        userSign = shareData.getString(Constants.USER_SIGN, null);
        token = shareData.getString(Constants.USER_TOKEN, null);
        myRoomNum = shareData.getInt(Constants.USER_ROOM_NUM, 404);
        nickName = shareData.getString(Constants.USER_NICK, null);
        avatar = shareData.getString(Constants.USER_AVATAR, null);
        sign = shareData.getString(Constants.USER_SIG, null);
    }
}
