package com.appfeel.cordova.annotated.android.plugin;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.json.JSONArray;
import org.json.JSONException;

public class AnnotatedCordovaPlugin extends CordovaPlugin {
    private static String TAG = "AnnotatedCordovaPlugin";
    private Map<String, CordovaPluginAction> pluginActions;

    private Map<String, CordovaPluginAction> getPluginActions() {
        Map<String, CordovaPluginAction> pActions = new HashMap<>();
        for (Method method : getClass().getDeclaredMethods()) {
            PluginAction pluginAction = (PluginAction) method.getAnnotation(PluginAction.class);
            if (pluginAction != null) {
                String actionName = pluginAction.actionName();
                if (actionName.isEmpty()) {
                    actionName = method.getName();
                }
                pActions.put(actionName, new CordovaPluginAction(method, pluginAction.thread(), pluginAction.isAutofinish()));
                method.setAccessible(true);
            }
        }
        return pActions;
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (this.pluginActions == null) {
            this.pluginActions = getPluginActions();
        }
        CordovaPluginAction pluginAction = this.pluginActions.get(action);
        if (pluginAction != null) {
            return pluginAction.execute(this.f5cordova, this, args, callbackContext);
        }
        LOG.d(TAG, String.format("Unknown plugin action: %s", new Object[]{action}));
        return false;
    }
}
