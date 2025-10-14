package com.adobe.phonegap.push;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import me.leolin.shortcutbadger.ShortcutBadger;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PushPlugin extends CordovaPlugin implements PushConstants {
    public static final String LOG_TAG = "Push_Plugin";
    /* access modifiers changed from: private */
    public static List<Bundle> gCachedExtras = Collections.synchronizedList(new ArrayList());
    private static boolean gForeground = false;
    private static CordovaWebView gWebView;
    /* access modifiers changed from: private */
    public static CallbackContext pushContext;
    /* access modifiers changed from: private */
    public static String registration_id = "";

    /* access modifiers changed from: private */
    public Context getApplicationContext() {
        return this.f5cordova.getActivity().getApplicationContext();
    }

    /* access modifiers changed from: private */
    @TargetApi(26)
    public JSONArray listChannels() throws JSONException {
        JSONArray channels = new JSONArray();
        if (Build.VERSION.SDK_INT >= 26) {
            for (NotificationChannel notificationChannel : ((NotificationManager) this.f5cordova.getActivity().getSystemService("notification")).getNotificationChannels()) {
                JSONObject channel = new JSONObject();
                channel.put(PushConstants.CHANNEL_ID, notificationChannel.getId());
                channel.put(PushConstants.CHANNEL_DESCRIPTION, notificationChannel.getDescription());
                channels.put(channel);
            }
        }
        return channels;
    }

    /* access modifiers changed from: private */
    @TargetApi(26)
    public void deleteChannel(String channelId) {
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) this.f5cordova.getActivity().getSystemService("notification")).deleteNotificationChannel(channelId);
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(26)
    public void createChannel(JSONObject channel) throws JSONException {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) this.f5cordova.getActivity().getSystemService("notification");
            String packageName = getApplicationContext().getPackageName();
            NotificationChannel mChannel = new NotificationChannel(channel.getString(PushConstants.CHANNEL_ID), channel.optString(PushConstants.CHANNEL_DESCRIPTION, ""), channel.optInt(PushConstants.CHANNEL_IMPORTANCE, 3));
            int lightColor = channel.optInt(PushConstants.CHANNEL_LIGHT_COLOR, -1);
            if (lightColor != -1) {
                mChannel.setLightColor(lightColor);
            }
            mChannel.setLockscreenVisibility(channel.optInt(PushConstants.VISIBILITY, 1));
            mChannel.setShowBadge(channel.optBoolean(PushConstants.BADGE, true));
            String sound = channel.optString(PushConstants.SOUND, PushConstants.SOUND_DEFAULT);
            AudioAttributes audioAttributes = new AudioAttributes.Builder().setContentType(4).setUsage(6).build();
            if (PushConstants.SOUND_RINGTONE.equals(sound)) {
                mChannel.setSound(Settings.System.DEFAULT_RINGTONE_URI, audioAttributes);
            } else if (sound != null && sound.isEmpty()) {
                mChannel.setSound((Uri) null, (AudioAttributes) null);
            } else if (sound == null || sound.contentEquals(PushConstants.SOUND_DEFAULT)) {
                mChannel.setSound(Settings.System.DEFAULT_NOTIFICATION_URI, audioAttributes);
            } else {
                mChannel.setSound(Uri.parse("android.resource://" + packageName + "/raw/" + sound), audioAttributes);
            }
            JSONArray pattern = channel.optJSONArray(PushConstants.CHANNEL_VIBRATION);
            if (pattern != null) {
                int patternLength = pattern.length();
                long[] patternArray = new long[patternLength];
                for (int i = 0; i < patternLength; i++) {
                    patternArray[i] = pattern.optLong(i);
                }
                mChannel.setVibrationPattern(patternArray);
            } else {
                mChannel.enableVibration(channel.optBoolean(PushConstants.CHANNEL_VIBRATION, true));
            }
            notificationManager.createNotificationChannel(mChannel);
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(26)
    public void createDefaultNotificationChannelIfNeeded(JSONObject options) {
        if (Build.VERSION.SDK_INT >= 26) {
            List<NotificationChannel> channels = ((NotificationManager) this.f5cordova.getActivity().getSystemService("notification")).getNotificationChannels();
            int i = 0;
            while (i < channels.size()) {
                if (!channels.get(i).getId().equals(PushConstants.DEFAULT_CHANNEL_ID)) {
                    i++;
                } else {
                    return;
                }
            }
            try {
                options.put(PushConstants.CHANNEL_ID, PushConstants.DEFAULT_CHANNEL_ID);
                options.putOpt(PushConstants.CHANNEL_DESCRIPTION, "PhoneGap PushPlugin");
                createChannel(options);
            } catch (JSONException e) {
                Log.e(LOG_TAG, "execute: Got JSON Exception " + e.getMessage());
            }
        }
    }

    public boolean execute(String action, final JSONArray data, final CallbackContext callbackContext) {
        Log.v(LOG_TAG, "execute: action=" + action);
        gWebView = this.webView;
        if (PushConstants.INITIALIZE.equals(action)) {
            this.f5cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    CallbackContext unused = PushPlugin.pushContext = callbackContext;
                    JSONObject jo = null;
                    Log.v(PushPlugin.LOG_TAG, "execute: data=" + data.toString());
                    SharedPreferences sharedPref = PushPlugin.this.getApplicationContext().getSharedPreferences(PushConstants.COM_ADOBE_PHONEGAP_PUSH, 0);
                    String token = null;
                    String senderID = null;
                    try {
                        jo = data.getJSONObject(0).getJSONObject("android");
                        PushPlugin.this.createDefaultNotificationChannelIfNeeded(jo);
                        Log.v(PushPlugin.LOG_TAG, "execute: jo=" + jo.toString());
                        senderID = PushPlugin.this.getStringResourceByName(PushConstants.GCM_DEFAULT_SENDER_ID);
                        Log.v(PushPlugin.LOG_TAG, "execute: senderID=" + senderID);
                        try {
                            token = FirebaseInstanceId.getInstance().getToken();
                        } catch (IllegalStateException e) {
                            Log.e(PushPlugin.LOG_TAG, "Exception raised while getting Firebase token " + e.getMessage());
                        }
                        if (token == null) {
                            try {
                                token = FirebaseInstanceId.getInstance().getToken(senderID, "FCM");
                            } catch (IllegalStateException e2) {
                                Log.e(PushPlugin.LOG_TAG, "Exception raised while getting Firebase token " + e2.getMessage());
                            }
                        }
                        if (!"".equals(token)) {
                            JSONObject json = new JSONObject().put(PushConstants.REGISTRATION_ID, token);
                            json.put(PushConstants.REGISTRATION_TYPE, "FCM");
                            Log.v(PushPlugin.LOG_TAG, "onRegistered: " + json.toString());
                            PushPlugin.this.subscribeToTopics(jo.optJSONArray(PushConstants.TOPICS), PushPlugin.registration_id);
                            PushPlugin.sendEvent(json);
                            if (jo != null) {
                                SharedPreferences.Editor editor = sharedPref.edit();
                                try {
                                    editor.putString(PushConstants.ICON, jo.getString(PushConstants.ICON));
                                } catch (JSONException e3) {
                                    Log.d(PushPlugin.LOG_TAG, "no icon option");
                                }
                                try {
                                    editor.putString(PushConstants.ICON_COLOR, jo.getString(PushConstants.ICON_COLOR));
                                } catch (JSONException e4) {
                                    Log.d(PushPlugin.LOG_TAG, "no iconColor option");
                                }
                                boolean clearBadge = jo.optBoolean(PushConstants.CLEAR_BADGE, false);
                                if (clearBadge) {
                                    PushPlugin.setApplicationIconBadgeNumber(PushPlugin.this.getApplicationContext(), 0);
                                }
                                editor.putBoolean(PushConstants.SOUND, jo.optBoolean(PushConstants.SOUND, true));
                                editor.putBoolean(PushConstants.VIBRATE, jo.optBoolean(PushConstants.VIBRATE, true));
                                editor.putBoolean(PushConstants.CLEAR_BADGE, clearBadge);
                                editor.putBoolean(PushConstants.CLEAR_NOTIFICATIONS, jo.optBoolean(PushConstants.CLEAR_NOTIFICATIONS, true));
                                editor.putBoolean(PushConstants.FORCE_SHOW, jo.optBoolean(PushConstants.FORCE_SHOW, false));
                                editor.putString(PushConstants.SENDER_ID, senderID);
                                editor.putString(PushConstants.MESSAGE_KEY, jo.optString(PushConstants.MESSAGE_KEY));
                                editor.putString(PushConstants.TITLE_KEY, jo.optString(PushConstants.TITLE_KEY));
                                editor.commit();
                            }
                            if (!PushPlugin.gCachedExtras.isEmpty()) {
                                Log.v(PushPlugin.LOG_TAG, "sending cached extras");
                                synchronized (PushPlugin.gCachedExtras) {
                                    for (Bundle sendExtras : PushPlugin.gCachedExtras) {
                                        PushPlugin.sendExtras(sendExtras);
                                    }
                                }
                                PushPlugin.gCachedExtras.clear();
                                return;
                            }
                            return;
                        }
                        callbackContext.error("Empty registration ID received from FCM");
                    } catch (JSONException e5) {
                        Log.e(PushPlugin.LOG_TAG, "execute: Got JSON Exception " + e5.getMessage());
                        callbackContext.error(e5.getMessage());
                    } catch (IOException e6) {
                        Log.e(PushPlugin.LOG_TAG, "execute: Got IO Exception " + e6.getMessage());
                        callbackContext.error(e6.getMessage());
                    } catch (Resources.NotFoundException e7) {
                        Log.e(PushPlugin.LOG_TAG, "execute: Got Resources NotFoundException " + e7.getMessage());
                        callbackContext.error(e7.getMessage());
                    }
                }
            });
        } else if (PushConstants.UNREGISTER.equals(action)) {
            this.f5cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        SharedPreferences sharedPref = PushPlugin.this.getApplicationContext().getSharedPreferences(PushConstants.COM_ADOBE_PHONEGAP_PUSH, 0);
                        JSONArray topics = data.optJSONArray(0);
                        if (topics == null || "".equals(PushPlugin.registration_id)) {
                            FirebaseInstanceId.getInstance().deleteInstanceId();
                            Log.v(PushPlugin.LOG_TAG, "UNREGISTER");
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.remove(PushConstants.SOUND);
                            editor.remove(PushConstants.VIBRATE);
                            editor.remove(PushConstants.CLEAR_BADGE);
                            editor.remove(PushConstants.CLEAR_NOTIFICATIONS);
                            editor.remove(PushConstants.FORCE_SHOW);
                            editor.remove(PushConstants.SENDER_ID);
                            editor.commit();
                        } else {
                            PushPlugin.this.unsubscribeFromTopics(topics, PushPlugin.registration_id);
                        }
                        callbackContext.success();
                    } catch (IOException e) {
                        Log.e(PushPlugin.LOG_TAG, "execute: Got JSON Exception " + e.getMessage());
                        callbackContext.error(e.getMessage());
                    }
                }
            });
        } else if (PushConstants.FINISH.equals(action)) {
            callbackContext.success();
        } else if (PushConstants.HAS_PERMISSION.equals(action)) {
            this.f5cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    JSONObject jo = new JSONObject();
                    try {
                        Log.d(PushPlugin.LOG_TAG, "has permission: " + NotificationManagerCompat.from(PushPlugin.this.getApplicationContext()).areNotificationsEnabled());
                        jo.put("isEnabled", NotificationManagerCompat.from(PushPlugin.this.getApplicationContext()).areNotificationsEnabled());
                        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, jo);
                        pluginResult.setKeepCallback(true);
                        callbackContext.sendPluginResult(pluginResult);
                    } catch (UnknownError e) {
                        callbackContext.error(e.getMessage());
                    } catch (JSONException e2) {
                        callbackContext.error(e2.getMessage());
                    }
                }
            });
        } else if (PushConstants.SET_APPLICATION_ICON_BADGE_NUMBER.equals(action)) {
            this.f5cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    Log.v(PushPlugin.LOG_TAG, "setApplicationIconBadgeNumber: data=" + data.toString());
                    try {
                        PushPlugin.setApplicationIconBadgeNumber(PushPlugin.this.getApplicationContext(), data.getJSONObject(0).getInt(PushConstants.BADGE));
                    } catch (JSONException e) {
                        callbackContext.error(e.getMessage());
                    }
                    callbackContext.success();
                }
            });
        } else if (PushConstants.GET_APPLICATION_ICON_BADGE_NUMBER.equals(action)) {
            this.f5cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    Log.v(PushPlugin.LOG_TAG, PushConstants.GET_APPLICATION_ICON_BADGE_NUMBER);
                    callbackContext.success(PushPlugin.getApplicationIconBadgeNumber(PushPlugin.this.getApplicationContext()));
                }
            });
        } else if (PushConstants.CLEAR_ALL_NOTIFICATIONS.equals(action)) {
            this.f5cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    Log.v(PushPlugin.LOG_TAG, PushConstants.CLEAR_ALL_NOTIFICATIONS);
                    PushPlugin.this.clearAllNotifications();
                    callbackContext.success();
                }
            });
        } else if (PushConstants.SUBSCRIBE.equals(action)) {
            this.f5cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        PushPlugin.this.subscribeToTopic(data.getString(0), PushPlugin.registration_id);
                        callbackContext.success();
                    } catch (JSONException e) {
                        callbackContext.error(e.getMessage());
                    }
                }
            });
        } else if (PushConstants.UNSUBSCRIBE.equals(action)) {
            this.f5cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        PushPlugin.this.unsubscribeFromTopic(data.getString(0), PushPlugin.registration_id);
                        callbackContext.success();
                    } catch (JSONException e) {
                        callbackContext.error(e.getMessage());
                    }
                }
            });
        } else if (PushConstants.CREATE_CHANNEL.equals(action)) {
            this.f5cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        PushPlugin.this.createChannel(data.getJSONObject(0));
                        callbackContext.success();
                    } catch (JSONException e) {
                        callbackContext.error(e.getMessage());
                    }
                }
            });
        } else if (PushConstants.DELETE_CHANNEL.equals(action)) {
            this.f5cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        PushPlugin.this.deleteChannel(data.getString(0));
                        callbackContext.success();
                    } catch (JSONException e) {
                        callbackContext.error(e.getMessage());
                    }
                }
            });
        } else if (PushConstants.LIST_CHANNELS.equals(action)) {
            this.f5cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        callbackContext.success(PushPlugin.this.listChannels());
                    } catch (JSONException e) {
                        callbackContext.error(e.getMessage());
                    }
                }
            });
        } else if (PushConstants.CLEAR_NOTIFICATION.equals(action)) {
            this.f5cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        Log.v(PushPlugin.LOG_TAG, PushConstants.CLEAR_NOTIFICATION);
                        PushPlugin.this.clearNotification(data.getInt(0));
                        callbackContext.success();
                    } catch (JSONException e) {
                        callbackContext.error(e.getMessage());
                    }
                }
            });
        } else {
            Log.e(LOG_TAG, "Invalid action : " + action);
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
            return false;
        }
        return true;
    }

    public static void sendEvent(JSONObject _json) {
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, _json);
        pluginResult.setKeepCallback(true);
        if (pushContext != null) {
            pushContext.sendPluginResult(pluginResult);
        }
    }

    public static void sendError(String message) {
        PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, message);
        pluginResult.setKeepCallback(true);
        if (pushContext != null) {
            pushContext.sendPluginResult(pluginResult);
        }
    }

    public static void sendExtras(Bundle extras) {
        if (extras != null) {
            String noCache = extras.getString(PushConstants.NO_CACHE);
            if (gWebView != null) {
                sendEvent(convertBundleToJson(extras));
            } else if (!"1".equals(noCache)) {
                Log.v(LOG_TAG, "sendExtras: caching extras to send at a later time.");
                gCachedExtras.add(extras);
            }
        }
    }

    public static int getApplicationIconBadgeNumber(Context context) {
        return context.getSharedPreferences(PushConstants.BADGE, 0).getInt(PushConstants.BADGE, 0);
    }

    public static void setApplicationIconBadgeNumber(Context context, int badgeCount) {
        if (badgeCount > 0) {
            ShortcutBadger.applyCount(context, badgeCount);
        } else {
            ShortcutBadger.removeCount(context);
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(PushConstants.BADGE, 0).edit();
        editor.putInt(PushConstants.BADGE, Math.max(badgeCount, 0));
        editor.apply();
    }

    public void initialize(CordovaInterface cordova2, CordovaWebView webView) {
        super.initialize(cordova2, webView);
        gForeground = true;
    }

    public void onPause(boolean multitasking) {
        super.onPause(multitasking);
        gForeground = false;
        if (getApplicationContext().getSharedPreferences(PushConstants.COM_ADOBE_PHONEGAP_PUSH, 0).getBoolean(PushConstants.CLEAR_NOTIFICATIONS, true)) {
            clearAllNotifications();
        }
    }

    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        gForeground = true;
    }

    public void onDestroy() {
        super.onDestroy();
        gForeground = false;
        gWebView = null;
    }

    /* access modifiers changed from: private */
    public void clearAllNotifications() {
        ((NotificationManager) this.f5cordova.getActivity().getSystemService("notification")).cancelAll();
    }

    /* access modifiers changed from: private */
    public void clearNotification(int id) {
        ((NotificationManager) this.f5cordova.getActivity().getSystemService("notification")).cancel((String) this.f5cordova.getActivity().getPackageManager().getApplicationLabel(this.f5cordova.getActivity().getApplicationInfo()), id);
    }

    /* access modifiers changed from: private */
    public void subscribeToTopics(JSONArray topics, String registrationToken) {
        if (topics != null) {
            for (int i = 0; i < topics.length(); i++) {
                subscribeToTopic(topics.optString(i, (String) null), registrationToken);
            }
        }
    }

    /* access modifiers changed from: private */
    public void subscribeToTopic(String topic, String registrationToken) {
        if (topic != null) {
            Log.d(LOG_TAG, "Subscribing to topic: " + topic);
            FirebaseMessaging.getInstance().subscribeToTopic(topic);
        }
    }

    /* access modifiers changed from: private */
    public void unsubscribeFromTopics(JSONArray topics, String registrationToken) {
        if (topics != null) {
            for (int i = 0; i < topics.length(); i++) {
                unsubscribeFromTopic(topics.optString(i, (String) null), registrationToken);
            }
        }
    }

    /* access modifiers changed from: private */
    public void unsubscribeFromTopic(String topic, String registrationToken) {
        if (topic != null) {
            Log.d(LOG_TAG, "Unsubscribing to topic: " + topic);
            FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
        }
    }

    private static JSONObject convertBundleToJson(Bundle extras) {
        Log.d(LOG_TAG, "convert extras to json");
        try {
            JSONObject json = new JSONObject();
            JSONObject additionalData = new JSONObject();
            HashSet<String> jsonKeySet = new HashSet<>();
            Collections.addAll(jsonKeySet, new String[]{PushConstants.TITLE, PushConstants.MESSAGE, "count", PushConstants.SOUND, PushConstants.IMAGE});
            for (String key : extras.keySet()) {
                Object value = extras.get(key);
                Log.d(LOG_TAG, "key = " + key);
                if (jsonKeySet.contains(key)) {
                    json.put(key, value);
                } else if (key.equals(PushConstants.COLDSTART)) {
                    additionalData.put(key, extras.getBoolean(PushConstants.COLDSTART));
                } else if (key.equals(PushConstants.FOREGROUND)) {
                    additionalData.put(key, extras.getBoolean(PushConstants.FOREGROUND));
                } else if (key.equals(PushConstants.DISMISSED)) {
                    additionalData.put(key, extras.getBoolean(PushConstants.DISMISSED));
                } else if (value instanceof String) {
                    String strValue = (String) value;
                    try {
                        if (strValue.startsWith("{")) {
                            additionalData.put(key, new JSONObject(strValue));
                        } else if (strValue.startsWith("[")) {
                            additionalData.put(key, new JSONArray(strValue));
                        } else {
                            additionalData.put(key, value);
                        }
                    } catch (Exception e) {
                        additionalData.put(key, value);
                    }
                }
            }
            json.put(PushConstants.ADDITIONAL_DATA, additionalData);
            Log.v(LOG_TAG, "extrasToJSON: " + json.toString());
            return json;
        } catch (JSONException e2) {
            Log.e(LOG_TAG, "extrasToJSON: JSON exception");
            return null;
        }
    }

    /* access modifiers changed from: private */
    public String getStringResourceByName(String aString) {
        Activity activity = this.f5cordova.getActivity();
        return activity.getString(activity.getResources().getIdentifier(aString, "string", activity.getPackageName()));
    }

    public static boolean isInForeground() {
        return gForeground;
    }

    public static boolean isActive() {
        return gWebView != null;
    }

    protected static void setRegistrationID(String token) {
        registration_id = token;
    }
}
