package com.google.android.gms.internal.cast;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Locale;

public final class zzdt {
    private static final Object zzabh = new Object();
    private static final zzdo zzbf = new zzdo("RequestTracker");
    private final Handler handler = new zzep(Looper.getMainLooper());
    private long zzabf;
    @VisibleForTesting
    private zzdu zzabg;
    @VisibleForTesting
    private Runnable zzqg;
    @VisibleForTesting
    private long zzxh = -1;

    public zzdt(long j) {
        this.zzabf = j;
    }

    public final void zza(long j, zzdu zzdu) {
        zzdu zzdu2;
        long j2;
        synchronized (zzabh) {
            zzdu2 = this.zzabg;
            j2 = this.zzxh;
            this.zzxh = j;
            this.zzabg = zzdu;
        }
        if (zzdu2 != null) {
            zzdu2.zzb(j2);
        }
        synchronized (zzabh) {
            if (this.zzqg != null) {
                this.handler.removeCallbacks(this.zzqg);
            }
            this.zzqg = new zzdw(this);
            this.handler.postDelayed(this.zzqg, this.zzabf);
        }
    }

    public final boolean zzeo() {
        boolean z;
        synchronized (zzabh) {
            z = this.zzxh != -1;
        }
        return z;
    }

    public final boolean test(long j) {
        boolean z;
        synchronized (zzabh) {
            z = this.zzxh != -1 && this.zzxh == j;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzc(long r10, int r12, java.lang.Object r13) {
        /*
            r9 = this;
            r0 = 1
            r1 = 0
            java.lang.Object r2 = zzabh
            monitor-enter(r2)
            long r4 = r9.zzxh     // Catch:{ all -> 0x002d }
            r6 = -1
            int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r3 == 0) goto L_0x002a
            long r4 = r9.zzxh     // Catch:{ all -> 0x002d }
            int r3 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r3 != 0) goto L_0x002a
            java.util.Locale r1 = java.util.Locale.ROOT     // Catch:{ all -> 0x002d }
            java.lang.String r3 = "request %d completed"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x002d }
            r5 = 0
            java.lang.Long r6 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x002d }
            r4[r5] = r6     // Catch:{ all -> 0x002d }
            java.lang.String r1 = java.lang.String.format(r1, r3, r4)     // Catch:{ all -> 0x002d }
            r9.zza(r12, r13, r1)     // Catch:{ all -> 0x002d }
            monitor-exit(r2)     // Catch:{ all -> 0x002d }
        L_0x0029:
            return r0
        L_0x002a:
            monitor-exit(r2)     // Catch:{ all -> 0x002d }
            r0 = r1
            goto L_0x0029
        L_0x002d:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x002d }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzdt.zzc(long, int, java.lang.Object):boolean");
    }

    public final boolean zzx(int i) {
        return zza((int) CastStatusCodes.CANCELED, (Object) null);
    }

    private final boolean zza(int i, Object obj) {
        synchronized (zzabh) {
            if (this.zzxh == -1) {
                return false;
            }
            zza(i, (Object) null, String.format(Locale.ROOT, "clearing request %d", new Object[]{Long.valueOf(this.zzxh)}));
            return true;
        }
    }

    private final void zza(int i, Object obj, String str) {
        zzbf.d(str, new Object[0]);
        synchronized (zzabh) {
            if (this.zzabg != null) {
                this.zzabg.zza(this.zzxh, i, obj);
            }
            this.zzxh = -1;
            this.zzabg = null;
            synchronized (zzabh) {
                if (this.zzqg != null) {
                    this.handler.removeCallbacks(this.zzqg);
                    this.zzqg = null;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzep() {
        synchronized (zzabh) {
            if (this.zzxh != -1) {
                zza(15, (Object) null);
            }
        }
    }
}
