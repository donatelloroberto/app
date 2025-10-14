package com.opentok.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.view.Display;
import android.view.WindowManager;
import com.opentok.android.BaseVideoCapturer;
import com.opentok.android.OtLog;
import com.opentok.android.Publisher;
import com.opentok.android.VideoUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.ReentrantLock;

@SuppressLint({"WrongConstant"})
class DefaultVideoCapturer extends BaseVideoCapturer implements Camera.PreviewCallback, BaseVideoCapturer.CaptureSwitch {
    private static final int PIXEL_FORMAT = 17;
    private static final OtLog.LogToken log = new OtLog.LogToken("[camera]", true);
    private boolean blackFrames;
    private Camera camera;
    private int cameraIndex;
    private int[] captureFpsRange;
    private int captureHeight;
    private int captureWidth;
    private Camera.CameraInfo currentDeviceInfo;
    private Display currentDisplay;
    private int expectedFrameSize;
    int fps;
    int[] frame;
    Handler handler;
    int height;
    private boolean isCapturePaused;
    /* access modifiers changed from: private */
    public boolean isCaptureRunning;
    private boolean isCaptureStarted;
    Runnable newFrame;
    private final int numCaptureBuffers;
    PixelFormat pixelFormat;
    private Publisher.CameraCaptureFrameRate preferredFramerate;
    private Publisher.CameraCaptureResolution preferredResolution;
    public ReentrantLock previewBufferLock;
    private SurfaceTexture surfaceTexture;
    int width;

    public DefaultVideoCapturer(Context context, Publisher.CameraCaptureResolution resolution, Publisher.CameraCaptureFrameRate fps2) {
        this.cameraIndex = 0;
        this.currentDeviceInfo = null;
        this.previewBufferLock = new ReentrantLock();
        this.pixelFormat = new PixelFormat();
        this.isCaptureStarted = false;
        this.isCaptureRunning = false;
        this.numCaptureBuffers = 3;
        this.expectedFrameSize = 0;
        this.captureWidth = -1;
        this.captureHeight = -1;
        this.blackFrames = false;
        this.isCapturePaused = false;
        this.preferredResolution = Publisher.CameraCaptureResolution.defaultResolution();
        this.preferredFramerate = Publisher.CameraCaptureFrameRate.defaultFrameRate();
        this.fps = 1;
        this.width = 0;
        this.height = 0;
        this.handler = new Handler();
        this.newFrame = new Runnable() {
            public void run() {
                if (DefaultVideoCapturer.this.isCaptureRunning) {
                    if (DefaultVideoCapturer.this.frame == null) {
                        new VideoUtils.Size();
                        VideoUtils.Size resolution = DefaultVideoCapturer.this.getPreferredResolution();
                        DefaultVideoCapturer.this.fps = DefaultVideoCapturer.this.getPreferredFramerate();
                        DefaultVideoCapturer.this.width = resolution.width;
                        DefaultVideoCapturer.this.height = resolution.height;
                        DefaultVideoCapturer.this.frame = new int[(DefaultVideoCapturer.this.width * DefaultVideoCapturer.this.height)];
                    }
                    DefaultVideoCapturer.this.provideIntArrayFrame(DefaultVideoCapturer.this.frame, 2, DefaultVideoCapturer.this.width, DefaultVideoCapturer.this.height, 0, false);
                    DefaultVideoCapturer.this.handler.postDelayed(DefaultVideoCapturer.this.newFrame, (long) (1000 / DefaultVideoCapturer.this.fps));
                }
            }
        };
        this.cameraIndex = getCameraIndexUsingFront(true);
        this.currentDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        this.preferredFramerate = fps2;
        this.preferredResolution = resolution;
    }

    public synchronized void init() {
        log.w("init() enetered", new Object[0]);
        try {
            this.camera = Camera.open(this.cameraIndex);
        } catch (RuntimeException exp) {
            log.e(exp, "The camera is in use by another app", new Object[0]);
            error(exp);
        }
        this.currentDeviceInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(this.cameraIndex, this.currentDeviceInfo);
        log.w("init() exit", new Object[0]);
        return;
    }

