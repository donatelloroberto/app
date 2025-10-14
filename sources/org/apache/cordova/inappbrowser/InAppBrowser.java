package org.apache.cordova.inappbrowser;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
public class InAppBrowser extends CordovaPlugin {
    private static final String CLEAR_ALL_CACHE = "clearcache";
    private static final String CLEAR_SESSION_CACHE = "clearsessioncache";
    private static final String CLOSE_BUTTON_CAPTION = "closebuttoncaption";
    private static final String CLOSE_BUTTON_COLOR = "closebuttoncolor";
    private static final Boolean DEFAULT_HARDWARE_BACK = true;
    private static final String EXIT_EVENT = "exit";
    private static final int FILECHOOSER_REQUESTCODE = 1;
    private static final int FILECHOOSER_REQUESTCODE_LOLLIPOP = 2;
    private static final String FOOTER = "footer";
    private static final String FOOTER_COLOR = "footercolor";
    private static final String HARDWARE_BACK_BUTTON = "hardwareback";
    private static final String HIDDEN = "hidden";
    private static final String HIDE_NAVIGATION = "hidenavigationbuttons";
    private static final String HIDE_URL = "hideurlbar";
    private static final String LOAD_ERROR_EVENT = "loaderror";
    private static final String LOAD_START_EVENT = "loadstart";
    private static final String LOAD_STOP_EVENT = "loadstop";
    private static final String LOCATION = "location";
    protected static final String LOG_TAG = "InAppBrowser";
    private static final String MEDIA_PLAYBACK_REQUIRES_USER_ACTION = "mediaPlaybackRequiresUserAction";
    private static final String NAVIGATION_COLOR = "navigationbuttoncolor";
    private static final String NULL = "null";
    private static final String SELF = "_self";
    private static final String SHOULD_PAUSE = "shouldPauseOnSuspend";
    private static final String SYSTEM = "_system";
    private static final String TOOLBAR_COLOR = "toolbarcolor";
    private static final String USER_WIDE_VIEW_PORT = "useWideViewPort";
    private static final String ZOOM = "zoom";
    private static final List customizableOptions = Arrays.asList(new String[]{CLOSE_BUTTON_CAPTION, TOOLBAR_COLOR, NAVIGATION_COLOR, CLOSE_BUTTON_COLOR, FOOTER_COLOR});
    /* access modifiers changed from: private */
    public String[] allowedSchemes;
    private CallbackContext callbackContext;
    /* access modifiers changed from: private */
    public boolean clearAllCache = false;
    /* access modifiers changed from: private */
    public boolean clearSessionCache = false;
    /* access modifiers changed from: private */
    public String closeButtonCaption = "";
    /* access modifiers changed from: private */
    public String closeButtonColor = "";
    /* access modifiers changed from: private */
    public InAppBrowserDialog dialog;
    /* access modifiers changed from: private */
    public EditText edittext;
    /* access modifiers changed from: private */
    public String footerColor = "";
    private boolean hadwareBackButton = true;
    /* access modifiers changed from: private */
    public boolean hideNavigationButtons = false;
    /* access modifiers changed from: private */
    public boolean hideUrlBar = false;
    /* access modifiers changed from: private */
    public WebView inAppWebView;
    /* access modifiers changed from: private */
    public ValueCallback<Uri> mUploadCallback;
    /* access modifiers changed from: private */
    public ValueCallback<Uri[]> mUploadCallbackLollipop;
    /* access modifiers changed from: private */
    public boolean mediaPlaybackRequiresUserGesture = false;
    /* access modifiers changed from: private */
    public String navigationButtonColor = "";
    /* access modifiers changed from: private */
    public boolean openWindowHidden = false;
    private boolean shouldPauseInAppBrowser = false;
    /* access modifiers changed from: private */
    public boolean showFooter = false;
    private boolean showLocationBar = true;
    /* access modifiers changed from: private */
    public boolean showZoomControls = true;
    /* access modifiers changed from: private */
    public int toolbarColor = -3355444;
    /* access modifiers changed from: private */
    public boolean useWideViewPort = true;

    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext2) throws JSONException {
        String jsWrapper;
        String jsWrapper2;
        String jsWrapper3;
        if (action.equals("open")) {
            this.callbackContext = callbackContext2;
            final String url = args.getString(0);
            String t = args.optString(1);
            if (t == null || t.equals("") || t.equals(NULL)) {
                t = SELF;
            }
            final String target = t;
            final HashMap<String, String> features = parseFeature(args.optString(2));
            LOG.d(LOG_TAG, "target = " + target);
            final CallbackContext callbackContext3 = callbackContext2;
            this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v41, resolved type: java.lang.Object} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: java.lang.Boolean} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v47, resolved type: java.lang.Object} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: java.lang.Boolean} */
                /* JADX WARNING: Multi-variable type inference failed */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r16 = this;
                        r15 = 1
                        java.lang.String r7 = ""
                        java.lang.String r10 = "_self"
                        r0 = r16
                        java.lang.String r11 = r2
                        boolean r10 = r10.equals(r11)
                        if (r10 == 0) goto L_0x0180
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = "in self"
                        org.apache.cordova.LOG.d(r10, r11)
                        r9 = 0
                        r0 = r16
                        java.lang.String r10 = r3
                        java.lang.String r11 = "javascript:"
                        boolean r10 = r10.startsWith(r11)
                        if (r10 == 0) goto L_0x0027
                        java.lang.Boolean r9 = java.lang.Boolean.valueOf(r15)
                    L_0x0027:
                        if (r9 != 0) goto L_0x004c
                        java.lang.Class<org.apache.cordova.Config> r10 = org.apache.cordova.Config.class
                        java.lang.String r11 = "isUrlWhiteListed"
                        r12 = 1
                        java.lang.Class[] r12 = new java.lang.Class[r12]     // Catch:{ NoSuchMethodException -> 0x00c2, IllegalAccessException -> 0x00cd, InvocationTargetException -> 0x00d9 }
                        r13 = 0
                        java.lang.Class<java.lang.String> r14 = java.lang.String.class
                        r12[r13] = r14     // Catch:{ NoSuchMethodException -> 0x00c2, IllegalAccessException -> 0x00cd, InvocationTargetException -> 0x00d9 }
                        java.lang.reflect.Method r4 = r10.getMethod(r11, r12)     // Catch:{ NoSuchMethodException -> 0x00c2, IllegalAccessException -> 0x00cd, InvocationTargetException -> 0x00d9 }
                        r10 = 0
                        r11 = 1
                        java.lang.Object[] r11 = new java.lang.Object[r11]     // Catch:{ NoSuchMethodException -> 0x00c2, IllegalAccessException -> 0x00cd, InvocationTargetException -> 0x00d9 }
                        r12 = 0
                        r0 = r16
                        java.lang.String r13 = r3     // Catch:{ NoSuchMethodException -> 0x00c2, IllegalAccessException -> 0x00cd, InvocationTargetException -> 0x00d9 }
                        r11[r12] = r13     // Catch:{ NoSuchMethodException -> 0x00c2, IllegalAccessException -> 0x00cd, InvocationTargetException -> 0x00d9 }
                        java.lang.Object r10 = r4.invoke(r10, r11)     // Catch:{ NoSuchMethodException -> 0x00c2, IllegalAccessException -> 0x00cd, InvocationTargetException -> 0x00d9 }
                        r0 = r10
                        java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ NoSuchMethodException -> 0x00c2, IllegalAccessException -> 0x00cd, InvocationTargetException -> 0x00d9 }
                        r9 = r0
                    L_0x004c:
                        if (r9 != 0) goto L_0x0094
                        r0 = r16
                        org.apache.cordova.inappbrowser.InAppBrowser r10 = org.apache.cordova.inappbrowser.InAppBrowser.this     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        org.apache.cordova.CordovaWebView r10 = r10.webView     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        java.lang.Class r10 = r10.getClass()     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        java.lang.String r11 = "getPluginManager"
                        r12 = 0
                        java.lang.Class[] r12 = new java.lang.Class[r12]     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        java.lang.reflect.Method r2 = r10.getMethod(r11, r12)     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        r0 = r16
                        org.apache.cordova.inappbrowser.InAppBrowser r10 = org.apache.cordova.inappbrowser.InAppBrowser.this     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        org.apache.cordova.CordovaWebView r10 = r10.webView     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        r11 = 0
                        java.lang.Object[] r11 = new java.lang.Object[r11]     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        java.lang.Object r6 = r2.invoke(r10, r11)     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        org.apache.cordova.PluginManager r6 = (org.apache.cordova.PluginManager) r6     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        java.lang.Class r10 = r6.getClass()     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        java.lang.String r11 = "shouldAllowNavigation"
                        r12 = 1
                        java.lang.Class[] r12 = new java.lang.Class[r12]     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        r13 = 0
                        java.lang.Class<java.lang.String> r14 = java.lang.String.class
                        r12[r13] = r14     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        java.lang.reflect.Method r8 = r10.getMethod(r11, r12)     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        r10 = 1
                        java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        r11 = 0
                        r0 = r16
                        java.lang.String r12 = r3     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        r10[r11] = r12     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        java.lang.Object r10 = r8.invoke(r6, r10)     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        r0 = r10
                        java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ NoSuchMethodException -> 0x00e5, IllegalAccessException -> 0x00f0, InvocationTargetException -> 0x00fb }
                        r9 = r0
                    L_0x0094:
                        java.lang.Boolean r10 = java.lang.Boolean.TRUE
                        boolean r10 = r10.equals(r9)
                        if (r10 == 0) goto L_0x0106
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = "loading in webview"
                        org.apache.cordova.LOG.d(r10, r11)
                        r0 = r16
                        org.apache.cordova.inappbrowser.InAppBrowser r10 = org.apache.cordova.inappbrowser.InAppBrowser.this
                        org.apache.cordova.CordovaWebView r10 = r10.webView
                        r0 = r16
                        java.lang.String r11 = r3
                        r10.loadUrl(r11)
                    L_0x00b0:
                        org.apache.cordova.PluginResult r5 = new org.apache.cordova.PluginResult
                        org.apache.cordova.PluginResult$Status r10 = org.apache.cordova.PluginResult.Status.OK
                        r5.<init>((org.apache.cordova.PluginResult.Status) r10, (java.lang.String) r7)
                        r5.setKeepCallback(r15)
                        r0 = r16
                        org.apache.cordova.CallbackContext r10 = r5
                        r10.sendPluginResult(r5)
                        return
                    L_0x00c2:
                        r1 = move-exception
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = r1.getLocalizedMessage()
                        org.apache.cordova.LOG.d(r10, r11)
                        goto L_0x004c
                    L_0x00cd:
                        r1 = move-exception
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = r1.getLocalizedMessage()
                        org.apache.cordova.LOG.d(r10, r11)
                        goto L_0x004c
                    L_0x00d9:
                        r1 = move-exception
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = r1.getLocalizedMessage()
                        org.apache.cordova.LOG.d(r10, r11)
                        goto L_0x004c
                    L_0x00e5:
                        r1 = move-exception
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = r1.getLocalizedMessage()
                        org.apache.cordova.LOG.d(r10, r11)
                        goto L_0x0094
                    L_0x00f0:
                        r1 = move-exception
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = r1.getLocalizedMessage()
                        org.apache.cordova.LOG.d(r10, r11)
                        goto L_0x0094
                    L_0x00fb:
                        r1 = move-exception
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = r1.getLocalizedMessage()
                        org.apache.cordova.LOG.d(r10, r11)
                        goto L_0x0094
                    L_0x0106:
                        r0 = r16
                        java.lang.String r10 = r3
                        java.lang.String r11 = "tel:"
                        boolean r10 = r10.startsWith(r11)
                        if (r10 == 0) goto L_0x0167
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = "loading in dialer"
                        org.apache.cordova.LOG.d(r10, r11)     // Catch:{ ActivityNotFoundException -> 0x013a }
                        android.content.Intent r3 = new android.content.Intent     // Catch:{ ActivityNotFoundException -> 0x013a }
                        java.lang.String r10 = "android.intent.action.DIAL"
                        r3.<init>(r10)     // Catch:{ ActivityNotFoundException -> 0x013a }
                        r0 = r16
                        java.lang.String r10 = r3     // Catch:{ ActivityNotFoundException -> 0x013a }
                        android.net.Uri r10 = android.net.Uri.parse(r10)     // Catch:{ ActivityNotFoundException -> 0x013a }
                        r3.setData(r10)     // Catch:{ ActivityNotFoundException -> 0x013a }
                        r0 = r16
                        org.apache.cordova.inappbrowser.InAppBrowser r10 = org.apache.cordova.inappbrowser.InAppBrowser.this     // Catch:{ ActivityNotFoundException -> 0x013a }
                        org.apache.cordova.CordovaInterface r10 = r10.f5cordova     // Catch:{ ActivityNotFoundException -> 0x013a }
                        android.app.Activity r10 = r10.getActivity()     // Catch:{ ActivityNotFoundException -> 0x013a }
                        r10.startActivity(r3)     // Catch:{ ActivityNotFoundException -> 0x013a }
                        goto L_0x00b0
                    L_0x013a:
                        r1 = move-exception
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.StringBuilder r11 = new java.lang.StringBuilder
                        r11.<init>()
                        java.lang.String r12 = "Error dialing "
                        java.lang.StringBuilder r11 = r11.append(r12)
                        r0 = r16
                        java.lang.String r12 = r3
                        java.lang.StringBuilder r11 = r11.append(r12)
                        java.lang.String r12 = ": "
                        java.lang.StringBuilder r11 = r11.append(r12)
                        java.lang.String r12 = r1.toString()
                        java.lang.StringBuilder r11 = r11.append(r12)
                        java.lang.String r11 = r11.toString()
                        org.apache.cordova.LOG.e(r10, r11)
                        goto L_0x00b0
                    L_0x0167:
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = "loading in InAppBrowser"
                        org.apache.cordova.LOG.d(r10, r11)
                        r0 = r16
                        org.apache.cordova.inappbrowser.InAppBrowser r10 = org.apache.cordova.inappbrowser.InAppBrowser.this
                        r0 = r16
                        java.lang.String r11 = r3
                        r0 = r16
                        java.util.HashMap r12 = r4
                        java.lang.String r7 = r10.showWebPage(r11, r12)
                        goto L_0x00b0
                    L_0x0180:
                        java.lang.String r10 = "_system"
                        r0 = r16
                        java.lang.String r11 = r2
                        boolean r10 = r10.equals(r11)
                        if (r10 == 0) goto L_0x01a1
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = "in system"
                        org.apache.cordova.LOG.d(r10, r11)
                        r0 = r16
                        org.apache.cordova.inappbrowser.InAppBrowser r10 = org.apache.cordova.inappbrowser.InAppBrowser.this
                        r0 = r16
                        java.lang.String r11 = r3
                        java.lang.String r7 = r10.openExternal(r11)
                        goto L_0x00b0
                    L_0x01a1:
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = "in blank"
                        org.apache.cordova.LOG.d(r10, r11)
                        r0 = r16
                        org.apache.cordova.inappbrowser.InAppBrowser r10 = org.apache.cordova.inappbrowser.InAppBrowser.this
                        r0 = r16
                        java.lang.String r11 = r3
                        r0 = r16
                        java.util.HashMap r12 = r4
                        java.lang.String r7 = r10.showWebPage(r11, r12)
                        goto L_0x00b0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.inappbrowser.InAppBrowser.AnonymousClass1.run():void");
                }
            });
        } else if (action.equals("close")) {
            closeDialog();
        } else if (action.equals("injectScriptCode")) {
            String jsWrapper4 = null;
            if (args.getBoolean(1)) {
                jsWrapper4 = String.format("(function(){prompt(JSON.stringify([eval(%%s)]), 'gap-iab://%s')})()", new Object[]{callbackContext2.getCallbackId()});
            }
            injectDeferredObject(args.getString(0), jsWrapper4);
        } else if (action.equals("injectScriptFile")) {
            if (args.getBoolean(1)) {
                jsWrapper3 = String.format("(function(d) { var c = d.createElement('script'); c.src = %%s; c.onload = function() { prompt('', 'gap-iab://%s'); }; d.body.appendChild(c); })(document)", new Object[]{callbackContext2.getCallbackId()});
            } else {
                jsWrapper3 = "(function(d) { var c = d.createElement('script'); c.src = %s; d.body.appendChild(c); })(document)";
            }
            injectDeferredObject(args.getString(0), jsWrapper3);
        } else if (action.equals("injectStyleCode")) {
            if (args.getBoolean(1)) {
                jsWrapper2 = String.format("(function(d) { var c = d.createElement('style'); c.innerHTML = %%s; d.body.appendChild(c); prompt('', 'gap-iab://%s');})(document)", new Object[]{callbackContext2.getCallbackId()});
            } else {
                jsWrapper2 = "(function(d) { var c = d.createElement('style'); c.innerHTML = %s; d.body.appendChild(c); })(document)";
            }
            injectDeferredObject(args.getString(0), jsWrapper2);
        } else if (action.equals("injectStyleFile")) {
            if (args.getBoolean(1)) {
                jsWrapper = String.format("(function(d) { var c = d.createElement('link'); c.rel='stylesheet'; c.type='text/css'; c.href = %%s; d.head.appendChild(c); prompt('', 'gap-iab://%s');})(document)", new Object[]{callbackContext2.getCallbackId()});
            } else {
                jsWrapper = "(function(d) { var c = d.createElement('link'); c.rel='stylesheet'; c.type='text/css'; c.href = %s; d.head.appendChild(c); })(document)";
            }
            injectDeferredObject(args.getString(0), jsWrapper);
        } else if (action.equals("show")) {
            this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    InAppBrowser.this.dialog.show();
                }
            });
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
            pluginResult.setKeepCallback(true);
            this.callbackContext.sendPluginResult(pluginResult);
        } else if (!action.equals("hide")) {
            return false;
        } else {
            this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    InAppBrowser.this.dialog.hide();
                }
            });
            PluginResult pluginResult2 = new PluginResult(PluginResult.Status.OK);
            pluginResult2.setKeepCallback(true);
            this.callbackContext.sendPluginResult(pluginResult2);
        }
        return true;
    }

    public void onReset() {
        closeDialog();
    }

    public void onPause(boolean multitasking) {
        if (this.shouldPauseInAppBrowser) {
            this.inAppWebView.onPause();
        }
    }

    public void onResume(boolean multitasking) {
        if (this.shouldPauseInAppBrowser) {
            this.inAppWebView.onResume();
        }
    }

    public void onDestroy() {
        closeDialog();
    }

    private void injectDeferredObject(String source, String jsWrapper) {
        String scriptToInject;
        if (this.inAppWebView != null) {
            if (jsWrapper != null) {
                JSONArray jsonEsc = new JSONArray();
                jsonEsc.put(source);
                String jsonRepr = jsonEsc.toString();
                scriptToInject = String.format(jsWrapper, new Object[]{jsonRepr.substring(1, jsonRepr.length() - 1)});
            } else {
                scriptToInject = source;
            }
            final String finalScriptToInject = scriptToInject;
            this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                @SuppressLint({"NewApi"})
                public void run() {
                    if (Build.VERSION.SDK_INT < 19) {
                        InAppBrowser.this.inAppWebView.loadUrl("javascript:" + finalScriptToInject);
                    } else {
                        InAppBrowser.this.inAppWebView.evaluateJavascript(finalScriptToInject, (ValueCallback) null);
                    }
                }
            });
            return;
        }
        LOG.d(LOG_TAG, "Can't inject code into the system browser");
    }

    private HashMap<String, String> parseFeature(String optString) {
        if (optString.equals(NULL)) {
            return null;
        }
        HashMap<String, String> map = new HashMap<>();
        StringTokenizer features = new StringTokenizer(optString, ",");
        while (features.hasMoreElements()) {
            StringTokenizer option = new StringTokenizer(features.nextToken(), "=");
            if (option.hasMoreElements()) {
                String key = option.nextToken();
                String value = option.nextToken();
                if (!customizableOptions.contains(key) && !value.equals("yes") && !value.equals("no")) {
                    value = "yes";
                }
                map.put(key, value);
            }
        }
        return map;
    }

    public String openExternal(String url) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            try {
                Uri uri = Uri.parse(url);
                if ("file".equals(uri.getScheme())) {
                    intent.setDataAndType(uri, this.webView.getResourceApi().getMimeType(uri));
                } else {
                    intent.setData(uri);
                }
                intent.putExtra("com.android.browser.application_id", this.f5cordova.getActivity().getPackageName());
                this.f5cordova.getActivity().startActivity(intent);
                return "";
            } catch (RuntimeException e) {
                e = e;
                Intent intent2 = intent;
                LOG.d(LOG_TAG, "InAppBrowser: Error loading url " + url + ":" + e.toString());
                return e.toString();
            }
        } catch (RuntimeException e2) {
            e = e2;
            LOG.d(LOG_TAG, "InAppBrowser: Error loading url " + url + ":" + e.toString());
            return e.toString();
        }
    }

    public void closeDialog() {
        this.f5cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                WebView childView = InAppBrowser.this.inAppWebView;
                if (childView != null) {
                    childView.setWebViewClient(new WebViewClient() {
                        public void onPageFinished(WebView view, String url) {
                            if (InAppBrowser.this.dialog != null) {
                                InAppBrowser.this.dialog.dismiss();
                                InAppBrowserDialog unused = InAppBrowser.this.dialog = null;
                            }
                        }
                    });
                    childView.loadUrl("about:blank");
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put(AppMeasurement.Param.TYPE, "exit");
                        InAppBrowser.this.sendUpdate(obj, false);
                    } catch (JSONException e) {
                        LOG.d(InAppBrowser.LOG_TAG, "Should never happen");
                    }
                }
            }
        });
    }

    public void goBack() {
        if (this.inAppWebView.canGoBack()) {
            this.inAppWebView.goBack();
        }
    }

    public boolean canGoBack() {
        return this.inAppWebView.canGoBack();
    }

    public boolean hardwareBack() {
        return this.hadwareBackButton;
    }

    /* access modifiers changed from: private */
    public void goForward() {
        if (this.inAppWebView.canGoForward()) {
            this.inAppWebView.goForward();
        }
    }

    /* access modifiers changed from: private */
    public void navigate(String url) {
        ((InputMethodManager) this.f5cordova.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.edittext.getWindowToken(), 0);
        if (url.startsWith("http") || url.startsWith("file:")) {
            this.inAppWebView.loadUrl(url);
        } else {
            this.inAppWebView.loadUrl("http://" + url);
        }
        this.inAppWebView.requestFocus();
    }

    /* access modifiers changed from: private */
    public boolean getShowLocationBar() {
        return this.showLocationBar;
    }

    /* access modifiers changed from: private */
    public InAppBrowser getInAppBrowser() {
        return this;
    }

    public String showWebPage(String url, HashMap<String, String> features) {
        this.showLocationBar = true;
        this.showZoomControls = true;
        this.openWindowHidden = false;
        this.mediaPlaybackRequiresUserGesture = false;
        if (features != null) {
            String show = features.get("location");
            if (show != null) {
                this.showLocationBar = show.equals("yes");
            }
            if (this.showLocationBar) {
                String hideNavigation = features.get(HIDE_NAVIGATION);
                String hideUrl = features.get(HIDE_URL);
                if (hideNavigation != null) {
                    this.hideNavigationButtons = hideNavigation.equals("yes");
                }
                if (hideUrl != null) {
                    this.hideUrlBar = hideUrl.equals("yes");
                }
            }
            String zoom = features.get(ZOOM);
            if (zoom != null) {
                this.showZoomControls = zoom.equals("yes");
            }
            String hidden = features.get(HIDDEN);
            if (hidden != null) {
                this.openWindowHidden = hidden.equals("yes");
            }
            String hardwareBack = features.get(HARDWARE_BACK_BUTTON);
            if (hardwareBack != null) {
                this.hadwareBackButton = hardwareBack.equals("yes");
            } else {
                this.hadwareBackButton = DEFAULT_HARDWARE_BACK.booleanValue();
            }
            String mediaPlayback = features.get(MEDIA_PLAYBACK_REQUIRES_USER_ACTION);
            if (mediaPlayback != null) {
                this.mediaPlaybackRequiresUserGesture = mediaPlayback.equals("yes");
            }
            String cache = features.get(CLEAR_ALL_CACHE);
            if (cache != null) {
                this.clearAllCache = cache.equals("yes");
            } else {
                String cache2 = features.get(CLEAR_SESSION_CACHE);
                if (cache2 != null) {
                    this.clearSessionCache = cache2.equals("yes");
                }
            }
            String shouldPause = features.get(SHOULD_PAUSE);
            if (shouldPause != null) {
                this.shouldPauseInAppBrowser = shouldPause.equals("yes");
            }
            String wideViewPort = features.get(USER_WIDE_VIEW_PORT);
            if (wideViewPort != null) {
                this.useWideViewPort = wideViewPort.equals("yes");
            }
            String closeButtonCaptionSet = features.get(CLOSE_BUTTON_CAPTION);
            if (closeButtonCaptionSet != null) {
                this.closeButtonCaption = closeButtonCaptionSet;
            }
            String closeButtonColorSet = features.get(CLOSE_BUTTON_COLOR);
            if (closeButtonColorSet != null) {
                this.closeButtonColor = closeButtonColorSet;
            }
            String toolbarColorSet = features.get(TOOLBAR_COLOR);
            if (toolbarColorSet != null) {
                this.toolbarColor = Color.parseColor(toolbarColorSet);
            }
            String navigationButtonColorSet = features.get(NAVIGATION_COLOR);
            if (navigationButtonColorSet != null) {
                this.navigationButtonColor = navigationButtonColorSet;
            }
            String showFooterSet = features.get(FOOTER);
            if (showFooterSet != null) {
                this.showFooter = showFooterSet.equals("yes");
            }
            String footerColorSet = features.get(FOOTER_COLOR);
            if (footerColorSet != null) {
                this.footerColor = footerColorSet;
            }
        }
        final String str = url;
        final CordovaWebView cordovaWebView = this.webView;
        this.f5cordova.getActivity().runOnUiThread(new Runnable() {
            private int dpToPixels(int dipValue) {
                return (int) TypedValue.applyDimension(1, (float) dipValue, InAppBrowser.this.f5cordova.getActivity().getResources().getDisplayMetrics());
            }

            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: android.widget.ImageButton} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: android.widget.ImageButton} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.widget.TextView} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: android.widget.ImageButton} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            private android.view.View createCloseButton(int r13) {
                /*
                    r12 = this;
                    r11 = 0
                    r10 = 10
                    r8 = 0
                    r9 = 16
                    org.apache.cordova.inappbrowser.InAppBrowser r6 = org.apache.cordova.inappbrowser.InAppBrowser.this
                    org.apache.cordova.CordovaInterface r6 = r6.f5cordova
                    android.app.Activity r6 = r6.getActivity()
                    android.content.res.Resources r1 = r6.getResources()
                    org.apache.cordova.inappbrowser.InAppBrowser r6 = org.apache.cordova.inappbrowser.InAppBrowser.this
                    java.lang.String r6 = r6.closeButtonCaption
                    java.lang.String r7 = ""
                    if (r6 == r7) goto L_0x008c
                    android.widget.TextView r2 = new android.widget.TextView
                    org.apache.cordova.inappbrowser.InAppBrowser r6 = org.apache.cordova.inappbrowser.InAppBrowser.this
                    org.apache.cordova.CordovaInterface r6 = r6.f5cordova
                    android.app.Activity r6 = r6.getActivity()
                    r2.<init>(r6)
                    org.apache.cordova.inappbrowser.InAppBrowser r6 = org.apache.cordova.inappbrowser.InAppBrowser.this
                    java.lang.String r6 = r6.closeButtonCaption
                    r2.setText(r6)
                    r6 = 1101004800(0x41a00000, float:20.0)
                    r2.setTextSize(r6)
                    org.apache.cordova.inappbrowser.InAppBrowser r6 = org.apache.cordova.inappbrowser.InAppBrowser.this
                    java.lang.String r6 = r6.closeButtonColor
                    java.lang.String r7 = ""
                    if (r6 == r7) goto L_0x004e
                    org.apache.cordova.inappbrowser.InAppBrowser r6 = org.apache.cordova.inappbrowser.InAppBrowser.this
                    java.lang.String r6 = r6.closeButtonColor
                    int r6 = android.graphics.Color.parseColor(r6)
                    r2.setTextColor(r6)
                L_0x004e:
                    r2.setGravity(r9)
                    int r6 = r12.dpToPixels(r10)
                    int r7 = r12.dpToPixels(r10)
                    r2.setPadding(r6, r8, r7, r8)
                    r0 = r2
                L_0x005d:
                    android.widget.RelativeLayout$LayoutParams r4 = new android.widget.RelativeLayout$LayoutParams
                    r6 = -2
                    r7 = -1
                    r4.<init>(r6, r7)
                    r6 = 11
                    r4.addRule(r6)
                    r0.setLayoutParams(r4)
                    int r6 = android.os.Build.VERSION.SDK_INT
                    if (r6 < r9) goto L_0x00d9
                    r0.setBackground(r11)
                L_0x0073:
                    java.lang.String r6 = "Close Button"
                    r0.setContentDescription(r6)
                    java.lang.Integer r6 = java.lang.Integer.valueOf(r13)
                    int r6 = r6.intValue()
                    r0.setId(r6)
                    org.apache.cordova.inappbrowser.InAppBrowser$6$1 r6 = new org.apache.cordova.inappbrowser.InAppBrowser$6$1
                    r6.<init>()
                    r0.setOnClickListener(r6)
                    return r0
                L_0x008c:
                    android.widget.ImageButton r2 = new android.widget.ImageButton
                    org.apache.cordova.inappbrowser.InAppBrowser r6 = org.apache.cordova.inappbrowser.InAppBrowser.this
                    org.apache.cordova.CordovaInterface r6 = r6.f5cordova
                    android.app.Activity r6 = r6.getActivity()
                    r2.<init>(r6)
                    java.lang.String r6 = "ic_action_remove"
                    java.lang.String r7 = "drawable"
                    org.apache.cordova.inappbrowser.InAppBrowser r8 = org.apache.cordova.inappbrowser.InAppBrowser.this
                    org.apache.cordova.CordovaInterface r8 = r8.f5cordova
                    android.app.Activity r8 = r8.getActivity()
                    java.lang.String r8 = r8.getPackageName()
                    int r5 = r1.getIdentifier(r6, r7, r8)
                    android.graphics.drawable.Drawable r3 = r1.getDrawable(r5)
                    org.apache.cordova.inappbrowser.InAppBrowser r6 = org.apache.cordova.inappbrowser.InAppBrowser.this
                    java.lang.String r6 = r6.closeButtonColor
                    java.lang.String r7 = ""
                    if (r6 == r7) goto L_0x00c8
                    org.apache.cordova.inappbrowser.InAppBrowser r6 = org.apache.cordova.inappbrowser.InAppBrowser.this
                    java.lang.String r6 = r6.closeButtonColor
                    int r6 = android.graphics.Color.parseColor(r6)
                    r2.setColorFilter(r6)
                L_0x00c8:
                    r2.setImageDrawable(r3)
                    android.widget.ImageView$ScaleType r6 = android.widget.ImageView.ScaleType.FIT_CENTER
                    r2.setScaleType(r6)
                    int r6 = android.os.Build.VERSION.SDK_INT
                    if (r6 < r9) goto L_0x00d7
                    r2.getAdjustViewBounds()
                L_0x00d7:
                    r0 = r2
                    goto L_0x005d
                L_0x00d9:
                    r0.setBackgroundDrawable(r11)
                    goto L_0x0073
                */
                throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.inappbrowser.InAppBrowser.AnonymousClass6.createCloseButton(int):android.view.View");
            }

            @SuppressLint({"NewApi"})
            public void run() {
                int _footerColor;
                if (InAppBrowser.this.dialog != null) {
                    InAppBrowser.this.dialog.dismiss();
                }
                InAppBrowserDialog unused = InAppBrowser.this.dialog = new InAppBrowserDialog(InAppBrowser.this.f5cordova.getActivity(), 16973830);
                InAppBrowser.this.dialog.getWindow().getAttributes().windowAnimations = 16973826;
                InAppBrowser.this.dialog.requestWindowFeature(1);
                InAppBrowser.this.dialog.setCancelable(true);
                InAppBrowser.this.dialog.setInAppBroswer(InAppBrowser.this.getInAppBrowser());
                LinearLayout linearLayout = new LinearLayout(InAppBrowser.this.f5cordova.getActivity());
                linearLayout.setOrientation(1);
                RelativeLayout relativeLayout = new RelativeLayout(InAppBrowser.this.f5cordova.getActivity());
                relativeLayout.setBackgroundColor(InAppBrowser.this.toolbarColor);
                relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, dpToPixels(44)));
                relativeLayout.setPadding(dpToPixels(2), dpToPixels(2), dpToPixels(2), dpToPixels(2));
                relativeLayout.setHorizontalGravity(3);
                relativeLayout.setVerticalGravity(48);
                RelativeLayout actionButtonContainer = new RelativeLayout(InAppBrowser.this.f5cordova.getActivity());
                actionButtonContainer.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
                actionButtonContainer.setHorizontalGravity(3);
                actionButtonContainer.setVerticalGravity(16);
                Integer num = 1;
                actionButtonContainer.setId(num.intValue());
                ImageButton back = new ImageButton(InAppBrowser.this.f5cordova.getActivity());
                RelativeLayout.LayoutParams backLayoutParams = new RelativeLayout.LayoutParams(-2, -1);
                backLayoutParams.addRule(5);
                back.setLayoutParams(backLayoutParams);
                back.setContentDescription("Back Button");
                Integer num2 = 2;
                back.setId(num2.intValue());
                Resources activityRes = InAppBrowser.this.f5cordova.getActivity().getResources();
                Drawable backIcon = activityRes.getDrawable(activityRes.getIdentifier("ic_action_previous_item", PushConstants.DRAWABLE, InAppBrowser.this.f5cordova.getActivity().getPackageName()));
                if (InAppBrowser.this.navigationButtonColor != "") {
                    back.setColorFilter(Color.parseColor(InAppBrowser.this.navigationButtonColor));
                }
                if (Build.VERSION.SDK_INT >= 16) {
                    back.setBackground((Drawable) null);
                } else {
                    back.setBackgroundDrawable((Drawable) null);
                }
                back.setImageDrawable(backIcon);
                back.setScaleType(ImageView.ScaleType.FIT_CENTER);
                back.setPadding(0, dpToPixels(10), 0, dpToPixels(10));
                if (Build.VERSION.SDK_INT >= 16) {
                    back.getAdjustViewBounds();
                }
                back.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        InAppBrowser.this.goBack();
                    }
                });
                ImageButton imageButton = new ImageButton(InAppBrowser.this.f5cordova.getActivity());
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
                layoutParams.addRule(1, 2);
                imageButton.setLayoutParams(layoutParams);
                imageButton.setContentDescription("Forward Button");
                Integer num3 = 3;
                imageButton.setId(num3.intValue());
                Drawable fwdIcon = activityRes.getDrawable(activityRes.getIdentifier("ic_action_next_item", PushConstants.DRAWABLE, InAppBrowser.this.f5cordova.getActivity().getPackageName()));
                if (InAppBrowser.this.navigationButtonColor != "") {
                    imageButton.setColorFilter(Color.parseColor(InAppBrowser.this.navigationButtonColor));
                }
                if (Build.VERSION.SDK_INT >= 16) {
                    imageButton.setBackground((Drawable) null);
                } else {
                    imageButton.setBackgroundDrawable((Drawable) null);
                }
                imageButton.setImageDrawable(fwdIcon);
                imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageButton.setPadding(0, dpToPixels(10), 0, dpToPixels(10));
                if (Build.VERSION.SDK_INT >= 16) {
                    imageButton.getAdjustViewBounds();
                }
                imageButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        InAppBrowser.this.goForward();
                    }
                });
                EditText unused2 = InAppBrowser.this.edittext = new EditText(InAppBrowser.this.f5cordova.getActivity());
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams2.addRule(1, 1);
                layoutParams2.addRule(0, 5);
                InAppBrowser.this.edittext.setLayoutParams(layoutParams2);
                Integer num4 = 4;
                InAppBrowser.this.edittext.setId(num4.intValue());
                InAppBrowser.this.edittext.setSingleLine(true);
                InAppBrowser.this.edittext.setText(str);
                InAppBrowser.this.edittext.setInputType(16);
                InAppBrowser.this.edittext.setImeOptions(2);
                InAppBrowser.this.edittext.setInputType(0);
                InAppBrowser.this.edittext.setOnKeyListener(new View.OnKeyListener() {
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() != 0 || keyCode != 66) {
                            return false;
                        }
                        InAppBrowser.this.navigate(InAppBrowser.this.edittext.getText().toString());
                        return true;
                    }
                });
                relativeLayout.addView(createCloseButton(5));
                RelativeLayout relativeLayout2 = new RelativeLayout(InAppBrowser.this.f5cordova.getActivity());
                if (InAppBrowser.this.footerColor != "") {
                    _footerColor = Color.parseColor(InAppBrowser.this.footerColor);
                } else {
                    _footerColor = -3355444;
                }
                relativeLayout2.setBackgroundColor(_footerColor);
                RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, dpToPixels(44));
                layoutParams3.addRule(12, -1);
                relativeLayout2.setLayoutParams(layoutParams3);
                if (InAppBrowser.this.closeButtonCaption != "") {
                    relativeLayout2.setPadding(dpToPixels(8), dpToPixels(8), dpToPixels(8), dpToPixels(8));
                }
                relativeLayout2.setHorizontalGravity(3);
                relativeLayout2.setVerticalGravity(80);
                relativeLayout2.addView(createCloseButton(7));
                WebView unused3 = InAppBrowser.this.inAppWebView = new WebView(InAppBrowser.this.f5cordova.getActivity());
                InAppBrowser.this.inAppWebView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                Integer num5 = 6;
                InAppBrowser.this.inAppWebView.setId(num5.intValue());
                InAppBrowser.this.inAppWebView.setWebChromeClient(new InAppChromeClient(cordovaWebView) {
                    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                        LOG.d(InAppBrowser.LOG_TAG, "File Chooser 5.0+");
                        if (InAppBrowser.this.mUploadCallbackLollipop != null) {
                            InAppBrowser.this.mUploadCallbackLollipop.onReceiveValue((Object) null);
                        }
                        ValueCallback unused = InAppBrowser.this.mUploadCallbackLollipop = filePathCallback;
                        Intent content = new Intent("android.intent.action.GET_CONTENT");
                        content.addCategory("android.intent.category.OPENABLE");
                        content.setType("*/*");
                        InAppBrowser.this.f5cordova.startActivityForResult(InAppBrowser.this, Intent.createChooser(content, "Select File"), 2);
                        return true;
                    }

                    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                        LOG.d(InAppBrowser.LOG_TAG, "File Chooser 4.1+");
                        openFileChooser(uploadMsg, acceptType);
                    }

                    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                        LOG.d(InAppBrowser.LOG_TAG, "File Chooser 3.0+");
                        ValueCallback unused = InAppBrowser.this.mUploadCallback = uploadMsg;
                        Intent content = new Intent("android.intent.action.GET_CONTENT");
                        content.addCategory("android.intent.category.OPENABLE");
                        InAppBrowser.this.f5cordova.startActivityForResult(InAppBrowser.this, Intent.createChooser(content, "Select File"), 1);
                    }
                });
                InAppBrowser.this.inAppWebView.setWebViewClient(new InAppBrowserClient(cordovaWebView, InAppBrowser.this.edittext));
                WebSettings settings = InAppBrowser.this.inAppWebView.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setJavaScriptCanOpenWindowsAutomatically(true);
                settings.setBuiltInZoomControls(InAppBrowser.this.showZoomControls);
                settings.setPluginState(WebSettings.PluginState.ON);
                if (Build.VERSION.SDK_INT >= 17) {
                    settings.setMediaPlaybackRequiresUserGesture(InAppBrowser.this.mediaPlaybackRequiresUserGesture);
                }
                String overrideUserAgent = InAppBrowser.this.preferences.getString("OverrideUserAgent", (String) null);
                String appendUserAgent = InAppBrowser.this.preferences.getString("AppendUserAgent", (String) null);
                if (overrideUserAgent != null) {
                    settings.setUserAgentString(overrideUserAgent);
                }
                if (appendUserAgent != null) {
                    settings.setUserAgentString(settings.getUserAgentString() + appendUserAgent);
                }
                Bundle appSettings = InAppBrowser.this.f5cordova.getActivity().getIntent().getExtras();
                if (appSettings == null ? true : appSettings.getBoolean("InAppBrowserStorageEnabled", true)) {
                    settings.setDatabasePath(InAppBrowser.this.f5cordova.getActivity().getApplicationContext().getDir("inAppBrowserDB", 0).getPath());
                    settings.setDatabaseEnabled(true);
                }
                settings.setDomStorageEnabled(true);
                if (InAppBrowser.this.clearAllCache) {
                    CookieManager.getInstance().removeAllCookie();
                } else if (InAppBrowser.this.clearSessionCache) {
                    CookieManager.getInstance().removeSessionCookie();
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    CookieManager.getInstance().setAcceptThirdPartyCookies(InAppBrowser.this.inAppWebView, true);
                }
                InAppBrowser.this.inAppWebView.loadUrl(str);
                Integer num6 = 6;
                InAppBrowser.this.inAppWebView.setId(num6.intValue());
                InAppBrowser.this.inAppWebView.getSettings().setLoadWithOverviewMode(true);
                InAppBrowser.this.inAppWebView.getSettings().setUseWideViewPort(InAppBrowser.this.useWideViewPort);
                InAppBrowser.this.inAppWebView.requestFocus();
                InAppBrowser.this.inAppWebView.requestFocusFromTouch();
                actionButtonContainer.addView(back);
                actionButtonContainer.addView(imageButton);
                if (!InAppBrowser.this.hideNavigationButtons) {
                    relativeLayout.addView(actionButtonContainer);
                }
                if (!InAppBrowser.this.hideUrlBar) {
                    relativeLayout.addView(InAppBrowser.this.edittext);
                }
                if (InAppBrowser.this.getShowLocationBar()) {
                    linearLayout.addView(relativeLayout);
                }
                RelativeLayout webViewLayout = new RelativeLayout(InAppBrowser.this.f5cordova.getActivity());
                webViewLayout.addView(InAppBrowser.this.inAppWebView);
                linearLayout.addView(webViewLayout);
                if (InAppBrowser.this.showFooter) {
                    webViewLayout.addView(relativeLayout2);
                }
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(InAppBrowser.this.dialog.getWindow().getAttributes());
                lp.width = -1;
                lp.height = -1;
                InAppBrowser.this.dialog.setContentView(linearLayout);
                InAppBrowser.this.dialog.show();
                InAppBrowser.this.dialog.getWindow().setAttributes(lp);
                if (InAppBrowser.this.openWindowHidden) {
                    InAppBrowser.this.dialog.hide();
                }
            }
        });
        return "";
    }

    /* access modifiers changed from: private */
    public void sendUpdate(JSONObject obj, boolean keepCallback) {
        sendUpdate(obj, keepCallback, PluginResult.Status.OK);
    }

    /* access modifiers changed from: private */
    public void sendUpdate(JSONObject obj, boolean keepCallback, PluginResult.Status status) {
        if (this.callbackContext != null) {
            PluginResult result = new PluginResult(status, obj);
            result.setKeepCallback(keepCallback);
            this.callbackContext.sendPluginResult(result);
            if (!keepCallback) {
                this.callbackContext = null;
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Uri result;
        if (Build.VERSION.SDK_INT >= 21) {
            LOG.d(LOG_TAG, "onActivityResult (For Android >= 5.0)");
            if (requestCode != 2 || this.mUploadCallbackLollipop == null) {
                super.onActivityResult(requestCode, resultCode, intent);
                return;
            }
            this.mUploadCallbackLollipop.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
            this.mUploadCallbackLollipop = null;
            return;
        }
        LOG.d(LOG_TAG, "onActivityResult (For Android < 5.0)");
        if (requestCode != 1 || this.mUploadCallback == null) {
            super.onActivityResult(requestCode, resultCode, intent);
        } else if (this.mUploadCallback != null) {
            if (intent != null) {
                this.f5cordova.getActivity();
                if (resultCode == -1) {
                    result = intent.getData();
                    this.mUploadCallback.onReceiveValue(result);
                    this.mUploadCallback = null;
                }
            }
            result = null;
            this.mUploadCallback.onReceiveValue(result);
            this.mUploadCallback = null;
        }
    }

    public class InAppBrowserClient extends WebViewClient {
        EditText edittext;
        CordovaWebView webView;

        public InAppBrowserClient(CordovaWebView webView2, EditText mEditText) {
            this.webView = webView2;
            this.edittext = mEditText;
        }

        public boolean shouldOverrideUrlLoading(WebView webView2, String url) {
            String address;
            if (url.startsWith("tel:")) {
                try {
                    Intent intent = new Intent("android.intent.action.DIAL");
                    intent.setData(Uri.parse(url));
                    InAppBrowser.this.f5cordova.getActivity().startActivity(intent);
                    return true;
                } catch (ActivityNotFoundException e) {
                    LOG.e(InAppBrowser.LOG_TAG, "Error dialing " + url + ": " + e.toString());
                }
            } else if (url.startsWith("geo:") || url.startsWith("mailto:") || url.startsWith("market:") || url.startsWith("intent:")) {
                try {
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    intent2.setData(Uri.parse(url));
                    InAppBrowser.this.f5cordova.getActivity().startActivity(intent2);
                    return true;
                } catch (ActivityNotFoundException e2) {
                    LOG.e(InAppBrowser.LOG_TAG, "Error with " + url + ": " + e2.toString());
                }
            } else if (url.startsWith("sms:")) {
                try {
                    Intent intent3 = new Intent("android.intent.action.VIEW");
                    int parmIndex = url.indexOf(63);
                    if (parmIndex == -1) {
                        address = url.substring(4);
                    } else {
                        address = url.substring(4, parmIndex);
                        String query = Uri.parse(url).getQuery();
                        if (query != null && query.startsWith("body=")) {
                            intent3.putExtra("sms_body", query.substring(5));
                        }
                    }
                    intent3.setData(Uri.parse("sms:" + address));
                    intent3.putExtra("address", address);
                    intent3.setType("vnd.android-dir/mms-sms");
                    InAppBrowser.this.f5cordova.getActivity().startActivity(intent3);
                    return true;
                } catch (ActivityNotFoundException e3) {
                    LOG.e(InAppBrowser.LOG_TAG, "Error sending sms " + url + ":" + e3.toString());
                }
            } else {
                if (!url.startsWith("http:") && !url.startsWith("https:") && url.matches("^[a-z]*://.*?$")) {
                    if (InAppBrowser.this.allowedSchemes == null) {
                        String[] unused = InAppBrowser.this.allowedSchemes = InAppBrowser.this.preferences.getString("AllowedSchemes", "").split(",");
                    }
                    if (InAppBrowser.this.allowedSchemes != null) {
                        String[] access$2600 = InAppBrowser.this.allowedSchemes;
                        int length = access$2600.length;
                        int i = 0;
                        while (i < length) {
                            if (url.startsWith(access$2600[i])) {
                                try {
                                    JSONObject obj = new JSONObject();
                                    obj.put(AppMeasurement.Param.TYPE, "customscheme");
                                    obj.put(ImagesContract.URL, url);
                                    InAppBrowser.this.sendUpdate(obj, true);
                                    return true;
                                } catch (JSONException e4) {
                                    LOG.e(InAppBrowser.LOG_TAG, "Custom Scheme URI passed in has caused a JSON error.");
                                }
                            } else {
                                i++;
                            }
                        }
                    }
                }
                return false;
            }
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            String newloc;
            super.onPageStarted(view, url, favicon);
            if (url.startsWith("http:") || url.startsWith("https:") || url.startsWith("file:")) {
                newloc = url;
            } else {
                LOG.e(InAppBrowser.LOG_TAG, "Possible Uncaught/Unknown URI");
                newloc = "http://" + url;
            }
            if (!newloc.equals(this.edittext.getText().toString())) {
                this.edittext.setText(newloc);
            }
            try {
                JSONObject obj = new JSONObject();
                obj.put(AppMeasurement.Param.TYPE, InAppBrowser.LOAD_START_EVENT);
                obj.put(ImagesContract.URL, newloc);
                InAppBrowser.this.sendUpdate(obj, true);
            } catch (JSONException e) {
                LOG.e(InAppBrowser.LOG_TAG, "URI passed in has caused a JSON error.");
            }
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (Build.VERSION.SDK_INT >= 21) {
                CookieManager.getInstance().flush();
            } else {
                CookieSyncManager.getInstance().sync();
            }
            view.clearFocus();
            view.requestFocus();
            try {
                JSONObject obj = new JSONObject();
                obj.put(AppMeasurement.Param.TYPE, InAppBrowser.LOAD_STOP_EVENT);
                obj.put(ImagesContract.URL, url);
                InAppBrowser.this.sendUpdate(obj, true);
            } catch (JSONException e) {
                LOG.d(InAppBrowser.LOG_TAG, "Should never happen");
            }
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            try {
                JSONObject obj = new JSONObject();
                obj.put(AppMeasurement.Param.TYPE, InAppBrowser.LOAD_ERROR_EVENT);
                obj.put(ImagesContract.URL, failingUrl);
                obj.put("code", errorCode);
                obj.put(PushConstants.MESSAGE, description);
                InAppBrowser.this.sendUpdate(obj, true, PluginResult.Status.ERROR);
            } catch (JSONException e) {
                LOG.d(InAppBrowser.LOG_TAG, "Should never happen");
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: org.apache.cordova.PluginManager} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v14, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: org.apache.cordova.PluginManager} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onReceivedHttpAuthRequest(android.webkit.WebView r9, android.webkit.HttpAuthHandler r10, java.lang.String r11, java.lang.String r12) {
            /*
                r8 = this;
                r3 = 0
                org.apache.cordova.CordovaWebView r5 = r8.webView     // Catch:{ NoSuchMethodException -> 0x0045, IllegalAccessException -> 0x0050, InvocationTargetException -> 0x005b }
                java.lang.Class r5 = r5.getClass()     // Catch:{ NoSuchMethodException -> 0x0045, IllegalAccessException -> 0x0050, InvocationTargetException -> 0x005b }
                java.lang.String r6 = "getPluginManager"
                r7 = 0
                java.lang.Class[] r7 = new java.lang.Class[r7]     // Catch:{ NoSuchMethodException -> 0x0045, IllegalAccessException -> 0x0050, InvocationTargetException -> 0x005b }
                java.lang.reflect.Method r2 = r5.getMethod(r6, r7)     // Catch:{ NoSuchMethodException -> 0x0045, IllegalAccessException -> 0x0050, InvocationTargetException -> 0x005b }
                org.apache.cordova.CordovaWebView r5 = r8.webView     // Catch:{ NoSuchMethodException -> 0x0045, IllegalAccessException -> 0x0050, InvocationTargetException -> 0x005b }
                r6 = 0
                java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ NoSuchMethodException -> 0x0045, IllegalAccessException -> 0x0050, InvocationTargetException -> 0x005b }
                java.lang.Object r5 = r2.invoke(r5, r6)     // Catch:{ NoSuchMethodException -> 0x0045, IllegalAccessException -> 0x0050, InvocationTargetException -> 0x005b }
                r0 = r5
                org.apache.cordova.PluginManager r0 = (org.apache.cordova.PluginManager) r0     // Catch:{ NoSuchMethodException -> 0x0045, IllegalAccessException -> 0x0050, InvocationTargetException -> 0x005b }
                r3 = r0
            L_0x001d:
                if (r3 != 0) goto L_0x0035
                org.apache.cordova.CordovaWebView r5 = r8.webView     // Catch:{ NoSuchFieldException -> 0x0066, IllegalAccessException -> 0x0071 }
                java.lang.Class r5 = r5.getClass()     // Catch:{ NoSuchFieldException -> 0x0066, IllegalAccessException -> 0x0071 }
                java.lang.String r6 = "pluginManager"
                java.lang.reflect.Field r4 = r5.getField(r6)     // Catch:{ NoSuchFieldException -> 0x0066, IllegalAccessException -> 0x0071 }
                org.apache.cordova.CordovaWebView r5 = r8.webView     // Catch:{ NoSuchFieldException -> 0x0066, IllegalAccessException -> 0x0071 }
                java.lang.Object r5 = r4.get(r5)     // Catch:{ NoSuchFieldException -> 0x0066, IllegalAccessException -> 0x0071 }
                r0 = r5
                org.apache.cordova.PluginManager r0 = (org.apache.cordova.PluginManager) r0     // Catch:{ NoSuchFieldException -> 0x0066, IllegalAccessException -> 0x0071 }
                r3 = r0
            L_0x0035:
                if (r3 == 0) goto L_0x007c
                org.apache.cordova.CordovaWebView r5 = r8.webView
                org.apache.cordova.CordovaHttpAuthHandler r6 = new org.apache.cordova.CordovaHttpAuthHandler
                r6.<init>(r10)
                boolean r5 = r3.onReceivedHttpAuthRequest(r5, r6, r11, r12)
                if (r5 == 0) goto L_0x007c
            L_0x0044:
                return
            L_0x0045:
                r1 = move-exception
                java.lang.String r5 = "InAppBrowser"
                java.lang.String r6 = r1.getLocalizedMessage()
                org.apache.cordova.LOG.d(r5, r6)
                goto L_0x001d
            L_0x0050:
                r1 = move-exception
                java.lang.String r5 = "InAppBrowser"
                java.lang.String r6 = r1.getLocalizedMessage()
                org.apache.cordova.LOG.d(r5, r6)
                goto L_0x001d
            L_0x005b:
                r1 = move-exception
                java.lang.String r5 = "InAppBrowser"
                java.lang.String r6 = r1.getLocalizedMessage()
                org.apache.cordova.LOG.d(r5, r6)
                goto L_0x001d
            L_0x0066:
                r1 = move-exception
                java.lang.String r5 = "InAppBrowser"
                java.lang.String r6 = r1.getLocalizedMessage()
                org.apache.cordova.LOG.d(r5, r6)
                goto L_0x0035
            L_0x0071:
                r1 = move-exception
                java.lang.String r5 = "InAppBrowser"
                java.lang.String r6 = r1.getLocalizedMessage()
                org.apache.cordova.LOG.d(r5, r6)
                goto L_0x0035
            L_0x007c:
                super.onReceivedHttpAuthRequest(r9, r10, r11, r12)
                goto L_0x0044
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.inappbrowser.InAppBrowser.InAppBrowserClient.onReceivedHttpAuthRequest(android.webkit.WebView, android.webkit.HttpAuthHandler, java.lang.String, java.lang.String):void");
        }
    }
}
