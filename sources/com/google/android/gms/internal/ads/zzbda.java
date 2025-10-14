package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

final class zzbda<T> implements zzbdm<T> {
    private final zzbcu zzdwl;
    private final boolean zzdwm;
    private final zzbee<?, ?> zzdwv;
    private final zzbbd<?> zzdww;

    private zzbda(zzbee<?, ?> zzbee, zzbbd<?> zzbbd, zzbcu zzbcu) {
        this.zzdwv = zzbee;
        this.zzdwm = zzbbd.zzh(zzbcu);
        this.zzdww = zzbbd;
        this.zzdwl = zzbcu;
    }

    static <T> zzbda<T> zza(zzbee<?, ?> zzbee, zzbbd<?> zzbbd, zzbcu zzbcu) {
        return new zzbda<>(zzbee, zzbbd, zzbcu);
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzdwv.zzac(t).equals(this.zzdwv.zzac(t2))) {
            return false;
        }
        if (this.zzdwm) {
            return this.zzdww.zzm(t).equals(this.zzdww.zzm(t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzdwv.zzac(t).hashCode();
        return this.zzdwm ? (hashCode * 53) + this.zzdww.zzm(t).hashCode() : hashCode;
    }

    public final T newInstance() {
        return this.zzdwl.zzadf().zzadj();
    }

    public final void zza(T t, zzbdl zzbdl, zzbbb zzbbb) throws IOException {
        boolean z;
        zzbee<?, ?> zzbee = this.zzdwv;
        zzbbd<?> zzbbd = this.zzdww;
        Object zzad = zzbee.zzad(t);
        zzbbg<?> zzn = zzbbd.zzn(t);
        do {
            try {
                if (zzbdl.zzaci() == Integer.MAX_VALUE) {
                    zzbee.zzf(t, zzad);
                    return;
                }
                int tag = zzbdl.getTag();
                if (tag == 11) {
                    int i = 0;
                    Object obj = null;
                    zzbah zzbah = null;
                    while (zzbdl.zzaci() != Integer.MAX_VALUE) {
                        int tag2 = zzbdl.getTag();
                        if (tag2 == 16) {
                            i = zzbdl.zzabt();
                            obj = zzbbd.zza(zzbbb, this.zzdwl, i);
                        } else if (tag2 == 26) {
                            if (obj != null) {
                                zzbbd.zza(zzbdl, obj, zzbbb, zzn);
                            } else {
                                zzbah = zzbdl.zzabs();
                            }
                        } else if (!zzbdl.zzacj()) {
                            break;
                        }
                    }
                    if (zzbdl.getTag() != 12) {
                        throw zzbbu.zzadp();
                    } else if (zzbah != null) {
                        if (obj != null) {
                            zzbbd.zza(zzbah, obj, zzbbb, zzn);
                        } else {
                            zzbee.zza(zzad, i, zzbah);
                        }
                    }
                } else if ((tag & 7) == 2) {
                    Object zza = zzbbd.zza(zzbbb, this.zzdwl, tag >>> 3);
                    if (zza != null) {
                        zzbbd.zza(zzbdl, zza, zzbbb, zzn);
                    } else {
                        z = zzbee.zza(zzad, zzbdl);
                        continue;
                    }
                } else {
                    z = zzbdl.zzacj();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzbee.zzf(t, zzad);
            }
        } while (z);
    }

    public final void zza(T t, zzbey zzbey) throws IOException {
        Iterator<Map.Entry<?, Object>> it = this.zzdww.zzm(t).iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzbbi zzbbi = (zzbbi) next.getKey();
            if (zzbbi.zzacz() != zzbex.MESSAGE || zzbbi.zzada() || zzbbi.zzadb()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (next instanceof zzbbz) {
                zzbey.zza(zzbbi.zzhq(), (Object) ((zzbbz) next).zzadv().zzaav());
            } else {
                zzbey.zza(zzbbi.zzhq(), next.getValue());
            }
        }
        zzbee<?, ?> zzbee = this.zzdwv;
        zzbee.zzc(zzbee.zzac(t), zzbey);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r9, byte[] r10, int r11, int r12, com.google.android.gms.internal.ads.zzbae r13) throws java.io.IOException {
        /*
            r8 = this;
            r7 = 2
            r0 = r9
            com.google.android.gms.internal.ads.zzbbo r0 = (com.google.android.gms.internal.ads.zzbbo) r0
            com.google.android.gms.internal.ads.zzbef r4 = r0.zzdtt
            com.google.android.gms.internal.ads.zzbef r0 = com.google.android.gms.internal.ads.zzbef.zzagc()
            if (r4 != r0) goto L_0x0014
            com.google.android.gms.internal.ads.zzbef r4 = com.google.android.gms.internal.ads.zzbef.zzagd()
            com.google.android.gms.internal.ads.zzbbo r9 = (com.google.android.gms.internal.ads.zzbbo) r9
            r9.zzdtt = r4
        L_0x0014:
            if (r11 >= r12) goto L_0x006f
            int r2 = com.google.android.gms.internal.ads.zzbad.zza(r10, r11, r13)
            int r0 = r13.zzdpl
            r1 = 11
            if (r0 == r1) goto L_0x0031
            r1 = r0 & 7
            if (r1 != r7) goto L_0x002c
            r1 = r10
            r3 = r12
            r5 = r13
            int r11 = com.google.android.gms.internal.ads.zzbad.zza((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.ads.zzbef) r4, (com.google.android.gms.internal.ads.zzbae) r5)
            goto L_0x0014
        L_0x002c:
            int r11 = com.google.android.gms.internal.ads.zzbad.zza(r0, r10, r2, r12, r13)
            goto L_0x0014
        L_0x0031:
            r5 = 0
            r0 = 0
            r3 = r0
            r1 = r2
        L_0x0035:
            if (r1 >= r12) goto L_0x0063
            int r0 = com.google.android.gms.internal.ads.zzbad.zza(r10, r1, r13)
            int r1 = r13.zzdpl
            int r2 = r1 >>> 3
            r6 = r1 & 7
            switch(r2) {
                case 2: goto L_0x004d;
                case 3: goto L_0x0057;
                default: goto L_0x0044;
            }
        L_0x0044:
            r2 = 12
            if (r1 == r2) goto L_0x0064
            int r1 = com.google.android.gms.internal.ads.zzbad.zza(r1, r10, r0, r12, r13)
            goto L_0x0035
        L_0x004d:
            if (r6 != 0) goto L_0x0044
            int r1 = com.google.android.gms.internal.ads.zzbad.zza(r10, r0, r13)
            int r0 = r13.zzdpl
            r5 = r0
            goto L_0x0035
        L_0x0057:
            if (r6 != r7) goto L_0x0044
            int r1 = com.google.android.gms.internal.ads.zzbad.zze(r10, r0, r13)
            java.lang.Object r0 = r13.zzdpn
            com.google.android.gms.internal.ads.zzbah r0 = (com.google.android.gms.internal.ads.zzbah) r0
            r3 = r0
            goto L_0x0035
        L_0x0063:
            r0 = r1
        L_0x0064:
            if (r3 == 0) goto L_0x006d
            int r1 = r5 << 3
            r1 = r1 | 2
            r4.zzb(r1, r3)
        L_0x006d:
            r11 = r0
            goto L_0x0014
        L_0x006f:
            if (r11 == r12) goto L_0x0076
            com.google.android.gms.internal.ads.zzbbu r0 = com.google.android.gms.internal.ads.zzbbu.zzadr()
            throw r0
        L_0x0076:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbda.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.ads.zzbae):void");
    }

    public final boolean zzaa(T t) {
        return this.zzdww.zzm(t).isInitialized();
    }

    public final void zzc(T t, T t2) {
        zzbdo.zza(this.zzdwv, t, t2);
        if (this.zzdwm) {
            zzbdo.zza(this.zzdww, t, t2);
        }
    }

    public final void zzo(T t) {
        this.zzdwv.zzo(t);
        this.zzdww.zzo(t);
    }

    public final int zzy(T t) {
        zzbee<?, ?> zzbee = this.zzdwv;
        int zzae = zzbee.zzae(zzbee.zzac(t)) + 0;
        return this.zzdwm ? zzae + this.zzdww.zzm(t).zzacx() : zzae;
    }
}
