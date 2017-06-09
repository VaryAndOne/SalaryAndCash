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
import com.vary.salaryandcash.modules.adapter.PersonAdapter;
import com.vary.salaryandcash.modules.adapter.SettingAdapter;
import com.vary.salaryandcash.mvp.model.Person;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017-06-09.
 */

public class SettingFragment extends SupportFragment {
    private View view;
    private SettingAdapter foodAdapter;
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        // todo,当该Fragment对用户可见时
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        TextView app_title = (TextView) view.findViewById(R.id.app_title);
        app_title.setText("设置");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView((RecyclerView) view.findViewById(R.id.recyclerview));
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
