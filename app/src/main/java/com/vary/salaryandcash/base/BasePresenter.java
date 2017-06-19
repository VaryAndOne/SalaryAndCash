package com.vary.salaryandcash.base;
import android.util.Log;

import com.vary.salaryandcash.mvp.model.SalariesResponse;
import com.vary.salaryandcash.mvp.view.BaseView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
public class BasePresenter<V extends BaseView> {
    @Inject protected V mView;
    protected V getView() {
        return mView;
    }
}
