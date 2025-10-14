package com.google.android.gms.internal.ads;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzadh
public final class zzaqn extends zzaqh {
    private static final Set<String> zzdbg = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat zzdbh = new DecimalFormat("#,###");
    private File zzdbi;
    private boolean zzdbj;

    public zzaqn(zzapw zzapw) {
        super(zzapw);
        File cacheDir = this.mContext.getCacheDir();
        if (cacheDir == null) {
            zzakb.zzdk("Context.getCacheDir() returned null");
            return;
        }
        this.zzdbi = new File(cacheDir, "admobVideoStreams");
        if (!this.zzdbi.isDirectory() && !this.zzdbi.mkdirs()) {
            String valueOf = String.valueOf(this.zzdbi.getAbsolutePath());
            zzakb.zzdk(valueOf.length() != 0 ? "Could not create preload cache directory at ".concat(valueOf) : new String("Could not create preload cache directory at "));
            this.zzdbi = null;
        } else if (!this.zzdbi.setReadable(true, false) || !this.zzdbi.setExecutable(true, false)) {
            String valueOf2 = String.valueOf(this.zzdbi.getAbsolutePath());
            zzakb.zzdk(valueOf2.length() != 0 ? "Could not set cache file permissions at ".concat(valueOf2) : new String("Could not set cache file permissions at "));
            this.zzdbi = null;
        }
    }

    private final File zzc(File file) {
        return new File(this.zzdbi, String.valueOf(file.getName()).concat(".done"));
    }

