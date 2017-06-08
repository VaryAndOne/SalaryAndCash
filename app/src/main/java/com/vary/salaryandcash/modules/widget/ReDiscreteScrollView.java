package com.vary.salaryandcash.modules.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.yarolegovich.discretescrollview.DiscreteScrollView;

/**
 * Created by Administrator on 2017-06-08.
 */

public class ReDiscreteScrollView extends DiscreteScrollView {
    public ReDiscreteScrollView(Context context) {
        super(context);
    }

    public ReDiscreteScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReDiscreteScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
