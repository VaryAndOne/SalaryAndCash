package com.vary.salaryandcash.modules.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.base.BaseRecyclerAdapter;
import com.vary.salaryandcash.base.CommonHolder;
import com.vary.salaryandcash.modules.CatchActivity;
import com.vary.salaryandcash.modules.FillStyleActivity;
import com.vary.salaryandcash.modules.SettingActivity;
import com.vary.salaryandcash.modules.TaskActivity;
import com.vary.salaryandcash.mvp.model.Person;
import com.vary.salaryandcash.utilities.ToastUtil;

import butterknife.Bind;

/**
 * Created by Administrator on 2017-04-21.
 */

public class SettingAdapter extends BaseRecyclerAdapter<Person> {
    @Override
    public CommonHolder<Person> setViewHolder(ViewGroup parent) {
        return new SettingAdapter.CardHolder(parent.getContext(), parent);
    }

    class CardHolder extends CommonHolder<Person> {

//        @Bind(R.id.avatar)
//        CircleImageView avatar;
//
        @Bind(R.id.tv_person)
        TextView tv_food;
//
//        @Bind(R.id.tv_info)
//        TextView tv_info;

        @Bind(R.id.iv_person)
        ImageView iv_food;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_setting);
        }

        @Override
        public void bindData(final Person person) {
            iv_food.setImageResource(person.imageSrc);
            tv_food.setText(person.title);

        }
    }
}
