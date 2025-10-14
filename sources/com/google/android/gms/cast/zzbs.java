package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

final class zzbs implements RemoteMediaPlayer.MediaChannelResult {
    private final /* synthetic */ Status zzam;

    zzbs(RemoteMediaPlayer.zzb zzb, Status status) {
        this.zzam = status;
    }

    public final Status getStatus() {
        return this.zzam;
    }

    public final JSONObject getCustomData() {
        return null;
    }
}
