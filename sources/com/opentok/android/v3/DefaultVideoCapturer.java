package com.opentok.android.v3;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Keep;
import com.opentok.android.v3.AbstractCapturer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Keep
public class DefaultVideoCapturer extends AbstractCapturer implements AbstractCapturer.CaptureSwitch {
    private final AbstractCapturer capturer;

    public enum CaptureType {
        DEFAULT,
        LEGACY_API,
        CAMERA2_API
    }

    public enum FrameRate {
        FPS_30,
        FPS_15,
        FPS_7,
        FPS_1
    }

    public enum Resolution {
        LOW,
        MEDIUM,
        HIGH
    }

    public static class Builder {
        private final Set<String> cam2Lst = new HashSet(Arrays.asList(new String[]{"nexus 4", "nexus 5", "nexus 5x", "nexus 6", "nexus 6p", "nexus 7", "nexus 10", "pixel", "gt-i9300", "samsung-sm-g925a", "samsung-sm-g935a", "samsung-sm-t817a", "sm-g900h", "sm-j106h", "lgus991", "lg-h810", "lg-k430", "xt1058", "aquaris e5", "c6602"}));
        private CaptureType captureType;
        private Context context;
        private AbstractCapturer.CapturerError errorCb;
        private FrameRate frameRate;
        private Resolution resolution;

        public Builder(Context context2) {
            this.context = context2;
            this.resolution = Resolution.MEDIUM;
            this.frameRate = FrameRate.FPS_30;
            this.captureType = CaptureType.DEFAULT;
            this.errorCb = null;
        }

        public Builder setResolution(Resolution resolution2) {
            this.resolution = resolution2;
            return this;
        }

        public Builder setFrameRate(FrameRate frameRate2) {
            this.frameRate = frameRate2;
            return this;
        }

        public Builder setErrorListener(AbstractCapturer.CapturerError errorListener) {
            this.errorCb = errorListener;
            return this;
        }

        public Builder setCaptureType(CaptureType type) {
            this.captureType = type;
            return this;
        }

        public DefaultVideoCapturer Build() {
            AbstractCapturer camera1VideoCapturer;
            switch (this.captureType) {
                case LEGACY_API:
                    return new DefaultVideoCapturer(new Camera1VideoCapturer(this.context, this.frameRate, this.resolution, this.errorCb));
                case CAMERA2_API:
                    return new DefaultVideoCapturer(new Camera2VideoCapturer(this.context, this.frameRate, this.resolution, this.errorCb));
                default:
                    if (this.cam2Lst.contains(Build.MODEL.toLowerCase(Locale.ROOT))) {
                        camera1VideoCapturer = new Camera2VideoCapturer(this.context, this.frameRate, this.resolution, this.errorCb);
                    } else {
                        camera1VideoCapturer = new Camera1VideoCapturer(this.context, this.frameRate, this.resolution, this.errorCb);
                    }
                    return new DefaultVideoCapturer(camera1VideoCapturer);
            }
        }
    }

    public boolean initialize() throws Exception {
        return this.capturer.initialize();
    }

    public boolean start() throws Exception {
        return this.capturer.start();
    }

    public boolean stop() throws Exception {
        return this.capturer.stop();
    }

    public boolean destroy() throws Exception {
        return this.capturer.destroy();
    }

    public void pause() throws Exception {
        this.capturer.pause();
    }

    public void resume() throws Exception {
        this.capturer.resume();
    }

    public boolean isInitialized() throws Exception {
        return this.capturer.isInitialized();
    }

    public boolean isStarted() throws Exception {
        return this.capturer.isStarted();
    }

    public AbstractCapturer.CaptureSettings getCaptureSettings() {
        return this.capturer.getCaptureSettings();
    }

    public void cycleCamera() throws Exception {
        ((AbstractCapturer.CaptureSwitch) this.capturer).cycleCamera();
    }

    DefaultVideoCapturer(AbstractCapturer capturer2) {
        this.capturer = capturer2;
    }

    /* access modifiers changed from: package-private */
    public boolean initCapturer(long nativeCapInterfaceHndl, AbstractCapturer.CapturerError ce, AbstractCapturer.TexturePassthrough notify) throws Exception {
        return this.capturer.initCapturer(nativeCapInterfaceHndl, ce, notify);
    }

    /* access modifiers changed from: package-private */
    public void captureSettings(long nativeCaptureSettingsHndl) {
        this.capturer.captureSettings(nativeCaptureSettingsHndl);
    }

    /* access modifiers changed from: package-private */
    public void registerCapturerError(AbstractCapturer.CapturerError ce) {
        this.capturer.registerCapturerError(ce);
    }

    /* access modifiers changed from: package-private */
    public void unregisterCapturerError(AbstractCapturer.CapturerError ce) {
        this.capturer.unregisterCapturerError(ce);
    }

    /* access modifiers changed from: package-private */
    public boolean isMirrorX() {
        return this.capturer.isMirrorX();
    }
}
