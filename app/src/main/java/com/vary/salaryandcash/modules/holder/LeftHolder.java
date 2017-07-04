package com.vary.salaryandcash.modules.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseHolder;
import com.vary.salaryandcash.modules.itf.OnCakeClickListener;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.utilities.ImageUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.vary.salaryandcash.utilities.ImageUtils.zoomImg;

/**
 * Created by Administrator on 2017-07-04.
 */

public class LeftHolder extends BaseHolder<Salary> implements View.OnClickListener{
    @Bind(R.id.iv_icon) protected ImageView mCakeIcon;
    @Bind(R.id.iv_person) protected ImageView personHead;
    @Bind(R.id.tv_info) protected TextView tv_info;
    private Context mContext;
    private Salary mCake;
    private static LeftHolder mMainHolder;
    //        SpannableString msp =new SpannableString("我是你爹\n88");
    public LeftHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mContext = itemView.getContext();
    }

    @Override
    public void bindData(Salary cake) {
        mCake = cake;
        tv_info.setText(cake.getId()+".00");
        Glide.with(mContext).load(cake.getMicroVideo())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(personHead);
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
