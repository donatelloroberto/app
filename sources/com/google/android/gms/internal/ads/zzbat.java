package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;

final class zzbat implements zzbdl {
    private int tag;
    private final zzbaq zzdqi;
    private int zzdqj;
    private int zzdqk = 0;

    private zzbat(zzbaq zzbaq) {
        this.zzdqi = (zzbaq) zzbbq.zza(zzbaq, "input");
        this.zzdqi.zzdqa = this;
    }

    public static zzbat zza(zzbaq zzbaq) {
        return zzbaq.zzdqa != null ? zzbaq.zzdqa : new zzbat(zzbaq);
    }

    private final Object zza(zzbes zzbes, Class<?> cls, zzbbb zzbbb) throws IOException {
        switch (zzbau.zzdql[zzbes.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzabq());
            case 2:
                return zzabs();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzabu());
            case 5:
                return Integer.valueOf(zzabp());
            case 6:
                return Long.valueOf(zzabo());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzabn());
            case 9:
                return Long.valueOf(zzabm());
            case 10:
                zzbv(2);
                return zzc(zzbdg.zzaeo().zze(cls), zzbbb);
            case 11:
                return Integer.valueOf(zzabv());
            case 12:
                return Long.valueOf(zzabw());
            case 13:
                return Integer.valueOf(zzabx());
            case 14:
                return Long.valueOf(zzaby());
            case 15:
                return zzabr();
            case 16:
                return Integer.valueOf(zzabt());
            case 17:
                return Long.valueOf(zzabl());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int zzabk;
        int zzabk2;
        if ((this.tag & 7) != 2) {
            throw zzbbu.zzadq();
        } else if (!(list instanceof zzbcd) || z) {
            do {
                list.add(z ? zzabr() : readString());
                if (!this.zzdqi.zzaca()) {
                    zzabk = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk == this.tag);
            this.zzdqk = zzabk;
        } else {
            zzbcd zzbcd = (zzbcd) list;
            do {
                zzbcd.zzap(zzabs());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
        }
    }

    private final void zzbv(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzbbu.zzadq();
        }
    }

