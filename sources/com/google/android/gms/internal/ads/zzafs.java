package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.ImagesContract;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzafs {
    private static final SimpleDateFormat zzcho = new SimpleDateFormat("yyyyMMdd", Locale.US);

    /* JADX WARNING: Removed duplicated region for block: B:62:0x016b A[Catch:{ JSONException -> 0x0288 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzaej zza(android.content.Context r54, com.google.android.gms.internal.ads.zzaef r55, java.lang.String r56) {
        /*
            org.json.JSONObject r28 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0288 }
            r0 = r28
            r1 = r56
            r0.<init>(r1)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "ad_base_url"
            r5 = 0
            r0 = r28
            java.lang.String r6 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "ad_url"
            r5 = 0
            r0 = r28
            java.lang.String r7 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "ad_size"
            r5 = 0
            r0 = r28
            java.lang.String r19 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "ad_slot_size"
            r0 = r28
            r1 = r19
            java.lang.String r43 = r0.optString(r4, r1)     // Catch:{ JSONException -> 0x0288 }
            if (r55 == 0) goto L_0x00e2
            r0 = r55
            int r4 = r0.zzcdb     // Catch:{ JSONException -> 0x0288 }
            if (r4 == 0) goto L_0x00e2
            r27 = 1
        L_0x0038:
            java.lang.String r4 = "ad_json"
            r5 = 0
            r0 = r28
            java.lang.String r5 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            if (r5 != 0) goto L_0x004c
            java.lang.String r4 = "ad_html"
            r5 = 0
            r0 = r28
            java.lang.String r5 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x0288 }
        L_0x004c:
            if (r5 != 0) goto L_0x0057
            java.lang.String r4 = "body"
            r5 = 0
            r0 = r28
            java.lang.String r5 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x0288 }
        L_0x0057:
            if (r5 != 0) goto L_0x0067
            java.lang.String r4 = "ads"
            r0 = r28
            boolean r4 = r0.has(r4)     // Catch:{ JSONException -> 0x0288 }
            if (r4 == 0) goto L_0x0067
            java.lang.String r5 = r28.toString()     // Catch:{ JSONException -> 0x0288 }
        L_0x0067:
            r20 = -1
            java.lang.String r4 = "debug_dialog"
            r8 = 0
            r0 = r28
            java.lang.String r22 = r0.optString(r4, r8)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "debug_signals"
            r8 = 0
            r0 = r28
            java.lang.String r45 = r0.optString(r4, r8)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "interstitial_timeout"
            r0 = r28
            boolean r4 = r0.has(r4)     // Catch:{ JSONException -> 0x0288 }
            if (r4 == 0) goto L_0x00e6
            java.lang.String r4 = "interstitial_timeout"
            r0 = r28
            double r8 = r0.getDouble(r4)     // Catch:{ JSONException -> 0x0288 }
            r10 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r8 = r8 * r10
            long r0 = (long) r8     // Catch:{ JSONException -> 0x0288 }
            r16 = r0
        L_0x0096:
            java.lang.String r4 = "orientation"
            r8 = 0
            r0 = r28
            java.lang.String r4 = r0.optString(r4, r8)     // Catch:{ JSONException -> 0x0288 }
            r18 = -1
            java.lang.String r8 = "portrait"
            boolean r8 = r8.equals(r4)     // Catch:{ JSONException -> 0x0288 }
            if (r8 == 0) goto L_0x00e9
            com.google.android.gms.internal.ads.zzakq r4 = com.google.android.gms.ads.internal.zzbv.zzem()     // Catch:{ JSONException -> 0x0288 }
            int r18 = r4.zzrm()     // Catch:{ JSONException -> 0x0288 }
        L_0x00b1:
            r4 = 0
            boolean r8 = android.text.TextUtils.isEmpty(r5)     // Catch:{ JSONException -> 0x0288 }
            if (r8 == 0) goto L_0x02c4
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ JSONException -> 0x0288 }
            if (r8 != 0) goto L_0x02c4
            r0 = r55
            com.google.android.gms.internal.ads.zzang r4 = r0.zzacr     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r6 = r4.zzcw     // Catch:{ JSONException -> 0x0288 }
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r4 = r55
            r5 = r54
            com.google.android.gms.internal.ads.zzaej r4 = com.google.android.gms.internal.ads.zzafn.zza(r4, r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r6 = r4.zzbyq     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r7 = r4.zzceo     // Catch:{ JSONException -> 0x0288 }
            long r0 = r4.zzceu     // Catch:{ JSONException -> 0x0288 }
            r20 = r0
        L_0x00d9:
            if (r7 != 0) goto L_0x00fa
            com.google.android.gms.internal.ads.zzaej r4 = new com.google.android.gms.internal.ads.zzaej     // Catch:{ JSONException -> 0x0288 }
            r5 = 0
            r4.<init>(r5)     // Catch:{ JSONException -> 0x0288 }
        L_0x00e1:
            return r4
        L_0x00e2:
            r27 = 0
            goto L_0x0038
        L_0x00e6:
            r16 = -1
            goto L_0x0096
        L_0x00e9:
            java.lang.String r8 = "landscape"
            boolean r4 = r8.equals(r4)     // Catch:{ JSONException -> 0x0288 }
            if (r4 == 0) goto L_0x00b1
            com.google.android.gms.internal.ads.zzakq r4 = com.google.android.gms.ads.internal.zzbv.zzem()     // Catch:{ JSONException -> 0x0288 }
            int r18 = r4.zzrl()     // Catch:{ JSONException -> 0x0288 }
            goto L_0x00b1
        L_0x00fa:
            java.lang.String r5 = "click_urls"
            r0 = r28
            org.json.JSONArray r5 = r0.optJSONArray(r5)     // Catch:{ JSONException -> 0x0288 }
            if (r4 != 0) goto L_0x02a8
            r8 = 0
        L_0x0105:
            if (r5 == 0) goto L_0x010b
            java.util.List r8 = zza((org.json.JSONArray) r5, (java.util.List<java.lang.String>) r8)     // Catch:{ JSONException -> 0x0288 }
        L_0x010b:
            java.lang.String r5 = "impression_urls"
            r0 = r28
            org.json.JSONArray r5 = r0.optJSONArray(r5)     // Catch:{ JSONException -> 0x0288 }
            if (r4 != 0) goto L_0x02ac
            r9 = 0
        L_0x0116:
            if (r5 == 0) goto L_0x011c
            java.util.List r9 = zza((org.json.JSONArray) r5, (java.util.List<java.lang.String>) r9)     // Catch:{ JSONException -> 0x0288 }
        L_0x011c:
            java.lang.String r5 = "downloaded_impression_urls"
            r0 = r28
            org.json.JSONArray r5 = r0.optJSONArray(r5)     // Catch:{ JSONException -> 0x0288 }
            if (r4 != 0) goto L_0x02b0
            r51 = 0
        L_0x0128:
            if (r5 == 0) goto L_0x0130
            r0 = r51
            java.util.List r51 = zza((org.json.JSONArray) r5, (java.util.List<java.lang.String>) r0)     // Catch:{ JSONException -> 0x0288 }
        L_0x0130:
            java.lang.String r5 = "manual_impression_urls"
            r0 = r28
            org.json.JSONArray r5 = r0.optJSONArray(r5)     // Catch:{ JSONException -> 0x0288 }
            if (r4 != 0) goto L_0x02b6
            r15 = 0
        L_0x013b:
            if (r5 == 0) goto L_0x0141
            java.util.List r15 = zza((org.json.JSONArray) r5, (java.util.List<java.lang.String>) r15)     // Catch:{ JSONException -> 0x0288 }
        L_0x0141:
            if (r4 == 0) goto L_0x02c0
            int r5 = r4.orientation     // Catch:{ JSONException -> 0x0288 }
            r10 = -1
            if (r5 == r10) goto L_0x014c
            int r0 = r4.orientation     // Catch:{ JSONException -> 0x0288 }
            r18 = r0
        L_0x014c:
            long r10 = r4.zzcep     // Catch:{ JSONException -> 0x0288 }
            r12 = 0
            int r5 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r5 <= 0) goto L_0x02c0
            long r10 = r4.zzcep     // Catch:{ JSONException -> 0x0288 }
        L_0x0156:
            java.lang.String r4 = "active_view"
            r0 = r28
            java.lang.String r25 = r0.optString(r4)     // Catch:{ JSONException -> 0x0288 }
            r24 = 0
            java.lang.String r4 = "ad_is_javascript"
            r5 = 0
            r0 = r28
            boolean r23 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            if (r23 == 0) goto L_0x0174
            java.lang.String r4 = "ad_passback_url"
            r5 = 0
            r0 = r28
            java.lang.String r24 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x0288 }
        L_0x0174:
            java.lang.String r4 = "mediation"
            r5 = 0
            r0 = r28
            boolean r12 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "custom_render_allowed"
            r5 = 0
            r0 = r28
            boolean r26 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "content_url_opted_out"
            r5 = 1
            r0 = r28
            boolean r29 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "content_vertical_opted_out"
            r5 = 1
            r0 = r28
            boolean r46 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "prefetch"
            r5 = 0
            r0 = r28
            boolean r30 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "refresh_interval_milliseconds"
            r16 = -1
            r0 = r28
            r1 = r16
            long r16 = r0.optLong(r4, r1)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "mediation_config_cache_time_milliseconds"
            r32 = -1
            r0 = r28
            r1 = r32
            long r13 = r0.optLong(r4, r1)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "gws_query_id"
            java.lang.String r5 = ""
            r0 = r28
            java.lang.String r31 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "height"
            java.lang.String r5 = "fluid"
            java.lang.String r32 = ""
            r0 = r28
            r1 = r32
            java.lang.String r5 = r0.optString(r5, r1)     // Catch:{ JSONException -> 0x0288 }
            boolean r32 = r4.equals(r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "native_express"
            r5 = 0
            r0 = r28
            boolean r33 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "video_start_urls"
            r0 = r28
            org.json.JSONArray r4 = r0.optJSONArray(r4)     // Catch:{ JSONException -> 0x0288 }
            r5 = 0
            java.util.List r35 = zza((org.json.JSONArray) r4, (java.util.List<java.lang.String>) r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "video_complete_urls"
            r0 = r28
            org.json.JSONArray r4 = r0.optJSONArray(r4)     // Catch:{ JSONException -> 0x0288 }
            r5 = 0
            java.util.List r36 = zza((org.json.JSONArray) r4, (java.util.List<java.lang.String>) r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "rewards"
            r0 = r28
            org.json.JSONArray r4 = r0.optJSONArray(r4)     // Catch:{ JSONException -> 0x0288 }
            com.google.android.gms.internal.ads.zzaig r34 = com.google.android.gms.internal.ads.zzaig.zza(r4)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "use_displayed_impression"
            r5 = 0
            r0 = r28
            boolean r37 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "auto_protection_configuration"
            r0 = r28
            org.json.JSONObject r4 = r0.optJSONObject(r4)     // Catch:{ JSONException -> 0x0288 }
            com.google.android.gms.internal.ads.zzael r38 = com.google.android.gms.internal.ads.zzael.zzl(r4)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "set_cookie"
            java.lang.String r5 = ""
            r0 = r28
            java.lang.String r40 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "remote_ping_urls"
            r0 = r28
            org.json.JSONArray r4 = r0.optJSONArray(r4)     // Catch:{ JSONException -> 0x0288 }
            r5 = 0
            java.util.List r41 = zza((org.json.JSONArray) r4, (java.util.List<java.lang.String>) r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "safe_browsing"
            r0 = r28
            org.json.JSONObject r4 = r0.optJSONObject(r4)     // Catch:{ JSONException -> 0x0288 }
            com.google.android.gms.internal.ads.zzaiq r44 = com.google.android.gms.internal.ads.zzaiq.zzo(r4)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "render_in_browser"
            r0 = r55
            boolean r5 = r0.zzbss     // Catch:{ JSONException -> 0x0288 }
            r0 = r28
            boolean r42 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "custom_close_blocked"
            r0 = r28
            boolean r48 = r0.optBoolean(r4)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "enable_omid"
            r5 = 0
            r0 = r28
            boolean r50 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "omid_settings"
            r5 = 0
            r0 = r28
            java.lang.String r53 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            java.lang.String r4 = "disable_closable_area"
            r5 = 0
            r0 = r28
            boolean r52 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x0288 }
            com.google.android.gms.internal.ads.zzaej r4 = new com.google.android.gms.internal.ads.zzaej     // Catch:{ JSONException -> 0x0288 }
            r0 = r55
            boolean r0 = r0.zzcdd     // Catch:{ JSONException -> 0x0288 }
            r28 = r0
            r0 = r55
            boolean r0 = r0.zzcdr     // Catch:{ JSONException -> 0x0288 }
            r39 = r0
            r0 = r55
            boolean r0 = r0.zzced     // Catch:{ JSONException -> 0x0288 }
            r47 = r0
            r49 = 0
            r5 = r55
            r4.<init>(r5, r6, r7, r8, r9, r10, r12, r13, r15, r16, r18, r19, r20, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53)     // Catch:{ JSONException -> 0x0288 }
            goto L_0x00e1
        L_0x0288:
            r4 = move-exception
            java.lang.String r5 = "Could not parse the inline ad response: "
            java.lang.String r4 = r4.getMessage()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r6 = r4.length()
            if (r6 == 0) goto L_0x02ba
            java.lang.String r4 = r5.concat(r4)
        L_0x029d:
            com.google.android.gms.internal.ads.zzakb.zzdk(r4)
            com.google.android.gms.internal.ads.zzaej r4 = new com.google.android.gms.internal.ads.zzaej
            r5 = 0
            r4.<init>(r5)
            goto L_0x00e1
        L_0x02a8:
            java.util.List<java.lang.String> r8 = r4.zzbsn     // Catch:{ JSONException -> 0x0288 }
            goto L_0x0105
        L_0x02ac:
            java.util.List<java.lang.String> r9 = r4.zzbso     // Catch:{ JSONException -> 0x0288 }
            goto L_0x0116
        L_0x02b0:
            java.util.List<java.lang.String> r0 = r4.zzbsp     // Catch:{ JSONException -> 0x0288 }
            r51 = r0
            goto L_0x0128
        L_0x02b6:
            java.util.List<java.lang.String> r15 = r4.zzces     // Catch:{ JSONException -> 0x0288 }
            goto L_0x013b
        L_0x02ba:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r5)
            goto L_0x029d
        L_0x02c0:
            r10 = r16
            goto L_0x0156
        L_0x02c4:
            r7 = r5
            goto L_0x00d9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzafs.zza(android.content.Context, com.google.android.gms.internal.ads.zzaef, java.lang.String):com.google.android.gms.internal.ads.zzaej");
    }

    @Nullable
    private static List<String> zza(@Nullable JSONArray jSONArray, @Nullable List<String> list) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            list.add(jSONArray.getString(i));
        }
        return list;
    }

    @Nullable
    public static JSONObject zza(Context context, zzafl zzafl) {
        String str;
        zzaef zzaef = zzafl.zzcgs;
        Location location = zzafl.zzaqe;
        zzaga zzaga = zzafl.zzcgt;
        Bundle bundle = zzafl.zzcdc;
        JSONObject jSONObject = zzafl.zzcgu;
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("extra_caps", zzkb.zzik().zzd(zznk.zzbbk));
            if (zzafl.zzcdj.size() > 0) {
                hashMap.put("eid", TextUtils.join(",", zzafl.zzcdj));
            }
            if (zzaef.zzccu != null) {
                hashMap.put("ad_pos", zzaef.zzccu);
            }
            zzjj zzjj = zzaef.zzccv;
            String zzqn = zzajw.zzqn();
            if (zzqn != null) {
                hashMap.put("abf", zzqn);
            }
            if (zzjj.zzapw != -1) {
                hashMap.put("cust_age", zzcho.format(new Date(zzjj.zzapw)));
            }
            if (zzjj.extras != null) {
                hashMap.put("extras", zzjj.extras);
            }
            if (zzjj.zzapx != -1) {
                hashMap.put("cust_gender", Integer.valueOf(zzjj.zzapx));
            }
            if (zzjj.zzapy != null) {
                hashMap.put("kw", zzjj.zzapy);
            }
            if (zzjj.zzaqa != -1) {
                hashMap.put("tag_for_child_directed_treatment", Integer.valueOf(zzjj.zzaqa));
            }
            if (zzjj.zzapz) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbfp)).booleanValue()) {
                    hashMap.put("test_request", true);
                } else {
                    hashMap.put("adtest", "on");
                }
            }
            if (zzjj.versionCode >= 2) {
                if (zzjj.zzaqb) {
                    hashMap.put("d_imp_hdr", 1);
                }
                if (!TextUtils.isEmpty(zzjj.zzaqc)) {
                    hashMap.put("ppid", zzjj.zzaqc);
                }
            }
            if (zzjj.versionCode >= 3 && zzjj.zzaqf != null) {
                hashMap.put(ImagesContract.URL, zzjj.zzaqf);
            }
            if (zzjj.versionCode >= 5) {
                if (zzjj.zzaqh != null) {
                    hashMap.put("custom_targeting", zzjj.zzaqh);
                }
                if (zzjj.zzaqi != null) {
                    hashMap.put("category_exclusions", zzjj.zzaqi);
                }
                if (zzjj.zzaqj != null) {
                    hashMap.put("request_agent", zzjj.zzaqj);
                }
            }
            if (zzjj.versionCode >= 6 && zzjj.zzaqk != null) {
                hashMap.put("request_pkg", zzjj.zzaqk);
            }
            if (zzjj.versionCode >= 7) {
                hashMap.put("is_designed_for_families", Boolean.valueOf(zzjj.zzaql));
            }
            if (zzaef.zzacv.zzard != null) {
                boolean z = false;
                boolean z2 = false;
                for (zzjn zzjn : zzaef.zzacv.zzard) {
                    if (!zzjn.zzarf && !z) {
                        hashMap.put("format", zzjn.zzarb);
                        z = true;
                    }
                    if (zzjn.zzarf && !z2) {
                        hashMap.put("fluid", "height");
                        z2 = true;
                    }
                    if (z && z2) {
                        break;
                    }
                }
            } else {
                hashMap.put("format", zzaef.zzacv.zzarb);
                if (zzaef.zzacv.zzarf) {
                    hashMap.put("fluid", "height");
                }
            }
            if (zzaef.zzacv.width == -1) {
                hashMap.put("smart_w", "full");
            }
            if (zzaef.zzacv.height == -2) {
                hashMap.put("smart_h", "auto");
            }
            if (zzaef.zzacv.zzard != null) {
                StringBuilder sb = new StringBuilder();
                boolean z3 = false;
                for (zzjn zzjn2 : zzaef.zzacv.zzard) {
                    if (zzjn2.zzarf) {
                        z3 = true;
                    } else {
                        if (sb.length() != 0) {
                            sb.append("|");
                        }
                        sb.append(zzjn2.width == -1 ? (int) (((float) zzjn2.widthPixels) / zzaga.zzagu) : zzjn2.width);
                        sb.append("x");
                        sb.append(zzjn2.height == -2 ? (int) (((float) zzjn2.heightPixels) / zzaga.zzagu) : zzjn2.height);
                    }
                }
                if (z3) {
                    if (sb.length() != 0) {
                        sb.insert(0, "|");
                    }
                    sb.insert(0, "320x50");
                }
                hashMap.put("sz", sb);
            }
            if (zzaef.zzcdb != 0) {
                hashMap.put("native_version", Integer.valueOf(zzaef.zzcdb));
                hashMap.put("native_templates", zzaef.zzads);
                zzpl zzpl = zzaef.zzadj;
                if (zzpl != null) {
                    switch (zzpl.zzbjo) {
                        case 0:
                            str = "any";
                            break;
                        case 1:
                            str = DeviceInfo.ORIENTATION_PORTRAIT;
                            break;
                        case 2:
                            str = DeviceInfo.ORIENTATION_LANDSCAPE;
                            break;
                        default:
                            str = "not_set";
                            break;
                    }
                } else {
                    str = "any";
                }
                hashMap.put("native_image_orientation", str);
                if (!zzaef.zzcdk.isEmpty()) {
                    hashMap.put("native_custom_templates", zzaef.zzcdk);
                }
                if (zzaef.versionCode >= 24) {
                    hashMap.put("max_num_ads", Integer.valueOf(zzaef.zzceg));
                }
                if (!TextUtils.isEmpty(zzaef.zzcee)) {
                    try {
                        hashMap.put("native_advanced_settings", new JSONArray(zzaef.zzcee));
                    } catch (JSONException e) {
                        zzakb.zzc("Problem creating json from native advanced settings", e);
                    }
                }
            }
            if (zzaef.zzadn != null && zzaef.zzadn.size() > 0) {
                for (Integer next : zzaef.zzadn) {
                    if (next.intValue() == 2) {
                        hashMap.put("iba", true);
                    } else if (next.intValue() == 1) {
                        hashMap.put("ina", true);
                    }
                }
            }
            if (zzaef.zzacv.zzarg) {
                hashMap.put("ene", true);
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzaxv)).booleanValue()) {
                hashMap.put("xsrve", true);
            }
            if (zzaef.zzadl != null) {
                hashMap.put("is_icon_ad", true);
                hashMap.put("icon_ad_expansion_behavior", Integer.valueOf(zzaef.zzadl.zzasj));
            }
            hashMap.put("slotname", zzaef.zzacp);
            hashMap.put("pn", zzaef.applicationInfo.packageName);
            if (zzaef.zzccw != null) {
                hashMap.put("vc", Integer.valueOf(zzaef.zzccw.versionCode));
            }
            hashMap.put("ms", zzafl.zzccx);
            hashMap.put("seq_num", zzaef.zzccy);
            hashMap.put("session_id", zzaef.zzccz);
            hashMap.put("js", zzaef.zzacr.zzcw);
            zzagk zzagk = zzafl.zzcgo;
            Bundle bundle2 = zzaef.zzcdw;
            Bundle bundle3 = zzafl.zzcgn;
            hashMap.put("am", Integer.valueOf(zzaga.zzcjk));
            hashMap.put("cog", zzv(zzaga.zzcjl));
            hashMap.put("coh", zzv(zzaga.zzcjm));
            if (!TextUtils.isEmpty(zzaga.zzcjn)) {
                hashMap.put("carrier", zzaga.zzcjn);
            }
            hashMap.put("gl", zzaga.zzcjo);
            if (zzaga.zzcjp) {
                hashMap.put("simulator", 1);
            }
            if (zzaga.zzcjq) {
                hashMap.put("is_sidewinder", 1);
            }
            hashMap.put("ma", zzv(zzaga.zzcjr));
            hashMap.put("sp", zzv(zzaga.zzcjs));
            hashMap.put("hl", zzaga.zzcjt);
            if (!TextUtils.isEmpty(zzaga.zzcju)) {
                hashMap.put("mv", zzaga.zzcju);
            }
            hashMap.put("muv", Integer.valueOf(zzaga.zzcjw));
            if (zzaga.zzcjx != -2) {
                hashMap.put("cnt", Integer.valueOf(zzaga.zzcjx));
            }
            hashMap.put("gnt", Integer.valueOf(zzaga.zzcjy));
            hashMap.put("pt", Integer.valueOf(zzaga.zzcjz));
            hashMap.put("rm", Integer.valueOf(zzaga.zzcka));
            hashMap.put("riv", Integer.valueOf(zzaga.zzckb));
            Bundle bundle4 = new Bundle();
            bundle4.putString("build_build", zzaga.zzckg);
            bundle4.putString("build_device", zzaga.zzckh);
            Bundle bundle5 = new Bundle();
            bundle5.putBoolean("is_charging", zzaga.zzckd);
            bundle5.putDouble("battery_level", zzaga.zzckc);
            bundle4.putBundle("battery", bundle5);
            Bundle bundle6 = new Bundle();
            bundle6.putInt("active_network_state", zzaga.zzckf);
            bundle6.putBoolean("active_network_metered", zzaga.zzcke);
            if (zzagk != null) {
                Bundle bundle7 = new Bundle();
                bundle7.putInt("predicted_latency_micros", zzagk.zzckq);
                bundle7.putLong("predicted_down_throughput_bps", zzagk.zzckr);
                bundle7.putLong("predicted_up_throughput_bps", zzagk.zzcks);
                bundle6.putBundle("predictions", bundle7);
            }
            bundle4.putBundle("network", bundle6);
            Bundle bundle8 = new Bundle();
            bundle8.putBoolean("is_browser_custom_tabs_capable", zzaga.zzcki);
            bundle4.putBundle("browser", bundle8);
            if (bundle2 != null) {
                Bundle bundle9 = new Bundle();
                bundle9.putString("runtime_free", Long.toString(bundle2.getLong("runtime_free_memory", -1)));
                bundle9.putString("runtime_max", Long.toString(bundle2.getLong("runtime_max_memory", -1)));
                bundle9.putString("runtime_total", Long.toString(bundle2.getLong("runtime_total_memory", -1)));
                bundle9.putString("web_view_count", Integer.toString(bundle2.getInt("web_view_count", 0)));
                Debug.MemoryInfo memoryInfo = (Debug.MemoryInfo) bundle2.getParcelable("debug_memory_info");
                if (memoryInfo != null) {
                    bundle9.putString("debug_info_dalvik_private_dirty", Integer.toString(memoryInfo.dalvikPrivateDirty));
                    bundle9.putString("debug_info_dalvik_pss", Integer.toString(memoryInfo.dalvikPss));
                    bundle9.putString("debug_info_dalvik_shared_dirty", Integer.toString(memoryInfo.dalvikSharedDirty));
                    bundle9.putString("debug_info_native_private_dirty", Integer.toString(memoryInfo.nativePrivateDirty));
                    bundle9.putString("debug_info_native_pss", Integer.toString(memoryInfo.nativePss));
                    bundle9.putString("debug_info_native_shared_dirty", Integer.toString(memoryInfo.nativeSharedDirty));
                    bundle9.putString("debug_info_other_private_dirty", Integer.toString(memoryInfo.otherPrivateDirty));
                    bundle9.putString("debug_info_other_pss", Integer.toString(memoryInfo.otherPss));
                    bundle9.putString("debug_info_other_shared_dirty", Integer.toString(memoryInfo.otherSharedDirty));
                }
                bundle4.putBundle("android_mem_info", bundle9);
            }
            Bundle bundle10 = new Bundle();
            bundle10.putBundle("parental_controls", bundle3);
            if (!TextUtils.isEmpty(zzaga.zzcjv)) {
                bundle10.putString("package_version", zzaga.zzcjv);
            }
            bundle4.putBundle("play_store", bundle10);
            hashMap.put("device", bundle4);
            Bundle bundle11 = new Bundle();
            bundle11.putString("doritos", zzafl.zzcgp);
            bundle11.putString("doritos_v2", zzafl.zzcgq);
            if (((Boolean) zzkb.zzik().zzd(zznk.zzayj)).booleanValue()) {
                String str2 = null;
                boolean z4 = false;
                if (zzafl.zzcgr != null) {
                    str2 = zzafl.zzcgr.getId();
                    z4 = zzafl.zzcgr.isLimitAdTrackingEnabled();
                }
                if (!TextUtils.isEmpty(str2)) {
                    bundle11.putString("rdid", str2);
                    bundle11.putBoolean("is_lat", z4);
                    bundle11.putString("idtype", "adid");
                } else {
                    zzkb.zzif();
                    bundle11.putString("pdid", zzamu.zzbd(context));
                    bundle11.putString("pdidtype", "ssaid");
                }
            }
            hashMap.put("pii", bundle11);
            hashMap.put("platform", Build.MANUFACTURER);
            hashMap.put("submodel", Build.MODEL);
            if (location != null) {
                zza((HashMap<String, Object>) hashMap, location);
            } else if (zzaef.zzccv.versionCode >= 2 && zzaef.zzccv.zzaqe != null) {
                zza((HashMap<String, Object>) hashMap, zzaef.zzccv.zzaqe);
            }
            if (zzaef.versionCode >= 2) {
                hashMap.put("quality_signals", zzaef.zzcda);
            }
            if (zzaef.versionCode >= 4 && zzaef.zzcdd) {
                hashMap.put("forceHttps", Boolean.valueOf(zzaef.zzcdd));
            }
            if (bundle != null) {
                hashMap.put("content_info", bundle);
            }
            if (zzaef.versionCode >= 5) {
                hashMap.put("u_sd", Float.valueOf(zzaef.zzagu));
                hashMap.put("sh", Integer.valueOf(zzaef.zzcdf));
                hashMap.put("sw", Integer.valueOf(zzaef.zzcde));
            } else {
                hashMap.put("u_sd", Float.valueOf(zzaga.zzagu));
                hashMap.put("sh", Integer.valueOf(zzaga.zzcdf));
                hashMap.put("sw", Integer.valueOf(zzaga.zzcde));
            }
            if (zzaef.versionCode >= 6) {
                if (!TextUtils.isEmpty(zzaef.zzcdg)) {
                    try {
                        hashMap.put("view_hierarchy", new JSONObject(zzaef.zzcdg));
                    } catch (JSONException e2) {
                        zzakb.zzc("Problem serializing view hierarchy to JSON", e2);
                    }
                }
                hashMap.put("correlation_id", Long.valueOf(zzaef.zzcdh));
            }
            if (zzaef.versionCode >= 7) {
                hashMap.put("request_id", zzaef.zzcdi);
            }
            if (zzaef.versionCode >= 12 && !TextUtils.isEmpty(zzaef.zzcdm)) {
                hashMap.put("anchor", zzaef.zzcdm);
            }
            if (zzaef.versionCode >= 13) {
                hashMap.put("android_app_volume", Float.valueOf(zzaef.zzcdn));
            }
            if (zzaef.versionCode >= 18) {
                hashMap.put("android_app_muted", Boolean.valueOf(zzaef.zzcdt));
            }
            if (zzaef.versionCode >= 14 && zzaef.zzcdo > 0) {
                hashMap.put("target_api", Integer.valueOf(zzaef.zzcdo));
            }
            if (zzaef.versionCode >= 15) {
                hashMap.put("scroll_index", Integer.valueOf(zzaef.zzcdp == -1 ? -1 : zzaef.zzcdp));
            }
            if (zzaef.versionCode >= 16) {
                hashMap.put("_activity_context", Boolean.valueOf(zzaef.zzcdq));
            }
            if (zzaef.versionCode >= 18) {
                if (!TextUtils.isEmpty(zzaef.zzcdu)) {
                    try {
                        hashMap.put("app_settings", new JSONObject(zzaef.zzcdu));
                    } catch (JSONException e3) {
                        zzakb.zzc("Problem creating json from app settings", e3);
                    }
                }
                hashMap.put("render_in_browser", Boolean.valueOf(zzaef.zzbss));
            }
            if (zzaef.versionCode >= 18) {
                hashMap.put("android_num_video_cache_tasks", Integer.valueOf(zzaef.zzcdv));
            }
            zzang zzang = zzaef.zzacr;
            boolean z5 = zzaef.zzceh;
            boolean z6 = zzafl.zzcgv;
            boolean z7 = zzaef.zzcej;
            Bundle bundle12 = new Bundle();
            Bundle bundle13 = new Bundle();
            bundle13.putString("cl", "193400285");
            bundle13.putString("rapid_rc", "dev");
            bundle13.putString("rapid_rollup", "HEAD");
            bundle12.putBundle("build_meta", bundle13);
            bundle12.putString("mf", Boolean.toString(((Boolean) zzkb.zzik().zzd(zznk.zzbbm)).booleanValue()));
            bundle12.putBoolean("instant_app", z5);
            bundle12.putBoolean("lite", zzang.zzcvh);
            bundle12.putBoolean("local_service", z6);
            bundle12.putBoolean("is_privileged_process", z7);
            hashMap.put("sdk_env", bundle12);
            hashMap.put("cache_state", jSONObject);
            if (zzaef.versionCode >= 19) {
                hashMap.put("gct", zzaef.zzcdx);
            }
            if (zzaef.versionCode >= 21 && zzaef.zzcdy) {
                hashMap.put("de", "1");
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzayy)).booleanValue()) {
                String str3 = zzaef.zzacv.zzarb;
                boolean z8 = str3.equals("interstitial_mb") || str3.equals("reward_mb");
                Bundle bundle14 = zzaef.zzcdz;
                boolean z9 = bundle14 != null;
                if (z8 && z9) {
                    Bundle bundle15 = new Bundle();
                    bundle15.putBundle("interstitial_pool", bundle14);
                    hashMap.put("counters", bundle15);
                }
            }
            if (zzaef.zzcea != null) {
                hashMap.put("gmp_app_id", zzaef.zzcea);
            }
            if (zzaef.zzceb == null) {
                hashMap.put("fbs_aiid", "");
            } else if ("TIME_OUT".equals(zzaef.zzceb)) {
                hashMap.put("sai_timeout", zzkb.zzik().zzd(zznk.zzaxt));
            } else {
                hashMap.put("fbs_aiid", zzaef.zzceb);
            }
            if (zzaef.zzcec != null) {
                hashMap.put("fbs_aeid", zzaef.zzcec);
            }
            if (zzaef.versionCode >= 24) {
                hashMap.put("disable_ml", Boolean.valueOf(zzaef.zzcei));
            }
            String str4 = (String) zzkb.zzik().zzd(zznk.zzavo);
            if (str4 != null && !str4.isEmpty()) {
                if (Build.VERSION.SDK_INT >= ((Integer) zzkb.zzik().zzd(zznk.zzavp)).intValue()) {
                    HashMap hashMap2 = new HashMap();
                    for (String str5 : str4.split(",")) {
                        hashMap2.put(str5, zzams.zzdd(str5));
                    }
                    hashMap.put("video_decoders", hashMap2);
                }
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbet)).booleanValue()) {
                hashMap.put("omid_v", zzbv.zzfa().getVersion(context));
            }
            if (zzaef.zzcek != null && !zzaef.zzcek.isEmpty()) {
                hashMap.put("android_permissions", zzaef.zzcek);
            }
            if (zzakb.isLoggable(2)) {
                String valueOf = String.valueOf(zzbv.zzek().zzn(hashMap).toString(2));
                zzakb.v(valueOf.length() != 0 ? "Ad Request JSON: ".concat(valueOf) : new String("Ad Request JSON: "));
            }
            return zzbv.zzek().zzn(hashMap);
        } catch (JSONException e4) {
            String valueOf2 = String.valueOf(e4.getMessage());
            zzakb.zzdk(valueOf2.length() != 0 ? "Problem serializing ad request to JSON: ".concat(valueOf2) : new String("Problem serializing ad request to JSON: "));
            return null;
        }
    }

    private static void zza(HashMap<String, Object> hashMap, Location location) {
        HashMap hashMap2 = new HashMap();
        Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long valueOf2 = Long.valueOf(location.getTime() * 1000);
        Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        hashMap2.put("radius", valueOf);
        hashMap2.put("lat", valueOf3);
        hashMap2.put("long", valueOf4);
        hashMap2.put("time", valueOf2);
        hashMap.put("uule", hashMap2);
    }

    public static JSONObject zzb(zzaej zzaej) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (zzaej.zzbyq != null) {
            jSONObject.put("ad_base_url", zzaej.zzbyq);
        }
        if (zzaej.zzcet != null) {
            jSONObject.put("ad_size", zzaej.zzcet);
        }
        jSONObject.put("native", zzaej.zzare);
        if (zzaej.zzare) {
            jSONObject.put("ad_json", zzaej.zzceo);
        } else {
            jSONObject.put("ad_html", zzaej.zzceo);
        }
        if (zzaej.zzcev != null) {
            jSONObject.put("debug_dialog", zzaej.zzcev);
        }
        if (zzaej.zzcfl != null) {
            jSONObject.put("debug_signals", zzaej.zzcfl);
        }
        if (zzaej.zzcep != -1) {
            jSONObject.put("interstitial_timeout", ((double) zzaej.zzcep) / 1000.0d);
        }
        if (zzaej.orientation == zzbv.zzem().zzrm()) {
            jSONObject.put("orientation", DeviceInfo.ORIENTATION_PORTRAIT);
        } else if (zzaej.orientation == zzbv.zzem().zzrl()) {
            jSONObject.put("orientation", DeviceInfo.ORIENTATION_LANDSCAPE);
        }
        if (zzaej.zzbsn != null) {
            jSONObject.put("click_urls", zzm(zzaej.zzbsn));
        }
        if (zzaej.zzbso != null) {
            jSONObject.put("impression_urls", zzm(zzaej.zzbso));
        }
        if (zzaej.zzbsp != null) {
            jSONObject.put("downloaded_impression_urls", zzm(zzaej.zzbsp));
        }
        if (zzaej.zzces != null) {
            jSONObject.put("manual_impression_urls", zzm(zzaej.zzces));
        }
        if (zzaej.zzcey != null) {
            jSONObject.put("active_view", zzaej.zzcey);
        }
        jSONObject.put("ad_is_javascript", zzaej.zzcew);
        if (zzaej.zzcex != null) {
            jSONObject.put("ad_passback_url", zzaej.zzcex);
        }
        jSONObject.put("mediation", zzaej.zzceq);
        jSONObject.put("custom_render_allowed", zzaej.zzcez);
        jSONObject.put("content_url_opted_out", zzaej.zzcfa);
        jSONObject.put("content_vertical_opted_out", zzaej.zzcfm);
        jSONObject.put("prefetch", zzaej.zzcfb);
        if (zzaej.zzbsu != -1) {
            jSONObject.put("refresh_interval_milliseconds", zzaej.zzbsu);
        }
        if (zzaej.zzcer != -1) {
            jSONObject.put("mediation_config_cache_time_milliseconds", zzaej.zzcer);
        }
        if (!TextUtils.isEmpty(zzaej.zzamj)) {
            jSONObject.put("gws_query_id", zzaej.zzamj);
        }
        jSONObject.put("fluid", zzaej.zzarf ? "height" : "");
        jSONObject.put("native_express", zzaej.zzarg);
        if (zzaej.zzcff != null) {
            jSONObject.put("video_start_urls", zzm(zzaej.zzcff));
        }
        if (zzaej.zzcfg != null) {
            jSONObject.put("video_complete_urls", zzm(zzaej.zzcfg));
        }
        if (zzaej.zzcfe != null) {
            zzaig zzaig = zzaej.zzcfe;
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("rb_type", zzaig.type);
            jSONObject2.put("rb_amount", zzaig.zzcmk);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject2);
            jSONObject.put("rewards", jSONArray);
        }
        jSONObject.put("use_displayed_impression", zzaej.zzcfh);
        jSONObject.put("auto_protection_configuration", zzaej.zzcfi);
        jSONObject.put("render_in_browser", zzaej.zzbss);
        jSONObject.put("disable_closable_area", zzaej.zzzm);
        return jSONObject;
    }

    @Nullable
    private static JSONArray zzm(List<String> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    private static Integer zzv(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }
}
