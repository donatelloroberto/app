package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzds;
import org.json.JSONObject;

final class zzbn extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzgb;
    private final /* synthetic */ JSONObject zzgg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbn(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzgb = remoteMediaPlayer;
        this.zzgg = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzct zzct) throws zzds {
        this.zzgb.zzfu.zzc(this.zzgz, this.zzgg);
    }
}
