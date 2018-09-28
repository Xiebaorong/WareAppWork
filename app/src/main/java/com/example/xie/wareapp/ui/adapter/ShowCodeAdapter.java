package com.example.xie.wareapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xie.wareapp.R;
import com.example.xie.wareapp.mvp.model.CityBean;
import com.example.xie.wareapp.ui.activity.CountryCodeActivity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 7invensun on 2018/9/27.
 */

public class ShowCodeAdapter extends BaseAdapter {
    private static final String TAG = "ShowCodeAdapter";
    private Context context;
    private List<CityBean> mCityList;
    private HashMap<String, Integer> alphaIndexer;
    private String[] sections;

    public ShowCodeAdapter(Context activity, List<CityBean> list) {
        mCityList = list;
        context = activity;
        alphaIndexer = new HashMap<String, Integer>();
        sections = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String currentStr = list.get(i).getFirstAlpha();
            String previewStr = (i - 1) >= 0 ? list.get(i - 1).getFirstAlpha()
                    : " ";
            if (!previewStr.equals(currentStr)) {//前一个首字母与当前首字母不同时加入HashMap中同时显示该字母
                String name = list.get(i).getFirstAlpha();
                alphaIndexer.put(name, i);
                sections[i] = name;
            }
        }
    }

    public HashMap<String, Integer> getCityMap(){
        return alphaIndexer;
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
        holder.tvCityShowcountrycode.setText(mCityList.get(position).getCountry());
        holder.tvCodeShowcountrycode.setText(mCityList.get(position).getCode());
        String currentStr = mCityList.get(position).getFirstAlpha();
        String previewStr = (position - 1) >= 0 ? mCityList.get(position - 1).getFirstAlpha() : " ";
        if (!previewStr.equals(currentStr)) {
            holder.tvAlphaShowcountrycode.setVisibility(View.VISIBLE);
            holder.tvAlphaShowcountrycode.setText(currentStr);
        } else {
            holder.tvAlphaShowcountrycode.setVisibility(View.GONE);
        }

        return view;
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
