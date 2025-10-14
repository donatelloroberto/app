package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.Set;

@zzadh
public final class zzaab extends zzaal {
    private static final Set<String> zzbvy = CollectionUtils.setOf("top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center");
    private final Object mLock = new Object();
    private zzaam zzbmy;
    private final zzaqw zzbnd;
    private final Activity zzbvp;
    private String zzbvz = "top-right";
    private boolean zzbwa = true;
    private int zzbwb = 0;
    private int zzbwc = 0;
    private int zzbwd = 0;
    private int zzbwe = 0;
    private zzasi zzbwf;
    private ImageView zzbwg;
    private LinearLayout zzbwh;
    private PopupWindow zzbwi;
    private RelativeLayout zzbwj;
    private ViewGroup zzbwk;
    private int zzuq = -1;
    private int zzur = -1;

    public zzaab(zzaqw zzaqw, zzaam zzaam) {
        super(zzaqw, "resize");
        this.zzbnd = zzaqw;
        this.zzbvp = zzaqw.zzto();
        this.zzbmy = zzaam;
    }

    private final void zza(int i, int i2) {
        zzb(i, i2 - zzbv.zzek().zzh(this.zzbvp)[0], this.zzuq, this.zzur);
    }

    private final int[] zzne() {
        boolean z;
        int i;
        int i2;
        int[] zzg = zzbv.zzek().zzg(this.zzbvp);
        int[] zzh = zzbv.zzek().zzh(this.zzbvp);
        int i3 = zzg[0];
        int i4 = zzg[1];
        if (this.zzuq < 50 || this.zzuq > i3) {
            zzakb.zzdk("Width is too small or too large.");
            z = false;
        } else if (this.zzur < 50 || this.zzur > i4) {
            zzakb.zzdk("Height is too small or too large.");
            z = false;
        } else if (this.zzur == i4 && this.zzuq == i3) {
            zzakb.zzdk("Cannot resize to a full-screen ad.");
            z = false;
        } else {
            if (this.zzbwa) {
                String str = this.zzbvz;
                char c = 65535;
                switch (str.hashCode()) {
                    case -1364013995:
                        if (str.equals("center")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1012429441:
                        if (str.equals("top-left")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -655373719:
                        if (str.equals("bottom-left")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1163912186:
                        if (str.equals("bottom-right")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 1288627767:
                        if (str.equals("bottom-center")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 1755462605:
                        if (str.equals("top-center")) {
                            c = 1;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        i = this.zzbwd + this.zzbwb;
                        i2 = this.zzbwc + this.zzbwe;
                        break;
                    case 1:
                        i = ((this.zzbwb + this.zzbwd) + (this.zzuq / 2)) - 25;
                        i2 = this.zzbwc + this.zzbwe;
                        break;
                    case 2:
                        i = ((this.zzbwb + this.zzbwd) + (this.zzuq / 2)) - 25;
                        i2 = ((this.zzbwc + this.zzbwe) + (this.zzur / 2)) - 25;
                        break;
                    case 3:
                        i = this.zzbwd + this.zzbwb;
                        i2 = ((this.zzbwc + this.zzbwe) + this.zzur) - 50;
                        break;
                    case 4:
                        i = ((this.zzbwb + this.zzbwd) + (this.zzuq / 2)) - 25;
                        i2 = ((this.zzbwc + this.zzbwe) + this.zzur) - 50;
                        break;
                    case 5:
                        i = ((this.zzbwb + this.zzbwd) + this.zzuq) - 50;
                        i2 = ((this.zzbwc + this.zzbwe) + this.zzur) - 50;
                        break;
                    default:
                        i = ((this.zzbwb + this.zzbwd) + this.zzuq) - 50;
                        i2 = this.zzbwc + this.zzbwe;
                        break;
                }
                if (i < 0 || i + 50 > i3 || i2 < zzh[0] || i2 + 50 > zzh[1]) {
                    z = false;
                }
            }
            z = true;
        }
        if (!z) {
            return null;
        }
        if (this.zzbwa) {
            return new int[]{this.zzbwb + this.zzbwd, this.zzbwc + this.zzbwe};
        }
        int[] zzg2 = zzbv.zzek().zzg(this.zzbvp);
        int[] zzh2 = zzbv.zzek().zzh(this.zzbvp);
        int i5 = zzg2[0];
        int i6 = this.zzbwb + this.zzbwd;
        int i7 = this.zzbwc + this.zzbwe;
        if (i6 < 0) {
            i6 = 0;
        } else if (this.zzuq + i6 > i5) {
            i6 = i5 - this.zzuq;
        }
        if (i7 < zzh2[0]) {
            i7 = zzh2[0];
        } else if (this.zzur + i7 > zzh2[1]) {
            i7 = zzh2[1] - this.zzur;
        }
        return new int[]{i6, i7};
    }

    public final void zza(int i, int i2, boolean z) {
        synchronized (this.mLock) {
            this.zzbwb = i;
            this.zzbwc = i2;
            if (this.zzbwi != null && z) {
                int[] zzne = zzne();
                if (zzne != null) {
                    PopupWindow popupWindow = this.zzbwi;
                    zzkb.zzif();
                    int zza = zzamu.zza((Context) this.zzbvp, zzne[0]);
                    zzkb.zzif();
                    popupWindow.update(zza, zzamu.zza((Context) this.zzbvp, zzne[1]), this.zzbwi.getWidth(), this.zzbwi.getHeight());
                    zza(zzne[0], zzne[1]);
                } else {
                    zzm(true);
                }
            }
        }
    }

    public final void zzb(int i, int i2) {
        this.zzbwb = i;
        this.zzbwc = i2;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzk(java.util.Map<java.lang.String, java.lang.String> r14) {
        /*
            r13 = this;
            r4 = -1
            r5 = 1
            r3 = 0
            java.lang.Object r6 = r13.mLock
            monitor-enter(r6)
            android.app.Activity r1 = r13.zzbvp     // Catch:{ all -> 0x0020 }
            if (r1 != 0) goto L_0x0011
            java.lang.String r1 = "Not an activity context. Cannot resize."
            r13.zzbw(r1)     // Catch:{ all -> 0x0020 }
            monitor-exit(r6)     // Catch:{ all -> 0x0020 }
        L_0x0010:
            return
        L_0x0011:
            com.google.android.gms.internal.ads.zzaqw r1 = r13.zzbnd     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzasi r1 = r1.zzud()     // Catch:{ all -> 0x0020 }
            if (r1 != 0) goto L_0x0023
            java.lang.String r1 = "Webview is not yet available, size is not set."
            r13.zzbw(r1)     // Catch:{ all -> 0x0020 }
            monitor-exit(r6)     // Catch:{ all -> 0x0020 }
            goto L_0x0010
        L_0x0020:
            r1 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0020 }
            throw r1
        L_0x0023:
            com.google.android.gms.internal.ads.zzaqw r1 = r13.zzbnd     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzasi r1 = r1.zzud()     // Catch:{ all -> 0x0020 }
            boolean r1 = r1.zzvs()     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x0036
            java.lang.String r1 = "Is interstitial. Cannot resize an interstitial."
            r13.zzbw(r1)     // Catch:{ all -> 0x0020 }
            monitor-exit(r6)     // Catch:{ all -> 0x0020 }
            goto L_0x0010
        L_0x0036:
            com.google.android.gms.internal.ads.zzaqw r1 = r13.zzbnd     // Catch:{ all -> 0x0020 }
            boolean r1 = r1.zzuj()     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x0045
            java.lang.String r1 = "Cannot resize an expanded banner."
            r13.zzbw(r1)     // Catch:{ all -> 0x0020 }
            monitor-exit(r6)     // Catch:{ all -> 0x0020 }
            goto L_0x0010
        L_0x0045:
            java.lang.String r1 = "width"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0020 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0020 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0020 }
            if (r1 != 0) goto L_0x0064
            com.google.android.gms.ads.internal.zzbv.zzek()     // Catch:{ all -> 0x0020 }
            java.lang.String r1 = "width"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0020 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0020 }
            int r1 = com.google.android.gms.internal.ads.zzakk.zzcv(r1)     // Catch:{ all -> 0x0020 }
            r13.zzuq = r1     // Catch:{ all -> 0x0020 }
        L_0x0064:
            java.lang.String r1 = "height"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0020 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0020 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0020 }
            if (r1 != 0) goto L_0x0083
            com.google.android.gms.ads.internal.zzbv.zzek()     // Catch:{ all -> 0x0020 }
            java.lang.String r1 = "height"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0020 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0020 }
            int r1 = com.google.android.gms.internal.ads.zzakk.zzcv(r1)     // Catch:{ all -> 0x0020 }
            r13.zzur = r1     // Catch:{ all -> 0x0020 }
        L_0x0083:
            java.lang.String r1 = "offsetX"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0020 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0020 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0020 }
            if (r1 != 0) goto L_0x00a2
            com.google.android.gms.ads.internal.zzbv.zzek()     // Catch:{ all -> 0x0020 }
            java.lang.String r1 = "offsetX"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0020 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0020 }
            int r1 = com.google.android.gms.internal.ads.zzakk.zzcv(r1)     // Catch:{ all -> 0x0020 }
            r13.zzbwd = r1     // Catch:{ all -> 0x0020 }
        L_0x00a2:
            java.lang.String r1 = "offsetY"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0020 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0020 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0020 }
            if (r1 != 0) goto L_0x00c1
            com.google.android.gms.ads.internal.zzbv.zzek()     // Catch:{ all -> 0x0020 }
            java.lang.String r1 = "offsetY"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0020 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0020 }
            int r1 = com.google.android.gms.internal.ads.zzakk.zzcv(r1)     // Catch:{ all -> 0x0020 }
            r13.zzbwe = r1     // Catch:{ all -> 0x0020 }
        L_0x00c1:
            java.lang.String r1 = "allowOffscreen"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0020 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0020 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0020 }
            if (r1 != 0) goto L_0x00dd
            java.lang.String r1 = "allowOffscreen"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0020 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0020 }
            boolean r1 = java.lang.Boolean.parseBoolean(r1)     // Catch:{ all -> 0x0020 }
            r13.zzbwa = r1     // Catch:{ all -> 0x0020 }
        L_0x00dd:
            java.lang.String r1 = "customClosePosition"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0020 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0020 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0020 }
            if (r2 != 0) goto L_0x00ed
            r13.zzbvz = r1     // Catch:{ all -> 0x0020 }
        L_0x00ed:
            int r1 = r13.zzuq     // Catch:{ all -> 0x0020 }
            if (r1 < 0) goto L_0x0100
            int r1 = r13.zzur     // Catch:{ all -> 0x0020 }
            if (r1 < 0) goto L_0x0100
            r1 = r5
        L_0x00f6:
            if (r1 != 0) goto L_0x0102
            java.lang.String r1 = "Invalid width and height options. Cannot resize."
            r13.zzbw(r1)     // Catch:{ all -> 0x0020 }
            monitor-exit(r6)     // Catch:{ all -> 0x0020 }
            goto L_0x0010
        L_0x0100:
            r1 = r3
            goto L_0x00f6
        L_0x0102:
            android.app.Activity r1 = r13.zzbvp     // Catch:{ all -> 0x0020 }
            android.view.Window r7 = r1.getWindow()     // Catch:{ all -> 0x0020 }
            if (r7 == 0) goto L_0x0110
            android.view.View r1 = r7.getDecorView()     // Catch:{ all -> 0x0020 }
            if (r1 != 0) goto L_0x0118
        L_0x0110:
            java.lang.String r1 = "Activity context is not ready, cannot get window or decor view."
            r13.zzbw(r1)     // Catch:{ all -> 0x0020 }
            monitor-exit(r6)     // Catch:{ all -> 0x0020 }
            goto L_0x0010
        L_0x0118:
            int[] r8 = r13.zzne()     // Catch:{ all -> 0x0020 }
            if (r8 != 0) goto L_0x0126
            java.lang.String r1 = "Resize location out of screen or close button is not visible."
            r13.zzbw(r1)     // Catch:{ all -> 0x0020 }
            monitor-exit(r6)     // Catch:{ all -> 0x0020 }
            goto L_0x0010
        L_0x0126:
            com.google.android.gms.internal.ads.zzkb.zzif()     // Catch:{ all -> 0x0020 }
            android.app.Activity r1 = r13.zzbvp     // Catch:{ all -> 0x0020 }
            int r2 = r13.zzuq     // Catch:{ all -> 0x0020 }
            int r9 = com.google.android.gms.internal.ads.zzamu.zza((android.content.Context) r1, (int) r2)     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzkb.zzif()     // Catch:{ all -> 0x0020 }
            android.app.Activity r1 = r13.zzbvp     // Catch:{ all -> 0x0020 }
            int r2 = r13.zzur     // Catch:{ all -> 0x0020 }
            int r10 = com.google.android.gms.internal.ads.zzamu.zza((android.content.Context) r1, (int) r2)     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzaqw r1 = r13.zzbnd     // Catch:{ all -> 0x0020 }
            android.view.View r1 = r1.getView()     // Catch:{ all -> 0x0020 }
            android.view.ViewParent r2 = r1.getParent()     // Catch:{ all -> 0x0020 }
            if (r2 == 0) goto L_0x027c
            boolean r1 = r2 instanceof android.view.ViewGroup     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x027c
            r0 = r2
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0     // Catch:{ all -> 0x0020 }
            r1 = r0
            com.google.android.gms.internal.ads.zzaqw r11 = r13.zzbnd     // Catch:{ all -> 0x0020 }
            android.view.View r11 = r11.getView()     // Catch:{ all -> 0x0020 }
            r1.removeView(r11)     // Catch:{ all -> 0x0020 }
            android.widget.PopupWindow r1 = r13.zzbwi     // Catch:{ all -> 0x0020 }
            if (r1 != 0) goto L_0x0275
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2     // Catch:{ all -> 0x0020 }
            r13.zzbwk = r2     // Catch:{ all -> 0x0020 }
            com.google.android.gms.ads.internal.zzbv.zzek()     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzaqw r1 = r13.zzbnd     // Catch:{ all -> 0x0020 }
            android.view.View r1 = r1.getView()     // Catch:{ all -> 0x0020 }
            android.graphics.Bitmap r1 = com.google.android.gms.internal.ads.zzakk.zzs(r1)     // Catch:{ all -> 0x0020 }
            android.widget.ImageView r2 = new android.widget.ImageView     // Catch:{ all -> 0x0020 }
            android.app.Activity r11 = r13.zzbvp     // Catch:{ all -> 0x0020 }
            r2.<init>(r11)     // Catch:{ all -> 0x0020 }
            r13.zzbwg = r2     // Catch:{ all -> 0x0020 }
            android.widget.ImageView r2 = r13.zzbwg     // Catch:{ all -> 0x0020 }
            r2.setImageBitmap(r1)     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzaqw r1 = r13.zzbnd     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzasi r1 = r1.zzud()     // Catch:{ all -> 0x0020 }
            r13.zzbwf = r1     // Catch:{ all -> 0x0020 }
            android.view.ViewGroup r1 = r13.zzbwk     // Catch:{ all -> 0x0020 }
            android.widget.ImageView r2 = r13.zzbwg     // Catch:{ all -> 0x0020 }
            r1.addView(r2)     // Catch:{ all -> 0x0020 }
        L_0x018b:
            android.widget.RelativeLayout r1 = new android.widget.RelativeLayout     // Catch:{ all -> 0x0020 }
            android.app.Activity r2 = r13.zzbvp     // Catch:{ all -> 0x0020 }
            r1.<init>(r2)     // Catch:{ all -> 0x0020 }
            r13.zzbwj = r1     // Catch:{ all -> 0x0020 }
            android.widget.RelativeLayout r1 = r13.zzbwj     // Catch:{ all -> 0x0020 }
            r2 = 0
            r1.setBackgroundColor(r2)     // Catch:{ all -> 0x0020 }
            android.widget.RelativeLayout r1 = r13.zzbwj     // Catch:{ all -> 0x0020 }
            android.view.ViewGroup$LayoutParams r2 = new android.view.ViewGroup$LayoutParams     // Catch:{ all -> 0x0020 }
            r2.<init>(r9, r10)     // Catch:{ all -> 0x0020 }
            r1.setLayoutParams(r2)     // Catch:{ all -> 0x0020 }
            com.google.android.gms.ads.internal.zzbv.zzek()     // Catch:{ all -> 0x0020 }
            android.widget.RelativeLayout r1 = r13.zzbwj     // Catch:{ all -> 0x0020 }
            r2 = 0
            android.widget.PopupWindow r1 = com.google.android.gms.internal.ads.zzakk.zza((android.view.View) r1, (int) r9, (int) r10, (boolean) r2)     // Catch:{ all -> 0x0020 }
            r13.zzbwi = r1     // Catch:{ all -> 0x0020 }
            android.widget.PopupWindow r1 = r13.zzbwi     // Catch:{ all -> 0x0020 }
            r2 = 1
            r1.setOutsideTouchable(r2)     // Catch:{ all -> 0x0020 }
            android.widget.PopupWindow r1 = r13.zzbwi     // Catch:{ all -> 0x0020 }
            r2 = 1
            r1.setTouchable(r2)     // Catch:{ all -> 0x0020 }
            android.widget.PopupWindow r2 = r13.zzbwi     // Catch:{ all -> 0x0020 }
            boolean r1 = r13.zzbwa     // Catch:{ all -> 0x0020 }
            if (r1 != 0) goto L_0x0284
            r1 = r5
        L_0x01c3:
            r2.setClippingEnabled(r1)     // Catch:{ all -> 0x0020 }
            android.widget.RelativeLayout r1 = r13.zzbwj     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzaqw r2 = r13.zzbnd     // Catch:{ all -> 0x0020 }
            android.view.View r2 = r2.getView()     // Catch:{ all -> 0x0020 }
            r11 = -1
            r12 = -1
            r1.addView(r2, r11, r12)     // Catch:{ all -> 0x0020 }
            android.widget.LinearLayout r1 = new android.widget.LinearLayout     // Catch:{ all -> 0x0020 }
            android.app.Activity r2 = r13.zzbvp     // Catch:{ all -> 0x0020 }
            r1.<init>(r2)     // Catch:{ all -> 0x0020 }
            r13.zzbwh = r1     // Catch:{ all -> 0x0020 }
            android.widget.RelativeLayout$LayoutParams r2 = new android.widget.RelativeLayout$LayoutParams     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzkb.zzif()     // Catch:{ all -> 0x0020 }
            android.app.Activity r1 = r13.zzbvp     // Catch:{ all -> 0x0020 }
            r11 = 50
            int r1 = com.google.android.gms.internal.ads.zzamu.zza((android.content.Context) r1, (int) r11)     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzkb.zzif()     // Catch:{ all -> 0x0020 }
            android.app.Activity r11 = r13.zzbvp     // Catch:{ all -> 0x0020 }
            r12 = 50
            int r11 = com.google.android.gms.internal.ads.zzamu.zza((android.content.Context) r11, (int) r12)     // Catch:{ all -> 0x0020 }
            r2.<init>(r1, r11)     // Catch:{ all -> 0x0020 }
            java.lang.String r1 = r13.zzbvz     // Catch:{ all -> 0x0020 }
            int r11 = r1.hashCode()     // Catch:{ all -> 0x0020 }
            switch(r11) {
                case -1364013995: goto L_0x029d;
                case -1012429441: goto L_0x0287;
                case -655373719: goto L_0x02a8;
                case 1163912186: goto L_0x02be;
                case 1288627767: goto L_0x02b3;
                case 1755462605: goto L_0x0292;
                default: goto L_0x0200;
            }     // Catch:{ all -> 0x0020 }
        L_0x0200:
            r1 = r4
        L_0x0201:
            switch(r1) {
                case 0: goto L_0x02c9;
                case 1: goto L_0x02d5;
                case 2: goto L_0x02e1;
                case 3: goto L_0x02e8;
                case 4: goto L_0x02f4;
                case 5: goto L_0x0300;
                default: goto L_0x0204;
            }     // Catch:{ all -> 0x0020 }
        L_0x0204:
            r1 = 10
            r2.addRule(r1)     // Catch:{ all -> 0x0020 }
            r1 = 11
            r2.addRule(r1)     // Catch:{ all -> 0x0020 }
        L_0x020e:
            android.widget.LinearLayout r1 = r13.zzbwh     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzaac r3 = new com.google.android.gms.internal.ads.zzaac     // Catch:{ all -> 0x0020 }
            r3.<init>(r13)     // Catch:{ all -> 0x0020 }
            r1.setOnClickListener(r3)     // Catch:{ all -> 0x0020 }
            android.widget.LinearLayout r1 = r13.zzbwh     // Catch:{ all -> 0x0020 }
            java.lang.String r3 = "Close button"
            r1.setContentDescription(r3)     // Catch:{ all -> 0x0020 }
            android.widget.RelativeLayout r1 = r13.zzbwj     // Catch:{ all -> 0x0020 }
            android.widget.LinearLayout r3 = r13.zzbwh     // Catch:{ all -> 0x0020 }
            r1.addView(r3, r2)     // Catch:{ all -> 0x0020 }
            android.widget.PopupWindow r1 = r13.zzbwi     // Catch:{ RuntimeException -> 0x030c }
            android.view.View r2 = r7.getDecorView()     // Catch:{ RuntimeException -> 0x030c }
            r3 = 0
            com.google.android.gms.internal.ads.zzkb.zzif()     // Catch:{ RuntimeException -> 0x030c }
            android.app.Activity r4 = r13.zzbvp     // Catch:{ RuntimeException -> 0x030c }
            r5 = 0
            r5 = r8[r5]     // Catch:{ RuntimeException -> 0x030c }
            int r4 = com.google.android.gms.internal.ads.zzamu.zza((android.content.Context) r4, (int) r5)     // Catch:{ RuntimeException -> 0x030c }
            com.google.android.gms.internal.ads.zzkb.zzif()     // Catch:{ RuntimeException -> 0x030c }
            android.app.Activity r5 = r13.zzbvp     // Catch:{ RuntimeException -> 0x030c }
            r7 = 1
            r7 = r8[r7]     // Catch:{ RuntimeException -> 0x030c }
            int r5 = com.google.android.gms.internal.ads.zzamu.zza((android.content.Context) r5, (int) r7)     // Catch:{ RuntimeException -> 0x030c }
            r1.showAtLocation(r2, r3, r4, r5)     // Catch:{ RuntimeException -> 0x030c }
            r1 = 0
            r1 = r8[r1]     // Catch:{ all -> 0x0020 }
            r2 = 1
            r2 = r8[r2]     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzaam r3 = r13.zzbmy     // Catch:{ all -> 0x0020 }
            if (r3 == 0) goto L_0x025b
            com.google.android.gms.internal.ads.zzaam r3 = r13.zzbmy     // Catch:{ all -> 0x0020 }
            int r4 = r13.zzuq     // Catch:{ all -> 0x0020 }
            int r5 = r13.zzur     // Catch:{ all -> 0x0020 }
            r3.zza(r1, r2, r4, r5)     // Catch:{ all -> 0x0020 }
        L_0x025b:
            com.google.android.gms.internal.ads.zzaqw r1 = r13.zzbnd     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzasi r2 = com.google.android.gms.internal.ads.zzasi.zzi(r9, r10)     // Catch:{ all -> 0x0020 }
            r1.zza((com.google.android.gms.internal.ads.zzasi) r2)     // Catch:{ all -> 0x0020 }
            r1 = 0
            r1 = r8[r1]     // Catch:{ all -> 0x0020 }
            r2 = 1
            r2 = r8[r2]     // Catch:{ all -> 0x0020 }
            r13.zza(r1, r2)     // Catch:{ all -> 0x0020 }
            java.lang.String r1 = "resized"
            r13.zzby(r1)     // Catch:{ all -> 0x0020 }
            monitor-exit(r6)     // Catch:{ all -> 0x0020 }
            goto L_0x0010
        L_0x0275:
            android.widget.PopupWindow r1 = r13.zzbwi     // Catch:{ all -> 0x0020 }
            r1.dismiss()     // Catch:{ all -> 0x0020 }
            goto L_0x018b
        L_0x027c:
            java.lang.String r1 = "Webview is detached, probably in the middle of a resize or expand."
            r13.zzbw(r1)     // Catch:{ all -> 0x0020 }
            monitor-exit(r6)     // Catch:{ all -> 0x0020 }
            goto L_0x0010
        L_0x0284:
            r1 = r3
            goto L_0x01c3
        L_0x0287:
            java.lang.String r5 = "top-left"
            boolean r1 = r1.equals(r5)     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x0200
            r1 = r3
            goto L_0x0201
        L_0x0292:
            java.lang.String r3 = "top-center"
            boolean r1 = r1.equals(r3)     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x0200
            r1 = r5
            goto L_0x0201
        L_0x029d:
            java.lang.String r3 = "center"
            boolean r1 = r1.equals(r3)     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x0200
            r1 = 2
            goto L_0x0201
        L_0x02a8:
            java.lang.String r3 = "bottom-left"
            boolean r1 = r1.equals(r3)     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x0200
            r1 = 3
            goto L_0x0201
        L_0x02b3:
            java.lang.String r3 = "bottom-center"
            boolean r1 = r1.equals(r3)     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x0200
            r1 = 4
            goto L_0x0201
        L_0x02be:
            java.lang.String r3 = "bottom-right"
            boolean r1 = r1.equals(r3)     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x0200
            r1 = 5
            goto L_0x0201
        L_0x02c9:
            r1 = 10
            r2.addRule(r1)     // Catch:{ all -> 0x0020 }
            r1 = 9
            r2.addRule(r1)     // Catch:{ all -> 0x0020 }
            goto L_0x020e
        L_0x02d5:
            r1 = 10
            r2.addRule(r1)     // Catch:{ all -> 0x0020 }
            r1 = 14
            r2.addRule(r1)     // Catch:{ all -> 0x0020 }
            goto L_0x020e
        L_0x02e1:
            r1 = 13
            r2.addRule(r1)     // Catch:{ all -> 0x0020 }
            goto L_0x020e
        L_0x02e8:
            r1 = 12
            r2.addRule(r1)     // Catch:{ all -> 0x0020 }
            r1 = 9
            r2.addRule(r1)     // Catch:{ all -> 0x0020 }
            goto L_0x020e
        L_0x02f4:
            r1 = 12
            r2.addRule(r1)     // Catch:{ all -> 0x0020 }
            r1 = 14
            r2.addRule(r1)     // Catch:{ all -> 0x0020 }
            goto L_0x020e
        L_0x0300:
            r1 = 12
            r2.addRule(r1)     // Catch:{ all -> 0x0020 }
            r1 = 11
            r2.addRule(r1)     // Catch:{ all -> 0x0020 }
            goto L_0x020e
        L_0x030c:
            r1 = move-exception
            java.lang.String r2 = "Cannot show popup window: "
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0020 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x0020 }
            int r3 = r1.length()     // Catch:{ all -> 0x0020 }
            if (r3 == 0) goto L_0x034f
            java.lang.String r1 = r2.concat(r1)     // Catch:{ all -> 0x0020 }
        L_0x0321:
            r13.zzbw(r1)     // Catch:{ all -> 0x0020 }
            android.widget.RelativeLayout r1 = r13.zzbwj     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzaqw r2 = r13.zzbnd     // Catch:{ all -> 0x0020 }
            android.view.View r2 = r2.getView()     // Catch:{ all -> 0x0020 }
            r1.removeView(r2)     // Catch:{ all -> 0x0020 }
            android.view.ViewGroup r1 = r13.zzbwk     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x034c
            android.view.ViewGroup r1 = r13.zzbwk     // Catch:{ all -> 0x0020 }
            android.widget.ImageView r2 = r13.zzbwg     // Catch:{ all -> 0x0020 }
            r1.removeView(r2)     // Catch:{ all -> 0x0020 }
            android.view.ViewGroup r1 = r13.zzbwk     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzaqw r2 = r13.zzbnd     // Catch:{ all -> 0x0020 }
            android.view.View r2 = r2.getView()     // Catch:{ all -> 0x0020 }
            r1.addView(r2)     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzaqw r1 = r13.zzbnd     // Catch:{ all -> 0x0020 }
            com.google.android.gms.internal.ads.zzasi r2 = r13.zzbwf     // Catch:{ all -> 0x0020 }
            r1.zza((com.google.android.gms.internal.ads.zzasi) r2)     // Catch:{ all -> 0x0020 }
        L_0x034c:
            monitor-exit(r6)     // Catch:{ all -> 0x0020 }
            goto L_0x0010
        L_0x034f:
            java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x0020 }
            r1.<init>(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x0321
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaab.zzk(java.util.Map):void");
    }

    public final void zzm(boolean z) {
        synchronized (this.mLock) {
            if (this.zzbwi != null) {
                this.zzbwi.dismiss();
                this.zzbwj.removeView(this.zzbnd.getView());
                if (this.zzbwk != null) {
                    this.zzbwk.removeView(this.zzbwg);
                    this.zzbwk.addView(this.zzbnd.getView());
                    this.zzbnd.zza(this.zzbwf);
                }
                if (z) {
                    zzby(PushConstants.SOUND_DEFAULT);
                    if (this.zzbmy != null) {
                        this.zzbmy.zzcq();
                    }
                }
                this.zzbwi = null;
                this.zzbwj = null;
                this.zzbwk = null;
                this.zzbwh = null;
            }
        }
    }

    public final boolean zznf() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzbwi != null;
        }
        return z;
    }
}
