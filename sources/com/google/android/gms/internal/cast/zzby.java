package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzby implements ResultCallback<Status> {
    private final /* synthetic */ zzbs zzww;
    private final /* synthetic */ long zzxa;

    zzby(zzbs zzbs, long j) {
        this.zzww = zzbs;
        this.zzxa = j;
    }

    public final /* synthetic */ void onResult(Result result) {
        Status status = (Status) result;
        if (!status.isSuccess()) {
            this.zzww.zza(this.zzxa, status.getStatusCode());
        }
    }
}
