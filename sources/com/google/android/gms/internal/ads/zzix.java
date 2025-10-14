package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzix extends zzbfc<zzix> {
    private Integer zzanu = null;
    private zziw zzapn = null;
    private zzis zzapo = null;
    private zzit zzapq = null;

    public zzix() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzu */
    public final zzix zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    if (this.zzapq == null) {
                        this.zzapq = new zzit();
                    }
                    zzbez.zza(this.zzapq);
                    continue;
                case 16:
                    int position = zzbez.getPosition();
                    try {
                        this.zzanu = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 26:
                    if (this.zzapn == null) {
                        this.zzapn = new zziw();
                    }
                    zzbez.zza(this.zzapn);
                    continue;
                case 34:
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
        if (this.zzapq != null) {
            zzbfa.zza(1, (zzbfi) this.zzapq);
        }
        if (this.zzanu != null) {
            zzbfa.zzm(2, this.zzanu.intValue());
        }
        if (this.zzapn != null) {
            zzbfa.zza(3, (zzbfi) this.zzapn);
        }
        if (this.zzapo != null) {
            zzbfa.zza(4, (zzbfi) this.zzapo);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        if (this.zzapq != null) {
            zzr += zzbfa.zzb(1, (zzbfi) this.zzapq);
        }
        if (this.zzanu != null) {
            zzr += zzbfa.zzq(2, this.zzanu.intValue());
        }
        if (this.zzapn != null) {
            zzr += zzbfa.zzb(3, (zzbfi) this.zzapn);
        }
        return this.zzapo != null ? zzr + zzbfa.zzb(4, (zzbfi) this.zzapo) : zzr;
    }
}
