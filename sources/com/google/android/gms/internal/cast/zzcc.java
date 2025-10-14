package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public abstract class zzcc extends zzbz<GameManagerClient.GameManagerInstanceResult> {
    final /* synthetic */ zzbs zzww;
    /* access modifiers changed from: private */
    public GameManagerClient zzxf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzcc(zzbs zzbs, GameManagerClient gameManagerClient) {
        super(zzbs);
        this.zzww = zzbs;
        this.zzxf = gameManagerClient;
        this.zzxb = new zzcb(this, zzbs);
    }

    public static GameManagerClient.GameManagerInstanceResult zzc(Status status) {
        return new zzce(status, (GameManagerClient) null);
    }

    public /* synthetic */ Result createFailedResult(Status status) {
        return zzc(status);
    }
}
