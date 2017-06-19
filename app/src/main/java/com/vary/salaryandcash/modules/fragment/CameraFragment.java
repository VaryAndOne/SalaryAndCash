package com.vary.salaryandcash.modules.fragment;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseSupportFragment;
import com.vary.salaryandcash.modules.CameraActivity;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
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

public class CameraFragment extends SupportFragment {

    private static final String TAG= CameraFragment.class.getSimpleName();
    private Button mTakeButton;
    private Camera mCamera;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private boolean isRecording = false;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_camera, container, false);
        mView.findViewById(R.id.navigate_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        initView();
        return mView;
    }

    private void initView() {
        final Camera.PreviewCallback mPreviewCallbacx=new Camera.PreviewCallback() {
            @Override
            public void onPreviewFrame(final byte[] arg0, Camera arg1) {
                // TODO Auto-generated method stub

                //创建一个上游 Observable：
                Observable<byte[]> observable; observable = Observable.create(new ObservableOnSubscribe<byte[]>() {
                    @Override
                    public void subscribe(ObservableEmitter<byte[]> emitter) throws Exception {
                        emitter.onNext(arg0);
                        emitter.onComplete();
                    }
                });

                //创建一个下游 Observer
                Observer<byte[]> observer = new Observer<byte[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("TAG", "subscribe");
                    }

                    @Override
                    public void onNext(byte[] value) {
                        Log.d("TAG", "" + value);
                        encode(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TAG", "error");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG", "complete");
                    }
                };
                //建立连接
                observable.subscribe(observer);
            }
        };


        mTakeButton=(Button)mView.findViewById(R.id.take_button);

        PackageManager pm=getActivity().getPackageManager();
        boolean hasCamera=pm.hasSystemFeature(PackageManager.FEATURE_CAMERA) ||
                pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT) ||
                Build.VERSION.SDK_INT<Build.VERSION_CODES.GINGERBREAD;
        if(!hasCamera)
            mTakeButton.setEnabled(false);

        mTakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(mCamera!=null)
                {
                    if (isRecording) {
                        mTakeButton.setText("Start");
                        mCamera.setPreviewCallback(null);
                        Toast.makeText(getActivity(), "encode done", Toast.LENGTH_SHORT).show();
                        isRecording = false;
                    }else {
                        mTakeButton.setText("Stop");
                        initial(mCamera.getParameters().getPreviewSize().width,mCamera.getParameters().getPreviewSize().height);
                        mCamera.setPreviewCallback(mPreviewCallbacx);
                        isRecording = true;
                    }
                }
            }
        });


        mSurfaceView=(SurfaceView)mView.findViewById(R.id.surfaceView1);
        SurfaceHolder holder=mSurfaceView.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder arg0) {
                // TODO Auto-generated method stub
                if(mCamera!=null)
                {
                    mCamera.stopPreview();
                    mSurfaceView = null;
                    mSurfaceHolder = null;
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder arg0) {
                // TODO Auto-generated method stub
                try{
                    if(mCamera!=null){
                        mCamera.setPreviewDisplay(arg0);
                        mSurfaceHolder=arg0;
                    }
                }catch(IOException exception){
                    Log.e(TAG, "Error setting up preview display", exception);
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                if(mCamera==null) return;
                Camera.Parameters parameters=mCamera.getParameters();
                parameters.setPreviewSize(640,480);
                parameters.setPictureSize(640,480);
                mCamera.setParameters(parameters);
                try{
                    mCamera.startPreview();
                    mSurfaceHolder=arg0;
                }catch(Exception e){
                    Log.e(TAG, "could not start preview", e);
                    mCamera.release();
                    mCamera=null;
                }
            }
        });
    }

//    @TargetApi(9)
//    @Override
//    public void onResume(){
//        super.onResume();
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.GINGERBREAD){
//            mCamera=Camera.open(0);
//        }else
//        {
//            mCamera=Camera.open();
//        }
//    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.GINGERBREAD){
            mCamera=Camera.open(0);
        }else
        {
            mCamera=Camera.open();
        }
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        flush();
        close();
        if(mCamera!=null){
            mCamera.release();
            mCamera=null;
        }
    }

//    @Override
//    public void onPause(){
//        super.onPause();
//        flush();
//        close();
//        if(mCamera!=null){
//            mCamera.release();
//            mCamera=null;
//        }
//    }

    //JNI
    public native int initial(int width,int height);
    public native int encode(byte[] yuvimage);
    public native int flush();
    public native int close();

}
