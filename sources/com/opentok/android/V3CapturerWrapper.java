package com.opentok.android;

import com.opentok.android.BaseVideoCapturer;
import com.opentok.android.v3.AbstractCapturer;
import java.nio.ByteBuffer;

class V3CapturerWrapper extends AbstractCapturer {
    private BaseVideoCapturer capturer;
    private boolean isInitialized = false;

    public V3CapturerWrapper(BaseVideoCapturer capturer2) {
        this.capturer = capturer2;
        capturer2.setV3Capturer(this);
    }

    public boolean initialize() throws Exception {
        try {
            this.capturer.init();
            this.isInitialized = true;
            return true;
        } catch (Exception e) {
            this.capturer.error(e);
            return false;
        }
    }

    public boolean destroy() throws Exception {
        try {
            this.capturer.destroy();
            this.isInitialized = false;
            return true;
        } catch (Exception e) {
            this.capturer.error(e);
            return false;
        }
    }

    public boolean start() throws Exception {
        try {
            return this.capturer.startCapture() == 0;
        } catch (Exception e) {
            this.capturer.error(e);
            return false;
        }
    }

    public boolean stop() throws Exception {
        try {
            return this.capturer.stopCapture() == 0;
        } catch (Exception e) {
            this.capturer.error(e);
            return false;
        }
    }

    public void pause() throws Exception {
        this.capturer.onPause();
    }

    public void resume() throws Exception {
        this.capturer.onResume();
    }

    public boolean isInitialized() throws Exception {
        return this.isInitialized;
    }

    public boolean isStarted() throws Exception {
        return this.capturer.isCaptureStarted();
    }

    public AbstractCapturer.CaptureSettings getCaptureSettings() {
        BaseVideoCapturer.CaptureSettings settings = this.capturer.getCaptureSettings();
        return new AbstractCapturer.CaptureSettings(settings.width, settings.height, settings.fps);
    }

    /* access modifiers changed from: package-private */
    public void provideRawFrame(byte[] data, int format, int width, int height, int rotation, boolean mirrorX, byte[] metadata) {
        if (this.isInitialized) {
            provideFrame(data, format, width, height, rotation, mirrorX, metadata);
        }
    }

    /* access modifiers changed from: package-private */
    public void provideRawFrame(int[] data, int format, int width, int height, int rotation, boolean mirrorX, byte[] metadata) {
        if (this.isInitialized) {
            provideFrame(data, format, width, height, rotation, mirrorX, metadata);
        }
    }

    /* access modifiers changed from: package-private */
    public void provideRawFrame(ByteBuffer buffer, int format, int width, int height, int rotation, boolean mirrorX, byte[] metadata) {
        if (this.isInitialized) {
            provideFrame(buffer, format, width, height, rotation, mirrorX, metadata);
        }
    }

    /* access modifiers changed from: package-private */
    public void provideRawFrame(ByteBuffer yplane, ByteBuffer uplane, ByteBuffer vplane, int yPixelStride, int yRowStride, int uPixelStride, int uRowStride, int vPixelStride, int vRowStride, int width, int height, int rotation, boolean mirrorX, byte[] metadata) {
        if (this.isInitialized) {
            provideFrame(yplane, uplane, vplane, yPixelStride, yRowStride, uPixelStride, uRowStride, vPixelStride, vRowStride, width, height, rotation, mirrorX, metadata);
        }
    }

    /* access modifiers changed from: package-private */
    public void setBaseVideoCapturer(BaseVideoCapturer capturer2) {
        if (!this.isInitialized) {
            this.capturer = capturer2;
            capturer2.setV3Capturer(this);
        }
    }

    /* access modifiers changed from: package-private */
    public BaseVideoCapturer getBaseVideoCapturer() {
        return this.capturer;
    }
}
