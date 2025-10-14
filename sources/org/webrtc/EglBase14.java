package org.webrtc;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.os.Build;
import android.view.Surface;
import org.webrtc.EglBase;

@TargetApi(18)
public class EglBase14 extends EglBase {
    private static final int CURRENT_SDK_VERSION = Build.VERSION.SDK_INT;
    private static final int EGLExt_SDK_VERSION = 18;
    private static final String TAG = "EglBase14";
    private EGLConfig eglConfig;
    private EGLContext eglContext;
    private EGLDisplay eglDisplay = getEglDisplay();
    private EGLSurface eglSurface = EGL14.EGL_NO_SURFACE;

    public static boolean isEGL14Supported() {
        boolean z;
        StringBuilder append = new StringBuilder().append("SDK version: ").append(CURRENT_SDK_VERSION).append(". isEGL14Supported: ");
        if (CURRENT_SDK_VERSION >= 18) {
            z = true;
        } else {
            z = false;
        }
        Logging.d(TAG, append.append(z).toString());
        return CURRENT_SDK_VERSION >= 18;
    }

    public static class Context extends EglBase.Context {
        /* access modifiers changed from: private */
        public final EGLContext egl14Context;

        public Context(EGLContext eglContext) {
            this.egl14Context = eglContext;
        }
    }

    public EglBase14(Context sharedContext, int[] configAttributes) {
        this.eglConfig = getEglConfig(this.eglDisplay, configAttributes);
        this.eglContext = createEglContext(sharedContext, this.eglDisplay, this.eglConfig);
    }

    public void createSurface(Surface surface) {
        createSurfaceInternal(surface);
    }

    public void createSurface(SurfaceTexture surfaceTexture) {
        createSurfaceInternal(surfaceTexture);
    }

