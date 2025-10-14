package com.google.android.gms.cast;

import com.google.android.gms.cast.MediaLoadRequestData;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzct;
import org.json.JSONObject;

final class zzbe extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzgb;
    private final /* synthetic */ long zzgf;
    private final /* synthetic */ JSONObject zzgg;
    private final /* synthetic */ MediaInfo zzgo;
    private final /* synthetic */ boolean zzgp;
    private final /* synthetic */ long[] zzgq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbe(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, MediaInfo mediaInfo, boolean z, long j, long[] jArr, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzgb = remoteMediaPlayer;
        this.zzgo = mediaInfo;
        this.zzgp = z;
        this.zzgf = j;
        this.zzgq = jArr;
        this.zzgg = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzct zzct) {
        synchronized (this.zzgb.lock) {
            this.zzgb.zzfu.zza(this.zzgz, new MediaLoadRequestData.Builder().setMediaInfo(this.zzgo).setAutoplay(Boolean.valueOf(this.zzgp)).setCurrentTime(this.zzgf).setActiveTrackIds(this.zzgq).setCustomData(this.zzgg).build());
        }
    }
}
