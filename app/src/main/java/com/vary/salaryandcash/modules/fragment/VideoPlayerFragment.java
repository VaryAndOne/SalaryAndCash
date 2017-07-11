package com.vary.salaryandcash.modules.fragment;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.base.BaseSupportFragment;
import com.vary.salaryandcash.modules.itf.ShowImageListener;
import com.vary.salaryandcash.modules.widget.media.AndroidMediaController;
import com.vary.salaryandcash.modules.widget.media.IjkVideoView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

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

public class VideoPlayerFragment extends BaseSupportFragment {
    IjkVideoView videoView;
    public static VideoPlayerFragment myFragment;
    public static synchronized VideoPlayerFragment getInstance(String image){
        if (myFragment == null){
            synchronized (VideoPlayerFragment.class){
                if (myFragment == null){
                    myFragment = new VideoPlayerFragment();
                }
            }
        }
        Bundle args = new Bundle();
        args.putString("image",image);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void initMainView(View pMainView) {
        if (pMainView != null) {
            shadow = (ImageView) pMainView.findViewById(R.id.iv_shadow);
            shadow.setVisibility(View.VISIBLE);
        }
    }
    private ImageView shadow;
    @Override
    protected void initView() {

        final ViewStub mainLayout = (ViewStub) mView.findViewById(R.id.content_viewstub);
        getActivity().getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                View mainView = mainLayout.inflate();
                initMainView(mainView);
            }
        });
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        videoView = (IjkVideoView) mView.findViewById(R.id.ijkPlayer);
        mView.findViewById(R.id.navigate_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        AndroidMediaController controller = new AndroidMediaController(getActivity(), false);
        videoView.setMediaController(controller);
//        String url = "https://wdl.wallstreetcn.com/41aae4d2-390a-48ff-9230-ee865552e72d";
        String getImage = (String) myFragment.getArguments().get("image");
        videoView.setVideoURI(Uri.parse(getImage));
        videoView.start();
        videoView.setShowImageListener(new ShowImageListener() {
            @Override
            public void isShow(boolean isShow) {
                shadow.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getBaseView() {
        return R.layout.fragment_videoplayer;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        IjkMediaPlayer.native_profileEnd();
    }

    @Override
    public void onResume() {
        super.onResume();
        videoView.resume();
    }

}
