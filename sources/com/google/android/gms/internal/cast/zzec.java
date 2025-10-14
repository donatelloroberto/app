package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzec extends zzee {
    zzec(zzdx zzdx, GoogleApiClient googleApiClient) {
        super(zzdx, googleApiClient);
    }

    public final void zza(zzeh zzeh) throws RemoteException {
        zzeh.zza(new zzeg(this));
    }

    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzeh) anyClient);
    }
}
