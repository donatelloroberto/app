package acidhax.cordova.chromecast;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.cast.CastMediaControlIntent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Chromecast extends CordovaPlugin implements ChromecastOnMediaUpdatedListener, ChromecastOnSessionUpdatedListener {
    private static final String SETTINGS_NAME = "CordovaChromecastSettings";
    private String appId;
    private boolean autoConnect = false;
    /* access modifiers changed from: private */
    public volatile ChromecastSession currentSession;
    private String lastAppId = null;
    private String lastSessionId = null;
    /* access modifiers changed from: private */
    public MediaRouteSelector mMediaRouteSelector;
    /* access modifiers changed from: private */
    public MediaRouter mMediaRouter;
    /* access modifiers changed from: private */
    public volatile ChromecastMediaRouterCallback mMediaRouterCallback = new ChromecastMediaRouterCallback();
    private SharedPreferences settings;

    /* access modifiers changed from: private */
    public void log(String s) {
        sendJavascript("console.log('" + s + "');");
    }

    public void initialize(CordovaInterface cordova2, CordovaWebView webView) {
        super.initialize(cordova2, webView);
        this.settings = this.f5cordova.getActivity().getSharedPreferences(SETTINGS_NAME, 0);
        this.lastSessionId = this.settings.getString("lastSessionId", "");
        this.lastAppId = this.settings.getString("lastAppId", "");
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.currentSession != null) {
        }
    }

    public boolean execute(String action, JSONArray args, CallbackContext cbContext) throws JSONException {
        try {
            Method[] list = getClass().getMethods();
            Method methodToExecute = null;
            int length = list.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Method method = list[i];
                if (method.getName().equals(action)) {
                    Type[] types = method.getGenericParameterTypes();
                    if (args.length() + 1 == types.length) {
                        boolean isValid = true;
                        int i2 = 0;
                        while (true) {
                            if (i2 < args.length()) {
                                if (types[i2] != args.get(i2).getClass()) {
                                    isValid = false;
                                    break;
                                }
                                isValid = true;
                                i2++;
                            } else {
                                break;
                            }
                        }
                        if (isValid) {
                            methodToExecute = method;
                            break;
                        }
                    } else {
                        continue;
                    }
                }
                i++;
            }
            if (methodToExecute == null) {
                return false;
            }
            Object[] variableArgs = new Object[methodToExecute.getGenericParameterTypes().length];
            for (int i3 = 0; i3 < args.length(); i3++) {
                variableArgs[i3] = args.get(i3);
            }
            variableArgs[variableArgs.length - 1] = cbContext;
            if (methodToExecute.getReturnType() == Boolean.TYPE) {
                return ((Boolean) methodToExecute.invoke(this, variableArgs)).booleanValue();
            }
            methodToExecute.invoke(this, variableArgs);
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return false;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void setLastSessionId(String sessionId) {
        this.lastSessionId = sessionId;
        this.settings.edit().putString("lastSessionId", sessionId).apply();
    }

    public boolean setup(CallbackContext callbackContext) {
        callbackContext.success();
        return true;
    }

    public boolean initialize(String appId2, String autoJoinPolicy, String defaultActionPolicy, CallbackContext callbackContext) {
        final Activity activity = this.f5cordova.getActivity();
        this.appId = appId2;
        log("initialize " + autoJoinPolicy + " " + appId2 + " " + this.lastAppId);
        if (autoJoinPolicy.equals("origin_scoped") && appId2.equals(this.lastAppId)) {
            log("lastAppId " + this.lastAppId);
            this.autoConnect = true;
        } else if (autoJoinPolicy.equals("origin_scoped")) {
            log("setting lastAppId " + this.lastAppId);
            this.settings.edit().putString("lastAppId", appId2).apply();
        }
        final String str = appId2;
        final CallbackContext callbackContext2 = callbackContext;
        activity.runOnUiThread(new Runnable() {
            public void run() {
                MediaRouter unused = Chromecast.this.mMediaRouter = MediaRouter.getInstance(activity.getApplicationContext());
                MediaRouteSelector unused2 = Chromecast.this.mMediaRouteSelector = new MediaRouteSelector.Builder().addControlCategory(CastMediaControlIntent.categoryForCast(str)).build();
                Chromecast.this.mMediaRouterCallback.registerCallbacks(this);
                Chromecast.this.mMediaRouter.addCallback(Chromecast.this.mMediaRouteSelector, Chromecast.this.mMediaRouterCallback, 1);
                callbackContext2.success();
                Chromecast.this.checkReceiverAvailable();
                Chromecast.this.emitAllRoutes((CallbackContext) null);
            }
        });
        return true;
    }

    public boolean requestSession(final CallbackContext callbackContext) {
        if (this.currentSession != null) {
            callbackContext.success(this.currentSession.createSessionObject());
        } else {
            setLastSessionId("");
            final Activity activity = this.f5cordova.getActivity();
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    MediaRouter unused = Chromecast.this.mMediaRouter = MediaRouter.getInstance(activity.getApplicationContext());
                    final List<MediaRouter.RouteInfo> routeList = Chromecast.this.mMediaRouter.getRoutes();
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle("Choose a Chromecast");
                    ArrayList<String> seq_tmp1 = new ArrayList<>();
                    final ArrayList<Integer> seq_tmp_cnt_final = new ArrayList<>();
                    for (int n = 1; n < routeList.size(); n++) {
                        MediaRouter.RouteInfo route = routeList.get(n);
                        if (!route.getName().equals("Phone") && route.getId().indexOf("Cast") > -1) {
                            seq_tmp1.add(route.getName());
                            seq_tmp_cnt_final.add(Integer.valueOf(n));
                        }
                    }
                    builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            callbackContext.error("cancel");
                        }
                    });
                    builder.setItems((CharSequence[]) seq_tmp1.toArray(new CharSequence[seq_tmp1.size()]), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            int which2 = ((Integer) seq_tmp_cnt_final.get(which)).intValue();
                            Chromecast.this.createSession((MediaRouter.RouteInfo) routeList.get(which2), callbackContext);
                        }
                    });
                    builder.show();
                }
            });
        }
        return true;
    }

    public boolean selectRoute(final String routeId, final CallbackContext callbackContext) {
        if (this.currentSession != null) {
            callbackContext.success(this.currentSession.createSessionObject());
        } else {
            setLastSessionId("");
            final Activity activity = this.f5cordova.getActivity();
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    MediaRouter unused = Chromecast.this.mMediaRouter = MediaRouter.getInstance(activity.getApplicationContext());
                    for (MediaRouter.RouteInfo route : Chromecast.this.mMediaRouter.getRoutes()) {
                        if (route.getId().equals(routeId)) {
                            Chromecast.this.createSession(route, callbackContext);
                            return;
                        }
                    }
                    callbackContext.error("No route found");
                }
            });
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void createSession(MediaRouter.RouteInfo routeInfo, final CallbackContext callbackContext) {
        this.currentSession = new ChromecastSession(routeInfo, this.f5cordova, this, this);
        this.currentSession.launch(this.appId, new ChromecastSessionCallback() {
            /* access modifiers changed from: package-private */
            public void onSuccess(Object object) {
                ChromecastSession session = (ChromecastSession) object;
                if (object == null) {
                    onError("unknown");
                } else if (session == Chromecast.this.currentSession) {
                    Chromecast.this.setLastSessionId(Chromecast.this.currentSession.getSessionId());
                    if (callbackContext != null) {
                        callbackContext.success(session.createSessionObject());
                    } else {
                        Chromecast.this.sendJavascript("chrome.cast._.sessionJoined(" + Chromecast.this.currentSession.createSessionObject().toString() + ");");
                    }
                }
            }

            /* access modifiers changed from: package-private */
            public void onError(String reason) {
                if (reason != null) {
                    Chromecast.this.log("createSession onError " + reason);
                    if (callbackContext != null) {
                        callbackContext.error(reason);
                    }
                } else if (callbackContext != null) {
                    callbackContext.error("unknown");
                }
            }
        });
    }

    private void joinSession(MediaRouter.RouteInfo routeInfo) {
        new ChromecastSession(routeInfo, this.f5cordova, this, this).join(this.appId, this.lastSessionId, new ChromecastSessionCallback() {
            /* access modifiers changed from: package-private */
            public void onSuccess(Object object) {
                if (Chromecast.this.currentSession == null) {
                    try {
                        ChromecastSession unused = Chromecast.this.currentSession = (ChromecastSession) object;
                        Chromecast.this.setLastSessionId(Chromecast.this.currentSession.getSessionId());
                        Chromecast.this.sendJavascript("chrome.cast._.sessionJoined(" + Chromecast.this.currentSession.createSessionObject().toString() + ");");
                    } catch (Exception e) {
                        Chromecast.this.log("wut.... " + e.getMessage() + e.getStackTrace());
                    }
                }
            }

            /* access modifiers changed from: package-private */
            public void onError(String reason) {
                Chromecast.this.log("sessionJoinAttempt error " + reason);
            }
        });
    }

    public boolean setReceiverVolumeLevel(Double newLevel, CallbackContext callbackContext) {
        if (this.currentSession != null) {
            this.currentSession.setVolume(newLevel.doubleValue(), genericCallback(callbackContext));
            return true;
        }
        callbackContext.error("session_error");
        return true;
    }

    public boolean setReceiverVolumeLevel(Integer newLevel, CallbackContext callbackContext) {
        return setReceiverVolumeLevel(Double.valueOf(newLevel.doubleValue()), callbackContext);
    }

    public boolean setReceiverMuted(Boolean muted, CallbackContext callbackContext) {
        if (this.currentSession != null) {
            this.currentSession.setMute(muted.booleanValue(), genericCallback(callbackContext));
            return true;
        }
        callbackContext.error("session_error");
        return true;
    }

    public boolean stopSession(CallbackContext callbackContext) {
        callbackContext.error("not_implemented");
        return true;
    }

    public boolean sendMessage(String namespace, String message, final CallbackContext callbackContext) {
        if (this.currentSession == null) {
            return true;
        }
        this.currentSession.sendMessage(namespace, message, new ChromecastSessionCallback() {
            /* access modifiers changed from: package-private */
            public void onSuccess(Object object) {
                callbackContext.success();
            }

            /* access modifiers changed from: package-private */
            public void onError(String reason) {
                callbackContext.error(reason);
            }
        });
        return true;
    }

    public boolean addMessageListener(String namespace, CallbackContext callbackContext) {
        if (this.currentSession == null) {
            return true;
        }
        this.currentSession.addMessageListener(namespace);
        callbackContext.success();
        return true;
    }

    public boolean loadMedia(String contentId, String contentType, Integer duration, String streamType, Boolean autoPlay, Double currentTime, JSONObject metadata, CallbackContext callbackContext) {
        if (this.currentSession != null) {
            final CallbackContext callbackContext2 = callbackContext;
            return this.currentSession.loadMedia(contentId, contentType, (long) duration.intValue(), streamType, autoPlay.booleanValue(), currentTime.doubleValue(), metadata, new ChromecastSessionCallback() {
                /* access modifiers changed from: package-private */
                public void onSuccess(Object object) {
                    if (object == null) {
                        onError("unknown");
                    } else {
                        callbackContext2.success((JSONObject) object);
                    }
                }

                /* access modifiers changed from: package-private */
                public void onError(String reason) {
                    callbackContext2.error(reason);
                }
            });
        }
        callbackContext.error("session_error");
        return false;
    }

    public boolean loadMedia(String contentId, String contentType, Integer duration, String streamType, Boolean autoPlay, Integer currentTime, JSONObject metadata, CallbackContext callbackContext) {
        return loadMedia(contentId, contentType, duration, streamType, autoPlay, new Double(currentTime.doubleValue()), metadata, callbackContext);
    }

    public boolean mediaPlay(CallbackContext callbackContext) {
        if (this.currentSession != null) {
            this.currentSession.mediaPlay(genericCallback(callbackContext));
            return true;
        }
        callbackContext.error("session_error");
        return true;
    }

    public boolean mediaPause(CallbackContext callbackContext) {
        if (this.currentSession != null) {
            this.currentSession.mediaPause(genericCallback(callbackContext));
            return true;
        }
        callbackContext.error("session_error");
        return true;
    }

    public boolean mediaSeek(Integer seekTime, String resumeState, CallbackContext callbackContext) {
        if (this.currentSession != null) {
            this.currentSession.mediaSeek(seekTime.longValue() * 1000, resumeState, genericCallback(callbackContext));
            return true;
        }
        callbackContext.error("session_error");
        return true;
    }

    public boolean setMediaVolume(Double level, CallbackContext callbackContext) {
        if (this.currentSession != null) {
            this.currentSession.mediaSetVolume(level.doubleValue(), genericCallback(callbackContext));
            return true;
        }
        callbackContext.error("session_error");
        return true;
    }

    public boolean setMediaMuted(Boolean muted, CallbackContext callbackContext) {
        if (this.currentSession != null) {
            this.currentSession.mediaSetMuted(muted.booleanValue(), genericCallback(callbackContext));
            return true;
        }
        callbackContext.error("session_error");
        return true;
    }

    public boolean mediaStop(CallbackContext callbackContext) {
        if (this.currentSession != null) {
            this.currentSession.mediaStop(genericCallback(callbackContext));
            return true;
        }
        callbackContext.error("session_error");
        return true;
    }

    public boolean sessionStop(CallbackContext callbackContext) {
        if (this.currentSession != null) {
            this.currentSession.kill(genericCallback(callbackContext));
            this.currentSession = null;
            setLastSessionId("");
            return true;
        }
        callbackContext.success();
        return true;
    }

    public boolean sessionLeave(CallbackContext callbackContext) {
        if (this.currentSession != null) {
            this.currentSession.leave(genericCallback(callbackContext));
            this.currentSession = null;
            setLastSessionId("");
            return true;
        }
        callbackContext.success();
        return true;
    }

    public boolean emitAllRoutes(CallbackContext callbackContext) {
        final Activity activity = this.f5cordova.getActivity();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                MediaRouter unused = Chromecast.this.mMediaRouter = MediaRouter.getInstance(activity.getApplicationContext());
                for (MediaRouter.RouteInfo route : Chromecast.this.mMediaRouter.getRoutes()) {
                    if (!route.getName().equals("Phone") && route.getId().indexOf("Cast") > -1) {
                        Chromecast.this.sendJavascript("chrome.cast._.routeAdded(" + Chromecast.this.routeToJSON(route) + ")");
                    }
                }
            }
        });
        if (callbackContext == null) {
            return true;
        }
        callbackContext.success();
        return true;
    }

    /* access modifiers changed from: private */
    public void checkReceiverAvailable() {
        final Activity activity = this.f5cordova.getActivity();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                MediaRouter unused = Chromecast.this.mMediaRouter = MediaRouter.getInstance(activity.getApplicationContext());
                boolean available = false;
                Iterator<MediaRouter.RouteInfo> it = Chromecast.this.mMediaRouter.getRoutes().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    MediaRouter.RouteInfo route = it.next();
                    if (!route.getName().equals("Phone") && route.getId().indexOf("Cast") > -1) {
                        available = true;
                        break;
                    }
                }
                if (available || (Chromecast.this.currentSession != null && Chromecast.this.currentSession.isConnected())) {
                    Chromecast.this.sendJavascript("chrome.cast._.receiverAvailable()");
                } else {
                    Chromecast.this.sendJavascript("chrome.cast._.receiverUnavailable()");
                }
            }
        });
    }

    private ChromecastSessionCallback genericCallback(final CallbackContext callbackContext) {
        return new ChromecastSessionCallback() {
            public void onSuccess(Object object) {
                callbackContext.success();
            }

            public void onError(String reason) {
                callbackContext.error(reason);
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onRouteAdded(MediaRouter router, MediaRouter.RouteInfo route) {
        if (!this.autoConnect || this.currentSession != null || route.getName().equals("Phone")) {
            log("For some reason, not attempting to join route " + route.getName() + ", " + this.currentSession + ", " + this.autoConnect);
        } else {
            log("Attempting to join route " + route.getName());
            joinSession(route);
        }
        if (!route.getName().equals("Phone") && route.getId().indexOf("Cast") > -1) {
            sendJavascript("chrome.cast._.routeAdded(" + routeToJSON(route) + ")");
        }
        checkReceiverAvailable();
    }

    /* access modifiers changed from: protected */
    public void onRouteRemoved(MediaRouter router, MediaRouter.RouteInfo route) {
        checkReceiverAvailable();
        if (!route.getName().equals("Phone") && route.getId().indexOf("Cast") > -1) {
            sendJavascript("chrome.cast._.routeRemoved(" + routeToJSON(route) + ")");
        }
    }

    /* access modifiers changed from: protected */
    public void onRouteSelected(MediaRouter router, MediaRouter.RouteInfo route) {
        createSession(route, (CallbackContext) null);
    }

    /* access modifiers changed from: protected */
    public void onRouteUnselected(MediaRouter router, MediaRouter.RouteInfo route) {
    }

    /* access modifiers changed from: private */
    public JSONObject routeToJSON(MediaRouter.RouteInfo route) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("name", route.getName());
            obj.put(PushConstants.CHANNEL_ID, route.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public void onMediaUpdated(boolean isAlive, JSONObject media) {
        if (isAlive) {
            sendJavascript("chrome.cast._.mediaUpdated(true, " + media.toString() + ");");
        } else {
            sendJavascript("chrome.cast._.mediaUpdated(false, " + media.toString() + ");");
        }
    }

    public void onSessionUpdated(boolean isAlive, JSONObject session) {
        if (isAlive) {
            sendJavascript("chrome.cast._.sessionUpdated(true, " + session.toString() + ");");
            return;
        }
        log("SESSION DESTROYYYY");
        sendJavascript("chrome.cast._.sessionUpdated(false, " + session.toString() + ");");
        this.currentSession = null;
    }

    public void onMediaLoaded(JSONObject media) {
        sendJavascript("chrome.cast._.mediaLoaded(true, " + media.toString() + ");");
    }

    public void onMessage(ChromecastSession session, String namespace, String message) {
        sendJavascript("chrome.cast._.onMessage('" + session.getSessionId() + "', '" + namespace + "', '" + message + "')");
    }

    /* access modifiers changed from: private */
    @TargetApi(19)
    public void sendJavascript(final String javascript) {
        this.webView.getView().post(new Runnable() {
            public void run() {
                if (Build.VERSION.SDK_INT >= 19) {
                    Chromecast.this.webView.sendJavascript(javascript);
                } else {
                    Chromecast.this.webView.loadUrl("javascript:" + javascript);
                }
            }
        });
    }
}
