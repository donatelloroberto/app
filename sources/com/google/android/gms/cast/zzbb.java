package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzds;
import org.json.JSONObject;

final class zzbb extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzgb;
    private final /* synthetic */ JSONObject zzgg;
    private final /* synthetic */ MediaQueueItem[] zzgm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbb(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzgb = remoteMediaPlayer;
        this.zzgm = mediaQueueItemArr;
        this.zzgg = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzct zzct) throws zzds {
        this.zzgb.zzfu.zza(this.zzgz, 0, -1, this.zzgm, 0, (Integer) null, this.zzgg);
    }
}
