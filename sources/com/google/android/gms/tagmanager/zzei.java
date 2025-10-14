package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zza;
import com.google.android.gms.internal.gtm.zzb;

final class zzei extends zzbq {
    private static final String ID = zza.RANDOM.toString();
    private static final String zzaix = zzb.MIN.toString();
    private static final String zzaiy = zzb.MAX.toString();

    public zzei() {
        super(ID, new String[0]);
    }

    public final boolean zzgw() {
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0045, code lost:
        if (r4 <= r0) goto L_0x0047;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.gtm.zzl zzb(java.util.Map<java.lang.String, com.google.android.gms.internal.gtm.zzl> r10) {
        /*
            r9 = this;
            r6 = 0
            r2 = 4746794007244308480(0x41dfffffffc00000, double:2.147483647E9)
            java.lang.String r0 = zzaix
            java.lang.Object r0 = r10.get(r0)
            com.google.android.gms.internal.gtm.zzl r0 = (com.google.android.gms.internal.gtm.zzl) r0
            java.lang.String r1 = zzaiy
            java.lang.Object r1 = r10.get(r1)
            com.google.android.gms.internal.gtm.zzl r1 = (com.google.android.gms.internal.gtm.zzl) r1
            if (r0 == 0) goto L_0x005b
            com.google.android.gms.internal.gtm.zzl r4 = com.google.android.gms.tagmanager.zzgj.zzkc()
            if (r0 == r4) goto L_0x005b
            if (r1 == 0) goto L_0x005b
            com.google.android.gms.internal.gtm.zzl r4 = com.google.android.gms.tagmanager.zzgj.zzkc()
            if (r1 == r4) goto L_0x005b
            com.google.android.gms.tagmanager.zzgi r0 = com.google.android.gms.tagmanager.zzgj.zzd(r0)
            com.google.android.gms.tagmanager.zzgi r1 = com.google.android.gms.tagmanager.zzgj.zzd(r1)
            com.google.android.gms.tagmanager.zzgi r4 = com.google.android.gms.tagmanager.zzgj.zzka()
            if (r0 == r4) goto L_0x005b
            com.google.android.gms.tagmanager.zzgi r4 = com.google.android.gms.tagmanager.zzgj.zzka()
            if (r1 == r4) goto L_0x005b
            double r4 = r0.doubleValue()
            double r0 = r1.doubleValue()
            int r8 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r8 > 0) goto L_0x005b
        L_0x0047:
            double r2 = java.lang.Math.random()
            double r0 = r0 - r4
            double r0 = r0 * r2
            double r0 = r0 + r4
            long r0 = java.lang.Math.round(r0)
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            com.google.android.gms.internal.gtm.zzl r0 = com.google.android.gms.tagmanager.zzgj.zzi(r0)
            return r0
        L_0x005b:
            r0 = r2
            r4 = r6
            goto L_0x0047
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzei.zzb(java.util.Map):com.google.android.gms.internal.gtm.zzl");
    }
}
