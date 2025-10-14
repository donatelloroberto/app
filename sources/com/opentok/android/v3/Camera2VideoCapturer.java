package com.opentok.android.v3;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.util.Range;
import android.util.Size;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.WindowManager;
import com.opentok.android.v3.AbstractCapturer;
import com.opentok.android.v3.DefaultVideoCapturer;
import com.opentok.android.v3.debug.LogInterface;
import com.opentok.android.v3.debug.OtLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@TargetApi(21)
final class Camera2VideoCapturer extends AbstractCapturer implements AbstractCapturer.CaptureSwitch, SurfaceTexture.OnFrameAvailableListener {
    private static final int PIXEL_FORMAT = 35;
    private static final int PREFERRED_FACING_CAMERA = 0;
    private static final SparseIntArray frameRateTable = new SparseIntArray() {
        {
            append(DefaultVideoCapturer.FrameRate.FPS_1.ordinal(), 1);
            append(DefaultVideoCapturer.FrameRate.FPS_7.ordinal(), 7);
            append(DefaultVideoCapturer.FrameRate.FPS_15.ordinal(), 15);
            append(DefaultVideoCapturer.FrameRate.FPS_30.ordinal(), 30);
        }
    };
    /* access modifiers changed from: private */
    public static final LogInterface log = OtLog.LogToken("[Camera-2]");
    private static final SparseArray<Size> resolutionTable = new SparseArray<Size>() {
        {
            append(DefaultVideoCapturer.Resolution.LOW.ordinal(), new Size(352, 288));
            append(DefaultVideoCapturer.Resolution.MEDIUM.ordinal(), new Size(640, 480));
            append(DefaultVideoCapturer.Resolution.HIGH.ordinal(), new Size(1280, 720));
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
    /* access modifiers changed from: private */
    public Size actualDimentions;
    /* access modifiers changed from: private */
    public Range<Integer> actualFps;
    /* access modifiers changed from: private */
    public Handler camHandler;
    private HandlerThread camThread;
    /* access modifiers changed from: private */
    public CameraDevice camera;
    private int cameraIndex;
    private CameraManager cameraManager;
    private final CameraDevice.StateCallback cameraObserver = new CameraDevice.StateCallback() {
        public void onOpened(@NonNull CameraDevice camera) {
            Camera2VideoCapturer.log.d("onOpened", new Object[0]);
            SurfaceTexture unused = Camera2VideoCapturer.this.surfaceTexture = new SurfaceTexture(1);
            Camera2VideoCapturer.this.surfaceTexture.setDefaultBufferSize(Camera2VideoCapturer.this.actualDimentions.getWidth(), Camera2VideoCapturer.this.actualDimentions.getHeight());
            Surface unused2 = Camera2VideoCapturer.this.surface = new Surface(Camera2VideoCapturer.this.surfaceTexture);
            Camera2VideoCapturer.this.surfaceTexture.setOnFrameAvailableListener(Camera2VideoCapturer.this);
            CameraState unused3 = Camera2VideoCapturer.this.cameraState = CameraState.OPEN;
            CameraDevice unused4 = Camera2VideoCapturer.this.camera = camera;
            Camera2VideoCapturer.this.signalCamStateChange();
        }

        public void onDisconnected(@NonNull CameraDevice camera) {
            try {
                Camera2VideoCapturer.log.d("onDisconnected", new Object[0]);
                Camera2VideoCapturer.this.camera.close();
                Camera2VideoCapturer.this.waitForCamStateChange(Camera2VideoCapturer.this.cameraState);
            } catch (NullPointerException e) {
            }
        }

        public void onError(@NonNull CameraDevice camera, int error) {
            try {
                Camera2VideoCapturer.log.d("onError", new Object[0]);
                Camera2VideoCapturer.this.camera.close();
                Camera2VideoCapturer.this.waitForCamStateChange(Camera2VideoCapturer.this.cameraState);
            } catch (NullPointerException e) {
            }
            Camera2VideoCapturer.this.postAsyncException(new RuntimeException("Camera Open Error: " + error));
        }

        public void onClosed(@NonNull CameraDevice camera) {
            Camera2VideoCapturer.log.d("onClosed", new Object[0]);
            super.onClosed(camera);
            Camera2VideoCapturer.this.surfaceTexture.setOnFrameAvailableListener((SurfaceTexture.OnFrameAvailableListener) null);
            CameraState unused = Camera2VideoCapturer.this.cameraState = CameraState.CLOSED;
            CameraDevice unused2 = Camera2VideoCapturer.this.camera = null;
            Camera2VideoCapturer.this.signalCamStateChange();
        }
    };
    /* access modifiers changed from: private */
    public CameraState cameraState;
    /* access modifiers changed from: private */
    public final CameraCaptureSession.CaptureCallback captureNotification = new CameraCaptureSession.CaptureCallback() {
        public void onCaptureStarted(CameraCaptureSession session, CaptureRequest request, long timestamp, long frameNumber) {
            super.onCaptureStarted(session, request, timestamp, frameNumber);
        }
    };
    /* access modifiers changed from: private */
    public CameraCaptureSession captureSession;
    private final CameraCaptureSession.StateCallback captureSessionObserver = new CameraCaptureSession.StateCallback() {
        public void onConfigured(@NonNull CameraCaptureSession session) {
            CaptureRequest.Builder captureRequestBuilder;
            try {
                if (Camera2VideoCapturer.this.isFrontCamera()) {
                    captureRequestBuilder = Camera2VideoCapturer.this.camera.createCaptureRequest(1);
                    captureRequestBuilder.addTarget(Camera2VideoCapturer.this.surface);
                    captureRequestBuilder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, Camera2VideoCapturer.this.actualFps);
                    captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, 2);
                    captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 4);
                    captureRequestBuilder.set(CaptureRequest.CONTROL_SCENE_MODE, 1);
                } else {
                    captureRequestBuilder = Camera2VideoCapturer.this.camera.createCaptureRequest(3);
                    captureRequestBuilder.addTarget(Camera2VideoCapturer.this.surface);
                    captureRequestBuilder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, Camera2VideoCapturer.this.actualFps);
                }
                CameraState unused = Camera2VideoCapturer.this.cameraState = CameraState.CAPTURE;
                CameraCaptureSession unused2 = Camera2VideoCapturer.this.captureSession = session;
                Camera2VideoCapturer.this.captureSession.setRepeatingRequest(captureRequestBuilder.build(), Camera2VideoCapturer.this.captureNotification, Camera2VideoCapturer.this.camHandler);
                Camera2VideoCapturer.this.signalCamStateChange();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        public void onConfigureFailed(@NonNull CameraCaptureSession session) {
            CameraState unused = Camera2VideoCapturer.this.cameraState = CameraState.ERROR;
            Camera2VideoCapturer.this.postAsyncException(new RuntimeException("Camera session configuration failed"));
            Camera2VideoCapturer.this.signalCamStateChange();
        }

        public void onClosed(@NonNull CameraCaptureSession session) {
            Camera2VideoCapturer.this.camera.close();
        }
    };
    /* access modifiers changed from: private */
    public CameraInfoCache characteristics;
    private Condition condition;
    private Context context;
    private Size desiredDimensions;
    private int desiredFps;
    private DisplayOrientationCache displayOrientationCache;
    private boolean isPaused;
    private ReentrantLock reentrantLock;
    private List<RuntimeException> runtimeExceptionList;
    /* access modifiers changed from: private */
    public Surface surface;
    /* access modifiers changed from: private */
    public SurfaceTexture surfaceTexture;

