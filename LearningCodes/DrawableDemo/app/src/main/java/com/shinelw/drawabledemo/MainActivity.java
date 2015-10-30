package com.shinelw.drawabledemo;

import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageLevel(1);
        textView = (TextView) findViewById(R.id.text);
        TransitionDrawable drawable = (TransitionDrawable) textView.getBackground();
        drawable.startTransition(5000);
    }
}
