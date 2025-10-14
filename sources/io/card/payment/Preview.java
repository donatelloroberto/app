package io.card.payment;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

class Preview extends ViewGroup {
    static final /* synthetic */ boolean $assertionsDisabled = (!Preview.class.desiredAssertionStatus());
    private static final String TAG = Preview.class.getSimpleName();
    private int mPreviewHeight;
    private int mPreviewWidth;
    SurfaceView mSurfaceView;

    public Preview(Context context, AttributeSet attributeSet, int previewWidth, int previewHeight) {
        super(context, attributeSet);
        this.mPreviewWidth = previewHeight;
        this.mPreviewHeight = previewWidth;
        this.mSurfaceView = new SurfaceView(context);
        addView(this.mSurfaceView);
    }

    public SurfaceView getSurfaceView() {
        if ($assertionsDisabled || this.mSurfaceView != null) {
            return this.mSurfaceView;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    public SurfaceHolder getSurfaceHolder() {
        SurfaceHolder holder = getSurfaceView().getHolder();
        if ($assertionsDisabled || holder != null) {
            return holder;
        }
        throw new AssertionError();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(255, 255, 0, 0);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec), resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed && getChildCount() > 0) {
            if ($assertionsDisabled || this.mSurfaceView != null) {
                int width = r - l;
                int height = b - t;
                if (this.mPreviewHeight * width > this.mPreviewWidth * height) {
                    int scaledChildWidth = (this.mPreviewWidth * height) / this.mPreviewHeight;
                    this.mSurfaceView.layout((width - scaledChildWidth) / 2, 0, (width + scaledChildWidth) / 2, height);
                    return;
                }
                int scaledChildHeight = (this.mPreviewHeight * width) / this.mPreviewWidth;
                this.mSurfaceView.layout(0, (height - scaledChildHeight) / 2, width, (height + scaledChildHeight) / 2);
                return;
            }
            throw new AssertionError();
        }
    }
}
