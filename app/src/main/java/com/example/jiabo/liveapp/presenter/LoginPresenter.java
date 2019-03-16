package com.example.jiabo.liveapp.presenter;

import com.example.jiabo.liveapp.base.BasePresenter;
import com.example.jiabo.liveapp.iview.ILoginView;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;


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
    public void login(String userName, String userPassword) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("successful");
                subscriber.onCompleted();
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
                        //登录结果的逻辑处理
                        mIView.onSuccessInLogin(result);
                    }
                });
    }
}
