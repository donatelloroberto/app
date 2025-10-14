package com.google.android.gms.internal.gtm;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzqm extends zzpo<Double> implements zzrj<Double>, zzsv, RandomAccess {
    private static final zzqm zzaxe;
    private int size;
    private double[] zzaxf;

    zzqm() {
        this(new double[10], 0);
    }

    private zzqm(double[] dArr, int i) {
        this.zzaxf = dArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzmz();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzaxf, i2, this.zzaxf, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzqm)) {
            return super.equals(obj);
        }
        zzqm zzqm = (zzqm) obj;
        if (this.size != zzqm.size) {
            return false;
        }
        double[] dArr = zzqm.zzaxf;
        for (int i = 0; i < this.size; i++) {
            if (Double.doubleToLongBits(this.zzaxf[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzre.zzz(Double.doubleToLongBits(this.zzaxf[i2]));
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final void zzd(double d) {
        zzc(this.size, d);
    }

    private final void zzc(int i, double d) {
        zzmz();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzai(i));
        }
        if (this.size < this.zzaxf.length) {
            System.arraycopy(this.zzaxf, i, this.zzaxf, i + 1, this.size - i);
        } else {
            double[] dArr = new double[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzaxf, 0, dArr, 0, i);
            System.arraycopy(this.zzaxf, i, dArr, i + 1, this.size - i);
            this.zzaxf = dArr;
        }
        this.zzaxf[i] = d;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zzmz();
        zzre.checkNotNull(collection);
        if (!(collection instanceof zzqm)) {
            return super.addAll(collection);
        }
        zzqm zzqm = (zzqm) collection;
        if (zzqm.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzqm.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzqm.size;
        if (i > this.zzaxf.length) {
            this.zzaxf = Arrays.copyOf(this.zzaxf, i);
        }
        System.arraycopy(zzqm.zzaxf, 0, this.zzaxf, this.size, zzqm.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzmz();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzaxf[i]))) {
                System.arraycopy(this.zzaxf, i + 1, this.zzaxf, i, (this.size - i) - 1);
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
        double doubleValue = ((Double) obj).doubleValue();
        zzmz();
        zzah(i);
        double d = this.zzaxf[i];
        this.zzaxf[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final /* synthetic */ Object remove(int i) {
        zzmz();
        zzah(i);
        double d = this.zzaxf[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzaxf, i + 1, this.zzaxf, i, (this.size - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzc(i, ((Double) obj).doubleValue());
    }

    public final /* synthetic */ zzrj zzaj(int i) {
        if (i >= this.size) {
            return new zzqm(Arrays.copyOf(this.zzaxf, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzah(i);
        return Double.valueOf(this.zzaxf[i]);
    }

    static {
        zzqm zzqm = new zzqm(new double[0], 0);
        zzaxe = zzqm;
        zzqm.zzmi();
    }
}
