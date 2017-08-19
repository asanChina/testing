package com.google.pengjie.second.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.google.pengjie.second.R;

/**
 * Created by pengjie on 8/19/17.
 */

public class CustomImageView extends View {
    private String text = "";
    private int textColor = Color.BLACK;
    private int textSize = 20;
    private Bitmap bitmap = null;
    private int imageScaleType = 0;
    private Paint paint;
    private Rect rect;

    public CustomImageView(Context context) {
        this(context, null);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView);
        try {
            int count = typedArray.getIndexCount();
            for (int i = 0; i < count; ++i) {
                int attr = typedArray.getIndex(i);
                switch(attr) {
                    case R.styleable.CustomTitleView_titleText:
                        text = typedArray.getString(attr);
                        break;
                    case R.styleable.CustomTitleView_titleTextColor:
                        textColor = typedArray.getColor(attr, Color.BLACK);
                        break;
                    case R.styleable.CustomTitleView_titleTextSize:
                        textSize = typedArray.getDimensionPixelSize(attr, 20);
                        break;
                }
            }
        } finally {
            typedArray.recycle();
        }

        typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomImageView);
        try {
            int count = typedArray.getIndexCount();
            for (int i = 0; i < count; ++i) {
                int attr = typedArray.getIndex(i);
                switch(attr) {
                    case R.styleable.CustomImageView_image:
                        bitmap = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(attr, 0));
                        break;
                    case R.styleable.CustomImageView_imageScaleType:
                        imageScaleType = typedArray.getInt(attr, 0);
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
        rect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int measuredWidth, measuredHeight;

        paint.setTextSize(textSize);
        paint.getTextBounds(text, 0, text.length(), rect);

        if (widthMode == MeasureSpec.EXACTLY) {
            measuredWidth = widthSize;
        } else {
            int textRequired = getPaddingLeft() + getPaddingRight() + rect.width();
            int imageRequired = (bitmap == null ? 0 : bitmap.getWidth()) + getPaddingLeft() + getPaddingRight();
            measuredWidth = Math.min(widthSize, Math.max(textRequired, imageRequired));
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            measuredHeight = heightSize;
        } else {
            int textRequired = rect.height();
            int imageRequired = bitmap.getHeight();
            measuredHeight = getPaddingBottom() + getPaddingTop() + textRequired + imageRequired;
        }

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();

        if (width <= 0 || height <= 0) {
            return;
        }

        if (width < rect.width()) {
            text = TextUtils.ellipsize(text, new TextPaint(paint), width, TextUtils.TruncateAt.END).toString();
            paint.getTextBounds(text, 0, text.length(), rect);
        }

        paint.setColor(textColor);
        canvas.drawText(text, width/2.0f - rect.width()/2.0f + getPaddingLeft() - rect.left, getHeight() - getPaddingBottom() - rect.bottom, paint);
        int textHeight = rect.height();

        rect.left = getPaddingLeft();
        rect.right = width + getPaddingLeft();
        rect.top = getPaddingTop();
        rect.bottom = getHeight() - getPaddingBottom() - textHeight;

        if (imageScaleType == 0) {
            canvas.drawBitmap(bitmap, null, rect, paint);
        } else {
            Rect src = new Rect();
            if (bitmap.getWidth() > rect.width()) {
                src.left = bitmap.getWidth()/2 - rect.width()/2;
                src.right = src.left + rect.width();
            } else {
                src.left = 0;
                src.right = bitmap.getWidth();
                rect.left = getPaddingLeft() + (rect.width()/2 - bitmap.getWidth()/2);
                rect.right = rect.left + bitmap.getWidth();
            }
            if (bitmap.getHeight() > rect.height()) {
                src.top = bitmap.getHeight()/2 - rect.height()/2;
                src.bottom = src.top + rect.height();
            } else {
                src.top = 0;
                src.bottom = bitmap.getHeight();
                rect.top = getPaddingTop() + (rect.height()/2 - bitmap.getHeight()/2);
                rect.bottom = rect.top + bitmap.getHeight();
            }
            canvas.drawBitmap(bitmap, src, rect, paint);
        }
    }
}
