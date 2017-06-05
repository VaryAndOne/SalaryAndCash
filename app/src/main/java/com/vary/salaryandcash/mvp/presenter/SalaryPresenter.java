package com.vary.salaryandcash.mvp.presenter;

import com.vary.salaryandcash.api.SalaryApiService;
import com.vary.salaryandcash.base.BasePresenter;
import com.vary.salaryandcash.mapper.SalaryMapper;
import com.vary.salaryandcash.mvp.model.SalariesResponse;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

/**
 * Created by Administrator on 2017-06-03.
 */

public class SalaryPresenter extends BasePresenter<MainView> implements Observer<SalariesResponse>{

    @Inject protected SalaryApiService mApiService;
    @Inject protected SalaryMapper mSalaryMapper;

    @Inject
    public SalaryPresenter() {
    }

    public void getSalaries() {
        getView().onShowDialog("Loading cakes....");
        Observable<SalariesResponse> salariesResponseObservable = mApiService.getSalaries();
        subscribe(salariesResponseObservable,this);
    }

    @Override
    public void onCompleted() {
        getView().onHideDialog();
        getView().onShowToast("Cakes loading complete!");
    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().onShowToast("Error loading cakes " + e.getMessage());
    }

    @Override
    public void onNext(SalariesResponse salariesResponse) {
        List<Salary>  salaries = mSalaryMapper.mapCakes(salariesResponse);
        getView().onClearItems();
        getView().onSalaryLoaded(salaries);
    }
}
