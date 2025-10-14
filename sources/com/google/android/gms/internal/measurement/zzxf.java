package com.google.android.gms.internal.measurement;

import java.io.PrintStream;

public final class zzxf {
    private static final zzxg zzboi;
    private static final int zzboj;

    static final class zza extends zzxg {
        zza() {
        }

        public final void zza(Throwable th, PrintStream printStream) {
            th.printStackTrace(printStream);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0019  */
    static {
        /*
            r2 = 1
            r0 = 0
            java.lang.Integer r0 = zzsm()     // Catch:{ Throwable -> 0x002e }
            if (r0 == 0) goto L_0x001d
            int r1 = r0.intValue()     // Catch:{ Throwable -> 0x002e }
            r3 = 19
            if (r1 < r3) goto L_0x001d
            com.google.android.gms.internal.measurement.zzxk r1 = new com.google.android.gms.internal.measurement.zzxk     // Catch:{ Throwable -> 0x002e }
            r1.<init>()     // Catch:{ Throwable -> 0x002e }
        L_0x0015:
            zzboi = r1
            if (r0 != 0) goto L_0x0070
            r0 = r2
        L_0x001a:
            zzboj = r0
            return
        L_0x001d:
            java.lang.String r1 = "com.google.devtools.build.android.desugar.runtime.twr_disable_mimic"
            boolean r1 = java.lang.Boolean.getBoolean(r1)     // Catch:{ Throwable -> 0x002e }
            if (r1 != 0) goto L_0x0068
            r1 = r2
        L_0x0026:
            if (r1 == 0) goto L_0x006a
            com.google.android.gms.internal.measurement.zzxj r1 = new com.google.android.gms.internal.measurement.zzxj     // Catch:{ Throwable -> 0x002e }
            r1.<init>()     // Catch:{ Throwable -> 0x002e }
            goto L_0x0015
        L_0x002e:
            r1 = move-exception
            java.io.PrintStream r3 = java.lang.System.err
            java.lang.Class<com.google.android.gms.internal.measurement.zzxf$zza> r4 = com.google.android.gms.internal.measurement.zzxf.zza.class
            java.lang.String r4 = r4.getName()
            java.lang.String r5 = java.lang.String.valueOf(r4)
            int r5 = r5.length()
            int r5 = r5 + 132
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            java.lang.String r5 = "An error has occured when initializing the try-with-resources desuguring strategy. The default strategy "
            java.lang.StringBuilder r5 = r6.append(r5)
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.String r5 = "will be used. The error is: "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.println(r4)
            java.io.PrintStream r3 = java.lang.System.err
            r1.printStackTrace(r3)
            com.google.android.gms.internal.measurement.zzxf$zza r1 = new com.google.android.gms.internal.measurement.zzxf$zza
            r1.<init>()
            goto L_0x0015
        L_0x0068:
            r1 = 0
            goto L_0x0026
        L_0x006a:
            com.google.android.gms.internal.measurement.zzxf$zza r1 = new com.google.android.gms.internal.measurement.zzxf$zza     // Catch:{ Throwable -> 0x002e }
            r1.<init>()     // Catch:{ Throwable -> 0x002e }
            goto L_0x0015
        L_0x0070:
            int r0 = r0.intValue()
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzxf.<clinit>():void");
    }

    public static void zza(Throwable th, PrintStream printStream) {
        zzboi.zza(th, printStream);
    }

    private static Integer zzsm() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get((Object) null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }
}
