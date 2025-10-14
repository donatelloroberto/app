package com.google.android.gms.internal.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
@VisibleForTesting
final class zzasq extends zzasv implements ViewTreeObserver.OnGlobalLayoutListener, DownloadListener, zzaqw, zzuo {
    private zzamt zzaee;
    private final WindowManager zzaeu;
    @Nullable
    private final zzci zzbjc;
    private int zzbwy = -1;
    private int zzbwz = -1;
    private int zzbxb = -1;
    private int zzbxc = -1;
    @GuardedBy("this")
    private String zzchp = "";
    private zznv zzdad;
    private final zzbo zzddb;
    @GuardedBy("this")
    private zzd zzddg;
    @GuardedBy("this")
    private zzasi zzddh;
    @GuardedBy("this")
    private boolean zzddi;
    @GuardedBy("this")
    private boolean zzddk;
    @GuardedBy("this")
    private boolean zzddl;
    @GuardedBy("this")
    private int zzddm;
    @GuardedBy("this")
    private boolean zzddn = true;
    @GuardedBy("this")
    private boolean zzddo = false;
    @GuardedBy("this")
    private zzarl zzddp;
    @GuardedBy("this")
    private boolean zzddq;
    @GuardedBy("this")
    private boolean zzddr;
    @GuardedBy("this")
    private zzox zzdds;
    @GuardedBy("this")
    private int zzddt;
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public int zzddu;
    private zznv zzddv;
    private zznv zzddw;
    private zznw zzddx;
    private WeakReference<View.OnClickListener> zzddy;
    @GuardedBy("this")
    private zzd zzddz;
    private Map<String, zzaqh> zzdeb;
    private zzasj zzdet;
    private float zzdeu;
    @GuardedBy("this")
    private String zzus;
    private final zzw zzwc;
    private final zzang zzyf;

