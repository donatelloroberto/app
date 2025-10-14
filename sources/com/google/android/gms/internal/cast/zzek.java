package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzek extends zzeq {
    private final /* synthetic */ zzen zzabt;
    private final /* synthetic */ zzeh zzabu;

    zzek(zzeh zzeh, zzen zzen) {
        this.zzabu = zzeh;
        this.zzabt = zzen;
    }

    public final void zzy(int i) throws RemoteException {
        zzeh.zzbf.d("onRemoteDisplayEnded", new Object[0]);
        if (this.zzabt != null) {
            this.zzabt.zzy(i);
        }
        if (this.zzabu.zzabr != null) {
            this.zzabu.zzabr.onRemoteDisplayEnded(new Status(i));
        }
    }
}
