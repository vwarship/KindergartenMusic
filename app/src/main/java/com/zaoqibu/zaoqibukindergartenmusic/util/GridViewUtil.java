package com.zaoqibu.zaoqibukindergartenmusic.util;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by vwarship on 2014/11/27.
 */
public class GridViewUtil {
    private static int PORTRAIT_GRIDVIEW_COLUMN_NUMBER = 2;

    static public int calcItemWidth(Activity activity) {
        int colNum = PORTRAIT_GRIDVIEW_COLUMN_NUMBER;

        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (colNum-1+2)*8,
                activity.getResources().getDisplayMetrics());

        final int screenWidth = getScreenWidth(activity);
        return (int) ( (screenWidth-padding) / colNum);
    }

    static private int getScreenWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.widthPixels;
    }
}
