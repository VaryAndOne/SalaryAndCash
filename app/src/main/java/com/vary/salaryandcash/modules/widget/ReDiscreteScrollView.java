package com.vary.salaryandcash.modules.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.yarolegovich.discretescrollview.DiscreteScrollView;

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
