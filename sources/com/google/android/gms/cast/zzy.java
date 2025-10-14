package com.google.android.gms.cast;

import android.view.Display;
import com.google.android.gms.tasks.OnCompleteListener;

final class zzy implements OnCompleteListener<Display> {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;

    zzy(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzci = castRemoteDisplayLocalService;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0046, code lost:
        r0 = r6.getResult();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004c, code lost:
        if (r0 == null) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004e, code lost:
        r5.zzci.zza(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0053, code lost:
        com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbr.set(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0060, code lost:
        if (r5.zzci.zzca == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0068, code lost:
        if (r5.zzci.zzcb == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r5.zzci.zzca.unbindService(r5.zzci.zzcb);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0084, code lost:
        com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbf.e("Cast Remote Display session created without display", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0091, code lost:
        com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbf.d("No need to unbind service, already unbound", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onComplete(@android.support.annotation.NonNull com.google.android.gms.tasks.Task<android.view.Display> r6) {
        /*
            r5 = this;
            r4 = 0
            r3 = 0
            boolean r0 = r6.isSuccessful()
            if (r0 != 0) goto L_0x0019
            com.google.android.gms.internal.cast.zzdo r0 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbf
            java.lang.String r1 = "Connection was not successful"
            java.lang.Object[] r2 = new java.lang.Object[r3]
            r0.e(r1, r2)
            com.google.android.gms.cast.CastRemoteDisplayLocalService r0 = r5.zzci
            r0.zze()
        L_0x0018:
            return
        L_0x0019:
            com.google.android.gms.internal.cast.zzdo r0 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbf
            java.lang.String r1 = "startRemoteDisplay successful"
            java.lang.Object[] r2 = new java.lang.Object[r3]
            r0.d(r1, r2)
            java.lang.Object r1 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbq
            monitor-enter(r1)
            com.google.android.gms.cast.CastRemoteDisplayLocalService r0 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzcg     // Catch:{ all -> 0x0042 }
            if (r0 != 0) goto L_0x0045
            com.google.android.gms.internal.cast.zzdo r0 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbf     // Catch:{ all -> 0x0042 }
            java.lang.String r2 = "Remote Display started but session already cancelled"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0042 }
            r0.d(r2, r3)     // Catch:{ all -> 0x0042 }
            com.google.android.gms.cast.CastRemoteDisplayLocalService r0 = r5.zzci     // Catch:{ all -> 0x0042 }
            r0.zze()     // Catch:{ all -> 0x0042 }
            monitor-exit(r1)     // Catch:{ all -> 0x0042 }
            goto L_0x0018
        L_0x0042:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0042 }
            throw r0
        L_0x0045:
            monitor-exit(r1)     // Catch:{ all -> 0x0042 }
            java.lang.Object r0 = r6.getResult()
            android.view.Display r0 = (android.view.Display) r0
            if (r0 == 0) goto L_0x0084
            com.google.android.gms.cast.CastRemoteDisplayLocalService r1 = r5.zzci
            r1.zza((android.view.Display) r0)
        L_0x0053:
            java.util.concurrent.atomic.AtomicBoolean r0 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbr
            r0.set(r3)
            com.google.android.gms.cast.CastRemoteDisplayLocalService r0 = r5.zzci
            android.content.Context r0 = r0.zzca
            if (r0 == 0) goto L_0x0018
            com.google.android.gms.cast.CastRemoteDisplayLocalService r0 = r5.zzci
            android.content.ServiceConnection r0 = r0.zzcb
            if (r0 == 0) goto L_0x0018
            com.google.android.gms.cast.CastRemoteDisplayLocalService r0 = r5.zzci     // Catch:{ IllegalArgumentException -> 0x0090 }
            android.content.Context r0 = r0.zzca     // Catch:{ IllegalArgumentException -> 0x0090 }
            com.google.android.gms.cast.CastRemoteDisplayLocalService r1 = r5.zzci     // Catch:{ IllegalArgumentException -> 0x0090 }
            android.content.ServiceConnection r1 = r1.zzcb     // Catch:{ IllegalArgumentException -> 0x0090 }
            r0.unbindService(r1)     // Catch:{ IllegalArgumentException -> 0x0090 }
        L_0x0079:
            com.google.android.gms.cast.CastRemoteDisplayLocalService r0 = r5.zzci
            android.content.ServiceConnection unused = r0.zzcb = null
            com.google.android.gms.cast.CastRemoteDisplayLocalService r0 = r5.zzci
            android.content.Context unused = r0.zzca = null
            goto L_0x0018
        L_0x0084:
            com.google.android.gms.internal.cast.zzdo r0 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbf
            java.lang.String r1 = "Cast Remote Display session created without display"
            java.lang.Object[] r2 = new java.lang.Object[r3]
            r0.e(r1, r2)
            goto L_0x0053
        L_0x0090:
            r0 = move-exception
            com.google.android.gms.internal.cast.zzdo r0 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbf
            java.lang.String r1 = "No need to unbind service, already unbound"
            java.lang.Object[] r2 = new java.lang.Object[r3]
            r0.d(r1, r2)
            goto L_0x0079
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzy.onComplete(com.google.android.gms.tasks.Task):void");
    }
}
