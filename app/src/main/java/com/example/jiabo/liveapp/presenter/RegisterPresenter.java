package com.example.jiabo.liveapp.presenter;

import com.example.jiabo.liveapp.base.BasePresenter;
import com.example.jiabo.liveapp.iview.IRegisterView;
import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.core.ILiveLoginManager;

/**
 * @author jiabo
 * Date: 2019/3/15 & 11:21
 * Version : 1.0
 * description : 注册的prenter
 * * Modify by
 */
public class RegisterPresenter extends BasePresenter<IRegisterView> {

    private static final String TAG = "RegisterPresenter";

    public RegisterPresenter(IRegisterView iView) {
        super(iView);
    }

    public void register(final String username, String password) {
        ILiveLoginManager.getInstance().tlsRegister(username, password, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                mIView.onSuccessInRegister(username, data);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                mIView.onFailureInRegister(module, errCode, errMsg);
            }
        });
    }
}
