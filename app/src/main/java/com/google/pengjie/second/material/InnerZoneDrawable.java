package com.google.pengjie.second.material;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;

import com.google.pengjie.second.R;

/**
 * Custom drawable for drawing a circular background behind the target view.
 */
public class InnerZoneDrawable extends Drawable {

    private final Paint paint = new Paint();
    private final Rect targetBounds = new Rect();

    // The size used as the radius of the inner zone when a Drawable is used.
    private final int innerRadiusPx;

    public InnerZoneDrawable(Context context) {
        Resources resources = context.getResources();
        innerRadiusPx =
                resources.getDimensionPixelSize(R.dimen.libraries_material_featurehighlight_inner_radius);

        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(Canvas canvas) {
        int targetCenterX = targetBounds.centerX();
        int targetCenterY = targetBounds.centerY();

        int radius =
                Math.max(innerRadiusPx, Math.max(targetBounds.width() / 2, targetBounds.height() / 2));
        canvas.drawCircle(targetCenterX, targetCenterY, radius, paint);
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

    public void setDrawConstraints(Rect targetRect) {
        this.targetBounds.set(targetRect);
    }
}