package com.example.iLive.liveapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iLive.liveapp.R;
import com.example.iLive.liveapp.Utils.LogUtil;
import com.example.iLive.liveapp.model.entity.LiveListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiabo
 * Data : 2019/3/25
 * Description : 主页直播列表RecyclerView的adapter
 * Modify by
 */

public class LiveListAdapter extends RecyclerView.Adapter<LiveListAdapter.LiveLiveViewHolder> {

    private static final String TAG = "LiveListAdapter";

    private List<LiveListEntity> mLiveList = new ArrayList<>();


    @NonNull
    @Override
    public LiveLiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live_list, parent,
                false);

        LiveLiveViewHolder liveLiveViewHolder = new LiveLiveViewHolder(view);
        return liveLiveViewHolder;
    }

    public void fillList(List<LiveListEntity> liveList) {
        if (liveList == null) {
            LogUtil.e(TAG, "fillList: liveLive is null!");
        }
        mLiveList = liveList;
    }

    @Override
    public void onBindViewHolder(@NonNull LiveLiveViewHolder holder, int position) {
        holder.liveCover.setImageBitmap(mLiveList.get(position).getLiveCoverImg());
        holder.liveCover.setImageURI(mLiveList.get(position).getUrl());
        holder.liveTitle.setText(mLiveList.get(position).getLiveTitle());
        holder.liveBrieIntroduction.setText(mLiveList.get(position).getLiveBrieIntroduction());
    }

    @Override
    public int getItemCount() {
        return mLiveList.size();
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
}
