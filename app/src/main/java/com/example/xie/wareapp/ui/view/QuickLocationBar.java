package com.example.xie.wareapp.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.xie.wareapp.R;

/**
 * Created by 7invensun on 2018/9/26.
 */

public class QuickLocationBar extends View {
    private String characters[] = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"
            , "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private Paint paint;
    private int choose = -1;
    public QuickLocationBar(Context context) {
        super(context);
    }

    public QuickLocationBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public QuickLocationBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        int width = getWidth();
        int height = getHeight();
        int singleHeight = characters.length;
        for (int i = 0; i < singleHeight; i++) {
            paint.setColor(getResources().getColor(R.color.colorBlack));
            paint.setAntiAlias(true);
            paint.setTextSize(150*(float) width/320);
            if (i==choose){
                paint.setColor(getResources().getColor(R.color.colorGrey));
            }
            float xPos = width / 2 - paint.measureText(characters[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(characters[i], xPos, yPos, paint);
            paint.reset();// 每次绘制完成后不要忘记重制Paint
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        float y = event.getY();
        int c = (int) (y / getHeight() * characters.length);
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                choose = -1;//
                setBackgroundColor(0x0000);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                if (choose != c) {
                    if (c >= 0 && c < characters.length) {
                        choose = c;
                        invalidate();
                    }
                }
                break;
        }
        return true;

    }
}
