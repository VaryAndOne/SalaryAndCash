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

public class ShopAdapter extends BaseRecyclerAdapter<Salary> {

    @Override
    public CommonHolder<Salary> setViewHolder(ViewGroup parent) {
        return new CardHolder(parent.getContext(), parent);
    }

    class CardHolder extends CommonHolder<Salary> {

//        @Bind(R.id.tv_info)
//        TextView tv_info;

        @Bind(R.id.image)
        ImageView iv_pic;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_shop_card);
        }

        @Override
        public void bindData(Salary photo) {
//            iv_pic.setImageResource(photo.imgSrc);
//            tv_info.setText(photo.name);
            Glide.with(itemView.getContext()).load(photo.getMicroVideo())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv_pic);
            iv_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    ToastUtil.show("item clicked!");
//                    Intent intent = new Intent(TkApplication.appContext, VideoPlayerActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    TkApplication.appContext.startActivity(intent);

                }
            });
        }


    }

}