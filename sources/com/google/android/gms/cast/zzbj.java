package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzds;
import org.json.JSONObject;

final class zzbj extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzgb;
    private final /* synthetic */ long zzgf;
    private final /* synthetic */ JSONObject zzgg;
    private final /* synthetic */ int zzgr;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbj(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int i, long j, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzgb = remoteMediaPlayer;
        this.zzgr = i;
        this.zzgf = j;
        this.zzgg = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzct zzct) throws zzds {
        if (this.zzgb.zzf(this.zzgr) == -1) {
            setResult((RemoteMediaPlayer.MediaChannelResult) createFailedResult(new Status(0)));
        } else {
            this.zzgb.zzfu.zza(this.zzgz, this.zzgr, this.zzgf, (MediaQueueItem[]) null, 0, (Integer) null, this.zzgg);
        }
    }
}
