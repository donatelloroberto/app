package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;

public final class zzav {
    private static zzav zzdb;
    @GuardedBy("serviceClassNames")
    private final SimpleArrayMap<String, String> zzdc = new SimpleArrayMap<>();
    private Boolean zzdd = null;
    private Boolean zzde = null;
    final Queue<Intent> zzdf = new ArrayDeque();
    private final Queue<Intent> zzdg = new ArrayDeque();

    public static synchronized zzav zzai() {
        zzav zzav;
        synchronized (zzav.class) {
            if (zzdb == null) {
                zzdb = new zzav();
            }
            zzav = zzdb;
        }
        return zzav;
    }

    private zzav() {
    }

    public static void zzb(Context context, Intent intent) {
        context.sendBroadcast(zza(context, "com.google.firebase.INSTANCE_ID_EVENT", intent));
    }

    public static void zzc(Context context, Intent intent) {
        context.sendBroadcast(zza(context, "com.google.firebase.MESSAGING_EVENT", intent));
    }

    private static Intent zza(Context context, String str, Intent intent) {
        Intent intent2 = new Intent(context, FirebaseInstanceIdReceiver.class);
        intent2.setAction(str);
        intent2.putExtra("wrapped_intent", intent);
        return intent2;
    }

    public final Intent zzaj() {
        return this.zzdg.poll();
    }

