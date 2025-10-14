package com.google.android.gms.internal.ads;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzbdg {
    private static final zzbdg zzdxa = new zzbdg();
    private final zzbdn zzdxb;
    private final ConcurrentMap<Class<?>, zzbdm<?>> zzdxc = new ConcurrentHashMap();

    private zzbdg() {
        zzbdn zzbdn = null;
        String[] strArr = {"com.google.protobuf.AndroidProto3SchemaFactory"};
        for (int i = 0; i <= 0; i++) {
            zzbdn = zzeq(strArr[0]);
            if (zzbdn != null) {
                break;
            }
        }
        this.zzdxb = zzbdn == null ? new zzbcj() : zzbdn;
    }

    public static zzbdg zzaeo() {
        return zzdxa;
    }

    private static zzbdn zzeq(String str) {
        try {
            return (zzbdn) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }

    public final <T> zzbdm<T> zzab(T t) {
        return zze(t.getClass());
    }

    public final <T> zzbdm<T> zze(Class<T> cls) {
        zzbbq.zza(cls, "messageType");
        zzbdm<T> zzbdm = (zzbdm) this.zzdxc.get(cls);
        if (zzbdm != null) {
            return zzbdm;
        }
        zzbdm<T> zzd = this.zzdxb.zzd(cls);
        zzbbq.zza(cls, "messageType");
        zzbbq.zza(zzd, "schema");
        zzbdm<T> putIfAbsent = this.zzdxc.putIfAbsent(cls, zzd);
        return putIfAbsent != null ? putIfAbsent : zzd;
    }
}
