package com.example.xie.wareapp.mvp.view;

import com.example.xie.wareapp.mvp.model.CityBean;

import java.util.List;

/**
 * Created by 7invensun on 2018/9/27.
 */

public interface ICountryCodeView {
    void getCountryCodeList(List<CityBean> list);

    void networkConnectedState(boolean connected);

}
