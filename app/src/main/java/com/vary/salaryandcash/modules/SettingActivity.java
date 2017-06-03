package com.vary.salaryandcash.modules;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.modules.adapter.PersonAdapter;
import com.vary.salaryandcash.modules.adapter.SettingAdapter;
import com.vary.salaryandcash.mvp.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-06-01.
 */

public class SettingActivity extends AppCompatActivity {

    private SettingAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setupRecyclerView((RecyclerView) findViewById(R.id.recyclerview));
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
