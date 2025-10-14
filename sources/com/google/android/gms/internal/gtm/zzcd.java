package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class zzcd {
    private final List<zzbk> zzaaz;
    private final long zzaba;
    private final long zzabb;
    private final int zzabc;
    private final boolean zzabd;
    private final String zzabe;
    private final Map<String, String> zztc;

    public zzcd(zzam zzam, Map<String, String> map, long j, boolean z) {
        this(zzam, map, j, z, 0, 0, (List<zzbk>) null);
    }

    public zzcd(zzam zzam, Map<String, String> map, long j, boolean z, long j2, int i) {
        this(zzam, map, j, z, j2, i, (List<zzbk>) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00bd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzcd(com.google.android.gms.internal.gtm.zzam r6, java.util.Map<java.lang.String, java.lang.String> r7, long r8, boolean r10, long r11, int r13, java.util.List<com.google.android.gms.internal.gtm.zzbk> r14) {
        /*
            r5 = this;
            r1 = 0
            r5.<init>()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r7)
            r5.zzabb = r8
            r5.zzabd = r10
            r5.zzaba = r11
            r5.zzabc = r13
            if (r14 == 0) goto L_0x007a
            r0 = r14
        L_0x0015:
            r5.zzaaz = r0
            if (r14 == 0) goto L_0x00e4
            java.util.Iterator r2 = r14.iterator()
        L_0x001d:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x00e4
            java.lang.Object r0 = r2.next()
            com.google.android.gms.internal.gtm.zzbk r0 = (com.google.android.gms.internal.gtm.zzbk) r0
            java.lang.String r3 = "appendVersion"
            java.lang.String r4 = r0.getId()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x001d
            java.lang.String r0 = r0.getValue()
        L_0x0039:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 == 0) goto L_0x007f
        L_0x003f:
            r5.zzabe = r1
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.util.Set r0 = r7.entrySet()
            java.util.Iterator r2 = r0.iterator()
        L_0x004e:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0081
            java.lang.Object r0 = r2.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r3 = r0.getKey()
            boolean r3 = zzc(r3)
            if (r3 == 0) goto L_0x004e
            java.lang.Object r3 = r0.getKey()
            java.lang.String r3 = zza(r6, r3)
            if (r3 == 0) goto L_0x004e
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = zzb(r6, r0)
            r1.put(r3, r0)
            goto L_0x004e
        L_0x007a:
            java.util.List r0 = java.util.Collections.emptyList()
            goto L_0x0015
        L_0x007f:
            r1 = r0
            goto L_0x003f
        L_0x0081:
            java.util.Set r0 = r7.entrySet()
            java.util.Iterator r2 = r0.iterator()
        L_0x0089:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x00b5
            java.lang.Object r0 = r2.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r3 = r0.getKey()
            boolean r3 = zzc(r3)
            if (r3 != 0) goto L_0x0089
            java.lang.Object r3 = r0.getKey()
            java.lang.String r3 = zza(r6, r3)
            if (r3 == 0) goto L_0x0089
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = zzb(r6, r0)
            r1.put(r3, r0)
            goto L_0x0089
        L_0x00b5:
            java.lang.String r0 = r5.zzabe
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x00dd
            java.lang.String r0 = "_v"
            java.lang.String r2 = r5.zzabe
            com.google.android.gms.internal.gtm.zzcz.zzb((java.util.Map<java.lang.String, java.lang.String>) r1, (java.lang.String) r0, (java.lang.String) r2)
            java.lang.String r0 = r5.zzabe
            java.lang.String r2 = "ma4.0.0"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x00d8
            java.lang.String r0 = r5.zzabe
            java.lang.String r2 = "ma4.0.1"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x00dd
        L_0x00d8:
            java.lang.String r0 = "adid"
            r1.remove(r0)
        L_0x00dd:
            java.util.Map r0 = java.util.Collections.unmodifiableMap(r1)
            r5.zztc = r0
            return
        L_0x00e4:
            r0 = r1
            goto L_0x0039
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzcd.<init>(com.google.android.gms.internal.gtm.zzam, java.util.Map, long, boolean, long, int, java.util.List):void");
    }

    private static boolean zzc(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj.toString().startsWith("&");
    }

    private static String zza(zzam zzam, Object obj) {
        if (obj == null) {
            return null;
        }
        String obj2 = obj.toString();
        if (obj2.startsWith("&")) {
            obj2 = obj2.substring(1);
        }
        int length = obj2.length();
        if (length > 256) {
            obj2 = obj2.substring(0, 256);
            zzam.zzc("Hit param name is too long and will be trimmed", Integer.valueOf(length), obj2);
        }
        if (TextUtils.isEmpty(obj2)) {
            return null;
        }
        return obj2;
    }

    private static String zzb(zzam zzam, Object obj) {
        String obj2;
        if (obj == null) {
            obj2 = "";
        } else {
            obj2 = obj.toString();
        }
        int length = obj2.length();
        if (length <= 8192) {
            return obj2;
        }
        String substring = obj2.substring(0, 8192);
        zzam.zzc("Hit param value is too long and will be trimmed", Integer.valueOf(length), substring);
        return substring;
    }

    public final int zzff() {
        return this.zzabc;
    }

    public final Map<String, String> zzdm() {
        return this.zztc;
    }

    public final long zzfg() {
        return this.zzaba;
    }

    public final long zzfh() {
        return this.zzabb;
    }

    public final List<zzbk> zzfi() {
        return this.zzaaz;
    }

    public final boolean zzfj() {
        return this.zzabd;
    }

    public final long zzfk() {
        return zzcz.zzag(zzd("_s", "0"));
    }

    public final String zzfl() {
        return zzd("_m", "");
    }

    private final String zzd(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkArgument(!str.startsWith("&"), "Short param name required");
        String str3 = this.zztc.get(str);
        if (str3 != null) {
            return str3;
        }
        return str2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ht=").append(this.zzabb);
        if (this.zzaba != 0) {
            sb.append(", dbId=").append(this.zzaba);
        }
        if (this.zzabc != 0) {
            sb.append(", appUID=").append(this.zzabc);
        }
        ArrayList arrayList = new ArrayList(this.zztc.keySet());
        Collections.sort(arrayList);
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            String str = (String) obj;
            sb.append(", ");
            sb.append(str);
            sb.append("=");
            sb.append(this.zztc.get(str));
        }
        return sb.toString();
    }
}