    public synchronized int startCapture() {
        int i = -1;
        synchronized (this) {
            log.w("started() entered", new Object[0]);
            if (!this.isCaptureStarted) {
                if (this.camera != null) {
                    VideoUtils.Size resolution = getPreferredResolution();
                    if (configureCaptureSize(resolution.width, resolution.height) != null) {
                        Camera.Parameters parameters = this.camera.getParameters();
                        parameters.setPreviewSize(this.captureWidth, this.captureHeight);
                        parameters.setPreviewFormat(17);
                        parameters.setPreviewFpsRange(this.captureFpsRange[0], this.captureFpsRange[1]);
                        try {
                            this.camera.setParameters(parameters);
                            PixelFormat.getPixelFormatInfo(17, this.pixelFormat);
                            int bufSize = ((this.captureWidth * this.captureHeight) * this.pixelFormat.bitsPerPixel) / 8;
                            for (int i2 = 0; i2 < 3; i2++) {
                                this.camera.addCallbackBuffer(new byte[bufSize]);
                            }
                            try {
                                this.surfaceTexture = new SurfaceTexture(42);
                                this.camera.setPreviewTexture(this.surfaceTexture);
                                this.camera.setPreviewCallbackWithBuffer(this);
                                this.camera.startPreview();
                                this.previewBufferLock.lock();
                                this.expectedFrameSize = bufSize;
                                this.previewBufferLock.unlock();
                            } catch (Exception exp) {
                                error(exp);
                                exp.printStackTrace();
                            }
                        } catch (RuntimeException exp2) {
                            log.e(exp2, "Camera.setParameters(parameters) - failed", new Object[0]);
                            error(exp2);
                        }
                    }
                } else {
                    this.blackFrames = true;
                    this.handler.postDelayed(this.newFrame, (long) (1000 / this.fps));
                }
                this.isCaptureRunning = true;
                this.isCaptureStarted = true;
                log.w("started() exit", new Object[0]);
                i = 0;
            }
        }
        return i;
    }

    public synchronized int stopCapture() {
        int i = 0;
        synchronized (this) {
            if (this.camera != null && this.isCaptureStarted) {
                this.previewBufferLock.lock();
                try {
                    if (this.isCaptureRunning) {
                        this.isCaptureRunning = false;
                        this.camera.stopPreview();
                        this.camera.setPreviewCallbackWithBuffer((Camera.PreviewCallback) null);
                        this.camera.release();
                        this.camera = null;
                        log.d("Camera capture is stopped", new Object[0]);
                    }
                    this.previewBufferLock.unlock();
                } catch (RuntimeException exp) {
                    log.e(exp, "Camera.stopPreview() - failed ", new Object[0]);
                    error(exp);
                    i = -1;
                }
            }
            this.isCaptureStarted = false;
            if (this.blackFrames) {
                this.handler.removeCallbacks(this.newFrame);
            }
        }
        return i;
    }

    public void destroy() {
    }

    public boolean isCaptureStarted() {
        return this.isCaptureStarted;
    }

    public BaseVideoCapturer.CaptureSettings getCaptureSettings() {
        BaseVideoCapturer.CaptureSettings settings = new BaseVideoCapturer.CaptureSettings();
        VideoUtils.Size resolution = getPreferredResolution();
        int framerate = getPreferredFramerate();
        if (this.camera == null || configureCaptureSize(resolution.width, resolution.height) == null) {
            settings.fps = framerate;
            settings.width = resolution.width;
            settings.height = resolution.height;
            settings.format = 2;
        } else {
            settings.fps = framerate;
            settings.width = resolution.width;
            settings.height = resolution.height;
            settings.format = 1;
            settings.expectedDelay = 0;
        }
        return settings;
    }

    public synchronized void onPause() {
        if (this.isCaptureStarted) {
            this.isCapturePaused = true;
            stopCapture();
        }
    }

