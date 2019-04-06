package com.example.jiabo.liveapp.model.entity;

/**
 * Created by jiabo
 * Data : 2019/4/2
 * Description : 创建直播页面的数据实体类
 * Modify by
 */

public class CreateLiveEntity {

    /**
     * 直播的封面
     */
    private String coverImgUrl;
    /**
     * 直播的粉分辨率
     */
    private String resolvingPower;
    /**
     * 直播的标题
     */
    private String liveTitle;

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getLiveTitle() {
        return liveTitle;
    }

    public void setLiveTitle(String liveTitle) {
        this.liveTitle = liveTitle;
    }

    public String getResolvingPower() {
        return resolvingPower;
    }

    public void setResolvingPower(String resolvingPower) {
        this.resolvingPower = resolvingPower;
    }
}
