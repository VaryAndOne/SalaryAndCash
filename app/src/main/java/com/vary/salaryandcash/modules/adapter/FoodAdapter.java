package com.vary.salaryandcash.modules.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseRecyclerAdapter;
import com.vary.salaryandcash.base.CommonHolder;
import com.vary.salaryandcash.mvp.model.Salary;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;
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

public class FoodAdapter extends BaseRecyclerAdapter<Salary> {
    @Override
    public CommonHolder<Salary> setViewHolder(ViewGroup parent) {
        return new CardHolder(parent.getContext(), parent);
    }

    class CardHolder extends CommonHolder<Salary> {

        @Bind(R.id.avatar)
        CircleImageView avatar;

        @Bind(R.id.tv_info)
        TextView tv_info;

        @Bind(R.id.iv_icon)
        ImageView iv_food;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_food);
        }

        @Override
        public void bindData(Salary food) {
//            avatar.setImageResource(food.avatar_id);
//            iv_food.setImageResource(food.imageSrc);
            Glide.with(itemView.getContext()).load(food.getMicroVideo())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv_food);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    ToastUtil.show("item clicked!");
                }
            });
        }
    }
}
