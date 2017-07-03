package com.vary.salaryandcash.modules.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.modules.adapter.MyPagerAdapter;

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

public class MainFragment extends SupportFragment {
    private View view;
    private ViewPager mPager;
    private AppBarLayout app_bar_layout;
    private static MainFragment mMainFragment;

    public static synchronized MainFragment newInstance() {
        if (mMainFragment == null) {
            mMainFragment = new MainFragment();
        }
        return mMainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        mPager = (ViewPager) view.findViewById(R.id.pager);
//        mPager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager()));
        mPager.setAdapter(new MyPagerAdapter(getChildFragmentManager(),this));
        view.findViewById(R.id.account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(new HomeFragment());
            }
        });
        view.findViewById(R.id.camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(new MediaFragment());
            }
        });
        app_bar_layout = (AppBarLayout) view.findViewById(R.id.app_bar_layout);
        return view;
    }

    public void onLazyInitView(@Nullable Bundle savedInstanceState){
       mPager.setCurrentItem(1,true);
    }

    public AppBarLayout getApp_bar_layout() {
        return app_bar_layout;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
