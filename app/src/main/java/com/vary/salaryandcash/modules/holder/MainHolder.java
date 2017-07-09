package com.vary.salaryandcash.modules.holder;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.base.BaseHolder;
import com.vary.salaryandcash.modules.fragment.MainFragment;
import com.vary.salaryandcash.modules.fragment.MyFragment;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.utilities.ColorUtils;

import java.util.Random;

/**
 * Created by Administrator on 2017-07-04.
 */

public class MainHolder extends BaseHolder<Salary> {
    private static MainHolder myFragment;

    public MainHolder(View itemView) {
        super(itemView);
    }
    //        SpannableString msp =new SpannableString("我是你爹\n88");
    @Override
    public void bindData(final Salary cake) {
        tv_info.setText(cake.getPreviewDescription() + ".00");
        int customizedColor = ColorUtils.CustomizedColors()[ColorUtils.getInstance().nextInt(ColorUtils.CustomizedColors().length)];
        Glide.with(itemView.getContext())
                .load(cake.getMicroVideo())
                .placeholder(customizedColor)
                .crossFade()
                .override(SalaryApplication.getWidth()/2, Integer.parseInt(cake.getDetailDescription()))
                .into(mCakeIcon);
    }

}
