package com.example.xie.wareapp.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 7invensun on 2018/9/27.
 */

public class CityBean implements Parcelable {
    private String code;
    private String country;
    private String firstAlpha;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstAlpha() {
        return firstAlpha;
    }

    public void setFirstAlpha(String firstAlpha) {
        this.firstAlpha = firstAlpha;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.country);
        dest.writeString(this.firstAlpha);
    }

    public CityBean() {
    }

    protected CityBean(Parcel in) {
        this.code = in.readString();
        this.country = in.readString();
        this.firstAlpha = in.readString();
    }

    public static final Parcelable.Creator<CityBean> CREATOR = new Parcelable.Creator<CityBean>() {
        @Override
        public CityBean createFromParcel(Parcel source) {
            return new CityBean(source);
        }

        @Override
        public CityBean[] newArray(int size) {
            return new CityBean[size];
        }
    };
}
