package com.google.android.gms.cast;

import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzds;
import org.json.JSONObject;

final class zzbm extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzgb;
    private final /* synthetic */ JSONObject zzgg;
    private final /* synthetic */ long zzgt;
    private final /* synthetic */ int zzgu;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbm(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, long j, int i, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzgb = remoteMediaPlayer;
        this.zzgt = j;
        this.zzgu = i;
        this.zzgg = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzct zzct) throws zzds {
        this.zzgb.zzfu.zza(this.zzgz, new MediaSeekOptions.Builder().setPosition(this.zzgt).setResumeState(this.zzgu).setCustomData(this.zzgg).build());
    }
}
