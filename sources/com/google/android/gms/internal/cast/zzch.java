package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.PlayerInfo;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.JsonUtils;
import org.json.JSONObject;

public final class zzch implements PlayerInfo {
    private final int zzfd;
    private final String zzxg;
    private final JSONObject zzxy;
    private final boolean zzxz;

    public zzch(String str, int i, JSONObject jSONObject, boolean z) {
        this.zzxg = str;
        this.zzfd = i;
        this.zzxy = jSONObject;
        this.zzxz = z;
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

    public final boolean isConnected() {
        switch (this.zzfd) {
            case 3:
            case 4:
            case 5:
            case 6:
                return true;
            default:
                return false;
        }
    }

    public final boolean isControllable() {
        return this.zzxz;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof PlayerInfo)) {
            return false;
        }
        PlayerInfo playerInfo = (PlayerInfo) obj;
        if (this.zzxz != playerInfo.isControllable() || this.zzfd != playerInfo.getPlayerState() || !zzdc.zza(this.zzxg, playerInfo.getPlayerId()) || !JsonUtils.areJsonValuesEquivalent(this.zzxy, playerInfo.getPlayerData())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzxg, Integer.valueOf(this.zzfd), this.zzxy, Boolean.valueOf(this.zzxz));
    }
}
