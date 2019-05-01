package com.example.iLive.liveapp.constant;

/**
 * Created by jiabo
 * Data : 2019/4/3
 * Description :
 * Modify by
 */

public class Constants {

    public static final String USER_INFO = "user_info";
    public static final String USER_ID = "user_id";
    public static final String USER_SIG = "user_sig";
    public static final String USER_TOKEN = "user_token";
    public static final String USER_NICK = "user_nick";
    public static final String USER_AVATAR = "user_avatar";
    public static final String USER_SIGN = "user_sign";
    public static final String USER_ROOM_NUM = "user_room_num";

    /**
     * 互动直播sdk 的sdk appid
     */
    public static final int SDK_APP_ID = 1400205648;

    /**
     * 进入房间的角色；0：主播；1：观众
     */
    public static final int ROLE_ANCHOR = 0;
    public static final int ROLE_AUDIENCE = 1;

    /**
     * 自定义消息类型
     */
    public static class MessageType {
        /**
         * 文本消息
         */
        public static final int TEXT_MSG = 0;
    }
}
