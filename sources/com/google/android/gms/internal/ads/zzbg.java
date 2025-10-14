package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbg extends zzbfc<zzbg> {
    public Integer zzfe;
    private Integer zzff;
    public byte[] zzgq = null;
    public byte[][] zzgv = zzbfl.zzece;

    public zzbg() {
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzbg zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    int zzb = zzbfl.zzb(zzbez, 10);
                    int length = this.zzgv == null ? 0 : this.zzgv.length;
                    byte[][] bArr = new byte[(zzb + length)][];
                    if (length != 0) {
                        System.arraycopy(this.zzgv, 0, bArr, 0, length);
                    }
                    while (length < bArr.length - 1) {
                        bArr[length] = zzbez.readBytes();
                        zzbez.zzabk();
                        length++;
                    }
                    bArr[length] = zzbez.readBytes();
                    this.zzgv = bArr;
                    continue;
                case 18:
                    this.zzgq = zzbez.readBytes();
                    continue;
                case 24:
                    int position = zzbez.getPosition();
                    try {
                        this.zzff = Integer.valueOf(zzaz.zze(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 32:
                    int position2 = zzbez.getPosition();
                    try {
                        this.zzfe = Integer.valueOf(zzaz.zzf(zzbez.zzacc()));
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
        if (this.zzgv != null && this.zzgv.length > 0) {
            for (byte[] bArr : this.zzgv) {
                if (bArr != null) {
                    zzbfa.zza(1, bArr);
                }
            }
        }
        if (this.zzgq != null) {
            zzbfa.zza(2, this.zzgq);
        }
        if (this.zzff != null) {
            zzbfa.zzm(3, this.zzff.intValue());
        }
        if (this.zzfe != null) {
            zzbfa.zzm(4, this.zzfe.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int i;
        int i2;
        int zzr = super.zzr();
        if (this.zzgv == null || this.zzgv.length <= 0) {
            i = zzr;
        } else {
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (i3 < this.zzgv.length) {
                byte[] bArr = this.zzgv[i3];
                if (bArr != null) {
                    i5++;
                    i2 = zzbfa.zzv(bArr) + i4;
                } else {
                    i2 = i4;
                }
                i3++;
                i4 = i2;
            }
            i = zzr + i4 + (i5 * 1);
        }
        if (this.zzgq != null) {
            i += zzbfa.zzb(2, this.zzgq);
        }
        if (this.zzff != null) {
            i += zzbfa.zzq(3, this.zzff.intValue());
        }
        return this.zzfe != null ? i + zzbfa.zzq(4, this.zzfe.intValue()) : i;
    }
}