    public final void abort() {
        this.zzdbj = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0242, code lost:
        r6 = new java.net.URL(r3, r8);
        r3 = r6.getProtocol();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x024b, code lost:
        if (r3 != null) goto L_0x0255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0254, code lost:
        throw new java.io.IOException("Protocol is null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x025b, code lost:
        if (r3.equals("http") != false) goto L_0x0281;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0263, code lost:
        if (r3.equals("https") != false) goto L_0x0281;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0265, code lost:
        r2 = java.lang.String.valueOf(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0271, code lost:
        if (r2.length() == 0) goto L_0x027b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0273, code lost:
        r2 = "Unsupported scheme: ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x027a, code lost:
        throw new java.io.IOException(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x027b, code lost:
        r2 = new java.lang.String("Unsupported scheme: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0281, code lost:
        r3 = java.lang.String.valueOf(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x028b, code lost:
        if (r3.length() == 0) goto L_0x029b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x028d, code lost:
        r3 = "Redirecting to ".concat(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0291, code lost:
        com.google.android.gms.internal.ads.zzakb.zzck(r3);
        r2.disconnect();
        r2 = r4;
        r3 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x029b, code lost:
        r3 = new java.lang.String("Redirecting to ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x02a8, code lost:
        throw new java.io.IOException("Too many redirects (20)");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02ab, code lost:
        if ((r2 instanceof java.net.HttpURLConnection) == false) goto L_0x030b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x02ad, code lost:
        r6 = r2.getResponseCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02b7, code lost:
        if (r6 < 400) goto L_0x030b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x02b9, code lost:
        r4 = "badUrl";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:?, code lost:
        r3 = java.lang.String.valueOf(java.lang.Integer.toString(r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02c9, code lost:
        if (r3.length() == 0) goto L_0x0301;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02cb, code lost:
        r3 = "HTTP request failed. Code: ".concat(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x02fd, code lost:
        throw new java.io.IOException(new java.lang.StringBuilder(java.lang.String.valueOf(r27).length() + 32).append("HTTP status code ").append(r6).append(" at ").append(r27).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x02fe, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:?, code lost:
        r3 = new java.lang.String("HTTP request failed. Code: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0307, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0308, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:?, code lost:
        r7 = r2.getContentLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x030f, code lost:
        if (r7 >= 0) goto L_0x0340;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0311, code lost:
        r2 = java.lang.String.valueOf(r27);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x031b, code lost:
        if (r2.length() == 0) goto L_0x033a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x031d, code lost:
        r2 = "Stream cache aborted, missing content-length header at ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0321, code lost:
        com.google.android.gms.internal.ads.zzakb.zzdk(r2);
        zza(r27, r13.getAbsolutePath(), "contentLengthMissing", (java.lang.String) null);
        zzdbg.remove(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x033a, code lost:
        r2 = new java.lang.String("Stream cache aborted, missing content-length header at ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0340, code lost:
        r4 = zzdbh.format((long) r7);
        r15 = ((java.lang.Integer) com.google.android.gms.internal.ads.zzkb.zzik().zzd(com.google.android.gms.internal.ads.zznk.zzauy)).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x035b, code lost:
        if (r7 <= r15) goto L_0x03bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x035d, code lost:
        com.google.android.gms.internal.ads.zzakb.zzdk(new java.lang.StringBuilder((java.lang.String.valueOf(r4).length() + 33) + java.lang.String.valueOf(r27).length()).append("Content length ").append(r4).append(" exceeds limit at ").append(r27).toString());
        r2 = java.lang.String.valueOf(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x039c, code lost:
        if (r2.length() == 0) goto L_0x03b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x039e, code lost:
        r2 = "File too big for full file cache. Size: ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x03a2, code lost:
        zza(r27, r13.getAbsolutePath(), "sizeExceeded", r2);
        zzdbg.remove(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x03b7, code lost:
        r2 = new java.lang.String("File too big for full file cache. Size: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x03bd, code lost:
        com.google.android.gms.internal.ads.zzakb.zzck(new java.lang.StringBuilder((java.lang.String.valueOf(r4).length() + 20) + java.lang.String.valueOf(r27).length()).append("Caching ").append(r4).append(" bytes from ").append(r27).toString());
        r16 = java.nio.channels.Channels.newChannel(r2.getInputStream());
        r12 = new java.io.FileOutputStream(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:?, code lost:
        r17 = r12.getChannel();
        r18 = java.nio.ByteBuffer.allocate(1048576);
        r19 = com.google.android.gms.ads.internal.zzbv.zzer();
        r6 = 0;
        r20 = r19.currentTimeMillis();
        r0 = new com.google.android.gms.internal.ads.zzamj(((java.lang.Long) com.google.android.gms.internal.ads.zzkb.zzik().zzd(com.google.android.gms.internal.ads.zznk.zzavb)).longValue());
        r24 = ((java.lang.Long) com.google.android.gms.internal.ads.zzkb.zzik().zzd(com.google.android.gms.internal.ads.zznk.zzava)).longValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0439, code lost:
        r2 = r16.read(r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0441, code lost:
        if (r2 < 0) goto L_0x04fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0443, code lost:
        r6 = r6 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0444, code lost:
        if (r6 <= r15) goto L_0x0473;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:?, code lost:
        r3 = java.lang.String.valueOf(java.lang.Integer.toString(r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x0456, code lost:
        if (r3.length() == 0) goto L_0x0468;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0458, code lost:
        r3 = "File too big for full file cache. Size: ".concat(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x0463, code lost:
        throw new java.io.IOException("stream cache file size limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0464, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0465, code lost:
        r5 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:?, code lost:
        r3 = new java.lang.String("File too big for full file cache. Size: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x046e, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x046f, code lost:
        r3 = null;
        r5 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:?, code lost:
        r18.flip();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x047a, code lost:
        if (r17.write(r18) > 0) goto L_0x0476;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x047c, code lost:
        r18.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x048b, code lost:
        if ((r19.currentTimeMillis() - r20) <= (1000 * r24)) goto L_0x04c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:?, code lost:
        r2 = java.lang.Long.toString(r24);
        r3 = new java.lang.StringBuilder(java.lang.String.valueOf(r2).length() + 29).append("Timeout exceeded. Limit: ").append(r2).append(" sec").toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x04bd, code lost:
        throw new java.io.IOException("stream cache time limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x04be, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x04bf, code lost:
        r5 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x04c6, code lost:
        if (r26.zzdbj == false) goto L_0x04d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x04d1, code lost:
        throw new java.io.IOException("abort requested");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x04d2, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x04d3, code lost:
        r3 = null;
        r5 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x04db, code lost:
        if (r0.tryAcquire() == false) goto L_0x0439;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x04dd, code lost:
        com.google.android.gms.internal.ads.zzamu.zzsy.post(new com.google.android.gms.internal.ads.zzaqi(r26, r27, r13.getAbsolutePath(), r6, r7, false));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x04f4, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x04f5, code lost:
        r3 = null;
        r4 = android.support.v7.media.MediaRouteProviderProtocol.SERVICE_DATA_ERROR;
        r5 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x04fa, code lost:
        r12.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x0502, code lost:
        if (com.google.android.gms.internal.ads.zzakb.isLoggable(3) == false) goto L_0x0540;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x0504, code lost:
        r2 = zzdbh.format((long) r6);
        com.google.android.gms.internal.ads.zzakb.zzck(new java.lang.StringBuilder((java.lang.String.valueOf(r2).length() + 22) + java.lang.String.valueOf(r27).length()).append("Preloaded ").append(r2).append(" bytes from ").append(r27).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x0540, code lost:
        r13.setReadable(true, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x0549, code lost:
        if (r14.isFile() == false) goto L_0x0565;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x054b, code lost:
        r14.setLastModified(java.lang.System.currentTimeMillis());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:?, code lost:
        r14.createNewFile();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x056b, code lost:
        com.google.android.gms.internal.ads.zzakb.zzc(new java.lang.StringBuilder(java.lang.String.valueOf(r27).length() + 25).append("Preload failed for URL \"").append(r27).append("\"").toString(), r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x0595, code lost:
        r2 = new java.lang.String("Could not delete partial cache file at ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x05a2, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x05a3, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x05a9, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x05aa, code lost:
        r3 = null;
        r4 = android.support.v7.media.MediaRouteProviderProtocol.SERVICE_DATA_ERROR;
        r5 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0160, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        com.google.android.gms.ads.internal.zzbv.zzew();
        r7 = ((java.lang.Integer) com.google.android.gms.internal.ads.zzkb.zzik().zzd(com.google.android.gms.internal.ads.zznk.zzavc)).intValue();
        r3 = new java.net.URL(r27);
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x017f, code lost:
        r4 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0183, code lost:
        if (r4 > 20) goto L_0x02a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0185, code lost:
        r2 = r3.openConnection();
        r2.setConnectTimeout(r7);
        r2.setReadTimeout(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0191, code lost:
        if ((r2 instanceof java.net.HttpURLConnection) != false) goto L_0x0212;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x019a, code lost:
        throw new java.io.IOException("Invalid protocol.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x019b, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x019c, code lost:
        r3 = null;
        r4 = android.support.v7.media.MediaRouteProviderProtocol.SERVICE_DATA_ERROR;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01a0, code lost:
        if ((r2 instanceof java.lang.RuntimeException) != false) goto L_0x01a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01a2, code lost:
        com.google.android.gms.ads.internal.zzbv.zzeo().zza(r2, "VideoStreamFullFileCache.preload");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01b2, code lost:
        if (r26.zzdbj != false) goto L_0x01b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01b4, code lost:
        com.google.android.gms.internal.ads.zzakb.zzdj(new java.lang.StringBuilder(java.lang.String.valueOf(r27).length() + 26).append("Preload aborted for URL \"").append(r27).append("\"").toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01e8, code lost:
        r2 = java.lang.String.valueOf(r13.getAbsolutePath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01f6, code lost:
        if (r2.length() != 0) goto L_0x01f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01f8, code lost:
        r2 = "Could not delete partial cache file at ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01fc, code lost:
        com.google.android.gms.internal.ads.zzakb.zzdk(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01ff, code lost:
        zza(r27, r13.getAbsolutePath(), r4, r3);
        zzdbg.remove(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:?, code lost:
        r2 = (java.net.HttpURLConnection) r2;
        r6 = new com.google.android.gms.internal.ads.zzamy();
        r6.zza(r2, (byte[]) null);
        r2.setInstanceFollowRedirects(false);
        r8 = r2.getResponseCode();
        r6.zza(r2, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x022b, code lost:
        if ((r8 / 100) != 3) goto L_0x02a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x022d, code lost:
        r8 = r2.getHeaderField("Location");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0233, code lost:
        if (r8 != null) goto L_0x0242;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x023c, code lost:
        throw new java.io.IOException("Missing Location header in redirect");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x023d, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x023e, code lost:
        r3 = null;
        r4 = android.support.v7.media.MediaRouteProviderProtocol.SERVICE_DATA_ERROR;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x056b  */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x0595  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x05a9 A[ExcHandler: RuntimeException (e java.lang.RuntimeException), Splitter:B:155:0x03ff] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01a2  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01b4  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01f8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzdp(java.lang.String r27) {
        /*
            r26 = this;
            r0 = r26
            java.io.File r2 = r0.zzdbi
            if (r2 != 0) goto L_0x0013
            r2 = 0
            java.lang.String r3 = "noCacheDir"
            r4 = 0
            r0 = r26
            r1 = r27
            r0.zza(r1, r2, r3, r4)
            r2 = 0
        L_0x0012:
            return r2
        L_0x0013:
            r0 = r26
            java.io.File r2 = r0.zzdbi
            if (r2 != 0) goto L_0x0048
            r2 = 0
            r3 = r2
        L_0x001b:
            com.google.android.gms.internal.ads.zzna<java.lang.Integer> r2 = com.google.android.gms.internal.ads.zznk.zzaux
            com.google.android.gms.internal.ads.zzni r4 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r2 = r4.zzd(r2)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            if (r3 <= r2) goto L_0x00b1
            r0 = r26
            java.io.File r2 = r0.zzdbi
            if (r2 != 0) goto L_0x006a
            r2 = 0
        L_0x0034:
            if (r2 != 0) goto L_0x0013
            java.lang.String r2 = "Unable to expire stream cache"
            com.google.android.gms.internal.ads.zzakb.zzdk(r2)
            r2 = 0
            java.lang.String r3 = "expireFailed"
            r4 = 0
            r0 = r26
            r1 = r27
            r0.zza(r1, r2, r3, r4)
            r2 = 0
            goto L_0x0012
        L_0x0048:
            r2 = 0
            r0 = r26
            java.io.File r3 = r0.zzdbi
            java.io.File[] r4 = r3.listFiles()
            int r5 = r4.length
            r3 = 0
        L_0x0053:
            if (r3 >= r5) goto L_0x0068
            r6 = r4[r3]
            java.lang.String r6 = r6.getName()
            java.lang.String r7 = ".done"
            boolean r6 = r6.endsWith(r7)
            if (r6 != 0) goto L_0x0065
            int r2 = r2 + 1
        L_0x0065:
            int r3 = r3 + 1
            goto L_0x0053
        L_0x0068:
            r3 = r2
            goto L_0x001b
        L_0x006a:
            r7 = 0
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r0 = r26
            java.io.File r2 = r0.zzdbi
            java.io.File[] r9 = r2.listFiles()
            int r10 = r9.length
            r2 = 0
            r8 = r2
        L_0x007b:
            if (r8 >= r10) goto L_0x0098
            r6 = r9[r8]
            java.lang.String r2 = r6.getName()
            java.lang.String r3 = ".done"
            boolean r2 = r2.endsWith(r3)
            if (r2 != 0) goto L_0x05af
            long r2 = r6.lastModified()
            int r11 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r11 >= 0) goto L_0x05af
        L_0x0093:
            int r8 = r8 + 1
            r4 = r2
            r7 = r6
            goto L_0x007b
        L_0x0098:
            r2 = 0
            if (r7 == 0) goto L_0x0034
            boolean r2 = r7.delete()
            r0 = r26
            java.io.File r3 = r0.zzc(r7)
            boolean r4 = r3.isFile()
            if (r4 == 0) goto L_0x0034
            boolean r3 = r3.delete()
            r2 = r2 & r3
            goto L_0x0034
        L_0x00b1:
            com.google.android.gms.internal.ads.zzkb.zzif()
            java.lang.String r2 = com.google.android.gms.internal.ads.zzamu.zzde(r27)
            java.io.File r13 = new java.io.File
            r0 = r26
            java.io.File r3 = r0.zzdbi
            r13.<init>(r3, r2)
            r0 = r26
            java.io.File r14 = r0.zzc(r13)
            boolean r2 = r13.isFile()
            if (r2 == 0) goto L_0x00ff
            boolean r2 = r14.isFile()
            if (r2 == 0) goto L_0x00ff
            long r2 = r13.length()
            int r3 = (int) r2
            java.lang.String r4 = "Stream cache hit at "
            java.lang.String r2 = java.lang.String.valueOf(r27)
            int r5 = r2.length()
            if (r5 == 0) goto L_0x00f9
            java.lang.String r2 = r4.concat(r2)
        L_0x00e8:
            com.google.android.gms.internal.ads.zzakb.zzck(r2)
            java.lang.String r2 = r13.getAbsolutePath()
            r0 = r26
            r1 = r27
            r0.zza((java.lang.String) r1, (java.lang.String) r2, (int) r3)
            r2 = 1
            goto L_0x0012
        L_0x00f9:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r4)
            goto L_0x00e8
        L_0x00ff:
            r0 = r26
            java.io.File r2 = r0.zzdbi
            java.lang.String r2 = r2.getAbsolutePath()
            java.lang.String r3 = java.lang.String.valueOf(r2)
            java.lang.String r2 = java.lang.String.valueOf(r27)
            int r4 = r2.length()
            if (r4 == 0) goto L_0x014d
            java.lang.String r2 = r3.concat(r2)
            r9 = r2
        L_0x011a:
            java.util.Set<java.lang.String> r3 = zzdbg
            monitor-enter(r3)
            java.util.Set<java.lang.String> r2 = zzdbg     // Catch:{ all -> 0x014a }
            boolean r2 = r2.contains(r9)     // Catch:{ all -> 0x014a }
            if (r2 == 0) goto L_0x015a
            java.lang.String r4 = "Stream cache already in progress at "
            java.lang.String r2 = java.lang.String.valueOf(r27)     // Catch:{ all -> 0x014a }
            int r5 = r2.length()     // Catch:{ all -> 0x014a }
            if (r5 == 0) goto L_0x0154
            java.lang.String r2 = r4.concat(r2)     // Catch:{ all -> 0x014a }
        L_0x0135:
            com.google.android.gms.internal.ads.zzakb.zzdk(r2)     // Catch:{ all -> 0x014a }
            java.lang.String r2 = r13.getAbsolutePath()     // Catch:{ all -> 0x014a }
            java.lang.String r4 = "inProgress"
            r5 = 0
            r0 = r26
            r1 = r27
            r0.zza(r1, r2, r4, r5)     // Catch:{ all -> 0x014a }
            r2 = 0
            monitor-exit(r3)     // Catch:{ all -> 0x014a }
            goto L_0x0012
        L_0x014a:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x014a }
            throw r2
        L_0x014d:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r3)
            r9 = r2
            goto L_0x011a
        L_0x0154:
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x014a }
            r2.<init>(r4)     // Catch:{ all -> 0x014a }
            goto L_0x0135
        L_0x015a:
            java.util.Set<java.lang.String> r2 = zzdbg     // Catch:{ all -> 0x014a }
            r2.add(r9)     // Catch:{ all -> 0x014a }
            monitor-exit(r3)     // Catch:{ all -> 0x014a }
            r5 = 0
            java.lang.String r11 = "error"
            r10 = 0
            com.google.android.gms.ads.internal.zzbv.zzew()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            com.google.android.gms.internal.ads.zzna<java.lang.Integer> r2 = com.google.android.gms.internal.ads.zznk.zzavc     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            com.google.android.gms.internal.ads.zzni r3 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.Object r2 = r3.zzd(r2)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            int r7 = r2.intValue()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.net.URL r3 = new java.net.URL     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r0 = r27
            r3.<init>(r0)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r2 = 0
        L_0x017f:
            int r4 = r2 + 1
            r2 = 20
            if (r4 > r2) goto L_0x02a1
            java.net.URLConnection r2 = r3.openConnection()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r2.setConnectTimeout(r7)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r2.setReadTimeout(r7)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            boolean r6 = r2 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            if (r6 != 0) goto L_0x0212
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.String r3 = "Invalid protocol."
            r2.<init>(r3)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            throw r2     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
        L_0x019b:
            r2 = move-exception
            r3 = r10
            r4 = r11
        L_0x019e:
            boolean r6 = r2 instanceof java.lang.RuntimeException
            if (r6 == 0) goto L_0x01ab
            com.google.android.gms.internal.ads.zzajm r6 = com.google.android.gms.ads.internal.zzbv.zzeo()
            java.lang.String r7 = "VideoStreamFullFileCache.preload"
            r6.zza(r2, r7)
        L_0x01ab:
            r5.close()     // Catch:{ IOException -> 0x059c, NullPointerException -> 0x059f }
        L_0x01ae:
            r0 = r26
            boolean r5 = r0.zzdbj
            if (r5 == 0) goto L_0x056b
            java.lang.String r2 = java.lang.String.valueOf(r27)
            int r2 = r2.length()
            int r2 = r2 + 26
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r2)
            java.lang.String r2 = "Preload aborted for URL \""
            java.lang.StringBuilder r2 = r5.append(r2)
            r0 = r27
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r5 = "\""
            java.lang.StringBuilder r2 = r2.append(r5)
            java.lang.String r2 = r2.toString()
            com.google.android.gms.internal.ads.zzakb.zzdj(r2)
        L_0x01dc:
            boolean r2 = r13.exists()
            if (r2 == 0) goto L_0x01ff
            boolean r2 = r13.delete()
            if (r2 != 0) goto L_0x01ff
            java.lang.String r5 = "Could not delete partial cache file at "
            java.lang.String r2 = r13.getAbsolutePath()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r6 = r2.length()
            if (r6 == 0) goto L_0x0595
            java.lang.String r2 = r5.concat(r2)
        L_0x01fc:
            com.google.android.gms.internal.ads.zzakb.zzdk(r2)
        L_0x01ff:
            java.lang.String r2 = r13.getAbsolutePath()
            r0 = r26
            r1 = r27
            r0.zza(r1, r2, r4, r3)
            java.util.Set<java.lang.String> r2 = zzdbg
            r2.remove(r9)
            r2 = 0
            goto L_0x0012
        L_0x0212:
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            com.google.android.gms.internal.ads.zzamy r6 = new com.google.android.gms.internal.ads.zzamy     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r6.<init>()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r8 = 0
            r6.zza((java.net.HttpURLConnection) r2, (byte[]) r8)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r8 = 0
            r2.setInstanceFollowRedirects(r8)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            int r8 = r2.getResponseCode()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r6.zza((java.net.HttpURLConnection) r2, (int) r8)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            int r6 = r8 / 100
            r8 = 3
            if (r6 != r8) goto L_0x02a9
            java.lang.String r6 = "Location"
            java.lang.String r8 = r2.getHeaderField(r6)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            if (r8 != 0) goto L_0x0242
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.String r3 = "Missing Location header in redirect"
            r2.<init>(r3)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            throw r2     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
        L_0x023d:
            r2 = move-exception
            r3 = r10
            r4 = r11
            goto L_0x019e
        L_0x0242:
            java.net.URL r6 = new java.net.URL     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r6.<init>(r3, r8)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.String r3 = r6.getProtocol()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            if (r3 != 0) goto L_0x0255
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.String r3 = "Protocol is null"
            r2.<init>(r3)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            throw r2     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
        L_0x0255:
            java.lang.String r12 = "http"
            boolean r12 = r3.equals(r12)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            if (r12 != 0) goto L_0x0281
            java.lang.String r12 = "https"
            boolean r12 = r3.equals(r12)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            if (r12 != 0) goto L_0x0281
            java.io.IOException r4 = new java.io.IOException     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.String r6 = "Unsupported scheme: "
            java.lang.String r2 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            int r3 = r2.length()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            if (r3 == 0) goto L_0x027b
            java.lang.String r2 = r6.concat(r2)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
        L_0x0277:
            r4.<init>(r2)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            throw r4     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
        L_0x027b:
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r2.<init>(r6)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            goto L_0x0277
        L_0x0281:
            java.lang.String r12 = "Redirecting to "
            java.lang.String r3 = java.lang.String.valueOf(r8)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            int r8 = r3.length()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            if (r8 == 0) goto L_0x029b
            java.lang.String r3 = r12.concat(r3)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
        L_0x0291:
            com.google.android.gms.internal.ads.zzakb.zzck(r3)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r2.disconnect()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r2 = r4
            r3 = r6
            goto L_0x017f
        L_0x029b:
            java.lang.String r3 = new java.lang.String     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r3.<init>(r12)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            goto L_0x0291
        L_0x02a1:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.String r3 = "Too many redirects (20)"
            r2.<init>(r3)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            throw r2     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
        L_0x02a9:
            boolean r3 = r2 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            if (r3 == 0) goto L_0x030b
            r0 = r2
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r3 = r0
            int r6 = r3.getResponseCode()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r3 = 400(0x190, float:5.6E-43)
            if (r6 < r3) goto L_0x030b
            java.lang.String r4 = "badUrl"
            java.lang.String r2 = "HTTP request failed. Code: "
            java.lang.String r3 = java.lang.Integer.toString(r6)     // Catch:{ IOException -> 0x0307, RuntimeException -> 0x05a2 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x0307, RuntimeException -> 0x05a2 }
            int r7 = r3.length()     // Catch:{ IOException -> 0x0307, RuntimeException -> 0x05a2 }
            if (r7 == 0) goto L_0x0301
            java.lang.String r3 = r2.concat(r3)     // Catch:{ IOException -> 0x0307, RuntimeException -> 0x05a2 }
        L_0x02cf:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException -> 0x02fe, RuntimeException -> 0x05a6 }
            java.lang.String r7 = java.lang.String.valueOf(r27)     // Catch:{ IOException -> 0x02fe, RuntimeException -> 0x05a6 }
            int r7 = r7.length()     // Catch:{ IOException -> 0x02fe, RuntimeException -> 0x05a6 }
            int r7 = r7 + 32
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x02fe, RuntimeException -> 0x05a6 }
            r8.<init>(r7)     // Catch:{ IOException -> 0x02fe, RuntimeException -> 0x05a6 }
            java.lang.String r7 = "HTTP status code "
            java.lang.StringBuilder r7 = r8.append(r7)     // Catch:{ IOException -> 0x02fe, RuntimeException -> 0x05a6 }
            java.lang.StringBuilder r6 = r7.append(r6)     // Catch:{ IOException -> 0x02fe, RuntimeException -> 0x05a6 }
            java.lang.String r7 = " at "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ IOException -> 0x02fe, RuntimeException -> 0x05a6 }
            r0 = r27
            java.lang.StringBuilder r6 = r6.append(r0)     // Catch:{ IOException -> 0x02fe, RuntimeException -> 0x05a6 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x02fe, RuntimeException -> 0x05a6 }
            r2.<init>(r6)     // Catch:{ IOException -> 0x02fe, RuntimeException -> 0x05a6 }
            throw r2     // Catch:{ IOException -> 0x02fe, RuntimeException -> 0x05a6 }
        L_0x02fe:
            r2 = move-exception
            goto L_0x019e
        L_0x0301:
            java.lang.String r3 = new java.lang.String     // Catch:{ IOException -> 0x0307, RuntimeException -> 0x05a2 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x0307, RuntimeException -> 0x05a2 }
            goto L_0x02cf
        L_0x0307:
            r2 = move-exception
            r3 = r10
            goto L_0x019e
        L_0x030b:
            int r7 = r2.getContentLength()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            if (r7 >= 0) goto L_0x0340
            java.lang.String r3 = "Stream cache aborted, missing content-length header at "
            java.lang.String r2 = java.lang.String.valueOf(r27)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            int r4 = r2.length()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            if (r4 == 0) goto L_0x033a
            java.lang.String r2 = r3.concat(r2)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
        L_0x0321:
            com.google.android.gms.internal.ads.zzakb.zzdk(r2)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.String r2 = r13.getAbsolutePath()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.String r3 = "contentLengthMissing"
            r4 = 0
            r0 = r26
            r1 = r27
            r0.zza(r1, r2, r3, r4)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.util.Set<java.lang.String> r2 = zzdbg     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r2.remove(r9)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r2 = 0
            goto L_0x0012
        L_0x033a:
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r2.<init>(r3)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            goto L_0x0321
        L_0x0340:
            java.text.DecimalFormat r3 = zzdbh     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            long r0 = (long) r7     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r16 = r0
            r0 = r16
            java.lang.String r4 = r3.format(r0)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            com.google.android.gms.internal.ads.zzna<java.lang.Integer> r3 = com.google.android.gms.internal.ads.zznk.zzauy     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            com.google.android.gms.internal.ads.zzni r6 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.Object r3 = r6.zzd(r3)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            int r15 = r3.intValue()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            if (r7 <= r15) goto L_0x03bd
            java.lang.String r2 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            int r2 = r2.length()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            int r2 = r2 + 33
            java.lang.String r3 = java.lang.String.valueOf(r27)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            int r3 = r3.length()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r3.<init>(r2)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.String r2 = "Content length "
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.String r3 = " exceeds limit at "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r0 = r27
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            com.google.android.gms.internal.ads.zzakb.zzdk(r2)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.String r3 = "File too big for full file cache. Size: "
            java.lang.String r2 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            int r4 = r2.length()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            if (r4 == 0) goto L_0x03b7
            java.lang.String r2 = r3.concat(r2)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
        L_0x03a2:
            java.lang.String r3 = r13.getAbsolutePath()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.String r4 = "sizeExceeded"
            r0 = r26
            r1 = r27
            r0.zza(r1, r3, r4, r2)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.util.Set<java.lang.String> r2 = zzdbg     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r2.remove(r9)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r2 = 0
            goto L_0x0012
        L_0x03b7:
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r2.<init>(r3)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            goto L_0x03a2
        L_0x03bd:
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            int r3 = r3.length()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            int r3 = r3 + 20
            java.lang.String r6 = java.lang.String.valueOf(r27)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            int r6 = r6.length()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            int r3 = r3 + r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r6.<init>(r3)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.String r3 = "Caching "
            java.lang.StringBuilder r3 = r6.append(r3)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.String r4 = " bytes from "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r0 = r27
            java.lang.StringBuilder r3 = r3.append(r0)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            com.google.android.gms.internal.ads.zzakb.zzck(r3)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.nio.channels.ReadableByteChannel r16 = java.nio.channels.Channels.newChannel(r2)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.io.FileOutputStream r12 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            r12.<init>(r13)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x023d }
            java.nio.channels.FileChannel r17 = r12.getChannel()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            r2 = 1048576(0x100000, float:1.469368E-39)
            java.nio.ByteBuffer r18 = java.nio.ByteBuffer.allocate(r2)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            com.google.android.gms.common.util.Clock r19 = com.google.android.gms.ads.internal.zzbv.zzer()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            r6 = 0
            long r20 = r19.currentTimeMillis()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            com.google.android.gms.internal.ads.zzna<java.lang.Long> r2 = com.google.android.gms.internal.ads.zznk.zzavb     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            com.google.android.gms.internal.ads.zzni r3 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            java.lang.Object r2 = r3.zzd(r2)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            long r2 = r2.longValue()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            com.google.android.gms.internal.ads.zzamj r22 = new com.google.android.gms.internal.ads.zzamj     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            r0 = r22
            r0.<init>(r2)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            com.google.android.gms.internal.ads.zzna<java.lang.Long> r2 = com.google.android.gms.internal.ads.zznk.zzava     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            com.google.android.gms.internal.ads.zzni r3 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            java.lang.Object r2 = r3.zzd(r2)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            long r24 = r2.longValue()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
        L_0x0439:
            r0 = r16
            r1 = r18
            int r2 = r0.read(r1)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            if (r2 < 0) goto L_0x04fa
            int r6 = r6 + r2
            if (r6 <= r15) goto L_0x0473
            java.lang.String r4 = "sizeExceeded"
            java.lang.String r2 = "File too big for full file cache. Size: "
            java.lang.String r3 = java.lang.Integer.toString(r6)     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
            int r5 = r3.length()     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
            if (r5 == 0) goto L_0x0468
            java.lang.String r3 = r2.concat(r3)     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
        L_0x045c:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException -> 0x0464, RuntimeException -> 0x04be }
            java.lang.String r5 = "stream cache file size limit exceeded"
            r2.<init>(r5)     // Catch:{ IOException -> 0x0464, RuntimeException -> 0x04be }
            throw r2     // Catch:{ IOException -> 0x0464, RuntimeException -> 0x04be }
        L_0x0464:
            r2 = move-exception
            r5 = r12
            goto L_0x019e
        L_0x0468:
            java.lang.String r3 = new java.lang.String     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
            goto L_0x045c
        L_0x046e:
            r2 = move-exception
            r3 = r10
            r5 = r12
            goto L_0x019e
        L_0x0473:
            r18.flip()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
        L_0x0476:
            int r2 = r17.write(r18)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            if (r2 > 0) goto L_0x0476
            r18.clear()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            long r2 = r19.currentTimeMillis()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            long r2 = r2 - r20
            r4 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 * r24
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x04c2
            java.lang.String r4 = "downloadTimeout"
            java.lang.String r2 = java.lang.Long.toString(r24)     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
            java.lang.String r3 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
            int r3 = r3.length()     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
            int r3 = r3 + 29
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
            r5.<init>(r3)     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
            java.lang.String r3 = "Timeout exceeded. Limit: "
            java.lang.StringBuilder r3 = r5.append(r3)     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
            java.lang.String r3 = " sec"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
            java.lang.String r3 = r2.toString()     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException -> 0x0464, RuntimeException -> 0x04be }
            java.lang.String r5 = "stream cache time limit exceeded"
            r2.<init>(r5)     // Catch:{ IOException -> 0x0464, RuntimeException -> 0x04be }
            throw r2     // Catch:{ IOException -> 0x0464, RuntimeException -> 0x04be }
        L_0x04be:
            r2 = move-exception
            r5 = r12
            goto L_0x019e
        L_0x04c2:
            r0 = r26
            boolean r2 = r0.zzdbj     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            if (r2 == 0) goto L_0x04d7
            java.lang.String r4 = "externalAbort"
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
            java.lang.String r3 = "abort requested"
            r2.<init>(r3)     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
            throw r2     // Catch:{ IOException -> 0x046e, RuntimeException -> 0x04d2 }
        L_0x04d2:
            r2 = move-exception
            r3 = r10
            r5 = r12
            goto L_0x019e
        L_0x04d7:
            boolean r2 = r22.tryAcquire()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            if (r2 == 0) goto L_0x0439
            java.lang.String r5 = r13.getAbsolutePath()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            android.os.Handler r23 = com.google.android.gms.internal.ads.zzamu.zzsy     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            com.google.android.gms.internal.ads.zzaqi r2 = new com.google.android.gms.internal.ads.zzaqi     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            r8 = 0
            r3 = r26
            r4 = r27
            r2.<init>(r3, r4, r5, r6, r7, r8)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            r0 = r23
            r0.post(r2)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            goto L_0x0439
        L_0x04f4:
            r2 = move-exception
            r3 = r10
            r4 = r11
            r5 = r12
            goto L_0x019e
        L_0x04fa:
            r12.close()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            r2 = 3
            boolean r2 = com.google.android.gms.internal.ads.zzakb.isLoggable(r2)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            if (r2 == 0) goto L_0x0540
            java.text.DecimalFormat r2 = zzdbh     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            long r4 = (long) r6     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            java.lang.String r2 = r2.format(r4)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            java.lang.String r3 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            int r3 = r3.length()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            int r3 = r3 + 22
            java.lang.String r4 = java.lang.String.valueOf(r27)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            int r4 = r4.length()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            java.lang.String r3 = "Preloaded "
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            java.lang.String r3 = " bytes from "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            r0 = r27
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            com.google.android.gms.internal.ads.zzakb.zzck(r2)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
        L_0x0540:
            r2 = 1
            r3 = 0
            r13.setReadable(r2, r3)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            boolean r2 = r14.isFile()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            if (r2 == 0) goto L_0x0565
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            r14.setLastModified(r2)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
        L_0x0552:
            java.lang.String r2 = r13.getAbsolutePath()     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            r0 = r26
            r1 = r27
            r0.zza((java.lang.String) r1, (java.lang.String) r2, (int) r6)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            java.util.Set<java.lang.String> r2 = zzdbg     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            r2.remove(r9)     // Catch:{ IOException -> 0x04f4, RuntimeException -> 0x05a9 }
            r2 = 1
            goto L_0x0012
        L_0x0565:
            r14.createNewFile()     // Catch:{ IOException -> 0x0569, RuntimeException -> 0x05a9 }
            goto L_0x0552
        L_0x0569:
            r2 = move-exception
            goto L_0x0552
        L_0x056b:
            java.lang.String r5 = java.lang.String.valueOf(r27)
            int r5 = r5.length()
            int r5 = r5 + 25
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            java.lang.String r5 = "Preload failed for URL \""
            java.lang.StringBuilder r5 = r6.append(r5)
            r0 = r27
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r6 = "\""
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.google.android.gms.internal.ads.zzakb.zzc(r5, r2)
            goto L_0x01dc
        L_0x0595:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r5)
            goto L_0x01fc
        L_0x059c:
            r5 = move-exception
            goto L_0x01ae
        L_0x059f:
            r5 = move-exception
            goto L_0x01ae
        L_0x05a2:
            r2 = move-exception
            r3 = r10
            goto L_0x019e
        L_0x05a6:
            r2 = move-exception
            goto L_0x019e
        L_0x05a9:
            r2 = move-exception
            r3 = r10
            r4 = r11
            r5 = r12
            goto L_0x019e
        L_0x05af:
            r2 = r4
            r6 = r7
            goto L_0x0093
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaqn.zzdp(java.lang.String):boolean");
    }
}
