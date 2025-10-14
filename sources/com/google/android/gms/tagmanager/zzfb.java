package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzb;
import com.google.android.gms.internal.gtm.zzl;
import com.google.android.gms.internal.gtm.zzor;
import com.google.android.gms.internal.gtm.zzot;
import com.google.android.gms.internal.gtm.zzov;
import com.google.android.gms.internal.gtm.zzox;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@VisibleForTesting
final class zzfb {
    private static final zzdz<zzl> zzajp = new zzdz<>(zzgj.zzkc(), true);
    private final DataLayer zzaed;
    private final zzov zzajq;
    private final zzbo zzajr;
    private final Map<String, zzbq> zzajs;
    private final Map<String, zzbq> zzajt;
    private final Map<String, zzbq> zzaju;
    private final zzp<zzot, zzdz<zzl>> zzajv;
    private final zzp<String, zzfh> zzajw;
    private final Set<zzox> zzajx;
    private final Map<String, zzfi> zzajy;
    private volatile String zzajz;
    private int zzaka;

    public zzfb(Context context, zzov zzov, DataLayer dataLayer, zzan zzan, zzan zzan2, zzbo zzbo) {
        if (zzov == null) {
            throw new NullPointerException("resource cannot be null");
        }
        this.zzajq = zzov;
        this.zzajx = new HashSet(zzov.zzls());
        this.zzaed = dataLayer;
        this.zzajr = zzbo;
        zzfc zzfc = new zzfc(this);
        new zzq();
        this.zzajv = zzq.zza(1048576, zzfc);
        zzfd zzfd = new zzfd(this);
        new zzq();
        this.zzajw = zzq.zza(1048576, zzfd);
        this.zzajs = new HashMap();
        zzb(new zzm(context));
        zzb(new zzam(zzan2));
        zzb(new zzaz(dataLayer));
        zzb(new zzgk(context, dataLayer));
        this.zzajt = new HashMap();
        zzc(new zzak());
        zzc(new zzbl());
        zzc(new zzbm());
        zzc(new zzbs());
        zzc(new zzbt());
        zzc(new zzde());
        zzc(new zzdf());
        zzc(new zzel());
        zzc(new zzfy());
        this.zzaju = new HashMap();
        zza((zzbq) new zze(context));
        zza((zzbq) new zzf(context));
        zza((zzbq) new zzh(context));
        zza((zzbq) new zzi(context));
        zza((zzbq) new zzj(context));
        zza((zzbq) new zzk(context));
        zza((zzbq) new zzl(context));
        zza((zzbq) new zzt());
        zza((zzbq) new zzaj(this.zzajq.getVersion()));
        zza((zzbq) new zzam(zzan));
        zza((zzbq) new zzas(dataLayer));
        zza((zzbq) new zzbc(context));
        zza((zzbq) new zzbd());
        zza((zzbq) new zzbk());
        zza((zzbq) new zzbp(this));
        zza((zzbq) new zzbu());
        zza((zzbq) new zzbv());
        zza((zzbq) new zzcv(context));
        zza((zzbq) new zzcx());
        zza((zzbq) new zzdd());
        zza((zzbq) new zzdk());
        zza((zzbq) new zzdm(context));
        zza((zzbq) new zzea());
        zza((zzbq) new zzee());
        zza((zzbq) new zzei());
        zza((zzbq) new zzek());
        zza((zzbq) new zzem(context));
        zza((zzbq) new zzfj());
        zza((zzbq) new zzfk());
        zza((zzbq) new zzge());
        zza((zzbq) new zzgl());
        this.zzajy = new HashMap();
        for (zzox next : this.zzajx) {
            for (int i = 0; i < next.zzmq().size(); i++) {
                zzot zzot = next.zzmq().get(i);
                zzfi zzb = zzb(this.zzajy, zza(zzot));
                zzb.zza(next);
                zzb.zza(next, zzot);
                zzb.zza(next, "Unknown");
            }
            for (int i2 = 0; i2 < next.zzmr().size(); i2++) {
                zzot zzot2 = next.zzmr().get(i2);
                zzfi zzb2 = zzb(this.zzajy, zza(zzot2));
                zzb2.zza(next);
                zzb2.zzb(next, zzot2);
                zzb2.zzb(next, "Unknown");
            }
        }
        for (Map.Entry next2 : this.zzajq.zzmo().entrySet()) {
            for (zzot zzot3 : (List) next2.getValue()) {
                if (!zzgj.zzg(zzot3.zzlu().get(zzb.NOT_DEFAULT_MACRO.toString())).booleanValue()) {
                    zzb(this.zzajy, (String) next2.getKey()).zzb(zzot3);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00f8, code lost:
        if (r2.longValue() > r12) goto L_0x00fa;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zze(java.util.List<com.google.android.gms.internal.gtm.zzj> r17) {
        /*
            r16 = this;
            monitor-enter(r16)
            java.util.Iterator r5 = r17.iterator()     // Catch:{ all -> 0x0044 }
        L_0x0005:
            boolean r2 = r5.hasNext()     // Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x0186
            java.lang.Object r2 = r5.next()     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.gtm.zzj r2 = (com.google.android.gms.internal.gtm.zzj) r2     // Catch:{ all -> 0x0044 }
            java.lang.String r3 = r2.name     // Catch:{ all -> 0x0044 }
            if (r3 == 0) goto L_0x001f
            java.lang.String r3 = r2.name     // Catch:{ all -> 0x0044 }
            java.lang.String r4 = "gaExperiment:"
            boolean r3 = r3.startsWith(r4)     // Catch:{ all -> 0x0044 }
            if (r3 != 0) goto L_0x0047
        L_0x001f:
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0044 }
            java.lang.String r3 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0044 }
            int r3 = r3.length()     // Catch:{ all -> 0x0044 }
            int r3 = r3 + 22
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0044 }
            r4.<init>(r3)     // Catch:{ all -> 0x0044 }
            java.lang.String r3 = "Ignored supplemental: "
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ all -> 0x0044 }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ all -> 0x0044 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0044 }
            com.google.android.gms.tagmanager.zzdi.zzab(r2)     // Catch:{ all -> 0x0044 }
            goto L_0x0005
        L_0x0044:
            r2 = move-exception
            monitor-exit(r16)
            throw r2
        L_0x0047:
            r0 = r16
            com.google.android.gms.tagmanager.DataLayer r6 = r0.zzaed     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.gtm.zzh r3 = r2.zzqi     // Catch:{ all -> 0x0044 }
            if (r3 != 0) goto L_0x0055
            java.lang.String r2 = "supplemental missing experimentSupplemental"
            com.google.android.gms.tagmanager.zzdi.zzac(r2)     // Catch:{ all -> 0x0044 }
            goto L_0x0005
        L_0x0055:
            com.google.android.gms.internal.gtm.zzh r3 = r2.zzqi     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.gtm.zzl[] r4 = r3.zzpf     // Catch:{ all -> 0x0044 }
            int r7 = r4.length     // Catch:{ all -> 0x0044 }
            r3 = 0
        L_0x005b:
            if (r3 >= r7) goto L_0x0069
            r8 = r4[r3]     // Catch:{ all -> 0x0044 }
            java.lang.String r8 = com.google.android.gms.tagmanager.zzgj.zzc(r8)     // Catch:{ all -> 0x0044 }
            r6.zzaq(r8)     // Catch:{ all -> 0x0044 }
            int r3 = r3 + 1
            goto L_0x005b
        L_0x0069:
            com.google.android.gms.internal.gtm.zzh r3 = r2.zzqi     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.gtm.zzl[] r7 = r3.zzpe     // Catch:{ all -> 0x0044 }
            int r8 = r7.length     // Catch:{ all -> 0x0044 }
            r3 = 0
            r4 = r3
        L_0x0070:
            if (r4 >= r8) goto L_0x00b3
            r3 = r7[r4]     // Catch:{ all -> 0x0044 }
            java.lang.Object r3 = com.google.android.gms.tagmanager.zzgj.zzh((com.google.android.gms.internal.gtm.zzl) r3)     // Catch:{ all -> 0x0044 }
            boolean r9 = r3 instanceof java.util.Map     // Catch:{ all -> 0x0044 }
            if (r9 != 0) goto L_0x00b0
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0044 }
            java.lang.String r9 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0044 }
            int r9 = r9.length()     // Catch:{ all -> 0x0044 }
            int r9 = r9 + 36
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0044 }
            r10.<init>(r9)     // Catch:{ all -> 0x0044 }
            java.lang.String r9 = "value: "
            java.lang.StringBuilder r9 = r10.append(r9)     // Catch:{ all -> 0x0044 }
            java.lang.StringBuilder r3 = r9.append(r3)     // Catch:{ all -> 0x0044 }
            java.lang.String r9 = " is not a map value, ignored."
            java.lang.StringBuilder r3 = r3.append(r9)     // Catch:{ all -> 0x0044 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0044 }
            com.google.android.gms.tagmanager.zzdi.zzac(r3)     // Catch:{ all -> 0x0044 }
            r3 = 0
        L_0x00a7:
            if (r3 == 0) goto L_0x00ac
            r6.push(r3)     // Catch:{ all -> 0x0044 }
        L_0x00ac:
            int r3 = r4 + 1
            r4 = r3
            goto L_0x0070
        L_0x00b0:
            java.util.Map r3 = (java.util.Map) r3     // Catch:{ all -> 0x0044 }
            goto L_0x00a7
        L_0x00b3:
            com.google.android.gms.internal.gtm.zzh r2 = r2.zzqi     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.gtm.zzc$zzc[] r7 = r2.zzpg     // Catch:{ all -> 0x0044 }
            int r8 = r7.length     // Catch:{ all -> 0x0044 }
            r2 = 0
            r4 = r2
        L_0x00ba:
            if (r4 >= r8) goto L_0x0005
            r9 = r7[r4]     // Catch:{ all -> 0x0044 }
            boolean r2 = r9.hasKey()     // Catch:{ all -> 0x0044 }
            if (r2 != 0) goto L_0x00cd
            java.lang.String r2 = "GaExperimentRandom: No key"
            com.google.android.gms.tagmanager.zzdi.zzac(r2)     // Catch:{ all -> 0x0044 }
        L_0x00c9:
            int r2 = r4 + 1
            r4 = r2
            goto L_0x00ba
        L_0x00cd:
            java.lang.String r2 = r9.getKey()     // Catch:{ all -> 0x0044 }
            java.lang.Object r3 = r6.get(r2)     // Catch:{ all -> 0x0044 }
            boolean r2 = r3 instanceof java.lang.Number     // Catch:{ all -> 0x0044 }
            if (r2 != 0) goto L_0x0151
            r2 = 0
        L_0x00da:
            long r10 = r9.zzg()     // Catch:{ all -> 0x0044 }
            long r12 = r9.zzh()     // Catch:{ all -> 0x0044 }
            boolean r14 = r9.zzi()     // Catch:{ all -> 0x0044 }
            if (r14 == 0) goto L_0x00fa
            if (r2 == 0) goto L_0x00fa
            long r14 = r2.longValue()     // Catch:{ all -> 0x0044 }
            int r14 = (r14 > r10 ? 1 : (r14 == r10 ? 0 : -1))
            if (r14 < 0) goto L_0x00fa
            long r14 = r2.longValue()     // Catch:{ all -> 0x0044 }
            int r2 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r2 <= 0) goto L_0x010f
        L_0x00fa:
            int r2 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r2 > 0) goto L_0x015f
            double r2 = java.lang.Math.random()     // Catch:{ all -> 0x0044 }
            long r12 = r12 - r10
            double r12 = (double) r12     // Catch:{ all -> 0x0044 }
            double r2 = r2 * r12
            double r10 = (double) r10     // Catch:{ all -> 0x0044 }
            double r2 = r2 + r10
            long r2 = java.lang.Math.round(r2)     // Catch:{ all -> 0x0044 }
            java.lang.Long r3 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x0044 }
        L_0x010f:
            java.lang.String r2 = r9.getKey()     // Catch:{ all -> 0x0044 }
            r6.zzaq(r2)     // Catch:{ all -> 0x0044 }
            java.lang.String r2 = r9.getKey()     // Catch:{ all -> 0x0044 }
            java.util.Map r3 = com.google.android.gms.tagmanager.DataLayer.zzg(r2, r3)     // Catch:{ all -> 0x0044 }
            long r10 = r9.zzj()     // Catch:{ all -> 0x0044 }
            r12 = 0
            int r2 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r2 <= 0) goto L_0x014c
            java.lang.String r2 = "gtm"
            boolean r2 = r3.containsKey(r2)     // Catch:{ all -> 0x0044 }
            if (r2 != 0) goto L_0x0166
            java.lang.String r2 = "gtm"
            r10 = 2
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ all -> 0x0044 }
            r11 = 0
            java.lang.String r12 = "lifetime"
            r10[r11] = r12     // Catch:{ all -> 0x0044 }
            r11 = 1
            long r12 = r9.zzj()     // Catch:{ all -> 0x0044 }
            java.lang.Long r9 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0044 }
            r10[r11] = r9     // Catch:{ all -> 0x0044 }
            java.util.Map r9 = com.google.android.gms.tagmanager.DataLayer.mapOf(r10)     // Catch:{ all -> 0x0044 }
            r3.put(r2, r9)     // Catch:{ all -> 0x0044 }
        L_0x014c:
            r6.push(r3)     // Catch:{ all -> 0x0044 }
            goto L_0x00c9
        L_0x0151:
            r0 = r3
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ all -> 0x0044 }
            r2 = r0
            long r10 = r2.longValue()     // Catch:{ all -> 0x0044 }
            java.lang.Long r2 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0044 }
            goto L_0x00da
        L_0x015f:
            java.lang.String r2 = "GaExperimentRandom: random range invalid"
            com.google.android.gms.tagmanager.zzdi.zzac(r2)     // Catch:{ all -> 0x0044 }
            goto L_0x00c9
        L_0x0166:
            java.lang.String r2 = "gtm"
            java.lang.Object r2 = r3.get(r2)     // Catch:{ all -> 0x0044 }
            boolean r10 = r2 instanceof java.util.Map     // Catch:{ all -> 0x0044 }
            if (r10 == 0) goto L_0x0180
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ all -> 0x0044 }
            java.lang.String r10 = "lifetime"
            long r12 = r9.zzj()     // Catch:{ all -> 0x0044 }
            java.lang.Long r9 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0044 }
            r2.put(r10, r9)     // Catch:{ all -> 0x0044 }
            goto L_0x014c
        L_0x0180:
            java.lang.String r2 = "GaExperimentRandom: gtm not a map"
            com.google.android.gms.tagmanager.zzdi.zzac(r2)     // Catch:{ all -> 0x0044 }
            goto L_0x014c
        L_0x0186:
            monitor-exit(r16)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzfb.zze(java.util.List):void");
    }

    public final synchronized void zzan(String str) {
        zzbk(str);
        zzar zzid = this.zzajr.zzba(str).zzid();
        for (zzot zza : zza(this.zzajx, (Set<String>) new HashSet(), (zzfg) new zzff(this), zzid.zzhs()).getObject()) {
            zza(this.zzajs, zza, (Set<String>) new HashSet(), zzid.zzhr());
        }
        zzbk((String) null);
    }

    public final zzdz<zzl> zzbj(String str) {
        this.zzaka = 0;
        return zza(str, (Set<String>) new HashSet(), this.zzajr.zzaz(str).zzic());
    }

    @VisibleForTesting
    private final synchronized void zzbk(String str) {
        this.zzajz = str;
    }

    /* access modifiers changed from: package-private */
    public final synchronized String zzjf() {
        return this.zzajz;
    }

    private static zzfi zzb(Map<String, zzfi> map, String str) {
        zzfi zzfi = map.get(str);
        if (zzfi != null) {
            return zzfi;
        }
        zzfi zzfi2 = new zzfi();
        map.put(str, zzfi2);
        return zzfi2;
    }

    private final zzdz<Set<zzot>> zza(Set<zzox> set, Set<String> set2, zzfg zzfg, zzfa zzfa) {
        zzdz zzdz;
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        boolean z = true;
        Iterator<zzox> it = set.iterator();
        while (true) {
            boolean z2 = z;
            if (it.hasNext()) {
                zzox next = it.next();
                zzeq zzis = zzfa.zzis();
                boolean z3 = true;
                Iterator<zzot> it2 = next.zzlx().iterator();
                while (true) {
                    boolean z4 = z3;
                    if (!it2.hasNext()) {
                        Iterator<zzot> it3 = next.zzlw().iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                zzgj.zzi(true);
                                zzdz = new zzdz(true, z4);
                                break;
                            }
                            zzdz<Boolean> zza = zza(it3.next(), set2, zzis.zzin());
                            if (!zza.getObject().booleanValue()) {
                                zzgj.zzi(false);
                                zzdz = new zzdz(false, zza.zziu());
                                break;
                            }
                            z4 = z4 && zza.zziu();
                        }
                    } else {
                        zzdz<Boolean> zza2 = zza(it2.next(), set2, zzis.zzim());
                        if (zza2.getObject().booleanValue()) {
                            zzgj.zzi(false);
                            zzdz = new zzdz(false, zza2.zziu());
                            break;
                        }
                        z3 = z4 && zza2.zziu();
                    }
                }
                if (((Boolean) zzdz.getObject()).booleanValue()) {
                    zzfg.zza(next, hashSet, hashSet2, zzis);
                }
                z = z2 && zzdz.zziu();
            } else {
                hashSet.removeAll(hashSet2);
                zzfa.zzb(hashSet);
                return new zzdz<>(hashSet, z2);
            }
        }
    }

    private static String zza(zzot zzot) {
        return zzgj.zzc(zzot.zzlu().get(zzb.INSTANCE_NAME.toString()));
    }

    private static void zza(Map<String, zzbq> map, zzbq zzbq) {
        if (map.containsKey(zzbq.zzif())) {
            String valueOf = String.valueOf(zzbq.zzif());
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Duplicate function type name: ".concat(valueOf) : new String("Duplicate function type name: "));
        } else {
            map.put(zzbq.zzif(), zzbq);
        }
    }

    @VisibleForTesting
    private final void zza(zzbq zzbq) {
        zza(this.zzaju, zzbq);
    }

    @VisibleForTesting
    private final void zzb(zzbq zzbq) {
        zza(this.zzajs, zzbq);
    }

    @VisibleForTesting
    private final void zzc(zzbq zzbq) {
        zza(this.zzajt, zzbq);
    }

    @VisibleForTesting
    private final zzdz<Boolean> zza(zzot zzot, Set<String> set, zzen zzen) {
        zzdz<zzl> zza = zza(this.zzajt, zzot, set, zzen);
        Boolean zzg = zzgj.zzg(zza.getObject());
        zzen.zza(zzgj.zzi(zzg));
        return new zzdz<>(zzg, zza.zziu());
    }

    private final String zzjg() {
        if (this.zzaka <= 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(this.zzaka));
        for (int i = 2; i < this.zzaka; i++) {
            sb.append(' ');
        }
        sb.append(": ");
        return sb.toString();
    }

    private final zzdz<zzl> zza(String str, Set<String> set, zzdl zzdl) {
        zzot zzot;
        zzdz<zzl> zzdz;
        this.zzaka++;
        zzfh zzfh = this.zzajw.get(str);
        if (zzfh != null) {
            this.zzajr.zzie();
            zza(zzfh.zzji(), set);
            this.zzaka--;
            return zzfh.zzjh();
        }
        zzfi zzfi = this.zzajy.get(str);
        if (zzfi == null) {
            String zzjg = zzjg();
            zzdi.zzav(new StringBuilder(String.valueOf(zzjg).length() + 15 + String.valueOf(str).length()).append(zzjg).append("Invalid macro: ").append(str).toString());
            this.zzaka--;
            return zzajp;
        }
        zzdz<Set<zzot>> zza = zza(zzfi.zzjj(), set, (zzfg) new zzfe(this, zzfi.zzjk(), zzfi.zzjl(), zzfi.zzjn(), zzfi.zzjm()), zzdl.zzhs());
        if (zza.getObject().isEmpty()) {
            zzot = zzfi.zzjo();
        } else {
            if (zza.getObject().size() > 1) {
                String zzjg2 = zzjg();
                zzdi.zzac(new StringBuilder(String.valueOf(zzjg2).length() + 37 + String.valueOf(str).length()).append(zzjg2).append("Multiple macros active for macroName ").append(str).toString());
            }
            zzot = (zzot) zza.getObject().iterator().next();
        }
        if (zzot == null) {
            this.zzaka--;
            return zzajp;
        }
        zzdz<zzl> zza2 = zza(this.zzaju, zzot, set, zzdl.zzil());
        boolean z = zza.zziu() && zza2.zziu();
        if (zza2 == zzajp) {
            zzdz = zzajp;
        } else {
            zzdz = new zzdz<>(zza2.getObject(), z);
        }
        zzl zzji = zzot.zzji();
        if (zzdz.zziu()) {
            this.zzajw.zza(str, new zzfh(zzdz, zzji));
        }
        zza(zzji, set);
        this.zzaka--;
        return zzdz;
    }

    private final void zza(zzl zzl, Set<String> set) {
        zzdz<zzl> zza;
        if (zzl != null && (zza = zza(zzl, set, (zzgm) new zzdx())) != zzajp) {
            Object zzh = zzgj.zzh(zza.getObject());
            if (zzh instanceof Map) {
                this.zzaed.push((Map) zzh);
            } else if (zzh instanceof List) {
                for (Object next : (List) zzh) {
                    if (next instanceof Map) {
                        this.zzaed.push((Map) next);
                    } else {
                        zzdi.zzac("pushAfterEvaluate: value not a Map");
                    }
                }
            } else {
                zzdi.zzac("pushAfterEvaluate: value not a Map or List");
            }
        }
    }

    private final zzdz<zzl> zza(zzl zzl, Set<String> set, zzgm zzgm) {
        if (!zzl.zzqw) {
            return new zzdz<>(zzl, true);
        }
        switch (zzl.type) {
            case 2:
                zzl zzk = zzor.zzk(zzl);
                zzk.zzqn = new zzl[zzl.zzqn.length];
                for (int i = 0; i < zzl.zzqn.length; i++) {
                    zzdz<zzl> zza = zza(zzl.zzqn[i], set, zzgm.zzv(i));
                    if (zza == zzajp) {
                        return zzajp;
                    }
                    zzk.zzqn[i] = zza.getObject();
                }
                return new zzdz<>(zzk, false);
            case 3:
                zzl zzk2 = zzor.zzk(zzl);
                if (zzl.zzqo.length != zzl.zzqp.length) {
                    String valueOf = String.valueOf(zzl.toString());
                    zzdi.zzav(valueOf.length() != 0 ? "Invalid serving value: ".concat(valueOf) : new String("Invalid serving value: "));
                    return zzajp;
                }
                zzk2.zzqo = new zzl[zzl.zzqo.length];
                zzk2.zzqp = new zzl[zzl.zzqo.length];
                for (int i2 = 0; i2 < zzl.zzqo.length; i2++) {
                    zzdz<zzl> zza2 = zza(zzl.zzqo[i2], set, zzgm.zzw(i2));
                    zzdz<zzl> zza3 = zza(zzl.zzqp[i2], set, zzgm.zzx(i2));
                    if (zza2 == zzajp || zza3 == zzajp) {
                        return zzajp;
                    }
                    zzk2.zzqo[i2] = zza2.getObject();
                    zzk2.zzqp[i2] = zza3.getObject();
                }
                return new zzdz<>(zzk2, false);
            case 4:
                if (set.contains(zzl.zzqq)) {
                    String str = zzl.zzqq;
                    String obj = set.toString();
                    zzdi.zzav(new StringBuilder(String.valueOf(str).length() + 79 + String.valueOf(obj).length()).append("Macro cycle detected.  Current macro reference: ").append(str).append(".  Previous macro references: ").append(obj).append(".").toString());
                    return zzajp;
                }
                set.add(zzl.zzqq);
                zzdz<zzl> zza4 = zzgn.zza(zza(zzl.zzqq, set, zzgm.zzit()), zzl.zzqv);
                set.remove(zzl.zzqq);
                return zza4;
            case 7:
                zzl zzk3 = zzor.zzk(zzl);
                zzk3.zzqu = new zzl[zzl.zzqu.length];
                for (int i3 = 0; i3 < zzl.zzqu.length; i3++) {
                    zzdz<zzl> zza5 = zza(zzl.zzqu[i3], set, zzgm.zzy(i3));
                    if (zza5 == zzajp) {
                        return zzajp;
                    }
                    zzk3.zzqu[i3] = zza5.getObject();
                }
                return new zzdz<>(zzk3, false);
            default:
                zzdi.zzav(new StringBuilder(25).append("Unknown type: ").append(zzl.type).toString());
                return zzajp;
        }
    }

    private final zzdz<zzl> zza(Map<String, zzbq> map, zzot zzot, Set<String> set, zzen zzen) {
        boolean z;
        boolean z2 = true;
        zzl zzl = zzot.zzlu().get(zzb.FUNCTION.toString());
        if (zzl == null) {
            zzdi.zzav("No function id in properties");
            return zzajp;
        }
        String str = zzl.zzqr;
        zzbq zzbq = map.get(str);
        if (zzbq == null) {
            zzdi.zzav(String.valueOf(str).concat(" has no backing implementation."));
            return zzajp;
        }
        zzdz<zzl> zzdz = this.zzajv.get(zzot);
        if (zzdz != null) {
            this.zzajr.zzie();
            return zzdz;
        }
        HashMap hashMap = new HashMap();
        boolean z3 = true;
        for (Map.Entry next : zzot.zzlu().entrySet()) {
            zzdz<zzl> zza = zza((zzl) next.getValue(), set, zzen.zzbg((String) next.getKey()).zzb((zzl) next.getValue()));
            if (zza == zzajp) {
                return zzajp;
            }
            if (zza.zziu()) {
                zzot.zza((String) next.getKey(), zza.getObject());
                z = z3;
            } else {
                z = false;
            }
            hashMap.put((String) next.getKey(), zza.getObject());
            z3 = z;
        }
        if (!zzbq.zza(hashMap.keySet())) {
            String valueOf = String.valueOf(zzbq.zzig());
            String valueOf2 = String.valueOf(hashMap.keySet());
            zzdi.zzav(new StringBuilder(String.valueOf(str).length() + 43 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length()).append("Incorrect keys for function ").append(str).append(" required ").append(valueOf).append(" had ").append(valueOf2).toString());
            return zzajp;
        }
        if (!z3 || !zzbq.zzgw()) {
            z2 = false;
        }
        zzdz<zzl> zzdz2 = new zzdz<>(zzbq.zzb(hashMap), z2);
        if (z2) {
            this.zzajv.zza(zzot, zzdz2);
        }
        zzen.zza(zzdz2.getObject());
        return zzdz2;
    }
}
