package com.example.xie.wareapp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xie.wareapp.R;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";
    private static final int REQUEST_CODE_GETCOUNTRYCODE = 1;
    @BindView(R.id.tv_areacode_register)
    TextView tvAreacodeRegister;
    @BindView(R.id.et_phone_register)
    EditText etPhoneRegister;
    @BindView(R.id.btn_register_register)
    Button btnRegisterRegister;

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void OnActCreate(Bundle savedInstanceState) {

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GETCOUNTRYCODE){

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_areacode_register:
                startActivityForResult(new Intent(this,CountryCodeActivity.class),REQUEST_CODE_GETCOUNTRYCODE);
                break;
        }
    }
}
