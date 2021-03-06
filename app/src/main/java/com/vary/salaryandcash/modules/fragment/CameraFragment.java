package com.vary.salaryandcash.modules.fragment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sackcentury.shinebuttonlib.ShineButton;
import com.vary.salaryandcash.R;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import static com.vary.salaryandcash.R.id.camera;

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
                Observable<byte[]>  observable = Observable.create(new ObservableOnSubscribe<byte[]>() {
                    @Override
                    public void subscribe( ObservableEmitter<byte[]> emitter) throws Exception {

                        if (arg0 != null) {
                            emitter.onNext(arg0);
                        }
//                        emitter.onComplete();
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

                    }

                    @Override
                    public void onComplete() {

                    }
                };
                //建立连接
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);

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

                int rotation = getDisplayOrientation();
                mCamera.setDisplayOrientation(rotation);

                Camera.Parameters parameters=mCamera.getParameters();

                parameters.setRotation(rotation);

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

    public int getDisplayOrientation() {

        android.hardware.Camera.CameraInfo camInfo =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_BACK, camInfo);
        Display display = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result = (camInfo.orientation - degrees + 360) % 360;
        return result;
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.GINGERBREAD){
            mCamera=Camera.open(0);
//            mCamera.setDisplayOrientation(90);
        }else
        {
            mCamera=Camera.open();
//            mCamera.setDisplayOrientation(90);
        }
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
//        flush();
//        close();
        if(mCamera!=null){
            mCamera.release();
            mCamera=null;
        }
    }

    //JNI
    public native int initial(int width,int height);
    public native int encode(byte[] yuvimage);
    public native int flush();
    public native int close();


}
