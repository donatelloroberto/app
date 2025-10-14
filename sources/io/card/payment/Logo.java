package io.card.payment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

class Logo {
    private final Context mContext;
    private Bitmap mLogo;
    private final Paint mPaint = new Paint();
    private boolean mUseCardIOLogo;

    public Logo(Context context) {
        this.mPaint.setAntiAlias(true);
        this.mPaint.setAlpha(100);
        this.mLogo = null;
        this.mContext = context;
    }

    /* access modifiers changed from: package-private */
    public void loadLogo(boolean useCardIOLogo) {
        if (this.mLogo == null || useCardIOLogo != this.mUseCardIOLogo) {
            this.mUseCardIOLogo = useCardIOLogo;
            if (useCardIOLogo) {
                this.mLogo = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.cio_card_io_logo);
            } else {
                this.mLogo = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.cio_paypal_logo);
            }
        }
    }

    public void draw(Canvas canvas, float maxWidth, float maxHeight) {
        float drawWidth;
        float drawHeight;
        if (this.mLogo == null) {
            loadLogo(false);
        }
        canvas.save();
        float targetAspectRatio = ((float) this.mLogo.getHeight()) / ((float) this.mLogo.getWidth());
        if (maxHeight / maxWidth < targetAspectRatio) {
            drawHeight = maxHeight;
            drawWidth = maxHeight / targetAspectRatio;
        } else {
            drawWidth = maxWidth;
            drawHeight = maxWidth * targetAspectRatio;
        }
        float halfWidth = drawWidth / 2.0f;
        float halfHeight = drawHeight / 2.0f;
        canvas.drawBitmap(this.mLogo, new Rect(0, 0, this.mLogo.getWidth(), this.mLogo.getHeight()), new RectF(-halfWidth, -halfHeight, halfWidth, halfHeight), this.mPaint);
        canvas.restore();
    }
}
