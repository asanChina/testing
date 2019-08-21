package com.google.pengjie.second.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MyTabIndicator extends LinearLayout {

    Paint indicatorPaint = new Paint();
    int childCount;
    int indicatorHeightPx = 5;
    int indicatorTop, indicatorLeft, indicatorWidth;

    public MyTabIndicator(Context context) {
        this(context, null);
    }

    public MyTabIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTabIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        indicatorPaint.setColor(Color.RED);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        childCount = getChildCount();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthRequired = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightRequired = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();


        Log.e("here", "herein MyTabIndicator.onMeasure, " + widthRequired + ", " + printMode(widthMode) + "; " + heightRequired + ", " + printMode(heightMode) + "; " + measuredWidth + ", " + measuredHeight);


        indicatorTop = measuredHeight;
        indicatorWidth = measuredWidth / childCount;

        setMeasuredDimension(measuredWidth, measuredHeight + indicatorHeightPx);
        Log.e("here", "herein MyTabIndicator.onMeasure, " + getMeasuredWidth() + ", " + getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("here", "herein onDraw(Canvas)" + canvas.getWidth() + ", " + canvas.getHeight());
        canvas.drawRect(indicatorLeft, indicatorTop, indicatorLeft + indicatorWidth, indicatorTop + indicatorHeightPx, indicatorPaint);
    }

    public void scroll(int position, float offset) {
        indicatorLeft = (int) (position + offset) * indicatorWidth;
        invalidate();
    }

    private String printMode(int mode) {
        if (mode == MeasureSpec.AT_MOST) {
            return "at most";
        } else if (mode == MeasureSpec.EXACTLY) {
            return "exactly";
        } else if (mode == MeasureSpec.UNSPECIFIED) {
            return "unspecified";
        } else {
            return "error";
        }
    }
}
