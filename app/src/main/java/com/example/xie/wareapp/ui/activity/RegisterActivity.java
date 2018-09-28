package com.example.xie.wareapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xie.wareapp.R;
import com.example.xie.wareapp.mvp.model.CityBean;
import com.example.xie.wareapp.mvp.presenter.Imp.RegisterPresenterImp;
import com.example.xie.wareapp.mvp.view.IRegisterView;

import butterknife.BindView;

public class RegisterActivity extends BaseActivity implements IRegisterView, View.OnClickListener {
    private static final String TAG = "RegisterActivity";
    private static final int REQUEST_CODE_GETCOUNTRYCODE = 1;
    @BindView(R.id.tv_areacode_register)
    TextView tvAreacodeRegister;
    @BindView(R.id.et_phone_register)
    EditText etPhoneRegister;
    @BindView(R.id.btn_register_register)
    Button btnRegisterRegister;
    private CityBean model;
    private RegisterPresenterImp registerPresenterImp;

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void OnActCreate(Bundle savedInstanceState) {
        registerPresenterImp = new RegisterPresenterImp(this);
    }

    @Override
    protected void initView() {
        tvAreacodeRegister.setOnClickListener(this);
    }

    @Override
    protected int switchoverAnimationIn() {
        return RIGHT;
    }

    @Override
    protected int switchoverAnimationOut() {
        return LEFT;
    }

    public void registerAppClick(View view) {
        String phone = etPhoneRegister.getText().toString().trim();
        if (!phone.isEmpty()) {
            registerPresenterImp.isPhoneFormat(phone);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GETCOUNTRYCODE) {
            if (resultCode == RESULT_OK) {
                model = data.getExtras().getParcelable("CityBean");
                tvAreacodeRegister.setText(model.getCountry() + " +" + model.getCode());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_areacode_register:
                startActivityForResult(new Intent(this, CountryCodeActivity.class), REQUEST_CODE_GETCOUNTRYCODE);
                break;
        }
    }

    @Override
    public void isSendVerificationCode(boolean isMobileNO, String phone) {
        String code;
        if (model != null) {
            code = model.getCode();
        }else {
            code = mContext.getString(R.string.default_code);
        }
        if (isMobileNO) {
            registerPresenterImp.sendVerificationCode(code,phone);
        } else {
            sendToast("手机格式不正确");
        }

    }
}
