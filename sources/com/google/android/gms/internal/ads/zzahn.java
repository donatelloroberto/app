package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.Future;

@zzadh
public final class zzahn extends zzajx implements zzaht, zzahw, zzaia {
    /* access modifiers changed from: private */
    public final Context mContext;
    private int mErrorCode = 3;
    private final Object mLock;
    public final String zzbth;
    private final zzaji zzbze;
    private final zzaib zzcll;
    private final zzahw zzclm;
    /* access modifiers changed from: private */
    public final String zzcln;
    private final zzwx zzclo;
    private final long zzclp;
    private int zzclq = 0;
    private zzahq zzclr;
    private Future zzcls;
    private volatile zzb zzclt;

    public zzahn(Context context, String str, String str2, zzwx zzwx, zzaji zzaji, zzaib zzaib, zzahw zzahw, long j) {
        this.mContext = context;
        this.zzbth = str;
        this.zzcln = str2;
        this.zzclo = zzwx;
        this.zzbze = zzaji;
        this.zzcll = zzaib;
        this.mLock = new Object();
        this.zzclm = zzahw;
        this.zzclp = j;
    }

    /* access modifiers changed from: private */
    public final void zza(zzjj zzjj, zzxq zzxq) {
        this.zzcll.zzpf().zza((zzahw) this);
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbth)) {
                zzxq.zza(zzjj, this.zzcln, this.zzclo.zzbrr);
            } else {
                zzxq.zzc(zzjj, this.zzcln);
            }
        } catch (RemoteException e) {
            zzakb.zzc("Fail to load ad from adapter.", e);
            zza(this.zzbth, 0);
        }
    }

    private final boolean zzf(long j) {
        long elapsedRealtime = this.zzclp - (zzbv.zzer().elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            this.mErrorCode = 4;
            return false;
        }
        try {
            this.mLock.wait(elapsedRealtime);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            this.mErrorCode = 5;
            return false;
        }
    }

    public final void onStop() {
    }

    public final void zza(zzb zzb) {
        this.zzclt = zzb;
    }

    public final void zza(String str, int i) {
        synchronized (this.mLock) {
            this.zzclq = 2;
            this.mErrorCode = i;
            this.mLock.notify();
        }
    }

    public final void zzac(int i) {
        zza(this.zzbth, 0);
    }

    public final void zzc(Bundle bundle) {
        zzb zzb = this.zzclt;
        if (zzb != null) {
            zzb.zza("", bundle);
        }
    }

    public final void zzcb(String str) {
        synchronized (this.mLock) {
            this.zzclq = 1;
            this.mLock.notify();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
        r2 = new com.google.android.gms.internal.ads.zzahs().zzg(com.google.android.gms.ads.internal.zzbv.zzer().elapsedRealtime() - r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0066, code lost:
        if (1 != r10.zzclq) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0068, code lost:
        r0 = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0069, code lost:
        r10.zzclr = r2.zzad(r0).zzcc(r10.zzbth).zzcd(r10.zzclo.zzbru).zzpd();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r0 = r10.mErrorCode;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzdn() {
        /*
            r10 = this;
            r9 = 1
            r8 = 0
            com.google.android.gms.internal.ads.zzaib r0 = r10.zzcll
            if (r0 == 0) goto L_0x0016
            com.google.android.gms.internal.ads.zzaib r0 = r10.zzcll
            com.google.android.gms.internal.ads.zzahv r0 = r0.zzpf()
            if (r0 == 0) goto L_0x0016
            com.google.android.gms.internal.ads.zzaib r0 = r10.zzcll
            com.google.android.gms.internal.ads.zzxq r0 = r0.zzpe()
            if (r0 != 0) goto L_0x0017
        L_0x0016:
            return
        L_0x0017:
            com.google.android.gms.internal.ads.zzaib r0 = r10.zzcll
            com.google.android.gms.internal.ads.zzahv r1 = r0.zzpf()
            r1.zza((com.google.android.gms.internal.ads.zzahw) r8)
            r1.zza((com.google.android.gms.internal.ads.zzaht) r10)
            r1.zza((com.google.android.gms.internal.ads.zzaia) r10)
            com.google.android.gms.internal.ads.zzaji r0 = r10.zzbze
            com.google.android.gms.internal.ads.zzaef r0 = r0.zzcgs
            com.google.android.gms.internal.ads.zzjj r0 = r0.zzccv
            com.google.android.gms.internal.ads.zzaib r2 = r10.zzcll
            com.google.android.gms.internal.ads.zzxq r2 = r2.zzpe()
            boolean r3 = r2.isInitialized()     // Catch:{ RemoteException -> 0x009f }
            if (r3 == 0) goto L_0x0094
            android.os.Handler r3 = com.google.android.gms.internal.ads.zzamu.zzsy     // Catch:{ RemoteException -> 0x009f }
            com.google.android.gms.internal.ads.zzaho r4 = new com.google.android.gms.internal.ads.zzaho     // Catch:{ RemoteException -> 0x009f }
            r4.<init>(r10, r0, r2)     // Catch:{ RemoteException -> 0x009f }
            r3.post(r4)     // Catch:{ RemoteException -> 0x009f }
        L_0x0042:
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.zzbv.zzer()
            long r2 = r0.elapsedRealtime()
        L_0x004a:
            java.lang.Object r4 = r10.mLock
            monitor-enter(r4)
            int r0 = r10.zzclq     // Catch:{ all -> 0x00e4 }
            if (r0 == 0) goto L_0x00af
            com.google.android.gms.internal.ads.zzahs r0 = new com.google.android.gms.internal.ads.zzahs     // Catch:{ all -> 0x00e4 }
            r0.<init>()     // Catch:{ all -> 0x00e4 }
            com.google.android.gms.common.util.Clock r5 = com.google.android.gms.ads.internal.zzbv.zzer()     // Catch:{ all -> 0x00e4 }
            long r6 = r5.elapsedRealtime()     // Catch:{ all -> 0x00e4 }
            long r2 = r6 - r2
            com.google.android.gms.internal.ads.zzahs r2 = r0.zzg(r2)     // Catch:{ all -> 0x00e4 }
            int r0 = r10.zzclq     // Catch:{ all -> 0x00e4 }
            if (r9 != r0) goto L_0x00ac
            r0 = 6
        L_0x0069:
            com.google.android.gms.internal.ads.zzahs r0 = r2.zzad(r0)     // Catch:{ all -> 0x00e4 }
            java.lang.String r2 = r10.zzbth     // Catch:{ all -> 0x00e4 }
            com.google.android.gms.internal.ads.zzahs r0 = r0.zzcc(r2)     // Catch:{ all -> 0x00e4 }
            com.google.android.gms.internal.ads.zzwx r2 = r10.zzclo     // Catch:{ all -> 0x00e4 }
            java.lang.String r2 = r2.zzbru     // Catch:{ all -> 0x00e4 }
            com.google.android.gms.internal.ads.zzahs r0 = r0.zzcd(r2)     // Catch:{ all -> 0x00e4 }
            com.google.android.gms.internal.ads.zzahq r0 = r0.zzpd()     // Catch:{ all -> 0x00e4 }
            r10.zzclr = r0     // Catch:{ all -> 0x00e4 }
            monitor-exit(r4)     // Catch:{ all -> 0x00e4 }
        L_0x0082:
            r1.zza((com.google.android.gms.internal.ads.zzahw) r8)
            r1.zza((com.google.android.gms.internal.ads.zzaht) r8)
            int r0 = r10.zzclq
            if (r0 != r9) goto L_0x00ea
            com.google.android.gms.internal.ads.zzahw r0 = r10.zzclm
            java.lang.String r1 = r10.zzbth
            r0.zzcb(r1)
            goto L_0x0016
        L_0x0094:
            android.os.Handler r3 = com.google.android.gms.internal.ads.zzamu.zzsy     // Catch:{ RemoteException -> 0x009f }
            com.google.android.gms.internal.ads.zzahp r4 = new com.google.android.gms.internal.ads.zzahp     // Catch:{ RemoteException -> 0x009f }
            r4.<init>(r10, r2, r0, r1)     // Catch:{ RemoteException -> 0x009f }
            r3.post(r4)     // Catch:{ RemoteException -> 0x009f }
            goto L_0x0042
        L_0x009f:
            r0 = move-exception
            java.lang.String r2 = "Fail to check if adapter is initialized."
            com.google.android.gms.internal.ads.zzakb.zzc(r2, r0)
            java.lang.String r0 = r10.zzbth
            r2 = 0
            r10.zza((java.lang.String) r0, (int) r2)
            goto L_0x0042
        L_0x00ac:
            int r0 = r10.mErrorCode     // Catch:{ all -> 0x00e4 }
            goto L_0x0069
        L_0x00af:
            boolean r0 = r10.zzf(r2)     // Catch:{ all -> 0x00e4 }
            if (r0 != 0) goto L_0x00e7
            com.google.android.gms.internal.ads.zzahs r0 = new com.google.android.gms.internal.ads.zzahs     // Catch:{ all -> 0x00e4 }
            r0.<init>()     // Catch:{ all -> 0x00e4 }
            int r5 = r10.mErrorCode     // Catch:{ all -> 0x00e4 }
            com.google.android.gms.internal.ads.zzahs r0 = r0.zzad(r5)     // Catch:{ all -> 0x00e4 }
            com.google.android.gms.common.util.Clock r5 = com.google.android.gms.ads.internal.zzbv.zzer()     // Catch:{ all -> 0x00e4 }
            long r6 = r5.elapsedRealtime()     // Catch:{ all -> 0x00e4 }
            long r2 = r6 - r2
            com.google.android.gms.internal.ads.zzahs r0 = r0.zzg(r2)     // Catch:{ all -> 0x00e4 }
            java.lang.String r2 = r10.zzbth     // Catch:{ all -> 0x00e4 }
            com.google.android.gms.internal.ads.zzahs r0 = r0.zzcc(r2)     // Catch:{ all -> 0x00e4 }
            com.google.android.gms.internal.ads.zzwx r2 = r10.zzclo     // Catch:{ all -> 0x00e4 }
            java.lang.String r2 = r2.zzbru     // Catch:{ all -> 0x00e4 }
            com.google.android.gms.internal.ads.zzahs r0 = r0.zzcd(r2)     // Catch:{ all -> 0x00e4 }
            com.google.android.gms.internal.ads.zzahq r0 = r0.zzpd()     // Catch:{ all -> 0x00e4 }
            r10.zzclr = r0     // Catch:{ all -> 0x00e4 }
            monitor-exit(r4)     // Catch:{ all -> 0x00e4 }
            goto L_0x0082
        L_0x00e4:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00e4 }
            throw r0
        L_0x00e7:
            monitor-exit(r4)     // Catch:{ all -> 0x00e4 }
            goto L_0x004a
        L_0x00ea:
            com.google.android.gms.internal.ads.zzahw r0 = r10.zzclm
            java.lang.String r1 = r10.zzbth
            int r2 = r10.mErrorCode
            r0.zza(r1, r2)
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahn.zzdn():void");
    }

    public final Future zzoz() {
        if (this.zzcls != null) {
            return this.zzcls;
        }
        zzanz zzanz = (zzanz) zznt();
        this.zzcls = zzanz;
        return zzanz;
    }

    public final zzahq zzpa() {
        zzahq zzahq;
        synchronized (this.mLock) {
            zzahq = this.zzclr;
        }
        return zzahq;
    }

    public final zzwx zzpb() {
        return this.zzclo;
    }

    public final void zzpc() {
        zza(this.zzbze.zzcgs.zzccv, this.zzcll.zzpe());
    }
}
