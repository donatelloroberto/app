package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@TargetApi(14)
@ParametersAreNonnullByDefault
public final class zzgk extends Thread {
    private final Object mLock;
    private boolean mStarted = false;
    private final int zzagx;
    private final int zzagz;
    private boolean zzahy = false;
    private final zzgf zzahz;
    private final zzadf zzaia;
    private final int zzaib;
    private final int zzaic;
    private final int zzaid;
    private final int zzaie;
    private final int zzaif;
    private final int zzaig;
    private final String zzaih;
    private final boolean zzaii;
    private boolean zzbm = false;

    public zzgk(zzgf zzgf, zzadf zzadf) {
        this.zzahz = zzgf;
        this.zzaia = zzadf;
        this.mLock = new Object();
        this.zzagx = ((Integer) zzkb.zzik().zzd(zznk.zzawl)).intValue();
        this.zzaic = ((Integer) zzkb.zzik().zzd(zznk.zzawm)).intValue();
        this.zzagz = ((Integer) zzkb.zzik().zzd(zznk.zzawn)).intValue();
        this.zzaid = ((Integer) zzkb.zzik().zzd(zznk.zzawo)).intValue();
        this.zzaie = ((Integer) zzkb.zzik().zzd(zznk.zzawr)).intValue();
        this.zzaif = ((Integer) zzkb.zzik().zzd(zznk.zzawt)).intValue();
        this.zzaig = ((Integer) zzkb.zzik().zzd(zznk.zzawu)).intValue();
        this.zzaib = ((Integer) zzkb.zzik().zzd(zznk.zzawp)).intValue();
        this.zzaih = (String) zzkb.zzik().zzd(zznk.zzaww);
        this.zzaii = ((Boolean) zzkb.zzik().zzd(zznk.zzawy)).booleanValue();
        setName("ContentFetchTask");
    }

