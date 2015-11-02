package com.shinelw.designlibrarydemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton button;
    private Snackbar bar;
    private TabLayout tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (FloatingActionButton) findViewById(R.id.float_action_button);
        tabs = (TabLayout) findViewById(R.id.layout_tab);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.setTabTextColors(Color.BLUE, Color.GREEN);
        tabs.setSelectedTabIndicatorHeight(40);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               bar.make(button, "text", Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       bar.dismiss();
                   }
               }).show();
                Toast.makeText(MainActivity.this, "dflsdjf", Toast.LENGTH_SHORT).show();
            }
        });

        for (int i = 0; i < 8; i++) {
            tabs.addTab(tabs.newTab().setText("tab" + (i + 1)));
        }
    }

}
