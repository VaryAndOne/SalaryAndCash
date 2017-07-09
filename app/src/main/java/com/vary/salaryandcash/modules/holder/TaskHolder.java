package com.vary.salaryandcash.modules.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
    //        SpannableString msp =new SpannableString("我是你爹\n88");
    public TaskHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Salary cake) {
        tv_info.setText(cake.getTitle()+ ".00");
        tv_state.setText(cake.getPreviewDescription());
        int customizedColor = ColorUtils.CustomizedColors()[ColorUtils.getInstance().nextInt(ColorUtils.CustomizedColors().length)];
        Glide.with(SalaryApplication.appContext).load(cake.getMicroVideo())
                .placeholder(customizedColor)
                .crossFade()
                .into(mCakeIcon);

    }
}
