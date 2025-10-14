package com.google.android.gms.internal.gtm;

final class zzqo {
    private static final Class<?> zzaxg = zzom();

    private static Class<?> zzom() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static zzqp zzon() {
        if (zzaxg != null) {
            try {
                return zzdc("getEmptyRegistry");
            } catch (Exception e) {
            }
        }
        return zzqp.zzaxk;
    }

    static zzqp zzoo() {
        zzqp zzqp = null;
        if (zzaxg != null) {
            try {
                zzqp = zzdc("loadGeneratedRegistry");
            } catch (Exception e) {
            }
        }
        if (zzqp == null) {
            zzqp = zzqp.zzoo();
        }
        return zzqp == null ? zzon() : zzqp;
    }

    private static final zzqp zzdc(String str) throws Exception {
        return (zzqp) zzaxg.getDeclaredMethod(str, new Class[0]).invoke((Object) null, new Object[0]);
    }
}
