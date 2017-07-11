package com.vary.salaryandcash.modules.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaCodec;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.google.android.exoplayer.DefaultLoadControl;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.LoadControl;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.hls.DefaultHlsTrackSelector;
import com.google.android.exoplayer.hls.HlsChunkSource;
import com.google.android.exoplayer.hls.HlsPlaylist;
import com.google.android.exoplayer.hls.HlsPlaylistParser;
import com.google.android.exoplayer.hls.HlsSampleSource;
import com.google.android.exoplayer.hls.PtsTimestampAdjusterProvider;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.util.ManifestFetcher;
import com.google.android.exoplayer.util.PlayerControl;
import com.google.android.exoplayer.util.Util;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.base.BaseSupportFragment;

import java.io.IOException;

import me.yokeyword.fragmentation.SupportFragment;

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

public class PlayerFragment extends BaseSupportFragment implements ManifestFetcher.ManifestCallback<HlsPlaylist>,ExoPlayer.Listener
        ,HlsSampleSource.EventListener,AudioManager.OnAudioFocusChangeListener{

    private SurfaceView surface_view;
    private ExoPlayer player;
    private PlayerControl playerControl;
    private String video_url;
    private Handler mainHandler;
    private AudioManager am;
    private String userAgent;
    private ManifestFetcher<HlsPlaylist> playListFetcher;
    private static final int BUFFER_SEGMENT_SIZE = 64 * 1024;
    private static final int MAIN_BUFFER_SEGMENTS = 254;
    private static final int TYPE_VIDEO = 0;
    private TrackRenderer videoRenderer;
    private MediaCodecAudioTrackRenderer audioRenderer;

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

        surface_view = (SurfaceView) mView.findViewById(R.id.surface_view);
        player = ExoPlayer.Factory.newInstance(2);
        playerControl = new PlayerControl(player);
        video_url = "http://vary.oss-cn-beijing.aliyuncs.com/video/20100101_105339.m3u8";
        am = (AudioManager) SalaryApplication.appContext.getSystemService(Context.AUDIO_SERVICE);
        mainHandler = new Handler();
        userAgent = Util.getUserAgent(getActivity(),"MainActivity");
        HlsPlaylistParser parser = new HlsPlaylistParser();
        playListFetcher = new ManifestFetcher<>(video_url,new DefaultUriDataSource(getActivity(),userAgent),parser);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);

            }
        },1500);
    }

    @Override
    public int getBaseView() {
        return R.layout.fragment_player;
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            shadow.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        playListFetcher.singleLoad(mainHandler.getLooper(), this);
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onPlayWhenReadyCommitted() {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onLoadStarted(int sourceId, long length, int type, int trigger, Format format, long mediaStartTimeMs, long mediaEndTimeMs) {

    }

    @Override
    public void onLoadCompleted(int sourceId, long bytesLoaded, int type, int trigger, Format format, long mediaStartTimeMs, long mediaEndTimeMs, long elapsedRealtimeMs, long loadDurationMs) {

    }

    @Override
    public void onLoadCanceled(int sourceId, long bytesLoaded) {

    }

    @Override
    public void onLoadError(int sourceId, IOException e) {

    }

    @Override
    public void onUpstreamDiscarded(int sourceId, long mediaStartTimeMs, long mediaEndTimeMs) {

    }

    @Override
    public void onDownstreamFormatChanged(int sourceId, Format format, int trigger, long mediaTimeMs) {

    }

    @Override
    public void onSingleManifest(HlsPlaylist manifest) {
        LoadControl loadControl = new DefaultLoadControl(new DefaultAllocator(BUFFER_SEGMENT_SIZE));
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        PtsTimestampAdjusterProvider timestampAdjusterProvider = new PtsTimestampAdjusterProvider();
        boolean haveSubtitles = false;
        boolean haveAudios = false;
        DataSource dataSource = new DefaultUriDataSource(getActivity(),bandwidthMeter,userAgent);
        HlsChunkSource chunkSource = new HlsChunkSource(true,dataSource,manifest, DefaultHlsTrackSelector.newDefaultInstance(getActivity()),bandwidthMeter
                ,timestampAdjusterProvider,HlsChunkSource.ADAPTIVE_MODE_SPLICE);
        HlsSampleSource sampleSource = new HlsSampleSource(chunkSource,loadControl,
                MAIN_BUFFER_SEGMENTS*BUFFER_SEGMENT_SIZE,mainHandler,this,TYPE_VIDEO);
        MediaCodecVideoTrackRenderer videoRenderer = new MediaCodecVideoTrackRenderer(getActivity(),sampleSource, MediaCodecSelector.DEFAULT, MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT);
        MediaCodecAudioTrackRenderer audioRenderer = new MediaCodecAudioTrackRenderer(sampleSource,MediaCodecSelector.DEFAULT);
        this.videoRenderer = videoRenderer;
        this.audioRenderer = audioRenderer;
        pushSuface(false);
        player.prepare(videoRenderer,audioRenderer);
        player.addListener(this);
        if (requestFocus()){
            player.setPlayWhenReady(true);
        }

    }

    private void pushSuface(boolean blockForSurfacePush) {
        if (videoRenderer == null){
            return;
        }
        if (blockForSurfacePush){
            player.blockingSendMessage(videoRenderer,MediaCodecVideoTrackRenderer.MSG_SET_SURFACE,surface_view.getHolder().getSurface());
        }else{
            player.sendMessage(videoRenderer,MediaCodecVideoTrackRenderer.MSG_SET_SURFACE,surface_view.getHolder().getSurface());
        }
    }

    private boolean requestFocus() {
        return  AudioManager.AUDIOFOCUS_REQUEST_GRANTED == am.requestAudioFocus(this,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);
    }

    @Override
    public void onSingleManifestError(IOException e) {

    }

    @Override
    public void onAudioFocusChange(int i) {

    }
}
