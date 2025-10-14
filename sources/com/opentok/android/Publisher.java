package com.opentok.android;

import android.content.Context;
import com.opentok.android.BaseVideoCapturer;
import com.opentok.android.Camera2VideoCapturer;
import com.opentok.android.OpentokError;
import com.opentok.android.OtLog;
import com.opentok.android.PublisherKit;
import com.opentok.impl.OpentokErrorImpl;

public class Publisher extends PublisherKit {
    private static final OtLog.LogToken log = new OtLog.LogToken();
    protected CameraCaptureFrameRate cameraFrameRate;
    protected CameraListener cameraListener;
    protected CameraCaptureResolution cameraResolution;
    private boolean isPreviewEnabled;

    public interface CameraListener {
        void onCameraChanged(Publisher publisher, int i);

        void onCameraError(Publisher publisher, OpentokError opentokError);
    }

    public enum CameraCaptureResolution {
        LOW(0),
        MEDIUM(1),
        HIGH(2);
        
        private int captureResolution;

        private CameraCaptureResolution(int resolution) {
            this.captureResolution = resolution;
        }

        /* access modifiers changed from: package-private */
        public int getCaptureResolution() {
            return this.captureResolution;
        }

        static CameraCaptureResolution fromResolution(int captureResolutionId) {
            for (CameraCaptureResolution resolution : values()) {
                if (resolution.getCaptureResolution() == captureResolutionId) {
                    return resolution;
                }
            }
            throw new IllegalArgumentException("unknown capture resolution " + captureResolutionId);
        }

        static CameraCaptureResolution defaultResolution() {
            return MEDIUM;
        }
    }

    public enum CameraCaptureFrameRate {
        FPS_30(0),
        FPS_15(1),
        FPS_7(2),
        FPS_1(3);
        
        private int captureFramerate;

        private CameraCaptureFrameRate(int framerate) {
            this.captureFramerate = framerate;
        }

        /* access modifiers changed from: package-private */
        public int getCaptureFrameRate() {
            return this.captureFramerate;
        }

        static CameraCaptureFrameRate fromFramerate(int captureFramerateId) {
            for (CameraCaptureFrameRate fps : values()) {
                if (fps.getCaptureFrameRate() == captureFramerateId) {
                    return fps;
                }
            }
            throw new IllegalArgumentException("unknown capture framerate " + captureFramerateId);
        }

        static CameraCaptureFrameRate defaultFrameRate() {
            return FPS_30;
        }
    }

    public void setCameraListener(CameraListener listener) {
        this.cameraListener = listener;
    }

    @Deprecated
    public Publisher(Context context) {
        this(context, (String) null, true, 0, true, (BaseVideoCapturer) null, (CameraCaptureResolution) null, (CameraCaptureFrameRate) null, (BaseVideoRenderer) null);
    }

    @Deprecated
    public Publisher(Context context, String name) {
        this(context, name, true, 0, true, (BaseVideoCapturer) null, (CameraCaptureResolution) null, (CameraCaptureFrameRate) null, (BaseVideoRenderer) null);
    }

    @Deprecated
    public Publisher(Context context, String name, BaseVideoCapturer capturer) {
        this(context, name, true, 0, true, capturer, (CameraCaptureResolution) null, (CameraCaptureFrameRate) null, (BaseVideoRenderer) null);
    }

    @Deprecated
    public Publisher(Context context, String name, boolean audioTrack, boolean videoTrack) {
        this(context, name, audioTrack, 0, videoTrack, (BaseVideoCapturer) null, (CameraCaptureResolution) null, (CameraCaptureFrameRate) null, (BaseVideoRenderer) null);
    }

    @Deprecated
    public Publisher(Context context, String name, CameraCaptureResolution resolution, CameraCaptureFrameRate frameRate) {
        this(context, name, true, 0, true, (BaseVideoCapturer) null, resolution, frameRate, (BaseVideoRenderer) null);
    }

    public static class Builder extends PublisherKit.Builder {
        CameraCaptureFrameRate frameRate = null;
        CameraCaptureResolution resolution = null;

