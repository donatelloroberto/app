package com.google.android.gms.internal.gtm;

final class zzsb implements zzsj {
    private zzsj[] zzbco;

    zzsb(zzsj... zzsjArr) {
        this.zzbco = zzsjArr;
    }

    public final boolean zze(Class<?> cls) {
        for (zzsj zze : this.zzbco) {
            if (zze.zze(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzsi zzf(Class<?> cls) {
        for (zzsj zzsj : this.zzbco) {
            if (zzsj.zze(cls)) {
                return zzsj.zzf(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}
