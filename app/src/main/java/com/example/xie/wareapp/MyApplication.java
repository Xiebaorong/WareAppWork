package com.example.xie.wareapp;

import android.app.Application;

import com.example.xie.wareapp.utils.OkhttpUtil;
import com.example.xie.wareapp.utils.SharedPreferencesUtil;

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SharedPreferencesUtil.getInstance().initSharedPreferences(instance);
        OkhttpUtil.getInstance().initOkHttp();
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
