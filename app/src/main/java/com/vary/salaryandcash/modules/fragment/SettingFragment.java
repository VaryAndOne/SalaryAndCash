package com.vary.salaryandcash.modules.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseHolder;
import com.vary.salaryandcash.base.BaseSupportFragment;
import com.vary.salaryandcash.modules.adapter.PersonAdapter;
import com.vary.salaryandcash.modules.adapter.SalaryAdapter;
import com.vary.salaryandcash.modules.adapter.SettingAdapter;
import com.vary.salaryandcash.mvp.model.Person;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.presenter.SalaryPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
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

public class SettingFragment extends BaseSupportFragment {
    private SettingAdapter foodAdapter;
    @Bind(R.id.recyclerview) protected RecyclerView mCakeList;
    @Override
    protected void initView() {
        app_title = (TextView) mView.findViewById(R.id.app_title);
        app_title.setText("设置");
    }

    @Override
    public int getBaseView() {
        return R.layout.fragment_app;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCakeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        foodAdapter = new SettingAdapter();
        mCakeList.setAdapter(foodAdapter);
        refreshCard();
    }
    void refreshCard() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("服务条款及隐私权政策",R.drawable.ic_help_outline_black_24dp));
        persons.add(new Person("关于我们",R.drawable.ic_help_outline_black_24dp));
        persons.add(new Person("版本 1.0",R.drawable.ic_help_outline_black_24dp));
        persons.add(new Person("联系我们",R.drawable.ic_help_outline_black_24dp));
        foodAdapter.setDataList(persons);
    }
}
