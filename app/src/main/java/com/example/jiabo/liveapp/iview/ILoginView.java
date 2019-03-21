package com.example.jiabo.liveapp.iview;

import com.example.jiabo.liveapp.base.IView;

/**
 * @author jiabo
 * Date: 2019/3/15 & 9:51
 * Version : 1.0
 * description :
 * * Modify by
 */
public interface ILoginView extends IView {

    /**
     * 登录成功后的回调
     */
    void onSuccessInLogin(Object data);

    /**
     * 登录失败后的回调
     */
    void onFailureInLogin(String module, int errCode, String errMsg);
}
