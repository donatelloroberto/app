package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzrc;
import java.io.IOException;
import java.util.Arrays;

public final class zzts {
    private static final zzts zzbem = new zzts(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzavs;
    private int zzbal;
    private Object[] zzbcz;
    private int[] zzben;

    public static zzts zzrj() {
        return zzbem;
    }

    static zzts zzrk() {
        return new zzts();
    }

    static zzts zza(zzts zzts, zzts zzts2) {
        int i = zzts.count + zzts2.count;
        int[] copyOf = Arrays.copyOf(zzts.zzben, i);
        System.arraycopy(zzts2.zzben, 0, copyOf, zzts.count, zzts2.count);
        Object[] copyOf2 = Arrays.copyOf(zzts.zzbcz, i);
        System.arraycopy(zzts2.zzbcz, 0, copyOf2, zzts.count, zzts2.count);
        return new zzts(i, copyOf, copyOf2, true);
    }

    private zzts() {
        this(0, new int[8], new Object[8], true);
    }

    private zzts(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzbal = -1;
        this.count = i;
        this.zzben = iArr;
        this.zzbcz = objArr;
        this.zzavs = z;
    }

    public final void zzmi() {
        this.zzavs = false;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzum zzum) throws IOException {
        if (zzum.zzol() == zzrc.zze.zzbbd) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzum.zza(this.zzben[i] >>> 3, this.zzbcz[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzum.zza(this.zzben[i2] >>> 3, this.zzbcz[i2]);
        }
    }

    public final void zzb(zzum zzum) throws IOException {
        if (this.count != 0) {
            if (zzum.zzol() == zzrc.zze.zzbbc) {
                for (int i = 0; i < this.count; i++) {
                    zzb(this.zzben[i], this.zzbcz[i], zzum);
                }
                return;
            }
            for (int i2 = this.count - 1; i2 >= 0; i2--) {
                zzb(this.zzben[i2], this.zzbcz[i2], zzum);
            }
        }
    }

    private static void zzb(int i, Object obj, zzum zzum) throws IOException {
        int i2 = i >>> 3;
        switch (i & 7) {
            case 0:
                zzum.zzi(i2, ((Long) obj).longValue());
                return;
            case 1:
                zzum.zzc(i2, ((Long) obj).longValue());
                return;
            case 2:
                zzum.zza(i2, (zzps) obj);
                return;
            case 3:
                if (zzum.zzol() == zzrc.zze.zzbbc) {
                    zzum.zzbk(i2);
                    ((zzts) obj).zzb(zzum);
                    zzum.zzbl(i2);
                    return;
                }
                zzum.zzbl(i2);
                ((zzts) obj).zzb(zzum);
                zzum.zzbk(i2);
                return;
            case 5:
                zzum.zzh(i2, ((Integer) obj).intValue());
                return;
            default:
                throw new RuntimeException(zzrk.zzpt());
        }
    }

    public final int zzrl() {
        int i = this.zzbal;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.count; i2++) {
                i += zzqj.zzd(this.zzben[i2] >>> 3, (zzps) this.zzbcz[i2]);
            }
            this.zzbal = i;
        }
        return i;
    }

    public final int zzpe() {
        int zzpe;
        int i = this.zzbal;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.count; i2++) {
                int i3 = this.zzben[i2];
                int i4 = i3 >>> 3;
                switch (i3 & 7) {
                    case 0:
                        zzpe = zzqj.zze(i4, ((Long) this.zzbcz[i2]).longValue());
                        break;
                    case 1:
                        zzpe = zzqj.zzg(i4, ((Long) this.zzbcz[i2]).longValue());
                        break;
                    case 2:
                        zzpe = zzqj.zzc(i4, (zzps) this.zzbcz[i2]);
                        break;
                    case 3:
                        zzpe = ((zzts) this.zzbcz[i2]).zzpe() + (zzqj.zzbb(i4) << 1);
                        break;
                    case 5:
                        zzpe = zzqj.zzl(i4, ((Integer) this.zzbcz[i2]).intValue());
                        break;
                    default:
                        throw new IllegalStateException(zzrk.zzpt());
                }
                i += zzpe;
            }
            this.zzbal = i;
        }
        return i;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof zzts)) {
            return false;
        }
        zzts zzts = (zzts) obj;
        if (this.count == zzts.count) {
            int[] iArr = this.zzben;
            int[] iArr2 = zzts.zzben;
            int i = this.count;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                } else if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                Object[] objArr = this.zzbcz;
                Object[] objArr2 = zzts.zzbcz;
                int i3 = this.count;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    } else if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        break;
                    } else {
                        i4++;
                    }
                }
                if (z2) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 17;
        int i2 = (this.count + 527) * 31;
        int[] iArr = this.zzben;
        int i3 = this.count;
        int i4 = 17;
        for (int i5 = 0; i5 < i3; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzbcz;
        int i7 = this.count;
        for (int i8 = 0; i8 < i7; i8++) {
            i = (i * 31) + objArr[i8].hashCode();
        }
        return i6 + i;
    }

    /* access modifiers changed from: package-private */
    public final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzsn.zza(sb, i, String.valueOf(this.zzben[i2] >>> 3), this.zzbcz[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(int i, Object obj) {
        if (!this.zzavs) {
            throw new UnsupportedOperationException();
        }
        if (this.count == this.zzben.length) {
            int i2 = (this.count < 4 ? 8 : this.count >> 1) + this.count;
            this.zzben = Arrays.copyOf(this.zzben, i2);
            this.zzbcz = Arrays.copyOf(this.zzbcz, i2);
        }
        this.zzben[this.count] = i;
        this.zzbcz[this.count] = obj;
        this.count++;
    }
}
