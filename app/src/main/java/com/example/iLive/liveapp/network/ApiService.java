package com.example.iLive.liveapp.network;

import com.example.iLive.liveapp.network.entity.CreateRoomResponse;
import com.example.iLive.liveapp.network.entity.ResponseLiveRoomInfo;
import com.example.iLive.liveapp.network.entity.LoginResponse;
import com.example.iLive.liveapp.network.entity.RequestBackInfo;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by jiabo
 * Data : 2019/4/5
 * Description : retrofit 请求接口
 * Modify by
 */

public interface ApiService {

    /**
     * 注册账号
     */
    @POST
    Observable<RequestBackInfo> register(@Url String url, @Query("svc") String svc, @Query("cmd") String cmd,
                                         @Body RequestBody requestBody);

    /**
     * 账号登录，获取userSig
     */
    @POST
    Observable<RequestBackInfo<LoginResponse>> login(@Url String url, @Query("svc") String svc,
                                                     @Query("cmd") String cmd, @Body RequestBody requestBody);

    /**
     * 创建房间,获取房间ID和群组ID
     */
    @POST
    Observable<RequestBackInfo<CreateRoomResponse>> createRoom(@Url String url, @Query("svc") String svc,
                                                               @Query("cmd") String cmd, @Body RequestBody requestBody);

    /**
     * 上报房间信息
     */
    @POST
    Observable<RequestBackInfo> reportRoomInfo(@Url String url, @Query("svc") String svc, @Query("cmd") String cmd,
                                               @Body RequestBody requestBody);

    /**
     * 获取房间信息
     */
    @POST
    Observable<RequestBackInfo<ResponseLiveRoomInfo>> loadLiveRoomInfo(@Url String url, @Query("svc") String svc,
                                                                       @Query("cmd") String cmd,
                                                                       @Body RequestBody requestBody);

    /**
     * 退出房间
     * */
    @POST
    Observable<RequestBackInfo> exitRoom(@Url String url, @Query("svc") String svc, @Query("cmd") String cmd,
                                         @Body RequestBody requestBody);
}
