package com.vary.salaryandcash.base;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.modules.MainActivity;
import com.vary.salaryandcash.modules.itf.MyOnTouchListener;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

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

public  class BaseSupportFragment extends SupportFragment {
    public View mView;
    public TextView app_title;
    public ImageView remove;

    private float mPosX, mPosY, mCurPosX, mCurPosY;
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        // todo,当该Fragment对用户可见时
        mView.findViewById(R.id.navigate_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });

        MyOnTouchListener onTouchListener = new MyOnTouchListener() {
            @Override
            public boolean onTouch(MotionEvent ev) {
                switch (ev.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mPosX = ev.getX();
                        mPosY = ev.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurPosX = ev.getX();
                        mCurPosY = ev.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mCurPosX - mPosX > 10
                                && (Math.abs(mCurPosX - mPosX) > 150)) {
                            //向下滑動
                            Toast.makeText(SalaryApplication.appContext, "向下滑動", Toast.LENGTH_SHORT).show();
                            pop();
                        }
                        break;
                }
                return false;
            }
        };
        ((MainActivity) getActivity()).registerMyOnTouchListener(onTouchListener);
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
        // 设置无动画
        // return new DefaultNoAnimator();
        // 设置自定义动画
        // return new FragmentAnimator(enter,exit,popEnter,popExit);

        // 默认竖向(和安卓5.0以上的动画相同)
//        return super.onCreateFragmentAnimator();
    }

}
