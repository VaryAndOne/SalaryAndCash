package com.vary.salaryandcash.modules.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.utilities.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

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
public abstract class SalaryAdapter extends RecyclerView.Adapter<SalaryAdapter.Holder> {
    private LayoutInflater mLayoutInflater;
    private List<Salary> mCakeList = new ArrayList<>();
    public boolean isChangeLayout = false;

    public SalaryAdapter(LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    public  abstract int getView();

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(getView(),parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(mCakeList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCakeList.size();
    }

    public void setDataList(List<Salary> datas) {
        mCakeList.clear();
        if (null != datas) {
            mCakeList.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public void addCakes(List<Salary> cakes) {
        mCakeList.addAll(cakes);
        notifyDataSetChanged();
    }

    public void clearCakes() {
        mCakeList.clear();
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.iv_icon) protected ImageView mCakeIcon;
//        @Bind(R.id.textview_title) protected TextView mCakeTitle;
//        @Bind(R.id.textview_preview_description) protected TextView mCakePreviewDescription;

        private Context mContext;
        private Salary mCake;

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bind(Salary cake) {
            mCake = cake;
//            mCakeTitle.setText(cake.getTitle());
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
    }

    public void setCakeClickListener(OnCakeClickListener listener) {
        mCakeClickListener = listener;
    }

    private OnCakeClickListener mCakeClickListener;

    public interface OnCakeClickListener {

        void onClick(View v, Salary cake, int position);
    }
}