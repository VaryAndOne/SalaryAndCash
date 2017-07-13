package com.vary.salaryandcash.api;


import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.vary.salaryandcash.mvp.model.AccountResponse;
import com.vary.salaryandcash.mvp.model.SalariesResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

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

    @GET("group/{id}")
    Observable<SalariesResponse> getTask(@Path("id") String id);

    @GET("group{id}")
    Observable<SalariesResponse> getGroup(@Path("id") String id);

    @GET("group/{id}")
    Observable<SalariesResponse> getCatch(@Path("id") String id);

    @GET("person.json")
    Observable<AccountResponse> getPerson();

    @GET("salary.json")
    Call<SalariesResponse> getTheSalaries();
}
