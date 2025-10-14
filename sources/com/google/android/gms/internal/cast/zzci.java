package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerState;
import com.google.android.gms.cast.games.PlayerInfo;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.JsonUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public final class zzci implements GameManagerState {
    private final String zzxw;
    private final int zzxx;
    private final int zzya;
    private final int zzyb;
    private final String zzyc;
    private final JSONObject zzyd;
    private final Map<String, PlayerInfo> zzye;

    public zzci(int i, int i2, String str, JSONObject jSONObject, Collection<PlayerInfo> collection, String str2, int i3) {
        this.zzya = i;
        this.zzyb = i2;
        this.zzyc = str;
        this.zzyd = jSONObject;
        this.zzxw = str2;
        this.zzxx = i3;
        this.zzye = new HashMap(collection.size());
        for (PlayerInfo next : collection) {
            this.zzye.put(next.getPlayerId(), next);
        }
    }

    public final int getLobbyState() {
        return this.zzya;
    }

    public final int getGameplayState() {
        return this.zzyb;
    }

    public final JSONObject getGameData() {
        return this.zzyd;
    }

    public final CharSequence getGameStatusText() {
        return this.zzyc;
    }

    public final CharSequence getApplicationName() {
        return this.zzxw;
    }

    public final int getMaxPlayers() {
        return this.zzxx;
    }

    public final List<PlayerInfo> getPlayersInState(int i) {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo next : getPlayers()) {
            if (next.getPlayerState() == i) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final PlayerInfo getPlayer(String str) {
        if (str == null) {
            return null;
        }
        return this.zzye.get(str);
    }

    public final Collection<PlayerInfo> getPlayers() {
        return Collections.unmodifiableCollection(this.zzye.values());
    }

    public final List<PlayerInfo> getControllablePlayers() {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo next : getPlayers()) {
            if (next.isControllable()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final List<PlayerInfo> getConnectedPlayers() {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo next : getPlayers()) {
            if (next.isConnected()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final List<PlayerInfo> getConnectedControllablePlayers() {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo next : getPlayers()) {
            if (next.isConnected() && next.isControllable()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final boolean hasLobbyStateChanged(GameManagerState gameManagerState) {
        return this.zzya != gameManagerState.getLobbyState();
    }

    public final boolean hasGameplayStateChanged(GameManagerState gameManagerState) {
        return this.zzyb != gameManagerState.getGameplayState();
    }

    public final boolean hasGameDataChanged(GameManagerState gameManagerState) {
        return !JsonUtils.areJsonValuesEquivalent(this.zzyd, gameManagerState.getGameData());
    }

    public final boolean hasGameStatusTextChanged(GameManagerState gameManagerState) {
        return !zzdc.zza(this.zzyc, gameManagerState.getGameStatusText());
    }

    public final boolean hasPlayerChanged(String str, GameManagerState gameManagerState) {
        return !zzdc.zza(getPlayer(str), gameManagerState.getPlayer(str));
    }

    public final boolean hasPlayerStateChanged(String str, GameManagerState gameManagerState) {
        PlayerInfo player = getPlayer(str);
        PlayerInfo player2 = gameManagerState.getPlayer(str);
        if (player == null && player2 == null) {
            return false;
        }
        if (player == null || player2 == null) {
            return true;
        }
        if (player.getPlayerState() != player2.getPlayerState()) {
            return true;
        }
        return false;
    }

    public final boolean hasPlayerDataChanged(String str, GameManagerState gameManagerState) {
        PlayerInfo player = getPlayer(str);
        PlayerInfo player2 = gameManagerState.getPlayer(str);
        if (player == null && player2 == null) {
            return false;
        }
        if (player == null || player2 == null) {
            return true;
        }
        if (!JsonUtils.areJsonValuesEquivalent(player.getPlayerData(), player2.getPlayerData())) {
            return true;
        }
        return false;
    }

    public final Collection<String> getListOfChangedPlayers(GameManagerState gameManagerState) {
        HashSet hashSet = new HashSet();
        for (PlayerInfo next : getPlayers()) {
            PlayerInfo player = gameManagerState.getPlayer(next.getPlayerId());
            if (player == null || !next.equals(player)) {
                hashSet.add(next.getPlayerId());
            }
        }
        for (PlayerInfo next2 : gameManagerState.getPlayers()) {
            if (getPlayer(next2.getPlayerId()) == null) {
                hashSet.add(next2.getPlayerId());
            }
        }
        return hashSet;
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (obj == null || !(obj instanceof GameManagerState)) {
            return false;
        }
        GameManagerState gameManagerState = (GameManagerState) obj;
        if (getPlayers().size() != gameManagerState.getPlayers().size()) {
            return false;
        }
        for (PlayerInfo next : getPlayers()) {
            boolean z2 = false;
            for (PlayerInfo next2 : gameManagerState.getPlayers()) {
                if (!zzdc.zza(next.getPlayerId(), next2.getPlayerId())) {
                    z = z2;
                } else if (!zzdc.zza(next, next2)) {
                    return false;
                } else {
                    z = true;
                }
                z2 = z;
            }
            if (!z2) {
                return false;
            }
        }
        if (this.zzya != gameManagerState.getLobbyState() || this.zzyb != gameManagerState.getGameplayState() || this.zzxx != gameManagerState.getMaxPlayers() || !zzdc.zza(this.zzxw, gameManagerState.getApplicationName()) || !zzdc.zza(this.zzyc, gameManagerState.getGameStatusText()) || !JsonUtils.areJsonValuesEquivalent(this.zzyd, gameManagerState.getGameData())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzya), Integer.valueOf(this.zzyb), this.zzye, this.zzyc, this.zzyd, this.zzxw, Integer.valueOf(this.zzxx));
    }
}
