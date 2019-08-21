package com.google.pengjie.second.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class ShellTextView extends AppCompatTextView {
    public ShellTextView(Context context) {
        super(context);
    }

    public ShellTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShellTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("here", "herein ShellTextView.onMeasure, " + MeasureSpec.getSize(widthMeasureSpec) + ", " + printMode(MeasureSpec.getMode(widthMeasureSpec)) + "; " + MeasureSpec.getSize(heightMeasureSpec) + ", " + printMode(MeasureSpec.getMode(heightMeasureSpec)));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
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
