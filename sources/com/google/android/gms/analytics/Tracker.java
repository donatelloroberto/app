package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzan;
import com.google.android.gms.internal.gtm.zzap;
import com.google.android.gms.internal.gtm.zzcg;
import com.google.android.gms.internal.gtm.zzcy;
import com.google.android.gms.internal.gtm.zzcz;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

@VisibleForTesting
public class Tracker extends zzan {
    private boolean zztb;
    private final Map<String, String> zztc = new HashMap();
    private final Map<String, String> zztd = new HashMap();
    /* access modifiers changed from: private */
    public final zzcg zzte;
    /* access modifiers changed from: private */
    public final zza zztf;
    private ExceptionReporter zztg;
    /* access modifiers changed from: private */
    public zzcy zzth;

    Tracker(zzap zzap, String str, zzcg zzcg) {
        super(zzap);
        if (str != null) {
            this.zztc.put("&tid", str);
        }
        this.zztc.put("useSecure", "1");
        this.zztc.put("&a", Integer.toString(new Random().nextInt(Integer.MAX_VALUE) + 1));
        this.zzte = new zzcg("tracking", zzcn());
        this.zztf = new zza(zzap);
    }

    class zza extends zzan implements GoogleAnalytics.zza {
        private boolean zztq;
        private int zztr;
        private long zzts = -1;
        private boolean zztt;
        private long zztu;

        protected zza(zzap zzap) {
            super(zzap);
        }

        /* access modifiers changed from: protected */
        public final void zzaw() {
        }

        public final void setSessionTimeout(long j) {
            this.zzts = j;
            zzay();
        }

        public final void enableAutoActivityTracking(boolean z) {
            this.zztq = z;
            zzay();
        }

        public final synchronized boolean zzax() {
            boolean z;
            z = this.zztt;
            this.zztt = false;
            return z;
        }

        private final void zzay() {
            if (this.zzts >= 0 || this.zztq) {
                zzcr().zza((GoogleAnalytics.zza) Tracker.this.zztf);
            } else {
                zzcr().zzb((GoogleAnalytics.zza) Tracker.this.zztf);
            }
        }

        public final void zzc(Activity activity) {
            String canonicalName;
            String stringExtra;
            if (this.zztr == 0) {
                if (zzcn().elapsedRealtime() >= this.zztu + Math.max(1000, this.zzts)) {
                    this.zztt = true;
                }
            }
            this.zztr++;
            if (this.zztq) {
                Intent intent = activity.getIntent();
                if (intent != null) {
                    Tracker.this.setCampaignParamsOnNextHit(intent.getData());
                }
                HashMap hashMap = new HashMap();
                hashMap.put("&t", "screenview");
                Tracker tracker = Tracker.this;
                if (Tracker.this.zzth != null) {
                    zzcy zzk = Tracker.this.zzth;
                    String canonicalName2 = activity.getClass().getCanonicalName();
                    canonicalName = zzk.zzacs.get(canonicalName2);
                    if (canonicalName == null) {
                        canonicalName = canonicalName2;
                    }
                } else {
                    canonicalName = activity.getClass().getCanonicalName();
                }
                tracker.set("&cd", canonicalName);
                if (TextUtils.isEmpty((CharSequence) hashMap.get("&dr"))) {
                    Preconditions.checkNotNull(activity);
                    Intent intent2 = activity.getIntent();
                    if (intent2 == null) {
                        stringExtra = null;
                    } else {
                        stringExtra = intent2.getStringExtra("android.intent.extra.REFERRER_NAME");
                        if (TextUtils.isEmpty(stringExtra)) {
                            stringExtra = null;
                        }
                    }
                    if (!TextUtils.isEmpty(stringExtra)) {
                        hashMap.put("&dr", stringExtra);
                    }
                }
                Tracker.this.send(hashMap);
            }
        }

