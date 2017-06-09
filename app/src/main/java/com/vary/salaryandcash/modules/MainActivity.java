package com.vary.salaryandcash.modules;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.modules.adapter.MyPagerAdapter;
import com.vary.salaryandcash.modules.fragment.HomeFragment;
import com.vary.salaryandcash.modules.fragment.MainFragment;

import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {
    private ViewPager mPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.frame_Layout_root, new MainFragment());
        }
    }
}
