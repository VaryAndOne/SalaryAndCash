package com.vary.salaryandcash.di.module;

import com.vary.salaryandcash.api.SalaryApiService;
import com.vary.salaryandcash.di.scope.PerActivity;
import com.vary.salaryandcash.mvp.view.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
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
