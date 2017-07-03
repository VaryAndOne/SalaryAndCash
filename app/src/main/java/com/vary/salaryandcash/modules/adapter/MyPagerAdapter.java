package com.vary.salaryandcash.modules.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.base.BaseSupportFragmentVertical;
import com.vary.salaryandcash.modules.fragment.LeftFragment;
import com.vary.salaryandcash.modules.fragment.MainFragment;
import com.vary.salaryandcash.modules.fragment.MyFragment;
import com.vary.salaryandcash.modules.fragment.RightFragment;

import java.util.ArrayList;
import java.util.List;

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

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    List<BaseSupportFragmentVertical> fragments ;
    MainFragment mainFragment;

    public MyPagerAdapter(FragmentManager fm, MainFragment mainFragment) {
        super(fm);
        this.mainFragment=mainFragment;
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.add(RightFragment.getInstance(1));
        fragments.add(MyFragment.getInstance(2,mainFragment));
        fragments.add(LeftFragment.getInstance(3,mainFragment));
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mainFragment.getActivity().getSupportFragmentManager().beginTransaction().remove((Fragment) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
