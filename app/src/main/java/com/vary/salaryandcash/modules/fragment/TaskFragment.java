package com.vary.salaryandcash.modules.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.di.components.DaggerSalaryComponent;
import com.vary.salaryandcash.di.module.SalaryModule;
import com.vary.salaryandcash.modules.adapter.PersonAdapter;
import com.vary.salaryandcash.modules.adapter.SalaryAdapter;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.presenter.SalaryPresenter;
import com.vary.salaryandcash.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017-06-09.
 */

public class TaskFragment extends SupportFragment implements MainView {
    private View view;
    @Inject
    protected SalaryPresenter mPresenter;
    @Bind(R.id.recyclerview) protected RecyclerView mCakeList;
    private SalaryAdapter mCakeAdapter;
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        // todo,当该Fragment对用户可见时
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_task, container, false);
        DaggerSalaryComponent.builder()
                .applicationComponent(((SalaryApplication) (getActivity().getApplication())).getApplicationComponent())
                .salaryModule(new SalaryModule(this))
                .build().inject(this);
        mPresenter.getSalaries();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCakeList = (RecyclerView) view.findViewById(R.id.recyclerview);
        mCakeList.setHasFixedSize(true);
        mCakeList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mCakeAdapter = new SalaryAdapter(getLayoutInflater(savedInstanceState));
//        mCakeAdapter.setCakeClickListener(mCakeClickListener);
        mCakeList.setAdapter(mCakeAdapter);
    }

    @Override
    public void onSalaryLoaded(List<Salary> salaries) {
        mCakeAdapter.addCakes(salaries);
    }

    @Override
    public void onShowDialog(String s) {
//        showDialog(s);
    }

    @Override
    public void onHideDialog() {
//        hideDialog();
    }

    @Override
    public void onShowToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClearItems() {
        mCakeAdapter.clearCakes();
    }
}
