package com.example.jiabo.liveapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jiabo.liveapp.R;
import com.example.jiabo.liveapp.Utils.LogUtil;
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

    private static final String TAG = "RegisterActivity";

    private EditText mUsernameEdit;
    private EditText mPasswordEdit;
    private RegisterPresenter mRegisterPresenter;
    private static final Integer RESULT_CODE_FOR_LOGIN_ACTIVITY = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        mRegisterPresenter.onDestroy();
    }

    private void initView() {
        mRegisterPresenter = new RegisterPresenter(this);
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
        String passwordStr = mPasswordEdit.getText().toString();
        if (TextUtils.isEmpty(usernameStr) || TextUtils.isEmpty(passwordStr)) {
            LogUtil.d(TAG, "loadRegisterMsg: username or password = null");
            Toast.makeText(this, R.string.username_password_cannot_null, Toast.LENGTH_SHORT).show();
        } else if (usernameStr.getBytes().length < 4 || usernameStr.getBytes().length > 24) {
            LogUtil.d(TAG, "loadRegisterMsg: username bytes length are invalid");
            Toast.makeText(this, R.string.username_length_invalid, Toast.LENGTH_SHORT).show();
        } else if (passwordStr.getBytes().length < 8 || passwordStr.getBytes().length > 16) {
            LogUtil.d(TAG, "loadRegisterMsg: password bytes length are invalid");
            Toast.makeText(this, R.string.password_length_invalid, Toast.LENGTH_SHORT).show();
        } else {
            mRegisterPresenter.register(usernameStr, passwordStr);
        }

    }

    /**
     * 注册完成后返回登录页面
     */
    private void resultForLoginActivity(String resultData) {
        Intent intent = new Intent();
        intent.putExtra("resultData", resultData);
        setResult(RESULT_CODE_FOR_LOGIN_ACTIVITY, intent);
        this.finish();
    }

    /*-------------------------------这里是接口的实现--------------------------*/
    @Override
    public void onClick(View view) {
        loadRegisterMsg();
    }


    @Override
    public void onSuccessInRegister(String username, Object data) {
        LogUtil.d(TAG, "register successful!!!   return data: " + data);
        Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT).show();
        resultForLoginActivity(username);
    }

    @Override
    public void onFailureInRegister(String module, int errCode, String errMsg) {
        LogUtil.e(TAG, "register failed : " + module + " | " + +errCode + " | " + errMsg);
        Toast.makeText(this, R.string.register_failed + ": " + errMsg,
                Toast.LENGTH_SHORT).show();
    }
}
