package com.google.android.gms.internal.ads;

import android.support.v7.widget.helper.ItemTouchHelper;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class zzp {
    public final List<zzl> allHeaders;
    public final byte[] data;
    public final int statusCode;
    public final Map<String, String> zzab;
    public final boolean zzac;
    private final long zzad;

    private zzp(int i, byte[] bArr, Map<String, String> map, List<zzl> list, boolean z, long j) {
        this.statusCode = i;
        this.data = bArr;
        this.zzab = map;
        if (list == null) {
            this.allHeaders = null;
        } else {
            this.allHeaders = Collections.unmodifiableList(list);
        }
        this.zzac = z;
        this.zzad = j;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzp(int r10, byte[] r11, java.util.Map<java.lang.String, java.lang.String> r12, boolean r13, long r14) {
        /*
            r9 = this;
            if (r12 != 0) goto L_0x000d
            r4 = 0
        L_0x0003:
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r5 = r13
            r6 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        L_0x000d:
            boolean r0 = r12.isEmpty()
            if (r0 == 0) goto L_0x0018
            java.util.List r4 = java.util.Collections.emptyList()
            goto L_0x0003
        L_0x0018:
            java.util.ArrayList r4 = new java.util.ArrayList
            int r0 = r12.size()
            r4.<init>(r0)
            java.util.Set r0 = r12.entrySet()
            java.util.Iterator r2 = r0.iterator()
        L_0x0029:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0003
            java.lang.Object r0 = r2.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            com.google.android.gms.internal.ads.zzl r3 = new com.google.android.gms.internal.ads.zzl
            java.lang.Object r1 = r0.getKey()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            r3.<init>(r1, r0)
            r4.add(r3)
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzp.<init>(int, byte[], java.util.Map, boolean, long):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzp(int r9, byte[] r10, boolean r11, long r12, java.util.List<com.google.android.gms.internal.ads.zzl> r14) {
        /*
            r8 = this;
            if (r14 != 0) goto L_0x000d
            r3 = 0
        L_0x0003:
            r0 = r8
            r1 = r9
            r2 = r10
            r4 = r14
            r5 = r11
            r6 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        L_0x000d:
            boolean r0 = r14.isEmpty()
            if (r0 == 0) goto L_0x0018
            java.util.Map r3 = java.util.Collections.emptyMap()
            goto L_0x0003
        L_0x0018:
            java.util.TreeMap r3 = new java.util.TreeMap
            java.util.Comparator r0 = java.lang.String.CASE_INSENSITIVE_ORDER
            r3.<init>(r0)
            java.util.Iterator r1 = r14.iterator()
        L_0x0023:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x0003
            java.lang.Object r0 = r1.next()
            com.google.android.gms.internal.ads.zzl r0 = (com.google.android.gms.internal.ads.zzl) r0
            java.lang.String r2 = r0.getName()
            java.lang.String r0 = r0.getValue()
            r3.put(r2, r0)
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzp.<init>(int, byte[], boolean, long, java.util.List):void");
    }

    @Deprecated
    public zzp(byte[] bArr, Map<String, String> map) {
        this((int) ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, bArr, map, false, 0);
    }
}
