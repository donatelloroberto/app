package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbc extends zzbfc<zzbc> {
    private Long zzeq = null;
    private Long zzer = null;
    public Long zzgi = null;
    public Long zzgj = null;
    public Long zzgk = null;

    public zzbc() {
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    this.zzeq = Long.valueOf(zzbez.zzacd());
                    continue;
                case 16:
                    this.zzer = Long.valueOf(zzbez.zzacd());
                    continue;
                case 24:
                    this.zzgi = Long.valueOf(zzbez.zzacd());
                    continue;
                case 32:
                    this.zzgj = Long.valueOf(zzbez.zzacd());
                    continue;
                case 40:
                    this.zzgk = Long.valueOf(zzbez.zzacd());
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
        if (this.zzeq != null) {
            zzbfa.zzi(1, this.zzeq.longValue());
        }
        if (this.zzer != null) {
            zzbfa.zzi(2, this.zzer.longValue());
        }
        if (this.zzgi != null) {
            zzbfa.zzi(3, this.zzgi.longValue());
        }
        if (this.zzgj != null) {
            zzbfa.zzi(4, this.zzgj.longValue());
        }
        if (this.zzgk != null) {
            zzbfa.zzi(5, this.zzgk.longValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        if (this.zzeq != null) {
            zzr += zzbfa.zzd(1, this.zzeq.longValue());
        }
        if (this.zzer != null) {
            zzr += zzbfa.zzd(2, this.zzer.longValue());
        }
        if (this.zzgi != null) {
            zzr += zzbfa.zzd(3, this.zzgi.longValue());
        }
        if (this.zzgj != null) {
            zzr += zzbfa.zzd(4, this.zzgj.longValue());
        }
        return this.zzgk != null ? zzr + zzbfa.zzd(5, this.zzgk.longValue()) : zzr;
    }
}
