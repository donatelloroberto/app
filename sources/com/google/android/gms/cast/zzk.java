package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzdg;

final class zzk extends zzdg {
    private final /* synthetic */ String val$sessionId;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzk(Cast.CastApi.zza zza, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.val$sessionId = str;
    }

    public final void zza(zzct zzct) throws RemoteException {
        if (TextUtils.isEmpty(this.val$sessionId)) {
            setResult(createFailedResult(new Status(CastStatusCodes.INVALID_REQUEST, "IllegalArgument: sessionId cannot be null or empty", (PendingIntent) null)));
            return;
        }
        try {
            zzct.zza(this.val$sessionId, (BaseImplementation.ResultHolder<Status>) this);
        } catch (IllegalStateException e) {
            zzr(CastStatusCodes.INVALID_REQUEST);
        }
    }

    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzct) anyClient);
    }
}
