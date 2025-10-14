package com.opentok.android.v3;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.Surface;
import com.google.android.gms.common.Scopes;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.opentok.android.v3.debug.LogInterface;
import com.opentok.android.v3.debug.OtLog;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.EglBase;
import org.webrtc.EglBase14;
import org.webrtc.GlRectDrawer;
import org.webrtc.ThreadUtils;

@Keep
@TargetApi(19)
class MediaCodecVideoEncoder {
    private static final int BITRATE_ADJUSTMENT_FPS = 30;
    private static final double BITRATE_CORRECTION_MAX_SCALE = 4.0d;
    private static final double BITRATE_CORRECTION_SEC = 3.0d;
    private static final int BITRATE_CORRECTION_STEPS = 20;
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final int DEQUEUE_TIMEOUT = 0;
    private static final String[] H264_BLACKLIST = {"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4"};
    private static final String H264_MIME_TYPE = "video/avc";
    /* access modifiers changed from: private */
    public static final SupportedEncoderRecord[] H264_SUPPORT = {new SupportedEncoderRecord("OMX.qcom.", 19, SupportedEncoderRecord.Priority.HARDWARE, BitrateAdjustmentType.NO_ADJUSTMENT, (Map<String, Integer>) null), new SupportedEncoderRecord("OMX.Exynos.", 21, SupportedEncoderRecord.Priority.HARDWARE, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT, new HashMap<String, Integer>() {
        {
            put("HighProfile", 23);
        }
    }), new SupportedEncoderRecord("OMX.IMG.", 19, SupportedEncoderRecord.Priority.HARDWARE, BitrateAdjustmentType.NO_ADJUSTMENT, (Map<String, Integer>) null), new SupportedEncoderRecord("OMX.MTK.", 19, SupportedEncoderRecord.Priority.HARDWARE, BitrateAdjustmentType.NO_ADJUSTMENT, (Map<String, Integer>) null), new SupportedEncoderRecord("OMX.google.h264.", 23, SupportedEncoderRecord.Priority.SOFTWARE, BitrateAdjustmentType.NO_ADJUSTMENT, (Map<String, Integer>) null)};
    private static final SparseIntArray KeyFrameIntervalSecTbl = new SparseIntArray() {
        {
            append(VideoCodecType.VIDEO_CODEC_VP8.ordinal(), 100);
            append(VideoCodecType.VIDEO_CODEC_VP9.ordinal(), 100);
            append(VideoCodecType.VIDEO_CODEC_H264.ordinal(), 20);
        }
    };
    private static final int MAXIMUM_INITIAL_FPS = 30;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_L_MS = 15000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS = 20000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_N_MS = 15000;
    private static final SparseArray<Pair<String, SupportedEncoderRecord[]>> SupportedEncoderTbl = new SparseArray<Pair<String, SupportedEncoderRecord[]>>() {
        {
            append(VideoCodecType.VIDEO_CODEC_VP8.ordinal(), new Pair(MediaCodecVideoEncoder.VP8_MIME_TYPE, MediaCodecVideoEncoder.VP8_SUPPORT));
            append(VideoCodecType.VIDEO_CODEC_VP9.ordinal(), new Pair(MediaCodecVideoEncoder.VP9_MIME_TYPE, MediaCodecVideoEncoder.VP9_SUPPORT));
            append(VideoCodecType.VIDEO_CODEC_H264.ordinal(), new Pair(MediaCodecVideoEncoder.H264_MIME_TYPE, MediaCodecVideoEncoder.H264_SUPPORT));
        }
    };
    private static final int VIDEO_AVCLevel3 = 256;
    private static final int VIDEO_AVCProfileHigh = 8;
    private static final int VIDEO_ControlRateConstant = 2;
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    /* access modifiers changed from: private */
    public static final SupportedEncoderRecord[] VP8_SUPPORT = new SupportedEncoderRecord[0];
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    /* access modifiers changed from: private */
    public static final SupportedEncoderRecord[] VP9_SUPPORT = {new SupportedEncoderRecord("OMX.qcom.", 24, SupportedEncoderRecord.Priority.HARDWARE, BitrateAdjustmentType.NO_ADJUSTMENT, (Map<String, Integer>) null), new SupportedEncoderRecord("OMX.Exynos.", 24, SupportedEncoderRecord.Priority.HARDWARE, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT, (Map<String, Integer>) null)};
    private static int codecErrors = 0;
    private static MediaCodecVideoEncoderErrorCallback errorCallback = null;
    private static Set<String> hwEncoderDisabledTypes = new HashSet();
    /* access modifiers changed from: private */
    public static final LogInterface log = OtLog.LogToken("[MediaCodecEncoder]");
    private static MediaCodecVideoEncoder runningInstance = null;
    private static final int[] supportedColorList = {19, 21, 2141391872, COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m};
    private static final int[] supportedSurfaceColorList = {2130708361};
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

