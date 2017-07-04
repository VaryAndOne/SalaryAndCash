package com.vary.salaryandcash.modules.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vary.salaryandcash.base.BaseHolder;
import com.vary.salaryandcash.modules.holder.MainHolder;
import com.vary.salaryandcash.mvp.model.Salary;

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
public abstract class SalaryAdapter extends RecyclerView.Adapter<BaseHolder<Salary>> {
    public LayoutInflater mLayoutInflater;
    public List<Salary> mCakeList = new ArrayList<>();
    public View mView;

    public SalaryAdapter(LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    public abstract int getView();

    @Override
    public BaseHolder<Salary> onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = mLayoutInflater.inflate(getView(), parent, false);
        return getHolder();
    }

    @Override
    public void onBindViewHolder(BaseHolder<Salary> holder, int position) {
        holder.bindData(mCakeList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCakeList.size();
    }

    public void setDataList(List<Salary> datas) {
        mCakeList.clear();
        if (null != datas) {
            mCakeList.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public void addCakes(List<Salary> cakes) {
        mCakeList.addAll(cakes);
        notifyDataSetChanged();
    }

    public void clearCakes() {
        mCakeList.clear();
        notifyDataSetChanged();
    }

    public abstract BaseHolder<Salary> getHolder();
}