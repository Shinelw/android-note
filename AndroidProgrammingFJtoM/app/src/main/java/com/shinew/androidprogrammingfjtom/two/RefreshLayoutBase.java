package com.shinew.androidprogrammingfjtom.two;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by shinelw on 4/5/16.
 */
public abstract class RefreshLayoutBase <T extends View>  extends ViewGroup implements AbsListView.OnScrollListener{

    protected Scroller mScroller;
    protected View mHeaderview;
    protected View mFooterView;
    protected int mYOffdet;
    protected T mContentView;
    protected int mInitScrollY = 0;
    protected int mLastY = 0;


    public static final int STATUS_IDLE = 0;
    public static final int STATUS_PULL_TO_PEFRESH = 1;
    public static final int STATUS_RELEASE_TO_REFRESH = 2;
    public static final int STATUS_REFERSHING = 3;
    public static final int STATUS_LOADING = 4;
    protected int mCurrentStatus = STATUS_IDLE;
    private ImageView mArrowIamgeView;
    private boolean isArrowUp;
    private TextView mTipsTextView;
    private TextView mTimeTextView;
    private ProgressBar mProgeressBar;
    private int mScreenHeight;
    private int mHeaderHeight;

    public RefreshLayoutBase(Context context) {
        super(context);
    }
}
