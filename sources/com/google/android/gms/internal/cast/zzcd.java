package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

final class zzcd implements GameManagerClient.GameManagerResult {
    private final Status zzhf;
    private final String zzxg;
    private final long zzxh;
    private final JSONObject zzxi;

    zzcd(Status status, String str, long j, JSONObject jSONObject) {
        this.zzhf = status;
        this.zzxg = str;
        this.zzxh = j;
        this.zzxi = jSONObject;
    }

    public final Status getStatus() {
        return this.zzhf;
    }

    public final String getPlayerId() {
        return this.zzxg;
    }

    public final long getRequestId() {
        return this.zzxh;
    }

    public final JSONObject getExtraMessageData() {
        return this.zzxi;
    }
}
