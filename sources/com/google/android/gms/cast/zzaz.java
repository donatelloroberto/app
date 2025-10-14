package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzds;
import org.json.JSONObject;

final class zzaz extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzgb;
    private final /* synthetic */ JSONObject zzgg;
    private final /* synthetic */ int zzgj;
    private final /* synthetic */ MediaQueueItem[] zzgk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaz(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, int i, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzgb = remoteMediaPlayer;
        this.zzgk = mediaQueueItemArr;
        this.zzgj = i;
        this.zzgg = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzct zzct) throws zzds {
        this.zzgb.zzfu.zza(this.zzgz, this.zzgk, this.zzgj, 0, -1, -1, this.zzgg);
    }
}
