package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzif extends zzbfc<zzif> {
    private Integer zzamo = null;
    private zzis zzamp = null;
    private zzis zzamq = null;
    private zzis zzamr = null;
    private zzis[] zzams = zzis.zzht();
    private Integer zzamt = null;

    public zzif() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    this.zzamo = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 18:
                    if (this.zzamp == null) {
                        this.zzamp = new zzis();
                    }
                    zzbez.zza(this.zzamp);
                    continue;
                case 26:
                    if (this.zzamq == null) {
                        this.zzamq = new zzis();
                    }
                    zzbez.zza(this.zzamq);
                    continue;
                case 34:
                    if (this.zzamr == null) {
                        this.zzamr = new zzis();
                    }
                    zzbez.zza(this.zzamr);
                    continue;
                case 42:
                    int zzb = zzbfl.zzb(zzbez, 42);
                    int length = this.zzams == null ? 0 : this.zzams.length;
                    zzis[] zzisArr = new zzis[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzams, 0, zzisArr, 0, length);
                    }
                    while (length < zzisArr.length - 1) {
                        zzisArr[length] = new zzis();
                        zzbez.zza(zzisArr[length]);
                        zzbez.zzabk();
                        length++;
                    }
                    zzisArr[length] = new zzis();
                    zzbez.zza(zzisArr[length]);
                    this.zzams = zzisArr;
                    continue;
                case 48:
                    this.zzamt = Integer.valueOf(zzbez.zzacc());
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
        if (this.zzamo != null) {
            zzbfa.zzm(1, this.zzamo.intValue());
        }
        if (this.zzamp != null) {
            zzbfa.zza(2, (zzbfi) this.zzamp);
        }
        if (this.zzamq != null) {
            zzbfa.zza(3, (zzbfi) this.zzamq);
        }
        if (this.zzamr != null) {
            zzbfa.zza(4, (zzbfi) this.zzamr);
        }
        if (this.zzams != null && this.zzams.length > 0) {
            for (zzis zzis : this.zzams) {
                if (zzis != null) {
                    zzbfa.zza(5, (zzbfi) zzis);
                }
            }
        }
        if (this.zzamt != null) {
            zzbfa.zzm(6, this.zzamt.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        if (this.zzamo != null) {
            zzr += zzbfa.zzq(1, this.zzamo.intValue());
        }
        if (this.zzamp != null) {
            zzr += zzbfa.zzb(2, (zzbfi) this.zzamp);
        }
        if (this.zzamq != null) {
            zzr += zzbfa.zzb(3, (zzbfi) this.zzamq);
        }
        if (this.zzamr != null) {
            zzr += zzbfa.zzb(4, (zzbfi) this.zzamr);
        }
        if (this.zzams != null && this.zzams.length > 0) {
            int i = zzr;
            for (zzis zzis : this.zzams) {
                if (zzis != null) {
                    i += zzbfa.zzb(5, (zzbfi) zzis);
                }
            }
            zzr = i;
        }
        return this.zzamt != null ? zzr + zzbfa.zzq(6, this.zzamt.intValue()) : zzr;
    }
}
