package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.CastRemoteDisplayClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzs extends CastRemoteDisplayClient.zza {
    private final /* synthetic */ TaskCompletionSource zzbh;
    private final /* synthetic */ zzt zzbo;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzs(zzt zzt, TaskCompletionSource taskCompletionSource) {
        super((zzp) null);
        this.zzbo = zzt;
        this.zzbh = taskCompletionSource;
    }

    public final void onDisconnected() throws RemoteException {
        this.zzbo.zzbn.zzbf.d("onDisconnected", new Object[0]);
        this.zzbo.zzbn.zzc();
        TaskUtil.setResultOrApiException(Status.RESULT_SUCCESS, this.zzbh);
    }

    public final void onError(int i) throws RemoteException {
        this.zzbo.zzbn.zzbf.d("onError: %d", Integer.valueOf(i));
        this.zzbo.zzbn.zzc();
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, this.zzbh);
    }
}
