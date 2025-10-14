package com.google.android.gms.internal.measurement;

final class zzzr implements zzaam {
    private static final zzzr zzbsh = new zzzr();

    private zzzr() {
    }

    public static zzzr zztu() {
        return zzbsh;
    }

    public final boolean zzd(Class<?> cls) {
        return zzzs.class.isAssignableFrom(cls);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Class<?>, java.lang.Class] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzaal zze(java.lang.Class<?> r6) {
        /*
            r5 = this;
            java.lang.Class<com.google.android.gms.internal.measurement.zzzs> r0 = com.google.android.gms.internal.measurement.zzzs.class
            boolean r0 = r0.isAssignableFrom(r6)
            if (r0 != 0) goto L_0x0028
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "Unsupported message type: "
            java.lang.String r0 = r6.getName()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r3 = r0.length()
            if (r3 == 0) goto L_0x0022
            java.lang.String r0 = r2.concat(r0)
        L_0x001e:
            r1.<init>(r0)
            throw r1
        L_0x0022:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r2)
            goto L_0x001e
        L_0x0028:
            java.lang.Class<com.google.android.gms.internal.measurement.zzzs> r0 = com.google.android.gms.internal.measurement.zzzs.class
            java.lang.Class r0 = r6.asSubclass(r0)     // Catch:{ Exception -> 0x003c }
            com.google.android.gms.internal.measurement.zzzs r0 = com.google.android.gms.internal.measurement.zzzs.zzf(r0)     // Catch:{ Exception -> 0x003c }
            r1 = 3
            r2 = 0
            r3 = 0
            java.lang.Object r0 = r0.zza((int) r1, (java.lang.Object) r2, (java.lang.Object) r3)     // Catch:{ Exception -> 0x003c }
            com.google.android.gms.internal.measurement.zzaal r0 = (com.google.android.gms.internal.measurement.zzaal) r0     // Catch:{ Exception -> 0x003c }
            return r0
        L_0x003c:
            r0 = move-exception
            r1 = r0
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            java.lang.String r3 = "Unable to get message info for "
            java.lang.String r0 = r6.getName()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r4 = r0.length()
            if (r4 == 0) goto L_0x0058
            java.lang.String r0 = r3.concat(r0)
        L_0x0054:
            r2.<init>(r0, r1)
            throw r2
        L_0x0058:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r3)
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzzr.zze(java.lang.Class):com.google.android.gms.internal.measurement.zzaal");
    }
}
