package org.webrtc;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.SystemClock;
import android.view.Surface;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;
import org.webrtc.EncodedImage;
import org.webrtc.ThreadUtils;
import org.webrtc.VideoDecoder;
import org.webrtc.VideoFrame;

@TargetApi(16)
class HardwareVideoDecoder implements VideoDecoder {
    private static final int DEQUEUE_INPUT_TIMEOUT_US = 500000;
    private static final int DEQUEUE_OUTPUT_BUFFER_TIMEOUT_US = 100000;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final String MEDIA_FORMAT_KEY_CROP_BOTTOM = "crop-bottom";
    private static final String MEDIA_FORMAT_KEY_CROP_LEFT = "crop-left";
    private static final String MEDIA_FORMAT_KEY_CROP_RIGHT = "crop-right";
    private static final String MEDIA_FORMAT_KEY_CROP_TOP = "crop-top";
    private static final String MEDIA_FORMAT_KEY_SLICE_HEIGHT = "slice-height";
    private static final String MEDIA_FORMAT_KEY_STRIDE = "stride";
    private static final String TAG = "HardwareVideoDecoder";
    private int activeOutputBuffers = 0;
    /* access modifiers changed from: private */
    public final Object activeOutputBuffersLock = new Object();
    private VideoDecoder.Callback callback;
    /* access modifiers changed from: private */
    public MediaCodec codec = null;
    private final String codecName;
    private final VideoCodecType codecType;
    private int colorFormat;
    private ThreadUtils.ThreadChecker decoderThreadChecker;
    private final Object dimensionLock = new Object();
    private final Deque<FrameInfo> frameInfos;
    private boolean hasDecodedFirstFrame;
    private int height;
    private boolean keyFrameRequired;
    private Thread outputThread;
    /* access modifiers changed from: private */
    public ThreadUtils.ThreadChecker outputThreadChecker;
    /* access modifiers changed from: private */
    public volatile boolean running = false;
    private volatile Exception shutdownException = null;
    private int sliceHeight;
    private int stride;
    private int width;

    static /* synthetic */ int access$610(HardwareVideoDecoder x0) {
        int i = x0.activeOutputBuffers;
        x0.activeOutputBuffers = i - 1;
        return i;
    }

    private static class FrameInfo {
        final long decodeStartTimeMs;
        final int rotation;

        FrameInfo(long decodeStartTimeMs2, int rotation2) {
            this.decodeStartTimeMs = decodeStartTimeMs2;
            this.rotation = rotation2;
        }
    }

    HardwareVideoDecoder(String codecName2, VideoCodecType codecType2, int colorFormat2) {
        if (!isSupportedColorFormat(colorFormat2)) {
            throw new IllegalArgumentException("Unsupported color format: " + colorFormat2);
        }
        this.codecName = codecName2;
        this.codecType = codecType2;
        this.colorFormat = colorFormat2;
        this.frameInfos = new LinkedBlockingDeque();
    }

    public VideoCodecStatus initDecode(VideoDecoder.Settings settings, VideoDecoder.Callback callback2) {
        this.decoderThreadChecker = new ThreadUtils.ThreadChecker();
        return initDecodeInternal(settings.width, settings.height, callback2);
    }

    private VideoCodecStatus initDecodeInternal(int width2, int height2, VideoDecoder.Callback callback2) {
        this.decoderThreadChecker.checkIsOnValidThread();
        if (this.outputThread != null) {
            Logging.e(TAG, "initDecodeInternal called while the codec is already running");
            return VideoCodecStatus.ERROR;
        }
        this.callback = callback2;
        this.width = width2;
        this.height = height2;
        this.stride = width2;
        this.sliceHeight = height2;
        this.hasDecodedFirstFrame = false;
        this.keyFrameRequired = true;
        try {
            this.codec = MediaCodec.createByCodecName(this.codecName);
            try {
                MediaFormat format = MediaFormat.createVideoFormat(this.codecType.mimeType(), width2, height2);
                format.setInteger("color-format", this.colorFormat);
                this.codec.configure(format, (Surface) null, (MediaCrypto) null, 0);
                this.codec.start();
                this.running = true;
                this.outputThread = createOutputThread();
                this.outputThread.start();
                return VideoCodecStatus.OK;
            } catch (IllegalStateException e) {
                Logging.e(TAG, "initDecode failed", e);
                release();
                return VideoCodecStatus.ERROR;
            }
        } catch (IOException | IllegalArgumentException e2) {
            Logging.e(TAG, "Cannot create media decoder " + this.codecName);
            return VideoCodecStatus.ERROR;
        }
    }

