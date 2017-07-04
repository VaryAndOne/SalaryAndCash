package com.vary.salaryandcash.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vary.salaryandcash.mvp.model.Salary;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017-07-04.
 */

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder{
    public BaseHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bindData(T t);
}
