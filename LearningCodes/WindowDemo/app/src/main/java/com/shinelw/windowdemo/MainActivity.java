package com.shinelw.windowdemo;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button mFloatingButton;
    private LayoutParams mLayoutParams;
    private WindowManager mWindowManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFloatingButton = new Button(this);
        mFloatingButton.setText("Button");
        mWindowManager = getWindowManager();
        mLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,0, 0, PixelFormat.TRANSPARENT);
        mLayoutParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL|LayoutParams.FLAG_NOT_FOCUSABLE|LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 100;
        mLayoutParams.y = 100;
        mWindowManager.addView(mFloatingButton, mLayoutParams);
    }

}
