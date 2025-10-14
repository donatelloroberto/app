package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzio extends zzbfc<zzio> {
    private Integer zzaoa = null;
    private Integer zzaob = null;
    private Integer zzaoc = null;
    private Integer zzaod = null;
    private Integer zzaoe = null;
    private Integer zzaof = null;
    private Integer zzaog = null;
    private Integer zzaoh = null;
    private Integer zzaoi = null;
    private Integer zzaoj = null;
    private zzip zzaok = null;

    public zzio() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzq */
    public final zzio zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    int position = zzbez.getPosition();
                    try {
                        this.zzaoa = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 16:
                    int position2 = zzbez.getPosition();
                    try {
                        this.zzaob = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e2) {
                        zzbez.zzdc(position2);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 24:
                    this.zzaoc = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 32:
                    this.zzaod = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 40:
                    this.zzaoe = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 48:
                    this.zzaof = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 56:
                    this.zzaog = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 64:
                    this.zzaoh = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 72:
                    this.zzaoi = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 80:
                    this.zzaoj = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 90:
                    if (this.zzaok == null) {
                        this.zzaok = new zzip();
                    }
                    zzbez.zza(this.zzaok);
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
        if (this.zzaoa != null) {
            zzbfa.zzm(1, this.zzaoa.intValue());
        }
        if (this.zzaob != null) {
            zzbfa.zzm(2, this.zzaob.intValue());
        }
        if (this.zzaoc != null) {
            zzbfa.zzm(3, this.zzaoc.intValue());
        }
        if (this.zzaod != null) {
            zzbfa.zzm(4, this.zzaod.intValue());
        }
        if (this.zzaoe != null) {
            zzbfa.zzm(5, this.zzaoe.intValue());
        }
        if (this.zzaof != null) {
            zzbfa.zzm(6, this.zzaof.intValue());
        }
        if (this.zzaog != null) {
            zzbfa.zzm(7, this.zzaog.intValue());
        }
        if (this.zzaoh != null) {
            zzbfa.zzm(8, this.zzaoh.intValue());
        }
        if (this.zzaoi != null) {
            zzbfa.zzm(9, this.zzaoi.intValue());
        }
        if (this.zzaoj != null) {
            zzbfa.zzm(10, this.zzaoj.intValue());
        }
        if (this.zzaok != null) {
            zzbfa.zza(11, (zzbfi) this.zzaok);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        if (this.zzaoa != null) {
            zzr += zzbfa.zzq(1, this.zzaoa.intValue());
        }
        if (this.zzaob != null) {
            zzr += zzbfa.zzq(2, this.zzaob.intValue());
        }
        if (this.zzaoc != null) {
            zzr += zzbfa.zzq(3, this.zzaoc.intValue());
        }
        if (this.zzaod != null) {
            zzr += zzbfa.zzq(4, this.zzaod.intValue());
        }
        if (this.zzaoe != null) {
            zzr += zzbfa.zzq(5, this.zzaoe.intValue());
        }
        if (this.zzaof != null) {
            zzr += zzbfa.zzq(6, this.zzaof.intValue());
        }
        if (this.zzaog != null) {
            zzr += zzbfa.zzq(7, this.zzaog.intValue());
        }
        if (this.zzaoh != null) {
            zzr += zzbfa.zzq(8, this.zzaoh.intValue());
        }
        if (this.zzaoi != null) {
            zzr += zzbfa.zzq(9, this.zzaoi.intValue());
        }
        if (this.zzaoj != null) {
            zzr += zzbfa.zzq(10, this.zzaoj.intValue());
        }
        return this.zzaok != null ? zzr + zzbfa.zzb(11, (zzbfi) this.zzaok) : zzr;
    }
}
