package com.vary.salaryandcash.modules;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.base.BaseActivity;
import com.vary.salaryandcash.di.components.DaggerSalaryComponent;
import com.vary.salaryandcash.di.module.SalaryModule;
import com.vary.salaryandcash.modules.adapter.SalaryAdapter;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.presenter.SalaryPresenter;
import com.vary.salaryandcash.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by Administrator on 2017-06-01.
 */

public class TaskActivity extends BaseActivity implements MainView{

    @Inject protected SalaryPresenter mPresenter;
    @Bind(R.id.cake_list) protected RecyclerView mCakeList;
    private SalaryAdapter mCakeAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_task;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initializeList();
        mPresenter.getSalaries();
    }

    private void initializeList() {
        mCakeList.setHasFixedSize(true);
        mCakeList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mCakeAdapter = new SalaryAdapter(getLayoutInflater());
//        mCakeAdapter.setCakeClickListener(mCakeClickListener);
        mCakeList.setAdapter(mCakeAdapter);
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerSalaryComponent.builder()
                .applicationComponent(getApplicationComponent())
                .salaryModule(new SalaryModule(this))
                .build().inject(this);
    }

    @Override
    public void onSalaryLoaded(List<Salary> salaries) {
        mCakeAdapter.addCakes(salaries);
    }

    @Override
    public void onShowDialog(String s) {
        showDialog(s);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String s) {
        Toast.makeText(TaskActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClearItems() {
        mCakeAdapter.clearCakes();
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_task);
//        ImageView iv_icon = ((ImageView)findViewById(R.id.iv_icon));
//        ((ImageView)findViewById(R.id.iv_icon1)).setImageBitmap(bmp1);
//        ((ImageView)findViewById(R.id.iv_icon2)).setImageBitmap(bmp2);
//        iv_icon.setImageBitmap(bmp);
//        iv_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(TaskActivity.this, VideoPlayerActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//    public static Bitmap createVideoThumbnail(String filePath, int kind) {
//        return ThumbnailUtils.createVideoThumbnail(filePath, kind);
//    }
//    Bitmap bmp = createVideoThumbnail(Environment.getExternalStorageDirectory().getPath()+"/recordtest"+"/20173283240.mp4", MediaStore.Video.Thumbnails.MICRO_KIND);
//    Bitmap bmp1 = createVideoThumbnail(Environment.getExternalStorageDirectory().getPath()+"/gifshow"+"/20170302_060751.mp4", MediaStore.Video.Thumbnails.MICRO_KIND);
//    Bitmap bmp2 = createVideoThumbnail(Environment.getExternalStorageDirectory().getPath()+"/gifshow"+"/20100101_105339.mp4", MediaStore.Video.Thumbnails.MICRO_KIND);
}
