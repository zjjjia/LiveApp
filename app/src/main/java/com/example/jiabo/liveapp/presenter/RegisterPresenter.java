package com.example.jiabo.liveapp.presenter;

import com.example.jiabo.liveapp.base.BasePresenter;
import com.example.jiabo.liveapp.iview.IRegisterView;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * @author jiabo
 * Date: 2019/3/15 & 11:21
 * Version : 1.0
 * description : 注册的prenter
 * * Modify by
 */
public class RegisterPresenter extends BasePresenter<IRegisterView> {

    public RegisterPresenter(IRegisterView iView) {
        super(iView);
    }

    public void register(String phoneNumber, String username, String password) {
        Observable.create(new Observable.OnSubscribe<String>() {
            public void call(Subscriber<? super String> subscriber) {

            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String result) {
                        //登录结果的处理
                    }
                });
    }
}
