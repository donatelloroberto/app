package acidhax.cordova.chromecast;

import org.json.JSONObject;

public interface ChromecastOnSessionUpdatedListener {
    void onMessage(ChromecastSession chromecastSession, String str, String str2);

    void onSessionUpdated(boolean z, JSONObject jSONObject);
}
