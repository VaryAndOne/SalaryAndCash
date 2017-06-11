package com.vary.salaryandcash.modules.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.modules.adapter.PersonAdapter;
import com.vary.salaryandcash.mvp.model.Person;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017-06-09.
 */

public class HomeFragment extends SupportFragment {
    private View view;
    private PersonAdapter foodAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView((RecyclerView) view.findViewById(R.id.recyclerview));
    }

    private void setupRecyclerView(RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        foodAdapter = new PersonAdapter(this);
        View view = View.inflate(getActivity(), R.layout.item_head, null);
        foodAdapter.setHeadHolder(view);
        rv.setAdapter(foodAdapter);
        refreshCard();
    }

    void refreshCard() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("任务",R.drawable.icon_app));
        persons.add(new Person("群组",R.drawable.ic_group_black_24dp));
        persons.add(new Person("盯紧",R.drawable.ic_flag_black_24dp));
        persons.add(new Person("清扫",R.drawable.ic_delete_sweep_black_24dp));
        persons.add(new Person("设置",R.drawable.ic_settings_black_24dp));
        persons.add(new Person("意见反馈",R.drawable.ic_announcement_black_24dp));
        persons.add(new Person("帮助",R.drawable.ic_help_outline_black_24dp));
        foodAdapter.setDataList(persons);
    }
}