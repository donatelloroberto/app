package com.opentok.android.v3;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.Keep;
import android.util.Pair;
import android.util.SparseArray;
import android.view.Surface;
import com.opentok.android.v3.debug.LogInterface;
import com.opentok.android.v3.debug.OtLog;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.ThreadUtils;

@Keep
class MediaCodecVideoDecoder {
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka = 2141391874;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka = 2141391873;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka = 2141391875;
    private static final int DEQUEUE_INPUT_TIMEOUT = 500000;
    private static final String FORMAT_KEY_CROP_BOTTOM = "crop-bottom";
    private static final String FORMAT_KEY_CROP_LEFT = "crop-left";
    private static final String FORMAT_KEY_CROP_RIGHT = "crop-right";
    private static final String FORMAT_KEY_CROP_TOP = "crop-top";
    private static final String FORMAT_KEY_SLICE_HEIGHT = "slice-height";
    private static final String FORMAT_KEY_STRIDE = "stride";
    private static final String H264_MIME_TYPE = "video/avc";
    /* access modifiers changed from: private */
    public static final SupportedDecoderRecord[] H264_SUPPORT = {new SupportedDecoderRecord("OMX.qcom.", 19, SupportedDecoderRecord.Priority.HARDWARE, new HashMap<String, Integer>() {
        {
            put("HighProfile", 21);
        }
    }), new SupportedDecoderRecord("OMX.Intel.", 19, SupportedDecoderRecord.Priority.HARDWARE, (Map<String, Integer>) null), new SupportedDecoderRecord("OMX.Exynos.", 19, SupportedDecoderRecord.Priority.HARDWARE, new HashMap<String, Integer>() {
        {
            put("HighProfile", 23);
        }
    }), new SupportedDecoderRecord("OMX.IMG.", 19, SupportedDecoderRecord.Priority.HARDWARE, (Map<String, Integer>) null), new SupportedDecoderRecord("OMX.MTK.", 19, SupportedDecoderRecord.Priority.HARDWARE, (Map<String, Integer>) null), new SupportedDecoderRecord("OMX.google.h264.", 23, SupportedDecoderRecord.Priority.SOFTWARE, (Map<String, Integer>) null)};
    private static final long MAX_DECODE_TIME_MS = 200;
    private static final int MAX_QUEUED_OUTPUTBUFFERS = 3;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final SparseArray<Pair<String, SupportedDecoderRecord[]>> SupportedDecoderTbl = new SparseArray<Pair<String, SupportedDecoderRecord[]>>() {
        {
            append(VideoCodecType.VIDEO_CODEC_VP8.ordinal(), new Pair(MediaCodecVideoDecoder.VP8_MIME_TYPE, MediaCodecVideoDecoder.VP8_SUPPORT));
            append(VideoCodecType.VIDEO_CODEC_VP9.ordinal(), new Pair(MediaCodecVideoDecoder.VP9_MIME_TYPE, MediaCodecVideoDecoder.VP9_SUPPORT));
            append(VideoCodecType.VIDEO_CODEC_H264.ordinal(), new Pair(MediaCodecVideoDecoder.H264_MIME_TYPE, MediaCodecVideoDecoder.H264_SUPPORT));
        }
    };
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    /* access modifiers changed from: private */
    public static final SupportedDecoderRecord[] VP8_SUPPORT = new SupportedDecoderRecord[0];
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    /* access modifiers changed from: private */
    public static final SupportedDecoderRecord[] VP9_SUPPORT = {new SupportedDecoderRecord("OMX.qcom.", 19, SupportedDecoderRecord.Priority.HARDWARE, (Map<String, Integer>) null), new SupportedDecoderRecord("OMX.Exynos.", 19, SupportedDecoderRecord.Priority.HARDWARE, (Map<String, Integer>) null)};
    private static int codecErrors = 0;
    private static MediaCodecVideoDecoderErrorCallback errorCallback = null;
    private static Set<String> hwDecoderDisabledTypes = new HashSet();
    /* access modifiers changed from: private */
    public static final LogInterface log = OtLog.LogToken("[MediaCodecDecoder]");
    private static MediaCodecVideoDecoder runningInstance = null;
    private static final List<Integer> supportedColorList = Arrays.asList(new Integer[]{19, 21, 2141391872, Integer.valueOf(COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka), Integer.valueOf(COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka), Integer.valueOf(COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka), Integer.valueOf(COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m)});
    private int colorFormat;
    private final Queue<TimeStamps> decodeStartTimeMs = new LinkedList();
    private final Queue<DecodedOutputBuffer> dequeuedSurfaceOutputBuffers = new LinkedList();
    private int droppedFrames;
    private boolean hasDecodedFirstFrame;
    private int height;
    private ByteBuffer[] inputBuffers;
    /* access modifiers changed from: private */
    public MediaCodec mediaCodec;
    private Thread mediaCodecThread;
    private ByteBuffer[] outputBuffers;
    private int sliceHeight;
    private int stride;
    private Surface surface = null;
    private TextureListener textureListener;
    private boolean useSurface;
    private int width;

