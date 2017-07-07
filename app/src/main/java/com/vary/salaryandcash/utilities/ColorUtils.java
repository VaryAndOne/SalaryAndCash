package com.vary.salaryandcash.utilities;

import android.content.Context;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.modules.fragment.LeftFragment;
import com.vary.salaryandcash.modules.fragment.MainFragment;

import java.util.Random;

/**
 * Created by Administrator on 2017-07-07.
 */

public class ColorUtils {
    public static Random myFragment;
    public static synchronized Random getInstance() {
        if (myFragment == null) {
            synchronized (Random.class) {
                if (myFragment == null) {
                    myFragment = new Random();
                }
            }
        }
        return myFragment;
    }
    public static int[] CustomizedColors() {
        int[] customizedColors = new int[]{R.color.green,R.color.orange,R.color.blue,R.color.red,
                R.color.yellow,R.color.mred,R.color.purple,R.color.colorAccent};
        return customizedColors;
    }
}
