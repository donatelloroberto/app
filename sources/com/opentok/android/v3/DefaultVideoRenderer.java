package com.opentok.android.v3;

import android.content.Context;
import android.support.annotation.Keep;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

@Keep
class DefaultVideoRenderer extends AbstractRenderer implements SurfaceHolder.Callback {
    private SurfaceView surfaceView;

    public DefaultVideoRenderer(Context context) {
        super(context);
        this.surfaceView = new SurfaceView(context);
        this.surfaceView.getHolder().addCallback(this);
    }

    public void onVideoEnabled(boolean videoEnabled) {
    }

    public View getView() {
        return this.surfaceView;
    }

    public void surfaceCreated(SurfaceHolder holder) {
        super.setSurface(holder.getSurface());
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        super.surfaceResized(holder.getSurface());
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        super.setSurface((Surface) null);
    }
}
