package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.Display;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.cast.zzei;
import com.google.android.gms.internal.cast.zzej;
import com.google.android.gms.internal.cast.zzel;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzr extends TaskApiCall<zzei, Display> {
    private final /* synthetic */ String zzag;
    private final /* synthetic */ int zzbk;
    private final /* synthetic */ PendingIntent zzbl;
    private final /* synthetic */ CastDevice zzbm;
    final /* synthetic */ CastRemoteDisplayClient zzbn;

    zzr(CastRemoteDisplayClient castRemoteDisplayClient, int i, PendingIntent pendingIntent, CastDevice castDevice, String str) {
        this.zzbn = castRemoteDisplayClient;
        this.zzbk = i;
        this.zzbl = pendingIntent;
        this.zzbm = castDevice;
        this.zzag = str;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzei zzei = (zzei) anyClient;
        Bundle bundle = new Bundle();
        bundle.putInt("configuration", this.zzbk);
        ((zzel) zzei.getService()).zza((zzej) new zzq(this, taskCompletionSource, zzei), this.zzbl, this.zzbm.getDeviceId(), this.zzag, bundle);
    }
}
