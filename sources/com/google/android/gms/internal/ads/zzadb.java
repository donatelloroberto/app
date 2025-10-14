package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzadb implements zzadf {
    private static final Object sLock = new Object();
    @VisibleForTesting
    private static zzadf zzcbw = null;
    @VisibleForTesting
    private static zzadf zzcbx = null;
    private final Context zzatx;
    private final Object zzcby;
    private final WeakHashMap<Thread, Boolean> zzcbz;
    private final ExecutorService zzru;
    private final zzang zzzw;

    private zzadb(Context context) {
        this(context, zzang.zzsl());
    }

    private zzadb(Context context, zzang zzang) {
        this.zzcby = new Object();
        this.zzcbz = new WeakHashMap<>();
        this.zzru = Executors.newCachedThreadPool();
        this.zzatx = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzzw = zzang;
    }

    @VisibleForTesting
    private final Uri.Builder zza(String str, String str2, String str3, int i) {
        boolean z = false;
        try {
            z = Wrappers.packageManager(this.zzatx).isCallerInstantApp();
        } catch (Throwable th) {
            zzane.zzb("Error fetching instant app info", th);
        }
        String str4 = "unknown";
        try {
            str4 = this.zzatx.getPackageName();
        } catch (Throwable th2) {
            zzane.zzdk("Cannot obtain package name, proceeding.");
        }
        Uri.Builder appendQueryParameter = new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("is_aia", Boolean.toString(z)).appendQueryParameter(PushConstants.CHANNEL_ID, "gmob-apps-report-exception").appendQueryParameter("os", Build.VERSION.RELEASE).appendQueryParameter("api", String.valueOf(Build.VERSION.SDK_INT));
        String str5 = Build.MANUFACTURER;
        String str6 = Build.MODEL;
        if (!str6.startsWith(str5)) {
            str6 = new StringBuilder(String.valueOf(str5).length() + 1 + String.valueOf(str6).length()).append(str5).append(" ").append(str6).toString();
        }
        return appendQueryParameter.appendQueryParameter("device", str6).appendQueryParameter("js", this.zzzw.zzcw).appendQueryParameter("appid", str4).appendQueryParameter("exceptiontype", str).appendQueryParameter("stacktrace", str2).appendQueryParameter("eids", TextUtils.join(",", zznk.zzjb())).appendQueryParameter("exceptionkey", str3).appendQueryParameter("cl", "193400285").appendQueryParameter("rc", "dev").appendQueryParameter("session_id", zzkb.zzih()).appendQueryParameter("sampling_rate", Integer.toString(i)).appendQueryParameter("pb_tm", String.valueOf(zzkb.zzik().zzd(zznk.zzbfo)));
    }

    public static zzadf zzc(Context context, zzang zzang) {
        synchronized (sLock) {
            if (zzcbx == null) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzauh)).booleanValue()) {
                    zzadb zzadb = new zzadb(context, zzang);
                    Thread thread = Looper.getMainLooper().getThread();
                    if (thread != null) {
                        synchronized (zzadb.zzcby) {
                            zzadb.zzcbz.put(thread, true);
                        }
                        thread.setUncaughtExceptionHandler(new zzadd(zzadb, thread.getUncaughtExceptionHandler()));
                    }
                    Thread.setDefaultUncaughtExceptionHandler(new zzadc(zzadb, Thread.getDefaultUncaughtExceptionHandler()));
                    zzcbx = zzadb;
                } else {
                    zzcbx = new zzadg();
                }
            }
        }
        return zzcbx;
    }

    public static zzadf zzl(Context context) {
        synchronized (sLock) {
            if (zzcbw == null) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzauh)).booleanValue()) {
                    zzcbw = new zzadb(context);
                } else {
                    zzcbw = new zzadg();
                }
            }
        }
        return zzcbw;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        if (r2 == false) goto L_0x003e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.Thread r11, java.lang.Throwable r12) {
        /*
            r10 = this;
            r1 = 1
            r3 = 0
            if (r12 == 0) goto L_0x0048
            r2 = r3
            r0 = r3
            r5 = r12
        L_0x0007:
            if (r5 == 0) goto L_0x003a
            java.lang.StackTraceElement[] r6 = r5.getStackTrace()
            int r7 = r6.length
            r4 = r3
        L_0x000f:
            if (r4 >= r7) goto L_0x0034
            r8 = r6[r4]
            java.lang.String r9 = r8.getClassName()
            boolean r9 = com.google.android.gms.internal.ads.zzamu.zzdf(r9)
            if (r9 == 0) goto L_0x001e
            r0 = r1
        L_0x001e:
            java.lang.Class r9 = r10.getClass()
            java.lang.String r9 = r9.getName()
            java.lang.String r8 = r8.getClassName()
            boolean r8 = r9.equals(r8)
            if (r8 == 0) goto L_0x0031
            r2 = r1
        L_0x0031:
            int r4 = r4 + 1
            goto L_0x000f
        L_0x0034:
            java.lang.Throwable r4 = r5.getCause()
            r5 = r4
            goto L_0x0007
        L_0x003a:
            if (r0 == 0) goto L_0x0048
            if (r2 != 0) goto L_0x0048
        L_0x003e:
            if (r1 == 0) goto L_0x0047
            java.lang.String r0 = ""
            r1 = 1065353216(0x3f800000, float:1.0)
            r10.zza(r12, r0, r1)
        L_0x0047:
            return
        L_0x0048:
            r1 = r3
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzadb.zza(java.lang.Thread, java.lang.Throwable):void");
    }

    public final void zza(Throwable th, String str) {
        zza(th, str, 1.0f);
    }

    public final void zza(Throwable th, String str, float f) {
        if (zzamu.zzc(th) != null) {
            String name = th.getClass().getName();
            StringWriter stringWriter = new StringWriter();
            zzazr.zza(th, new PrintWriter(stringWriter));
            String stringWriter2 = stringWriter.toString();
            boolean z = Math.random() < ((double) f);
            int i = f > 0.0f ? (int) (1.0f / f) : 1;
            if (z) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(zza(name, stringWriter2, str, i).toString());
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj = arrayList2.get(i2);
                    i2++;
                    this.zzru.submit(new zzade(this, new zzanf(), (String) obj));
                }
            }
        }
    }
}
