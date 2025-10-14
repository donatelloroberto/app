package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzjb extends zzbfc<zzjb> {
    private Integer zzanu = null;
    private zziw zzapn = null;
    private zzis zzapo = null;

    public zzjb() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzy */
    public final zzjb zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    int position = zzbez.getPosition();
                    try {
                        this.zzanu = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 18:
                    if (this.zzapn == null) {
                        this.zzapn = new zziw();
                    }
                    zzbez.zza(this.zzapn);
                    continue;
                case 26:
                    if (this.zzapo == null) {
                        this.zzapo = new zzis();
                    }
                    zzbez.zza(this.zzapo);
                    continue;
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
        if (this.zzanu != null) {
            zzbfa.zzm(1, this.zzanu.intValue());
        }
        if (this.zzapn != null) {
            zzbfa.zza(2, (zzbfi) this.zzapn);
        }
        if (this.zzapo != null) {
            zzbfa.zza(3, (zzbfi) this.zzapo);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        if (this.zzanu != null) {
            zzr += zzbfa.zzq(1, this.zzanu.intValue());
        }
        if (this.zzapn != null) {
            zzr += zzbfa.zzb(2, (zzbfi) this.zzapn);
        }
        return this.zzapo != null ? zzr + zzbfa.zzb(3, (zzbfi) this.zzapo) : zzr;
    }
}
