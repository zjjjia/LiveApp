package com.example.jiabo.liveapp.base;

/**
 * @author jiabo
 * Date: 2019/3/15 & 9:55
 * Version : 1.0
 * description :
 * * Modify by
 */
public abstract class BasePresenter<T extends IView> {
    protected T mIView;

    public BasePresenter(T iView){
        this.mIView = iView;
    }

    public void onDestroy(){
        mIView = null;
    }
}
