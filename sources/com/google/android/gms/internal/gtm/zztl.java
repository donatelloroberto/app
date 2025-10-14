package com.google.android.gms.internal.gtm;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

class zztl extends AbstractSet<Map.Entry<K, V>> {
    private final /* synthetic */ zztc zzbef;

    private zztl(zztc zztc) {
        this.zzbef = zztc;
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return new zztk(this.zzbef, (zztd) null);
    }

    public int size() {
        return this.zzbef.size();
    }

    public boolean contains(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.zzbef.get(entry.getKey());
        Object value = entry.getValue();
        return obj2 == value || (obj2 != null && obj2.equals(value));
    }

    public boolean remove(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzbef.remove(entry.getKey());
        return true;
    }

    public void clear() {
        this.zzbef.clear();
    }

    public /* synthetic */ boolean add(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzbef.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    /* synthetic */ zztl(zztc zztc, zztd zztd) {
        this(zztc);
    }
}
