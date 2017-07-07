package com.vary.salaryandcash.modules.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.base.BaseSupportFragment;
import com.vary.salaryandcash.di.components.DaggerSalaryComponent;
import com.vary.salaryandcash.di.module.SalaryModule;
import com.vary.salaryandcash.modules.adapter.SalaryAdapter;
import com.vary.salaryandcash.modules.holder.GroupHolder;
import com.vary.salaryandcash.modules.holder.MainHolder;
import com.vary.salaryandcash.modules.itf.EndlessRecyclerOnScrollListener;
import com.vary.salaryandcash.mvp.model.AccountResponse;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.presenter.SalaryPresenter;
import com.vary.salaryandcash.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Created by
 *
 * 温家才
 * 性别 男 民族 汉
 * 生日1993年9月7日
 * 住址 吉林省农安县青山口乡柳条沟村乡约屯17组
 * 公民身份证号 220122199309077010
 *app_title = (TextView) mView.findViewById(R.id.app_title);
 app_title.setText("群组");
 * on 2017-06-03.
 */

public class GroupFragment extends BaseSupportFragment implements MainView {
    @Inject
    protected SalaryPresenter mPresenter;
    @Bind(R.id.recyclerview) protected RecyclerView mCakeList;

    public static GroupFragment myFragment;
    public static synchronized GroupFragment getInstance(String getPassword){
        if (myFragment == null){
            synchronized (LeftFragment.class){
                if (myFragment == null){
                    myFragment = new GroupFragment();
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
        app_title = (TextView) mView.findViewById(R.id.app_title);
        app_title.setText("群组");
        mCakeList = (RecyclerView) mView.findViewById(R.id.recyclerview);

        ptrFrameLayout = (PtrFrameLayout) mView.findViewById(R.id.pull_to_refresh);
        MaterialHeader header = new MaterialHeader(getContext());
        header.setPadding(0, 20, 0, 20);
        ptrFrameLayout.setDurationToCloseHeader(1500);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        DaggerSalaryComponent.builder()
                .applicationComponent(((SalaryApplication) (getActivity().getApplication())).getApplicationComponent())
                .salaryModule(new SalaryModule(this))
                .build().inject(this);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mCakeList.setLayoutManager(linearLayoutManager);
        mCakeList.setHasFixedSize(true);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return  PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
//                mPresenter.getSalaries();
                String getPassword = (String) myFragment.getArguments().get("getPassword");
                mPresenter.getTask(getPassword);
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCakeAdapter.setDataList(mSalaries);
                        ptrFrameLayout.refreshComplete();
                    }
                }, 1500);
                mCakeList.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
                    @Override
                    public void onLoadMore(int current_page) {
                        //maintain scroll position
                        int lastFirstVisiblePosition = ((LinearLayoutManager) mCakeList.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                        ((LinearLayoutManager) mCakeList.getLayoutManager()).scrollToPosition(lastFirstVisiblePosition);
//                        Toast.makeText(getActivity(), "底部", Toast.LENGTH_SHORT).show();
//                        Log.d("TAG","底部");
                        mCakeAdapter.addCakes(mSalaries);
//                loadMore(jsonSubreddit);
                    }
                });
            }
        });

    }

    @Override
    public int getBaseView() {
        return R.layout.fragment_app;
    }

    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
//        String getPassword = (String) myFragment.getArguments().get("getPassword");
//        mPresenter.getTask(getPassword);
        mCakeAdapter = new SalaryAdapter(getLayoutInflater(savedInstanceState)) {
            @Override
            public int getView() {
//                mCakeAdapter.isChangeText=true;
                return R.layout.item_group;
            }

            @Override
            public GroupHolder getHolder() {
                GroupHolder mainHolder = new GroupHolder(mCakeAdapter.mView);
//                mainHolder.isChangeText=true;
                return mainHolder;
            }
        };
//        mCakeAdapter.setCakeClickListener(mCakeClickListener);
            mCakeList.setAdapter(mCakeAdapter);
        ptrFrameLayout.setLoadingMinTime(1500);
        ptrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrameLayout.autoRefresh(true);
            }
        }, 50);
    }

    @Override
    public void onSalaryLoaded(List<Salary> salaries) {
//        mCakeAdapter.addCakes(salaries);
        mSalaries = salaries;
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
