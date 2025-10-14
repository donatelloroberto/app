package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkt extends zzaca<zzkt> {
    public long[] zzauw = zzacj.zzbxw;
    public long[] zzaux = zzacj.zzbxw;

    public zzkt() {
        this.zzbxg = null;
        this.zzbxr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkt)) {
            return false;
        }
        zzkt zzkt = (zzkt) obj;
        if (!zzace.equals(this.zzauw, zzkt.zzauw)) {
            return false;
        }
        if (!zzace.equals(this.zzaux, zzkt.zzaux)) {
            return false;
        }
        return (this.zzbxg == null || this.zzbxg.isEmpty()) ? zzkt.zzbxg == null || zzkt.zzbxg.isEmpty() : this.zzbxg.equals(zzkt.zzbxg);
    }

    public final int hashCode() {
        return ((this.zzbxg == null || this.zzbxg.isEmpty()) ? 0 : this.zzbxg.hashCode()) + ((((((getClass().getName().hashCode() + 527) * 31) + zzace.hashCode(this.zzauw)) * 31) + zzace.hashCode(this.zzaux)) * 31);
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int i;
        int zza = super.zza();
        if (this.zzauw == null || this.zzauw.length <= 0) {
            i = zza;
        } else {
            int i2 = 0;
            for (long zzao : this.zzauw) {
                i2 += zzaby.zzao(zzao);
            }
            i = zza + i2 + (this.zzauw.length * 1);
        }
        if (this.zzaux == null || this.zzaux.length <= 0) {
            return i;
        }
        int i3 = 0;
        for (long zzao2 : this.zzaux) {
            i3 += zzaby.zzao(zzao2);
        }
        return i + i3 + (this.zzaux.length * 1);
    }

    public final void zza(zzaby zzaby) throws IOException {
        if (this.zzauw != null && this.zzauw.length > 0) {
            for (long zza : this.zzauw) {
                zzaby.zza(1, zza);
            }
        }
        if (this.zzaux != null && this.zzaux.length > 0) {
            for (long zza2 : this.zzaux) {
                zzaby.zza(2, zza2);
            }
        }
        super.zza(zzaby);
    }

    public final /* synthetic */ zzacg zzb(zzabx zzabx) throws IOException {
        while (true) {
            int zzvf = zzabx.zzvf();
            switch (zzvf) {
                case 0:
                    break;
                case 8:
                    int zzb = zzacj.zzb(zzabx, 8);
                    int length = this.zzauw == null ? 0 : this.zzauw.length;
                    long[] jArr = new long[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzauw, 0, jArr, 0, length);
                    }
                    while (length < jArr.length - 1) {
                        jArr[length] = zzabx.zzvi();
                        zzabx.zzvf();
                        length++;
                    }
                    jArr[length] = zzabx.zzvi();
                    this.zzauw = jArr;
                    continue;
                case 10:
                    int zzaf = zzabx.zzaf(zzabx.zzvh());
                    int position = zzabx.getPosition();
                    int i = 0;
                    while (zzabx.zzvl() > 0) {
                        zzabx.zzvi();
                        i++;
                    }
                    zzabx.zzam(position);
                    int length2 = this.zzauw == null ? 0 : this.zzauw.length;
                    long[] jArr2 = new long[(i + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzauw, 0, jArr2, 0, length2);
                    }
                    while (length2 < jArr2.length) {
                        jArr2[length2] = zzabx.zzvi();
                        length2++;
                    }
                    this.zzauw = jArr2;
                    zzabx.zzal(zzaf);
                    continue;
                case 16:
                    int zzb2 = zzacj.zzb(zzabx, 16);
                    int length3 = this.zzaux == null ? 0 : this.zzaux.length;
                    long[] jArr3 = new long[(zzb2 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzaux, 0, jArr3, 0, length3);
                    }
                    while (length3 < jArr3.length - 1) {
                        jArr3[length3] = zzabx.zzvi();
                        zzabx.zzvf();
                        length3++;
                    }
                    jArr3[length3] = zzabx.zzvi();
                    this.zzaux = jArr3;
                    continue;
                case 18:
                    int zzaf2 = zzabx.zzaf(zzabx.zzvh());
                    int position2 = zzabx.getPosition();
                    int i2 = 0;
                    while (zzabx.zzvl() > 0) {
                        zzabx.zzvi();
                        i2++;
                    }
                    zzabx.zzam(position2);
                    int length4 = this.zzaux == null ? 0 : this.zzaux.length;
                    long[] jArr4 = new long[(i2 + length4)];
                    if (length4 != 0) {
                        System.arraycopy(this.zzaux, 0, jArr4, 0, length4);
                    }
                    while (length4 < jArr4.length) {
                        jArr4[length4] = zzabx.zzvi();
                        length4++;
                    }
                    this.zzaux = jArr4;
                    zzabx.zzal(zzaf2);
                    continue;
                default:
                    if (!super.zza(zzabx, zzvf)) {
                        break;
                    } else {
                        continue;
                    }
            }
        }
        return this;
    }
}
