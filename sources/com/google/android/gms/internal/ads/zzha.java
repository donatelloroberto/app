package com.google.android.gms.internal.ads;

@zzadh
public final class zzha {
    private final int zzaiz;
    private final zzgq zzajb;
    private String zzajj;
    private String zzajk;
    private final boolean zzajl = false;
    private final int zzajm;
    private final int zzajn;

    public zzha(int i, int i2, int i3) {
        this.zzaiz = i;
        if (i2 > 64 || i2 < 0) {
            this.zzajm = 64;
        } else {
            this.zzajm = i2;
        }
        if (i3 <= 0) {
            this.zzajn = 1;
        } else {
            this.zzajn = i3;
        }
        this.zzajb = new zzgz(this.zzajm);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d0 A[LOOP:0: B:1:0x0012->B:49:0x00d0, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0115 A[EDGE_INSN: B:77:0x0115->B:64:0x0115 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x010f A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zza(java.util.ArrayList<java.lang.String> r14, java.util.ArrayList<com.google.android.gms.internal.ads.zzgp> r15) {
        /*
            r13 = this;
            r12 = 32
            r4 = 1
            r2 = 0
            com.google.android.gms.internal.ads.zzhb r0 = new com.google.android.gms.internal.ads.zzhb
            r0.<init>(r13)
            java.util.Collections.sort(r15, r0)
            java.util.HashSet r7 = new java.util.HashSet
            r7.<init>()
            r1 = r2
        L_0x0012:
            int r0 = r15.size()
            if (r1 >= r0) goto L_0x0115
            java.lang.Object r0 = r15.get(r1)
            com.google.android.gms.internal.ads.zzgp r0 = (com.google.android.gms.internal.ads.zzgp) r0
            int r0 = r0.zzhf()
            java.lang.Object r0 = r14.get(r0)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            java.text.Normalizer$Form r3 = java.text.Normalizer.Form.NFKC
            java.lang.String r0 = java.text.Normalizer.normalize(r0, r3)
            java.util.Locale r3 = java.util.Locale.US
            java.lang.String r0 = r0.toLowerCase(r3)
            java.lang.String r3 = "\n"
            java.lang.String[] r8 = r0.split(r3)
            int r0 = r8.length
            if (r0 == 0) goto L_0x0113
            r0 = r2
        L_0x003e:
            int r3 = r8.length
            if (r0 >= r3) goto L_0x0113
            r6 = r8[r0]
            java.lang.String r3 = "'"
            int r3 = r6.indexOf(r3)
            r5 = -1
            if (r3 == r5) goto L_0x0146
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r6)
            r3 = r4
            r5 = r2
        L_0x0053:
            int r10 = r3 + 2
            int r11 = r9.length()
            if (r10 > r11) goto L_0x009c
            char r10 = r9.charAt(r3)
            r11 = 39
            if (r10 != r11) goto L_0x0095
            int r5 = r3 + -1
            char r5 = r9.charAt(r5)
            if (r5 == r12) goto L_0x0098
            int r5 = r3 + 1
            char r5 = r9.charAt(r5)
            r10 = 115(0x73, float:1.61E-43)
            if (r5 == r10) goto L_0x007f
            int r5 = r3 + 1
            char r5 = r9.charAt(r5)
            r10 = 83
            if (r5 != r10) goto L_0x0098
        L_0x007f:
            int r5 = r3 + 2
            int r10 = r9.length()
            if (r5 == r10) goto L_0x008f
            int r5 = r3 + 2
            char r5 = r9.charAt(r5)
            if (r5 != r12) goto L_0x0098
        L_0x008f:
            r9.insert(r3, r12)
            int r3 = r3 + 2
        L_0x0094:
            r5 = r4
        L_0x0095:
            int r3 = r3 + 1
            goto L_0x0053
        L_0x0098:
            r9.setCharAt(r3, r12)
            goto L_0x0094
        L_0x009c:
            if (r5 == 0) goto L_0x00d5
            java.lang.String r3 = r9.toString()
        L_0x00a2:
            if (r3 == 0) goto L_0x0146
            r13.zzajk = r3
        L_0x00a6:
            java.lang.String[] r9 = com.google.android.gms.internal.ads.zzgu.zzb(r3, r4)
            int r3 = r9.length
            int r5 = r13.zzajn
            if (r3 < r5) goto L_0x010f
            r3 = r2
        L_0x00b0:
            int r5 = r9.length
            if (r3 >= r5) goto L_0x0105
            java.lang.String r5 = ""
            r6 = r2
        L_0x00b6:
            int r10 = r13.zzajn
            if (r6 >= r10) goto L_0x0143
            int r10 = r3 + r6
            int r11 = r9.length
            if (r10 < r11) goto L_0x00d7
            r6 = r2
        L_0x00c0:
            if (r6 == 0) goto L_0x0105
            r7.add(r5)
            int r5 = r7.size()
            int r6 = r13.zzaiz
            if (r5 < r6) goto L_0x0102
            r0 = r2
        L_0x00ce:
            if (r0 == 0) goto L_0x0115
            int r0 = r1 + 1
            r1 = r0
            goto L_0x0012
        L_0x00d5:
            r3 = 0
            goto L_0x00a2
        L_0x00d7:
            if (r6 <= 0) goto L_0x00e3
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r10 = " "
            java.lang.String r5 = r5.concat(r10)
        L_0x00e3:
            java.lang.String r10 = java.lang.String.valueOf(r5)
            int r5 = r3 + r6
            r5 = r9[r5]
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r11 = r5.length()
            if (r11 == 0) goto L_0x00fc
            java.lang.String r5 = r10.concat(r5)
        L_0x00f9:
            int r6 = r6 + 1
            goto L_0x00b6
        L_0x00fc:
            java.lang.String r5 = new java.lang.String
            r5.<init>(r10)
            goto L_0x00f9
        L_0x0102:
            int r3 = r3 + 1
            goto L_0x00b0
        L_0x0105:
            int r3 = r7.size()
            int r5 = r13.zzaiz
            if (r3 < r5) goto L_0x010f
            r0 = r2
            goto L_0x00ce
        L_0x010f:
            int r0 = r0 + 1
            goto L_0x003e
        L_0x0113:
            r0 = r4
            goto L_0x00ce
        L_0x0115:
            com.google.android.gms.internal.ads.zzgt r1 = new com.google.android.gms.internal.ads.zzgt
            r1.<init>()
            java.lang.String r0 = ""
            r13.zzajj = r0
            java.util.Iterator r2 = r7.iterator()
        L_0x0122:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x013e
            java.lang.Object r0 = r2.next()
            java.lang.String r0 = (java.lang.String) r0
            com.google.android.gms.internal.ads.zzgq r3 = r13.zzajb     // Catch:{ IOException -> 0x0138 }
            byte[] r0 = r3.zzx(r0)     // Catch:{ IOException -> 0x0138 }
            r1.write(r0)     // Catch:{ IOException -> 0x0138 }
            goto L_0x0122
        L_0x0138:
            r0 = move-exception
            java.lang.String r2 = "Error while writing hash to byteStream"
            com.google.android.gms.internal.ads.zzakb.zzb(r2, r0)
        L_0x013e:
            java.lang.String r0 = r1.toString()
            return r0
        L_0x0143:
            r6 = r4
            goto L_0x00c0
        L_0x0146:
            r3 = r6
            goto L_0x00a6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzha.zza(java.util.ArrayList, java.util.ArrayList):java.lang.String");
    }
}
