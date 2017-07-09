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
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.base.BaseHolder;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.utilities.ColorUtils;

import butterknife.Bind;

/**
 * Created by Administrator on 2017-07-04.
 */

public class LeftHolder extends BaseHolder<Salary>{
    @Bind(R.id.iv_person) protected ImageView personHead;
    private static LeftHolder mMainHolder;
    //        SpannableString msp =new SpannableString("我是你爹\n88");
    public LeftHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Salary cake) {
        tv_info.setText(cake.getPreviewDescription()+".00");
        int customizedColor = ColorUtils.CustomizedColors()[ColorUtils.getInstance().nextInt(ColorUtils.CustomizedColors().length)];
        Glide.with(SalaryApplication.appContext).load(cake.getMicroVideo())
                .placeholder(R.drawable.ic_person)
                .crossFade()
                .into(personHead);
        Glide.with(SalaryApplication.appContext).load(cake.getMicroVideo())
                .placeholder(customizedColor)
                .crossFade()
                .into(mCakeIcon);
    }
}
