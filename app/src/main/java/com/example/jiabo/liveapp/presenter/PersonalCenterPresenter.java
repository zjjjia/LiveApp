package com.example.jiabo.liveapp.presenter;

import android.content.Context;

import com.example.jiabo.liveapp.base.BasePresenter;
import com.example.jiabo.liveapp.presenter.iview.IPersonalCenterView;

/**
 * Created by jiabo
 * Data : 2019/3/28
 * Description :  个人中心界面的presenter层
 * Modify by
 */

public class PersonalCenterPresenter extends BasePresenter<IPersonalCenterView> {


    public PersonalCenterPresenter(Context context, IPersonalCenterView iView) {
        super(context, iView);

    }

    /**
     * 退出登录
     */
    public void logOut() {

    }
}
