package nl.xservices.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class Insomnia extends CordovaPlugin {
    private static final String ACTION_ALLOW_SLEEP_AGAIN = "allowSleepAgain";
    private static final String ACTION_KEEP_AWAKE = "keepAwake";

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        try {
            if (ACTION_KEEP_AWAKE.equals(action)) {
                this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        Insomnia.this.f5cordova.getActivity().getWindow().addFlags(128);
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                    }
                });
                return true;
            } else if (ACTION_ALLOW_SLEEP_AGAIN.equals(action)) {
                this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        Insomnia.this.f5cordova.getActivity().getWindow().clearFlags(128);
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                    }
                });
                return true;
            } else {
                callbackContext.error("insomnia." + action + " is not a supported function. Did you mean '" + ACTION_KEEP_AWAKE + "'?");
                return false;
            }
        } catch (Exception e) {
            callbackContext.error(e.getMessage());
            return false;
        }
    }
}
