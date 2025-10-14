package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;

public class zzaj implements zzm {
    private static final boolean DEBUG = zzaf.DEBUG;
    @Deprecated
    private final zzar zzbo;
    private final zzai zzbp;
    private final zzak zzbq;

    public zzaj(zzai zzai) {
        this(zzai, new zzak(4096));
    }

    private zzaj(zzai zzai, zzak zzak) {
        this.zzbp = zzai;
        this.zzbo = zzai;
        this.zzbq = zzak;
    }

    @Deprecated
    public zzaj(zzar zzar) {
        this(zzar, new zzak(4096));
    }

    @Deprecated
    private zzaj(zzar zzar, zzak zzak) {
        this.zzbo = zzar;
        this.zzbp = new zzah(zzar);
        this.zzbq = zzak;
    }

    private static void zza(String str, zzr<?> zzr, zzae zzae) throws zzae {
        zzab zzj = zzr.zzj();
        int zzi = zzr.zzi();
        try {
            zzj.zza(zzae);
            zzr.zzb(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(zzi)}));
        } catch (zzae e) {
            zzr.zzb(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(zzi)}));
            throw e;
        }
    }

    private final byte[] zza(InputStream inputStream, int i) throws IOException, zzac {
        zzau zzau = new zzau(this.zzbq, i);
        if (inputStream == null) {
            try {
                throw new zzac();
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        zzaf.v("Error occurred when closing InputStream", new Object[0]);
                    }
                }
                this.zzbq.zza((byte[]) null);
                zzau.close();
                throw th;
            }
        } else {
            byte[] zzb = this.zzbq.zzb(1024);
            while (true) {
                int read = inputStream.read(zzb);
                if (read == -1) {
                    break;
                }
                zzau.write(zzb, 0, read);
            }
            byte[] byteArray = zzau.toByteArray();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    zzaf.v("Error occurred when closing InputStream", new Object[0]);
                }
            }
            this.zzbq.zza(zzb);
            zzau.close();
            return byteArray;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0212, code lost:
        throw new com.google.android.gms.internal.ads.zzg(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0215, code lost:
        if (r3 < 500) goto L_0x0221;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0220, code lost:
        throw new com.google.android.gms.internal.ads.zzac(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0226, code lost:
        throw new com.google.android.gms.internal.ads.zzac(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0227, code lost:
        zza("network", r21, new com.google.android.gms.internal.ads.zzo());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0235, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0236, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:?, code lost:
        return new com.google.android.gms.internal.ads.zzp(304, r4.data, true, android.os.SystemClock.elapsedRealtime() - r18, (java.util.List<com.google.android.gms.internal.ads.zzl>) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0065, code lost:
        zza("socket", r21, new com.google.android.gms.internal.ads.zzad());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0097, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0098, code lost:
        r3 = r2;
        r2 = java.lang.String.valueOf(r21.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a9, code lost:
        if (r2.length() != 0) goto L_0x00ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00ab, code lost:
        r2 = "Bad URL ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b2, code lost:
        throw new java.lang.RuntimeException(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e8, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00e9, code lost:
        r4 = null;
        r3 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ee, code lost:
        r3 = r3.getStatusCode();
        com.google.android.gms.internal.ads.zzaf.e("Unexpected response code %d for %s", java.lang.Integer.valueOf(r3), r21.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0108, code lost:
        if (r4 != null) goto L_0x010a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x010a, code lost:
        r2 = new com.google.android.gms.internal.ads.zzp(r3, r4, false, android.os.SystemClock.elapsedRealtime() - r18, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0118, code lost:
        if (r3 == 401) goto L_0x011e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x011e, code lost:
        zza("auth", r21, new com.google.android.gms.internal.ads.zza(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01f8, code lost:
        r2 = new java.lang.String("Bad URL ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0204, code lost:
        throw new com.google.android.gms.internal.ads.zzq(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0207, code lost:
        if (r3 < 400) goto L_0x0213;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x01ff A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0064 A[ExcHandler: SocketTimeoutException (e java.net.SocketTimeoutException), Splitter:B:35:0x00b3] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0097 A[ExcHandler: MalformedURLException (r2v5 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:2:0x000a] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ee  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.ads.zzp zzc(com.google.android.gms.internal.ads.zzr<?> r21) throws com.google.android.gms.internal.ads.zzae {
        /*
            r20 = this;
            long r18 = android.os.SystemClock.elapsedRealtime()
        L_0x0004:
            r3 = 0
            r9 = 0
            java.util.List r8 = java.util.Collections.emptyList()
            com.google.android.gms.internal.ads.zzc r4 = r21.zzf()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x0235 }
            if (r4 != 0) goto L_0x0040
            java.util.Map r2 = java.util.Collections.emptyMap()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x0235 }
        L_0x0014:
            r0 = r20
            com.google.android.gms.internal.ads.zzai r4 = r0.zzbp     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x0235 }
            r0 = r21
            com.google.android.gms.internal.ads.zzaq r17 = r4.zza(r0, r2)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x0235 }
            int r3 = r17.getStatusCode()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            java.util.List r8 = r17.zzq()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            r2 = 304(0x130, float:4.26E-43)
            if (r3 != r2) goto L_0x017e
            com.google.android.gms.internal.ads.zzc r4 = r21.zzf()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            if (r4 != 0) goto L_0x0072
            com.google.android.gms.internal.ads.zzp r2 = new com.google.android.gms.internal.ads.zzp     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            r3 = 304(0x130, float:4.26E-43)
            r4 = 0
            r5 = 1
            long r6 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            long r6 = r6 - r18
            r2.<init>((int) r3, (byte[]) r4, (boolean) r5, (long) r6, (java.util.List<com.google.android.gms.internal.ads.zzl>) r8)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
        L_0x003f:
            return r2
        L_0x0040:
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x0235 }
            r2.<init>()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x0235 }
            java.lang.String r5 = r4.zza     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x0235 }
            if (r5 == 0) goto L_0x0050
            java.lang.String r5 = "If-None-Match"
            java.lang.String r6 = r4.zza     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x0235 }
            r2.put(r5, r6)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x0235 }
        L_0x0050:
            long r6 = r4.zzc     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x0235 }
            r10 = 0
            int r5 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r5 <= 0) goto L_0x0014
            java.lang.String r5 = "If-Modified-Since"
            long r6 = r4.zzc     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x0235 }
            java.lang.String r4 = com.google.android.gms.internal.ads.zzap.zzb((long) r6)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x0235 }
            r2.put(r5, r4)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x0235 }
            goto L_0x0014
        L_0x0064:
            r2 = move-exception
            java.lang.String r2 = "socket"
            com.google.android.gms.internal.ads.zzad r3 = new com.google.android.gms.internal.ads.zzad
            r3.<init>()
            r0 = r21
            zza(r2, r0, r3)
            goto L_0x0004
        L_0x0072:
            java.util.TreeSet r5 = new java.util.TreeSet     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            java.util.Comparator r2 = java.lang.String.CASE_INSENSITIVE_ORDER     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            r5.<init>(r2)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            boolean r2 = r8.isEmpty()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            if (r2 != 0) goto L_0x00b3
            java.util.Iterator r3 = r8.iterator()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
        L_0x0083:
            boolean r2 = r3.hasNext()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            if (r2 == 0) goto L_0x00b3
            java.lang.Object r2 = r3.next()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            com.google.android.gms.internal.ads.zzl r2 = (com.google.android.gms.internal.ads.zzl) r2     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            java.lang.String r2 = r2.getName()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            r5.add(r2)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            goto L_0x0083
        L_0x0097:
            r2 = move-exception
            r3 = r2
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            java.lang.String r5 = "Bad URL "
            java.lang.String r2 = r21.getUrl()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r6 = r2.length()
            if (r6 == 0) goto L_0x01f8
            java.lang.String r2 = r5.concat(r2)
        L_0x00af:
            r4.<init>(r2, r3)
            throw r4
        L_0x00b3:
            java.util.ArrayList r16 = new java.util.ArrayList     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            r0 = r16
            r0.<init>(r8)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            java.util.List<com.google.android.gms.internal.ads.zzl> r2 = r4.zzg     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            if (r2 == 0) goto L_0x012c
            java.util.List<com.google.android.gms.internal.ads.zzl> r2 = r4.zzg     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            boolean r2 = r2.isEmpty()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            if (r2 != 0) goto L_0x016b
            java.util.List<com.google.android.gms.internal.ads.zzl> r2 = r4.zzg     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            java.util.Iterator r3 = r2.iterator()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
        L_0x00cc:
            boolean r2 = r3.hasNext()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            if (r2 == 0) goto L_0x016b
            java.lang.Object r2 = r3.next()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            com.google.android.gms.internal.ads.zzl r2 = (com.google.android.gms.internal.ads.zzl) r2     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            java.lang.String r6 = r2.getName()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            boolean r6 = r5.contains(r6)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            if (r6 != 0) goto L_0x00cc
            r0 = r16
            r0.add(r2)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            goto L_0x00cc
        L_0x00e8:
            r2 = move-exception
            r4 = r9
            r3 = r17
        L_0x00ec:
            if (r3 == 0) goto L_0x01ff
            int r3 = r3.getStatusCode()
            java.lang.String r2 = "Unexpected response code %d for %s"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            java.lang.Integer r7 = java.lang.Integer.valueOf(r3)
            r5[r6] = r7
            r6 = 1
            java.lang.String r7 = r21.getUrl()
            r5[r6] = r7
            com.google.android.gms.internal.ads.zzaf.e(r2, r5)
            if (r4 == 0) goto L_0x0227
            com.google.android.gms.internal.ads.zzp r2 = new com.google.android.gms.internal.ads.zzp
            r5 = 0
            long r6 = android.os.SystemClock.elapsedRealtime()
            long r6 = r6 - r18
            r2.<init>((int) r3, (byte[]) r4, (boolean) r5, (long) r6, (java.util.List<com.google.android.gms.internal.ads.zzl>) r8)
            r4 = 401(0x191, float:5.62E-43)
            if (r3 == r4) goto L_0x011e
            r4 = 403(0x193, float:5.65E-43)
            if (r3 != r4) goto L_0x0205
        L_0x011e:
            java.lang.String r3 = "auth"
            com.google.android.gms.internal.ads.zza r4 = new com.google.android.gms.internal.ads.zza
            r4.<init>(r2)
            r0 = r21
            zza(r3, r0, r4)
            goto L_0x0004
        L_0x012c:
            java.util.Map<java.lang.String, java.lang.String> r2 = r4.zzf     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            boolean r2 = r2.isEmpty()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            if (r2 != 0) goto L_0x016b
            java.util.Map<java.lang.String, java.lang.String> r2 = r4.zzf     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            java.util.Set r2 = r2.entrySet()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            java.util.Iterator r6 = r2.iterator()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
        L_0x013e:
            boolean r2 = r6.hasNext()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            if (r2 == 0) goto L_0x016b
            java.lang.Object r2 = r6.next()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            java.lang.Object r3 = r2.getKey()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            boolean r3 = r5.contains(r3)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            if (r3 != 0) goto L_0x013e
            com.google.android.gms.internal.ads.zzl r7 = new com.google.android.gms.internal.ads.zzl     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            java.lang.Object r3 = r2.getKey()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            r7.<init>(r3, r2)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            r0 = r16
            r0.add(r7)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            goto L_0x013e
        L_0x016b:
            com.google.android.gms.internal.ads.zzp r10 = new com.google.android.gms.internal.ads.zzp     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            r11 = 304(0x130, float:4.26E-43)
            byte[] r12 = r4.data     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            r13 = 1
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            long r14 = r2 - r18
            r10.<init>((int) r11, (byte[]) r12, (boolean) r13, (long) r14, (java.util.List<com.google.android.gms.internal.ads.zzl>) r16)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            r2 = r10
            goto L_0x003f
        L_0x017e:
            java.io.InputStream r2 = r17.getContent()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            if (r2 == 0) goto L_0x01e3
            int r4 = r17.getContentLength()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            r0 = r20
            byte[] r4 = r0.zza(r2, r4)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
        L_0x018e:
            long r6 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            long r6 = r6 - r18
            boolean r2 = DEBUG     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            if (r2 != 0) goto L_0x019e
            r10 = 3000(0xbb8, double:1.482E-320)
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 <= 0) goto L_0x01d0
        L_0x019e:
            java.lang.String r5 = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]"
            r2 = 5
            java.lang.Object[] r9 = new java.lang.Object[r2]     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            r2 = 0
            r9[r2] = r21     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            r2 = 1
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            r9[r2] = r6     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            r6 = 2
            if (r4 == 0) goto L_0x01e7
            int r2 = r4.length     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
        L_0x01b5:
            r9[r6] = r2     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            r2 = 3
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            r9[r2] = r6     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            r2 = 4
            com.google.android.gms.internal.ads.zzab r6 = r21.zzj()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            int r6 = r6.zzd()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            r9[r2] = r6     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            com.google.android.gms.internal.ads.zzaf.d(r5, r9)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
        L_0x01d0:
            r2 = 200(0xc8, float:2.8E-43)
            if (r3 < r2) goto L_0x01d8
            r2 = 299(0x12b, float:4.19E-43)
            if (r3 <= r2) goto L_0x01ea
        L_0x01d8:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            r2.<init>()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            throw r2     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
        L_0x01de:
            r2 = move-exception
            r3 = r17
            goto L_0x00ec
        L_0x01e3:
            r2 = 0
            byte[] r4 = new byte[r2]     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x00e8 }
            goto L_0x018e
        L_0x01e7:
            java.lang.String r2 = "null"
            goto L_0x01b5
        L_0x01ea:
            com.google.android.gms.internal.ads.zzp r2 = new com.google.android.gms.internal.ads.zzp     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            r5 = 0
            long r6 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            long r6 = r6 - r18
            r2.<init>((int) r3, (byte[]) r4, (boolean) r5, (long) r6, (java.util.List<com.google.android.gms.internal.ads.zzl>) r8)     // Catch:{ SocketTimeoutException -> 0x0064, MalformedURLException -> 0x0097, IOException -> 0x01de }
            goto L_0x003f
        L_0x01f8:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r5)
            goto L_0x00af
        L_0x01ff:
            com.google.android.gms.internal.ads.zzq r3 = new com.google.android.gms.internal.ads.zzq
            r3.<init>(r2)
            throw r3
        L_0x0205:
            r4 = 400(0x190, float:5.6E-43)
            if (r3 < r4) goto L_0x0213
            r4 = 499(0x1f3, float:6.99E-43)
            if (r3 > r4) goto L_0x0213
            com.google.android.gms.internal.ads.zzg r3 = new com.google.android.gms.internal.ads.zzg
            r3.<init>(r2)
            throw r3
        L_0x0213:
            r4 = 500(0x1f4, float:7.0E-43)
            if (r3 < r4) goto L_0x0221
            r4 = 599(0x257, float:8.4E-43)
            if (r3 > r4) goto L_0x0221
            com.google.android.gms.internal.ads.zzac r3 = new com.google.android.gms.internal.ads.zzac
            r3.<init>(r2)
            throw r3
        L_0x0221:
            com.google.android.gms.internal.ads.zzac r3 = new com.google.android.gms.internal.ads.zzac
            r3.<init>(r2)
            throw r3
        L_0x0227:
            java.lang.String r2 = "network"
            com.google.android.gms.internal.ads.zzo r3 = new com.google.android.gms.internal.ads.zzo
            r3.<init>()
            r0 = r21
            zza(r2, r0, r3)
            goto L_0x0004
        L_0x0235:
            r2 = move-exception
            r4 = r9
            goto L_0x00ec
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaj.zzc(com.google.android.gms.internal.ads.zzr):com.google.android.gms.internal.ads.zzp");
    }
}
