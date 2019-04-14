package com.example.jiabo.liveapp.presenter.iview;

import com.example.jiabo.liveapp.base.IView;

/**
 * @author jiabo
 * Date: 2019/3/15 & 9:51
 * Version : 1.0
 * description : 登录界面数据接口类
 * * Modify by
 */
public interface ILoginView extends IView {

    /**
     * 登录成功后的回调
     */
    void onSuccessInLogin();

    /**
     * 登录失败后的回调
     */
    void onFailureInLogin(int errCode, String errMsg);
}