    MediaCodecVideoEncoder() {
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

    private static class SupportedEncoderRecord {
        public final BitrateAdjustmentType bitrateAdjustmentType;
        public final Map<String, Integer> meta;
        public final String prefix;
        public final Priority priority;
        public final int supportedVersion;

        public enum Priority {
            HARDWARE(100),
            SOFTWARE(10);
            
            private final int priority;

            private Priority(int priority2) {
                this.priority = priority2;
            }
        }

        public SupportedEncoderRecord(String codecPrefix, int androidVersion, Priority pri, BitrateAdjustmentType bitrateAdjustment, Map<String, Integer> metaInfo) {
            this.prefix = codecPrefix;
            this.supportedVersion = androidVersion;
            this.priority = pri;
            this.bitrateAdjustmentType = bitrateAdjustment;
            this.meta = metaInfo == null ? new HashMap<>() : metaInfo;
        }
    }

    public static void setErrorCallback(MediaCodecVideoEncoderErrorCallback errorCallback2) {
        log.d("Set error callback", new Object[0]);
        errorCallback = errorCallback2;
    }

    public static void disableVp8HwCodec() {
        log.w("VP8 encoding is disabled by application.", new Object[0]);
        hwEncoderDisabledTypes.add(VP8_MIME_TYPE);
    }

    public static void disableVp9HwCodec() {
        log.w("VP9 encoding is disabled by application.", new Object[0]);
        hwEncoderDisabledTypes.add(VP9_MIME_TYPE);
    }

    public static void disableH264HwCodec() {
        log.w("H.264 encoding is disabled by application.", new Object[0]);
        hwEncoderDisabledTypes.add(H264_MIME_TYPE);
    }

    public static boolean isVp8HwSupported() {
        return !hwEncoderDisabledTypes.contains(VP8_MIME_TYPE) && findHwEncoder(VP8_MIME_TYPE, VP8_SUPPORT, supportedColorList).length > 0;
    }

    public static boolean isVp9HwSupported() {
        return !hwEncoderDisabledTypes.contains(VP9_MIME_TYPE) && findHwEncoder(VP9_MIME_TYPE, VP9_SUPPORT, supportedColorList).length > 0;
    }

    public static boolean isH264HwSupported() {
        return !hwEncoderDisabledTypes.contains(H264_MIME_TYPE) && findHwEncoder(H264_MIME_TYPE, H264_SUPPORT, supportedColorList).length > 0;
    }

    public static boolean isH264HighProfileHwSupported() {
        EncoderProperties[] availEncoders = findHwEncoder(H264_MIME_TYPE, H264_SUPPORT, supportedColorList);
        if (availEncoders.length <= 0 || !availEncoders[0].meta.containsKey("HighProfile") || Build.VERSION.SDK_INT < availEncoders[0].meta.get("HighProfile").intValue()) {
            return false;
        }
        return true;
    }

    public static boolean isVp8HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains(VP8_MIME_TYPE) && findHwEncoder(VP8_MIME_TYPE, VP8_SUPPORT, supportedSurfaceColorList).length > 0;
    }

