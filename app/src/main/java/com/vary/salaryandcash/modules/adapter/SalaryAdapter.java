package com.vary.salaryandcash.modules.adapter;

/**
 * Created by Administrator on 2017-06-05.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.mvp.model.Salary;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/24/2016
 */
public class SalaryAdapter extends RecyclerView.Adapter<SalaryAdapter.Holder> {

    private LayoutInflater mLayoutInflater;
    private List<Salary> mCakeList = new ArrayList<>();

    public SalaryAdapter(LayoutInflater inflater) {
        mLayoutInflater = inflater;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_task, parent, false);
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
            Glide.with(mContext).load(cake.getMicroVideo())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(mCakeIcon);
        }

        @Override
        public void onClick(View v) {
//            if (mCakeClickListener != null) {
//                mCakeClickListener.onClick(mCakeIcon, mCake, getAdapterPosition());
//            }
        }
    }

//    public void setCakeClickListener(OnCakeClickListener listener) {
//        mCakeClickListener = listener;
//    }
//
//    private OnCakeClickListener mCakeClickListener;
//
//    public interface OnCakeClickListener {
//
//        void onClick(View v, Cake cake, int position);
//    }
}