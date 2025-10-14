package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaal;
import com.google.android.gms.internal.ads.zzaaq;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzakq;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import java.util.Collections;
import java.util.Map;

@zzadh
public class zzd extends zzaaq implements zzw {
    @VisibleForTesting
    private static final int zzbxm = Color.argb(0, 0, 0, 0);
    protected final Activity mActivity;
    @VisibleForTesting
    zzaqw zzbnd;
    @VisibleForTesting
    AdOverlayInfoParcel zzbxn;
    @VisibleForTesting
    private zzi zzbxo;
    @VisibleForTesting
    private zzo zzbxp;
    @VisibleForTesting
    private boolean zzbxq = false;
    @VisibleForTesting
    private FrameLayout zzbxr;
    @VisibleForTesting
    private WebChromeClient.CustomViewCallback zzbxs;
    @VisibleForTesting
    private boolean zzbxt = false;
    @VisibleForTesting
    private boolean zzbxu = false;
    @VisibleForTesting
    private zzh zzbxv;
    @VisibleForTesting
    private boolean zzbxw = false;
    @VisibleForTesting
    int zzbxx = 0;
    private final Object zzbxy = new Object();
    private Runnable zzbxz;
    private boolean zzbya;
    private boolean zzbyb;
    private boolean zzbyc = false;
    private boolean zzbyd = false;
    private boolean zzbye = true;

    public zzd(Activity activity) {
        this.mActivity = activity;
    }

    private final void zznl() {
        if (this.mActivity.isFinishing() && !this.zzbyc) {
            this.zzbyc = true;
            if (this.zzbnd != null) {
                this.zzbnd.zzai(this.zzbxx);
                synchronized (this.zzbxy) {
                    if (!this.zzbya && this.zzbnd.zzun()) {
                        this.zzbxz = new zzf(this);
                        zzakk.zzcrm.postDelayed(this.zzbxz, ((Long) zzkb.zzik().zzd(zznk.zzayq)).longValue());
                        return;
                    }
                }
            }
            zznm();
        }
    }

    private final void zzno() {
        this.zzbnd.zzno();
    }

