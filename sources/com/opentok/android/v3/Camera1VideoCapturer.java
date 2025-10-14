package com.opentok.android.v3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.WindowManager;
import com.opentok.android.v3.AbstractCapturer;
import com.opentok.android.v3.DefaultVideoCapturer;
import com.opentok.android.v3.debug.LogInterface;
import com.opentok.android.v3.debug.OtLog;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressLint({"WrongConstant"})
final class Camera1VideoCapturer extends AbstractCapturer implements Camera.ErrorCallback, AbstractCapturer.CaptureSwitch, SurfaceTexture.OnFrameAvailableListener {
    private static final SparseIntArray frameRateTable = new SparseIntArray() {
        {
            append(DefaultVideoCapturer.FrameRate.FPS_1.ordinal(), 1000);
            append(DefaultVideoCapturer.FrameRate.FPS_7.ordinal(), 7000);
            append(DefaultVideoCapturer.FrameRate.FPS_15.ordinal(), 15000);
            append(DefaultVideoCapturer.FrameRate.FPS_30.ordinal(), 30000);
        }
    };
    /* access modifiers changed from: private */
    public static final LogInterface log = OtLog.LogToken("[Camera-Legacy]");
    private static final SparseArray<int[]> resolutionTable = new SparseArray<int[]>() {
        {
            append(DefaultVideoCapturer.Resolution.LOW.ordinal(), new int[]{352, 288});
            append(DefaultVideoCapturer.Resolution.MEDIUM.ordinal(), new int[]{640, 480});
            append(DefaultVideoCapturer.Resolution.HIGH.ordinal(), new int[]{1280, 720});
        }
    };
    /* access modifiers changed from: private */
    public static final SparseIntArray rotationTable = new SparseIntArray() {
        {
            append(0, 0);
            append(1, 90);
            append(2, 180);
            append(3, 270);
        }
    };
    private Handler camHandler = null;
    private HandlerThread camThread = null;
    /* access modifiers changed from: private */
    public Camera camera = null;
    /* access modifiers changed from: private */
    public int cameraIndex = 0;
    /* access modifiers changed from: private */
    public int[] cameraResolution = {0, 0};
    /* access modifiers changed from: private */
    public Context context = null;
    /* access modifiers changed from: private */
    public Camera.Parameters currentCamParams = null;
    /* access modifiers changed from: private */
    public Camera.CameraInfo currentDeviceInfo = new Camera.CameraInfo();
    /* access modifiers changed from: private */
    public int framesPerSec = 0;
    private Runnable init_runnable = new Runnable() {
        public void run() {
            Camera unused = Camera1VideoCapturer.this.camera = Camera.open(Camera1VideoCapturer.this.cameraIndex);
            Camera1VideoCapturer.this.camera.setErrorCallback(Camera1VideoCapturer.this);
            Camera.Parameters unused2 = Camera1VideoCapturer.this.currentCamParams = Camera1VideoCapturer.this.camera.getParameters();
            Camera.getCameraInfo(Camera1VideoCapturer.this.cameraIndex, Camera1VideoCapturer.this.currentDeviceInfo);
            if (Camera1VideoCapturer.this.currentDeviceInfo.facing == 1 && "samsung-sm-g900a".equals(Build.MODEL.toLowerCase(Locale.ROOT)) && 30000 == Camera1VideoCapturer.this.framesPerSec) {
                int unused3 = Camera1VideoCapturer.this.framesPerSec = 24000;
            }
            Camera1VideoCapturer.this.state.set(State.INITALIZED.ordinal());
        }
    };
    /* access modifiers changed from: private */
    public OrientationCache orientationCache;
    private int prePausedState = State.DEAD.ordinal();
    private Runnable start_runnable = new Runnable() {
        public void run() {
            try {
                int[] size = Camera1VideoCapturer._findClosestCaptureSize(Camera1VideoCapturer.this.currentCamParams, Camera1VideoCapturer.this.cameraResolution);
                int[] fps = Camera1VideoCapturer._findClosestFps(Camera1VideoCapturer.this.currentCamParams, Camera1VideoCapturer.this.framesPerSec);
                OrientationCache unused = Camera1VideoCapturer.this.orientationCache = new OrientationCache((WindowManager) Camera1VideoCapturer.this.context.getSystemService("window"), new Handler(Looper.getMainLooper()));
                Camera1VideoCapturer.this.currentCamParams.setPreviewSize(size[0], size[1]);
                Camera1VideoCapturer.this.currentCamParams.setPreviewFpsRange(fps[0], fps[1]);
                Camera1VideoCapturer.this.camera.setParameters(Camera1VideoCapturer.this.currentCamParams);
                SurfaceTexture unused2 = Camera1VideoCapturer.this.surfaceTexture = new SurfaceTexture(1);
                Camera1VideoCapturer.this.surfaceTexture.setDefaultBufferSize(size[0], size[1]);
                Camera1VideoCapturer.this.surfaceTexture.setOnFrameAvailableListener(Camera1VideoCapturer.this);
                Camera1VideoCapturer.this.camera.setPreviewTexture(Camera1VideoCapturer.this.surfaceTexture);
                Camera1VideoCapturer.this.camera.startPreview();
                Camera1VideoCapturer.this.state.set(State.STARTED.ordinal());
            } catch (Exception e) {
                Camera1VideoCapturer.this.onError(e);
            }
        }
    };
    /* access modifiers changed from: private */
    public AtomicInteger state = new AtomicInteger(State.DEAD.ordinal());
    private Runnable stop_runnable = new Runnable() {
        public void run() {
            Camera1VideoCapturer.this.camera.stopPreview();
            Camera1VideoCapturer.this.camera.release();
            Camera1VideoCapturer.this.surfaceTexture.setOnFrameAvailableListener((SurfaceTexture.OnFrameAvailableListener) null);
            Camera1VideoCapturer.this.orientationCache.shutdown();
            Camera1VideoCapturer.this.state.set(State.INITALIZED.ordinal());
        }
    };
    /* access modifiers changed from: private */
    public SurfaceTexture surfaceTexture = null;

