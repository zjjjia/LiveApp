package com.example.jiabo.liveapp.presenter;

import com.example.jiabo.liveapp.base.BasePresenter;
import com.example.jiabo.liveapp.iview.ILoginView;


/**
 * @author jiabo
 * Date: 2019/3/15 & 10:06
 * Version : 1.0
 * description : 登录的Presenter
 * * Modify by
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(ILoginView iView) {
        super(iView);
    }

    /**
     * 进行登录验证的操作
     */
    public void login(String username, String password) {

    }
}
