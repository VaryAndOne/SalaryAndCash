package com.vary.salaryandcash.api;


import com.vary.salaryandcash.mvp.model.SalariesResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by
 * 温家才
 * 性别 男 民族 汉
 * 生日1993年9月7日
 * 住址 吉林省农安县青山口乡柳条沟村乡约屯17组
 * 公民身份证号 220122199309077010
 * on 2017-06-03
 *
 */

public interface  SalaryApiService {

    @GET("salary.json")
    Observable<SalariesResponse> getSalaries();

    @GET("task.json")
    Observable<SalariesResponse> getTask();

    @GET("group.json")
    Observable<SalariesResponse> getGroup();

    @GET("salary.json")
    Call<SalariesResponse> getTheSalaries();
}
