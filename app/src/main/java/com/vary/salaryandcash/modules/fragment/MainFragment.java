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
 * Created by Administrator on 2017-06-09.
 */

public class MainFragment extends SupportFragment {
    private View view;
    private ViewPager mPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager()));
        view.findViewById(R.id.account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), PersonActivity.class);
//                startActivity(intent);
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