    public interface MediaCodecVideoDecoderErrorCallback {
        void onMediaCodecVideoDecoderCriticalError(int i);
    }

    public enum VideoCodecType {
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264
    }

    MediaCodecVideoDecoder() {
    }

    private static class SupportedDecoderRecord {
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

        public SupportedDecoderRecord(String codecPrefix, int androidVersion, Priority pri, Map<String, Integer> metaData) {
            this.prefix = codecPrefix;
            this.supportedVersion = androidVersion;
            this.priority = pri;
            this.meta = metaData == null ? new HashMap<>() : metaData;
        }
    }

    public static void setErrorCallback(MediaCodecVideoDecoderErrorCallback errorCallback2) {
        log.d("Set error callback", new Object[0]);
        errorCallback = errorCallback2;
    }

    public static void disableVp8HwCodec() {
        log.w("VP8 decoding is disabled by application.", new Object[0]);
        hwDecoderDisabledTypes.add(VP8_MIME_TYPE);
    }

    public static void disableVp9HwCodec() {
        log.w("VP9 decoding is disabled by application.", new Object[0]);
        hwDecoderDisabledTypes.add(VP9_MIME_TYPE);
    }

    public static void disableH264HwCodec() {
        log.w("H.264 decoding is disabled by application.", new Object[0]);
        hwDecoderDisabledTypes.add(H264_MIME_TYPE);
    }

    public static boolean isVp8HwSupported() {
        return !hwDecoderDisabledTypes.contains(VP8_MIME_TYPE) && findDecoder(VP8_MIME_TYPE, VP8_SUPPORT).length > 0;
    }

    public static boolean isVp9HwSupported() {
        return !hwDecoderDisabledTypes.contains(VP9_MIME_TYPE) && findDecoder(VP9_MIME_TYPE, VP9_SUPPORT).length > 0;
    }

    public static boolean isH264HwSupported() {
        return !hwDecoderDisabledTypes.contains(H264_MIME_TYPE) && findDecoder(H264_MIME_TYPE, H264_SUPPORT).length > 0;
    }

    public static boolean isH264HighProfileHwSupported() {
        DecoderProperties[] availDecoders = findDecoder(H264_MIME_TYPE, H264_SUPPORT);
        if (availDecoders.length <= 0 || !availDecoders[0].meta.containsKey("HighProfile") || Build.VERSION.SDK_INT < availDecoders[0].meta.get("HighProfile").intValue()) {
            return false;
        }
        return true;
    }

    public static void printStackTrace() {
        if (runningInstance != null && runningInstance.mediaCodecThread != null) {
            StackTraceElement[] mediaCodecStackTraces = runningInstance.mediaCodecThread.getStackTrace();
            if (mediaCodecStackTraces.length > 0) {
                log.d("MediaCodecVideoDecoder stacks trace:", new Object[0]);
                for (StackTraceElement stackTrace : mediaCodecStackTraces) {
                    log.d(stackTrace.toString(), new Object[0]);
                }
            }
        }
    }

    private static class DecoderProperties {
        public final String codecName;
        public final int colorFormat;
        public final Map<String, Integer> meta;

