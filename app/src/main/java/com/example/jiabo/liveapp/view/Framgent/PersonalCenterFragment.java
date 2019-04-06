package com.example.jiabo.liveapp.view.Framgent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.jiabo.liveapp.R;
import com.example.jiabo.liveapp.presenter.iview.IPersonalCenterView;
import com.example.jiabo.liveapp.presenter.PersonalCenterPresenter;

/**
 * @author jiabo
 *         Date: 2019/3/20 & 14:40
 *         Version : 1.0
 *         description :  个人中心的Fragment
 *         * Modify by
 */
public class PersonalCenterFragment extends Fragment implements View.OnClickListener, IPersonalCenterView {

    private static final String TAG = "PersonalCenterFragment";
    private ImageView mSetHeadImg;
    private ImageButton mSetPersonalData;
    private Button mLogOutBtn;
    private PersonalCenterPresenter mPersonalPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_center_pager, null);
        mPersonalPresenter = new PersonalCenterPresenter(this);
        initView(view);

        return view;
    }

    private void initView(View view) {
        mSetHeadImg = view.findViewById(R.id.head_img_view);
        mSetPersonalData = view.findViewById(R.id.set_personal_data_btn);
        mLogOutBtn = view.findViewById(R.id.logout_btn);
    }

    /*----------------------------这里是接口的实现--------------------------------*/

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_img_view:

                break;
            case R.id.set_personal_data_btn:

                break;
            case R.id.logout_btn:
                mPersonalPresenter.logOut();
                break;
        }
    }
}
