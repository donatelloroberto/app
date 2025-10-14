package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzhu;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONObject;

@zzadh
public final class zzadk extends zzajx implements zzadx {
    private final Context mContext;
    @VisibleForTesting
    private zzwy zzbtj;
    @VisibleForTesting
    private zzaef zzbuc;
    @VisibleForTesting
    private zzaej zzbzf;
    /* access modifiers changed from: private */
    public Runnable zzbzg;
    /* access modifiers changed from: private */
    public final Object zzbzh = new Object();
    private final zzadj zzccf;
    /* access modifiers changed from: private */
    public final zzaeg zzccg;
    private final zzhs zzcch;
    private final zzhx zzcci;
    @GuardedBy("mCancelLock")
    @VisibleForTesting
    zzalc zzccj;

    public zzadk(Context context, zzaeg zzaeg, zzadj zzadj, zzhx zzhx) {
        this.zzccf = zzadj;
        this.mContext = context;
        this.zzccg = zzaeg;
        this.zzcci = zzhx;
        this.zzcch = new zzhs(this.zzcci);
        this.zzcch.zza((zzht) new zzadl(this));
        zzit zzit = new zzit();
        zzit.zzaot = Integer.valueOf(this.zzccg.zzacr.zzcve);
        zzit.zzaou = Integer.valueOf(this.zzccg.zzacr.zzcvf);
        zzit.zzaov = Integer.valueOf(this.zzccg.zzacr.zzcvg ? 0 : 2);
        this.zzcch.zza((zzht) new zzadm(zzit));
        if (this.zzccg.zzccw != null) {
            this.zzcch.zza((zzht) new zzadn(this));
        }
        zzjn zzjn = this.zzccg.zzacv;
        if (zzjn.zzarc && "interstitial_mb".equals(zzjn.zzarb)) {
            this.zzcch.zza(zzado.zzccm);
        } else if (zzjn.zzarc && "reward_mb".equals(zzjn.zzarb)) {
            this.zzcch.zza(zzadp.zzccm);
        } else if (zzjn.zzare || zzjn.zzarc) {
            this.zzcch.zza(zzadr.zzccm);
        } else {
            this.zzcch.zza(zzadq.zzccm);
        }
        this.zzcch.zza(zzhu.zza.zzb.AD_REQUEST);
    }

    @VisibleForTesting
    private final zzjn zza(zzaef zzaef) throws zzadu {
        boolean z = true;
        if (this.zzbuc == null || this.zzbuc.zzadn == null || this.zzbuc.zzadn.size() <= 1) {
            z = false;
        }
        if (z && this.zzbtj != null && !this.zzbtj.zzbte) {
            return null;
        }
        if (this.zzbzf.zzarf) {
            for (zzjn zzjn : zzaef.zzacv.zzard) {
                if (zzjn.zzarf) {
                    return new zzjn(zzjn, zzaef.zzacv.zzard);
                }
            }
        }
        if (this.zzbzf.zzcet == null) {
            throw new zzadu("The ad response must specify one of the supported ad sizes.", 0);
        }
        String[] split = this.zzbzf.zzcet.split("x");
        if (split.length != 2) {
            String valueOf = String.valueOf(this.zzbzf.zzcet);
            throw new zzadu(valueOf.length() != 0 ? "Invalid ad size format from the ad response: ".concat(valueOf) : new String("Invalid ad size format from the ad response: "), 0);
        }
        try {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            for (zzjn zzjn2 : zzaef.zzacv.zzard) {
                float f = this.mContext.getResources().getDisplayMetrics().density;
                int i = zzjn2.width == -1 ? (int) (((float) zzjn2.widthPixels) / f) : zzjn2.width;
                int i2 = zzjn2.height == -2 ? (int) (((float) zzjn2.heightPixels) / f) : zzjn2.height;
                if (parseInt == i && parseInt2 == i2 && !zzjn2.zzarf) {
                    return new zzjn(zzjn2, zzaef.zzacv.zzard);
                }
            }
            String valueOf2 = String.valueOf(this.zzbzf.zzcet);
            throw new zzadu(valueOf2.length() != 0 ? "The ad size from the ad response was not one of the requested sizes: ".concat(valueOf2) : new String("The ad size from the ad response was not one of the requested sizes: "), 0);
        } catch (NumberFormatException e) {
            String valueOf3 = String.valueOf(this.zzbzf.zzcet);
            throw new zzadu(valueOf3.length() != 0 ? "Invalid ad size number from the ad response: ".concat(valueOf3) : new String("Invalid ad size number from the ad response: "), 0);
        }
    }

