package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.PlatformVersion;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgg {
    private final Object zzaho = new Object();
    @GuardedBy("mActivityTrackerLock")
    private zzgh zzahp = null;
    @GuardedBy("mActivityTrackerLock")
    private boolean zzahq = false;

    @Nullable
    public final Activity getActivity() {
        Activity activity = null;
        synchronized (this.zzaho) {
            if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                if (this.zzahp != null) {
                    activity = this.zzahp.getActivity();
                }
            }
        }
        return activity;
    }

    @Nullable
    public final Context getContext() {
        Context context = null;
        synchronized (this.zzaho) {
            if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                if (this.zzahp != null) {
                    context = this.zzahp.getContext();
                }
            }
        }
        return context;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void initialize(android.content.Context r5) {
        /*
            r4 = this;
            java.lang.Object r2 = r4.zzaho
            monitor-enter(r2)
            boolean r0 = r4.zzahq     // Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x0050
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich()     // Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x000f
            monitor-exit(r2)     // Catch:{ all -> 0x0023 }
        L_0x000e:
            return
        L_0x000f:
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzayg     // Catch:{ all -> 0x0023 }
            com.google.android.gms.internal.ads.zzni r1 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x0023 }
            java.lang.Object r0 = r1.zzd(r0)     // Catch:{ all -> 0x0023 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0023 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x0026
            monitor-exit(r2)     // Catch:{ all -> 0x0023 }
            goto L_0x000e
        L_0x0023:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0023 }
            throw r0
        L_0x0026:
            r1 = 0
            android.content.Context r0 = r5.getApplicationContext()     // Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x002e
            r0 = r5
        L_0x002e:
            boolean r3 = r0 instanceof android.app.Application     // Catch:{ all -> 0x0023 }
            if (r3 == 0) goto L_0x0052
            android.app.Application r0 = (android.app.Application) r0     // Catch:{ all -> 0x0023 }
        L_0x0034:
            if (r0 != 0) goto L_0x003d
            java.lang.String r0 = "Can not cast Context to Application"
            com.google.android.gms.internal.ads.zzakb.zzdk(r0)     // Catch:{ all -> 0x0023 }
            monitor-exit(r2)     // Catch:{ all -> 0x0023 }
            goto L_0x000e
        L_0x003d:
            com.google.android.gms.internal.ads.zzgh r1 = r4.zzahp     // Catch:{ all -> 0x0023 }
            if (r1 != 0) goto L_0x0048
            com.google.android.gms.internal.ads.zzgh r1 = new com.google.android.gms.internal.ads.zzgh     // Catch:{ all -> 0x0023 }
            r1.<init>()     // Catch:{ all -> 0x0023 }
            r4.zzahp = r1     // Catch:{ all -> 0x0023 }
        L_0x0048:
            com.google.android.gms.internal.ads.zzgh r1 = r4.zzahp     // Catch:{ all -> 0x0023 }
            r1.zza((android.app.Application) r0, (android.content.Context) r5)     // Catch:{ all -> 0x0023 }
            r0 = 1
            r4.zzahq = r0     // Catch:{ all -> 0x0023 }
        L_0x0050:
            monitor-exit(r2)     // Catch:{ all -> 0x0023 }
            goto L_0x000e
        L_0x0052:
            r0 = r1
            goto L_0x0034
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgg.initialize(android.content.Context):void");
    }

    public final void zza(zzgj zzgj) {
        synchronized (this.zzaho) {
            if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzayg)).booleanValue()) {
                    if (this.zzahp == null) {
                        this.zzahp = new zzgh();
                    }
                    this.zzahp.zza(zzgj);
                }
            }
        }
    }
}
