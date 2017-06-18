package com.vary.salaryandcash.modules;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseActivity;
import com.vary.salaryandcash.modules.widget.media.AndroidMediaController;
import com.vary.salaryandcash.modules.widget.media.IjkVideoView;
import com.vary.salaryandcash.mvp.model.Salary;

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
public class VideoPlayerActivity extends BaseActivity {
    IjkVideoView videoView;

    public static final String CAKE = "cake";
    @Override
    protected int getContentView() {
        return R.layout.activity_videoplayer;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        Salary cake = (Salary) intent.getSerializableExtra(CAKE);

        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        videoView = (IjkVideoView) findViewById(R.id.ijkPlayer);
        AndroidMediaController controller = new AndroidMediaController(this, false);
        videoView.setMediaController(controller);
//        String url = "http://60.206.109.44/hls/20100101_105339.m3u8";
        String url = "https://wdl.wallstreetcn.com/41aae4d2-390a-48ff-9230-ee865552e72d";
//        String url = cake.getImage();
        videoView.setVideoURI(Uri.parse(url));
        videoView.start();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPause() {
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