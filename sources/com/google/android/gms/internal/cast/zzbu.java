package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import java.io.IOException;
import org.json.JSONObject;

final class zzbu extends zzcc {
    final /* synthetic */ zzbs zzww;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbu(zzbs zzbs, GameManagerClient gameManagerClient) {
        super(zzbs, gameManagerClient);
        this.zzww = zzbs;
    }

    public final void execute() {
        try {
            this.zzww.zzio.setMessageReceivedCallbacks(this.zzww.zzpl, this.zzww.getNamespace(), new zzbt(this));
            this.zzww.zzdv();
            this.zzww.zzdu();
            this.zzww.zza((String) null, 1100, (JSONObject) null, this.zzxb);
        } catch (IOException | IllegalStateException e) {
            this.zzxb.zza(-1, 8, (Object) null);
        }
    }
}
