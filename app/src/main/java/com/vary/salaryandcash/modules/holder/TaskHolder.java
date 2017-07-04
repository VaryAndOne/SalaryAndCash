package com.vary.salaryandcash.modules.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseHolder;
import com.vary.salaryandcash.modules.itf.OnCakeClickListener;
import com.vary.salaryandcash.mvp.model.Salary;

import butterknife.Bind;

/**
 * Created by Administrator on 2017-07-04.
 */

public class TaskHolder extends BaseHolder<Salary> implements View.OnClickListener{
    @Bind(R.id.iv_icon) protected ImageView mCakeIcon;
    @Bind(R.id.tv_info) protected TextView tv_info;
    @Bind(R.id.tv_state) protected TextView tv_state;
    private Context mContext;
    private Salary mCake;
    private static TaskHolder mMainHolder;
    //        SpannableString msp =new SpannableString("我是你爹\n88");
    public TaskHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mContext = itemView.getContext();
    }

    @Override
    public void bindData(Salary cake) {
        mCake = cake;
        tv_info.setText(cake.getTitle()+ ".00");
        tv_state.setText(cake.getPreviewDescription());
        Glide.with(mContext).load(cake.getMicroVideo())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mCakeIcon);

    }

    @Override
    public void onClick(View v) {
        if (mCakeClickListener != null) {
            mCakeClickListener.onClick(mCakeIcon, mCake, getAdapterPosition());
        }
    }

    public void setCakeClickListener(OnCakeClickListener listener) {
        mCakeClickListener = listener;
    }

    private OnCakeClickListener mCakeClickListener;

}
