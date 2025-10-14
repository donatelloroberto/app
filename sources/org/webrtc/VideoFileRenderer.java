package org.webrtc;

import android.os.Handler;
import android.os.HandlerThread;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import org.webrtc.EglBase;
import org.webrtc.VideoRenderer;

public class VideoFileRenderer implements VideoRenderer.Callbacks {
    private static final String TAG = "VideoFileRenderer";
    /* access modifiers changed from: private */
    public EglBase eglBase;
    private final Object handlerLock = new Object();
    private final int outputFileHeight;
    private final String outputFileName;
    private final int outputFileWidth;
    private final ByteBuffer outputFrameBuffer;
    private final int outputFrameSize;
    private ArrayList<ByteBuffer> rawFrames = new ArrayList<>();
    /* access modifiers changed from: private */
    public final HandlerThread renderThread;
    private final Handler renderThreadHandler;
    private final FileOutputStream videoOutFile;
    /* access modifiers changed from: private */
    public YuvConverter yuvConverter;

    public static native ByteBuffer nativeCreateNativeByteBuffer(int i);

    public static native void nativeFreeNativeByteBuffer(ByteBuffer byteBuffer);

    public static native void nativeI420Scale(ByteBuffer byteBuffer, int i, ByteBuffer byteBuffer2, int i2, ByteBuffer byteBuffer3, int i3, int i4, int i5, ByteBuffer byteBuffer4, int i6, int i7);

    static {
        System.loadLibrary("jingle_peerconnection_so");
    }

    public VideoFileRenderer(String outputFile, int outputFileWidth2, int outputFileHeight2, final EglBase.Context sharedContext) throws IOException {
        if (outputFileWidth2 % 2 == 1 || outputFileHeight2 % 2 == 1) {
            throw new IllegalArgumentException("Does not support uneven width or height");
        }
        this.outputFileName = outputFile;
        this.outputFileWidth = outputFileWidth2;
        this.outputFileHeight = outputFileHeight2;
        this.outputFrameSize = ((outputFileWidth2 * outputFileHeight2) * 3) / 2;
        this.outputFrameBuffer = ByteBuffer.allocateDirect(this.outputFrameSize);
        this.videoOutFile = new FileOutputStream(outputFile);
        this.videoOutFile.write(("YUV4MPEG2 C420 W" + outputFileWidth2 + " H" + outputFileHeight2 + " Ip F30:1 A1:1\n").getBytes());
        this.renderThread = new HandlerThread(TAG);
        this.renderThread.start();
        this.renderThreadHandler = new Handler(this.renderThread.getLooper());
        ThreadUtils.invokeAtFrontUninterruptibly(this.renderThreadHandler, (Runnable) new Runnable() {
            public void run() {
                EglBase unused = VideoFileRenderer.this.eglBase = EglBase.create(sharedContext, EglBase.CONFIG_PIXEL_BUFFER);
                VideoFileRenderer.this.eglBase.createDummyPbufferSurface();
                VideoFileRenderer.this.eglBase.makeCurrent();
                YuvConverter unused2 = VideoFileRenderer.this.yuvConverter = new YuvConverter();
            }
        });
    }

    public void renderFrame(final VideoRenderer.I420Frame frame) {
        this.renderThreadHandler.post(new Runnable() {
            public void run() {
                VideoFileRenderer.this.renderFrameOnRenderThread(frame);
            }
        });
    }

    /* access modifiers changed from: private */
    public void renderFrameOnRenderThread(VideoRenderer.I420Frame frame) {
        float frameAspectRatio = ((float) frame.rotatedWidth()) / ((float) frame.rotatedHeight());
        float[] texMatrix = RendererCommon.multiplyMatrices(RendererCommon.rotateTextureMatrix(frame.samplingMatrix, (float) frame.rotationDegree), RendererCommon.getLayoutMatrix(false, frameAspectRatio, ((float) this.outputFileWidth) / ((float) this.outputFileHeight)));
        try {
            ByteBuffer buffer = nativeCreateNativeByteBuffer(this.outputFrameSize);
            if (!frame.yuvFrame) {
                this.yuvConverter.convert(this.outputFrameBuffer, this.outputFileWidth, this.outputFileHeight, this.outputFileWidth, frame.textureId, texMatrix);
                int stride = this.outputFileWidth;
                byte[] data = this.outputFrameBuffer.array();
                int offset = this.outputFrameBuffer.arrayOffset();
                buffer.put(data, offset, this.outputFileWidth * this.outputFileHeight);
                for (int r = this.outputFileHeight; r < (this.outputFileHeight * 3) / 2; r++) {
                    buffer.put(data, (r * stride) + offset, stride / 2);
                }
                for (int r2 = this.outputFileHeight; r2 < (this.outputFileHeight * 3) / 2; r2++) {
                    buffer.put(data, (r2 * stride) + offset + (stride / 2), stride / 2);
                }
            } else {
                nativeI420Scale(frame.yuvPlanes[0], frame.yuvStrides[0], frame.yuvPlanes[1], frame.yuvStrides[1], frame.yuvPlanes[2], frame.yuvStrides[2], frame.width, frame.height, this.outputFrameBuffer, this.outputFileWidth, this.outputFileHeight);
                buffer.put(this.outputFrameBuffer.array(), this.outputFrameBuffer.arrayOffset(), this.outputFrameSize);
            }
            buffer.rewind();
            this.rawFrames.add(buffer);
        } finally {
            VideoRenderer.renderFrameDone(frame);
        }
    }

    public void release() {
        final CountDownLatch cleanupBarrier = new CountDownLatch(1);
        this.renderThreadHandler.post(new Runnable() {
            public void run() {
                VideoFileRenderer.this.yuvConverter.release();
                VideoFileRenderer.this.eglBase.release();
                VideoFileRenderer.this.renderThread.quit();
                cleanupBarrier.countDown();
            }
        });
        ThreadUtils.awaitUninterruptibly(cleanupBarrier);
        try {
            Iterator<ByteBuffer> it = this.rawFrames.iterator();
            while (it.hasNext()) {
                ByteBuffer buffer = it.next();
                this.videoOutFile.write("FRAME\n".getBytes());
                byte[] data = new byte[this.outputFrameSize];
                buffer.get(data);
                this.videoOutFile.write(data);
                nativeFreeNativeByteBuffer(buffer);
            }
            this.videoOutFile.close();
            Logging.d(TAG, "Video written to disk as " + this.outputFileName + ". Number frames are " + this.rawFrames.size() + " and the dimension of the frames are " + this.outputFileWidth + "x" + this.outputFileHeight + ".");
        } catch (IOException e) {
            Logging.e(TAG, "Error writing video to disk", e);
        }
    }
}
