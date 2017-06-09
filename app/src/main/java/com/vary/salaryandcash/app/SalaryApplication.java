package com.vary.salaryandcash.app;

import android.app.Application;
import android.content.Context;

import com.vary.salaryandcash.di.components.ApplicationComponent;
import com.vary.salaryandcash.di.components.DaggerApplicationComponent;
import com.vary.salaryandcash.di.module.ApplicationModule;

import me.yokeyword.fragmentation.Fragmentation;

/**
 * Created by Administrator on 2017-05-29.
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
                .stackViewMode(Fragmentation.NONE)
                .install();
    }

    public static SalaryApplication getInstance() {
        return instance;
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this, "http://60.206.109.43:8080"))
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
