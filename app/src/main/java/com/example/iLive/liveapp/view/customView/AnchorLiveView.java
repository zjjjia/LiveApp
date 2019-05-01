package com.example.iLive.liveapp.view.customView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iLive.liveapp.R;
import com.example.iLive.liveapp.adapter.BarrageViewAdapter;
import com.example.iLive.liveapp.model.entity.BarrageMsg;

/**
 * Created by jiabo
 * Data : 2019/4/29
 * Description : 主播的view
 * Modify by
 */
public class AnchorLiveView extends LinearLayout {

    private Context mContext;
    private TextView mRoomNum;
    private RecyclerView mBarrageRecyclerView;
    private BarrageViewAdapter mBarrageViewAdapter;

    public AnchorLiveView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public AnchorLiveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void setRoomNum(int roomNum) {
        mRoomNum.setText(String.valueOf(roomNum));
    }

    public void setBarrageMsg(String userId, String barrageMsg) {
        mBarrageViewAdapter.fillList(new BarrageMsg(userId, barrageMsg));
        mBarrageRecyclerView.smoothScrollToPosition(mBarrageViewAdapter.getItemCount() - 1);
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_anchor_live_view, this, true);
        mRoomNum = view.findViewById(R.id.room_num);
        mBarrageRecyclerView = view.findViewById(R.id.barrage_recycler_view);

        initRecyclerView();
    }

    private void initRecyclerView() {
        mBarrageViewAdapter = new BarrageViewAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBarrageRecyclerView.setLayoutManager(layoutManager);
        mBarrageRecyclerView.setAdapter(mBarrageViewAdapter);
    }
}
