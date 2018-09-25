package com.example.xie.wareapp.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.xie.wareapp.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    private static final int MSG_WHAT_SHOWTOAST = 1;
    public Context mContext;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int TOP = 3;
    public static final int BOTTOM = 4;
    public static final int SCALE = 5;
    public static final int FADE = 6;
    private Toast toast;
    private Unbinder bind;
    private Handler baseHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_WHAT_SHOWTOAST:
                    String content = msg.getData().getString("content");
                    showToast(content);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (getLayout() != 0) {
            setContentView(getLayout());
        }
        bind = ButterKnife.bind(this);
        OnActCreate(savedInstanceState);
        initView();
        if (switchoverAnimationIn() != 0) {
            activityIn(switchoverAnimationIn());
        }


    }

    private void activityOut(int state) {
        switch (state) {
            case LEFT:
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                break;
            case RIGHT:
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                break;
//            case TOP:
//                overridePendingTransition(R.anim.top_in, R.anim.top_out);
//                break;
//            case BOTTOM:
//                overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
//                break;
//            case SCALE:
//                overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
//                break;
//            case FADE:
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                break;
        }
    }

    private void activityIn(int state) {
        switch (state) {
            case LEFT:
                //输入，退出  的界面
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                break;
            case RIGHT:
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                break;
//                case TOP:
//                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
//                    break;
//                case BOTTOM:
//                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
//                    break;
//                case SCALE:
//                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
//                    break;
//                case FADE:
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                    break;
        }
    }


    public abstract int getLayout();

    protected abstract void OnActCreate(Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract int switchoverAnimationIn();

    protected abstract int switchoverAnimationOut();


    public void sendToast(String content) {
        Message msg = baseHandler.obtainMessage();
        msg.what = MSG_WHAT_SHOWTOAST;
        Bundle bundle = new Bundle();
        bundle.putString("content", content);
        msg.setData(bundle);
        baseHandler.sendMessage(msg);
    }

    private void showToast(String content) {
        if (toast == null) {
            toast = Toast.makeText(mContext, content, toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
        baseHandler.removeCallbacksAndMessages(1);
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();

    }

    @Override
    public void finish() {
        super.finish();
        if (switchoverAnimationOut()!=0){
            activityOut(switchoverAnimationOut());
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
           baseHandler.removeCallbacksAndMessages("");
        }
        return super.onKeyDown(keyCode, event);
    }
}
