package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONObject;

@zzadh
public final class zzakd {
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    @Nullable
    public SharedPreferences zzatw;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public boolean zzcik = true;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public boolean zzcil = true;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public boolean zzcim = true;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public boolean zzcit = false;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public String zzcpj = "";
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public int zzcqg = -1;
    private zzanz<?> zzcqu;
    /* access modifiers changed from: private */
    public CopyOnWriteArraySet<zzakh> zzcqv = new CopyOnWriteArraySet<>();
    @GuardedBy("mLock")
    @Nullable
    SharedPreferences.Editor zzcqw;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public boolean zzcqx = false;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    @Nullable
    public String zzcqy;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    @Nullable
    public String zzcqz;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public long zzcra = 0;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public long zzcrb = 0;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public long zzcrc = 0;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public int zzcrd = 0;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public Set<String> zzcre = Collections.emptySet();
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public JSONObject zzcrf = new JSONObject();

    /* access modifiers changed from: private */
    public final void zze(Bundle bundle) {
        new zzakf(this, bundle).zznt();
    }

    /* access modifiers changed from: private */
    @TargetApi(23)
    public static boolean zzqq() {
        return PlatformVersion.isAtLeastM() && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
    }

    private final void zzqr() {
        if (this.zzcqu != null && !this.zzcqu.isDone()) {
            try {
                this.zzcqu.get(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                zzakb.zzc("Interrupted while waiting for preferences loaded.", e);
            } catch (CancellationException | ExecutionException | TimeoutException e2) {
                zzakb.zzb("Fail to initialize AdSharedPreferenceManager.", e2);
            }
        }
    }

    /* access modifiers changed from: private */
    public final Bundle zzqs() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("listener_registration_bundle", true);
        synchronized (this.mLock) {
            bundle.putBoolean("use_https", this.zzcik);
            bundle.putBoolean("content_url_opted_out", this.zzcil);
            bundle.putBoolean("content_vertical_opted_out", this.zzcim);
            bundle.putBoolean("auto_collect_location", this.zzcit);
            bundle.putInt("version_code", this.zzcrd);
            bundle.putStringArray("never_pool_slots", (String[]) this.zzcre.toArray(new String[this.zzcre.size()]));
            bundle.putString("app_settings_json", this.zzcpj);
            bundle.putLong("app_settings_last_update_ms", this.zzcra);
            bundle.putLong("app_last_background_time_ms", this.zzcrb);
            bundle.putInt("request_in_session_count", this.zzcqg);
            bundle.putLong("first_ad_req_time_ms", this.zzcrc);
            bundle.putString("native_advanced_settings", this.zzcrf.toString());
            if (this.zzcqy != null) {
                bundle.putString("content_url_hashes", this.zzcqy);
            }
            if (this.zzcqz != null) {
                bundle.putString("content_vertical_hashes", this.zzcqz);
            }
        }
        return bundle;
    }

