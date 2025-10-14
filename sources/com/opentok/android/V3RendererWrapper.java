package com.opentok.android;

import android.content.Context;
import android.view.View;
import com.opentok.android.BaseVideoRenderer;
import com.opentok.android.v3.AbstractRenderer;

class V3RendererWrapper extends AbstractRenderer {
    private BaseVideoRenderer renderer;

    private native void initFrame(BaseVideoRenderer.Frame frame, long j, boolean z);

    public V3RendererWrapper(BaseVideoRenderer renderer2) {
        super((Context) null);
        this.renderer = renderer2;
    }

    public void onVideoEnabled(boolean videoEnabled) {
    }

    public View getView() {
        return null;
    }

    public void pause() {
        super.pause();
        this.renderer.onPause();
    }

    public void resume() {
        super.resume();
        this.renderer.onResume();
    }

    /* access modifiers changed from: protected */
    public void onFrame(long nativeFrameCtx, boolean reverseFrame) {
        BaseVideoRenderer.Frame frame = this.renderer.aquireFrameObject();
        initFrame(frame, nativeFrameCtx, reverseFrame);
        this.renderer.onFrame(frame);
    }

    /* access modifiers changed from: package-private */
    public void setBaseRenderer(BaseVideoRenderer renderer2) {
        this.renderer = renderer2;
        this.renderer.setV3Renderer(this);
    }

    /* access modifiers changed from: package-private */
    public BaseVideoRenderer getBaseRenderer() {
        return this.renderer;
    }
}
