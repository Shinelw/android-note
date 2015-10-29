package com.shinelw.viewdemo;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by shinelw on 10/28/15.
 */
public class Horizontal2 extends ViewGroup {
    public Horizontal2(Context context) {
        super(context);
    }
    private Scroller mScroller = new Scroller(getContext());
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            if (!mScroller.isFinished()) {
                mScroller.abortAnimation();
                return true;
            }
            return false;
        }else {
            return true;
        }
    }


}
