package com.example.jiabo.liveapp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jiabo.liveapp.R;
import com.example.jiabo.liveapp.iview.IRegisterView;
import com.example.jiabo.liveapp.presenter.RegisterPresenter;

/**
 * @author jiabo
 * Date: 2019/3/15 & 10:06
 * Version : 1.0
 * description : 注册的view
 * * Modify by
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, IRegisterView {

    private EditText mPhoneNumberEdit;
    private EditText mUsernameEdit;
    private EditText mPasswordEdit;
    private Button mRegisterBtn;
    private String mUsernameStr;
    private String mPhoneNumberStr;
    private String mPasswordStr;

    private RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        loadRegisterMsg();
    }

    private void initView() {
        mRegisterPresenter = new RegisterPresenter(this);
        mPhoneNumberEdit = findViewById(R.id.register_phone_number);
        mUsernameEdit = findViewById(R.id.register_username);
        mPasswordEdit = findViewById(R.id.register_password);
        mRegisterBtn = findViewById(R.id.register_btn);

        mRegisterBtn.setOnClickListener(this);
    }

    /**
     * 获取界面上输入的注册信息
     */
    private void loadRegisterMsg() {
        mUsernameStr = mUsernameEdit.getText().toString();
        mPhoneNumberStr = mPhoneNumberEdit.getText().toString();
        mPasswordStr = mPasswordEdit.getText().toString();
        if (mUsernameStr == null) {
            Toast.makeText(this, R.string.username_cannot_null, Toast.LENGTH_SHORT);
            return;
        }
        if (mPhoneNumberStr == null) {
            Toast.makeText(this, R.string.username_cannot_null, Toast.LENGTH_SHORT);
            return;
        }
        if (mPasswordStr == null) {
            Toast.makeText(this, R.string.username_cannot_null, Toast.LENGTH_SHORT);
            return;
        }

    }

    /*-------------------------------这里是接口的实现--------------------------*/
    @Override
    public void onClick(View view) {
        mRegisterPresenter.register(mPhoneNumberStr, mUsernameStr, mPasswordStr);
    }


    @Override
    public void onSuccessInRegister() {

    }

    @Override
    public void onFailureInRegister() {

    }
}
