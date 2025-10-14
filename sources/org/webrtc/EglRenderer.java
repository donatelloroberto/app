package org.webrtc;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;
import org.webrtc.EglBase;
import org.webrtc.RendererCommon;
import org.webrtc.VideoRenderer;

public class EglRenderer implements VideoRenderer.Callbacks {
    private static final long LOG_INTERVAL_SEC = 4;
    private static final int MAX_SURFACE_CLEAR_COUNT = 3;
    private static final String TAG = "EglRenderer";
    /* access modifiers changed from: private */
    public GlTextureFrameBuffer bitmapTextureFramebuffer;
    /* access modifiers changed from: private */
    public RendererCommon.GlDrawer drawer;
    /* access modifiers changed from: private */
    public EglBase eglBase;
    private final EglSurfaceCreation eglSurfaceCreationRunnable = new EglSurfaceCreation();
    private final Object fpsReductionLock = new Object();
    /* access modifiers changed from: private */
    public final ArrayList<FrameListenerAndParams> frameListeners = new ArrayList<>();
    private final Object frameLock = new Object();
    private int framesDropped;
    private int framesReceived;
    private int framesRendered;
    /* access modifiers changed from: private */
    public final Object handlerLock = new Object();
    private float layoutAspectRatio;
    private final Object layoutLock = new Object();
    /* access modifiers changed from: private */
    public final Runnable logStatisticsRunnable = new Runnable() {
        public void run() {
            EglRenderer.this.logStatistics();
            synchronized (EglRenderer.this.handlerLock) {
                if (EglRenderer.this.renderThreadHandler != null) {
                    EglRenderer.this.renderThreadHandler.removeCallbacks(EglRenderer.this.logStatisticsRunnable);
                    EglRenderer.this.renderThreadHandler.postDelayed(EglRenderer.this.logStatisticsRunnable, TimeUnit.SECONDS.toMillis(4));
                }
            }
        }
    };
    private long minRenderPeriodNs;
    private boolean mirror;
    private final String name;
    private long nextFrameTimeNs;
    private VideoRenderer.I420Frame pendingFrame;
    private final Runnable renderFrameRunnable = new Runnable() {
        public void run() {
            EglRenderer.this.renderFrameOnRenderThread();
        }
    };
    private long renderSwapBufferTimeNs;
    /* access modifiers changed from: private */
    public Handler renderThreadHandler;
    private long renderTimeNs;
    private final Object statisticsLock = new Object();
    private long statisticsStartTimeNs;
    /* access modifiers changed from: private */
    public final RendererCommon.YuvUploader yuvUploader = new RendererCommon.YuvUploader();

    public interface FrameListener {
        void onFrame(Bitmap bitmap);
    }

    private static class FrameListenerAndParams {
        public final boolean applyFpsReduction;
        public final RendererCommon.GlDrawer drawer;
        public final FrameListener listener;
        public final float scale;

        public FrameListenerAndParams(FrameListener listener2, float scale2, RendererCommon.GlDrawer drawer2, boolean applyFpsReduction2) {
            this.listener = listener2;
            this.scale = scale2;
            this.drawer = drawer2;
            this.applyFpsReduction = applyFpsReduction2;
        }
    }

    private class EglSurfaceCreation implements Runnable {
        private Object surface;

        private EglSurfaceCreation() {
        }

        public synchronized void setSurface(Object surface2) {
            this.surface = surface2;
        }

        public synchronized void run() {
            if (!(this.surface == null || EglRenderer.this.eglBase == null || EglRenderer.this.eglBase.hasSurface())) {
                if (this.surface instanceof Surface) {
                    EglRenderer.this.eglBase.createSurface((Surface) this.surface);
                } else if (this.surface instanceof SurfaceTexture) {
                    EglRenderer.this.eglBase.createSurface((SurfaceTexture) this.surface);
                } else {
                    throw new IllegalStateException("Invalid surface: " + this.surface);
                }
                EglRenderer.this.eglBase.makeCurrent();
                GLES20.glPixelStorei(3317, 1);
            }
        }
    }

    public EglRenderer(String name2) {
        this.name = name2;
    }

