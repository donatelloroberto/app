package com.opentok.android.v3;

import android.content.Context;
import android.support.annotation.Keep;
import android.view.Surface;
import android.view.View;
import com.opentok.android.v3.debug.LogInterface;
import com.opentok.android.v3.debug.OtLog;
import com.opentok.android.v3.loader.Loader;
import java.util.concurrent.atomic.AtomicBoolean;

@Keep
public abstract class AbstractRenderer {
    private static final LogInterface log = OtLog.LogToken("[AbstractRenderer]");
    private AtomicBoolean isActive;
    private AtomicBoolean isRendering;
    private AtomicBoolean isVideoPaused;
    private long nativeCtx;
    private PresentationStyle style;

    public enum PresentationStyle {
        FILL,
        FIT
    }

    private native long finalizeNative(long j);

    private native long initNative(Context context);

    private static native void registerNatives();

    private native void renderNative(long j, long j2, boolean z, boolean z2);

    private native void renderResizeNative(long j, Surface surface);

    private native boolean startNative(long j, Surface surface);

    private native void stopNative(long j);

    public abstract View getView();

    public abstract void onVideoEnabled(boolean z);

    public AbstractRenderer(Context context) {
        this.nativeCtx = context != null ? initNative(context) : 0;
        this.style = PresentationStyle.FILL;
        this.isActive = new AtomicBoolean(false);
        this.isRendering = new AtomicBoolean(false);
        this.isVideoPaused = new AtomicBoolean(false);
    }

    public void setStyle(PresentationStyle style2) {
        this.style = style2;
    }

    public PresentationStyle getStyle() {
        return this.style;
    }

    public void pause() {
        this.isVideoPaused.set(true);
    }

    public void resume() {
        this.isVideoPaused.set(false);
    }

    /* access modifiers changed from: protected */
    public boolean isPaused() {
        return this.isVideoPaused.get();
    }

    /* access modifiers changed from: protected */
    public void setSurface(Surface surface) {
        boolean z = false;
        spinlock();
        if (this.isActive.get()) {
            this.isActive.set(false);
            stopNative(this.nativeCtx);
        }
        if (surface != null) {
            Utility.androidAssert(startNative(this.nativeCtx, surface));
        }
        AtomicBoolean atomicBoolean = this.isActive;
        if (surface != null) {
            z = true;
        }
        atomicBoolean.set(z);
    }

    /* access modifiers changed from: protected */
    public void surfaceResized(Surface surface) {
        spinlock();
        if (this.isActive.get()) {
            renderResizeNative(this.nativeCtx, surface);
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        log.d("finalize(...) called", new Object[0]);
        if (0 != this.nativeCtx) {
            this.nativeCtx = finalizeNative(this.nativeCtx);
        }
    }

    /* access modifiers changed from: protected */
    public void onFrame(long nativeFrameCtx, boolean reverseFrame) {
        boolean z = true;
        if (!this.isVideoPaused.get() && this.isActive.get()) {
            this.isRendering.set(true);
            long j = this.nativeCtx;
            if (PresentationStyle.FIT != this.style) {
                z = false;
            }
            renderNative(j, nativeFrameCtx, reverseFrame, z);
            this.isRendering.set(false);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:3:0x000e, LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void spinlock() {
        /*
            r1 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicBoolean r0 = r1.isRendering
            boolean r0 = r0.get()
            if (r0 == 0) goto L_0x0010
            java.util.concurrent.atomic.AtomicBoolean r0 = r1.isActive
            boolean r0 = r0.get()
            if (r0 != 0) goto L_0x0000
        L_0x0010:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.opentok.android.v3.AbstractRenderer.spinlock():void");
    }

    static {
        Loader.load();
        registerNatives();
    }
}
