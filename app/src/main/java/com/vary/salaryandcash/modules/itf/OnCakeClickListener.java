package com.vary.salaryandcash.modules.itf;

import android.view.View;

import com.vary.salaryandcash.mvp.model.Salary;

/**
 * Created by Administrator on 2017-07-04.
 */

public interface OnCakeClickListener {

    void onClick(View v, Salary cake, int position);
}
