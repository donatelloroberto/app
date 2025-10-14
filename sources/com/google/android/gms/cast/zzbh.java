package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzds;
import org.json.JSONObject;

final class zzbh extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzgb;
    private final /* synthetic */ int zzge;
    private final /* synthetic */ JSONObject zzgg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbh(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int i, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzgb = remoteMediaPlayer;
        this.zzge = i;
        this.zzgg = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzct zzct) throws zzds {
        this.zzgb.zzfu.zza(this.zzgz, 0, -1, (MediaQueueItem[]) null, 0, Integer.valueOf(this.zzge), this.zzgg);
    }
}