    public void onResume() {
        if (this.isCapturePaused) {
            init();
            startCapture();
            this.isCapturePaused = false;
        }
    }

    private int getNaturalCameraOrientation() {
        if (this.currentDeviceInfo != null) {
            return this.currentDeviceInfo.orientation;
        }
        return 0;
    }

    public boolean isFrontCamera() {
        if (this.currentDeviceInfo == null) {
            return false;
        }
        if (this.currentDeviceInfo.facing == 1) {
            return true;
        }
        return false;
    }

    public int getCameraIndex() {
        return this.cameraIndex;
    }

    public synchronized void cycleCamera() {
        swapCamera((getCameraIndex() + 1) % Camera.getNumberOfCameras());
    }

    @SuppressLint({"DefaultLocale"})
    public synchronized void swapCamera(int index) {
        boolean wasStarted = this.isCaptureStarted;
        if (this.camera != null) {
            stopCapture();
        }
        this.cameraIndex = index;
        if (wasStarted) {
            if (-1 != Build.MODEL.toLowerCase().indexOf("samsung")) {
                forceCameraRelease(index);
            }
            this.camera = Camera.open(index);
            this.currentDeviceInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(index, this.currentDeviceInfo);
            startCapture();
        }
    }

    private int compensateCameraRotation(int uiRotation) {
        int currentDeviceOrientation = 0;
        switch (uiRotation) {
            case 0:
                currentDeviceOrientation = 0;
                break;
            case 1:
                currentDeviceOrientation = 270;
                break;
            case 2:
                currentDeviceOrientation = 180;
                break;
            case 3:
                currentDeviceOrientation = 90;
                break;
        }
        int cameraOrientation = getNaturalCameraOrientation();
        int cameraRotation = roundRotation(currentDeviceOrientation);
        if (isFrontCamera()) {
            return (((360 - cameraRotation) % 360) + cameraOrientation) % 360;
        }
        return (cameraRotation + cameraOrientation) % 360;
    }

    private static int roundRotation(int rotation) {
        return ((int) (Math.round(((double) rotation) / 90.0d) * 90)) % 360;
    }

