package com.example.xie.wareapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.xie.wareapp.R;
import com.example.xie.wareapp.mvp.presenter.Imp.LoginPresenterImp;
import com.example.xie.wareapp.mvp.view.ILoginView;
import com.mob.tools.utils.UIHandler;

import butterknife.BindView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 登录Activity
 */
public class LoginActivity extends BaseActivity implements ILoginView, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.iv_app_logo)
    ImageView ivAppLogo;
    @BindView(R.id.tv_app_name)
    TextView tvAppName;
    @BindView(R.id.et_name_login)
    EditText etNameLogin;
    @BindView(R.id.et_pass_login)
    EditText etPassLogin;
    @BindView(R.id.cb_rememberPass_login)
    CheckBox cbRememberPassLogin;
    @BindView(R.id.tv_registerAforget_login)
    TextView tvRegisterAforgetLogin;
    private LoginPresenterImp loginPresenter;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void OnActCreate(Bundle savedInstanceState) {
        loginPresenter = new LoginPresenterImp(this);
    }

    @Override
    protected void initView() {
        ivAppLogo.setBackgroundResource(R.mipmap.suo);
        tvAppName.setText(mContext.getText(R.string.app_name));
        registerOrForgetLogin();
        cbRememberPassLogin.setOnCheckedChangeListener(this);

        String username = loginPresenter.getUsername();
        String password = loginPresenter.getPassword();
        if (!("").equals(username) || !("").equals(password)) {
            etNameLogin.setText(username);
            etPassLogin.setText(password);
            cbRememberPassLogin.setChecked(true);
        }

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
    protected void onResume() {
        super.onResume();
    }

    private void registerOrForgetLogin() {

        SpannableString clickString = new SpannableString("我要注册");

        clickString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Log.e(TAG, "registerOrForgetLogin: 我要注册");
//                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                showShare();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);//设置颜色
            }
        }, 0, clickString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvRegisterAforgetLogin.append(clickString);
        tvRegisterAforgetLogin.append(new SpannableString(" / "));
        SpannableString clickString2 = new SpannableString("忘记密码");
        clickString2.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Log.e(TAG, "registerOrForgetLogin: 忘记密码");
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE); //设置颜色
            }
        }, 0, clickString2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvRegisterAforgetLogin.append(clickString2);
        tvRegisterAforgetLogin.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件
    }

    public void loginAppClick(View view) {
        if (etNameLogin.getText().toString().trim().equals("")) {
            sendToast("请输入用户名");
        } else if (etPassLogin.getText().toString().trim().equals("")) {
            sendToast("请输入密码");
        } else {
            loginPresenter.loginApp();
        }
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void setUsername(String username) {

    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        if (result) {
            sendToast("登录成功");
        } else {
            sendToast("失败");
        }
    }

    @Override
    public String getUsername() {
        return etNameLogin.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassLogin.getText().toString().trim();
    }

    @Override
    public void send(SpannableString clickString) {
        tvRegisterAforgetLogin.append(clickString);
        tvRegisterAforgetLogin.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        loginPresenter.isSaveMag(isChecked);
    }

    private void showShare() {
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//
//        // title标题，微信、QQ和QQ空间等平台使用
//        oks.setTitle("11111");
//        // titleUrl QQ和QQ空间跳转链接
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url在微信、微博，Facebook等平台中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");
//        // 启动分享GUI
//        oks.show(this);

//        Platform plat = ShareSDK.getPlatform(QQ.NAME);
//        plat.showUser(null);
    }

    private void authorize(Platform plat) {
//        if (plat == null) {
//            popupOthers();
//            return;
//        }
////判断指定平台是否已经完成授权
//        if(plat.isAuthValid()) {
//            String userId = plat.getDb().getUserId();
//            if (userId != null) {
//                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
//                login(plat.getName(), userId, null);
//                return;
//            }
//        }
//        plat.setPlatformActionListener(this);
//        // true不使用SSO授权，false使用SSO授权
//        plat.SSOSetting(true);
//        //获取用户资料
//        plat.showUser(null);
    }

}