    private static void zzbw(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzbbu.zzadr();
        }
    }

    private static void zzbx(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzbbu.zzadr();
        }
    }

    private final void zzby(int i) throws IOException {
        if (this.zzdqi.zzacb() != i) {
            throw zzbbu.zzadl();
        }
    }

    private final <T> T zzc(zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        int zzabt = this.zzdqi.zzabt();
        if (this.zzdqi.zzdpx >= this.zzdqi.zzdpy) {
            throw new zzbbu("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int zzbr = this.zzdqi.zzbr(zzabt);
        T newInstance = zzbdm.newInstance();
        this.zzdqi.zzdpx++;
        zzbdm.zza(newInstance, this, zzbbb);
        zzbdm.zzo(newInstance);
        this.zzdqi.zzbp(0);
        zzbaq zzbaq = this.zzdqi;
        zzbaq.zzdpx--;
        this.zzdqi.zzbs(zzbr);
        return newInstance;
    }

    private final <T> T zzd(zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        int i = this.zzdqj;
        this.zzdqj = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzbdm.newInstance();
            zzbdm.zza(newInstance, this, zzbbb);
            zzbdm.zzo(newInstance);
            if (this.tag == this.zzdqj) {
                return newInstance;
            }
            throw zzbbu.zzadr();
        } finally {
            this.zzdqj = i;
        }
    }

    public final int getTag() {
        return this.tag;
    }

    public final double readDouble() throws IOException {
        zzbv(1);
        return this.zzdqi.readDouble();
    }

    public final float readFloat() throws IOException {
        zzbv(5);
        return this.zzdqi.readFloat();
    }

    public final String readString() throws IOException {
        zzbv(2);
        return this.zzdqi.readString();
    }

    public final void readStringList(List<String> list) throws IOException {
        zza(list, false);
    }

    public final <T> T zza(zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        zzbv(2);
        return zzc(zzbdm, zzbbb);
    }

    public final <T> void zza(List<T> list, zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        int zzabk;
        if ((this.tag & 7) != 2) {
            throw zzbbu.zzadq();
        }
        int i = this.tag;
        do {
            list.add(zzc(zzbdm, zzbbb));
            if (!this.zzdqi.zzaca() && this.zzdqk == 0) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == i);
        this.zzdqk = zzabk;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r7, com.google.android.gms.internal.ads.zzbcn<K, V> r8, com.google.android.gms.internal.ads.zzbbb r9) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 2
            r6.zzbv(r0)
            com.google.android.gms.internal.ads.zzbaq r0 = r6.zzdqi
            int r0 = r0.zzabt()
            com.google.android.gms.internal.ads.zzbaq r1 = r6.zzdqi
            int r2 = r1.zzbr(r0)
            K r1 = r8.zzdvz
            V r0 = r8.zzdwb
        L_0x0014:
            int r3 = r6.zzaci()     // Catch:{ all -> 0x0045 }
            r4 = 2147483647(0x7fffffff, float:NaN)
            if (r3 == r4) goto L_0x0062
            com.google.android.gms.internal.ads.zzbaq r4 = r6.zzdqi     // Catch:{ all -> 0x0045 }
            boolean r4 = r4.zzaca()     // Catch:{ all -> 0x0045 }
            if (r4 != 0) goto L_0x0062
            switch(r3) {
                case 1: goto L_0x004c;
                case 2: goto L_0x0055;
                default: goto L_0x0028;
            }
        L_0x0028:
            boolean r3 = r6.zzacj()     // Catch:{ zzbbv -> 0x0036 }
            if (r3 != 0) goto L_0x0014
            com.google.android.gms.internal.ads.zzbbu r3 = new com.google.android.gms.internal.ads.zzbbu     // Catch:{ zzbbv -> 0x0036 }
            java.lang.String r4 = "Unable to parse map entry."
            r3.<init>(r4)     // Catch:{ zzbbv -> 0x0036 }
            throw r3     // Catch:{ zzbbv -> 0x0036 }
        L_0x0036:
            r3 = move-exception
            boolean r3 = r6.zzacj()     // Catch:{ all -> 0x0045 }
            if (r3 != 0) goto L_0x0014
            com.google.android.gms.internal.ads.zzbbu r0 = new com.google.android.gms.internal.ads.zzbbu     // Catch:{ all -> 0x0045 }
            java.lang.String r1 = "Unable to parse map entry."
            r0.<init>(r1)     // Catch:{ all -> 0x0045 }
            throw r0     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r0 = move-exception
            com.google.android.gms.internal.ads.zzbaq r1 = r6.zzdqi
            r1.zzbs(r2)
            throw r0
        L_0x004c:
            com.google.android.gms.internal.ads.zzbes r3 = r8.zzdvy     // Catch:{ zzbbv -> 0x0036 }
            r4 = 0
            r5 = 0
            java.lang.Object r1 = r6.zza((com.google.android.gms.internal.ads.zzbes) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.ads.zzbbb) r5)     // Catch:{ zzbbv -> 0x0036 }
            goto L_0x0014
        L_0x0055:
            com.google.android.gms.internal.ads.zzbes r3 = r8.zzdwa     // Catch:{ zzbbv -> 0x0036 }
            V r4 = r8.zzdwb     // Catch:{ zzbbv -> 0x0036 }
            java.lang.Class r4 = r4.getClass()     // Catch:{ zzbbv -> 0x0036 }
            java.lang.Object r0 = r6.zza((com.google.android.gms.internal.ads.zzbes) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.ads.zzbbb) r9)     // Catch:{ zzbbv -> 0x0036 }
            goto L_0x0014
        L_0x0062:
            r7.put(r1, r0)     // Catch:{ all -> 0x0045 }
            com.google.android.gms.internal.ads.zzbaq r0 = r6.zzdqi
            r0.zzbs(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbat.zza(java.util.Map, com.google.android.gms.internal.ads.zzbcn, com.google.android.gms.internal.ads.zzbbb):void");
    }

    public final void zzaa(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    int zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                    do {
                        zzbbp.zzco(this.zzdqi.zzabu());
                    } while (this.zzdqi.zzacb() < zzabt);
                    zzby(zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                zzbbp.zzco(this.zzdqi.zzabu());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                int zzabt2 = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabu()));
                } while (this.zzdqi.zzacb() < zzabt2);
                zzby(zzabt2);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Integer.valueOf(this.zzdqi.zzabu()));
            if (!this.zzdqi.zzaca()) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }

    public final void zzab(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            switch (this.tag & 7) {
                case 2:
                    int zzabt = this.zzdqi.zzabt();
                    zzbx(zzabt);
                    int zzacb = zzabt + this.zzdqi.zzacb();
                    do {
                        zzbbp.zzco(this.zzdqi.zzabv());
                    } while (this.zzdqi.zzacb() < zzacb);
                    return;
                case 5:
                    break;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                zzbbp.zzco(this.zzdqi.zzabv());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
            return;
        }
        switch (this.tag & 7) {
            case 2:
                int zzabt2 = this.zzdqi.zzabt();
                zzbx(zzabt2);
                int zzacb2 = zzabt2 + this.zzdqi.zzacb();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabv()));
                } while (this.zzdqi.zzacb() < zzacb2);
                return;
            case 5:
                break;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Integer.valueOf(this.zzdqi.zzabv()));
            if (!this.zzdqi.zzaca()) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }

    public final long zzabl() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabl();
    }

    public final long zzabm() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabm();
    }

    public final int zzabn() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabn();
    }

    public final long zzabo() throws IOException {
        zzbv(1);
        return this.zzdqi.zzabo();
    }

    public final int zzabp() throws IOException {
        zzbv(5);
        return this.zzdqi.zzabp();
    }

    public final boolean zzabq() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabq();
    }

    public final String zzabr() throws IOException {
        zzbv(2);
        return this.zzdqi.zzabr();
    }

    public final zzbah zzabs() throws IOException {
        zzbv(2);
        return this.zzdqi.zzabs();
    }

    public final int zzabt() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabt();
    }

    public final int zzabu() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabu();
    }

    public final int zzabv() throws IOException {
        zzbv(5);
        return this.zzdqi.zzabv();
    }

    public final long zzabw() throws IOException {
        zzbv(1);
        return this.zzdqi.zzabw();
    }

    public final int zzabx() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabx();
    }

    public final long zzaby() throws IOException {
        zzbv(0);
        return this.zzdqi.zzaby();
    }

    public final void zzac(List<Long> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzabt = this.zzdqi.zzabt();
                    zzbw(zzabt);
                    int zzacb = zzabt + this.zzdqi.zzacb();
                    do {
                        zzbci.zzw(this.zzdqi.zzabw());
                    } while (this.zzdqi.zzacb() < zzacb);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                zzbci.zzw(this.zzdqi.zzabw());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzabt2 = this.zzdqi.zzabt();
                zzbw(zzabt2);
                int zzacb2 = zzabt2 + this.zzdqi.zzacb();
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabw()));
                } while (this.zzdqi.zzacb() < zzacb2);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Long.valueOf(this.zzdqi.zzabw()));
            if (!this.zzdqi.zzaca()) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }

    public final int zzaci() throws IOException {
        if (this.zzdqk != 0) {
            this.tag = this.zzdqk;
            this.zzdqk = 0;
        } else {
            this.tag = this.zzdqi.zzabk();
        }
        if (this.tag == 0 || this.tag == this.zzdqj) {
            return Integer.MAX_VALUE;
        }
        return this.tag >>> 3;
    }

    public final boolean zzacj() throws IOException {
        if (this.zzdqi.zzaca() || this.tag == this.zzdqj) {
            return false;
        }
        return this.zzdqi.zzbq(this.tag);
    }

    public final void zzad(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    int zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                    do {
                        zzbbp.zzco(this.zzdqi.zzabx());
                    } while (this.zzdqi.zzacb() < zzabt);
                    zzby(zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                zzbbp.zzco(this.zzdqi.zzabx());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                int zzabt2 = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabx()));
                } while (this.zzdqi.zzacb() < zzabt2);
                zzby(zzabt2);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Integer.valueOf(this.zzdqi.zzabx()));
            if (!this.zzdqi.zzaca()) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }

    public final void zzae(List<Long> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    int zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                    do {
                        zzbci.zzw(this.zzdqi.zzaby());
                    } while (this.zzdqi.zzacb() < zzabt);
                    zzby(zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                zzbci.zzw(this.zzdqi.zzaby());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                int zzabt2 = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                do {
                    list.add(Long.valueOf(this.zzdqi.zzaby()));
                } while (this.zzdqi.zzacb() < zzabt2);
                zzby(zzabt2);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Long.valueOf(this.zzdqi.zzaby()));
            if (!this.zzdqi.zzaca()) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }

    public final <T> T zzb(zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        zzbv(3);
        return zzd(zzbdm, zzbbb);
    }

    public final <T> void zzb(List<T> list, zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        int zzabk;
        if ((this.tag & 7) != 3) {
            throw zzbbu.zzadq();
        }
        int i = this.tag;
        do {
            list.add(zzd(zzbdm, zzbbb));
            if (!this.zzdqi.zzaca() && this.zzdqk == 0) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == i);
        this.zzdqk = zzabk;
    }

    public final void zzp(List<Double> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbay) {
            zzbay zzbay = (zzbay) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzabt = this.zzdqi.zzabt();
                    zzbw(zzabt);
                    int zzacb = zzabt + this.zzdqi.zzacb();
                    do {
                        zzbay.zzd(this.zzdqi.readDouble());
                    } while (this.zzdqi.zzacb() < zzacb);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                zzbay.zzd(this.zzdqi.readDouble());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzabt2 = this.zzdqi.zzabt();
                zzbw(zzabt2);
                int zzacb2 = zzabt2 + this.zzdqi.zzacb();
                do {
                    list.add(Double.valueOf(this.zzdqi.readDouble()));
                } while (this.zzdqi.zzacb() < zzacb2);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Double.valueOf(this.zzdqi.readDouble()));
            if (!this.zzdqi.zzaca()) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }

    public final void zzq(List<Float> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbm) {
            zzbbm zzbbm = (zzbbm) list;
            switch (this.tag & 7) {
                case 2:
                    int zzabt = this.zzdqi.zzabt();
                    zzbx(zzabt);
                    int zzacb = zzabt + this.zzdqi.zzacb();
                    do {
                        zzbbm.zzd(this.zzdqi.readFloat());
                    } while (this.zzdqi.zzacb() < zzacb);
                    return;
                case 5:
                    break;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                zzbbm.zzd(this.zzdqi.readFloat());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
            return;
        }
        switch (this.tag & 7) {
            case 2:
                int zzabt2 = this.zzdqi.zzabt();
                zzbx(zzabt2);
                int zzacb2 = zzabt2 + this.zzdqi.zzacb();
                do {
                    list.add(Float.valueOf(this.zzdqi.readFloat()));
                } while (this.zzdqi.zzacb() < zzacb2);
                return;
            case 5:
                break;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Float.valueOf(this.zzdqi.readFloat()));
            if (!this.zzdqi.zzaca()) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }

    public final void zzr(List<Long> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    int zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                    do {
                        zzbci.zzw(this.zzdqi.zzabl());
                    } while (this.zzdqi.zzacb() < zzabt);
                    zzby(zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                zzbci.zzw(this.zzdqi.zzabl());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                int zzabt2 = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabl()));
                } while (this.zzdqi.zzacb() < zzabt2);
                zzby(zzabt2);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Long.valueOf(this.zzdqi.zzabl()));
            if (!this.zzdqi.zzaca()) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }

    public final void zzs(List<Long> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    int zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                    do {
                        zzbci.zzw(this.zzdqi.zzabm());
                    } while (this.zzdqi.zzacb() < zzabt);
                    zzby(zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                zzbci.zzw(this.zzdqi.zzabm());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                int zzabt2 = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabm()));
                } while (this.zzdqi.zzacb() < zzabt2);
                zzby(zzabt2);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Long.valueOf(this.zzdqi.zzabm()));
            if (!this.zzdqi.zzaca()) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }

    public final void zzt(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    int zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                    do {
                        zzbbp.zzco(this.zzdqi.zzabn());
                    } while (this.zzdqi.zzacb() < zzabt);
                    zzby(zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                zzbbp.zzco(this.zzdqi.zzabn());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                int zzabt2 = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabn()));
                } while (this.zzdqi.zzacb() < zzabt2);
                zzby(zzabt2);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Integer.valueOf(this.zzdqi.zzabn()));
            if (!this.zzdqi.zzaca()) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }

    public final void zzu(List<Long> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzabt = this.zzdqi.zzabt();
                    zzbw(zzabt);
                    int zzacb = zzabt + this.zzdqi.zzacb();
                    do {
                        zzbci.zzw(this.zzdqi.zzabo());
                    } while (this.zzdqi.zzacb() < zzacb);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                zzbci.zzw(this.zzdqi.zzabo());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzabt2 = this.zzdqi.zzabt();
                zzbw(zzabt2);
                int zzacb2 = zzabt2 + this.zzdqi.zzacb();
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabo()));
                } while (this.zzdqi.zzacb() < zzacb2);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Long.valueOf(this.zzdqi.zzabo()));
            if (!this.zzdqi.zzaca()) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }

    public final void zzv(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            switch (this.tag & 7) {
                case 2:
                    int zzabt = this.zzdqi.zzabt();
                    zzbx(zzabt);
                    int zzacb = zzabt + this.zzdqi.zzacb();
                    do {
                        zzbbp.zzco(this.zzdqi.zzabp());
                    } while (this.zzdqi.zzacb() < zzacb);
                    return;
                case 5:
                    break;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                zzbbp.zzco(this.zzdqi.zzabp());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
            return;
        }
        switch (this.tag & 7) {
            case 2:
                int zzabt2 = this.zzdqi.zzabt();
                zzbx(zzabt2);
                int zzacb2 = zzabt2 + this.zzdqi.zzacb();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabp()));
                } while (this.zzdqi.zzacb() < zzacb2);
                return;
            case 5:
                break;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Integer.valueOf(this.zzdqi.zzabp()));
            if (!this.zzdqi.zzaca()) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }

    public final void zzw(List<Boolean> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbaf) {
            zzbaf zzbaf = (zzbaf) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    int zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                    do {
                        zzbaf.addBoolean(this.zzdqi.zzabq());
                    } while (this.zzdqi.zzacb() < zzabt);
                    zzby(zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                zzbaf.addBoolean(this.zzdqi.zzabq());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                int zzabt2 = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                do {
                    list.add(Boolean.valueOf(this.zzdqi.zzabq()));
                } while (this.zzdqi.zzacb() < zzabt2);
                zzby(zzabt2);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Boolean.valueOf(this.zzdqi.zzabq()));
            if (!this.zzdqi.zzaca()) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }

    public final void zzx(List<String> list) throws IOException {
        zza(list, true);
    }

    public final void zzy(List<zzbah> list) throws IOException {
        int zzabk;
        if ((this.tag & 7) != 2) {
            throw zzbbu.zzadq();
        }
        do {
            list.add(zzabs());
            if (!this.zzdqi.zzaca()) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }

    public final void zzz(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    int zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                    do {
                        zzbbp.zzco(this.zzdqi.zzabt());
                    } while (this.zzdqi.zzacb() < zzabt);
                    zzby(zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                zzbbp.zzco(this.zzdqi.zzabt());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                int zzabt2 = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabt()));
                } while (this.zzdqi.zzacb() < zzabt2);
                zzby(zzabt2);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Integer.valueOf(this.zzdqi.zzabt()));
            if (!this.zzdqi.zzaca()) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }
}
