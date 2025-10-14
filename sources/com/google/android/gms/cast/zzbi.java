package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzds;
import java.util.Locale;
import org.json.JSONObject;

final class zzbi extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzgb;
    private final /* synthetic */ JSONObject zzgg;
    private final /* synthetic */ int zzgr;
    private final /* synthetic */ int zzgs;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbi(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int i, int i2, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzgb = remoteMediaPlayer;
        this.zzgr = i;
        this.zzgs = i2;
        this.zzgg = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzct zzct) throws zzds {
        int i;
        int zza = this.zzgb.zzf(this.zzgr);
        if (zza == -1) {
            setResult((RemoteMediaPlayer.MediaChannelResult) createFailedResult(new Status(0)));
        } else if (this.zzgs < 0) {
            setResult((RemoteMediaPlayer.MediaChannelResult) createFailedResult(new Status(CastStatusCodes.INVALID_REQUEST, String.format(Locale.ROOT, "Invalid request: Invalid newIndex %d.", new Object[]{Integer.valueOf(this.zzgs)}))));
        } else if (zza == this.zzgs) {
            setResult((RemoteMediaPlayer.MediaChannelResult) createFailedResult(new Status(0)));
        } else {
            MediaQueueItem queueItem = this.zzgb.getMediaStatus().getQueueItem(this.zzgs > zza ? this.zzgs + 1 : this.zzgs);
            if (queueItem != null) {
                i = queueItem.getItemId();
            } else {
                i = 0;
            }
            this.zzgb.zzfu.zza(this.zzgz, new int[]{this.zzgr}, i, this.zzgg);
        }
    }
}
