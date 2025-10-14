package org.webrtc;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.view.Surface;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;
import org.webrtc.EncodedImage;
import org.webrtc.VideoEncoder;
import org.webrtc.VideoFrame;

@TargetApi(19)
class HardwareVideoEncoder implements VideoEncoder {
    private static final int DEQUEUE_OUTPUT_BUFFER_TIMEOUT_US = 100000;
    private static final String KEY_BITRATE_MODE = "bitrate-mode";
    private static final int MAX_ENCODER_Q_SIZE = 2;
    private static final int MAX_VIDEO_FRAMERATE = 30;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final String TAG = "HardwareVideoEncoder";
    private static final int VIDEO_ControlRateConstant = 2;
    private int adjustedBitrate;
    private final BitrateAdjuster bitrateAdjuster;
    private VideoEncoder.Callback callback;
    private MediaCodec codec;
    private final String codecName;
    private final VideoCodecType codecType;
    private final int colorFormat;
    private ByteBuffer configBuffer = null;
    private final long forcedKeyFrameMs;
    private int height;
    private final ColorFormat inputColorFormat;
    private final int keyFrameIntervalSec;
    private long lastKeyFrameMs;
    private final Deque<EncodedImage.Builder> outputBuilders;
    private Thread outputThread;
    /* access modifiers changed from: private */
    public volatile boolean running = false;
    private volatile Exception shutdownException = null;
    private int width;

    public HardwareVideoEncoder(String codecName2, VideoCodecType codecType2, int colorFormat2, int keyFrameIntervalSec2, int forceKeyFrameIntervalMs, BitrateAdjuster bitrateAdjuster2) {
        this.codecName = codecName2;
        this.codecType = codecType2;
        this.colorFormat = colorFormat2;
        this.inputColorFormat = ColorFormat.valueOf(colorFormat2);
        this.keyFrameIntervalSec = keyFrameIntervalSec2;
        this.forcedKeyFrameMs = (long) forceKeyFrameIntervalMs;
        this.bitrateAdjuster = bitrateAdjuster2;
        this.outputBuilders = new LinkedBlockingDeque();
    }

    public VideoCodecStatus initEncode(VideoEncoder.Settings settings, VideoEncoder.Callback callback2) {
        return initEncodeInternal(settings.width, settings.height, settings.startBitrate, settings.maxFramerate, callback2);
    }

    private VideoCodecStatus initEncodeInternal(int width2, int height2, int bitrateKbps, int fps, VideoEncoder.Callback callback2) {
        Logging.d(TAG, "initEncode: " + width2 + " x " + height2 + ". @ " + bitrateKbps + "kbps. Fps: " + fps);
        this.width = width2;
        this.height = height2;
        if (!(bitrateKbps == 0 || fps == 0)) {
            this.bitrateAdjuster.setTargets(bitrateKbps * 1000, fps);
        }
        this.adjustedBitrate = this.bitrateAdjuster.getAdjustedBitrateBps();
        this.callback = callback2;
        this.lastKeyFrameMs = -1;
        try {
            this.codec = MediaCodec.createByCodecName(this.codecName);
            try {
                MediaFormat format = MediaFormat.createVideoFormat(this.codecType.mimeType(), width2, height2);
                format.setInteger("bitrate", this.adjustedBitrate);
                format.setInteger(KEY_BITRATE_MODE, 2);
                format.setInteger("color-format", this.colorFormat);
                format.setInteger("frame-rate", this.bitrateAdjuster.getAdjustedFramerate());
                format.setInteger("i-frame-interval", this.keyFrameIntervalSec);
                Logging.d(TAG, "Format: " + format);
                this.codec.configure(format, (Surface) null, (MediaCrypto) null, 1);
                this.codec.start();
                this.running = true;
                this.outputThread = createOutputThread();
                this.outputThread.start();
                return VideoCodecStatus.OK;
            } catch (IllegalStateException e) {
                Logging.e(TAG, "initEncode failed", e);
                release();
                return VideoCodecStatus.ERROR;
            }
        } catch (IOException | IllegalArgumentException e2) {
            Logging.e(TAG, "Cannot create media encoder " + this.codecName);
            return VideoCodecStatus.ERROR;
        }
    }

    public VideoCodecStatus release() {
        try {
            this.running = false;
            if (!ThreadUtils.joinUninterruptibly(this.outputThread, 5000)) {
                Logging.e(TAG, "Media encoder release timeout");
                return VideoCodecStatus.TIMEOUT;
            } else if (this.shutdownException != null) {
                Logging.e(TAG, "Media encoder release exception", this.shutdownException);
                VideoCodecStatus videoCodecStatus = VideoCodecStatus.ERROR;
                this.codec = null;
                this.outputThread = null;
                this.outputBuilders.clear();
                return videoCodecStatus;
            } else {
                this.codec = null;
                this.outputThread = null;
                this.outputBuilders.clear();
                return VideoCodecStatus.OK;
            }
        } finally {
            this.codec = null;
            this.outputThread = null;
            this.outputBuilders.clear();
        }
    }

