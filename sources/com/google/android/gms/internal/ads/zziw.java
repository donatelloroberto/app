package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zziw extends zzbfc<zziw> {
    private Integer zzapp = null;

    public zziw() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzt */
    public final zziw zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    int position = zzbez.getPosition();
                    try {
                        int zzacc = zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 3) {
                            throw new IllegalArgumentException(new StringBuilder(46).append(zzacc).append(" is not a valid enum VideoErrorCode").toString());
                        }
                        this.zzapp = Integer.valueOf(zzacc);
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                default:
                    if (!super.zza(zzbez, zzabk)) {
                        break;
                    } else {
                        continue;
                    }
            }
        }
        return this;
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzapp != null) {
            zzbfa.zzm(1, this.zzapp.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        return this.zzapp != null ? zzr + zzbfa.zzq(1, this.zzapp.intValue()) : zzr;
    }
}
