package com.vary.salaryandcash.modules.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseSupportFragment;


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

public class HelpFragment extends BaseSupportFragment {

    @Override
    protected void initView() {
        app_title = (TextView) mView.findViewById(R.id.app_title);
        app_title.setText("帮助");
    }

    @Override
    public int getBaseView() {
        return R.layout.fragment_web;
    }
}
