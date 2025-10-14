package com.google.android.gms.internal.ads;

import android.graphics.Point;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@zzadh
@ParametersAreNonnullByDefault
public final class zzpp extends zzqg implements View.OnClickListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    @VisibleForTesting
    static final String[] zzbjs = {NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO, UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO};
    private final Object mLock = new Object();
    @Nullable
    @GuardedBy("mLock")
    @VisibleForTesting
    private zzoz zzbij;
    @Nullable
    @VisibleForTesting
    private View zzbjx;
    @VisibleForTesting
    private Point zzbjz = new Point();
    @VisibleForTesting
    private Point zzbka = new Point();
    @Nullable
    @VisibleForTesting
    private WeakReference<zzfp> zzbkb = new WeakReference<>((Object) null);
    private final WeakReference<View> zzbke;
    private final Map<String, WeakReference<View>> zzbkf = new HashMap();
    private final Map<String, WeakReference<View>> zzbkg = new HashMap();
    private final Map<String, WeakReference<View>> zzbkh = new HashMap();

    public zzpp(View view, HashMap<String, View> hashMap, HashMap<String, View> hashMap2) {
        zzbv.zzfg();
        zzaor.zza(view, (ViewTreeObserver.OnGlobalLayoutListener) this);
        zzbv.zzfg();
        zzaor.zza(view, (ViewTreeObserver.OnScrollChangedListener) this);
        view.setOnTouchListener(this);
        view.setOnClickListener(this);
        this.zzbke = new WeakReference<>(view);
        for (Map.Entry next : hashMap.entrySet()) {
            String str = (String) next.getKey();
            View view2 = (View) next.getValue();
            if (view2 != null) {
                this.zzbkf.put(str, new WeakReference(view2));
                if (!NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str) && !UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str)) {
                    view2.setOnTouchListener(this);
                    view2.setClickable(true);
                    view2.setOnClickListener(this);
                }
            }
        }
        this.zzbkh.putAll(this.zzbkf);
        for (Map.Entry next2 : hashMap2.entrySet()) {
            View view3 = (View) next2.getValue();
            if (view3 != null) {
                this.zzbkg.put((String) next2.getKey(), new WeakReference(view3));
                view3.setOnTouchListener(this);
            }
        }
        this.zzbkh.putAll(this.zzbkg);
        zznk.initialize(view.getContext());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.ads.zzpd r7) {
        /*
            r6 = this;
            java.lang.Object r2 = r6.mLock
            monitor-enter(r2)
            java.lang.String[] r3 = zzbjs     // Catch:{ all -> 0x0039 }
            int r4 = r3.length     // Catch:{ all -> 0x0039 }
            r0 = 0
            r1 = r0
        L_0x0008:
            if (r1 >= r4) goto L_0x0029
            r0 = r3[r1]     // Catch:{ all -> 0x0039 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r6.zzbkh     // Catch:{ all -> 0x0039 }
            java.lang.Object r0 = r5.get(r0)     // Catch:{ all -> 0x0039 }
            java.lang.ref.WeakReference r0 = (java.lang.ref.WeakReference) r0     // Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x0025
            java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x0039 }
            android.view.View r0 = (android.view.View) r0     // Catch:{ all -> 0x0039 }
        L_0x001c:
            boolean r1 = r0 instanceof android.widget.FrameLayout     // Catch:{ all -> 0x0039 }
            if (r1 != 0) goto L_0x002b
            r7.zzkq()     // Catch:{ all -> 0x0039 }
            monitor-exit(r2)     // Catch:{ all -> 0x0039 }
        L_0x0024:
            return
        L_0x0025:
            int r0 = r1 + 1
            r1 = r0
            goto L_0x0008
        L_0x0029:
            r0 = 0
            goto L_0x001c
        L_0x002b:
            com.google.android.gms.internal.ads.zzpr r1 = new com.google.android.gms.internal.ads.zzpr     // Catch:{ all -> 0x0039 }
            r1.<init>(r6, r0)     // Catch:{ all -> 0x0039 }
            boolean r3 = r7 instanceof com.google.android.gms.internal.ads.zzoy     // Catch:{ all -> 0x0039 }
            if (r3 == 0) goto L_0x003c
            r7.zzb((android.view.View) r0, (com.google.android.gms.internal.ads.zzox) r1)     // Catch:{ all -> 0x0039 }
        L_0x0037:
            monitor-exit(r2)     // Catch:{ all -> 0x0039 }
            goto L_0x0024
        L_0x0039:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0039 }
            throw r0
        L_0x003c:
            r7.zza((android.view.View) r0, (com.google.android.gms.internal.ads.zzox) r1)     // Catch:{ all -> 0x0039 }
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpp.zza(com.google.android.gms.internal.ads.zzpd):void");
    }

    /* access modifiers changed from: private */
    public final boolean zza(String[] strArr) {
        for (String str : strArr) {
            if (this.zzbkf.get(str) != null) {
                return true;
            }
        }
        for (String str2 : strArr) {
            if (this.zzbkg.get(str2) != null) {
                return false;
            }
        }
        return false;
    }

    private final void zzl(@Nullable View view) {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                zzoz zzkn = this.zzbij instanceof zzoy ? ((zzoy) this.zzbij).zzkn() : this.zzbij;
                if (zzkn != null) {
                    zzkn.zzl(view);
                }
            }
        }
    }

    @VisibleForTesting
    private final int zzv(int i) {
        int zzb;
        synchronized (this.mLock) {
            zzkb.zzif();
            zzb = zzamu.zzb(this.zzbij.getContext(), i);
        }
        return zzb;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r8) {
        /*
            r7 = this;
            java.lang.Object r6 = r7.mLock
            monitor-enter(r6)
            com.google.android.gms.internal.ads.zzoz r0 = r7.zzbij     // Catch:{ all -> 0x0015 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r6)     // Catch:{ all -> 0x0015 }
        L_0x0008:
            return
        L_0x0009:
            java.lang.ref.WeakReference<android.view.View> r0 = r7.zzbke     // Catch:{ all -> 0x0015 }
            java.lang.Object r5 = r0.get()     // Catch:{ all -> 0x0015 }
            android.view.View r5 = (android.view.View) r5     // Catch:{ all -> 0x0015 }
            if (r5 != 0) goto L_0x0018
            monitor-exit(r6)     // Catch:{ all -> 0x0015 }
            goto L_0x0008
        L_0x0015:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0015 }
            throw r0
        L_0x0018:
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x0015 }
            r3.<init>()     // Catch:{ all -> 0x0015 }
            java.lang.String r0 = "x"
            android.graphics.Point r1 = r7.zzbjz     // Catch:{ all -> 0x0015 }
            int r1 = r1.x     // Catch:{ all -> 0x0015 }
            int r1 = r7.zzv(r1)     // Catch:{ all -> 0x0015 }
            float r1 = (float) r1     // Catch:{ all -> 0x0015 }
            r3.putFloat(r0, r1)     // Catch:{ all -> 0x0015 }
            java.lang.String r0 = "y"
            android.graphics.Point r1 = r7.zzbjz     // Catch:{ all -> 0x0015 }
            int r1 = r1.y     // Catch:{ all -> 0x0015 }
            int r1 = r7.zzv(r1)     // Catch:{ all -> 0x0015 }
            float r1 = (float) r1     // Catch:{ all -> 0x0015 }
            r3.putFloat(r0, r1)     // Catch:{ all -> 0x0015 }
            java.lang.String r0 = "start_x"
            android.graphics.Point r1 = r7.zzbka     // Catch:{ all -> 0x0015 }
            int r1 = r1.x     // Catch:{ all -> 0x0015 }
            int r1 = r7.zzv(r1)     // Catch:{ all -> 0x0015 }
            float r1 = (float) r1     // Catch:{ all -> 0x0015 }
            r3.putFloat(r0, r1)     // Catch:{ all -> 0x0015 }
            java.lang.String r0 = "start_y"
            android.graphics.Point r1 = r7.zzbka     // Catch:{ all -> 0x0015 }
            int r1 = r1.y     // Catch:{ all -> 0x0015 }
            int r1 = r7.zzv(r1)     // Catch:{ all -> 0x0015 }
            float r1 = (float) r1     // Catch:{ all -> 0x0015 }
            r3.putFloat(r0, r1)     // Catch:{ all -> 0x0015 }
            android.view.View r0 = r7.zzbjx     // Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x008e
            android.view.View r0 = r7.zzbjx     // Catch:{ all -> 0x0015 }
            boolean r0 = r0.equals(r8)     // Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x008e
            com.google.android.gms.internal.ads.zzoz r0 = r7.zzbij     // Catch:{ all -> 0x0015 }
            boolean r0 = r0 instanceof com.google.android.gms.internal.ads.zzoy     // Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x0083
            com.google.android.gms.internal.ads.zzoz r0 = r7.zzbij     // Catch:{ all -> 0x0015 }
            com.google.android.gms.internal.ads.zzoy r0 = (com.google.android.gms.internal.ads.zzoy) r0     // Catch:{ all -> 0x0015 }
            com.google.android.gms.internal.ads.zzoz r0 = r0.zzkn()     // Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x0081
            com.google.android.gms.internal.ads.zzoz r0 = r7.zzbij     // Catch:{ all -> 0x0015 }
            com.google.android.gms.internal.ads.zzoy r0 = (com.google.android.gms.internal.ads.zzoy) r0     // Catch:{ all -> 0x0015 }
            com.google.android.gms.internal.ads.zzoz r0 = r0.zzkn()     // Catch:{ all -> 0x0015 }
            java.lang.String r2 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4 = r7.zzbkh     // Catch:{ all -> 0x0015 }
            r1 = r8
            r0.zza(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x0015 }
        L_0x0081:
            monitor-exit(r6)     // Catch:{ all -> 0x0015 }
            goto L_0x0008
        L_0x0083:
            com.google.android.gms.internal.ads.zzoz r0 = r7.zzbij     // Catch:{ all -> 0x0015 }
            java.lang.String r2 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4 = r7.zzbkh     // Catch:{ all -> 0x0015 }
            r1 = r8
            r0.zza(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x0015 }
            goto L_0x0081
        L_0x008e:
            com.google.android.gms.internal.ads.zzoz r0 = r7.zzbij     // Catch:{ all -> 0x0015 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r1 = r7.zzbkh     // Catch:{ all -> 0x0015 }
            r0.zza(r8, r1, r3, r5)     // Catch:{ all -> 0x0015 }
            goto L_0x0081
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpp.onClick(android.view.View):void");
    }

    public final void onGlobalLayout() {
        View view;
        synchronized (this.mLock) {
            if (!(this.zzbij == null || (view = (View) this.zzbke.get()) == null)) {
                this.zzbij.zzc(view, this.zzbkh);
            }
        }
    }

    public final void onScrollChanged() {
        View view;
        synchronized (this.mLock) {
            if (!(this.zzbij == null || (view = (View) this.zzbke.get()) == null)) {
                this.zzbij.zzc(view, this.zzbkh);
            }
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                View view2 = (View) this.zzbke.get();
                if (view2 != null) {
                    int[] iArr = new int[2];
                    view2.getLocationOnScreen(iArr);
                    Point point = new Point((int) (motionEvent.getRawX() - ((float) iArr[0])), (int) (motionEvent.getRawY() - ((float) iArr[1])));
                    this.zzbjz = point;
                    if (motionEvent.getAction() == 0) {
                        this.zzbka = point;
                    }
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    obtain.setLocation((float) point.x, (float) point.y);
                    this.zzbij.zzd(obtain);
                    obtain.recycle();
                }
            }
        }
        return false;
    }

    public final void unregisterNativeAd() {
        synchronized (this.mLock) {
            this.zzbjx = null;
            this.zzbij = null;
            this.zzbjz = null;
            this.zzbka = null;
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper) {
        View view;
        synchronized (this.mLock) {
            zzl((View) null);
            Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
            if (!(unwrap instanceof zzpd)) {
                zzakb.zzdk("Not an instance of native engine. This is most likely a transient error");
                return;
            }
            zzpd zzpd = (zzpd) unwrap;
            if (!zzpd.zzkk()) {
                zzakb.e("Your account must be enabled to use this feature. Talk to your account manager to request this feature for your account.");
                return;
            }
            View view2 = (View) this.zzbke.get();
            if (!(this.zzbij == null || view2 == null)) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbbu)).booleanValue()) {
                    this.zzbij.zzb(view2, this.zzbkh);
                }
            }
            synchronized (this.mLock) {
                if (this.zzbij instanceof zzpd) {
                    zzpd zzpd2 = (zzpd) this.zzbij;
                    View view3 = (View) this.zzbke.get();
                    if (!(zzpd2 == null || zzpd2.getContext() == null || view3 == null || !zzbv.zzfh().zzu(view3.getContext()))) {
                        zzaix zzks = zzpd2.zzks();
                        if (zzks != null) {
                            zzks.zzx(false);
                        }
                        zzfp zzfp = (zzfp) this.zzbkb.get();
                        if (!(zzfp == null || zzks == null)) {
                            zzfp.zzb(zzks);
                        }
                    }
                }
            }
            if (!(this.zzbij instanceof zzoy) || !((zzoy) this.zzbij).zzkm()) {
                this.zzbij = zzpd;
                if (zzpd instanceof zzoy) {
                    ((zzoy) zzpd).zzc((zzoz) null);
                }
            } else {
                ((zzoy) this.zzbij).zzc(zzpd);
            }
            String[] strArr = {NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW, UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW};
            int i = 0;
            while (true) {
                if (i >= 2) {
                    view = null;
                    break;
                }
                WeakReference weakReference = this.zzbkh.get(strArr[i]);
                if (weakReference != null) {
                    view = (View) weakReference.get();
                    break;
                }
                i++;
            }
            if (view == null) {
                zzakb.zzdk("Ad choices asset view is not provided.");
            } else {
                ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
                if (viewGroup != null) {
                    this.zzbjx = zzpd.zza((View.OnClickListener) this, true);
                    if (this.zzbjx != null) {
                        this.zzbkh.put(NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE, new WeakReference(this.zzbjx));
                        this.zzbkf.put(NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE, new WeakReference(this.zzbjx));
                        viewGroup.removeAllViews();
                        viewGroup.addView(this.zzbjx);
                    }
                }
            }
            zzpd.zza(view2, this.zzbkf, this.zzbkg, (View.OnTouchListener) this, (View.OnClickListener) this);
            zzakk.zzcrm.post(new zzpq(this, zzpd));
            zzl(view2);
            this.zzbij.zzj(view2);
            synchronized (this.mLock) {
                if (this.zzbij instanceof zzpd) {
                    zzpd zzpd3 = (zzpd) this.zzbij;
                    View view4 = (View) this.zzbke.get();
                    if (!(zzpd3 == null || zzpd3.getContext() == null || view4 == null || !zzbv.zzfh().zzu(view4.getContext()))) {
                        zzfp zzfp2 = (zzfp) this.zzbkb.get();
                        if (zzfp2 == null) {
                            zzfp2 = new zzfp(view4.getContext(), view4);
                            this.zzbkb = new WeakReference<>(zzfp2);
                        }
                        zzfp2.zza((zzft) zzpd3.zzks());
                    }
                }
            }
        }
    }

    public final void zzc(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            this.zzbij.setClickConfirmingView((View) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }
}
