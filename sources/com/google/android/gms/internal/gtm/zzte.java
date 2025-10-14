package com.google.android.gms.internal.gtm;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzte implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzbee;
    private final /* synthetic */ zztc zzbef;

    private zzte(zztc zztc) {
        this.zzbef = zztc;
        this.pos = this.zzbef.zzbdz.size();
    }

    public final boolean hasNext() {
        return (this.pos > 0 && this.pos <= this.zzbef.zzbdz.size()) || zzrf().hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Map.Entry<K, V>> zzrf() {
        if (this.zzbee == null) {
            this.zzbee = this.zzbef.zzbec.entrySet().iterator();
        }
        return this.zzbee;
    }

    public final /* synthetic */ Object next() {
        if (zzrf().hasNext()) {
            return (Map.Entry) zzrf().next();
        }
        List zzb = this.zzbef.zzbdz;
        int i = this.pos - 1;
        this.pos = i;
        return (Map.Entry) zzb.get(i);
    }

    /* synthetic */ zzte(zztc zztc, zztd zztd) {
        this(zztc);
    }
}
