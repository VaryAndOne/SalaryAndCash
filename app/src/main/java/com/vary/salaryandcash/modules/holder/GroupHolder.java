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

public class GroupHolder extends BaseHolder<Salary> implements View.OnClickListener{
    private Context mContext;
    private Salary mCake;
    private static GroupHolder mMainHolder;
    //        SpannableString msp =new SpannableString("我是你爹\n88");
    public GroupHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mContext = itemView.getContext();
    }

    @Override
    public void bindData(Salary cake) {
        mCake = cake;
        tv_info.setText(cake.getPreviewDescription());
        Glide.with(mContext).load(cake.getMicroVideo())
                .placeholder(R.drawable.shape_nine_pic)
                .crossFade()
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
