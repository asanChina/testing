package com.google.pengjie.second.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.View;

/**
 * Created by pengjie on 11/11/17.
 */

public class XfermodeView extends View {
    Paint paint;

    public XfermodeView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.RED);
        //int layerId = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), null, Canvas.ALL_SAVE_FLAG);
        paint.setColor(Color.argb(55, 0, 255, 0));
        canvas.drawCircle(200, 200, 200, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        paint.setColor(Color.BLUE);
        canvas.drawRect(200, 200, 600, 600, paint);
        paint.setXfermode(null);
        //canvas.restoreToCount(layerId);
    }
}
