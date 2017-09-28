package com.google.pengjie.second.featurehighlight;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.google.pengjie.second.R;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

public class FeatureHighlightView extends View {
    private static final int DEFAULT_INNER_RADIUS = 100;
    private static final int DEFAULT_INNER_BACKGROUND = Color.argb(10, 255, 120, 77);
    private static final int DEFAULT_OUTER_RADIUS = 300;
    private static final int DEFAULT_OUTER_BACKGROUND = Color.argb(60, 20, 90, 130);
    private static final int INVALID_VALUE = Integer.MIN_VALUE;

    private final Context context;

    private int innerRadius = DEFAULT_INNER_RADIUS;
    private int innerBackground = DEFAULT_INNER_BACKGROUND;
    private int outerRadius = DEFAULT_OUTER_RADIUS;
    private int outerBackground = DEFAULT_OUTER_BACKGROUND;

    private Paint paint;
    private int centerX = INVALID_VALUE, centerY = INVALID_VALUE;
    private View target;
    private ValueAnimator valueAnimator;
    private int currentRadius;

    public FeatureHighlightView(Context context) {
        this(context, null);
    }

    public FeatureHighlightView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FeatureHighlightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FeatureHighlightView);
        try {
            int count = typedArray.getIndexCount();
            for (int i = 0; i < count; ++i) {
                int attrIndex = typedArray.getIndex(i);
                switch (attrIndex) {
                    case R.styleable.FeatureHighlightView_innerRadius:
                        innerRadius = typedArray.getDimensionPixelSize(attrIndex, DEFAULT_INNER_RADIUS);
                        break;
                    case R.styleable.FeatureHighlightView_innerBackground:
                        innerBackground = typedArray.getColor(attrIndex, DEFAULT_INNER_BACKGROUND);
                        break;
                    case R.styleable.FeatureHighlightView_outerRadius:
                        outerRadius = typedArray.getDimensionPixelSize(attrIndex, DEFAULT_OUTER_RADIUS);
                        break;
                    case R.styleable.FeatureHighlightView_outerBackground:
                        outerBackground = typedArray.getColor(attrIndex, DEFAULT_OUTER_BACKGROUND);
                        break;
                }
            }
        } finally {
            typedArray.recycle();
        }

        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void showHighlight(@IdRes int id) {
        target = ((Activity) context).findViewById(id);
        int[] location = new int[2];
        target.getLocationOnScreen(location);
        int width = target.getWidth();
        int height = target.getHeight();
        int[] another = new int[2];
        this.getLocationOnScreen(another);
        centerX = location[0] + width/2;
        centerY = location[1] + height/2 - another[1];
        this.setVisibility(VISIBLE);
        valueAnimator = ValueAnimator.ofInt(innerRadius, outerRadius);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                currentRadius = innerRadius;
                Log.e("here", "here repeat");
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentRadius = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(1500);
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (centerX == INVALID_VALUE || centerY == INVALID_VALUE) {
            return;
        }
        Log.e("here", "here onDraw: centerX = " + centerX + "; centerY = " + centerY);
        paint.setColor(outerBackground);
        canvas.drawCircle(centerX, centerY, outerRadius, paint);
        paint.setColor(innerBackground);
        canvas.drawCircle(centerX, centerY, currentRadius, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case ACTION_DOWN:
                break;
            case ACTION_UP:
                if (nearBy(event.getX(), event.getY())) {
                    this.setVisibility(GONE);
                    target.performClick();
                    target.setPressed(true);
                    target.invalidate();
                    target.setPressed(false);
                    target.invalidate();
                    valueAnimator.end();
                }
                break;
            case ACTION_MOVE: break;
        }
        return true;
    }

    private boolean nearBy(float x, float y) {
        if ((x-centerX) * (x-centerX) + (y-centerY) * (y-centerY) < innerRadius * innerRadius) {
            return true;
        }
        return false;
    }
}
