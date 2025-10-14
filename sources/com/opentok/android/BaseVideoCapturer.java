package com.opentok.android;

import com.opentok.android.OpentokError;
import com.opentok.impl.OpentokErrorImpl;
import java.nio.ByteBuffer;

public abstract class BaseVideoCapturer {
    public static final int ARGB = 2;
    public static final int BGRA = 7;
    public static final int MJPEG = 9;
    public static final int NV12 = 4;
    public static final int NV21 = 1;
    public static final int RGB = 8;
    public static final int UYVY = 6;
    public static final int YUV420P = 3;
    public static final int YUY2 = 5;
    private PublisherKit publisherKit;
    private V3CapturerWrapper v3Wrapper;

    public static class CaptureSettings {
        public int expectedDelay;
        public int format;
        public int fps;
        public int height;
        public int width;
    }

    public interface CaptureSwitch {
        void cycleCamera();

        int getCameraIndex();

        void swapCamera(int i);
    }

    public abstract void destroy();

    public abstract CaptureSettings getCaptureSettings();

    public abstract void init();

    public abstract boolean isCaptureStarted();

    public abstract void onPause();

    public abstract void onResume();

    public abstract int startCapture();

    public abstract int stopCapture();

    private int mapFormatToOTCFormat(int format) {
        switch (format) {
            case 1:
                return 3;
            case 2:
                return 6;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 4;
            case 6:
                return 5;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 10;
            default:
                throw new IllegalArgumentException("Invalid pixel format");
        }
    }

    public void provideByteArrayFrame(byte[] data, int format, int width, int height, int rotation, boolean mirrorX) {
        provideByteArrayFrame(data, format, width, height, rotation, mirrorX, (byte[]) null);
    }

    public void provideByteArrayFrame(byte[] data, int format, int width, int height, int rotation, boolean mirrorX, byte[] metadata) {
        this.v3Wrapper.provideRawFrame(data, mapFormatToOTCFormat(format), width, height, rotation, mirrorX, metadata);
    }

    public void provideIntArrayFrame(int[] data, int format, int width, int height, int rotation, boolean mirrorX) {
        provideIntArrayFrame(data, format, width, height, rotation, mirrorX, (byte[]) null);
    }

    public void provideIntArrayFrame(int[] data, int format, int width, int height, int rotation, boolean mirrorX, byte[] metadata) {
        this.v3Wrapper.provideRawFrame(data, mapFormatToOTCFormat(format), width, height, rotation, mirrorX, metadata);
    }

    public void provideBufferFrame(ByteBuffer buffer, int format, int width, int height, int rotation, boolean mirrorX) {
        provideBufferFrame(buffer, mapFormatToOTCFormat(format), width, height, rotation, mirrorX, (byte[]) null);
    }

    public void provideBufferFrame(ByteBuffer buffer, int format, int width, int height, int rotation, boolean mirrorX, byte[] metadata) {
        this.v3Wrapper.provideRawFrame(buffer, mapFormatToOTCFormat(format), width, height, rotation, mirrorX, metadata);
    }

    public void provideBufferFramePlanar(ByteBuffer yplane, ByteBuffer uplane, ByteBuffer vplane, int yPixelStride, int yRowStride, int uPixelStride, int uRowStride, int vPixelStride, int vRowStride, int width, int height, int rotation, boolean mirrorX) {
        provideBufferFramePlanar(yplane, uplane, vplane, yPixelStride, yRowStride, uPixelStride, uRowStride, vPixelStride, vRowStride, width, height, rotation, mirrorX, (byte[]) null);
    }

    public void provideBufferFramePlanar(ByteBuffer yplane, ByteBuffer uplane, ByteBuffer vplane, int yPixelStride, int yRowStride, int uPixelStride, int uRowStride, int vPixelStride, int vRowStride, int width, int height, int rotation, boolean mirrorX, byte[] metadata) {
        this.v3Wrapper.provideRawFrame(yplane, uplane, vplane, yPixelStride, yRowStride, uPixelStride, uRowStride, vPixelStride, vRowStride, width, height, rotation, mirrorX, metadata);
    }

    /* access modifiers changed from: package-private */
    public void onCaptureError(Exception exp) {
        OpentokError error;
        OtLog.i("Error on video capturer", new Object[0]);
        if (exp != null) {
            error = new OpentokError(OpentokError.Domain.PublisherErrorDomain, OpentokError.ErrorCode.VideoCaptureFailed.getErrorCode(), exp);
        } else {
            error = new OpentokErrorImpl(OpentokError.Domain.PublisherErrorDomain, OpentokError.ErrorCode.VideoCaptureFailed.getErrorCode());
        }
        if (this.publisherKit != null) {
            this.publisherKit.throwError(error);
        }
    }

    /* access modifiers changed from: package-private */
    public void error(Exception exp) {
        if (this.publisherKit instanceof Publisher) {
            ((Publisher) this.publisherKit).onCameraFailed(exp);
        } else {
            onCaptureError(exp);
        }
    }

    /* access modifiers changed from: package-private */
    public void setV3Capturer(V3CapturerWrapper capturerWrapper) {
        this.v3Wrapper = capturerWrapper;
    }

    /* access modifiers changed from: protected */
    public void setPublisherKit(PublisherKit publisher) {
        this.publisherKit = publisher;
    }
}
