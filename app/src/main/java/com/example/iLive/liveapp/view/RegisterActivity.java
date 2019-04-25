package com.example.iLive.liveapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iLive.liveapp.R;
import com.example.iLive.liveapp.Utils.LogUtil;
import com.example.iLive.liveapp.base.BaseActivity;
import com.example.iLive.liveapp.constant.OtherCode;
import com.example.iLive.liveapp.presenter.iview.IRegisterView;
import com.example.iLive.liveapp.presenter.RegisterPresenter;

import java.util.regex.Pattern;

/**
 * @author jiabo
 *         Date: 2019/3/15 & 10:06
 *         Version : 1.0
 *         description : 注册的view
 *         * Modify by
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener, IRegisterView {

    private static final String TAG = "RegisterActivity";

    private EditText mUsernameEdit;
    private EditText mPasswordEdit;
    private RegisterPresenter mRegisterPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRegisterPresenter.onDestroy();
    }

    private void initView() {
        mRegisterPresenter = new RegisterPresenter(this,this);
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
        if (usernameStr.length() < 4 || usernameStr.length() > 24
                || Pattern.compile("^[0-9]*$").matcher(usernameStr).matches()
                || !Pattern.compile("^[a-zA-Z0-9_]*$").matcher(usernameStr).matches()) {
            LogUtil.i(TAG, "loadRegisterMsg: " + usernameStr.length());
            Toast.makeText(this, R.string.str_hint_account, Toast.LENGTH_SHORT).show();
            return;
        }

        if (usernameStr.length() == 0 || passwordStr.length() == 0) {
            Toast.makeText(this, R.string.str_cannot_null, Toast.LENGTH_SHORT).show();
            return;
        }

        if (passwordStr.length() < 8 || passwordStr.length() > 16) {
            Toast.makeText(this, R.string.str_hint_password, Toast.LENGTH_SHORT).show();
            return;
        }
        mRegisterPresenter.register(usernameStr, passwordStr);

    }

    /**
     * 注册完成后返回登录页面
     */
    private void resultForLoginActivity(String resultData) {
        Intent intent = new Intent();
        intent.putExtra("resultData", resultData);
        setResult(OtherCode.RESULT_CODE, intent);
        this.finish();
    }

    /*-------------------------------这里是接口的实现--------------------------*/
    @Override
    public void onClick(View view) {
        loadRegisterMsg();
    }


    @Override
    public void onSuccessInRegister(String username) {
        Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT).show();
        resultForLoginActivity(username);
    }

    @Override
    public void onFailureInRegister(int errCode, String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    }
}
