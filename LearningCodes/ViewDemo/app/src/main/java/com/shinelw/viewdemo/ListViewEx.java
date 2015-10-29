package com.shinelw.viewdemo;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by shinelw on 10/28/15.
 */
public class ListViewEx extends ListView {
    public ListViewEx(Context context) {
        super(context);
    }

    private static final String TAG = "ListViewEx";
    private Horizontal2 horizontal2;
    private int mLastX = 0;
    private int mLastY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                horizontal2.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    horizontal2.requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(ev);
    }
}
