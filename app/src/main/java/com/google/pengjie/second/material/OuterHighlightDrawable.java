package com.google.pengjie.second.material;

/**
 * Created by pengjie on 9/27/17.
 */
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.util.TypedValue;

import com.google.pengjie.second.R;

/**
 * Custom drawable for drawing a circular the outer highlight.
 */
public class OuterHighlightDrawable extends Drawable {

    // The threshold distance from the top and bottom of the view beyond which the offset-layout will
    // be used instead of the centered-layout.
    private final int centerThresholdPx;

    // The offset distance of the Outer Highlight when offset-layout is used.
    private final int offsetHorizontalOffsetPx;

    private final int outerVisualPadding;

    private final Rect targetBounds = new Rect();
    private final Rect textBounds = new Rect();
    private final Paint paint = new Paint();

    public OuterHighlightDrawable(Context context) {
        paint.setColor(getPrimaryColor(context));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        Resources resources = context.getResources();
        centerThresholdPx =
                resources.getDimensionPixelSize(
                        R.dimen.libraries_material_featurehighlight_center_threshold);
        offsetHorizontalOffsetPx =
                resources.getDimensionPixelSize(
                        R.dimen.libraries_material_featurehighlight_center_horizontal_offset);
        outerVisualPadding =
                resources.getDimensionPixelSize(R.dimen.libraries_material_featurehighlight_outer_padding);
    }

    @Override
    public void draw(Canvas canvas) {
        // TODO(kennyy): More accurate radius measurement for circular inner.
        int targetCenterX = targetBounds.centerX();
        int targetCenterY = targetBounds.centerY();

        int centerX;
        int centerY;

        int distanceToNearestVerticalEdge = Math.min(targetCenterY, canvas.getHeight() - targetCenterY);
        if (distanceToNearestVerticalEdge < centerThresholdPx) {
            centerX = targetCenterX;
            centerY = targetCenterY;
        } else {
            boolean onLeftSide = targetCenterX < canvas.getWidth();
            centerX =
                    onLeftSide
                            ? textBounds.centerX() + offsetHorizontalOffsetPx
                            : textBounds.centerX() - offsetHorizontalOffsetPx;
            centerY = textBounds.centerY();
        }

        int radius =
                outerVisualPadding
                        + Math.max(
                        coveringRadius(centerX, centerY, targetBounds),
                        coveringRadius(centerX, centerY, textBounds));
        canvas.drawCircle(centerX, centerY, radius, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public void setColor(@ColorInt int color) {
        paint.setColor(color);
    }

    public void setDrawConstraints(Rect targetBounds, Rect textBounds) {
        this.targetBounds.set(targetBounds);
        this.textBounds.set(textBounds);
    }

    // Calculates the minimum integer radius of a circle from origin (originX, originY) required to
    // completely cover the input Rect.
    private int coveringRadius(float originX, float originY, Rect rect) {
        return (int)
                Math.ceil(
                        MathUtils.distanceToFurthestCorner(
                                originX, originY, rect.left, rect.top, rect.right, rect.bottom));
    }

    private static int getPrimaryColor(Context context) {
        TypedValue value = new TypedValue();
        context
                .getTheme()
                .resolveAttribute(android.support.v7.appcompat.R.attr.colorAccent, value, true);
        return value.data;
    }
}