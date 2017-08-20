package com.google.pengjie.second.customview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.icu.util.Measure;
import android.support.annotation.Nullable;
import android.support.constraint.solver.SolverVariable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.google.pengjie.second.R;

/**
 * Created by pengjie on 8/19/17.
 */

public class ProgressView extends View {
    private int firstColor = Color.RED;
    private int secondColor = Color.BLUE;
    private int innerRadius = 40;
    private int ringWidth = 10;
    private int speed = 10;
    private Paint paint;
    private ValueAnimator valueAnimator;
    private int currentDegree = 0;
    private boolean progressColor = true; // if true, firstColor is moving, otherwise secondColor is moving.

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        try {
            int count = typedArray.getIndexCount();
            for (int i = 0; i < count; ++i) {
                int attrIndex = typedArray.getIndex(i);
                switch(attrIndex) {
                    case R.styleable.ProgressView_firstColor:
                        firstColor = typedArray.getColor(attrIndex, Color.RED);
                        break;
                    case R.styleable.ProgressView_secondColor:
                        secondColor = typedArray.getColor(attrIndex, Color.BLUE);
                        break;
                    case R.styleable.ProgressView_innerRadius1:
                        innerRadius = typedArray.getDimensionPixelSize(attrIndex, 40);
                        break;
                    case R.styleable.ProgressView_ringWidth:
                        ringWidth = typedArray.getDimensionPixelSize(attrIndex, 10);
                        break;
                    case R.styleable.ProgressView_speed:
                        speed = typedArray.getInt(attrIndex, 10);
                        break;
                }
            }
        } finally {
            typedArray.recycle();
        }

        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        valueAnimator = ValueAnimator.ofInt(0, 360);
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
                progressColor = !progressColor;
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Integer integer = (Integer) valueAnimator.getAnimatedValue();
                currentDegree = integer;
                invalidate();
            }
        });
        valueAnimator.setDuration(360/speed * 1000);
    }

    public void start() {
        valueAnimator.start();
    }


    public void end() {
        valueAnimator.end();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int measuredWidth, measuredHeight;

        if (widthMode == MeasureSpec.EXACTLY) {
            measuredWidth = widthSize;
        } else {
            int required = getPaddingLeft() + getPaddingRight() + innerRadius * 2 + ringWidth * 2;
            measuredWidth = Math.min(widthSize, required);
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            measuredHeight = heightSize;
        } else {
            int required = getPaddingTop() + getPaddingBottom() + innerRadius * 2 + ringWidth * 2;
            measuredHeight = Math.min(heightSize, required);
        }

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();
        int minSize = Math.min(width, height);
        if (minSize <= 0) {
            return;
        }

        if (minSize < innerRadius * 2 + ringWidth * 2) {
            innerRadius = (int) (1.0/3*minSize);
            ringWidth = (int) (1.0/6*minSize);
        }

        int centerX = getWidth()/2;
        int centerY = getHeight()/2;

        if (progressColor) {

            paint.setColor(secondColor);
            paint.setStrokeWidth(ringWidth);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(centerX, centerY, innerRadius, paint);
            paint.setColor(firstColor);
            RectF rectF = new RectF(centerX - innerRadius, centerY - innerRadius, centerX + innerRadius, centerY + innerRadius);
            canvas.drawArc(rectF, -90, currentDegree, false, paint);
        } else {

            paint.setColor(firstColor);
            paint.setStrokeWidth(ringWidth);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(centerX, centerY, innerRadius, paint);
            paint.setColor(secondColor);
            RectF rectF = new RectF(centerX - innerRadius, centerY - innerRadius, centerX + innerRadius, centerY + innerRadius);
            canvas.drawArc(rectF, -90, currentDegree, false, paint);
        }
    }
}