    public VideoCodecStatus decode(EncodedImage frame, VideoDecoder.DecodeInfo info) {
        int width2;
        int height2;
        VideoCodecStatus status;
        this.decoderThreadChecker.checkIsOnValidThread();
        if (this.codec == null || this.callback == null) {
            return VideoCodecStatus.UNINITIALIZED;
        }
        if (frame.buffer == null) {
            Logging.e(TAG, "decode() - no input data");
            return VideoCodecStatus.ERR_PARAMETER;
        }
        int size = frame.buffer.remaining();
        if (size == 0) {
            Logging.e(TAG, "decode() - input buffer empty");
            return VideoCodecStatus.ERR_PARAMETER;
        }
        synchronized (this.dimensionLock) {
            width2 = this.width;
            height2 = this.height;
        }
        if (frame.encodedWidth * frame.encodedHeight > 0 && ((frame.encodedWidth != width2 || frame.encodedHeight != height2) && (status = reinitDecode(frame.encodedWidth, frame.encodedHeight)) != VideoCodecStatus.OK)) {
            return status;
        }
        if (this.keyFrameRequired) {
            if (frame.frameType != EncodedImage.FrameType.VideoFrameKey) {
                Logging.e(TAG, "decode() - key frame required first");
                return VideoCodecStatus.ERROR;
            } else if (!frame.completeFrame) {
                Logging.e(TAG, "decode() - complete frame required first");
                return VideoCodecStatus.ERROR;
            }
        }
        try {
            int index = this.codec.dequeueInputBuffer(500000);
            if (index < 0) {
                Logging.e(TAG, "decode() - no HW buffers available; decoder falling behind");
                return VideoCodecStatus.ERROR;
            }
            try {
                ByteBuffer buffer = this.codec.getInputBuffers()[index];
                if (buffer.capacity() < size) {
                    Logging.e(TAG, "decode() - HW buffer too small");
                    return VideoCodecStatus.ERROR;
                }
                buffer.put(frame.buffer);
                this.frameInfos.offer(new FrameInfo(SystemClock.elapsedRealtime(), frame.rotation));
                try {
                    this.codec.queueInputBuffer(index, 0, size, frame.captureTimeMs * 1000, 0);
                    if (this.keyFrameRequired) {
                        this.keyFrameRequired = false;
                    }
                    return VideoCodecStatus.OK;
                } catch (IllegalStateException e) {
                    Logging.e(TAG, "queueInputBuffer failed", e);
                    this.frameInfos.pollLast();
                    return VideoCodecStatus.ERROR;
                }
            } catch (IllegalStateException e2) {
                Logging.e(TAG, "getInputBuffers failed", e2);
                return VideoCodecStatus.ERROR;
            }
        } catch (IllegalStateException e3) {
            Logging.e(TAG, "dequeueInputBuffer failed", e3);
            return VideoCodecStatus.ERROR;
        }
    }

    public boolean getPrefersLateDecoding() {
        return true;
    }

    public String getImplementationName() {
        return "HardwareVideoDecoder: " + this.codecName;
    }

    public VideoCodecStatus release() {
        try {
            this.running = false;
            if (!ThreadUtils.joinUninterruptibly(this.outputThread, 5000)) {
                Logging.e(TAG, "Media encoder release timeout", new RuntimeException());
                return VideoCodecStatus.TIMEOUT;
            } else if (this.shutdownException != null) {
                Logging.e(TAG, "Media encoder release error", new RuntimeException(this.shutdownException));
                this.shutdownException = null;
                VideoCodecStatus videoCodecStatus = VideoCodecStatus.ERROR;
                this.codec = null;
                this.callback = null;
                this.outputThread = null;
                this.frameInfos.clear();
                return videoCodecStatus;
            } else {
                this.codec = null;
                this.callback = null;
                this.outputThread = null;
                this.frameInfos.clear();
                return VideoCodecStatus.OK;
            }
        } finally {
            this.codec = null;
            this.callback = null;
            this.outputThread = null;
            this.frameInfos.clear();
        }
    }

    private VideoCodecStatus reinitDecode(int newWidth, int newHeight) {
        this.decoderThreadChecker.checkIsOnValidThread();
        VideoCodecStatus status = release();
        return status != VideoCodecStatus.OK ? status : initDecodeInternal(newWidth, newHeight, this.callback);
    }

