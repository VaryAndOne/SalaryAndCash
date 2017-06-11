package com.vary.salaryandcash.modules.fragment;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseSupportFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017-06-09.
 */

public class SplashFragment extends SupportFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_splash, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        if (view != null) {
            ImageView imageView = (ImageView) view.findViewById(R.id.laucher_logo);
            playAnimator(imageView);
        }
    }

    private void playAnimator(ImageView imageView) {
        if (imageView != null) {
            PropertyValuesHolder pvha = PropertyValuesHolder.ofFloat("alpha", 1f);
            ObjectAnimator.ofPropertyValuesHolder(imageView,pvha).setDuration(1000).start();
        }
    }
}
