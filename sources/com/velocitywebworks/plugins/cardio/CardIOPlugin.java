package com.velocitywebworks.plugins.cardio;

import android.content.Intent;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CardIOPlugin extends CordovaPlugin {
    public static String cardIOAPIKey;
    public static Boolean confirm;
    public static Boolean cvv;
    public static Boolean expiry;
    public static JSONArray mCreditcardNumber = null;
    public static Boolean showLogo;
    public static Boolean suppressManual;
    public static Boolean zip;
    public CallbackContext callbackContext;

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext2) {
        this.callbackContext = callbackContext2;
        try {
            JSONObject config = args.getJSONObject(0);
            cardIOAPIKey = config.getString("apiKey");
            expiry = Boolean.valueOf(config.getBoolean("expiry"));
            cvv = Boolean.valueOf(config.getBoolean("cvv"));
            zip = Boolean.valueOf(config.getBoolean("zip"));
            confirm = Boolean.valueOf(config.getBoolean("confirm"));
            showLogo = Boolean.valueOf(config.getBoolean("showLogo"));
            suppressManual = Boolean.valueOf(config.getBoolean("suppressManual"));
            this.f5cordova.getActivity().startActivity(new Intent(this.f5cordova.getActivity(), CardIOMain.class));
            PluginResult cardData = new PluginResult(PluginResult.Status.NO_RESULT);
            cardData.setKeepCallback(true);
            callbackContext2.sendPluginResult(cardData);
            return true;
        } catch (JSONException e) {
            callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
            return false;
        }
    }

    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        if (mCreditcardNumber != null) {
            PluginResult cardData = new PluginResult(PluginResult.Status.OK, mCreditcardNumber);
            cardData.setKeepCallback(false);
            this.callbackContext.sendPluginResult(cardData);
            mCreditcardNumber = null;
            return;
        }
        PluginResult cardData2 = new PluginResult(PluginResult.Status.NO_RESULT);
        cardData2.setKeepCallback(false);
        this.callbackContext.sendPluginResult(cardData2);
    }
}
