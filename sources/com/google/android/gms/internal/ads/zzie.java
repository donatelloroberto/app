package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzie extends zzbfc<zzie> {
    private String zzamj = null;
    private zzic[] zzamk = zzic.zzhr();
    private Integer zzaml = null;
    private Integer zzamm = null;
    private Integer zzamn = null;

    public zzie() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzh */
    public final zzie zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzamj = zzbez.readString();
                    continue;
                case 18:
                    int zzb = zzbfl.zzb(zzbez, 18);
                    int length = this.zzamk == null ? 0 : this.zzamk.length;
                    zzic[] zzicArr = new zzic[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzamk, 0, zzicArr, 0, length);
                    }
                    while (length < zzicArr.length - 1) {
                        zzicArr[length] = new zzic();
                        zzbez.zza(zzicArr[length]);
                        zzbez.zzabk();
                        length++;
                    }
                    zzicArr[length] = new zzic();
                    zzbez.zza(zzicArr[length]);
                    this.zzamk = zzicArr;
                    continue;
                case 24:
                    int position = zzbez.getPosition();
                    try {
                        this.zzaml = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 32:
                    int position2 = zzbez.getPosition();
                    try {
                        this.zzamm = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e2) {
                        zzbez.zzdc(position2);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 40:
                    int position3 = zzbez.getPosition();
                    try {
                        this.zzamn = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
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
        if (this.zzamj != null) {
            zzbfa.zzf(1, this.zzamj);
        }
        if (this.zzamk != null && this.zzamk.length > 0) {
            for (zzic zzic : this.zzamk) {
                if (zzic != null) {
                    zzbfa.zza(2, (zzbfi) zzic);
                }
            }
        }
        if (this.zzaml != null) {
            zzbfa.zzm(3, this.zzaml.intValue());
        }
        if (this.zzamm != null) {
            zzbfa.zzm(4, this.zzamm.intValue());
        }
        if (this.zzamn != null) {
            zzbfa.zzm(5, this.zzamn.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        if (this.zzamj != null) {
            zzr += zzbfa.zzg(1, this.zzamj);
        }
        if (this.zzamk != null && this.zzamk.length > 0) {
            int i = zzr;
            for (zzic zzic : this.zzamk) {
                if (zzic != null) {
                    i += zzbfa.zzb(2, (zzbfi) zzic);
                }
            }
            zzr = i;
        }
        if (this.zzaml != null) {
            zzr += zzbfa.zzq(3, this.zzaml.intValue());
        }
        if (this.zzamm != null) {
            zzr += zzbfa.zzq(4, this.zzamm.intValue());
        }
        return this.zzamn != null ? zzr + zzbfa.zzq(5, this.zzamn.intValue()) : zzr;
    }
}
