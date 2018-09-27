package com.example.xie.wareapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xie.wareapp.R;
import com.example.xie.wareapp.mvp.model.CityBean;
import com.example.xie.wareapp.ui.activity.CountryCodeActivity;

import java.util.List;

/**
 * Created by 7invensun on 2018/9/27.
 */

public class ShowCodeAdapter extends BaseAdapter {
    private Context context;
    private List<CityBean> mCityList;

    public ShowCodeAdapter(Context activity, List<CityBean> list) {
        mCityList = list;
        context = activity;
    }

    @Override
    public int getCount() {
        return mCityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_item_showcountrycode, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        return null;
    }

    private class ViewHolder {
        private TextView tvAlphaShowcountrycode;
        private TextView tvCityShowcountrycode;
        private TextView tvCodeShowcountrycode;
        private ViewHolder(View view) {
            tvAlphaShowcountrycode = view.findViewById(R.id.tv_alpha_showcountrycode);
            tvCityShowcountrycode = view.findViewById(R.id.tv_city_showcountrycode);
            tvCodeShowcountrycode = view.findViewById(R.id.tv_code_showcountrycode);

        }
    }
}
