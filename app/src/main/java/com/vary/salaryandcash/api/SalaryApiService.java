package com.vary.salaryandcash.api;


import com.vary.salaryandcash.mvp.model.SalariesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2017-06-03.
 */

public interface  SalaryApiService {

    @GET("salary.json")
    Observable<SalariesResponse> getSalaries();

    @GET("salary.json")
    Call<SalariesResponse> getTheSalaries();
}
