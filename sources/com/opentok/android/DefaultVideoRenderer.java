package com.opentok.android;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.view.View;
import com.opentok.android.BaseVideoRenderer;
import java.nio.ByteBuffer;
import java.util.concurrent.locks.ReentrantLock;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class DefaultVideoRenderer extends BaseVideoRenderer {
    Context context;
    ReentrantLock frameLock = new ReentrantLock();
    boolean isPillarBoxEnabled = true;
    boolean isVideoDisabled = false;
    BaseVideoRenderer.Frame lastFrame;
    protected long nativeInstance;
    MyRenderer renderer;
    GLSurfaceView view;

    /* access modifiers changed from: private */
    public native void nativeCreateRenderer();

    /* access modifiers changed from: private */
    public native void nativeRenderFrame(ByteBuffer byteBuffer, int i, int i2, int i3, int i4, boolean z, boolean z2);

    /* access modifiers changed from: private */
    public native void nativeSetupRenderer(int i, int i2);

    public DefaultVideoRenderer(Context context2) {
        this.context = context2;
        this.view = new GLSurfaceView(context2);
        this.view.setEGLContextClientVersion(2);
        this.renderer = new MyRenderer();
        this.view.setRenderer(this.renderer);
        this.view.setRenderMode(0);
        this.view.setZOrderMediaOverlay(true);
    }

    private class MyRenderer implements GLSurfaceView.Renderer {
        public MyRenderer() {
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            GLES20.glClear(16384);
            DefaultVideoRenderer.this.nativeCreateRenderer();
        }

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            DefaultVideoRenderer.this.nativeSetupRenderer(width, height);
        }

        public void onDrawFrame(GL10 gl) {
            boolean videoDisabled = false;
            BaseVideoRenderer.Frame frame = null;
            DefaultVideoRenderer.this.frameLock.lock();
            if (DefaultVideoRenderer.this.isVideoDisabled) {
                videoDisabled = true;
            } else if (DefaultVideoRenderer.this.lastFrame != null) {
                frame = DefaultVideoRenderer.this.lastFrame;
                DefaultVideoRenderer.this.lastFrame = null;
            }
            DefaultVideoRenderer.this.frameLock.unlock();
            if (videoDisabled) {
                gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
                GLES20.glClear(16384);
            } else if (frame != null) {
                ByteBuffer bb = frame.getBuffer();
                bb.clear();
                DefaultVideoRenderer.this.nativeRenderFrame(bb, frame.getWidth(), frame.getHeight(), frame.getYstride(), frame.getUvStride(), frame.isMirroredX(), DefaultVideoRenderer.this.isPillarBoxEnabled);
                frame.recycle();
            }
        }
    }

    public void onPause() {
        this.view.onPause();
    }

    public void onResume() {
        this.view.onResume();
    }

    public void onFrame(BaseVideoRenderer.Frame frame) {
        boolean added = false;
        this.frameLock.lock();
        if (this.lastFrame == null) {
            this.lastFrame = frame;
            added = true;
        }
        this.frameLock.unlock();
        if (added) {
            this.view.requestRender();
        } else {
            frame.recycle();
        }
    }

    public void setStyle(String key, String value) {
        if (!BaseVideoRenderer.STYLE_VIDEO_SCALE.equals(key)) {
            return;
        }
        if (BaseVideoRenderer.STYLE_VIDEO_FIT.equals(value)) {
            this.isPillarBoxEnabled = true;
        } else if (BaseVideoRenderer.STYLE_VIDEO_FILL.equals(value)) {
            this.isPillarBoxEnabled = false;
        }
    }

    public void onVideoPropertiesChanged(boolean videoEnabled) {
        this.frameLock.lock();
        this.isVideoDisabled = !videoEnabled;
        this.frameLock.unlock();
        this.view.requestRender();
    }

    public View getView() {
        return this.view;
    }
}
