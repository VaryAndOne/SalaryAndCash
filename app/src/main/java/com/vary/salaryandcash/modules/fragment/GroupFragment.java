package com.vary.salaryandcash.modules.fragment;

import android.app.admin.SystemUpdatePolicy;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.exceptions.HyphenateException;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.base.BaseSupportFragment;
import com.vary.salaryandcash.di.components.DaggerSalaryComponent;
import com.vary.salaryandcash.di.module.SalaryModule;
import com.vary.salaryandcash.modules.adapter.SalaryAdapter;
import com.vary.salaryandcash.modules.holder.GroupHolder;
import com.vary.salaryandcash.modules.holder.GroupListHolder;
import com.vary.salaryandcash.modules.itf.EndlessRecyclerOnScrollListener;
import com.vary.salaryandcash.modules.itf.OnItemClickListener;
import com.vary.salaryandcash.mvp.model.AccountResponse;
import com.vary.salaryandcash.mvp.model.SalariesResponse;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.presenter.SalaryPresenter;
import com.vary.salaryandcash.mvp.view.MainView;

import org.reactivestreams.Subscriber;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.Flowable.*;

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

public class GroupFragment extends BaseSupportFragment {
    @Inject
    protected SalaryPresenter mPresenter;
    @Bind(R.id.recyclerview) protected RecyclerView mCakeList;

    public static GroupFragment myFragment;
    public static String TAG = GroupFragment.class.getSimpleName();
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
        app_title = (TextView) mView.findViewById(R.id.app_title);
        app_title.setText("群组");
        mCakeList = (RecyclerView) mView.findViewById(R.id.recyclerview);
        ptrFrameLayout = (PtrFrameLayout) mView.findViewById(R.id.pull_to_refresh);
        MaterialHeader header = new MaterialHeader(getContext());
        header.setPadding(0, 20, 0, 20);
        ptrFrameLayout.setDurationToCloseHeader(1500);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
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
                if (EMClient.getInstance().isLoggedInBefore()) {
                    Flowable.create(new FlowableOnSubscribe<List<EMGroup>>() {
                        @Override
                        public void subscribe(FlowableEmitter<List<EMGroup>> e) throws Exception {
                            List<EMGroup> grouplist = EMClient.getInstance().groupManager().getJoinedGroupsFromServer();
//                        SystemClock.sleep(1000);
                            e.onNext(grouplist);
                            e.onComplete();
                        }
                    }, BackpressureStrategy.BUFFER)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<EMGroup>>() {
                                @Override
                                public void accept(final List<EMGroup> s) throws Exception {
                                    mCakeAdapter.setDataList(s);
                                    ptrFrameLayout.refreshComplete();
                                    mCakeAdapter.setOnItemClickListener(new OnItemClickListener() {
                                        @Override
                                        public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                                            String currUsername = EMClient.getInstance().getCurrentUser();
                                            String chatId = s.get(position).getGroupId();
                                            if (chatId.equals(currUsername) ) {
                                                Toast.makeText(getActivity(), "不能和自己聊天", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                            if (isStart){
                                                start(SessionFragment.getInstance(chatId));
                                                timer.start();
                                            }
                                            Toast.makeText(getActivity(), "position"+chatId, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                    return;
                }else {
                    ptrFrameLayout.refreshComplete();
                }
            }
        });

    }

    boolean isStart = true;
    CountDownTimer timer = new CountDownTimer(4000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            isStart =false;
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e("CountDown",millisUntilFinished+"");
        }
        @Override
        public void onFinish() {
            Log.d(TAG,"完成");
            isStart = true;
        }
    };

    @Override
    public int getBaseView() {
        return R.layout.fragment_app;
    }

    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        mCakeAdapter = new SalaryAdapter(getLayoutInflater(savedInstanceState)) {
            @Override
            public int getView() {
                return R.layout.item_group;
            }
            @Override
            public GroupListHolder getHolder() {
                GroupListHolder mainHolder = new GroupListHolder(mCakeAdapter.mView);
                return mainHolder;
            }
        };
        mCakeList.setAdapter(mCakeAdapter);
        ptrFrameLayout.setLoadingMinTime(1500);
        ptrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrameLayout.autoRefresh(true);
            }
        }, 50);
    }
}
