package com.shinelw.animationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_test);
        animation.setFillAfter(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(0, 720, button.getX()/2, button.getY()/2, 0, false);
                rotate3dAnimation.setDuration(2000);
                button.startAnimation(rotate3dAnimation);
            }
        });
    }

}
