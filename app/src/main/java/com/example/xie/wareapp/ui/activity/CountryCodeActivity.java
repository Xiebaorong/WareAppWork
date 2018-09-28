package com.example.xie.wareapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xie.wareapp.R;
import com.example.xie.wareapp.mvp.model.CityBean;
import com.example.xie.wareapp.mvp.presenter.Imp.CountryCodePresenterImp;
import com.example.xie.wareapp.mvp.view.ICountryCodeView;
import com.example.xie.wareapp.ui.adapter.ShowCodeAdapter;
import com.example.xie.wareapp.ui.view.QuickLocationBar;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class CountryCodeActivity extends BaseActivity implements ICountryCodeView, QuickLocationBar.OnTouchLetterChangedListener, AdapterView.OnItemClickListener {
    private static final String TAG = "CountryCodeActivity";
    @BindView(R.id.lv_code_countrycode)
    ListView lvCodeCountryCode;
    @BindView(R.id.qb_alpha_countrycode)
    QuickLocationBar quAlphaCountryCode;
    private ShowCodeAdapter showCodeAdapter;
    private CountryCodePresenterImp countryCodePresenterImp;
    private HashMap<String, Integer> alphaIndexer;
    private Intent intent;
    @Override
    public int getLayout() {
        return R.layout.activity_country_code;
    }

    @Override
    protected void OnActCreate(Bundle savedInstanceState) {
        intent = new Intent();
        countryCodePresenterImp = new CountryCodePresenterImp(this);
        countryCodePresenterImp.isNetworkConnected(this);
    }

    @Override
    protected void initView() {
        quAlphaCountryCode.setOnTouchLitterChangedListener(this);
        lvCodeCountryCode.setOnItemClickListener(this);
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
            alphaIndexer = showCodeAdapter.getCityMap();
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

    @Override
    public void setCityBean(CityBean model) {
        intent.putExtra("CityBean",model);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void touchLetterChanged(String s) {
        int position = 0;
        try {
            position = alphaIndexer.get(s);
        } catch (Exception e) {
            Log.e(TAG, "无:" + s);
        }
        lvCodeCountryCode.setSelection(position);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        countryCodePresenterImp.getCityModel(lvCodeCountryCode,position);
    }

}
