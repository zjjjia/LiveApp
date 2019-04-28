package com.example.iLive.liveapp.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iLive.liveapp.R;
import com.example.iLive.liveapp.Utils.LogUtil;
import com.example.iLive.liveapp.network.entity.ResponseLiveRoomInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiabo
 * Data : 2019/3/25
 * Description : 主页直播列表RecyclerView的adapter
 * Modify by
 */

public class LiveRoomListAdapter extends RecyclerView.Adapter<LiveRoomListAdapter.LiveLiveViewHolder> {

    private static final String TAG = "LiveRoomListAdapter";

    private List<ResponseLiveRoomInfo.Room> mLiveList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    @NonNull
    @Override
    public LiveLiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live_list, parent,
                false);


        return new LiveLiveViewHolder(view);
    }

    public void fillList(List<ResponseLiveRoomInfo.Room> liveList) {
        if (liveList == null) {
            LogUtil.e(TAG, "fillList: liveLive is null!");
        }
        mLiveList = liveList;
    }

    @Override
    public void onBindViewHolder(@NonNull LiveLiveViewHolder holder, final int position) {
        //holder.liveCover.setImageBitmap(mLiveList.get(position).getLiveCoverImg());
        holder.liveCover.setImageURI(Uri.parse(mLiveList.get(position).getInfo().getCover()));
        holder.liveTitle.setText(mLiveList.get(position).getInfo().getTitle());
        //holder.liveBrieIntroduction.setText(mLiveList.get(position).getLiveBrieIntroduction());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLiveList.size();
    }

    public void setOnItemClickListten(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    static class LiveLiveViewHolder extends RecyclerView.ViewHolder {

        ImageView liveCover;
        TextView liveTitle;
        TextView liveBrieIntroduction;

        public LiveLiveViewHolder(View itemView) {
            super(itemView);

            liveCover = itemView.findViewById(R.id.cover_img_view);
            liveTitle = itemView.findViewById(R.id.live_title);
            liveBrieIntroduction = itemView.findViewById(R.id.live_brief_introduction);
        }
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }
}
