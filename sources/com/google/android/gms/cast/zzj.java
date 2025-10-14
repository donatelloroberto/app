package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzct;

final class zzj extends Cast.zza {
    private final /* synthetic */ String val$sessionId;
    private final /* synthetic */ String zzag;
    private final /* synthetic */ zzah zzai = null;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzj(Cast.CastApi.zza zza, GoogleApiClient googleApiClient, String str, String str2, zzah zzah) {
        super(googleApiClient);
        this.zzag = str;
        this.val$sessionId = str2;
    }

    public final void zza(zzct zzct) throws RemoteException {
        try {
            zzct.zza(this.zzag, this.val$sessionId, this.zzai, this);
        } catch (IllegalStateException e) {
            zzr(CastStatusCodes.INVALID_REQUEST);
        }
    }

    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzct) anyClient);
    }
}
