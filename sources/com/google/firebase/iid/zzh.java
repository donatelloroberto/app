package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.GuardedBy;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public final class zzh implements ServiceConnection {
    private final Context zzac;
    private final Intent zzad;
    private final ScheduledExecutorService zzae;
    private final Queue<zzd> zzaf;
    private zzf zzag;
    @GuardedBy("this")
    private boolean zzah;

    public zzh(Context context, String str) {
        this(context, str, new ScheduledThreadPoolExecutor(0, new NamedThreadFactory("Firebase-FirebaseInstanceIdServiceConnection")));
    }

    @VisibleForTesting
    private zzh(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
        this.zzaf = new ArrayDeque();
        this.zzah = false;
        this.zzac = context.getApplicationContext();
        this.zzad = new Intent(str).setPackage(this.zzac.getPackageName());
        this.zzae = scheduledExecutorService;
    }

    public final synchronized void zza(Intent intent, BroadcastReceiver.PendingResult pendingResult) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "new intent queued in the bind-strategy delivery");
        }
        this.zzaf.add(new zzd(intent, pendingResult, this.zzae));
        zzd();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005f, code lost:
        if (android.util.Log.isLoggable("EnhancedIntentService", 3) == false) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0065, code lost:
        if (r4.zzah != false) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0067, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0068, code lost:
        android.util.Log.d("EnhancedIntentService", new java.lang.StringBuilder(39).append("binder is dead. start connection? ").append(r0).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0082, code lost:
        if (r4.zzah != false) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0084, code lost:
        r4.zzah = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0095, code lost:
        if (com.google.android.gms.common.stats.ConnectionTracker.getInstance().bindService(r4.zzac, r4.zzad, r4, 65) == false) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0099, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        android.util.Log.e("EnhancedIntentService", "binding to the service failed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a9, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00aa, code lost:
        android.util.Log.e("EnhancedIntentService", "Exception while binding the service", r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized void zzd() {
        /*
            r4 = this;
            r1 = 1
            r2 = 0
            monitor-enter(r4)
            java.lang.String r0 = "EnhancedIntentService"
            r3 = 3
            boolean r0 = android.util.Log.isLoggable(r0, r3)     // Catch:{ all -> 0x0055 }
            if (r0 == 0) goto L_0x0013
            java.lang.String r0 = "EnhancedIntentService"
            java.lang.String r3 = "flush queue called"
            android.util.Log.d(r0, r3)     // Catch:{ all -> 0x0055 }
        L_0x0013:
            java.util.Queue<com.google.firebase.iid.zzd> r0 = r4.zzaf     // Catch:{ all -> 0x0055 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0055 }
            if (r0 != 0) goto L_0x0097
            java.lang.String r0 = "EnhancedIntentService"
            r3 = 3
            boolean r0 = android.util.Log.isLoggable(r0, r3)     // Catch:{ all -> 0x0055 }
            if (r0 == 0) goto L_0x002b
            java.lang.String r0 = "EnhancedIntentService"
            java.lang.String r3 = "found intent to be delivered"
            android.util.Log.d(r0, r3)     // Catch:{ all -> 0x0055 }
        L_0x002b:
            com.google.firebase.iid.zzf r0 = r4.zzag     // Catch:{ all -> 0x0055 }
            if (r0 == 0) goto L_0x0058
            com.google.firebase.iid.zzf r0 = r4.zzag     // Catch:{ all -> 0x0055 }
            boolean r0 = r0.isBinderAlive()     // Catch:{ all -> 0x0055 }
            if (r0 == 0) goto L_0x0058
            java.lang.String r0 = "EnhancedIntentService"
            r3 = 3
            boolean r0 = android.util.Log.isLoggable(r0, r3)     // Catch:{ all -> 0x0055 }
            if (r0 == 0) goto L_0x0047
            java.lang.String r0 = "EnhancedIntentService"
            java.lang.String r3 = "binder is alive, sending the intent."
            android.util.Log.d(r0, r3)     // Catch:{ all -> 0x0055 }
        L_0x0047:
            java.util.Queue<com.google.firebase.iid.zzd> r0 = r4.zzaf     // Catch:{ all -> 0x0055 }
            java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x0055 }
            com.google.firebase.iid.zzd r0 = (com.google.firebase.iid.zzd) r0     // Catch:{ all -> 0x0055 }
            com.google.firebase.iid.zzf r3 = r4.zzag     // Catch:{ all -> 0x0055 }
            r3.zza((com.google.firebase.iid.zzd) r0)     // Catch:{ all -> 0x0055 }
            goto L_0x0013
        L_0x0055:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L_0x0058:
            java.lang.String r0 = "EnhancedIntentService"
            r3 = 3
            boolean r0 = android.util.Log.isLoggable(r0, r3)     // Catch:{ all -> 0x0055 }
            if (r0 == 0) goto L_0x0080
            java.lang.String r3 = "EnhancedIntentService"
            boolean r0 = r4.zzah     // Catch:{ all -> 0x0055 }
            if (r0 != 0) goto L_0x0099
            r0 = r1
        L_0x0068:
            r1 = 39
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0055 }
            r2.<init>(r1)     // Catch:{ all -> 0x0055 }
            java.lang.String r1 = "binder is dead. start connection? "
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ all -> 0x0055 }
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ all -> 0x0055 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0055 }
            android.util.Log.d(r3, r0)     // Catch:{ all -> 0x0055 }
        L_0x0080:
            boolean r0 = r4.zzah     // Catch:{ all -> 0x0055 }
            if (r0 != 0) goto L_0x0097
            r0 = 1
            r4.zzah = r0     // Catch:{ all -> 0x0055 }
            com.google.android.gms.common.stats.ConnectionTracker r0 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ SecurityException -> 0x00a9 }
            android.content.Context r1 = r4.zzac     // Catch:{ SecurityException -> 0x00a9 }
            android.content.Intent r2 = r4.zzad     // Catch:{ SecurityException -> 0x00a9 }
            r3 = 65
            boolean r0 = r0.bindService(r1, r2, r4, r3)     // Catch:{ SecurityException -> 0x00a9 }
            if (r0 == 0) goto L_0x009b
        L_0x0097:
            monitor-exit(r4)
            return
        L_0x0099:
            r0 = r2
            goto L_0x0068
        L_0x009b:
            java.lang.String r0 = "EnhancedIntentService"
            java.lang.String r1 = "binding to the service failed"
            android.util.Log.e(r0, r1)     // Catch:{ SecurityException -> 0x00a9 }
        L_0x00a2:
            r0 = 0
            r4.zzah = r0     // Catch:{ all -> 0x0055 }
            r4.zze()     // Catch:{ all -> 0x0055 }
            goto L_0x0097
        L_0x00a9:
            r0 = move-exception
            java.lang.String r1 = "EnhancedIntentService"
            java.lang.String r2 = "Exception while binding the service"
            android.util.Log.e(r1, r2, r0)     // Catch:{ all -> 0x0055 }
            goto L_0x00a2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzh.zzd():void");
    }

    @GuardedBy("this")
    private final void zze() {
        while (!this.zzaf.isEmpty()) {
            this.zzaf.poll().finish();
        }
    }

    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            String valueOf = String.valueOf(componentName);
            Log.d("EnhancedIntentService", new StringBuilder(String.valueOf(valueOf).length() + 20).append("onServiceConnected: ").append(valueOf).toString());
        }
        this.zzah = false;
        if (!(iBinder instanceof zzf)) {
            String valueOf2 = String.valueOf(iBinder);
            Log.e("EnhancedIntentService", new StringBuilder(String.valueOf(valueOf2).length() + 28).append("Invalid service connection: ").append(valueOf2).toString());
            zze();
        } else {
            this.zzag = (zzf) iBinder;
            zzd();
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            String valueOf = String.valueOf(componentName);
            Log.d("EnhancedIntentService", new StringBuilder(String.valueOf(valueOf).length() + 23).append("onServiceDisconnected: ").append(valueOf).toString());
        }
        zzd();
    }
}
