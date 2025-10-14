package com.google.android.gms.internal.gtm;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzry extends zzpo<Long> implements zzrj<Long>, zzsv, RandomAccess {
    private static final zzry zzbck;
    private int size;
    private long[] zzbcl;

    zzry() {
        this(new long[10], 0);
    }

    private zzry(long[] jArr, int i) {
        this.zzbcl = jArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzmz();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzbcl, i2, this.zzbcl, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzry)) {
            return super.equals(obj);
        }
        zzry zzry = (zzry) obj;
        if (this.size != zzry.size) {
            return false;
        }
        long[] jArr = zzry.zzbcl;
        for (int i = 0; i < this.size; i++) {
            if (this.zzbcl[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzre.zzz(this.zzbcl[i2]);
        }
        return i;
    }

    public final long getLong(int i) {
        zzah(i);
        return this.zzbcl[i];
    }

    public final int size() {
        return this.size;
    }

    public final void zzaa(long j) {
        zzk(this.size, j);
    }

    private final void zzk(int i, long j) {
        zzmz();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzai(i));
        }
        if (this.size < this.zzbcl.length) {
            System.arraycopy(this.zzbcl, i, this.zzbcl, i + 1, this.size - i);
        } else {
            long[] jArr = new long[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzbcl, 0, jArr, 0, i);
            System.arraycopy(this.zzbcl, i, jArr, i + 1, this.size - i);
            this.zzbcl = jArr;
        }
        this.zzbcl[i] = j;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        zzmz();
        zzre.checkNotNull(collection);
        if (!(collection instanceof zzry)) {
            return super.addAll(collection);
        }
        zzry zzry = (zzry) collection;
        if (zzry.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzry.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzry.size;
        if (i > this.zzbcl.length) {
            this.zzbcl = Arrays.copyOf(this.zzbcl, i);
        }
        System.arraycopy(zzry.zzbcl, 0, this.zzbcl, this.size, zzry.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzmz();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Long.valueOf(this.zzbcl[i]))) {
                System.arraycopy(this.zzbcl, i + 1, this.zzbcl, i, (this.size - i) - 1);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzah(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzai(i));
        }
    }

    private final String zzai(int i) {
        return new StringBuilder(35).append("Index:").append(i).append(", Size:").append(this.size).toString();
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzmz();
        zzah(i);
        long j = this.zzbcl[i];
        this.zzbcl[i] = longValue;
        return Long.valueOf(j);
    }

    public final /* synthetic */ Object remove(int i) {
        zzmz();
        zzah(i);
        long j = this.zzbcl[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzbcl, i + 1, this.zzbcl, i, (this.size - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzk(i, ((Long) obj).longValue());
    }

    public final /* synthetic */ zzrj zzaj(int i) {
        if (i >= this.size) {
            return new zzry(Arrays.copyOf(this.zzbcl, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    static {
        zzry zzry = new zzry(new long[0], 0);
        zzbck = zzry;
        zzry.zzmi();
    }
}
