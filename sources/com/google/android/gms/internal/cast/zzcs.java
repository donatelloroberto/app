package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcs extends IStatusCallback.Stub {
    private final /* synthetic */ TaskCompletionSource zzbh;

    zzcs(zzcp zzcp, TaskCompletionSource taskCompletionSource) {
        this.zzbh = taskCompletionSource;
    }

    public final void onResult(Status status) throws RemoteException {
        TaskUtil.setResultOrApiException(status, this.zzbh);
    }
}
