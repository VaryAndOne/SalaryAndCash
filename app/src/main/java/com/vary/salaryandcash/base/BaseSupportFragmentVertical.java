package com.vary.salaryandcash.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.di.components.DaggerSalaryComponent;
import com.vary.salaryandcash.di.module.SalaryModule;
import com.vary.salaryandcash.modules.MainActivity;
import com.vary.salaryandcash.modules.adapter.SalaryAdapter;
import com.vary.salaryandcash.modules.fragment.MainFragment;
import com.vary.salaryandcash.modules.itf.MyOnTouchListener;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.presenter.SalaryPresenter;
import com.vary.salaryandcash.mvp.view.MainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

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

public abstract class BaseSupportFragmentVertical extends SupportFragment {
    public View mView;
    public TextView app_title;
    public ImageView remove;
    public SalaryAdapter mCakeAdapter;
    @Inject
    protected SalaryPresenter mPresenter;
    public static MainFragment mMainFragment;
    public RecyclerView rv;
    public PtrFrameLayout ptrFrameLayout;
    public StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    public LinearLayoutManager linearLayoutManager;
    public List<Salary> mSalaries;
    public boolean isRefresh = true;
    public int mVerticalOffset = 0;
    public EventBus bus = EventBus.getDefault();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getBaseView(), container, false);
        bus.register(this);
        initView();
        return mView;
    }

    protected abstract void initView();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {/* Do something */
        mVerticalOffset = Integer.parseInt(event);
//        Log.d("TAG","mVerticalOffset"+mVerticalOffset);
        isRefresh = mVerticalOffset >= 0 ? true : false;
    };

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        bus.unregister(this);
    }

    public abstract int getBaseView();
}
