package com.vary.salaryandcash.modules.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.di.components.DaggerSalaryComponent;
import com.vary.salaryandcash.di.module.SalaryModule;
import com.vary.salaryandcash.modules.adapter.PhotoAdapter;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.presenter.SalaryPresenter;
import com.vary.salaryandcash.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
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

public class MyFragment extends SupportFragment implements MainView {

    private View mView;
    private PhotoAdapter photoAdapter;
    @Inject
    protected SalaryPresenter mPresenter;
    private static MainFragment mMainFragment;
    private  RecyclerView rv;

    public static MyFragment getInstance(int position, MainFragment mainFragment){
        mMainFragment=mainFragment;
        MyFragment myFragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt("position",position);
        myFragment.setArguments(args);
        return myFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_view_pager, container, false);
     //   textView = (TextView) layout.findViewById(R.id.position);
        DaggerSalaryComponent.builder()
                .applicationComponent(((SalaryApplication) (getActivity().getApplication())).getApplicationComponent())
                .salaryModule(new SalaryModule(this))
                .build().inject(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
   //         textView.setText("The page Selected is "+bundle.getInt("position"));
        }
        rv = (RecyclerView) mView.findViewById(R.id.recyclerview);

        final PtrFrameLayout ptrFrameLayout = (PtrFrameLayout) mView.findViewById(R.id.pull_refresh);
        MaterialHeader header = new MaterialHeader(getContext());
        header.setPadding(0, 20, 0, 20);
//        header.initWithString("Ultra PTR");
        ptrFrameLayout.setDurationToCloseHeader(100);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);

        final boolean[] refresh = {true};
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                mMainFragment.getApp_bar_layout().addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        refresh[0] = verticalOffset>=0?true:false;
                    }
                });
                return refresh[0]&&PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.refreshComplete();
                    }
                }, 100);
            }
        });

        return mView;
    }

    public void onLazyInitView(@Nullable Bundle savedInstanceState){
        mPresenter.getSalaries();
        if (mView != null) {
            rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            photoAdapter = new PhotoAdapter(this);
            rv.setAdapter(photoAdapter);
        }
    }


    @Override
    public void onSalaryLoaded(List<Salary> salaries) {
        photoAdapter.setDataList(salaries);
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
        photoAdapter.clearDatas();
    }
}
