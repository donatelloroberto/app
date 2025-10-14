package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@zzadh
public final class zzxh implements zzww {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    public final long mStartTime;
    private final boolean zzael;
    private final zzwy zzbtj;
    private final boolean zzbtn;
    private final boolean zzbto;
    private final zzaef zzbuc;
    /* access modifiers changed from: private */
    public final long zzbud;
    private final int zzbue;
    /* access modifiers changed from: private */
    public boolean zzbuf = false;
    /* access modifiers changed from: private */
    public final Map<zzanz<zzxe>, zzxb> zzbug = new HashMap();
    private final String zzbuh;
    private List<zzxe> zzbui = new ArrayList();
    private final zzxn zzwh;

    public zzxh(Context context, zzaef zzaef, zzxn zzxn, zzwy zzwy, boolean z, boolean z2, String str, long j, long j2, int i, boolean z3) {
        this.mContext = context;
        this.zzbuc = zzaef;
        this.zzwh = zzxn;
        this.zzbtj = zzwy;
        this.zzael = z;
        this.zzbtn = z2;
        this.zzbuh = str;
        this.mStartTime = j;
        this.zzbud = j2;
        this.zzbue = 2;
        this.zzbto = z3;
    }

    private final void zza(zzanz<zzxe> zzanz) {
        zzakk.zzcrm.post(new zzxj(this, zzanz));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r2.hasNext() == false) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        r0 = r2.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r1 = (com.google.android.gms.internal.ads.zzxe) r0.get();
        r4.zzbui.add(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002b, code lost:
        if (r1 == null) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        if (r1.zzbtv != 0) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        zza((com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0036, code lost:
        com.google.android.gms.internal.ads.zzakb.zzc("Exception while processing an adapter; continuing with other adapters", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003f, code lost:
        zza((com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return new com.google.android.gms.internal.ads.zzxe(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
        r2 = r5.iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzxe zzi(java.util.List<com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>> r5) {
        /*
            r4 = this;
            java.lang.Object r2 = r4.mLock
            monitor-enter(r2)
            boolean r0 = r4.zzbuf     // Catch:{ all -> 0x003c }
            if (r0 == 0) goto L_0x000f
            com.google.android.gms.internal.ads.zzxe r1 = new com.google.android.gms.internal.ads.zzxe     // Catch:{ all -> 0x003c }
            r0 = -1
            r1.<init>(r0)     // Catch:{ all -> 0x003c }
            monitor-exit(r2)     // Catch:{ all -> 0x003c }
        L_0x000e:
            return r1
        L_0x000f:
            monitor-exit(r2)     // Catch:{ all -> 0x003c }
            java.util.Iterator r2 = r5.iterator()
        L_0x0014:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x003f
            java.lang.Object r0 = r2.next()
            com.google.android.gms.internal.ads.zzanz r0 = (com.google.android.gms.internal.ads.zzanz) r0
            java.lang.Object r1 = r0.get()     // Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
            com.google.android.gms.internal.ads.zzxe r1 = (com.google.android.gms.internal.ads.zzxe) r1     // Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
            java.util.List<com.google.android.gms.internal.ads.zzxe> r3 = r4.zzbui     // Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
            r3.add(r1)     // Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
            if (r1 == 0) goto L_0x0014
            int r3 = r1.zzbtv     // Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
            if (r3 != 0) goto L_0x0014
            r4.zza((com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>) r0)     // Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
            goto L_0x000e
        L_0x0035:
            r0 = move-exception
        L_0x0036:
            java.lang.String r1 = "Exception while processing an adapter; continuing with other adapters"
            com.google.android.gms.internal.ads.zzakb.zzc(r1, r0)
            goto L_0x0014
        L_0x003c:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x003c }
            throw r0
        L_0x003f:
            r0 = 0
            r4.zza((com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>) r0)
            com.google.android.gms.internal.ads.zzxe r1 = new com.google.android.gms.internal.ads.zzxe
            r0 = 1
            r1.<init>(r0)
            goto L_0x000e
        L_0x004a:
            r0 = move-exception
            goto L_0x0036
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxh.zzi(java.util.List):com.google.android.gms.internal.ads.zzxe");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        r0 = r14.zzbtj.zzbsy;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        r8 = r15.iterator();
        r6 = r0;
        r2 = null;
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r8.hasNext() == false) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002f, code lost:
        r0 = r8.next();
        r10 = com.google.android.gms.ads.internal.zzbv.zzer().currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003f, code lost:
        if (r6 != 0) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0045, code lost:
        if (r0.isDone() == false) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0047, code lost:
        r1 = (com.google.android.gms.internal.ads.zzxe) r0.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004d, code lost:
        r14.zzbui.add(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0052, code lost:
        if (r1 == null) goto L_0x00c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0056, code lost:
        if (r1.zzbtv != 0) goto L_0x00c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0058, code lost:
        r5 = r1.zzbua;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005a, code lost:
        if (r5 == null) goto L_0x00c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0060, code lost:
        if (r5.zzmm() <= r4) goto L_0x00c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0065, code lost:
        r4 = r5.zzmm();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0067, code lost:
        r6 = java.lang.Math.max(r6 - (com.google.android.gms.ads.internal.zzbv.zzer().currentTimeMillis() - r10), 0);
        r2 = r1;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x007c, code lost:
        r0 = 10000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r1 = (com.google.android.gms.internal.ads.zzxe) r0.get(r6, java.util.concurrent.TimeUnit.MILLISECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0088, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        com.google.android.gms.internal.ads.zzakb.zzc("Exception while processing an adapter; continuing with other adapters", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x008e, code lost:
        r6 = java.lang.Math.max(r6 - (com.google.android.gms.ads.internal.zzbv.zzer().currentTimeMillis() - r10), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x009f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a0, code lost:
        java.lang.Math.max(r6 - (com.google.android.gms.ads.internal.zzbv.zzer().currentTimeMillis() - r10), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ae, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00af, code lost:
        zza((com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b2, code lost:
        if (r2 != null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c2, code lost:
        r1 = r2;
        r0 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        return new com.google.android.gms.internal.ads.zzxe(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r14.zzbtj.zzbsy == -1) goto L_0x007c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzxe zzj(java.util.List<com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>> r15) {
        /*
            r14 = this;
            r5 = 0
            r4 = -1
            r12 = 0
            java.lang.Object r1 = r14.mLock
            monitor-enter(r1)
            boolean r0 = r14.zzbuf     // Catch:{ all -> 0x0079 }
            if (r0 == 0) goto L_0x0013
            com.google.android.gms.internal.ads.zzxe r2 = new com.google.android.gms.internal.ads.zzxe     // Catch:{ all -> 0x0079 }
            r0 = -1
            r2.<init>(r0)     // Catch:{ all -> 0x0079 }
            monitor-exit(r1)     // Catch:{ all -> 0x0079 }
        L_0x0012:
            return r2
        L_0x0013:
            monitor-exit(r1)     // Catch:{ all -> 0x0079 }
            com.google.android.gms.internal.ads.zzwy r0 = r14.zzbtj
            long r0 = r0.zzbsy
            r2 = -1
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x007c
            com.google.android.gms.internal.ads.zzwy r0 = r14.zzbtj
            long r0 = r0.zzbsy
        L_0x0022:
            java.util.Iterator r8 = r15.iterator()
            r6 = r0
            r2 = r5
            r3 = r5
        L_0x0029:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x00af
            java.lang.Object r0 = r8.next()
            com.google.android.gms.internal.ads.zzanz r0 = (com.google.android.gms.internal.ads.zzanz) r0
            com.google.android.gms.common.util.Clock r1 = com.google.android.gms.ads.internal.zzbv.zzer()
            long r10 = r1.currentTimeMillis()
            int r1 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r1 != 0) goto L_0x007f
            boolean r1 = r0.isDone()     // Catch:{ InterruptedException -> 0x00bc, ExecutionException -> 0x00be, RemoteException -> 0x0088, TimeoutException -> 0x00c0 }
            if (r1 == 0) goto L_0x007f
            java.lang.Object r1 = r0.get()     // Catch:{ InterruptedException -> 0x00bc, ExecutionException -> 0x00be, RemoteException -> 0x0088, TimeoutException -> 0x00c0 }
            com.google.android.gms.internal.ads.zzxe r1 = (com.google.android.gms.internal.ads.zzxe) r1     // Catch:{ InterruptedException -> 0x00bc, ExecutionException -> 0x00be, RemoteException -> 0x0088, TimeoutException -> 0x00c0 }
        L_0x004d:
            java.util.List<com.google.android.gms.internal.ads.zzxe> r5 = r14.zzbui     // Catch:{ InterruptedException -> 0x00bc, ExecutionException -> 0x00be, RemoteException -> 0x0088, TimeoutException -> 0x00c0 }
            r5.add(r1)     // Catch:{ InterruptedException -> 0x00bc, ExecutionException -> 0x00be, RemoteException -> 0x0088, TimeoutException -> 0x00c0 }
            if (r1 == 0) goto L_0x00c2
            int r5 = r1.zzbtv     // Catch:{ InterruptedException -> 0x00bc, ExecutionException -> 0x00be, RemoteException -> 0x0088, TimeoutException -> 0x00c0 }
            if (r5 != 0) goto L_0x00c2
            com.google.android.gms.internal.ads.zzxw r5 = r1.zzbua     // Catch:{ InterruptedException -> 0x00bc, ExecutionException -> 0x00be, RemoteException -> 0x0088, TimeoutException -> 0x00c0 }
            if (r5 == 0) goto L_0x00c2
            int r9 = r5.zzmm()     // Catch:{ InterruptedException -> 0x00bc, ExecutionException -> 0x00be, RemoteException -> 0x0088, TimeoutException -> 0x00c0 }
            if (r9 <= r4) goto L_0x00c2
            int r2 = r5.zzmm()     // Catch:{ InterruptedException -> 0x00bc, ExecutionException -> 0x00be, RemoteException -> 0x0088, TimeoutException -> 0x00c0 }
            r4 = r2
        L_0x0067:
            com.google.android.gms.common.util.Clock r2 = com.google.android.gms.ads.internal.zzbv.zzer()
            long r2 = r2.currentTimeMillis()
            long r2 = r2 - r10
            long r2 = r6 - r2
            long r6 = java.lang.Math.max(r2, r12)
            r2 = r1
            r3 = r0
            goto L_0x0029
        L_0x0079:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0079 }
            throw r0
        L_0x007c:
            r0 = 10000(0x2710, double:4.9407E-320)
            goto L_0x0022
        L_0x007f:
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x00bc, ExecutionException -> 0x00be, RemoteException -> 0x0088, TimeoutException -> 0x00c0 }
            java.lang.Object r1 = r0.get(r6, r1)     // Catch:{ InterruptedException -> 0x00bc, ExecutionException -> 0x00be, RemoteException -> 0x0088, TimeoutException -> 0x00c0 }
            com.google.android.gms.internal.ads.zzxe r1 = (com.google.android.gms.internal.ads.zzxe) r1     // Catch:{ InterruptedException -> 0x00bc, ExecutionException -> 0x00be, RemoteException -> 0x0088, TimeoutException -> 0x00c0 }
            goto L_0x004d
        L_0x0088:
            r0 = move-exception
        L_0x0089:
            java.lang.String r1 = "Exception while processing an adapter; continuing with other adapters"
            com.google.android.gms.internal.ads.zzakb.zzc(r1, r0)     // Catch:{ all -> 0x009f }
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.zzbv.zzer()
            long r0 = r0.currentTimeMillis()
            long r0 = r0 - r10
            long r0 = r6 - r0
            long r0 = java.lang.Math.max(r0, r12)
            r6 = r0
            goto L_0x0029
        L_0x009f:
            r0 = move-exception
            com.google.android.gms.common.util.Clock r1 = com.google.android.gms.ads.internal.zzbv.zzer()
            long r2 = r1.currentTimeMillis()
            long r2 = r2 - r10
            long r2 = r6 - r2
            java.lang.Math.max(r2, r12)
            throw r0
        L_0x00af:
            r14.zza((com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>) r3)
            if (r2 != 0) goto L_0x0012
            com.google.android.gms.internal.ads.zzxe r2 = new com.google.android.gms.internal.ads.zzxe
            r0 = 1
            r2.<init>(r0)
            goto L_0x0012
        L_0x00bc:
            r0 = move-exception
            goto L_0x0089
        L_0x00be:
            r0 = move-exception
            goto L_0x0089
        L_0x00c0:
            r0 = move-exception
            goto L_0x0089
        L_0x00c2:
            r1 = r2
            r0 = r3
            goto L_0x0067
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxh.zzj(java.util.List):com.google.android.gms.internal.ads.zzxe");
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzbuf = true;
            for (zzxb cancel : this.zzbug.values()) {
                cancel.cancel();
            }
        }
    }

    public final zzxe zzh(List<zzwx> list) {
        zzjn zzjn;
        zzakb.zzck("Starting mediation.");
        ArrayList arrayList = new ArrayList();
        zzjn zzjn2 = this.zzbuc.zzacv;
        int[] iArr = new int[2];
        if (zzjn2.zzard != null) {
            zzbv.zzfd();
            if (zzxg.zza(this.zzbuh, iArr)) {
                int i = iArr[0];
                int i2 = iArr[1];
                zzjn[] zzjnArr = zzjn2.zzard;
                int length = zzjnArr.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    zzjn = zzjnArr[i3];
                    if (i == zzjn.width && i2 == zzjn.height) {
                        break;
                    }
                    i3++;
                }
            }
        }
        zzjn = zzjn2;
        for (zzwx next : list) {
            String valueOf = String.valueOf(next.zzbrs);
            zzakb.zzdj(valueOf.length() != 0 ? "Trying mediation network: ".concat(valueOf) : new String("Trying mediation network: "));
            for (String zzxb : next.zzbrt) {
                zzxb zzxb2 = new zzxb(this.mContext, zzxb, this.zzwh, this.zzbtj, next, this.zzbuc.zzccv, zzjn, this.zzbuc.zzacr, this.zzael, this.zzbtn, this.zzbuc.zzadj, this.zzbuc.zzads, this.zzbuc.zzcdk, this.zzbuc.zzcef, this.zzbto);
                zzanz zza = zzaki.zza(new zzxi(this, zzxb2));
                this.zzbug.put(zza, zzxb2);
                arrayList.add(zza);
            }
        }
        switch (this.zzbue) {
            case 2:
                return zzj(arrayList);
            default:
                return zzi(arrayList);
        }
    }

    public final List<zzxe> zzme() {
        return this.zzbui;
    }
}
