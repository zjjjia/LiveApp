package com.example.jiabo.liveapp.iview;

import com.example.jiabo.liveapp.base.IView;

/**
 * @author jiabo
 * Date: 2019/3/15 & 11:23
 * Version : 1.0
 * description : 注册的Iview接口
 * * Modify by
 */
public interface IRegisterView extends IView {

    /**
     * 注册成功后的回调
     */
    void onSuccessInRegister();

    /**
     * 注册失败后的回调
     */
    void onFailureInRegister();
}
