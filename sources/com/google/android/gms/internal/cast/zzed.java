package com.google.android.gms.internal.cast;

import android.annotation.TargetApi;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.common.api.Status;

@TargetApi(19)
public final class zzed extends zzeb {
    private final zzeh zzabp;
    private final /* synthetic */ zzee zzabq;

    public zzed(zzee zzee, zzeh zzeh) {
        this.zzabq = zzee;
        this.zzabp = zzeh;
    }

    public final void zza(int i, int i2, Surface surface) {
        zzdx.zzbf.d("onConnected", new Object[0]);
        DisplayManager displayManager = (DisplayManager) this.zzabp.getContext().getSystemService("display");
        if (displayManager == null) {
            zzdx.zzbf.e("Unable to get the display manager", new Object[0]);
            this.zzabq.setResult(new zzef(Status.RESULT_INTERNAL_ERROR));
            return;
        }
        this.zzabq.zzabo.zzc();
        VirtualDisplay unused = this.zzabq.zzabo.zzbg = displayManager.createVirtualDisplay("private_display", i, i2, ((i < i2 ? i : i2) * 320) / 1080, surface, 2);
        if (this.zzabq.zzabo.zzbg == null) {
            zzdx.zzbf.e("Unable to create virtual display", new Object[0]);
            this.zzabq.setResult(new zzef(Status.RESULT_INTERNAL_ERROR));
        } else if (this.zzabq.zzabo.zzbg.getDisplay() == null) {
            zzdx.zzbf.e("Virtual display does not have a display", new Object[0]);
            this.zzabq.setResult(new zzef(Status.RESULT_INTERNAL_ERROR));
        } else {
            try {
                ((zzel) this.zzabp.getService()).zza(this, this.zzabq.zzabo.zzbg.getDisplay().getDisplayId());
            } catch (RemoteException | IllegalStateException e) {
                zzdx.zzbf.e("Unable to provision the route's new virtual Display", new Object[0]);
                this.zzabq.setResult(new zzef(Status.RESULT_INTERNAL_ERROR));
            }
        }
    }

    public final void zzd() {
        zzdx.zzbf.d("onConnectedWithDisplay", new Object[0]);
        if (this.zzabq.zzabo.zzbg == null) {
            zzdx.zzbf.e("There is no virtual display", new Object[0]);
            this.zzabq.setResult(new zzef(Status.RESULT_INTERNAL_ERROR));
            return;
        }
        Display display = this.zzabq.zzabo.zzbg.getDisplay();
        if (display != null) {
            this.zzabq.setResult(new zzef(display));
            return;
        }
        zzdx.zzbf.e("Virtual display no longer has a display", new Object[0]);
        this.zzabq.setResult(new zzef(Status.RESULT_INTERNAL_ERROR));
    }

    public final void onError(int i) throws RemoteException {
        zzdx.zzbf.d("onError: %d", Integer.valueOf(i));
        this.zzabq.zzabo.zzc();
        this.zzabq.setResult(new zzef(Status.RESULT_INTERNAL_ERROR));
    }
}