        public final void zzd(Activity activity) {
            this.zztr--;
            this.zztr = Math.max(0, this.zztr);
            if (this.zztr == 0) {
                this.zztu = zzcn().elapsedRealtime();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzaw() {
        this.zztf.zzag();
        String zzaz = zzcu().zzaz();
        if (zzaz != null) {
            set("&an", zzaz);
        }
        String zzba = zzcu().zzba();
        if (zzba != null) {
            set("&av", zzba);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzcy zzcy) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = true;
        zzq("Loading Tracker config values");
        this.zzth = zzcy;
        if (this.zzth.zzacm != null) {
            String str = this.zzth.zzacm;
            set("&tid", str);
            zza("trackingId loaded", str);
        }
        if (this.zzth.zzacn >= 0.0d) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            String d = Double.toString(this.zzth.zzacn);
            set("&sf", d);
            zza("Sample frequency loaded", d);
        }
        if (this.zzth.zzaco >= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            int i = this.zzth.zzaco;
            setSessionTimeout((long) i);
            zza("Session timeout loaded", Integer.valueOf(i));
        }
        if (this.zzth.zzacp != -1) {
            if (this.zzth.zzacp == 1) {
                z4 = true;
            } else {
                z4 = false;
            }
            enableAutoActivityTracking(z4);
            zza("Auto activity tracking loaded", Boolean.valueOf(z4));
        }
        if (this.zzth.zzacq != -1) {
            if (this.zzth.zzacq == 1) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3) {
                set("&aip", "1");
            }
            zza("Anonymize ip loaded", Boolean.valueOf(z3));
        }
        if (this.zzth.zzacr != 1) {
            z5 = false;
        }
        enableExceptionReporting(z5);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void enableExceptionReporting(boolean r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            com.google.android.gms.analytics.ExceptionReporter r0 = r3.zztg     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x000a
            r0 = 1
        L_0x0006:
            if (r0 != r4) goto L_0x000c
            monitor-exit(r3)     // Catch:{ all -> 0x0029 }
        L_0x0009:
            return
        L_0x000a:
            r0 = 0
            goto L_0x0006
        L_0x000c:
            if (r4 == 0) goto L_0x002c
            android.content.Context r0 = r3.getContext()     // Catch:{ all -> 0x0029 }
            java.lang.Thread$UncaughtExceptionHandler r1 = java.lang.Thread.getDefaultUncaughtExceptionHandler()     // Catch:{ all -> 0x0029 }
            com.google.android.gms.analytics.ExceptionReporter r2 = new com.google.android.gms.analytics.ExceptionReporter     // Catch:{ all -> 0x0029 }
            r2.<init>(r3, r1, r0)     // Catch:{ all -> 0x0029 }
            r3.zztg = r2     // Catch:{ all -> 0x0029 }
            com.google.android.gms.analytics.ExceptionReporter r0 = r3.zztg     // Catch:{ all -> 0x0029 }
            java.lang.Thread.setDefaultUncaughtExceptionHandler(r0)     // Catch:{ all -> 0x0029 }
            java.lang.String r0 = "Uncaught exceptions will be reported to Google Analytics"
            r3.zzq(r0)     // Catch:{ all -> 0x0029 }
        L_0x0027:
            monitor-exit(r3)     // Catch:{ all -> 0x0029 }
            goto L_0x0009
        L_0x0029:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0029 }
            throw r0
        L_0x002c:
            com.google.android.gms.analytics.ExceptionReporter r0 = r3.zztg     // Catch:{ all -> 0x0029 }
            java.lang.Thread$UncaughtExceptionHandler r0 = r0.zzaf()     // Catch:{ all -> 0x0029 }
            java.lang.Thread.setDefaultUncaughtExceptionHandler(r0)     // Catch:{ all -> 0x0029 }
            java.lang.String r0 = "Uncaught exceptions will not be reported to Google Analytics"
            r3.zzq(r0)     // Catch:{ all -> 0x0029 }
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.Tracker.enableExceptionReporting(boolean):void");
    }

    public void setSessionTimeout(long j) {
        this.zztf.setSessionTimeout(1000 * j);
    }

    public void enableAutoActivityTracking(boolean z) {
        this.zztf.enableAutoActivityTracking(z);
    }

    private static String zza(Map.Entry<String, String> entry) {
        boolean z;
        String key = entry.getKey();
        if (!key.startsWith("&") || key.length() < 2) {
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            return null;
        }
        return entry.getKey().substring(1);
    }

