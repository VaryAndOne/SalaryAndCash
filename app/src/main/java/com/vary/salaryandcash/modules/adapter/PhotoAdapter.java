package com.vary.salaryandcash.modules.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseRecyclerAdapter;
import com.vary.salaryandcash.base.CommonHolder;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.utilities.ImageUtils;

import butterknife.Bind;

import static com.vary.salaryandcash.utilities.ImageUtils.zoomImg;

/**
 * Created by lcodecore on 2016/12/7.
 */

public class PhotoAdapter extends BaseRecyclerAdapter<Salary> {


    @Override
    public CommonHolder<Salary> setViewHolder(ViewGroup parent) {
        return new CardHolder(parent.getContext(), parent);
    }

    class CardHolder extends CommonHolder<Salary> {
        @Bind(R.id.tv_info)
        TextView tv_info;

        @Bind(R.id.iv_pic)
        ImageView iv_pic;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_photo);
        }

        @Override
        public void bindData(Salary photo) {
            Glide.with(itemView.getContext())
                    .load(photo.getMicroVideo())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {


                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {


                            //这个bitmap就是你图片url加载得到的结果
                            //获取bitmap信息，可赋值给外部变量操作，也可在此时行操作。
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) iv_pic.getLayoutParams();//获取你要填充图片的布局的layoutParam
//                            layoutParams.height = (int) (((float) bitmap.getHeight()) / bitmap.getWidth() * ImageUtils.getScreenWidth(itemView.getContext()) / 2 );
                            layoutParams.height = (int) (((float) bitmap.getHeight()) / bitmap.getWidth() * ImageUtils.getScreenWidth(itemView.getContext())/1.2);
                            //因为是2列,所以宽度是屏幕的一半,高度是根据bitmap的高/宽*屏幕宽的一半
                            layoutParams.width =  ImageUtils.getScreenWidth(itemView.getContext()) / 2;//这个是布局的宽度
                            iv_pic.setLayoutParams(layoutParams);//容器的宽高设置好了
                            bitmap = zoomImg(bitmap, layoutParams.width, layoutParams.height);
                            // 然后在改变一下bitmap的宽高
                            iv_pic.setImageBitmap(bitmap);
                        }


                    });

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