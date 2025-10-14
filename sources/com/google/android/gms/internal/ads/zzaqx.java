package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.gmsg.zza;
import com.google.android.gms.ads.internal.gmsg.zzab;
import com.google.android.gms.ads.internal.gmsg.zzac;
import com.google.android.gms.ads.internal.gmsg.zzad;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.gmsg.zzf;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.gmsg.zzy;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzx;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@zzadh
@ParametersAreNonnullByDefault
@VisibleForTesting
public class zzaqx extends WebViewClient implements zzasc {
    private static final String[] zzdbo = {"UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
    private static final String[] zzdbp = {"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};
    private final Object mLock;
    @GuardedBy("mLock")
    private boolean zzaek;
    private zzjd zzapt;
    private zzb zzbll;
    private zzd zzblm;
    private zzz zzbmu;
    private zzx zzbmw;
    private zzaab zzbmx;
    private zzaam zzbmy;
    private zzt zzbnb;
    private zzn zzbnc;
    private zzaqw zzbnd;
    private final HashMap<String, List<zzv<? super zzaqw>>> zzdbq;
    private zzasd zzdbr;
    private zzase zzdbs;
    private zzasf zzdbt;
    private boolean zzdbu;
    @GuardedBy("mLock")
    private boolean zzdbv;
    @GuardedBy("mLock")
    private ViewTreeObserver.OnGlobalLayoutListener zzdbw;
    @GuardedBy("mLock")
    private ViewTreeObserver.OnScrollChangedListener zzdbx;
    @GuardedBy("mLock")
    private boolean zzdby;
    private final zzaak zzdbz;
    private zzasg zzdca;
    private boolean zzdcb;
    private boolean zzdcc;
    private int zzdcd;
    private View.OnAttachStateChangeListener zzdce;
    @Nullable
    protected zzait zzxd;

    public zzaqx(zzaqw zzaqw, boolean z) {
        this(zzaqw, z, new zzaak(zzaqw, zzaqw.zzua(), new zzmw(zzaqw.getContext())), (zzaab) null);
    }

    @VisibleForTesting
    private zzaqx(zzaqw zzaqw, boolean z, zzaak zzaak, zzaab zzaab) {
        this.zzdbq = new HashMap<>();
        this.mLock = new Object();
        this.zzdbu = false;
        this.zzbnd = zzaqw;
        this.zzaek = z;
        this.zzdbz = zzaak;
        this.zzbmx = null;
    }

    /* access modifiers changed from: private */
    public final void zza(View view, zzait zzait, int i) {
        if (zzait.zzph() && i > 0) {
            zzait.zzr(view);
            if (zzait.zzph()) {
                zzakk.zzcrm.postDelayed(new zzaqz(this, view, zzait, i), 100);
            }
        }
    }

    private final void zza(AdOverlayInfoParcel adOverlayInfoParcel) {
        boolean z = false;
        boolean zznf = this.zzbmx != null ? this.zzbmx.zznf() : false;
        zzbv.zzei();
        Context context = this.zzbnd.getContext();
        if (!zznf) {
            z = true;
        }
        zzl.zza(context, adOverlayInfoParcel, z);
        if (this.zzxd != null) {
            String str = adOverlayInfoParcel.url;
            if (str == null && adOverlayInfoParcel.zzbyl != null) {
                str = adOverlayInfoParcel.zzbyl.url;
            }
            this.zzxd.zzcf(str);
        }
    }

    private final void zzd(Context context, String str, String str2, String str3) {
        String str4;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzazy)).booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putString(NotificationCompat.CATEGORY_ERROR, str);
            bundle.putString("code", str2);
            if (!TextUtils.isEmpty(str3)) {
                Uri parse = Uri.parse(str3);
                if (parse.getHost() != null) {
                    str4 = parse.getHost();
                    bundle.putString("host", str4);
                    zzbv.zzek().zza(context, this.zzbnd.zztq().zzcw, "gmob-apps", bundle, true);
                }
            }
            str4 = "";
            bundle.putString("host", str4);
            zzbv.zzek().zza(context, this.zzbnd.zztq().zzcw, "gmob-apps", bundle, true);
        }
    }

    private final WebResourceResponse zze(String str, Map<String, String> map) throws IOException {
        HttpURLConnection httpURLConnection;
        int i = 0;
        URL url = new URL(str);
        while (true) {
            int i2 = i + 1;
            if (i2 <= 20) {
                URLConnection openConnection = url.openConnection();
                openConnection.setConnectTimeout(10000);
                openConnection.setReadTimeout(10000);
                for (Map.Entry next : map.entrySet()) {
                    openConnection.addRequestProperty((String) next.getKey(), (String) next.getValue());
                }
                if (!(openConnection instanceof HttpURLConnection)) {
                    throw new IOException("Invalid protocol.");
                }
                httpURLConnection = (HttpURLConnection) openConnection;
                zzbv.zzek().zza(this.zzbnd.getContext(), this.zzbnd.zztq().zzcw, false, httpURLConnection);
                zzamy zzamy = new zzamy();
                zzamy.zza(httpURLConnection, (byte[]) null);
                int responseCode = httpURLConnection.getResponseCode();
                zzamy.zza(httpURLConnection, responseCode);
                if (responseCode < 300 || responseCode >= 400) {
                    zzbv.zzek();
                } else {
                    String headerField = httpURLConnection.getHeaderField("Location");
                    if (headerField == null) {
                        throw new IOException("Missing Location header in redirect");
                    }
                    URL url2 = new URL(url, headerField);
                    String protocol = url2.getProtocol();
                    if (protocol == null) {
                        zzakb.zzdk("Protocol is null");
                        return null;
                    } else if (protocol.equals("http") || protocol.equals("https")) {
                        String valueOf = String.valueOf(headerField);
                        zzakb.zzck(valueOf.length() != 0 ? "Redirecting to ".concat(valueOf) : new String("Redirecting to "));
                        httpURLConnection.disconnect();
                        i = i2;
                        url = url2;
                    } else {
                        String valueOf2 = String.valueOf(protocol);
                        zzakb.zzdk(valueOf2.length() != 0 ? "Unsupported scheme: ".concat(valueOf2) : new String("Unsupported scheme: "));
                        return null;
                    }
                }
            } else {
                throw new IOException(new StringBuilder(32).append("Too many redirects (20)").toString());
            }
        }
        zzbv.zzek();
        return zzakk.zzb(httpURLConnection);
    }

    private final void zzi(Uri uri) {
        String path = uri.getPath();
        List<zzv> list = this.zzdbq.get(path);
        if (list != null) {
            zzbv.zzek();
            Map<String, String> zzg = zzakk.zzg(uri);
            if (zzakb.isLoggable(2)) {
                String valueOf = String.valueOf(path);
                zzakb.v(valueOf.length() != 0 ? "Received GMSG: ".concat(valueOf) : new String("Received GMSG: "));
                for (String next : zzg.keySet()) {
                    String str = zzg.get(next);
                    zzakb.v(new StringBuilder(String.valueOf(next).length() + 4 + String.valueOf(str).length()).append("  ").append(next).append(": ").append(str).toString());
                }
            }
            for (zzv zza : list) {
                zza.zza(this.zzbnd, zzg);
            }
            return;
        }
        String valueOf2 = String.valueOf(uri);
        zzakb.v(new StringBuilder(String.valueOf(valueOf2).length() + 32).append("No GMSG handler found for GMSG: ").append(valueOf2).toString());
    }

    private final void zzuy() {
        if (this.zzdce != null) {
            this.zzbnd.getView().removeOnAttachStateChangeListener(this.zzdce);
        }
    }

    private final void zzvd() {
        if (this.zzdbr != null && ((this.zzdcb && this.zzdcd <= 0) || this.zzdcc)) {
            this.zzdbr.zze(!this.zzdcc);
            this.zzdbr = null;
        }
        this.zzbnd.zzup();
    }

    public final void onLoadResource(WebView webView, String str) {
        String valueOf = String.valueOf(str);
        zzakb.v(valueOf.length() != 0 ? "Loading resource: ".concat(valueOf) : new String("Loading resource: "));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzi(parse);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        r2.zzdbs.zzly();
        r2.zzdbs = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
        zzvd();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0018, code lost:
        r2.zzdcb = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        if (r2.zzdbs == null) goto L_0x0027;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onPageFinished(android.webkit.WebView r3, java.lang.String r4) {
        /*
            r2 = this;
            java.lang.Object r1 = r2.mLock
            monitor-enter(r1)
            com.google.android.gms.internal.ads.zzaqw r0 = r2.zzbnd     // Catch:{ all -> 0x002b }
            boolean r0 = r0.isDestroyed()     // Catch:{ all -> 0x002b }
            if (r0 == 0) goto L_0x0017
            java.lang.String r0 = "Blank page loaded, 1..."
            com.google.android.gms.internal.ads.zzakb.v(r0)     // Catch:{ all -> 0x002b }
            com.google.android.gms.internal.ads.zzaqw r0 = r2.zzbnd     // Catch:{ all -> 0x002b }
            r0.zzuk()     // Catch:{ all -> 0x002b }
            monitor-exit(r1)     // Catch:{ all -> 0x002b }
        L_0x0016:
            return
        L_0x0017:
            monitor-exit(r1)     // Catch:{ all -> 0x002b }
            r0 = 1
            r2.zzdcb = r0
            com.google.android.gms.internal.ads.zzase r0 = r2.zzdbs
            if (r0 == 0) goto L_0x0027
            com.google.android.gms.internal.ads.zzase r0 = r2.zzdbs
            r0.zzly()
            r0 = 0
            r2.zzdbs = r0
        L_0x0027:
            r2.zzvd()
            goto L_0x0016
        L_0x002b:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x002b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaqx.onPageFinished(android.webkit.WebView, java.lang.String):void");
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        zzd(this.zzbnd.getContext(), "http_err", (i >= 0 || (-i) + -1 >= zzdbo.length) ? String.valueOf(i) : zzdbo[(-i) - 1], str2);
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (sslError != null) {
            int primaryError = sslError.getPrimaryError();
            zzd(this.zzbnd.getContext(), "ssl_err", (primaryError < 0 || primaryError >= zzdbp.length) ? String.valueOf(primaryError) : zzdbp[primaryError], zzbv.zzem().zza(sslError));
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    public final void reset() {
        if (this.zzxd != null) {
            this.zzxd.zzpj();
            this.zzxd = null;
        }
        zzuy();
        synchronized (this.mLock) {
            this.zzdbq.clear();
            this.zzapt = null;
            this.zzbnc = null;
            this.zzdbr = null;
            this.zzdbs = null;
            this.zzbll = null;
            this.zzblm = null;
            this.zzdbu = false;
            this.zzaek = false;
            this.zzdbv = false;
            this.zzdby = false;
            this.zzbnb = null;
            this.zzdbt = null;
            if (this.zzbmx != null) {
                this.zzbmx.zzm(true);
                this.zzbmx = null;
            }
        }
    }

    @Nullable
    @TargetApi(11)
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zzd(str, Collections.emptyMap());
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 79:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 222:
                return true;
            default:
                return false;
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Uri uri;
        String valueOf = String.valueOf(str);
        zzakb.v(valueOf.length() != 0 ? "AdWebView shouldOverrideUrlLoading: ".concat(valueOf) : new String("AdWebView shouldOverrideUrlLoading: "));
        Uri parse = Uri.parse(str);
        if (!"gmsg".equalsIgnoreCase(parse.getScheme()) || !"mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            if (this.zzdbu && webView == this.zzbnd.getWebView()) {
                String scheme = parse.getScheme();
                if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                    if (this.zzapt != null) {
                        if (((Boolean) zzkb.zzik().zzd(zznk.zzaxf)).booleanValue()) {
                            this.zzapt.onAdClicked();
                            if (this.zzxd != null) {
                                this.zzxd.zzcf(str);
                            }
                            this.zzapt = null;
                        }
                    }
                    return super.shouldOverrideUrlLoading(webView, str);
                }
            }
            if (!this.zzbnd.getWebView().willNotDraw()) {
                try {
                    zzci zzui = this.zzbnd.zzui();
                    if (zzui != null && zzui.zzb(parse)) {
                        parse = zzui.zza(parse, this.zzbnd.getContext(), this.zzbnd.getView(), this.zzbnd.zzto());
                    }
                    uri = parse;
                } catch (zzcj e) {
                    String valueOf2 = String.valueOf(str);
                    zzakb.zzdk(valueOf2.length() != 0 ? "Unable to append parameter to URL: ".concat(valueOf2) : new String("Unable to append parameter to URL: "));
                    uri = parse;
                }
                if (this.zzbmw == null || this.zzbmw.zzcy()) {
                    zza(new zzc("android.intent.action.VIEW", uri.toString(), (String) null, (String) null, (String) null, (String) null, (String) null));
                } else {
                    this.zzbmw.zzs(str);
                }
            } else {
                String valueOf3 = String.valueOf(str);
                zzakb.zzdk(valueOf3.length() != 0 ? "AdWebView unable to handle URL: ".concat(valueOf3) : new String("AdWebView unable to handle URL: "));
            }
        } else {
            zzi(parse);
        }
        return true;
    }

    public final void zza(int i, int i2, boolean z) {
        this.zzdbz.zzc(i, i2);
        if (this.zzbmx != null) {
            this.zzbmx.zza(i, i2, z);
        }
    }

    public final void zza(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        synchronized (this.mLock) {
            this.zzdbv = true;
            this.zzbnd.zzuo();
            this.zzdbw = onGlobalLayoutListener;
            this.zzdbx = onScrollChangedListener;
        }
    }

    public final void zza(zzc zzc) {
        zzn zzn = null;
        boolean zzuj = this.zzbnd.zzuj();
        zzjd zzjd = (!zzuj || this.zzbnd.zzud().zzvs()) ? this.zzapt : null;
        if (!zzuj) {
            zzn = this.zzbnc;
        }
        zza(new AdOverlayInfoParcel(zzc, zzjd, zzn, this.zzbnb, this.zzbnd.zztq()));
    }

    public final void zza(zzasd zzasd) {
        this.zzdbr = zzasd;
    }

    public final void zza(zzase zzase) {
        this.zzdbs = zzase;
    }

    public final void zza(zzasf zzasf) {
        this.zzdbt = zzasf;
    }

    public final void zza(zzasg zzasg) {
        this.zzdca = zzasg;
    }

    public final void zza(zzjd zzjd, zzb zzb, zzn zzn, zzd zzd, zzt zzt, boolean z, @Nullable zzz zzz, zzx zzx, zzaam zzaam, @Nullable zzait zzait) {
        zzx zzx2 = zzx == null ? new zzx(this.zzbnd.getContext(), zzait, (zzael) null) : zzx;
        this.zzbmx = new zzaab(this.zzbnd, zzaam);
        this.zzxd = zzait;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzayf)).booleanValue()) {
            zza("/adMetadata", (zzv<? super zzaqw>) new zza(zzb));
        }
        zza("/appEvent", (zzv<? super zzaqw>) new com.google.android.gms.ads.internal.gmsg.zzc(zzd));
        zza("/backButton", (zzv<? super zzaqw>) zzf.zzblx);
        zza("/refresh", (zzv<? super zzaqw>) zzf.zzbly);
        zza("/canOpenURLs", (zzv<? super zzaqw>) zzf.zzblo);
        zza("/canOpenIntents", (zzv<? super zzaqw>) zzf.zzblp);
        zza("/click", (zzv<? super zzaqw>) zzf.zzblq);
        zza("/close", (zzv<? super zzaqw>) zzf.zzblr);
        zza("/customClose", (zzv<? super zzaqw>) zzf.zzbls);
        zza("/instrument", (zzv<? super zzaqw>) zzf.zzbmb);
        zza("/delayPageLoaded", (zzv<? super zzaqw>) zzf.zzbmd);
        zza("/delayPageClosed", (zzv<? super zzaqw>) zzf.zzbme);
        zza("/getLocationInfo", (zzv<? super zzaqw>) zzf.zzbmf);
        zza("/httpTrack", (zzv<? super zzaqw>) zzf.zzblt);
        zza("/log", (zzv<? super zzaqw>) zzf.zzblu);
        zza("/mraid", (zzv<? super zzaqw>) new zzac(zzx2, this.zzbmx, zzaam));
        zza("/mraidLoaded", (zzv<? super zzaqw>) this.zzdbz);
        zza("/open", (zzv<? super zzaqw>) new zzad(this.zzbnd.getContext(), this.zzbnd.zztq(), this.zzbnd.zzui(), zzt, zzjd, zzb, zzd, zzn, zzx2, this.zzbmx));
        zza("/precache", (zzv<? super zzaqw>) new zzaql());
        zza("/touch", (zzv<? super zzaqw>) zzf.zzblw);
        zza("/video", (zzv<? super zzaqw>) zzf.zzblz);
        zza("/videoMeta", (zzv<? super zzaqw>) zzf.zzbma);
        if (zzbv.zzfh().zzs(this.zzbnd.getContext())) {
            zza("/logScionEvent", (zzv<? super zzaqw>) new zzab(this.zzbnd.getContext()));
        }
        if (zzz != null) {
            zza("/setInterstitialProperties", (zzv<? super zzaqw>) new zzy(zzz));
        }
        this.zzapt = zzjd;
        this.zzbnc = zzn;
        this.zzbll = zzb;
        this.zzblm = zzd;
        this.zzbnb = zzt;
        this.zzbmw = zzx2;
        this.zzbmy = zzaam;
        this.zzbmu = zzz;
        this.zzdbu = z;
    }

    public final void zza(String str, zzv<? super zzaqw> zzv) {
        synchronized (this.mLock) {
            List list = this.zzdbq.get(str);
            if (list == null) {
                list = new CopyOnWriteArrayList();
                this.zzdbq.put(str, list);
            }
            list.add(zzv);
        }
    }

    public final void zza(String str, Predicate<zzv<? super zzaqw>> predicate) {
        synchronized (this.mLock) {
            List<zzv> list = this.zzdbq.get(str);
            if (list != null) {
                ArrayList arrayList = new ArrayList();
                for (zzv zzv : list) {
                    if (predicate.apply(zzv)) {
                        arrayList.add(zzv);
                    }
                }
                list.removeAll(arrayList);
            }
        }
    }

    public final void zza(boolean z, int i) {
        zza(new AdOverlayInfoParcel((!this.zzbnd.zzuj() || this.zzbnd.zzud().zzvs()) ? this.zzapt : null, this.zzbnc, this.zzbnb, this.zzbnd, z, i, this.zzbnd.zztq()));
    }

    public final void zza(boolean z, int i, String str) {
        zzarb zzarb = null;
        boolean zzuj = this.zzbnd.zzuj();
        zzjd zzjd = (!zzuj || this.zzbnd.zzud().zzvs()) ? this.zzapt : null;
        if (!zzuj) {
            zzarb = new zzarb(this.zzbnd, this.zzbnc);
        }
        zza(new AdOverlayInfoParcel(zzjd, zzarb, this.zzbll, this.zzblm, this.zzbnb, this.zzbnd, z, i, str, this.zzbnd.zztq()));
    }

    public final void zza(boolean z, int i, String str, String str2) {
        boolean zzuj = this.zzbnd.zzuj();
        zza(new AdOverlayInfoParcel((!zzuj || this.zzbnd.zzud().zzvs()) ? this.zzapt : null, zzuj ? null : new zzarb(this.zzbnd, this.zzbnc), this.zzbll, this.zzblm, this.zzbnb, this.zzbnd, z, i, str, str2, this.zzbnd.zztq()));
    }

    public final void zzah(boolean z) {
        this.zzdbu = z;
    }

    public final void zzb(int i, int i2) {
        if (this.zzbmx != null) {
            this.zzbmx.zzb(i, i2);
        }
    }

    public final void zzb(String str, zzv<? super zzaqw> zzv) {
        synchronized (this.mLock) {
            List list = this.zzdbq.get(str);
            if (list != null) {
                list.remove(zzv);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final WebResourceResponse zzd(String str, Map<String, String> map) {
        zzhi zza;
        try {
            String zzb = zzajb.zzb(str, this.zzbnd.getContext());
            if (!zzb.equals(str)) {
                return zze(zzb, map);
            }
            zzhl zzaa = zzhl.zzaa(str);
            if (zzaa != null && (zza = zzbv.zzeq().zza(zzaa)) != null && zza.zzhi()) {
                return new WebResourceResponse("", "", zza.zzhj());
            }
            if (zzamy.isEnabled()) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzazn)).booleanValue()) {
                    return zze(str, map);
                }
            }
            return null;
        } catch (Exception | NoClassDefFoundError e) {
            zzbv.zzeo().zza(e, "AdWebViewClient.interceptRequest");
            return null;
        }
    }

    public final boolean zzfz() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzaek;
        }
        return z;
    }

    public final void zznk() {
        synchronized (this.mLock) {
            this.zzdbu = false;
            this.zzaek = true;
            zzaoe.zzcvy.execute(new zzaqy(this));
        }
    }

    public final zzx zzut() {
        return this.zzbmw;
    }

    public final boolean zzuu() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdbv;
        }
        return z;
    }

    public final ViewTreeObserver.OnGlobalLayoutListener zzuv() {
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
        synchronized (this.mLock) {
            onGlobalLayoutListener = this.zzdbw;
        }
        return onGlobalLayoutListener;
    }

    public final ViewTreeObserver.OnScrollChangedListener zzuw() {
        ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;
        synchronized (this.mLock) {
            onScrollChangedListener = this.zzdbx;
        }
        return onScrollChangedListener;
    }

    public final boolean zzux() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdby;
        }
        return z;
    }

    public final void zzuz() {
        zzait zzait = this.zzxd;
        if (zzait != null) {
            WebView webView = this.zzbnd.getWebView();
            if (ViewCompat.isAttachedToWindow(webView)) {
                zza((View) webView, zzait, 10);
                return;
            }
            zzuy();
            this.zzdce = new zzara(this, zzait);
            this.zzbnd.getView().addOnAttachStateChangeListener(this.zzdce);
        }
    }

    public final void zzva() {
        synchronized (this.mLock) {
            this.zzdby = true;
        }
        this.zzdcd++;
        zzvd();
    }

    public final void zzvb() {
        this.zzdcd--;
        zzvd();
    }

    public final void zzvc() {
        this.zzdcc = true;
        zzvd();
    }

    public final zzasg zzve() {
        return this.zzdca;
    }

    public final zzait zzvf() {
        return this.zzxd;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzvg() {
        this.zzbnd.zzuo();
        com.google.android.gms.ads.internal.overlay.zzd zzub = this.zzbnd.zzub();
        if (zzub != null) {
            zzub.zznk();
        }
        if (this.zzdbt != null) {
            this.zzdbt.zzdb();
            this.zzdbt = null;
        }
    }
}
