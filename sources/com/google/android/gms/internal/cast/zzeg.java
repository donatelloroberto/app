package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public final class zzeg extends zzeb {
    private final /* synthetic */ zzee zzabq;

    protected zzeg(zzee zzee) {
        this.zzabq = zzee;
    }

    public final void onDisconnected() throws RemoteException {
        zzdx.zzbf.d("onDisconnected", new Object[0]);
        this.zzabq.zzabo.zzc();
        this.zzabq.setResult(new zzef(Status.RESULT_SUCCESS));
    }

    public final void onError(int i) throws RemoteException {
        zzdx.zzbf.d("onError: %d", Integer.valueOf(i));
        this.zzabq.zzabo.zzc();
        this.zzabq.setResult(new zzef(Status.RESULT_INTERNAL_ERROR));
    }
}
