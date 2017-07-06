package com.vary.salaryandcash.modules.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseHolder;
import com.vary.salaryandcash.modules.fragment.LeftFragment;
import com.vary.salaryandcash.modules.fragment.MainFragment;
import com.vary.salaryandcash.modules.itf.OnCakeClickListener;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.utilities.ImageUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.vary.salaryandcash.utilities.ImageUtils.zoomImg;

/**
 * Created by Administrator on 2017-07-04.
 */

public class MainHolder extends BaseHolder<Salary> implements View.OnClickListener{
    private Salary mCake;
    private static MainHolder mMainHolder;

    public MainHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }
    //        SpannableString msp =new SpannableString("我是你爹\n88");

    @Override
    public void bindData(final Salary cake) {
        mCake = cake;
        tv_info.setText(cake.getPreviewDescription() + ".00");
        Glide.with(itemView.getContext())
                .load(cake.getMicroVideo())
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .override(Integer.parseInt(cake.getTitle()), Integer.parseInt(cake.getDetailDescription()))
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
