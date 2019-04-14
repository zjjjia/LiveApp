package com.example.jiabo.liveapp.network;

import com.example.jiabo.liveapp.Utils.LogUtil;
import com.example.jiabo.liveapp.constant.Constants;
import com.example.jiabo.liveapp.network.entity.CreateRoomResponse;
import com.example.jiabo.liveapp.network.entity.responseLiveRoomInfo;
import com.example.jiabo.liveapp.network.entity.LoginResponse;
import com.example.jiabo.liveapp.network.entity.RequestBackInfo;
import com.example.jiabo.liveapp.network.entity.ReportRoomInfo;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jiabo
 * Data : 2019/4/5
 * Description : 网络请求工具类
 * Modify by
 */

public class RetrofitFactory {

    private static final String TAG = "RetrofitFactory";
    private static final String baseUrl = "http://139.199.16.20/graduateProject/";

    private static OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .addInterceptor(getHttpLoggingInterceptor())
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build();

    private static ApiService mApiService = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
            .create(ApiService.class);

    /**
     * 注册账号
     */
    public static Observable<RequestBackInfo> register(String username, String password) {
        String jsonData = "{ \"id\":\"" + username + "\", \"pwd\": \"" + password + "\"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData);
        return mApiService.register("", "account", "regist", requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 账号登录
     */
    public static Observable<RequestBackInfo<LoginResponse>> login(String username, String password) {
        String jsonData = "{ \"id\":\"" + username + "\", \"pwd\":\"" + password + "\", \"appid\": "
                + Constants.SDK_APP_ID + "}";
        LogUtil.d(TAG, "login: " + jsonData);
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), jsonData);
        return mApiService.login("", "account", "login", requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 创建房间
     */
    public static Observable<RequestBackInfo<CreateRoomResponse>> createRoom(String token) {
        String type = "live";
        String jsonData = "{\"token\": \"" + token + "\", \"type\": " + "\"" + type + "\"}";
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), jsonData);
        return mApiService.createRoom("", "live", "create", requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 上报房间信息
     */
    public static Observable<RequestBackInfo> reportRoomInfo(String token, ReportRoomInfo reportRoomInfo) {
        String jsonData = "{\"token\": " + "\"" + token + "\", \"room\": {" + reportRoomInfo.toJsonString() + "}}";
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), jsonData);
        return mApiService.reportRoomInfo("", "live", "reportroom", requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 拉取直播房间列表
     *
     * @param index 起始房间位置(从0开始)
     * @param size  列表长度
     */
    public static Observable<RequestBackInfo<responseLiveRoomInfo>> loadLiveRoomInfo(String token, Integer index, Integer size) {
        String type = "live";
        String jsonData = "{\"token\": \"" + token + "\", \"type\": \"" + type + "\", \"index\": " + index + ", " +
                "\"size\": " + size + ", \"appid\": " + Constants.SDK_APP_ID + "}";
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), jsonData);
        return mApiService.loadLiveRoomInfo("", "lvie", "roomlist", requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 开启okHttp网路请求相关信息的log日志打印
     */
    private static Interceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }
}