    @VisibleForTesting
    private zzasq(zzash zzash, zzasi zzasi, String str, boolean z, boolean z2, @Nullable zzci zzci, zzang zzang, zznx zznx, zzbo zzbo, zzw zzw, zzhs zzhs) {
        super(zzash);
        this.zzddh = zzasi;
        this.zzus = str;
        this.zzddk = z;
        this.zzddm = -1;
        this.zzbjc = zzci;
        this.zzyf = zzang;
        this.zzddb = zzbo;
        this.zzwc = zzw;
        this.zzaeu = (WindowManager) getContext().getSystemService("window");
        this.zzaee = new zzamt(zzvv().zzto(), this, this, (ViewTreeObserver.OnScrollChangedListener) null);
        zzbv.zzek().zza((Context) zzash, zzang.zzcw, getSettings());
        setDownloadListener(this);
        this.zzdeu = zzvv().getResources().getDisplayMetrics().density;
        zzvk();
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            addJavascriptInterface(zzaro.zzk(this), "googleAdsJsInterface");
        }
        zzvo();
        this.zzddx = new zznw(new zznx(true, "make_wv", this.zzus));
        this.zzddx.zzji().zzc(zznx);
        this.zzdad = zznq.zzb(this.zzddx.zzji());
        this.zzddx.zza("native:view_create", this.zzdad);
        this.zzddw = null;
        this.zzddv = null;
        zzbv.zzem().zzaw(zzash);
    }

    private final void zzal(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("isVisible", z ? "1" : "0");
        zzup.zza((zzuo) this, "onAdVisibilityChanged", (Map) hashMap);
    }

    static zzasq zzc(Context context, zzasi zzasi, String str, boolean z, boolean z2, @Nullable zzci zzci, zzang zzang, zznx zznx, zzbo zzbo, zzw zzw, zzhs zzhs) {
        return new zzasq(new zzash(context), zzasi, str, z, z2, zzci, zzang, zznx, zzbo, zzw, zzhs);
    }

    private final boolean zzvh() {
        int i;
        int i2;
        if (!this.zzdet.zzfz() && !this.zzdet.zzuu()) {
            return false;
        }
        zzbv.zzek();
        DisplayMetrics zza = zzakk.zza(this.zzaeu);
        zzkb.zzif();
        int zzb = zzamu.zzb(zza, zza.widthPixels);
        zzkb.zzif();
        int zzb2 = zzamu.zzb(zza, zza.heightPixels);
        Activity zzto = zzvv().zzto();
        if (zzto == null || zzto.getWindow() == null) {
            i = zzb2;
            i2 = zzb;
        } else {
            zzbv.zzek();
            int[] zzf = zzakk.zzf(zzto);
            zzkb.zzif();
            i2 = zzamu.zzb(zza, zzf[0]);
            zzkb.zzif();
            i = zzamu.zzb(zza, zzf[1]);
        }
        if (this.zzbwy == zzb && this.zzbwz == zzb2 && this.zzbxb == i2 && this.zzbxc == i) {
            return false;
        }
        boolean z = (this.zzbwy == zzb && this.zzbwz == zzb2) ? false : true;
        this.zzbwy = zzb;
        this.zzbwz = zzb2;
        this.zzbxb = i2;
        this.zzbxc = i;
        new zzaal(this).zza(zzb, zzb2, i2, i, zza.density, this.zzaeu.getDefaultDisplay().getRotation());
        return z;
    }

    private final void zzvj() {
        zznq.zza(this.zzddx.zzji(), this.zzdad, "aeh2");
    }

    private final synchronized void zzvk() {
        if (this.zzddk || this.zzddh.zzvs()) {
            zzakb.zzck("Enabling hardware acceleration on an overlay.");
            zzvm();
        } else if (Build.VERSION.SDK_INT < 18) {
            zzakb.zzck("Disabling hardware acceleration on an AdView.");
            zzvl();
        } else {
            zzakb.zzck("Enabling hardware acceleration on an AdView.");
            zzvm();
        }
    }

    private final synchronized void zzvl() {
        if (!this.zzddl) {
            zzbv.zzem().zzz(this);
        }
        this.zzddl = true;
    }

    private final synchronized void zzvm() {
        if (this.zzddl) {
            zzbv.zzem().zzy(this);
        }
        this.zzddl = false;
    }

    private final synchronized void zzvn() {
        this.zzdeb = null;
    }

    private final void zzvo() {
        zznx zzji;
        if (this.zzddx != null && (zzji = this.zzddx.zzji()) != null && zzbv.zzeo().zzpy() != null) {
            zzbv.zzeo().zzpy().zza(zzji);
        }
    }

    public final View.OnClickListener getOnClickListener() {
        return (View.OnClickListener) this.zzddy.get();
    }

    public final synchronized int getRequestedOrientation() {
        return this.zzddm;
    }

    public final View getView() {
        return this;
    }

    public final WebView getWebView() {
        return this;
    }

    /* access modifiers changed from: protected */
    public final synchronized void onAttachedToWindow() {
        boolean z;
        super.onAttachedToWindow();
        if (!isDestroyed()) {
            this.zzaee.onAttachedToWindow();
        }
        boolean z2 = this.zzddq;
        if (this.zzdet == null || !this.zzdet.zzuu()) {
            z = z2;
        } else {
            if (!this.zzddr) {
                ViewTreeObserver.OnGlobalLayoutListener zzuv = this.zzdet.zzuv();
                if (zzuv != null) {
                    zzbv.zzfg();
                    if (this == null) {
                        throw null;
                    }
                    zzaor.zza((View) this, zzuv);
                }
                ViewTreeObserver.OnScrollChangedListener zzuw = this.zzdet.zzuw();
                if (zzuw != null) {
                    zzbv.zzfg();
                    if (this == null) {
                        throw null;
                    }
                    zzaor.zza((View) this, zzuw);
                }
                this.zzddr = true;
            }
            zzvh();
            z = true;
        }
        zzal(z);
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        synchronized (this) {
            if (!isDestroyed()) {
                this.zzaee.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
            if (this.zzddr && this.zzdet != null && this.zzdet.zzuu() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                ViewTreeObserver.OnGlobalLayoutListener zzuv = this.zzdet.zzuv();
                if (zzuv != null) {
                    zzbv.zzem().zza(getViewTreeObserver(), zzuv);
                }
                ViewTreeObserver.OnScrollChangedListener zzuw = this.zzdet.zzuw();
                if (zzuw != null) {
                    getViewTreeObserver().removeOnScrollChangedListener(zzuw);
                }
                this.zzddr = false;
            }
        }
        zzal(false);
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            zzbv.zzek();
            zzakk.zza(getContext(), intent);
        } catch (ActivityNotFoundException e) {
            zzakb.zzck(new StringBuilder(String.valueOf(str).length() + 51 + String.valueOf(str4).length()).append("Couldn't find an Activity to view url/mimetype: ").append(str).append(" / ").append(str4).toString());
        }
    }

    /* access modifiers changed from: protected */
    @TargetApi(21)
    public final void onDraw(Canvas canvas) {
        if (Build.VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
            super.onDraw(canvas);
            if (this.zzdet != null && this.zzdet.zzve() != null) {
                this.zzdet.zzve().zzda();
            }
        }
    }

    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzaxx)).booleanValue()) {
            float axisValue = motionEvent.getAxisValue(9);
            float axisValue2 = motionEvent.getAxisValue(10);
            if (motionEvent.getActionMasked() == 8 && ((axisValue > 0.0f && !canScrollVertically(-1)) || ((axisValue < 0.0f && !canScrollVertically(1)) || ((axisValue2 > 0.0f && !canScrollHorizontally(-1)) || (axisValue2 < 0.0f && !canScrollHorizontally(1)))))) {
                return false;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public final void onGlobalLayout() {
        boolean zzvh = zzvh();
        zzd zzub = zzub();
        if (zzub != null && zzvh) {
            zzub.zznn();
        }
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"DrawAllocation"})
    public final synchronized void onMeasure(int i, int i2) {
        boolean z;
        int size;
        int i3;
        int i4;
        if (isDestroyed()) {
            setMeasuredDimension(0, 0);
        } else if (isInEditMode() || this.zzddk || this.zzddh.zzvt()) {
            super.onMeasure(i, i2);
        } else if (this.zzddh.zzvu()) {
            zzarl zztm = zztm();
            float aspectRatio = zztm != null ? zztm.getAspectRatio() : 0.0f;
            if (aspectRatio == 0.0f) {
                super.onMeasure(i, i2);
            } else {
                int size2 = View.MeasureSpec.getSize(i);
                int size3 = View.MeasureSpec.getSize(i2);
                int i5 = (int) (((float) size3) * aspectRatio);
                int i6 = (int) (((float) size2) / aspectRatio);
                if (size3 == 0 && i6 != 0) {
                    i4 = (int) (aspectRatio * ((float) i6));
                    i3 = i6;
                    size3 = i6;
                } else if (size2 != 0 || i5 == 0) {
                    i3 = i6;
                    i4 = i5;
                } else {
                    i3 = (int) (((float) i5) / aspectRatio);
                    i4 = i5;
                    size2 = i5;
                }
                setMeasuredDimension(Math.min(i4, size2), Math.min(i3, size3));
            }
        } else if (this.zzddh.isFluid()) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbch)).booleanValue() || !PlatformVersion.isAtLeastJellyBeanMR1()) {
                super.onMeasure(i, i2);
            } else {
                zza("/contentHeight", (zzv<? super zzaqw>) new zzasr(this));
                zzbe("(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();");
                int size4 = View.MeasureSpec.getSize(i);
                switch (this.zzddu) {
                    case -1:
                        size = View.MeasureSpec.getSize(i2);
                        break;
                    default:
                        size = (int) (((float) this.zzddu) * this.zzdeu);
                        break;
                }
                setMeasuredDimension(size4, size);
            }
        } else if (this.zzddh.zzvs()) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            this.zzaeu.getDefaultDisplay().getMetrics(displayMetrics);
            setMeasuredDimension(displayMetrics.widthPixels, displayMetrics.heightPixels);
        } else {
            int mode = View.MeasureSpec.getMode(i);
            int size5 = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size6 = View.MeasureSpec.getSize(i2);
            int i7 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size5 : Integer.MAX_VALUE;
            int i8 = (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) ? size6 : Integer.MAX_VALUE;
            boolean z2 = this.zzddh.widthPixels > i7 || this.zzddh.heightPixels > i8;
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbfe)).booleanValue()) {
                z = ((float) this.zzddh.widthPixels) / this.zzdeu <= ((float) i7) / this.zzdeu && ((float) this.zzddh.heightPixels) / this.zzdeu <= ((float) i8) / this.zzdeu;
                if (!z2) {
                    z = z2;
                }
            } else {
                z = z2;
            }
            if (z) {
                zzakb.zzdk(new StringBuilder(103).append("Not enough space to show ad. Needs ").append((int) (((float) this.zzddh.widthPixels) / this.zzdeu)).append("x").append((int) (((float) this.zzddh.heightPixels) / this.zzdeu)).append(" dp, but only has ").append((int) (((float) size5) / this.zzdeu)).append("x").append((int) (((float) size6) / this.zzdeu)).append(" dp.").toString());
                if (getVisibility() != 8) {
                    setVisibility(4);
                }
                setMeasuredDimension(0, 0);
            } else {
                if (getVisibility() != 8) {
                    setVisibility(0);
                }
                setMeasuredDimension(this.zzddh.widthPixels, this.zzddh.heightPixels);
            }
        }
    }

    public final void onPause() {
        try {
            if (PlatformVersion.isAtLeastHoneycomb()) {
                super.onPause();
            }
        } catch (Exception e) {
            zzakb.zzb("Could not pause webview.", e);
        }
    }

    public final void onResume() {
        try {
            if (PlatformVersion.isAtLeastHoneycomb()) {
                super.onResume();
            }
        } catch (Exception e) {
            zzakb.zzb("Could not resume webview.", e);
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.zzdet.zzuu()) {
            synchronized (this) {
                if (this.zzdds != null) {
                    this.zzdds.zzc(motionEvent);
                }
            }
        } else if (this.zzbjc != null) {
            this.zzbjc.zza(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zzddy = new WeakReference<>(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    public final synchronized void setRequestedOrientation(int i) {
        this.zzddm = i;
        if (this.zzddg != null) {
            this.zzddg.setRequestedOrientation(this.zzddm);
        }
    }

    public final void stopLoading() {
        try {
            super.stopLoading();
        } catch (Exception e) {
            zzakb.zzb("Could not stop loading webview.", e);
        }
    }

    public final void zza(zzc zzc) {
        this.zzdet.zza(zzc);
    }

    public final synchronized void zza(zzd zzd) {
        this.zzddg = zzd;
    }

    public final synchronized void zza(zzarl zzarl) {
        if (this.zzddp != null) {
            zzakb.e("Attempt to create multiple AdWebViewVideoControllers.");
        } else {
            this.zzddp = zzarl;
        }
    }

    public final synchronized void zza(zzasi zzasi) {
        this.zzddh = zzasi;
        requestLayout();
    }

    public final void zza(zzasj zzasj) {
        this.zzdet = zzasj;
    }

    public final void zza(zzfs zzfs) {
        synchronized (this) {
            this.zzddq = zzfs.zztg;
        }
        zzal(zzfs.zztg);
    }

    public final void zza(String str, zzv<? super zzaqw> zzv) {
        if (this.zzdet != null) {
            this.zzdet.zza(str, zzv);
        }
    }

    public final void zza(String str, Predicate<zzv<? super zzaqw>> predicate) {
        if (this.zzdet != null) {
            this.zzdet.zza(str, predicate);
        }
    }

    public final void zza(String str, Map map) {
        zzup.zza((zzuo) this, str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        zzup.zzb(this, str, jSONObject);
    }

    public final void zza(boolean z, int i) {
        this.zzdet.zza(z, i);
    }

    public final void zza(boolean z, int i, String str) {
        this.zzdet.zza(z, i, str);
    }

    public final void zza(boolean z, int i, String str, String str2) {
        this.zzdet.zza(z, i, str, str2);
    }

    public final void zzah(boolean z) {
        this.zzdet.zzah(z);
    }

    public final void zzai(int i) {
        if (i == 0) {
            zznq.zza(this.zzddx.zzji(), this.zzdad, "aebb2");
        }
        zzvj();
        if (this.zzddx.zzji() != null) {
            this.zzddx.zzji().zze("close_type", String.valueOf(i));
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("closetype", String.valueOf(i));
        hashMap.put("version", this.zzyf.zzcw);
        zzup.zza((zzuo) this, "onhide", (Map) hashMap);
    }

    public final synchronized void zzai(boolean z) {
        boolean z2 = z != this.zzddk;
        this.zzddk = z;
        zzvk();
        if (z2) {
            new zzaal(this).zzby(z ? "expanded" : PushConstants.SOUND_DEFAULT);
        }
    }

    public final synchronized void zzaj(boolean z) {
        this.zzddn = z;
    }

    public final synchronized void zzak(boolean z) {
        this.zzddt = (z ? 1 : -1) + this.zzddt;
        if (this.zzddt <= 0 && this.zzddg != null) {
            this.zzddg.zznq();
        }
    }

    /* access modifiers changed from: protected */
    public final synchronized void zzam(boolean z) {
        if (!z) {
            zzvo();
            this.zzaee.zzsd();
            if (this.zzddg != null) {
                this.zzddg.close();
                this.zzddg.onDestroy();
                this.zzddg = null;
            }
        }
        this.zzdet.reset();
        zzbv.zzff();
        zzaqg.zzb((zzapw) this);
        zzvn();
    }

    public final synchronized void zzb(zzd zzd) {
        this.zzddz = zzd;
    }

    public final synchronized void zzb(zzox zzox) {
        this.zzdds = zzox;
    }

    public final void zzb(String str, zzv<? super zzaqw> zzv) {
        if (this.zzdet != null) {
            this.zzdet.zzb(str, zzv);
        }
    }

    public final void zzb(String str, JSONObject jSONObject) {
        zzup.zza((zzuo) this, str, jSONObject);
    }

    public final synchronized void zzbe(String str) {
        if (!isDestroyed()) {
            super.zzbe(str);
        } else {
            zzakb.zzdk("The webview is destroyed. Ignoring action.");
        }
    }

    public final zzw zzbi() {
        return this.zzwc;
    }

    public final void zzbm(Context context) {
        zzvv().setBaseContext(context);
        this.zzaee.zzi(zzvv().zzto());
    }

    public final synchronized void zzc(String str, String str2, @Nullable String str3) {
        String str4;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzaya)).booleanValue()) {
            str4 = zzarx.zzb(str2, zzarx.zzvp());
        } else {
            str4 = str2;
        }
        super.loadDataWithBaseURL(str, str4, WebRequest.CONTENT_TYPE_HTML, WebRequest.CHARSET_UTF_8, str3);
    }

    public final synchronized void zzcl() {
        this.zzddo = true;
        if (this.zzddb != null) {
            this.zzddb.zzcl();
        }
    }

    public final synchronized void zzcm() {
        this.zzddo = false;
        if (this.zzddb != null) {
            this.zzddb.zzcm();
        }
    }

    public final synchronized void zzdr(String str) {
        if (str == null) {
            str = "";
        }
        this.zzchp = str;
    }

    public final void zzf(String str, String str2) {
        zzup.zza((zzuo) this, str, str2);
    }

    public final void zzno() {
        if (this.zzddv == null) {
            zznq.zza(this.zzddx.zzji(), this.zzdad, "aes2");
            this.zzddv = zznq.zzb(this.zzddx.zzji());
            this.zzddx.zza("native:view_show", this.zzddv);
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.zzyf.zzcw);
        zzup.zza((zzuo) this, "onshow", (Map) hashMap);
    }

    public final void zznp() {
        zzd zzub = zzub();
        if (zzub != null) {
            zzub.zznp();
        }
    }

    public final synchronized String zzol() {
        return this.zzchp;
    }

    public final zzapn zztl() {
        return null;
    }

    public final synchronized zzarl zztm() {
        return this.zzddp;
    }

    public final zznv zztn() {
        return this.zzdad;
    }

    public final Activity zzto() {
        return zzvv().zzto();
    }

    public final zznw zztp() {
        return this.zzddx;
    }

    public final zzang zztq() {
        return this.zzyf;
    }

    public final int zztr() {
        return getMeasuredHeight();
    }

    public final int zzts() {
        return getMeasuredWidth();
    }

    public final void zzty() {
        zzvj();
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.zzyf.zzcw);
        zzup.zza((zzuo) this, "onhide", (Map) hashMap);
    }

    public final void zztz() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(zzbv.zzfj().zzdp()));
        hashMap.put("app_volume", String.valueOf(zzbv.zzfj().zzdo()));
        hashMap.put("device_volume", String.valueOf(zzalb.zzay(getContext())));
        zzup.zza((zzuo) this, MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, (Map) hashMap);
    }

    public final synchronized void zzu(boolean z) {
        if (this.zzddg != null) {
            this.zzddg.zza(this.zzdet.zzfz(), z);
        } else {
            this.zzddi = z;
        }
    }

    public final Context zzua() {
        return zzvv().zzua();
    }

    public final synchronized zzd zzub() {
        return this.zzddg;
    }

    public final synchronized zzd zzuc() {
        return this.zzddz;
    }

    public final synchronized zzasi zzud() {
        return this.zzddh;
    }

    public final synchronized String zzue() {
        return this.zzus;
    }

    public final /* synthetic */ zzasc zzuf() {
        return this.zzdet;
    }

    public final WebViewClient zzug() {
        return this.zzdfb;
    }

    public final synchronized boolean zzuh() {
        return this.zzddi;
    }

    public final zzci zzui() {
        return this.zzbjc;
    }

    public final synchronized boolean zzuj() {
        return this.zzddk;
    }

    public final synchronized boolean zzul() {
        return this.zzddn;
    }

    public final synchronized boolean zzum() {
        return this.zzddo;
    }

    public final synchronized boolean zzun() {
        return this.zzddt > 0;
    }

    public final void zzuo() {
        this.zzaee.zzsc();
    }

    public final void zzup() {
        if (this.zzddw == null) {
            this.zzddw = zznq.zzb(this.zzddx.zzji());
            this.zzddx.zza("native:view_load", this.zzddw);
        }
    }

    public final synchronized zzox zzuq() {
        return this.zzdds;
    }

    public final void zzur() {
        setBackgroundColor(0);
    }

    public final void zzus() {
        zzakb.v("Cannot add text view to inner AdWebView");
    }
}
