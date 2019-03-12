package com.example.jiabo.liveapp;

/**
 * Created by jiabo
 * Data : 2019/3/12
 * Description : 登录页面的activity
 * Modify by
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText userNameEdit;
    private EditText userPasswordEdit;
    private Button loginInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        userNameEdit = findViewById(R.id.login_username_input);
        userPasswordEdit = findViewById(R.id.login_password_input);
        loginInBtn = findViewById(R.id.login_in_btn);

        loginInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameStr = userNameEdit.getText().toString();
                String userPasswordStr = userPasswordEdit.getText().toString();

                //登录的操作
            }
        });
    }
}
