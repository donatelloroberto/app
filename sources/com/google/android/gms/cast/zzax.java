package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzds;

final class zzax extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzgb;
    private final /* synthetic */ TextTrackStyle zzgh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzax(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, TextTrackStyle textTrackStyle) {
        super(googleApiClient);
        this.zzgb = remoteMediaPlayer;
        this.zzgh = textTrackStyle;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzct zzct) throws zzds {
        this.zzgb.zzfu.zza(this.zzgz, this.zzgh);
    }
}
