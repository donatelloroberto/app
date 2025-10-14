package com.google.android.gms.internal.ads;

@zzadh
public final class zzaco implements zzacd<zzoq> {
    private final boolean zzbto;
    private final boolean zzcbk;
    private final boolean zzcbl;

    public zzaco(boolean z, boolean z2, boolean z3) {
        this.zzcbk = z;
        this.zzcbl = z2;
        this.zzbto = z3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00d8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ com.google.android.gms.internal.ads.zzpb zza(com.google.android.gms.internal.ads.zzabv r14, org.json.JSONObject r15) throws org.json.JSONException, java.lang.InterruptedException, java.util.concurrent.ExecutionException {
        /*
            r13 = this;
            r3 = 0
            r11 = 0
            java.lang.String r2 = "images"
            boolean r4 = r13.zzcbk
            boolean r5 = r13.zzcbl
            r0 = r14
            r1 = r15
            java.util.List r0 = r0.zza(r1, r2, r3, r4, r5)
            java.lang.String r1 = "secondary_image"
            boolean r2 = r13.zzcbk
            com.google.android.gms.internal.ads.zzanz r4 = r14.zza(r15, r1, r3, r2)
            com.google.android.gms.internal.ads.zzanz r7 = r14.zzg(r15)
            java.lang.String r1 = "video"
            com.google.android.gms.internal.ads.zzanz r1 = r14.zzc((org.json.JSONObject) r15, (java.lang.String) r1)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r3 = r0.iterator()
        L_0x0029:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x003f
            java.lang.Object r0 = r3.next()
            com.google.android.gms.internal.ads.zzanz r0 = (com.google.android.gms.internal.ads.zzanz) r0
            java.lang.Object r0 = r0.get()
            com.google.android.gms.internal.ads.zzon r0 = (com.google.android.gms.internal.ads.zzon) r0
            r2.add(r0)
            goto L_0x0029
        L_0x003f:
            com.google.android.gms.internal.ads.zzaqw r10 = com.google.android.gms.internal.ads.zzabv.zzc(r1)
            com.google.android.gms.internal.ads.zzoq r0 = new com.google.android.gms.internal.ads.zzoq
            java.lang.String r1 = "headline"
            java.lang.String r3 = r15.getString(r1)
            boolean r1 = r13.zzbto
            if (r1 == 0) goto L_0x00d4
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r1 = com.google.android.gms.internal.ads.zznk.zzbfr
            com.google.android.gms.internal.ads.zzni r5 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r1 = r5.zzd(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x00d4
            com.google.android.gms.internal.ads.zzajm r1 = com.google.android.gms.ads.internal.zzbv.zzeo()
            android.content.res.Resources r1 = r1.getResources()
            if (r1 == 0) goto L_0x00d1
            int r5 = com.google.android.gms.ads.impl.R.string.s7
            java.lang.String r1 = r1.getString(r5)
        L_0x0071:
            if (r3 == 0) goto L_0x009d
            java.lang.String r5 = java.lang.String.valueOf(r1)
            int r5 = r5.length()
            int r5 = r5 + 3
            java.lang.String r6 = java.lang.String.valueOf(r3)
            int r6 = r6.length()
            int r5 = r5 + r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            java.lang.StringBuilder r1 = r6.append(r1)
            java.lang.String r5 = " : "
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r1 = r1.toString()
        L_0x009d:
            java.lang.String r3 = "body"
            java.lang.String r3 = r15.getString(r3)
            java.lang.Object r4 = r4.get()
            com.google.android.gms.internal.ads.zzpw r4 = (com.google.android.gms.internal.ads.zzpw) r4
            java.lang.String r5 = "call_to_action"
            java.lang.String r5 = r15.getString(r5)
            java.lang.String r6 = "advertiser"
            java.lang.String r6 = r15.getString(r6)
            java.lang.Object r7 = r7.get()
            com.google.android.gms.internal.ads.zzoj r7 = (com.google.android.gms.internal.ads.zzoj) r7
            android.os.Bundle r8 = new android.os.Bundle
            r8.<init>()
            if (r10 == 0) goto L_0x00d6
            com.google.android.gms.internal.ads.zzarl r9 = r10.zztm()
        L_0x00c6:
            if (r10 == 0) goto L_0x00d8
            android.view.View r10 = r10.getView()
        L_0x00cc:
            r12 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return r0
        L_0x00d1:
            java.lang.String r1 = "Test Ad"
            goto L_0x0071
        L_0x00d4:
            r1 = r3
            goto L_0x009d
        L_0x00d6:
            r9 = r11
            goto L_0x00c6
        L_0x00d8:
            r10 = r11
            goto L_0x00cc
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaco.zza(com.google.android.gms.internal.ads.zzabv, org.json.JSONObject):com.google.android.gms.internal.ads.zzpb");
    }
}
