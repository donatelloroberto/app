package com.google.android.gms.internal.gtm;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzqz extends zzpo<Float> implements zzrj<Float>, zzsv, RandomAccess {
    private static final zzqz zzbag;
    private int size;
    private float[] zzbah;

    zzqz() {
        this(new float[10], 0);
    }

    private zzqz(float[] fArr, int i) {
        this.zzbah = fArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzmz();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzbah, i2, this.zzbah, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzqz)) {
            return super.equals(obj);
        }
        zzqz zzqz = (zzqz) obj;
        if (this.size != zzqz.size) {
            return false;
        }
        float[] fArr = zzqz.zzbah;
        for (int i = 0; i < this.size; i++) {
            if (Float.floatToIntBits(this.zzbah[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.zzbah[i2]);
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final void zzc(float f) {
        zzc(this.size, f);
    }

    private final void zzc(int i, float f) {
        zzmz();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzai(i));
        }
        if (this.size < this.zzbah.length) {
            System.arraycopy(this.zzbah, i, this.zzbah, i + 1, this.size - i);
        } else {
            float[] fArr = new float[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzbah, 0, fArr, 0, i);
            System.arraycopy(this.zzbah, i, fArr, i + 1, this.size - i);
            this.zzbah = fArr;
        }
        this.zzbah[i] = f;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Float> collection) {
        zzmz();
        zzre.checkNotNull(collection);
        if (!(collection instanceof zzqz)) {
            return super.addAll(collection);
        }
        zzqz zzqz = (zzqz) collection;
        if (zzqz.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzqz.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzqz.size;
        if (i > this.zzbah.length) {
            this.zzbah = Arrays.copyOf(this.zzbah, i);
        }
        System.arraycopy(zzqz.zzbah, 0, this.zzbah, this.size, zzqz.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzmz();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Float.valueOf(this.zzbah[i]))) {
                System.arraycopy(this.zzbah, i + 1, this.zzbah, i, (this.size - i) - 1);
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
        float floatValue = ((Float) obj).floatValue();
        zzmz();
        zzah(i);
        float f = this.zzbah[i];
        this.zzbah[i] = floatValue;
        return Float.valueOf(f);
    }

    public final /* synthetic */ Object remove(int i) {
        zzmz();
        zzah(i);
        float f = this.zzbah[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzbah, i + 1, this.zzbah, i, (this.size - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Float.valueOf(f);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzc(i, ((Float) obj).floatValue());
    }

    public final /* synthetic */ zzrj zzaj(int i) {
        if (i >= this.size) {
            return new zzqz(Arrays.copyOf(this.zzbah, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzah(i);
        return Float.valueOf(this.zzbah[i]);
    }

    static {
        zzqz zzqz = new zzqz(new float[0], 0);
        zzbag = zzqz;
        zzqz.zzmi();
    }
}
