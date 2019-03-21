package com.example.jiabo.liveapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiabo.liveapp.R;
import com.example.jiabo.liveapp.Utils.LogUtil;
import com.example.jiabo.liveapp.iview.ILoginView;
import com.example.jiabo.liveapp.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

    private static final String TAG = "LoginActivity";
    private static final Integer REQUEST_CODE_FOR_REGISTER_ACTIVITY = 1001;
    private static final Integer RESULT_CODE_FOR_LOGIN_ACTIVITY = 1002;

    private EditText mUserNameEdit;
    private EditText mUserPasswordEdit;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.onDestroy();
    }

    private void initView() {
        mLoginPresenter = new LoginPresenter(this);
        mUserNameEdit = findViewById(R.id.login_username);
        mUserPasswordEdit = findViewById(R.id.login_password);
        Button loginBtn = findViewById(R.id.login_in_btn);
        TextView stepIntoRegister = findViewById(R.id.step_into_register);

        loginBtn.setOnClickListener(this);
        stepIntoRegister.setOnClickListener(this);
    }

    /**
     * 获取界面上的输入的登录信息
     */
    private void loadLoginMessage() {
        String usernameStr = mUserNameEdit.getText().toString();
        String passwordStr = mUserPasswordEdit.getText().toString();
        LogUtil.d(TAG, "loadLoginMessage: username = " + usernameStr + ";------ password = " + passwordStr);
        if (usernameStr.isEmpty() || passwordStr.isEmpty()) {
            LogUtil.e(TAG, "loadLoginMessage: username = null");
            Toast.makeText(this, R.string.username_password_cannot_null, Toast.LENGTH_SHORT).show();
        } else {
            mLoginPresenter.login(usernameStr, passwordStr);
        }
    }

    /**
     * 跳转到注册页面
     */
    private void stepIntoRegisterView() {
        Intent intent = new Intent();
        intent.setClass(this, RegisterActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FOR_REGISTER_ACTIVITY);
    }

    /**
     * 登录成功，跳转到首页
     */
    private void stepIntoHomePage() {
        Intent intent = new Intent();
        intent.setClass(this, HomeActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.d(TAG, "requestCode : " + requestCode + " | resultCode: " + resultCode);
        if (requestCode == REQUEST_CODE_FOR_REGISTER_ACTIVITY && resultCode == RESULT_CODE_FOR_LOGIN_ACTIVITY) {
            String username = data.getStringExtra("resultData");
            mUserNameEdit.setText(username);
        }
    }

    /*---------------------这里是接口的实现---------------------------*/

    /**
     * 点击事件的监听处理
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_in_btn:
                //登录的操作
                //loadLoginMessage();
                stepIntoHomePage();
                break;
            case R.id.step_into_register:
                //没有账号，去注册
                stepIntoRegisterView();
                break;
        }
    }

    @Override
    public void onSuccessInLogin(Object data) {
        LogUtil.d(TAG, "onSuccessInLogin: " + data);
        Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show();
        stepIntoHomePage();
    }

    @Override
    public void onFailureInLogin(String module, int errCode, String errMsg) {
        LogUtil.d(TAG, "onFailureInLogin: module: " + module + " | errCode: " + errCode
                + " | errMsg: " + errMsg);
        Toast.makeText(this, R.string.login_failed + ": " + errMsg, Toast.LENGTH_SHORT)
                .show();
    }

}