    public void init(final EglBase.Context sharedContext, final int[] configAttributes, RendererCommon.GlDrawer drawer2) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                throw new IllegalStateException(this.name + "Already initialized");
            }
            logD("Initializing EglRenderer");
            this.drawer = drawer2;
            HandlerThread renderThread = new HandlerThread(this.name + TAG);
            renderThread.start();
            this.renderThreadHandler = new Handler(renderThread.getLooper());
            ThreadUtils.invokeAtFrontUninterruptibly(this.renderThreadHandler, (Runnable) new Runnable() {
                public void run() {
                    if (sharedContext == null) {
                        EglRenderer.this.logD("EglBase10.create context");
                        EglBase unused = EglRenderer.this.eglBase = EglBase.createEgl10(configAttributes);
                        return;
                    }
                    EglRenderer.this.logD("EglBase.create shared context");
                    EglBase unused2 = EglRenderer.this.eglBase = EglBase.create(sharedContext, configAttributes);
                }
            });
            this.renderThreadHandler.post(this.eglSurfaceCreationRunnable);
            resetStatistics(System.nanoTime());
            this.renderThreadHandler.postDelayed(this.logStatisticsRunnable, TimeUnit.SECONDS.toMillis(4));
        }
    }

    public void createEglSurface(Surface surface) {
        createEglSurfaceInternal(surface);
    }

    public void createEglSurface(SurfaceTexture surfaceTexture) {
        createEglSurfaceInternal(surfaceTexture);
    }

    private void createEglSurfaceInternal(Object surface) {
        this.eglSurfaceCreationRunnable.setSurface(surface);
        postToRenderThread(this.eglSurfaceCreationRunnable);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0043, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        if (r5.pendingFrame == null) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0048, code lost:
        org.webrtc.VideoRenderer.renderFrameDone(r5.pendingFrame);
        r5.pendingFrame = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0050, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0051, code lost:
        logD("Releasing done.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003e, code lost:
        org.webrtc.ThreadUtils.awaitUninterruptibly(r0);
        r3 = r5.frameLock;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void release() {
        /*
            r5 = this;
            java.lang.String r2 = "Releasing."
            r5.logD(r2)
            java.util.concurrent.CountDownLatch r0 = new java.util.concurrent.CountDownLatch
            r2 = 1
            r0.<init>(r2)
            java.lang.Object r3 = r5.handlerLock
            monitor-enter(r3)
            android.os.Handler r2 = r5.renderThreadHandler     // Catch:{ all -> 0x0057 }
            if (r2 != 0) goto L_0x0019
            java.lang.String r2 = "Already released"
            r5.logD(r2)     // Catch:{ all -> 0x0057 }
            monitor-exit(r3)     // Catch:{ all -> 0x0057 }
        L_0x0018:
            return
        L_0x0019:
            android.os.Handler r2 = r5.renderThreadHandler     // Catch:{ all -> 0x0057 }
            java.lang.Runnable r4 = r5.logStatisticsRunnable     // Catch:{ all -> 0x0057 }
            r2.removeCallbacks(r4)     // Catch:{ all -> 0x0057 }
            android.os.Handler r2 = r5.renderThreadHandler     // Catch:{ all -> 0x0057 }
            org.webrtc.EglRenderer$4 r4 = new org.webrtc.EglRenderer$4     // Catch:{ all -> 0x0057 }
            r4.<init>(r0)     // Catch:{ all -> 0x0057 }
            r2.postAtFrontOfQueue(r4)     // Catch:{ all -> 0x0057 }
            android.os.Handler r2 = r5.renderThreadHandler     // Catch:{ all -> 0x0057 }
            android.os.Looper r1 = r2.getLooper()     // Catch:{ all -> 0x0057 }
            android.os.Handler r2 = r5.renderThreadHandler     // Catch:{ all -> 0x0057 }
            org.webrtc.EglRenderer$5 r4 = new org.webrtc.EglRenderer$5     // Catch:{ all -> 0x0057 }
            r4.<init>(r1)     // Catch:{ all -> 0x0057 }
            r2.post(r4)     // Catch:{ all -> 0x0057 }
            r2 = 0
            r5.renderThreadHandler = r2     // Catch:{ all -> 0x0057 }
            monitor-exit(r3)     // Catch:{ all -> 0x0057 }
            org.webrtc.ThreadUtils.awaitUninterruptibly(r0)
            java.lang.Object r3 = r5.frameLock
            monitor-enter(r3)
            org.webrtc.VideoRenderer$I420Frame r2 = r5.pendingFrame     // Catch:{ all -> 0x005a }
            if (r2 == 0) goto L_0x0050
            org.webrtc.VideoRenderer$I420Frame r2 = r5.pendingFrame     // Catch:{ all -> 0x005a }
            org.webrtc.VideoRenderer.renderFrameDone(r2)     // Catch:{ all -> 0x005a }
            r2 = 0
            r5.pendingFrame = r2     // Catch:{ all -> 0x005a }
        L_0x0050:
            monitor-exit(r3)     // Catch:{ all -> 0x005a }
            java.lang.String r2 = "Releasing done."
            r5.logD(r2)
            goto L_0x0018
        L_0x0057:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0057 }
            throw r2
        L_0x005a:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x005a }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.EglRenderer.release():void");
    }

    private void resetStatistics(long currentTimeNs) {
        synchronized (this.statisticsLock) {
            this.statisticsStartTimeNs = currentTimeNs;
            this.framesReceived = 0;
            this.framesDropped = 0;
            this.framesRendered = 0;
            this.renderTimeNs = 0;
            this.renderSwapBufferTimeNs = 0;
        }
    }

    public void printStackTrace() {
        Thread renderThread;
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler == null) {
                renderThread = null;
            } else {
                renderThread = this.renderThreadHandler.getLooper().getThread();
            }
            if (renderThread != null) {
                StackTraceElement[] renderStackTrace = renderThread.getStackTrace();
                if (renderStackTrace.length > 0) {
                    logD("EglRenderer stack trace:");
                    for (StackTraceElement traceElem : renderStackTrace) {
                        logD(traceElem.toString());
                    }
                }
            }
        }
    }

    public void setMirror(boolean mirror2) {
        logD("setMirror: " + mirror2);
        synchronized (this.layoutLock) {
            this.mirror = mirror2;
        }
    }

    public void setLayoutAspectRatio(float layoutAspectRatio2) {
        logD("setLayoutAspectRatio: " + layoutAspectRatio2);
        synchronized (this.layoutLock) {
            this.layoutAspectRatio = layoutAspectRatio2;
        }
    }

    public void setFpsReduction(float fps) {
        logD("setFpsReduction: " + fps);
        synchronized (this.fpsReductionLock) {
            long previousRenderPeriodNs = this.minRenderPeriodNs;
            if (fps <= 0.0f) {
                this.minRenderPeriodNs = LongCompanionObject.MAX_VALUE;
            } else {
                this.minRenderPeriodNs = (long) (((float) TimeUnit.SECONDS.toNanos(1)) / fps);
            }
            if (this.minRenderPeriodNs != previousRenderPeriodNs) {
                this.nextFrameTimeNs = System.nanoTime();
            }
        }
    }

    public void disableFpsReduction() {
        setFpsReduction(Float.POSITIVE_INFINITY);
    }

    public void pauseVideo() {
        setFpsReduction(0.0f);
    }

    public void addFrameListener(FrameListener listener, float scale) {
        addFrameListener(listener, scale, (RendererCommon.GlDrawer) null, false);
    }

    public void addFrameListener(FrameListener listener, float scale, RendererCommon.GlDrawer drawerParam) {
        addFrameListener(listener, scale, drawerParam, false);
    }

    public void addFrameListener(FrameListener listener, float scale, RendererCommon.GlDrawer drawerParam, boolean applyFpsReduction) {
        final RendererCommon.GlDrawer glDrawer = drawerParam;
        final FrameListener frameListener = listener;
        final float f = scale;
        final boolean z = applyFpsReduction;
        postToRenderThread(new Runnable() {
            public void run() {
                EglRenderer.this.frameListeners.add(new FrameListenerAndParams(frameListener, f, glDrawer == null ? EglRenderer.this.drawer : glDrawer, z));
            }
        });
    }

    public void removeFrameListener(final FrameListener listener) {
        if (Thread.currentThread() == this.renderThreadHandler.getLooper().getThread()) {
            throw new RuntimeException("removeFrameListener must not be called on the render thread.");
        }
        final CountDownLatch latch = new CountDownLatch(1);
        postToRenderThread(new Runnable() {
            public void run() {
                latch.countDown();
                Iterator<FrameListenerAndParams> iter = EglRenderer.this.frameListeners.iterator();
                while (iter.hasNext()) {
                    if (iter.next().listener == listener) {
                        iter.remove();
                    }
                }
            }
        });
        ThreadUtils.awaitUninterruptibly(latch);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0038, code lost:
        if (r0 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x003a, code lost:
        r2 = r5.statisticsLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x003c, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r5.framesDropped++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0043, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void renderFrame(org.webrtc.VideoRenderer.I420Frame r6) {
        /*
            r5 = this;
            java.lang.Object r2 = r5.statisticsLock
            monitor-enter(r2)
            int r1 = r5.framesReceived     // Catch:{ all -> 0x001b }
            int r1 = r1 + 1
            r5.framesReceived = r1     // Catch:{ all -> 0x001b }
            monitor-exit(r2)     // Catch:{ all -> 0x001b }
            java.lang.Object r2 = r5.handlerLock
            monitor-enter(r2)
            android.os.Handler r1 = r5.renderThreadHandler     // Catch:{ all -> 0x004d }
            if (r1 != 0) goto L_0x001e
            java.lang.String r1 = "Dropping frame - Not initialized or already released."
            r5.logD(r1)     // Catch:{ all -> 0x004d }
            org.webrtc.VideoRenderer.renderFrameDone(r6)     // Catch:{ all -> 0x004d }
            monitor-exit(r2)     // Catch:{ all -> 0x004d }
        L_0x001a:
            return
        L_0x001b:
            r1 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x001b }
            throw r1
        L_0x001e:
            java.lang.Object r3 = r5.frameLock     // Catch:{ all -> 0x004d }
            monitor-enter(r3)     // Catch:{ all -> 0x004d }
            org.webrtc.VideoRenderer$I420Frame r1 = r5.pendingFrame     // Catch:{ all -> 0x004a }
            if (r1 == 0) goto L_0x0048
            r0 = 1
        L_0x0026:
            if (r0 == 0) goto L_0x002d
            org.webrtc.VideoRenderer$I420Frame r1 = r5.pendingFrame     // Catch:{ all -> 0x004a }
            org.webrtc.VideoRenderer.renderFrameDone(r1)     // Catch:{ all -> 0x004a }
        L_0x002d:
            r5.pendingFrame = r6     // Catch:{ all -> 0x004a }
            android.os.Handler r1 = r5.renderThreadHandler     // Catch:{ all -> 0x004a }
            java.lang.Runnable r4 = r5.renderFrameRunnable     // Catch:{ all -> 0x004a }
            r1.post(r4)     // Catch:{ all -> 0x004a }
            monitor-exit(r3)     // Catch:{ all -> 0x004a }
            monitor-exit(r2)     // Catch:{ all -> 0x004d }
            if (r0 == 0) goto L_0x001a
            java.lang.Object r2 = r5.statisticsLock
            monitor-enter(r2)
            int r1 = r5.framesDropped     // Catch:{ all -> 0x0045 }
            int r1 = r1 + 1
            r5.framesDropped = r1     // Catch:{ all -> 0x0045 }
            monitor-exit(r2)     // Catch:{ all -> 0x0045 }
            goto L_0x001a
        L_0x0045:
            r1 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0045 }
            throw r1
        L_0x0048:
            r0 = 0
            goto L_0x0026
        L_0x004a:
            r1 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x004a }
            throw r1     // Catch:{ all -> 0x004d }
        L_0x004d:
            r1 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x004d }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.EglRenderer.renderFrame(org.webrtc.VideoRenderer$I420Frame):void");
    }

    public void releaseEglSurface(final Runnable completionCallback) {
        this.eglSurfaceCreationRunnable.setSurface((Object) null);
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                this.renderThreadHandler.removeCallbacks(this.eglSurfaceCreationRunnable);
                this.renderThreadHandler.postAtFrontOfQueue(new Runnable() {
                    public void run() {
                        if (EglRenderer.this.eglBase != null) {
                            EglRenderer.this.eglBase.detachCurrent();
                            EglRenderer.this.eglBase.releaseSurface();
                        }
                        completionCallback.run();
                    }
                });
                return;
            }
            completionCallback.run();
        }
    }

    private void postToRenderThread(Runnable runnable) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                this.renderThreadHandler.post(runnable);
            }
        }
    }

    /* access modifiers changed from: private */
    public void clearSurfaceOnRenderThread(float r, float g, float b, float a) {
        if (this.eglBase != null && this.eglBase.hasSurface()) {
            logD("clearSurface");
            GLES20.glClearColor(r, g, b, a);
            GLES20.glClear(16384);
            this.eglBase.swapBuffers();
        }
    }

    public void clearImage() {
        clearImage(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void clearImage(float r, float g, float b, float a) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                final float f = r;
                final float f2 = g;
                final float f3 = b;
                final float f4 = a;
                this.renderThreadHandler.postAtFrontOfQueue(new Runnable() {
                    public void run() {
                        EglRenderer.this.clearSurfaceOnRenderThread(f, f2, f3, f4);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:104:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        if (r31.eglBase == null) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
        if (r31.eglBase.hasSurface() != false) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        logD("Dropping frame - No surface");
        org.webrtc.VideoRenderer.renderFrameDone(r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0037, code lost:
        r9 = r31.fpsReductionLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003b, code lost:
        monitor-enter(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0047, code lost:
        if (r31.minRenderPeriodNs != kotlin.jvm.internal.LongCompanionObject.MAX_VALUE) goto L_0x0164;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0049, code lost:
        r24 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004b, code lost:
        monitor-exit(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004c, code lost:
        r26 = java.lang.System.nanoTime();
        r30 = org.webrtc.RendererCommon.rotateTextureMatrix(r20.samplingMatrix, (float) r20.rotationDegree);
        r9 = r31.layoutLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0061, code lost:
        monitor-enter(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0069, code lost:
        if (r31.layoutAspectRatio <= 0.0f) goto L_0x01bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006b, code lost:
        r21 = ((float) r20.rotatedWidth()) / ((float) r20.rotatedHeight());
        r22 = org.webrtc.RendererCommon.getLayoutMatrix(r31.mirror, r21, r31.layoutAspectRatio);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008b, code lost:
        if (r21 <= r31.layoutAspectRatio) goto L_0x01ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008d, code lost:
        r7 = (int) (((float) r20.rotatedHeight()) * r31.layoutAspectRatio);
        r8 = r20.rotatedHeight();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009c, code lost:
        r6 = org.webrtc.RendererCommon.multiplyMatrices(r30, r22);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a4, code lost:
        monitor-exit(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a5, code lost:
        r25 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ab, code lost:
        if (r20.yuvFrame == false) goto L_0x00d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ad, code lost:
        r25 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00af, code lost:
        if (r25 != false) goto L_0x00d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b1, code lost:
        r4 = r31.frameListeners.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00bd, code lost:
        if (r4.hasNext() == false) goto L_0x00d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00bf, code lost:
        r23 = r4.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00cc, code lost:
        if (r23.scale == 0.0f) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ce, code lost:
        if (r24 != false) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d4, code lost:
        if (r23.applyFpsReduction != false) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d6, code lost:
        r25 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d8, code lost:
        if (r25 == false) goto L_0x01d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00da, code lost:
        r5 = r31.yuvUploader.uploadYuvData(r20.width, r20.height, r20.yuvStrides, r20.yuvPlanes);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f2, code lost:
        if (r24 == false) goto L_0x0154;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f4, code lost:
        android.opengl.GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        android.opengl.GLES20.glClear(16384);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0104, code lost:
        if (r20.yuvFrame == false) goto L_0x01db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0106, code lost:
        r31.drawer.drawYuv(r5, r6, r7, r8, 0, 0, r31.eglBase.surfaceWidth(), r31.eglBase.surfaceHeight());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x011f, code lost:
        r28 = java.lang.System.nanoTime();
        r31.eglBase.swapBuffers();
        r18 = java.lang.System.nanoTime();
        r9 = r31.statisticsLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0132, code lost:
        monitor-enter(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        r31.framesRendered++;
        r31.renderTimeNs += r18 - r26;
        r31.renderSwapBufferTimeNs += r18 - r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0153, code lost:
        monitor-exit(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0154, code lost:
        notifyCallbacks(r20, r5, r30, r24);
        org.webrtc.VideoRenderer.renderFrameDone(r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x016c, code lost:
        if (r31.minRenderPeriodNs > 0) goto L_0x0172;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x016e, code lost:
        r24 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0172, code lost:
        r18 = java.lang.System.nanoTime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x017c, code lost:
        if (r18 >= r31.nextFrameTimeNs) goto L_0x0189;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x017e, code lost:
        logD("Skipping frame rendering - fps reduction is active.");
        r24 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0189, code lost:
        r31.nextFrameTimeNs += r31.minRenderPeriodNs;
        r31.nextFrameTimeNs = java.lang.Math.max(r31.nextFrameTimeNs, r18);
        r24 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
        r7 = r20.rotatedWidth();
        r8 = (int) (((float) r20.rotatedWidth()) / r31.layoutAspectRatio);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01c0, code lost:
        if (r31.mirror == false) goto L_0x01d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01c2, code lost:
        r22 = org.webrtc.RendererCommon.horizontalFlipMatrix();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01c6, code lost:
        r7 = r20.rotatedWidth();
        r8 = r20.rotatedHeight();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01d0, code lost:
        r22 = org.webrtc.RendererCommon.identityMatrix();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01d8, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01db, code lost:
        r31.drawer.drawOes(r20.textureId, r6, r7, r8, 0, 0, r31.eglBase.surfaceWidth(), r31.eglBase.surfaceHeight());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void renderFrameOnRenderThread() {
        /*
            r31 = this;
            r0 = r31
            java.lang.Object r9 = r0.frameLock
            monitor-enter(r9)
            r0 = r31
            org.webrtc.VideoRenderer$I420Frame r4 = r0.pendingFrame     // Catch:{ all -> 0x0034 }
            if (r4 != 0) goto L_0x000d
            monitor-exit(r9)     // Catch:{ all -> 0x0034 }
        L_0x000c:
            return
        L_0x000d:
            r0 = r31
            org.webrtc.VideoRenderer$I420Frame r0 = r0.pendingFrame     // Catch:{ all -> 0x0034 }
            r20 = r0
            r4 = 0
            r0 = r31
            r0.pendingFrame = r4     // Catch:{ all -> 0x0034 }
            monitor-exit(r9)     // Catch:{ all -> 0x0034 }
            r0 = r31
            org.webrtc.EglBase r4 = r0.eglBase
            if (r4 == 0) goto L_0x0029
            r0 = r31
            org.webrtc.EglBase r4 = r0.eglBase
            boolean r4 = r4.hasSurface()
            if (r4 != 0) goto L_0x0037
        L_0x0029:
            java.lang.String r4 = "Dropping frame - No surface"
            r0 = r31
            r0.logD(r4)
            org.webrtc.VideoRenderer.renderFrameDone(r20)
            goto L_0x000c
        L_0x0034:
            r4 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x0034 }
            throw r4
        L_0x0037:
            r0 = r31
            java.lang.Object r9 = r0.fpsReductionLock
            monitor-enter(r9)
            r0 = r31
            long r10 = r0.minRenderPeriodNs     // Catch:{ all -> 0x01a8 }
            r12 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r4 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r4 != 0) goto L_0x0164
            r24 = 0
        L_0x004b:
            monitor-exit(r9)     // Catch:{ all -> 0x01a8 }
            long r26 = java.lang.System.nanoTime()
            r0 = r20
            float[] r4 = r0.samplingMatrix
            r0 = r20
            int r9 = r0.rotationDegree
            float r9 = (float) r9
            float[] r30 = org.webrtc.RendererCommon.rotateTextureMatrix(r4, r9)
            r0 = r31
            java.lang.Object r9 = r0.layoutLock
            monitor-enter(r9)
            r0 = r31
            float r4 = r0.layoutAspectRatio     // Catch:{ all -> 0x01d5 }
            r10 = 0
            int r4 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r4 <= 0) goto L_0x01bc
            int r4 = r20.rotatedWidth()     // Catch:{ all -> 0x01d5 }
            float r4 = (float) r4     // Catch:{ all -> 0x01d5 }
            int r10 = r20.rotatedHeight()     // Catch:{ all -> 0x01d5 }
            float r10 = (float) r10     // Catch:{ all -> 0x01d5 }
            float r21 = r4 / r10
            r0 = r31
            boolean r4 = r0.mirror     // Catch:{ all -> 0x01d5 }
            r0 = r31
            float r10 = r0.layoutAspectRatio     // Catch:{ all -> 0x01d5 }
            r0 = r21
            float[] r22 = org.webrtc.RendererCommon.getLayoutMatrix(r4, r0, r10)     // Catch:{ all -> 0x01d5 }
            r0 = r31
            float r4 = r0.layoutAspectRatio     // Catch:{ all -> 0x01d5 }
            int r4 = (r21 > r4 ? 1 : (r21 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x01ab
            int r4 = r20.rotatedHeight()     // Catch:{ all -> 0x01d5 }
            float r4 = (float) r4     // Catch:{ all -> 0x01d5 }
            r0 = r31
            float r10 = r0.layoutAspectRatio     // Catch:{ all -> 0x01d5 }
            float r4 = r4 * r10
            int r7 = (int) r4     // Catch:{ all -> 0x01d5 }
            int r8 = r20.rotatedHeight()     // Catch:{ all -> 0x01d5 }
        L_0x009c:
            r0 = r30
            r1 = r22
            float[] r6 = org.webrtc.RendererCommon.multiplyMatrices(r0, r1)     // Catch:{ all -> 0x01d5 }
            monitor-exit(r9)     // Catch:{ all -> 0x01d5 }
            r25 = 0
            r0 = r20
            boolean r4 = r0.yuvFrame
            if (r4 == 0) goto L_0x00d8
            r25 = r24
            if (r25 != 0) goto L_0x00d8
            r0 = r31
            java.util.ArrayList<org.webrtc.EglRenderer$FrameListenerAndParams> r4 = r0.frameListeners
            java.util.Iterator r4 = r4.iterator()
        L_0x00b9:
            boolean r9 = r4.hasNext()
            if (r9 == 0) goto L_0x00d8
            java.lang.Object r23 = r4.next()
            org.webrtc.EglRenderer$FrameListenerAndParams r23 = (org.webrtc.EglRenderer.FrameListenerAndParams) r23
            r0 = r23
            float r9 = r0.scale
            r10 = 0
            int r9 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            if (r9 == 0) goto L_0x00b9
            if (r24 != 0) goto L_0x00d6
            r0 = r23
            boolean r9 = r0.applyFpsReduction
            if (r9 != 0) goto L_0x00b9
        L_0x00d6:
            r25 = 1
        L_0x00d8:
            if (r25 == 0) goto L_0x01d8
            r0 = r31
            org.webrtc.RendererCommon$YuvUploader r4 = r0.yuvUploader
            r0 = r20
            int r9 = r0.width
            r0 = r20
            int r10 = r0.height
            r0 = r20
            int[] r11 = r0.yuvStrides
            r0 = r20
            java.nio.ByteBuffer[] r12 = r0.yuvPlanes
            int[] r5 = r4.uploadYuvData(r9, r10, r11, r12)
        L_0x00f2:
            if (r24 == 0) goto L_0x0154
            r4 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            android.opengl.GLES20.glClearColor(r4, r9, r10, r11)
            r4 = 16384(0x4000, float:2.2959E-41)
            android.opengl.GLES20.glClear(r4)
            r0 = r20
            boolean r4 = r0.yuvFrame
            if (r4 == 0) goto L_0x01db
            r0 = r31
            org.webrtc.RendererCommon$GlDrawer r4 = r0.drawer
            r9 = 0
            r10 = 0
            r0 = r31
            org.webrtc.EglBase r11 = r0.eglBase
            int r11 = r11.surfaceWidth()
            r0 = r31
            org.webrtc.EglBase r12 = r0.eglBase
            int r12 = r12.surfaceHeight()
            r4.drawYuv(r5, r6, r7, r8, r9, r10, r11, r12)
        L_0x011f:
            long r28 = java.lang.System.nanoTime()
            r0 = r31
            org.webrtc.EglBase r4 = r0.eglBase
            r4.swapBuffers()
            long r18 = java.lang.System.nanoTime()
            r0 = r31
            java.lang.Object r9 = r0.statisticsLock
            monitor-enter(r9)
            r0 = r31
            int r4 = r0.framesRendered     // Catch:{ all -> 0x01fd }
            int r4 = r4 + 1
            r0 = r31
            r0.framesRendered = r4     // Catch:{ all -> 0x01fd }
            r0 = r31
            long r10 = r0.renderTimeNs     // Catch:{ all -> 0x01fd }
            long r12 = r18 - r26
            long r10 = r10 + r12
            r0 = r31
            r0.renderTimeNs = r10     // Catch:{ all -> 0x01fd }
            r0 = r31
            long r10 = r0.renderSwapBufferTimeNs     // Catch:{ all -> 0x01fd }
            long r12 = r18 - r28
            long r10 = r10 + r12
            r0 = r31
            r0.renderSwapBufferTimeNs = r10     // Catch:{ all -> 0x01fd }
            monitor-exit(r9)     // Catch:{ all -> 0x01fd }
        L_0x0154:
            r0 = r31
            r1 = r20
            r2 = r30
            r3 = r24
            r0.notifyCallbacks(r1, r5, r2, r3)
            org.webrtc.VideoRenderer.renderFrameDone(r20)
            goto L_0x000c
        L_0x0164:
            r0 = r31
            long r10 = r0.minRenderPeriodNs     // Catch:{ all -> 0x01a8 }
            r12 = 0
            int r4 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r4 > 0) goto L_0x0172
            r24 = 1
            goto L_0x004b
        L_0x0172:
            long r18 = java.lang.System.nanoTime()     // Catch:{ all -> 0x01a8 }
            r0 = r31
            long r10 = r0.nextFrameTimeNs     // Catch:{ all -> 0x01a8 }
            int r4 = (r18 > r10 ? 1 : (r18 == r10 ? 0 : -1))
            if (r4 >= 0) goto L_0x0189
            java.lang.String r4 = "Skipping frame rendering - fps reduction is active."
            r0 = r31
            r0.logD(r4)     // Catch:{ all -> 0x01a8 }
            r24 = 0
            goto L_0x004b
        L_0x0189:
            r0 = r31
            long r10 = r0.nextFrameTimeNs     // Catch:{ all -> 0x01a8 }
            r0 = r31
            long r12 = r0.minRenderPeriodNs     // Catch:{ all -> 0x01a8 }
            long r10 = r10 + r12
            r0 = r31
            r0.nextFrameTimeNs = r10     // Catch:{ all -> 0x01a8 }
            r0 = r31
            long r10 = r0.nextFrameTimeNs     // Catch:{ all -> 0x01a8 }
            r0 = r18
            long r10 = java.lang.Math.max(r10, r0)     // Catch:{ all -> 0x01a8 }
            r0 = r31
            r0.nextFrameTimeNs = r10     // Catch:{ all -> 0x01a8 }
            r24 = 1
            goto L_0x004b
        L_0x01a8:
            r4 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x01a8 }
            throw r4
        L_0x01ab:
            int r7 = r20.rotatedWidth()     // Catch:{ all -> 0x01d5 }
            int r4 = r20.rotatedWidth()     // Catch:{ all -> 0x01d5 }
            float r4 = (float) r4     // Catch:{ all -> 0x01d5 }
            r0 = r31
            float r10 = r0.layoutAspectRatio     // Catch:{ all -> 0x01d5 }
            float r4 = r4 / r10
            int r8 = (int) r4     // Catch:{ all -> 0x01d5 }
            goto L_0x009c
        L_0x01bc:
            r0 = r31
            boolean r4 = r0.mirror     // Catch:{ all -> 0x01d5 }
            if (r4 == 0) goto L_0x01d0
            float[] r22 = org.webrtc.RendererCommon.horizontalFlipMatrix()     // Catch:{ all -> 0x01d5 }
        L_0x01c6:
            int r7 = r20.rotatedWidth()     // Catch:{ all -> 0x01d5 }
            int r8 = r20.rotatedHeight()     // Catch:{ all -> 0x01d5 }
            goto L_0x009c
        L_0x01d0:
            float[] r22 = org.webrtc.RendererCommon.identityMatrix()     // Catch:{ all -> 0x01d5 }
            goto L_0x01c6
        L_0x01d5:
            r4 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x01d5 }
            throw r4
        L_0x01d8:
            r5 = 0
            goto L_0x00f2
        L_0x01db:
            r0 = r31
            org.webrtc.RendererCommon$GlDrawer r9 = r0.drawer
            r0 = r20
            int r10 = r0.textureId
            r14 = 0
            r15 = 0
            r0 = r31
            org.webrtc.EglBase r4 = r0.eglBase
            int r16 = r4.surfaceWidth()
            r0 = r31
            org.webrtc.EglBase r4 = r0.eglBase
            int r17 = r4.surfaceHeight()
            r11 = r6
            r12 = r7
            r13 = r8
            r9.drawOes(r10, r11, r12, r13, r14, r15, r16, r17)
            goto L_0x011f
        L_0x01fd:
            r4 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x01fd }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.EglRenderer.renderFrameOnRenderThread():void");
    }

    private void notifyCallbacks(VideoRenderer.I420Frame frame, int[] yuvTextures, float[] texMatrix, boolean wasRendered) {
        if (!this.frameListeners.isEmpty()) {
            float[] bitmapMatrix = RendererCommon.multiplyMatrices(RendererCommon.multiplyMatrices(texMatrix, this.mirror ? RendererCommon.horizontalFlipMatrix() : RendererCommon.identityMatrix()), RendererCommon.verticalFlipMatrix());
            Iterator<FrameListenerAndParams> it = this.frameListeners.iterator();
            while (it.hasNext()) {
                FrameListenerAndParams listenerAndParams = it.next();
                if (wasRendered || !listenerAndParams.applyFpsReduction) {
                    it.remove();
                    int scaledWidth = (int) (listenerAndParams.scale * ((float) frame.rotatedWidth()));
                    int scaledHeight = (int) (listenerAndParams.scale * ((float) frame.rotatedHeight()));
                    if (scaledWidth == 0 || scaledHeight == 0) {
                        listenerAndParams.listener.onFrame((Bitmap) null);
                    } else {
                        if (this.bitmapTextureFramebuffer == null) {
                            this.bitmapTextureFramebuffer = new GlTextureFrameBuffer(6408);
                        }
                        this.bitmapTextureFramebuffer.setSize(scaledWidth, scaledHeight);
                        GLES20.glBindFramebuffer(36160, this.bitmapTextureFramebuffer.getFrameBufferId());
                        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.bitmapTextureFramebuffer.getTextureId(), 0);
                        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                        GLES20.glClear(16384);
                        if (frame.yuvFrame) {
                            listenerAndParams.drawer.drawYuv(yuvTextures, bitmapMatrix, frame.rotatedWidth(), frame.rotatedHeight(), 0, 0, scaledWidth, scaledHeight);
                        } else {
                            listenerAndParams.drawer.drawOes(frame.textureId, bitmapMatrix, frame.rotatedWidth(), frame.rotatedHeight(), 0, 0, scaledWidth, scaledHeight);
                        }
                        ByteBuffer bitmapBuffer = ByteBuffer.allocateDirect(scaledWidth * scaledHeight * 4);
                        GLES20.glViewport(0, 0, scaledWidth, scaledHeight);
                        GLES20.glReadPixels(0, 0, scaledWidth, scaledHeight, 6408, 5121, bitmapBuffer);
                        GLES20.glBindFramebuffer(36160, 0);
                        GlUtil.checkNoGLES2Error("EglRenderer.notifyCallbacks");
                        Bitmap bitmap = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888);
                        bitmap.copyPixelsFromBuffer(bitmapBuffer);
                        listenerAndParams.listener.onFrame(bitmap);
                    }
                }
            }
        }
    }

    private String averageTimeAsString(long sumTimeNs, int count) {
        return count <= 0 ? "NA" : TimeUnit.NANOSECONDS.toMicros(sumTimeNs / ((long) count)) + " μs";
    }

    /* access modifiers changed from: private */
    public void logStatistics() {
        long currentTimeNs = System.nanoTime();
        synchronized (this.statisticsLock) {
            long elapsedTimeNs = currentTimeNs - this.statisticsStartTimeNs;
            if (elapsedTimeNs > 0) {
                float renderFps = ((float) (((long) this.framesRendered) * TimeUnit.SECONDS.toNanos(1))) / ((float) elapsedTimeNs);
                logD("Duration: " + TimeUnit.NANOSECONDS.toMillis(elapsedTimeNs) + " ms. Frames received: " + this.framesReceived + ". Dropped: " + this.framesDropped + ". Rendered: " + this.framesRendered + ". Render fps: " + String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(renderFps)}) + ". Average render time: " + averageTimeAsString(this.renderTimeNs, this.framesRendered) + ". Average swapBuffer time: " + averageTimeAsString(this.renderSwapBufferTimeNs, this.framesRendered) + ".");
                resetStatistics(currentTimeNs);
            }
        }
    }

    /* access modifiers changed from: private */
    public void logD(String string) {
        Logging.d(TAG, this.name + string);
    }
}
