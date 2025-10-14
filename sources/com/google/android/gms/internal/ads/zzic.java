package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzic extends zzbfc<zzic> {
    private static volatile zzic[] zzame;
    private Integer zzamf = null;
    private zziq zzamg = null;

    public zzic() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzf */
    public final zzic zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    int position = zzbez.getPosition();
                    try {
                        int zzacc = zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 10) {
                            throw new IllegalArgumentException(new StringBuilder(44).append(zzacc).append(" is not a valid enum AdFormatType").toString());
                        }
                        this.zzamf = Integer.valueOf(zzacc);
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 18:
                    if (this.zzamg == null) {
                        this.zzamg = new zziq();
                    }
                    zzbez.zza(this.zzamg);
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

    public static zzic[] zzhr() {
        if (zzame == null) {
            synchronized (zzbfg.zzebs) {
                if (zzame == null) {
                    zzame = new zzic[0];
                }
            }
        }
        return zzame;
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzamf != null) {
            zzbfa.zzm(1, this.zzamf.intValue());
        }
        if (this.zzamg != null) {
            zzbfa.zza(2, (zzbfi) this.zzamg);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        if (this.zzamf != null) {
            zzr += zzbfa.zzq(1, this.zzamf.intValue());
        }
        return this.zzamg != null ? zzr + zzbfa.zzb(2, (zzbfi) this.zzamg) : zzr;
    }
}
