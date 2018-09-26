package com.example.xie.wareapp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xie.wareapp.R;

public class CountryCodeActivity extends BaseActivity {
    private static final String TAG = "CountryCodeActivity";
    @Override
    public int getLayout() {
        return R.layout.activity_country_code;
    }

    @Override
    protected void OnActCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int switchoverAnimationIn() {
        return RIGHT;
    }

    @Override
    protected int switchoverAnimationOut() {
        return LEFT;
    }
}
