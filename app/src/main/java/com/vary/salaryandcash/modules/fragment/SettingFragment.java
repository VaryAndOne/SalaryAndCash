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
import com.vary.salaryandcash.base.BaseSupportFragment;
import com.vary.salaryandcash.modules.adapter.PersonAdapter;
import com.vary.salaryandcash.modules.adapter.SettingAdapter;
import com.vary.salaryandcash.mvp.model.Person;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017-06-09.
 */

public class SettingFragment extends BaseSupportFragment {
    private SettingAdapter foodAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_app, container, false);
        TextView app_title = (TextView) mView.findViewById(R.id.app_title);
        app_title.setText("设置");
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView((RecyclerView) mView.findViewById(R.id.recyclerview));
    }

    private void setupRecyclerView(RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        foodAdapter = new SettingAdapter();
        rv.setAdapter(foodAdapter);
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
