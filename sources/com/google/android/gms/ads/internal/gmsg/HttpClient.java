package com.google.android.gms.ads.internal.gmsg;

import android.content.Context;
import android.support.annotation.Keep;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaki;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzue;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Keep
@KeepName
@zzadh
public class HttpClient implements zzv<zzue> {
    private final Context mContext;
    private final zzang zzyf;

    @zzadh
    @VisibleForTesting
    static class zza {
        private final String mKey;
        private final String mValue;

        public zza(String str, String str2) {
            this.mKey = str;
            this.mValue = str2;
        }

        public final String getKey() {
            return this.mKey;
        }

        public final String getValue() {
            return this.mValue;
        }
    }

    @zzadh
    @VisibleForTesting
    static class zzb {
        private final String zzbmm;
        private final URL zzbmn;
        private final ArrayList<zza> zzbmo;
        private final String zzbmp;

        zzb(String str, URL url, ArrayList<zza> arrayList, String str2) {
            this.zzbmm = str;
            this.zzbmn = url;
            this.zzbmo = arrayList;
            this.zzbmp = str2;
        }

        public final String zzkv() {
            return this.zzbmm;
        }

        public final URL zzkw() {
            return this.zzbmn;
        }

        public final ArrayList<zza> zzkx() {
            return this.zzbmo;
        }

        public final String zzky() {
            return this.zzbmp;
        }
    }

    @zzadh
    @VisibleForTesting
    class zzc {
        private final zzd zzbmq;
        private final boolean zzbmr;
        private final String zzbms;

        public zzc(HttpClient httpClient, boolean z, zzd zzd, String str) {
            this.zzbmr = z;
            this.zzbmq = zzd;
            this.zzbms = str;
        }

        public final String getReason() {
            return this.zzbms;
        }

        public final boolean isSuccess() {
            return this.zzbmr;
        }

        public final zzd zzkz() {
            return this.zzbmq;
        }
    }

    @zzadh
    @VisibleForTesting
    static class zzd {
        private final String zzbhy;
        private final String zzbmm;
        private final int zzbmt;
        private final List<zza> zzcf;

        zzd(String str, int i, List<zza> list, String str2) {
            this.zzbmm = str;
            this.zzbmt = i;
            this.zzcf = list;
            this.zzbhy = str2;
        }

        public final String getBody() {
            return this.zzbhy;
        }

        public final int getResponseCode() {
            return this.zzbmt;
        }

        public final String zzkv() {
            return this.zzbmm;
        }

        public final Iterable<zza> zzla() {
            return this.zzcf;
        }
    }

