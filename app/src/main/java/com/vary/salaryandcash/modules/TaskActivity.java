package com.vary.salaryandcash.modules;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;

/**
 * Created by Administrator on 2017-06-01.
 */

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ImageView iv_icon = ((ImageView)findViewById(R.id.iv_icon));
        ((ImageView)findViewById(R.id.iv_icon1)).setImageBitmap(bmp1);
        ((ImageView)findViewById(R.id.iv_icon2)).setImageBitmap(bmp2);
        iv_icon.setImageBitmap(bmp);
        iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskActivity.this, VideoPlayerActivity.class);
                startActivity(intent);
            }
        });
    }

    public static Bitmap createVideoThumbnail(String filePath, int kind) {
        return ThumbnailUtils.createVideoThumbnail(filePath, kind);
    }
    Bitmap bmp = createVideoThumbnail(Environment.getExternalStorageDirectory().getPath()+"/recordtest"+"/20173283240.mp4", MediaStore.Video.Thumbnails.MICRO_KIND);
    Bitmap bmp1 = createVideoThumbnail(Environment.getExternalStorageDirectory().getPath()+"/gifshow"+"/20170302_060751.mp4", MediaStore.Video.Thumbnails.MICRO_KIND);
    Bitmap bmp2 = createVideoThumbnail(Environment.getExternalStorageDirectory().getPath()+"/gifshow"+"/20100101_105339.mp4", MediaStore.Video.Thumbnails.MICRO_KIND);
}
