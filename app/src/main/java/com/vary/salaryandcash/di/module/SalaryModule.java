package com.vary.salaryandcash.di.module;

import com.vary.salaryandcash.api.SalaryApiService;
import com.vary.salaryandcash.di.scope.PerActivity;
import com.vary.salaryandcash.mvp.view.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017-06-05.
 */
@Module
public class SalaryModule {

    private MainView mView;

    public SalaryModule(MainView view) {
        mView = view;
    }

    @PerActivity
    @Provides
    SalaryApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(SalaryApiService.class);
    }

    @PerActivity
    @Provides
    MainView provideView() {
        return mView;
    }
}
