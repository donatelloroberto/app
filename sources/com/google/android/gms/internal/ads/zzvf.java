package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzvf {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock;
    private final String zzbpx;
    /* access modifiers changed from: private */
    public zzalo<zzuu> zzbpy;
    private zzalo<zzuu> zzbpz;
    /* access modifiers changed from: private */
    @Nullable
    public zzvw zzbqa;
    /* access modifiers changed from: private */
    public int zzbqb;
    private final zzang zzyf;

    public zzvf(Context context, zzang zzang, String str) {
        this.mLock = new Object();
        this.zzbqb = 1;
        this.zzbpx = str;
        this.mContext = context.getApplicationContext();
        this.zzyf = zzang;
        this.zzbpy = new zzvr();
        this.zzbpz = new zzvr();
    }

    public zzvf(Context context, zzang zzang, String str, zzalo<zzuu> zzalo, zzalo<zzuu> zzalo2) {
        this(context, zzang, str);
        this.zzbpy = zzalo;
        this.zzbpz = zzalo2;
    }

    /* access modifiers changed from: protected */
    public final zzvw zza(@Nullable zzci zzci) {
        zzvw zzvw = new zzvw(this.zzbpz);
        zzaoe.zzcvy.execute(new zzvg(this, zzci, zzvw));
        zzvw.zza(new zzvo(this, zzvw), new zzvp(this, zzvw));
        return zzvw;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzci zzci, zzvw zzvw) {
        try {
            Context context = this.mContext;
            zzang zzang = this.zzyf;
            zzuu zzuf = ((Boolean) zzkb.zzik().zzd(zznk.zzaxz)).booleanValue() ? new zzuf(context, zzang) : new zzuw(context, zzang, zzci, (zzw) null);
            zzuf.zza(new zzvh(this, zzvw, zzuf));
            zzuf.zza("/jsLoaded", new zzvk(this, zzvw, zzuf));
            zzamk zzamk = new zzamk();
            zzvl zzvl = new zzvl(this, zzci, zzuf, zzamk);
            zzamk.set(zzvl);
            zzuf.zza("/requestReload", zzvl);
            if (this.zzbpx.endsWith(".js")) {
                zzuf.zzbb(this.zzbpx);
            } else if (this.zzbpx.startsWith("<html>")) {
                zzuf.zzbc(this.zzbpx);
            } else {
                zzuf.zzbd(this.zzbpx);
            }
            zzakk.zzcrm.postDelayed(new zzvm(this, zzvw, zzuf), (long) zzvq.zzbqo);
        } catch (Throwable th) {
            zzakb.zzb("Error creating webview.", th);
            zzbv.zzeo().zza(th, "SdkJavascriptFactory.loadJavascriptEngine");
            zzvw.reject();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void zza(com.google.android.gms.internal.ads.zzvw r4, com.google.android.gms.internal.ads.zzuu r5) {
        /*
            r3 = this;
            java.lang.Object r1 = r3.mLock
            monitor-enter(r1)
            int r0 = r4.getStatus()     // Catch:{ all -> 0x0029 }
            r2 = -1
            if (r0 == r2) goto L_0x0011
            int r0 = r4.getStatus()     // Catch:{ all -> 0x0029 }
            r2 = 1
            if (r0 != r2) goto L_0x0013
        L_0x0011:
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
        L_0x0012:
            return
        L_0x0013:
            r4.reject()     // Catch:{ all -> 0x0029 }
            java.util.concurrent.Executor r0 = com.google.android.gms.internal.ads.zzaoe.zzcvy     // Catch:{ all -> 0x0029 }
            r5.getClass()     // Catch:{ all -> 0x0029 }
            java.lang.Runnable r2 = com.google.android.gms.internal.ads.zzvj.zza(r5)     // Catch:{ all -> 0x0029 }
            r0.execute(r2)     // Catch:{ all -> 0x0029 }
            java.lang.String r0 = "Could not receive loaded message in a timely manner. Rejecting."
            com.google.android.gms.internal.ads.zzakb.v(r0)     // Catch:{ all -> 0x0029 }
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            goto L_0x0012
        L_0x0029:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzvf.zza(com.google.android.gms.internal.ads.zzvw, com.google.android.gms.internal.ads.zzuu):void");
    }

    public final zzvs zzb(@Nullable zzci zzci) {
        zzvs zzvs;
        synchronized (this.mLock) {
            if (this.zzbqa == null || this.zzbqa.getStatus() == -1) {
                this.zzbqb = 2;
                this.zzbqa = zza((zzci) null);
                zzvs = this.zzbqa.zzlz();
            } else if (this.zzbqb == 0) {
                zzvs = this.zzbqa.zzlz();
            } else if (this.zzbqb == 1) {
                this.zzbqb = 2;
                zza((zzci) null);
                zzvs = this.zzbqa.zzlz();
            } else {
                zzvs = this.zzbqb == 2 ? this.zzbqa.zzlz() : this.zzbqa.zzlz();
            }
        }
        return zzvs;
    }
}
