package com.google.android.gms.cast;

import android.support.annotation.NonNull;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzbr implements ResultCallback<Status> {
    private final long zzhb;
    private final /* synthetic */ RemoteMediaPlayer.zza zzhc;

    zzbr(RemoteMediaPlayer.zza zza, long j) {
        this.zzhc = zza;
        this.zzhb = j;
    }

    public final /* synthetic */ void onResult(@NonNull Result result) {
        Status status = (Status) result;
        if (!status.isSuccess()) {
            RemoteMediaPlayer.this.zzfu.zza(this.zzhb, status.getStatusCode());
        }
    }
}
