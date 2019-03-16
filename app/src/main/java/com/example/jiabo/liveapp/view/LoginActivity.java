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

    private EditText mUserNameEdit;
    private EditText mUserPasswordEdit;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
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
        if (usernameStr.equals("")) {
            LogUtil.e(TAG, "loadLoginMessage: username = null" );
            Toast.makeText(this, R.string.username_cannot_null, Toast.LENGTH_SHORT).show();
        }else if (passwordStr.equals("")) {
            LogUtil.e(TAG, "loadLoginMessage: password = null" );
            Toast.makeText(this, R.string.password_cannot_null, Toast.LENGTH_SHORT).show();
        }else{
            mLoginPresenter.login(usernameStr, passwordStr);
        }
    }

    private void stepIntoRegisterView() {
        Intent intent = new Intent();
        intent.setClass(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

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
                LogUtil.d(TAG, "onClick: login");
                loadLoginMessage();

                break;
            case R.id.step_into_register:
                //没有账号，去注册
                stepIntoRegisterView();
                break;
        }
    }

    @Override
    public void onSuccessInLogin(String result) {

    }

    @Override
    public void onFailureInLogin() {

    }

}
