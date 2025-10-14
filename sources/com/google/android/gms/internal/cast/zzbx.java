package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;
import org.json.JSONObject;

@VisibleForTesting
public abstract class zzbx extends zzbz<GameManagerClient.GameManagerResult> {
    final /* synthetic */ zzbs zzww;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbx(zzbs zzbs) {
        super(zzbs);
        this.zzww = zzbs;
        this.zzxb = new zzca(this, zzbs);
    }

    public static GameManagerClient.GameManagerResult zzb(Status status) {
        return new zzcd(status, (String) null, -1, (JSONObject) null);
    }

    public /* synthetic */ Result createFailedResult(Status status) {
        return zzb(status);
    }
}
