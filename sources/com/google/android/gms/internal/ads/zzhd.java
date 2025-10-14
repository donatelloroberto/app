package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzhd {
    @Nullable
    @GuardedBy("mLock")
    private Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private final Runnable zzajq = new zzhe(this);
    /* access modifiers changed from: private */
    @Nullable
    @GuardedBy("mLock")
    public zzhk zzajr;
    /* access modifiers changed from: private */
    @Nullable
    @GuardedBy("mLock")
    public zzho zzajs;

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void connect() {
        /*
            r6 = this;
            java.lang.Object r1 = r6.mLock
            monitor-enter(r1)
            android.content.Context r0 = r6.mContext     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x000b
            com.google.android.gms.internal.ads.zzhk r0 = r6.zzajr     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x000d
        L_0x000b:
            monitor-exit(r1)     // Catch:{ all -> 0x002f }
        L_0x000c:
            return
        L_0x000d:
            com.google.android.gms.internal.ads.zzhg r0 = new com.google.android.gms.internal.ads.zzhg     // Catch:{ all -> 0x002f }
            r0.<init>(r6)     // Catch:{ all -> 0x002f }
            com.google.android.gms.internal.ads.zzhh r2 = new com.google.android.gms.internal.ads.zzhh     // Catch:{ all -> 0x002f }
            r2.<init>(r6)     // Catch:{ all -> 0x002f }
            com.google.android.gms.internal.ads.zzhk r3 = new com.google.android.gms.internal.ads.zzhk     // Catch:{ all -> 0x002f }
            android.content.Context r4 = r6.mContext     // Catch:{ all -> 0x002f }
            com.google.android.gms.internal.ads.zzamg r5 = com.google.android.gms.ads.internal.zzbv.zzez()     // Catch:{ all -> 0x002f }
            android.os.Looper r5 = r5.zzsa()     // Catch:{ all -> 0x002f }
            r3.<init>(r4, r5, r0, r2)     // Catch:{ all -> 0x002f }
            r6.zzajr = r3     // Catch:{ all -> 0x002f }
            com.google.android.gms.internal.ads.zzhk r0 = r6.zzajr     // Catch:{ all -> 0x002f }
            r0.checkAvailabilityAndConnect()     // Catch:{ all -> 0x002f }
            monitor-exit(r1)     // Catch:{ all -> 0x002f }
            goto L_0x000c
        L_0x002f:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x002f }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhd.connect():void");
    }

    /* access modifiers changed from: private */
    public final void disconnect() {
        synchronized (this.mLock) {
            if (this.zzajr != null) {
                if (this.zzajr.isConnected() || this.zzajr.isConnecting()) {
                    this.zzajr.disconnect();
                }
                this.zzajr = null;
                this.zzajs = null;
                Binder.flushPendingCommands();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void initialize(android.content.Context r4) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x0003
        L_0x0002:
            return
        L_0x0003:
            java.lang.Object r1 = r3.mLock
            monitor-enter(r1)
            android.content.Context r0 = r3.mContext     // Catch:{ all -> 0x000c }
            if (r0 == 0) goto L_0x000f
            monitor-exit(r1)     // Catch:{ all -> 0x000c }
            goto L_0x0002
        L_0x000c:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x000c }
            throw r0
        L_0x000f:
            android.content.Context r0 = r4.getApplicationContext()     // Catch:{ all -> 0x000c }
            r3.mContext = r0     // Catch:{ all -> 0x000c }
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzbdo     // Catch:{ all -> 0x000c }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x000c }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x000c }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x000c }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x000c }
            if (r0 == 0) goto L_0x002c
            r3.connect()     // Catch:{ all -> 0x000c }
        L_0x002a:
            monitor-exit(r1)     // Catch:{ all -> 0x000c }
            goto L_0x0002
        L_0x002c:
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzbdn     // Catch:{ all -> 0x000c }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x000c }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x000c }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x000c }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x000c }
            if (r0 == 0) goto L_0x002a
            com.google.android.gms.internal.ads.zzhf r0 = new com.google.android.gms.internal.ads.zzhf     // Catch:{ all -> 0x000c }
            r0.<init>(r3)     // Catch:{ all -> 0x000c }
            com.google.android.gms.internal.ads.zzgg r2 = com.google.android.gms.ads.internal.zzbv.zzen()     // Catch:{ all -> 0x000c }
            r2.zza(r0)     // Catch:{ all -> 0x000c }
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhd.initialize(android.content.Context):void");
    }

    public final zzhi zza(zzhl zzhl) {
        zzhi zzhi;
        synchronized (this.mLock) {
            if (this.zzajs == null) {
                zzhi = new zzhi();
            } else {
                try {
                    zzhi = this.zzajs.zza(zzhl);
                } catch (RemoteException e) {
                    zzakb.zzb("Unable to call into cache service.", e);
                    zzhi = new zzhi();
                }
            }
        }
        return zzhi;
    }

    public final void zzhh() {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbdp)).booleanValue()) {
            synchronized (this.mLock) {
                connect();
                zzbv.zzek();
                zzakk.zzcrm.removeCallbacks(this.zzajq);
                zzbv.zzek();
                zzakk.zzcrm.postDelayed(this.zzajq, ((Long) zzkb.zzik().zzd(zznk.zzbdq)).longValue());
            }
        }
    }
}
