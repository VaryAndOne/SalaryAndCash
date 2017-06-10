package com.vary.salaryandcash.modules;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewStub;
import android.widget.ProgressBar;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.modules.fragment.MainFragment;
import com.vary.salaryandcash.modules.fragment.SplashFragment;

import java.lang.ref.WeakReference;

import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {
    private MyHandler mHandler = new MyHandler(this);
    private ProgressBar mNetLoadingBar;
    private SplashFragment splashFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        splashFragment = new SplashFragment();
        final ViewStub mainLayout = (ViewStub) findViewById(R.id.content_viewstub);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.container, splashFragment);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                mHandler.sendEmptyMessage(0);
            }
        }).start();

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                View mainView = mainLayout.inflate();
                initView(mainView);
            }
        });
        
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                mHandler.postDelayed(new DelayRunnableImpl(),2000);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView(View pMainView) {
        if (pMainView != null) {
            mNetLoadingBar = (ProgressBar) pMainView.findViewById(R.id.progressbar);
            mNetLoadingBar.setVisibility(View.VISIBLE);
        }
    }

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
              splashFragment.start(new MainFragment());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
