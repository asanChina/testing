package com.google.pengjie.second.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.google.pengjie.second.R;

/**
 * Created by pengjie on 8/19/17.
 */

public class CustomTitleView extends View {
    private String text = "";
    private int textColor = Color.BLACK;
    private int textSize = 20;
    private Paint textPaint;
    private Rect textBound;

    public CustomTitleView(Context context) {
        this(context, null);
    }

    public CustomTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView);
        try {
            int num = typedArray.getIndexCount();
            for (int i = 0; i < num; ++i) {
                int index = typedArray.getIndex(i);
                switch(index) {
                    case R.styleable.CustomTitleView_titleText:
                        text = typedArray.getString(index);
                        break;
                    case R.styleable.CustomTitleView_titleTextColor:
                        textColor = typedArray.getColor(index, Color.BLACK);
                        break;
                    case R.styleable.CustomTitleView_titleTextSize:
                        textSize = typedArray.getDimensionPixelSize(index, 20);
                        break;
                }
            }
        } finally {
            typedArray.recycle();
        }
        init();
    }

    private void init() {
        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);
        textBound = new Rect();
    }

    public void setText(String text) {
        this.text = text;
        requestLayout();
    }

    public void setTextColor(int color) {
        this.textColor = color;
        requestLayout();
    }

    public void setTextSize(int size) {
        this.textSize = size;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth, measuredHeight;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        textPaint.setTextSize(textSize);
        textPaint.getTextBounds(text, 0, text.length(), textBound);

        Log.e("here", "here left = " + textBound.left + ", right = " + textBound.right + ", width = " + textBound.width() + ", top = " + textBound.top + ", bottom = " + textBound.bottom + ", height = " + textBound.height());
        if (widthMode == MeasureSpec.EXACTLY) {
            measuredWidth = widthSize;
        } else {
            int required = getPaddingLeft() + textBound.width() + getPaddingRight();
            measuredWidth = required;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            measuredHeight = heightSize;
        } else {
            int required = getPaddingTop() + textBound.height() + getPaddingBottom();
            measuredHeight = required;
        }

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        textPaint.setColor(textColor);

        canvas.drawText(text, (width - textBound.width())/2.0f-textBound.left, (height-textBound.height())/2.0f-textBound.top, textPaint);
    }
}
