package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.cast.zzei;
import com.google.android.gms.internal.cast.zzel;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzt extends TaskApiCall<zzei, Void> {
    final /* synthetic */ CastRemoteDisplayClient zzbn;

    zzt(CastRemoteDisplayClient castRemoteDisplayClient) {
        this.zzbn = castRemoteDisplayClient;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzel) ((zzei) anyClient).getService()).zza(new zzs(this, taskCompletionSource));
    }
}