    public final void initialize(Context context) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.zzcqu = (zzanz) new zzake(this, context).zznt();
    }

    public final void zza(zzakh zzakh) {
        synchronized (this.mLock) {
            if (this.zzcqu != null && this.zzcqu.isDone()) {
                zzakh.zzd(zzqs());
            }
            this.zzcqv.add(zzakh);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0097, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0098, code lost:
        com.google.android.gms.internal.ads.zzakb.zzc("Could not update native advanced settings", r0);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r9, java.lang.String r10, boolean r11) {
        /*
            r8 = this;
            r0 = 0
            r8.zzqr()
            java.lang.Object r3 = r8.mLock
            monitor-enter(r3)
            org.json.JSONObject r1 = r8.zzcrf     // Catch:{ all -> 0x0041 }
            org.json.JSONArray r1 = r1.optJSONArray(r9)     // Catch:{ all -> 0x0041 }
            if (r1 != 0) goto L_0x009e
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ all -> 0x0041 }
            r1.<init>()     // Catch:{ all -> 0x0041 }
            r2 = r1
        L_0x0015:
            int r1 = r2.length()     // Catch:{ all -> 0x0041 }
        L_0x0019:
            int r4 = r2.length()     // Catch:{ all -> 0x0041 }
            if (r0 >= r4) goto L_0x0047
            org.json.JSONObject r4 = r2.optJSONObject(r0)     // Catch:{ all -> 0x0041 }
            if (r4 != 0) goto L_0x0027
            monitor-exit(r3)     // Catch:{ all -> 0x0041 }
        L_0x0026:
            return
        L_0x0027:
            java.lang.String r5 = "template_id"
            java.lang.String r5 = r4.optString(r5)     // Catch:{ all -> 0x0041 }
            boolean r5 = r10.equals(r5)     // Catch:{ all -> 0x0041 }
            if (r5 == 0) goto L_0x0044
            r1 = 1
            if (r11 != r1) goto L_0x0048
            java.lang.String r1 = "uses_media_view"
            r5 = 0
            boolean r1 = r4.optBoolean(r1, r5)     // Catch:{ all -> 0x0041 }
            if (r1 != r11) goto L_0x0048
            monitor-exit(r3)     // Catch:{ all -> 0x0041 }
            goto L_0x0026
        L_0x0041:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0041 }
            throw r0
        L_0x0044:
            int r0 = r0 + 1
            goto L_0x0019
        L_0x0047:
            r0 = r1
        L_0x0048:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0097 }
            r1.<init>()     // Catch:{ JSONException -> 0x0097 }
            java.lang.String r4 = "template_id"
            r1.put(r4, r10)     // Catch:{ JSONException -> 0x0097 }
            java.lang.String r4 = "uses_media_view"
            r1.put(r4, r11)     // Catch:{ JSONException -> 0x0097 }
            java.lang.String r4 = "timestamp_ms"
            com.google.android.gms.common.util.Clock r5 = com.google.android.gms.ads.internal.zzbv.zzer()     // Catch:{ JSONException -> 0x0097 }
            long r6 = r5.currentTimeMillis()     // Catch:{ JSONException -> 0x0097 }
            r1.put(r4, r6)     // Catch:{ JSONException -> 0x0097 }
            r2.put(r0, r1)     // Catch:{ JSONException -> 0x0097 }
            org.json.JSONObject r0 = r8.zzcrf     // Catch:{ JSONException -> 0x0097 }
            r0.put(r9, r2)     // Catch:{ JSONException -> 0x0097 }
        L_0x006c:
            android.content.SharedPreferences$Editor r0 = r8.zzcqw     // Catch:{ all -> 0x0041 }
            if (r0 == 0) goto L_0x0082
            android.content.SharedPreferences$Editor r0 = r8.zzcqw     // Catch:{ all -> 0x0041 }
            java.lang.String r1 = "native_advanced_settings"
            org.json.JSONObject r2 = r8.zzcrf     // Catch:{ all -> 0x0041 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0041 }
            r0.putString(r1, r2)     // Catch:{ all -> 0x0041 }
            android.content.SharedPreferences$Editor r0 = r8.zzcqw     // Catch:{ all -> 0x0041 }
            r0.apply()     // Catch:{ all -> 0x0041 }
        L_0x0082:
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x0041 }
            r0.<init>()     // Catch:{ all -> 0x0041 }
            java.lang.String r1 = "native_advanced_settings"
            org.json.JSONObject r2 = r8.zzcrf     // Catch:{ all -> 0x0041 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0041 }
            r0.putString(r1, r2)     // Catch:{ all -> 0x0041 }
            r8.zze((android.os.Bundle) r0)     // Catch:{ all -> 0x0041 }
            monitor-exit(r3)     // Catch:{ all -> 0x0041 }
            goto L_0x0026
        L_0x0097:
            r0 = move-exception
            java.lang.String r1 = "Could not update native advanced settings"
            com.google.android.gms.internal.ads.zzakb.zzc(r1, r0)     // Catch:{ all -> 0x0041 }
            goto L_0x006c
        L_0x009e:
            r2 = r1
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakd.zza(java.lang.String, java.lang.String, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzab(boolean r4) {
        /*
            r3 = this;
            r3.zzqr()
            java.lang.Object r1 = r3.mLock
            monitor-enter(r1)
            boolean r0 = r3.zzcik     // Catch:{ all -> 0x0031 }
            if (r0 != r4) goto L_0x000c
            monitor-exit(r1)     // Catch:{ all -> 0x0031 }
        L_0x000b:
            return
        L_0x000c:
            r3.zzcik = r4     // Catch:{ all -> 0x0031 }
            android.content.SharedPreferences$Editor r0 = r3.zzcqw     // Catch:{ all -> 0x0031 }
            if (r0 == 0) goto L_0x001e
            android.content.SharedPreferences$Editor r0 = r3.zzcqw     // Catch:{ all -> 0x0031 }
            java.lang.String r2 = "use_https"
            r0.putBoolean(r2, r4)     // Catch:{ all -> 0x0031 }
            android.content.SharedPreferences$Editor r0 = r3.zzcqw     // Catch:{ all -> 0x0031 }
            r0.apply()     // Catch:{ all -> 0x0031 }
        L_0x001e:
            boolean r0 = r3.zzcqx     // Catch:{ all -> 0x0031 }
            if (r0 != 0) goto L_0x002f
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x0031 }
            r0.<init>()     // Catch:{ all -> 0x0031 }
            java.lang.String r2 = "use_https"
            r0.putBoolean(r2, r4)     // Catch:{ all -> 0x0031 }
            r3.zze((android.os.Bundle) r0)     // Catch:{ all -> 0x0031 }
        L_0x002f:
            monitor-exit(r1)     // Catch:{ all -> 0x0031 }
            goto L_0x000b
        L_0x0031:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0031 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakd.zzab(boolean):void");
    }

    public final void zzac(boolean z) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcil != z) {
                this.zzcil = z;
                if (this.zzcqw != null) {
                    this.zzcqw.putBoolean("content_url_opted_out", z);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("content_url_opted_out", this.zzcil);
                bundle.putBoolean("content_vertical_opted_out", this.zzcim);
                zze(bundle);
            }
        }
    }

    public final void zzad(boolean z) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcim != z) {
                this.zzcim = z;
                if (this.zzcqw != null) {
                    this.zzcqw.putBoolean("content_vertical_opted_out", z);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("content_url_opted_out", this.zzcil);
                bundle.putBoolean("content_vertical_opted_out", this.zzcim);
                zze(bundle);
            }
        }
    }

    public final void zzae(int i) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcrd != i) {
                this.zzcrd = i;
                if (this.zzcqw != null) {
                    this.zzcqw.putInt("version_code", i);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("version_code", i);
                zze(bundle);
            }
        }
    }

    public final void zzae(boolean z) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcit != z) {
                this.zzcit = z;
                if (this.zzcqw != null) {
                    this.zzcqw.putBoolean("auto_collect_location", z);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("auto_collect_location", z);
                zze(bundle);
            }
        }
    }

    public final void zzaf(int i) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcqg != i) {
                this.zzcqg = i;
                if (this.zzcqw != null) {
                    this.zzcqw.putInt("request_in_session_count", i);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("request_in_session_count", i);
                zze(bundle);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzcn(@javax.annotation.Nullable java.lang.String r4) {
        /*
            r3 = this;
            r3.zzqr()
            java.lang.Object r1 = r3.mLock
            monitor-enter(r1)
            if (r4 == 0) goto L_0x0010
            java.lang.String r0 = r3.zzcqy     // Catch:{ all -> 0x0033 }
            boolean r0 = r4.equals(r0)     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x0012
        L_0x0010:
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
        L_0x0011:
            return
        L_0x0012:
            r3.zzcqy = r4     // Catch:{ all -> 0x0033 }
            android.content.SharedPreferences$Editor r0 = r3.zzcqw     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x0024
            android.content.SharedPreferences$Editor r0 = r3.zzcqw     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = "content_url_hashes"
            r0.putString(r2, r4)     // Catch:{ all -> 0x0033 }
            android.content.SharedPreferences$Editor r0 = r3.zzcqw     // Catch:{ all -> 0x0033 }
            r0.apply()     // Catch:{ all -> 0x0033 }
        L_0x0024:
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x0033 }
            r0.<init>()     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = "content_url_hashes"
            r0.putString(r2, r4)     // Catch:{ all -> 0x0033 }
            r3.zze((android.os.Bundle) r0)     // Catch:{ all -> 0x0033 }
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            goto L_0x0011
        L_0x0033:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakd.zzcn(java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzco(@javax.annotation.Nullable java.lang.String r4) {
        /*
            r3 = this;
            r3.zzqr()
            java.lang.Object r1 = r3.mLock
            monitor-enter(r1)
            if (r4 == 0) goto L_0x0010
            java.lang.String r0 = r3.zzcqz     // Catch:{ all -> 0x0033 }
            boolean r0 = r4.equals(r0)     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x0012
        L_0x0010:
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
        L_0x0011:
            return
        L_0x0012:
            r3.zzcqz = r4     // Catch:{ all -> 0x0033 }
            android.content.SharedPreferences$Editor r0 = r3.zzcqw     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x0024
            android.content.SharedPreferences$Editor r0 = r3.zzcqw     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = "content_vertical_hashes"
            r0.putString(r2, r4)     // Catch:{ all -> 0x0033 }
            android.content.SharedPreferences$Editor r0 = r3.zzcqw     // Catch:{ all -> 0x0033 }
            r0.apply()     // Catch:{ all -> 0x0033 }
        L_0x0024:
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x0033 }
            r0.<init>()     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = "content_vertical_hashes"
            r0.putString(r2, r4)     // Catch:{ all -> 0x0033 }
            r3.zze((android.os.Bundle) r0)     // Catch:{ all -> 0x0033 }
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            goto L_0x0011
        L_0x0033:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakd.zzco(java.lang.String):void");
    }

    public final void zzcp(String str) {
        zzqr();
        synchronized (this.mLock) {
            if (!this.zzcre.contains(str)) {
                this.zzcre.add(str);
                if (this.zzcqw != null) {
                    this.zzcqw.putStringSet("never_pool_slots", this.zzcre);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putStringArray("never_pool_slots", (String[]) this.zzcre.toArray(new String[this.zzcre.size()]));
                zze(bundle);
            }
        }
    }

    public final void zzcq(String str) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcre.contains(str)) {
                this.zzcre.remove(str);
                if (this.zzcqw != null) {
                    this.zzcqw.putStringSet("never_pool_slots", this.zzcre);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putStringArray("never_pool_slots", (String[]) this.zzcre.toArray(new String[this.zzcre.size()]));
                zze(bundle);
            }
        }
    }

    public final boolean zzcr(String str) {
        boolean contains;
        zzqr();
        synchronized (this.mLock) {
            contains = this.zzcre.contains(str);
        }
        return contains;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzcs(java.lang.String r6) {
        /*
            r5 = this;
            r5.zzqr()
            java.lang.Object r1 = r5.mLock
            monitor-enter(r1)
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.zzbv.zzer()     // Catch:{ all -> 0x0049 }
            long r2 = r0.currentTimeMillis()     // Catch:{ all -> 0x0049 }
            r5.zzcra = r2     // Catch:{ all -> 0x0049 }
            if (r6 == 0) goto L_0x001a
            java.lang.String r0 = r5.zzcpj     // Catch:{ all -> 0x0049 }
            boolean r0 = r6.equals(r0)     // Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x001c
        L_0x001a:
            monitor-exit(r1)     // Catch:{ all -> 0x0049 }
        L_0x001b:
            return
        L_0x001c:
            r5.zzcpj = r6     // Catch:{ all -> 0x0049 }
            android.content.SharedPreferences$Editor r0 = r5.zzcqw     // Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x0035
            android.content.SharedPreferences$Editor r0 = r5.zzcqw     // Catch:{ all -> 0x0049 }
            java.lang.String r4 = "app_settings_json"
            r0.putString(r4, r6)     // Catch:{ all -> 0x0049 }
            android.content.SharedPreferences$Editor r0 = r5.zzcqw     // Catch:{ all -> 0x0049 }
            java.lang.String r4 = "app_settings_last_update_ms"
            r0.putLong(r4, r2)     // Catch:{ all -> 0x0049 }
            android.content.SharedPreferences$Editor r0 = r5.zzcqw     // Catch:{ all -> 0x0049 }
            r0.apply()     // Catch:{ all -> 0x0049 }
        L_0x0035:
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x0049 }
            r0.<init>()     // Catch:{ all -> 0x0049 }
            java.lang.String r4 = "app_settings_json"
            r0.putString(r4, r6)     // Catch:{ all -> 0x0049 }
            java.lang.String r4 = "app_settings_last_update_ms"
            r0.putLong(r4, r2)     // Catch:{ all -> 0x0049 }
            r5.zze((android.os.Bundle) r0)     // Catch:{ all -> 0x0049 }
            monitor-exit(r1)     // Catch:{ all -> 0x0049 }
            goto L_0x001b
        L_0x0049:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0049 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakd.zzcs(java.lang.String):void");
    }

    public final void zzj(long j) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcrb != j) {
                this.zzcrb = j;
                if (this.zzcqw != null) {
                    this.zzcqw.putLong("app_last_background_time_ms", j);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putLong("app_last_background_time_ms", j);
                zze(bundle);
            }
        }
    }

    public final void zzk(long j) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcrc != j) {
                this.zzcrc = j;
                if (this.zzcqw != null) {
                    this.zzcqw.putLong("first_ad_req_time_ms", j);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putLong("first_ad_req_time_ms", j);
                zze(bundle);
            }
        }
    }

    public final boolean zzqt() {
        boolean z;
        zzqr();
        synchronized (this.mLock) {
            z = this.zzcik || this.zzcqx;
        }
        return z;
    }

    public final boolean zzqu() {
        boolean z;
        zzqr();
        synchronized (this.mLock) {
            z = this.zzcil;
        }
        return z;
    }

    @Nullable
    public final String zzqv() {
        String str;
        zzqr();
        synchronized (this.mLock) {
            str = this.zzcqy;
        }
        return str;
    }

    public final boolean zzqw() {
        boolean z;
        zzqr();
        synchronized (this.mLock) {
            z = this.zzcim;
        }
        return z;
    }

    @Nullable
    public final String zzqx() {
        String str;
        zzqr();
        synchronized (this.mLock) {
            str = this.zzcqz;
        }
        return str;
    }

    public final boolean zzqy() {
        boolean z;
        zzqr();
        synchronized (this.mLock) {
            z = this.zzcit;
        }
        return z;
    }

    public final int zzqz() {
        int i;
        zzqr();
        synchronized (this.mLock) {
            i = this.zzcrd;
        }
        return i;
    }

    public final zzajl zzra() {
        zzajl zzajl;
        zzqr();
        synchronized (this.mLock) {
            zzajl = new zzajl(this.zzcpj, this.zzcra);
        }
        return zzajl;
    }

    public final long zzrb() {
        long j;
        zzqr();
        synchronized (this.mLock) {
            j = this.zzcrb;
        }
        return j;
    }

    public final int zzrc() {
        int i;
        zzqr();
        synchronized (this.mLock) {
            i = this.zzcqg;
        }
        return i;
    }

    public final long zzrd() {
        long j;
        zzqr();
        synchronized (this.mLock) {
            j = this.zzcrc;
        }
        return j;
    }

    public final JSONObject zzre() {
        JSONObject jSONObject;
        zzqr();
        synchronized (this.mLock) {
            jSONObject = this.zzcrf;
        }
        return jSONObject;
    }

    public final void zzrf() {
        zzqr();
        synchronized (this.mLock) {
            this.zzcrf = new JSONObject();
            if (this.zzcqw != null) {
                this.zzcqw.remove("native_advanced_settings");
                this.zzcqw.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", "{}");
            zze(bundle);
        }
    }
}
