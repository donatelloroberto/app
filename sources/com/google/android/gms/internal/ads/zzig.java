package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzig extends zzbfc<zzig> {
    public String zzamu = null;
    private zzis zzamv = null;
    private Integer zzamw = null;
    public zzit zzamx = null;
    private Integer zzamy = null;
    private Integer zzamz = null;
    private Integer zzana = null;
    private Integer zzanb = null;

    public zzig() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzi */
    public final zzig zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzamu = zzbez.readString();
                    continue;
                case 18:
                    if (this.zzamv == null) {
                        this.zzamv = new zzis();
                    }
                    zzbez.zza(this.zzamv);
                    continue;
                case 24:
                    this.zzamw = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 34:
                    if (this.zzamx == null) {
                        this.zzamx = new zzit();
                    }
                    zzbez.zza(this.zzamx);
                    continue;
                case 40:
                    this.zzamy = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 48:
                    int position = zzbez.getPosition();
                    try {
                        this.zzamz = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 56:
                    int position2 = zzbez.getPosition();
                    try {
                        this.zzana = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e2) {
                        zzbez.zzdc(position2);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 64:
                    int position3 = zzbez.getPosition();
                    try {
                        this.zzanb = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e3) {
                        zzbez.zzdc(position3);
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
        if (this.zzamu != null) {
            zzbfa.zzf(1, this.zzamu);
        }
        if (this.zzamv != null) {
            zzbfa.zza(2, (zzbfi) this.zzamv);
        }
        if (this.zzamw != null) {
            zzbfa.zzm(3, this.zzamw.intValue());
        }
        if (this.zzamx != null) {
            zzbfa.zza(4, (zzbfi) this.zzamx);
        }
        if (this.zzamy != null) {
            zzbfa.zzm(5, this.zzamy.intValue());
        }
        if (this.zzamz != null) {
            zzbfa.zzm(6, this.zzamz.intValue());
        }
        if (this.zzana != null) {
            zzbfa.zzm(7, this.zzana.intValue());
        }
        if (this.zzanb != null) {
            zzbfa.zzm(8, this.zzanb.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        if (this.zzamu != null) {
            zzr += zzbfa.zzg(1, this.zzamu);
        }
        if (this.zzamv != null) {
            zzr += zzbfa.zzb(2, (zzbfi) this.zzamv);
        }
        if (this.zzamw != null) {
            zzr += zzbfa.zzq(3, this.zzamw.intValue());
        }
        if (this.zzamx != null) {
            zzr += zzbfa.zzb(4, (zzbfi) this.zzamx);
        }
        if (this.zzamy != null) {
            zzr += zzbfa.zzq(5, this.zzamy.intValue());
        }
        if (this.zzamz != null) {
            zzr += zzbfa.zzq(6, this.zzamz.intValue());
        }
        if (this.zzana != null) {
            zzr += zzbfa.zzq(7, this.zzana.intValue());
        }
        return this.zzanb != null ? zzr + zzbfa.zzq(8, this.zzanb.intValue()) : zzr;
    }
}