    /* access modifiers changed from: private */
    public final void zzc(int i, String str) {
        if (i == 3 || i == -1) {
            zzakb.zzdj(str);
        } else {
            zzakb.zzdk(str);
        }
        if (this.zzbzf == null) {
            this.zzbzf = new zzaej(i);
        } else {
            this.zzbzf = new zzaej(i, this.zzbzf.zzbsu);
        }
        this.zzccf.zza(new zzaji(this.zzbuc != null ? this.zzbuc : new zzaef(this.zzccg, -1, (String) null, (String) null, (String) null), this.zzbzf, this.zzbtj, (zzjn) null, i, -1, this.zzbzf.zzceu, (JSONObject) null, this.zzcch, (Boolean) null));
    }

    public final void onStop() {
        synchronized (this.zzbzh) {
            if (this.zzccj != null) {
                this.zzccj.cancel();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final zzalc zza(zzang zzang, zzaol<zzaef> zzaol) {
        Context context = this.mContext;
        if (new zzadw(context).zza(zzang)) {
            zzakb.zzck("Fetching ad response from local ad request service.");
            zzaec zzaec = new zzaec(context, zzaol, this);
            zzaec.zznt();
            return zzaec;
        }
        zzakb.zzck("Fetching ad response from remote ad request service.");
        zzkb.zzif();
        if (zzamu.zzbe(context)) {
            return new zzaed(context, zzang, zzaol, this);
        }
        zzakb.zzdk("Failed to connect to remote ad request service.");
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0197  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01a1  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0221  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(@android.support.annotation.NonNull com.google.android.gms.internal.ads.zzaej r14) {
        /*
            r13 = this;
            r9 = 0
            r5 = -2
            r3 = -3
            r8 = 1
            r1 = 0
            java.lang.String r0 = "Received ad response."
            com.google.android.gms.internal.ads.zzakb.zzck(r0)
            r13.zzbzf = r14
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.zzbv.zzer()
            long r6 = r0.elapsedRealtime()
            java.lang.Object r2 = r13.zzbzh
            monitor-enter(r2)
            r0 = 0
            r13.zzccj = r0     // Catch:{ all -> 0x0094 }
            monitor-exit(r2)     // Catch:{ all -> 0x0094 }
            com.google.android.gms.internal.ads.zzajm r0 = com.google.android.gms.ads.internal.zzbv.zzeo()
            com.google.android.gms.internal.ads.zzakd r0 = r0.zzqh()
            com.google.android.gms.internal.ads.zzaej r2 = r13.zzbzf
            boolean r2 = r2.zzcdr
            r0.zzae((boolean) r2)
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzayy
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r2.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0051
            com.google.android.gms.internal.ads.zzaej r0 = r13.zzbzf
            boolean r0 = r0.zzced
            if (r0 == 0) goto L_0x0097
            com.google.android.gms.internal.ads.zzajm r0 = com.google.android.gms.ads.internal.zzbv.zzeo()
            com.google.android.gms.internal.ads.zzakd r0 = r0.zzqh()
            com.google.android.gms.internal.ads.zzaef r2 = r13.zzbuc
            java.lang.String r2 = r2.zzacp
            r0.zzcp(r2)
        L_0x0051:
            com.google.android.gms.internal.ads.zzaej r0 = r13.zzbzf     // Catch:{ zzadu -> 0x0080 }
            int r0 = r0.errorCode     // Catch:{ zzadu -> 0x0080 }
            if (r0 == r5) goto L_0x00a7
            com.google.android.gms.internal.ads.zzaej r0 = r13.zzbzf     // Catch:{ zzadu -> 0x0080 }
            int r0 = r0.errorCode     // Catch:{ zzadu -> 0x0080 }
            if (r0 == r3) goto L_0x00a7
            com.google.android.gms.internal.ads.zzadu r0 = new com.google.android.gms.internal.ads.zzadu     // Catch:{ zzadu -> 0x0080 }
            com.google.android.gms.internal.ads.zzaej r1 = r13.zzbzf     // Catch:{ zzadu -> 0x0080 }
            int r1 = r1.errorCode     // Catch:{ zzadu -> 0x0080 }
            r2 = 66
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ zzadu -> 0x0080 }
            r3.<init>(r2)     // Catch:{ zzadu -> 0x0080 }
            java.lang.String r2 = "There was a problem getting an ad response. ErrorCode: "
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ zzadu -> 0x0080 }
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ zzadu -> 0x0080 }
            java.lang.String r1 = r1.toString()     // Catch:{ zzadu -> 0x0080 }
            com.google.android.gms.internal.ads.zzaej r2 = r13.zzbzf     // Catch:{ zzadu -> 0x0080 }
            int r2 = r2.errorCode     // Catch:{ zzadu -> 0x0080 }
            r0.<init>(r1, r2)     // Catch:{ zzadu -> 0x0080 }
            throw r0     // Catch:{ zzadu -> 0x0080 }
        L_0x0080:
            r0 = move-exception
            int r1 = r0.getErrorCode()
            java.lang.String r0 = r0.getMessage()
            r13.zzc(r1, r0)
            android.os.Handler r0 = com.google.android.gms.internal.ads.zzakk.zzcrm
            java.lang.Runnable r1 = r13.zzbzg
            r0.removeCallbacks(r1)
        L_0x0093:
            return
        L_0x0094:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0094 }
            throw r0
        L_0x0097:
            com.google.android.gms.internal.ads.zzajm r0 = com.google.android.gms.ads.internal.zzbv.zzeo()
            com.google.android.gms.internal.ads.zzakd r0 = r0.zzqh()
            com.google.android.gms.internal.ads.zzaef r2 = r13.zzbuc
            java.lang.String r2 = r2.zzacp
            r0.zzcq(r2)
            goto L_0x0051
        L_0x00a7:
            com.google.android.gms.internal.ads.zzaej r0 = r13.zzbzf     // Catch:{ zzadu -> 0x0080 }
            int r0 = r0.errorCode     // Catch:{ zzadu -> 0x0080 }
            if (r0 == r3) goto L_0x0121
            com.google.android.gms.internal.ads.zzaej r0 = r13.zzbzf     // Catch:{ zzadu -> 0x0080 }
            java.lang.String r0 = r0.zzceo     // Catch:{ zzadu -> 0x0080 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ zzadu -> 0x0080 }
            if (r0 == 0) goto L_0x00c0
            com.google.android.gms.internal.ads.zzadu r0 = new com.google.android.gms.internal.ads.zzadu     // Catch:{ zzadu -> 0x0080 }
            java.lang.String r1 = "No fill from ad server."
            r2 = 3
            r0.<init>(r1, r2)     // Catch:{ zzadu -> 0x0080 }
            throw r0     // Catch:{ zzadu -> 0x0080 }
        L_0x00c0:
            com.google.android.gms.internal.ads.zzajm r0 = com.google.android.gms.ads.internal.zzbv.zzeo()     // Catch:{ zzadu -> 0x0080 }
            com.google.android.gms.internal.ads.zzakd r0 = r0.zzqh()     // Catch:{ zzadu -> 0x0080 }
            com.google.android.gms.internal.ads.zzaej r2 = r13.zzbzf     // Catch:{ zzadu -> 0x0080 }
            boolean r2 = r2.zzcdd     // Catch:{ zzadu -> 0x0080 }
            r0.zzab(r2)     // Catch:{ zzadu -> 0x0080 }
            com.google.android.gms.internal.ads.zzaej r0 = r13.zzbzf     // Catch:{ zzadu -> 0x0080 }
            boolean r0 = r0.zzceq     // Catch:{ zzadu -> 0x0080 }
            if (r0 == 0) goto L_0x01f3
            com.google.android.gms.internal.ads.zzwy r0 = new com.google.android.gms.internal.ads.zzwy     // Catch:{ JSONException -> 0x01cc }
            com.google.android.gms.internal.ads.zzaej r2 = r13.zzbzf     // Catch:{ JSONException -> 0x01cc }
            java.lang.String r2 = r2.zzceo     // Catch:{ JSONException -> 0x01cc }
            r0.<init>((java.lang.String) r2)     // Catch:{ JSONException -> 0x01cc }
            r13.zzbtj = r0     // Catch:{ JSONException -> 0x01cc }
            com.google.android.gms.internal.ads.zzajm r0 = com.google.android.gms.ads.internal.zzbv.zzeo()     // Catch:{ JSONException -> 0x01cc }
            com.google.android.gms.internal.ads.zzwy r2 = r13.zzbtj     // Catch:{ JSONException -> 0x01cc }
            boolean r2 = r2.zzbss     // Catch:{ JSONException -> 0x01cc }
            r0.zzaa(r2)     // Catch:{ JSONException -> 0x01cc }
        L_0x00eb:
            com.google.android.gms.internal.ads.zzaej r0 = r13.zzbzf     // Catch:{ zzadu -> 0x0080 }
            java.lang.String r0 = r0.zzcds     // Catch:{ zzadu -> 0x0080 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ zzadu -> 0x0080 }
            if (r0 != 0) goto L_0x0121
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzbdj     // Catch:{ zzadu -> 0x0080 }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ zzadu -> 0x0080 }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ zzadu -> 0x0080 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ zzadu -> 0x0080 }
            boolean r0 = r0.booleanValue()     // Catch:{ zzadu -> 0x0080 }
            if (r0 == 0) goto L_0x0121
            java.lang.String r0 = "Received cookie from server. Setting webview cookie in CookieManager."
            com.google.android.gms.internal.ads.zzakb.zzck(r0)     // Catch:{ zzadu -> 0x0080 }
            com.google.android.gms.internal.ads.zzakq r0 = com.google.android.gms.ads.internal.zzbv.zzem()     // Catch:{ zzadu -> 0x0080 }
            android.content.Context r2 = r13.mContext     // Catch:{ zzadu -> 0x0080 }
            android.webkit.CookieManager r0 = r0.zzax(r2)     // Catch:{ zzadu -> 0x0080 }
            if (r0 == 0) goto L_0x0121
            java.lang.String r2 = "googleads.g.doubleclick.net"
            com.google.android.gms.internal.ads.zzaej r3 = r13.zzbzf     // Catch:{ zzadu -> 0x0080 }
            java.lang.String r3 = r3.zzcds     // Catch:{ zzadu -> 0x0080 }
            r0.setCookie(r2, r3)     // Catch:{ zzadu -> 0x0080 }
        L_0x0121:
            com.google.android.gms.internal.ads.zzaef r0 = r13.zzbuc     // Catch:{ zzadu -> 0x0080 }
            com.google.android.gms.internal.ads.zzjn r0 = r0.zzacv     // Catch:{ zzadu -> 0x0080 }
            com.google.android.gms.internal.ads.zzjn[] r0 = r0.zzard     // Catch:{ zzadu -> 0x0080 }
            if (r0 == 0) goto L_0x0223
            com.google.android.gms.internal.ads.zzaef r0 = r13.zzbuc     // Catch:{ zzadu -> 0x0080 }
            com.google.android.gms.internal.ads.zzjn r4 = r13.zza((com.google.android.gms.internal.ads.zzaef) r0)     // Catch:{ zzadu -> 0x0080 }
        L_0x012f:
            com.google.android.gms.internal.ads.zzajm r0 = com.google.android.gms.ads.internal.zzbv.zzeo()
            com.google.android.gms.internal.ads.zzakd r0 = r0.zzqh()
            com.google.android.gms.internal.ads.zzaej r2 = r13.zzbzf
            boolean r2 = r2.zzcfa
            r0.zzac(r2)
            com.google.android.gms.internal.ads.zzajm r0 = com.google.android.gms.ads.internal.zzbv.zzeo()
            com.google.android.gms.internal.ads.zzakd r0 = r0.zzqh()
            com.google.android.gms.internal.ads.zzaej r2 = r13.zzbzf
            boolean r2 = r2.zzcfm
            r0.zzad(r2)
            com.google.android.gms.internal.ads.zzaej r0 = r13.zzbzf
            java.lang.String r0 = r0.zzcey
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0206
            org.json.JSONObject r10 = new org.json.JSONObject     // Catch:{ Exception -> 0x0200 }
            com.google.android.gms.internal.ads.zzaej r0 = r13.zzbzf     // Catch:{ Exception -> 0x0200 }
            java.lang.String r0 = r0.zzcey     // Catch:{ Exception -> 0x0200 }
            r10.<init>(r0)     // Catch:{ Exception -> 0x0200 }
        L_0x0160:
            com.google.android.gms.internal.ads.zzaej r0 = r13.zzbzf
            int r0 = r0.zzcfo
            r2 = 2
            if (r0 != r2) goto L_0x0191
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r8)
            com.google.android.gms.internal.ads.zzaef r0 = r13.zzbuc
            com.google.android.gms.internal.ads.zzjj r0 = r0.zzccv
            android.os.Bundle r1 = r0.zzaqg
            if (r1 == 0) goto L_0x0209
            android.os.Bundle r0 = r0.zzaqg
        L_0x0175:
            java.lang.Class<com.google.ads.mediation.admob.AdMobAdapter> r1 = com.google.ads.mediation.admob.AdMobAdapter.class
            java.lang.String r1 = r1.getName()
            android.os.Bundle r1 = r0.getBundle(r1)
            if (r1 == 0) goto L_0x0210
            java.lang.Class<com.google.ads.mediation.admob.AdMobAdapter> r1 = com.google.ads.mediation.admob.AdMobAdapter.class
            java.lang.String r1 = r1.getName()
            android.os.Bundle r0 = r0.getBundle(r1)
        L_0x018b:
            java.lang.String r1 = "render_test_label"
            r0.putBoolean(r1, r8)
            r1 = r2
        L_0x0191:
            com.google.android.gms.internal.ads.zzaej r0 = r13.zzbzf
            int r0 = r0.zzcfo
            if (r0 != r8) goto L_0x019b
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r9)
        L_0x019b:
            com.google.android.gms.internal.ads.zzaej r0 = r13.zzbzf
            int r0 = r0.zzcfo
            if (r0 != 0) goto L_0x0221
            com.google.android.gms.internal.ads.zzaef r0 = r13.zzbuc
            com.google.android.gms.internal.ads.zzjj r0 = r0.zzccv
            boolean r0 = com.google.android.gms.internal.ads.zzamm.zzo(r0)
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r0)
        L_0x01ad:
            com.google.android.gms.internal.ads.zzaji r0 = new com.google.android.gms.internal.ads.zzaji
            com.google.android.gms.internal.ads.zzaef r1 = r13.zzbuc
            com.google.android.gms.internal.ads.zzaej r2 = r13.zzbzf
            com.google.android.gms.internal.ads.zzwy r3 = r13.zzbtj
            com.google.android.gms.internal.ads.zzaej r8 = r13.zzbzf
            long r8 = r8.zzceu
            com.google.android.gms.internal.ads.zzhs r11 = r13.zzcch
            r0.<init>(r1, r2, r3, r4, r5, r6, r8, r10, r11, r12)
            com.google.android.gms.internal.ads.zzadj r1 = r13.zzccf
            r1.zza(r0)
            android.os.Handler r0 = com.google.android.gms.internal.ads.zzakk.zzcrm
            java.lang.Runnable r1 = r13.zzbzg
            r0.removeCallbacks(r1)
            goto L_0x0093
        L_0x01cc:
            r0 = move-exception
            java.lang.String r1 = "Could not parse mediation config."
            com.google.android.gms.internal.ads.zzakb.zzb(r1, r0)     // Catch:{ zzadu -> 0x0080 }
            com.google.android.gms.internal.ads.zzadu r1 = new com.google.android.gms.internal.ads.zzadu     // Catch:{ zzadu -> 0x0080 }
            java.lang.String r2 = "Could not parse mediation config: "
            com.google.android.gms.internal.ads.zzaej r0 = r13.zzbzf     // Catch:{ zzadu -> 0x0080 }
            java.lang.String r0 = r0.zzceo     // Catch:{ zzadu -> 0x0080 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ zzadu -> 0x0080 }
            int r3 = r0.length()     // Catch:{ zzadu -> 0x0080 }
            if (r3 == 0) goto L_0x01ed
            java.lang.String r0 = r2.concat(r0)     // Catch:{ zzadu -> 0x0080 }
        L_0x01e8:
            r2 = 0
            r1.<init>(r0, r2)     // Catch:{ zzadu -> 0x0080 }
            throw r1     // Catch:{ zzadu -> 0x0080 }
        L_0x01ed:
            java.lang.String r0 = new java.lang.String     // Catch:{ zzadu -> 0x0080 }
            r0.<init>(r2)     // Catch:{ zzadu -> 0x0080 }
            goto L_0x01e8
        L_0x01f3:
            com.google.android.gms.internal.ads.zzajm r0 = com.google.android.gms.ads.internal.zzbv.zzeo()     // Catch:{ zzadu -> 0x0080 }
            com.google.android.gms.internal.ads.zzaej r2 = r13.zzbzf     // Catch:{ zzadu -> 0x0080 }
            boolean r2 = r2.zzbss     // Catch:{ zzadu -> 0x0080 }
            r0.zzaa(r2)     // Catch:{ zzadu -> 0x0080 }
            goto L_0x00eb
        L_0x0200:
            r0 = move-exception
            java.lang.String r2 = "Error parsing the JSON for Active View."
            com.google.android.gms.internal.ads.zzakb.zzb(r2, r0)
        L_0x0206:
            r10 = r1
            goto L_0x0160
        L_0x0209:
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            goto L_0x0175
        L_0x0210:
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.lang.Class<com.google.ads.mediation.admob.AdMobAdapter> r3 = com.google.ads.mediation.admob.AdMobAdapter.class
            java.lang.String r3 = r3.getName()
            r0.putBundle(r3, r1)
            r0 = r1
            goto L_0x018b
        L_0x0221:
            r12 = r1
            goto L_0x01ad
        L_0x0223:
            r4 = r1
            goto L_0x012f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzadk.zza(com.google.android.gms.internal.ads.zzaej):void");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzii zzii) {
        zzii.zzanm.zzamu = this.zzccg.zzccw.packageName;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzii zzii) {
        zzii.zzanh = this.zzccg.zzcdi;
    }

    public final void zzdn() {
        String string;
        zzakb.zzck("AdLoaderBackgroundTask started.");
        this.zzbzg = new zzads(this);
        zzakk.zzcrm.postDelayed(this.zzbzg, ((Long) zzkb.zzik().zzd(zznk.zzban)).longValue());
        long elapsedRealtime = zzbv.zzer().elapsedRealtime();
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzbak)).booleanValue() || this.zzccg.zzccv.extras == null || (string = this.zzccg.zzccv.extras.getString("_ad")) == null) {
            zzaop zzaop = new zzaop();
            zzaki.zzb(new zzadt(this, zzaop));
            String zzz = zzbv.zzfh().zzz(this.mContext);
            String zzaa = zzbv.zzfh().zzaa(this.mContext);
            String zzab = zzbv.zzfh().zzab(this.mContext);
            zzbv.zzfh().zzg(this.mContext, zzab);
            this.zzbuc = new zzaef(this.zzccg, elapsedRealtime, zzz, zzaa, zzab);
            zzaop.zzk(this.zzbuc);
            return;
        }
        this.zzbuc = new zzaef(this.zzccg, elapsedRealtime, (String) null, (String) null, (String) null);
        zza(zzafs.zza(this.mContext, this.zzbuc, string));
    }
}
