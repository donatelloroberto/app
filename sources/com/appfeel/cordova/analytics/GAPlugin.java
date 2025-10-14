package com.appfeel.cordova.analytics;

import android.os.Bundle;
import com.appfeel.cordova.annotated.android.plugin.AnnotatedCordovaPlugin;
import com.appfeel.cordova.annotated.android.plugin.ExecutionThread;
import com.appfeel.cordova.annotated.android.plugin.PluginAction;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Iterator;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONObject;

public class GAPlugin extends AnnotatedCordovaPlugin {
    private static final String OPT_EVENT_NAME = "eventName";
    private static final String OPT_EVENT_PARAMS = "eventParams";
    private static final String OPT_USER_PROPERTY_NAME = "userPropertyName";
    private static final String OPT_USER_PROPERTY_VALUE = "userPropertyValue";
    private FirebaseAnalytics mFirebaseAnalytics = null;

    @PluginAction(actionName = "logEvent", isAutofinish = false, thread = ExecutionThread.WORKER)
    private void logEvent(JSONObject options, CallbackContext callbackContext) {
        if (this.mFirebaseAnalytics == null) {
            this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.f5cordova.getActivity());
        }
        PluginResult result = new PluginResult(PluginResult.Status.ERROR, "Missing event name");
        if (options.has(OPT_EVENT_NAME)) {
            try {
                Bundle bundle = new Bundle();
                if (options.has(OPT_EVENT_PARAMS)) {
                    JSONObject params = options.optJSONObject(OPT_EVENT_PARAMS);
                    Iterator<String> keys = params.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        bundle.putString(key, params.getString(key));
                    }
                }
                this.mFirebaseAnalytics.logEvent(options.optString(OPT_EVENT_NAME), bundle);
                result = new PluginResult(PluginResult.Status.OK);
            } catch (Exception e) {
                result = new PluginResult(PluginResult.Status.ERROR, e.getMessage());
            }
        }
        callbackContext.sendPluginResult(result);
    }

    @PluginAction(actionName = "resetAnalyticsData", isAutofinish = false, thread = ExecutionThread.WORKER)
    private void resetAnalyticsData(CallbackContext callbackContext) {
        if (this.mFirebaseAnalytics == null) {
            this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.f5cordova.getActivity());
        }
        PluginResult result = new PluginResult(PluginResult.Status.OK);
        try {
            this.mFirebaseAnalytics.resetAnalyticsData();
        } catch (Exception e) {
            result = new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
        callbackContext.sendPluginResult(result);
    }

    @PluginAction(actionName = "setAnalyticsCollectionEnabled", isAutofinish = false, thread = ExecutionThread.WORKER)
    private void setAnalyticsCollectionEnabled(boolean enabled, CallbackContext callbackContext) {
        if (this.mFirebaseAnalytics == null) {
            this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.f5cordova.getActivity());
        }
        PluginResult result = new PluginResult(PluginResult.Status.OK);
        try {
            this.mFirebaseAnalytics.setAnalyticsCollectionEnabled(enabled);
        } catch (Exception e) {
            result = new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
        callbackContext.sendPluginResult(result);
    }

    @PluginAction(actionName = "setCurrentScreen", isAutofinish = false, thread = ExecutionThread.UI)
    private void setCurrentScreen(String screen, CallbackContext callbackContext) {
        if (this.mFirebaseAnalytics == null) {
            this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.f5cordova.getActivity());
        }
        PluginResult result = new PluginResult(PluginResult.Status.ERROR, "Missing current screen name");
        if (screen != null) {
            try {
                this.mFirebaseAnalytics.setCurrentScreen(this.f5cordova.getActivity(), screen, (String) null);
                result = new PluginResult(PluginResult.Status.OK);
            } catch (Exception e) {
                result = new PluginResult(PluginResult.Status.ERROR, e.getMessage());
            }
        }
        callbackContext.sendPluginResult(result);
    }

    @PluginAction(actionName = "setMinimumSessionDuration", isAutofinish = false, thread = ExecutionThread.WORKER)
    private void setMinimumSessionDuration(long milliseconds, CallbackContext callbackContext) {
        if (this.mFirebaseAnalytics == null) {
            this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.f5cordova.getActivity());
        }
        PluginResult result = new PluginResult(PluginResult.Status.OK);
        try {
            this.mFirebaseAnalytics.setMinimumSessionDuration(milliseconds);
        } catch (Exception e) {
            result = new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
        callbackContext.sendPluginResult(result);
    }

    @PluginAction(actionName = "setSessionTimeoutDuration", isAutofinish = false, thread = ExecutionThread.WORKER)
    private void setSessionTimeoutDuration(long milliseconds, CallbackContext callbackContext) {
        if (this.mFirebaseAnalytics == null) {
            this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.f5cordova.getActivity());
        }
        PluginResult result = new PluginResult(PluginResult.Status.OK);
        try {
            this.mFirebaseAnalytics.setSessionTimeoutDuration(milliseconds);
        } catch (Exception e) {
            result = new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
        callbackContext.sendPluginResult(result);
    }

    @PluginAction(actionName = "setUserId", isAutofinish = false, thread = ExecutionThread.WORKER)
    private void setUserId(String userId, CallbackContext callbackContext) {
        if (this.mFirebaseAnalytics == null) {
            this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.f5cordova.getActivity());
        }
        PluginResult result = new PluginResult(PluginResult.Status.ERROR, "Missing user id");
        if (userId != null) {
            try {
                this.mFirebaseAnalytics.setUserId(userId);
                result = new PluginResult(PluginResult.Status.OK);
            } catch (Exception e) {
                result = new PluginResult(PluginResult.Status.ERROR, e.getMessage());
            }
        }
        callbackContext.sendPluginResult(result);
    }

    @PluginAction(actionName = "setUserProperty", isAutofinish = false, thread = ExecutionThread.WORKER)
    private void setUserProperty(JSONObject options, CallbackContext callbackContext) {
        if (this.mFirebaseAnalytics == null) {
            this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.f5cordova.getActivity());
        }
        PluginResult result = new PluginResult(PluginResult.Status.ERROR, "Missing user property name or user property value");
        if (options.has(OPT_USER_PROPERTY_NAME) && options.has(OPT_USER_PROPERTY_VALUE)) {
            try {
                this.mFirebaseAnalytics.setUserProperty(options.optString(OPT_USER_PROPERTY_NAME), options.optString(OPT_USER_PROPERTY_VALUE));
                result = new PluginResult(PluginResult.Status.OK);
            } catch (Exception e) {
                result = new PluginResult(PluginResult.Status.ERROR, e.getMessage());
            }
        }
        callbackContext.sendPluginResult(result);
    }
}
