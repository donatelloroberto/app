package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
class zzee extends BaseImplementation.ApiMethodImpl<CastRemoteDisplay.CastRemoteDisplaySessionResult, zzeh> {
    final /* synthetic */ zzdx zzabo;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzee(zzdx zzdx, GoogleApiClient googleApiClient) {
        super((Api<?>) zzdx.zzabj, googleApiClient);
        this.zzabo = zzdx;
    }

    @VisibleForTesting
    /* renamed from: zza */
    public void doExecute(zzeh zzeh) throws RemoteException {
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ Result createFailedResult(Status status) {
        return new zzef(status);
    }
}
