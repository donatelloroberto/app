package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzds;
import org.json.JSONObject;

final class zzay extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzgb;
    private final /* synthetic */ long zzgf;
    private final /* synthetic */ JSONObject zzgg;
    private final /* synthetic */ MediaQueueItem zzgi;
    private final /* synthetic */ int zzgj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzay(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, MediaQueueItem mediaQueueItem, int i, long j, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzgb = remoteMediaPlayer;
        this.zzgi = mediaQueueItem;
        this.zzgj = i;
        this.zzgf = j;
        this.zzgg = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzct zzct) throws zzds {
        this.zzgb.zzfu.zza(this.zzgz, new MediaQueueItem[]{this.zzgi}, this.zzgj, 0, 0, this.zzgf, this.zzgg);
    }
}
