package com.example.xie.wareapp.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.xie.wareapp.R;
import com.example.xie.wareapp.mvp.model.CityBean;
import com.example.xie.wareapp.mvp.presenter.Imp.CountryCodePresenterImp;
import com.example.xie.wareapp.mvp.view.ICountryCodeView;
import com.example.xie.wareapp.ui.adapter.ShowCodeAdapter;
import com.example.xie.wareapp.ui.view.QuickLocationBar;

import java.util.List;

import butterknife.BindView;

public class CountryCodeActivity extends BaseActivity implements ICountryCodeView {
    private static final String TAG = "CountryCodeActivity";
    @BindView(R.id.lv_code_countrycode)
    ListView lvCodeCountryCode;
    @BindView(R.id.qb_alpha_countrycode)
    QuickLocationBar quAlphaCountryCode;
    private ShowCodeAdapter showCodeAdapter;
    private CountryCodePresenterImp countryCodePresenterImp;

    @Override
    public int getLayout() {
        return R.layout.activity_country_code;
    }

    @Override
    protected void OnActCreate(Bundle savedInstanceState) {
        countryCodePresenterImp = new CountryCodePresenterImp(this);
        countryCodePresenterImp.isNetworkConnected(this);

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

    @Override
    public void getCountryCodeList(List<CityBean> list) {
        if (list.size() > 0) {
            disMissDialog();
            showCodeAdapter = new ShowCodeAdapter(this, list);
            lvCodeCountryCode.setAdapter(showCodeAdapter);
        }
    }

    @Override
    public void networkConnectedState(boolean connected) {
        if (connected) {
            showProgressDialog("加载中。。。");
            countryCodePresenterImp.findCountrtCode(this);
        } else {
            countryCodePresenterImp.findCountrtCode(this);
        }
    }
}