    public VideoCodecStatus encode(VideoFrame videoFrame, VideoEncoder.EncodeInfo encodeInfo) {
        if (this.codec == null) {
            return VideoCodecStatus.UNINITIALIZED;
        }
        int frameWidth = videoFrame.getWidth();
        int frameHeight = videoFrame.getHeight();
        if (!(frameWidth == this.width && frameHeight == this.height)) {
            VideoCodecStatus status = resetCodec(frameWidth, frameHeight);
            if (status != VideoCodecStatus.OK) {
                return status;
            }
        }
        try {
            int index = this.codec.dequeueInputBuffer(0);
            if (index == -1) {
                Logging.e(TAG, "Dropped frame, no input buffers available");
                return VideoCodecStatus.OK;
            } else if (this.outputBuilders.size() > 2) {
                Logging.e(TAG, "Dropped frame, encoder queue full");
                return VideoCodecStatus.OK;
            } else {
                try {
                    this.inputColorFormat.fillBufferFromI420(this.codec.getInputBuffers()[index], videoFrame.getBuffer().toI420());
                    boolean requestedKeyFrame = false;
                    for (EncodedImage.FrameType frameType : encodeInfo.frameTypes) {
                        if (frameType == EncodedImage.FrameType.VideoFrameKey) {
                            requestedKeyFrame = true;
                        }
                    }
                    long presentationTimestampUs = (videoFrame.getTimestampNs() + 500) / 1000;
                    long presentationTimestampMs = (500 + presentationTimestampUs) / 1000;
                    if (requestedKeyFrame || shouldForceKeyFrame(presentationTimestampMs)) {
                        requestKeyFrame(presentationTimestampMs);
                    }
                    int bufferSize = ((videoFrame.getBuffer().getHeight() * videoFrame.getBuffer().getWidth()) * 3) / 2;
                    this.outputBuilders.offer(EncodedImage.builder().setCaptureTimeMs(presentationTimestampMs).setCompleteFrame(true).setEncodedWidth(videoFrame.getWidth()).setEncodedHeight(videoFrame.getHeight()).setRotation(videoFrame.getRotation()));
                    try {
                        this.codec.queueInputBuffer(index, 0, bufferSize, presentationTimestampUs, 0);
                        return VideoCodecStatus.OK;
                    } catch (IllegalStateException e) {
                        Logging.e(TAG, "queueInputBuffer failed", e);
                        this.outputBuilders.pollLast();
                        return VideoCodecStatus.FALLBACK_SOFTWARE;
                    }
                } catch (IllegalStateException e2) {
                    Logging.e(TAG, "getInputBuffers failed", e2);
                    return VideoCodecStatus.FALLBACK_SOFTWARE;
                }
            }
        } catch (IllegalStateException e3) {
            Logging.e(TAG, "dequeueInputBuffer failed", e3);
            return VideoCodecStatus.FALLBACK_SOFTWARE;
        }
    }

    public VideoCodecStatus setChannelParameters(short packetLoss, long roundTripTimeMs) {
        return VideoCodecStatus.OK;
    }

    public VideoCodecStatus setRateAllocation(VideoEncoder.BitrateAllocation bitrateAllocation, int framerate) {
        if (framerate > 30) {
            framerate = 30;
        }
        this.bitrateAdjuster.setTargets(bitrateAllocation.getSum(), framerate);
        return updateBitrate();
    }

    public VideoEncoder.ScalingSettings getScalingSettings() {
        return null;
    }

    public String getImplementationName() {
        return "HardwareVideoEncoder: " + this.codecName;
    }

    private VideoCodecStatus resetCodec(int newWidth, int newHeight) {
        VideoCodecStatus status = release();
        if (status != VideoCodecStatus.OK) {
            return status;
        }
        return initEncodeInternal(newWidth, newHeight, 0, 0, this.callback);
    }

    private boolean shouldForceKeyFrame(long presentationTimestampMs) {
        return this.forcedKeyFrameMs > 0 && presentationTimestampMs > this.lastKeyFrameMs + this.forcedKeyFrameMs;
    }

