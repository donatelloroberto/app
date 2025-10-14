package com.google.android.gms.internal.gtm;

public final class zzus implements Cloneable {
    private static final zzut zzbhe = new zzut();
    private int mSize;
    private boolean zzbhf;
    private int[] zzbhg;
    private zzut[] zzbhh;

    zzus() {
        this(10);
    }

    private zzus(int i) {
        this.zzbhf = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzbhg = new int[idealIntArraySize];
        this.zzbhh = new zzut[idealIntArraySize];
        this.mSize = 0;
    }

    /* access modifiers changed from: package-private */
    public final zzut zzcd(int i) {
        int zzcf = zzcf(i);
        if (zzcf < 0 || this.zzbhh[zzcf] == zzbhe) {
            return null;
        }
        return this.zzbhh[zzcf];
    }

    /* access modifiers changed from: package-private */
    public final void zza(int i, zzut zzut) {
        int zzcf = zzcf(i);
        if (zzcf >= 0) {
            this.zzbhh[zzcf] = zzut;
            return;
        }
        int i2 = zzcf ^ -1;
        if (i2 >= this.mSize || this.zzbhh[i2] != zzbhe) {
            if (this.mSize >= this.zzbhg.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                int[] iArr = new int[idealIntArraySize];
                zzut[] zzutArr = new zzut[idealIntArraySize];
                System.arraycopy(this.zzbhg, 0, iArr, 0, this.zzbhg.length);
                System.arraycopy(this.zzbhh, 0, zzutArr, 0, this.zzbhh.length);
                this.zzbhg = iArr;
                this.zzbhh = zzutArr;
            }
            if (this.mSize - i2 != 0) {
                System.arraycopy(this.zzbhg, i2, this.zzbhg, i2 + 1, this.mSize - i2);
                System.arraycopy(this.zzbhh, i2, this.zzbhh, i2 + 1, this.mSize - i2);
            }
            this.zzbhg[i2] = i;
            this.zzbhh[i2] = zzut;
            this.mSize++;
            return;
        }
        this.zzbhg[i2] = i;
        this.zzbhh[i2] = zzut;
    }

    /* access modifiers changed from: package-private */
    public final int size() {
        return this.mSize;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    /* access modifiers changed from: package-private */
    public final zzut zzce(int i) {
        return this.zzbhh[i];
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzus)) {
            return false;
        }
        zzus zzus = (zzus) obj;
        if (this.mSize != zzus.mSize) {
            return false;
        }
        int[] iArr = this.zzbhg;
        int[] iArr2 = zzus.zzbhg;
        int i = this.mSize;
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
            zzut[] zzutArr = this.zzbhh;
            zzut[] zzutArr2 = zzus.zzbhh;
            int i3 = this.mSize;
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    z2 = true;
                    break;
                } else if (!zzutArr[i4].equals(zzutArr2[i4])) {
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
        return false;
    }

    public final int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.zzbhg[i2]) * 31) + this.zzbhh[i2].hashCode();
        }
        return i;
    }

    private static int idealIntArraySize(int i) {
        int i2 = i << 2;
        int i3 = 4;
        while (true) {
            if (i3 >= 32) {
                break;
            } else if (i2 <= (1 << i3) - 12) {
                i2 = (1 << i3) - 12;
                break;
            } else {
                i3++;
            }
        }
        return i2 / 4;
    }

    private final int zzcf(int i) {
        int i2 = 0;
        int i3 = this.mSize - 1;
        while (i2 <= i3) {
            int i4 = (i2 + i3) >>> 1;
            int i5 = this.zzbhg[i4];
            if (i5 < i) {
                i2 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return i2 ^ -1;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.mSize;
        zzus zzus = new zzus(i);
        System.arraycopy(this.zzbhg, 0, zzus.zzbhg, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zzbhh[i2] != null) {
                zzus.zzbhh[i2] = (zzut) this.zzbhh[i2].clone();
            }
        }
        zzus.mSize = i;
        return zzus;
    }
}
