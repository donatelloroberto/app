package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.internal.cast.zzct;

final class zzh extends Cast.zza {
    private final /* synthetic */ String zzag;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzh(Cast.CastApi.zza zza, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzag = str;
    }

    public final void zza(zzct zzct) throws RemoteException {
        try {
            String str = this.zzag;
            LaunchOptions launchOptions = new LaunchOptions();
            launchOptions.setRelaunchIfRunning(false);
            zzct.zza(str, launchOptions, (BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult>) this);
        } catch (IllegalStateException e) {
            zzr(CastStatusCodes.INVALID_REQUEST);
        }
    }

    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzct) anyClient);
    }
}
