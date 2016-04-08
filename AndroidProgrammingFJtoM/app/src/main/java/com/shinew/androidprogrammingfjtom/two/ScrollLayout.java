package com.shinew.androidprogrammingfjtom.two;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by shinelw on 4/5/16.
 */
public class ScrollLayout extends FrameLayout {

    private String TAG = ScrollLayout.class.getSimpleName();
    Scroller mScroller;

    public ScrollLayout(Context context) {
        super(context);
        mScroller = new Scroller(context);
    }


    //在重绘的时候调用
     @Override
    public void computeScroll() {
         if(mScroller.computeScrollOffset()){
             this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
             this.postInvalidate();
         }
    }

    //调用这个方法进行滚动，这里我们只滚动竖直方向
    public void scrollTo(int y) {
        mScroller.startScroll(getScrollX(), getScrollY(), 0, y);
        this.invalidate();
    }
}
