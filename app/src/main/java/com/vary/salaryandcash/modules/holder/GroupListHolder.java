package com.vary.salaryandcash.modules.holder;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMGroup;
import com.vary.salaryandcash.base.BaseHolder;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.utilities.ColorUtils;

import java.util.List;

/**
 * Created by Administrator on 2017-07-04.
 */

public class GroupListHolder extends BaseHolder<EMGroup> {
    private Context mContext;
    private static GroupListHolder mMainHolder;
    //        SpannableString msp =new SpannableString("我是你爹\n88");
    public GroupListHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
    }


    @Override
    public void bindData(EMGroup cake) {
        tv_info.setText(cake.getGroupName());
        int customizedColor = ColorUtils.CustomizedColors()[ColorUtils.getInstance().nextInt(ColorUtils.CustomizedColors().length)];
//        Glide.with(mContext).load(cake.getMicroVideo())
//                .placeholder(customizedColor)
//                .crossFade()
//                .into(mCakeIcon);
    }


}
