package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zza {
    private static Object zzadq = new Object();
    private static zza zzadr;
    private volatile boolean closed;
    private volatile long zzadj;
    private volatile long zzadk;
    private volatile long zzadl;
    private volatile long zzadm;
    private final Thread zzadn;
    private final Object zzado;
    private zzd zzadp;
    /* access modifiers changed from: private */
    public final Context zzrm;
    private final Clock zzsd;
    private volatile AdvertisingIdClient.Info zzvp;

    public static zza zzf(Context context) {
        if (zzadr == null) {
            synchronized (zzadq) {
                if (zzadr == null) {
                    zza zza = new zza(context);
                    zzadr = zza;
                    zza.zzadn.start();
                }
            }
        }
        return zzadr;
    }

    private zza(Context context) {
        this(context, (zzd) null, DefaultClock.getInstance());
    }

    @VisibleForTesting
    private zza(Context context, zzd zzd, Clock clock) {
        this.zzadj = 900000;
        this.zzadk = 30000;
        this.closed = false;
        this.zzado = new Object();
        this.zzadp = new zzb(this);
        this.zzsd = clock;
        if (context != null) {
            this.zzrm = context.getApplicationContext();
        } else {
            this.zzrm = context;
        }
        this.zzadl = this.zzsd.currentTimeMillis();
        this.zzadn = new Thread(new zzc(this));
    }

    public final String zzgq() {
        if (this.zzvp == null) {
            zzgr();
        } else {
            zzgs();
        }
        zzgt();
        if (this.zzvp == null) {
            return null;
        }
        return this.zzvp.getId();
    }

    public final boolean isLimitAdTrackingEnabled() {
        if (this.zzvp == null) {
            zzgr();
        } else {
            zzgs();
        }
        zzgt();
        return this.zzvp == null || this.zzvp.isLimitAdTrackingEnabled();
    }

    private final void zzgr() {
        synchronized (this) {
            try {
                if (!this.closed) {
                    zzgs();
                    wait(500);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    private final void zzgs() {
        if (this.zzsd.currentTimeMillis() - this.zzadl > this.zzadk) {
            synchronized (this.zzado) {
                this.zzado.notify();
            }
            this.zzadl = this.zzsd.currentTimeMillis();
        }
    }

    private final void zzgt() {
        if (this.zzsd.currentTimeMillis() - this.zzadm > 3600000) {
            this.zzvp = null;
        }
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:225)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public final void zzgu() {
        /*
            r4 = this;
            r0 = 10
            android.os.Process.setThreadPriority(r0)
        L_0x0005:
            boolean r0 = r4.closed
            if (r0 != 0) goto L_0x003e
            com.google.android.gms.tagmanager.zzd r0 = r4.zzadp
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r0 = r0.zzgv()
            if (r0 == 0) goto L_0x0020
            r4.zzvp = r0
            com.google.android.gms.common.util.Clock r0 = r4.zzsd
            long r0 = r0.currentTimeMillis()
            r4.zzadm = r0
            java.lang.String r0 = "Obtained fresh AdvertisingId info from GmsCore."
            com.google.android.gms.tagmanager.zzdi.zzaw(r0)
        L_0x0020:
            monitor-enter(r4)
            r4.notifyAll()     // Catch:{ all -> 0x003b }
            monitor-exit(r4)     // Catch:{ all -> 0x003b }
            java.lang.Object r1 = r4.zzado     // Catch:{ InterruptedException -> 0x0034 }
            monitor-enter(r1)     // Catch:{ InterruptedException -> 0x0034 }
            java.lang.Object r0 = r4.zzado     // Catch:{ all -> 0x0031 }
            long r2 = r4.zzadj     // Catch:{ all -> 0x0031 }
            r0.wait(r2)     // Catch:{ all -> 0x0031 }
            monitor-exit(r1)     // Catch:{ all -> 0x0031 }
            goto L_0x0005
        L_0x0031:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0031 }
            throw r0     // Catch:{ InterruptedException -> 0x0034 }
        L_0x0034:
            r0 = move-exception
            java.lang.String r0 = "sleep interrupted in AdvertiserDataPoller thread; continuing"
            com.google.android.gms.tagmanager.zzdi.zzaw(r0)
            goto L_0x0005
        L_0x003b:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x003b }
            throw r0
        L_0x003e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zza.zzgu():void");
    }

    @VisibleForTesting
    public final void close() {
        this.closed = true;
        this.zzadn.interrupt();
    }
}
