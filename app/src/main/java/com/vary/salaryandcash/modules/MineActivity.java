package com.vary.salaryandcash.modules;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.vary.salaryandcash.R;


/**
 * Created by Administrator on 2017-06-01.
 */

public class MineActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ((ImageView)findViewById(R.id.iv_icon)).setImageBitmap(bmp);
        ((ImageView)findViewById(R.id.iv_icon1)).setImageBitmap(bmp1);
        ((ImageView)findViewById(R.id.iv_icon2)).setImageBitmap(bmp2);
    }

    public static Bitmap createVideoThumbnail(String filePath, int kind) {
        return ThumbnailUtils.createVideoThumbnail(filePath, kind);
    }
    Bitmap bmp = createVideoThumbnail(Environment.getExternalStorageDirectory().getPath()+"/recordtest"+"/20173283240.mp4", MediaStore.Video.Thumbnails.MICRO_KIND);
    Bitmap bmp1 = createVideoThumbnail(Environment.getExternalStorageDirectory().getPath()+"/gifshow"+"/20170302_060751.mp4", MediaStore.Video.Thumbnails.MICRO_KIND);
    Bitmap bmp2 = createVideoThumbnail(Environment.getExternalStorageDirectory().getPath()+"/gifshow"+"/20100101_105339.mp4", MediaStore.Video.Thumbnails.MICRO_KIND);

}
