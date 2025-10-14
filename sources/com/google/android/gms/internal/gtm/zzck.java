package com.google.android.gms.internal.gtm;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;
import kotlin.text.Typography;

final class zzck extends zzan {
    /* access modifiers changed from: private */
    public static final byte[] zzabr = "\n".getBytes();
    private final String zzabp = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{"GoogleAnalytics", zzao.VERSION, Build.VERSION.RELEASE, zzcz.zza(Locale.getDefault()), Build.MODEL, Build.ID});
    private final zzcv zzabq;

    zzck(zzap zzap) {
        super(zzap);
        this.zzabq = new zzcv(zzap.zzcn());
    }

    /* access modifiers changed from: protected */
    public final void zzaw() {
        zza("Network initialized. User agent", this.zzabp);
    }

    public final boolean zzfr() {
        NetworkInfo networkInfo;
        zzk.zzav();
        zzdb();
        try {
            networkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException e) {
            networkInfo = null;
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        zzq("No network connectivity");
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<java.lang.Long> zzb(java.util.List<com.google.android.gms.internal.gtm.zzcd> r10) {
        /*
            r9 = this;
            r8 = 200(0xc8, float:2.8E-43)
            r3 = 0
            r2 = 1
            com.google.android.gms.analytics.zzk.zzav()
            r9.zzdb()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r10)
            com.google.android.gms.internal.gtm.zzbq r0 = r9.zzcp()
            java.util.Set r0 = r0.zzew()
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0033
            com.google.android.gms.internal.gtm.zzcv r1 = r9.zzabq
            com.google.android.gms.internal.gtm.zzbz<java.lang.Integer> r0 = com.google.android.gms.internal.gtm.zzby.zzaab
            java.lang.Object r0 = r0.get()
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            long r4 = (long) r0
            r6 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 * r6
            boolean r0 = r1.zzj(r4)
            if (r0 != 0) goto L_0x007d
        L_0x0033:
            r1 = r3
        L_0x0034:
            r4 = r3
        L_0x0035:
            if (r1 == 0) goto L_0x0106
            boolean r0 = r10.isEmpty()
            if (r0 != 0) goto L_0x00a2
        L_0x003d:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r2)
            java.lang.String r0 = "Uploading batched hits. compression, count"
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r4)
            int r2 = r10.size()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r9.zza(r0, r1, r2)
            com.google.android.gms.internal.gtm.zzcl r2 = new com.google.android.gms.internal.gtm.zzcl
            r2.<init>(r9)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r3 = r10.iterator()
        L_0x005f:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x00a4
            java.lang.Object r0 = r3.next()
            com.google.android.gms.internal.gtm.zzcd r0 = (com.google.android.gms.internal.gtm.zzcd) r0
            boolean r5 = r2.zze(r0)
            if (r5 == 0) goto L_0x00a4
            long r6 = r0.zzfg()
            java.lang.Long r0 = java.lang.Long.valueOf(r6)
            r1.add(r0)
            goto L_0x005f
        L_0x007d:
            com.google.android.gms.internal.gtm.zzbz<java.lang.String> r0 = com.google.android.gms.internal.gtm.zzby.zzzu
            java.lang.Object r0 = r0.get()
            java.lang.String r0 = (java.lang.String) r0
            com.google.android.gms.internal.gtm.zzbg r0 = com.google.android.gms.internal.gtm.zzbg.zzz(r0)
            com.google.android.gms.internal.gtm.zzbg r1 = com.google.android.gms.internal.gtm.zzbg.NONE
            if (r0 == r1) goto L_0x00a0
            r1 = r2
        L_0x008e:
            com.google.android.gms.internal.gtm.zzbz<java.lang.String> r0 = com.google.android.gms.internal.gtm.zzby.zzzv
            java.lang.Object r0 = r0.get()
            java.lang.String r0 = (java.lang.String) r0
            com.google.android.gms.internal.gtm.zzbm r0 = com.google.android.gms.internal.gtm.zzbm.zzaa(r0)
            com.google.android.gms.internal.gtm.zzbm r4 = com.google.android.gms.internal.gtm.zzbm.GZIP
            if (r0 != r4) goto L_0x0034
            r4 = r2
            goto L_0x0035
        L_0x00a0:
            r1 = r3
            goto L_0x008e
        L_0x00a2:
            r2 = r3
            goto L_0x003d
        L_0x00a4:
            int r0 = r2.zzfu()
            if (r0 != 0) goto L_0x00ac
            r0 = r1
        L_0x00ab:
            return r0
        L_0x00ac:
            java.net.URL r0 = r9.zzfs()
            if (r0 != 0) goto L_0x00bc
            java.lang.String r0 = "Failed to build batching endpoint url"
            r9.zzu(r0)
        L_0x00b7:
            java.util.List r0 = java.util.Collections.emptyList()
            goto L_0x00ab
        L_0x00bc:
            if (r4 == 0) goto L_0x00d7
            byte[] r3 = r2.getPayload()
            int r0 = r9.zzb((java.net.URL) r0, (byte[]) r3)
        L_0x00c6:
            if (r8 != r0) goto L_0x00e0
            java.lang.String r0 = "Batched upload completed. Hits batched"
            int r2 = r2.zzfu()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r9.zza(r0, r2)
            r0 = r1
            goto L_0x00ab
        L_0x00d7:
            byte[] r3 = r2.getPayload()
            int r0 = r9.zza((java.net.URL) r0, (byte[]) r3)
            goto L_0x00c6
        L_0x00e0:
            java.lang.String r1 = "Network error uploading hits. status code"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            r9.zza(r1, r2)
            com.google.android.gms.internal.gtm.zzbq r1 = r9.zzcp()
            java.util.Set r1 = r1.zzew()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            boolean r0 = r1.contains(r0)
            if (r0 == 0) goto L_0x00b7
            java.lang.String r0 = "Server instructed the client to stop batching"
            r9.zzt(r0)
            com.google.android.gms.internal.gtm.zzcv r0 = r9.zzabq
            r0.start()
            goto L_0x00b7
        L_0x0106:
            java.util.ArrayList r4 = new java.util.ArrayList
            int r0 = r10.size()
            r4.<init>(r0)
            java.util.Iterator r5 = r10.iterator()
        L_0x0113:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x0150
            java.lang.Object r0 = r5.next()
            com.google.android.gms.internal.gtm.zzcd r0 = (com.google.android.gms.internal.gtm.zzcd) r0
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)
            boolean r1 = r0.zzfj()
            if (r1 != 0) goto L_0x0153
            r1 = r2
        L_0x0129:
            java.lang.String r6 = r9.zza((com.google.android.gms.internal.gtm.zzcd) r0, (boolean) r1)
            if (r6 != 0) goto L_0x0155
            com.google.android.gms.internal.gtm.zzci r1 = r9.zzco()
            java.lang.String r6 = "Error formatting hit for upload"
            r1.zza((com.google.android.gms.internal.gtm.zzcd) r0, (java.lang.String) r6)
            r1 = r2
        L_0x0139:
            if (r1 == 0) goto L_0x0150
            long r0 = r0.zzfg()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r4.add(r0)
            int r0 = r4.size()
            int r1 = com.google.android.gms.internal.gtm.zzbq.zzer()
            if (r0 < r1) goto L_0x0113
        L_0x0150:
            r0 = r4
            goto L_0x00ab
        L_0x0153:
            r1 = r3
            goto L_0x0129
        L_0x0155:
            int r7 = r6.length()
            com.google.android.gms.internal.gtm.zzbz<java.lang.Integer> r1 = com.google.android.gms.internal.gtm.zzby.zzzt
            java.lang.Object r1 = r1.get()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            if (r7 > r1) goto L_0x017e
            java.net.URL r1 = r9.zzb((com.google.android.gms.internal.gtm.zzcd) r0, (java.lang.String) r6)
            if (r1 != 0) goto L_0x0174
            java.lang.String r1 = "Failed to build collect GET endpoint url"
            r9.zzu(r1)
        L_0x0172:
            r1 = r3
            goto L_0x0139
        L_0x0174:
            int r1 = r9.zza((java.net.URL) r1)
            if (r1 != r8) goto L_0x017c
            r1 = r2
            goto L_0x0139
        L_0x017c:
            r1 = r3
            goto L_0x0139
        L_0x017e:
            java.lang.String r1 = r9.zza((com.google.android.gms.internal.gtm.zzcd) r0, (boolean) r3)
            if (r1 != 0) goto L_0x018f
            com.google.android.gms.internal.gtm.zzci r1 = r9.zzco()
            java.lang.String r6 = "Error formatting hit for POST upload"
            r1.zza((com.google.android.gms.internal.gtm.zzcd) r0, (java.lang.String) r6)
            r1 = r2
            goto L_0x0139
        L_0x018f:
            byte[] r6 = r1.getBytes()
            int r7 = r6.length
            com.google.android.gms.internal.gtm.zzbz<java.lang.Integer> r1 = com.google.android.gms.internal.gtm.zzby.zzzy
            java.lang.Object r1 = r1.get()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            if (r7 <= r1) goto L_0x01ad
            com.google.android.gms.internal.gtm.zzci r1 = r9.zzco()
            java.lang.String r6 = "Hit payload exceeds size limit"
            r1.zza((com.google.android.gms.internal.gtm.zzcd) r0, (java.lang.String) r6)
            r1 = r2
            goto L_0x0139
        L_0x01ad:
            java.net.URL r1 = r9.zzd(r0)
            if (r1 != 0) goto L_0x01b9
            java.lang.String r1 = "Failed to build collect POST endpoint url"
            r9.zzu(r1)
            goto L_0x0172
        L_0x01b9:
            int r1 = r9.zza((java.net.URL) r1, (byte[]) r6)
            if (r1 != r8) goto L_0x0172
            r1 = r2
            goto L_0x0139
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzck.zzb(java.util.List):java.util.List");
    }

    private final int zza(URL url) {
        Preconditions.checkNotNull(url);
        zzb("GET request", url);
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection zzb = zzb(url);
            zzb.connect();
            zza(zzb);
            int responseCode = zzb.getResponseCode();
            if (responseCode == 200) {
                zzcs().zzcl();
            }
            zzb("GET status", Integer.valueOf(responseCode));
            if (zzb == null) {
                return responseCode;
            }
            zzb.disconnect();
            return responseCode;
        } catch (IOException e) {
            zzd("Network GET connection error", e);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return 0;
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0075 A[SYNTHETIC, Splitter:B:26:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x008b A[SYNTHETIC, Splitter:B:36:0x008b] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0090  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(java.net.URL r6, byte[] r7) {
        /*
            r5 = this;
            r2 = 0
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r7)
            java.lang.String r0 = "POST bytes, url"
            int r1 = r7.length
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r5.zzb(r0, r1, r6)
            boolean r0 = zzda()
            if (r0 == 0) goto L_0x0021
            java.lang.String r0 = "Post payload\n"
            java.lang.String r1 = new java.lang.String
            r1.<init>(r7)
            r5.zza(r0, r1)
        L_0x0021:
            android.content.Context r0 = r5.getContext()     // Catch:{ IOException -> 0x006b, all -> 0x0086 }
            r0.getPackageName()     // Catch:{ IOException -> 0x006b, all -> 0x0086 }
            java.net.HttpURLConnection r3 = r5.zzb((java.net.URL) r6)     // Catch:{ IOException -> 0x006b, all -> 0x0086 }
            r0 = 1
            r3.setDoOutput(r0)     // Catch:{ IOException -> 0x00a0, all -> 0x009b }
            int r0 = r7.length     // Catch:{ IOException -> 0x00a0, all -> 0x009b }
            r3.setFixedLengthStreamingMode(r0)     // Catch:{ IOException -> 0x00a0, all -> 0x009b }
            r3.connect()     // Catch:{ IOException -> 0x00a0, all -> 0x009b }
            java.io.OutputStream r1 = r3.getOutputStream()     // Catch:{ IOException -> 0x00a0, all -> 0x009b }
            r1.write(r7)     // Catch:{ IOException -> 0x00a3 }
            r5.zza((java.net.HttpURLConnection) r3)     // Catch:{ IOException -> 0x00a3 }
            int r0 = r3.getResponseCode()     // Catch:{ IOException -> 0x00a3 }
            r2 = 200(0xc8, float:2.8E-43)
            if (r0 != r2) goto L_0x0050
            com.google.android.gms.internal.gtm.zzae r2 = r5.zzcs()     // Catch:{ IOException -> 0x00a3 }
            r2.zzcl()     // Catch:{ IOException -> 0x00a3 }
        L_0x0050:
            java.lang.String r2 = "POST status"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x00a3 }
            r5.zzb(r2, r4)     // Catch:{ IOException -> 0x00a3 }
            if (r1 == 0) goto L_0x005e
            r1.close()     // Catch:{ IOException -> 0x0064 }
        L_0x005e:
            if (r3 == 0) goto L_0x0063
            r3.disconnect()
        L_0x0063:
            return r0
        L_0x0064:
            r1 = move-exception
            java.lang.String r2 = "Error closing http post connection output stream"
            r5.zze(r2, r1)
            goto L_0x005e
        L_0x006b:
            r0 = move-exception
            r1 = r2
            r3 = r2
        L_0x006e:
            java.lang.String r2 = "Network POST connection error"
            r5.zzd(r2, r0)     // Catch:{ all -> 0x009e }
            if (r1 == 0) goto L_0x0078
            r1.close()     // Catch:{ IOException -> 0x007f }
        L_0x0078:
            if (r3 == 0) goto L_0x007d
            r3.disconnect()
        L_0x007d:
            r0 = 0
            goto L_0x0063
        L_0x007f:
            r0 = move-exception
            java.lang.String r1 = "Error closing http post connection output stream"
            r5.zze(r1, r0)
            goto L_0x0078
        L_0x0086:
            r0 = move-exception
            r1 = r2
            r3 = r2
        L_0x0089:
            if (r1 == 0) goto L_0x008e
            r1.close()     // Catch:{ IOException -> 0x0094 }
        L_0x008e:
            if (r3 == 0) goto L_0x0093
            r3.disconnect()
        L_0x0093:
            throw r0
        L_0x0094:
            r1 = move-exception
            java.lang.String r2 = "Error closing http post connection output stream"
            r5.zze(r2, r1)
            goto L_0x008e
        L_0x009b:
            r0 = move-exception
            r1 = r2
            goto L_0x0089
        L_0x009e:
            r0 = move-exception
            goto L_0x0089
        L_0x00a0:
            r0 = move-exception
            r1 = r2
            goto L_0x006e
        L_0x00a3:
            r0 = move-exception
            goto L_0x006e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzck.zza(java.net.URL, byte[]):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d5 A[SYNTHETIC, Splitter:B:42:0x00d5] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00da  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzb(java.net.URL r11, byte[] r12) {
        /*
            r10 = this;
            r2 = 0
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r11)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r12)
            android.content.Context r0 = r10.getContext()     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            r0.getPackageName()     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            r0.<init>()     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            java.util.zip.GZIPOutputStream r1 = new java.util.zip.GZIPOutputStream     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            r1.<init>(r0)     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            r1.write(r12)     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            r1.close()     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            r0.close()     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            byte[] r4 = r0.toByteArray()     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            java.lang.String r0 = "POST compressed size, ratio %, url"
            int r1 = r4.length     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            r6 = 100
            int r3 = r4.length     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            long r8 = (long) r3     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            long r6 = r6 * r8
            int r3 = r12.length     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            long r8 = (long) r3     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            long r6 = r6 / r8
            java.lang.Long r3 = java.lang.Long.valueOf(r6)     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            r10.zza(r0, r1, r3, r11)     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            int r0 = r4.length     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            int r1 = r12.length     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            if (r0 <= r1) goto L_0x004e
            java.lang.String r0 = "Compressed payload is larger then uncompressed. compressed, uncompressed"
            int r1 = r4.length     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            int r3 = r12.length     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            r10.zzc(r0, r1, r3)     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
        L_0x004e:
            boolean r0 = zzda()     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            if (r0 == 0) goto L_0x006e
            java.lang.String r1 = "Post payload"
            java.lang.String r3 = "\n"
            java.lang.String r0 = new java.lang.String     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            r0.<init>(r12)     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            int r5 = r0.length()     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            if (r5 == 0) goto L_0x00af
            java.lang.String r0 = r3.concat(r0)     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
        L_0x006b:
            r10.zza(r1, r0)     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
        L_0x006e:
            java.net.HttpURLConnection r3 = r10.zzb((java.net.URL) r11)     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            r0 = 1
            r3.setDoOutput(r0)     // Catch:{ IOException -> 0x00ea, all -> 0x00e5 }
            java.lang.String r0 = "Content-Encoding"
            java.lang.String r1 = "gzip"
            r3.addRequestProperty(r0, r1)     // Catch:{ IOException -> 0x00ea, all -> 0x00e5 }
            int r0 = r4.length     // Catch:{ IOException -> 0x00ea, all -> 0x00e5 }
            r3.setFixedLengthStreamingMode(r0)     // Catch:{ IOException -> 0x00ea, all -> 0x00e5 }
            r3.connect()     // Catch:{ IOException -> 0x00ea, all -> 0x00e5 }
            java.io.OutputStream r1 = r3.getOutputStream()     // Catch:{ IOException -> 0x00ea, all -> 0x00e5 }
            r1.write(r4)     // Catch:{ IOException -> 0x00ed }
            r1.close()     // Catch:{ IOException -> 0x00ed }
            r10.zza((java.net.HttpURLConnection) r3)     // Catch:{ IOException -> 0x00ea, all -> 0x00e5 }
            int r0 = r3.getResponseCode()     // Catch:{ IOException -> 0x00ea, all -> 0x00e5 }
            r1 = 200(0xc8, float:2.8E-43)
            if (r0 != r1) goto L_0x00a0
            com.google.android.gms.internal.gtm.zzae r1 = r10.zzcs()     // Catch:{ IOException -> 0x00ea, all -> 0x00e5 }
            r1.zzcl()     // Catch:{ IOException -> 0x00ea, all -> 0x00e5 }
        L_0x00a0:
            java.lang.String r1 = "POST status"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x00ea, all -> 0x00e5 }
            r10.zzb(r1, r4)     // Catch:{ IOException -> 0x00ea, all -> 0x00e5 }
            if (r3 == 0) goto L_0x00ae
            r3.disconnect()
        L_0x00ae:
            return r0
        L_0x00af:
            java.lang.String r0 = new java.lang.String     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            r0.<init>(r3)     // Catch:{ IOException -> 0x00b5, all -> 0x00d0 }
            goto L_0x006b
        L_0x00b5:
            r0 = move-exception
            r1 = r2
            r3 = r2
        L_0x00b8:
            java.lang.String r2 = "Network compressed POST connection error"
            r10.zzd(r2, r0)     // Catch:{ all -> 0x00e8 }
            if (r1 == 0) goto L_0x00c2
            r1.close()     // Catch:{ IOException -> 0x00c9 }
        L_0x00c2:
            if (r3 == 0) goto L_0x00c7
            r3.disconnect()
        L_0x00c7:
            r0 = 0
            goto L_0x00ae
        L_0x00c9:
            r0 = move-exception
            java.lang.String r1 = "Error closing http compressed post connection output stream"
            r10.zze(r1, r0)
            goto L_0x00c2
        L_0x00d0:
            r0 = move-exception
            r1 = r2
            r3 = r2
        L_0x00d3:
            if (r1 == 0) goto L_0x00d8
            r1.close()     // Catch:{ IOException -> 0x00de }
        L_0x00d8:
            if (r3 == 0) goto L_0x00dd
            r3.disconnect()
        L_0x00dd:
            throw r0
        L_0x00de:
            r1 = move-exception
            java.lang.String r2 = "Error closing http compressed post connection output stream"
            r10.zze(r2, r1)
            goto L_0x00d8
        L_0x00e5:
            r0 = move-exception
            r1 = r2
            goto L_0x00d3
        L_0x00e8:
            r0 = move-exception
            goto L_0x00d3
        L_0x00ea:
            r0 = move-exception
            r1 = r2
            goto L_0x00b8
        L_0x00ed:
            r0 = move-exception
            goto L_0x00b8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzck.zzb(java.net.URL, byte[]):int");
    }

    private final void zza(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = httpURLConnection.getInputStream();
            do {
            } while (inputStream.read(new byte[1024]) > 0);
            if (inputStream != null) {
                try {
                } catch (IOException e) {
                    zze("Error closing http connection input stream", e);
                }
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    zze("Error closing http connection input stream", e2);
                }
            }
        }
    }

    @VisibleForTesting
    private final HttpURLConnection zzb(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if (!(openConnection instanceof HttpURLConnection)) {
            throw new IOException("Failed to obtain http connection");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setConnectTimeout(zzby.zzaad.get().intValue());
        httpURLConnection.setReadTimeout(zzby.zzaae.get().intValue());
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestProperty("User-Agent", this.zzabp);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    private final URL zzd(zzcd zzcd) {
        String concat;
        if (zzcd.zzfj()) {
            String valueOf = String.valueOf(zzbq.zzet());
            String valueOf2 = String.valueOf(zzbq.zzev());
            concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        } else {
            String valueOf3 = String.valueOf(zzbq.zzeu());
            String valueOf4 = String.valueOf(zzbq.zzev());
            concat = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
        }
        try {
            return new URL(concat);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private final URL zzb(zzcd zzcd, String str) {
        String sb;
        if (zzcd.zzfj()) {
            String zzet = zzbq.zzet();
            String zzev = zzbq.zzev();
            sb = new StringBuilder(String.valueOf(zzet).length() + 1 + String.valueOf(zzev).length() + String.valueOf(str).length()).append(zzet).append(zzev).append("?").append(str).toString();
        } else {
            String zzeu = zzbq.zzeu();
            String zzev2 = zzbq.zzev();
            sb = new StringBuilder(String.valueOf(zzeu).length() + 1 + String.valueOf(zzev2).length() + String.valueOf(str).length()).append(zzeu).append(zzev2).append("?").append(str).toString();
        }
        try {
            return new URL(sb);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private final URL zzfs() {
        String valueOf = String.valueOf(zzbq.zzet());
        String valueOf2 = String.valueOf(zzby.zzzs.get());
        try {
            return new URL(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final String zza(zzcd zzcd, boolean z) {
        String valueOf;
        Preconditions.checkNotNull(zzcd);
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry next : zzcd.zzdm().entrySet()) {
                String str = (String) next.getKey();
                if (!"ht".equals(str) && !"qt".equals(str) && !"AppUID".equals(str) && !"z".equals(str) && !"_gmsv".equals(str)) {
                    zza(sb, str, (String) next.getValue());
                }
            }
            zza(sb, "ht", String.valueOf(zzcd.zzfh()));
            zza(sb, "qt", String.valueOf(zzcn().currentTimeMillis() - zzcd.zzfh()));
            if (z) {
                long zzfk = zzcd.zzfk();
                if (zzfk != 0) {
                    valueOf = String.valueOf(zzfk);
                } else {
                    valueOf = String.valueOf(zzcd.zzfg());
                }
                zza(sb, "z", valueOf);
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            zze("Failed to encode name or value", e);
            return null;
        }
    }

    private static void zza(StringBuilder sb, String str, String str2) throws UnsupportedEncodingException {
        if (sb.length() != 0) {
            sb.append(Typography.amp);
        }
        sb.append(URLEncoder.encode(str, WebRequest.CHARSET_UTF_8));
        sb.append('=');
        sb.append(URLEncoder.encode(str2, WebRequest.CHARSET_UTF_8));
    }
}
