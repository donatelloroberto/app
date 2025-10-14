package com.google.android.gms.cast;

import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.cast.CastRemoteDisplayClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.internal.cast.zzei;
import com.google.android.gms.internal.cast.zzel;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzq extends CastRemoteDisplayClient.zza {
    private final /* synthetic */ TaskCompletionSource zzbh;
    private final /* synthetic */ zzei zzbi;
    private final /* synthetic */ zzr zzbj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzq(zzr zzr, TaskCompletionSource taskCompletionSource, zzei zzei) {
        super((zzp) null);
        this.zzbj = zzr;
        this.zzbh = taskCompletionSource;
        this.zzbi = zzei;
    }

    public final void zza(int i, int i2, Surface surface) throws RemoteException {
        this.zzbj.zzbn.zzbf.d("onConnected", new Object[0]);
        DisplayManager displayManager = (DisplayManager) this.zzbj.zzbn.getApplicationContext().getSystemService("display");
        if (displayManager == null) {
            this.zzbj.zzbn.zzbf.e("Unable to get the display manager", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbh);
            return;
        }
        this.zzbj.zzbn.zzc();
        VirtualDisplay unused = this.zzbj.zzbn.zzbg = displayManager.createVirtualDisplay("private_display", i, i2, CastRemoteDisplayClient.zza(i, i2), surface, 2);
        if (this.zzbj.zzbn.zzbg == null) {
            this.zzbj.zzbn.zzbf.e("Unable to create virtual display", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbh);
            return;
        }
        Display display = this.zzbj.zzbn.zzbg.getDisplay();
        if (display == null) {
            this.zzbj.zzbn.zzbf.e("Virtual display does not have a display", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbh);
            return;
        }
        try {
            ((zzel) this.zzbi.getService()).zza(this, display.getDisplayId());
        } catch (RemoteException | IllegalStateException e) {
            this.zzbj.zzbn.zzbf.e("Unable to provision the route's new virtual Display", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbh);
        }
    }

    public final void zzd() {
        this.zzbj.zzbn.zzbf.d("onConnectedWithDisplay", new Object[0]);
        if (this.zzbj.zzbn.zzbg == null) {
            this.zzbj.zzbn.zzbf.e("There is no virtual display", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbh);
            return;
        }
        Display display = this.zzbj.zzbn.zzbg.getDisplay();
        if (display != null) {
            TaskUtil.setResultOrApiException(Status.RESULT_SUCCESS, display, this.zzbh);
            return;
        }
        this.zzbj.zzbn.zzbf.e("Virtual display no longer has a display", new Object[0]);
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbh);
    }

    public final void onError(int i) throws RemoteException {
        this.zzbj.zzbn.zzbf.d("onError: %d", Integer.valueOf(i));
        this.zzbj.zzbn.zzc();
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbh);
    }
}
