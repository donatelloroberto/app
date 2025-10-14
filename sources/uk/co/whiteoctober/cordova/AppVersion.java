package uk.co.whiteoctober.cordova;

import android.content.pm.PackageManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class AppVersion extends CordovaPlugin {
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
            if (action.equals("getAppName")) {
                PackageManager packageManager = this.f5cordova.getActivity().getPackageManager();
                callbackContext.success((String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.f5cordova.getActivity().getPackageName(), 0)));
                return true;
            } else if (action.equals("getPackageName")) {
                callbackContext.success(this.f5cordova.getActivity().getPackageName());
                return true;
            } else if (action.equals("getVersionNumber")) {
                callbackContext.success(this.f5cordova.getActivity().getPackageManager().getPackageInfo(this.f5cordova.getActivity().getPackageName(), 0).versionName);
                return true;
            } else if (!action.equals("getVersionCode")) {
                return false;
            } else {
                callbackContext.success(this.f5cordova.getActivity().getPackageManager().getPackageInfo(this.f5cordova.getActivity().getPackageName(), 0).versionCode);
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            callbackContext.success("N/A");
            return true;
        }
    }
}
