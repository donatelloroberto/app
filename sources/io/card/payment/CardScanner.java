package io.card.payment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

class CardScanner implements Camera.AutoFocusCallback, Camera.PreviewCallback, SurfaceHolder.Callback {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final String TAG = CardScanner.class.getSimpleName();
    private static boolean manualFallbackForError;
    private static boolean processingInProgress = false;
    private long captureStart;
    private Bitmap detectedBitmap;
    private boolean isSurfaceValid;
    private long mAutoFocusCompletedAt;
    private long mAutoFocusStartedAt;
    private Camera mCamera;
    private boolean mFirstPreviewFrame = true;
    private int mFrameOrientation = 1;
    private byte[] mPreviewBuffer;
    final int mPreviewHeight = 480;
    final int mPreviewWidth = 640;
    protected WeakReference<CardIOActivity> mScanActivityRef;
    private boolean mScanExpiry;
    private boolean mSuppressScan = false;
    private int mUnblurDigits = -1;
    private int numAutoRefocus;
    private int numFramesSkipped;
    private int numManualRefocus;
    private int numManualTorchChange;
    protected boolean useCamera = true;

    private native void nCleanup();

    private native void nGetGuideFrame(int i, int i2, int i3, Rect rect);

    private native int nGetNumFramesScanned();

    private native void nResetAnalytics();

    private native void nScanFrame(byte[] bArr, int i, int i2, int i3, DetectionInfo detectionInfo, Bitmap bitmap, boolean z);

    private native void nSetup(boolean z, float f);

    private native void nSetup(boolean z, float f, int i);

    public static native boolean nUseNeon();

    public static native boolean nUseTegra();

    public static native boolean nUseX86();

