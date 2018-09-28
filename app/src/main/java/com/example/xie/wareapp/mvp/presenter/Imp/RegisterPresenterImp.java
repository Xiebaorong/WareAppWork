package com.example.xie.wareapp.mvp.presenter.Imp;

import android.text.TextUtils;
import android.util.Log;

import com.example.xie.wareapp.mvp.presenter.IRegisterPresenter;
import com.example.xie.wareapp.mvp.view.IRegisterView;
import com.example.xie.wareapp.ui.activity.RegisterActivity;
import com.example.xie.wareapp.utils.FormatExamineUtil;

/**
 * Created by 7invensun on 2018/9/28.
 */

public class RegisterPresenterImp implements IRegisterPresenter {
    private static final String TAG = "RegisterPresenterImp";
    private IRegisterView registerView;
    public RegisterPresenterImp(RegisterActivity view) {
        registerView = view;
    }

    @Override
    public void isPhoneFormat(String phone) {
        boolean isMobileNO = FormatExamineUtil.isMobileNO(phone);
        registerView.isSendVerificationCode(isMobileNO,phone);
    }

    @Override
    public void sendVerificationCode(String code, String phone) {
        Log.e(TAG, "sendVerificationCode: "+code+"---"+phone );
    }

}
