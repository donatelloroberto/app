package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzdg;

final class zzf extends zzdg {
    private final /* synthetic */ String zzae;
    private final /* synthetic */ String zzaf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzf(Cast.CastApi.zza zza, GoogleApiClient googleApiClient, String str, String str2) {
        super(googleApiClient);
        this.zzae = str;
        this.zzaf = str2;
    }

    public final void zza(zzct zzct) throws RemoteException {
        try {
            zzct.zza(this.zzae, this.zzaf, (BaseImplementation.ResultHolder<Status>) this);
        } catch (IllegalArgumentException | IllegalStateException e) {
            zzr(CastStatusCodes.INVALID_REQUEST);
        }
    }

    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzct) anyClient);
    }
}