    public final int zzb(Context context, String str, Intent intent) {
        String str2;
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf = String.valueOf(str);
            Log.d("FirebaseInstanceId", valueOf.length() != 0 ? "Starting service: ".concat(valueOf) : new String("Starting service: "));
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -842411455:
                if (str.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
                    c = 0;
                    break;
                }
                break;
            case 41532704:
                if (str.equals("com.google.firebase.MESSAGING_EVENT")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.zzdf.offer(intent);
                break;
            case 1:
                this.zzdg.offer(intent);
                break;
            default:
                String valueOf2 = String.valueOf(str);
                if (valueOf2.length() != 0) {
                    str2 = "Unknown service action: ".concat(valueOf2);
                } else {
                    str2 = new String("Unknown service action: ");
                }
                Log.w("FirebaseInstanceId", str2);
                return 500;
        }
        Intent intent2 = new Intent(str);
        intent2.setPackage(context.getPackageName());
        return zzd(context, intent2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002e A[Catch:{ SecurityException -> 0x00fd, IllegalStateException -> 0x010c }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0034 A[Catch:{ SecurityException -> 0x00fd, IllegalStateException -> 0x010c }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f0 A[SYNTHETIC, Splitter:B:50:0x00f0] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0109 A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzd(android.content.Context r6, android.content.Intent r7) {
        /*
            r5 = this;
            android.support.v4.util.SimpleArrayMap<java.lang.String, java.lang.String> r1 = r5.zzdc
            monitor-enter(r1)
            android.support.v4.util.SimpleArrayMap<java.lang.String, java.lang.String> r0 = r5.zzdc     // Catch:{ all -> 0x003e }
            java.lang.String r2 = r7.getAction()     // Catch:{ all -> 0x003e }
            java.lang.Object r0 = r0.get(r2)     // Catch:{ all -> 0x003e }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x003e }
            monitor-exit(r1)     // Catch:{ all -> 0x003e }
            if (r0 != 0) goto L_0x00ba
            android.content.pm.PackageManager r0 = r6.getPackageManager()
            r1 = 0
            android.content.pm.ResolveInfo r0 = r0.resolveService(r7, r1)
            if (r0 == 0) goto L_0x0021
            android.content.pm.ServiceInfo r1 = r0.serviceInfo
            if (r1 != 0) goto L_0x0041
        L_0x0021:
            java.lang.String r0 = "FirebaseInstanceId"
            java.lang.String r1 = "Failed to resolve target intent service, skipping classname enforcement"
            android.util.Log.e(r0, r1)
        L_0x0028:
            boolean r0 = r5.zzd(r6)     // Catch:{ SecurityException -> 0x00fd, IllegalStateException -> 0x010c }
            if (r0 == 0) goto L_0x00f0
            android.content.ComponentName r0 = android.support.v4.content.WakefulBroadcastReceiver.startWakefulService(r6, r7)     // Catch:{ SecurityException -> 0x00fd, IllegalStateException -> 0x010c }
        L_0x0032:
            if (r0 != 0) goto L_0x0109
            java.lang.String r0 = "FirebaseInstanceId"
            java.lang.String r1 = "Error while delivering the message: ServiceIntent not found."
            android.util.Log.e(r0, r1)     // Catch:{ SecurityException -> 0x00fd, IllegalStateException -> 0x010c }
            r0 = 404(0x194, float:5.66E-43)
        L_0x003d:
            return r0
        L_0x003e:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x003e }
            throw r0
        L_0x0041:
            android.content.pm.ServiceInfo r0 = r0.serviceInfo
            java.lang.String r1 = r6.getPackageName()
            java.lang.String r2 = r0.packageName
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0053
            java.lang.String r1 = r0.name
            if (r1 != 0) goto L_0x008d
        L_0x0053:
            java.lang.String r1 = "FirebaseInstanceId"
            java.lang.String r2 = r0.packageName
            java.lang.String r0 = r0.name
            java.lang.String r3 = java.lang.String.valueOf(r2)
            int r3 = r3.length()
            int r3 = r3 + 94
            java.lang.String r4 = java.lang.String.valueOf(r0)
            int r4 = r4.length()
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Error resolving target intent service, skipping classname enforcement. Resolved service was: "
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r3 = "/"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r1, r0)
            goto L_0x0028
        L_0x008d:
            java.lang.String r0 = r0.name
            java.lang.String r1 = "."
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L_0x00ad
            java.lang.String r1 = r6.getPackageName()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r2 = r0.length()
            if (r2 == 0) goto L_0x00e1
            java.lang.String r0 = r1.concat(r0)
        L_0x00ad:
            android.support.v4.util.SimpleArrayMap<java.lang.String, java.lang.String> r1 = r5.zzdc
            monitor-enter(r1)
            android.support.v4.util.SimpleArrayMap<java.lang.String, java.lang.String> r2 = r5.zzdc     // Catch:{ all -> 0x00e7 }
            java.lang.String r3 = r7.getAction()     // Catch:{ all -> 0x00e7 }
            r2.put(r3, r0)     // Catch:{ all -> 0x00e7 }
            monitor-exit(r1)     // Catch:{ all -> 0x00e7 }
        L_0x00ba:
            java.lang.String r1 = "FirebaseInstanceId"
            r2 = 3
            boolean r1 = android.util.Log.isLoggable(r1, r2)
            if (r1 == 0) goto L_0x00d8
            java.lang.String r2 = "FirebaseInstanceId"
            java.lang.String r3 = "Restricting intent to a specific service: "
            java.lang.String r1 = java.lang.String.valueOf(r0)
            int r4 = r1.length()
            if (r4 == 0) goto L_0x00ea
            java.lang.String r1 = r3.concat(r1)
        L_0x00d5:
            android.util.Log.d(r2, r1)
        L_0x00d8:
            java.lang.String r1 = r6.getPackageName()
            r7.setClassName(r1, r0)
            goto L_0x0028
        L_0x00e1:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r1)
            goto L_0x00ad
        L_0x00e7:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00e7 }
            throw r0
        L_0x00ea:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r3)
            goto L_0x00d5
        L_0x00f0:
            android.content.ComponentName r0 = r6.startService(r7)     // Catch:{ SecurityException -> 0x00fd, IllegalStateException -> 0x010c }
            java.lang.String r1 = "FirebaseInstanceId"
            java.lang.String r2 = "Missing wake lock permission, service start may be delayed"
            android.util.Log.d(r1, r2)     // Catch:{ SecurityException -> 0x00fd, IllegalStateException -> 0x010c }
            goto L_0x0032
        L_0x00fd:
            r0 = move-exception
            java.lang.String r1 = "FirebaseInstanceId"
            java.lang.String r2 = "Error while delivering the message to the serviceIntent"
            android.util.Log.e(r1, r2, r0)
            r0 = 401(0x191, float:5.62E-43)
            goto L_0x003d
        L_0x0109:
            r0 = -1
            goto L_0x003d
        L_0x010c:
            r0 = move-exception
            java.lang.String r1 = "FirebaseInstanceId"
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r2 = java.lang.String.valueOf(r0)
            int r2 = r2.length()
            int r2 = r2 + 45
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Failed to start service while in background: "
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r1, r0)
            r0 = 402(0x192, float:5.63E-43)
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzav.zzd(android.content.Context, android.content.Intent):int");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzd(Context context) {
        if (this.zzdd == null) {
            this.zzdd = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0);
        }
        if (!this.zzdd.booleanValue() && Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Missing Permission: android.permission.WAKE_LOCK this should normally be included by the manifest merger, but may needed to be manually added to your manifest");
        }
        return this.zzdd.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final boolean zze(Context context) {
        if (this.zzde == null) {
            this.zzde = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0);
        }
        if (!this.zzdd.booleanValue() && Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Missing Permission: android.permission.ACCESS_NETWORK_STATE this should normally be included by the manifest merger, but may needed to be manually added to your manifest");
        }
        return this.zzde.booleanValue();
    }
}