    private enum CameraState {
        CLOSED,
        SETUP,
        OPEN,
        CAPTURE,
        ERROR
    }

    Camera2VideoCapturer(Context context2, DefaultVideoCapturer.FrameRate fps, DefaultVideoCapturer.Resolution resolution, AbstractCapturer.CapturerError errorCb) {
        this.context = context2;
        this.cameraManager = (CameraManager) context2.getSystemService("camera");
        this.camera = null;
        this.cameraState = CameraState.CLOSED;
        this.reentrantLock = new ReentrantLock();
        this.condition = this.reentrantLock.newCondition();
        this.desiredDimensions = resolutionTable.get(resolution.ordinal());
        this.desiredFps = frameRateTable.get(fps.ordinal());
        this.runtimeExceptionList = new ArrayList();
        this.isPaused = false;
        try {
            this.cameraIndex = findCameraIndex(selectCamera(0));
            registerCapturerError(errorCb);
        } catch (CameraAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public synchronized boolean initialize() throws Exception {
        log.d("init entered", new Object[0]);
        super.initialize();
        this.characteristics = null;
        startCamThread();
        this.displayOrientationCache = new DisplayOrientationCache((WindowManager) this.context.getSystemService("window"), this.camHandler);
        initCamera();
        log.d("init Exit", new Object[0]);
        return CameraState.OPEN == this.cameraState;
    }

    public synchronized boolean start() throws Exception {
        boolean z = true;
        synchronized (this) {
            log.d("startCapture entered", new Object[0]);
            if (CameraState.OPEN == this.cameraState) {
                this.camera.createCaptureSession(Arrays.asList(new Surface[]{this.surface}), this.captureSessionObserver, this.camHandler);
                waitForCamStateChange(CameraState.OPEN);
            }
            if (CameraState.CAPTURE != this.cameraState) {
                z = false;
            }
        }
        return z;
    }

    public synchronized boolean stop() throws Exception {
        boolean z = false;
        synchronized (this) {
            log.d("stopCapture entered", new Object[0]);
            if (CameraState.CLOSED != this.cameraState) {
                CameraState oldState = this.cameraState;
                this.captureSession.close();
                waitForCamStateChange(oldState);
                this.characteristics = null;
            }
            log.d("stopCapture exited", new Object[0]);
            if (CameraState.CLOSED == this.cameraState) {
                z = true;
            }
        }
        return z;
    }

    public synchronized boolean destroy() throws Exception {
        this.displayOrientationCache.shutdown();
        stopCamThread();
        super.destroy();
        this.surface = null;
        this.surfaceTexture = null;
        return CameraState.CLOSED == this.cameraState;
    }

    public synchronized void pause() throws Exception {
        log.d("pause", new Object[0]);
        switch (this.cameraState) {
            case CAPTURE:
                stop();
                this.isPaused = true;
                break;
        }
    }

    public synchronized void resume() throws Exception {
        log.d("resume", new Object[0]);
        if (this.isPaused) {
            initCamera();
            start();
            this.isPaused = false;
        }
    }

    public boolean isInitialized() throws Exception {
        return CameraState.OPEN == this.cameraState;
    }

    public boolean isStarted() throws Exception {
        return CameraState.CAPTURE == this.cameraState;
    }

    public AbstractCapturer.CaptureSettings getCaptureSettings() {
        return new AbstractCapturer.CaptureSettings(this.actualDimentions.getWidth(), this.actualDimentions.getHeight(), this.actualFps.getUpper().intValue());
    }

    public synchronized void cycleCamera() throws Exception {
        swapCamera((this.cameraIndex + 1) % this.cameraManager.getCameraIdList().length);
    }

    private int findCameraIndex(String camId) throws CameraAccessException {
        String[] idList = this.cameraManager.getCameraIdList();
        for (int ndx = 0; ndx < idList.length; ndx++) {
            if (idList[ndx].equals(camId)) {
                return ndx;
            }
        }
        return -1;
    }

    private String selectCamera(int lenseDirection) throws CameraAccessException {
        for (String id : this.cameraManager.getCameraIdList()) {
            if (lenseDirection == ((Integer) this.cameraManager.getCameraCharacteristics(id).get(CameraCharacteristics.LENS_FACING)).intValue()) {
                return id;
            }
        }
        return null;
    }

    private void swapCamera(int cameraId) throws Exception {
        CameraState oldState = this.cameraState;
        switch (oldState) {
            case CAPTURE:
                stop();
                break;
        }
        this.cameraIndex = cameraId;
        switch (oldState) {
            case CAPTURE:
                initCamera();
                start();
                return;
            default:
                return;
        }
    }

    private void startCamThread() {
        this.camThread = new HandlerThread("Camera-Thread");
        this.camThread.start();
        this.camHandler = new Handler(this.camThread.getLooper());
    }

    private void stopCamThread() {
        try {
            this.camThread.quitSafely();
            this.camThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NullPointerException e2) {
        } finally {
            this.camThread = null;
            this.camHandler = null;
        }
    }

    private void initCamera() throws Exception {
        this.cameraState = CameraState.SETUP;
        String camId = this.cameraManager.getCameraIdList()[this.cameraIndex];
        this.actualDimentions = selectPreferedSize(camId, this.desiredDimensions.getWidth(), this.desiredDimensions.getHeight(), 35);
        this.actualFps = selectCameraFpsRange(camId, this.desiredFps);
        this.characteristics = new CameraInfoCache(this.cameraManager.getCameraCharacteristics(camId));
        this.cameraManager.openCamera(camId, this.cameraObserver, this.camHandler);
        waitForCamStateChange(CameraState.SETUP);
    }

    /* access modifiers changed from: private */
    public void waitForCamStateChange(CameraState oldState) throws RuntimeException {
        this.reentrantLock.lock();
        while (oldState == this.cameraState) {
            try {
                this.condition.await();
            } catch (InterruptedException e) {
                waitForCamStateChange(oldState);
            }
        }
        this.reentrantLock.unlock();
        Iterator<RuntimeException> it = this.runtimeExceptionList.iterator();
        if (it.hasNext()) {
            throw it.next();
        }
        this.runtimeExceptionList.clear();
    }

    /* access modifiers changed from: private */
    public void signalCamStateChange() {
        this.reentrantLock.lock();
        this.condition.signalAll();
        this.reentrantLock.unlock();
    }

    /* access modifiers changed from: private */
    public void postAsyncException(RuntimeException exp) {
        this.runtimeExceptionList.add(exp);
    }

    /* access modifiers changed from: private */
    public boolean isFrontCamera() {
        return this.characteristics != null && this.characteristics.isFrontFacing();
    }

    private Size selectPreferedSize(String camId, final int width, final int height, int format) throws CameraAccessException {
        StreamConfigurationMap dimMap = (StreamConfigurationMap) this.cameraManager.getCameraCharacteristics(camId).get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        List<Size> sizeLst = new ArrayList<>();
        int[] outputFormats = dimMap.getOutputFormats();
        Collections.addAll(sizeLst, dimMap.getOutputSizes(35));
        return (Size) Collections.min(sizeLst, new Comparator<Size>() {
            public int compare(Size lhs, Size rhs) {
                return (Math.abs(lhs.getWidth() - width) + Math.abs(lhs.getHeight() - height)) - (Math.abs(rhs.getWidth() - width) + Math.abs(rhs.getHeight() - height));
            }
        });
    }

    private Range<Integer> selectCameraFpsRange(String camId, final int fps) throws CameraAccessException {
        for (String id : this.cameraManager.getCameraIdList()) {
            if (id.equals(camId)) {
                CameraCharacteristics info = this.cameraManager.getCameraCharacteristics(id);
                List<Range<Integer>> fpsLst = new ArrayList<>();
                Collections.addAll(fpsLst, (Object[]) info.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES));
                return (Range) Collections.min(fpsLst, new Comparator<Range<Integer>>() {
                    public int compare(Range<Integer> lhs, Range<Integer> rhs) {
                        return calcError(lhs) - calcError(rhs);
                    }

                    private int calcError(Range<Integer> val) {
                        return Math.abs(val.getUpper().intValue() - fps) + val.getLower().intValue();
                    }
                });
            }
        }
        return null;
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture2) {
        onCaptureFrame(surfaceTexture2, this.actualDimentions.getWidth(), this.actualDimentions.getHeight(), this.displayOrientationCache.getRotation(), this.characteristics.isFrontFacing(), (byte[]) null);
    }

    private static class CameraInfoCache {
        private boolean frontFacing = false;
        private CameraCharacteristics info;
        private int sensorOrientation = 0;

        CameraInfoCache(CameraCharacteristics info2) throws CameraAccessException {
            boolean z;
            this.info = info2;
            if (((Integer) info2.get(CameraCharacteristics.LENS_FACING)).intValue() == 0) {
                z = true;
            } else {
                z = false;
            }
            this.frontFacing = z;
            this.sensorOrientation = ((Integer) info2.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        }

        /* access modifiers changed from: package-private */
        public <T> T get(CameraCharacteristics.Key<T> key) {
            return this.info.get(key);
        }

        /* access modifiers changed from: package-private */
        public boolean isFrontFacing() {
            return this.frontFacing;
        }

        /* access modifiers changed from: package-private */
        public int sensorOrientation() {
            return this.sensorOrientation;
        }
    }

    private class DisplayOrientationCache implements Runnable {
        private static final int POLL_DELAY_MS = 750;
        private Handler handler;
        private AtomicInteger rotation = new AtomicInteger(0);
        private WindowManager winMgr;

        DisplayOrientationCache(WindowManager wm, Handler hndlr) {
            this.winMgr = wm;
            this.handler = hndlr;
            this.handler.postDelayed(this, 750);
        }

        /* access modifiers changed from: package-private */
        public int calculateCamRotation() {
            if (Camera2VideoCapturer.this.characteristics == null) {
                return 0;
            }
            int displayOrientation = Camera2VideoCapturer.rotationTable.get(this.winMgr.getDefaultDisplay().getRotation());
            int cameraOrientation = Camera2VideoCapturer.this.characteristics.sensorOrientation();
            if (!Camera2VideoCapturer.this.characteristics.isFrontFacing()) {
                return ((cameraOrientation - displayOrientation) + 360) % 360;
            }
            return (cameraOrientation + displayOrientation) % 360;
        }

        public int getRotation() {
            return this.rotation.get();
        }

        public void run() {
            this.rotation.set(calculateCamRotation());
            this.handler.postDelayed(this, 750);
        }

        public void shutdown() {
            this.handler.removeCallbacks(this);
        }
    }
}
