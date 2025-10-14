package com.opentok.android.v3;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.opentok.android.v3.AbstractCapturer;
import com.opentok.android.v3.AbstractRenderer;
import com.opentok.android.v3.debug.LogInterface;
import com.opentok.android.v3.debug.OtLog;
import com.opentok.android.v3.loader.Loader;

public class PassthroughRenderer extends AbstractRenderer implements SurfaceHolder.Callback, AbstractCapturer.TexturePassthrough {
    private static final LogInterface log = OtLog.LogToken("[PassThroughRenderer]");
    private long nativeHndl = 0;
    private SurfaceView surfaceView;

    private native long finalizePassthroughNative(long j);

    private native long initPassthroughNative(Surface surface);

    private static native void registerNatives();

    private native void renderPassthroughNative(long j, SurfaceTexture surfaceTexture, int i, int i2, int i3, boolean z, boolean z2);

    private native void resizePassthroughNative(long j, Surface surface);

    public PassthroughRenderer(Context context) {
        super(context);
        this.surfaceView = new SurfaceView(context);
        this.surfaceView.getHolder().addCallback(this);
    }

    public synchronized void onPassthroughFrame(SurfaceTexture surfaceTexture, int width, int height, int rotation, boolean mirror_x) {
        boolean z;
        if (0 != this.nativeHndl && !isPaused()) {
            long j = this.nativeHndl;
            if (AbstractRenderer.PresentationStyle.FIT == getStyle()) {
                z = true;
            } else {
                z = false;
            }
            renderPassthroughNative(j, surfaceTexture, width, height, rotation, mirror_x, z);
        }
    }

    public void onVideoEnabled(boolean videoEnabled) {
    }

    public View getView() {
        return this.surfaceView;
    }

    public synchronized void surfaceCreated(SurfaceHolder holder) {
        log.d("surfaceCreated(...) enter", new Object[0]);
        this.nativeHndl = initPassthroughNative(this.surfaceView.getHolder().getSurface());
        log.d("surfaceCreated(...) exit", new Object[0]);
    }

    public synchronized void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        log.d("surfaceChanged(...) enter", new Object[0]);
        if (0 != this.nativeHndl) {
            resizePassthroughNative(this.nativeHndl, holder.getSurface());
        }
        log.d("surfaceChanged(...) exit", new Object[0]);
    }

    public synchronized void surfaceDestroyed(SurfaceHolder holder) {
        log.d("surfaceDestroyed(...) enter", new Object[0]);
        this.nativeHndl = finalizePassthroughNative(this.nativeHndl);
        log.d("surfaceDestroyed(...) exit", new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void onFrame(long nativeFrameCtx, boolean reverseFrame) {
    }

    static {
        Loader.load();
        registerNatives();
    }
}
