package by.chemerisuk.cordova.advertising;

import by.chemerisuk.cordova.support.CordovaMethod;
import by.chemerisuk.cordova.support.ReflectiveCordovaPlugin;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import org.apache.cordova.CallbackContext;
import org.json.JSONObject;

public class IdfaPlugin extends ReflectiveCordovaPlugin {
    private static final String TAG = "IdfaPlugin";

    /* access modifiers changed from: protected */
    @CordovaMethod(ReflectiveCordovaPlugin.ExecutionThread.WORKER)
    public void getInfo(CallbackContext callbackContext) throws Exception {
        AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(this.f5cordova.getActivity().getApplicationContext());
        JSONObject result = new JSONObject();
        result.put("aaid", info.getId());
        result.put("limitAdTracking", info.isLimitAdTrackingEnabled());
        callbackContext.success(result);
    }
}