    private void createSurfaceInternal(Object surface) {
        if ((surface instanceof Surface) || (surface instanceof SurfaceTexture)) {
            checkIsNotReleased();
            if (this.eglSurface != EGL14.EGL_NO_SURFACE) {
                throw new RuntimeException("Already has an EGLSurface");
            }
            this.eglSurface = EGL14.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, surface, new int[]{12344}, 0);
            if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
                throw new RuntimeException("Failed to create window surface: 0x" + Integer.toHexString(EGL14.eglGetError()));
            }
            return;
        }
        throw new IllegalStateException("Input must be either a Surface or SurfaceTexture");
    }

    public void createDummyPbufferSurface() {
        createPbufferSurface(1, 1);
    }

    public void createPbufferSurface(int width, int height) {
        checkIsNotReleased();
        if (this.eglSurface != EGL14.EGL_NO_SURFACE) {
            throw new RuntimeException("Already has an EGLSurface");
        }
        this.eglSurface = EGL14.eglCreatePbufferSurface(this.eglDisplay, this.eglConfig, new int[]{12375, width, 12374, height, 12344}, 0);
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
            throw new RuntimeException("Failed to create pixel buffer surface with size " + width + "x" + height + ": 0x" + Integer.toHexString(EGL14.eglGetError()));
        }
    }

    public Context getEglBaseContext() {
        return new Context(this.eglContext);
    }

    public boolean hasSurface() {
        return this.eglSurface != EGL14.EGL_NO_SURFACE;
    }

    public int surfaceWidth() {
        int[] widthArray = new int[1];
        EGL14.eglQuerySurface(this.eglDisplay, this.eglSurface, 12375, widthArray, 0);
        return widthArray[0];
    }

    public int surfaceHeight() {
        int[] heightArray = new int[1];
        EGL14.eglQuerySurface(this.eglDisplay, this.eglSurface, 12374, heightArray, 0);
        return heightArray[0];
    }

    public void releaseSurface() {
        if (this.eglSurface != EGL14.EGL_NO_SURFACE) {
            EGL14.eglDestroySurface(this.eglDisplay, this.eglSurface);
            this.eglSurface = EGL14.EGL_NO_SURFACE;
        }
    }

    private void checkIsNotReleased() {
        if (this.eglDisplay == EGL14.EGL_NO_DISPLAY || this.eglContext == EGL14.EGL_NO_CONTEXT || this.eglConfig == null) {
            throw new RuntimeException("This object has been released");
        }
    }

    public void release() {
        checkIsNotReleased();
        releaseSurface();
        detachCurrent();
        EGL14.eglDestroyContext(this.eglDisplay, this.eglContext);
        EGL14.eglReleaseThread();
        EGL14.eglTerminate(this.eglDisplay);
        this.eglContext = EGL14.EGL_NO_CONTEXT;
        this.eglDisplay = EGL14.EGL_NO_DISPLAY;
        this.eglConfig = null;
    }

    public void makeCurrent() {
        checkIsNotReleased();
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
            throw new RuntimeException("No EGLSurface - can't make current");
        }
        synchronized (EglBase.lock) {
            if (!EGL14.eglMakeCurrent(this.eglDisplay, this.eglSurface, this.eglSurface, this.eglContext)) {
                throw new RuntimeException("eglMakeCurrent failed: 0x" + Integer.toHexString(EGL14.eglGetError()));
            }
        }
    }

    public void detachCurrent() {
        synchronized (EglBase.lock) {
            if (!EGL14.eglMakeCurrent(this.eglDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT)) {
                throw new RuntimeException("eglDetachCurrent failed: 0x" + Integer.toHexString(EGL14.eglGetError()));
            }
        }
    }

    public void swapBuffers() {
        checkIsNotReleased();
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
            throw new RuntimeException("No EGLSurface - can't swap buffers");
        }
        synchronized (EglBase.lock) {
            EGL14.eglSwapBuffers(this.eglDisplay, this.eglSurface);
        }
    }

    public void swapBuffers(long timeStampNs) {
        checkIsNotReleased();
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
            throw new RuntimeException("No EGLSurface - can't swap buffers");
        }
        synchronized (EglBase.lock) {
            EGLExt.eglPresentationTimeANDROID(this.eglDisplay, this.eglSurface, timeStampNs);
            EGL14.eglSwapBuffers(this.eglDisplay, this.eglSurface);
        }
    }

    private static EGLDisplay getEglDisplay() {
        EGLDisplay eglDisplay2 = EGL14.eglGetDisplay(0);
        if (eglDisplay2 == EGL14.EGL_NO_DISPLAY) {
            throw new RuntimeException("Unable to get EGL14 display: 0x" + Integer.toHexString(EGL14.eglGetError()));
        }
        int[] version = new int[2];
        if (EGL14.eglInitialize(eglDisplay2, version, 0, version, 1)) {
            return eglDisplay2;
        }
        throw new RuntimeException("Unable to initialize EGL14: 0x" + Integer.toHexString(EGL14.eglGetError()));
    }

    private static EGLConfig getEglConfig(EGLDisplay eglDisplay2, int[] configAttributes) {
        EGLConfig[] configs = new EGLConfig[1];
        int[] numConfigs = new int[1];
        if (!EGL14.eglChooseConfig(eglDisplay2, configAttributes, 0, configs, 0, configs.length, numConfigs, 0)) {
            throw new RuntimeException("eglChooseConfig failed: 0x" + Integer.toHexString(EGL14.eglGetError()));
        } else if (numConfigs[0] <= 0) {
            throw new RuntimeException("Unable to find any matching EGL config");
        } else {
            EGLConfig eglConfig2 = configs[0];
            if (eglConfig2 != null) {
                return eglConfig2;
            }
            throw new RuntimeException("eglChooseConfig returned null");
        }
    }

    private static EGLContext createEglContext(Context sharedContext, EGLDisplay eglDisplay2, EGLConfig eglConfig2) {
        EGLContext rootContext;
        EGLContext eglContext2;
        if (sharedContext == null || sharedContext.egl14Context != EGL14.EGL_NO_CONTEXT) {
            int[] contextAttributes = {12440, 2, 12344};
            if (sharedContext == null) {
                rootContext = EGL14.EGL_NO_CONTEXT;
            } else {
                rootContext = sharedContext.egl14Context;
            }
            synchronized (EglBase.lock) {
                eglContext2 = EGL14.eglCreateContext(eglDisplay2, eglConfig2, rootContext, contextAttributes, 0);
            }
            if (eglContext2 != EGL14.EGL_NO_CONTEXT) {
                return eglContext2;
            }
            throw new RuntimeException("Failed to create EGL context: 0x" + Integer.toHexString(EGL14.eglGetError()));
        }
        throw new RuntimeException("Invalid sharedContext");
    }
}
