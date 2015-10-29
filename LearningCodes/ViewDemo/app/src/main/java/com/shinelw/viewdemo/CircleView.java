package com.shinelw.viewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shinelw on 10/29/15.
 */
public class CircleView extends View {

    private int mColor = Color.RED;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor = a.getColor(R.styleable.CircleView_circle_color, Color.RED);
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor = a.getColor(R.styleable.CircleView_circle_color, Color.RED);
        init();
    }

    private void init(){
        mPaint.setColor(mColor);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightMeasureSpec == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, 200);
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200, heightMeasureSpec);
        }else if(heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthMeasureSpec, 200);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int paddingleft = getPaddingLeft();
        int paddingright = getPaddingRight();
        int paddingtop = getPaddingTop();
        int paddingbottom = getPaddingBottom();
        width = width - paddingleft - paddingright;
        height = height - paddingtop - paddingbottom;
        int radius = Math.min(width, height)/2;
        canvas.drawCircle(paddingleft + width/2, paddingtop + height/2, radius, mPaint);
    }
}
