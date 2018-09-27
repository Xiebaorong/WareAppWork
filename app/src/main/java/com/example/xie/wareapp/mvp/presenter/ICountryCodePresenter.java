package com.example.xie.wareapp.mvp.presenter;

import android.content.Context;

/**
 * Created by 7invensun on 2018/9/27.
 */

public interface ICountryCodePresenter {
    void isNetworkConnected(Context context);

    void findCountrtCode(Context context);
}
