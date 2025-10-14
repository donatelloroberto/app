package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzaii implements zzait {
    /* access modifiers changed from: private */
    public static List<Future<Void>> zzcml = Collections.synchronizedList(new ArrayList());
    private static ScheduledExecutorService zzcmm = Executors.newSingleThreadScheduledExecutor();
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private final zzaiq zzciy;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final zzbfm zzcmn;
    @GuardedBy("mLock")
    private final LinkedHashMap<String, zzbfu> zzcmo;
    @GuardedBy("mLock")
    private final List<String> zzcmp = new ArrayList();
    @GuardedBy("mLock")
    private final List<String> zzcmq = new ArrayList();
    private final zzaiv zzcmr;
    @VisibleForTesting
    private boolean zzcms;
    private final zzaiw zzcmt;
    private HashSet<String> zzcmu = new HashSet<>();
    private boolean zzcmv = false;
    private boolean zzcmw = false;
    private boolean zzcmx = false;

    public zzaii(Context context, zzang zzang, zzaiq zzaiq, String str, zzaiv zzaiv) {
        Preconditions.checkNotNull(zzaiq, "SafeBrowsing config is not present.");
        this.mContext = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzcmo = new LinkedHashMap<>();
        this.zzcmr = zzaiv;
        this.zzciy = zzaiq;
        for (String lowerCase : this.zzciy.zzcnh) {
            this.zzcmu.add(lowerCase.toLowerCase(Locale.ENGLISH));
        }
        this.zzcmu.remove("cookie".toLowerCase(Locale.ENGLISH));
        zzbfm zzbfm = new zzbfm();
        zzbfm.zzamf = 8;
        zzbfm.url = str;
        zzbfm.zzech = str;
        zzbfm.zzecj = new zzbfn();
        zzbfm.zzecj.zzcnd = this.zzciy.zzcnd;
        zzbfv zzbfv = new zzbfv();
        zzbfv.zzedv = zzang.zzcw;
        zzbfv.zzedx = Boolean.valueOf(Wrappers.packageManager(this.mContext).isCallerInstantApp());
        long apkVersion = (long) GoogleApiAvailabilityLight.getInstance().getApkVersion(this.mContext);
        if (apkVersion > 0) {
            zzbfv.zzedw = Long.valueOf(apkVersion);
        }
        zzbfm.zzect = zzbfv;
        this.zzcmn = zzbfm;
        this.zzcmt = new zzaiw(this.mContext, this.zzciy.zzcnk, this);
    }

    @Nullable
    private final zzbfu zzci(String str) {
        zzbfu zzbfu;
        synchronized (this.mLock) {
            zzbfu = this.zzcmo.get(str);
        }
        return zzbfu;
    }

    static final /* synthetic */ Void zzcj(String str) {
        return null;
    }

    @VisibleForTesting
    private final zzanz<Void> zzpk() {
        zzanz<Void> zza;
        boolean z = true;
        if ((!this.zzcms || !this.zzciy.zzcnj) && ((!this.zzcmx || !this.zzciy.zzcni) && (this.zzcms || !this.zzciy.zzcng))) {
            z = false;
        }
        if (!z) {
            return zzano.zzi(null);
        }
        synchronized (this.mLock) {
            this.zzcmn.zzeck = new zzbfu[this.zzcmo.size()];
            this.zzcmo.values().toArray(this.zzcmn.zzeck);
            this.zzcmn.zzecu = (String[]) this.zzcmp.toArray(new String[0]);
            this.zzcmn.zzecv = (String[]) this.zzcmq.toArray(new String[0]);
            if (zzais.isEnabled()) {
                String str = this.zzcmn.url;
                String str2 = this.zzcmn.zzecl;
                StringBuilder sb = new StringBuilder(new StringBuilder(String.valueOf(str).length() + 53 + String.valueOf(str2).length()).append("Sending SB report\n  url: ").append(str).append("\n  clickUrl: ").append(str2).append("\n  resources: \n").toString());
                for (zzbfu zzbfu : this.zzcmn.zzeck) {
                    sb.append("    [");
                    sb.append(zzbfu.zzedu.length);
                    sb.append("] ");
                    sb.append(zzbfu.url);
                }
                zzais.zzck(sb.toString());
            }
            zzanz<String> zza2 = new zzalt(this.mContext).zza(1, this.zzciy.zzcne, (Map<String, String>) null, zzbfi.zzb(this.zzcmn));
            if (zzais.isEnabled()) {
                zza2.zza(new zzain(this), zzaki.zzcrj);
            }
            zza = zzano.zza(zza2, zzaik.zzcmz, zzaoe.zzcvz);
        }
        return zza;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        return;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r9, java.util.Map<java.lang.String, java.lang.String> r10, int r11) {
        /*
            r8 = this;
            r1 = 3
            java.lang.Object r2 = r8.mLock
            monitor-enter(r2)
            if (r11 != r1) goto L_0x0009
            r0 = 1
            r8.zzcmx = r0     // Catch:{ all -> 0x00ac }
        L_0x0009:
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.zzbfu> r0 = r8.zzcmo     // Catch:{ all -> 0x00ac }
            boolean r0 = r0.containsKey(r9)     // Catch:{ all -> 0x00ac }
            if (r0 == 0) goto L_0x0023
            if (r11 != r1) goto L_0x0021
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.zzbfu> r0 = r8.zzcmo     // Catch:{ all -> 0x00ac }
            java.lang.Object r0 = r0.get(r9)     // Catch:{ all -> 0x00ac }
            com.google.android.gms.internal.ads.zzbfu r0 = (com.google.android.gms.internal.ads.zzbfu) r0     // Catch:{ all -> 0x00ac }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x00ac }
            r0.zzedt = r1     // Catch:{ all -> 0x00ac }
        L_0x0021:
            monitor-exit(r2)     // Catch:{ all -> 0x00ac }
        L_0x0022:
            return
        L_0x0023:
            com.google.android.gms.internal.ads.zzbfu r3 = new com.google.android.gms.internal.ads.zzbfu     // Catch:{ all -> 0x00ac }
            r3.<init>()     // Catch:{ all -> 0x00ac }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x00ac }
            r3.zzedt = r0     // Catch:{ all -> 0x00ac }
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.zzbfu> r0 = r8.zzcmo     // Catch:{ all -> 0x00ac }
            int r0 = r0.size()     // Catch:{ all -> 0x00ac }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x00ac }
            r3.zzedn = r0     // Catch:{ all -> 0x00ac }
            r3.url = r9     // Catch:{ all -> 0x00ac }
            com.google.android.gms.internal.ads.zzbfp r0 = new com.google.android.gms.internal.ads.zzbfp     // Catch:{ all -> 0x00ac }
            r0.<init>()     // Catch:{ all -> 0x00ac }
            r3.zzedo = r0     // Catch:{ all -> 0x00ac }
            java.util.HashSet<java.lang.String> r0 = r8.zzcmu     // Catch:{ all -> 0x00ac }
            int r0 = r0.size()     // Catch:{ all -> 0x00ac }
            if (r0 <= 0) goto L_0x00c2
            if (r10 == 0) goto L_0x00c2
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x00ac }
            r4.<init>()     // Catch:{ all -> 0x00ac }
            java.util.Set r0 = r10.entrySet()     // Catch:{ all -> 0x00ac }
            java.util.Iterator r5 = r0.iterator()     // Catch:{ all -> 0x00ac }
        L_0x005a:
            boolean r0 = r5.hasNext()     // Catch:{ all -> 0x00ac }
            if (r0 == 0) goto L_0x00b5
            java.lang.Object r0 = r5.next()     // Catch:{ all -> 0x00ac }
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ all -> 0x00ac }
            java.lang.Object r1 = r0.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
            if (r1 == 0) goto L_0x00af
            java.lang.Object r1 = r0.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
        L_0x0072:
            java.lang.Object r6 = r0.getValue()     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
            if (r6 == 0) goto L_0x00b2
            java.lang.Object r0 = r0.getValue()     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
        L_0x007e:
            java.util.Locale r6 = java.util.Locale.ENGLISH     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
            java.lang.String r6 = r1.toLowerCase(r6)     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
            java.util.HashSet<java.lang.String> r7 = r8.zzcmu     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
            boolean r6 = r7.contains(r6)     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
            if (r6 == 0) goto L_0x005a
            com.google.android.gms.internal.ads.zzbfo r6 = new com.google.android.gms.internal.ads.zzbfo     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
            r6.<init>()     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
            java.lang.String r7 = "UTF-8"
            byte[] r1 = r1.getBytes(r7)     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
            r6.zzecx = r1     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
            java.lang.String r1 = "UTF-8"
            byte[] r0 = r0.getBytes(r1)     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
            r6.zzecy = r0     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
            r4.add(r6)     // Catch:{ UnsupportedEncodingException -> 0x00a5 }
            goto L_0x005a
        L_0x00a5:
            r0 = move-exception
            java.lang.String r0 = "Cannot convert string to bytes, skip header."
            com.google.android.gms.internal.ads.zzais.zzck(r0)     // Catch:{ all -> 0x00ac }
            goto L_0x005a
        L_0x00ac:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00ac }
            throw r0
        L_0x00af:
            java.lang.String r1 = ""
            goto L_0x0072
        L_0x00b2:
            java.lang.String r0 = ""
            goto L_0x007e
        L_0x00b5:
            int r0 = r4.size()     // Catch:{ all -> 0x00ac }
            com.google.android.gms.internal.ads.zzbfo[] r0 = new com.google.android.gms.internal.ads.zzbfo[r0]     // Catch:{ all -> 0x00ac }
            r4.toArray(r0)     // Catch:{ all -> 0x00ac }
            com.google.android.gms.internal.ads.zzbfp r1 = r3.zzedo     // Catch:{ all -> 0x00ac }
            r1.zzeda = r0     // Catch:{ all -> 0x00ac }
        L_0x00c2:
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.zzbfu> r0 = r8.zzcmo     // Catch:{ all -> 0x00ac }
            r0.put(r9, r3)     // Catch:{ all -> 0x00ac }
            monitor-exit(r2)     // Catch:{ all -> 0x00ac }
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaii.zza(java.lang.String, java.util.Map, int):void");
    }

    public final String[] zzb(String[] strArr) {
        return (String[]) this.zzcmt.zzc(strArr).toArray(new String[0]);
    }

    public final void zzcf(String str) {
        synchronized (this.mLock) {
            this.zzcmn.zzecl = str;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzcg(String str) {
        synchronized (this.mLock) {
            this.zzcmp.add(str);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzch(String str) {
        synchronized (this.mLock) {
            this.zzcmq.add(str);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzanz zzm(Map map) throws Exception {
        if (map != null) {
            for (String str : map.keySet()) {
                JSONArray optJSONArray = new JSONObject((String) map.get(str)).optJSONArray("matches");
                if (optJSONArray != null) {
                    synchronized (this.mLock) {
                        int length = optJSONArray.length();
                        zzbfu zzci = zzci(str);
                        if (zzci == null) {
                            String valueOf = String.valueOf(str);
                            zzais.zzck(valueOf.length() != 0 ? "Cannot find the corresponding resource object for ".concat(valueOf) : new String("Cannot find the corresponding resource object for "));
                        } else {
                            zzci.zzedu = new String[length];
                            for (int i = 0; i < length; i++) {
                                zzci.zzedu[i] = optJSONArray.getJSONObject(i).getString("threat_type");
                            }
                            this.zzcms = (length > 0) | this.zzcms;
                        }
                    }
                }
            }
        }
        try {
            if (this.zzcms) {
                synchronized (this.mLock) {
                    this.zzcmn.zzamf = 9;
                }
            }
            return zzpk();
        } catch (JSONException e) {
            JSONException jSONException = e;
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbdi)).booleanValue()) {
                zzakb.zza("Failed to get SafeBrowsing metadata", jSONException);
            }
            return zzano.zzd(new Exception("Safebrowsing report transmission failed."));
        }
    }

    public final zzaiq zzpg() {
        return this.zzciy;
    }

    public final boolean zzph() {
        return PlatformVersion.isAtLeastKitKat() && this.zzciy.zzcnf && !this.zzcmw;
    }

    public final void zzpi() {
        this.zzcmv = true;
    }

    public final void zzpj() {
        synchronized (this.mLock) {
            zzanz<B> zza = zzano.zza(this.zzcmr.zza(this.mContext, this.zzcmo.keySet()), new zzaij(this), zzaoe.zzcvz);
            zzanz<V> zza2 = zzano.zza(zza, 10, TimeUnit.SECONDS, zzcmm);
            zzano.zza(zza, new zzaim(this, zza2), zzaoe.zzcvz);
            zzcml.add(zza2);
        }
    }

    public final void zzr(View view) {
        if (this.zzciy.zzcnf && !this.zzcmw) {
            zzbv.zzek();
            Bitmap zzt = zzakk.zzt(view);
            if (zzt == null) {
                zzais.zzck("Failed to capture the webview bitmap.");
                return;
            }
            this.zzcmw = true;
            zzakk.zzd(new zzail(this, zzt));
        }
    }
}
