package com.google.pengjie.second.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;

import com.google.pengjie.second.R;

/**
 * Created by pengjie on 11/11/17.
 */

public class ShaderView extends View {
    private Bitmap bitmap;
    private Paint paint;

    public ShaderView(Context context) {
        super(context);
        init(context);
    }

    void init(Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_done_black_24dp);
        paint = new Paint();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(bitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.RED);
        canvas.translate(100, 100);
        canvas.drawRect(0, 0, 400, 400, paint);
    }
}
