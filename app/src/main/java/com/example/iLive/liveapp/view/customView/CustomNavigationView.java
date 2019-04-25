package com.example.iLive.liveapp.view.customView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.iLive.liveapp.R;
import com.example.iLive.liveapp.Utils.LogUtil;

/**
 * @author jiabo
 *         Date: 2019/3/19 & 21:42
 *         Version : 1.0
 *         description : 自定义view底部导航栏的实现类
 *         * Modify by
 */
public class CustomNavigationView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "ItemNavigationView";

    private ImageButton homeBtn;
    private ImageButton liveBtn;
    private ImageButton centerBtn;
    private NavigationClickListener mClickListener;

    private final static int HOME_PAGER_POSITION = 0;
    private final static int CENTER_PAGER_POSITION = 1;

    public CustomNavigationView(Context context) {
        super(context);
        initView();
    }

    public CustomNavigationView(Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void setOnClickListener(NavigationClickListener clickListener) {
        this.mClickListener = clickListener;

    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_navigation_view, this, true);

        homeBtn = view.findViewById(R.id.navigation_home);
        liveBtn = view.findViewById(R.id.navigation_live);
        centerBtn = view.findViewById(R.id.navigation_center);

        homeBtn.setOnClickListener(this);
        liveBtn.setOnClickListener(this);
        centerBtn.setOnClickListener(this);

    }

    public void changeButtonIco(int fragmentPosition) {
        switch (fragmentPosition) {
            case HOME_PAGER_POSITION:
                homeBtn.setImageDrawable(getResources().getDrawable(R.drawable.home_yellow));
                liveBtn.setImageDrawable(getResources().getDrawable(R.drawable.live));
                centerBtn.setImageDrawable(getResources().getDrawable(R.drawable.center));
                break;
            case CENTER_PAGER_POSITION:
                homeBtn.setImageDrawable(getResources().getDrawable(R.drawable.home));
                liveBtn.setImageDrawable(getResources().getDrawable(R.drawable.live));
                centerBtn.setImageDrawable(getResources().getDrawable(R.drawable.center_yellow));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (mClickListener == null) {
            LogUtil.e(TAG, "onClick: navigationListener = null");
            return;
        }
        switch (v.getId()) {
            case R.id.navigation_home:
                mClickListener.homeClickListener();
                break;
            case R.id.navigation_live:
                mClickListener.liveClickListener();
                break;
            case R.id.navigation_center:
                mClickListener.centerClickListener();
                break;
            default:
        }
    }

    public interface NavigationClickListener {
        void homeClickListener();

        void liveClickListener();

        void centerClickListener();
    }
}
