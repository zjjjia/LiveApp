package com.example.jiabo.liveapp.view;

import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiabo.liveapp.R;
import com.example.jiabo.liveapp.base.BaseActivity;
import com.example.jiabo.liveapp.presenter.iview.ICreateLiveView;
import com.example.jiabo.liveapp.presenter.CreateLivePresenter;
import com.example.jiabo.liveapp.view.customView.PickerView;

import java.util.ArrayList;
import java.util.List;

public class CreateLiveActivity extends BaseActivity implements View.OnClickListener, ICreateLiveView {

    private static final String TAG = "CreateLiveActivity";

    private LinearLayout mSetLiveCoverBtn;
    private ImageView mLiveCoverImg;
    private ImageView mLiveCoverIco;
    private TextView mLiveCoverTextView;
    private EditText mLiveTitleEdit;
    private ImageButton mSetLiveResolvingBtn;
    private Button mLiveStartBtn;
    private CreateLivePresenter mCreateLivePresenter;
    private PickerView mSelectInPickerView;
    private List<String> mResolvingList = new ArrayList<>();

    private String mLiveTitle;
    private String mLiveCover;
    private String mResolvingPower;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_live);
        mCreateLivePresenter = new CreateLivePresenter(this);

        initView();
    }

    private void initView() {
        mSetLiveCoverBtn = findViewById(R.id.set_cover_btn);
        mLiveCoverImg = findViewById(R.id.set_live_cover);
        mLiveTitleEdit = findViewById(R.id.set_live_title);
        mSetLiveResolvingBtn = findViewById(R.id.set_live_resolving);
        mLiveStartBtn = findViewById(R.id.live_start_btn);
        mLiveCoverImg = findViewById(R.id.set_cover_ico);
        mLiveCoverTextView = findViewById(R.id.set_cover_text);

        mSetLiveCoverBtn.setOnClickListener(this);
        mSetLiveResolvingBtn.setOnClickListener(this);
        mLiveStartBtn.setOnClickListener(this);
    }

    private void showSetResolvingDialog() {
        AlertDialog.Builder selectDialog = new AlertDialog.Builder(CreateLiveActivity.this);
        final View view = LinearLayout.inflate(this, R.layout.item_dialog_select_resolving, null);
        initDialog(view);

        selectDialog.create();
        selectDialog.setView(view);
        selectDialog.show();

    }

    private void initDialog(View view) {
        mSelectInPickerView = view.findViewById(R.id.select_resolving_dialog);

        initPickViewDataList();
        mSelectInPickerView.setDataList(mResolvingList);
        mSelectInPickerView.setOnSelectListener(new PickerView.OnSelectListener() {
            @Override
            public void onSelect(View view, String selected) {
                mResolvingPower = selected;
                Toast.makeText(CreateLiveActivity.this, "you selected " + selected,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hideSetCoverBtn() {
        mSetLiveCoverBtn.setVisibility(View.GONE);
        mLiveCoverTextView.setVisibility(View.GONE);
        mLiveCoverIco.setVisibility(View.GONE);
    }


    @Override
    public void onPause() {
        super.onPause();

        this.finish();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mCreateLivePresenter.onDestroy();
    }

    private void initPickViewDataList() {
        mResolvingList.add("xxxx0");
        mResolvingList.add("xxxx1");
        mResolvingList.add("xxxx2");
        mResolvingList.add("xxxx3");
        mResolvingList.add("xxxx4");
    }

    /*---------------接口实现的地方----------------------*/
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.set_cover_btn:
                mCreateLivePresenter.loadCoverFromLocal();
                break;
            case R.id.set_live_resolving:
                showSetResolvingDialog();
                break;
            case R.id.live_start_btn:
                mLiveTitle = mLiveTitleEdit.getText().toString();
                if (mLiveCoverImg == null) {
                    Toast.makeText(this, R.string.must_select_cover, Toast.LENGTH_LONG).show();
                    return;
                }
                if (mLiveTitle == null) {
                    Toast.makeText(this, R.string.must_set_title, Toast.LENGTH_LONG).show();
                    return;
                }
                mCreateLivePresenter.startLive(mLiveTitle, mLiveCover, mResolvingPower);
                break;
        }
    }


    @Override
    public void loadLiveCoverPath(String coverPath) {
        if (coverPath != null) {
            mLiveCover = coverPath;
            mLiveCoverImg.setImageURI(Uri.parse(coverPath));
            hideSetCoverBtn();
        }
    }
}
