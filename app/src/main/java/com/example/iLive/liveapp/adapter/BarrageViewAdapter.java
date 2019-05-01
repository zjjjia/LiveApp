package com.example.iLive.liveapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iLive.liveapp.R;
import com.example.iLive.liveapp.model.entity.BarrageMsg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiabo
 * Data : 2019/4/29
 * Description : 弹幕列表recyclerView
 * Modify by
 */
public class BarrageViewAdapter extends RecyclerView.Adapter<BarrageViewAdapter.BarrageMsgViewHolder> {

    private static final String TAG = "BarrageViewAdapter";
    private List<BarrageMsg> mBarrageMsgList = new ArrayList<>();


    @Override
    public BarrageMsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barrage_list, parent, false);

        return new BarrageMsgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BarrageMsgViewHolder holder, int position) {
        holder.userName.setText(mBarrageMsgList.get(position).getUserName());
        holder.barrageMsg.setText(mBarrageMsgList.get(position).getBarrageMsg());
    }

    @Override
    public int getItemCount() {
        return mBarrageMsgList.size();
    }

    public void fillList(BarrageMsg barrageMsg) {
        mBarrageMsgList.add(barrageMsg);
        notifyItemInserted(mBarrageMsgList.size() - 1);

    }

    public class BarrageMsgViewHolder extends RecyclerView.ViewHolder {

        TextView userName;
        TextView barrageMsg;

        public BarrageMsgViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_id);
            barrageMsg = itemView.findViewById(R.id.barrage_message);
        }
    }
}
