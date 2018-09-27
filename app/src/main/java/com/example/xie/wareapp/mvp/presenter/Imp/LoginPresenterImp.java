package com.example.xie.wareapp.mvp.presenter.Imp;

import android.util.Log;


import com.example.xie.wareapp.callback.LoadCallBack;
import com.example.xie.wareapp.mvp.model.User;
import com.example.xie.wareapp.mvp.presenter.ILoginPresenter;
import com.example.xie.wareapp.mvp.view.ILoginView;
import com.example.xie.wareapp.ui.activity.LoginActivity;
import com.example.xie.wareapp.utils.AppUrl;
import com.example.xie.wareapp.utils.OkhttpUtil;
import com.example.xie.wareapp.utils.SharedPreferencesUtil;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by 7invensun on 2018/9/19.
 */

public class LoginPresenterImp implements ILoginPresenter {
    private static final String TAG = "LoginPresenterCompl";
    ILoginView loginView;
    private boolean isSaveUserMsg;

    public LoginPresenterImp(LoginActivity view) {
        loginView = view;
    }

    @Override
    public void isSaveMag(boolean isSave) {
        isSaveUserMsg = isSave;
    }

    @Override
    public void loginApp() {
        boolean firstLogin = SharedPreferencesUtil.getInstance().isFirstLogin();
        Log.e(TAG, "loginApp: " + firstLogin);

        Map<String, String> params = new HashMap<>();
        params.put("username", loginView.getUsername());
        params.put("pass", loginView.getPassword());

        OkhttpUtil.getInstance().newRequest(AppUrl.LOGINAPP, OkhttpUtil.HttpMethodType.GET, params, new LoadCallBack<String>(loginView.getContext()) {

            @Override
            public void onSuccess(Call call, Response response, String result) {
                Type type = new TypeToken<User>() {
                }.getType();
//                User user = (User) GsonUtil.jsontoBean(result, type);
                if (loginView.getUsername().equals("123") && ("123").equals(loginView.getPassword())) {
                    isLoginApp(true, 1);
                } else {
                    isLoginApp(false, 0);
                }

            }

            @Override
            public void onError(Call call, int statusCode, Exception e) {

            }
        });

    }



    public void isLoginApp(boolean isLoginSuccess, int code) {
        if (isLoginSuccess) {
            if (isSaveUserMsg) {
                Log.e(TAG, "isLoginApp: 保存数据");
                SharedPreferencesUtil.getInstance().saveUserMsg(loginView.getUsername(), loginView.getPassword(), isSaveUserMsg);
            } else {
                Log.e(TAG, "isLoginApp: 不保存数据");
                SharedPreferencesUtil.getInstance().saveUserMsg("","",false);
            }
        }
        loginView.onLoginResult(isLoginSuccess, code);
    }

    public String getUsername() {
        return (String) SharedPreferencesUtil.getInstance().getOtherMessage("username");
    }

    public String getPassword() {
        return (String) SharedPreferencesUtil.getInstance().getOtherMessage("pass");
    }

    public boolean getIsSave(){
        return (boolean) SharedPreferencesUtil.getInstance().getOtherMessage("isSave");
    }


}
