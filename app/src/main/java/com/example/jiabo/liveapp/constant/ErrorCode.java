package com.example.jiabo.liveapp.constant;

/**
 * Created by jiabo
 * Data : 2019/4/5
 * Description : 错误码常量类
 * Modify by
 */

public class ErrorCode {

    public static final int SUCCESS = 0;
    /**
     * 请求错误
     */
    public static final int REQUEST_ERROR = 1001;
    /**
     * 请求json错误
     */
    public static final int JSON_ERROR = 1002;
    /**
     * 请求数据错误
     */
    public static final int DATA_ERROR = 1003;
    /**
     * 用户已经注册
     */
    public static final int USER_EXISTED = 1004;
    /**
     * 用户不存在
     */
    public static final int USER_NOT_EXISTED = 1005;
    /**
     * 密码错误
     */
    public static final int PASSWORD_ERROR = 1006;
    /**
     * 重复登录
     */
    public static final int REPEAT_LOGIN = 1007;
    /**
     * 重复退出
     */
    public static final int REPEAT_LOGOUT = 1008;
    /**
     * token过期
     */
    public static final int TOKEN_INVALIED = 1008;
    /**
     * 直播房间不存在
     */
    public static final int LIVE_ROOT_NOT_EXISTED = 1010;
    /**
     * 用户没有ab房间ID
     */
    public static final int NO_AV_ROOM_ID = 2001;
    /**
     * 服务器内部错误
     */
    public static final int SERVER_ERROR = 9000;

    /**
     * 未知错误
     */
    public static final int UNKNOW_ERROR = 3000;

    /**
     * 请求超时
     */
    public static final int REQUEST_TIME_OUT = 3001;
    /**
     * 网络连接超时
     */
    public static final int CONNECT_TIME_OUT = 3002;
    /**
     * 请求地址不存在
     */
    public static final int URL_NOT_EXISTED = 404;
    /**
     * 网络异常，检查网络状态
     */
    public static final int INTERNET_ERROR = 504;
    /**
     * 请求失败
     */
    public static final int REQUEST_FAILED = 3005;
    /**
     * 域名解析失败
     */
    public static final int DNS_resolution_failed = 3006;
}
