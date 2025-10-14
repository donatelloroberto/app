package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzct;
import org.json.JSONObject;

final class zzaw extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzgb;
    private final /* synthetic */ MediaQueueItem[] zzgc;
    private final /* synthetic */ int zzgd;
    private final /* synthetic */ int zzge;
    private final /* synthetic */ long zzgf;
    private final /* synthetic */ JSONObject zzgg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaw(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzgb = remoteMediaPlayer;
        this.zzgc = mediaQueueItemArr;
        this.zzgd = i;
        this.zzge = i2;
        this.zzgf = j;
        this.zzgg = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzct zzct) {
        this.zzgb.zzfu.zza(this.zzgz, this.zzgc, this.zzgd, this.zzge, this.zzgf, this.zzgg);
    }
}
