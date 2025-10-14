package com.opentok.android;

import android.view.View;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class BaseVideoRenderer {
    public static final String STYLE_VIDEO_FILL = "STYLE_VIDEO_FILL";
    public static final String STYLE_VIDEO_FIT = "STYLE_VIDEO_FIT";
    public static final String STYLE_VIDEO_SCALE = "STYLE_VIDEO_SCALE";
    /* access modifiers changed from: private */
    public ConcurrentLinkedQueue<Frame> reuseFrameStack = new ConcurrentLinkedQueue<>();
    private V3RendererWrapper v3Wrapper;

    public abstract View getView();

    public abstract void onFrame(Frame frame);

    public abstract void onPause();

    public abstract void onResume();

    public abstract void onVideoPropertiesChanged(boolean z);

    public abstract void setStyle(String str, String str2);

    public final class Frame {
        protected ByteBuffer buffer;
        protected int format;
        protected int height;
        protected long internalBuffer;
        protected byte[] metadata;
        protected boolean mirrored;
        protected int uvStride;
        protected int width;
        protected int yStride;

        private native long finalizeNative(long j);

        protected Frame() {
        }

        public void recycle() {
            this.internalBuffer = finalizeNative(this.internalBuffer);
            this.buffer = null;
            this.height = 0;
            this.width = 0;
            this.format = 0;
            BaseVideoRenderer.this.reuseFrameStack.add(this);
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            if (0 != this.internalBuffer) {
                this.internalBuffer = finalizeNative(this.internalBuffer);
            }
            super.finalize();
        }

        public ByteBuffer getBuffer() {
            return this.buffer;
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

        public boolean isMirroredX() {
            return this.mirrored;
        }

        public int getYstride() {
            return this.yStride;
        }

        public int getUvStride() {
            return this.uvStride;
        }

        public byte[] getMetadata() {
            return this.metadata;
        }
    }

    /* access modifiers changed from: package-private */
    public Frame aquireFrameObject() {
        if (this.reuseFrameStack.isEmpty()) {
            return new Frame();
        }
        return (Frame) this.reuseFrameStack.remove();
    }

    /* access modifiers changed from: package-private */
    public void setV3Renderer(V3RendererWrapper wrapper) {
        this.v3Wrapper = wrapper;
    }
}
