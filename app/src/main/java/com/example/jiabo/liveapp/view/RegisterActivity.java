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

    private RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        mRegisterPresenter = new RegisterPresenter(this);
        mPhoneNumberEdit = findViewById(R.id.register_phone_number);
        mUsernameEdit = findViewById(R.id.register_username);
        mPasswordEdit = findViewById(R.id.register_password);
        Button registerBtn = findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(this);
    }

    /**
     * 获取界面上输入的注册信息
     */
    private void loadRegisterMsg() {
        String usernameStr = mUsernameEdit.getText().toString();
        String phoneNumberStr = mPhoneNumberEdit.getText().toString();
        String passwordStr = mPasswordEdit.getText().toString();
        if (usernameStr.equals("")) {
            Toast.makeText(this, R.string.username_cannot_null, Toast.LENGTH_SHORT).show();
        } else if (phoneNumberStr.equals("")) {
            Toast.makeText(this, R.string.username_cannot_null, Toast.LENGTH_SHORT).show();
        } else if (passwordStr.equals("")) {
            Toast.makeText(this, R.string.username_cannot_null, Toast.LENGTH_SHORT).show();
        }else {
            mRegisterPresenter.register(phoneNumberStr, usernameStr, passwordStr);
        }

    }

    /*-------------------------------这里是接口的实现--------------------------*/
    @Override
    public void onClick(View view) {
        loadRegisterMsg();
    }


    @Override
    public void onSuccessInRegister() {

    }

    @Override
    public void onFailureInRegister() {

    }
}