    private enum State {
        DEAD,
        INITALIZED,
        STARTED
    }

    Camera1VideoCapturer(Context context2, DefaultVideoCapturer.FrameRate fps, DefaultVideoCapturer.Resolution resolution, AbstractCapturer.CapturerError errorCb) {
        this.context = context2;
        this.cameraIndex = findCameraIndex(1);
        this.cameraResolution = resolutionTable.get(resolution.ordinal());
        this.framesPerSec = frameRateTable.get(fps.ordinal());
        registerCapturerError(errorCb);
    }

    public synchronized boolean initialize() throws Exception {
        boolean z = false;
        synchronized (this) {
            log.d("init(...) called", new Object[0]);
            if (State.DEAD.ordinal() == this.state.get()) {
                try {
                    super.initialize();
                    startCamThread();
                    FutureTask<Void> task = new FutureTask<>(this.init_runnable, (Object) null);
                    this.camHandler.post(task);
                    task.get();
                } catch (Exception e) {
                    super.destroy();
                    throw e;
                }
            }
            if (State.INITALIZED.ordinal() == this.state.get()) {
                z = true;
            }
        }
        return z;
    }

    public synchronized boolean start() throws Exception {
        boolean z = false;
        synchronized (this) {
            log.d("start(...) called", new Object[0]);
            if (State.INITALIZED.ordinal() == this.state.get()) {
                FutureTask<Void> task = new FutureTask<>(this.start_runnable, (Object) null);
                this.camHandler.post(task);
                task.get();
            }
            log.d("start(...) end", new Object[0]);
            if (State.STARTED.ordinal() == this.state.get()) {
                z = true;
            }
        }
        return z;
    }

    public synchronized boolean stop() throws Exception {
        boolean z = false;
        synchronized (this) {
            log.d("stop(...) called", new Object[0]);
            if (State.STARTED.ordinal() == this.state.get()) {
                FutureTask<Void> task = new FutureTask<>(this.stop_runnable, (Object) null);
                this.camHandler.post(task);
                task.get();
            }
            log.d("stop(...) exited", new Object[0]);
            if (State.INITALIZED.ordinal() == this.state.get()) {
                z = true;
            }
        }
        return z;
    }

    public synchronized boolean destroy() throws Exception {
        boolean z = false;
        synchronized (this) {
            log.d("destroy(...) called", new Object[0]);
            if (State.INITALIZED.ordinal() == this.state.get()) {
                stopCamThread();
                this.state.set(State.DEAD.ordinal());
                this.surfaceTexture = null;
                this.camera = null;
                super.destroy();
            }
            if (State.DEAD.ordinal() == this.state.get()) {
                z = true;
            }
        }
        return z;
    }

    public boolean isInitialized() throws Exception {
        return State.INITALIZED.ordinal() == this.state.get();
    }

    public boolean isStarted() throws Exception {
        return State.STARTED.ordinal() == this.state.get();
    }

    public AbstractCapturer.CaptureSettings getCaptureSettings() {
        return new AbstractCapturer.CaptureSettings(this.cameraResolution[0], this.cameraResolution[1], this.framesPerSec / 1000);
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture2) {
        boolean z = true;
        if (State.STARTED.ordinal() == this.state.get()) {
            int i = this.cameraResolution[0];
            int i2 = this.cameraResolution[1];
            int rotation = this.orientationCache.getRotation();
            if (this.currentDeviceInfo.facing != 1) {
                z = false;
            }
            onCaptureFrame(surfaceTexture2, i, i2, rotation, z, (byte[]) null);
        }
    }

    public void pause() throws Exception {
        this.prePausedState = this.state.get();
        if (State.STARTED.ordinal() == this.prePausedState) {
            stop();
            destroy();
        } else if (State.INITALIZED.ordinal() == this.prePausedState) {
            destroy();
        }
    }