    private final void zzs(boolean z) {
        int intValue = ((Integer) zzkb.zzik().zzd(zznk.zzben)).intValue();
        zzp zzp = new zzp();
        zzp.size = 50;
        zzp.paddingLeft = z ? intValue : 0;
        zzp.paddingRight = z ? 0 : intValue;
        zzp.paddingTop = 0;
        zzp.paddingBottom = intValue;
        this.zzbxp = new zzo(this.mActivity, zzp, this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        zza(z, this.zzbxn.zzbyr);
        this.zzbxv.addView(this.zzbxp, layoutParams);
    }

    /* JADX WARNING: Removed duplicated region for block: B:111:0x02f2  */
    /* JADX WARNING: Removed duplicated region for block: B:114:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0143 A[SYNTHETIC, Splitter:B:51:0x0143] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0234  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0266  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x026d  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0270  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0273  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x02a3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzt(boolean r18) throws com.google.android.gms.ads.internal.overlay.zzg {
        /*
            r17 = this;
            r0 = r17
            boolean r1 = r0.zzbyb
            if (r1 != 0) goto L_0x000e
            r0 = r17
            android.app.Activity r1 = r0.mActivity
            r2 = 1
            r1.requestWindowFeature(r2)
        L_0x000e:
            r0 = r17
            android.app.Activity r1 = r0.mActivity
            android.view.Window r3 = r1.getWindow()
            if (r3 != 0) goto L_0x0020
            com.google.android.gms.ads.internal.overlay.zzg r1 = new com.google.android.gms.ads.internal.overlay.zzg
            java.lang.String r2 = "Invalid activity, no window available."
            r1.<init>(r2)
            throw r1
        L_0x0020:
            r2 = 1
            boolean r1 = com.google.android.gms.common.util.PlatformVersion.isAtLeastN()
            if (r1 == 0) goto L_0x0309
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r1 = com.google.android.gms.internal.ads.zznk.zzbel
            com.google.android.gms.internal.ads.zzni r4 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r1 = r4.zzd(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0309
            com.google.android.gms.ads.internal.zzbv.zzek()
            r0 = r17
            android.app.Activity r1 = r0.mActivity
            r0 = r17
            android.app.Activity r2 = r0.mActivity
            android.content.res.Resources r2 = r2.getResources()
            android.content.res.Configuration r2 = r2.getConfiguration()
            boolean r1 = com.google.android.gms.internal.ads.zzakk.zza((android.app.Activity) r1, (android.content.res.Configuration) r2)
        L_0x0050:
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzbxn
            com.google.android.gms.ads.internal.zzaq r2 = r2.zzbyw
            if (r2 == 0) goto L_0x026d
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzbxn
            com.google.android.gms.ads.internal.zzaq r2 = r2.zzbyw
            boolean r2 = r2.zzzf
            if (r2 == 0) goto L_0x026d
            r2 = 1
        L_0x0063:
            r0 = r17
            boolean r4 = r0.zzbxu
            if (r4 == 0) goto L_0x006b
            if (r2 == 0) goto L_0x00a7
        L_0x006b:
            if (r1 == 0) goto L_0x00a7
            r1 = 1024(0x400, float:1.435E-42)
            r2 = 1024(0x400, float:1.435E-42)
            r3.setFlags(r1, r2)
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r1 = com.google.android.gms.internal.ads.zznk.zzayr
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r1 = r2.zzd(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x00a7
            boolean r1 = com.google.android.gms.common.util.PlatformVersion.isAtLeastKitKat()
            if (r1 == 0) goto L_0x00a7
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            com.google.android.gms.ads.internal.zzaq r1 = r1.zzbyw
            if (r1 == 0) goto L_0x00a7
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            com.google.android.gms.ads.internal.zzaq r1 = r1.zzbyw
            boolean r1 = r1.zzzk
            if (r1 == 0) goto L_0x00a7
            android.view.View r1 = r3.getDecorView()
            r2 = 4098(0x1002, float:5.743E-42)
            r1.setSystemUiVisibility(r2)
        L_0x00a7:
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            com.google.android.gms.internal.ads.zzaqw r1 = r1.zzbyo
            if (r1 == 0) goto L_0x0270
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            com.google.android.gms.internal.ads.zzaqw r1 = r1.zzbyo
            com.google.android.gms.internal.ads.zzasc r1 = r1.zzuf()
        L_0x00b9:
            if (r1 == 0) goto L_0x0273
            boolean r5 = r1.zzfz()
        L_0x00bf:
            r1 = 0
            r0 = r17
            r0.zzbxw = r1
            if (r5 == 0) goto L_0x00ec
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            int r1 = r1.orientation
            com.google.android.gms.internal.ads.zzakq r2 = com.google.android.gms.ads.internal.zzbv.zzem()
            int r2 = r2.zzrl()
            if (r1 != r2) goto L_0x0279
            r0 = r17
            android.app.Activity r1 = r0.mActivity
            android.content.res.Resources r1 = r1.getResources()
            android.content.res.Configuration r1 = r1.getConfiguration()
            int r1 = r1.orientation
            r2 = 1
            if (r1 != r2) goto L_0x0276
            r1 = 1
        L_0x00e8:
            r0 = r17
            r0.zzbxw = r1
        L_0x00ec:
            r0 = r17
            boolean r1 = r0.zzbxw
            r2 = 46
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r2)
            java.lang.String r2 = "Delay onShow to next orientation change: "
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            com.google.android.gms.internal.ads.zzakb.zzck(r1)
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            int r1 = r1.orientation
            r0 = r17
            r0.setRequestedOrientation(r1)
            com.google.android.gms.internal.ads.zzakq r1 = com.google.android.gms.ads.internal.zzbv.zzem()
            boolean r1 = r1.zza((android.view.Window) r3)
            if (r1 == 0) goto L_0x0122
            java.lang.String r1 = "Hardware acceleration on the AdActivity window enabled."
            com.google.android.gms.internal.ads.zzakb.zzck(r1)
        L_0x0122:
            r0 = r17
            boolean r1 = r0.zzbxu
            if (r1 != 0) goto L_0x02a3
            r0 = r17
            com.google.android.gms.ads.internal.overlay.zzh r1 = r0.zzbxv
            r2 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r1.setBackgroundColor(r2)
        L_0x0131:
            r0 = r17
            android.app.Activity r1 = r0.mActivity
            r0 = r17
            com.google.android.gms.ads.internal.overlay.zzh r2 = r0.zzbxv
            r1.setContentView(r2)
            r1 = 1
            r0 = r17
            r0.zzbyb = r1
            if (r18 == 0) goto L_0x02f2
            com.google.android.gms.ads.internal.zzbv.zzel()     // Catch:{ Exception -> 0x02b7 }
            r0 = r17
            android.app.Activity r1 = r0.mActivity     // Catch:{ Exception -> 0x02b7 }
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzbxn     // Catch:{ Exception -> 0x02b7 }
            com.google.android.gms.internal.ads.zzaqw r2 = r2.zzbyo     // Catch:{ Exception -> 0x02b7 }
            if (r2 == 0) goto L_0x02ae
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzbxn     // Catch:{ Exception -> 0x02b7 }
            com.google.android.gms.internal.ads.zzaqw r2 = r2.zzbyo     // Catch:{ Exception -> 0x02b7 }
            com.google.android.gms.internal.ads.zzasi r2 = r2.zzud()     // Catch:{ Exception -> 0x02b7 }
        L_0x015c:
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r0.zzbxn     // Catch:{ Exception -> 0x02b7 }
            com.google.android.gms.internal.ads.zzaqw r3 = r3.zzbyo     // Catch:{ Exception -> 0x02b7 }
            if (r3 == 0) goto L_0x02b1
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r0.zzbxn     // Catch:{ Exception -> 0x02b7 }
            com.google.android.gms.internal.ads.zzaqw r3 = r3.zzbyo     // Catch:{ Exception -> 0x02b7 }
            java.lang.String r3 = r3.zzue()     // Catch:{ Exception -> 0x02b7 }
        L_0x016e:
            r4 = 1
            r6 = 0
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r7 = r0.zzbxn     // Catch:{ Exception -> 0x02b7 }
            com.google.android.gms.internal.ads.zzang r7 = r7.zzacr     // Catch:{ Exception -> 0x02b7 }
            r8 = 0
            r9 = 0
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r10 = r0.zzbxn     // Catch:{ Exception -> 0x02b7 }
            com.google.android.gms.internal.ads.zzaqw r10 = r10.zzbyo     // Catch:{ Exception -> 0x02b7 }
            if (r10 == 0) goto L_0x02b4
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r10 = r0.zzbxn     // Catch:{ Exception -> 0x02b7 }
            com.google.android.gms.internal.ads.zzaqw r10 = r10.zzbyo     // Catch:{ Exception -> 0x02b7 }
            com.google.android.gms.ads.internal.zzw r10 = r10.zzbi()     // Catch:{ Exception -> 0x02b7 }
        L_0x018a:
            com.google.android.gms.internal.ads.zzhs r11 = com.google.android.gms.internal.ads.zzhs.zzhm()     // Catch:{ Exception -> 0x02b7 }
            com.google.android.gms.internal.ads.zzaqw r1 = com.google.android.gms.internal.ads.zzarc.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ Exception -> 0x02b7 }
            r0 = r17
            r0.zzbnd = r1     // Catch:{ Exception -> 0x02b7 }
            r0 = r17
            com.google.android.gms.internal.ads.zzaqw r1 = r0.zzbnd
            com.google.android.gms.internal.ads.zzasc r6 = r1.zzuf()
            r7 = 0
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            com.google.android.gms.ads.internal.gmsg.zzb r8 = r1.zzbyx
            r9 = 0
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            com.google.android.gms.ads.internal.gmsg.zzd r10 = r1.zzbyp
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            com.google.android.gms.ads.internal.overlay.zzt r11 = r1.zzbyt
            r12 = 1
            r13 = 0
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            com.google.android.gms.internal.ads.zzaqw r1 = r1.zzbyo
            if (r1 == 0) goto L_0x02c5
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            com.google.android.gms.internal.ads.zzaqw r1 = r1.zzbyo
            com.google.android.gms.internal.ads.zzasc r1 = r1.zzuf()
            com.google.android.gms.ads.internal.zzx r14 = r1.zzut()
        L_0x01ca:
            r15 = 0
            r16 = 0
            r6.zza(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            r0 = r17
            com.google.android.gms.internal.ads.zzaqw r1 = r0.zzbnd
            com.google.android.gms.internal.ads.zzasc r1 = r1.zzuf()
            com.google.android.gms.ads.internal.overlay.zze r2 = new com.google.android.gms.ads.internal.overlay.zze
            r0 = r17
            r2.<init>(r0)
            r1.zza((com.google.android.gms.internal.ads.zzasd) r2)
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            java.lang.String r1 = r1.url
            if (r1 == 0) goto L_0x02c8
            r0 = r17
            com.google.android.gms.internal.ads.zzaqw r1 = r0.zzbnd
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzbxn
            java.lang.String r2 = r2.url
            r1.loadUrl(r2)
        L_0x01f7:
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            com.google.android.gms.internal.ads.zzaqw r1 = r1.zzbyo
            if (r1 == 0) goto L_0x020a
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            com.google.android.gms.internal.ads.zzaqw r1 = r1.zzbyo
            r0 = r17
            r1.zzb((com.google.android.gms.ads.internal.overlay.zzd) r0)
        L_0x020a:
            r0 = r17
            com.google.android.gms.internal.ads.zzaqw r1 = r0.zzbnd
            r0 = r17
            r1.zza((com.google.android.gms.ads.internal.overlay.zzd) r0)
            r0 = r17
            com.google.android.gms.internal.ads.zzaqw r1 = r0.zzbnd
            android.view.ViewParent r1 = r1.getParent()
            if (r1 == 0) goto L_0x022e
            boolean r2 = r1 instanceof android.view.ViewGroup
            if (r2 == 0) goto L_0x022e
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            r0 = r17
            com.google.android.gms.internal.ads.zzaqw r2 = r0.zzbnd
            android.view.View r2 = r2.getView()
            r1.removeView(r2)
        L_0x022e:
            r0 = r17
            boolean r1 = r0.zzbxu
            if (r1 == 0) goto L_0x023b
            r0 = r17
            com.google.android.gms.internal.ads.zzaqw r1 = r0.zzbnd
            r1.zzur()
        L_0x023b:
            r0 = r17
            com.google.android.gms.ads.internal.overlay.zzh r1 = r0.zzbxv
            r0 = r17
            com.google.android.gms.internal.ads.zzaqw r2 = r0.zzbnd
            android.view.View r2 = r2.getView()
            r3 = -1
            r4 = -1
            r1.addView(r2, r3, r4)
            if (r18 != 0) goto L_0x0257
            r0 = r17
            boolean r1 = r0.zzbxw
            if (r1 != 0) goto L_0x0257
            r17.zzno()
        L_0x0257:
            r0 = r17
            r0.zzs(r5)
            r0 = r17
            com.google.android.gms.internal.ads.zzaqw r1 = r0.zzbnd
            boolean r1 = r1.zzuh()
            if (r1 == 0) goto L_0x026c
            r1 = 1
            r0 = r17
            r0.zza((boolean) r5, (boolean) r1)
        L_0x026c:
            return
        L_0x026d:
            r2 = 0
            goto L_0x0063
        L_0x0270:
            r1 = 0
            goto L_0x00b9
        L_0x0273:
            r5 = 0
            goto L_0x00bf
        L_0x0276:
            r1 = 0
            goto L_0x00e8
        L_0x0279:
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            int r1 = r1.orientation
            com.google.android.gms.internal.ads.zzakq r2 = com.google.android.gms.ads.internal.zzbv.zzem()
            int r2 = r2.zzrm()
            if (r1 != r2) goto L_0x00ec
            r0 = r17
            android.app.Activity r1 = r0.mActivity
            android.content.res.Resources r1 = r1.getResources()
            android.content.res.Configuration r1 = r1.getConfiguration()
            int r1 = r1.orientation
            r2 = 2
            if (r1 != r2) goto L_0x02a1
            r1 = 1
        L_0x029b:
            r0 = r17
            r0.zzbxw = r1
            goto L_0x00ec
        L_0x02a1:
            r1 = 0
            goto L_0x029b
        L_0x02a3:
            r0 = r17
            com.google.android.gms.ads.internal.overlay.zzh r1 = r0.zzbxv
            int r2 = zzbxm
            r1.setBackgroundColor(r2)
            goto L_0x0131
        L_0x02ae:
            r2 = 0
            goto L_0x015c
        L_0x02b1:
            r3 = 0
            goto L_0x016e
        L_0x02b4:
            r10 = 0
            goto L_0x018a
        L_0x02b7:
            r1 = move-exception
            java.lang.String r2 = "Error obtaining webview."
            com.google.android.gms.internal.ads.zzakb.zzb(r2, r1)
            com.google.android.gms.ads.internal.overlay.zzg r1 = new com.google.android.gms.ads.internal.overlay.zzg
            java.lang.String r2 = "Could not obtain webview for the overlay."
            r1.<init>(r2)
            throw r1
        L_0x02c5:
            r14 = 0
            goto L_0x01ca
        L_0x02c8:
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            java.lang.String r1 = r1.zzbys
            if (r1 == 0) goto L_0x02ea
            r0 = r17
            com.google.android.gms.internal.ads.zzaqw r6 = r0.zzbnd
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            java.lang.String r7 = r1.zzbyq
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            java.lang.String r8 = r1.zzbys
            java.lang.String r9 = "text/html"
            java.lang.String r10 = "UTF-8"
            r11 = 0
            r6.loadDataWithBaseURL(r7, r8, r9, r10, r11)
            goto L_0x01f7
        L_0x02ea:
            com.google.android.gms.ads.internal.overlay.zzg r1 = new com.google.android.gms.ads.internal.overlay.zzg
            java.lang.String r2 = "No URL or HTML to display in ad overlay."
            r1.<init>(r2)
            throw r1
        L_0x02f2:
            r0 = r17
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r1 = r0.zzbxn
            com.google.android.gms.internal.ads.zzaqw r1 = r1.zzbyo
            r0 = r17
            r0.zzbnd = r1
            r0 = r17
            com.google.android.gms.internal.ads.zzaqw r1 = r0.zzbnd
            r0 = r17
            android.app.Activity r2 = r0.mActivity
            r1.zzbm(r2)
            goto L_0x020a
        L_0x0309:
            r1 = r2
            goto L_0x0050
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.overlay.zzd.zzt(boolean):void");
    }

    public final void close() {
        this.zzbxx = 2;
        this.mActivity.finish();
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
    }

    public final void onBackPressed() {
        this.zzbxx = 0;
    }

    public void onCreate(Bundle bundle) {
        boolean z = false;
        this.mActivity.requestWindowFeature(1);
        if (bundle != null) {
            z = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        this.zzbxt = z;
        try {
            this.zzbxn = AdOverlayInfoParcel.zzc(this.mActivity.getIntent());
            if (this.zzbxn == null) {
                throw new zzg("Could not get info for ad overlay.");
            }
            if (this.zzbxn.zzacr.zzcvf > 7500000) {
                this.zzbxx = 3;
            }
            if (this.mActivity.getIntent() != null) {
                this.zzbye = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
            }
            if (this.zzbxn.zzbyw != null) {
                this.zzbxu = this.zzbxn.zzbyw.zzze;
            } else {
                this.zzbxu = false;
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbbg)).booleanValue() && this.zzbxu && this.zzbxn.zzbyw.zzzj != -1) {
                new zzj(this, (zzf) null).zzqo();
            }
            if (bundle == null) {
                if (this.zzbxn.zzbyn != null && this.zzbye) {
                    this.zzbxn.zzbyn.zzcc();
                }
                if (!(this.zzbxn.zzbyu == 1 || this.zzbxn.zzbym == null)) {
                    this.zzbxn.zzbym.onAdClicked();
                }
            }
            this.zzbxv = new zzh(this.mActivity, this.zzbxn.zzbyv, this.zzbxn.zzacr.zzcw);
            this.zzbxv.setId(1000);
            switch (this.zzbxn.zzbyu) {
                case 1:
                    zzt(false);
                    return;
                case 2:
                    this.zzbxo = new zzi(this.zzbxn.zzbyo);
                    zzt(false);
                    return;
                case 3:
                    zzt(true);
                    return;
                default:
                    throw new zzg("Could not determine ad overlay type.");
            }
        } catch (zzg e) {
            zzakb.zzdk(e.getMessage());
            this.zzbxx = 3;
            this.mActivity.finish();
        }
    }

    public final void onDestroy() {
        if (this.zzbnd != null) {
            this.zzbxv.removeView(this.zzbnd.getView());
        }
        zznl();
    }

    public final void onPause() {
        zznh();
        if (this.zzbxn.zzbyn != null) {
            this.zzbxn.zzbyn.onPause();
        }
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzbem)).booleanValue() && this.zzbnd != null && (!this.mActivity.isFinishing() || this.zzbxo == null)) {
            zzbv.zzem();
            zzakq.zzi(this.zzbnd);
        }
        zznl();
    }

    public final void onRestart() {
    }

    public final void onResume() {
        if (this.zzbxn.zzbyn != null) {
            this.zzbxn.zzbyn.onResume();
        }
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbem)).booleanValue()) {
            return;
        }
        if (this.zzbnd == null || this.zzbnd.isDestroyed()) {
            zzakb.zzdk("The webview does not exist. Ignoring action.");
            return;
        }
        zzbv.zzem();
        zzakq.zzj(this.zzbnd);
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzbxt);
    }

    public final void onStart() {
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzbem)).booleanValue()) {
            return;
        }
        if (this.zzbnd == null || this.zzbnd.isDestroyed()) {
            zzakb.zzdk("The webview does not exist. Ignoring action.");
            return;
        }
        zzbv.zzem();
        zzakq.zzj(this.zzbnd);
    }

    public final void onStop() {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbem)).booleanValue() && this.zzbnd != null && (!this.mActivity.isFinishing() || this.zzbxo == null)) {
            zzbv.zzem();
            zzakq.zzi(this.zzbnd);
        }
        zznl();
    }

    public final void setRequestedOrientation(int i) {
        if (this.mActivity.getApplicationInfo().targetSdkVersion >= ((Integer) zzkb.zzik().zzd(zznk.zzbfs)).intValue()) {
            if (this.mActivity.getApplicationInfo().targetSdkVersion <= ((Integer) zzkb.zzik().zzd(zznk.zzbft)).intValue()) {
                if (Build.VERSION.SDK_INT >= ((Integer) zzkb.zzik().zzd(zznk.zzbfu)).intValue()) {
                    if (Build.VERSION.SDK_INT <= ((Integer) zzkb.zzik().zzd(zznk.zzbfv)).intValue()) {
                        return;
                    }
                }
            }
        }
        this.mActivity.setRequestedOrientation(i);
    }

    public final void zza(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        this.zzbxr = new FrameLayout(this.mActivity);
        this.zzbxr.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.zzbxr.addView(view, -1, -1);
        this.mActivity.setContentView(this.zzbxr);
        this.zzbyb = true;
        this.zzbxs = customViewCallback;
        this.zzbxq = true;
    }

    public final void zza(boolean z, boolean z2) {
        boolean z3 = false;
        boolean z4 = ((Boolean) zzkb.zzik().zzd(zznk.zzays)).booleanValue() && this.zzbxn != null && this.zzbxn.zzbyw != null && this.zzbxn.zzbyw.zzzl;
        boolean z5 = ((Boolean) zzkb.zzik().zzd(zznk.zzayt)).booleanValue() && this.zzbxn != null && this.zzbxn.zzbyw != null && this.zzbxn.zzbyw.zzzm;
        if (z && z2 && z4 && !z5) {
            new zzaal(this.zzbnd, "useCustomClose").zzbw("Custom close has been disabled for interstitial ads in this ad slot.");
        }
        if (this.zzbxp != null) {
            zzo zzo = this.zzbxp;
            if (z5 || (z2 && !z4)) {
                z3 = true;
            }
            zzo.zzu(z3);
        }
    }

    public final void zzax() {
        this.zzbyb = true;
    }

    public final void zznh() {
        if (this.zzbxn != null && this.zzbxq) {
            setRequestedOrientation(this.zzbxn.orientation);
        }
        if (this.zzbxr != null) {
            this.mActivity.setContentView(this.zzbxv);
            this.zzbyb = true;
            this.zzbxr.removeAllViews();
            this.zzbxr = null;
        }
        if (this.zzbxs != null) {
            this.zzbxs.onCustomViewHidden();
            this.zzbxs = null;
        }
        this.zzbxq = false;
    }

    public final void zzni() {
        this.zzbxx = 1;
        this.mActivity.finish();
    }

    public final boolean zznj() {
        this.zzbxx = 0;
        if (this.zzbnd == null) {
            return true;
        }
        boolean zzul = this.zzbnd.zzul();
        if (zzul) {
            return zzul;
        }
        this.zzbnd.zza("onbackblocked", (Map<String, ?>) Collections.emptyMap());
        return zzul;
    }

    public final void zznk() {
        this.zzbxv.removeView(this.zzbxp);
        zzs(true);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zznm() {
        if (!this.zzbyd) {
            this.zzbyd = true;
            if (this.zzbnd != null) {
                this.zzbxv.removeView(this.zzbnd.getView());
                if (this.zzbxo != null) {
                    this.zzbnd.zzbm(this.zzbxo.zzrt);
                    this.zzbnd.zzai(false);
                    this.zzbxo.parent.addView(this.zzbnd.getView(), this.zzbxo.index, this.zzbxo.zzbyi);
                    this.zzbxo = null;
                } else if (this.mActivity.getApplicationContext() != null) {
                    this.zzbnd.zzbm(this.mActivity.getApplicationContext());
                }
                this.zzbnd = null;
            }
            if (this.zzbxn != null && this.zzbxn.zzbyn != null) {
                this.zzbxn.zzbyn.zzcb();
            }
        }
    }

    public final void zznn() {
        if (this.zzbxw) {
            this.zzbxw = false;
            zzno();
        }
    }

    public final void zznp() {
        this.zzbxv.zzbyh = true;
    }

    public final void zznq() {
        synchronized (this.zzbxy) {
            this.zzbya = true;
            if (this.zzbxz != null) {
                zzakk.zzcrm.removeCallbacks(this.zzbxz);
                zzakk.zzcrm.post(this.zzbxz);
            }
        }
    }

    public final void zzo(IObjectWrapper iObjectWrapper) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbel)).booleanValue() && PlatformVersion.isAtLeastN()) {
            zzbv.zzek();
            if (zzakk.zza(this.mActivity, (Configuration) ObjectWrapper.unwrap(iObjectWrapper))) {
                this.mActivity.getWindow().addFlags(1024);
                this.mActivity.getWindow().clearFlags(2048);
                return;
            }
            this.mActivity.getWindow().addFlags(2048);
            this.mActivity.getWindow().clearFlags(1024);
        }
    }
}
