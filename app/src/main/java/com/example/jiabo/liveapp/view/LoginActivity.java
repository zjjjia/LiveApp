package com.example.jiabo.liveapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiabo.liveapp.R;
import com.example.jiabo.liveapp.Utils.LogUtil;
import com.example.jiabo.liveapp.base.BaseActivity;
import com.example.jiabo.liveapp.constant.OtherCode;
import com.example.jiabo.liveapp.presenter.iview.ILoginView;
import com.example.jiabo.liveapp.presenter.LoginPresenter;

import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginView {

    private static final String TAG = "LoginActivity";

    private EditText mUserNameEdit;
    private EditText mUserPasswordEdit;
    private LoginPresenter mLoginPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLoginPresenter.onDestroy();
    }

    private void initView() {
        mLoginPresenter = new LoginPresenter(this, this);
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
    private void login() {
        String usernameStr = mUserNameEdit.getText().toString();
        String passwordStr = mUserPasswordEdit.getText().toString();

        if(TextUtils.isEmpty(usernameStr) || TextUtils.isEmpty(passwordStr)){
            Toast.makeText(this, R.string.str_cannot_null, Toast.LENGTH_SHORT).show();
            return;
        }

        if(usernameStr.length() < 4 || usernameStr.length() > 24 || Pattern.compile("^[0-9]*$").matcher(usernameStr).matches()
                || !Pattern.compile("^[a-zA-Z0-9_]*$").matcher(usernameStr).matches()){
            Toast.makeText(this, R.string.str_hint_account, Toast.LENGTH_SHORT).show();
            return;
        }

        if(passwordStr.length() < 8 || passwordStr.length() > 16){
            Toast.makeText(this, R.string.str_hint_password, Toast.LENGTH_SHORT).show();
            return;
        }

        mLoginPresenter.login(usernameStr, passwordStr);
    }

    /**
     * 跳转到注册页面
     */
    private void stepIntoRegisterView() {
        Intent intent = new Intent();
        intent.setClass(this, RegisterActivity.class);
        startActivityForResult(intent, OtherCode.REQUEST_CODE);
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
        if (requestCode == OtherCode.REQUEST_CODE
                && resultCode == OtherCode.RESULT_CODE) {
            String username = data.getStringExtra("resultData");
            LogUtil.d(TAG, "onActivityResult: " + username);
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
                login();
                break;
            case R.id.step_into_register:
                //没有账号，去注册
                stepIntoRegisterView();
                break;
        }
    }

    @Override
    public void onSuccessInLogin() {
        Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show();
        stepIntoHomePage();
    }

    @Override
    public void onFailureInLogin(int errCode, String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT)
                .show();
    }

}
