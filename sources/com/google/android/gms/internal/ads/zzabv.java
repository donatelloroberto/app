package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.ads.internal.zzbc;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzabv implements Callable<zzajh> {
    @VisibleForTesting
    private static long zzbzx = 10;
    private final Context mContext;
    private int mErrorCode;
    private final Object mLock = new Object();
    private final zzacm zzaad;
    private final zzci zzbjc;
    private final zzaji zzbze;
    private final zzalt zzbzy;
    /* access modifiers changed from: private */
    public final zzbc zzbzz;
    private boolean zzcaa;
    private List<String> zzcab;
    private JSONObject zzcac;
    private String zzcad;
    @Nullable
    private String zzcae;
    private final zznx zzvr;

    public zzabv(Context context, zzbc zzbc, zzalt zzalt, zzci zzci, zzaji zzaji, zznx zznx) {
        this.mContext = context;
        this.zzbzz = zzbc;
        this.zzbzy = zzalt;
        this.zzbze = zzaji;
        this.zzbjc = zzci;
        this.zzvr = zznx;
        this.zzaad = zzbc.zzdr();
        this.zzcaa = false;
        this.mErrorCode = -2;
        this.zzcab = null;
        this.zzcad = null;
        this.zzcae = null;
    }

    private final zzajh zza(zzpb zzpb, boolean z) {
        int i;
        synchronized (this.mLock) {
            i = this.mErrorCode;
            if (zzpb == null && this.mErrorCode == -2) {
                i = 0;
            }
        }
        return new zzajh(this.zzbze.zzcgs.zzccv, (zzaqw) null, this.zzbze.zzcos.zzbsn, i, this.zzbze.zzcos.zzbso, this.zzcab, this.zzbze.zzcos.orientation, this.zzbze.zzcos.zzbsu, this.zzbze.zzcgs.zzccy, false, (zzwx) null, (zzxq) null, (String) null, (zzwy) null, (zzxa) null, 0, this.zzbze.zzacv, this.zzbze.zzcos.zzcep, this.zzbze.zzcoh, this.zzbze.zzcoi, this.zzbze.zzcos.zzcev, this.zzcac, i != -2 ? null : zzpb, (zzaig) null, (List<String>) null, (List<String>) null, this.zzbze.zzcos.zzcfh, this.zzbze.zzcos.zzcfi, (String) null, this.zzbze.zzcos.zzbsr, this.zzcad, this.zzbze.zzcoq, this.zzbze.zzcos.zzzl, this.zzbze.zzcor, z, this.zzbze.zzcos.zzbsp, this.zzbze.zzcos.zzzm, this.zzcae);
    }

    private final zzanz<zzon> zza(JSONObject jSONObject, boolean z, boolean z2) throws JSONException {
        String string = z ? jSONObject.getString(ImagesContract.URL) : jSONObject.optString(ImagesContract.URL);
        double optDouble = jSONObject.optDouble("scale", 1.0d);
        boolean optBoolean = jSONObject.optBoolean("is_transparent", true);
        if (!TextUtils.isEmpty(string)) {
            return z2 ? zzano.zzi(new zzon((Drawable) null, Uri.parse(string), optDouble)) : this.zzbzy.zza(string, new zzacb(this, z, optDouble, optBoolean, string));
        }
        zzd(0, z);
        return zzano.zzi(null);
    }

    private final void zzab(int i) {
        synchronized (this.mLock) {
            this.zzcaa = true;
            this.mErrorCode = i;
        }
    }

    private static zzaqw zzb(zzanz<zzaqw> zzanz) {
        try {
            return (zzaqw) zzanz.get((long) ((Integer) zzkb.zzik().zzd(zznk.zzbby)).intValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            zzane.zzc("", e);
            Thread.currentThread().interrupt();
        } catch (CancellationException | ExecutionException | TimeoutException e2) {
            zzane.zzc("", e2);
        }
        return null;
    }

    private static Integer zzb(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt("b")));
        } catch (JSONException e) {
            return null;
        }
    }

    static zzaqw zzc(zzanz<zzaqw> zzanz) {
        try {
            return (zzaqw) zzanz.get((long) ((Integer) zzkb.zzik().zzd(zznk.zzbbx)).intValue(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            zzakb.zzc("InterruptedException occurred while waiting for video to load", e);
            Thread.currentThread().interrupt();
        } catch (CancellationException | ExecutionException | TimeoutException e2) {
            zzakb.zzc("Exception occurred while waiting for video to load", e2);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public final void zzc(zzqs zzqs, String str) {
        try {
            zzrc zzr = this.zzbzz.zzr(zzqs.getCustomTemplateId());
            if (zzr != null) {
                zzr.zzb(zzqs, str);
            }
        } catch (RemoteException e) {
            zzakb.zzc(new StringBuilder(String.valueOf(str).length() + 40).append("Failed to call onCustomClick for asset ").append(str).append(".").toString(), e);
        }
    }

    /* access modifiers changed from: private */
    public static <V> List<V> zzk(List<zzanz<V>> list) throws ExecutionException, InterruptedException {
        ArrayList arrayList = new ArrayList();
        for (zzanz<V> zzanz : list) {
            Object obj = zzanz.get();
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003d, code lost:
        if (r2.length() != 0) goto L_0x0043;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0075 A[Catch:{ InterruptedException | CancellationException | JSONException -> 0x018d, ExecutionException -> 0x01a5, TimeoutException -> 0x020b, Exception -> 0x0212 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0093 A[Catch:{ InterruptedException | CancellationException | JSONException -> 0x018d, ExecutionException -> 0x01a5, TimeoutException -> 0x020b, Exception -> 0x0212 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b5 A[Catch:{ InterruptedException | CancellationException | JSONException -> 0x018d, ExecutionException -> 0x01a5, TimeoutException -> 0x020b, Exception -> 0x0212 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0197  */
    /* renamed from: zznw */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzajh call() {
        /*
            r15 = this;
            r12 = 0
            r11 = 0
            com.google.android.gms.ads.internal.zzbc r2 = r15.zzbzz     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.lang.String r10 = r2.getUuid()     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            boolean r2 = r15.zznx()     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r2 != 0) goto L_0x00b3
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzaji r2 = r15.zzbze     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzaej r2 = r2.zzcos     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.lang.String r2 = r2.zzceo     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r3.<init>(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzaji r4 = r15.zzbze     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzaej r4 = r4.zzcos     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.lang.String r4 = r4.zzceo     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r2.<init>(r4)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            int r4 = r2.length()     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r4 == 0) goto L_0x003f
            java.lang.String r4 = "ads"
            org.json.JSONArray r2 = r2.optJSONArray(r4)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r2 == 0) goto L_0x00b1
            r4 = 0
            org.json.JSONObject r2 = r2.optJSONObject(r4)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        L_0x0037:
            if (r2 == 0) goto L_0x003f
            int r2 = r2.length()     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r2 != 0) goto L_0x0043
        L_0x003f:
            r2 = 3
            r15.zzab(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        L_0x0043:
            com.google.android.gms.internal.ads.zzacm r2 = r15.zzaad     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzanz r2 = r2.zzh(r3)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            long r4 = zzbzx     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.lang.Object r2 = r2.get(r4, r3)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            org.json.JSONObject r2 = (org.json.JSONObject) r2     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.lang.String r3 = "success"
            r4 = 0
            boolean r3 = r2.optBoolean(r3, r4)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r3 == 0) goto L_0x00b3
            java.lang.String r3 = "json"
            org.json.JSONObject r2 = r2.getJSONObject(r3)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.lang.String r3 = "ads"
            org.json.JSONArray r2 = r2.optJSONArray(r3)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r3 = 0
            org.json.JSONObject r7 = r2.getJSONObject(r3)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        L_0x006d:
            java.lang.String r2 = "enable_omid"
            boolean r14 = r7.optBoolean(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r14 != 0) goto L_0x00b5
            r2 = 0
            com.google.android.gms.internal.ads.zzany r2 = com.google.android.gms.internal.ads.zzano.zzi(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r13 = r2
        L_0x007b:
            boolean r2 = r15.zznx()     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r2 != 0) goto L_0x0083
            if (r7 != 0) goto L_0x00e8
        L_0x0083:
            r4 = r11
        L_0x0084:
            boolean r2 = r15.zznx()     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r2 != 0) goto L_0x008e
            if (r4 == 0) goto L_0x008e
            if (r7 != 0) goto L_0x01a7
        L_0x008e:
            r3 = r11
        L_0x008f:
            boolean r2 = r3 instanceof com.google.android.gms.internal.ads.zzos     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r2 == 0) goto L_0x00a3
            r0 = r3
            com.google.android.gms.internal.ads.zzos r0 = (com.google.android.gms.internal.ads.zzos) r0     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r2 = r0
            com.google.android.gms.internal.ads.zzabz r4 = new com.google.android.gms.internal.ads.zzabz     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r4.<init>(r15, r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzacm r2 = r15.zzaad     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.lang.String r5 = "/nativeAdCustomClick"
            r2.zza((java.lang.String) r5, r4)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        L_0x00a3:
            com.google.android.gms.internal.ads.zzajh r2 = r15.zza((com.google.android.gms.internal.ads.zzpb) r3, (boolean) r14)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.ads.internal.zzbc r3 = r15.zzbzz     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzaqw r4 = zzb(r13)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r3.zzg(r4)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        L_0x00b0:
            return r2
        L_0x00b1:
            r2 = r11
            goto L_0x0037
        L_0x00b3:
            r7 = r11
            goto L_0x006d
        L_0x00b5:
            java.lang.String r2 = "omid_settings"
            org.json.JSONObject r2 = r7.optJSONObject(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r2 != 0) goto L_0x00c4
            r2 = 0
            com.google.android.gms.internal.ads.zzany r2 = com.google.android.gms.internal.ads.zzano.zzi(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r13 = r2
            goto L_0x007b
        L_0x00c4:
            java.lang.String r3 = "omid_html"
            java.lang.String r3 = r2.optString(r3)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            boolean r2 = android.text.TextUtils.isEmpty(r3)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r2 == 0) goto L_0x00d7
            r2 = 0
            com.google.android.gms.internal.ads.zzany r2 = com.google.android.gms.internal.ads.zzano.zzi(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r13 = r2
            goto L_0x007b
        L_0x00d7:
            com.google.android.gms.internal.ads.zzaoj r2 = new com.google.android.gms.internal.ads.zzaoj     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r2.<init>()     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.util.concurrent.Executor r4 = com.google.android.gms.internal.ads.zzaoe.zzcvy     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzabw r5 = new com.google.android.gms.internal.ads.zzabw     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r5.<init>(r15, r2, r3)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r4.execute(r5)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r13 = r2
            goto L_0x007b
        L_0x00e8:
            java.lang.String r2 = "template_id"
            java.lang.String r5 = r7.getString(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzaji r2 = r15.zzbze     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzaef r2 = r2.zzcgs     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzpl r2 = r2.zzadj     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r2 == 0) goto L_0x0124
            com.google.android.gms.internal.ads.zzaji r2 = r15.zzbze     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzaef r2 = r2.zzcgs     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzpl r2 = r2.zzadj     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            boolean r2 = r2.zzbjn     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r4 = r2
        L_0x00ff:
            com.google.android.gms.internal.ads.zzaji r2 = r15.zzbze     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzaef r2 = r2.zzcgs     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzpl r2 = r2.zzadj     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r2 == 0) goto L_0x0126
            com.google.android.gms.internal.ads.zzaji r2 = r15.zzbze     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzaef r2 = r2.zzcgs     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzpl r2 = r2.zzadj     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            boolean r2 = r2.zzbjp     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r3 = r2
        L_0x0110:
            java.lang.String r2 = "2"
            boolean r2 = r2.equals(r5)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r2 == 0) goto L_0x0128
            com.google.android.gms.internal.ads.zzacn r2 = new com.google.android.gms.internal.ads.zzacn     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzaji r5 = r15.zzbze     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            boolean r5 = r5.zzcor     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r2.<init>(r4, r3, r5)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r4 = r2
            goto L_0x0084
        L_0x0124:
            r4 = r12
            goto L_0x00ff
        L_0x0126:
            r3 = r12
            goto L_0x0110
        L_0x0128:
            java.lang.String r2 = "1"
            boolean r2 = r2.equals(r5)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r2 == 0) goto L_0x013c
            com.google.android.gms.internal.ads.zzaco r2 = new com.google.android.gms.internal.ads.zzaco     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzaji r5 = r15.zzbze     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            boolean r5 = r5.zzcor     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r2.<init>(r4, r3, r5)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r4 = r2
            goto L_0x0084
        L_0x013c:
            java.lang.String r2 = "3"
            boolean r2 = r2.equals(r5)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r2 == 0) goto L_0x01a0
            java.lang.String r2 = "custom_template_id"
            java.lang.String r2 = r7.getString(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzaoj r3 = new com.google.android.gms.internal.ads.zzaoj     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r3.<init>()     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            android.os.Handler r5 = com.google.android.gms.internal.ads.zzakk.zzcrm     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzaby r6 = new com.google.android.gms.internal.ads.zzaby     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r6.<init>(r15, r3, r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r5.post(r6)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            long r8 = zzbzx     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.lang.Object r2 = r3.get(r8, r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r2 == 0) goto L_0x016b
            com.google.android.gms.internal.ads.zzacp r2 = new com.google.android.gms.internal.ads.zzacp     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r2.<init>(r4)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r4 = r2
            goto L_0x0084
        L_0x016b:
            java.lang.String r3 = "No handler for custom template: "
            java.lang.String r2 = "custom_template_id"
            java.lang.String r2 = r7.getString(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            int r4 = r2.length()     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r4 == 0) goto L_0x0187
            java.lang.String r2 = r3.concat(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        L_0x0181:
            com.google.android.gms.internal.ads.zzakb.e(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        L_0x0184:
            r4 = r11
            goto L_0x0084
        L_0x0187:
            java.lang.String r2 = new java.lang.String     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r2.<init>(r3)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            goto L_0x0181
        L_0x018d:
            r2 = move-exception
        L_0x018e:
            java.lang.String r3 = "Malformed native JSON response."
            com.google.android.gms.internal.ads.zzakb.zzc(r3, r2)
        L_0x0193:
            boolean r2 = r15.zzcaa
            if (r2 != 0) goto L_0x019a
            r15.zzab(r12)
        L_0x019a:
            com.google.android.gms.internal.ads.zzajh r2 = r15.zza((com.google.android.gms.internal.ads.zzpb) r11, (boolean) r12)
            goto L_0x00b0
        L_0x01a0:
            r2 = 0
            r15.zzab(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            goto L_0x0184
        L_0x01a5:
            r2 = move-exception
            goto L_0x018e
        L_0x01a7:
            java.lang.String r2 = "tracking_urls_and_actions"
            org.json.JSONObject r5 = r7.getJSONObject(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.lang.String r2 = "impression_tracking_urls"
            org.json.JSONArray r6 = r5.optJSONArray(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r6 != 0) goto L_0x01f0
            r2 = r11
        L_0x01b6:
            if (r2 != 0) goto L_0x0206
            r2 = r11
        L_0x01b9:
            r15.zzcab = r2     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.lang.String r2 = "active_view"
            org.json.JSONObject r2 = r5.optJSONObject(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r15.zzcac = r2     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.lang.String r2 = "debug_signals"
            java.lang.String r2 = r7.optString(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r15.zzcad = r2     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.lang.String r2 = "omid_settings"
            java.lang.String r2 = r7.optString(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r15.zzcae = r2     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzpb r8 = r4.zza(r15, r7)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzpd r2 = new com.google.android.gms.internal.ads.zzpd     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            android.content.Context r3 = r15.mContext     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.ads.internal.zzbc r4 = r15.zzbzz     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzacm r5 = r15.zzaad     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzci r6 = r15.zzbjc     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzaji r9 = r15.zzbze     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzaef r9 = r9.zzcgs     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            com.google.android.gms.internal.ads.zzang r9 = r9.zzacr     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r8.zzb(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r3 = r8
            goto L_0x008f
        L_0x01f0:
            int r2 = r6.length()     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r3 = r12
        L_0x01f7:
            int r8 = r6.length()     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            if (r3 >= r8) goto L_0x01b6
            java.lang.String r8 = r6.getString(r3)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            r2[r3] = r8     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            int r3 = r3 + 1
            goto L_0x01f7
        L_0x0206:
            java.util.List r2 = java.util.Arrays.asList(r2)     // Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
            goto L_0x01b9
        L_0x020b:
            r2 = move-exception
            java.lang.String r3 = "Timeout when loading native ad."
            com.google.android.gms.internal.ads.zzakb.zzc(r3, r2)
            goto L_0x0193
        L_0x0212:
            r2 = move-exception
            java.lang.String r3 = "Error occured while doing native ads initialization."
            com.google.android.gms.internal.ads.zzakb.zzc(r3, r2)
            goto L_0x0193
        L_0x021a:
            r2 = move-exception
            goto L_0x018e
        L_0x021d:
            r2 = move-exception
            goto L_0x018e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzabv.call():com.google.android.gms.internal.ads.zzajh");
    }

    private final boolean zznx() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcaa;
        }
        return z;
    }

    public final zzanz<zzon> zza(JSONObject jSONObject, String str, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, z, z2);
    }

    public final List<zzanz<zzon>> zza(JSONObject jSONObject, String str, boolean z, boolean z2, boolean z3) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        ArrayList arrayList = new ArrayList();
        if (optJSONArray == null || optJSONArray.length() == 0) {
            zzd(0, false);
            return arrayList;
        }
        int length = z3 ? optJSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            arrayList.add(zza(jSONObject2, false, z2));
        }
        return arrayList;
    }

    public final Future<zzon> zza(JSONObject jSONObject, String str, boolean z) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
        boolean optBoolean = jSONObject2.optBoolean("require", true);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, optBoolean, z);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzaoj zzaoj, String str) {
        try {
            zzbv.zzel();
            zzaqw zza = zzarc.zza(this.mContext, zzasi.zzvq(), "native-omid", false, false, this.zzbjc, this.zzbze.zzcgs.zzacr, this.zzvr, (zzbo) null, this.zzbzz.zzbi(), this.zzbze.zzcoq);
            zza.zzuf().zza((zzasd) new zzabx(zzaoj, zza));
            zza.loadData(str, WebRequest.CONTENT_TYPE_HTML, WebRequest.CHARSET_UTF_8);
        } catch (Exception e) {
            zzaoj.set(null);
            zzane.zzc("", e);
        }
    }

    public final zzanz<zzaqw> zzc(JSONObject jSONObject, String str) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return zzano.zzi(null);
        }
        if (TextUtils.isEmpty(optJSONObject.optString("vast_xml"))) {
            zzakb.zzdk("Required field 'vast_xml' is missing");
            return zzano.zzi(null);
        }
        zzace zzace = new zzace(this.mContext, this.zzbjc, this.zzbze, this.zzvr, this.zzbzz);
        zzaoj zzaoj = new zzaoj();
        zzaoe.zzcvy.execute(new zzacf(zzace, optJSONObject, zzaoj));
        return zzaoj;
    }

    public final void zzd(int i, boolean z) {
        if (z) {
            zzab(i);
        }
    }

    public final zzanz<zzoj> zzg(JSONObject jSONObject) throws JSONException {
        List<zzanz> list;
        JSONObject optJSONObject = jSONObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return zzano.zzi(null);
        }
        String optString = optJSONObject.optString(PushConstants.STYLE_TEXT);
        int optInt = optJSONObject.optInt("text_size", -1);
        Integer zzb = zzb(optJSONObject, "text_color");
        Integer zzb2 = zzb(optJSONObject, "bg_color");
        int optInt2 = optJSONObject.optInt("animation_ms", 1000);
        int optInt3 = optJSONObject.optInt("presentation_ms", 4000);
        int i = (this.zzbze.zzcgs.zzadj == null || this.zzbze.zzcgs.zzadj.versionCode < 2) ? 1 : this.zzbze.zzcgs.zzadj.zzbjq;
        boolean optBoolean = optJSONObject.optBoolean("allow_pub_rendering");
        List arrayList = new ArrayList();
        if (optJSONObject.optJSONArray("images") != null) {
            list = zza(optJSONObject, "images", false, false, true);
        } else {
            arrayList.add(zza(optJSONObject, PushConstants.IMAGE, false, false));
            list = arrayList;
        }
        zzaoj zzaoj = new zzaoj();
        int size = list.size();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (zzanz zza : list) {
            zza.zza(new zzacc(atomicInteger, size, zzaoj, list), zzaki.zzcrj);
        }
        return zzano.zza(zzaoj, new zzaca(this, optString, zzb2, zzb, optInt, optInt3, optInt2, i, optBoolean), (Executor) zzaki.zzcrj);
    }
}
