package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzuq;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class zzur<M extends zzuq<M>, T> {
    public final int tag;
    private final int type;
    private final zzrc<?, ?> zzban;
    protected final Class<T> zzbhc;
    protected final boolean zzbhd;

    public static <M extends zzuq<M>, T extends zzuw> zzur<M, T> zza(int i, Class<T> cls, long j) {
        return new zzur<>(11, cls, 810, false);
    }

    private zzur(int i, Class<T> cls, int i2, boolean z) {
        this(11, cls, (zzrc<?, ?>) null, 810, false);
    }

    private zzur(int i, Class<T> cls, zzrc<?, ?> zzrc, int i2, boolean z) {
        this.type = i;
        this.zzbhc = cls;
        this.tag = i2;
        this.zzbhd = false;
        this.zzban = null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzur)) {
            return false;
        }
        zzur zzur = (zzur) obj;
        if (this.type == zzur.type && this.zzbhc == zzur.zzbhc && this.tag == zzur.tag && this.zzbhd == zzur.zzbhd) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (this.zzbhd ? 1 : 0) + ((((((this.type + 1147) * 31) + this.zzbhc.hashCode()) * 31) + this.tag) * 31);
    }

    /* access modifiers changed from: package-private */
    public final T zzag(List<zzuy> list) {
        if (list == null) {
            return null;
        }
        if (this.zzbhd) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                zzuy zzuy = list.get(i);
                if (zzuy.zzawe.length != 0) {
                    arrayList.add(zzc(zzun.zzk(zzuy.zzawe)));
                }
            }
            int size = arrayList.size();
            if (size == 0) {
                return null;
            }
            T cast = this.zzbhc.cast(Array.newInstance(this.zzbhc.getComponentType(), size));
            for (int i2 = 0; i2 < size; i2++) {
                Array.set(cast, i2, arrayList.get(i2));
            }
            return cast;
        } else if (list.isEmpty()) {
            return null;
        } else {
            return this.zzbhc.cast(zzc(zzun.zzk(list.get(list.size() - 1).zzawe)));
        }
    }

    private final Object zzc(zzun zzun) {
        Class<?> componentType = this.zzbhd ? this.zzbhc.getComponentType() : this.zzbhc;
        try {
            switch (this.type) {
                case 10:
                    zzuw zzuw = (zzuw) componentType.newInstance();
                    zzun.zza(zzuw, this.tag >>> 3);
                    return zzuw;
                case 11:
                    zzuw zzuw2 = (zzuw) componentType.newInstance();
                    zzun.zza(zzuw2);
                    return zzuw2;
                default:
                    throw new IllegalArgumentException(new StringBuilder(24).append("Unknown type ").append(this.type).toString());
            }
        } catch (InstantiationException e) {
            String valueOf = String.valueOf(componentType);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 33).append("Error creating instance of class ").append(valueOf).toString(), e);
        } catch (IllegalAccessException e2) {
            String valueOf2 = String.valueOf(componentType);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf2).length() + 33).append("Error creating instance of class ").append(valueOf2).toString(), e2);
        } catch (IOException e3) {
            throw new IllegalArgumentException("Error reading extension field", e3);
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(Object obj, zzuo zzuo) {
        try {
            zzuo.zzcb(this.tag);
            switch (this.type) {
                case 10:
                    ((zzuw) obj).zza(zzuo);
                    zzuo.zzd(this.tag >>> 3, 4);
                    return;
                case 11:
                    zzuo.zzb((zzuw) obj);
                    return;
                default:
                    throw new IllegalArgumentException(new StringBuilder(24).append("Unknown type ").append(this.type).toString());
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        throw new IllegalStateException(e);
    }

    /* access modifiers changed from: protected */
    public final int zzaj(Object obj) {
        int i = this.tag >>> 3;
        switch (this.type) {
            case 10:
                return (zzuo.zzbb(i) << 1) + ((zzuw) obj).zzpe();
            case 11:
                return zzuo.zzb(i, (zzuw) obj);
            default:
                throw new IllegalArgumentException(new StringBuilder(24).append("Unknown type ").append(this.type).toString());
        }
    }
}
