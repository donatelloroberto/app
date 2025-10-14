package com.google.android.gms.internal.cast;

import com.google.android.gms.common.util.JsonUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzck {
    private final int zzfd;
    private final String zzxg;
    private final JSONObject zzxy;

    public zzck(JSONObject jSONObject) throws JSONException {
        this(jSONObject.optString("playerId"), jSONObject.optInt("playerState"), jSONObject.optJSONObject("playerData"));
    }

    private zzck(String str, int i, JSONObject jSONObject) {
        this.zzxg = str;
        this.zzfd = i;
        this.zzxy = jSONObject;
    }

    public final int getPlayerState() {
        return this.zzfd;
    }

    public final JSONObject getPlayerData() {
        return this.zzxy;
    }

    public final String getPlayerId() {
        return this.zzxg;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzck)) {
            return false;
        }
        zzck zzck = (zzck) obj;
        if (this.zzfd != zzck.zzfd || !zzdc.zza(this.zzxg, zzck.zzxg) || !JsonUtils.areJsonValuesEquivalent(this.zzxy, zzck.zzxy)) {
            return false;
        }
        return true;
    }
}
