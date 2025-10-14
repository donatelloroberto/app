package com.google.android.gms.internal.cast;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.cast.games.GameManagerState;
import com.google.android.gms.cast.games.PlayerInfo;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.measurement.AppMeasurement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzbs extends zzco {
    private static final String NAMESPACE = zzdc.zzr("com.google.cast.games");
    /* access modifiers changed from: private */
    public static final zzdo zzbf = new zzdo("GameManagerChannel");
    /* access modifiers changed from: private */
    public final Cast.CastApi zzio;
    /* access modifiers changed from: private */
    public final GoogleApiClient zzpl;
    private final Map<String, String> zzwi = new ConcurrentHashMap();
    private final SharedPreferences zzwj;
    private final String zzwk;
    /* access modifiers changed from: private */
    public zzcg zzwl;
    private boolean zzwm = false;
    private GameManagerState zzwn;
    private GameManagerState zzwo;
    private String zzwp;
    private JSONObject zzwq;
    private long zzwr = 0;
    private GameManagerClient.Listener zzws;
    private final Clock zzwt;
    /* access modifiers changed from: private */
    public String zzwu;

    public zzbs(GoogleApiClient googleApiClient, String str, Cast.CastApi castApi) throws IllegalArgumentException, IllegalStateException {
        super(NAMESPACE, "CastGameManagerChannel", (String) null);
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("castSessionId cannot be null.");
        } else if (googleApiClient == null || !googleApiClient.isConnected() || !googleApiClient.hasConnectedApi(Cast.API)) {
            throw new IllegalArgumentException("googleApiClient needs to be connected and contain the Cast.API API.");
        } else {
            this.zzwt = DefaultClock.getInstance();
            this.zzwk = str;
            this.zzio = castApi;
            this.zzpl = googleApiClient;
            Context applicationContext = googleApiClient.getContext().getApplicationContext();
            this.zzwj = applicationContext.getApplicationContext().getSharedPreferences(String.format(Locale.ROOT, "%s.%s", new Object[]{applicationContext.getPackageName(), "game_manager_channel_data"}), 0);
            this.zzwo = null;
            this.zzwn = new zzci(0, 0, "", (JSONObject) null, new ArrayList(), "", -1);
        }
    }

    public final synchronized PendingResult<GameManagerClient.GameManagerInstanceResult> zza(GameManagerClient gameManagerClient) throws IllegalArgumentException {
        return this.zzpl.execute(new zzbu(this, gameManagerClient));
    }

    public final synchronized void dispose() throws IllegalStateException {
        if (!this.zzwm) {
            this.zzwn = null;
            this.zzwo = null;
            this.zzwp = null;
            this.zzwq = null;
            this.zzwm = true;
            try {
                this.zzio.removeMessageReceivedCallbacks(this.zzpl, getNamespace());
            } catch (IOException e) {
                zzbf.w("Exception while detaching game manager channel.", e);
            }
        }
        return;
    }

    public final synchronized PendingResult<GameManagerClient.GameManagerResult> zza(String str, int i, JSONObject jSONObject) throws IllegalStateException {
        zzdt();
        return this.zzpl.execute(new zzbw(this, i, str, jSONObject));
    }

    public final synchronized void sendGameMessage(String str, JSONObject jSONObject) throws IllegalStateException {
        zzdt();
        long j = 1 + this.zzwr;
        this.zzwr = j;
        JSONObject zza = zza(j, str, 7, jSONObject);
        if (zza != null) {
            this.zzio.sendMessage(this.zzpl, getNamespace(), zza.toString());
        }
    }

    public final synchronized PendingResult<GameManagerClient.GameManagerResult> sendGameRequest(String str, JSONObject jSONObject) throws IllegalStateException {
        zzdt();
        return this.zzpl.execute(new zzbv(this, str, jSONObject));
    }

    public final synchronized GameManagerState getCurrentState() throws IllegalStateException {
        zzdt();
        return this.zzwn;
    }

    public final synchronized String getLastUsedPlayerId() throws IllegalStateException {
        zzdt();
        return this.zzwu;
    }

    private final synchronized String zzo(String str) throws IllegalStateException {
        return str == null ? null : this.zzwi.get(str);
    }

    public final synchronized void setListener(GameManagerClient.Listener listener) {
        this.zzws = listener;
    }

    public final void zzp(String str) {
        int i;
        zzbf.d("message received: %s", str);
        try {
            zzcf zzl = zzcf.zzl(new JSONObject(str));
            if (zzl == null) {
                zzbf.w("Could not parse game manager message from string: %s", str);
            } else if ((isInitialized() || zzl.zzxv != null) && !isDisposed()) {
                boolean z = zzl.zzxj == 1;
                if (z && !TextUtils.isEmpty(zzl.zzxu)) {
                    this.zzwi.put(zzl.zzxs, zzl.zzxu);
                    zzdu();
                }
                if (zzl.zzxk == 0) {
                    zza(zzl);
                } else {
                    zzbf.w("Not updating from game message because the message contains error code: %d", Integer.valueOf(zzl.zzxk));
                }
                int i2 = zzl.zzxk;
                switch (i2) {
                    case 0:
                        i = 0;
                        break;
                    case 1:
                        i = CastStatusCodes.INVALID_REQUEST;
                        break;
                    case 2:
                        i = CastStatusCodes.NOT_ALLOWED;
                        break;
                    case 3:
                        i = GameManagerClient.STATUS_INCORRECT_VERSION;
                        break;
                    case 4:
                        i = GameManagerClient.STATUS_TOO_MANY_PLAYERS;
                        break;
                    default:
                        zzbf.w(new StringBuilder(53).append("Unknown GameManager protocol status code: ").append(i2).toString(), new Object[0]);
                        i = 13;
                        break;
                }
                if (z) {
                    zzb(zzl.zzxt, i, zzl);
                }
                if (isInitialized() && i == 0) {
                    if (this.zzws != null) {
                        if (this.zzwo != null && !this.zzwn.equals(this.zzwo)) {
                            this.zzws.onStateChanged(this.zzwn, this.zzwo);
                        }
                        if (!(this.zzwq == null || this.zzwp == null)) {
                            this.zzws.onGameMessageReceived(this.zzwp, this.zzwq);
                        }
                    }
                    this.zzwo = null;
                    this.zzwp = null;
                    this.zzwq = null;
                }
            }
        } catch (JSONException e) {
            zzbf.w("Message is malformed (%s); ignoring: %s", e.getMessage(), str);
        }
    }

    public final synchronized boolean isDisposed() {
        return this.zzwm;
    }

    private final synchronized boolean isInitialized() {
        return this.zzwl != null;
    }

    public final void zza(long j, int i) {
        zzb(j, i, (Object) null);
    }

    private final synchronized void zzdt() throws IllegalStateException {
        if (!isInitialized()) {
            throw new IllegalStateException("Attempted to perform an operation on the GameManagerChannel before it is initialized.");
        } else if (isDisposed()) {
            throw new IllegalStateException("Attempted to perform an operation on the GameManagerChannel after it has been disposed.");
        }
    }

    /* access modifiers changed from: private */
    public final void zza(String str, int i, JSONObject jSONObject, zzdu zzdu) {
        long j = 1 + this.zzwr;
        this.zzwr = j;
        JSONObject zza = zza(j, str, i, jSONObject);
        if (zza == null) {
            zzdu.zza(-1, CastStatusCodes.INVALID_REQUEST, (Object) null);
            zzbf.w("Not sending request because it was invalid.", new Object[0]);
            return;
        }
        zzdt zzdt = new zzdt(30000);
        zzdt.zza(j, zzdu);
        zza(zzdt);
        this.zzio.sendMessage(this.zzpl, getNamespace(), zza.toString()).setResultCallback(new zzby(this, j));
    }

    private final JSONObject zza(long j, String str, int i, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("requestId", j);
            jSONObject2.put(AppMeasurement.Param.TYPE, i);
            jSONObject2.put("extraMessageData", jSONObject);
            jSONObject2.put("playerId", str);
            jSONObject2.put("playerToken", zzo(str));
            return jSONObject2;
        } catch (JSONException e) {
            zzbf.w("JSONException when trying to create a message: %s", e.getMessage());
            return null;
        }
    }

    private final synchronized void zza(zzcf zzcf) {
        boolean z = true;
        synchronized (this) {
            if (zzcf.zzxj != 1) {
                z = false;
            }
            this.zzwo = this.zzwn;
            if (z && zzcf.zzxv != null) {
                this.zzwl = zzcf.zzxv;
            }
            if (isInitialized()) {
                ArrayList arrayList = new ArrayList();
                for (zzck next : zzcf.zzxp) {
                    String playerId = next.getPlayerId();
                    arrayList.add(new zzch(playerId, next.getPlayerState(), next.getPlayerData(), this.zzwi.containsKey(playerId)));
                }
                this.zzwn = new zzci(zzcf.zzxo, zzcf.zzxn, zzcf.zzxr, zzcf.zzxq, arrayList, this.zzwl.zzdx(), this.zzwl.getMaxPlayers());
                PlayerInfo player = this.zzwn.getPlayer(zzcf.zzxs);
                if (player != null && player.isControllable() && zzcf.zzxj == 2) {
                    this.zzwp = zzcf.zzxs;
                    this.zzwq = zzcf.zzxm;
                }
            }
        }
    }

    private final void zzb(long j, int i, Object obj) {
        List<zzdt> zzea = zzea();
        synchronized (zzea) {
            Iterator<zzdt> it = zzea.iterator();
            while (it.hasNext()) {
                if (it.next().zzc(j, i, obj)) {
                    it.remove();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public final synchronized void zzdu() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("castSessionId", this.zzwk);
            jSONObject.put("playerTokenMap", new JSONObject(this.zzwi));
            this.zzwj.edit().putString("save_data", jSONObject.toString()).commit();
        } catch (JSONException e) {
            zzbf.w("Error while saving data: %s", e.getMessage());
        }
        return;
    }

    /* access modifiers changed from: private */
    public final synchronized void zzdv() {
        String string = this.zzwj.getString("save_data", (String) null);
        if (string != null) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                if (this.zzwk.equals(jSONObject.getString("castSessionId"))) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("playerTokenMap");
                    Iterator<String> keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        this.zzwi.put(next, jSONObject2.getString(next));
                    }
                    this.zzwr = 0;
                }
            } catch (JSONException e) {
                zzbf.w("Error while loading data: %s", e.getMessage());
            }
        }
        return;
    }
}
