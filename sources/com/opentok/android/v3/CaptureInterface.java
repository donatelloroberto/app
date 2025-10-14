package com.opentok.android.v3;

import android.graphics.SurfaceTexture;
import android.support.annotation.NonNull;
import java.nio.ByteBuffer;

public interface CaptureInterface {

    public interface CaptureBus {

        public enum Format {
            YUV420P,
            NV12,
            NV21,
            YUY2,
            UYVY,
            ARGB32,
            BGRA32,
            RGB24,
            MJPEG
        }

        void error(Exception exc);

        void provideFrame(SurfaceTexture surfaceTexture, int i, int i2, int i3, boolean z, byte[] bArr);

        void provideFrame(ByteBuffer byteBuffer, Format format, int i, int i2, int i3, boolean z, byte[] bArr);

        void provideFrame(byte[] bArr, Format format, int i, int i2, int i3, boolean z, byte[] bArr2);

        void provideFrame(int[] iArr, Format format, int i, int i2, int i3, boolean z, byte[] bArr);

        void provideFramePlanar(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, byte[] bArr);
    }

    void destroy() throws Exception;

    CaptureSettings getCaptureSettings();

    void initialize(@NonNull CaptureBus captureBus) throws Exception;

    void pause() throws Exception;

    void resume() throws Exception;

    void start() throws Exception;

    void stop() throws Exception;

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
}
