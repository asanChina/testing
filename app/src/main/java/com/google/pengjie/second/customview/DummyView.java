package com.google.pengjie.second.customview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by pengjie on 12/23/17.
 */

public class DummyView extends AppCompatImageView {
    private static final String TAG = DummyView.class.getSimpleName();
    private Paint paint;
    private ValueAnimator valueAnimator;
    private float value = 0.0f;

    public DummyView(Context context) {
        super(context);
        Log.e(TAG, "here in DummyView.constructor");
        init();
    }

    public DummyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "here in DummyView.constructor 2");
        init();
    }

    public DummyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e(TAG, "here in DummyView.constructor 3");
    }

    private void init() {
        paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        valueAnimator = ValueAnimator.ofFloat(0, 1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "here in DummyView.onMeasure(int widthMeasureSpec, int heightMeasureSpec)");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        valueAnimator.setDuration(4000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                value = (Float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setRepeatCount(0);
        valueAnimator.start();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, "here in DummyView.onLayout(boolean changed, int left, int top, int right, int bottom)");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "here in onDraw, value = " + value);
        float left = value * getWidth();
        canvas.drawRect(left, 0, getWidth(), getHeight(), paint);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Log.e(TAG, "here in DummyView.onSaveInstanceState()");
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        Log.e(TAG, "here in DummyView.onRestoreInstanceState(Parcelable state)");
    }
}
