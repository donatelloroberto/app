package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;

final class zzgl extends Thread {
    private final /* synthetic */ zzgh zzami;
    private final Object zzaml = new Object();
    private final BlockingQueue<zzgk<?>> zzamm;

    public zzgl(zzgh zzgh, String str, BlockingQueue<zzgk<?>> blockingQueue) {
        this.zzami = zzgh;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(blockingQueue);
        this.zzamm = blockingQueue;
        setName(str);
    }

    private final void zza(InterruptedException interruptedException) {
        this.zzami.zzgf().zziv().zzg(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x008b, code lost:
        r1 = r6.zzami.zzamd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0091, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r6.zzami.zzame.release();
        r6.zzami.zzamd.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00aa, code lost:
        if (r6 != r6.zzami.zzalx) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ac, code lost:
        com.google.android.gms.internal.measurement.zzgl unused = r6.zzami.zzalx = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00b2, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00b3, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00c2, code lost:
        if (r6 != r6.zzami.zzaly) goto L_0x00ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00c4, code lost:
        com.google.android.gms.internal.measurement.zzgl unused = r6.zzami.zzaly = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        r6.zzami.zzgf().zzis().log("Current scheduler thread is neither worker nor network");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r6 = this;
            r0 = 0
            r1 = r0
        L_0x0002:
            if (r1 != 0) goto L_0x0015
            com.google.android.gms.internal.measurement.zzgh r0 = r6.zzami     // Catch:{ InterruptedException -> 0x0010 }
            java.util.concurrent.Semaphore r0 = r0.zzame     // Catch:{ InterruptedException -> 0x0010 }
            r0.acquire()     // Catch:{ InterruptedException -> 0x0010 }
            r0 = 1
            r1 = r0
            goto L_0x0002
        L_0x0010:
            r0 = move-exception
            r6.zza(r0)
            goto L_0x0002
        L_0x0015:
            int r0 = android.os.Process.myTid()     // Catch:{ all -> 0x0033 }
            int r2 = android.os.Process.getThreadPriority(r0)     // Catch:{ all -> 0x0033 }
        L_0x001d:
            java.util.concurrent.BlockingQueue<com.google.android.gms.internal.measurement.zzgk<?>> r0 = r6.zzamm     // Catch:{ all -> 0x0033 }
            java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x0033 }
            com.google.android.gms.internal.measurement.zzgk r0 = (com.google.android.gms.internal.measurement.zzgk) r0     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x0060
            boolean r1 = r0.zzamk     // Catch:{ all -> 0x0033 }
            if (r1 == 0) goto L_0x005d
            r1 = r2
        L_0x002c:
            android.os.Process.setThreadPriority(r1)     // Catch:{ all -> 0x0033 }
            r0.run()     // Catch:{ all -> 0x0033 }
            goto L_0x001d
        L_0x0033:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzgh r1 = r6.zzami
            java.lang.Object r1 = r1.zzamd
            monitor-enter(r1)
            com.google.android.gms.internal.measurement.zzgh r2 = r6.zzami     // Catch:{ all -> 0x00f4 }
            java.util.concurrent.Semaphore r2 = r2.zzame     // Catch:{ all -> 0x00f4 }
            r2.release()     // Catch:{ all -> 0x00f4 }
            com.google.android.gms.internal.measurement.zzgh r2 = r6.zzami     // Catch:{ all -> 0x00f4 }
            java.lang.Object r2 = r2.zzamd     // Catch:{ all -> 0x00f4 }
            r2.notifyAll()     // Catch:{ all -> 0x00f4 }
            com.google.android.gms.internal.measurement.zzgh r2 = r6.zzami     // Catch:{ all -> 0x00f4 }
            com.google.android.gms.internal.measurement.zzgl r2 = r2.zzalx     // Catch:{ all -> 0x00f4 }
            if (r6 != r2) goto L_0x00e4
            com.google.android.gms.internal.measurement.zzgh r2 = r6.zzami     // Catch:{ all -> 0x00f4 }
            r3 = 0
            com.google.android.gms.internal.measurement.zzgl unused = r2.zzalx = null     // Catch:{ all -> 0x00f4 }
        L_0x005b:
            monitor-exit(r1)     // Catch:{ all -> 0x00f4 }
            throw r0
        L_0x005d:
            r1 = 10
            goto L_0x002c
        L_0x0060:
            java.lang.Object r1 = r6.zzaml     // Catch:{ all -> 0x0033 }
            monitor-enter(r1)     // Catch:{ all -> 0x0033 }
            java.util.concurrent.BlockingQueue<com.google.android.gms.internal.measurement.zzgk<?>> r0 = r6.zzamm     // Catch:{ all -> 0x00b9 }
            java.lang.Object r0 = r0.peek()     // Catch:{ all -> 0x00b9 }
            if (r0 != 0) goto L_0x007a
            com.google.android.gms.internal.measurement.zzgh r0 = r6.zzami     // Catch:{ all -> 0x00b9 }
            boolean r0 = r0.zzamf     // Catch:{ all -> 0x00b9 }
            if (r0 != 0) goto L_0x007a
            java.lang.Object r0 = r6.zzaml     // Catch:{ InterruptedException -> 0x00b4 }
            r4 = 30000(0x7530, double:1.4822E-319)
            r0.wait(r4)     // Catch:{ InterruptedException -> 0x00b4 }
        L_0x007a:
            monitor-exit(r1)     // Catch:{ all -> 0x00b9 }
            com.google.android.gms.internal.measurement.zzgh r0 = r6.zzami     // Catch:{ all -> 0x0033 }
            java.lang.Object r1 = r0.zzamd     // Catch:{ all -> 0x0033 }
            monitor-enter(r1)     // Catch:{ all -> 0x0033 }
            java.util.concurrent.BlockingQueue<com.google.android.gms.internal.measurement.zzgk<?>> r0 = r6.zzamm     // Catch:{ all -> 0x00e1 }
            java.lang.Object r0 = r0.peek()     // Catch:{ all -> 0x00e1 }
            if (r0 != 0) goto L_0x00de
            monitor-exit(r1)     // Catch:{ all -> 0x00e1 }
            com.google.android.gms.internal.measurement.zzgh r0 = r6.zzami
            java.lang.Object r1 = r0.zzamd
            monitor-enter(r1)
            com.google.android.gms.internal.measurement.zzgh r0 = r6.zzami     // Catch:{ all -> 0x00cb }
            java.util.concurrent.Semaphore r0 = r0.zzame     // Catch:{ all -> 0x00cb }
            r0.release()     // Catch:{ all -> 0x00cb }
            com.google.android.gms.internal.measurement.zzgh r0 = r6.zzami     // Catch:{ all -> 0x00cb }
            java.lang.Object r0 = r0.zzamd     // Catch:{ all -> 0x00cb }
            r0.notifyAll()     // Catch:{ all -> 0x00cb }
            com.google.android.gms.internal.measurement.zzgh r0 = r6.zzami     // Catch:{ all -> 0x00cb }
            com.google.android.gms.internal.measurement.zzgl r0 = r0.zzalx     // Catch:{ all -> 0x00cb }
            if (r6 != r0) goto L_0x00bc
            com.google.android.gms.internal.measurement.zzgh r0 = r6.zzami     // Catch:{ all -> 0x00cb }
            r2 = 0
            com.google.android.gms.internal.measurement.zzgl unused = r0.zzalx = null     // Catch:{ all -> 0x00cb }
        L_0x00b2:
            monitor-exit(r1)     // Catch:{ all -> 0x00cb }
            return
        L_0x00b4:
            r0 = move-exception
            r6.zza(r0)     // Catch:{ all -> 0x00b9 }
            goto L_0x007a
        L_0x00b9:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00b9 }
            throw r0     // Catch:{ all -> 0x0033 }
        L_0x00bc:
            com.google.android.gms.internal.measurement.zzgh r0 = r6.zzami     // Catch:{ all -> 0x00cb }
            com.google.android.gms.internal.measurement.zzgl r0 = r0.zzaly     // Catch:{ all -> 0x00cb }
            if (r6 != r0) goto L_0x00ce
            com.google.android.gms.internal.measurement.zzgh r0 = r6.zzami     // Catch:{ all -> 0x00cb }
            r2 = 0
            com.google.android.gms.internal.measurement.zzgl unused = r0.zzaly = null     // Catch:{ all -> 0x00cb }
            goto L_0x00b2
        L_0x00cb:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00cb }
            throw r0
        L_0x00ce:
            com.google.android.gms.internal.measurement.zzgh r0 = r6.zzami     // Catch:{ all -> 0x00cb }
            com.google.android.gms.internal.measurement.zzfh r0 = r0.zzgf()     // Catch:{ all -> 0x00cb }
            com.google.android.gms.internal.measurement.zzfj r0 = r0.zzis()     // Catch:{ all -> 0x00cb }
            java.lang.String r2 = "Current scheduler thread is neither worker nor network"
            r0.log(r2)     // Catch:{ all -> 0x00cb }
            goto L_0x00b2
        L_0x00de:
            monitor-exit(r1)     // Catch:{ all -> 0x00e1 }
            goto L_0x001d
        L_0x00e1:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00e1 }
            throw r0     // Catch:{ all -> 0x0033 }
        L_0x00e4:
            com.google.android.gms.internal.measurement.zzgh r2 = r6.zzami     // Catch:{ all -> 0x00f4 }
            com.google.android.gms.internal.measurement.zzgl r2 = r2.zzaly     // Catch:{ all -> 0x00f4 }
            if (r6 != r2) goto L_0x00f7
            com.google.android.gms.internal.measurement.zzgh r2 = r6.zzami     // Catch:{ all -> 0x00f4 }
            r3 = 0
            com.google.android.gms.internal.measurement.zzgl unused = r2.zzaly = null     // Catch:{ all -> 0x00f4 }
            goto L_0x005b
        L_0x00f4:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00f4 }
            throw r0
        L_0x00f7:
            com.google.android.gms.internal.measurement.zzgh r2 = r6.zzami     // Catch:{ all -> 0x00f4 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x00f4 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zzis()     // Catch:{ all -> 0x00f4 }
            java.lang.String r3 = "Current scheduler thread is neither worker nor network"
            r2.log(r3)     // Catch:{ all -> 0x00f4 }
            goto L_0x005b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgl.run():void");
    }

    public final void zzju() {
        synchronized (this.zzaml) {
            this.zzaml.notifyAll();
        }
    }
}
