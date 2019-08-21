package com.google.pengjie.second.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class StupidViewGroup extends LinearLayout {
    int i = 0;

    public StupidViewGroup(Context context) {
        this(context, null);
    }

    public StupidViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StupidViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(0).getLayoutParams();
        layoutParams.height += 10 * (++i);
        getChildAt(0).setLayoutParams(layoutParams);
        getChildAt(0).requestLayout();

        Log.e("here", "herein StupidViewGroup.onMeasure 1, " + MeasureSpec.getSize(widthMeasureSpec) + ", " + printMode(MeasureSpec.getMode(widthMeasureSpec)) + "; " + MeasureSpec.getSize(heightMeasureSpec) + ", " + printMode(MeasureSpec.getMode(heightMeasureSpec)));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("here", "herein StupidViewGroup.onMeasure 2, " + MeasureSpec.getSize(widthMeasureSpec) + ", " + printMode(MeasureSpec.getMode(widthMeasureSpec)) + "; " + MeasureSpec.getSize(heightMeasureSpec) + ", " + printMode(MeasureSpec.getMode(heightMeasureSpec)) + ", getMeasuredWidth() = " + getMeasuredWidth() + ", getMeasuredHeight() = " + getMeasuredHeight());


        layoutParams = (LinearLayout.LayoutParams) getChildAt(0).getLayoutParams();
        layoutParams.height += 10 * (++i);
        getChildAt(0).setLayoutParams(layoutParams);
        getChildAt(0).requestLayout();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("here", "herein onSizeChanged, w = " + w + ", h = " + h + ", oldw = " + oldw  + ", oldh = " + oldh);
    }

    private String printMode(int mode) {
        if (mode == MeasureSpec.UNSPECIFIED) {
            return "UNSPECIFIED";
        } else if (mode == MeasureSpec.AT_MOST) {
            return "AT_MOST";
        } else if (mode == MeasureSpec.EXACTLY) {
            return "EXACTLY";
        } else {
            return "ERROR";
        }
    }
}
