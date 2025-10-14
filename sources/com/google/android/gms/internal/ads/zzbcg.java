package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzbcg extends zzbce {
    private static final Class<?> zzdvs = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzbcg() {
        super();
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        List<L> zzc = zzc(obj, j);
        if (zzc.isEmpty()) {
            List<L> zzbcc = zzc instanceof zzbcd ? new zzbcc(i) : new ArrayList<>(i);
            zzbek.zza(obj, j, (Object) zzbcc);
            return zzbcc;
        } else if (zzdvs.isAssignableFrom(zzc.getClass())) {
            ArrayList arrayList = new ArrayList(zzc.size() + i);
            arrayList.addAll(zzc);
            zzbek.zza(obj, j, (Object) arrayList);
            return arrayList;
        } else if (!(zzc instanceof zzbeh)) {
            return zzc;
        } else {
            zzbcc zzbcc2 = new zzbcc(zzc.size() + i);
            zzbcc2.addAll((zzbeh) zzc);
            zzbek.zza(obj, j, (Object) zzbcc2);
            return zzbcc2;
        }
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzbek.zzp(obj, j);
    }

    /* access modifiers changed from: package-private */
    public final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    /* access modifiers changed from: package-private */
    public final <E> void zza(Object obj, Object obj2, long j) {
        List zzc = zzc(obj2, j);
        List zza = zza(obj, j, zzc.size());
        int size = zza.size();
        int size2 = zzc.size();
        if (size > 0 && size2 > 0) {
            zza.addAll(zzc);
        }
        if (size <= 0) {
            zza = zzc;
        }
        zzbek.zza(obj, j, (Object) zza);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzbek.zzp(obj, j);
        if (list instanceof zzbcd) {
            unmodifiableList = ((zzbcd) list).zzadx();
        } else if (!zzdvs.isAssignableFrom(list.getClass())) {
            unmodifiableList = Collections.unmodifiableList(list);
        } else {
            return;
        }
        zzbek.zza(obj, j, unmodifiableList);
    }
}
