package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzix implements ServiceConnection, BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    final /* synthetic */ zzij zzapn;
    /* access modifiers changed from: private */
    public volatile boolean zzapt;
    private volatile zzfg zzapu;

    protected zzix(zzij zzij) {
        this.zzapn = zzij;
    }

    @MainThread
    public final void onConnected(@Nullable Bundle bundle) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnected");
        synchronized (this) {
            try {
                this.zzapu = null;
                this.zzapn.zzge().zzc((Runnable) new zzja(this, (zzez) this.zzapu.getService()));
            } catch (DeadObjectException | IllegalStateException e) {
                this.zzapu = null;
                this.zzapt = false;
            }
        }
    }

    @MainThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionFailed");
        zzfh zzjv = this.zzapn.zzacw.zzjv();
        if (zzjv != null) {
            zzjv.zziv().zzg("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zzapt = false;
            this.zzapu = null;
        }
        this.zzapn.zzge().zzc((Runnable) new zzjc(this));
    }

    @MainThread
    public final void onConnectionSuspended(int i) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
        this.zzapn.zzgf().zziy().log("Service connection suspended");
        this.zzapn.zzge().zzc((Runnable) new zzjb(this));
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008e A[SYNTHETIC, Splitter:B:39:0x008e] */
    @android.support.annotation.MainThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected(android.content.ComponentName r5, android.os.IBinder r6) {
        /*
            r4 = this;
            r1 = 0
            java.lang.String r0 = "MeasurementServiceConnection.onServiceConnected"
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r0)
            monitor-enter(r4)
            if (r6 != 0) goto L_0x001d
            r0 = 0
            r4.zzapt = r0     // Catch:{ all -> 0x0055 }
            com.google.android.gms.internal.measurement.zzij r0 = r4.zzapn     // Catch:{ all -> 0x0055 }
            com.google.android.gms.internal.measurement.zzfh r0 = r0.zzgf()     // Catch:{ all -> 0x0055 }
            com.google.android.gms.internal.measurement.zzfj r0 = r0.zzis()     // Catch:{ all -> 0x0055 }
            java.lang.String r1 = "Service connected with null binder"
            r0.log(r1)     // Catch:{ all -> 0x0055 }
            monitor-exit(r4)     // Catch:{ all -> 0x0055 }
        L_0x001c:
            return
        L_0x001d:
            java.lang.String r0 = r6.getInterfaceDescriptor()     // Catch:{ RemoteException -> 0x006b }
            java.lang.String r2 = "com.google.android.gms.measurement.internal.IMeasurementService"
            boolean r2 = r2.equals(r0)     // Catch:{ RemoteException -> 0x006b }
            if (r2 == 0) goto L_0x007d
            if (r6 != 0) goto L_0x0058
            r0 = r1
        L_0x002c:
            com.google.android.gms.internal.measurement.zzij r1 = r4.zzapn     // Catch:{ RemoteException -> 0x009f }
            com.google.android.gms.internal.measurement.zzfh r1 = r1.zzgf()     // Catch:{ RemoteException -> 0x009f }
            com.google.android.gms.internal.measurement.zzfj r1 = r1.zziz()     // Catch:{ RemoteException -> 0x009f }
            java.lang.String r2 = "Bound to IMeasurementService interface"
            r1.log(r2)     // Catch:{ RemoteException -> 0x009f }
        L_0x003b:
            if (r0 != 0) goto L_0x008e
            r0 = 0
            r4.zzapt = r0     // Catch:{ all -> 0x0055 }
            com.google.android.gms.common.stats.ConnectionTracker r0 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ IllegalArgumentException -> 0x009d }
            com.google.android.gms.internal.measurement.zzij r1 = r4.zzapn     // Catch:{ IllegalArgumentException -> 0x009d }
            android.content.Context r1 = r1.getContext()     // Catch:{ IllegalArgumentException -> 0x009d }
            com.google.android.gms.internal.measurement.zzij r2 = r4.zzapn     // Catch:{ IllegalArgumentException -> 0x009d }
            com.google.android.gms.internal.measurement.zzix r2 = r2.zzapg     // Catch:{ IllegalArgumentException -> 0x009d }
            r0.unbindService(r1, r2)     // Catch:{ IllegalArgumentException -> 0x009d }
        L_0x0053:
            monitor-exit(r4)     // Catch:{ all -> 0x0055 }
            goto L_0x001c
        L_0x0055:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0055 }
            throw r0
        L_0x0058:
            java.lang.String r0 = "com.google.android.gms.measurement.internal.IMeasurementService"
            android.os.IInterface r0 = r6.queryLocalInterface(r0)     // Catch:{ RemoteException -> 0x006b }
            boolean r2 = r0 instanceof com.google.android.gms.internal.measurement.zzez     // Catch:{ RemoteException -> 0x006b }
            if (r2 == 0) goto L_0x0065
            com.google.android.gms.internal.measurement.zzez r0 = (com.google.android.gms.internal.measurement.zzez) r0     // Catch:{ RemoteException -> 0x006b }
            goto L_0x002c
        L_0x0065:
            com.google.android.gms.internal.measurement.zzfb r0 = new com.google.android.gms.internal.measurement.zzfb     // Catch:{ RemoteException -> 0x006b }
            r0.<init>(r6)     // Catch:{ RemoteException -> 0x006b }
            goto L_0x002c
        L_0x006b:
            r0 = move-exception
            r0 = r1
        L_0x006d:
            com.google.android.gms.internal.measurement.zzij r1 = r4.zzapn     // Catch:{ all -> 0x0055 }
            com.google.android.gms.internal.measurement.zzfh r1 = r1.zzgf()     // Catch:{ all -> 0x0055 }
            com.google.android.gms.internal.measurement.zzfj r1 = r1.zzis()     // Catch:{ all -> 0x0055 }
            java.lang.String r2 = "Service connect failed to get IMeasurementService"
            r1.log(r2)     // Catch:{ all -> 0x0055 }
            goto L_0x003b
        L_0x007d:
            com.google.android.gms.internal.measurement.zzij r2 = r4.zzapn     // Catch:{ RemoteException -> 0x006b }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ RemoteException -> 0x006b }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zzis()     // Catch:{ RemoteException -> 0x006b }
            java.lang.String r3 = "Got binder with a wrong descriptor"
            r2.zzg(r3, r0)     // Catch:{ RemoteException -> 0x006b }
            r0 = r1
            goto L_0x003b
        L_0x008e:
            com.google.android.gms.internal.measurement.zzij r1 = r4.zzapn     // Catch:{ all -> 0x0055 }
            com.google.android.gms.internal.measurement.zzgh r1 = r1.zzge()     // Catch:{ all -> 0x0055 }
            com.google.android.gms.internal.measurement.zziy r2 = new com.google.android.gms.internal.measurement.zziy     // Catch:{ all -> 0x0055 }
            r2.<init>(r4, r0)     // Catch:{ all -> 0x0055 }
            r1.zzc((java.lang.Runnable) r2)     // Catch:{ all -> 0x0055 }
            goto L_0x0053
        L_0x009d:
            r0 = move-exception
            goto L_0x0053
        L_0x009f:
            r1 = move-exception
            goto L_0x006d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzix.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
        this.zzapn.zzgf().zziy().log("Service disconnected");
        this.zzapn.zzge().zzc((Runnable) new zziz(this, componentName));
    }

    @WorkerThread
    public final void zzc(Intent intent) {
        this.zzapn.zzab();
        Context context = this.zzapn.getContext();
        ConnectionTracker instance = ConnectionTracker.getInstance();
        synchronized (this) {
            if (this.zzapt) {
                this.zzapn.zzgf().zziz().log("Connection attempt already in progress");
                return;
            }
            this.zzapn.zzgf().zziz().log("Using local app measurement service");
            this.zzapt = true;
            instance.bindService(context, intent, this.zzapn.zzapg, 129);
        }
    }

    @WorkerThread
    public final void zzkq() {
        this.zzapn.zzab();
        Context context = this.zzapn.getContext();
        synchronized (this) {
            if (this.zzapt) {
                this.zzapn.zzgf().zziz().log("Connection attempt already in progress");
            } else if (this.zzapu != null) {
                this.zzapn.zzgf().zziz().log("Already awaiting connection attempt");
            } else {
                this.zzapu = new zzfg(context, Looper.getMainLooper(), this, this);
                this.zzapn.zzgf().zziz().log("Connecting to remote service");
                this.zzapt = true;
                this.zzapu.checkAvailabilityAndConnect();
            }
        }
    }
}
