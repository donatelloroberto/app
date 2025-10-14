package cordova.plugin.pip;

import android.app.PictureInPictureParams;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;
import android.util.Rational;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class PictureInPicture extends CordovaPlugin {
    private CallbackContext callback = null;
    private final PictureInPictureParams.Builder pictureInPictureParamsBuilder = new PictureInPictureParams.Builder();

    public void initialize(CordovaInterface cordova2, CordovaWebView webView) {
        super.initialize(cordova2, webView);
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("enter")) {
            enterPip(Double.valueOf(args.getDouble(0)), Double.valueOf(args.getDouble(1)), callbackContext);
            return true;
        } else if (action.equals("isPip")) {
            isPip(callbackContext);
            return true;
        } else if (action.equals("onPipModeChanged")) {
            if (this.callback != null) {
                return true;
            }
            this.callback = callbackContext;
            new PluginResult(PluginResult.Status.NO_RESULT).setKeepCallback(true);
            return true;
        } else if (!action.equals("isPipModeSupported")) {
            return false;
        } else {
            isPipModeSupported(callbackContext);
            return true;
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (this.callback != null) {
            try {
                if (this.f5cordova.getActivity().isInPictureInPictureMode()) {
                    callbackFunction(true, "true");
                } else {
                    callbackFunction(true, "false");
                }
            } catch (Exception e) {
                callbackFunction(false, Log.getStackTraceString(e));
            }
        }
    }

    public void callbackFunction(boolean op, String str) {
        if (op) {
            PluginResult result = new PluginResult(PluginResult.Status.OK, str);
            result.setKeepCallback(true);
            this.callback.sendPluginResult(result);
            return;
        }
        PluginResult result2 = new PluginResult(PluginResult.Status.ERROR, str);
        result2.setKeepCallback(true);
        this.callback.sendPluginResult(result2);
    }

    private void enterPip(Double width, Double height, CallbackContext callbackContext) {
        if (width != null) {
            try {
                if (width.doubleValue() > 0.0d && height != null && height.doubleValue() > 0.0d) {
                    this.pictureInPictureParamsBuilder.setAspectRatio(new Rational(Integer.valueOf(width.intValue()).intValue(), Integer.valueOf(height.intValue()).intValue())).build();
                    this.f5cordova.getActivity().enterPictureInPictureMode(this.pictureInPictureParamsBuilder.build());
                    callbackContext.success("Scaled picture-in-picture mode started.");
                    return;
                }
            } catch (Exception e) {
                callbackContext.error(Log.getStackTraceString(e));
                return;
            }
        }
        this.f5cordova.getActivity().enterPictureInPictureMode();
        callbackContext.success("Default picture-in-picture mode started.");
    }

    public void isPip(CallbackContext callbackContext) {
        try {
            if (this.f5cordova.getActivity().isInPictureInPictureMode()) {
                callbackContext.success("true");
            } else {
                callbackContext.success("false");
            }
        } catch (Exception e) {
            callbackContext.error(Log.getStackTraceString(e));
        }
    }

    private void isPipModeSupported(CallbackContext callbackContext) {
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                callbackContext.success("true");
            } else {
                callbackContext.success("false");
            }
        } catch (Exception e) {
            callbackContext.error(Log.getStackTraceString(e));
        }
    }
}