    public void resume() throws Exception {
        if (State.STARTED.ordinal() == this.prePausedState) {
            initialize();
            start();
        } else if (State.INITALIZED.ordinal() == this.prePausedState) {
            initialize();
        }
        this.prePausedState = State.DEAD.ordinal();
    }

    public void cycleCamera() throws Exception {
        swapCamera((this.cameraIndex + 1) % Camera.getNumberOfCameras());
    }

    public void onError(int error, Camera camera2) {
        if (this.camera == camera2) {
            onError(new RuntimeException("android.hardware.Camera Error Code:" + error));
        }
    }

    @SuppressLint({"DefaultLocale"})
    private void swapCamera(int index) throws Exception {
        int previousState = this.state.get();
        pause();
        this.cameraIndex = index;
        if (State.STARTED.ordinal() == previousState && Build.MODEL.toLowerCase().contains("samsung")) {
            forceCameraRelease(index);
        }
        resume();
    }

    private void forceCameraRelease(int cameraIndex2) {
        Camera camera2 = null;
        try {
            Camera camera3 = Camera.open(cameraIndex2);
            if (camera3 != null) {
                camera3.release();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            if (camera2 != null) {
                camera2.release();
            }
        } catch (Throwable th) {
            if (camera2 != null) {
                camera2.release();
            }
            throw th;
        }
    }

    private static int findCameraIndex(int facingDirection) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            Camera.getCameraInfo(i, info);
            if (facingDirection == info.facing) {
                return i;
            }
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public static int[] _findClosestCaptureSize(Camera.Parameters parameters, final int[] resolution) {
        Camera.Size sz = (Camera.Size) Collections.min(parameters.getSupportedPreviewSizes(), new Comparator<Camera.Size>() {
            public int compare(Camera.Size lhs, Camera.Size rhs) {
                return (Math.abs(lhs.width - resolution[0]) + Math.abs(lhs.height - resolution[1])) - (Math.abs(rhs.width - resolution[0]) + Math.abs(rhs.height - resolution[1]));
            }
        });
        return new int[]{sz.width, sz.height};
    }

    /* access modifiers changed from: private */
    public static int[] _findClosestFps(Camera.Parameters parameters, final int fps) {
        return (int[]) Collections.min(parameters.getSupportedPreviewFpsRange(), new Comparator<int[]>() {
            public int compare(int[] lhs, int[] rhs) {
                return calcError(lhs) - calcError(rhs);
            }

            private int calcError(int[] val) {
                return val[0] + Math.abs(val[1] - fps);
            }
        });
    }

    private void startCamThread() {
        this.camThread = new HandlerThread("Camera-Thread");
        this.camThread.start();
        this.camHandler = new Handler(this.camThread.getLooper());
    }

    private void stopCamThread() {
        try {
            this.camThread.quit();
            this.camThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NullPointerException e2) {
        } finally {
            this.camThread = null;
            this.camHandler = null;
        }
    }

    private static abstract class RunnableTrap implements Runnable {
        Exception exp;

        /* access modifiers changed from: protected */
        public abstract void runTrap() throws Exception;

        private RunnableTrap() {
        }

        public void run() {
            try {
                runTrap();
            } catch (Exception e) {
                this.exp = e;
            }
        }
    }

    private class OrientationCache implements Runnable {
        private static final int POLL_DELAY_MS = 750;
        private Display defaultDisplay;
        private boolean exit;
        private Handler handler;
        private AtomicInteger rotation = new AtomicInteger(0);
        private WindowManager winMgr;

        OrientationCache(WindowManager wm, Handler hndlr) {
            this.winMgr = wm;
            this.handler = hndlr;
            this.exit = false;
            this.defaultDisplay = this.winMgr.getDefaultDisplay();
            this.handler.post(this);
        }

        /* access modifiers changed from: package-private */
        public int calculateCamRotation() {
            if (Camera1VideoCapturer.this.currentDeviceInfo == null || this.defaultDisplay == null) {
                return 0;
            }
            int displayOrientation = Camera1VideoCapturer.rotationTable.get(this.defaultDisplay.getRotation());
            int cameraOrientation = Camera1VideoCapturer.this.currentDeviceInfo.orientation;
            if (Camera1VideoCapturer.this.currentDeviceInfo.facing != 1) {
                return ((cameraOrientation - displayOrientation) + 360) % 360;
            }
            return (cameraOrientation + displayOrientation) % 360;
        }

        /* access modifiers changed from: package-private */
        public void updateDisplay() {
            this.defaultDisplay = this.winMgr.getDefaultDisplay();
        }

        public int getRotation() {
            return this.rotation.get();
        }

        public void run() {
            if (!this.exit) {
                Camera1VideoCapturer.log.d("OrientationCache::run()", new Object[0]);
                updateDisplay();
                this.rotation.set(calculateCamRotation());
                this.handler.postDelayed(this, 750);
            }
        }

        public void shutdown() {
            Camera1VideoCapturer.log.d("OrientationCache::shutdown()", new Object[0]);
            this.exit = true;
            this.handler.removeCallbacks(this);
        }
    }
}
