package com.byq.applib;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

public class StatusUtil {
    public static void setStatusBarColor(Activity activity,int color) {
        activity.getWindow().setStatusBarColor(color);
    }

    public static void setWhiteStatusBar(Activity activity) {
        activity.getWindow().setStatusBarColor(Color.WHITE);
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
    }

    public static void setStatusTextColorToBlack(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
    }
}
