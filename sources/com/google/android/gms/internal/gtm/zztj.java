package com.google.android.gms.internal.gtm;

import java.util.Map;

final class zztj implements Comparable<zztj>, Map.Entry<K, V> {
    private V value;
    private final /* synthetic */ zztc zzbef;
    private final K zzbei;

    zztj(zztc zztc, Map.Entry<K, V> entry) {
        this(zztc, (Comparable) entry.getKey(), entry.getValue());
    }

    zztj(zztc zztc, K k, V v) {
        this.zzbef = zztc;
        this.zzbei = k;
        this.value = v;
    }

    public final V getValue() {
        return this.value;
    }

    public final V setValue(V v) {
        this.zzbef.zzrd();
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (!equals(this.zzbei, entry.getKey()) || !equals(this.value, entry.getValue())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = this.zzbei == null ? 0 : this.zzbei.hashCode();
        if (this.value != null) {
            i = this.value.hashCode();
        }
        return hashCode ^ i;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzbei);
        String valueOf2 = String.valueOf(this.value);
        return new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length()).append(valueOf).append("=").append(valueOf2).toString();
    }

    private static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public final /* synthetic */ Object getKey() {
        return this.zzbei;
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return ((Comparable) getKey()).compareTo((Comparable) ((zztj) obj).getKey());
    }
}
