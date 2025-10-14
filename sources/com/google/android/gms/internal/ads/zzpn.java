package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzpn extends zzqb implements View.OnClickListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    @VisibleForTesting
    private static final String[] zzbjs = {NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO, UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO};
    private final Object mLock = new Object();
    @Nullable
    @VisibleForTesting
    private zzoz zzbij;
    private final FrameLayout zzbjt;
    private View zzbju;
    private final boolean zzbjv;
    @VisibleForTesting
    private Map<String, WeakReference<View>> zzbjw = Collections.synchronizedMap(new HashMap());
    @Nullable
    @VisibleForTesting
    private View zzbjx;
    @VisibleForTesting
    private boolean zzbjy = false;
    @VisibleForTesting
    private Point zzbjz = new Point();
    @VisibleForTesting
    private Point zzbka = new Point();
    @VisibleForTesting
    private WeakReference<zzfp> zzbkb = new WeakReference<>((Object) null);
    @Nullable
    @VisibleForTesting
    private FrameLayout zzvh;

    @TargetApi(21)
    public zzpn(FrameLayout frameLayout, FrameLayout frameLayout2) {
        this.zzbjt = frameLayout;
        this.zzvh = frameLayout2;
        zzbv.zzfg();
        zzaor.zza((View) this.zzbjt, (ViewTreeObserver.OnGlobalLayoutListener) this);
        zzbv.zzfg();
        zzaor.zza((View) this.zzbjt, (ViewTreeObserver.OnScrollChangedListener) this);
        this.zzbjt.setOnTouchListener(this);
        this.zzbjt.setOnClickListener(this);
        if (frameLayout2 != null && PlatformVersion.isAtLeastLollipop()) {
            frameLayout2.setElevation(Float.MAX_VALUE);
        }
        zznk.initialize(this.zzbjt.getContext());
        this.zzbjv = ((Boolean) zzkb.zzik().zzd(zznk.zzbcd)).booleanValue();
    }

    private final void zzkt() {
        synchronized (this.mLock) {
            if (!this.zzbjv && this.zzbjy) {
                int measuredWidth = this.zzbjt.getMeasuredWidth();
                int measuredHeight = this.zzbjt.getMeasuredHeight();
                if (!(measuredWidth == 0 || measuredHeight == 0 || this.zzvh == null)) {
                    this.zzvh.setLayoutParams(new FrameLayout.LayoutParams(measuredWidth, measuredHeight));
                    this.zzbjy = false;
                }
            }
        }
    }

    private final void zzl(@Nullable View view) {
        if (this.zzbij != null) {
            zzoz zzkn = this.zzbij instanceof zzoy ? ((zzoy) this.zzbij).zzkn() : this.zzbij;
            if (zzkn != null) {
                zzkn.zzl(view);
            }
        }
    }

    @VisibleForTesting
    private final int zzv(int i) {
        zzkb.zzif();
        return zzamu.zzb(this.zzbij.getContext(), i);
    }

    public final void destroy() {
        synchronized (this.mLock) {
            if (this.zzvh != null) {
                this.zzvh.removeAllViews();
            }
            this.zzvh = null;
            this.zzbjw = null;
            this.zzbjx = null;
            this.zzbij = null;
            this.zzbjz = null;
            this.zzbka = null;
            this.zzbkb = null;
            this.zzbju = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r8) {
        /*
            r7 = this;
            java.lang.Object r6 = r7.mLock
            monitor-enter(r6)
            com.google.android.gms.internal.ads.zzoz r0 = r7.zzbij     // Catch:{ all -> 0x007b }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r6)     // Catch:{ all -> 0x007b }
        L_0x0008:
            return
        L_0x0009:
            com.google.android.gms.internal.ads.zzoz r0 = r7.zzbij     // Catch:{ all -> 0x007b }
            r0.cancelUnconfirmedClick()     // Catch:{ all -> 0x007b }
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x007b }
            r3.<init>()     // Catch:{ all -> 0x007b }
            java.lang.String r0 = "x"
            android.graphics.Point r1 = r7.zzbjz     // Catch:{ all -> 0x007b }
            int r1 = r1.x     // Catch:{ all -> 0x007b }
            int r1 = r7.zzv(r1)     // Catch:{ all -> 0x007b }
            float r1 = (float) r1     // Catch:{ all -> 0x007b }
            r3.putFloat(r0, r1)     // Catch:{ all -> 0x007b }
            java.lang.String r0 = "y"
            android.graphics.Point r1 = r7.zzbjz     // Catch:{ all -> 0x007b }
            int r1 = r1.y     // Catch:{ all -> 0x007b }
            int r1 = r7.zzv(r1)     // Catch:{ all -> 0x007b }
            float r1 = (float) r1     // Catch:{ all -> 0x007b }
            r3.putFloat(r0, r1)     // Catch:{ all -> 0x007b }
            java.lang.String r0 = "start_x"
            android.graphics.Point r1 = r7.zzbka     // Catch:{ all -> 0x007b }
            int r1 = r1.x     // Catch:{ all -> 0x007b }
            int r1 = r7.zzv(r1)     // Catch:{ all -> 0x007b }
            float r1 = (float) r1     // Catch:{ all -> 0x007b }
            r3.putFloat(r0, r1)     // Catch:{ all -> 0x007b }
            java.lang.String r0 = "start_y"
            android.graphics.Point r1 = r7.zzbka     // Catch:{ all -> 0x007b }
            int r1 = r1.y     // Catch:{ all -> 0x007b }
            int r1 = r7.zzv(r1)     // Catch:{ all -> 0x007b }
            float r1 = (float) r1     // Catch:{ all -> 0x007b }
            r3.putFloat(r0, r1)     // Catch:{ all -> 0x007b }
            android.view.View r0 = r7.zzbjx     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x008b
            android.view.View r0 = r7.zzbjx     // Catch:{ all -> 0x007b }
            boolean r0 = r0.equals(r8)     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x008b
            com.google.android.gms.internal.ads.zzoz r0 = r7.zzbij     // Catch:{ all -> 0x007b }
            boolean r0 = r0 instanceof com.google.android.gms.internal.ads.zzoy     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x007e
            com.google.android.gms.internal.ads.zzoz r0 = r7.zzbij     // Catch:{ all -> 0x007b }
            com.google.android.gms.internal.ads.zzoy r0 = (com.google.android.gms.internal.ads.zzoy) r0     // Catch:{ all -> 0x007b }
            com.google.android.gms.internal.ads.zzoz r0 = r0.zzkn()     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x0079
            com.google.android.gms.internal.ads.zzoz r0 = r7.zzbij     // Catch:{ all -> 0x007b }
            com.google.android.gms.internal.ads.zzoy r0 = (com.google.android.gms.internal.ads.zzoy) r0     // Catch:{ all -> 0x007b }
            com.google.android.gms.internal.ads.zzoz r0 = r0.zzkn()     // Catch:{ all -> 0x007b }
            java.lang.String r2 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4 = r7.zzbjw     // Catch:{ all -> 0x007b }
            android.widget.FrameLayout r5 = r7.zzbjt     // Catch:{ all -> 0x007b }
            r1 = r8
            r0.zza(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x007b }
        L_0x0079:
            monitor-exit(r6)     // Catch:{ all -> 0x007b }
            goto L_0x0008
        L_0x007b:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x007b }
            throw r0
        L_0x007e:
            com.google.android.gms.internal.ads.zzoz r0 = r7.zzbij     // Catch:{ all -> 0x007b }
            java.lang.String r2 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4 = r7.zzbjw     // Catch:{ all -> 0x007b }
            android.widget.FrameLayout r5 = r7.zzbjt     // Catch:{ all -> 0x007b }
            r1 = r8
            r0.zza(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x007b }
            goto L_0x0079
        L_0x008b:
            com.google.android.gms.internal.ads.zzoz r0 = r7.zzbij     // Catch:{ all -> 0x007b }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r1 = r7.zzbjw     // Catch:{ all -> 0x007b }
            android.widget.FrameLayout r2 = r7.zzbjt     // Catch:{ all -> 0x007b }
            r0.zza(r8, r1, r3, r2)     // Catch:{ all -> 0x007b }
            goto L_0x0079
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpn.onClick(android.view.View):void");
    }

    public final void onGlobalLayout() {
        synchronized (this.mLock) {
            zzkt();
            if (this.zzbij != null) {
                this.zzbij.zzc(this.zzbjt, this.zzbjw);
            }
        }
    }

    public final void onScrollChanged() {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                this.zzbij.zzc(this.zzbjt, this.zzbjw);
            }
            zzkt();
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                int[] iArr = new int[2];
                this.zzbjt.getLocationOnScreen(iArr);
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
        return false;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0121 A[Catch:{ Exception -> 0x0241 }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x014b A[Catch:{ Exception -> 0x0241 }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x017f A[SYNTHETIC, Splitter:B:85:0x017f] */
    public final void zza(com.google.android.gms.dynamic.IObjectWrapper r13) {
        /*
            r12 = this;
            r11 = 2
            r3 = 1
            r7 = 0
            r8 = 0
            java.lang.Object r9 = r12.mLock
            monitor-enter(r9)
            r1 = 0
            r12.zzl(r1)     // Catch:{ all -> 0x00b0 }
            java.lang.Object r1 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r13)     // Catch:{ all -> 0x00b0 }
            boolean r2 = r1 instanceof com.google.android.gms.internal.ads.zzpd     // Catch:{ all -> 0x00b0 }
            if (r2 != 0) goto L_0x001a
            java.lang.String r1 = "Not an instance of native engine. This is most likely a transient error"
            com.google.android.gms.internal.ads.zzakb.zzdk(r1)     // Catch:{ all -> 0x00b0 }
            monitor-exit(r9)     // Catch:{ all -> 0x00b0 }
        L_0x0019:
            return
        L_0x001a:
            boolean r2 = r12.zzbjv     // Catch:{ all -> 0x00b0 }
            if (r2 != 0) goto L_0x0033
            android.widget.FrameLayout r2 = r12.zzvh     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x0033
            android.widget.FrameLayout r2 = r12.zzvh     // Catch:{ all -> 0x00b0 }
            android.widget.FrameLayout$LayoutParams r4 = new android.widget.FrameLayout$LayoutParams     // Catch:{ all -> 0x00b0 }
            r5 = 0
            r6 = 0
            r4.<init>(r5, r6)     // Catch:{ all -> 0x00b0 }
            r2.setLayoutParams(r4)     // Catch:{ all -> 0x00b0 }
            android.widget.FrameLayout r2 = r12.zzbjt     // Catch:{ all -> 0x00b0 }
            r2.requestLayout()     // Catch:{ all -> 0x00b0 }
        L_0x0033:
            r2 = 1
            r12.zzbjy = r2     // Catch:{ all -> 0x00b0 }
            com.google.android.gms.internal.ads.zzpd r1 = (com.google.android.gms.internal.ads.zzpd) r1     // Catch:{ all -> 0x00b0 }
            com.google.android.gms.internal.ads.zzoz r2 = r12.zzbij     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x0057
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r2 = com.google.android.gms.internal.ads.zznk.zzbbu     // Catch:{ all -> 0x00b0 }
            com.google.android.gms.internal.ads.zzni r4 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x00b0 }
            java.lang.Object r2 = r4.zzd(r2)     // Catch:{ all -> 0x00b0 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x00b0 }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x0057
            com.google.android.gms.internal.ads.zzoz r2 = r12.zzbij     // Catch:{ all -> 0x00b0 }
            android.widget.FrameLayout r4 = r12.zzbjt     // Catch:{ all -> 0x00b0 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r12.zzbjw     // Catch:{ all -> 0x00b0 }
            r2.zzb(r4, r5)     // Catch:{ all -> 0x00b0 }
        L_0x0057:
            com.google.android.gms.internal.ads.zzoz r2 = r12.zzbij     // Catch:{ all -> 0x00b0 }
            boolean r2 = r2 instanceof com.google.android.gms.internal.ads.zzpd     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x0092
            com.google.android.gms.internal.ads.zzoz r2 = r12.zzbij     // Catch:{ all -> 0x00b0 }
            com.google.android.gms.internal.ads.zzpd r2 = (com.google.android.gms.internal.ads.zzpd) r2     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x0092
            android.content.Context r4 = r2.getContext()     // Catch:{ all -> 0x00b0 }
            if (r4 == 0) goto L_0x0092
            com.google.android.gms.internal.ads.zzaiy r4 = com.google.android.gms.ads.internal.zzbv.zzfh()     // Catch:{ all -> 0x00b0 }
            android.widget.FrameLayout r5 = r12.zzbjt     // Catch:{ all -> 0x00b0 }
            android.content.Context r5 = r5.getContext()     // Catch:{ all -> 0x00b0 }
            boolean r4 = r4.zzu(r5)     // Catch:{ all -> 0x00b0 }
            if (r4 == 0) goto L_0x0092
            com.google.android.gms.internal.ads.zzaix r4 = r2.zzks()     // Catch:{ all -> 0x00b0 }
            if (r4 == 0) goto L_0x0083
            r2 = 0
            r4.zzx(r2)     // Catch:{ all -> 0x00b0 }
        L_0x0083:
            java.lang.ref.WeakReference<com.google.android.gms.internal.ads.zzfp> r2 = r12.zzbkb     // Catch:{ all -> 0x00b0 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x00b0 }
            com.google.android.gms.internal.ads.zzfp r2 = (com.google.android.gms.internal.ads.zzfp) r2     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x0092
            if (r4 == 0) goto L_0x0092
            r2.zzb(r4)     // Catch:{ all -> 0x00b0 }
        L_0x0092:
            com.google.android.gms.internal.ads.zzoz r2 = r12.zzbij     // Catch:{ all -> 0x00b0 }
            boolean r2 = r2 instanceof com.google.android.gms.internal.ads.zzoy     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x00b3
            com.google.android.gms.internal.ads.zzoz r2 = r12.zzbij     // Catch:{ all -> 0x00b0 }
            com.google.android.gms.internal.ads.zzoy r2 = (com.google.android.gms.internal.ads.zzoy) r2     // Catch:{ all -> 0x00b0 }
            boolean r2 = r2.zzkm()     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x00b3
            com.google.android.gms.internal.ads.zzoz r2 = r12.zzbij     // Catch:{ all -> 0x00b0 }
            com.google.android.gms.internal.ads.zzoy r2 = (com.google.android.gms.internal.ads.zzoy) r2     // Catch:{ all -> 0x00b0 }
            r2.zzc(r1)     // Catch:{ all -> 0x00b0 }
        L_0x00a9:
            android.widget.FrameLayout r2 = r12.zzvh     // Catch:{ all -> 0x00b0 }
            if (r2 != 0) goto L_0x00c2
            monitor-exit(r9)     // Catch:{ all -> 0x00b0 }
            goto L_0x0019
        L_0x00b0:
            r1 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x00b0 }
            throw r1
        L_0x00b3:
            r12.zzbij = r1     // Catch:{ all -> 0x00b0 }
            boolean r2 = r1 instanceof com.google.android.gms.internal.ads.zzoy     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x00a9
            r0 = r1
            com.google.android.gms.internal.ads.zzoy r0 = (com.google.android.gms.internal.ads.zzoy) r0     // Catch:{ all -> 0x00b0 }
            r2 = r0
            r4 = 0
            r2.zzc(r4)     // Catch:{ all -> 0x00b0 }
            goto L_0x00a9
        L_0x00c2:
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r2 = com.google.android.gms.internal.ads.zznk.zzbbu     // Catch:{ all -> 0x00b0 }
            com.google.android.gms.internal.ads.zzni r4 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x00b0 }
            java.lang.Object r2 = r4.zzd(r2)     // Catch:{ all -> 0x00b0 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x00b0 }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x00da
            android.widget.FrameLayout r2 = r12.zzvh     // Catch:{ all -> 0x00b0 }
            r4 = 0
            r2.setClickable(r4)     // Catch:{ all -> 0x00b0 }
        L_0x00da:
            android.widget.FrameLayout r2 = r12.zzvh     // Catch:{ all -> 0x00b0 }
            r2.removeAllViews()     // Catch:{ all -> 0x00b0 }
            boolean r5 = r1.zzkj()     // Catch:{ all -> 0x00b0 }
            if (r5 == 0) goto L_0x0279
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r12.zzbjw     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x0218
            r2 = 2
            java.lang.String[] r6 = new java.lang.String[r2]     // Catch:{ all -> 0x00b0 }
            r2 = 0
            java.lang.String r4 = "1098"
            r6[r2] = r4     // Catch:{ all -> 0x00b0 }
            r2 = 1
            java.lang.String r4 = "3011"
            r6[r2] = r4     // Catch:{ all -> 0x00b0 }
            r4 = r8
        L_0x00f7:
            if (r4 >= r11) goto L_0x0218
            r2 = r6[r4]     // Catch:{ all -> 0x00b0 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r10 = r12.zzbjw     // Catch:{ all -> 0x00b0 }
            java.lang.Object r2 = r10.get(r2)     // Catch:{ all -> 0x00b0 }
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x0213
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x00b0 }
            android.view.View r2 = (android.view.View) r2     // Catch:{ all -> 0x00b0 }
        L_0x010b:
            boolean r4 = r2 instanceof android.view.ViewGroup     // Catch:{ all -> 0x00b0 }
            if (r4 == 0) goto L_0x0279
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2     // Catch:{ all -> 0x00b0 }
            r4 = r2
        L_0x0112:
            if (r5 == 0) goto L_0x021b
            if (r4 == 0) goto L_0x021b
            r2 = r3
        L_0x0117:
            android.view.View r3 = r1.zza((android.view.View.OnClickListener) r12, (boolean) r2)     // Catch:{ all -> 0x00b0 }
            r12.zzbjx = r3     // Catch:{ all -> 0x00b0 }
            android.view.View r3 = r12.zzbjx     // Catch:{ all -> 0x00b0 }
            if (r3 == 0) goto L_0x013d
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r3 = r12.zzbjw     // Catch:{ all -> 0x00b0 }
            if (r3 == 0) goto L_0x0133
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r3 = r12.zzbjw     // Catch:{ all -> 0x00b0 }
            java.lang.String r5 = "1007"
            java.lang.ref.WeakReference r6 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x00b0 }
            android.view.View r10 = r12.zzbjx     // Catch:{ all -> 0x00b0 }
            r6.<init>(r10)     // Catch:{ all -> 0x00b0 }
            r3.put(r5, r6)     // Catch:{ all -> 0x00b0 }
        L_0x0133:
            if (r2 == 0) goto L_0x021e
            r4.removeAllViews()     // Catch:{ all -> 0x00b0 }
            android.view.View r2 = r12.zzbjx     // Catch:{ all -> 0x00b0 }
            r4.addView(r2)     // Catch:{ all -> 0x00b0 }
        L_0x013d:
            android.widget.FrameLayout r2 = r12.zzbjt     // Catch:{ all -> 0x00b0 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r3 = r12.zzbjw     // Catch:{ all -> 0x00b0 }
            r4 = 0
            r5 = r12
            r6 = r12
            r1.zza((android.view.View) r2, (java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>>) r3, (java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>>) r4, (android.view.View.OnTouchListener) r5, (android.view.View.OnClickListener) r6)     // Catch:{ all -> 0x00b0 }
            boolean r2 = r12.zzbjv     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x0179
            android.view.View r2 = r12.zzbju     // Catch:{ all -> 0x00b0 }
            if (r2 != 0) goto L_0x0168
            android.view.View r2 = new android.view.View     // Catch:{ all -> 0x00b0 }
            android.widget.FrameLayout r3 = r12.zzbjt     // Catch:{ all -> 0x00b0 }
            android.content.Context r3 = r3.getContext()     // Catch:{ all -> 0x00b0 }
            r2.<init>(r3)     // Catch:{ all -> 0x00b0 }
            r12.zzbju = r2     // Catch:{ all -> 0x00b0 }
            android.view.View r2 = r12.zzbju     // Catch:{ all -> 0x00b0 }
            android.widget.FrameLayout$LayoutParams r3 = new android.widget.FrameLayout$LayoutParams     // Catch:{ all -> 0x00b0 }
            r4 = -1
            r5 = 0
            r3.<init>(r4, r5)     // Catch:{ all -> 0x00b0 }
            r2.setLayoutParams(r3)     // Catch:{ all -> 0x00b0 }
        L_0x0168:
            android.widget.FrameLayout r2 = r12.zzbjt     // Catch:{ all -> 0x00b0 }
            android.view.View r3 = r12.zzbju     // Catch:{ all -> 0x00b0 }
            android.view.ViewParent r3 = r3.getParent()     // Catch:{ all -> 0x00b0 }
            if (r2 == r3) goto L_0x0179
            android.widget.FrameLayout r2 = r12.zzbjt     // Catch:{ all -> 0x00b0 }
            android.view.View r3 = r12.zzbju     // Catch:{ all -> 0x00b0 }
            r2.addView(r3)     // Catch:{ all -> 0x00b0 }
        L_0x0179:
            com.google.android.gms.internal.ads.zzaqw r2 = r1.zzko()     // Catch:{ Exception -> 0x0241 }
        L_0x017d:
            if (r2 == 0) goto L_0x018c
            android.widget.FrameLayout r3 = r12.zzvh     // Catch:{ all -> 0x00b0 }
            if (r3 == 0) goto L_0x018c
            android.widget.FrameLayout r3 = r12.zzvh     // Catch:{ all -> 0x00b0 }
            android.view.View r2 = r2.getView()     // Catch:{ all -> 0x00b0 }
            r3.addView(r2)     // Catch:{ all -> 0x00b0 }
        L_0x018c:
            java.lang.Object r4 = r12.mLock     // Catch:{ all -> 0x00b0 }
            monitor-enter(r4)     // Catch:{ all -> 0x00b0 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r12.zzbjw     // Catch:{ all -> 0x0272 }
            r1.zzf(r2)     // Catch:{ all -> 0x0272 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r12.zzbjw     // Catch:{ all -> 0x0272 }
            if (r2 == 0) goto L_0x0260
            java.lang.String[] r5 = zzbjs     // Catch:{ all -> 0x0272 }
            int r6 = r5.length     // Catch:{ all -> 0x0272 }
            r3 = r8
        L_0x019c:
            if (r3 >= r6) goto L_0x0260
            r2 = r5[r3]     // Catch:{ all -> 0x0272 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r8 = r12.zzbjw     // Catch:{ all -> 0x0272 }
            java.lang.Object r2 = r8.get(r2)     // Catch:{ all -> 0x0272 }
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2     // Catch:{ all -> 0x0272 }
            if (r2 == 0) goto L_0x025b
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0272 }
            android.view.View r2 = (android.view.View) r2     // Catch:{ all -> 0x0272 }
        L_0x01b0:
            boolean r3 = r2 instanceof android.widget.FrameLayout     // Catch:{ all -> 0x0272 }
            if (r3 != 0) goto L_0x0263
            r1.zzkq()     // Catch:{ all -> 0x0272 }
            monitor-exit(r4)     // Catch:{ all -> 0x0272 }
        L_0x01b8:
            android.widget.FrameLayout r2 = r12.zzbjt     // Catch:{ all -> 0x00b0 }
            r1.zzi(r2)     // Catch:{ all -> 0x00b0 }
            android.widget.FrameLayout r1 = r12.zzbjt     // Catch:{ all -> 0x00b0 }
            r12.zzl(r1)     // Catch:{ all -> 0x00b0 }
            com.google.android.gms.internal.ads.zzoz r1 = r12.zzbij     // Catch:{ all -> 0x00b0 }
            android.widget.FrameLayout r2 = r12.zzbjt     // Catch:{ all -> 0x00b0 }
            r1.zzj(r2)     // Catch:{ all -> 0x00b0 }
            com.google.android.gms.internal.ads.zzoz r1 = r12.zzbij     // Catch:{ all -> 0x00b0 }
            boolean r1 = r1 instanceof com.google.android.gms.internal.ads.zzpd     // Catch:{ all -> 0x00b0 }
            if (r1 == 0) goto L_0x0210
            com.google.android.gms.internal.ads.zzoz r1 = r12.zzbij     // Catch:{ all -> 0x00b0 }
            com.google.android.gms.internal.ads.zzpd r1 = (com.google.android.gms.internal.ads.zzpd) r1     // Catch:{ all -> 0x00b0 }
            if (r1 == 0) goto L_0x0210
            android.content.Context r2 = r1.getContext()     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x0210
            com.google.android.gms.internal.ads.zzaiy r2 = com.google.android.gms.ads.internal.zzbv.zzfh()     // Catch:{ all -> 0x00b0 }
            android.widget.FrameLayout r3 = r12.zzbjt     // Catch:{ all -> 0x00b0 }
            android.content.Context r3 = r3.getContext()     // Catch:{ all -> 0x00b0 }
            boolean r2 = r2.zzu(r3)     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x0210
            java.lang.ref.WeakReference<com.google.android.gms.internal.ads.zzfp> r2 = r12.zzbkb     // Catch:{ all -> 0x00b0 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x00b0 }
            com.google.android.gms.internal.ads.zzfp r2 = (com.google.android.gms.internal.ads.zzfp) r2     // Catch:{ all -> 0x00b0 }
            if (r2 != 0) goto L_0x0209
            com.google.android.gms.internal.ads.zzfp r2 = new com.google.android.gms.internal.ads.zzfp     // Catch:{ all -> 0x00b0 }
            android.widget.FrameLayout r3 = r12.zzbjt     // Catch:{ all -> 0x00b0 }
            android.content.Context r3 = r3.getContext()     // Catch:{ all -> 0x00b0 }
            android.widget.FrameLayout r4 = r12.zzbjt     // Catch:{ all -> 0x00b0 }
            r2.<init>(r3, r4)     // Catch:{ all -> 0x00b0 }
            java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x00b0 }
            r3.<init>(r2)     // Catch:{ all -> 0x00b0 }
            r12.zzbkb = r3     // Catch:{ all -> 0x00b0 }
        L_0x0209:
            com.google.android.gms.internal.ads.zzaix r1 = r1.zzks()     // Catch:{ all -> 0x00b0 }
            r2.zza((com.google.android.gms.internal.ads.zzft) r1)     // Catch:{ all -> 0x00b0 }
        L_0x0210:
            monitor-exit(r9)     // Catch:{ all -> 0x00b0 }
            goto L_0x0019
        L_0x0213:
            int r2 = r4 + 1
            r4 = r2
            goto L_0x00f7
        L_0x0218:
            r2 = r7
            goto L_0x010b
        L_0x021b:
            r2 = r8
            goto L_0x0117
        L_0x021e:
            android.content.Context r2 = r1.getContext()     // Catch:{ all -> 0x00b0 }
            com.google.android.gms.ads.formats.AdChoicesView r3 = new com.google.android.gms.ads.formats.AdChoicesView     // Catch:{ all -> 0x00b0 }
            r3.<init>(r2)     // Catch:{ all -> 0x00b0 }
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams     // Catch:{ all -> 0x00b0 }
            r4 = -1
            r5 = -1
            r2.<init>(r4, r5)     // Catch:{ all -> 0x00b0 }
            r3.setLayoutParams(r2)     // Catch:{ all -> 0x00b0 }
            android.view.View r2 = r12.zzbjx     // Catch:{ all -> 0x00b0 }
            r3.addView(r2)     // Catch:{ all -> 0x00b0 }
            android.widget.FrameLayout r2 = r12.zzvh     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x013d
            android.widget.FrameLayout r2 = r12.zzvh     // Catch:{ all -> 0x00b0 }
            r2.addView(r3)     // Catch:{ all -> 0x00b0 }
            goto L_0x013d
        L_0x0241:
            r2 = move-exception
            com.google.android.gms.ads.internal.zzbv.zzem()     // Catch:{ all -> 0x00b0 }
            boolean r3 = com.google.android.gms.internal.ads.zzakq.zzrp()     // Catch:{ all -> 0x00b0 }
            if (r3 == 0) goto L_0x0253
            java.lang.String r2 = "Privileged processes cannot create HTML overlays."
            com.google.android.gms.internal.ads.zzakb.zzdk(r2)     // Catch:{ all -> 0x00b0 }
            r2 = r7
            goto L_0x017d
        L_0x0253:
            java.lang.String r3 = "Error obtaining overlay."
            com.google.android.gms.internal.ads.zzakb.zzb(r3, r2)     // Catch:{ all -> 0x00b0 }
            r2 = r7
            goto L_0x017d
        L_0x025b:
            int r2 = r3 + 1
            r3 = r2
            goto L_0x019c
        L_0x0260:
            r2 = r7
            goto L_0x01b0
        L_0x0263:
            com.google.android.gms.internal.ads.zzpo r3 = new com.google.android.gms.internal.ads.zzpo     // Catch:{ all -> 0x0272 }
            r3.<init>(r12, r2)     // Catch:{ all -> 0x0272 }
            boolean r5 = r1 instanceof com.google.android.gms.internal.ads.zzoy     // Catch:{ all -> 0x0272 }
            if (r5 == 0) goto L_0x0275
            r1.zzb((android.view.View) r2, (com.google.android.gms.internal.ads.zzox) r3)     // Catch:{ all -> 0x0272 }
        L_0x026f:
            monitor-exit(r4)     // Catch:{ all -> 0x0272 }
            goto L_0x01b8
        L_0x0272:
            r1 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0272 }
            throw r1     // Catch:{ all -> 0x00b0 }
        L_0x0275:
            r1.zza((android.view.View) r2, (com.google.android.gms.internal.ads.zzox) r3)     // Catch:{ all -> 0x0272 }
            goto L_0x026f
        L_0x0279:
            r4 = r7
            goto L_0x0112
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpn.zza(com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    public final IObjectWrapper zzak(String str) {
        View view = null;
        synchronized (this.mLock) {
            if (this.zzbjw == null) {
                return null;
            }
            WeakReference weakReference = this.zzbjw.get(str);
            if (weakReference != null) {
                view = (View) weakReference.get();
            }
            IObjectWrapper wrap = ObjectWrapper.wrap(view);
            return wrap;
        }
    }

    public final void zzb(IObjectWrapper iObjectWrapper, int i) {
        zzfp zzfp;
        if (!(!zzbv.zzfh().zzu(this.zzbjt.getContext()) || this.zzbkb == null || (zzfp = (zzfp) this.zzbkb.get()) == null)) {
            zzfp.zzgm();
        }
        zzkt();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(java.lang.String r5, com.google.android.gms.dynamic.IObjectWrapper r6) {
        /*
            r4 = this;
            java.lang.Object r0 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r6)
            android.view.View r0 = (android.view.View) r0
            java.lang.Object r1 = r4.mLock
            monitor-enter(r1)
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r4.zzbjw     // Catch:{ all -> 0x0018 }
            if (r2 != 0) goto L_0x000f
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
        L_0x000e:
            return
        L_0x000f:
            if (r0 != 0) goto L_0x001b
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r0 = r4.zzbjw     // Catch:{ all -> 0x0018 }
            r0.remove(r5)     // Catch:{ all -> 0x0018 }
        L_0x0016:
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            goto L_0x000e
        L_0x0018:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            throw r0
        L_0x001b:
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r4.zzbjw     // Catch:{ all -> 0x0018 }
            java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0018 }
            r3.<init>(r0)     // Catch:{ all -> 0x0018 }
            r2.put(r5, r3)     // Catch:{ all -> 0x0018 }
            java.lang.String r2 = "1098"
            boolean r2 = r2.equals(r5)     // Catch:{ all -> 0x0018 }
            if (r2 != 0) goto L_0x0035
            java.lang.String r2 = "3011"
            boolean r2 = r2.equals(r5)     // Catch:{ all -> 0x0018 }
            if (r2 == 0) goto L_0x0037
        L_0x0035:
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            goto L_0x000e
        L_0x0037:
            r0.setOnTouchListener(r4)     // Catch:{ all -> 0x0018 }
            r2 = 1
            r0.setClickable(r2)     // Catch:{ all -> 0x0018 }
            r0.setOnClickListener(r4)     // Catch:{ all -> 0x0018 }
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpn.zzb(java.lang.String, com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    public final void zzc(IObjectWrapper iObjectWrapper) {
        this.zzbij.setClickConfirmingView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }
}
