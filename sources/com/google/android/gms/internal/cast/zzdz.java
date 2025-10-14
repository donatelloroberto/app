package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzdz extends zzee {
    private final /* synthetic */ String zzabn;
    private final /* synthetic */ zzdx zzabo;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdz(zzdx zzdx, GoogleApiClient googleApiClient, String str) {
        super(zzdx, googleApiClient);
        this.zzabo = zzdx;
        this.zzabn = str;
    }

    public final void zza(zzeh zzeh) throws RemoteException {
        zzeh.zza(new zzed(this, zzeh), this.zzabo.zzabk, this.zzabn);
    }

    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzeh) anyClient);
    }
}
