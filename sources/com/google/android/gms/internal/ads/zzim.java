package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzim extends zzbfc<zzim> {
    private Integer zzamf = null;
    private Integer zzanx = null;

    public zzim() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzo */
    public final zzim zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    int position = zzbez.getPosition();
                    try {
                        int zzacc = zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 2) {
                            throw new IllegalArgumentException(new StringBuilder(43).append(zzacc).append(" is not a valid enum NetworkType").toString());
                        }
                        this.zzamf = Integer.valueOf(zzacc);
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 16:
                    int position2 = zzbez.getPosition();
                    try {
                        int zzacc2 = zzbez.zzacc();
                        if ((zzacc2 < 0 || zzacc2 > 2) && (zzacc2 < 4 || zzacc2 > 4)) {
                            throw new IllegalArgumentException(new StringBuilder(51).append(zzacc2).append(" is not a valid enum CellularNetworkType").toString());
                        }
                        this.zzanx = Integer.valueOf(zzacc2);
                        continue;
                    } catch (IllegalArgumentException e2) {
                        zzbez.zzdc(position2);
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
        if (this.zzamf != null) {
            zzbfa.zzm(1, this.zzamf.intValue());
        }
        if (this.zzanx != null) {
            zzbfa.zzm(2, this.zzanx.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        if (this.zzamf != null) {
            zzr += zzbfa.zzq(1, this.zzamf.intValue());
        }
        return this.zzanx != null ? zzr + zzbfa.zzq(2, this.zzanx.intValue()) : zzr;
    }
}