    public HttpClient(Context context, zzang zzang) {
        this.mContext = context;
        this.zzyf = zzang;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: byte[]} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r3v11 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.ads.internal.gmsg.HttpClient.zzc zza(com.google.android.gms.ads.internal.gmsg.HttpClient.zzb r10) {
        /*
            r9 = this;
            r2 = 0
            r3 = 0
            java.net.URL r0 = r10.zzkw()     // Catch:{ Exception -> 0x0110, all -> 0x0109 }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ Exception -> 0x0110, all -> 0x0109 }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ Exception -> 0x0110, all -> 0x0109 }
            com.google.android.gms.internal.ads.zzakk r1 = com.google.android.gms.ads.internal.zzbv.zzek()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            android.content.Context r4 = r9.mContext     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            com.google.android.gms.internal.ads.zzang r5 = r9.zzyf     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.lang.String r5 = r5.zzcw     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r6 = 0
            r1.zza((android.content.Context) r4, (java.lang.String) r5, (boolean) r6, (java.net.HttpURLConnection) r0)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.util.ArrayList r1 = r10.zzkx()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            int r5 = r1.size()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r4 = r2
        L_0x0025:
            if (r4 >= r5) goto L_0x004e
            java.lang.Object r2 = r1.get(r4)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            int r4 = r4 + 1
            com.google.android.gms.ads.internal.gmsg.HttpClient$zza r2 = (com.google.android.gms.ads.internal.gmsg.HttpClient.zza) r2     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.lang.String r6 = r2.getKey()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.lang.String r2 = r2.getValue()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r0.addRequestProperty(r6, r2)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            goto L_0x0025
        L_0x003b:
            r1 = move-exception
            r2 = r0
        L_0x003d:
            com.google.android.gms.ads.internal.gmsg.HttpClient$zzc r0 = new com.google.android.gms.ads.internal.gmsg.HttpClient$zzc     // Catch:{ all -> 0x010c }
            r3 = 0
            r4 = 0
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x010c }
            r0.<init>(r9, r3, r4, r1)     // Catch:{ all -> 0x010c }
            if (r2 == 0) goto L_0x004d
            r2.disconnect()
        L_0x004d:
            return r0
        L_0x004e:
            java.lang.String r1 = r10.zzky()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            if (r1 != 0) goto L_0x0077
            r1 = 1
            r0.setDoOutput(r1)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.lang.String r1 = r10.zzky()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            byte[] r3 = r1.getBytes()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            int r1 = r3.length     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r0.setFixedLengthStreamingMode(r1)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.io.OutputStream r2 = r0.getOutputStream()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r1.write(r3)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r1.close()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
        L_0x0077:
            com.google.android.gms.internal.ads.zzamy r4 = new com.google.android.gms.internal.ads.zzamy     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r4.<init>()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r4.zza((java.net.HttpURLConnection) r0, (byte[]) r3)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r5.<init>()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.util.Map r1 = r0.getHeaderFields()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            if (r1 == 0) goto L_0x00cf
            java.util.Map r1 = r0.getHeaderFields()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.util.Set r1 = r1.entrySet()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.util.Iterator r6 = r1.iterator()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
        L_0x0096:
            boolean r1 = r6.hasNext()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            if (r1 == 0) goto L_0x00cf
            java.lang.Object r1 = r6.next()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.lang.Object r2 = r1.getValue()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.util.List r2 = (java.util.List) r2     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.util.Iterator r7 = r2.iterator()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
        L_0x00ac:
            boolean r2 = r7.hasNext()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            if (r2 == 0) goto L_0x0096
            java.lang.Object r2 = r7.next()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            com.google.android.gms.ads.internal.gmsg.HttpClient$zza r8 = new com.google.android.gms.ads.internal.gmsg.HttpClient$zza     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.lang.Object r3 = r1.getKey()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r8.<init>(r3, r2)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r5.add(r8)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            goto L_0x00ac
        L_0x00c7:
            r1 = move-exception
            r3 = r0
        L_0x00c9:
            if (r3 == 0) goto L_0x00ce
            r3.disconnect()
        L_0x00ce:
            throw r1
        L_0x00cf:
            com.google.android.gms.ads.internal.gmsg.HttpClient$zzd r2 = new com.google.android.gms.ads.internal.gmsg.HttpClient$zzd     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.lang.String r1 = r10.zzkv()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            int r3 = r0.getResponseCode()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            com.google.android.gms.ads.internal.zzbv.zzek()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.io.InputStream r7 = r0.getInputStream()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.lang.String r6 = com.google.android.gms.internal.ads.zzakk.zza((java.io.InputStreamReader) r6)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r2.<init>(r1, r3, r5, r6)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            int r1 = r2.getResponseCode()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r4.zza((java.net.HttpURLConnection) r0, (int) r1)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            java.lang.String r1 = r2.getBody()     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r4.zzdg(r1)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            com.google.android.gms.ads.internal.gmsg.HttpClient$zzc r1 = new com.google.android.gms.ads.internal.gmsg.HttpClient$zzc     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            r3 = 1
            r4 = 0
            r1.<init>(r9, r3, r2, r4)     // Catch:{ Exception -> 0x003b, all -> 0x00c7 }
            if (r0 == 0) goto L_0x0106
            r0.disconnect()
        L_0x0106:
            r0 = r1
            goto L_0x004d
        L_0x0109:
            r0 = move-exception
            r1 = r0
            goto L_0x00c9
        L_0x010c:
            r0 = move-exception
            r1 = r0
            r3 = r2
            goto L_0x00c9
        L_0x0110:
            r0 = move-exception
            r1 = r0
            r2 = r3
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.gmsg.HttpClient.zza(com.google.android.gms.ads.internal.gmsg.HttpClient$zzb):com.google.android.gms.ads.internal.gmsg.HttpClient$zzc");
    }

    private static JSONObject zza(zzd zzd2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("http_request_id", zzd2.zzkv());
            if (zzd2.getBody() != null) {
                jSONObject.put(PushConstants.BODY, zzd2.getBody());
            }
            JSONArray jSONArray = new JSONArray();
            for (zza next : zzd2.zzla()) {
                jSONArray.put(new JSONObject().put("key", next.getKey()).put(FirebaseAnalytics.Param.VALUE, next.getValue()));
            }
            jSONObject.put("headers", jSONArray);
            jSONObject.put("response_code", zzd2.getResponseCode());
        } catch (JSONException e) {
            zzakb.zzb("Error constructing JSON for http response.", e);
        }
        return jSONObject;
    }

    private static zzb zzc(JSONObject jSONObject) {
        URL url;
        String optString = jSONObject.optString("http_request_id");
        String optString2 = jSONObject.optString(ImagesContract.URL);
        String optString3 = jSONObject.optString("post_body", (String) null);
        try {
            url = new URL(optString2);
        } catch (MalformedURLException e) {
            zzakb.zzb("Error constructing http request.", e);
            url = null;
        }
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("headers");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(new zza(optJSONObject.optString("key"), optJSONObject.optString(FirebaseAnalytics.Param.VALUE)));
            }
        }
        return new zzb(optString, url, arrayList, optString3);
    }

    @Keep
    @KeepName
    public JSONObject send(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            String optString = jSONObject.optString("http_request_id");
            zzc zza2 = zza(zzc(jSONObject));
            if (zza2.isSuccess()) {
                jSONObject2.put("response", zza(zza2.zzkz()));
                jSONObject2.put(FirebaseAnalytics.Param.SUCCESS, true);
            } else {
                jSONObject2.put("response", new JSONObject().put("http_request_id", optString));
                jSONObject2.put(FirebaseAnalytics.Param.SUCCESS, false);
                jSONObject2.put("reason", zza2.getReason());
            }
        } catch (Exception e) {
            zzakb.zzb("Error executing http request.", e);
            try {
                jSONObject2.put("response", new JSONObject().put("http_request_id", ""));
                jSONObject2.put(FirebaseAnalytics.Param.SUCCESS, false);
                jSONObject2.put("reason", e.toString());
            } catch (JSONException e2) {
                zzakb.zzb("Error executing http request.", e2);
            }
        }
        return jSONObject2;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzaki.zzb(new zzw(this, map, (zzue) obj));
    }
}
