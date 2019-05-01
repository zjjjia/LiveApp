package com.example.iLive.liveapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.iLive.liveapp.R;
import com.example.iLive.liveapp.Utils.LogUtil;
import com.example.iLive.liveapp.base.BaseActivity;
import com.example.iLive.liveapp.constant.Constants;
import com.example.iLive.liveapp.model.entity.CurrentLiveInfo;
import com.example.iLive.liveapp.model.entity.MyselfInfo;
import com.example.iLive.liveapp.presenter.LivePresenter;
import com.example.iLive.liveapp.presenter.iview.ILiveView;
import com.example.iLive.liveapp.view.customView.AnchorLiveView;
import com.example.iLive.liveapp.view.customView.AudienceLiveView;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class LiveActivity extends BaseActivity implements ILiveView {

    private static final String TAG = "LiveActivity";

    private LivePresenter mLivePresenter;
    private TXCloudVideoView mLocalVideoView;
    private int mUserRole;
    private int mRoomNum;
    private String mAnchorId;
    private AnchorLiveView mAnchorLiveView;
    private AudienceLiveView mAudienceLiveView;

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

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(params);
        ConstraintLayout constraintLayout = findViewById(R.id.live_parent);

        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        if (mUserRole == Constants.ROLE_ANCHOR) {                              //主播进入房间
            LogUtil.d(TAG, "initView: add anchor view");
            mAnchorLiveView = new AnchorLiveView(this);
            mAnchorLiveView.setLayoutParams(layoutParams);
            mAnchorLiveView.setRoomNum(CurrentLiveInfo.getRoomNum());
            linearLayout.addView(mAnchorLiveView);
        } else if (mUserRole == Constants.ROLE_AUDIENCE) {                  //观众进入房间
            LogUtil.d(TAG, "initView: add audience view");
            mAudienceLiveView = new AudienceLiveView(this);
            mAudienceLiveView.setLayoutParams(layoutParams);
            initAudienceViewData();
            linearLayout.addView(mAudienceLiveView);
        }
        constraintLayout.addView(linearLayout);
    }

    private void initAudienceViewData() {
        if (mAudienceLiveView != null) {
            mAudienceLiveView.setHostId(mAnchorId);
            mAudienceLiveView.setRoomNum(mRoomNum);
            mAudienceLiveView.setListener(new AudienceLiveView.AudienceLiveViewListener() {
                @Override
                public void onClickSendBtn(String barrageMsg) {
                    mAudienceLiveView.setBarrageMsg(MyselfInfo.getInstance().getId(), barrageMsg);
                    mLivePresenter.sendBarrageMsg(barrageMsg);
                }
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.d(TAG, "----------------------LiveActivity onStop----------------------------");
        if (mLivePresenter != null) {
            mLivePresenter.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "----------------------LiveActivity onDestroy----------------------------");
        if (mLivePresenter != null) {
            mLivePresenter.onDestroy();
        }
        finish();
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
        Toast.makeText(this, R.string.host_exited, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void hostExitRoom() {
        Toast.makeText(this, R.string.host_exited, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onRecvCustomCmdMsg(String userId, int cmdID, String message) {
        mAnchorLiveView.setBarrageMsg(userId, message);
    }

    @Override
    public void onFirstVideoFrame(String userId, int width, int height) {

    }
}
