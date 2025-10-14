package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Status;
import java.util.Locale;

final class zzcb implements zzdu {
    private final /* synthetic */ zzbs zzxc;
    private final /* synthetic */ zzcc zzxe;

    zzcb(zzcc zzcc, zzbs zzbs) {
        this.zzxe = zzcc;
        this.zzxc = zzbs;
    }

    public final void zzb(long j) {
        this.zzxe.setResult(zzcc.zzc(new Status(2103)));
    }

    public final void zza(long j, int i, Object obj) {
        if (obj == null) {
            try {
                this.zzxe.setResult(new zzce(new Status(i, (String) null, (PendingIntent) null), this.zzxe.zzxf));
            } catch (ClassCastException e) {
                this.zzxe.setResult(zzcc.zzc(new Status(13)));
            }
        } else {
            zzcf zzcf = (zzcf) obj;
            zzcg zzcg = zzcf.zzxv;
            if (zzcg == null || zzdc.zza("1.0.0", zzcg.getVersion())) {
                this.zzxe.setResult(new zzce(new Status(i, zzcf.zzxl, (PendingIntent) null), this.zzxe.zzxf));
                return;
            }
            zzcg unused = this.zzxe.zzww.zzwl = null;
            this.zzxe.setResult(zzcc.zzc(new Status(GameManagerClient.STATUS_INCORRECT_VERSION, String.format(Locale.ROOT, "Incorrect Game Manager SDK version. Receiver: %s Sender: %s", new Object[]{zzcg.getVersion(), "1.0.0"}))));
        }
    }
}
