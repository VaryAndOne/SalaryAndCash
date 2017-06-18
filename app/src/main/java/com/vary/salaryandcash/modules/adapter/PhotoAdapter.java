package com.vary.salaryandcash.modules.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseRecyclerAdapter;
import com.vary.salaryandcash.base.CommonHolder;
import com.vary.salaryandcash.modules.fragment.HomeFragment;
import com.vary.salaryandcash.modules.fragment.MainFragment;
import com.vary.salaryandcash.modules.fragment.MyFragment;
import com.vary.salaryandcash.modules.fragment.SuggestFragment;
import com.vary.salaryandcash.modules.fragment.VideoPlayerFragment;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.utilities.ImageUtils;
import com.vary.salaryandcash.utilities.ToastUtil;

import butterknife.Bind;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.SupportFragment;

import static com.vary.salaryandcash.utilities.ImageUtils.zoomImg;

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

public class PhotoAdapter extends BaseRecyclerAdapter<Salary> {
private MyFragment myFragment;
    public PhotoAdapter(MyFragment myFragment) {
        super();
        this.myFragment= myFragment;
    }

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
                    ToastUtil.show("item clicked!");
                    ((SupportFragment)myFragment.getParentFragment()).start(new VideoPlayerFragment());
//                    Intent intent = new Intent(TkApplication.appContext, VideoPlayerActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    TkApplication.appContext.startActivity(intent);

                }
            });
        }

    }

}