        public Builder(Context context) {
            super(context);
        }

        public Builder resolution(CameraCaptureResolution resolution2) {
            this.resolution = resolution2;
            return this;
        }

        public Builder frameRate(CameraCaptureFrameRate frameRate2) {
            this.frameRate = frameRate2;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder audioTrack(boolean enabled) {
            this.audioTrack = enabled;
            return this;
        }

        public Builder videoTrack(boolean enabled) {
            this.videoTrack = enabled;
            return this;
        }

        public Builder capturer(BaseVideoCapturer capturer) {
            this.capturer = capturer;
            return this;
        }

        public Builder renderer(BaseVideoRenderer renderer) {
            this.renderer = renderer;
            return this;
        }

        public Builder audioBitrate(int bitsPerSecond) {
            return (Builder) super.audioBitrate(bitsPerSecond);
        }

        public Publisher build() {
            return new Publisher(this.context, this.name, this.audioTrack, this.audioBitrate, this.videoTrack, this.capturer, this.resolution, this.frameRate, this.renderer);
        }
    }

    @Deprecated
    protected Publisher(Context context, String name, boolean audioTrack, boolean videoTrack, BaseVideoCapturer capturer, CameraCaptureResolution resolution, CameraCaptureFrameRate frameRate, BaseVideoRenderer renderer) {
        this(context, name, audioTrack, 0, videoTrack, capturer, resolution, frameRate, renderer);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected Publisher(android.content.Context r11, java.lang.String r12, boolean r13, int r14, boolean r15, com.opentok.android.BaseVideoCapturer r16, com.opentok.android.Publisher.CameraCaptureResolution r17, com.opentok.android.Publisher.CameraCaptureFrameRate r18, com.opentok.android.BaseVideoRenderer r19) {
        /*
            r10 = this;
            if (r16 != 0) goto L_0x0043
            if (r11 == 0) goto L_0x0043
            if (r17 == 0) goto L_0x0039
        L_0x0006:
            if (r18 == 0) goto L_0x003e
        L_0x0008:
            r0 = r17
            r1 = r18
            com.opentok.android.BaseVideoCapturer r8 = com.opentok.android.VideoCaptureFactory.constructCapturer(r11, r0, r1)
        L_0x0010:
            if (r19 != 0) goto L_0x0046
            if (r11 == 0) goto L_0x0046
            com.opentok.android.BaseVideoRenderer r9 = com.opentok.android.VideoRenderFactory.constructRenderer(r11)
        L_0x0018:
            r2 = r10
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            r7 = r15
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            r2 = 0
            r10.isPreviewEnabled = r2
            com.opentok.android.BaseVideoCapturer r2 = r10.getCapturer()
            boolean r2 = r2 instanceof com.opentok.android.DefaultVideoCapturer
            if (r2 == 0) goto L_0x0035
            com.opentok.android.BaseVideoCapturer r2 = r10.getCapturer()
            com.opentok.android.DefaultVideoCapturer r2 = (com.opentok.android.DefaultVideoCapturer) r2
            r2.setPublisher(r10)
        L_0x0035:
            com.opentok.android.AudioDeviceManager.initializeDefaultDevice(r11)
            return
        L_0x0039:
            com.opentok.android.Publisher$CameraCaptureResolution r17 = com.opentok.android.Publisher.CameraCaptureResolution.defaultResolution()
            goto L_0x0006
        L_0x003e:
            com.opentok.android.Publisher$CameraCaptureFrameRate r18 = com.opentok.android.Publisher.CameraCaptureFrameRate.defaultFrameRate()
            goto L_0x0008
        L_0x0043:
            r8 = r16
            goto L_0x0010
        L_0x0046:
            r9 = r19
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: com.opentok.android.Publisher.<init>(android.content.Context, java.lang.String, boolean, int, boolean, com.opentok.android.BaseVideoCapturer, com.opentok.android.Publisher$CameraCaptureResolution, com.opentok.android.Publisher$CameraCaptureFrameRate, com.opentok.android.BaseVideoRenderer):void");
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        log.d("Publisher finalizing", new Object[0]);
        super.finalize();
    }

    public void destroy() {
        super.destroy();
    }

    @Deprecated
    public void setCameraId(int cameraId) {
        if (this.capturerWrapper.getBaseVideoCapturer() != null) {
            log.e("Capturer is not yet initialized. Call startPreview() or publish into a session", new Object[0]);
            return;
        }
        try {
            ((BaseVideoCapturer.CaptureSwitch) this.capturerWrapper.getBaseVideoCapturer()).swapCamera(cameraId);
            onPublisherCameraPositionChanged(this, cameraId);
        } catch (ClassCastException e) {
            log.e("This capturer cannot change cameras since it does not implement BaseVideoCapturer.CaptureSwitch interface", new Object[0]);
        }
    }

    public void cycleCamera() {
        if (this.capturerWrapper.getBaseVideoCapturer() == null) {
            log.e("Capturer is not yet initialized. Call startPreview() or publish into a session", new Object[0]);
            return;
        }
        try {
            BaseVideoCapturer.CaptureSwitch captureSwitch = (BaseVideoCapturer.CaptureSwitch) this.capturerWrapper.getBaseVideoCapturer();
            captureSwitch.cycleCamera();
            onPublisherCameraPositionChanged(this, captureSwitch.getCameraIndex());
        } catch (Camera2VideoCapturer.Camera2Exception re) {
            onCameraFailed(re);
        } catch (ClassCastException e) {
            log.e("This capturer cannot change cameras since it does not implement BaseVideoCapturer.CaptureSwitch interface", new Object[0]);
        } catch (RuntimeException e2) {
            log.e(e2.getMessage(), new Object[0]);
            onCameraFailed(e2);
        }
    }

    @Deprecated
    public void swapCamera() {
        log.i("swap camera", new Object[0]);
        if (this.capturerWrapper.getBaseVideoCapturer() == null) {
            log.e("Capturer is not yet initialized. Call startPreview() or publish into a session", new Object[0]);
        } else {
            cycleCamera();
        }
    }

    @Deprecated
    public int getCameraId() {
        if (this.capturerWrapper.getBaseVideoCapturer() == null) {
            log.e("Capturer is not yet initialized. Call startPreview() or publish into a session", new Object[0]);
            return -1;
        }
        try {
            return ((BaseVideoCapturer.CaptureSwitch) this.capturerWrapper.getBaseVideoCapturer()).getCameraIndex();
        } catch (ClassCastException e) {
            return -1;
        }
    }

    /* access modifiers changed from: package-private */
    public void onPublisherCameraPositionChanged(Publisher publisher, final int newCameraId) {
        log.i("Publisher has changed the camera position to: %d", Integer.valueOf(newCameraId));
        this.handler.post(new Runnable() {
            public void run() {
                Publisher.this.onCameraChanged(newCameraId);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCameraChanged(int newCameraId) {
        if (this.cameraListener != null) {
            this.cameraListener.onCameraChanged(this, newCameraId);
        }
    }

    /* access modifiers changed from: package-private */
    public void onCameraFailed(final Exception exp) {
        log.i("Camera device has failed ", new Object[0]);
        this.handler.post(new Runnable() {
            public void run() {
                OpentokError error;
                if (exp != null) {
                    error = new OpentokError(OpentokError.Domain.PublisherErrorDomain, OpentokError.ErrorCode.CameraFailed.getErrorCode(), exp);
                } else {
                    error = new OpentokErrorImpl(OpentokError.Domain.PublisherErrorDomain, OpentokError.ErrorCode.CameraFailed.getErrorCode());
                }
                Publisher.this.onCameraError(error);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCameraError(OpentokError error) {
        if (this.cameraListener != null) {
            this.cameraListener.onCameraError(this, error);
        }
    }

    public void startPreview() {
        this.isPreviewEnabled = true;
        buildIfNeeded();
    }

    /* access modifiers changed from: package-private */
    public void unpublish() {
        if (!this.isPreviewEnabled) {
            super.unpublish();
        }
    }
}
