package com.example.jiabo.liveapp.network;

import com.example.jiabo.liveapp.network.entity.CreateRoomResponse;
import com.example.jiabo.liveapp.network.entity.responseLiveRoomInfo;
import com.example.jiabo.liveapp.network.entity.LoginResponse;
import com.example.jiabo.liveapp.network.entity.RequestBackInfo;

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

    @POST
    Observable<RequestBackInfo> register(@Url String url, @Query("svc") String svc, @Query("cmd") String cmd,
                                         @Body RequestBody requestBody);

    @POST
    Observable<RequestBackInfo<LoginResponse>> login(@Url String url, @Query("svc") String svc,
                                                     @Query("cmd") String cmd, @Body RequestBody requestBody);

    @POST
    Observable<RequestBackInfo<CreateRoomResponse>> createRoom(@Url String url, @Query("svc") String svc,
                                                               @Query("cmd") String cmd, @Body RequestBody requestBody);

    @POST
    Observable<RequestBackInfo> reportRoomInfo(@Url String url, @Query("svc") String svc, @Query("cmd") String cmd,
                                               @Body RequestBody requestBody);

    @POST
    Observable<RequestBackInfo<responseLiveRoomInfo>> loadLiveRoomInfo(@Url String url, @Query("svc") String svc,
                                                                       @Query("cmd") String cmd, @Body RequestBody requestBody);
}
