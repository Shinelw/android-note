package com.shinelw.designdemo;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by shinelw on 11/3/15.
 */
public class CoorActivity extends AppCompatActivity {
    private FloatingActionButton button;
    private CoordinatorLayout rootLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coor);
        button = (FloatingActionButton) findViewById(R.id.button);
        rootLayout = (CoordinatorLayout) findViewById(R.id.layout_root);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rootLayout, "hi,everyone", Snackbar.LENGTH_LONG).setAction("好的", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                    }
                }).show();
            }
        });
    }
}
