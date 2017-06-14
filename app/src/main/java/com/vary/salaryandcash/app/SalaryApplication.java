package com.vary.salaryandcash.app;

import android.app.Application;
import android.content.Context;

import com.vary.salaryandcash.di.components.ApplicationComponent;
import com.vary.salaryandcash.di.components.DaggerApplicationComponent;
import com.vary.salaryandcash.di.module.ApplicationModule;

import me.yokeyword.fragmentation.Fragmentation;

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
public class SalaryApplication extends Application {

    public static Context appContext;
    private static SalaryApplication instance;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        instance = this;
        initializeApplicationComponent();

        Fragmentation.builder()
                // 显示悬浮球 ; 其他Mode:SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(Fragmentation.BUBBLE)
                .install();
    }

    public static SalaryApplication getInstance() {
        return instance;
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this, "http://vary.oss-cn-beijing.aliyuncs.com/json/"))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
