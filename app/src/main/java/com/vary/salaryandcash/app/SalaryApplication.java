package com.vary.salaryandcash.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017-05-29.
 */

public class SalaryApplication extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
