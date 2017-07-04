package com.vary.salaryandcash.modules.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
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
import com.vary.salaryandcash.modules.holder.MainHolder;
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
public abstract class SalaryAdapter extends RecyclerView.Adapter<MainHolder> {
    public LayoutInflater mLayoutInflater;
    public List<Salary> mCakeList = new ArrayList<>();
    public View mView;
//    public boolean isChangeLayout = false;
//    public boolean isChangeText= false;

    public SalaryAdapter(LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    public abstract int getView();

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = mLayoutInflater.inflate(getView(), parent, false);
        return getHolder();
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {
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

    public abstract MainHolder getHolder();
}