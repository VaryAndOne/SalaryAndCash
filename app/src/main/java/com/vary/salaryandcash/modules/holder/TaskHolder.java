package com.vary.salaryandcash.modules.holder;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.base.BaseHolder;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.utilities.ColorUtils;

import butterknife.Bind;

/**
 * Created by Administrator on 2017-07-04.
 */

public class TaskHolder extends BaseHolder<Salary> {
    @Bind(R.id.tv_state) protected TextView tv_state;
    private static TaskHolder mMainHolder;
    public TaskHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Salary cake) {
        tv_info.setText(cake.getTitle()+ ".00");
//        SpannableString msp = new SpannableString("测试文字测\n试文字测试文字测试文字测试文字");
        SpannableString msp = new SpannableString(cake.getPreviewDescription());
        msp.setSpan(new AbsoluteSizeSpan(22,true), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        msp.setSpan(new ForegroundColorSpan(SalaryApplication.appContext.getResources().getColor(R.color.green)), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_state.setText(msp);
//        tv_state.setText(cake.getPreviewDescription());

        int customizedColor = ColorUtils.CustomizedColors()[ColorUtils.getInstance().nextInt(ColorUtils.CustomizedColors().length)];
        Glide.with(SalaryApplication.appContext).load(cake.getMicroVideo())
                .placeholder(customizedColor)
                .crossFade()
                .into(mCakeIcon);

    }

}