    private static void zza(Map<String, String> map, Map<String, String> map2) {
        Preconditions.checkNotNull(map2);
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                String zza2 = zza((Map.Entry<String, String>) next);
                if (zza2 != null) {
                    map2.put(zza2, (String) next.getValue());
                }
            }
        }
    }

    public void send(Map<String, String> map) {
        long currentTimeMillis = zzcn().currentTimeMillis();
        if (zzcr().getAppOptOut()) {
            zzr("AppOptOut is set to true. Not sending Google Analytics hit");
            return;
        }
        boolean isDryRunEnabled = zzcr().isDryRunEnabled();
        HashMap hashMap = new HashMap();
        zza(this.zztc, hashMap);
        zza(map, hashMap);
        boolean zzb = zzcz.zzb(this.zztc.get("useSecure"), true);
        Map<String, String> map2 = this.zztd;
        Preconditions.checkNotNull(hashMap);
        if (map2 != null) {
            for (Map.Entry next : map2.entrySet()) {
                String zza2 = zza((Map.Entry<String, String>) next);
                if (zza2 != null && !hashMap.containsKey(zza2)) {
                    hashMap.put(zza2, (String) next.getValue());
                }
            }
        }
        this.zztd.clear();
        String str = (String) hashMap.get("t");
        if (TextUtils.isEmpty(str)) {
            zzco().zza((Map<String, String>) hashMap, "Missing hit type parameter");
            return;
        }
        String str2 = (String) hashMap.get("tid");
        if (TextUtils.isEmpty(str2)) {
            zzco().zza((Map<String, String>) hashMap, "Missing tracking id parameter");
            return;
        }
        boolean z = this.zztb;
        synchronized (this) {
            if ("screenview".equalsIgnoreCase(str) || "pageview".equalsIgnoreCase(str) || "appview".equalsIgnoreCase(str) || TextUtils.isEmpty(str)) {
                int parseInt = Integer.parseInt(this.zztc.get("&a")) + 1;
                if (parseInt >= Integer.MAX_VALUE) {
                    parseInt = 1;
                }
                this.zztc.put("&a", Integer.toString(parseInt));
            }
        }
        zzcq().zza((Runnable) new zzp(this, hashMap, z, str, currentTimeMillis, isDryRunEnabled, zzb, str2));
    }

    public String get(String str) {
        zzdb();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.zztc.containsKey(str)) {
            return this.zztc.get(str);
        }
        if (str.equals("&ul")) {
            return zzcz.zza(Locale.getDefault());
        }
        if (str.equals("&cid")) {
            return zzcw().zzeh();
        }
        if (str.equals("&sr")) {
            return zzcz().zzfb();
        }
        if (str.equals("&aid")) {
            return zzcy().zzdv().zzbb();
        }
        if (str.equals("&an")) {
            return zzcy().zzdv().zzaz();
        }
        if (str.equals("&av")) {
            return zzcy().zzdv().zzba();
        }
        if (str.equals("&aiid")) {
            return zzcy().zzdv().zzbc();
        }
        return null;
    }

    public void set(String str, String str2) {
        Preconditions.checkNotNull(str, "Key should be non-null");
        if (!TextUtils.isEmpty(str)) {
            this.zztc.put(str, str2);
        }
    }

    public void setSampleRate(double d) {
        set("&sf", Double.toString(d));
    }

    public void setUseSecure(boolean z) {
        set("useSecure", zzcz.zzc(z));
    }

    public void setScreenName(String str) {
        set("&cd", str);
    }

    public void setLocation(String str) {
        set("&dl", str);
    }

    public void setReferrer(String str) {
        set("&dr", str);
    }

    public void setPage(String str) {
        set("&dp", str);
    }

    public void setHostname(String str) {
        set("&dh", str);
    }

    public void setTitle(String str) {
        set("&dt", str);
    }

    public void setLanguage(String str) {
        set("&ul", str);
    }

    public void setEncoding(String str) {
        set("&de", str);
    }

    public void setScreenColors(String str) {
        set("&sd", str);
    }

    public void setScreenResolution(int i, int i2) {
        if (i >= 0 || i2 >= 0) {
            set("&sr", new StringBuilder(23).append(i).append("x").append(i2).toString());
        } else {
            zzt("Invalid width or height. The values should be non-negative.");
        }
    }

    public void setViewportSize(String str) {
        set("&vp", str);
    }

    public void setClientId(String str) {
        set("&cid", str);
    }

    public void setAppName(String str) {
        set("&an", str);
    }

    public void setAppId(String str) {
        set("&aid", str);
    }

    public void setAppInstallerId(String str) {
        set("&aiid", str);
    }

    public void setAppVersion(String str) {
        set("&av", str);
    }

    public void setAnonymizeIp(boolean z) {
        set("&aip", zzcz.zzc(z));
    }

    public void setCampaignParamsOnNextHit(Uri uri) {
        if (uri != null && !uri.isOpaque()) {
            String queryParameter = uri.getQueryParameter("referrer");
            if (!TextUtils.isEmpty(queryParameter)) {
                String valueOf = String.valueOf(queryParameter);
                Uri parse = Uri.parse(valueOf.length() != 0 ? "http://hostname/?".concat(valueOf) : new String("http://hostname/?"));
                String queryParameter2 = parse.getQueryParameter("utm_id");
                if (queryParameter2 != null) {
                    this.zztd.put("&ci", queryParameter2);
                }
                String queryParameter3 = parse.getQueryParameter("anid");
                if (queryParameter3 != null) {
                    this.zztd.put("&anid", queryParameter3);
                }
                String queryParameter4 = parse.getQueryParameter("utm_campaign");
                if (queryParameter4 != null) {
                    this.zztd.put("&cn", queryParameter4);
                }
                String queryParameter5 = parse.getQueryParameter("utm_content");
                if (queryParameter5 != null) {
                    this.zztd.put("&cc", queryParameter5);
                }
                String queryParameter6 = parse.getQueryParameter("utm_medium");
                if (queryParameter6 != null) {
                    this.zztd.put("&cm", queryParameter6);
                }
                String queryParameter7 = parse.getQueryParameter("utm_source");
                if (queryParameter7 != null) {
                    this.zztd.put("&cs", queryParameter7);
                }
                String queryParameter8 = parse.getQueryParameter("utm_term");
                if (queryParameter8 != null) {
                    this.zztd.put("&ck", queryParameter8);
                }
                String queryParameter9 = parse.getQueryParameter("dclid");
                if (queryParameter9 != null) {
                    this.zztd.put("&dclid", queryParameter9);
                }
                String queryParameter10 = parse.getQueryParameter("gclid");
                if (queryParameter10 != null) {
                    this.zztd.put("&gclid", queryParameter10);
                }
                String queryParameter11 = parse.getQueryParameter(FirebaseAnalytics.Param.ACLID);
                if (queryParameter11 != null) {
                    this.zztd.put("&aclid", queryParameter11);
                }
            }
        }
    }

    public void enableAdvertisingIdCollection(boolean z) {
        this.zztb = z;
    }
}
