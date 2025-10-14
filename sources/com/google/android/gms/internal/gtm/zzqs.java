package com.google.android.gms.internal.gtm;

final class zzqs {
    private static final zzqq<?> zzaxm = new zzqr();
    private static final zzqq<?> zzaxn = zzos();

    private static zzqq<?> zzos() {
        try {
            return (zzqq) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    static zzqq<?> zzot() {
        return zzaxm;
    }

    static zzqq<?> zzou() {
        if (zzaxn != null) {
            return zzaxn;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
