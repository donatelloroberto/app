package com.google.android.gms.internal.measurement;

final class zzzb extends zzze {
    private final int zzbrk;
    private final int zzbrl;

    zzzb(byte[] bArr, int i, int i2) {
        super(bArr);
        zzb(i, i + i2, bArr.length);
        this.zzbrk = i;
        this.zzbrl = i2;
    }

    public final int size() {
        return this.zzbrl;
    }

    public final byte zzae(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzbrm[this.zzbrk + i];
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(22).append("Index < 0: ").append(i).toString());
        }
        throw new ArrayIndexOutOfBoundsException(new StringBuilder(40).append("Index > length: ").append(i).append(", ").append(size).toString());
    }

    /* access modifiers changed from: protected */
    public final int zzth() {
        return this.zzbrk;
    }
}
