package com.example.xie.wareapp.mvp.presenter;

/**
 * Created by 7invensun on 2018/9/28.
 */

public interface IRegisterPresenter {
    void isPhoneFormat(String phone);

    void sendVerificationCode(String code, String phone);
}
