package com.shinelw.animationdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.animation.IntEvaluator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

public class MainActivity extends Activity {
    private Button button;
    private ListView listview;
    private Button button2;
    private TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        textview = (TextView) findViewById(R.id.textview);
        textview.setVisibility(View.GONE);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(0, 720, button.getX() / 2, button.getY() / 2, 0, false);
                rotate3dAnimation.setDuration(2000);

                //属性动画
                ObjectAnimator.ofFloat(button, "translationY", -button.getHeight()).start();
                ValueAnimator colorAnim = ObjectAnimator.ofInt(button, "backgroundColor", Color.RED, Color.BLUE);
                colorAnim.setDuration(3000);
                colorAnim.setEvaluator(new ArgbEvaluator());
                colorAnim.setRepeatCount(ValueAnimator.INFINITE);
                colorAnim.setRepeatMode(ValueAnimator.REVERSE);
                colorAnim.start();

                //旋转、平移、缩放、透明度改变
                AnimatorSet set = new AnimatorSet();
                set.playTogether(
                        ObjectAnimator.ofFloat(button, "rotationX", 0, 360),
                        ObjectAnimator.ofFloat(button, "rotationY", 0, 180),
                        ObjectAnimator.ofFloat(button, "translationX", 0, 90),
                        ObjectAnimator.ofFloat(button, "translationY", 0, 90),
                        ObjectAnimator.ofFloat(button, "scaleX", 1, 1.5f),
                        ObjectAnimator.ofFloat(button, "scaleY", 1, 1.5f),
                        ObjectAnimator.ofFloat(button, "alpha", 1, 0.25f, 1)

                );
                set.setDuration(5* 1000).start();

                //通过xml文件来实现动画，但是不推荐，理由是在xml中无法动态获取控件的宽高
//                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.property_animator);
//                set.setTarget(button);
//                set.start();

            }
        });

        listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(new BaseAdapter() {
           @Override
           public int getCount() {
               return 10;
           }

           @Override
           public Object getItem(int position) {
               return null;
           }

           @Override
           public long getItemId(int position) {
               return 0;
           }

           @Override
           public View getView(int position, View convertView, ViewGroup parent) {
               View view = getLayoutInflater().from(MainActivity.this).inflate(android.R.layout.simple_list_item_1, null);
               TextView textview = (TextView) view.findViewById(android.R.id.text1);
               textview.setText("item" + position);
               return view;
           }
        });

        //对button的宽度进行动画
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofInt(button2, "width", button2.getMeasuredWidth(), 500, 50, button2.getMeasuredWidth()).setDuration(4000).start();
                ObjectAnimator.ofInt(textview, "width", 500).setDuration(4000).start();
            }
        });

    }

    private void performAnimatie(final View target, final int start, final int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            //持有一个INtEvaluators对象， 方便对下面估值的时候使用
            private IntEvaluator mEvaluator = new IntEvaluator();
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
               //获得当前动画的进度值， 整型， 1——100之间
                int currentValue = (int) animation.getAnimatedValue();
                Log.d("MainActivity", "current value :" + currentValue);

                //获得当前进度占整个动画过程的比例
            }
        });
    }

}
