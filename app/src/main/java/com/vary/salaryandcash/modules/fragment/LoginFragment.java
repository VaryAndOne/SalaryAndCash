package com.vary.salaryandcash.modules.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseSupportFragmentVertical;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by
 *
 * 温家才
 * 性别 男 民族 汉
 * 生日1993年9月7日
 * 住址 吉林省农安县青山口乡柳条沟村乡约屯17组
 * 公民身份证号 220122199309077010
 *
 * on 2017-06-03.
 */

public class LoginFragment extends BaseSupportFragmentVertical {
    EditText userName,password;
    @Override
    public int getBaseView() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {
        mView.findViewById(R.id.navigate_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        userName = (EditText) mView.findViewById(R.id.ec_edit_username);
        password = (EditText) mView.findViewById(R.id.ec_edit_password);

    }
}
