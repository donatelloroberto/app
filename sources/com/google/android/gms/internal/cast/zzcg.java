package com.google.android.gms.internal.cast;

import org.json.JSONException;
import org.json.JSONObject;

public final class zzcg {
    private final String version;
    private final String zzxw;
    private final int zzxx;

    public zzcg(JSONObject jSONObject) throws JSONException {
        this(jSONObject.optString("applicationName"), jSONObject.optInt("maxPlayers"), jSONObject.optString("version"));
    }

    private zzcg(String str, int i, String str2) {
        this.zzxw = str;
        this.zzxx = i;
        this.version = str2;
    }

    public final String zzdx() {
        return this.zzxw;
    }

    public final int getMaxPlayers() {
        return this.zzxx;
    }

    public final String getVersion() {
        return this.version;
    }
}
