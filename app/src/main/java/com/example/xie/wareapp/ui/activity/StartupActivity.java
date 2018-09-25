package com.example.xie.wareapp.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xie.wareapp.R;
import com.example.xie.wareapp.utils.StatusBarUtil;

import butterknife.BindView;

public class StartupActivity extends BaseActivity {
    private static final String TAG = "StartupActivity";
    private static final int MSG_WHAT_COUNTDOWN = 1;
    @BindView(R.id.btn_upNext_startup)
    Button btnUpNextStartup;
    @BindView(R.id.tv_countdown_startup)
    TextView tvCountdownStartup;
    private int count = 5;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_WHAT_COUNTDOWN:
                    int newCount = 0;
                    if (count >= 1) {
                        newCount = count--;
                        tvCountdownStartup.setText(newCount+"秒后跳转界面");
                        handler.sendEmptyMessageDelayed(MSG_WHAT_COUNTDOWN,1000);
                    }else {
                        startup();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private void startup() {
        handler.removeMessages(MSG_WHAT_COUNTDOWN);
        startActivity(new Intent(StartupActivity.this,LoginActivity.class));
        finish();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_startup;
    }

    @Override
    protected void OnActCreate(Bundle savedInstanceState) {
        //设置状态栏颜色
        StatusBarUtil.setWindowStatusBarColor(this, R.color.colorStartupStatusBar);
        handler.sendEmptyMessage(MSG_WHAT_COUNTDOWN);


    }

    @Override
    protected void initView() {

    }

    @Override
    protected int switchoverAnimationIn() {
        return LEFT;
    }

    @Override
    protected int switchoverAnimationOut() {
        return 0;
    }


    public void startupNextActivityClick(View view) {
        startup();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        handler.removeMessages(MSG_WHAT_COUNTDOWN);
        return super.onKeyDown(keyCode, event);
    }
}
