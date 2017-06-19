package com.vary.salaryandcash.modules.fragment;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseSupportFragment;
import com.vary.salaryandcash.modules.widget.ViewServer;
import com.vary.salaryandcash.modules.widget.media.AndroidMediaController;
import com.vary.salaryandcash.modules.widget.media.IjkVideoView;

import me.yokeyword.fragmentation.SupportFragment;
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

public class VideoPlayerFragment extends SupportFragment {
    IjkVideoView videoView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.activity_videoplayer, container, false);
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
        String url = "http://vary.oss-cn-beijing.aliyuncs.com/video/20100101_105339.m3u8";
//        String url = "https://wdl.wallstreetcn.com/41aae4d2-390a-48ff-9230-ee865552e72d";
//        String url = cake.getImage();
        videoView.setVideoURI(Uri.parse(url));
        videoView.start();
        return mView;
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
