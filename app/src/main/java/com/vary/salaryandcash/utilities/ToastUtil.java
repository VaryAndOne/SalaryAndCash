package com.vary.salaryandcash.utilities;

import android.widget.Toast;

import com.vary.salaryandcash.app.SalaryApplication;


/**
 * Created by Administrator on 2017-05-03.
 */

    public class ToastUtil {
        public static void show(String msg){
            Toast.makeText(SalaryApplication.appContext, msg, Toast.LENGTH_SHORT).show();
        }
    }
