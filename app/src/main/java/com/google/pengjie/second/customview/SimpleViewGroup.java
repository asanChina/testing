package com.google.pengjie.second.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by pengjie on 9/12/17.
 */

public class SimpleViewGroup extends ViewGroup {
    private Paint paint;

    public SimpleViewGroup(Context context) {
        this(context, null);
    }

    public SimpleViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        init();
    }

    void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.argb(46, 20, 70, 134));
        paint.setStrokeWidth(100);
        canvas.drawCircle(200, 600, 100, paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        canvas.drawCircle(200, 600, 100, paint);

        canvas.drawCircle(200, 600, 1, paint);
    }
}
