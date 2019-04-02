package com.example.jiabo.liveapp.presenter;

import com.example.jiabo.liveapp.base.BasePresenter;
import com.example.jiabo.liveapp.iview.ICreateLiveView;

/**
 * Created by jiabo
 * Data : 2019/3/27
 * Description : 创建直播界面（CreateLiveActivity）的presenter
 * Modify by
 */

public class CreateLivePresenter extends BasePresenter<ICreateLiveView> {


    public CreateLivePresenter(ICreateLiveView iView) {
        super(iView);
    }

    public void startLive(String liveTitle, String liveCover, String resolvingPower) {

    }

    /**
     * 从本地选择图片作为封面
     */
    public void loadCoverFromLocal() {

    }

}
