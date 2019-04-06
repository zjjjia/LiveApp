package com.example.jiabo.liveapp.network;

import com.example.jiabo.liveapp.model.entity.LoginResponseEntity;
import com.example.jiabo.liveapp.model.entity.RequestBackInfo;
import com.example.jiabo.liveapp.model.entity.UserInfoEntity;

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
    Observable<RequestBackInfo<LoginResponseEntity>> login(@Url String url, @Query("svc") String svc, @Query("cmd") String cmd,
                                                           @Body RequestBody requestBody);
}
