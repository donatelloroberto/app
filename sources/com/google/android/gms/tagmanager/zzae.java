package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.gtm.zzk;

final class zzae implements zzdh<zzk> {
    private final /* synthetic */ zzy zzafg;

    private zzae(zzy zzy) {
        this.zzafg = zzy;
    }

    public final void zzs(int i) {
        if (i == zzcz.zzahw) {
            this.zzafg.zzaex.zzhn();
        }
        synchronized (this.zzafg) {
            if (!this.zzafg.isReady()) {
                if (this.zzafg.zzafa != null) {
                    this.zzafg.setResult(this.zzafg.zzafa);
                } else {
                    this.zzafg.setResult(this.zzafg.createFailedResult(Status.RESULT_TIMEOUT));
                }
            }
        }
        this.zzafg.zzk(this.zzafg.zzaex.zzhm());
    }

    public final void zzhj() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void zze(java.lang.Object r6) {
        /*
            r5 = this;
            com.google.android.gms.internal.gtm.zzk r6 = (com.google.android.gms.internal.gtm.zzk) r6
            com.google.android.gms.tagmanager.zzy r0 = r5.zzafg
            com.google.android.gms.tagmanager.zzai r0 = r0.zzaex
            r0.zzho()
            com.google.android.gms.tagmanager.zzy r1 = r5.zzafg
            monitor-enter(r1)
            com.google.android.gms.internal.gtm.zzi r0 = r6.zzqk     // Catch:{ all -> 0x0079 }
            if (r0 != 0) goto L_0x003c
            com.google.android.gms.tagmanager.zzy r0 = r5.zzafg     // Catch:{ all -> 0x0079 }
            com.google.android.gms.internal.gtm.zzk r0 = r0.zzafc     // Catch:{ all -> 0x0079 }
            com.google.android.gms.internal.gtm.zzi r0 = r0.zzqk     // Catch:{ all -> 0x0079 }
            if (r0 != 0) goto L_0x0032
            java.lang.String r0 = "Current resource is null; network resource is also null"
            com.google.android.gms.tagmanager.zzdi.zzav(r0)     // Catch:{ all -> 0x0079 }
            com.google.android.gms.tagmanager.zzy r0 = r5.zzafg     // Catch:{ all -> 0x0079 }
            com.google.android.gms.tagmanager.zzai r0 = r0.zzaex     // Catch:{ all -> 0x0079 }
            long r2 = r0.zzhm()     // Catch:{ all -> 0x0079 }
            com.google.android.gms.tagmanager.zzy r0 = r5.zzafg     // Catch:{ all -> 0x0079 }
            r0.zzk(r2)     // Catch:{ all -> 0x0079 }
            monitor-exit(r1)     // Catch:{ all -> 0x0079 }
        L_0x0031:
            return
        L_0x0032:
            com.google.android.gms.tagmanager.zzy r0 = r5.zzafg     // Catch:{ all -> 0x0079 }
            com.google.android.gms.internal.gtm.zzk r0 = r0.zzafc     // Catch:{ all -> 0x0079 }
            com.google.android.gms.internal.gtm.zzi r0 = r0.zzqk     // Catch:{ all -> 0x0079 }
            r6.zzqk = r0     // Catch:{ all -> 0x0079 }
        L_0x003c:
            com.google.android.gms.tagmanager.zzy r0 = r5.zzafg     // Catch:{ all -> 0x0079 }
            com.google.android.gms.tagmanager.zzy r2 = r5.zzafg     // Catch:{ all -> 0x0079 }
            com.google.android.gms.common.util.Clock r2 = r2.zzsd     // Catch:{ all -> 0x0079 }
            long r2 = r2.currentTimeMillis()     // Catch:{ all -> 0x0079 }
            r4 = 0
            r0.zza(r6, r2, r4)     // Catch:{ all -> 0x0079 }
            com.google.android.gms.tagmanager.zzy r0 = r5.zzafg     // Catch:{ all -> 0x0079 }
            long r2 = r0.zzaeh     // Catch:{ all -> 0x0079 }
            r0 = 58
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0079 }
            r4.<init>(r0)     // Catch:{ all -> 0x0079 }
            java.lang.String r0 = "setting refresh time to current time: "
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ all -> 0x0079 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0079 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0079 }
            com.google.android.gms.tagmanager.zzdi.zzab(r0)     // Catch:{ all -> 0x0079 }
            com.google.android.gms.tagmanager.zzy r0 = r5.zzafg     // Catch:{ all -> 0x0079 }
            boolean r0 = r0.zzhi()     // Catch:{ all -> 0x0079 }
            if (r0 != 0) goto L_0x0077
            com.google.android.gms.tagmanager.zzy r0 = r5.zzafg     // Catch:{ all -> 0x0079 }
            r0.zza((com.google.android.gms.internal.gtm.zzk) r6)     // Catch:{ all -> 0x0079 }
        L_0x0077:
            monitor-exit(r1)     // Catch:{ all -> 0x0079 }
            goto L_0x0031
        L_0x0079:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0079 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzae.zze(java.lang.Object):void");
    }

    /* synthetic */ zzae(zzy zzy, zzz zzz) {
        this(zzy);
    }
}
