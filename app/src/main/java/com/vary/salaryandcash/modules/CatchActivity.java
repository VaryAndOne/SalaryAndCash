package com.vary.salaryandcash.modules;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseActivity;
import com.vary.salaryandcash.di.components.DaggerSalaryComponent;
import com.vary.salaryandcash.di.module.SalaryModule;
import com.vary.salaryandcash.modules.adapter.CatchAdapter;
import com.vary.salaryandcash.modules.adapter.SalaryAdapter;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.presenter.SalaryPresenter;
import com.vary.salaryandcash.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;



/**
 * Created by Administrator on 2017-06-01.
 */

public class CatchActivity extends BaseActivity implements MainView {

    @Inject
    protected SalaryPresenter mPresenter;
    @Bind(R.id.cake_list) protected RecyclerView mCakeList;
    private CatchAdapter mCakeAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_catch;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initializeList();
        mPresenter.getSalaries();
    }

    private void initializeList() {
        mCakeList.setHasFixedSize(true);
        mCakeList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mCakeAdapter = new CatchAdapter(getLayoutInflater());
        mCakeAdapter.setCakeClickListener(mCakeClickListener);
        mCakeList.setAdapter(mCakeAdapter);
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerSalaryComponent.builder()
                .applicationComponent(getApplicationComponent())
                .salaryModule(new SalaryModule(this))
                .build().inject(this);
    }

    @Override
    public void onSalaryLoaded(List<Salary> salaries) {
        mCakeAdapter.addCakes(salaries);
    }

    @Override
    public void onShowDialog(String s) {
        showDialog(s);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String s) {
        Toast.makeText(CatchActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClearItems() {
        mCakeAdapter.clearCakes();
    }

    private CatchAdapter.OnCakeClickListener mCakeClickListener = new CatchAdapter.OnCakeClickListener() {
        @Override
        public void onClick(View v, Salary cake, int position) {
            Intent intent = new Intent(CatchActivity.this, VideoPlayerActivity.class);
            intent.putExtra(VideoPlayerActivity.CAKE, cake);
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(TaskActivity.this, v, "cakeImageAnimation");
//                startActivity(intent, options.toBundle());
//            } else {
//                startActivity(intent);
//            }
            startActivity(intent);
        }
    };
}