    @VisibleForTesting
    private final zzgo zza(@Nullable View view, zzge zzge) {
        boolean z;
        if (view == null) {
            return new zzgo(this, 0, 0);
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return new zzgo(this, 0, 0);
            }
            zzge.zzb(text.toString(), globalVisibleRect, view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
            return new zzgo(this, 1, 0);
        } else if ((view instanceof WebView) && !(view instanceof zzaqw)) {
            zzge.zzgs();
            WebView webView = (WebView) view;
            if (!PlatformVersion.isAtLeastKitKat()) {
                z = false;
            } else {
                zzge.zzgs();
                webView.post(new zzgm(this, zzge, webView, globalVisibleRect));
                z = true;
            }
            return z ? new zzgo(this, 0, 1) : new zzgo(this, 0, 0);
        } else if (!(view instanceof ViewGroup)) {
            return new zzgo(this, 0, 0);
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i < viewGroup.getChildCount()) {
                zzgo zza = zza(viewGroup.getChildAt(i), zzge);
                i3 += zza.zzaiq;
                i++;
                i2 += zza.zzair;
            }
            return new zzgo(this, i3, i2);
        }
    }

    @VisibleForTesting
    private static boolean zzgx() {
        try {
            Context context = zzbv.zzen().getContext();
            if (context == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null) {
                return false;
            }
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ActivityManager.RunningAppProcessInfo next = it.next();
                if (Process.myPid() == next.pid) {
                    if (next.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode()) {
                        PowerManager powerManager = (PowerManager) context.getSystemService("power");
                        if (powerManager == null ? false : powerManager.isScreenOn()) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            zzbv.zzeo().zza(th, "ContentFetchTask.isInForeground");
            return false;
        }
    }

    private final void zzgz() {
        synchronized (this.mLock) {
            this.zzahy = true;
            zzakb.zzck(new StringBuilder(42).append("ContentFetchThread: paused, mPause = ").append(this.zzahy).toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0063, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0064, code lost:
        com.google.android.gms.internal.ads.zzakb.zzb("Error in ContentFetchTask", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        com.google.android.gms.ads.internal.zzbv.zzeo().zza(r1, "ContentFetchTask.extractContent");
        com.google.android.gms.internal.ads.zzakb.zzck("Failed getting root view of activity. Content not extracted.");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0063 A[ExcHandler: InterruptedException (r0v1 'e' java.lang.InterruptedException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r4 = this;
        L_0x0000:
            boolean r0 = zzgx()     // Catch:{ InterruptedException -> 0x0063, Exception -> 0x007a }
            if (r0 == 0) goto L_0x0088
            com.google.android.gms.internal.ads.zzgg r0 = com.google.android.gms.ads.internal.zzbv.zzen()     // Catch:{ InterruptedException -> 0x0063, Exception -> 0x007a }
            android.app.Activity r1 = r0.getActivity()     // Catch:{ InterruptedException -> 0x0063, Exception -> 0x007a }
            if (r1 != 0) goto L_0x0034
            java.lang.String r0 = "ContentFetchThread: no activity. Sleeping."
            com.google.android.gms.internal.ads.zzakb.zzck(r0)     // Catch:{ InterruptedException -> 0x0063, Exception -> 0x007a }
            r4.zzgz()     // Catch:{ InterruptedException -> 0x0063, Exception -> 0x007a }
        L_0x0018:
            int r0 = r4.zzaib     // Catch:{ InterruptedException -> 0x0063, Exception -> 0x007a }
            int r0 = r0 * 1000
            long r0 = (long) r0     // Catch:{ InterruptedException -> 0x0063, Exception -> 0x007a }
            java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x0063, Exception -> 0x007a }
        L_0x0020:
            java.lang.Object r1 = r4.mLock
            monitor-enter(r1)
        L_0x0023:
            boolean r0 = r4.zzahy     // Catch:{ all -> 0x0094 }
            if (r0 == 0) goto L_0x0091
            java.lang.String r0 = "ContentFetchTask: waiting"
            com.google.android.gms.internal.ads.zzakb.zzck(r0)     // Catch:{ InterruptedException -> 0x0032 }
            java.lang.Object r0 = r4.mLock     // Catch:{ InterruptedException -> 0x0032 }
            r0.wait()     // Catch:{ InterruptedException -> 0x0032 }
            goto L_0x0023
        L_0x0032:
            r0 = move-exception
            goto L_0x0023
        L_0x0034:
            if (r1 == 0) goto L_0x0018
            r0 = 0
            android.view.Window r2 = r1.getWindow()     // Catch:{ Exception -> 0x006a, InterruptedException -> 0x0063 }
            if (r2 == 0) goto L_0x0056
            android.view.Window r2 = r1.getWindow()     // Catch:{ Exception -> 0x006a, InterruptedException -> 0x0063 }
            android.view.View r2 = r2.getDecorView()     // Catch:{ Exception -> 0x006a, InterruptedException -> 0x0063 }
            if (r2 == 0) goto L_0x0056
            android.view.Window r1 = r1.getWindow()     // Catch:{ Exception -> 0x006a, InterruptedException -> 0x0063 }
            android.view.View r1 = r1.getDecorView()     // Catch:{ Exception -> 0x006a, InterruptedException -> 0x0063 }
            r2 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r0 = r1.findViewById(r2)     // Catch:{ Exception -> 0x006a, InterruptedException -> 0x0063 }
        L_0x0056:
            if (r0 == 0) goto L_0x0018
            if (r0 == 0) goto L_0x0018
            com.google.android.gms.internal.ads.zzgl r1 = new com.google.android.gms.internal.ads.zzgl     // Catch:{ InterruptedException -> 0x0063, Exception -> 0x007a }
            r1.<init>(r4, r0)     // Catch:{ InterruptedException -> 0x0063, Exception -> 0x007a }
            r0.post(r1)     // Catch:{ InterruptedException -> 0x0063, Exception -> 0x007a }
            goto L_0x0018
        L_0x0063:
            r0 = move-exception
            java.lang.String r1 = "Error in ContentFetchTask"
            com.google.android.gms.internal.ads.zzakb.zzb(r1, r0)
            goto L_0x0020
        L_0x006a:
            r1 = move-exception
            com.google.android.gms.internal.ads.zzajm r2 = com.google.android.gms.ads.internal.zzbv.zzeo()     // Catch:{ InterruptedException -> 0x0063, Exception -> 0x007a }
            java.lang.String r3 = "ContentFetchTask.extractContent"
            r2.zza(r1, r3)     // Catch:{ InterruptedException -> 0x0063, Exception -> 0x007a }
            java.lang.String r1 = "Failed getting root view of activity. Content not extracted."
            com.google.android.gms.internal.ads.zzakb.zzck(r1)     // Catch:{ InterruptedException -> 0x0063, Exception -> 0x007a }
            goto L_0x0056
        L_0x007a:
            r0 = move-exception
            java.lang.String r1 = "Error in ContentFetchTask"
            com.google.android.gms.internal.ads.zzakb.zzb(r1, r0)
            com.google.android.gms.internal.ads.zzadf r1 = r4.zzaia
            java.lang.String r2 = "ContentFetchTask.run"
            r1.zza(r0, r2)
            goto L_0x0020
        L_0x0088:
            java.lang.String r0 = "ContentFetchTask: sleeping"
            com.google.android.gms.internal.ads.zzakb.zzck(r0)     // Catch:{ InterruptedException -> 0x0063, Exception -> 0x007a }
            r4.zzgz()     // Catch:{ InterruptedException -> 0x0063, Exception -> 0x007a }
            goto L_0x0018
        L_0x0091:
            monitor-exit(r1)     // Catch:{ all -> 0x0094 }
            goto L_0x0000
        L_0x0094:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0094 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgk.run():void");
    }

    public final void wakeup() {
        synchronized (this.mLock) {
            this.zzahy = false;
            this.mLock.notifyAll();
            zzakb.zzck("ContentFetchThread: wakeup");
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zza(zzge zzge, WebView webView, String str, boolean z) {
        zzge.zzgr();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString(PushConstants.STYLE_TEXT);
                if (this.zzaii || TextUtils.isEmpty(webView.getTitle())) {
                    zzge.zza(optString, z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                } else {
                    String title = webView.getTitle();
                    zzge.zza(new StringBuilder(String.valueOf(title).length() + 1 + String.valueOf(optString).length()).append(title).append("\n").append(optString).toString(), z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                }
            }
            if (zzge.zzgn()) {
                this.zzahz.zzb(zzge);
            }
        } catch (JSONException e) {
            zzakb.zzck("Json string may be malformed.");
        } catch (Throwable th) {
            zzakb.zza("Failed to get webview content.", th);
            this.zzaia.zza(th, "ContentFetchTask.processWebViewContent");
        }
    }

    public final void zzgw() {
        synchronized (this.mLock) {
            if (this.mStarted) {
                zzakb.zzck("Content hash thread already started, quiting...");
                return;
            }
            this.mStarted = true;
            start();
        }
    }

    public final zzge zzgy() {
        return this.zzahz.zzgv();
    }

    public final boolean zzha() {
        return this.zzahy;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zzk(View view) {
        try {
            zzge zzge = new zzge(this.zzagx, this.zzaic, this.zzagz, this.zzaid, this.zzaie, this.zzaif, this.zzaig);
            Context context = zzbv.zzen().getContext();
            if (context != null && !TextUtils.isEmpty(this.zzaih)) {
                String str = (String) view.getTag(context.getResources().getIdentifier((String) zzkb.zzik().zzd(zznk.zzawv), PushConstants.CHANNEL_ID, context.getPackageName()));
                if (str != null && str.equals(this.zzaih)) {
                    return;
                }
            }
            zzgo zza = zza(view, zzge);
            zzge.zzgt();
            if (zza.zzaiq != 0 || zza.zzair != 0) {
                if (zza.zzair != 0 || zzge.zzgu() != 0) {
                    if (zza.zzair != 0 || !this.zzahz.zza(zzge)) {
                        this.zzahz.zzc(zzge);
                    }
                }
            }
        } catch (Exception e) {
            zzakb.zzb("Exception in fetchContentOnUIThread", e);
            this.zzaia.zza(e, "ContentFetchTask.fetchContent");
        }
    }
}
