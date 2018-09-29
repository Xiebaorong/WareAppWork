package com.example.xie.wareapp;

import android.app.Application;

import com.example.xie.wareapp.utils.OkhttpUtil;
import com.example.xie.wareapp.utils.SharedPreferencesUtil;
import com.mob.MobSDK;

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MobSDK.init(this);
        SharedPreferencesUtil.getInstance().initSharedPreferences(instance);
        OkhttpUtil.getInstance().initOkHttp();
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
