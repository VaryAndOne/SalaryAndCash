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
import com.vary.salaryandcash.modules.fragment.CatchFragment;
import com.vary.salaryandcash.modules.fragment.GroupFragment;
import com.vary.salaryandcash.modules.fragment.HelpFragment;
import com.vary.salaryandcash.modules.fragment.HomeFragment;
import com.vary.salaryandcash.modules.fragment.SettingFragment;
import com.vary.salaryandcash.modules.fragment.SuggestFragment;
import com.vary.salaryandcash.modules.fragment.TaskFragment;
import com.vary.salaryandcash.mvp.model.Person;
import com.vary.salaryandcash.utilities.ToastUtil;

import butterknife.Bind;

/**
 * Created by
 *
 * 温家才
 * 性别 男 民族 汉
 * 生日1993年9月7日
 * 住址 吉林省农安县青山口乡柳条沟村乡约屯17组
 * 公民身份证号 220122199309077010
 *
 * on 2017-06-03.
 */
public class PersonAdapter extends BaseRecyclerAdapter<Person> {
    private HomeFragment homeFragment;
    public PersonAdapter(HomeFragment homeFragment) {
        super();
        this.homeFragment = homeFragment;
    }

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
                            homeFragment.start(new TaskFragment());
                            break;
                        case 2:
                            homeFragment.start(new GroupFragment());
                            break;
                        case 3:
                            homeFragment.start(new CatchFragment());
                            break;
                        case 4:
                            ToastUtil.show("缓存已清理");
                            break;
                        case 5:
                            homeFragment.start(new SettingFragment());
                            break;
                        case 6:
                            homeFragment.start(new SuggestFragment());
                            break;
                        case 7:
                            homeFragment.start(new HelpFragment());
                            break;

                    }
                }
            });
        }
    }
}
