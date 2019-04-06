package com.example.jiabo.liveapp.model.entity;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by jiabo
 * Data : 2019/3/25
 * Description : 主页直播列表item的实体类
 * Modify by
 */

public class LiveListEntity {

    private Bitmap liveCoverImg;
    private String liveTitle;
    private String liveBrieIntroduction;
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public Uri getUrl() {
        return Uri.parse(url);
    }

    public Bitmap getLiveCoverImg() {
        return liveCoverImg;
    }

    public void setLiveCoverImg(Bitmap liveCoverImg) {
        this.liveCoverImg = liveCoverImg;
    }

    public String getLiveTitle() {
        return liveTitle;
    }

    public void setLiveTitle(String liveTitle) {
        this.liveTitle = liveTitle;
    }

    public String getLiveBrieIntroduction() {
        return liveBrieIntroduction;
    }

    public void setLiveBrieIntroduction(String liveBrieIntroduction) {
        this.liveBrieIntroduction = liveBrieIntroduction;
    }
}

