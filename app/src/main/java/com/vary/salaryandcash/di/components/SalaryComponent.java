package com.vary.salaryandcash.di.components;

import com.vary.salaryandcash.di.module.SalaryModule;
import com.vary.salaryandcash.di.scope.PerActivity;
import com.vary.salaryandcash.modules.CatchActivity;
import com.vary.salaryandcash.modules.TaskActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017-06-05.
 */
@PerActivity
@Component(modules = SalaryModule.class, dependencies = ApplicationComponent.class)
public interface SalaryComponent {

    void inject(TaskActivity activity);
    void inject(CatchActivity activity);
}
