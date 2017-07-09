package com.vary.salaryandcash.modules.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.base.BaseHolder;
import com.vary.salaryandcash.modules.holder.MainHolder;
import com.vary.salaryandcash.modules.itf.OnItemClickListener;
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
public abstract class SalaryAdapter<T> extends RecyclerView.Adapter<BaseHolder<T>> {
    public LayoutInflater mLayoutInflater;
    public List<T> mCakeList = new ArrayList<>();
    public View mView;
    private OnItemClickListener mClickListener;

    public SalaryAdapter(LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    public abstract int getView();

    @Override
    public BaseHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = mLayoutInflater.inflate(getView(), parent, false);
        return getHolder();
    }

    @Override
    public void onBindViewHolder(final BaseHolder<T> holder, final int position) {
        holder.bindData(mCakeList.get(position));
//        holder.mCakeIcon
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, v, holder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCakeList.size();
    }

    public void setDataList(List<T> datas) {
        mCakeList.clear();
        if (null != datas) {
            mCakeList.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public void addCakes(List<T> cakes) {
        mCakeList.addAll(cakes);
        notifyDataSetChanged();
    }

    public void clearCakes() {
        mCakeList.clear();
        notifyDataSetChanged();
    }

    public abstract BaseHolder<T> getHolder();

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

}