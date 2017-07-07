package com.vary.salaryandcash.modules.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.modules.holder.MainHolder;
import com.vary.salaryandcash.modules.holder.TaskHolder;
import com.vary.salaryandcash.modules.itf.MyOnTouchListener;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.base.BaseSupportFragment;
import com.vary.salaryandcash.di.components.DaggerSalaryComponent;
import com.vary.salaryandcash.di.module.SalaryModule;
import com.vary.salaryandcash.modules.MainActivity;
import com.vary.salaryandcash.modules.adapter.SalaryAdapter;
import com.vary.salaryandcash.mvp.model.AccountResponse;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.presenter.SalaryPresenter;
import com.vary.salaryandcash.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

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

public class TaskFragment extends BaseSupportFragment implements MainView {
    public SalaryAdapter mCakeAdapter;
    @Inject
    protected SalaryPresenter mPresenter;
    @Bind(R.id.recyclerview) protected RecyclerView mCakeList;
    public static TaskFragment myFragment;
    public static synchronized TaskFragment getInstance(String getPassword){
        if (myFragment == null){
            synchronized (LeftFragment.class){
                if (myFragment == null){
                    myFragment = new TaskFragment();
                }
            }
        }
        Bundle args = new Bundle();
        args.putString("getPassword",getPassword);
        myFragment.setArguments(args);
        return myFragment;
    }
    @Override
    protected void initView() {
        DaggerSalaryComponent.builder()
                .applicationComponent(((SalaryApplication) (getActivity().getApplication())).getApplicationComponent())
                .salaryModule(new SalaryModule(this))
                .build().inject(this);
        mCakeList.setHasFixedSize(true);
        mCakeList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public int getBaseView() {
        return R.layout.fragment_task;
    }

    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        String getPassword = (String) myFragment.getArguments().get("getPassword");
//        mPresenter.getGroup(".json");
        mPresenter.getGroup(getPassword);
        mCakeAdapter = new SalaryAdapter(getLayoutInflater(savedInstanceState)) {
            @Override
            public int getView() {
                return R.layout.item_task;
            }

            @Override
            public TaskHolder getHolder() {
                TaskHolder mainHolder = new TaskHolder(mCakeAdapter.mView);
                return mainHolder;
            }
        };
        if (mView != null) {
//        mCakeAdapter.setCakeClickListener(mCakeClickListener);
            mCakeList.setAdapter(mCakeAdapter);
        }
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

    @Override
    public void onAccountLoaded(AccountResponse response) {

    }
}
