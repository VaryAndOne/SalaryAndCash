package com.vary.salaryandcash.modules.fragment;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.base.BaseSupportFragmentVertical;
import com.vary.salaryandcash.di.components.DaggerSalaryComponent;
import com.vary.salaryandcash.di.module.SalaryModule;
import com.vary.salaryandcash.modules.adapter.SalaryAdapter;
import com.vary.salaryandcash.modules.itf.EndlessRecyclerOnScrollListener;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.presenter.SalaryPresenter;
import com.vary.salaryandcash.mvp.view.MainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import me.yokeyword.fragmentation.SupportFragment;

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

public class LeftFragment extends BaseSupportFragmentVertical implements MainView {
    public static LeftFragment myFragment;
    public static synchronized LeftFragment getInstance(int position, MainFragment mainFragment){
        mMainFragment=mainFragment;
        if (myFragment == null){
            myFragment = new LeftFragment();
        }
        Bundle args = new Bundle();
        args.putInt("position",position);
        myFragment.setArguments(args);
        return myFragment;
    }

    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        mCakeAdapter = new SalaryAdapter(getLayoutInflater(savedInstanceState)) {
            @Override
            public int getView() {
                return R.layout.item_food;
            }
        };
        ptrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrameLayout.autoRefresh(true);
            }
        }, 1500);
        rv.setAdapter(mCakeAdapter);
    }
    @Override
    public void onSalaryLoaded(final List<Salary> salaries) {
        mSalaries=salaries ;
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
        mCakeAdapter.clearCakes();
    }


    @Override
    protected void initView() {
        rv = (RecyclerView) mView.findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(getActivity());
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
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                mMainFragment.getApp_bar_layout().addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        isRefresh = verticalOffset >= 0 ? true : false;
                        bus.post(""+verticalOffset);
                    }
                });
                return isRefresh && PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPresenter.getSalaries();
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCakeAdapter.setDataList(mSalaries);
                        ptrFrameLayout.refreshComplete();
                    }
                }, 1500);
                rv.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
                    @Override
                    public void onLoadMore(int current_page) {
                        //maintain scroll position
                        int lastFirstVisiblePosition = ((LinearLayoutManager) rv.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                        ((LinearLayoutManager) rv.getLayoutManager()).scrollToPosition(lastFirstVisiblePosition);
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
        return R.layout.fragment_view_pager;
    }
}
