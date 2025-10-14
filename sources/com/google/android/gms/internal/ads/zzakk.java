package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.measurement.AppMeasurement;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzakk {
    public static final Handler zzcrm = new zzakc(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public String zzcpq;
    /* access modifiers changed from: private */
    public boolean zzcrn = true;
    private boolean zzcro = false;
    private boolean zzcrp = false;
    @GuardedBy("this")
    private Pattern zzcrq;
    @GuardedBy("this")
    private Pattern zzcrr;

    @VisibleForTesting
    public static Bundle zza(zzgk zzgk) {
        String zzqv;
        String zzqx;
        String str;
        if (zzgk == null) {
            return null;
        }
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzawq)).booleanValue()) {
            if (!((Boolean) zzkb.zzik().zzd(zznk.zzaws)).booleanValue()) {
                return null;
            }
        }
        if (zzbv.zzeo().zzqh().zzqu() && zzbv.zzeo().zzqh().zzqw()) {
            return null;
        }
        if (zzgk.zzha()) {
            zzgk.wakeup();
        }
        zzge zzgy = zzgk.zzgy();
        if (zzgy != null) {
            zzqv = zzgy.getSignature();
            str = zzgy.zzgo();
            String zzgp = zzgy.zzgp();
            if (zzqv != null) {
                zzbv.zzeo().zzqh().zzcn(zzqv);
            }
            if (zzgp != null) {
                zzbv.zzeo().zzqh().zzco(zzgp);
                zzqx = zzgp;
            } else {
                zzqx = zzgp;
            }
        } else {
            zzqv = zzbv.zzeo().zzqh().zzqv();
            zzqx = zzbv.zzeo().zzqh().zzqx();
            str = null;
        }
        Bundle bundle = new Bundle(1);
        if (zzqx != null) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzaws)).booleanValue() && !zzbv.zzeo().zzqh().zzqw()) {
                bundle.putString("v_fp_vertical", zzqx);
            }
        }
        if (zzqv != null) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzawq)).booleanValue() && !zzbv.zzeo().zzqh().zzqu()) {
                bundle.putString("fingerprint", zzqv);
                if (!zzqv.equals(str)) {
                    bundle.putString("v_fp", str);
                }
            }
        }
        if (!bundle.isEmpty()) {
            return bundle;
        }
        return null;
    }

    public static DisplayMetrics zza(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static PopupWindow zza(View view, int i, int i2, boolean z) {
        return new PopupWindow(view, i, i2, false);
    }

    public static String zza(Context context, View view, zzjn zzjn) {
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzaxi)).booleanValue()) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("width", zzjn.width);
            jSONObject2.put("height", zzjn.height);
            jSONObject.put("size", jSONObject2);
            jSONObject.put("activity", zzap(context));
            if (!zzjn.zzarc) {
                JSONArray jSONArray = new JSONArray();
                while (view != null) {
                    ViewParent parent = view.getParent();
                    if (parent != null) {
                        int i = -1;
                        if (parent instanceof ViewGroup) {
                            i = ((ViewGroup) parent).indexOfChild(view);
                        }
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put(AppMeasurement.Param.TYPE, parent.getClass().getName());
                        jSONObject3.put("index_of_child", i);
                        jSONArray.put(jSONObject3);
                    }
                    view = (parent == null || !(parent instanceof View)) ? null : (View) parent;
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("parents", jSONArray);
                }
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            zzakb.zzc("Fail to get view hierarchy json", e);
            return null;
        }
    }

    public static String zza(InputStreamReader inputStreamReader) throws IOException {
        StringBuilder sb = new StringBuilder(8192);
        char[] cArr = new char[2048];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read == -1) {
                return sb.toString();
            }
            sb.append(cArr, 0, read);
        }
    }

    private final JSONArray zza(Collection<?> collection) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object zza : collection) {
            zza(jSONArray, (Object) zza);
        }
        return jSONArray;
    }

    public static void zza(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Throwable th) {
            intent.addFlags(268435456);
            context.startActivity(intent);
        }
    }

    @TargetApi(18)
    public static void zza(Context context, Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbdy)).booleanValue()) {
                zzb(context, intent);
            }
            bundle.putString("com.android.browser.application_id", context.getPackageName());
            context.startActivity(intent);
            String uri2 = uri.toString();
            zzakb.zzck(new StringBuilder(String.valueOf(uri2).length() + 26).append("Opening ").append(uri2).append(" in a new browser.").toString());
        } catch (ActivityNotFoundException e) {
            zzakb.zzb("No browser is found.", e);
        }
    }

    public static void zza(Context context, String str, List<String> list) {
        for (String zzami : list) {
            new zzami(context, str, zzami).zznt();
        }
    }

    public static void zza(Context context, Throwable th) {
        boolean z;
        if (context != null) {
            try {
                z = ((Boolean) zzkb.zzik().zzd(zznk.zzaui)).booleanValue();
            } catch (IllegalStateException e) {
                z = false;
            }
            if (z) {
                CrashUtils.addDynamiteErrorToDropBox(context, th);
            }
        }
    }

    private final void zza(JSONArray jSONArray, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONArray.put(zzf((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONArray.put(zzn((Map) obj));
        } else if (obj instanceof Collection) {
            jSONArray.put(zza((Collection<?>) (Collection) obj));
        } else if (obj instanceof Object[]) {
            JSONArray jSONArray2 = new JSONArray();
            for (Object zza : (Object[]) obj) {
                zza(jSONArray2, zza);
            }
            jSONArray.put(jSONArray2);
        } else {
            jSONArray.put(obj);
        }
    }

    private final void zza(JSONObject jSONObject, String str, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONObject.put(str, zzf((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONObject.put(str, zzn((Map) obj));
        } else if (obj instanceof Collection) {
            if (str == null) {
                str = "null";
            }
            jSONObject.put(str, zza((Collection<?>) (Collection) obj));
        } else if (obj instanceof Object[]) {
            jSONObject.put(str, zza((Collection<?>) Arrays.asList((Object[]) obj)));
        } else {
            jSONObject.put(str, obj);
        }
    }

    @TargetApi(24)
    public static boolean zza(Activity activity, Configuration configuration) {
        zzkb.zzif();
        int zza = zzamu.zza((Context) activity, configuration.screenHeightDp);
        int zza2 = zzamu.zza((Context) activity, configuration.screenWidthDp);
        DisplayMetrics zza3 = zza((WindowManager) activity.getApplicationContext().getSystemService("window"));
        int i = zza3.heightPixels;
        int i2 = zza3.widthPixels;
        int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = identifier > 0 ? activity.getResources().getDimensionPixelSize(identifier) : 0;
        int intValue = ((Integer) zzkb.zzik().zzd(zznk.zzbek)).intValue() * ((int) Math.round(((double) activity.getResources().getDisplayMetrics().density) + 0.5d));
        return zzb(i, dimensionPixelSize + zza, intValue) && zzb(i2, zza2, intValue);
    }

    public static boolean zza(ClassLoader classLoader, Class<?> cls, String str) {
        try {
            return cls.isAssignableFrom(Class.forName(str, false, classLoader));
        } catch (Throwable th) {
            return false;
        }
    }

    public static boolean zzaj(Context context) {
        boolean z;
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        try {
            ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
            if (resolveActivity == null || resolveActivity.activityInfo == null) {
                zzakb.zzdk("Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.");
                return false;
            }
            if ((resolveActivity.activityInfo.configChanges & 16) == 0) {
                zzakb.zzdk(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[]{"keyboard"}));
                z = false;
            } else {
                z = true;
            }
            if ((resolveActivity.activityInfo.configChanges & 32) == 0) {
                zzakb.zzdk(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[]{"keyboardHidden"}));
                z = false;
            }
            if ((resolveActivity.activityInfo.configChanges & 128) == 0) {
                zzakb.zzdk(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[]{"orientation"}));
                z = false;
            }
            if ((resolveActivity.activityInfo.configChanges & 256) == 0) {
                zzakb.zzdk(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[]{"screenLayout"}));
                z = false;
            }
            if ((resolveActivity.activityInfo.configChanges & 512) == 0) {
                zzakb.zzdk(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[]{"uiMode"}));
                z = false;
            }
            if ((resolveActivity.activityInfo.configChanges & 1024) == 0) {
                zzakb.zzdk(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[]{"screenSize"}));
                z = false;
            }
            if ((resolveActivity.activityInfo.configChanges & 2048) != 0) {
                return z;
            }
            zzakb.zzdk(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", new Object[]{"smallestScreenSize"}));
            return false;
        } catch (Exception e) {
            zzakb.zzc("Could not verify that com.google.android.gms.ads.AdActivity is declared in AndroidManifest.xml", e);
            zzbv.zzeo().zza(e, "AdUtil.hasAdActivity");
            return false;
        }
    }

    @VisibleForTesting
    protected static String zzam(Context context) {
        try {
            return new WebView(context).getSettings().getUserAgentString();
        } catch (Throwable th) {
            return zzrg();
        }
    }

    public static AlertDialog.Builder zzan(Context context) {
        return new AlertDialog.Builder(context);
    }

    public static zzmw zzao(Context context) {
        return new zzmw(context);
    }

    private static String zzap(Context context) {
        ActivityManager.RunningTaskInfo runningTaskInfo;
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return null;
            }
            List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(1);
            if (!(runningTasks == null || runningTasks.isEmpty() || (runningTaskInfo = runningTasks.get(0)) == null || runningTaskInfo.topActivity == null)) {
                return runningTaskInfo.topActivity.getClassName();
            }
            return null;
        } catch (Exception e) {
        }
    }

    public static boolean zzaq(Context context) {
        try {
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
            return false;
        }
    }

    public static Bitmap zzar(Context context) {
        Bitmap bitmap;
        if (!(context instanceof Activity)) {
            return null;
        }
        try {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbbh)).booleanValue()) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    bitmap = zzv(window.getDecorView().getRootView());
                }
                bitmap = null;
            } else {
                bitmap = zzu(((Activity) context).getWindow().getDecorView());
            }
        } catch (RuntimeException e) {
            zzakb.zzb("Fail to capture screen shot", e);
        }
        return bitmap;
    }

    public static int zzas(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return 0;
        }
        return applicationInfo.targetSdkVersion;
    }

    @Nullable
    private static KeyguardManager zzat(Context context) {
        Object systemService = context.getSystemService("keyguard");
        if (systemService == null || !(systemService instanceof KeyguardManager)) {
            return null;
        }
        return (KeyguardManager) systemService;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r1 = zzat(r2);
     */
    @android.annotation.TargetApi(16)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zzau(android.content.Context r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L_0x0009
            boolean r1 = com.google.android.gms.common.util.PlatformVersion.isAtLeastJellyBean()
            if (r1 != 0) goto L_0x000a
        L_0x0009:
            return r0
        L_0x000a:
            android.app.KeyguardManager r1 = zzat(r2)
            if (r1 == 0) goto L_0x0009
            boolean r1 = r1.isKeyguardLocked()
            if (r1 == 0) goto L_0x0009
            r0 = 1
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakk.zzau(android.content.Context):boolean");
    }

    public static boolean zzav(Context context) {
        try {
            context.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi");
            return false;
        } catch (ClassNotFoundException e) {
            return true;
        } catch (Throwable th) {
            zzakb.zzb("Error loading class.", th);
            zzbv.zzeo().zza(th, "AdUtil.isLiteSdk");
            return false;
        }
    }

    public static WebResourceResponse zzb(HttpURLConnection httpURLConnection) throws IOException {
        String str;
        zzbv.zzek();
        String contentType = httpURLConnection.getContentType();
        String trim = TextUtils.isEmpty(contentType) ? "" : contentType.split(";")[0].trim();
        zzbv.zzek();
        String contentType2 = httpURLConnection.getContentType();
        if (!TextUtils.isEmpty(contentType2)) {
            String[] split = contentType2.split(";");
            if (split.length != 1) {
                int i = 1;
                while (true) {
                    if (i >= split.length) {
                        break;
                    }
                    if (split[i].trim().startsWith("charset")) {
                        String[] split2 = split[i].trim().split("=");
                        if (split2.length > 1) {
                            str = split2[1].trim();
                            break;
                        }
                    }
                    i++;
                }
            }
        }
        str = "";
        Map headerFields = httpURLConnection.getHeaderFields();
        HashMap hashMap = new HashMap(headerFields.size());
        for (Map.Entry entry : headerFields.entrySet()) {
            if (!(entry.getKey() == null || entry.getValue() == null || ((List) entry.getValue()).size() <= 0)) {
                hashMap.put((String) entry.getKey(), (String) ((List) entry.getValue()).get(0));
            }
        }
        return zzbv.zzem().zza(trim, str, httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage(), hashMap, httpURLConnection.getInputStream());
    }

    @TargetApi(18)
    public static void zzb(Context context, Intent intent) {
        if (intent != null && PlatformVersion.isAtLeastJellyBeanMR2()) {
            Bundle extras = intent.getExtras() != null ? intent.getExtras() : new Bundle();
            extras.putBinder(CustomTabsIntent.EXTRA_SESSION, (IBinder) null);
            extras.putString("com.android.browser.application_id", context.getPackageName());
            intent.putExtras(extras);
        }
    }

    @VisibleForTesting
    private static boolean zzb(int i, int i2, int i3) {
        return Math.abs(i - i2) <= i3;
    }

    public static String zzcu(String str) {
        return Uri.parse(str).buildUpon().query((String) null).build().toString();
    }

    public static int zzcv(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            String valueOf = String.valueOf(e);
            zzakb.zzdk(new StringBuilder(String.valueOf(valueOf).length() + 22).append("Could not parse value:").append(valueOf).toString());
            return 0;
        }
    }

    public static boolean zzcw(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }

    public static void zzd(Context context, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        zza(context, str, (List<String>) arrayList);
    }

    public static void zzd(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            runnable.run();
        } else {
            zzaki.zzb(runnable);
        }
    }

    public static void zze(Context context, String str, String str2) {
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            openFileOutput.write(str2.getBytes(WebRequest.CHARSET_UTF_8));
            openFileOutput.close();
        } catch (Exception e) {
            zzakb.zzb("Error writing to file in internal storage.", e);
        }
    }

    @Nullable
    public static WebResourceResponse zzf(Context context, String str, String str2) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("User-Agent", zzbv.zzek().zzm(context, str));
            hashMap.put("Cache-Control", "max-stale=3600");
            String str3 = (String) new zzalt(context).zzc(str2, hashMap).get(60, TimeUnit.SECONDS);
            if (str3 != null) {
                return new WebResourceResponse(WebRequest.CONTENT_TYPE_JAVASCRIPT, WebRequest.CHARSET_UTF_8, new ByteArrayInputStream(str3.getBytes(WebRequest.CHARSET_UTF_8)));
            }
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            zzakb.zzc("Could not fetch MRAID JS.", e);
        }
        return null;
    }

    private final JSONObject zzf(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            zza(jSONObject, str, bundle.get(str));
        }
        return jSONObject;
    }

    public static int[] zzf(Activity activity) {
        View findViewById;
        Window window = activity.getWindow();
        if (window == null || (findViewById = window.findViewById(16908290)) == null) {
            return zzrj();
        }
        return new int[]{findViewById.getWidth(), findViewById.getHeight()};
    }

    public static Map<String, String> zzg(Uri uri) {
        if (uri == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String next : zzbv.zzem().zzh(uri)) {
            hashMap.put(next, uri.getQueryParameter(next));
        }
        return hashMap;
    }

    public static boolean zzl(Context context, String str) {
        return Wrappers.packageManager(context).checkPermission(str, context.getPackageName()) == 0;
    }

    public static String zzn(Context context, String str) {
        try {
            return new String(IOUtils.readInputStreamFully(context.openFileInput(str), true), WebRequest.CHARSET_UTF_8);
        } catch (IOException e) {
            zzakb.zzck("Error reading from internal storage.");
            return "";
        }
    }

    private static String zzrg() {
        StringBuilder sb = new StringBuilder(256);
        sb.append("Mozilla/5.0 (Linux; U; Android");
        if (Build.VERSION.RELEASE != null) {
            sb.append(" ").append(Build.VERSION.RELEASE);
        }
        sb.append("; ").append(Locale.getDefault());
        if (Build.DEVICE != null) {
            sb.append("; ").append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                sb.append(" Build/").append(Build.DISPLAY);
            }
        }
        sb.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return sb.toString();
    }

    public static String zzrh() {
        return UUID.randomUUID().toString();
    }

    public static String zzri() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        return str2.startsWith(str) ? str2 : new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length()).append(str).append(" ").append(str2).toString();
    }

    private static int[] zzrj() {
        return new int[]{0, 0};
    }

    public static Bundle zzrk() {
        Bundle bundle = new Bundle();
        try {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzavm)).booleanValue()) {
                Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
                Debug.getMemoryInfo(memoryInfo);
                bundle.putParcelable("debug_memory_info", memoryInfo);
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzavn)).booleanValue()) {
                Runtime runtime = Runtime.getRuntime();
                bundle.putLong("runtime_free_memory", runtime.freeMemory());
                bundle.putLong("runtime_max_memory", runtime.maxMemory());
                bundle.putLong("runtime_total_memory", runtime.totalMemory());
            }
            bundle.putInt("web_view_count", zzbv.zzeo().zzqg());
        } catch (Exception e) {
            zzakb.zzc("Unable to gather memory stats", e);
        }
        return bundle;
    }

    public static Bitmap zzs(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    public static Bitmap zzt(View view) {
        if (view == null) {
            return null;
        }
        Bitmap zzv = zzv(view);
        return zzv == null ? zzu(view) : zzv;
    }

    private static Bitmap zzu(@NonNull View view) {
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            if (width == 0 || height == 0) {
                zzakb.zzdk("Width or height of view is zero");
                return null;
            }
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            view.layout(0, 0, width, height);
            view.draw(canvas);
            return createBitmap;
        } catch (RuntimeException e) {
            zzakb.zzb("Fail to capture the webview", e);
            return null;
        }
    }

    private static Bitmap zzv(@NonNull View view) {
        Bitmap bitmap = null;
        try {
            boolean isDrawingCacheEnabled = view.isDrawingCacheEnabled();
            view.setDrawingCacheEnabled(true);
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                bitmap = Bitmap.createBitmap(drawingCache);
            }
            view.setDrawingCacheEnabled(isDrawingCacheEnabled);
        } catch (RuntimeException e) {
            zzakb.zzb("Fail to capture the web view", e);
        }
        return bitmap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0014  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zzw(android.view.View r4) {
        /*
            r2 = 0
            r1 = 0
            android.view.View r0 = r4.getRootView()
            if (r0 == 0) goto L_0x0016
            android.content.Context r0 = r0.getContext()
            boolean r3 = r0 instanceof android.app.Activity
            if (r3 == 0) goto L_0x0016
            android.app.Activity r0 = (android.app.Activity) r0
        L_0x0012:
            if (r0 != 0) goto L_0x0018
            r0 = r1
        L_0x0015:
            return r0
        L_0x0016:
            r0 = r2
            goto L_0x0012
        L_0x0018:
            android.view.Window r0 = r0.getWindow()
            if (r0 != 0) goto L_0x002a
            r0 = r2
        L_0x001f:
            if (r0 == 0) goto L_0x002f
            int r0 = r0.flags
            r2 = 524288(0x80000, float:7.34684E-40)
            r0 = r0 & r2
            if (r0 == 0) goto L_0x002f
            r0 = 1
            goto L_0x0015
        L_0x002a:
            android.view.WindowManager$LayoutParams r0 = r0.getAttributes()
            goto L_0x001f
        L_0x002f:
            r0 = r1
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakk.zzw(android.view.View):boolean");
    }

    public static int zzx(@Nullable View view) {
        if (view == null) {
            return -1;
        }
        ViewParent parent = view.getParent();
        while (parent != null && !(parent instanceof AdapterView)) {
            parent = parent.getParent();
        }
        if (parent == null) {
            return -1;
        }
        return ((AdapterView) parent).getPositionForView(view);
    }

    public final JSONObject zza(@Nullable Bundle bundle, JSONObject jSONObject) {
        if (bundle == null) {
            return null;
        }
        try {
            return zzf(bundle);
        } catch (JSONException e) {
            zzakb.zzb("Error converting Bundle to JSON", e);
            return null;
        }
    }

    public final void zza(Context context, String str, WebSettings webSettings) {
        webSettings.setUserAgentString(zzm(context, str));
    }

    public final void zza(Context context, @Nullable String str, String str2, Bundle bundle, boolean z) {
        if (z) {
            zzbv.zzek();
            bundle.putString("device", zzri());
            bundle.putString("eids", TextUtils.join(",", zznk.zzjb()));
        }
        zzkb.zzif();
        zzamu.zza(context, str, str2, bundle, z, new zzakn(this, context, str));
    }

    public final void zza(Context context, String str, boolean z, HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", zzm(context, str));
        httpURLConnection.setUseCaches(false);
    }

    public final void zza(Context context, List<String> list) {
        if (!(context instanceof Activity) || TextUtils.isEmpty(zzbfw.zzbn((Activity) context))) {
            return;
        }
        if (list == null) {
            zzakb.v("Cannot ping urls: empty list.");
        } else if (!zzoh.zzh(context)) {
            zzakb.v("Cannot ping url because custom tabs is not supported");
        } else {
            zzoh zzoh = new zzoh();
            zzoh.zza((zzoi) new zzakl(this, list, zzoh, context));
            zzoh.zzd((Activity) context);
        }
    }

    public final boolean zza(View view, Context context) {
        PowerManager powerManager = null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            powerManager = (PowerManager) applicationContext.getSystemService("power");
        }
        return zza(view, powerManager, zzat(context));
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0071  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(android.view.View r5, android.os.PowerManager r6, android.app.KeyguardManager r7) {
        /*
            r4 = this;
            r2 = 1
            r1 = 0
            com.google.android.gms.internal.ads.zzakk r0 = com.google.android.gms.ads.internal.zzbv.zzek()
            boolean r0 = r0.zzcrn
            if (r0 != 0) goto L_0x0027
            if (r7 != 0) goto L_0x006a
            r0 = r1
        L_0x000d:
            if (r0 == 0) goto L_0x0027
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzazu
            com.google.android.gms.internal.ads.zzni r3 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r3.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x006f
            boolean r0 = zzw(r5)
            if (r0 == 0) goto L_0x006f
        L_0x0027:
            r0 = r2
        L_0x0028:
            int r3 = r5.getVisibility()
            if (r3 != 0) goto L_0x0073
            boolean r3 = r5.isShown()
            if (r3 == 0) goto L_0x0073
            if (r6 == 0) goto L_0x003c
            boolean r3 = r6.isScreenOn()
            if (r3 == 0) goto L_0x0071
        L_0x003c:
            r3 = r2
        L_0x003d:
            if (r3 == 0) goto L_0x0073
            if (r0 == 0) goto L_0x0073
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzazs
            com.google.android.gms.internal.ads.zzni r3 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r3.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0069
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            boolean r0 = r5.getLocalVisibleRect(r0)
            if (r0 != 0) goto L_0x0069
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            boolean r0 = r5.getGlobalVisibleRect(r0)
            if (r0 == 0) goto L_0x0073
        L_0x0069:
            return r2
        L_0x006a:
            boolean r0 = r7.inKeyguardRestrictedInputMode()
            goto L_0x000d
        L_0x006f:
            r0 = r1
            goto L_0x0028
        L_0x0071:
            r3 = r1
            goto L_0x003d
        L_0x0073:
            r2 = r1
            goto L_0x0069
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakk.zza(android.view.View, android.os.PowerManager, android.app.KeyguardManager):boolean");
    }

    public final boolean zzak(Context context) {
        if (this.zzcro) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.getApplicationContext().registerReceiver(new zzakp(this, (zzakl) null), intentFilter);
        this.zzcro = true;
        return true;
    }

    public final boolean zzal(Context context) {
        if (this.zzcrp) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.ads.intent.DEBUG_LOGGING_ENABLEMENT_CHANGED");
        context.getApplicationContext().registerReceiver(new zzako(this, (zzakl) null), intentFilter);
        this.zzcrp = true;
        return true;
    }

    public final void zzb(Context context, String str, String str2, Bundle bundle, boolean z) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzazx)).booleanValue()) {
            zza(context, str, str2, bundle, z);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if (((java.lang.String) com.google.android.gms.internal.ads.zzkb.zzik().zzd(com.google.android.gms.internal.ads.zznk.zzaxo)).equals(r3.zzcrq.pattern()) == false) goto L_0x0026;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzcx(java.lang.String r4) {
        /*
            r3 = this;
            r1 = 0
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L_0x0009
            r0 = r1
        L_0x0008:
            return r0
        L_0x0009:
            monitor-enter(r3)     // Catch:{ PatternSyntaxException -> 0x0047 }
            java.util.regex.Pattern r0 = r3.zzcrq     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0026
            com.google.android.gms.internal.ads.zzna<java.lang.String> r0 = com.google.android.gms.internal.ads.zznk.zzaxo     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x0044 }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x0044 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0044 }
            java.util.regex.Pattern r2 = r3.zzcrq     // Catch:{ all -> 0x0044 }
            java.lang.String r2 = r2.pattern()     // Catch:{ all -> 0x0044 }
            boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x0038
        L_0x0026:
            com.google.android.gms.internal.ads.zzna<java.lang.String> r0 = com.google.android.gms.internal.ads.zznk.zzaxo     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x0044 }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x0044 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0044 }
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)     // Catch:{ all -> 0x0044 }
            r3.zzcrq = r0     // Catch:{ all -> 0x0044 }
        L_0x0038:
            java.util.regex.Pattern r0 = r3.zzcrq     // Catch:{ all -> 0x0044 }
            java.util.regex.Matcher r0 = r0.matcher(r4)     // Catch:{ all -> 0x0044 }
            boolean r0 = r0.matches()     // Catch:{ all -> 0x0044 }
            monitor-exit(r3)     // Catch:{ all -> 0x0044 }
            goto L_0x0008
        L_0x0044:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0044 }
            throw r0     // Catch:{ PatternSyntaxException -> 0x0047 }
        L_0x0047:
            r0 = move-exception
            r0 = r1
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakk.zzcx(java.lang.String):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if (((java.lang.String) com.google.android.gms.internal.ads.zzkb.zzik().zzd(com.google.android.gms.internal.ads.zznk.zzaxp)).equals(r3.zzcrr.pattern()) == false) goto L_0x0026;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzcy(java.lang.String r4) {
        /*
            r3 = this;
            r1 = 0
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L_0x0009
            r0 = r1
        L_0x0008:
            return r0
        L_0x0009:
            monitor-enter(r3)     // Catch:{ PatternSyntaxException -> 0x0047 }
            java.util.regex.Pattern r0 = r3.zzcrr     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0026
            com.google.android.gms.internal.ads.zzna<java.lang.String> r0 = com.google.android.gms.internal.ads.zznk.zzaxp     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x0044 }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x0044 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0044 }
            java.util.regex.Pattern r2 = r3.zzcrr     // Catch:{ all -> 0x0044 }
            java.lang.String r2 = r2.pattern()     // Catch:{ all -> 0x0044 }
            boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x0038
        L_0x0026:
            com.google.android.gms.internal.ads.zzna<java.lang.String> r0 = com.google.android.gms.internal.ads.zznk.zzaxp     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x0044 }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x0044 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0044 }
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)     // Catch:{ all -> 0x0044 }
            r3.zzcrr = r0     // Catch:{ all -> 0x0044 }
        L_0x0038:
            java.util.regex.Pattern r0 = r3.zzcrr     // Catch:{ all -> 0x0044 }
            java.util.regex.Matcher r0 = r0.matcher(r4)     // Catch:{ all -> 0x0044 }
            boolean r0 = r0.matches()     // Catch:{ all -> 0x0044 }
            monitor-exit(r3)     // Catch:{ all -> 0x0044 }
            goto L_0x0008
        L_0x0044:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0044 }
            throw r0     // Catch:{ PatternSyntaxException -> 0x0047 }
        L_0x0047:
            r0 = move-exception
            r0 = r1
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakk.zzcy(java.lang.String):boolean");
    }

    public final int[] zzg(Activity activity) {
        int[] zzf = zzf(activity);
        zzkb.zzif();
        zzkb.zzif();
        return new int[]{zzamu.zzb((Context) activity, zzf[0]), zzamu.zzb((Context) activity, zzf[1])};
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0009, code lost:
        r1 = r0.findViewById(16908290);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int[] zzh(android.app.Activity r7) {
        /*
            r6 = this;
            r5 = 2
            r4 = 1
            r3 = 0
            android.view.Window r0 = r7.getWindow()
            if (r0 == 0) goto L_0x0039
            r1 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r1 = r0.findViewById(r1)
            if (r1 == 0) goto L_0x0039
            int[] r0 = new int[r5]
            int r2 = r1.getTop()
            r0[r3] = r2
            int r1 = r1.getBottom()
            r0[r4] = r1
        L_0x0020:
            int[] r1 = new int[r5]
            com.google.android.gms.internal.ads.zzkb.zzif()
            r2 = r0[r3]
            int r2 = com.google.android.gms.internal.ads.zzamu.zzb((android.content.Context) r7, (int) r2)
            r1[r3] = r2
            com.google.android.gms.internal.ads.zzkb.zzif()
            r0 = r0[r4]
            int r0 = com.google.android.gms.internal.ads.zzamu.zzb((android.content.Context) r7, (int) r0)
            r1[r4] = r0
            return r1
        L_0x0039:
            int[] r0 = zzrj()
            goto L_0x0020
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakk.zzh(android.app.Activity):int[]");
    }

    public final String zzm(Context context, String str) {
        String str2;
        synchronized (this.mLock) {
            if (this.zzcpq != null) {
                str2 = this.zzcpq;
            } else if (str == null) {
                str2 = zzrg();
            } else {
                try {
                    this.zzcpq = zzbv.zzem().getDefaultUserAgent(context);
                } catch (Exception e) {
                }
                if (TextUtils.isEmpty(this.zzcpq)) {
                    zzkb.zzif();
                    if (!zzamu.zzsh()) {
                        this.zzcpq = null;
                        zzcrm.post(new zzakm(this, context));
                        while (this.zzcpq == null) {
                            try {
                                this.mLock.wait();
                            } catch (InterruptedException e2) {
                                this.zzcpq = zzrg();
                                String valueOf = String.valueOf(this.zzcpq);
                                zzakb.zzdk(valueOf.length() != 0 ? "Interrupted, use default user agent: ".concat(valueOf) : new String("Interrupted, use default user agent: "));
                            }
                        }
                    } else {
                        this.zzcpq = zzam(context);
                    }
                }
                String valueOf2 = String.valueOf(this.zzcpq);
                this.zzcpq = new StringBuilder(String.valueOf(valueOf2).length() + 10 + String.valueOf(str).length()).append(valueOf2).append(" (Mobile; ").append(str).toString();
                try {
                    if (Wrappers.packageManager(context).isCallerInstantApp()) {
                        this.zzcpq = String.valueOf(this.zzcpq).concat(";aia");
                    }
                } catch (Exception e3) {
                    zzbv.zzeo().zza(e3, "AdUtil.getUserAgent");
                }
                this.zzcpq = String.valueOf(this.zzcpq).concat(")");
                str2 = this.zzcpq;
            }
        }
        return str2;
    }

    public final JSONObject zzn(Map<String, ?> map) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            for (String next : map.keySet()) {
                zza(jSONObject, next, (Object) map.get(next));
            }
            return jSONObject;
        } catch (ClassCastException e) {
            String valueOf = String.valueOf(e.getMessage());
            throw new JSONException(valueOf.length() != 0 ? "Could not convert map to JSON: ".concat(valueOf) : new String("Could not convert map to JSON: "));
        }
    }
}
