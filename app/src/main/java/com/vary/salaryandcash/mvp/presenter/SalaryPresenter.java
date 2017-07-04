package com.vary.salaryandcash.mvp.presenter;

import android.util.Log;

import com.vary.salaryandcash.api.SalaryApiService;
import com.vary.salaryandcash.base.BasePresenter;
import com.vary.salaryandcash.mapper.SalaryMapper;
import com.vary.salaryandcash.mvp.model.SalariesResponse;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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

public class SalaryPresenter extends BasePresenter<MainView> {

    @Inject
    protected SalaryApiService mApiService;
    @Inject
    protected SalaryMapper mSalaryMapper;

    @Inject
    public SalaryPresenter() {
    }

    public void getSalaries() {
        getView().onShowDialog("Loading cakes....");
        mApiService.getSalaries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SalariesResponse>() {
                    @Override
                    public void accept(SalariesResponse response) throws Exception {
                        Log.d("TAG", response.getReleaseDate());
                        List<Salary> salaries = mSalaryMapper.mapCakes(response);
                        getView().onClearItems();
                        getView().onSalaryLoaded(salaries);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.w("Error", throwable);
                    }
                });
    }

    public void getTask() {
        getView().onShowDialog("Loading Task....");
        mApiService.getTask()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SalariesResponse>() {
                    @Override
                    public void accept(SalariesResponse response) throws Exception {
                        Log.d("TAG", response.getReleaseDate());
                        List<Salary> salaries = mSalaryMapper.mapCakes(response);
                        getView().onClearItems();
                        getView().onSalaryLoaded(salaries);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.w("Error", throwable);
                    }
                });
    }
}
