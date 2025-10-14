package com.google.android.gms.internal.ads;

final /* synthetic */ class zzanq implements Runnable {
    private final zzaoj zzbnu;
    private final zzank zzcvl;
    private final zzanz zzcvm;

    zzanq(zzaoj zzaoj, zzank zzank, zzanz zzanz) {
        this.zzbnu = zzaoj;
        this.zzcvl = zzank;
        this.zzcvm = zzanz;
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Throwable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r3 = this;
            com.google.android.gms.internal.ads.zzaoj r2 = r3.zzbnu
            com.google.android.gms.internal.ads.zzank r0 = r3.zzcvl
            com.google.android.gms.internal.ads.zzanz r1 = r3.zzcvm
            java.lang.Object r1 = r1.get()     // Catch:{ CancellationException -> 0x0012, ExecutionException -> 0x0018, InterruptedException -> 0x0025, Exception -> 0x0031 }
            java.lang.Object r0 = r0.apply(r1)     // Catch:{ CancellationException -> 0x0012, ExecutionException -> 0x0018, InterruptedException -> 0x0025, Exception -> 0x0031 }
            r2.set(r0)     // Catch:{ CancellationException -> 0x0012, ExecutionException -> 0x0018, InterruptedException -> 0x0025, Exception -> 0x0031 }
        L_0x0011:
            return
        L_0x0012:
            r0 = move-exception
            r0 = 1
            r2.cancel(r0)
            goto L_0x0011
        L_0x0018:
            r0 = move-exception
            java.lang.Throwable r1 = r0.getCause()
            if (r1 != 0) goto L_0x0023
        L_0x001f:
            r2.setException(r0)
            goto L_0x0011
        L_0x0023:
            r0 = r1
            goto L_0x001f
        L_0x0025:
            r0 = move-exception
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            r1.interrupt()
            r2.setException(r0)
            goto L_0x0011
        L_0x0031:
            r0 = move-exception
            r2.setException(r0)
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzanq.run():void");
    }
}
