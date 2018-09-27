package com.example.xie.wareapp.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.example.xie.wareapp.R;
import com.example.xie.wareapp.mvp.model.CityBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 7invensun on 2018/9/27.
 */

public class CountryCodeUtil {
    public static List<CityBean> GetCountryZipCode(Context Context) {
        List<CityBean> list = new ArrayList<>();
        String CountryID = "";
        String CountryZipCode = "";
        TelephonyManager manager = (TelephonyManager) Context.getSystemService(Context.TELEPHONY_SERVICE);
        //getNetworkCountryIso
        CountryID = manager.getSimCountryIso().toUpperCase();
        String[] rl = Context.getResources().getStringArray(R.array.CountryCodes);
        for (int i = 0; i < rl.length; i++) {
            CityBean cityBean = new CityBean();

            String[] g = rl[i].split(",");
            if (g[1].trim().equals(CountryID.trim())) {
                CountryZipCode = g[0];
                break;
            }
            //g[0]数字  g[1]首字母 g[2]汉字
            cityBean.setCode(g[0]);
            cityBean.setFirstAlpha(g[1]);
            cityBean.setCountry(g[2]);
            list.add(cityBean);
        }
        return list;
    }

}