    static {
        boolean z;
        if (!CardScanner.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
        Log.i("card.io", "card.io 5.5.1 03/17/2017 14:23:12 -0400");
        try {
            loadLibrary("cardioDecider");
            Log.d("card.io", "Loaded card.io decider library.");
            Log.d("card.io", "    nUseNeon(): " + nUseNeon());
            Log.d("card.io", "    nUseTegra():" + nUseTegra());
            Log.d("card.io", "    nUseX86():  " + nUseX86());
            if (usesSupportedProcessorArch()) {
                loadLibrary("opencv_core");
                Log.d("card.io", "Loaded opencv core library");
                loadLibrary("opencv_imgproc");
                Log.d("card.io", "Loaded opencv imgproc library");
            }
            if (nUseNeon()) {
                loadLibrary("cardioRecognizer");
                Log.i("card.io", "Loaded card.io NEON library");
            } else if (nUseX86()) {
                loadLibrary("cardioRecognizer");
                Log.i("card.io", "Loaded card.io x86 library");
            } else if (nUseTegra()) {
                loadLibrary("cardioRecognizer_tegra2");
                Log.i("card.io", "Loaded card.io Tegra2 library");
            } else {
                Log.w("card.io", "unsupported processor - card.io scanning requires ARMv7 or x86 architecture");
                manualFallbackForError = true;
            }
        } catch (UnsatisfiedLinkError e) {
            Log.e("card.io", "Failed to load native library: " + e.getMessage());
            manualFallbackForError = true;
        }
    }

    private static void loadLibrary(String libraryName) throws UnsatisfiedLinkError {
        try {
            System.loadLibrary(libraryName);
        } catch (UnsatisfiedLinkError e) {
            String altLibsPath = CardIONativeLibsConfig.getAlternativeLibsPath();
            if (altLibsPath == null || altLibsPath.length() == 0) {
                throw e;
            }
            if (!File.separator.equals(Character.valueOf(altLibsPath.charAt(altLibsPath.length() - 1)))) {
                altLibsPath = altLibsPath + File.separator;
            }
            String fullPath = altLibsPath + Build.CPU_ABI + File.separator + System.mapLibraryName(libraryName);
            Log.d("card.io", "loadLibrary failed for library " + libraryName + ". Trying " + fullPath);
            System.load(fullPath);
        }
    }

    private static boolean usesSupportedProcessorArch() {
        return nUseNeon() || nUseTegra() || nUseX86();
    }

    static boolean processorSupported() {
        return !manualFallbackForError && usesSupportedProcessorArch();
    }

    CardScanner(CardIOActivity scanActivity, int currentFrameOrientation) {
        boolean z = true;
        Intent scanIntent = scanActivity.getIntent();
        if (scanIntent != null) {
            this.mSuppressScan = scanIntent.getBooleanExtra(CardIOActivity.EXTRA_SUPPRESS_SCAN, false);
            this.mScanExpiry = (!scanIntent.getBooleanExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false) || !scanIntent.getBooleanExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, true)) ? false : z;
            this.mUnblurDigits = scanIntent.getIntExtra(CardIOActivity.EXTRA_UNBLUR_DIGITS, -1);
        }
        this.mScanActivityRef = new WeakReference<>(scanActivity);
        this.mFrameOrientation = currentFrameOrientation;
        nSetup(this.mSuppressScan, 6.0f, this.mUnblurDigits);
    }

    private Camera connectToCamera(int checkInterval, int maxTimeout) {
        long start = System.currentTimeMillis();
        if (this.useCamera) {
            do {
                try {
                    return Camera.open();
                } catch (RuntimeException e) {
                    try {
                        Log.w("card.io", "Wasn't able to connect to camera service. Waiting and trying again...");
                        Thread.sleep((long) checkInterval);
                    } catch (InterruptedException e1) {
                        Log.e("card.io", "Interrupted while waiting for camera", e1);
                    }
                } catch (Exception e2) {
                    Log.e("card.io", "Unexpected exception. Please report it as a GitHub issue", e2);
                    maxTimeout = 0;
                }
            } while (System.currentTimeMillis() - start >= ((long) maxTimeout));
            Log.w(TAG, "camera connect timeout");
            return null;
        }
        Log.w(TAG, "camera connect timeout");
        return null;
        if (System.currentTimeMillis() - start >= ((long) maxTimeout)) {
            Log.w(TAG, "camera connect timeout");
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void prepareScanner() {
        this.mFirstPreviewFrame = true;
        this.mAutoFocusStartedAt = 0;
        this.mAutoFocusCompletedAt = 0;
        this.numManualRefocus = 0;
        this.numAutoRefocus = 0;
        this.numManualTorchChange = 0;
        this.numFramesSkipped = 0;
        if (this.useCamera && this.mCamera == null) {
            this.mCamera = connectToCamera(50, 5000);
            if (this.mCamera == null) {
                Log.e("card.io", "prepare scanner couldn't connect to camera!");
                return;
            }
            setCameraDisplayOrientation(this.mCamera);
            Camera.Parameters parameters = this.mCamera.getParameters();
            List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
            if (supportedPreviewSizes != null) {
                Camera.Size previewSize = null;
                Iterator<Camera.Size> it = supportedPreviewSizes.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Camera.Size s = it.next();
                    if (s.width == 640 && s.height == 480) {
                        previewSize = s;
                        break;
                    }
                }
                if (previewSize == null) {
                    Log.w("card.io", "Didn't find a supported 640x480 resolution, so forcing");
                    Camera.Size previewSize2 = supportedPreviewSizes.get(0);
                    previewSize2.width = 640;
                    previewSize2.height = 480;
                }
            }
            parameters.setPreviewSize(640, 480);
            this.mCamera.setParameters(parameters);
        } else if (!this.useCamera) {
            Log.w(TAG, "useCamera is false!");
        } else if (this.mCamera != null) {
            Log.v(TAG, "we already have a camera instance: " + this.mCamera);
        }
        if (this.detectedBitmap == null) {
            this.detectedBitmap = Bitmap.createBitmap(428, 270, Bitmap.Config.ARGB_8888);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean resumeScanning(SurfaceHolder holder) {
        if (this.mCamera == null) {
            prepareScanner();
        }
        if (this.useCamera && this.mCamera == null) {
            Log.i(TAG, "null camera. failure");
            return false;
        } else if ($assertionsDisabled || holder != null) {
            if (this.useCamera && this.mPreviewBuffer == null) {
                this.mPreviewBuffer = new byte[(307200 * (ImageFormat.getBitsPerPixel(this.mCamera.getParameters().getPreviewFormat()) / 8) * 3)];
                this.mCamera.addCallbackBuffer(this.mPreviewBuffer);
            }
            holder.addCallback(this);
            holder.setType(3);
            if (this.useCamera) {
                this.mCamera.setPreviewCallbackWithBuffer(this);
            }
            if (this.isSurfaceValid) {
                makePreviewGo(holder);
            }
            setFlashOn(false);
            this.captureStart = System.currentTimeMillis();
            nResetAnalytics();
            return true;
        } else {
            throw new AssertionError();
        }
    }

    public void pauseScanning() {
        setFlashOn(false);
        if (this.mCamera != null) {
            try {
                this.mCamera.stopPreview();
                this.mCamera.setPreviewDisplay((SurfaceHolder) null);
            } catch (IOException e) {
                Log.w("card.io", "can't stop preview display", e);
            }
            this.mCamera.setPreviewCallback((Camera.PreviewCallback) null);
            this.mCamera.release();
            this.mPreviewBuffer = null;
            this.mCamera = null;
        }
    }

    public void endScanning() {
        if (this.mCamera != null) {
            pauseScanning();
        }
        nCleanup();
        this.mPreviewBuffer = null;
    }

    private boolean makePreviewGo(SurfaceHolder holder) {
        if (!$assertionsDisabled && holder == null) {
            throw new AssertionError();
        } else if ($assertionsDisabled || holder.getSurface() != null) {
            this.mFirstPreviewFrame = true;
            if (this.useCamera) {
                try {
                    this.mCamera.setPreviewDisplay(holder);
                    try {
                        this.mCamera.startPreview();
                        this.mCamera.autoFocus(this);
                    } catch (RuntimeException e) {
                        Log.e("card.io", "startPreview failed on camera. Error: ", e);
                        return false;
                    }
                } catch (IOException e2) {
                    Log.e("card.io", "can't set preview display", e2);
                    return false;
                }
            }
            return true;
        } else {
            throw new AssertionError();
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        if (this.mCamera != null || !this.useCamera) {
            this.isSurfaceValid = true;
            makePreviewGo(holder);
            return;
        }
        Log.wtf("card.io", "CardScanner.surfaceCreated() - camera is null!");
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        boolean z;
        String str = TAG;
        Object[] objArr = new Object[4];
        if (holder != null) {
            z = true;
        } else {
            z = false;
        }
        objArr[0] = Boolean.valueOf(z);
        objArr[1] = Integer.valueOf(format);
        objArr[2] = Integer.valueOf(width);
        objArr[3] = Integer.valueOf(height);
        Log.d(str, String.format("Preview.surfaceChanged(holder?:%b, f:%d, w:%d, h:%d )", objArr));
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if (this.mCamera != null) {
            try {
                this.mCamera.stopPreview();
            } catch (Exception e) {
                Log.e("card.io", "error stopping camera", e);
            }
        }
        this.isSurfaceValid = false;
    }

    public void onPreviewFrame(byte[] data, Camera camera) {
        boolean sufficientFocus = true;
        if (data == null) {
            Log.w(TAG, "frame is null! skipping");
        } else if (processingInProgress) {
            Log.e(TAG, "processing in progress.... dropping frame");
            this.numFramesSkipped++;
            if (camera != null) {
                camera.addCallbackBuffer(data);
            }
        } else {
            processingInProgress = true;
            if (this.mFirstPreviewFrame) {
                this.mFirstPreviewFrame = false;
                this.mFrameOrientation = 1;
                ((CardIOActivity) this.mScanActivityRef.get()).onFirstFrame(1);
            }
            DetectionInfo dInfo = new DetectionInfo();
            nScanFrame(data, 640, 480, this.mFrameOrientation, dInfo, this.detectedBitmap, this.mScanExpiry);
            if (dInfo.focusScore < 6.0f) {
                sufficientFocus = false;
            }
            if (!sufficientFocus) {
                triggerAutoFocus(false);
            } else if (dInfo.predicted() || (this.mSuppressScan && dInfo.detected())) {
                ((CardIOActivity) this.mScanActivityRef.get()).onCardDetected(this.detectedBitmap, dInfo);
            }
            if (camera != null) {
                camera.addCallbackBuffer(data);
            }
            processingInProgress = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void onEdgeUpdate(DetectionInfo dInfo) {
        ((CardIOActivity) this.mScanActivityRef.get()).onEdgeUpdate(dInfo);
    }

    /* access modifiers changed from: package-private */
    public Rect getGuideFrame(int orientation, int previewWidth, int previewHeight) {
        if (!processorSupported()) {
            return null;
        }
        Rect r = new Rect();
        nGetGuideFrame(orientation, previewWidth, previewHeight, r);
        return r;
    }

    /* access modifiers changed from: package-private */
    public Rect getGuideFrame(int width, int height) {
        return getGuideFrame(this.mFrameOrientation, width, height);
    }

    /* access modifiers changed from: package-private */
    public void setDeviceOrientation(int orientation) {
        this.mFrameOrientation = orientation;
    }

    public void onAutoFocus(boolean success, Camera camera) {
        this.mAutoFocusCompletedAt = System.currentTimeMillis();
    }

    /* access modifiers changed from: package-private */
    public boolean isAutoFocusing() {
        return this.mAutoFocusCompletedAt < this.mAutoFocusStartedAt;
    }

    /* access modifiers changed from: package-private */
    public void triggerAutoFocus(boolean isManual) {
        if (this.useCamera && !isAutoFocusing()) {
            try {
                this.mAutoFocusStartedAt = System.currentTimeMillis();
                this.mCamera.autoFocus(this);
                if (isManual) {
                    this.numManualRefocus++;
                } else {
                    this.numAutoRefocus++;
                }
            } catch (RuntimeException e) {
                Log.w(TAG, "could not trigger auto focus: " + e);
            }
        }
    }

    public boolean isFlashOn() {
        if (!this.useCamera) {
            return false;
        }
        return this.mCamera.getParameters().getFlashMode().equals("torch");
    }

    public boolean setFlashOn(boolean b) {
        if (this.mCamera != null) {
            try {
                Camera.Parameters params = this.mCamera.getParameters();
                params.setFlashMode(b ? "torch" : "off");
                this.mCamera.setParameters(params);
                this.numManualTorchChange++;
                return true;
            } catch (RuntimeException e) {
                Log.w(TAG, "Could not set flash mode: " + e);
            }
        }
        return false;
    }

    private void setCameraDisplayOrientation(Camera mCamera2) {
        int result;
        if (Build.VERSION.SDK_INT >= 21) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(0, info);
            result = ((info.orientation - getRotationalOffset()) + 360) % 360;
        } else {
            result = 90;
        }
        mCamera2.setDisplayOrientation(result);
    }

    /* access modifiers changed from: package-private */
    public int getRotationalOffset() {
        int naturalOrientation = ((WindowManager) ((CardIOActivity) this.mScanActivityRef.get()).getSystemService("window")).getDefaultDisplay().getRotation();
        if (naturalOrientation == 0) {
            return 0;
        }
        if (naturalOrientation == 1) {
            return 90;
        }
        if (naturalOrientation == 2) {
            return 180;
        }
        if (naturalOrientation == 3) {
            return 270;
        }
        return 0;
    }
}
