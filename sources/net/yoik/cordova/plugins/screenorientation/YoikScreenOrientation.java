package net.yoik.cordova.plugins.screenorientation;

import android.app.Activity;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

public class YoikScreenOrientation extends CordovaPlugin {
    private static final String LANDSCAPE = "landscape";
    private static final String LANDSCAPE_PRIMARY = "landscape-primary";
    private static final String LANDSCAPE_SECONDARY = "landscape-secondary";
    private static final String PORTRAIT = "portrait";
    private static final String PORTRAIT_PRIMARY = "portrait-primary";
    private static final String PORTRAIT_SECONDARY = "portrait-secondary";
    private static final String TAG = "YoikScreenOrientation";
    private static final String UNLOCKED = "unlocked";

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "execute action: " + action);
        if (action.equals("screenOrientation")) {
            return routeScreenOrientation(args, callbackContext);
        }
        callbackContext.error("action not recognised");
        return false;
    }

    private boolean routeScreenOrientation(JSONArray args, CallbackContext callbackContext) {
        if (args.optString(0).equals("set")) {
            String orientation = args.optString(1);
            Log.d(TAG, "Requested ScreenOrientation: " + orientation);
            Activity activity = this.f5cordova.getActivity();
            if (orientation.equals(UNLOCKED)) {
                activity.setRequestedOrientation(-1);
            } else if (orientation.equals(LANDSCAPE_PRIMARY)) {
                activity.setRequestedOrientation(0);
            } else if (orientation.equals(PORTRAIT_PRIMARY)) {
                activity.setRequestedOrientation(1);
            } else if (orientation.equals("landscape")) {
                activity.setRequestedOrientation(6);
            } else if (orientation.equals("portrait")) {
                activity.setRequestedOrientation(7);
            } else if (orientation.equals(LANDSCAPE_SECONDARY)) {
                activity.setRequestedOrientation(8);
            } else if (orientation.equals(PORTRAIT_SECONDARY)) {
                activity.setRequestedOrientation(9);
            }
            callbackContext.success();
            return true;
        }
        callbackContext.error("ScreenOrientation not recognised");
        return false;
    }
}