    public static boolean isVp9HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains(VP9_MIME_TYPE) && findHwEncoder(VP9_MIME_TYPE, VP9_SUPPORT, supportedSurfaceColorList).length > 0;
    }

    public static boolean isH264HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains(H264_MIME_TYPE) && findHwEncoder(H264_MIME_TYPE, H264_SUPPORT, supportedSurfaceColorList).length > 0;
    }

    public static class EncoderProperties {
        public final BitrateAdjustmentType bitrateAdjustmentType;
        public final String codecName;
        public final int colorFormat;
        public final Map<String, Integer> meta;

        public EncoderProperties(String codecName2, int colorFormat2, BitrateAdjustmentType bitrateAdjustmentType2, Map<String, Integer> metaInfo) {
            this.codecName = codecName2;
            this.colorFormat = colorFormat2;
            this.bitrateAdjustmentType = bitrateAdjustmentType2;
            this.meta = metaInfo;
        }
    }

    private static MediaCodecInfo[] getCodecList() {
        if (Build.VERSION.SDK_INT < 19) {
            return new MediaCodecInfo[0];
        }
        List<MediaCodecInfo> codecList = new Vector<>();
        int ndx = 0;
        while (ndx < MediaCodecList.getCodecCount()) {
            MediaCodecInfo info = null;
            try {
                info = MediaCodecList.getCodecInfoAt(ndx);
                ndx++;
            } finally {
                if (info != null && info.isEncoder()) {
                    codecList.add(info);
                }
            }
        }
        return (MediaCodecInfo[]) codecList.toArray(new MediaCodecInfo[codecList.size()]);
    }

    private static EncoderProperties[] findHwEncoder(String mime, SupportedEncoderRecord[] supportedList, int[] colorList) {
        log.d("Trying to find HW encoder for mime " + mime, new Object[0]);
        List<Pair<EncoderProperties, SupportedEncoderRecord.Priority>> availableCodecs = new Vector<>();
        MediaCodecInfo[] codecList = getCodecList();
        int length = codecList.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                break;
            }
            MediaCodecInfo codec = codecList[i2];
            if (Arrays.asList(codec.getSupportedTypes()).contains(mime)) {
                if (!mime.equals(H264_MIME_TYPE) || !Arrays.asList(H264_BLACKLIST).contains(Build.MODEL)) {
                    try {
                        List<Integer> codecColorFormats = createIntList(codec.getCapabilitiesForType(mime).colorFormats);
                        for (SupportedEncoderRecord supportedCodec : supportedList) {
                            if (Build.VERSION.SDK_INT >= supportedCodec.supportedVersion && codec.getName().startsWith(supportedCodec.prefix)) {
                                int length2 = colorList.length;
                                int i3 = 0;
                                while (true) {
                                    if (i3 >= length2) {
                                        break;
                                    }
                                    int supportedColorFormat = colorList[i3];
                                    if (codecColorFormats.contains(Integer.valueOf(supportedColorFormat))) {
                                        log.d("Found target encoder " + codec.getName() + ". Color: 0x" + Integer.toHexString(supportedColorFormat) + ". Bitrate adjustment: " + supportedCodec.bitrateAdjustmentType, new Object[0]);
                                        availableCodecs.add(new Pair(new EncoderProperties(codec.getName(), supportedColorFormat, supportedCodec.bitrateAdjustmentType, supportedCodec.meta), supportedCodec.priority));
                                        break;
                                    }
                                    i3++;
                                }
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        log.e("Cannot retreive decoder capabilities", e);
                    } catch (Exception e2) {
                        log.e("general error", e2);
                        e2.printStackTrace();
                    }
                } else {
                    log.w("Model: " + Build.MODEL + " is blacklisted for H264.", new Object[0]);
                }
            }
            i = i2 + 1;
        }
        Collections.sort(availableCodecs, new Comparator<Pair<EncoderProperties, SupportedEncoderRecord.Priority>>() {
            public int compare(Pair<EncoderProperties, SupportedEncoderRecord.Priority> lhs, Pair<EncoderProperties, SupportedEncoderRecord.Priority> rhs) {
                return ((SupportedEncoderRecord.Priority) lhs.second).ordinal() - ((SupportedEncoderRecord.Priority) rhs.second).ordinal();
            }
        });
        int ndx = 0;
        EncoderProperties[] retLst = new EncoderProperties[availableCodecs.size()];
        for (Pair<EncoderProperties, SupportedEncoderRecord.Priority> itm : availableCodecs) {
            retLst[ndx] = (EncoderProperties) itm.first;
            ndx++;
        }
        return retLst;
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
                log.d("MediaCodecVideoEncoder stacks trace:", new Object[0]);
                for (StackTraceElement stackTrace : mediaCodecStackTraces) {
                    log.d(stackTrace.toString(), new Object[0]);
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
        int min;
        log.d("Java initEncode: " + type2 + ". Profile: " + profile2 + " : " + width2 + " x " + height2 + ". @ " + kbps + " kbps. Fps: " + fps + ". Encode from texture : " + (sharedContext != null), new Object[0]);
        if (this.mediaCodecThread != null) {
            throw new RuntimeException("Forgot to release()?");
        }
        boolean useSurface = sharedContext != null;
        Pair<String, SupportedEncoderRecord[]> supportedCodecList = SupportedEncoderTbl.get(type2.ordinal());
        EncoderProperties[] properties = findHwEncoder((String) supportedCodecList.first, (SupportedEncoderRecord[]) supportedCodecList.second, useSurface ? supportedSurfaceColorList : supportedColorList);
        if (properties == null || properties.length == 0) {
            throw new RuntimeException("Can not find HW encoder for " + type2);
        }
        this.profile = profile2;
        this.width = width2;
        this.height = height2;
        runningInstance = this;
        this.bitrateAccumulator = 0.0d;
        this.bitrateObservationTimeMs = 0.0d;
        this.bitrateAdjustmentScaleExp = 0;
        this.targetBitrateBps = kbps * 1000;
        this.bitrateAccumulatorMax = ((double) this.targetBitrateBps) / 8.0d;
        this.mediaCodecThread = Thread.currentThread();
        for (EncoderProperties encoderProps : properties) {
            this.colorFormat = encoderProps.colorFormat;
            this.bitrateAdjustmentType = encoderProps.bitrateAdjustmentType;
            this.lastKeyFrameMs = -1;
            this.forcedKeyFrameMs = 0;
            if (this.bitrateAdjustmentType == BitrateAdjustmentType.FRAMERATE_ADJUSTMENT) {
                min = 30;
            } else {
                min = Math.min(fps, 30);
            }
            this.targetFps = min;
            if (type2 == VideoCodecType.VIDEO_CODEC_VP8 && encoderProps.codecName.startsWith("OMX.qcom.")) {
                if (Build.VERSION.SDK_INT == 21 || Build.VERSION.SDK_INT == 22) {
                    this.forcedKeyFrameMs = 15000;
                } else if (Build.VERSION.SDK_INT == 23) {
                    this.forcedKeyFrameMs = QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS;
                } else if (Build.VERSION.SDK_INT > 23) {
                    this.forcedKeyFrameMs = 15000;
                }
            }
            log.d("Color format: " + this.colorFormat + ". Bitrate adjustment: " + this.bitrateAdjustmentType + ". Key frame interval: " + this.forcedKeyFrameMs + " . Initial fps: " + fps, new Object[0]);
            if (initEncoder(type2, encoderProps, sharedContext, (String) supportedCodecList.first)) {
                log.d("[h.264] using encoder: " + encoderProps.codecName, new Object[0]);
                return true;
            }
            this.mediaCodecThread = Thread.currentThread();
            log.d("[h.264] encoder init failed: " + encoderProps.codecName, new Object[0]);
        }
        return false;
    }

    private boolean initEncoder(VideoCodecType type2, EncoderProperties encoder, EglBase14.Context sharedContext, String mime) {
        boolean useSurface;
        boolean configureH264HighProfile;
        if (sharedContext != null) {
            useSurface = true;
        } else {
            useSurface = false;
        }
        int keyFrameIntervalSec = KeyFrameIntervalSecTbl.get(type2.ordinal());
        if (this.profile != H264Profile.CONSTRAINED_HIGH.getValue() || !isH264HighProfileHwSupported()) {
            configureH264HighProfile = false;
        } else {
            configureH264HighProfile = true;
        }
        try {
            MediaFormat format = MediaFormat.createVideoFormat(mime, this.width, this.height);
            format.setInteger("bitrate", this.targetBitrateBps);
            format.setInteger("bitrate-mode", 2);
            format.setInteger("color-format", encoder.colorFormat);
            format.setInteger("frame-rate", this.targetFps);
            format.setInteger("i-frame-interval", keyFrameIntervalSec);
            if (configureH264HighProfile) {
                format.setInteger(Scopes.PROFILE, 8);
                format.setInteger(FirebaseAnalytics.Param.LEVEL, 256);
            }
            log.d("  Format: " + format, new Object[0]);
            this.mediaCodec = createByCodecName(encoder.codecName);
            this.type = type2;
            if (this.mediaCodec == null) {
                log.e("Can not create media encoder", new Object[0]);
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
            log.d("Output buffers: " + this.outputBuffers.length, new Object[0]);
            return true;
        } catch (Exception e) {
            log.e("initEncode failed", e);
            release();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public ByteBuffer[] getInputBuffers() {
        ByteBuffer[] inputBuffers = this.mediaCodec.getInputBuffers();
        log.d("Input buffers: " + inputBuffers.length, new Object[0]);
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
                log.d("Sync frame request", new Object[0]);
            } else {
                log.d("Sync frame forced", new Object[0]);
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
            log.e("encodeBuffer failed", e);
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
            log.e("encodeTexture failed", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void release() {
        log.d("Java releaseEncoder", new Object[0]);
        checkOnMediaCodecThread();
        final AnonymousClass1CaughtException caughtException = new Object() {
            Exception e;
        };
        boolean stopHung = false;
        if (this.mediaCodec != null) {
            final CountDownLatch releaseDone = new CountDownLatch(1);
            new Thread(new Runnable() {
                public void run() {
                    MediaCodecVideoEncoder.log.d("Java releaseEncoder on release thread", new Object[0]);
                    try {
                        MediaCodecVideoEncoder.this.mediaCodec.stop();
                    } catch (Exception e) {
                        MediaCodecVideoEncoder.log.e("Media encoder stop failed", e);
                    }
                    try {
                        MediaCodecVideoEncoder.this.mediaCodec.release();
                    } catch (Exception e2) {
                        MediaCodecVideoEncoder.log.e("Media encoder release failed", e2);
                        caughtException.e = e2;
                    }
                    MediaCodecVideoEncoder.log.d("Java releaseEncoder on release thread done", new Object[0]);
                    releaseDone.countDown();
                }
            }).start();
            if (!ThreadUtils.awaitUninterruptibly(releaseDone, 5000)) {
                log.e("Media encoder release timeout", new Object[0]);
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
                log.e("Invoke codec error callback. Errors: " + codecErrors, new Object[0]);
                errorCallback.onMediaCodecVideoEncoderCriticalError(codecErrors);
            }
            throw new RuntimeException("Media encoder release timeout.");
        } else if (caughtException.e != null) {
            throw new RuntimeException(caughtException.e);
        } else {
            log.d("Java releaseEncoder done", new Object[0]);
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
            log.v("setRates: " + kbps + " -> " + (codecBitrateBps / 1000) + " kbps. Fps: " + this.targetFps, new Object[0]);
        } else if (this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            log.v("setRates: " + kbps + " kbps. Fps: " + this.targetFps + ". ExpScale: " + this.bitrateAdjustmentScaleExp, new Object[0]);
            if (this.bitrateAdjustmentScaleExp != 0) {
                codecBitrateBps = (int) (((double) codecBitrateBps) * getBitrateScale(this.bitrateAdjustmentScaleExp));
            }
        } else {
            log.v("setRates: " + kbps + " kbps. Fps: " + this.targetFps, new Object[0]);
        }
        try {
            Bundle params = new Bundle();
            params.putInt("video-bitrate", codecBitrateBps);
            this.mediaCodec.setParameters(params);
            return true;
        } catch (IllegalStateException e) {
            log.e("setRates failed", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(0);
        } catch (IllegalStateException e) {
            log.e("dequeueIntputBuffer failed", e);
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
                    log.d("Config frame generated. Offset: " + info.offset + ". Size: " + info.size, new Object[0]);
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
                    log.d(spsData, new Object[0]);
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
                    log.d("Sync frame generated", new Object[0]);
                }
                if (!isKeyFrame || this.type != VideoCodecType.VIDEO_CODEC_H264) {
                    return new OutputBufferInfo(result, outputBuffer.slice(), isKeyFrame, info.presentationTimeUs);
                }
                log.d("Appending config frame of size " + this.configData.capacity() + " to output buffer with offset " + info.offset + ", size " + info.size, new Object[0]);
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
            log.e("dequeueOutputBuffer failed", e);
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
                log.d("Acc: " + ((int) this.bitrateAccumulator) + ". Max: " + ((int) this.bitrateAccumulatorMax) + ". ExpScale: " + this.bitrateAdjustmentScaleExp, new Object[0]);
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
                    log.d("Adjusting bitrate scale to " + this.bitrateAdjustmentScaleExp + ". Value: " + getBitrateScale(this.bitrateAdjustmentScaleExp), new Object[0]);
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
            log.e("releaseOutputBuffer failed", e);
            return false;
        }
    }

    private static List<Integer> createIntList(int[] array) {
        List<Integer> ret = new Vector<>(array.length);
        for (int i : array) {
            ret.add(Integer.valueOf(i));
        }
        return ret;
    }
}
