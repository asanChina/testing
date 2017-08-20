package com.google.pengjie.second.featurehighlight;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.google.pengjie.second.R;

public class FeatureHighlightView extends View {
    private static final int DEFAULT_INNER_RADIUS = 100;
    private static final int DEFAULT_INNER_BACKGROUND = Color.argb(10, 255, 255, 255);
    private static final int DEFAULT_OUTER_RADIUS = 300;
    private static final int DEFAULT_OUTER_BACKGROUND = Color.argb(60, 0, 0, 0);
    private static final int INVALID_VALUE = Integer.MIN_VALUE;

    private final Context context;

    private int innerRadius = DEFAULT_INNER_RADIUS;
    private int innerBackground = DEFAULT_INNER_BACKGROUND;
    private int outerRadius = DEFAULT_OUTER_RADIUS;
    private int outerBackground = DEFAULT_OUTER_BACKGROUND;

    private Paint paint;
    private int centerX = INVALID_VALUE, centerY = INVALID_VALUE;

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
        View target = ((Activity) context).findViewById(id);
        int[] location = new int[2];
        target.getLocationOnScreen(location);
        int width = target.getWidth();
        int height = target.getHeight();
        centerX = location[0] + width/2;
        centerY = location[1] + height/2;
        this.setVisibility(VISIBLE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (centerX == INVALID_VALUE || centerY == INVALID_VALUE) {
            return;
        }

        paint.setColor(outerBackground);
        canvas.drawCircle(centerX, centerY, outerRadius, paint);
        paint.setColor(innerBackground);
        canvas.drawCircle(centerX, centerY, innerRadius, paint);
    }
}
