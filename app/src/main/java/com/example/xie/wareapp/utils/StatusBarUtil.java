package com.example.xie.wareapp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;


public class StatusBarUtil {

    public static void setWindowStatusBarColor(Activity activity, int colorResId){
        try {
            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setWindowStatusBarColor(Dialog dialog, int colorResId){
        try {
            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
                Window window = dialog.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(dialog.getContext().getResources().getColor(colorResId));
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }
}