    private void requestKeyFrame(long presentationTimestampMs) {
        try {
            Bundle b = new Bundle();
            b.putInt("request-sync", 0);
            this.codec.setParameters(b);
            this.lastKeyFrameMs = presentationTimestampMs;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "requestKeyFrame failed", e);
        }
    }

    private Thread createOutputThread() {
        return new Thread() {
            public void run() {
                while (HardwareVideoEncoder.this.running) {
                    HardwareVideoEncoder.this.deliverEncodedImage();
                }
                HardwareVideoEncoder.this.releaseCodecOnOutputThread();
            }
        };
    }

    /* access modifiers changed from: private */
    public void deliverEncodedImage() {
        ByteBuffer frameBuffer;
        boolean isKeyFrame = false;
        try {
            MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
            int index = this.codec.dequeueOutputBuffer(info, 100000);
            if (index >= 0) {
                ByteBuffer codecOutputBuffer = this.codec.getOutputBuffers()[index];
                codecOutputBuffer.position(info.offset);
                codecOutputBuffer.limit(info.offset + info.size);
                if ((info.flags & 2) != 0) {
                    Logging.d(TAG, "Config frame generated. Offset: " + info.offset + ". Size: " + info.size);
                    this.configBuffer = ByteBuffer.allocateDirect(info.size);
                    this.configBuffer.put(codecOutputBuffer);
                } else {
                    this.bitrateAdjuster.reportEncodedFrame(info.size);
                    if (this.adjustedBitrate != this.bitrateAdjuster.getAdjustedBitrateBps()) {
                        updateBitrate();
                    }
                    if ((info.flags & 1) != 0) {
                        isKeyFrame = true;
                    }
                    if (!isKeyFrame || this.codecType != VideoCodecType.H264) {
                        frameBuffer = ByteBuffer.allocateDirect(info.size);
                    } else {
                        Logging.d(TAG, "Prepending config frame of size " + this.configBuffer.capacity() + " to output buffer with offset " + info.offset + ", size " + info.size);
                        frameBuffer = ByteBuffer.allocateDirect(info.size + this.configBuffer.capacity());
                        this.configBuffer.rewind();
                        frameBuffer.put(this.configBuffer);
                    }
                    frameBuffer.put(codecOutputBuffer);
                    frameBuffer.rewind();
                    EncodedImage.FrameType frameType = EncodedImage.FrameType.VideoFrameDelta;
                    if (isKeyFrame) {
                        Logging.d(TAG, "Sync frame generated");
                        frameType = EncodedImage.FrameType.VideoFrameKey;
                    }
                    EncodedImage.Builder builder = this.outputBuilders.poll();
                    builder.setBuffer(frameBuffer).setFrameType(frameType);
                    this.callback.onEncodedFrame(builder.createEncodedImage(), new VideoEncoder.CodecSpecificInfo());
                }
                this.codec.releaseOutputBuffer(index, false);
            }
        } catch (IllegalStateException e) {
            Logging.e(TAG, "deliverOutput failed", e);
        }
    }

    /* access modifiers changed from: private */
    public void releaseCodecOnOutputThread() {
        Logging.d(TAG, "Releasing MediaCodec on output thread");
        try {
            this.codec.stop();
        } catch (Exception e) {
            Logging.e(TAG, "Media encoder stop failed", e);
        }
        try {
            this.codec.release();
        } catch (Exception e2) {
            Logging.e(TAG, "Media encoder release failed", e2);
            this.shutdownException = e2;
        }
        Logging.d(TAG, "Release on output thread done");
    }

    private VideoCodecStatus updateBitrate() {
        this.adjustedBitrate = this.bitrateAdjuster.getAdjustedBitrateBps();
        try {
            Bundle params = new Bundle();
            params.putInt("video-bitrate", this.adjustedBitrate);
            this.codec.setParameters(params);
            return VideoCodecStatus.OK;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "updateBitrate failed", e);
            return VideoCodecStatus.ERROR;
        }
    }

    private enum ColorFormat {
        I420 {
            /* access modifiers changed from: package-private */
            public void fillBufferFromI420(ByteBuffer buffer, VideoFrame.I420Buffer i420) {
                buffer.put(i420.getDataY());
                buffer.put(i420.getDataU());
                buffer.put(i420.getDataV());
            }
        },
        NV12 {
            /* access modifiers changed from: package-private */
            public void fillBufferFromI420(ByteBuffer buffer, VideoFrame.I420Buffer i420) {
                buffer.put(i420.getDataY());
                ByteBuffer u = i420.getDataU();
                ByteBuffer v = i420.getDataV();
                while (u.hasRemaining() && v.hasRemaining()) {
                    buffer.put(u.get());
                    buffer.put(v.get());
                }
            }
        };

        /* access modifiers changed from: package-private */
        public abstract void fillBufferFromI420(ByteBuffer byteBuffer, VideoFrame.I420Buffer i420Buffer);

        static ColorFormat valueOf(int colorFormat) {
            switch (colorFormat) {
                case 19:
                    return I420;
                case 21:
                case 2141391872:
                case 2141391876:
                    return NV12;
                default:
                    throw new IllegalArgumentException("Unsupported colorFormat: " + colorFormat);
            }
        }
    }
}
