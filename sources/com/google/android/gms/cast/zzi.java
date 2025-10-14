package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzdg;

final class zzi extends zzdg {
    zzi(Cast.CastApi.zza zza, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    public final void zza(zzct zzct) throws RemoteException {
        try {
            zzct.zzb(this);
        } catch (IllegalStateException e) {
            zzr(CastStatusCodes.INVALID_REQUEST);
        }
    }

    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzct) anyClient);
    }
}
