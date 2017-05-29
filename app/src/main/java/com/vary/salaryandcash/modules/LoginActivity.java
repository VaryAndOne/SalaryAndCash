package com.vary.salaryandcash.modules;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vary.salaryandcash.R;


/**
 * Created by Administrator on 2017-04-24.
 */

public class LoginActivity extends AppCompatActivity {

    // username 输入框
    private EditText mUsernameEdit;
    // 密码输入框
    private EditText mPasswordEdit;

    // 注册按钮
    private TextView mSignUpBtn;
    // 登录按钮
    private TextView mSignInBtn;

    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

}
