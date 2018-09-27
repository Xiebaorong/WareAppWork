package com.example.xie.wareapp.mvp.presenter.Imp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.xie.wareapp.R;
import com.example.xie.wareapp.mvp.model.CityBean;
import com.example.xie.wareapp.mvp.presenter.ICountryCodePresenter;
import com.example.xie.wareapp.mvp.view.ICountryCodeView;
import com.example.xie.wareapp.ui.activity.CountryCodeActivity;
import com.example.xie.wareapp.utils.CountryCodeUtil;
import com.example.xie.wareapp.utils.OkhttpUtil;

import java.util.List;

/**
 * Created by 7invensun on 2018/9/27.
 */

public class CountryCodePresenterImp implements ICountryCodePresenter {
    private static final String TAG = "CountryCodePresenterImp";
    private ICountryCodeView countryCodeView;

    public CountryCodePresenterImp(CountryCodeActivity view) {
        countryCodeView = view;
    }

    @Override
    public void isNetworkConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null) {
            boolean connected = manager.getActiveNetworkInfo().isConnected();
            countryCodeView.networkConnectedState(connected);
        } else {
            countryCodeView.networkConnectedState(false);
        }
    }

    @Override
    public void findCountrtCode(Context context) {
        List<CityBean> list = CountryCodeUtil.GetCountryZipCode(context);
        countryCodeView.getCountryCodeList(list);
    }

}
