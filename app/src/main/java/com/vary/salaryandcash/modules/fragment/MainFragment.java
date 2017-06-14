package com.vary.salaryandcash.modules.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseSupportFragment;
import com.vary.salaryandcash.modules.CameraActivity;
import com.vary.salaryandcash.modules.adapter.MyPagerAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

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

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        mPager = (ViewPager) view.findViewById(R.id.pager);
//        mPager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager()));
        mPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        view.findViewById(R.id.account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(new HomeFragment());
            }
        });
        view.findViewById(R.id.camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void onLazyInitView(@Nullable Bundle savedInstanceState){
       mPager.setCurrentItem(1,true);
    }
}
