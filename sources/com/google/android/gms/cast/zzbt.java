package com.google.android.gms.cast;

import android.util.Log;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzdu;
import org.json.JSONObject;

final class zzbt implements zzdu {
    private final /* synthetic */ RemoteMediaPlayer zzhd;
    private final /* synthetic */ RemoteMediaPlayer.zzb zzhe;

    zzbt(RemoteMediaPlayer.zzb zzb, RemoteMediaPlayer remoteMediaPlayer) {
        this.zzhe = zzb;
        this.zzhd = remoteMediaPlayer;
    }

    public final void zzb(long j) {
        try {
            this.zzhe.setResult((RemoteMediaPlayer.MediaChannelResult) this.zzhe.createFailedResult(new Status(2103)));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaPlayer", "Result already set when calling onRequestReplaced", e);
        }
    }

    public final void zza(long j, int i, Object obj) {
        JSONObject jSONObject;
        if (obj instanceof JSONObject) {
            jSONObject = (JSONObject) obj;
        } else {
            jSONObject = null;
        }
        try {
            this.zzhe.setResult(new RemoteMediaPlayer.zzc(new Status(i), jSONObject));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaPlayer", "Result already set when calling onRequestCompleted", e);
        }
    }
}
