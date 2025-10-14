package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

@TargetApi(14)
final class zzgh implements Application.ActivityLifecycleCallbacks {
    @Nullable
    private Activity mActivity;
    private Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    public boolean zzahr = true;
    /* access modifiers changed from: private */
    public boolean zzahs = false;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final List<zzgj> zzaht = new ArrayList();
    @GuardedBy("mLock")
    private final List<zzgw> zzahu = new ArrayList();
    private Runnable zzahv;
    private long zzahw;
    private boolean zzzv = false;

    zzgh() {
    }

    private final void setActivity(Activity activity) {
        synchronized (this.mLock) {
            if (!activity.getClass().getName().startsWith("com.google.android.gms.ads")) {
                this.mActivity = activity;
            }
        }
    }

    @Nullable
    public final Activity getActivity() {
        return this.mActivity;
    }

    @Nullable
    public final Context getContext() {
        return this.mContext;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onActivityDestroyed(android.app.Activity r6) {
        /*
            r5 = this;
            java.lang.Object r1 = r5.mLock
            monitor-enter(r1)
            android.app.Activity r0 = r5.mActivity     // Catch:{ all -> 0x0040 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r1)     // Catch:{ all -> 0x0040 }
        L_0x0008:
            return
        L_0x0009:
            android.app.Activity r0 = r5.mActivity     // Catch:{ all -> 0x0040 }
            boolean r0 = r0.equals(r6)     // Catch:{ all -> 0x0040 }
            if (r0 == 0) goto L_0x0014
            r0 = 0
            r5.mActivity = r0     // Catch:{ all -> 0x0040 }
        L_0x0014:
            java.util.List<com.google.android.gms.internal.ads.zzgw> r0 = r5.zzahu     // Catch:{ all -> 0x0040 }
            java.util.Iterator r2 = r0.iterator()     // Catch:{ all -> 0x0040 }
        L_0x001a:
            boolean r0 = r2.hasNext()     // Catch:{ all -> 0x0040 }
            if (r0 == 0) goto L_0x0043
            java.lang.Object r0 = r2.next()     // Catch:{ all -> 0x0040 }
            com.google.android.gms.internal.ads.zzgw r0 = (com.google.android.gms.internal.ads.zzgw) r0     // Catch:{ all -> 0x0040 }
            boolean r0 = r0.zza(r6)     // Catch:{ Exception -> 0x0030 }
            if (r0 == 0) goto L_0x001a
            r2.remove()     // Catch:{ Exception -> 0x0030 }
            goto L_0x001a
        L_0x0030:
            r0 = move-exception
            com.google.android.gms.internal.ads.zzajm r3 = com.google.android.gms.ads.internal.zzbv.zzeo()     // Catch:{ all -> 0x0040 }
            java.lang.String r4 = "AppActivityTracker.ActivityListener.onActivityDestroyed"
            r3.zza(r0, r4)     // Catch:{ all -> 0x0040 }
            java.lang.String r3 = ""
            com.google.android.gms.internal.ads.zzane.zzb(r3, r0)     // Catch:{ all -> 0x0040 }
            goto L_0x001a
        L_0x0040:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0040 }
            throw r0
        L_0x0043:
            monitor-exit(r1)     // Catch:{ all -> 0x0040 }
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgh.onActivityDestroyed(android.app.Activity):void");
    }

    public final void onActivityPaused(Activity activity) {
        setActivity(activity);
        synchronized (this.mLock) {
            for (zzgw onActivityPaused : this.zzahu) {
                try {
                    onActivityPaused.onActivityPaused(activity);
                } catch (Exception e) {
                    zzbv.zzeo().zza(e, "AppActivityTracker.ActivityListener.onActivityPaused");
                    zzane.zzb("", e);
                }
            }
        }
        this.zzahs = true;
        if (this.zzahv != null) {
            zzakk.zzcrm.removeCallbacks(this.zzahv);
        }
        Handler handler = zzakk.zzcrm;
        zzgi zzgi = new zzgi(this);
        this.zzahv = zzgi;
        handler.postDelayed(zzgi, this.zzahw);
    }

    public final void onActivityResumed(Activity activity) {
        boolean z = false;
        setActivity(activity);
        this.zzahs = false;
        if (!this.zzahr) {
            z = true;
        }
        this.zzahr = true;
        if (this.zzahv != null) {
            zzakk.zzcrm.removeCallbacks(this.zzahv);
        }
        synchronized (this.mLock) {
            for (zzgw onActivityResumed : this.zzahu) {
                try {
                    onActivityResumed.onActivityResumed(activity);
                } catch (Exception e) {
                    zzbv.zzeo().zza(e, "AppActivityTracker.ActivityListener.onActivityResumed");
                    zzane.zzb("", e);
                }
            }
            if (z) {
                for (zzgj zzh : this.zzaht) {
                    try {
                        zzh.zzh(true);
                    } catch (Exception e2) {
                        zzane.zzb("", e2);
                    }
                }
            } else {
                zzakb.zzck("App is still foreground.");
            }
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
        setActivity(activity);
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void zza(Application application, Context context) {
        if (!this.zzzv) {
            application.registerActivityLifecycleCallbacks(this);
            if (context instanceof Activity) {
                setActivity((Activity) context);
            }
            this.mContext = application;
            this.zzahw = ((Long) zzkb.zzik().zzd(zznk.zzayh)).longValue();
            this.zzzv = true;
        }
    }

    public final void zza(zzgj zzgj) {
        synchronized (this.mLock) {
            this.zzaht.add(zzgj);
        }
    }
}
