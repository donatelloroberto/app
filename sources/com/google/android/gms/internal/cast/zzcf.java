package com.google.android.gms.internal.cast;

import com.google.android.gms.measurement.AppMeasurement;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzcf {
    private static final zzdo zzbf = new zzdo("GameManagerMessage");
    protected final int zzxj;
    protected final int zzxk;
    protected final String zzxl;
    protected final JSONObject zzxm;
    protected final int zzxn;
    protected final int zzxo;
    protected final List<zzck> zzxp;
    protected final JSONObject zzxq;
    protected final String zzxr;
    protected final String zzxs;
    protected final long zzxt;
    protected final String zzxu;
    protected final zzcg zzxv;

    private zzcf(int i, int i2, String str, JSONObject jSONObject, int i3, int i4, List<zzck> list, JSONObject jSONObject2, String str2, String str3, long j, String str4, zzcg zzcg) {
        this.zzxj = i;
        this.zzxk = i2;
        this.zzxl = str;
        this.zzxm = jSONObject;
        this.zzxn = i3;
        this.zzxo = i4;
        this.zzxp = list;
        this.zzxq = jSONObject2;
        this.zzxr = str2;
        this.zzxs = str3;
        this.zzxt = j;
        this.zzxu = str4;
        this.zzxv = zzcg;
    }

    protected static zzcf zzl(JSONObject jSONObject) {
        int optInt = jSONObject.optInt(AppMeasurement.Param.TYPE, -1);
        switch (optInt) {
            case 1:
                zzcg zzcg = null;
                JSONObject optJSONObject = jSONObject.optJSONObject("gameManagerConfig");
                if (optJSONObject != null) {
                    zzcg = new zzcg(optJSONObject);
                }
                return new zzcf(optInt, jSONObject.optInt("statusCode"), jSONObject.optString("errorDescription"), jSONObject.optJSONObject("extraMessageData"), jSONObject.optInt("gameplayState"), jSONObject.optInt("lobbyState"), zza(jSONObject.optJSONArray("players")), jSONObject.optJSONObject("gameData"), jSONObject.optString("gameStatusText"), jSONObject.optString("playerId"), jSONObject.optLong("requestId"), jSONObject.optString("playerToken"), zzcg);
            case 2:
                return new zzcf(optInt, jSONObject.optInt("statusCode"), jSONObject.optString("errorDescription"), jSONObject.optJSONObject("extraMessageData"), jSONObject.optInt("gameplayState"), jSONObject.optInt("lobbyState"), zza(jSONObject.optJSONArray("players")), jSONObject.optJSONObject("gameData"), jSONObject.optString("gameStatusText"), jSONObject.optString("playerId"), -1, (String) null, (zzcg) null);
            default:
                try {
                    zzbf.w("Unrecognized Game Message type %d", Integer.valueOf(optInt));
                    break;
                } catch (JSONException e) {
                    zzbf.zzb(e, "Exception while parsing GameManagerMessage from json", new Object[0]);
                    break;
                }
        }
        return null;
    }

    private static List<zzck> zza(JSONArray jSONArray) {
        zzck zzck;
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                try {
                    zzck = new zzck(optJSONObject);
                } catch (JSONException e) {
                    zzbf.zzb(e, "Exception when attempting to parse PlayerInfoMessageComponent at index %d", Integer.valueOf(i));
                    zzck = null;
                }
                if (zzck != null) {
                    arrayList.add(zzck);
                }
            }
        }
        return arrayList;
    }
}
