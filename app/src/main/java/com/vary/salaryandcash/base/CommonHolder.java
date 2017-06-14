package com.vary.salaryandcash.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

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
/**
 * 聊天消息条目的基类
 * ViewHolder 与 Adapter 解耦
 */
public abstract class CommonHolder<T> extends RecyclerView.ViewHolder {
    public CommonHolder(Context context, ViewGroup root, int layoutRes) {
        super(LayoutInflater.from(context).inflate(layoutRes, root, false));
        ButterKnife.bind(this, itemView);
    }
    /**
     * 此适配器是为了能让详情页共用列表页的ViewHolder，一般情况无需重写该构造器
     */
    public CommonHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public Context getContext() {
        return itemView.getContext();
    }
    /**
     * 用给定的 data 对 holder 的 view 进行赋值
     */
    public abstract void bindData(T t);

    public void bindHeadData(){}
    /**
     * 通知适配器更新布局
     */
    public interface OnNotifyChangeListener {
        void onNotify();
    }
    OnNotifyChangeListener listener;
    public void setOnNotifyChangeListener(OnNotifyChangeListener listener) {
        this.listener = listener;
    }
    public void notifyChange() {
        listener.onNotify();
    }
}