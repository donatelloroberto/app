package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbft extends zzbfc<zzbft> {
    public String mimeType = null;
    public Integer zzamf = null;
    public byte[] zzedl = null;

    public zzbft() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzab */
    public final zzbft zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    int position = zzbez.getPosition();
                    try {
                        int zzabn = zzbez.zzabn();
                        if (zzabn < 0 || zzabn > 1) {
                            throw new IllegalArgumentException(new StringBuilder(36).append(zzabn).append(" is not a valid enum Type").toString());
                        }
                        this.zzamf = Integer.valueOf(zzabn);
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 18:
                    this.mimeType = zzbez.readString();
                    continue;
                case 26:
                    this.zzedl = zzbez.readBytes();
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
        if (this.zzamf != null) {
            zzbfa.zzm(1, this.zzamf.intValue());
        }
        if (this.mimeType != null) {
            zzbfa.zzf(2, this.mimeType);
        }
        if (this.zzedl != null) {
            zzbfa.zza(3, this.zzedl);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        if (this.zzamf != null) {
            zzr += zzbfa.zzq(1, this.zzamf.intValue());
        }
        if (this.mimeType != null) {
            zzr += zzbfa.zzg(2, this.mimeType);
        }
        return this.zzedl != null ? zzr + zzbfa.zzb(3, this.zzedl) : zzr;
    }
}
