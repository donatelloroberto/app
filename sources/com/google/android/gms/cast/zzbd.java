package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzds;
import org.json.JSONObject;

final class zzbd extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzgb;
    private final /* synthetic */ JSONObject zzgg;
    private final /* synthetic */ int zzgj;
    private final /* synthetic */ int[] zzgn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbd(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int[] iArr, int i, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzgb = remoteMediaPlayer;
        this.zzgn = iArr;
        this.zzgj = i;
        this.zzgg = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzct zzct) throws zzds {
        this.zzgb.zzfu.zza(this.zzgz, this.zzgn, this.zzgj, this.zzgg);
    }
}
