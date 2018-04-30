package csc780.sfsu.edu.textscheduler.util;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;

import csc780.sfsu.edu.textscheduler.R;

public class ColorUtil {

    public static int getMaterialColor(Resources resources, int index) {
        TypedArray colors = resources.obtainTypedArray(R.array.mdcolor_300);

        final int returnColor = colors.getColor(index % colors.length(), Color.BLACK);

        colors.recycle();
        return returnColor;
    }

}
