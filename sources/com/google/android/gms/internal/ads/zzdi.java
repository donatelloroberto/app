package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import java.lang.ref.WeakReference;

public final class zzdi implements Application.ActivityLifecycleCallbacks, View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static final Handler zzsy = new Handler(Looper.getMainLooper());
    private final zzcz zzps;
    private Application zzrk;
    private final Context zzsz;
    private final PowerManager zzta;
    private final KeyguardManager zztb;
    private BroadcastReceiver zztc;
    private WeakReference<ViewTreeObserver> zztd;
    private WeakReference<View> zzte;
    private zzcn zztf;
    private boolean zztg = false;
    private int zzth = -1;
    private long zzti = -3;

    public zzdi(zzcz zzcz, View view) {
        this.zzps = zzcz;
        this.zzsz = zzcz.zzrt;
        this.zzta = (PowerManager) this.zzsz.getSystemService("power");
        this.zztb = (KeyguardManager) this.zzsz.getSystemService("keyguard");
        if (this.zzsz instanceof Application) {
            this.zzrk = (Application) this.zzsz;
            this.zztf = new zzcn((Application) this.zzsz, this);
        }
        zzd(view);
    }

    private final void zza(Activity activity, int i) {
        Window window;
        if (this.zzte != null && (window = activity.getWindow()) != null) {
            View peekDecorView = window.peekDecorView();
            View view = (View) this.zzte.get();
            if (view != null && peekDecorView != null && view.getRootView() == peekDecorView.getRootView()) {
                this.zzth = i;
            }
        }
    }

    private final void zzao() {
        zzsy.post(new zzdj(this));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0051, code lost:
        if (r1 == false) goto L_0x0092;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzaq() {
        /*
            r9 = this;
            r3 = 1
            r2 = 0
            java.lang.ref.WeakReference<android.view.View> r0 = r9.zzte
            if (r0 != 0) goto L_0x0007
        L_0x0006:
            return
        L_0x0007:
            java.lang.ref.WeakReference<android.view.View> r0 = r9.zzte
            java.lang.Object r0 = r0.get()
            android.view.View r0 = (android.view.View) r0
            if (r0 != 0) goto L_0x0018
            r0 = -3
            r9.zzti = r0
            r9.zztg = r2
            goto L_0x0006
        L_0x0018:
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            boolean r5 = r0.getGlobalVisibleRect(r1)
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            boolean r6 = r0.getLocalVisibleRect(r1)
            com.google.android.gms.internal.ads.zzcz r1 = r9.zzps
            boolean r1 = r1.zzai()
            if (r1 != 0) goto L_0x0053
            android.app.KeyguardManager r1 = r9.zztb
            boolean r1 = r1.inKeyguardRestrictedInputMode()
            if (r1 == 0) goto L_0x0092
            android.app.Activity r1 = com.google.android.gms.internal.ads.zzdg.zzc(r0)
            if (r1 == 0) goto L_0x0090
            android.view.Window r1 = r1.getWindow()
            if (r1 != 0) goto L_0x008b
            r1 = 0
        L_0x0047:
            if (r1 == 0) goto L_0x0090
            int r1 = r1.flags
            r4 = 524288(0x80000, float:7.34684E-40)
            r1 = r1 & r4
            if (r1 == 0) goto L_0x0090
            r1 = r3
        L_0x0051:
            if (r1 == 0) goto L_0x0092
        L_0x0053:
            r1 = r3
        L_0x0054:
            int r4 = r0.getWindowVisibility()
            int r7 = r9.zzth
            r8 = -1
            if (r7 == r8) goto L_0x005f
            int r4 = r9.zzth
        L_0x005f:
            int r7 = r0.getVisibility()
            if (r7 != 0) goto L_0x0094
            boolean r0 = r0.isShown()
            if (r0 == 0) goto L_0x0094
            android.os.PowerManager r0 = r9.zzta
            boolean r0 = r0.isScreenOn()
            if (r0 == 0) goto L_0x0094
            if (r1 == 0) goto L_0x0094
            if (r6 == 0) goto L_0x0094
            if (r5 == 0) goto L_0x0094
            if (r4 != 0) goto L_0x0094
        L_0x007b:
            boolean r0 = r9.zztg
            if (r0 == r3) goto L_0x0006
            if (r3 == 0) goto L_0x0096
            long r0 = android.os.SystemClock.elapsedRealtime()
        L_0x0085:
            r9.zzti = r0
            r9.zztg = r3
            goto L_0x0006
        L_0x008b:
            android.view.WindowManager$LayoutParams r1 = r1.getAttributes()
            goto L_0x0047
        L_0x0090:
            r1 = r2
            goto L_0x0051
        L_0x0092:
            r1 = r2
            goto L_0x0054
        L_0x0094:
            r3 = r2
            goto L_0x007b
        L_0x0096:
            r0 = -2
            goto L_0x0085
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdi.zzaq():void");
    }

    private final void zze(View view) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zztd = new WeakReference<>(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        if (this.zztc == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zztc = new zzdk(this);
            this.zzsz.registerReceiver(this.zztc, intentFilter);
        }
        if (this.zzrk != null) {
            try {
                this.zzrk.registerActivityLifecycleCallbacks(this.zztf);
            } catch (Exception e) {
            }
        }
    }

    private final void zzf(View view) {
        try {
            if (this.zztd != null) {
                ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zztd.get();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnScrollChangedListener(this);
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                this.zztd = null;
            }
        } catch (Exception e) {
        }
        try {
            ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
            if (viewTreeObserver2.isAlive()) {
                viewTreeObserver2.removeOnScrollChangedListener(this);
                viewTreeObserver2.removeGlobalOnLayoutListener(this);
            }
        } catch (Exception e2) {
        }
        if (this.zztc != null) {
            try {
                this.zzsz.unregisterReceiver(this.zztc);
            } catch (Exception e3) {
            }
            this.zztc = null;
        }
        if (this.zzrk != null) {
            try {
                this.zzrk.unregisterActivityLifecycleCallbacks(this.zztf);
            } catch (Exception e4) {
            }
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(activity, 0);
        zzaq();
    }

    public final void onActivityDestroyed(Activity activity) {
        zzaq();
    }

    public final void onActivityPaused(Activity activity) {
        zza(activity, 4);
        zzaq();
    }

    public final void onActivityResumed(Activity activity) {
        zza(activity, 0);
        zzaq();
        zzao();
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzaq();
    }

    public final void onActivityStarted(Activity activity) {
        zza(activity, 0);
        zzaq();
    }

    public final void onActivityStopped(Activity activity) {
        zzaq();
    }

    public final void onGlobalLayout() {
        zzaq();
    }

    public final void onScrollChanged() {
        zzaq();
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzth = -1;
        zze(view);
        zzaq();
    }

    public final void onViewDetachedFromWindow(View view) {
        this.zzth = -1;
        zzaq();
        zzao();
        zzf(view);
    }

    public final long zzap() {
        if (this.zzti == -2 && this.zzte.get() == null) {
            this.zzti = -3;
        }
        return this.zzti;
    }

    /* access modifiers changed from: package-private */
    public final void zzd(View view) {
        View view2 = this.zzte != null ? (View) this.zzte.get() : null;
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener(this);
            zzf(view2);
        }
        this.zzte = new WeakReference<>(view);
        if (view != null) {
            if ((view.getWindowToken() == null && view.getWindowVisibility() == 8) ? false : true) {
                zze(view);
            }
            view.addOnAttachStateChangeListener(this);
            this.zzti = -2;
            return;
        }
        this.zzti = -3;
    }
}
