package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

final class zzcp extends TaskApiCall<zzcr, Void> {
    private final /* synthetic */ List zzpw;
    private final /* synthetic */ String[] zzyh;
    private final /* synthetic */ String zzyi;

    zzcp(zzcn zzcn, String[] strArr, String str, List list) {
        this.zzyh = strArr;
        this.zzyi = str;
        this.zzpw = list;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzdj) ((zzcr) anyClient).getService()).zza(new zzcs(this, taskCompletionSource), this.zzyh, this.zzyi, this.zzpw);
    }
}
