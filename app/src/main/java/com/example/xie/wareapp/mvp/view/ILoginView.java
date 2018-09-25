package com.example.xie.wareapp.mvp.view;

import android.content.Context;
import android.text.SpannableString;

public interface ILoginView {
    Context getContext();

    void setUsername(String username);
    void setPassword(String password);

    void onLoginResult(Boolean result, int code);

    String getUsername();
    String getPassword();

    void send(SpannableString clickString);
}
