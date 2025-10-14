package com.google.android.gms.internal.gtm;

import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
public final class zzt extends zzi<zzt> {
    private Map<Integer, Double> zzui = new HashMap(4);

    public final String toString() {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.zzui.entrySet()) {
            String valueOf = String.valueOf(next.getKey());
            hashMap.put(new StringBuilder(String.valueOf(valueOf).length() + 6).append("metric").append(valueOf).toString(), next.getValue());
        }
        return zza((Object) hashMap);
    }

    public final Map<Integer, Double> zzbl() {
        return Collections.unmodifiableMap(this.zzui);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        ((zzt) zzi).zzui.putAll(this.zzui);
    }
}
