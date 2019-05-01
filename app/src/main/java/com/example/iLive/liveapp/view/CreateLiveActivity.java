package com.example.iLive.liveapp.view;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iLive.liveapp.R;
import com.example.iLive.liveapp.Utils.LogUtil;
import com.example.iLive.liveapp.Utils.UIUtils;
import com.example.iLive.liveapp.base.BaseActivity;
import com.example.iLive.liveapp.constant.Constants;
import com.example.iLive.liveapp.presenter.CreateLivePresenter;
import com.example.iLive.liveapp.presenter.iview.ICreateLiveView;
import com.example.iLive.liveapp.view.customView.PickerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateLiveActivity extends BaseActivity implements View.OnClickListener, ICreateLiveView {

    private static final String TAG = "CreateLiveActivity";
    private static final int CHOOSE_COVER_BY_CAMERA = 100;
    private static final int CHOOSE_COVER_BY_ALBUM = 200;
    private static final int CROP_CHOOSE = 300;

    private LinearLayout mSetLiveCoverBtn;
    private ImageView mLiveCoverImg;
    private ImageView mLiveCoverIco;
    private TextView mLiveCoverTextView;
    private TextView mSelectedRole;
    private EditText mLiveTitleEdit;
    private CreateLivePresenter mCreateLivePresenter;
    private List<String> mRoleList = new ArrayList<>();

    private int mRoleIndex;
    private Dialog mPicChooseDialog;
    private Uri mFileUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_live);

        initPickViewDataList();
        initView();
        mCreateLivePresenter = new CreateLivePresenter(this, this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCreateLivePresenter != null) {
            mCreateLivePresenter.onDestroy();
        }
    }

    private void initView() {
        mSetLiveCoverBtn = findViewById(R.id.set_cover_btn);
        mLiveCoverImg = findViewById(R.id.set_live_cover);
        mLiveTitleEdit = findViewById(R.id.set_live_title);
        mLiveCoverTextView = findViewById(R.id.set_cover_text);
        mLiveCoverIco = findViewById(R.id.set_cover_ico);
        mSelectedRole = findViewById(R.id.selected_role);
        Button mLiveStartBtn = findViewById(R.id.live_start_btn);
        ImageButton mSetRoleShowBtn = findViewById(R.id.set_live_role);

        mSelectedRole.setText(mRoleList.get(mRoleList.size() / 2));
        mSetLiveCoverBtn.setOnClickListener(this);
        mSetRoleShowBtn.setOnClickListener(this);
        mLiveStartBtn.setOnClickListener(this);
    }

    /**
     * 选择分辨率的的弹窗
     */
    private void showSetRoleShowDialog() {
        AlertDialog.Builder selectDialog = new AlertDialog.Builder(CreateLiveActivity.this);
        final View view = LinearLayout.inflate(this, R.layout.item_dialog_select_resolving, null);
        initRoleShowDialog(view);

        selectDialog.create();
        selectDialog.setView(view);
        selectDialog.show();

    }

    private void initRoleShowDialog(View view) {
        PickerView mSelectInPickerView = view.findViewById(R.id.choose_resolving_dialog);

        mSelectInPickerView.setDataList(mRoleList);
        mSelectInPickerView.setOnSelectListener(new PickerView.OnSelectListener() {
            @Override
            public void onSelect(View view, int position) {
                LogUtil.d(TAG, "onSelect: selected role: " + position);
                mRoleIndex = position;
                mSelectedRole.setText(mRoleList.get(position));
            }
        });
    }

    private void hideCoverSetBtn() {
        mSetLiveCoverBtn.setVisibility(View.GONE);
        mLiveCoverTextView.setVisibility(View.GONE);
        mLiveCoverIco.setVisibility(View.GONE);
    }

    /**
     * 图片选择对话框
     */
    private void initChooseCoverDialog() {
        mPicChooseDialog = new Dialog(this, R.style.float_dialog);
        mPicChooseDialog.setContentView(R.layout.pic_choose_dialog);

        Window dialogWindow = mPicChooseDialog.getWindow();
        if (dialogWindow == null) {
            throw new AssertionError();
        }
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPicChooseDialog.show();

        Button choosePicByCamera = mPicChooseDialog.findViewById(R.id.choose_pic_camera);
        Button choosePicByAlbum = mPicChooseDialog.findViewById(R.id.choose_pic_album);
        Button cancelBtn = mPicChooseDialog.findViewById(R.id.cancel_btn);

        choosePicByCamera.setOnClickListener(this);
        choosePicByAlbum.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    private void initPickViewDataList() {
        mRoleList.add("1280*720");
        mRoleList.add("960x540");
        mRoleList.add("640x480");
        mRoleList.add("640x368");
        mRoleList.add("480x360");
        mRoleList.add("320x240");
    }

    /**
     * 选择封面图片之后的处理
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CHOOSE_COVER_BY_CAMERA:
                    LogUtil.d(TAG, "onActivityResult(CHOOSE_COVER_BY_CAMERA): " + mFileUri);
                    mCreateLivePresenter.startPhotoZoom(mFileUri);
                    break;
                case CHOOSE_COVER_BY_ALBUM:
                    String path = UIUtils.getPath(this, data.getData());
                    if (path != null) {
                        LogUtil.d(TAG, "onActivityResult: startPhotoZoom->path: " + path);
                        File file = new File(path);
                        mCreateLivePresenter.startPhotoZoom(UIUtils.getUriFromFile(this, file));
                    }
                    break;
                case CROP_CHOOSE:
                    hideCoverSetBtn();
                    LogUtil.d(TAG, "onActivityResult: intentData: " + data.getData());
                    mFileUri = data.getData();
                    mLiveCoverImg.setImageURI(mFileUri);
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.set_cover_btn:     //设置封面的按钮
                initChooseCoverDialog();
                break;
            case R.id.choose_pic_camera:     // 拍照作为封面
                mCreateLivePresenter.loadCover(CHOOSE_COVER_BY_CAMERA);
                mPicChooseDialog.dismiss();
                break;
            case R.id.choose_pic_album:       //从相册选择图片
                mCreateLivePresenter.loadCover(CHOOSE_COVER_BY_ALBUM);
                mPicChooseDialog.dismiss();
                break;
            case R.id.set_live_role:    //设置分辨率的按钮
                showSetRoleShowDialog();
                break;
            case R.id.cancel_btn:             //设置封面的弹窗的取消按钮
                mPicChooseDialog.dismiss();
                break;
            case R.id.live_start_btn:           //开始直播(创建房间）
                String mLiveTitle = mLiveTitleEdit.getText().toString();
                if (mFileUri == null) {
                    Toast.makeText(this, R.string.must_select_cover, Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(mLiveTitle)) {
                    Toast.makeText(this, R.string.must_set_title, Toast.LENGTH_LONG).show();
                    return;
                }
                mCreateLivePresenter.createRoom(mLiveTitle, mFileUri, mRoleIndex);
                break;
        }
    }

    /*-----------------------数据接口实现的地方----------------------*/
    @Override
    public void loadCover(Uri fileUri, int type) {
        switch (type) {
            case CHOOSE_COVER_BY_CAMERA:
                Intent intentPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intentPhoto.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intentPhoto, CHOOSE_COVER_BY_CAMERA);
                break;
            case CHOOSE_COVER_BY_ALBUM:
                LogUtil.d(TAG, "loadCover: open album");
                Intent intentAlbum = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intentAlbum.setType("image/*");
                startActivityForResult(intentAlbum, CHOOSE_COVER_BY_ALBUM);
        }
    }

    @Override
    public void startPhotoZoom(Intent intent) {
        startActivityForResult(intent, CROP_CHOOSE);
    }

    @Override
    public void startLive() {
        Intent intent = new Intent(this, LiveActivity.class);
        intent.putExtra("role", Constants.ROLE_ANCHOR);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(int errorCode, String errorInfo) {
        LogUtil.e(TAG, "onHttpError: errorCode: " + errorCode + "; errorInfo: " + errorInfo);
        Toast.makeText(this, errorCode + ": " + errorInfo, Toast.LENGTH_SHORT).show();
    }
}
