package com.shinelw.animationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Button button;
    private ListView listview;

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
                Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(0, 720, button.getX() / 2, button.getY() / 2, 0, false);
                rotate3dAnimation.setDuration(2000);
                button.startAnimation(rotate3dAnimation);
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
    }

}
