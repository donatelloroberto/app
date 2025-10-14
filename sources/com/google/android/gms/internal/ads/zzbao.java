package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.charset.Charset;

class zzbao extends zzban {
    protected final byte[] zzdpw;

    zzbao(byte[] bArr) {
        this.zzdpw = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbah)) {
            return false;
        }
        if (size() != ((zzbah) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzbao)) {
            return obj.equals(this);
        }
        int zzabg = zzabg();
        int zzabg2 = ((zzbao) obj).zzabg();
        if (zzabg == 0 || zzabg2 == 0 || zzabg == zzabg2) {
            return zza((zzbao) obj, 0, size());
        }
        return false;
    }

    public int size() {
        return this.zzdpw.length;
    }

    /* access modifiers changed from: protected */
    public final String zza(Charset charset) {
        return new String(this.zzdpw, zzabh(), size(), charset);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzbag zzbag) throws IOException {
        zzbag.zzb(this.zzdpw, zzabh(), size());
    }

    /* access modifiers changed from: protected */
    public void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzdpw, 0, bArr, 0, i3);
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzbah zzbah, int i, int i2) {
        if (i2 > zzbah.size()) {
            throw new IllegalArgumentException(new StringBuilder(40).append("Length too large: ").append(i2).append(size()).toString());
        } else if (i2 > zzbah.size()) {
            throw new IllegalArgumentException(new StringBuilder(59).append("Ran off end of other: 0, ").append(i2).append(", ").append(zzbah.size()).toString());
        } else if (!(zzbah instanceof zzbao)) {
            return zzbah.zzk(0, i2).equals(zzk(0, i2));
        } else {
            zzbao zzbao = (zzbao) zzbah;
            byte[] bArr = this.zzdpw;
            byte[] bArr2 = zzbao.zzdpw;
            int zzabh = zzabh() + i2;
            int zzabh2 = zzabh();
            int zzabh3 = zzbao.zzabh();
            while (zzabh2 < zzabh) {
                if (bArr[zzabh2] != bArr2[zzabh3]) {
                    return false;
                }
                zzabh2++;
                zzabh3++;
            }
            return true;
        }
    }

    public final boolean zzabe() {
        int zzabh = zzabh();
        return zzbem.zzf(this.zzdpw, zzabh, size() + zzabh);
    }

    public final zzbaq zzabf() {
        return zzbaq.zza(this.zzdpw, zzabh(), size(), true);
    }

    /* access modifiers changed from: protected */
    public int zzabh() {
        return 0;
    }

    public byte zzbn(int i) {
        return this.zzdpw[i];
    }

    /* access modifiers changed from: protected */
    public final int zzc(int i, int i2, int i3) {
        return zzbbq.zza(i, this.zzdpw, zzabh(), i3);
    }

    public final zzbah zzk(int i, int i2) {
        int zzd = zzd(0, i2, size());
        return zzd == 0 ? zzbah.zzdpq : new zzbak(this.zzdpw, zzabh(), zzd);
    }
}
