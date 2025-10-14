package io.card.payment;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v4.view.ViewCompat;
import java.util.Arrays;

class Torch {
    private static final String TAG = Torch.class.getSimpleName();
    private float mHeight;
    private boolean mOn = false;
    private float mWidth;

    public Torch(float width, float height) {
        this.mWidth = width;
        this.mHeight = height;
    }

    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate((-this.mWidth) / 2.0f, (-this.mHeight) / 2.0f);
        Paint borderPaint = new Paint();
        borderPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setAntiAlias(true);
        borderPaint.setStrokeWidth(1.5f);
        Paint fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(-1);
        if (this.mOn) {
            fillPaint.setAlpha(192);
        } else {
            fillPaint.setAlpha(96);
        }
        float[] outerRadii = new float[8];
        Arrays.fill(outerRadii, 5.0f);
        RoundRectShape buttonShape = new RoundRectShape(outerRadii, (RectF) null, (float[]) null);
        buttonShape.resize(this.mWidth, this.mHeight);
        buttonShape.draw(canvas, fillPaint);
        buttonShape.draw(canvas, borderPaint);
        Paint boltPaint = new Paint();
        boltPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        boltPaint.setAntiAlias(true);
        if (this.mOn) {
            boltPaint.setColor(-1);
        } else {
            boltPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        }
        Path boltPath = createBoltPath();
        Matrix m = new Matrix();
        float boltHeight = 0.8f * this.mHeight;
        m.postScale(boltHeight, boltHeight);
        boltPath.transform(m);
        canvas.translate(this.mWidth / 2.0f, this.mHeight / 2.0f);
        canvas.drawPath(boltPath, boltPaint);
        canvas.restore();
    }

    public void setOn(boolean on) {
        this.mOn = on;
    }

    private static Path createBoltPath() {
        Path p = new Path();
        p.moveTo(10.0f, 0.0f);
        p.lineTo(0.0f, 11.0f);
        p.lineTo(6.0f, 11.0f);
        p.lineTo(2.0f, 20.0f);
        p.lineTo(13.0f, 8.0f);
        p.lineTo(7.0f, 8.0f);
        p.lineTo(10.0f, 0.0f);
        p.setLastPoint(10.0f, 0.0f);
        Matrix m = new Matrix();
        m.postTranslate(-6.5f, -10.0f);
        m.postScale(0.05f, 0.05f);
        p.transform(m);
        return p;
    }
}
