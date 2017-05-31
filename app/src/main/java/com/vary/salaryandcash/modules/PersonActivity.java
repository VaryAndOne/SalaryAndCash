package com.vary.salaryandcash.modules;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.modules.adapter.PersonAdapter;
import com.vary.salaryandcash.mvp.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-04-21.
 */

public class PersonActivity extends AppCompatActivity {

    private PersonAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        setupRecyclerView((RecyclerView) findViewById(R.id.recyclerview));
    }

    private void setupRecyclerView(RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        foodAdapter = new PersonAdapter();
        View view = View.inflate(this, R.layout.item_head, null);
        foodAdapter.setHeadHolder(view);
        rv.setAdapter(foodAdapter);
        refreshCard();
    }

    void refreshCard() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("任务",R.drawable.icon_app));
        persons.add(new Person("群组",R.drawable.ic_group_black_24dp));
        persons.add(new Person("盯紧",R.drawable.ic_flag_black_24dp));
        persons.add(new Person("我的",R.drawable.ic_camera_alt_black_24dp));
        persons.add(new Person("清除缓存",R.drawable.ic_delete_sweep_black_24dp));
        persons.add(new Person("设置",R.drawable.ic_settings_black_24dp));
//        persons.add(new Food("Fresh Strawberries and Blackberries in Little Bowl", "BY VIKTOR HANACEK", R.drawable.food4, R.drawable.avatar3));
//        persons.add(new Food("Baked Healthy Fitness Broccoli Pie with Basil", "BY VIKTOR HANACEK", R.drawable.food5, R.drawable.avatar4));
        foodAdapter.setDataList(persons);
    }
}
