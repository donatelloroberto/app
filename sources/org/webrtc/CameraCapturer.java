package org.webrtc;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Looper;
import java.util.Arrays;
import org.webrtc.CameraSession;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.VideoCapturer;

abstract class CameraCapturer implements CameraVideoCapturer {
    private static final int MAX_OPEN_CAMERA_ATTEMPTS = 3;
    private static final int OPEN_CAMERA_DELAY_MS = 500;
    private static final int OPEN_CAMERA_TIMEOUT = 10000;
    private static final String TAG = "CameraCapturer";
    /* access modifiers changed from: private */
    public Context applicationContext;
    /* access modifiers changed from: private */
    public final CameraEnumerator cameraEnumerator;
    /* access modifiers changed from: private */
    public String cameraName;
    /* access modifiers changed from: private */
    public final CameraSession.Events cameraSessionEventsHandler = new CameraSession.Events() {
        public void onCameraOpening() {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (CameraCapturer.this.currentSession != null) {
                    Logging.w(CameraCapturer.TAG, "onCameraOpening while session was open.");
                } else {
                    CameraCapturer.this.eventsHandler.onCameraOpening(CameraCapturer.this.cameraName);
                }
            }
        }

        public void onCameraError(CameraSession session, String error) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (session != CameraCapturer.this.currentSession) {
                    Logging.w(CameraCapturer.TAG, "onCameraError from another session: " + error);
                    return;
                }
                CameraCapturer.this.eventsHandler.onCameraError(error);
                CameraCapturer.this.stopCapture();
            }
        }

        public void onCameraDisconnected(CameraSession session) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (session != CameraCapturer.this.currentSession) {
                    Logging.w(CameraCapturer.TAG, "onCameraDisconnected from another session.");
                    return;
                }
                CameraCapturer.this.eventsHandler.onCameraDisconnected();
                CameraCapturer.this.stopCapture();
            }
        }

        public void onCameraClosed(CameraSession session) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (session == CameraCapturer.this.currentSession || CameraCapturer.this.currentSession == null) {
                    CameraCapturer.this.eventsHandler.onCameraClosed();
                } else {
                    Logging.d(CameraCapturer.TAG, "onCameraClosed from another session.");
                }
            }
        }

        public void onByteBufferFrameCaptured(CameraSession session, byte[] data, int width, int height, int rotation, long timestamp) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (session != CameraCapturer.this.currentSession) {
                    Logging.w(CameraCapturer.TAG, "onByteBufferFrameCaptured from another session.");
                    return;
                }
                if (!CameraCapturer.this.firstFrameObserved) {
                    CameraCapturer.this.eventsHandler.onFirstFrameAvailable();
                    boolean unused = CameraCapturer.this.firstFrameObserved = true;
                }
                CameraCapturer.this.cameraStatistics.addFrame();
                CameraCapturer.this.capturerObserver.onByteBufferFrameCaptured(data, width, height, rotation, timestamp);
            }
        }

        public void onTextureFrameCaptured(CameraSession session, int width, int height, int oesTextureId, float[] transformMatrix, int rotation, long timestamp) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (session != CameraCapturer.this.currentSession) {
                    Logging.w(CameraCapturer.TAG, "onTextureFrameCaptured from another session.");
                    CameraCapturer.this.surfaceHelper.returnTextureFrame();
                    return;
                }
                if (!CameraCapturer.this.firstFrameObserved) {
                    CameraCapturer.this.eventsHandler.onFirstFrameAvailable();
                    boolean unused = CameraCapturer.this.firstFrameObserved = true;
                }
                CameraCapturer.this.cameraStatistics.addFrame();
                CameraCapturer.this.capturerObserver.onTextureFrameCaptured(width, height, oesTextureId, transformMatrix, rotation, timestamp);
            }
        }
    };
    /* access modifiers changed from: private */
    public CameraVideoCapturer.CameraStatistics cameraStatistics;
    private Handler cameraThreadHandler;
    /* access modifiers changed from: private */
    public VideoCapturer.CapturerObserver capturerObserver;
    /* access modifiers changed from: private */
    public final CameraSession.CreateSessionCallback createSessionCallback = new CameraSession.CreateSessionCallback() {
        public void onDone(CameraSession session) {
            CameraCapturer.this.checkIsOnCameraThread();
            Logging.d(CameraCapturer.TAG, "Create session done. Switch state: " + CameraCapturer.this.switchState + ". MediaRecorder state: " + CameraCapturer.this.mediaRecorderState);
            CameraCapturer.this.uiThreadHandler.removeCallbacks(CameraCapturer.this.openCameraTimeoutRunnable);
            synchronized (CameraCapturer.this.stateLock) {
                CameraCapturer.this.capturerObserver.onCapturerStarted(true);
                boolean unused = CameraCapturer.this.sessionOpening = false;
                CameraSession unused2 = CameraCapturer.this.currentSession = session;
                CameraVideoCapturer.CameraStatistics unused3 = CameraCapturer.this.cameraStatistics = new CameraVideoCapturer.CameraStatistics(CameraCapturer.this.surfaceHelper, CameraCapturer.this.eventsHandler);
                boolean unused4 = CameraCapturer.this.firstFrameObserved = false;
                CameraCapturer.this.stateLock.notifyAll();
                if (CameraCapturer.this.switchState == SwitchState.IN_PROGRESS) {
                    if (CameraCapturer.this.switchEventsHandler != null) {
                        CameraCapturer.this.switchEventsHandler.onCameraSwitchDone(CameraCapturer.this.cameraEnumerator.isFrontFacing(CameraCapturer.this.cameraName));
                        CameraVideoCapturer.CameraSwitchHandler unused5 = CameraCapturer.this.switchEventsHandler = null;
                    }
                    SwitchState unused6 = CameraCapturer.this.switchState = SwitchState.IDLE;
                } else if (CameraCapturer.this.switchState == SwitchState.PENDING) {
                    SwitchState unused7 = CameraCapturer.this.switchState = SwitchState.IDLE;
                    CameraCapturer.this.switchCameraInternal(CameraCapturer.this.switchEventsHandler);
                }
                if (CameraCapturer.this.mediaRecorderState == MediaRecorderState.IDLE_TO_ACTIVE || CameraCapturer.this.mediaRecorderState == MediaRecorderState.ACTIVE_TO_IDLE) {
                    if (CameraCapturer.this.mediaRecorderEventsHandler != null) {
                        CameraCapturer.this.mediaRecorderEventsHandler.onMediaRecorderSuccess();
                        CameraVideoCapturer.MediaRecorderHandler unused8 = CameraCapturer.this.mediaRecorderEventsHandler = null;
                    }
                    if (CameraCapturer.this.mediaRecorderState == MediaRecorderState.IDLE_TO_ACTIVE) {
                        MediaRecorderState unused9 = CameraCapturer.this.mediaRecorderState = MediaRecorderState.ACTIVE;
                    } else {
                        MediaRecorderState unused10 = CameraCapturer.this.mediaRecorderState = MediaRecorderState.IDLE;
                    }
                }
            }
        }

        public void onFailure(CameraSession.FailureType failureType, String error) {
            CameraCapturer.this.checkIsOnCameraThread();
            CameraCapturer.this.uiThreadHandler.removeCallbacks(CameraCapturer.this.openCameraTimeoutRunnable);
            synchronized (CameraCapturer.this.stateLock) {
                CameraCapturer.this.capturerObserver.onCapturerStarted(false);
                CameraCapturer.access$1810(CameraCapturer.this);
                if (CameraCapturer.this.openAttemptsRemaining <= 0) {
                    Logging.w(CameraCapturer.TAG, "Opening camera failed, passing: " + error);
                    boolean unused = CameraCapturer.this.sessionOpening = false;
                    CameraCapturer.this.stateLock.notifyAll();
                    if (CameraCapturer.this.switchState != SwitchState.IDLE) {
                        if (CameraCapturer.this.switchEventsHandler != null) {
                            CameraCapturer.this.switchEventsHandler.onCameraSwitchError(error);
                            CameraVideoCapturer.CameraSwitchHandler unused2 = CameraCapturer.this.switchEventsHandler = null;
                        }
                        SwitchState unused3 = CameraCapturer.this.switchState = SwitchState.IDLE;
                    }
                    if (CameraCapturer.this.mediaRecorderState != MediaRecorderState.IDLE) {
                        if (CameraCapturer.this.mediaRecorderEventsHandler != null) {
                            CameraCapturer.this.mediaRecorderEventsHandler.onMediaRecorderError(error);
                            CameraVideoCapturer.MediaRecorderHandler unused4 = CameraCapturer.this.mediaRecorderEventsHandler = null;
                        }
                        MediaRecorderState unused5 = CameraCapturer.this.mediaRecorderState = MediaRecorderState.IDLE;
                    }
                    if (failureType == CameraSession.FailureType.DISCONNECTED) {
                        CameraCapturer.this.eventsHandler.onCameraDisconnected();
                    } else {
                        CameraCapturer.this.eventsHandler.onCameraError(error);
                    }
                } else {
                    Logging.w(CameraCapturer.TAG, "Opening camera failed, retry: " + error);
                    CameraCapturer.this.createSessionInternal(CameraCapturer.OPEN_CAMERA_DELAY_MS, (MediaRecorder) null);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public CameraSession currentSession;
    /* access modifiers changed from: private */
    public final CameraVideoCapturer.CameraEventsHandler eventsHandler;
    /* access modifiers changed from: private */
    public boolean firstFrameObserved;
    /* access modifiers changed from: private */
    public int framerate;
    /* access modifiers changed from: private */
    public int height;
    /* access modifiers changed from: private */
    public CameraVideoCapturer.MediaRecorderHandler mediaRecorderEventsHandler;
    /* access modifiers changed from: private */
    public MediaRecorderState mediaRecorderState = MediaRecorderState.IDLE;
    /* access modifiers changed from: private */
    public int openAttemptsRemaining;
    /* access modifiers changed from: private */
    public final Runnable openCameraTimeoutRunnable = new Runnable() {
        public void run() {
            CameraCapturer.this.eventsHandler.onCameraError("Camera failed to start within timeout.");
        }
    };
    /* access modifiers changed from: private */
    public boolean sessionOpening;
    /* access modifiers changed from: private */
    public final Object stateLock = new Object();
    /* access modifiers changed from: private */
    public SurfaceTextureHelper surfaceHelper;
    /* access modifiers changed from: private */
    public CameraVideoCapturer.CameraSwitchHandler switchEventsHandler;
    /* access modifiers changed from: private */
    public SwitchState switchState = SwitchState.IDLE;
    /* access modifiers changed from: private */
    public final Handler uiThreadHandler;
    /* access modifiers changed from: private */
    public int width;

    enum MediaRecorderState {
        IDLE,
        IDLE_TO_ACTIVE,
        ACTIVE_TO_IDLE,
        ACTIVE
    }

    enum SwitchState {
        IDLE,
        PENDING,
        IN_PROGRESS
    }

    /* access modifiers changed from: protected */
    public abstract void createCameraSession(CameraSession.CreateSessionCallback createSessionCallback2, CameraSession.Events events, Context context, SurfaceTextureHelper surfaceTextureHelper, MediaRecorder mediaRecorder, String str, int i, int i2, int i3);

    static /* synthetic */ int access$1810(CameraCapturer x0) {
        int i = x0.openAttemptsRemaining;
        x0.openAttemptsRemaining = i - 1;
        return i;
    }

    public CameraCapturer(String cameraName2, CameraVideoCapturer.CameraEventsHandler eventsHandler2, CameraEnumerator cameraEnumerator2) {
        this.eventsHandler = eventsHandler2 == null ? new CameraVideoCapturer.CameraEventsHandler() {
            public void onCameraError(String errorDescription) {
            }

            public void onCameraDisconnected() {
            }

            public void onCameraFreezed(String errorDescription) {
            }

            public void onCameraOpening(String cameraName) {
            }

            public void onFirstFrameAvailable() {
            }

            public void onCameraClosed() {
            }
        } : eventsHandler2;
        this.cameraEnumerator = cameraEnumerator2;
        this.cameraName = cameraName2;
        this.uiThreadHandler = new Handler(Looper.getMainLooper());
        String[] deviceNames = cameraEnumerator2.getDeviceNames();
        if (deviceNames.length == 0) {
            throw new RuntimeException("No cameras attached.");
        } else if (!Arrays.asList(deviceNames).contains(this.cameraName)) {
            throw new IllegalArgumentException("Camera name " + this.cameraName + " does not match any known camera device.");
        }
    }

    public void initialize(SurfaceTextureHelper surfaceTextureHelper, Context applicationContext2, VideoCapturer.CapturerObserver capturerObserver2) {
        Handler handler;
        this.applicationContext = applicationContext2;
        this.capturerObserver = capturerObserver2;
        this.surfaceHelper = surfaceTextureHelper;
        if (surfaceTextureHelper == null) {
            handler = null;
        } else {
            handler = surfaceTextureHelper.getHandler();
        }
        this.cameraThreadHandler = handler;
    }

    public void startCapture(int width2, int height2, int framerate2) {
        Logging.d(TAG, "startCapture: " + width2 + "x" + height2 + "@" + framerate2);
        if (this.applicationContext == null) {
            throw new RuntimeException("CameraCapturer must be initialized before calling startCapture.");
        }
        synchronized (this.stateLock) {
            if (this.sessionOpening || this.currentSession != null) {
                Logging.w(TAG, "Session already open");
                return;
            }
            this.width = width2;
            this.height = height2;
            this.framerate = framerate2;
            this.sessionOpening = true;
            this.openAttemptsRemaining = 3;
            createSessionInternal(0, (MediaRecorder) null);
        }
    }

    /* access modifiers changed from: private */
    public void createSessionInternal(int delayMs, final MediaRecorder mediaRecorder) {
        this.uiThreadHandler.postDelayed(this.openCameraTimeoutRunnable, (long) (delayMs + OPEN_CAMERA_TIMEOUT));
        this.cameraThreadHandler.postDelayed(new Runnable() {
            public void run() {
                CameraCapturer.this.createCameraSession(CameraCapturer.this.createSessionCallback, CameraCapturer.this.cameraSessionEventsHandler, CameraCapturer.this.applicationContext, CameraCapturer.this.surfaceHelper, mediaRecorder, CameraCapturer.this.cameraName, CameraCapturer.this.width, CameraCapturer.this.height, CameraCapturer.this.framerate);
            }
        }, (long) delayMs);
    }

    public void stopCapture() {
        Logging.d(TAG, "Stop capture");
        synchronized (this.stateLock) {
            while (this.sessionOpening) {
                Logging.d(TAG, "Stop capture: Waiting for session to open");
                ThreadUtils.waitUninterruptibly(this.stateLock);
            }
            if (this.currentSession != null) {
                Logging.d(TAG, "Stop capture: Nulling session");
                this.cameraStatistics.release();
                this.cameraStatistics = null;
                final CameraSession oldSession = this.currentSession;
                this.cameraThreadHandler.post(new Runnable() {
                    public void run() {
                        oldSession.stop();
                    }
                });
                this.currentSession = null;
                this.capturerObserver.onCapturerStopped();
            } else {
                Logging.d(TAG, "Stop capture: No session open");
            }
        }
        Logging.d(TAG, "Stop capture done");
    }

    public void changeCaptureFormat(int width2, int height2, int framerate2) {
        Logging.d(TAG, "changeCaptureFormat: " + width2 + "x" + height2 + "@" + framerate2);
        synchronized (this.stateLock) {
            stopCapture();
            startCapture(width2, height2, framerate2);
        }
    }

    public void dispose() {
        Logging.d(TAG, "dispose");
        stopCapture();
    }

    public void switchCamera(final CameraVideoCapturer.CameraSwitchHandler switchEventsHandler2) {
        Logging.d(TAG, "switchCamera");
        this.cameraThreadHandler.post(new Runnable() {
            public void run() {
                CameraCapturer.this.switchCameraInternal(switchEventsHandler2);
            }
        });
    }

    public void addMediaRecorderToCamera(final MediaRecorder mediaRecorder, final CameraVideoCapturer.MediaRecorderHandler mediaRecoderEventsHandler) {
        Logging.d(TAG, "addMediaRecorderToCamera");
        this.cameraThreadHandler.post(new Runnable() {
            public void run() {
                CameraCapturer.this.updateMediaRecorderInternal(mediaRecorder, mediaRecoderEventsHandler);
            }
        });
    }

    public void removeMediaRecorderFromCamera(final CameraVideoCapturer.MediaRecorderHandler mediaRecoderEventsHandler) {
        Logging.d(TAG, "removeMediaRecorderFromCamera");
        this.cameraThreadHandler.post(new Runnable() {
            public void run() {
                CameraCapturer.this.updateMediaRecorderInternal((MediaRecorder) null, mediaRecoderEventsHandler);
            }
        });
    }

    public boolean isScreencast() {
        return false;
    }

    public void printStackTrace() {
        Thread cameraThread = null;
        if (this.cameraThreadHandler != null) {
            cameraThread = this.cameraThreadHandler.getLooper().getThread();
        }
        if (cameraThread != null) {
            StackTraceElement[] cameraStackTrace = cameraThread.getStackTrace();
            if (cameraStackTrace.length > 0) {
                Logging.d(TAG, "CameraCapturer stack trace:");
                for (StackTraceElement traceElem : cameraStackTrace) {
                    Logging.d(TAG, traceElem.toString());
                }
            }
        }
    }

    private void reportCameraSwitchError(String error, CameraVideoCapturer.CameraSwitchHandler switchEventsHandler2) {
        Logging.e(TAG, error);
        if (switchEventsHandler2 != null) {
            switchEventsHandler2.onCameraSwitchError(error);
        }
    }

    /* access modifiers changed from: private */
    public void switchCameraInternal(CameraVideoCapturer.CameraSwitchHandler switchEventsHandler2) {
        Logging.d(TAG, "switchCamera internal");
        String[] deviceNames = this.cameraEnumerator.getDeviceNames();
        if (deviceNames.length >= 2) {
            synchronized (this.stateLock) {
                if (this.switchState != SwitchState.IDLE) {
                    reportCameraSwitchError("Camera switch already in progress.", switchEventsHandler2);
                } else if (this.mediaRecorderState != MediaRecorderState.IDLE) {
                    reportCameraSwitchError("switchCamera: media recording is active", switchEventsHandler2);
                } else if (this.sessionOpening || this.currentSession != null) {
                    this.switchEventsHandler = switchEventsHandler2;
                    if (this.sessionOpening) {
                        this.switchState = SwitchState.PENDING;
                        return;
                    }
                    this.switchState = SwitchState.IN_PROGRESS;
                    Logging.d(TAG, "switchCamera: Stopping session");
                    this.cameraStatistics.release();
                    this.cameraStatistics = null;
                    final CameraSession oldSession = this.currentSession;
                    this.cameraThreadHandler.post(new Runnable() {
                        public void run() {
                            oldSession.stop();
                        }
                    });
                    this.currentSession = null;
                    this.cameraName = deviceNames[(Arrays.asList(deviceNames).indexOf(this.cameraName) + 1) % deviceNames.length];
                    this.sessionOpening = true;
                    this.openAttemptsRemaining = 1;
                    createSessionInternal(0, (MediaRecorder) null);
                    Logging.d(TAG, "switchCamera done");
                } else {
                    reportCameraSwitchError("switchCamera: camera is not running.", switchEventsHandler2);
                }
            }
        } else if (switchEventsHandler2 != null) {
            switchEventsHandler2.onCameraSwitchError("No camera to switch to.");
        }
    }

    private void reportUpdateMediaRecorderError(String error, CameraVideoCapturer.MediaRecorderHandler mediaRecoderEventsHandler) {
        checkIsOnCameraThread();
        Logging.e(TAG, error);
        if (mediaRecoderEventsHandler != null) {
            mediaRecoderEventsHandler.onMediaRecorderError(error);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0040, code lost:
        if (r5.mediaRecorderState == org.webrtc.CameraCapturer.MediaRecorderState.IDLE) goto L_0x0042;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateMediaRecorderInternal(android.media.MediaRecorder r6, org.webrtc.CameraVideoCapturer.MediaRecorderHandler r7) {
        /*
            r5 = this;
            r2 = 0
            r0 = 1
            r5.checkIsOnCameraThread()
            if (r6 == 0) goto L_0x0051
        L_0x0007:
            java.lang.String r2 = "CameraCapturer"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "updateMediaRecoderInternal internal. State: "
            java.lang.StringBuilder r3 = r3.append(r4)
            org.webrtc.CameraCapturer$MediaRecorderState r4 = r5.mediaRecorderState
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = ". Switch state: "
            java.lang.StringBuilder r3 = r3.append(r4)
            org.webrtc.CameraCapturer$SwitchState r4 = r5.switchState
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = ". Add MediaRecorder: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r3 = r3.toString()
            org.webrtc.Logging.d(r2, r3)
            java.lang.Object r3 = r5.stateLock
            monitor-enter(r3)
            if (r0 == 0) goto L_0x0042
            org.webrtc.CameraCapturer$MediaRecorderState r2 = r5.mediaRecorderState     // Catch:{ all -> 0x0060 }
            org.webrtc.CameraCapturer$MediaRecorderState r4 = org.webrtc.CameraCapturer.MediaRecorderState.IDLE     // Catch:{ all -> 0x0060 }
            if (r2 != r4) goto L_0x004a
        L_0x0042:
            if (r0 != 0) goto L_0x0053
            org.webrtc.CameraCapturer$MediaRecorderState r2 = r5.mediaRecorderState     // Catch:{ all -> 0x0060 }
            org.webrtc.CameraCapturer$MediaRecorderState r4 = org.webrtc.CameraCapturer.MediaRecorderState.ACTIVE     // Catch:{ all -> 0x0060 }
            if (r2 == r4) goto L_0x0053
        L_0x004a:
            java.lang.String r2 = "Incorrect state for MediaRecorder update."
            r5.reportUpdateMediaRecorderError(r2, r7)     // Catch:{ all -> 0x0060 }
            monitor-exit(r3)     // Catch:{ all -> 0x0060 }
        L_0x0050:
            return
        L_0x0051:
            r0 = r2
            goto L_0x0007
        L_0x0053:
            org.webrtc.CameraCapturer$SwitchState r2 = r5.switchState     // Catch:{ all -> 0x0060 }
            org.webrtc.CameraCapturer$SwitchState r4 = org.webrtc.CameraCapturer.SwitchState.IDLE     // Catch:{ all -> 0x0060 }
            if (r2 == r4) goto L_0x0063
            java.lang.String r2 = "MediaRecorder update while camera is switching."
            r5.reportUpdateMediaRecorderError(r2, r7)     // Catch:{ all -> 0x0060 }
            monitor-exit(r3)     // Catch:{ all -> 0x0060 }
            goto L_0x0050
        L_0x0060:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0060 }
            throw r2
        L_0x0063:
            org.webrtc.CameraSession r2 = r5.currentSession     // Catch:{ all -> 0x0060 }
            if (r2 != 0) goto L_0x006e
            java.lang.String r2 = "MediaRecorder update while camera is closed."
            r5.reportUpdateMediaRecorderError(r2, r7)     // Catch:{ all -> 0x0060 }
            monitor-exit(r3)     // Catch:{ all -> 0x0060 }
            goto L_0x0050
        L_0x006e:
            boolean r2 = r5.sessionOpening     // Catch:{ all -> 0x0060 }
            if (r2 == 0) goto L_0x0079
            java.lang.String r2 = "MediaRecorder update while camera is still opening."
            r5.reportUpdateMediaRecorderError(r2, r7)     // Catch:{ all -> 0x0060 }
            monitor-exit(r3)     // Catch:{ all -> 0x0060 }
            goto L_0x0050
        L_0x0079:
            r5.mediaRecorderEventsHandler = r7     // Catch:{ all -> 0x0060 }
            if (r0 == 0) goto L_0x00b2
            org.webrtc.CameraCapturer$MediaRecorderState r2 = org.webrtc.CameraCapturer.MediaRecorderState.IDLE_TO_ACTIVE     // Catch:{ all -> 0x0060 }
        L_0x007f:
            r5.mediaRecorderState = r2     // Catch:{ all -> 0x0060 }
            java.lang.String r2 = "CameraCapturer"
            java.lang.String r4 = "updateMediaRecoder: Stopping session"
            org.webrtc.Logging.d(r2, r4)     // Catch:{ all -> 0x0060 }
            org.webrtc.CameraVideoCapturer$CameraStatistics r2 = r5.cameraStatistics     // Catch:{ all -> 0x0060 }
            r2.release()     // Catch:{ all -> 0x0060 }
            r2 = 0
            r5.cameraStatistics = r2     // Catch:{ all -> 0x0060 }
            org.webrtc.CameraSession r1 = r5.currentSession     // Catch:{ all -> 0x0060 }
            android.os.Handler r2 = r5.cameraThreadHandler     // Catch:{ all -> 0x0060 }
            org.webrtc.CameraCapturer$11 r4 = new org.webrtc.CameraCapturer$11     // Catch:{ all -> 0x0060 }
            r4.<init>(r1)     // Catch:{ all -> 0x0060 }
            r2.post(r4)     // Catch:{ all -> 0x0060 }
            r2 = 0
            r5.currentSession = r2     // Catch:{ all -> 0x0060 }
            r2 = 1
            r5.sessionOpening = r2     // Catch:{ all -> 0x0060 }
            r2 = 1
            r5.openAttemptsRemaining = r2     // Catch:{ all -> 0x0060 }
            r2 = 0
            r5.createSessionInternal(r2, r6)     // Catch:{ all -> 0x0060 }
            monitor-exit(r3)     // Catch:{ all -> 0x0060 }
            java.lang.String r2 = "CameraCapturer"
            java.lang.String r3 = "updateMediaRecoderInternal done"
            org.webrtc.Logging.d(r2, r3)
            goto L_0x0050
        L_0x00b2:
            org.webrtc.CameraCapturer$MediaRecorderState r2 = org.webrtc.CameraCapturer.MediaRecorderState.ACTIVE_TO_IDLE     // Catch:{ all -> 0x0060 }
            goto L_0x007f
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.CameraCapturer.updateMediaRecorderInternal(android.media.MediaRecorder, org.webrtc.CameraVideoCapturer$MediaRecorderHandler):void");
    }

    /* access modifiers changed from: private */
    public void checkIsOnCameraThread() {
        if (Thread.currentThread() != this.cameraThreadHandler.getLooper().getThread()) {
            Logging.e(TAG, "Check is on camera thread failed.");
            throw new RuntimeException("Not on camera thread.");
        }
    }

    /* access modifiers changed from: protected */
    public String getCameraName() {
        String str;
        synchronized (this.stateLock) {
            str = this.cameraName;
        }
        return str;
    }
}
