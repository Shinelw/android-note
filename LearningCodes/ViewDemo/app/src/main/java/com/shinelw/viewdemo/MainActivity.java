package com.shinelw.viewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private View mView;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private int mLastX;
    private int mLastY;
    private TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = findViewById(R.id.view);
        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        textview = (TextView) findViewById(R.id.textView);
        ViewGroup content = (ViewGroup) findViewById(android.R.id.content);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mView.scrollBy(200, 0);
                //Toast.makeText(MainActivity.this, ""+mView.getScrollX() + "y:" + mView.getScrollY(), Toast.LENGTH_SHORT).show();
                // ObjectAnimator.ofFloat(mView, "translationX", 0, 1100).setDuration(100).start();
                ViewGroup.MarginLayoutParams parmas = (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
                parmas.width += 100;
                parmas.leftMargin += 100;
                mView.setLayoutParams(parmas);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.scrollBy(-200, 0);
                Toast.makeText(MainActivity.this, ""+mView.getScrollX() + "y:" + mView.getScrollY(), Toast.LENGTH_SHORT).show();
//                final int startX = 0;
//                final int deltaX = 100;
//                final ValueAnimator animator = ValueAnimator.ofInt(0,1).setDuration(1000);
//                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        float fraction = animator.getAnimatedFraction();
//                        mView.scrollTo(startX + (int)(deltaX*fraction), 0);
//                    }
//                });
//                animator.start();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.scrollBy(0, 200);
                mView.invalidate();
                Toast.makeText(MainActivity.this, ""+mView.getScrollX() + "y:" + mView.getScrollY(), Toast.LENGTH_SHORT).show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.scrollBy(0, -200);
                Toast.makeText(MainActivity.this, "" + mView.getScrollX() + "y:" + mView.getScrollY(), Toast.LENGTH_SHORT).show();
            }
        });
        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getRawX();
                int y = (int) event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int deltaX = x - mLastX;
                        int deltaY = y - mLastY;
                        textview.setText("move, deltax:" + deltaX + ", deltay:" + deltaY);
                        int translationX = (int) (mView.getTranslationX() + deltaX);
                        int translationY = (int) (mView.getTranslationY() + deltaY);
                        mView.setTranslationX(translationX);
                        mView.setTranslationY(translationY);
                        break;
                    default:
                        break;
                }
                mLastX = x;
                mLastY = y;
                return true;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
