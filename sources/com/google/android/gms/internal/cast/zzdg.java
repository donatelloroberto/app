package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public class zzdg extends zzcl<Status> {
    public zzdg(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* renamed from: zza */
    public void doExecute(zzct zzct) throws RemoteException {
    }

    public /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }
}
