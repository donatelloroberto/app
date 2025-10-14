package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzct;

final class zzbq extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzgb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbq(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzgb = remoteMediaPlayer;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzct zzct) {
        this.zzgb.zzfu.zzb(this.zzgz);
    }
}
