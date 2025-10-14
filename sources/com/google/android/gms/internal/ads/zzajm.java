package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.GuardedBy;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzajm implements zzakh {
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    @Nullable
    private zzgf zzahz = null;
    private final zzajt zzcpl = new zzajt();
    private final zzakd zzcpm = new zzakd();
    /* access modifiers changed from: private */
    @Nullable
    public zznn zzcpn = null;
    @Nullable
    private zzgk zzcpo = null;
    @Nullable
    private Boolean zzcpp = null;
    private String zzcpq;
    private final AtomicInteger zzcpr = new AtomicInteger(0);
    private final zzajp zzcps = new zzajp((zzajo) null);
    private final Object zzcpt = new Object();
    @GuardedBy("mGrantedPermissionLock")
    private zzanz<ArrayList<String>> zzcpu;
    private zzes zzvy;
    /* access modifiers changed from: private */
    public zzang zzyf;
    private boolean zzzv = false;

    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        return null;
     */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzgk zza(@javax.annotation.Nullable android.content.Context r5, boolean r6, boolean r7) {
        /*
            r4 = this;
            r1 = 0
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzawk
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r2.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x0015
            r0 = r1
        L_0x0014:
            return r0
        L_0x0015:
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich()
            if (r0 != 0) goto L_0x001d
            r0 = r1
            goto L_0x0014
        L_0x001d:
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzaws
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r2.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x0043
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzawq
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r2.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x0043
            r0 = r1
            goto L_0x0014
        L_0x0043:
            if (r6 == 0) goto L_0x0049
            if (r7 == 0) goto L_0x0049
            r0 = r1
            goto L_0x0014
        L_0x0049:
            java.lang.Object r2 = r4.mLock
            monitor-enter(r2)
            android.os.Looper r0 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x0083 }
            if (r0 == 0) goto L_0x0054
            if (r5 != 0) goto L_0x0057
        L_0x0054:
            monitor-exit(r2)     // Catch:{ all -> 0x0083 }
            r0 = r1
            goto L_0x0014
        L_0x0057:
            com.google.android.gms.internal.ads.zzgf r0 = r4.zzahz     // Catch:{ all -> 0x0083 }
            if (r0 != 0) goto L_0x0062
            com.google.android.gms.internal.ads.zzgf r0 = new com.google.android.gms.internal.ads.zzgf     // Catch:{ all -> 0x0083 }
            r0.<init>()     // Catch:{ all -> 0x0083 }
            r4.zzahz = r0     // Catch:{ all -> 0x0083 }
        L_0x0062:
            com.google.android.gms.internal.ads.zzgk r0 = r4.zzcpo     // Catch:{ all -> 0x0083 }
            if (r0 != 0) goto L_0x0075
            com.google.android.gms.internal.ads.zzgk r0 = new com.google.android.gms.internal.ads.zzgk     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzgf r1 = r4.zzahz     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzang r3 = r4.zzyf     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzadf r3 = com.google.android.gms.internal.ads.zzadb.zzc(r5, r3)     // Catch:{ all -> 0x0083 }
            r0.<init>(r1, r3)     // Catch:{ all -> 0x0083 }
            r4.zzcpo = r0     // Catch:{ all -> 0x0083 }
        L_0x0075:
            com.google.android.gms.internal.ads.zzgk r0 = r4.zzcpo     // Catch:{ all -> 0x0083 }
            r0.zzgw()     // Catch:{ all -> 0x0083 }
            java.lang.String r0 = "start fetching content..."
            com.google.android.gms.internal.ads.zzakb.zzdj(r0)     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzgk r0 = r4.zzcpo     // Catch:{ all -> 0x0083 }
            monitor-exit(r2)     // Catch:{ all -> 0x0083 }
            goto L_0x0014
        L_0x0083:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0083 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzajm.zza(android.content.Context, boolean, boolean):com.google.android.gms.internal.ads.zzgk");
    }

    @TargetApi(16)
    private static ArrayList<String> zzag(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(context.getApplicationInfo().packageName, 4096);
            if (packageInfo.requestedPermissions == null || packageInfo.requestedPermissionsFlags == null) {
                return arrayList;
            }
            for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
                if ((packageInfo.requestedPermissionsFlags[i] & 2) != 0) {
                    arrayList.add(packageInfo.requestedPermissions[i]);
                }
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException e) {
            return arrayList;
        }
    }

    @Nullable
    public final Context getApplicationContext() {
        return this.mContext;
    }

    @Nullable
    public final Resources getResources() {
        if (this.zzyf.zzcvg) {
            return this.mContext.getResources();
        }
        try {
            DynamiteModule load = DynamiteModule.load(this.mContext, DynamiteModule.PREFER_REMOTE, ModuleDescriptor.MODULE_ID);
            if (load != null) {
                return load.getModuleContext().getResources();
            }
            return null;
        } catch (DynamiteModule.LoadingException e) {
            zzakb.zzc("Cannot load resource from dynamite apk or local jar", e);
            return null;
        }
    }

    public final void zza(Boolean bool) {
        synchronized (this.mLock) {
            this.zzcpp = bool;
        }
    }

    public final void zza(Throwable th, String str) {
        zzadb.zzc(this.mContext, this.zzyf).zza(th, str);
    }

    public final void zzaa(boolean z) {
        this.zzcps.zzaa(z);
    }

    @Nullable
    public final zzgk zzaf(@Nullable Context context) {
        return zza(context, this.zzcpm.zzqu(), this.zzcpm.zzqw());
    }

    public final void zzb(Throwable th, String str) {
        zzadb.zzc(this.mContext, this.zzyf).zza(th, str, ((Float) zzkb.zzik().zzd(zznk.zzaul)).floatValue());
    }

    @TargetApi(23)
    public final void zzd(Context context, zzang zzang) {
        zznn zznn;
        synchronized (this.mLock) {
            if (!this.zzzv) {
                this.mContext = context.getApplicationContext();
                this.zzyf = zzang;
                zzbv.zzen().zza(zzbv.zzep());
                this.zzcpm.initialize(this.mContext);
                this.zzcpm.zza((zzakh) this);
                zzadb.zzc(this.mContext, this.zzyf);
                this.zzcpq = zzbv.zzek().zzm(context, zzang.zzcw);
                this.zzvy = new zzes(context.getApplicationContext(), this.zzyf);
                zzbv.zzet();
                if (!((Boolean) zzkb.zzik().zzd(zznk.zzawh)).booleanValue()) {
                    zzakb.v("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
                    zznn = null;
                } else {
                    zznn = new zznn();
                }
                this.zzcpn = zznn;
                zzanm.zza((zzanz) new zzajo(this).zznt(), "AppState.registerCsiReporter");
                this.zzzv = true;
                zzqi();
            }
        }
    }

    public final void zzd(Bundle bundle) {
        if (bundle.containsKey("content_url_opted_out") && bundle.containsKey("content_vertical_opted_out")) {
            zza(this.mContext, bundle.getBoolean("content_url_opted_out"), bundle.getBoolean("content_vertical_opted_out"));
        }
    }

    public final zzajt zzpx() {
        return this.zzcpl;
    }

    @Nullable
    public final zznn zzpy() {
        zznn zznn;
        synchronized (this.mLock) {
            zznn = this.zzcpn;
        }
        return zznn;
    }

    public final Boolean zzpz() {
        Boolean bool;
        synchronized (this.mLock) {
            bool = this.zzcpp;
        }
        return bool;
    }

    public final boolean zzqa() {
        return this.zzcps.zzqa();
    }

    public final boolean zzqb() {
        return this.zzcps.zzqb();
    }

    public final void zzqc() {
        this.zzcps.zzqc();
    }

    public final zzes zzqd() {
        return this.zzvy;
    }

    public final void zzqe() {
        this.zzcpr.incrementAndGet();
    }

    public final void zzqf() {
        this.zzcpr.decrementAndGet();
    }

    public final int zzqg() {
        return this.zzcpr.get();
    }

    public final zzakd zzqh() {
        zzakd zzakd;
        synchronized (this.mLock) {
            zzakd = this.zzcpm;
        }
        return zzakd;
    }

    public final zzanz<ArrayList<String>> zzqi() {
        if (this.mContext != null && PlatformVersion.isAtLeastJellyBean()) {
            if (!((Boolean) zzkb.zzik().zzd(zznk.zzbau)).booleanValue()) {
                synchronized (this.zzcpt) {
                    if (this.zzcpu != null) {
                        zzanz<ArrayList<String>> zzanz = this.zzcpu;
                        return zzanz;
                    }
                    zzanz<ArrayList<String>> zza = zzaki.zza(new zzajn(this));
                    this.zzcpu = zza;
                    return zza;
                }
            }
        }
        return zzano.zzi(new ArrayList());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ ArrayList zzqj() throws Exception {
        return zzag(this.mContext);
    }
}
