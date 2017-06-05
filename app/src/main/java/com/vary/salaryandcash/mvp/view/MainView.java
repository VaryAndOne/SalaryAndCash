package com.vary.salaryandcash.mvp.view;

import com.vary.salaryandcash.mvp.model.Salary;

import java.util.List;

/**
 * Created by Administrator on 2017-06-03.
 */

public interface MainView extends BaseView{
    void onSalaryLoaded(List<Salary> salaries);

    void onShowDialog(String s);

    void onHideDialog();

    void onShowToast(String s);

    void onClearItems();
}
