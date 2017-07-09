package com.vary.salaryandcash.modules.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import com.vary.salaryandcash.modules.holder.MainHolder;
import com.vary.salaryandcash.modules.itf.EndlessRecyclerOnScrollListenerStaggered;
import com.vary.salaryandcash.modules.itf.OnItemClickListener;
import com.vary.salaryandcash.mvp.model.AccountResponse;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.presenter.SalaryPresenter;
import com.vary.salaryandcash.mvp.view.MainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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

public class MyFragment extends BaseSupportFragmentVertical implements MainView {
    public static MyFragment myFragment;

    public static synchronized MyFragment getInstance(int position, MainFragment mainFragment){
        mMainFragment=mainFragment;
        if (myFragment == null){
            synchronized (MyFragment.class){
                if (myFragment == null){
                    myFragment = new MyFragment();
                }
            }
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
//                isChangeLayout = true;
                return R.layout.item_photo;
            }

            @Override
            public MainHolder getHolder() {
                MainHolder mainHolder = new MainHolder(mCakeAdapter.mView);
                return mainHolder;
            }
        };

        mCakeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                // 这里的DetailFragment在flow包里
                // 这里是父Fragment启动,要注意 栈层级
//                ((SupportFragment) getParentFragment()).start(new VideoPlayerFragment());
                ((SupportFragment) getParentFragment()).start(VideoPlayerFragment.getInstance());
                Toast.makeText(getActivity(), "position"+position, Toast.LENGTH_SHORT).show();
            }
        });

        ptrFrameLayout.setLoadingMinTime(1500);
        ptrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrameLayout.autoRefresh(true);
            }
        }, 50);
        rv.setAdapter(mCakeAdapter);
    }

    @Override
    public void onSalaryLoaded(final List<Salary> salaries) {
        mSalaries = salaries;
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
    public void onAccountLoaded(AccountResponse response) {

    }

    @Override
    protected void initView() {
        rv = (RecyclerView) mView.findViewById(R.id.recyclerview);
        rv.getItemAnimator().setChangeDuration(0);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
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
//        mStaggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rv.setLayoutManager(mStaggeredGridLayoutManager);
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
                rv.setOnScrollListener(new EndlessRecyclerOnScrollListenerStaggered(mStaggeredGridLayoutManager) {
                    @Override
                    public void onLoadMore(int current_page) {
                        int mSpanCount = 2;
                        int[] into = new int[mSpanCount];
                        int firstVisibleItem = mStaggeredGridLayoutManager.findFirstVisibleItemPositions(into)[0];
                        // do something...
//                        getData(current_page);
//                        Toast.makeText(getActivity(), "底部", Toast.LENGTH_SHORT).show();
                        mCakeAdapter.addCakes(mSalaries);
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