        public DecoderProperties(String codecName2, int colorFormat2, Map<String, Integer> meta2) {
            this.codecName = codecName2;
            this.colorFormat = colorFormat2;
            this.meta = meta2;
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
                if (info != null && !info.isEncoder()) {
                    codecList.add(info);
                }
            }
        }
        return (MediaCodecInfo[]) codecList.toArray(new MediaCodecInfo[codecList.size()]);
    }

    private static DecoderProperties[] findDecoder(String mime, SupportedDecoderRecord[] supportedList) {
        log.d("Trying to find HW decoder for mime " + mime, new Object[0]);
        List<Pair<DecoderProperties, SupportedDecoderRecord.Priority>> availableCodecs = new Vector<>();
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
                log.d("Found candidate decoder " + codec.getName(), new Object[0]);
                try {
                    List<Integer> codecColorFormats = createIntList(codec.getCapabilitiesForType(mime).colorFormats);
                    for (SupportedDecoderRecord supportedCodec : supportedList) {
                        if (Build.VERSION.SDK_INT >= supportedCodec.supportedVersion && codec.getName().startsWith(supportedCodec.prefix)) {
                            Iterator<Integer> it = supportedColorList.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                int supportedColorFormat = it.next().intValue();
                                if (codecColorFormats.contains(Integer.valueOf(supportedColorFormat))) {
                                    log.d("Found target decoder " + codec.getName() + ". Color: 0x" + Integer.toHexString(supportedColorFormat), new Object[0]);
                                    availableCodecs.add(new Pair(new DecoderProperties(codec.getName(), supportedColorFormat, supportedCodec.meta), supportedCodec.priority));
                                    break;
                                }
                            }
                        }
                    }
                } catch (IllegalArgumentException e) {
                    log.e("Cannot retreive decoder capabilities", e);
                } catch (Exception e2) {
                    log.e("general error", e2);
                    e2.printStackTrace();
                }
            }
            i = i2 + 1;
        }
        Collections.sort(availableCodecs, new Comparator<Pair<DecoderProperties, SupportedDecoderRecord.Priority>>() {
            public int compare(Pair<DecoderProperties, SupportedDecoderRecord.Priority> lhs, Pair<DecoderProperties, SupportedDecoderRecord.Priority> rhs) {
                return ((SupportedDecoderRecord.Priority) lhs.second).ordinal() - ((SupportedDecoderRecord.Priority) rhs.second).ordinal();
            }
        });
        int ndx = 0;
        DecoderProperties[] retLst = new DecoderProperties[availableCodecs.size()];
        for (Pair<DecoderProperties, SupportedDecoderRecord.Priority> itm : availableCodecs) {
            retLst[ndx] = (DecoderProperties) itm.first;
            ndx++;
        }
        return retLst;
    }

    private void checkOnMediaCodecThread() throws IllegalStateException {
        if (this.mediaCodecThread.getId() != Thread.currentThread().getId()) {
            throw new IllegalStateException("MediaCodecVideoDecoder previously operated on " + this.mediaCodecThread + " but is now called on " + Thread.currentThread());
        }
    }

    private boolean initDecode(VideoCodecType type, int width2, int height2, SurfaceTextureHelper surfaceTextureHelper) {
        if (this.mediaCodecThread != null) {
            throw new RuntimeException("initDecode: Forgot to release()?");
        }
        this.useSurface = surfaceTextureHelper != null;
        Pair<String, SupportedDecoderRecord[]> supportedCodecList = SupportedDecoderTbl.get(type.ordinal());
        DecoderProperties[] properties = findDecoder((String) supportedCodecList.first, (SupportedDecoderRecord[]) supportedCodecList.second);
        if (properties == null || properties.length == 0) {
            throw new RuntimeException("Cannot find HW decoder for " + type);
        }
        log.d("Java initDecode: " + type + " : " + width2 + " x " + height2 + ". Color: 0x" + Integer.toHexString(properties[0].colorFormat) + ". Use Surface: " + this.useSurface, new Object[0]);
        runningInstance = this;
        this.mediaCodecThread = Thread.currentThread();
        this.width = width2;
        this.height = height2;
        this.stride = width2;
        this.sliceHeight = height2;
        MediaFormat format = MediaFormat.createVideoFormat((String) supportedCodecList.first, width2, height2);
        boolean isDecoderCreated = false;
        int length = properties.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            DecoderProperties decoder = properties[i];
            try {
                if (this.useSurface) {
                    this.textureListener = new TextureListener(surfaceTextureHelper);
                    this.surface = new Surface(surfaceTextureHelper.getSurfaceTexture());
                } else {
                    format.setInteger("color-format", decoder.colorFormat);
                }
                log.d("  Format: " + format, new Object[0]);
                isDecoderCreated = initDecoder(decoder, format);
                if (true == isDecoderCreated) {
                    log.d("using decoder: " + decoder.codecName, new Object[0]);
                    break;
                }
                i++;
            } catch (Exception e) {
            }
        }
        return isDecoderCreated;
    }

    private boolean initDecoder(DecoderProperties decoder, MediaFormat format) {
        try {
            this.mediaCodec = MediaCodecVideoEncoder.createByCodecName(decoder.codecName);
            if (this.mediaCodec == null) {
                log.e("Can not create media decoder", new Object[0]);
                return false;
            }
            this.mediaCodec.configure(format, this.surface, (MediaCrypto) null, 0);
            this.mediaCodec.start();
            this.colorFormat = decoder.colorFormat;
            this.outputBuffers = this.mediaCodec.getOutputBuffers();
            this.inputBuffers = this.mediaCodec.getInputBuffers();
            this.decodeStartTimeMs.clear();
            this.hasDecodedFirstFrame = false;
            this.dequeuedSurfaceOutputBuffers.clear();
            this.droppedFrames = 0;
            log.d("Input buffers: " + this.inputBuffers.length + ". Output buffers: " + this.outputBuffers.length, new Object[0]);
            return true;
        } catch (IllegalStateException e) {
            log.e("initDecode failed", e);
            return false;
        }
    }

    private void reset(int width2, int height2) {
        if (this.mediaCodecThread == null || this.mediaCodec == null) {
            throw new RuntimeException("Incorrect reset call for non-initialized decoder.");
        }
        log.d("Java reset: " + width2 + " x " + height2, new Object[0]);
        this.mediaCodec.flush();
        this.width = width2;
        this.height = height2;
        this.decodeStartTimeMs.clear();
        this.dequeuedSurfaceOutputBuffers.clear();
        this.hasDecodedFirstFrame = false;
        this.droppedFrames = 0;
    }

    private void release() {
        log.d("Java releaseDecoder. Total number of dropped frames: " + this.droppedFrames, new Object[0]);
        checkOnMediaCodecThread();
        final CountDownLatch releaseDone = new CountDownLatch(1);
        new Thread(new Runnable() {
            public void run() {
                try {
                    MediaCodecVideoDecoder.log.d("Java releaseDecoder on release thread", new Object[0]);
                    MediaCodecVideoDecoder.this.mediaCodec.stop();
                    MediaCodecVideoDecoder.this.mediaCodec.release();
                    MediaCodecVideoDecoder.log.d("Java releaseDecoder on release thread done", new Object[0]);
                } catch (Exception e) {
                    MediaCodecVideoDecoder.log.e("Media decoder release failed", e);
                }
                releaseDone.countDown();
            }
        }).start();
        if (!ThreadUtils.awaitUninterruptibly(releaseDone, 5000)) {
            log.e("Media decoder release timeout", new Object[0]);
            codecErrors++;
            if (errorCallback != null) {
                log.e("Invoke codec error callback. Errors: " + codecErrors, new Object[0]);
                errorCallback.onMediaCodecVideoDecoderCriticalError(codecErrors);
            }
        }
        this.mediaCodec = null;
        this.mediaCodecThread = null;
        runningInstance = null;
        if (this.useSurface) {
            this.surface.release();
            this.surface = null;
            this.textureListener.release();
        }
        log.d("Java releaseDecoder done", new Object[0]);
    }

    private int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(500000);
        } catch (IllegalStateException e) {
            log.e("dequeueIntputBuffer failed", e);
            return -2;
        }
    }

    private boolean queueInputBuffer(int inputBufferIndex, int size, long presentationTimeStamUs, long timeStampMs, long ntpTimeStamp) {
        checkOnMediaCodecThread();
        try {
            this.inputBuffers[inputBufferIndex].position(0);
            this.inputBuffers[inputBufferIndex].limit(size);
            this.decodeStartTimeMs.add(new TimeStamps(SystemClock.elapsedRealtime(), timeStampMs, ntpTimeStamp));
            this.mediaCodec.queueInputBuffer(inputBufferIndex, 0, size, presentationTimeStamUs, 0);
            return true;
        } catch (IllegalStateException e) {
            log.e("decode failed", e);
            return false;
        }
    }

    private static class TimeStamps {
        /* access modifiers changed from: private */
        public final long decodeStartTimeMs;
        /* access modifiers changed from: private */
        public final long ntpTimeStampMs;
        /* access modifiers changed from: private */
        public final long timeStampMs;

        public TimeStamps(long decodeStartTimeMs2, long timeStampMs2, long ntpTimeStampMs2) {
            this.decodeStartTimeMs = decodeStartTimeMs2;
            this.timeStampMs = timeStampMs2;
            this.ntpTimeStampMs = ntpTimeStampMs2;
        }
    }

    private static class DecodedOutputBuffer {
        /* access modifiers changed from: private */
        public final long decodeTimeMs;
        /* access modifiers changed from: private */
        public final long endDecodeTimeMs;
        /* access modifiers changed from: private */
        public final int index;
        /* access modifiers changed from: private */
        public final long ntpTimeStampMs;
        private final int offset;
        /* access modifiers changed from: private */
        public final long presentationTimeStampMs;
        private final int size;
        /* access modifiers changed from: private */
        public final long timeStampMs;

        public DecodedOutputBuffer(int index2, int offset2, int size2, long presentationTimeStampMs2, long timeStampMs2, long ntpTimeStampMs2, long decodeTime, long endDecodeTime) {
            this.index = index2;
            this.offset = offset2;
            this.size = size2;
            this.presentationTimeStampMs = presentationTimeStampMs2;
            this.timeStampMs = timeStampMs2;
            this.ntpTimeStampMs = ntpTimeStampMs2;
            this.decodeTimeMs = decodeTime;
            this.endDecodeTimeMs = endDecodeTime;
        }
    }

    private static class DecodedTextureBuffer {
        private final long decodeTimeMs;
        private final long frameDelayMs;
        private final long ntpTimeStampMs;
        private final long presentationTimeStampMs;
        private final int textureID;
        private final long timeStampMs;
        private final float[] transformMatrix;

        public DecodedTextureBuffer(int textureID2, float[] transformMatrix2, long presentationTimeStampMs2, long timeStampMs2, long ntpTimeStampMs2, long decodeTimeMs2, long frameDelay) {
            this.textureID = textureID2;
            this.transformMatrix = transformMatrix2;
            this.presentationTimeStampMs = presentationTimeStampMs2;
            this.timeStampMs = timeStampMs2;
            this.ntpTimeStampMs = ntpTimeStampMs2;
            this.decodeTimeMs = decodeTimeMs2;
            this.frameDelayMs = frameDelay;
        }
    }

    private static class TextureListener implements SurfaceTextureHelper.OnTextureFrameAvailableListener {
        private DecodedOutputBuffer bufferToRender;
        private final Object newFrameLock = new Object();
        private DecodedTextureBuffer renderedBuffer;
        private final SurfaceTextureHelper surfaceTextureHelper;

        public TextureListener(SurfaceTextureHelper surfaceTextureHelper2) {
            this.surfaceTextureHelper = surfaceTextureHelper2;
            surfaceTextureHelper2.startListening(this);
        }

        public void addBufferToRender(DecodedOutputBuffer buffer) {
            if (this.bufferToRender != null) {
                MediaCodecVideoDecoder.log.e("Unexpected addBufferToRender() called while waiting for a texture.", new Object[0]);
                throw new IllegalStateException("Waiting for a texture.");
            } else {
                this.bufferToRender = buffer;
            }
        }

        public boolean isWaitingForTexture() {
            boolean z;
            synchronized (this.newFrameLock) {
                z = this.bufferToRender != null;
            }
            return z;
        }

        public void onTextureFrameAvailable(int oesTextureId, float[] transformMatrix, long timestampNs) {
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer != null) {
                    MediaCodecVideoDecoder.log.e("Unexpected onTextureFrameAvailable() called while already holding a texture.", new Object[0]);
                    throw new IllegalStateException("Already holding a texture.");
                }
                this.renderedBuffer = new DecodedTextureBuffer(oesTextureId, transformMatrix, this.bufferToRender.presentationTimeStampMs, this.bufferToRender.timeStampMs, this.bufferToRender.ntpTimeStampMs, this.bufferToRender.decodeTimeMs, SystemClock.elapsedRealtime() - this.bufferToRender.endDecodeTimeMs);
                this.bufferToRender = null;
                this.newFrameLock.notifyAll();
            }
        }

        public DecodedTextureBuffer dequeueTextureBuffer(int timeoutMs) {
            DecodedTextureBuffer returnedBuffer;
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer == null && timeoutMs > 0 && isWaitingForTexture()) {
                    try {
                        this.newFrameLock.wait((long) timeoutMs);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                returnedBuffer = this.renderedBuffer;
                this.renderedBuffer = null;
            }
            return returnedBuffer;
        }

        public void release() {
            this.surfaceTextureHelper.stopListening();
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer != null) {
                    this.surfaceTextureHelper.returnTextureFrame();
                    this.renderedBuffer = null;
                }
            }
        }
    }

    private DecodedOutputBuffer dequeueOutputBuffer(int dequeueTimeoutMs) {
        int newWidth;
        int newHeight;
        checkOnMediaCodecThread();
        if (this.decodeStartTimeMs.isEmpty()) {
            return null;
        }
        MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
        while (true) {
            int result = this.mediaCodec.dequeueOutputBuffer(info, TimeUnit.MILLISECONDS.toMicros((long) dequeueTimeoutMs));
            switch (result) {
                case -3:
                    this.outputBuffers = this.mediaCodec.getOutputBuffers();
                    log.d("Decoder output buffers changed: " + this.outputBuffers.length, new Object[0]);
                    if (!this.hasDecodedFirstFrame) {
                        break;
                    } else {
                        throw new RuntimeException("Unexpected output buffer change event.");
                    }
                case -2:
                    MediaFormat format = this.mediaCodec.getOutputFormat();
                    log.d("Decoder format changed: " + format.toString(), new Object[0]);
                    if (!format.containsKey(FORMAT_KEY_CROP_LEFT) || !format.containsKey(FORMAT_KEY_CROP_RIGHT) || !format.containsKey(FORMAT_KEY_CROP_BOTTOM) || !format.containsKey(FORMAT_KEY_CROP_TOP)) {
                        newWidth = format.getInteger("width");
                        newHeight = format.getInteger("height");
                    } else {
                        newWidth = (format.getInteger(FORMAT_KEY_CROP_RIGHT) + 1) - format.getInteger(FORMAT_KEY_CROP_LEFT);
                        newHeight = (format.getInteger(FORMAT_KEY_CROP_BOTTOM) + 1) - format.getInteger(FORMAT_KEY_CROP_TOP);
                    }
                    if (!this.hasDecodedFirstFrame || (newWidth == this.width && newHeight == this.height)) {
                        this.width = newWidth;
                        this.height = newHeight;
                        if (!this.useSurface && format.containsKey("color-format")) {
                            this.colorFormat = format.getInteger("color-format");
                            log.d("Color: 0x" + Integer.toHexString(this.colorFormat), new Object[0]);
                            if (!supportedColorList.contains(Integer.valueOf(this.colorFormat))) {
                                throw new IllegalStateException("Non supported color format: " + this.colorFormat);
                            }
                        }
                        if (format.containsKey(FORMAT_KEY_STRIDE)) {
                            this.stride = format.getInteger(FORMAT_KEY_STRIDE);
                        }
                        if (format.containsKey(FORMAT_KEY_SLICE_HEIGHT)) {
                            this.sliceHeight = format.getInteger(FORMAT_KEY_SLICE_HEIGHT);
                        }
                        log.d("Frame stride and slice height: " + this.stride + " x " + this.sliceHeight, new Object[0]);
                        this.stride = Math.max(this.width, this.stride);
                        this.sliceHeight = Math.max(this.height, this.sliceHeight);
                        break;
                    }
                    break;
                case -1:
                    return null;
                default:
                    this.hasDecodedFirstFrame = true;
                    TimeStamps timeStamps = this.decodeStartTimeMs.remove();
                    long decodeTimeMs = SystemClock.elapsedRealtime() - timeStamps.decodeStartTimeMs;
                    if (decodeTimeMs > MAX_DECODE_TIME_MS) {
                        log.e("Very high decode time: " + decodeTimeMs + "ms. Q size: " + this.decodeStartTimeMs.size() + ". Might be caused by resuming H264 decoding after a pause.", new Object[0]);
                        decodeTimeMs = MAX_DECODE_TIME_MS;
                    }
                    return new DecodedOutputBuffer(result, info.offset, info.size, TimeUnit.MICROSECONDS.toMillis(info.presentationTimeUs), timeStamps.timeStampMs, timeStamps.ntpTimeStampMs, decodeTimeMs, SystemClock.elapsedRealtime());
            }
        }
        throw new RuntimeException("Unexpected size change. Configured " + this.width + "*" + this.height + ". New " + newWidth + "*" + newHeight);
    }

    private DecodedTextureBuffer dequeueTextureBuffer(int dequeueTimeoutMs) {
        checkOnMediaCodecThread();
        if (!this.useSurface) {
            throw new IllegalStateException("dequeueTexture() called for byte buffer decoding.");
        }
        DecodedOutputBuffer outputBuffer = dequeueOutputBuffer(dequeueTimeoutMs);
        if (outputBuffer != null) {
            this.dequeuedSurfaceOutputBuffers.add(outputBuffer);
        }
        MaybeRenderDecodedTextureBuffer();
        DecodedTextureBuffer renderedBuffer = this.textureListener.dequeueTextureBuffer(dequeueTimeoutMs);
        if (renderedBuffer != null) {
            MaybeRenderDecodedTextureBuffer();
            return renderedBuffer;
        } else if (this.dequeuedSurfaceOutputBuffers.size() < Math.min(3, this.outputBuffers.length) && (dequeueTimeoutMs <= 0 || this.dequeuedSurfaceOutputBuffers.isEmpty())) {
            return null;
        } else {
            this.droppedFrames++;
            DecodedOutputBuffer droppedFrame = this.dequeuedSurfaceOutputBuffers.remove();
            if (dequeueTimeoutMs > 0) {
                log.w("Draining decoder. Dropping frame with TS: " + droppedFrame.presentationTimeStampMs + ". Total number of dropped frames: " + this.droppedFrames, new Object[0]);
            } else {
                log.w("Too many output buffers " + this.dequeuedSurfaceOutputBuffers.size() + ". Dropping frame with TS: " + droppedFrame.presentationTimeStampMs + ". Total number of dropped frames: " + this.droppedFrames, new Object[0]);
            }
            this.mediaCodec.releaseOutputBuffer(droppedFrame.index, false);
            return new DecodedTextureBuffer(0, (float[]) null, droppedFrame.presentationTimeStampMs, droppedFrame.timeStampMs, droppedFrame.ntpTimeStampMs, droppedFrame.decodeTimeMs, SystemClock.elapsedRealtime() - droppedFrame.endDecodeTimeMs);
        }
    }

    private void MaybeRenderDecodedTextureBuffer() {
        if (!this.dequeuedSurfaceOutputBuffers.isEmpty() && !this.textureListener.isWaitingForTexture()) {
            DecodedOutputBuffer buffer = this.dequeuedSurfaceOutputBuffers.remove();
            this.textureListener.addBufferToRender(buffer);
            this.mediaCodec.releaseOutputBuffer(buffer.index, true);
        }
    }

    private void returnDecodedOutputBuffer(int index) throws IllegalStateException, MediaCodec.CodecException {
        checkOnMediaCodecThread();
        if (this.useSurface) {
            throw new IllegalStateException("returnDecodedOutputBuffer() called for surface decoding.");
        }
        this.mediaCodec.releaseOutputBuffer(index, false);
    }

    private static List<Integer> createIntList(int[] array) {
        List<Integer> ret = new Vector<>(array.length);
        for (int i : array) {
            ret.add(Integer.valueOf(i));
        }
        return ret;
    }
}
