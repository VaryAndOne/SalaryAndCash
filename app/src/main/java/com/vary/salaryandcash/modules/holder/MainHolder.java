package com.vary.salaryandcash.modules.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseHolder;
import com.vary.salaryandcash.modules.fragment.LeftFragment;
import com.vary.salaryandcash.modules.fragment.MainFragment;
import com.vary.salaryandcash.modules.itf.OnCakeClickListener;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.utilities.ImageUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.vary.salaryandcash.utilities.ImageUtils.zoomImg;

/**
 * Created by Administrator on 2017-07-04.
 */

public class MainHolder extends BaseHolder<Salary> implements View.OnClickListener{
    public boolean isChangeLayout = false;
    public boolean isChangeText= false;
    @Bind(R.id.iv_icon) protected ImageView mCakeIcon;
    @Bind(R.id.tv_info) protected TextView tv_info;
    //        @Bind(R.id.textview_preview_description) protected TextView mCakePreviewDescription;
    private Context mContext;
    private Salary mCake;
    private static MainHolder mMainHolder;
    //        SpannableString msp =new SpannableString("我是你爹\n88");
    public MainHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mContext = itemView.getContext();
//        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(Salary cake) {
        mCake = cake;
        if (isChangeText){
            tv_info.setText("我是你爹\n88");
        }else{
            tv_info.setText(cake.getPreviewDescription());
        }

//            mCakePreviewDescription.setText(cake.getPreviewDescription());
        if (isChangeLayout == true){
            Glide.with(itemView.getContext())
                    .load(cake.getMicroVideo())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                            //这个bitmap就是你图片url加载得到的结果
                            //获取bitmap信息，可赋值给外部变量操作，也可在此时行操作。
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mCakeIcon.getLayoutParams();//获取你要填充图片的布局的layoutParam
//                            layoutParams.height = (int) (((float) bitmap.getHeight()) / bitmap.getWidth() * ImageUtils.getScreenWidth(itemView.getContext()) / 2 );
                            layoutParams.height = (int) (((float) bitmap.getHeight()) / bitmap.getWidth() * ImageUtils.getScreenWidth(itemView.getContext())/1.2);
                            //因为是2列,所以宽度是屏幕的一半,高度是根据bitmap的高/宽*屏幕宽的一半
                            layoutParams.width =  ImageUtils.getScreenWidth(itemView.getContext()) / 2;//这个是布局的宽度
//                                Toast.makeText(mContext, "layoutParams.height"+layoutParams.height, Toast.LENGTH_SHORT).show();
                            mCakeIcon.setLayoutParams(layoutParams);//容器的宽高设置好了
                            bitmap = zoomImg(bitmap, layoutParams.width, layoutParams.height);
                            // 然后在改变一下bitmap的宽高
                            mCakeIcon.setImageBitmap(bitmap);
                        }
                    });
        }else{
            Glide.with(mContext).load(cake.getMicroVideo())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(mCakeIcon);
        }

    }

    @Override
    public void onClick(View v) {
        if (mCakeClickListener != null) {
            mCakeClickListener.onClick(mCakeIcon, mCake, getAdapterPosition());
        }
    }

    public void setCakeClickListener(OnCakeClickListener listener) {
        mCakeClickListener = listener;
    }

    private OnCakeClickListener mCakeClickListener;

}
