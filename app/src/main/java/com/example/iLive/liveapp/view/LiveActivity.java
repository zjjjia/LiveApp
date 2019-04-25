package com.example.iLive.liveapp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.iLive.liveapp.R;
import com.example.iLive.liveapp.presenter.LivePresenter;
import com.example.iLive.liveapp.presenter.iview.ILiveView;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class LiveActivity extends AppCompatActivity implements ILiveView {

    private static final String TAG = "LiveActivity";

    private LivePresenter mLivePresenter;
    private TXCloudVideoView mLocalVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //应用运行时，保持屏幕高亮，不锁屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_live);

        initView();
        mLivePresenter = new LivePresenter(this, this);

        mLivePresenter.enterRoom(mLocalVideoView);
    }

    private void initView() {
        mLocalVideoView = findViewById(R.id.local_video_preview);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLivePresenter != null) {
            mLivePresenter.onDestroy();
        }
    }

    /*--------------------------------数据接口的实现----------------------------------*/

    @Override
    public void onError(int errCoe, String errMsg) {
        Toast.makeText(this, "onError: " + errMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onEnterRoom(long elapsed) {

    }
}
