package acidhax.cordova.chromecast;

import org.json.JSONObject;

public interface ChromecastOnMediaUpdatedListener {
    void onMediaLoaded(JSONObject jSONObject);

    void onMediaUpdated(boolean z, JSONObject jSONObject);
}
