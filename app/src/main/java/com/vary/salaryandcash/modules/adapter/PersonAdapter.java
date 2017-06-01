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

public class PersonAdapter extends BaseRecyclerAdapter<Person> {
    @Override
    public CommonHolder<Person> setViewHolder(ViewGroup parent) {
        return new PersonAdapter.CardHolder(parent.getContext(), parent);
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
            super(context, root, R.layout.item_person);
        }

        @Override
        public void bindData(final Person person) {
//            avatar.setImageResource(food.avatar_id);
            iv_food.setImageResource(person.imageSrc);
            tv_food.setText(person.title);

//            tv_info.setText(food.info);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = null;
                    switch (getLayoutPosition()){
                        case 0:
                            ToastUtil.show("getLayoutPosition : " + getLayoutPosition());
                            break;
                        case 1:
                            intent = new Intent(SalaryApplication.appContext, TaskActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            SalaryApplication.appContext.startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(SalaryApplication.appContext, FillStyleActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            SalaryApplication.appContext.startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent(SalaryApplication.appContext, CatchActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            SalaryApplication.appContext.startActivity(intent);
                            break;
                        case 4:
                            ToastUtil.show("缓存已清理");
                            break;
                        case 5:
                            intent = new Intent(SalaryApplication.appContext, SettingActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            SalaryApplication.appContext.startActivity(intent);
                            break;
                    }
                }
            });
        }
    }
}
