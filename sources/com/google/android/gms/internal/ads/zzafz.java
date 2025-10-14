package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@zzadh
public final class zzafz {
    private final int errorCode;
    private final String type;
    private String url;
    private final String zzcdi;
    private final List<String> zzcjd;
    private final String zzcje;
    private final String zzcjf;
    private final boolean zzcjg;
    private final String zzcjh;
    private final boolean zzcji;
    private final JSONObject zzcjj;

    public zzafz(int i, Map<String, String> map) {
        this.url = map.get(ImagesContract.URL);
        this.zzcje = map.get("base_uri");
        this.zzcjf = map.get("post_parameters");
        this.zzcjg = parseBoolean(map.get("drt_include"));
        this.zzcdi = map.get("request_id");
        this.type = map.get(AppMeasurement.Param.TYPE);
        this.zzcjd = zzbz(map.get("errors"));
        this.errorCode = i;
        this.zzcjh = map.get("fetched_ad");
        this.zzcji = parseBoolean(map.get("render_test_ad_label"));
        this.zzcjj = new JSONObject();
    }

    public zzafz(JSONObject jSONObject) {
        int i = 1;
        this.url = jSONObject.optString(ImagesContract.URL);
        this.zzcje = jSONObject.optString("base_uri");
        this.zzcjf = jSONObject.optString("post_parameters");
        this.zzcjg = parseBoolean(jSONObject.optString("drt_include"));
        this.zzcdi = jSONObject.optString("request_id");
        this.type = jSONObject.optString(AppMeasurement.Param.TYPE);
        this.zzcjd = zzbz(jSONObject.optString("errors"));
        this.errorCode = jSONObject.optInt("valid", 0) == 1 ? -2 : i;
        this.zzcjh = jSONObject.optString("fetched_ad");
        this.zzcji = jSONObject.optBoolean("render_test_ad_label");
        JSONObject optJSONObject = jSONObject.optJSONObject("preprocessor_flags");
        this.zzcjj = optJSONObject == null ? new JSONObject() : optJSONObject;
    }

    private static boolean parseBoolean(String str) {
        return str != null && (str.equals("1") || str.equals("true"));
    }

    private static List<String> zzbz(String str) {
        if (str == null) {
            return null;
        }
        return Arrays.asList(str.split(","));
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final String getType() {
        return this.type;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        this.url = str;
    }

    public final List<String> zzoh() {
        return this.zzcjd;
    }

    public final String zzoi() {
        return this.zzcje;
    }

    public final String zzoj() {
        return this.zzcjf;
    }

    public final boolean zzok() {
        return this.zzcjg;
    }

    public final String zzol() {
        return this.zzcdi;
    }

    public final String zzom() {
        return this.zzcjh;
    }

    public final boolean zzon() {
        return this.zzcji;
    }
}
