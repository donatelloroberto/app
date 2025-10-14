package com.amazon.mas.cpt.ads;

import android.app.Activity;
import com.amazon.cptplugins.AndroidResources;
import com.amazon.cptplugins.CrossPlatformTool;
import com.amazon.cptplugins.concurrent.SdkEventListener;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.json.JSONArray;
import org.json.JSONException;

public class AmazonMobileAdsPlugin extends CordovaPlugin implements AndroidResources, SdkEventListener {
    private static final String CALLER_ID = "callerId";
    private static final String ERROR = "error";
    private static final String PLUGIN = "AmazonMobileAds";
    private static final String RESPONSE = "response";
    private static final String TAG = "AmazonMobileAds";

    /* renamed from: cordova  reason: collision with root package name */
    private volatile CordovaInterface f4cordova;
    private AmazonMobileAdsJavaControllerImpl sdkController = null;

    private enum OPERATIONS {
        SETAPPLICATIONKEY {
            public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                LOG.d("AmazonMobileAds", "Executing SetApplicationKey...");
                try {
                    return plugin.sendPluginResult(plugin.getSdkController().setApplicationKey(args.getJSONObject(0).toString()), callbackContext, false);
                } catch (JSONException e) {
                    LOG.d("AmazonMobileAds", e.getMessage());
                    return false;
                }
            }
        },
        REGISTERAPPLICATION {
            public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                LOG.d("AmazonMobileAds", "Executing RegisterApplication...");
                return plugin.sendPluginResult(plugin.getSdkController().registerApplication(args.toString()), callbackContext, false);
            }
        },
        ENABLELOGGING {
            public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                LOG.d("AmazonMobileAds", "Executing EnableLogging...");
                try {
                    return plugin.sendPluginResult(plugin.getSdkController().enableLogging(args.getJSONObject(0).toString()), callbackContext, false);
                } catch (JSONException e) {
                    LOG.d("AmazonMobileAds", e.getMessage());
                    return false;
                }
            }
        },
        ENABLETESTING {
            public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                LOG.d("AmazonMobileAds", "Executing EnableTesting...");
                try {
                    return plugin.sendPluginResult(plugin.getSdkController().enableTesting(args.getJSONObject(0).toString()), callbackContext, false);
                } catch (JSONException e) {
                    LOG.d("AmazonMobileAds", e.getMessage());
                    return false;
                }
            }
        },
        ENABLEGEOLOCATION {
            public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                LOG.d("AmazonMobileAds", "Executing EnableGeoLocation...");
                try {
                    return plugin.sendPluginResult(plugin.getSdkController().enableGeoLocation(args.getJSONObject(0).toString()), callbackContext, false);
                } catch (JSONException e) {
                    LOG.d("AmazonMobileAds", e.getMessage());
                    return false;
                }
            }
        },
        CREATEFLOATINGBANNERAD {
            public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                LOG.d("AmazonMobileAds", "Executing CreateFloatingBannerAd...");
                try {
                    return plugin.sendPluginResult(plugin.getSdkController().createFloatingBannerAd(args.getJSONObject(0).toString()), callbackContext, false);
                } catch (JSONException e) {
                    LOG.d("AmazonMobileAds", e.getMessage());
                    return false;
                }
            }
        },
        CREATEINTERSTITIALAD {
            public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                LOG.d("AmazonMobileAds", "Executing CreateInterstitialAd...");
                return plugin.sendPluginResult(plugin.getSdkController().createInterstitialAd(args.toString()), callbackContext, false);
            }
        },
        LOADANDSHOWFLOATINGBANNERAD {
            public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                LOG.d("AmazonMobileAds", "Executing LoadAndShowFloatingBannerAd...");
                try {
                    return plugin.sendPluginResult(plugin.getSdkController().loadAndShowFloatingBannerAd(args.getJSONObject(0).toString()), callbackContext, false);
                } catch (JSONException e) {
                    LOG.d("AmazonMobileAds", e.getMessage());
                    return false;
                }
            }
        },
        LOADINTERSTITIALAD {
            public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                LOG.d("AmazonMobileAds", "Executing LoadInterstitialAd...");
                return plugin.sendPluginResult(plugin.getSdkController().loadInterstitialAd(args.toString()), callbackContext, false);
            }
        },
        SHOWINTERSTITIALAD {
            public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                LOG.d("AmazonMobileAds", "Executing ShowInterstitialAd...");
                return plugin.sendPluginResult(plugin.getSdkController().showInterstitialAd(args.toString()), callbackContext, false);
            }
        },
        CLOSEFLOATINGBANNERAD {
            public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                LOG.d("AmazonMobileAds", "Executing CloseFloatingBannerAd...");
                try {
                    return plugin.sendPluginResult(plugin.getSdkController().closeFloatingBannerAd(args.getJSONObject(0).toString()), callbackContext, false);
                } catch (JSONException e) {
                    LOG.d("AmazonMobileAds", e.getMessage());
                    return false;
                }
            }
        },
        ISINTERSTITIALADREADY {
            public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                LOG.d("AmazonMobileAds", "Executing IsInterstitialAdReady...");
                return plugin.sendPluginResult(plugin.getSdkController().isInterstitialAdReady(args.toString()), callbackContext, false);
            }
        },
        AREADSEQUAL {
            public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                LOG.d("AmazonMobileAds", "Executing AreAdsEqual...");
                try {
                    return plugin.sendPluginResult(plugin.getSdkController().areAdsEqual(args.getJSONObject(0).toString()), callbackContext, false);
                } catch (JSONException e) {
                    LOG.d("AmazonMobileAds", e.getMessage());
                    return false;
                }
            }
        };

        public abstract boolean execute(JSONArray jSONArray, CallbackContext callbackContext, AmazonMobileAdsPlugin amazonMobileAdsPlugin);
    }

    public AmazonMobileAdsJavaControllerImpl getSdkController() {
        return this.sdkController;
    }

    public void initialize(CordovaInterface cordova2, CordovaWebView webView) {
        super.initialize(cordova2, webView);
        this.f4cordova = cordova2;
        this.sdkController = AmazonMobileAdsJavaControllerImpl.newInstance(((CordovaActivity) cordova2.getActivity()).getApplicationContext());
        this.sdkController.setAndroidResources(this);
        this.sdkController.setSdkEventListener(this);
        this.sdkController.registerToJavaService();
    }

    public Activity getCurrentAndroidActivity() {
        return this.f4cordova.getActivity();
    }

    public CrossPlatformTool getCrossPlatformTool() {
        return CrossPlatformTool.CORDOVA;
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
            return OPERATIONS.valueOf(action.toUpperCase()).execute(args, callbackContext, this);
        } catch (IllegalArgumentException e) {
            LOG.d("AmazonMobileAds", "Invalid action - " + action);
            return false;
        }
    }

    public void fireSdkEvent(String jsonEventResponse) {
        LOG.d("AmazonMobileAds", "Sdk event was fired");
        this.webView.sendJavascript("javascript:AmazonMobileAds.fire('" + jsonEventResponse.replaceAll("'", "\\'") + "')");
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:? A[Catch:{ JSONException -> 0x00ac }, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0019 A[Catch:{ JSONException -> 0x00ac }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean sendPluginResult(java.lang.String r10, org.apache.cordova.CallbackContext r11, boolean r12) {
        /*
            r9 = this;
            r6 = 0
            r4 = 0
            if (r10 == 0) goto L_0x000c
            java.lang.String r7 = "null"
            boolean r7 = r10.equals(r7)     // Catch:{ JSONException -> 0x00ac }
            if (r7 == 0) goto L_0x001d
        L_0x000c:
            java.lang.String r1 = "error: null response from plugin"
            org.apache.cordova.PluginResult r5 = new org.apache.cordova.PluginResult     // Catch:{ JSONException -> 0x00ac }
            org.apache.cordova.PluginResult$Status r7 = org.apache.cordova.PluginResult.Status.ERROR     // Catch:{ JSONException -> 0x00ac }
            r5.<init>((org.apache.cordova.PluginResult.Status) r7, (java.lang.String) r1)     // Catch:{ JSONException -> 0x00ac }
            r6 = 0
            r4 = r5
        L_0x0017:
            if (r4 == 0) goto L_0x001c
            r11.sendPluginResult(r4)     // Catch:{ JSONException -> 0x00ac }
        L_0x001c:
            return r6
        L_0x001d:
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00ac }
            r3.<init>(r10)     // Catch:{ JSONException -> 0x00ac }
            java.lang.String r7 = "response"
            boolean r7 = r3.has(r7)     // Catch:{ JSONException -> 0x00ac }
            if (r7 == 0) goto L_0x006d
            java.lang.String r7 = "response"
            org.json.JSONObject r2 = r3.getJSONObject(r7)     // Catch:{ JSONException -> 0x00ac }
            java.lang.String r7 = "error"
            boolean r7 = r2.has(r7)     // Catch:{ JSONException -> 0x00ac }
            if (r7 == 0) goto L_0x005d
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00ac }
            r7.<init>()     // Catch:{ JSONException -> 0x00ac }
            java.lang.String r8 = "error : "
            java.lang.StringBuilder r8 = r7.append(r8)     // Catch:{ JSONException -> 0x00ac }
            java.lang.String r7 = "error"
            java.lang.Object r7 = r2.get(r7)     // Catch:{ JSONException -> 0x00ac }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ JSONException -> 0x00ac }
            java.lang.StringBuilder r7 = r8.append(r7)     // Catch:{ JSONException -> 0x00ac }
            java.lang.String r1 = r7.toString()     // Catch:{ JSONException -> 0x00ac }
            org.apache.cordova.PluginResult r5 = new org.apache.cordova.PluginResult     // Catch:{ JSONException -> 0x00ac }
            org.apache.cordova.PluginResult$Status r7 = org.apache.cordova.PluginResult.Status.ERROR     // Catch:{ JSONException -> 0x00ac }
            r5.<init>((org.apache.cordova.PluginResult.Status) r7, (java.lang.String) r1)     // Catch:{ JSONException -> 0x00ac }
            r6 = 0
            r4 = r5
            goto L_0x0017
        L_0x005d:
            org.apache.cordova.PluginResult r5 = new org.apache.cordova.PluginResult     // Catch:{ JSONException -> 0x00ac }
            org.apache.cordova.PluginResult$Status r7 = org.apache.cordova.PluginResult.Status.OK     // Catch:{ JSONException -> 0x00ac }
            r5.<init>((org.apache.cordova.PluginResult.Status) r7, (org.json.JSONObject) r2)     // Catch:{ JSONException -> 0x00ac }
            if (r12 == 0) goto L_0x006a
            r7 = 1
            r5.setKeepCallback(r7)     // Catch:{ JSONException -> 0x00b7 }
        L_0x006a:
            r6 = 1
            r4 = r5
            goto L_0x0017
        L_0x006d:
            java.lang.String r7 = "error"
            boolean r7 = r3.has(r7)     // Catch:{ JSONException -> 0x00ac }
            if (r7 == 0) goto L_0x009b
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00ac }
            r7.<init>()     // Catch:{ JSONException -> 0x00ac }
            java.lang.String r8 = "error : "
            java.lang.StringBuilder r8 = r7.append(r8)     // Catch:{ JSONException -> 0x00ac }
            java.lang.String r7 = "error"
            java.lang.Object r7 = r3.get(r7)     // Catch:{ JSONException -> 0x00ac }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ JSONException -> 0x00ac }
            java.lang.StringBuilder r7 = r8.append(r7)     // Catch:{ JSONException -> 0x00ac }
            java.lang.String r1 = r7.toString()     // Catch:{ JSONException -> 0x00ac }
            org.apache.cordova.PluginResult r5 = new org.apache.cordova.PluginResult     // Catch:{ JSONException -> 0x00ac }
            org.apache.cordova.PluginResult$Status r7 = org.apache.cordova.PluginResult.Status.ERROR     // Catch:{ JSONException -> 0x00ac }
            r5.<init>((org.apache.cordova.PluginResult.Status) r7, (java.lang.String) r1)     // Catch:{ JSONException -> 0x00ac }
            r6 = 0
            r4 = r5
            goto L_0x0017
        L_0x009b:
            org.apache.cordova.PluginResult r5 = new org.apache.cordova.PluginResult     // Catch:{ JSONException -> 0x00ac }
            org.apache.cordova.PluginResult$Status r7 = org.apache.cordova.PluginResult.Status.OK     // Catch:{ JSONException -> 0x00ac }
            r5.<init>((org.apache.cordova.PluginResult.Status) r7, (org.json.JSONObject) r3)     // Catch:{ JSONException -> 0x00ac }
            if (r12 == 0) goto L_0x00a8
            r7 = 1
            r5.setKeepCallback(r7)     // Catch:{ JSONException -> 0x00b7 }
        L_0x00a8:
            r6 = 1
            r4 = r5
            goto L_0x0017
        L_0x00ac:
            r0 = move-exception
        L_0x00ad:
            java.lang.String r7 = r0.getMessage()
            r11.error((java.lang.String) r7)
            r6 = 0
            goto L_0x001c
        L_0x00b7:
            r0 = move-exception
            r4 = r5
            goto L_0x00ad
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mas.cpt.ads.AmazonMobileAdsPlugin.sendPluginResult(java.lang.String, org.apache.cordova.CallbackContext, boolean):boolean");
    }
}
