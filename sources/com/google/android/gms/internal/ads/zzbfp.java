package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfp extends zzbfc<zzbfp> {
    private zzbfq zzecz = null;
    public zzbfo[] zzeda = zzbfo.zzagt();
    private byte[] zzedb = null;
    private byte[] zzedc = null;
    private Integer zzedd = null;

    public zzbfp() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    if (this.zzecz == null) {
                        this.zzecz = new zzbfq();
                    }
                    zzbez.zza(this.zzecz);
                    continue;
                case 18:
                    int zzb = zzbfl.zzb(zzbez, 18);
                    int length = this.zzeda == null ? 0 : this.zzeda.length;
                    zzbfo[] zzbfoArr = new zzbfo[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzeda, 0, zzbfoArr, 0, length);
                    }
                    while (length < zzbfoArr.length - 1) {
                        zzbfoArr[length] = new zzbfo();
                        zzbez.zza(zzbfoArr[length]);
                        zzbez.zzabk();
                        length++;
                    }
                    zzbfoArr[length] = new zzbfo();
                    zzbez.zza(zzbfoArr[length]);
                    this.zzeda = zzbfoArr;
                    continue;
                case 26:
                    this.zzedb = zzbez.readBytes();
                    continue;
                case 34:
                    this.zzedc = zzbez.readBytes();
                    continue;
                case 40:
                    this.zzedd = Integer.valueOf(zzbez.zzabn());
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
        if (this.zzecz != null) {
            zzbfa.zza(1, (zzbfi) this.zzecz);
        }
        if (this.zzeda != null && this.zzeda.length > 0) {
            for (zzbfo zzbfo : this.zzeda) {
                if (zzbfo != null) {
                    zzbfa.zza(2, (zzbfi) zzbfo);
                }
            }
        }
        if (this.zzedb != null) {
            zzbfa.zza(3, this.zzedb);
        }
        if (this.zzedc != null) {
            zzbfa.zza(4, this.zzedc);
        }
        if (this.zzedd != null) {
            zzbfa.zzm(5, this.zzedd.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        if (this.zzecz != null) {
            zzr += zzbfa.zzb(1, (zzbfi) this.zzecz);
        }
        if (this.zzeda != null && this.zzeda.length > 0) {
            int i = zzr;
            for (zzbfo zzbfo : this.zzeda) {
                if (zzbfo != null) {
                    i += zzbfa.zzb(2, (zzbfi) zzbfo);
                }
            }
            zzr = i;
        }
        if (this.zzedb != null) {
            zzr += zzbfa.zzb(3, this.zzedb);
        }
        if (this.zzedc != null) {
            zzr += zzbfa.zzb(4, this.zzedc);
        }
        return this.zzedd != null ? zzr + zzbfa.zzq(5, this.zzedd.intValue()) : zzr;
    }
}
