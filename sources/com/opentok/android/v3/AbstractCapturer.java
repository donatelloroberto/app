package com.opentok.android.v3;

import android.graphics.SurfaceTexture;
import android.support.annotation.Keep;
import com.opentok.android.v3.debug.LogInterface;
import com.opentok.android.v3.debug.OtLog;
import com.opentok.android.v3.loader.Loader;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Keep
public abstract class AbstractCapturer {
    private static final LogInterface log = OtLog.LogToken("[AbstractCapturer]");
    private AtomicInteger fps = new AtomicInteger(0);
    private AtomicBoolean mirror_x = new AtomicBoolean(false);
    private long nativeHndl = 0;
    private long nativeOTCCapHndl = 0;
    private List<WeakReference<CapturerError>> notifyErrorLst = new ArrayList();
    private TexturePassthrough texturePassthrough = null;

    public interface CaptureSwitch {
        void cycleCamera() throws Exception;
    }

    public interface CapturerError {
        void onError(Exception exc);
    }

    interface TexturePassthrough {
        void onPassthroughFrame(SurfaceTexture surfaceTexture, int i, int i2, int i3, boolean z);
    }

    private native int captureFrameNative(long j, long j2, SurfaceTexture surfaceTexture, int i, int i2, int i3, byte[] bArr);

    private native void captureSettingsNative(long j, int i, int i2, int i3, boolean z);

    private native long finalizeNative(long j);

    private native long initNative();

    private native void provideFrameNative(long j, ByteBuffer byteBuffer, int i, int i2, int i3, int i4, byte[] bArr);

    private native void provideFrameNative(long j, byte[] bArr, int i, int i2, int i3, int i4, byte[] bArr2);

    private native void provideFrameNative(long j, int[] iArr, int i, int i2, int i3, int i4, byte[] bArr);

    private native void provideFramePlanarNative(long j, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, byte[] bArr);

    private static native void registerNatives();

    public abstract CaptureSettings getCaptureSettings();

    public abstract boolean isInitialized() throws Exception;

    public abstract boolean isStarted() throws Exception;

    public abstract void pause() throws Exception;

    public abstract void resume() throws Exception;

    public abstract boolean start() throws Exception;

    public abstract boolean stop() throws Exception;

    public static class CaptureSettings {
        public final int fps;
        public final int height;
        public final int width;

        public CaptureSettings(int width2, int height2, int fps2) {
            this.width = width2;
            this.height = height2;
            this.fps = fps2;
        }
    }

    public boolean initialize() throws Exception {
        this.nativeHndl = initNative();
        return 0 != this.nativeHndl;
    }

    public boolean destroy() throws Exception {
        this.nativeHndl = finalizeNative(this.nativeHndl);
        return 0 == this.nativeHndl;
    }

    /* access modifiers changed from: package-private */
    public void registerCapturerError(CapturerError ce) {
        if (ce != null) {
            unregisterCapturerError(ce);
            this.notifyErrorLst.add(new WeakReference(ce));
        }
    }

    /* access modifiers changed from: package-private */
    public void unregisterCapturerError(CapturerError ce) {
        if (ce != null) {
            for (WeakReference<CapturerError> ref : this.notifyErrorLst) {
                if (ref.get() == ce) {
                    this.notifyErrorLst.remove(ref);
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean initCapturer(long nativeCapturerCtx, CapturerError ce, TexturePassthrough notify) throws Exception {
        this.nativeOTCCapHndl = nativeCapturerCtx;
        this.texturePassthrough = notify;
        registerCapturerError(ce);
        return initialize();
    }

    /* access modifiers changed from: package-private */
    public boolean isMirrorX() {
        return this.mirror_x.get();
    }

    /* access modifiers changed from: package-private */
    public void captureSettings(long nativeCaptureSettingsHndl) {
        CaptureSettings settings = getCaptureSettings();
        captureSettingsNative(nativeCaptureSettingsHndl, settings.width, settings.height, settings.fps, this.mirror_x.get());
    }

    /* access modifiers changed from: protected */
    public void onError(Exception e) {
        for (WeakReference<CapturerError> ce : this.notifyErrorLst) {
            CapturerError notify = (CapturerError) ce.get();
            if (notify != null) {
                notify.onError(e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void provideFrame(byte[] data, int format, int width, int height, int rotation, boolean mirrorX, byte[] metadata) {
        this.mirror_x.set(mirrorX);
        provideFrameNative(this.nativeOTCCapHndl, data, format, width, height, rotation, metadata);
    }

    /* access modifiers changed from: protected */
    public void provideFrame(int[] data, int format, int width, int height, int rotation, boolean mirrorX, byte[] metadata) {
        this.mirror_x.set(mirrorX);
        provideFrameNative(this.nativeOTCCapHndl, data, format, width, height, rotation, metadata);
    }

    /* access modifiers changed from: protected */
    public void provideFrame(ByteBuffer buffer, int format, int width, int height, int rotation, boolean mirrorX, byte[] metadata) {
        this.mirror_x.set(mirrorX);
        provideFrameNative(this.nativeOTCCapHndl, buffer, format, width, height, rotation, metadata);
    }

    /* access modifiers changed from: protected */
    public void provideFrame(ByteBuffer yplane, ByteBuffer uplane, ByteBuffer vplane, int yPixelStride, int yRowStride, int uPixelStride, int uRowStride, int vPixelStride, int vRowStride, int width, int height, int rotation, boolean mirrorX, byte[] metadata) {
        this.mirror_x.set(mirrorX);
        provideFramePlanarNative(this.nativeOTCCapHndl, yplane, uplane, vplane, yPixelStride, yRowStride, uPixelStride, uRowStride, vPixelStride, vRowStride, width, height, rotation, metadata);
    }

    /* access modifiers changed from: protected */
    public void onCaptureFrame(SurfaceTexture surfaceTexture, int width, int height, int rotation, boolean mirror_x2, byte[] metadata) {
        this.mirror_x.set(mirror_x2);
        this.fps.set(captureFrameNative(this.nativeHndl, this.nativeOTCCapHndl, surfaceTexture, width, height, rotation, metadata));
        if (this.texturePassthrough != null) {
            this.texturePassthrough.onPassthroughFrame(surfaceTexture, width, height, rotation, mirror_x2);
        }
    }

    static {
        Loader.load();
        registerNatives();
    }
}
