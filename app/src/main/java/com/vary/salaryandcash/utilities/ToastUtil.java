package com.vary.salaryandcash.utilities;
import android.widget.Toast;
import com.vary.salaryandcash.app.SalaryApplication;

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

    public class ToastUtil {
        public static void show(String msg){
            Toast.makeText(SalaryApplication.appContext, msg, Toast.LENGTH_SHORT).show();
        }
    }
