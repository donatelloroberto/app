package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzmb {
    private static final Object sLock = new Object();
    @GuardedBy("sLock")
    private static zzmb zzate;
    private zzlj zzatf;
    private RewardedVideoAd zzatg;

    private zzmb() {
    }

    public static zzmb zziv() {
        zzmb zzmb;
        synchronized (sLock) {
            if (zzate == null) {
                zzate = new zzmb();
            }
            zzmb = zzate;
        }
        return zzmb;
    }

    public final RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        RewardedVideoAd rewardedVideoAd;
        synchronized (sLock) {
            if (this.zzatg != null) {
                rewardedVideoAd = this.zzatg;
            } else {
                this.zzatg = new zzahm(context, (zzagz) zzjr.zza(context, false, new zzjz(zzkb.zzig(), context, new zzxm())));
                rewardedVideoAd = this.zzatg;
            }
        }
        return rewardedVideoAd;
    }

    public final void openDebugMenu(Context context, String str) {
        Preconditions.checkState(this.zzatf != null, "MobileAds.initialize() must be called prior to opening debug menu.");
        try {
            this.zzatf.zzb(ObjectWrapper.wrap(context), str);
        } catch (RemoteException e) {
            zzane.zzb("Unable to open debug menu.", e);
        }
    }

    public final void setAppMuted(boolean z) {
        Preconditions.checkState(this.zzatf != null, "MobileAds.initialize() must be called prior to setting app muted state.");
        try {
            this.zzatf.setAppMuted(z);
        } catch (RemoteException e) {
            zzane.zzb("Unable to set app mute state.", e);
        }
    }

    public final void setAppVolume(float f) {
        boolean z = true;
        Preconditions.checkArgument(0.0f <= f && f <= 1.0f, "The app volume must be a value between 0 and 1 inclusive.");
        if (this.zzatf == null) {
            z = false;
        }
        Preconditions.checkState(z, "MobileAds.initialize() must be called prior to setting the app volume.");
        try {
            this.zzatf.setAppVolume(f);
        } catch (RemoteException e) {
            zzane.zzb("Unable to set app volume.", e);
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(android.content.Context r4, java.lang.String r5, com.google.android.gms.internal.ads.zzmd r6) {
        /*
            r3 = this;
            java.lang.Object r1 = sLock
            monitor-enter(r1)
            com.google.android.gms.internal.ads.zzlj r0 = r3.zzatf     // Catch:{ all -> 0x0013 }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r1)     // Catch:{ all -> 0x0013 }
        L_0x0008:
            return
        L_0x0009:
            if (r4 != 0) goto L_0x0016
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0013 }
            java.lang.String r2 = "Context cannot be null."
            r0.<init>(r2)     // Catch:{ all -> 0x0013 }
            throw r0     // Catch:{ all -> 0x0013 }
        L_0x0013:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0013 }
            throw r0
        L_0x0016:
            com.google.android.gms.internal.ads.zzjr r0 = com.google.android.gms.internal.ads.zzkb.zzig()     // Catch:{ RemoteException -> 0x003f }
            com.google.android.gms.internal.ads.zzjw r2 = new com.google.android.gms.internal.ads.zzjw     // Catch:{ RemoteException -> 0x003f }
            r2.<init>(r0, r4)     // Catch:{ RemoteException -> 0x003f }
            r0 = 0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzjr.zza((android.content.Context) r4, (boolean) r0, r2)     // Catch:{ RemoteException -> 0x003f }
            com.google.android.gms.internal.ads.zzlj r0 = (com.google.android.gms.internal.ads.zzlj) r0     // Catch:{ RemoteException -> 0x003f }
            r3.zzatf = r0     // Catch:{ RemoteException -> 0x003f }
            com.google.android.gms.internal.ads.zzlj r0 = r3.zzatf     // Catch:{ RemoteException -> 0x003f }
            r0.zza()     // Catch:{ RemoteException -> 0x003f }
            if (r5 == 0) goto L_0x003d
            com.google.android.gms.internal.ads.zzlj r0 = r3.zzatf     // Catch:{ RemoteException -> 0x003f }
            com.google.android.gms.internal.ads.zzmc r2 = new com.google.android.gms.internal.ads.zzmc     // Catch:{ RemoteException -> 0x003f }
            r2.<init>(r3, r4)     // Catch:{ RemoteException -> 0x003f }
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r2)     // Catch:{ RemoteException -> 0x003f }
            r0.zza(r5, r2)     // Catch:{ RemoteException -> 0x003f }
        L_0x003d:
            monitor-exit(r1)     // Catch:{ all -> 0x0013 }
            goto L_0x0008
        L_0x003f:
            r0 = move-exception
            java.lang.String r2 = "MobileAdsSettingManager initialization failed"
            com.google.android.gms.internal.ads.zzane.zzc(r2, r0)     // Catch:{ all -> 0x0013 }
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzmb.zza(android.content.Context, java.lang.String, com.google.android.gms.internal.ads.zzmd):void");
    }

    public final float zzdo() {
        if (this.zzatf == null) {
            return 1.0f;
        }
        try {
            return this.zzatf.zzdo();
        } catch (RemoteException e) {
            zzane.zzb("Unable to get app volume.", e);
            return 1.0f;
        }
    }

    public final boolean zzdp() {
        if (this.zzatf == null) {
            return false;
        }
        try {
            return this.zzatf.zzdp();
        } catch (RemoteException e) {
            zzane.zzb("Unable to get app mute state.", e);
            return false;
        }
    }
}
