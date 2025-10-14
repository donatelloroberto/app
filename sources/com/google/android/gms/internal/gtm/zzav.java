package com.google.android.gms.internal.gtm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzav implements ServiceConnection {
    final /* synthetic */ zzat zzxe;
    private volatile zzce zzxf;
    private volatile boolean zzxg;

    protected zzav(zzat zzat) {
        this.zzxe = zzat;
    }

    public final zzce zzdq() {
        zzce zzce = null;
        zzk.zzav();
        Intent intent = new Intent("com.google.android.gms.analytics.service.START");
        intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
        Context context = this.zzxe.getContext();
        intent.putExtra("app_package_name", context.getPackageName());
        ConnectionTracker instance = ConnectionTracker.getInstance();
        synchronized (this) {
            this.zzxf = null;
            this.zzxg = true;
            boolean bindService = instance.bindService(context, intent, this.zzxe.zzxa, 129);
            this.zzxe.zza("Bind to service requested", Boolean.valueOf(bindService));
            if (!bindService) {
                this.zzxg = false;
            } else {
                try {
                    wait(zzby.zzaak.get().longValue());
                } catch (InterruptedException e) {
                    this.zzxe.zzt("Wait for service connect was interrupted");
                }
                this.zzxg = false;
                zzce = this.zzxf;
                this.zzxf = null;
                if (zzce == null) {
                    this.zzxe.zzu("Successfully bound to service but never got onServiceConnected callback");
                }
            }
        }
        return zzce;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002d A[SYNTHETIC, Splitter:B:16:0x002d] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0073 A[SYNTHETIC, Splitter:B:42:0x0073] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected(android.content.ComponentName r5, android.os.IBinder r6) {
        /*
            r4 = this;
            r1 = 0
            java.lang.String r0 = "AnalyticsServiceConnection.onServiceConnected"
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r0)
            monitor-enter(r4)
            if (r6 != 0) goto L_0x0015
            com.google.android.gms.internal.gtm.zzat r0 = r4.zzxe     // Catch:{ all -> 0x0065 }
            java.lang.String r1 = "Service connected with null binder"
            r0.zzu(r1)     // Catch:{ all -> 0x0065 }
            r4.notifyAll()     // Catch:{ all -> 0x0045 }
            monitor-exit(r4)     // Catch:{ all -> 0x0045 }
        L_0x0014:
            return
        L_0x0015:
            java.lang.String r0 = r6.getInterfaceDescriptor()     // Catch:{ RemoteException -> 0x005b }
            java.lang.String r2 = "com.google.android.gms.analytics.internal.IAnalyticsService"
            boolean r2 = r2.equals(r0)     // Catch:{ RemoteException -> 0x005b }
            if (r2 == 0) goto L_0x006a
            if (r6 != 0) goto L_0x0048
            r0 = r1
        L_0x0024:
            com.google.android.gms.internal.gtm.zzat r1 = r4.zzxe     // Catch:{ RemoteException -> 0x0092 }
            java.lang.String r2 = "Bound to IAnalyticsService interface"
            r1.zzq(r2)     // Catch:{ RemoteException -> 0x0092 }
        L_0x002b:
            if (r0 != 0) goto L_0x0073
            com.google.android.gms.common.stats.ConnectionTracker r0 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ IllegalArgumentException -> 0x0090 }
            com.google.android.gms.internal.gtm.zzat r1 = r4.zzxe     // Catch:{ IllegalArgumentException -> 0x0090 }
            android.content.Context r1 = r1.getContext()     // Catch:{ IllegalArgumentException -> 0x0090 }
            com.google.android.gms.internal.gtm.zzat r2 = r4.zzxe     // Catch:{ IllegalArgumentException -> 0x0090 }
            com.google.android.gms.internal.gtm.zzav r2 = r2.zzxa     // Catch:{ IllegalArgumentException -> 0x0090 }
            r0.unbindService(r1, r2)     // Catch:{ IllegalArgumentException -> 0x0090 }
        L_0x0040:
            r4.notifyAll()     // Catch:{ all -> 0x0045 }
            monitor-exit(r4)     // Catch:{ all -> 0x0045 }
            goto L_0x0014
        L_0x0045:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0045 }
            throw r0
        L_0x0048:
            java.lang.String r0 = "com.google.android.gms.analytics.internal.IAnalyticsService"
            android.os.IInterface r0 = r6.queryLocalInterface(r0)     // Catch:{ RemoteException -> 0x005b }
            boolean r2 = r0 instanceof com.google.android.gms.internal.gtm.zzce     // Catch:{ RemoteException -> 0x005b }
            if (r2 == 0) goto L_0x0055
            com.google.android.gms.internal.gtm.zzce r0 = (com.google.android.gms.internal.gtm.zzce) r0     // Catch:{ RemoteException -> 0x005b }
            goto L_0x0024
        L_0x0055:
            com.google.android.gms.internal.gtm.zzcf r0 = new com.google.android.gms.internal.gtm.zzcf     // Catch:{ RemoteException -> 0x005b }
            r0.<init>(r6)     // Catch:{ RemoteException -> 0x005b }
            goto L_0x0024
        L_0x005b:
            r0 = move-exception
            r0 = r1
        L_0x005d:
            com.google.android.gms.internal.gtm.zzat r1 = r4.zzxe     // Catch:{ all -> 0x0065 }
            java.lang.String r2 = "Service connect failed to get IAnalyticsService"
            r1.zzu(r2)     // Catch:{ all -> 0x0065 }
            goto L_0x002b
        L_0x0065:
            r0 = move-exception
            r4.notifyAll()     // Catch:{ all -> 0x0045 }
            throw r0     // Catch:{ all -> 0x0045 }
        L_0x006a:
            com.google.android.gms.internal.gtm.zzat r2 = r4.zzxe     // Catch:{ RemoteException -> 0x005b }
            java.lang.String r3 = "Got binder with a wrong descriptor"
            r2.zze(r3, r0)     // Catch:{ RemoteException -> 0x005b }
            r0 = r1
            goto L_0x002b
        L_0x0073:
            boolean r1 = r4.zzxg     // Catch:{ all -> 0x0065 }
            if (r1 != 0) goto L_0x008d
            com.google.android.gms.internal.gtm.zzat r1 = r4.zzxe     // Catch:{ all -> 0x0065 }
            java.lang.String r2 = "onServiceConnected received after the timeout limit"
            r1.zzt(r2)     // Catch:{ all -> 0x0065 }
            com.google.android.gms.internal.gtm.zzat r1 = r4.zzxe     // Catch:{ all -> 0x0065 }
            com.google.android.gms.analytics.zzk r1 = r1.zzcq()     // Catch:{ all -> 0x0065 }
            com.google.android.gms.internal.gtm.zzaw r2 = new com.google.android.gms.internal.gtm.zzaw     // Catch:{ all -> 0x0065 }
            r2.<init>(r4, r0)     // Catch:{ all -> 0x0065 }
            r1.zza((java.lang.Runnable) r2)     // Catch:{ all -> 0x0065 }
            goto L_0x0040
        L_0x008d:
            r4.zzxf = r0     // Catch:{ all -> 0x0065 }
            goto L_0x0040
        L_0x0090:
            r0 = move-exception
            goto L_0x0040
        L_0x0092:
            r1 = move-exception
            goto L_0x005d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzav.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("AnalyticsServiceConnection.onServiceDisconnected");
        this.zzxe.zzcq().zza((Runnable) new zzax(this, componentName));
    }
}
