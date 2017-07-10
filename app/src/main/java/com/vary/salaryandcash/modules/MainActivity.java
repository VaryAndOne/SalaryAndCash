package com.vary.salaryandcash.modules;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.modules.itf.MyOnTouchListener;
import com.vary.salaryandcash.modules.fragment.MainFragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportActivity;

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
public class MainActivity extends SupportActivity {
    private MyHandler mHandler = new MyHandler(this);
    private ProgressBar mNetLoadingBar;
//    private SplashFragment splashFragment;
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        final ViewStub mainLayout = (ViewStub) findViewById(R.id.content_viewstub);
//        splashFragment = SplashFragment.newInstance();
        if (savedInstanceState == null) {
            loadRootFragment(R.id.container, MainFragment.newInstance());
        }

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                SystemClock.sleep(1000);
//                mHandler.sendEmptyMessage(0);
//            }
//        }).start();

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                View mainView = mainLayout.inflate();
//                initView(mainView);
            }
        });
        
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                mHandler.postDelayed(new DelayRunnableImpl(),1000);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

//    private void initView(View pMainView) {
//        if (pMainView != null) {
//            mNetLoadingBar = (ProgressBar) pMainView.findViewById(R.id.progressbar);
//            mNetLoadingBar.setVisibility(View.VISIBLE);
//        }
//    }

    private static class MyHandler extends Handler {
        private WeakReference<MainActivity> wRef;

        private MyHandler(MainActivity pActivity) {
            this.wRef = new WeakReference<MainActivity>(pActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity mainActivity = wRef.get();
            if (mainActivity != null) {
                mainActivity.mNetLoadingBar.setVisibility(View.GONE);
            }
        }
    }

    private class DelayRunnableImpl implements Runnable {
        @Override
        public void run() {
//            splashFragment.start(MainFragment.newInstance());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void onBackPressedSupport() {

        Fragment topFragment = getTopFragment();

//        // 主页的Fragment
//        if (topFragment instanceof BaseMainFragment) {
//            mNavigationView.setCheckedItem(R.id.nav_home);
//        }

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                finish();
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private ArrayList<MyOnTouchListener> onTouchListeners = new ArrayList<MyOnTouchListener>(
            10);

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyOnTouchListener listener : onTouchListeners) {
            if(listener != null) {
                listener.onTouch(ev);
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    public void registerMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.add(myOnTouchListener);
    }
    public void unregisterMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.remove(myOnTouchListener) ;
    }


}
