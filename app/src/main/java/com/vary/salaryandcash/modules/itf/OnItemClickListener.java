package com.vary.salaryandcash.modules.itf;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017-07-09.
 */

public interface  OnItemClickListener {
    void onItemClick(int position, View view, RecyclerView.ViewHolder vh);
}
