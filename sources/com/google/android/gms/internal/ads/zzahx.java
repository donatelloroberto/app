package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.json.JSONObject;

@zzadh
public final class zzahx extends zzajx implements zzahw {
    private final Context mContext;
    private final Object mLock;
    private final zzaji zzbze;
    private final long zzclp;
    private final ArrayList<zzahn> zzcmd;
    private final List<zzahq> zzcme;
    private final HashSet<String> zzcmf;
    private final zzago zzcmg;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzahx(android.content.Context r7, com.google.android.gms.internal.ads.zzaji r8, com.google.android.gms.internal.ads.zzago r9) {
        /*
            r6 = this;
            com.google.android.gms.internal.ads.zzna<java.lang.Long> r0 = com.google.android.gms.internal.ads.zznk.zzaye
            com.google.android.gms.internal.ads.zzni r1 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r1.zzd(r0)
            java.lang.Long r0 = (java.lang.Long) r0
            long r4 = r0.longValue()
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
            r0.<init>(r1, r2, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahx.<init>(android.content.Context, com.google.android.gms.internal.ads.zzaji, com.google.android.gms.internal.ads.zzago):void");
    }

    @VisibleForTesting
    private zzahx(Context context, zzaji zzaji, zzago zzago, long j) {
        this.zzcmd = new ArrayList<>();
        this.zzcme = new ArrayList();
        this.zzcmf = new HashSet<>();
        this.mLock = new Object();
        this.mContext = context;
        this.zzbze = zzaji;
        this.zzcmg = zzago;
        this.zzclp = j;
    }

    private final zzajh zza(int i, @Nullable String str, @Nullable zzwx zzwx) {
        String substring;
        int i2;
        zzjj zzjj = this.zzbze.zzcgs.zzccv;
        List<String> list = this.zzbze.zzcos.zzbsn;
        List<String> list2 = this.zzbze.zzcos.zzbso;
        List<String> list3 = this.zzbze.zzcos.zzces;
        int i3 = this.zzbze.zzcos.orientation;
        long j = this.zzbze.zzcos.zzbsu;
        String str2 = this.zzbze.zzcgs.zzccy;
        boolean z = this.zzbze.zzcos.zzceq;
        zzwy zzwy = this.zzbze.zzcod;
        long j2 = this.zzbze.zzcos.zzcer;
        zzjn zzjn = this.zzbze.zzacv;
        long j3 = this.zzbze.zzcos.zzcep;
        long j4 = this.zzbze.zzcoh;
        long j5 = this.zzbze.zzcos.zzceu;
        String str3 = this.zzbze.zzcos.zzcev;
        JSONObject jSONObject = this.zzbze.zzcob;
        zzaig zzaig = this.zzbze.zzcos.zzcfe;
        List<String> list4 = this.zzbze.zzcos.zzcff;
        List<String> list5 = this.zzbze.zzcos.zzcfg;
        boolean z2 = this.zzbze.zzcos.zzcfh;
        zzael zzael = this.zzbze.zzcos.zzcfi;
        StringBuilder sb = new StringBuilder("");
        if (this.zzcme == null) {
            substring = sb.toString();
        } else {
            for (zzahq next : this.zzcme) {
                if (next != null && !TextUtils.isEmpty(next.zzbru)) {
                    String str4 = next.zzbru;
                    switch (next.errorCode) {
                        case 3:
                            i2 = 1;
                            break;
                        case 4:
                            i2 = 2;
                            break;
                        case 5:
                            i2 = 4;
                            break;
                        case 6:
                            i2 = 0;
                            break;
                        case 7:
                            i2 = 3;
                            break;
                        default:
                            i2 = 6;
                            break;
                    }
                    StringBuilder sb2 = sb;
                    sb2.append(String.valueOf(new StringBuilder(String.valueOf(str4).length() + 33).append(str4).append(".").append(i2).append(".").append(next.zzbub).toString()).concat("_"));
                }
            }
            substring = sb.substring(0, Math.max(0, sb.length() - 1));
        }
        return new zzajh(zzjj, (zzaqw) null, list, i, list2, list3, i3, j, str2, z, zzwx, (zzxq) null, str, zzwy, (zzxa) null, j2, zzjn, j3, j4, j5, str3, jSONObject, (zzpb) null, zzaig, list4, list5, z2, zzael, substring, this.zzbze.zzcos.zzbsr, this.zzbze.zzcos.zzcfl, this.zzbze.zzcoq, this.zzbze.zzcos.zzzl, this.zzbze.zzcor, this.zzbze.zzcos.zzcfp, this.zzbze.zzcos.zzbsp, this.zzbze.zzcos.zzzm, this.zzbze.zzcos.zzcfq);
    }

    public final void onStop() {
    }

    public final void zza(String str, int i) {
    }

    public final void zzcb(String str) {
        synchronized (this.mLock) {
            this.zzcmf.add(str);
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    public final void zzdn() {
        /*
            r15 = this;
            r14 = 0
            r10 = 0
            com.google.android.gms.internal.ads.zzaji r0 = r15.zzbze
            com.google.android.gms.internal.ads.zzwy r0 = r0.zzcod
            java.util.List<com.google.android.gms.internal.ads.zzwx> r0 = r0.zzbsm
            java.util.Iterator r11 = r0.iterator()
        L_0x000c:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L_0x00aa
            java.lang.Object r4 = r11.next()
            com.google.android.gms.internal.ads.zzwx r4 = (com.google.android.gms.internal.ads.zzwx) r4
            java.lang.String r3 = r4.zzbsb
            java.util.List<java.lang.String> r0 = r4.zzbrt
            java.util.Iterator r12 = r0.iterator()
        L_0x0020:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L_0x000c
            java.lang.Object r0 = r12.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x003c
            java.lang.String r1 = "com.google.ads.mediation.customevent.CustomEventAdapter"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0196
        L_0x003c:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0086 }
            r0.<init>(r3)     // Catch:{ JSONException -> 0x0086 }
            java.lang.String r1 = "class_name"
            java.lang.String r2 = r0.getString(r1)     // Catch:{ JSONException -> 0x0086 }
        L_0x0047:
            java.lang.Object r13 = r15.mLock
            monitor-enter(r13)
            com.google.android.gms.internal.ads.zzago r0 = r15.zzcmg     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzaib r6 = r0.zzca(r2)     // Catch:{ all -> 0x0083 }
            if (r6 == 0) goto L_0x005e
            com.google.android.gms.internal.ads.zzahv r0 = r6.zzpf()     // Catch:{ all -> 0x0083 }
            if (r0 == 0) goto L_0x005e
            com.google.android.gms.internal.ads.zzxq r0 = r6.zzpe()     // Catch:{ all -> 0x0083 }
            if (r0 != 0) goto L_0x008d
        L_0x005e:
            java.util.List<com.google.android.gms.internal.ads.zzahq> r0 = r15.zzcme     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzahs r1 = new com.google.android.gms.internal.ads.zzahs     // Catch:{ all -> 0x0083 }
            r1.<init>()     // Catch:{ all -> 0x0083 }
            java.lang.String r5 = r4.zzbru     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzahs r1 = r1.zzcd(r5)     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzahs r1 = r1.zzcc(r2)     // Catch:{ all -> 0x0083 }
            r6 = 0
            com.google.android.gms.internal.ads.zzahs r1 = r1.zzg(r6)     // Catch:{ all -> 0x0083 }
            r2 = 7
            com.google.android.gms.internal.ads.zzahs r1 = r1.zzad(r2)     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzahq r1 = r1.zzpd()     // Catch:{ all -> 0x0083 }
            r0.add(r1)     // Catch:{ all -> 0x0083 }
            monitor-exit(r13)     // Catch:{ all -> 0x0083 }
            goto L_0x0020
        L_0x0083:
            r0 = move-exception
            monitor-exit(r13)     // Catch:{ all -> 0x0083 }
            throw r0
        L_0x0086:
            r0 = move-exception
            java.lang.String r1 = "Unable to determine custom event class name, skipping..."
            com.google.android.gms.internal.ads.zzakb.zzb(r1, r0)
            goto L_0x0020
        L_0x008d:
            com.google.android.gms.internal.ads.zzahn r0 = new com.google.android.gms.internal.ads.zzahn     // Catch:{ all -> 0x0083 }
            android.content.Context r1 = r15.mContext     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzaji r5 = r15.zzbze     // Catch:{ all -> 0x0083 }
            long r8 = r15.zzclp     // Catch:{ all -> 0x0083 }
            r7 = r15
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzago r1 = r15.zzcmg     // Catch:{ all -> 0x0083 }
            com.google.android.gms.ads.internal.gmsg.zzb r1 = r1.zzos()     // Catch:{ all -> 0x0083 }
            r0.zza((com.google.android.gms.ads.internal.gmsg.zzb) r1)     // Catch:{ all -> 0x0083 }
            java.util.ArrayList<com.google.android.gms.internal.ads.zzahn> r1 = r15.zzcmd     // Catch:{ all -> 0x0083 }
            r1.add(r0)     // Catch:{ all -> 0x0083 }
            monitor-exit(r13)     // Catch:{ all -> 0x0083 }
            goto L_0x0020
        L_0x00aa:
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.ArrayList<com.google.android.gms.internal.ads.zzahn> r0 = r15.zzcmd
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r4 = r0.size()
            r2 = r10
        L_0x00b8:
            if (r2 >= r4) goto L_0x00ce
            java.lang.Object r1 = r0.get(r2)
            int r2 = r2 + 1
            com.google.android.gms.internal.ads.zzahn r1 = (com.google.android.gms.internal.ads.zzahn) r1
            java.lang.String r5 = r1.zzbth
            boolean r5 = r3.add(r5)
            if (r5 == 0) goto L_0x00b8
            r1.zzoz()
            goto L_0x00b8
        L_0x00ce:
            java.util.ArrayList<com.google.android.gms.internal.ads.zzahn> r0 = r15.zzcmd
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r4 = r0.size()
            r2 = r10
        L_0x00d7:
            if (r2 >= r4) goto L_0x0141
            java.lang.Object r1 = r0.get(r2)
            int r3 = r2 + 1
            com.google.android.gms.internal.ads.zzahn r1 = (com.google.android.gms.internal.ads.zzahn) r1
            java.util.concurrent.Future r2 = r1.zzoz()     // Catch:{ InterruptedException -> 0x0124, Exception -> 0x0154 }
            r2.get()     // Catch:{ InterruptedException -> 0x0124, Exception -> 0x0154 }
            java.lang.Object r2 = r15.mLock
            monitor-enter(r2)
            java.lang.String r5 = r1.zzbth     // Catch:{ all -> 0x0121 }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0121 }
            if (r5 != 0) goto L_0x00fc
            java.util.List<com.google.android.gms.internal.ads.zzahq> r5 = r15.zzcme     // Catch:{ all -> 0x0121 }
            com.google.android.gms.internal.ads.zzahq r6 = r1.zzpa()     // Catch:{ all -> 0x0121 }
            r5.add(r6)     // Catch:{ all -> 0x0121 }
        L_0x00fc:
            monitor-exit(r2)     // Catch:{ all -> 0x0121 }
            java.lang.Object r2 = r15.mLock
            monitor-enter(r2)
            java.util.HashSet<java.lang.String> r5 = r15.zzcmf     // Catch:{ all -> 0x0193 }
            java.lang.String r6 = r1.zzbth     // Catch:{ all -> 0x0193 }
            boolean r5 = r5.contains(r6)     // Catch:{ all -> 0x0193 }
            if (r5 == 0) goto L_0x018f
            java.lang.String r0 = r1.zzbth     // Catch:{ all -> 0x0193 }
            com.google.android.gms.internal.ads.zzwx r1 = r1.zzpb()     // Catch:{ all -> 0x0193 }
            r3 = -2
            com.google.android.gms.internal.ads.zzajh r0 = r15.zza(r3, r0, r1)     // Catch:{ all -> 0x0193 }
            android.os.Handler r1 = com.google.android.gms.internal.ads.zzamu.zzsy     // Catch:{ all -> 0x0193 }
            com.google.android.gms.internal.ads.zzahy r3 = new com.google.android.gms.internal.ads.zzahy     // Catch:{ all -> 0x0193 }
            r3.<init>(r15, r0)     // Catch:{ all -> 0x0193 }
            r1.post(r3)     // Catch:{ all -> 0x0193 }
            monitor-exit(r2)     // Catch:{ all -> 0x0193 }
        L_0x0120:
            return
        L_0x0121:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0121 }
            throw r0
        L_0x0124:
            r0 = move-exception
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0175 }
            r0.interrupt()     // Catch:{ all -> 0x0175 }
            java.lang.Object r2 = r15.mLock
            monitor-enter(r2)
            java.lang.String r0 = r1.zzbth     // Catch:{ all -> 0x0151 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0151 }
            if (r0 != 0) goto L_0x0140
            java.util.List<com.google.android.gms.internal.ads.zzahq> r0 = r15.zzcme     // Catch:{ all -> 0x0151 }
            com.google.android.gms.internal.ads.zzahq r1 = r1.zzpa()     // Catch:{ all -> 0x0151 }
            r0.add(r1)     // Catch:{ all -> 0x0151 }
        L_0x0140:
            monitor-exit(r2)     // Catch:{ all -> 0x0151 }
        L_0x0141:
            r0 = 3
            com.google.android.gms.internal.ads.zzajh r0 = r15.zza(r0, r14, r14)
            android.os.Handler r1 = com.google.android.gms.internal.ads.zzamu.zzsy
            com.google.android.gms.internal.ads.zzahz r2 = new com.google.android.gms.internal.ads.zzahz
            r2.<init>(r15, r0)
            r1.post(r2)
            goto L_0x0120
        L_0x0151:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0151 }
            throw r0
        L_0x0154:
            r2 = move-exception
            java.lang.String r5 = "Unable to resolve rewarded adapter."
            com.google.android.gms.internal.ads.zzakb.zzc(r5, r2)     // Catch:{ all -> 0x0175 }
            java.lang.Object r2 = r15.mLock
            monitor-enter(r2)
            java.lang.String r5 = r1.zzbth     // Catch:{ all -> 0x0172 }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0172 }
            if (r5 != 0) goto L_0x016e
            java.util.List<com.google.android.gms.internal.ads.zzahq> r5 = r15.zzcme     // Catch:{ all -> 0x0172 }
            com.google.android.gms.internal.ads.zzahq r1 = r1.zzpa()     // Catch:{ all -> 0x0172 }
            r5.add(r1)     // Catch:{ all -> 0x0172 }
        L_0x016e:
            monitor-exit(r2)     // Catch:{ all -> 0x0172 }
            r2 = r3
            goto L_0x00d7
        L_0x0172:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0172 }
            throw r0
        L_0x0175:
            r0 = move-exception
            java.lang.Object r2 = r15.mLock
            monitor-enter(r2)
            java.lang.String r3 = r1.zzbth     // Catch:{ all -> 0x018c }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x018c }
            if (r3 != 0) goto L_0x018a
            java.util.List<com.google.android.gms.internal.ads.zzahq> r3 = r15.zzcme     // Catch:{ all -> 0x018c }
            com.google.android.gms.internal.ads.zzahq r1 = r1.zzpa()     // Catch:{ all -> 0x018c }
            r3.add(r1)     // Catch:{ all -> 0x018c }
        L_0x018a:
            monitor-exit(r2)     // Catch:{ all -> 0x018c }
            throw r0
        L_0x018c:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x018c }
            throw r0
        L_0x018f:
            monitor-exit(r2)     // Catch:{ all -> 0x0193 }
            r2 = r3
            goto L_0x00d7
        L_0x0193:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0193 }
            throw r0
        L_0x0196:
            r2 = r0
            goto L_0x0047
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahx.zzdn():void");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzl(zzajh zzajh) {
        this.zzcmg.zzot().zzb(zzajh);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzm(zzajh zzajh) {
        this.zzcmg.zzot().zzb(zzajh);
    }
}
