package com.vary.salaryandcash.modules.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.di.components.DaggerSalaryComponent;
import com.vary.salaryandcash.di.module.SalaryModule;
import com.vary.salaryandcash.modules.adapter.FoodAdapter;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.presenter.SalaryPresenter;
import com.vary.salaryandcash.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017-04-20.
 */

public class LeftFragment extends Fragment implements MainView {

    private FoodAdapter foodAdapter;
    @Inject
    protected SalaryPresenter mPresenter;

    public static MyFragment getInstance(int position){
        MyFragment myFragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt("position",position);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.app_recycler_view, container, false);

        DaggerSalaryComponent.builder()
                .applicationComponent(((SalaryApplication) (getActivity().getApplication())).getApplicationComponent())
                .salaryModule(new SalaryModule(this))
                .build().inject(this);
        mPresenter.getSalaries();
        //   textView = (TextView) layout.findViewById(R.id.position);
        setupRecyclerView((RecyclerView) layout.findViewById(R.id.recyclerview),layout);
        Bundle bundle = getArguments();
        if (bundle != null) {
            //         textView.setText("The page Selected is "+bundle.getInt("position"));
        }
        return layout;
    }

    private void setupRecyclerView(RecyclerView rv, View layout) {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        foodAdapter = new FoodAdapter();
        rv.setAdapter(foodAdapter);
//        refreshCard();

    }
    @Override
    public void onSalaryLoaded(List<Salary> salaries) {
        foodAdapter.addItems(salaries);
    }

    @Override
    public void onShowDialog(String s) {

    }

    @Override
    public void onHideDialog() {

    }

    @Override
    public void onShowToast(String s) {

    }

    @Override
    public void onClearItems() {
        foodAdapter.clearDatas();
    }
}