    private static int getCameraIndexUsingFront(boolean front) {
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (front && info.facing == 1) {
                return i;
            }
            if (!front && info.facing == 0) {
                return i;
            }
        }
        return 0;
    }

    public void onPreviewFrame(byte[] data, Camera camera2) {
        this.previewBufferLock.lock();
        if (this.isCaptureRunning && data.length == this.expectedFrameSize) {
            byte[] bArr = data;
            provideByteArrayFrame(bArr, 1, this.captureWidth, this.captureHeight, compensateCameraRotation(this.currentDisplay.getRotation()), isFrontCamera());
            camera2.addCallbackBuffer(data);
        }
        this.previewBufferLock.unlock();
    }

    public void setPublisher(Publisher publisher) {
        log.d("setPublisher(" + publisher + ") called", new Object[0]);
        setPublisherKit(publisher);
    }

    private boolean forceCameraRelease(int cameraIndex2) {
        Camera camera2 = null;
        try {
            Camera camera3 = Camera.open(cameraIndex2);
            if (camera3 != null) {
                camera3.release();
            }
            return false;
        } catch (RuntimeException e) {
            if (camera2 == null) {
                return true;
            }
            camera2.release();
            return true;
        } catch (Throwable th) {
            if (camera2 != null) {
                camera2.release();
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public VideoUtils.Size getPreferredResolution() {
        VideoUtils.Size resolution = new VideoUtils.Size();
        switch (this.preferredResolution) {
            case LOW:
                resolution.width = 352;
                resolution.height = 288;
                break;
            case MEDIUM:
                resolution.width = 640;
                resolution.height = 480;
                break;
            case HIGH:
                resolution.width = 1280;
                resolution.height = 720;
                break;
        }
        return resolution;
    }

    /* access modifiers changed from: private */
    public int getPreferredFramerate() {
        switch (this.preferredFramerate) {
            case FPS_30:
                return 30;
            case FPS_15:
                return 15;
            case FPS_7:
                return 7;
            case FPS_1:
                return 1;
            default:
                return 0;
        }
    }

    private VideoUtils.Size configureCaptureSize(int preferredWidth, int preferredHeight) {
        int preferredFramerate2 = getPreferredFramerate();
        try {
            Camera.Parameters parameters = this.camera.getParameters();
            List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
            this.captureFpsRange = findClosestEnclosingFpsRange(preferredFramerate2 * 1000, parameters.getSupportedPreviewFpsRange());
            int maxw = 0;
            int maxh = 0;
            for (int i = 0; i < sizes.size(); i++) {
                Camera.Size size = sizes.get(i);
                if (size.width >= maxw && size.height >= maxh && size.width <= preferredWidth && size.height <= preferredHeight) {
                    maxw = size.width;
                    maxh = size.height;
                }
            }
            if (maxw == 0 || maxh == 0) {
                Camera.Size size2 = sizes.get(0);
                int minw = size2.width;
                int minh = size2.height;
                for (int i2 = 1; i2 < sizes.size(); i2++) {
                    Camera.Size size3 = sizes.get(i2);
                    if (size3.width <= minw && size3.height <= minh) {
                        minw = size3.width;
                        minh = size3.height;
                    }
                }
                maxw = minw;
                maxh = minh;
            }
            this.captureWidth = maxw;
            this.captureHeight = maxh;
            VideoUtils.Size retSize = new VideoUtils.Size(maxw, maxh);
            VideoUtils.Size size4 = retSize;
            return retSize;
        } catch (RuntimeException exp) {
            log.e(exp, "Error configuring capture size", new Object[0]);
            error(exp);
            return null;
        } catch (Throwable th) {
            return null;
        }
    }

    private int[] findClosestEnclosingFpsRange(int preferredFps, List<int[]> supportedFpsRanges) {
        if (supportedFpsRanges == null || supportedFpsRanges.size() == 0) {
            return new int[]{0, 0};
        }
        if (isFrontCamera() && "samsung-sm-g900a".equals(Build.MODEL.toLowerCase(Locale.ROOT)) && 30000 == preferredFps) {
            preferredFps = 24000;
        }
        final int fps2 = preferredFps;
        int[] closestRange = (int[]) Collections.min(supportedFpsRanges, new Comparator<int[]>() {
            private static final int MAX_FPS_DIFF_THRESHOLD = 5000;
            private static final int MAX_FPS_HIGH_DIFF_WEIGHT = 3;
            private static final int MAX_FPS_LOW_DIFF_WEIGHT = 1;
            private static final int MIN_FPS_HIGH_VALUE_WEIGHT = 4;
            private static final int MIN_FPS_LOW_VALUE_WEIGHT = 1;
            private static final int MIN_FPS_THRESHOLD = 8000;

            private int progressivePenalty(int value, int threshold, int lowWeight, int highWeight) {
                return value < threshold ? value * lowWeight : (threshold * lowWeight) + ((value - threshold) * highWeight);
            }

            private int diff(int[] range) {
                return progressivePenalty(range[0], MIN_FPS_THRESHOLD, 1, 4) + progressivePenalty(Math.abs((fps2 * 1000) - range[1]), MAX_FPS_DIFF_THRESHOLD, 1, 3);
            }

            public int compare(int[] lhs, int[] rhs) {
                return diff(lhs) - diff(rhs);
            }
        });
        checkRangeWithWarning(preferredFps, closestRange);
        return closestRange;
    }

    private void checkRangeWithWarning(int preferredFps, int[] range) {
        if (preferredFps < range[0] || preferredFps > range[1]) {
            log.w("Closest fps range found [%d-%d] for desired fps: %d", Integer.valueOf(range[0] / 1000), Integer.valueOf(range[1] / 1000), Integer.valueOf(preferredFps / 1000));
        }
    }
}
