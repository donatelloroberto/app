package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzaan {
    private static final Object sLock = new Object();
    @GuardedBy("sLock")
    @VisibleForTesting
    private static boolean zzbxg = false;
    @GuardedBy("sLock")
    @VisibleForTesting
    private static boolean zzsh = false;
    @VisibleForTesting
    private zzatn zzbxh;

    @VisibleForTesting
    private final void zzj(Context context) {
        synchronized (sLock) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbet)).booleanValue() && !zzbxg) {
                try {
                    zzbxg = true;
                    this.zzbxh = zzato.zzab(DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.ads.omid.DynamiteOmid"));
                } catch (DynamiteModule.LoadingException e) {
                    zzane.zzd("#007 Could not call remote method.", e);
                }
            }
        }
    }

    @Nullable
    public final String getVersion(Context context) {
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzbet)).booleanValue()) {
            return null;
        }
        try {
            zzj(context);
            String valueOf = String.valueOf(this.zzbxh.getVersion());
            return valueOf.length() != 0 ? "a.".concat(valueOf) : new String("a.");
        } catch (RemoteException | NullPointerException e) {
            zzane.zzd("#007 Could not call remote method.", e);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return null;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.dynamic.IObjectWrapper zza(java.lang.String r8, android.webkit.WebView r9, java.lang.String r10, java.lang.String r11, java.lang.String r12) {
        /*
            r7 = this;
            r6 = 0
            java.lang.Object r1 = sLock
            monitor-enter(r1)
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzbet     // Catch:{ all -> 0x002d }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x002d }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x002d }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x002d }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x002d }
            if (r0 == 0) goto L_0x001a
            boolean r0 = zzsh     // Catch:{ all -> 0x002d }
            if (r0 != 0) goto L_0x001d
        L_0x001a:
            monitor-exit(r1)     // Catch:{ all -> 0x002d }
            r0 = r6
        L_0x001c:
            return r0
        L_0x001d:
            monitor-exit(r1)     // Catch:{ all -> 0x002d }
            com.google.android.gms.internal.ads.zzatn r0 = r7.zzbxh     // Catch:{ RemoteException -> 0x0038, NullPointerException -> 0x0030 }
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r9)     // Catch:{ RemoteException -> 0x0038, NullPointerException -> 0x0030 }
            r1 = r8
            r3 = r10
            r4 = r11
            r5 = r12
            com.google.android.gms.dynamic.IObjectWrapper r0 = r0.zza(r1, r2, r3, r4, r5)     // Catch:{ RemoteException -> 0x0038, NullPointerException -> 0x0030 }
            goto L_0x001c
        L_0x002d:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x002d }
            throw r0
        L_0x0030:
            r0 = move-exception
        L_0x0031:
            java.lang.String r1 = "#007 Could not call remote method."
            com.google.android.gms.internal.ads.zzane.zzd(r1, r0)
            r0 = r6
            goto L_0x001c
        L_0x0038:
            r0 = move-exception
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaan.zza(java.lang.String, android.webkit.WebView, java.lang.String, java.lang.String, java.lang.String):com.google.android.gms.dynamic.IObjectWrapper");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.dynamic.IObjectWrapper r4, android.view.View r5) {
        /*
            r3 = this;
            java.lang.Object r1 = sLock
            monitor-enter(r1)
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzbet     // Catch:{ all -> 0x002d }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x002d }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x002d }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x002d }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x002d }
            if (r0 == 0) goto L_0x0019
            boolean r0 = zzsh     // Catch:{ all -> 0x002d }
            if (r0 != 0) goto L_0x001b
        L_0x0019:
            monitor-exit(r1)     // Catch:{ all -> 0x002d }
        L_0x001a:
            return
        L_0x001b:
            monitor-exit(r1)     // Catch:{ all -> 0x002d }
            com.google.android.gms.internal.ads.zzatn r0 = r3.zzbxh     // Catch:{ RemoteException -> 0x0026, NullPointerException -> 0x0030 }
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r5)     // Catch:{ RemoteException -> 0x0026, NullPointerException -> 0x0030 }
            r0.zza(r4, r1)     // Catch:{ RemoteException -> 0x0026, NullPointerException -> 0x0030 }
            goto L_0x001a
        L_0x0026:
            r0 = move-exception
        L_0x0027:
            java.lang.String r1 = "#007 Could not call remote method."
            com.google.android.gms.internal.ads.zzane.zzd(r1, r0)
            goto L_0x001a
        L_0x002d:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x002d }
            throw r0
        L_0x0030:
            r0 = move-exception
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaan.zza(com.google.android.gms.dynamic.IObjectWrapper, android.view.View):void");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzi(android.content.Context r5) {
        /*
            r4 = this;
            r1 = 0
            java.lang.Object r2 = sLock
            monitor-enter(r2)
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzbet     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzni r3 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x0020 }
            java.lang.Object r0 = r3.zzd(r0)     // Catch:{ all -> 0x0020 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0020 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x0019
            monitor-exit(r2)     // Catch:{ all -> 0x0020 }
            r0 = r1
        L_0x0018:
            return r0
        L_0x0019:
            boolean r0 = zzsh     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x0023
            r0 = 1
            monitor-exit(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x0018
        L_0x0020:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0020 }
            throw r0
        L_0x0023:
            r4.zzj(r5)     // Catch:{ RemoteException -> 0x003d, NullPointerException -> 0x0034 }
            com.google.android.gms.internal.ads.zzatn r0 = r4.zzbxh     // Catch:{ RemoteException -> 0x003d, NullPointerException -> 0x0034 }
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r5)     // Catch:{ RemoteException -> 0x003d, NullPointerException -> 0x0034 }
            boolean r0 = r0.zzy(r3)     // Catch:{ RemoteException -> 0x003d, NullPointerException -> 0x0034 }
            zzsh = r0     // Catch:{ RemoteException -> 0x003d, NullPointerException -> 0x0034 }
            monitor-exit(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x0018
        L_0x0034:
            r0 = move-exception
        L_0x0035:
            java.lang.String r3 = "#007 Could not call remote method."
            com.google.android.gms.internal.ads.zzane.zzd(r3, r0)     // Catch:{ all -> 0x0020 }
            monitor-exit(r2)     // Catch:{ all -> 0x0020 }
            r0 = r1
            goto L_0x0018
        L_0x003d:
            r0 = move-exception
            goto L_0x0035
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaan.zzi(android.content.Context):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzm(com.google.android.gms.dynamic.IObjectWrapper r4) {
        /*
            r3 = this;
            java.lang.Object r1 = sLock
            monitor-enter(r1)
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzbet     // Catch:{ all -> 0x0029 }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x0029 }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x0029 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0029 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0019
            boolean r0 = zzsh     // Catch:{ all -> 0x0029 }
            if (r0 != 0) goto L_0x001b
        L_0x0019:
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
        L_0x001a:
            return
        L_0x001b:
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            com.google.android.gms.internal.ads.zzatn r0 = r3.zzbxh     // Catch:{ RemoteException -> 0x0022, NullPointerException -> 0x002c }
            r0.zzm(r4)     // Catch:{ RemoteException -> 0x0022, NullPointerException -> 0x002c }
            goto L_0x001a
        L_0x0022:
            r0 = move-exception
        L_0x0023:
            java.lang.String r1 = "#007 Could not call remote method."
            com.google.android.gms.internal.ads.zzane.zzd(r1, r0)
            goto L_0x001a
        L_0x0029:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            throw r0
        L_0x002c:
            r0 = move-exception
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaan.zzm(com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzn(com.google.android.gms.dynamic.IObjectWrapper r4) {
        /*
            r3 = this;
            java.lang.Object r1 = sLock
            monitor-enter(r1)
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzbet     // Catch:{ all -> 0x0029 }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x0029 }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x0029 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0029 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0019
            boolean r0 = zzsh     // Catch:{ all -> 0x0029 }
            if (r0 != 0) goto L_0x001b
        L_0x0019:
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
        L_0x001a:
            return
        L_0x001b:
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            com.google.android.gms.internal.ads.zzatn r0 = r3.zzbxh     // Catch:{ RemoteException -> 0x0022, NullPointerException -> 0x002c }
            r0.zzn(r4)     // Catch:{ RemoteException -> 0x0022, NullPointerException -> 0x002c }
            goto L_0x001a
        L_0x0022:
            r0 = move-exception
        L_0x0023:
            java.lang.String r1 = "#007 Could not call remote method."
            com.google.android.gms.internal.ads.zzane.zzd(r1, r0)
            goto L_0x001a
        L_0x0029:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            throw r0
        L_0x002c:
            r0 = move-exception
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaan.zzn(com.google.android.gms.dynamic.IObjectWrapper):void");
    }
}
