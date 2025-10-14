package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzds;

final class zzau extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ long[] zzga;
    private final /* synthetic */ RemoteMediaPlayer zzgb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzau(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, long[] jArr) {
        super(googleApiClient);
        this.zzgb = remoteMediaPlayer;
        this.zzga = jArr;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzct zzct) throws zzds {
        this.zzgb.zzfu.zza(this.zzgz, this.zzga);
    }
}
