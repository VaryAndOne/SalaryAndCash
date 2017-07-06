package com.vary.salaryandcash.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.mvp.model.Salary;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017-07-04.
 */

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder{
    @Bind(R.id.iv_icon) public ImageView mCakeIcon;
    @Bind(R.id.tv_info) protected TextView tv_info;
    public BaseHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bindData(T t);
}
