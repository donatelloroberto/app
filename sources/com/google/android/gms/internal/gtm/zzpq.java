package com.google.android.gms.internal.gtm;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzpq extends zzpo<Boolean> implements zzrj<Boolean>, zzsv, RandomAccess {
    private static final zzpq zzavv;
    private int size;
    private boolean[] zzavw;

    zzpq() {
        this(new boolean[10], 0);
    }

    private zzpq(boolean[] zArr, int i) {
        this.zzavw = zArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzmz();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzavw, i2, this.zzavw, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzpq)) {
            return super.equals(obj);
        }
        zzpq zzpq = (zzpq) obj;
        if (this.size != zzpq.size) {
            return false;
        }
        boolean[] zArr = zzpq.zzavw;
        for (int i = 0; i < this.size; i++) {
            if (this.zzavw[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzre.zzk(this.zzavw[i2]);
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final void addBoolean(boolean z) {
        zza(this.size, z);
    }

    private final void zza(int i, boolean z) {
        zzmz();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzai(i));
        }
        if (this.size < this.zzavw.length) {
            System.arraycopy(this.zzavw, i, this.zzavw, i + 1, this.size - i);
        } else {
            boolean[] zArr = new boolean[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzavw, 0, zArr, 0, i);
            System.arraycopy(this.zzavw, i, zArr, i + 1, this.size - i);
            this.zzavw = zArr;
        }
        this.zzavw[i] = z;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzmz();
        zzre.checkNotNull(collection);
        if (!(collection instanceof zzpq)) {
            return super.addAll(collection);
        }
        zzpq zzpq = (zzpq) collection;
        if (zzpq.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzpq.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzpq.size;
        if (i > this.zzavw.length) {
            this.zzavw = Arrays.copyOf(this.zzavw, i);
        }
        System.arraycopy(zzpq.zzavw, 0, this.zzavw, this.size, zzpq.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzmz();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzavw[i]))) {
                System.arraycopy(this.zzavw, i + 1, this.zzavw, i, (this.size - i) - 1);
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
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzmz();
        zzah(i);
        boolean z = this.zzavw[i];
        this.zzavw[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ Object remove(int i) {
        zzmz();
        zzah(i);
        boolean z = this.zzavw[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzavw, i + 1, this.zzavw, i, (this.size - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zza(i, ((Boolean) obj).booleanValue());
    }

    public final /* synthetic */ zzrj zzaj(int i) {
        if (i >= this.size) {
            return new zzpq(Arrays.copyOf(this.zzavw, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzah(i);
        return Boolean.valueOf(this.zzavw[i]);
    }

    static {
        zzpq zzpq = new zzpq(new boolean[0], 0);
        zzavv = zzpq;
        zzpq.zzmi();
    }
}
