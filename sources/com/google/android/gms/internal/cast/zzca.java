package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

final class zzca implements zzdu {
    private final /* synthetic */ zzbs zzxc;
    private final /* synthetic */ zzbx zzxd;

    zzca(zzbx zzbx, zzbs zzbs) {
        this.zzxd = zzbx;
        this.zzxc = zzbs;
    }

    public final void zzb(long j) {
        this.zzxd.setResult(zzbx.zzb(new Status(2103)));
    }

    public final void zza(long j, int i, Object obj) {
        if (obj == null) {
            try {
                this.zzxd.setResult(new zzcd(new Status(i, (String) null, (PendingIntent) null), (String) null, j, (JSONObject) null));
            } catch (ClassCastException e) {
                this.zzxd.setResult(zzbx.zzb(new Status(13)));
            }
        } else {
            zzcf zzcf = (zzcf) obj;
            String str = zzcf.zzxs;
            if (i == 0 && str != null) {
                String unused = this.zzxd.zzww.zzwu = str;
            }
            this.zzxd.setResult(new zzcd(new Status(i, zzcf.zzxl, (PendingIntent) null), str, zzcf.zzxt, zzcf.zzxm));
        }
    }
}
