package com.example.iLive.liveapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.iLive.liveapp.R;
import com.example.iLive.liveapp.Utils.LogUtil;
import com.example.iLive.liveapp.base.BaseActivity;
import com.example.iLive.liveapp.presenter.LivePresenter;
import com.example.iLive.liveapp.presenter.iview.ILiveView;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.trtc.TRTCCloud;

public class LiveActivity extends BaseActivity implements ILiveView {

    private static final String TAG = "LiveActivity";

    private LivePresenter mLivePresenter;
    private TXCloudVideoView mLocalVideoView;
    private int mUserRole;
    private int mRoomNum;
    private String mAnchorId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //应用运行时，保持屏幕高亮，不锁屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_live);

        Intent intent = getIntent();
        mUserRole = intent.getIntExtra("role", 0);
        mRoomNum = intent.getIntExtra("roomNum", 0);
        mAnchorId = intent.getStringExtra("anchorId");
        initView();

        mLivePresenter = new LivePresenter(this, this, mUserRole);

        mLivePresenter.enterRoom(mLocalVideoView, mRoomNum, mAnchorId);
    }

    private void initView() {
        mLocalVideoView = findViewById(R.id.local_video_preview);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mLivePresenter != null) {
            mLivePresenter.onStop();
        }
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
        Toast.makeText(this, "enter room sucess!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUserEnter(String userId) {
        Toast.makeText(this, userId + " enter room", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUserExit(String userId, int reason) {
        Toast.makeText(this, userId + " exit room", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onExitRoom(int reason) {
        Toast.makeText(this, "exit room", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFirstVideoFrame(String userId, int width, int height) {

    }
}
