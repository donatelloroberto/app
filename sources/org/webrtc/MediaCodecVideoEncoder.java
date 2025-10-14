package org.webrtc;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import com.google.android.gms.common.Scopes;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.EglBase14;

@TargetApi(19)
public class MediaCodecVideoEncoder {
    private static final int BITRATE_ADJUSTMENT_FPS = 30;
    private static final double BITRATE_CORRECTION_MAX_SCALE = 4.0d;
    private static final double BITRATE_CORRECTION_SEC = 3.0d;
    private static final int BITRATE_CORRECTION_STEPS = 20;
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final int DEQUEUE_TIMEOUT = 0;
    private static final String[] H264_HW_EXCEPTION_MODELS = {"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4"};
    private static final String H264_MIME_TYPE = "video/avc";
    private static final int MAXIMUM_INITIAL_FPS = 30;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_L_MS = 15000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS = 20000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_N_MS = 15000;
    private static final String TAG = "MediaCodecVideoEncoder";
    private static final int VIDEO_AVCLevel3 = 256;
    private static final int VIDEO_AVCProfileHigh = 8;
    private static final int VIDEO_ControlRateConstant = 2;
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private static int codecErrors = 0;
    private static MediaCodecVideoEncoderErrorCallback errorCallback = null;
    private static final MediaCodecProperties exynosH264HighProfileHwProperties = new MediaCodecProperties("OMX.Exynos.", 23, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties exynosH264HwProperties = new MediaCodecProperties("OMX.Exynos.", 21, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties exynosVp8HwProperties = new MediaCodecProperties("OMX.Exynos.", 23, BitrateAdjustmentType.DYNAMIC_ADJUSTMENT);
    private static final MediaCodecProperties exynosVp9HwProperties = new MediaCodecProperties("OMX.Exynos.", 24, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties[] h264HighProfileHwList = {exynosH264HighProfileHwProperties};
    private static final MediaCodecProperties[] h264HwList = {qcomH264HwProperties, exynosH264HwProperties};
    private static Set<String> hwEncoderDisabledTypes = new HashSet();
    private static final MediaCodecProperties intelVp8HwProperties = new MediaCodecProperties("OMX.Intel.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties qcomH264HwProperties = new MediaCodecProperties("OMX.qcom.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties qcomVp8HwProperties = new MediaCodecProperties("OMX.qcom.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties qcomVp9HwProperties = new MediaCodecProperties("OMX.qcom.", 24, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static MediaCodecVideoEncoder runningInstance = null;
    private static final int[] supportedColorList = {19, 21, 2141391872, COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m};
    private static final int[] supportedSurfaceColorList = {2130708361};
    private static final MediaCodecProperties[] vp9HwList = {qcomVp9HwProperties, exynosVp9HwProperties};
    private double bitrateAccumulator;
    private double bitrateAccumulatorMax;
    private int bitrateAdjustmentScaleExp;
    private BitrateAdjustmentType bitrateAdjustmentType = BitrateAdjustmentType.NO_ADJUSTMENT;
    private double bitrateObservationTimeMs;
    private int colorFormat;
    private ByteBuffer configData = null;
    private GlRectDrawer drawer;
    private EglBase14 eglBase;
    private long forcedKeyFrameMs;
    private int height;
    private Surface inputSurface;
    private long lastKeyFrameMs;
    /* access modifiers changed from: private */
    public MediaCodec mediaCodec;
    private Thread mediaCodecThread;
    private ByteBuffer[] outputBuffers;
    private int profile;
    private int targetBitrateBps;
    private int targetFps;
    private VideoCodecType type;
    private int width;

    public enum BitrateAdjustmentType {
        NO_ADJUSTMENT,
        FRAMERATE_ADJUSTMENT,
        DYNAMIC_ADJUSTMENT
    }

    public interface MediaCodecVideoEncoderErrorCallback {
        void onMediaCodecVideoEncoderCriticalError(int i);
    }

    public enum VideoCodecType {
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264
    }

    public enum H264Profile {
        CONSTRAINED_BASELINE(0),
        BASELINE(1),
        MAIN(2),
        CONSTRAINED_HIGH(3),
        HIGH(4);
        
        private final int value;

        private H264Profile(int value2) {
            this.value = value2;
        }

        public int getValue() {
            return this.value;
        }
    }

    private static class MediaCodecProperties {
        public final BitrateAdjustmentType bitrateAdjustmentType;
        public final String codecPrefix;
        public final int minSdk;

        MediaCodecProperties(String codecPrefix2, int minSdk2, BitrateAdjustmentType bitrateAdjustmentType2) {
            this.codecPrefix = codecPrefix2;
            this.minSdk = minSdk2;
            this.bitrateAdjustmentType = bitrateAdjustmentType2;
        }
    }

    private static MediaCodecProperties[] vp8HwList() {
        ArrayList<MediaCodecProperties> supported_codecs = new ArrayList<>();
        supported_codecs.add(qcomVp8HwProperties);
        supported_codecs.add(exynosVp8HwProperties);
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-IntelVP8").equals("Enabled")) {
            supported_codecs.add(intelVp8HwProperties);
        }
        return (MediaCodecProperties[]) supported_codecs.toArray(new MediaCodecProperties[supported_codecs.size()]);
    }

    public static void setErrorCallback(MediaCodecVideoEncoderErrorCallback errorCallback2) {
        Logging.d(TAG, "Set error callback");
        errorCallback = errorCallback2;
    }

    public static void disableVp8HwCodec() {
        Logging.w(TAG, "VP8 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(VP8_MIME_TYPE);
    }

    public static void disableVp9HwCodec() {
        Logging.w(TAG, "VP9 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(VP9_MIME_TYPE);
    }

    public static void disableH264HwCodec() {
        Logging.w(TAG, "H.264 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(H264_MIME_TYPE);
    }

    public static boolean isVp8HwSupported() {
        return !hwEncoderDisabledTypes.contains(VP8_MIME_TYPE) && findHwEncoder(VP8_MIME_TYPE, vp8HwList(), supportedColorList) != null;
    }

    public static EncoderProperties vp8HwEncoderProperties() {
        if (hwEncoderDisabledTypes.contains(VP8_MIME_TYPE)) {
            return null;
        }
        return findHwEncoder(VP8_MIME_TYPE, vp8HwList(), supportedColorList);
    }

    public static boolean isVp9HwSupported() {
        return !hwEncoderDisabledTypes.contains(VP9_MIME_TYPE) && findHwEncoder(VP9_MIME_TYPE, vp9HwList, supportedColorList) != null;
    }

    public static boolean isH264HwSupported() {
        return !hwEncoderDisabledTypes.contains(H264_MIME_TYPE) && findHwEncoder(H264_MIME_TYPE, h264HwList, supportedColorList) != null;
    }

    public static boolean isH264HighProfileHwSupported() {
        return !hwEncoderDisabledTypes.contains(H264_MIME_TYPE) && findHwEncoder(H264_MIME_TYPE, h264HighProfileHwList, supportedColorList) != null;
    }

    public static boolean isVp8HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains(VP8_MIME_TYPE) && findHwEncoder(VP8_MIME_TYPE, vp8HwList(), supportedSurfaceColorList) != null;
    }

    public static boolean isVp9HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains(VP9_MIME_TYPE) && findHwEncoder(VP9_MIME_TYPE, vp9HwList, supportedSurfaceColorList) != null;
    }

    public static boolean isH264HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains(H264_MIME_TYPE) && findHwEncoder(H264_MIME_TYPE, h264HwList, supportedSurfaceColorList) != null;
    }

    public static class EncoderProperties {
        public final BitrateAdjustmentType bitrateAdjustmentType;
        public final String codecName;
        public final int colorFormat;

        public EncoderProperties(String codecName2, int colorFormat2, BitrateAdjustmentType bitrateAdjustmentType2) {
            this.codecName = codecName2;
            this.colorFormat = colorFormat2;
            this.bitrateAdjustmentType = bitrateAdjustmentType2;
        }
    }

    private static EncoderProperties findHwEncoder(String mime, MediaCodecProperties[] supportedHwCodecProperties, int[] colorList) {
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        if (!mime.equals(H264_MIME_TYPE) || !Arrays.asList(H264_HW_EXCEPTION_MODELS).contains(Build.MODEL)) {
            for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
                MediaCodecInfo info = null;
                try {
                    info = MediaCodecList.getCodecInfoAt(i);
                } catch (IllegalArgumentException e) {
                    Logging.e(TAG, "Cannot retrieve encoder codec info", e);
                }
                if (info != null && info.isEncoder()) {
                    String name = null;
                    String[] supportedTypes = info.getSupportedTypes();
                    int length = supportedTypes.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (supportedTypes[i2].equals(mime)) {
                            name = info.getName();
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (name != null) {
                        Logging.v(TAG, "Found candidate encoder " + name);
                        boolean supportedCodec = false;
                        BitrateAdjustmentType bitrateAdjustmentType2 = BitrateAdjustmentType.NO_ADJUSTMENT;
                        int length2 = supportedHwCodecProperties.length;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= length2) {
                                break;
                            }
                            MediaCodecProperties codecProperties = supportedHwCodecProperties[i3];
                            if (name.startsWith(codecProperties.codecPrefix)) {
                                if (Build.VERSION.SDK_INT < codecProperties.minSdk) {
                                    Logging.w(TAG, "Codec " + name + " is disabled due to SDK version " + Build.VERSION.SDK_INT);
                                } else {
                                    if (codecProperties.bitrateAdjustmentType != BitrateAdjustmentType.NO_ADJUSTMENT) {
                                        bitrateAdjustmentType2 = codecProperties.bitrateAdjustmentType;
                                        Logging.w(TAG, "Codec " + name + " requires bitrate adjustment: " + bitrateAdjustmentType2);
                                    }
                                    supportedCodec = true;
                                }
                            }
                            i3++;
                        }
                        if (supportedCodec) {
                            try {
                                MediaCodecInfo.CodecCapabilities capabilities = info.getCapabilitiesForType(mime);
                                int[] iArr = capabilities.colorFormats;
                                int length3 = iArr.length;
                                for (int i4 = 0; i4 < length3; i4++) {
                                    Logging.v(TAG, "   Color: 0x" + Integer.toHexString(iArr[i4]));
                                }
                                int length4 = colorList.length;
                                int i5 = 0;
                                while (true) {
                                    int i6 = i5;
                                    if (i6 >= length4) {
                                        continue;
                                        break;
                                    }
                                    int supportedColorFormat = colorList[i6];
                                    for (int codecColorFormat : capabilities.colorFormats) {
                                        if (codecColorFormat == supportedColorFormat) {
                                            Logging.d(TAG, "Found target encoder for mime " + mime + " : " + name + ". Color: 0x" + Integer.toHexString(codecColorFormat) + ". Bitrate adjustment: " + bitrateAdjustmentType2);
                                            return new EncoderProperties(name, codecColorFormat, bitrateAdjustmentType2);
                                        }
                                    }
                                    i5 = i6 + 1;
                                }
                            } catch (IllegalArgumentException e2) {
                                Logging.e(TAG, "Cannot retrieve encoder capabilities", e2);
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
            return null;
        }
        Logging.w(TAG, "Model: " + Build.MODEL + " has black listed H.264 encoder.");
        return null;
    }

    private void checkOnMediaCodecThread() {
        if (this.mediaCodecThread.getId() != Thread.currentThread().getId()) {
            throw new RuntimeException("MediaCodecVideoEncoder previously operated on " + this.mediaCodecThread + " but is now called on " + Thread.currentThread());
        }
    }

    public static void printStackTrace() {
        if (runningInstance != null && runningInstance.mediaCodecThread != null) {
            StackTraceElement[] mediaCodecStackTraces = runningInstance.mediaCodecThread.getStackTrace();
            if (mediaCodecStackTraces.length > 0) {
                Logging.d(TAG, "MediaCodecVideoEncoder stacks trace:");
                for (StackTraceElement stackTrace : mediaCodecStackTraces) {
                    Logging.d(TAG, stackTrace.toString());
                }
            }
        }
    }

    static MediaCodec createByCodecName(String codecName) {
        try {
            return MediaCodec.createByCodecName(codecName);
        } catch (Exception e) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean initEncode(VideoCodecType type2, int profile2, int width2, int height2, int kbps, int fps, EglBase14.Context sharedContext) {
        int fps2;
        boolean useSurface = sharedContext != null;
        Logging.d(TAG, "Java initEncode: " + type2 + ". Profile: " + profile2 + " : " + width2 + " x " + height2 + ". @ " + kbps + " kbps. Fps: " + fps + ". Encode from texture : " + useSurface);
        this.profile = profile2;
        this.width = width2;
        this.height = height2;
        if (this.mediaCodecThread != null) {
            throw new RuntimeException("Forgot to release()?");
        }
        EncoderProperties properties = null;
        String mime = null;
        int keyFrameIntervalSec = 0;
        boolean configureH264HighProfile = false;
        if (type2 == VideoCodecType.VIDEO_CODEC_VP8) {
            mime = VP8_MIME_TYPE;
            properties = findHwEncoder(VP8_MIME_TYPE, vp8HwList(), useSurface ? supportedSurfaceColorList : supportedColorList);
            keyFrameIntervalSec = 100;
        } else if (type2 == VideoCodecType.VIDEO_CODEC_VP9) {
            mime = VP9_MIME_TYPE;
            properties = findHwEncoder(VP9_MIME_TYPE, vp9HwList, useSurface ? supportedSurfaceColorList : supportedColorList);
            keyFrameIntervalSec = 100;
        } else if (type2 == VideoCodecType.VIDEO_CODEC_H264) {
            mime = H264_MIME_TYPE;
            properties = findHwEncoder(H264_MIME_TYPE, h264HwList, useSurface ? supportedSurfaceColorList : supportedColorList);
            if (profile2 == H264Profile.CONSTRAINED_HIGH.getValue()) {
                if (findHwEncoder(H264_MIME_TYPE, h264HighProfileHwList, useSurface ? supportedSurfaceColorList : supportedColorList) != null) {
                    Logging.d(TAG, "High profile H.264 encoder supported.");
                    configureH264HighProfile = true;
                } else {
                    Logging.d(TAG, "High profile H.264 encoder requested, but not supported. Use baseline.");
                }
            }
            keyFrameIntervalSec = 20;
        }
        if (properties == null) {
            throw new RuntimeException("Can not find HW encoder for " + type2);
        }
        runningInstance = this;
        this.colorFormat = properties.colorFormat;
        this.bitrateAdjustmentType = properties.bitrateAdjustmentType;
        if (this.bitrateAdjustmentType == BitrateAdjustmentType.FRAMERATE_ADJUSTMENT) {
            fps2 = 30;
        } else {
            fps2 = Math.min(fps, 30);
        }
        this.forcedKeyFrameMs = 0;
        this.lastKeyFrameMs = -1;
        if (type2 == VideoCodecType.VIDEO_CODEC_VP8 && properties.codecName.startsWith(qcomVp8HwProperties.codecPrefix)) {
            if (Build.VERSION.SDK_INT == 21 || Build.VERSION.SDK_INT == 22) {
                this.forcedKeyFrameMs = 15000;
            } else if (Build.VERSION.SDK_INT == 23) {
                this.forcedKeyFrameMs = QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS;
            } else if (Build.VERSION.SDK_INT > 23) {
                this.forcedKeyFrameMs = 15000;
            }
        }
        Logging.d(TAG, "Color format: " + this.colorFormat + ". Bitrate adjustment: " + this.bitrateAdjustmentType + ". Key frame interval: " + this.forcedKeyFrameMs + " . Initial fps: " + fps2);
        this.targetBitrateBps = kbps * 1000;
        this.targetFps = fps2;
        this.bitrateAccumulatorMax = ((double) this.targetBitrateBps) / 8.0d;
        this.bitrateAccumulator = 0.0d;
        this.bitrateObservationTimeMs = 0.0d;
        this.bitrateAdjustmentScaleExp = 0;
        this.mediaCodecThread = Thread.currentThread();
        try {
            MediaFormat format = MediaFormat.createVideoFormat(mime, width2, height2);
            format.setInteger("bitrate", this.targetBitrateBps);
            format.setInteger("bitrate-mode", 2);
            format.setInteger("color-format", properties.colorFormat);
            format.setInteger("frame-rate", this.targetFps);
            format.setInteger("i-frame-interval", keyFrameIntervalSec);
            if (configureH264HighProfile) {
                format.setInteger(Scopes.PROFILE, 8);
                format.setInteger(FirebaseAnalytics.Param.LEVEL, 256);
            }
            Logging.d(TAG, "  Format: " + format);
            this.mediaCodec = createByCodecName(properties.codecName);
            this.type = type2;
            if (this.mediaCodec == null) {
                Logging.e(TAG, "Can not create media encoder");
                release();
                return false;
            }
            this.mediaCodec.configure(format, (Surface) null, (MediaCrypto) null, 1);
            if (useSurface) {
                this.eglBase = new EglBase14(sharedContext, EglBase.CONFIG_RECORDABLE);
                this.inputSurface = this.mediaCodec.createInputSurface();
                this.eglBase.createSurface(this.inputSurface);
                this.drawer = new GlRectDrawer();
            }
            this.mediaCodec.start();
            this.outputBuffers = this.mediaCodec.getOutputBuffers();
            Logging.d(TAG, "Output buffers: " + this.outputBuffers.length);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "initEncode failed", e);
            release();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public ByteBuffer[] getInputBuffers() {
        ByteBuffer[] inputBuffers = this.mediaCodec.getInputBuffers();
        Logging.d(TAG, "Input buffers: " + inputBuffers.length);
        return inputBuffers;
    }

    /* access modifiers changed from: package-private */
    public void checkKeyFrameRequired(boolean requestedKeyFrame, long presentationTimestampUs) {
        long presentationTimestampMs = (500 + presentationTimestampUs) / 1000;
        if (this.lastKeyFrameMs < 0) {
            this.lastKeyFrameMs = presentationTimestampMs;
        }
        boolean forcedKeyFrame = false;
        if (!requestedKeyFrame && this.forcedKeyFrameMs > 0 && presentationTimestampMs > this.lastKeyFrameMs + this.forcedKeyFrameMs) {
            forcedKeyFrame = true;
        }
        if (requestedKeyFrame || forcedKeyFrame) {
            if (requestedKeyFrame) {
                Logging.d(TAG, "Sync frame request");
            } else {
                Logging.d(TAG, "Sync frame forced");
            }
            Bundle b = new Bundle();
            b.putInt("request-sync", 0);
            this.mediaCodec.setParameters(b);
            this.lastKeyFrameMs = presentationTimestampMs;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean encodeBuffer(boolean isKeyframe, int inputBuffer, int size, long presentationTimestampUs) {
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(isKeyframe, presentationTimestampUs);
            this.mediaCodec.queueInputBuffer(inputBuffer, 0, size, presentationTimestampUs, 0);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "encodeBuffer failed", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean encodeTexture(boolean isKeyframe, int oesTextureId, float[] transformationMatrix, long presentationTimestampUs) {
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(isKeyframe, presentationTimestampUs);
            this.eglBase.makeCurrent();
            GLES20.glClear(16384);
            this.drawer.drawOes(oesTextureId, transformationMatrix, this.width, this.height, 0, 0, this.width, this.height);
            this.eglBase.swapBuffers(TimeUnit.MICROSECONDS.toNanos(presentationTimestampUs));
            return true;
        } catch (RuntimeException e) {
            Logging.e(TAG, "encodeTexture failed", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void release() {
        Logging.d(TAG, "Java releaseEncoder");
        checkOnMediaCodecThread();
        final AnonymousClass1CaughtException caughtException = new Object() {
            Exception e;
        };
        boolean stopHung = false;
        if (this.mediaCodec != null) {
            final CountDownLatch releaseDone = new CountDownLatch(1);
            new Thread(new Runnable() {
                public void run() {
                    Logging.d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread");
                    try {
                        MediaCodecVideoEncoder.this.mediaCodec.stop();
                    } catch (Exception e) {
                        Logging.e(MediaCodecVideoEncoder.TAG, "Media encoder stop failed", e);
                    }
                    try {
                        MediaCodecVideoEncoder.this.mediaCodec.release();
                    } catch (Exception e2) {
                        Logging.e(MediaCodecVideoEncoder.TAG, "Media encoder release failed", e2);
                        caughtException.e = e2;
                    }
                    Logging.d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread done");
                    releaseDone.countDown();
                }
            }).start();
            if (!ThreadUtils.awaitUninterruptibly(releaseDone, 5000)) {
                Logging.e(TAG, "Media encoder release timeout");
                stopHung = true;
            }
            this.mediaCodec = null;
        }
        this.mediaCodecThread = null;
        if (this.drawer != null) {
            this.drawer.release();
            this.drawer = null;
        }
        if (this.eglBase != null) {
            this.eglBase.release();
            this.eglBase = null;
        }
        if (this.inputSurface != null) {
            this.inputSurface.release();
            this.inputSurface = null;
        }
        runningInstance = null;
        if (stopHung) {
            codecErrors++;
            if (errorCallback != null) {
                Logging.e(TAG, "Invoke codec error callback. Errors: " + codecErrors);
                errorCallback.onMediaCodecVideoEncoderCriticalError(codecErrors);
            }
            throw new RuntimeException("Media encoder release timeout.");
        } else if (caughtException.e != null) {
            RuntimeException runtimeException = new RuntimeException(caughtException.e);
            runtimeException.setStackTrace(ThreadUtils.concatStackTraces(caughtException.e.getStackTrace(), runtimeException.getStackTrace()));
            throw runtimeException;
        } else {
            Logging.d(TAG, "Java releaseEncoder done");
        }
    }

    private boolean setRates(int kbps, int frameRate) {
        checkOnMediaCodecThread();
        int codecBitrateBps = kbps * 1000;
        if (this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            this.bitrateAccumulatorMax = ((double) codecBitrateBps) / 8.0d;
            if (this.targetBitrateBps > 0 && codecBitrateBps < this.targetBitrateBps) {
                this.bitrateAccumulator = (this.bitrateAccumulator * ((double) codecBitrateBps)) / ((double) this.targetBitrateBps);
            }
        }
        this.targetBitrateBps = codecBitrateBps;
        this.targetFps = frameRate;
        if (this.bitrateAdjustmentType == BitrateAdjustmentType.FRAMERATE_ADJUSTMENT && this.targetFps > 0) {
            codecBitrateBps = (this.targetBitrateBps * 30) / this.targetFps;
            Logging.v(TAG, "setRates: " + kbps + " -> " + (codecBitrateBps / 1000) + " kbps. Fps: " + this.targetFps);
        } else if (this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            Logging.v(TAG, "setRates: " + kbps + " kbps. Fps: " + this.targetFps + ". ExpScale: " + this.bitrateAdjustmentScaleExp);
            if (this.bitrateAdjustmentScaleExp != 0) {
                codecBitrateBps = (int) (((double) codecBitrateBps) * getBitrateScale(this.bitrateAdjustmentScaleExp));
            }
        } else {
            Logging.v(TAG, "setRates: " + kbps + " kbps. Fps: " + this.targetFps);
        }
        try {
            Bundle params = new Bundle();
            params.putInt("video-bitrate", codecBitrateBps);
            this.mediaCodec.setParameters(params);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "setRates failed", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(0);
        } catch (IllegalStateException e) {
            Logging.e(TAG, "dequeueIntputBuffer failed", e);
            return -2;
        }
    }

    static class OutputBufferInfo {
        public final ByteBuffer buffer;
        public final int index;
        public final boolean isKeyFrame;
        public final long presentationTimestampUs;

        public OutputBufferInfo(int index2, ByteBuffer buffer2, boolean isKeyFrame2, long presentationTimestampUs2) {
            this.index = index2;
            this.buffer = buffer2;
            this.isKeyFrame = isKeyFrame2;
            this.presentationTimestampUs = presentationTimestampUs2;
        }
    }

    /* access modifiers changed from: package-private */
    public OutputBufferInfo dequeueOutputBuffer() {
        checkOnMediaCodecThread();
        try {
            MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
            int result = this.mediaCodec.dequeueOutputBuffer(info, 0);
            if (result >= 0) {
                if ((info.flags & 2) != 0) {
                    Logging.d(TAG, "Config frame generated. Offset: " + info.offset + ". Size: " + info.size);
                    this.configData = ByteBuffer.allocateDirect(info.size);
                    this.outputBuffers[result].position(info.offset);
                    this.outputBuffers[result].limit(info.offset + info.size);
                    this.configData.put(this.outputBuffers[result]);
                    String spsData = "";
                    int i = 0;
                    while (true) {
                        if (i >= (info.size < 8 ? info.size : 8)) {
                            break;
                        }
                        spsData = spsData + Integer.toHexString(this.configData.get(i) & 255) + " ";
                        i++;
                    }
                    Logging.d(TAG, spsData);
                    this.mediaCodec.releaseOutputBuffer(result, false);
                    result = this.mediaCodec.dequeueOutputBuffer(info, 0);
                }
            }
            if (result >= 0) {
                ByteBuffer outputBuffer = this.outputBuffers[result].duplicate();
                outputBuffer.position(info.offset);
                outputBuffer.limit(info.offset + info.size);
                reportEncodedFrame(info.size);
                boolean isKeyFrame = (info.flags & 1) != 0;
                if (isKeyFrame) {
                    Logging.d(TAG, "Sync frame generated");
                }
                if (!isKeyFrame || this.type != VideoCodecType.VIDEO_CODEC_H264) {
                    return new OutputBufferInfo(result, outputBuffer.slice(), isKeyFrame, info.presentationTimeUs);
                }
                Logging.d(TAG, "Appending config frame of size " + this.configData.capacity() + " to output buffer with offset " + info.offset + ", size " + info.size);
                ByteBuffer keyFrameBuffer = ByteBuffer.allocateDirect(this.configData.capacity() + info.size);
                this.configData.rewind();
                keyFrameBuffer.put(this.configData);
                keyFrameBuffer.put(outputBuffer);
                keyFrameBuffer.position(0);
                return new OutputBufferInfo(result, keyFrameBuffer, isKeyFrame, info.presentationTimeUs);
            } else if (result == -3) {
                this.outputBuffers = this.mediaCodec.getOutputBuffers();
                return dequeueOutputBuffer();
            } else if (result == -2) {
                return dequeueOutputBuffer();
            } else {
                if (result == -1) {
                    return null;
                }
                throw new RuntimeException("dequeueOutputBuffer: " + result);
            }
        } catch (IllegalStateException e) {
            Logging.e(TAG, "dequeueOutputBuffer failed", e);
            return new OutputBufferInfo(-1, (ByteBuffer) null, false, -1);
        }
    }

    private double getBitrateScale(int bitrateAdjustmentScaleExp2) {
        return Math.pow(BITRATE_CORRECTION_MAX_SCALE, ((double) bitrateAdjustmentScaleExp2) / 20.0d);
    }

    private void reportEncodedFrame(int size) {
        if (this.targetFps != 0 && this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            this.bitrateAccumulator += ((double) size) - (((double) this.targetBitrateBps) / (8.0d * ((double) this.targetFps)));
            this.bitrateObservationTimeMs += 1000.0d / ((double) this.targetFps);
            double bitrateAccumulatorCap = BITRATE_CORRECTION_SEC * this.bitrateAccumulatorMax;
            this.bitrateAccumulator = Math.min(this.bitrateAccumulator, bitrateAccumulatorCap);
            this.bitrateAccumulator = Math.max(this.bitrateAccumulator, -bitrateAccumulatorCap);
            if (this.bitrateObservationTimeMs > 3000.0d) {
                Logging.d(TAG, "Acc: " + ((int) this.bitrateAccumulator) + ". Max: " + ((int) this.bitrateAccumulatorMax) + ". ExpScale: " + this.bitrateAdjustmentScaleExp);
                boolean bitrateAdjustmentScaleChanged = false;
                if (this.bitrateAccumulator > this.bitrateAccumulatorMax) {
                    this.bitrateAdjustmentScaleExp -= (int) ((this.bitrateAccumulator / this.bitrateAccumulatorMax) + 0.5d);
                    this.bitrateAccumulator = this.bitrateAccumulatorMax;
                    bitrateAdjustmentScaleChanged = true;
                } else if (this.bitrateAccumulator < (-this.bitrateAccumulatorMax)) {
                    this.bitrateAdjustmentScaleExp += (int) (((-this.bitrateAccumulator) / this.bitrateAccumulatorMax) + 0.5d);
                    this.bitrateAccumulator = -this.bitrateAccumulatorMax;
                    bitrateAdjustmentScaleChanged = true;
                }
                if (bitrateAdjustmentScaleChanged) {
                    this.bitrateAdjustmentScaleExp = Math.min(this.bitrateAdjustmentScaleExp, 20);
                    this.bitrateAdjustmentScaleExp = Math.max(this.bitrateAdjustmentScaleExp, -20);
                    Logging.d(TAG, "Adjusting bitrate scale to " + this.bitrateAdjustmentScaleExp + ". Value: " + getBitrateScale(this.bitrateAdjustmentScaleExp));
                    setRates(this.targetBitrateBps / 1000, this.targetFps);
                }
                this.bitrateObservationTimeMs = 0.0d;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean releaseOutputBuffer(int index) {
        checkOnMediaCodecThread();
        try {
            this.mediaCodec.releaseOutputBuffer(index, false);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "releaseOutputBuffer failed", e);
            return false;
        }
    }
}
