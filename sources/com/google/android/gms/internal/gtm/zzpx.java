package com.google.android.gms.internal.gtm;

final class zzpx extends zzqc {
    private final int zzawb;
    private final int zzawc;

    zzpx(byte[] bArr, int i, int i2) {
        super(bArr);
        zzb(i, i + i2, bArr.length);
        this.zzawb = i;
        this.zzawc = i2;
    }

    public final byte zzak(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzawe[this.zzawb + i];
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(22).append("Index < 0: ").append(i).toString());
        }
        throw new ArrayIndexOutOfBoundsException(new StringBuilder(40).append("Index > length: ").append(i).append(", ").append(size).toString());
    }

    /* access modifiers changed from: package-private */
    public final byte zzal(int i) {
        return this.zzawe[this.zzawb + i];
    }

    public final int size() {
        return this.zzawc;
    }

    /* access modifiers changed from: protected */
    public final int zznf() {
        return this.zzawb;
    }
}
