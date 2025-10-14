package com.google.android.gms.internal.gtm;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzrd extends zzpo<Integer> implements zzrj<Integer>, zzsv, RandomAccess {
    private static final zzrd zzbbf;
    private int size;
    private int[] zzbbg;

    public static zzrd zzpo() {
        return zzbbf;
    }

    zzrd() {
        this(new int[10], 0);
    }

    private zzrd(int[] iArr, int i) {
        this.zzbbg = iArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzmz();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzbbg, i2, this.zzbbg, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzrd)) {
            return super.equals(obj);
        }
        zzrd zzrd = (zzrd) obj;
        if (this.size != zzrd.size) {
            return false;
        }
        int[] iArr = zzrd.zzbbg;
        for (int i = 0; i < this.size; i++) {
            if (this.zzbbg[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzbbg[i2];
        }
        return i;
    }

    public final int getInt(int i) {
        zzah(i);
        return this.zzbbg[i];
    }

    public final int size() {
        return this.size;
    }

    public final void zzbm(int i) {
        zzq(this.size, i);
    }

    private final void zzq(int i, int i2) {
        zzmz();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzai(i));
        }
        if (this.size < this.zzbbg.length) {
            System.arraycopy(this.zzbbg, i, this.zzbbg, i + 1, this.size - i);
        } else {
            int[] iArr = new int[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzbbg, 0, iArr, 0, i);
            System.arraycopy(this.zzbbg, i, iArr, i + 1, this.size - i);
            this.zzbbg = iArr;
        }
        this.zzbbg[i] = i2;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zzmz();
        zzre.checkNotNull(collection);
        if (!(collection instanceof zzrd)) {
            return super.addAll(collection);
        }
        zzrd zzrd = (zzrd) collection;
        if (zzrd.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzrd.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzrd.size;
        if (i > this.zzbbg.length) {
            this.zzbbg = Arrays.copyOf(this.zzbbg, i);
        }
        System.arraycopy(zzrd.zzbbg, 0, this.zzbbg, this.size, zzrd.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzmz();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Integer.valueOf(this.zzbbg[i]))) {
                System.arraycopy(this.zzbbg, i + 1, this.zzbbg, i, (this.size - i) - 1);
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
        int intValue = ((Integer) obj).intValue();
        zzmz();
        zzah(i);
        int i2 = this.zzbbg[i];
        this.zzbbg[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ Object remove(int i) {
        zzmz();
        zzah(i);
        int i2 = this.zzbbg[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzbbg, i + 1, this.zzbbg, i, (this.size - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzq(i, ((Integer) obj).intValue());
    }

    public final /* synthetic */ zzrj zzaj(int i) {
        if (i >= this.size) {
            return new zzrd(Arrays.copyOf(this.zzbbg, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    static {
        zzrd zzrd = new zzrd(new int[0], 0);
        zzbbf = zzrd;
        zzrd.zzmi();
    }
}
