package com.vary.salaryandcash.di.components;

import android.content.Context;

import com.vary.salaryandcash.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017-06-03.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit exposeRetrofit();

    Context exposeContext();
}
