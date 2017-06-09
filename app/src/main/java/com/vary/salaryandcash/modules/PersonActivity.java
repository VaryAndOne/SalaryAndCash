package com.vary.salaryandcash.modules;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.modules.adapter.PersonAdapter;
import com.vary.salaryandcash.modules.fragment.HomeFragment;
import com.vary.salaryandcash.mvp.model.Person;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by Administrator on 2017-04-21.
 */

public class PersonActivity extends SupportActivity {

    private PersonAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.frame_Layout_root, new HomeFragment());
        }
    }
}