    private Thread createOutputThread() {
        return new Thread("HardwareVideoDecoder.outputThread") {
            public void run() {
                ThreadUtils.ThreadChecker unused = HardwareVideoDecoder.this.outputThreadChecker = new ThreadUtils.ThreadChecker();
                while (HardwareVideoDecoder.this.running) {
                    HardwareVideoDecoder.this.deliverDecodedFrame();
                }
                HardwareVideoDecoder.this.releaseCodecOnOutputThread();
            }
        };
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void deliverDecodedFrame() {
        /*
            r28 = this;
            r0 = r28
            org.webrtc.ThreadUtils$ThreadChecker r4 = r0.outputThreadChecker
            r4.checkIsOnValidThread()
            android.media.MediaCodec$BufferInfo r22 = new android.media.MediaCodec$BufferInfo     // Catch:{ IllegalStateException -> 0x0047 }
            r22.<init>()     // Catch:{ IllegalStateException -> 0x0047 }
            r0 = r28
            android.media.MediaCodec r4 = r0.codec     // Catch:{ IllegalStateException -> 0x0047 }
            r24 = 100000(0x186a0, double:4.94066E-319)
            r0 = r22
            r1 = r24
            int r6 = r4.dequeueOutputBuffer(r0, r1)     // Catch:{ IllegalStateException -> 0x0047 }
            r4 = -2
            if (r6 != r4) goto L_0x002c
            r0 = r28
            android.media.MediaCodec r4 = r0.codec     // Catch:{ IllegalStateException -> 0x0047 }
            android.media.MediaFormat r4 = r4.getOutputFormat()     // Catch:{ IllegalStateException -> 0x0047 }
            r0 = r28
            r0.reformat(r4)     // Catch:{ IllegalStateException -> 0x0047 }
        L_0x002b:
            return
        L_0x002c:
            if (r6 >= 0) goto L_0x0052
            java.lang.String r4 = "HardwareVideoDecoder"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IllegalStateException -> 0x0047 }
            r7.<init>()     // Catch:{ IllegalStateException -> 0x0047 }
            java.lang.String r12 = "dequeueOutputBuffer returned "
            java.lang.StringBuilder r7 = r7.append(r12)     // Catch:{ IllegalStateException -> 0x0047 }
            java.lang.StringBuilder r7 = r7.append(r6)     // Catch:{ IllegalStateException -> 0x0047 }
            java.lang.String r7 = r7.toString()     // Catch:{ IllegalStateException -> 0x0047 }
            org.webrtc.Logging.v(r4, r7)     // Catch:{ IllegalStateException -> 0x0047 }
            goto L_0x002b
        L_0x0047:
            r20 = move-exception
            java.lang.String r4 = "HardwareVideoDecoder"
            java.lang.String r7 = "deliverDecodedFrame failed"
            r0 = r20
            org.webrtc.Logging.e(r4, r7, r0)
            goto L_0x002b
        L_0x0052:
            r0 = r28
            java.util.Deque<org.webrtc.HardwareVideoDecoder$FrameInfo> r4 = r0.frameInfos     // Catch:{ IllegalStateException -> 0x0047 }
            java.lang.Object r21 = r4.poll()     // Catch:{ IllegalStateException -> 0x0047 }
            org.webrtc.HardwareVideoDecoder$FrameInfo r21 = (org.webrtc.HardwareVideoDecoder.FrameInfo) r21     // Catch:{ IllegalStateException -> 0x0047 }
            r19 = 0
            r23 = 0
            if (r21 == 0) goto L_0x007b
            long r24 = android.os.SystemClock.elapsedRealtime()     // Catch:{ IllegalStateException -> 0x0047 }
            r0 = r21
            long r0 = r0.decodeStartTimeMs     // Catch:{ IllegalStateException -> 0x0047 }
            r26 = r0
            long r24 = r24 - r26
            r0 = r24
            int r4 = (int) r0     // Catch:{ IllegalStateException -> 0x0047 }
            java.lang.Integer r19 = java.lang.Integer.valueOf(r4)     // Catch:{ IllegalStateException -> 0x0047 }
            r0 = r21
            int r0 = r0.rotation     // Catch:{ IllegalStateException -> 0x0047 }
            r23 = r0
        L_0x007b:
            r4 = 1
            r0 = r28
            r0.hasDecodedFirstFrame = r4     // Catch:{ IllegalStateException -> 0x0047 }
            r0 = r28
            java.lang.Object r7 = r0.dimensionLock     // Catch:{ IllegalStateException -> 0x0047 }
            monitor-enter(r7)     // Catch:{ IllegalStateException -> 0x0047 }
            r0 = r28
            int r10 = r0.width     // Catch:{ all -> 0x00c0 }
            r0 = r28
            int r11 = r0.height     // Catch:{ all -> 0x00c0 }
            r0 = r28
            int r8 = r0.stride     // Catch:{ all -> 0x00c0 }
            r0 = r28
            int r9 = r0.sliceHeight     // Catch:{ all -> 0x00c0 }
            monitor-exit(r7)     // Catch:{ all -> 0x00c0 }
            r0 = r22
            int r4 = r0.size     // Catch:{ IllegalStateException -> 0x0047 }
            int r7 = r10 * r11
            int r7 = r7 * 3
            int r7 = r7 / 2
            if (r4 >= r7) goto L_0x00c3
            java.lang.String r4 = "HardwareVideoDecoder"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IllegalStateException -> 0x0047 }
            r7.<init>()     // Catch:{ IllegalStateException -> 0x0047 }
            java.lang.String r12 = "Insufficient output buffer size: "
            java.lang.StringBuilder r7 = r7.append(r12)     // Catch:{ IllegalStateException -> 0x0047 }
            r0 = r22
            int r12 = r0.size     // Catch:{ IllegalStateException -> 0x0047 }
            java.lang.StringBuilder r7 = r7.append(r12)     // Catch:{ IllegalStateException -> 0x0047 }
            java.lang.String r7 = r7.toString()     // Catch:{ IllegalStateException -> 0x0047 }
            org.webrtc.Logging.e(r4, r7)     // Catch:{ IllegalStateException -> 0x0047 }
            goto L_0x002b
        L_0x00c0:
            r4 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x00c0 }
            throw r4     // Catch:{ IllegalStateException -> 0x0047 }
        L_0x00c3:
            r0 = r22
            int r4 = r0.size     // Catch:{ IllegalStateException -> 0x0047 }
            int r7 = r8 * r11
            int r7 = r7 * 3
            int r7 = r7 / 2
            if (r4 >= r7) goto L_0x00dd
            if (r9 != r11) goto L_0x00dd
            if (r8 <= r10) goto L_0x00dd
            r0 = r22
            int r4 = r0.size     // Catch:{ IllegalStateException -> 0x0047 }
            int r4 = r4 * 2
            int r7 = r11 * 3
            int r8 = r4 / r7
        L_0x00dd:
            r0 = r28
            android.media.MediaCodec r4 = r0.codec     // Catch:{ IllegalStateException -> 0x0047 }
            java.nio.ByteBuffer[] r4 = r4.getOutputBuffers()     // Catch:{ IllegalStateException -> 0x0047 }
            r5 = r4[r6]     // Catch:{ IllegalStateException -> 0x0047 }
            r0 = r22
            int r4 = r0.offset     // Catch:{ IllegalStateException -> 0x0047 }
            r5.position(r4)     // Catch:{ IllegalStateException -> 0x0047 }
            r0 = r22
            int r4 = r0.size     // Catch:{ IllegalStateException -> 0x0047 }
            r5.limit(r4)     // Catch:{ IllegalStateException -> 0x0047 }
            r0 = r28
            int r4 = r0.colorFormat     // Catch:{ IllegalStateException -> 0x0047 }
            r7 = 19
            if (r4 != r7) goto L_0x014d
            int r4 = r9 % 2
            if (r4 != 0) goto L_0x0130
            r0 = r22
            int r7 = r0.offset     // Catch:{ IllegalStateException -> 0x0047 }
            r4 = r28
            org.webrtc.VideoFrame$I420Buffer r14 = r4.createBufferFromI420(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ IllegalStateException -> 0x0047 }
        L_0x010b:
            r0 = r22
            long r0 = r0.presentationTimeUs     // Catch:{ IllegalStateException -> 0x0047 }
            r24 = r0
            r26 = 1000(0x3e8, double:4.94E-321)
            long r16 = r24 * r26
            org.webrtc.VideoFrame r13 = new org.webrtc.VideoFrame     // Catch:{ IllegalStateException -> 0x0047 }
            android.graphics.Matrix r18 = new android.graphics.Matrix     // Catch:{ IllegalStateException -> 0x0047 }
            r18.<init>()     // Catch:{ IllegalStateException -> 0x0047 }
            r15 = r23
            r13.<init>(r14, r15, r16, r18)     // Catch:{ IllegalStateException -> 0x0047 }
            r0 = r28
            org.webrtc.VideoDecoder$Callback r4 = r0.callback     // Catch:{ IllegalStateException -> 0x0047 }
            r7 = 0
            r0 = r19
            r4.onDecodedFrame(r13, r0, r7)     // Catch:{ IllegalStateException -> 0x0047 }
            r13.release()     // Catch:{ IllegalStateException -> 0x0047 }
            goto L_0x002b
        L_0x0130:
            org.webrtc.I420BufferImpl r14 = new org.webrtc.I420BufferImpl     // Catch:{ IllegalStateException -> 0x0047 }
            r14.<init>(r10, r11)     // Catch:{ IllegalStateException -> 0x0047 }
            r0 = r22
            int r13 = r0.offset     // Catch:{ IllegalStateException -> 0x0047 }
            r12 = r5
            r15 = r8
            r16 = r9
            r17 = r10
            r18 = r11
            copyI420(r12, r13, r14, r15, r16, r17, r18)     // Catch:{ IllegalStateException -> 0x0047 }
            r0 = r28
            android.media.MediaCodec r4 = r0.codec     // Catch:{ IllegalStateException -> 0x0047 }
            r7 = 0
            r4.releaseOutputBuffer(r6, r7)     // Catch:{ IllegalStateException -> 0x0047 }
            goto L_0x010b
        L_0x014d:
            org.webrtc.I420BufferImpl r14 = new org.webrtc.I420BufferImpl     // Catch:{ IllegalStateException -> 0x0047 }
            r14.<init>(r10, r11)     // Catch:{ IllegalStateException -> 0x0047 }
            r0 = r22
            int r13 = r0.offset     // Catch:{ IllegalStateException -> 0x0047 }
            r12 = r5
            r15 = r8
            r16 = r9
            r17 = r10
            r18 = r11
            nv12ToI420(r12, r13, r14, r15, r16, r17, r18)     // Catch:{ IllegalStateException -> 0x0047 }
            r0 = r28
            android.media.MediaCodec r4 = r0.codec     // Catch:{ IllegalStateException -> 0x0047 }
            r7 = 0
            r4.releaseOutputBuffer(r6, r7)     // Catch:{ IllegalStateException -> 0x0047 }
            goto L_0x010b
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.HardwareVideoDecoder.deliverDecodedFrame():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00c7, code lost:
        if (r7.containsKey("color-format") == false) goto L_0x0118;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c9, code lost:
        r6.colorFormat = r7.getInteger("color-format");
        org.webrtc.Logging.d(TAG, "Color: 0x" + java.lang.Integer.toHexString(r6.colorFormat));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00f5, code lost:
        if (isSupportedColorFormat(r6.colorFormat) != false) goto L_0x0118;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00f7, code lost:
        stopOnOutputThread(new java.lang.IllegalStateException("Unsupported color format: " + r6.colorFormat));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0118, code lost:
        r3 = r6.dimensionLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x011a, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0121, code lost:
        if (r7.containsKey(MEDIA_FORMAT_KEY_STRIDE) == false) goto L_0x012b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0123, code lost:
        r6.stride = r7.getInteger(MEDIA_FORMAT_KEY_STRIDE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0131, code lost:
        if (r7.containsKey(MEDIA_FORMAT_KEY_SLICE_HEIGHT) == false) goto L_0x013b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0133, code lost:
        r6.sliceHeight = r7.getInteger(MEDIA_FORMAT_KEY_SLICE_HEIGHT);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x013b, code lost:
        org.webrtc.Logging.d(TAG, "Frame stride and slice height: " + r6.stride + " x " + r6.sliceHeight);
        r6.stride = java.lang.Math.max(r6.width, r6.stride);
        r6.sliceHeight = java.lang.Math.max(r6.height, r6.sliceHeight);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0175, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void reformat(android.media.MediaFormat r7) {
        /*
            r6 = this;
            org.webrtc.ThreadUtils$ThreadChecker r2 = r6.outputThreadChecker
            r2.checkIsOnValidThread()
            java.lang.String r2 = "HardwareVideoDecoder"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Decoder format changed: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = r7.toString()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            org.webrtc.Logging.d(r2, r3)
            java.lang.String r2 = "crop-left"
            boolean r2 = r7.containsKey(r2)
            if (r2 == 0) goto L_0x00af
            java.lang.String r2 = "crop-right"
            boolean r2 = r7.containsKey(r2)
            if (r2 == 0) goto L_0x00af
            java.lang.String r2 = "crop-bottom"
            boolean r2 = r7.containsKey(r2)
            if (r2 == 0) goto L_0x00af
            java.lang.String r2 = "crop-top"
            boolean r2 = r7.containsKey(r2)
            if (r2 == 0) goto L_0x00af
            java.lang.String r2 = "crop-right"
            int r2 = r7.getInteger(r2)
            int r2 = r2 + 1
            java.lang.String r3 = "crop-left"
            int r3 = r7.getInteger(r3)
            int r1 = r2 - r3
            java.lang.String r2 = "crop-bottom"
            int r2 = r7.getInteger(r2)
            int r2 = r2 + 1
            java.lang.String r3 = "crop-top"
            int r3 = r7.getInteger(r3)
            int r0 = r2 - r3
        L_0x0061:
            java.lang.Object r3 = r6.dimensionLock
            monitor-enter(r3)
            boolean r2 = r6.hasDecodedFirstFrame     // Catch:{ all -> 0x0115 }
            if (r2 == 0) goto L_0x00bc
            int r2 = r6.width     // Catch:{ all -> 0x0115 }
            if (r2 != r1) goto L_0x0070
            int r2 = r6.height     // Catch:{ all -> 0x0115 }
            if (r2 == r0) goto L_0x00bc
        L_0x0070:
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch:{ all -> 0x0115 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0115 }
            r4.<init>()     // Catch:{ all -> 0x0115 }
            java.lang.String r5 = "Unexpected size change. Configured "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0115 }
            int r5 = r6.width     // Catch:{ all -> 0x0115 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0115 }
            java.lang.String r5 = "*"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0115 }
            int r5 = r6.height     // Catch:{ all -> 0x0115 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0115 }
            java.lang.String r5 = ". New "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0115 }
            java.lang.StringBuilder r4 = r4.append(r1)     // Catch:{ all -> 0x0115 }
            java.lang.String r5 = "*"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0115 }
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ all -> 0x0115 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0115 }
            r2.<init>(r4)     // Catch:{ all -> 0x0115 }
            r6.stopOnOutputThread(r2)     // Catch:{ all -> 0x0115 }
            monitor-exit(r3)     // Catch:{ all -> 0x0115 }
        L_0x00ae:
            return
        L_0x00af:
            java.lang.String r2 = "width"
            int r1 = r7.getInteger(r2)
            java.lang.String r2 = "height"
            int r0 = r7.getInteger(r2)
            goto L_0x0061
        L_0x00bc:
            r6.width = r1     // Catch:{ all -> 0x0115 }
            r6.height = r0     // Catch:{ all -> 0x0115 }
            monitor-exit(r3)     // Catch:{ all -> 0x0115 }
            java.lang.String r2 = "color-format"
            boolean r2 = r7.containsKey(r2)
            if (r2 == 0) goto L_0x0118
            java.lang.String r2 = "color-format"
            int r2 = r7.getInteger(r2)
            r6.colorFormat = r2
            java.lang.String r2 = "HardwareVideoDecoder"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Color: 0x"
            java.lang.StringBuilder r3 = r3.append(r4)
            int r4 = r6.colorFormat
            java.lang.String r4 = java.lang.Integer.toHexString(r4)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            org.webrtc.Logging.d(r2, r3)
            int r2 = r6.colorFormat
            boolean r2 = r6.isSupportedColorFormat(r2)
            if (r2 != 0) goto L_0x0118
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Unsupported color format: "
            java.lang.StringBuilder r3 = r3.append(r4)
            int r4 = r6.colorFormat
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            r6.stopOnOutputThread(r2)
            goto L_0x00ae
        L_0x0115:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0115 }
            throw r2
        L_0x0118:
            java.lang.Object r3 = r6.dimensionLock
            monitor-enter(r3)
            java.lang.String r2 = "stride"
            boolean r2 = r7.containsKey(r2)     // Catch:{ all -> 0x0178 }
            if (r2 == 0) goto L_0x012b
            java.lang.String r2 = "stride"
            int r2 = r7.getInteger(r2)     // Catch:{ all -> 0x0178 }
            r6.stride = r2     // Catch:{ all -> 0x0178 }
        L_0x012b:
            java.lang.String r2 = "slice-height"
            boolean r2 = r7.containsKey(r2)     // Catch:{ all -> 0x0178 }
            if (r2 == 0) goto L_0x013b
            java.lang.String r2 = "slice-height"
            int r2 = r7.getInteger(r2)     // Catch:{ all -> 0x0178 }
            r6.sliceHeight = r2     // Catch:{ all -> 0x0178 }
        L_0x013b:
            java.lang.String r2 = "HardwareVideoDecoder"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0178 }
            r4.<init>()     // Catch:{ all -> 0x0178 }
            java.lang.String r5 = "Frame stride and slice height: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0178 }
            int r5 = r6.stride     // Catch:{ all -> 0x0178 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0178 }
            java.lang.String r5 = " x "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0178 }
            int r5 = r6.sliceHeight     // Catch:{ all -> 0x0178 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0178 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0178 }
            org.webrtc.Logging.d(r2, r4)     // Catch:{ all -> 0x0178 }
            int r2 = r6.width     // Catch:{ all -> 0x0178 }
            int r4 = r6.stride     // Catch:{ all -> 0x0178 }
            int r2 = java.lang.Math.max(r2, r4)     // Catch:{ all -> 0x0178 }
            r6.stride = r2     // Catch:{ all -> 0x0178 }
            int r2 = r6.height     // Catch:{ all -> 0x0178 }
            int r4 = r6.sliceHeight     // Catch:{ all -> 0x0178 }
            int r2 = java.lang.Math.max(r2, r4)     // Catch:{ all -> 0x0178 }
            r6.sliceHeight = r2     // Catch:{ all -> 0x0178 }
            monitor-exit(r3)     // Catch:{ all -> 0x0178 }
            goto L_0x00ae
        L_0x0178:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0178 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.HardwareVideoDecoder.reformat(android.media.MediaFormat):void");
    }

    /* access modifiers changed from: private */
    public void releaseCodecOnOutputThread() {
        this.outputThreadChecker.checkIsOnValidThread();
        Logging.d(TAG, "Releasing MediaCodec on output thread");
        waitOutputBuffersReleasedOnOutputThread();
        try {
            this.codec.stop();
        } catch (Exception e) {
            Logging.e(TAG, "Media decoder stop failed", e);
        }
        try {
            this.codec.release();
        } catch (Exception e2) {
            Logging.e(TAG, "Media decoder release failed", e2);
            this.shutdownException = e2;
        }
        this.codec = null;
        this.callback = null;
        this.outputThread = null;
        this.frameInfos.clear();
        Logging.d(TAG, "Release on output thread done");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void waitOutputBuffersReleasedOnOutputThread() {
        /*
            r4 = this;
            org.webrtc.ThreadUtils$ThreadChecker r1 = r4.outputThreadChecker
            r1.checkIsOnValidThread()
            java.lang.Object r2 = r4.activeOutputBuffersLock
            monitor-enter(r2)
        L_0x0008:
            int r1 = r4.activeOutputBuffers     // Catch:{ all -> 0x0025 }
            if (r1 <= 0) goto L_0x0023
            java.lang.String r1 = "HardwareVideoDecoder"
            java.lang.String r3 = "Waiting for all frames to be released."
            org.webrtc.Logging.d(r1, r3)     // Catch:{ all -> 0x0025 }
            java.lang.Object r1 = r4.activeOutputBuffersLock     // Catch:{ InterruptedException -> 0x0019 }
            r1.wait()     // Catch:{ InterruptedException -> 0x0019 }
            goto L_0x0008
        L_0x0019:
            r0 = move-exception
            java.lang.String r1 = "HardwareVideoDecoder"
            java.lang.String r3 = "Interrupted while waiting for output buffers to be released."
            org.webrtc.Logging.e(r1, r3, r0)     // Catch:{ all -> 0x0025 }
            monitor-exit(r2)     // Catch:{ all -> 0x0025 }
        L_0x0022:
            return
        L_0x0023:
            monitor-exit(r2)     // Catch:{ all -> 0x0025 }
            goto L_0x0022
        L_0x0025:
            r1 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0025 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.HardwareVideoDecoder.waitOutputBuffersReleasedOnOutputThread():void");
    }

    private void stopOnOutputThread(Exception e) {
        this.outputThreadChecker.checkIsOnValidThread();
        this.running = false;
        this.shutdownException = e;
    }

    private boolean isSupportedColorFormat(int colorFormat2) {
        for (int supported : MediaCodecUtils.DECODER_COLOR_FORMATS) {
            if (supported == colorFormat2) {
                return true;
            }
        }
        return false;
    }

    private VideoFrame.I420Buffer createBufferFromI420(ByteBuffer buffer, int outputBufferIndex, int offset, int stride2, int sliceHeight2, int width2, int height2) {
        final int uvStride = stride2 / 2;
        int i = (width2 + 1) / 2;
        final int chromaHeight = (height2 + 1) / 2;
        final int yPos = offset;
        final int uPos = yPos + (stride2 * sliceHeight2);
        final int vPos = uPos + ((uvStride * sliceHeight2) / 2);
        synchronized (this.activeOutputBuffersLock) {
            this.activeOutputBuffers++;
        }
        final ByteBuffer byteBuffer = buffer;
        final int i2 = height2;
        final int i3 = stride2;
        final int i4 = width2;
        final int i5 = outputBufferIndex;
        return new VideoFrame.I420Buffer() {
            private int refCount = 1;

            public ByteBuffer getDataY() {
                ByteBuffer data = byteBuffer.slice();
                data.position(yPos);
                data.limit(yPos + (getStrideY() * i2));
                return data;
            }

            public ByteBuffer getDataU() {
                ByteBuffer data = byteBuffer.slice();
                data.position(uPos);
                data.limit(uPos + (getStrideU() * chromaHeight));
                return data;
            }

            public ByteBuffer getDataV() {
                ByteBuffer data = byteBuffer.slice();
                data.position(vPos);
                data.limit(vPos + (getStrideV() * chromaHeight));
                return data;
            }

            public int getStrideY() {
                return i3;
            }

            public int getStrideU() {
                return uvStride;
            }

            public int getStrideV() {
                return uvStride;
            }

            public int getWidth() {
                return i4;
            }

            public int getHeight() {
                return i2;
            }

            public VideoFrame.I420Buffer toI420() {
                return this;
            }

            public void retain() {
                this.refCount++;
            }

            public void release() {
                this.refCount--;
                if (this.refCount == 0) {
                    HardwareVideoDecoder.this.codec.releaseOutputBuffer(i5, false);
                    synchronized (HardwareVideoDecoder.this.activeOutputBuffersLock) {
                        HardwareVideoDecoder.access$610(HardwareVideoDecoder.this);
                        HardwareVideoDecoder.this.activeOutputBuffersLock.notifyAll();
                    }
                }
            }
        };
    }

    private static void copyI420(ByteBuffer src, int offset, VideoFrame.I420Buffer frameBuffer, int stride2, int sliceHeight2, int width2, int height2) {
        int uvStride = stride2 / 2;
        int chromaWidth = (width2 + 1) / 2;
        int chromaHeight = sliceHeight2 % 2 == 0 ? (height2 + 1) / 2 : height2 / 2;
        int yPos = offset;
        int uPos = yPos + (stride2 * sliceHeight2);
        copyPlane(src, yPos, stride2, frameBuffer.getDataY(), 0, frameBuffer.getStrideY(), width2, height2);
        copyPlane(src, uPos, uvStride, frameBuffer.getDataU(), 0, frameBuffer.getStrideU(), chromaWidth, chromaHeight);
        copyPlane(src, uPos + ((uvStride * sliceHeight2) / 2), uvStride, frameBuffer.getDataV(), 0, frameBuffer.getStrideV(), chromaWidth, chromaHeight);
        if (sliceHeight2 % 2 != 0) {
            int strideU = frameBuffer.getStrideU();
            int endU = chromaHeight * strideU;
            copyRow(frameBuffer.getDataU(), endU - strideU, frameBuffer.getDataU(), endU, chromaWidth);
            int strideV = frameBuffer.getStrideV();
            int endV = chromaHeight * strideV;
            copyRow(frameBuffer.getDataV(), endV - strideV, frameBuffer.getDataV(), endV, chromaWidth);
        }
    }

    private static void nv12ToI420(ByteBuffer src, int offset, VideoFrame.I420Buffer frameBuffer, int stride2, int sliceHeight2, int width2, int height2) {
        int yPos = offset;
        int uvPos = yPos + (stride2 * sliceHeight2);
        int chromaWidth = (width2 + 1) / 2;
        int chromaHeight = (height2 + 1) / 2;
        copyPlane(src, yPos, stride2, frameBuffer.getDataY(), 0, frameBuffer.getStrideY(), width2, height2);
        int dstUPos = 0;
        int dstVPos = 0;
        for (int i = 0; i < chromaHeight; i++) {
            for (int j = 0; j < chromaWidth; j++) {
                frameBuffer.getDataU().put(dstUPos + j, src.get((j * 2) + uvPos));
                frameBuffer.getDataV().put(dstVPos + j, src.get((j * 2) + uvPos + 1));
            }
            dstUPos += frameBuffer.getStrideU();
            dstVPos += frameBuffer.getStrideV();
            uvPos += stride2;
        }
    }

    private static void copyPlane(ByteBuffer src, int srcPos, int srcStride, ByteBuffer dst, int dstPos, int dstStride, int width2, int height2) {
        for (int i = 0; i < height2; i++) {
            copyRow(src, srcPos, dst, dstPos, width2);
            srcPos += srcStride;
            dstPos += dstStride;
        }
    }

    private static void copyRow(ByteBuffer src, int srcPos, ByteBuffer dst, int dstPos, int width2) {
        for (int i = 0; i < width2; i++) {
            dst.put(dstPos + i, src.get(srcPos + i));
        }
    }
}
