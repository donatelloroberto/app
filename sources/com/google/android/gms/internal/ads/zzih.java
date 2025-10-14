package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzih extends zzbfc<zzih> {
    private Integer zzanc = null;
    private zzit zzand = null;
    private String zzane = null;
    private String zzanf = null;

    public zzih() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzj */
    public final zzih zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 40:
                    int position = zzbez.getPosition();
                    try {
                        int zzacc = zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 2) {
                            throw new IllegalArgumentException(new StringBuilder(40).append(zzacc).append(" is not a valid enum Platform").toString());
                        }
                        this.zzanc = Integer.valueOf(zzacc);
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 50:
                    if (this.zzand == null) {
                        this.zzand = new zzit();
                    }
                    zzbez.zza(this.zzand);
                    continue;
                case 58:
                    this.zzane = zzbez.readString();
                    continue;
                case 66:
                    this.zzanf = zzbez.readString();
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
        if (this.zzanc != null) {
            zzbfa.zzm(5, this.zzanc.intValue());
        }
        if (this.zzand != null) {
            zzbfa.zza(6, (zzbfi) this.zzand);
        }
        if (this.zzane != null) {
            zzbfa.zzf(7, this.zzane);
        }
        if (this.zzanf != null) {
            zzbfa.zzf(8, this.zzanf);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        if (this.zzanc != null) {
            zzr += zzbfa.zzq(5, this.zzanc.intValue());
        }
        if (this.zzand != null) {
            zzr += zzbfa.zzb(6, (zzbfi) this.zzand);
        }
        if (this.zzane != null) {
            zzr += zzbfa.zzg(7, this.zzane);
        }
        return this.zzanf != null ? zzr + zzbfa.zzg(8, this.zzanf) : zzr;
    }
}
