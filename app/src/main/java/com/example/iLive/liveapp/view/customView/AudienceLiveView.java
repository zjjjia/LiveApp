package com.example.iLive.liveapp.view.customView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iLive.liveapp.R;
import com.example.iLive.liveapp.Utils.LogUtil;
import com.example.iLive.liveapp.adapter.BarrageViewAdapter;
import com.example.iLive.liveapp.model.entity.BarrageMsg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiabo
 * Data : 2019/4/29
 * Description : 观看直播时view
 * Modify by
 */
public class AudienceLiveView extends LinearLayout {

    private static final String TAG = "AudienceLiveView";

    private Context mContext;
    private TextView mHostId;
    private TextView mRoomNum;
    private RecyclerView mBarrageRecyclerView;
    private BarrageViewAdapter mBarrageViewAdapter;
    private EditText mBarrageInput;
    private Button mSendBarrage;
    private AudienceLiveViewListener mListener;

    public AudienceLiveView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public AudienceLiveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    public void setHostId(String hostId) {
        mHostId.setText(hostId);
    }

    public void setRoomNum(int roomNum) {
        mRoomNum.setText(String.valueOf(roomNum));
    }

    public void setBarrageMsg(String hostId, String barrageMsg) {
        mBarrageViewAdapter.fillList(new BarrageMsg(hostId + ": ", barrageMsg));
        mBarrageRecyclerView.smoothScrollToPosition(mBarrageViewAdapter.getItemCount() - 1);
    }

    public void setListener(AudienceLiveViewListener listener) {
        mListener = listener;
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_audience_live_view, this, true);

        mHostId = view.findViewById(R.id.host_id);
        mRoomNum = view.findViewById(R.id.room_num);
        mBarrageRecyclerView = view.findViewById(R.id.barrage_recycler_view);
        mBarrageInput = view.findViewById(R.id.barrage_input);
        mSendBarrage = view.findViewById(R.id.send_barrage);

        mSendBarrage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String barrageMsg = mBarrageInput.getText().toString();
                mListener.onClickSendBtn(barrageMsg);
                mBarrageInput.getText().clear();
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        mBarrageViewAdapter = new BarrageViewAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBarrageRecyclerView.setLayoutManager(layoutManager);
        mBarrageRecyclerView.setAdapter(mBarrageViewAdapter);
    }

    public interface AudienceLiveViewListener {
        void onClickSendBtn(String barrageMsg);
    }
}
