package com.vary.salaryandcash.modules;

/**
 * Created by Administrator on 2017-07-11.
 */

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaCodec;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;

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

import java.io.IOException;

public class PlayerActivity extends AppCompatActivity implements ManifestFetcher.ManifestCallback<HlsPlaylist>,ExoPlayer.Listener
        ,HlsSampleSource.EventListener,AudioManager.OnAudioFocusChangeListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_player);
        surface_view = (SurfaceView) findViewById(R.id.surface_view);
        player = ExoPlayer.Factory.newInstance(2);
        playerControl = new PlayerControl(player);
        video_url = "http://vary.oss-cn-beijing.aliyuncs.com/video/20100101_105339.m3u8";
        am = (AudioManager) this.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        mainHandler = new Handler();
        userAgent = Util.getUserAgent(this,"MainActivity");
        HlsPlaylistParser parser = new HlsPlaylistParser();
        playListFetcher = new ManifestFetcher<>(video_url,new DefaultUriDataSource(this,userAgent),parser);
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
        DataSource dataSource = new DefaultUriDataSource(this,bandwidthMeter,userAgent);
        HlsChunkSource chunkSource = new HlsChunkSource(true,dataSource,manifest, DefaultHlsTrackSelector.newDefaultInstance(this),bandwidthMeter
                ,timestampAdjusterProvider,HlsChunkSource.ADAPTIVE_MODE_SPLICE);
        HlsSampleSource sampleSource = new HlsSampleSource(chunkSource,loadControl,
                MAIN_BUFFER_SEGMENTS*BUFFER_SEGMENT_SIZE,mainHandler,this,TYPE_VIDEO);
        MediaCodecVideoTrackRenderer videoRenderer = new MediaCodecVideoTrackRenderer(this,sampleSource, MediaCodecSelector.DEFAULT, MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT);
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
        return  AudioManager.AUDIOFOCUS_REQUEST_GRANTED == am.requestAudioFocus(PlayerActivity.this,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);
    }


    @Override
    public void onSingleManifestError(IOException e) {

    }

    @Override
    public void onAudioFocusChange(int i) {

    }
}