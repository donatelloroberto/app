package com.adobe.phonegap.push;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v4.internal.view.SupportMenu;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class FCMService extends FirebaseMessagingService implements PushConstants {
    private static final String LOG_TAG = "Push_FCMService";
    private static HashMap<Integer, ArrayList<String>> messageMap = new HashMap<>();

    public void setNotification(int notId, String message) {
        ArrayList<String> messageList = messageMap.get(Integer.valueOf(notId));
        if (messageList == null) {
            messageList = new ArrayList<>();
            messageMap.put(Integer.valueOf(notId), messageList);
        }
        if (message.isEmpty()) {
            messageList.clear();
        } else {
            messageList.add(message);
        }
    }

    public void onMessageReceived(RemoteMessage message) {
        String from = message.getFrom();
        Log.d(LOG_TAG, "onMessage - from: " + from);
        Bundle extras = new Bundle();
        if (message.getNotification() != null) {
            extras.putString(PushConstants.TITLE, message.getNotification().getTitle());
            extras.putString(PushConstants.MESSAGE, message.getNotification().getBody());
            extras.putString(PushConstants.SOUND, message.getNotification().getSound());
            extras.putString(PushConstants.ICON, message.getNotification().getIcon());
            extras.putString(PushConstants.COLOR, message.getNotification().getColor());
        }
        for (Map.Entry<String, String> entry : message.getData().entrySet()) {
            extras.putString(entry.getKey(), entry.getValue());
        }
        if (extras != null && isAvailableSender(from)) {
            Context applicationContext = getApplicationContext();
            SharedPreferences prefs = applicationContext.getSharedPreferences(PushConstants.COM_ADOBE_PHONEGAP_PUSH, 0);
            boolean forceShow = prefs.getBoolean(PushConstants.FORCE_SHOW, false);
            boolean clearBadge = prefs.getBoolean(PushConstants.CLEAR_BADGE, false);
            Bundle extras2 = normalizeExtras(applicationContext, extras, prefs.getString(PushConstants.MESSAGE_KEY, PushConstants.MESSAGE), prefs.getString(PushConstants.TITLE_KEY, PushConstants.TITLE));
            if (clearBadge) {
                PushPlugin.setApplicationIconBadgeNumber(getApplicationContext(), 0);
            }
            if (!forceShow && PushPlugin.isInForeground()) {
                Log.d(LOG_TAG, PushConstants.FOREGROUND);
                extras2.putBoolean(PushConstants.FOREGROUND, true);
                extras2.putBoolean(PushConstants.COLDSTART, false);
                PushPlugin.sendExtras(extras2);
            } else if (!forceShow || !PushPlugin.isInForeground()) {
                Log.d(LOG_TAG, "background");
                extras2.putBoolean(PushConstants.FOREGROUND, false);
                extras2.putBoolean(PushConstants.COLDSTART, PushPlugin.isActive());
                showNotificationIfPossible(applicationContext, extras2);
            } else {
                Log.d(LOG_TAG, "foreground force");
                extras2.putBoolean(PushConstants.FOREGROUND, true);
                extras2.putBoolean(PushConstants.COLDSTART, false);
                showNotificationIfPossible(applicationContext, extras2);
            }
        }
    }

    private void replaceKey(Context context, String oldKey, String newKey, Bundle extras, Bundle newExtras) {
        Object value = extras.get(oldKey);
        if (value == null) {
            return;
        }
        if (value instanceof String) {
            newExtras.putString(newKey, localizeKey(context, newKey, (String) value));
        } else if (value instanceof Boolean) {
            newExtras.putBoolean(newKey, ((Boolean) value).booleanValue());
        } else if (value instanceof Number) {
            newExtras.putDouble(newKey, ((Number) value).doubleValue());
        } else {
            newExtras.putString(newKey, String.valueOf(value));
        }
    }

    private String localizeKey(Context context, String key, String value) {
        if (!key.equals(PushConstants.TITLE) && !key.equals(PushConstants.MESSAGE) && !key.equals(PushConstants.SUMMARY_TEXT)) {
            return value;
        }
        try {
            JSONObject localeObject = new JSONObject(value);
            String localeKey = localeObject.getString(PushConstants.LOC_KEY);
            ArrayList<String> localeFormatData = new ArrayList<>();
            if (!localeObject.isNull(PushConstants.LOC_DATA)) {
                JSONArray localeDataArray = new JSONArray(localeObject.getString(PushConstants.LOC_DATA));
                for (int i = 0; i < localeDataArray.length(); i++) {
                    localeFormatData.add(localeDataArray.getString(i));
                }
            }
            String packageName = context.getPackageName();
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier(localeKey, "string", packageName);
            if (resourceId != 0) {
                return resources.getString(resourceId, localeFormatData.toArray());
            }
            Log.d(LOG_TAG, "can't find resource for locale key = " + localeKey);
            return value;
        } catch (JSONException e) {
            Log.d(LOG_TAG, "no locale found for key = " + key + ", error " + e.getMessage());
            return value;
        }
    }

    private String normalizeKey(String key, String messageKey, String titleKey, Bundle newExtras) {
        if (key.equals(PushConstants.BODY) || key.equals(PushConstants.ALERT) || key.equals(PushConstants.MP_MESSAGE) || key.equals(PushConstants.GCM_NOTIFICATION_BODY) || key.equals(PushConstants.TWILIO_BODY) || key.equals(messageKey) || key.equals(PushConstants.AWS_PINPOINT_BODY)) {
            String str = key;
            return PushConstants.MESSAGE;
        } else if (key.equals(PushConstants.TWILIO_TITLE) || key.equals(PushConstants.SUBJECT) || key.equals(titleKey)) {
            String str2 = key;
            return PushConstants.TITLE;
        } else if (key.equals(PushConstants.MSGCNT) || key.equals(PushConstants.BADGE)) {
            String str3 = key;
            return "count";
        } else if (key.equals(PushConstants.SOUNDNAME) || key.equals(PushConstants.TWILIO_SOUND)) {
            String str4 = key;
            return PushConstants.SOUND;
        } else if (key.equals(PushConstants.AWS_PINPOINT_PICTURE)) {
            newExtras.putString(PushConstants.STYLE, "picture");
            String str5 = key;
            return "picture";
        } else if (key.startsWith(PushConstants.GCM_NOTIFICATION)) {
            String str6 = key;
            return key.substring(PushConstants.GCM_NOTIFICATION.length() + 1, key.length());
        } else if (key.startsWith(PushConstants.GCM_N)) {
            String str7 = key;
            return key.substring(PushConstants.GCM_N.length() + 1, key.length());
        } else if (key.startsWith(PushConstants.UA_PREFIX)) {
            String key2 = key.substring(PushConstants.UA_PREFIX.length() + 1, key.length());
            String str8 = key2;
            return key2.toLowerCase();
        } else if (key.startsWith(PushConstants.AWS_PINPOINT_PREFIX)) {
            String str9 = key;
            return key.substring(PushConstants.AWS_PINPOINT_PREFIX.length() + 1, key.length());
        } else {
            return key;
        }
    }

    private Bundle normalizeExtras(Context context, Bundle extras, String messageKey, String titleKey) {
        Log.d(LOG_TAG, "normalize extras");
        Bundle newExtras = new Bundle();
        for (String key : extras.keySet()) {
            Log.d(LOG_TAG, "key = " + key);
            if (key.equals(PushConstants.PARSE_COM_DATA) || key.equals(PushConstants.MESSAGE) || key.equals(messageKey)) {
                Object json = extras.get(key);
                if (!(json instanceof String) || !((String) json).startsWith("{")) {
                    String newKey = normalizeKey(key, messageKey, titleKey, newExtras);
                    Log.d(LOG_TAG, "replace key " + key + " with " + newKey);
                    replaceKey(context, key, newKey, extras, newExtras);
                } else {
                    Log.d(LOG_TAG, "extracting nested message data from key = " + key);
                    try {
                        JSONObject data = new JSONObject((String) json);
                        if (data.has(PushConstants.ALERT) || data.has(PushConstants.MESSAGE) || data.has(PushConstants.BODY) || data.has(PushConstants.TITLE) || data.has(messageKey) || data.has(titleKey)) {
                            Iterator<String> jsonIter = data.keys();
                            while (jsonIter.hasNext()) {
                                String jsonKey = jsonIter.next();
                                Log.d(LOG_TAG, "key = data/" + jsonKey);
                                String value = data.getString(jsonKey);
                                String jsonKey2 = normalizeKey(jsonKey, messageKey, titleKey, newExtras);
                                newExtras.putString(jsonKey2, localizeKey(context, jsonKey2, value));
                            }
                        } else if (data.has(PushConstants.LOC_KEY) || data.has(PushConstants.LOC_DATA)) {
                            String newKey2 = normalizeKey(key, messageKey, titleKey, newExtras);
                            Log.d(LOG_TAG, "replace key " + key + " with " + newKey2);
                            replaceKey(context, key, newKey2, extras, newExtras);
                        }
                    } catch (JSONException e) {
                        Log.e(LOG_TAG, "normalizeExtras: JSON exception");
                    }
                }
            } else if (key.equals("notification")) {
                Bundle value2 = extras.getBundle(key);
                for (String notifkey : value2.keySet()) {
                    Log.d(LOG_TAG, "notifkey = " + notifkey);
                    String newKey3 = normalizeKey(notifkey, messageKey, titleKey, newExtras);
                    Log.d(LOG_TAG, "replace key " + notifkey + " with " + newKey3);
                    newExtras.putString(newKey3, localizeKey(context, newKey3, value2.getString(notifkey)));
                }
            } else {
                String newKey4 = normalizeKey(key, messageKey, titleKey, newExtras);
                Log.d(LOG_TAG, "replace key " + key + " with " + newKey4);
                replaceKey(context, key, newKey4, extras, newExtras);
            }
        }
        return newExtras;
    }

    private int extractBadgeCount(Bundle extras) {
        String msgcnt = extras.getString("count");
        if (msgcnt == null) {
            return -1;
        }
        try {
            return Integer.parseInt(msgcnt);
        } catch (NumberFormatException e) {
            Log.e(LOG_TAG, e.getLocalizedMessage(), e);
            return -1;
        }
    }

    private void showNotificationIfPossible(Context context, Bundle extras) {
        String message = extras.getString(PushConstants.MESSAGE);
        String title = extras.getString(PushConstants.TITLE);
        String contentAvailable = extras.getString(PushConstants.CONTENT_AVAILABLE);
        String forceStart = extras.getString(PushConstants.FORCE_START);
        int badgeCount = extractBadgeCount(extras);
        if (badgeCount >= 0) {
            Log.d(LOG_TAG, "count =[" + badgeCount + "]");
            PushPlugin.setApplicationIconBadgeNumber(context, badgeCount);
        }
        if (badgeCount == 0) {
            ((NotificationManager) getSystemService("notification")).cancelAll();
        }
        Log.d(LOG_TAG, "message =[" + message + "]");
        Log.d(LOG_TAG, "title =[" + title + "]");
        Log.d(LOG_TAG, "contentAvailable =[" + contentAvailable + "]");
        Log.d(LOG_TAG, "forceStart =[" + forceStart + "]");
        if (!((message == null || message.length() == 0) && (title == null || title.length() == 0))) {
            Log.d(LOG_TAG, "create notification");
            if (title == null || title.isEmpty()) {
                extras.putString(PushConstants.TITLE, getAppName(this));
            }
            createNotification(context, extras);
        }
        if (!PushPlugin.isActive() && "1".equals(forceStart)) {
            Log.d(LOG_TAG, "app is not running but we should start it and put in background");
            Intent intent = new Intent(this, PushHandlerActivity.class);
            intent.addFlags(268435456);
            intent.putExtra(PushConstants.PUSH_BUNDLE, extras);
            intent.putExtra(PushConstants.START_IN_BACKGROUND, true);
            intent.putExtra(PushConstants.FOREGROUND, false);
            startActivity(intent);
        } else if ("1".equals(contentAvailable)) {
            Log.d(LOG_TAG, "app is not running and content available true");
            Log.d(LOG_TAG, "send notification event");
            PushPlugin.sendExtras(extras);
        }
    }

    public void createNotification(Context context, Bundle extras) {
        NotificationCompat.Builder mBuilder;
        String channelID;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService("notification");
        String appName = getAppName(this);
        String packageName = context.getPackageName();
        Resources resources = context.getResources();
        int notId = parseInt(PushConstants.NOT_ID, extras);
        Intent intent = new Intent(this, PushHandlerActivity.class);
        intent.addFlags(603979776);
        intent.putExtra(PushConstants.PUSH_BUNDLE, extras);
        intent.putExtra(PushConstants.NOT_ID, notId);
        SecureRandom random = new SecureRandom();
        PendingIntent contentIntent = PendingIntent.getActivity(this, random.nextInt(), intent, 134217728);
        Intent intent2 = new Intent(this, PushDismissedHandler.class);
        intent2.putExtra(PushConstants.PUSH_BUNDLE, extras);
        intent2.putExtra(PushConstants.NOT_ID, notId);
        intent2.putExtra(PushConstants.DISMISSED, true);
        intent2.setAction(PushConstants.PUSH_DISMISSED);
        PendingIntent deleteIntent = PendingIntent.getBroadcast(this, random.nextInt(), intent2, 268435456);
        if (Build.VERSION.SDK_INT >= 26) {
            String channelID2 = extras.getString(PushConstants.ANDROID_CHANNEL_ID);
            if (channelID2 != null) {
                mBuilder = new NotificationCompat.Builder(context, channelID2);
            } else {
                List<NotificationChannel> channels = mNotificationManager.getNotificationChannels();
                if (channels.size() == 1) {
                    channelID = channels.get(0).getId();
                } else {
                    channelID = extras.getString(PushConstants.ANDROID_CHANNEL_ID, PushConstants.DEFAULT_CHANNEL_ID);
                }
                Log.d(LOG_TAG, "Using channel ID = " + channelID);
                mBuilder = new NotificationCompat.Builder(context, channelID);
            }
        } else {
            mBuilder = new NotificationCompat.Builder(context);
        }
        mBuilder.setWhen(System.currentTimeMillis()).setContentTitle(fromHtml(extras.getString(PushConstants.TITLE))).setTicker(fromHtml(extras.getString(PushConstants.TITLE))).setContentIntent(contentIntent).setDeleteIntent(deleteIntent).setAutoCancel(true);
        SharedPreferences prefs = context.getSharedPreferences(PushConstants.COM_ADOBE_PHONEGAP_PUSH, 0);
        String localIcon = prefs.getString(PushConstants.ICON, (String) null);
        String localIconColor = prefs.getString(PushConstants.ICON_COLOR, (String) null);
        boolean soundOption = prefs.getBoolean(PushConstants.SOUND, true);
        boolean vibrateOption = prefs.getBoolean(PushConstants.VIBRATE, true);
        Log.d(LOG_TAG, "stored icon=" + localIcon);
        Log.d(LOG_TAG, "stored iconColor=" + localIconColor);
        Log.d(LOG_TAG, "stored sound=" + soundOption);
        Log.d(LOG_TAG, "stored vibrate=" + vibrateOption);
        setNotificationVibration(extras, Boolean.valueOf(vibrateOption), mBuilder);
        setNotificationIconColor(extras.getString(PushConstants.COLOR), mBuilder, localIconColor);
        setNotificationSmallIcon(context, extras, packageName, resources, mBuilder, localIcon);
        setNotificationLargeIcon(extras, packageName, resources, mBuilder);
        if (soundOption) {
            setNotificationSound(context, extras, mBuilder);
        }
        setNotificationLedColor(extras, mBuilder);
        setNotificationPriority(extras, mBuilder);
        setNotificationMessage(notId, extras, mBuilder);
        setNotificationCount(context, extras, mBuilder);
        setNotificationOngoing(extras, mBuilder);
        setVisibility(context, extras, mBuilder);
        createActions(extras, mBuilder, resources, packageName, notId);
        mNotificationManager.notify(appName, notId, mBuilder.build());
    }

    private void updateIntent(Intent intent, String callback, Bundle extras, boolean foreground, int notId) {
        intent.putExtra(PushConstants.CALLBACK, callback);
        intent.putExtra(PushConstants.PUSH_BUNDLE, extras);
        intent.putExtra(PushConstants.FOREGROUND, foreground);
        intent.putExtra(PushConstants.NOT_ID, notId);
    }

    private void createActions(Bundle extras, NotificationCompat.Builder mBuilder, Resources resources, String packageName, int notId) {
        PendingIntent pIntent;
        Intent intent;
        Log.d(LOG_TAG, "create actions: with in-line");
        String actions = extras.getString(PushConstants.ACTIONS);
        if (actions != null) {
            try {
                JSONArray actionsArray = new JSONArray(actions);
                ArrayList<NotificationCompat.Action> wActions = new ArrayList<>();
                for (int i = 0; i < actionsArray.length(); i++) {
                    int uniquePendingIntentRequestCode = new SecureRandom().nextInt(2000000000) + 1;
                    Log.d(LOG_TAG, "adding action");
                    JSONObject action = actionsArray.getJSONObject(i);
                    Log.d(LOG_TAG, "adding callback = " + action.getString(PushConstants.CALLBACK));
                    boolean foreground = action.optBoolean(PushConstants.FOREGROUND, true);
                    boolean inline = action.optBoolean("inline", false);
                    if (inline) {
                        Log.d(LOG_TAG, "Version: " + Build.VERSION.SDK_INT + " = " + 23);
                        if (Build.VERSION.SDK_INT <= 23) {
                            Log.d(LOG_TAG, "push activity");
                            intent = new Intent(this, PushHandlerActivity.class);
                        } else {
                            Log.d(LOG_TAG, "push receiver");
                            intent = new Intent(this, BackgroundActionButtonHandler.class);
                        }
                        updateIntent(intent, action.getString(PushConstants.CALLBACK), extras, foreground, notId);
                        if (Build.VERSION.SDK_INT <= 23) {
                            Log.d(LOG_TAG, "push activity for notId " + notId);
                            pIntent = PendingIntent.getActivity(this, uniquePendingIntentRequestCode, intent, 1073741824);
                        } else {
                            Log.d(LOG_TAG, "push receiver for notId " + notId);
                            pIntent = PendingIntent.getBroadcast(this, uniquePendingIntentRequestCode, intent, 1073741824);
                        }
                    } else if (foreground) {
                        Intent intent2 = new Intent(this, PushHandlerActivity.class);
                        updateIntent(intent2, action.getString(PushConstants.CALLBACK), extras, foreground, notId);
                        pIntent = PendingIntent.getActivity(this, uniquePendingIntentRequestCode, intent2, 134217728);
                    } else {
                        Intent intent3 = new Intent(this, BackgroundActionButtonHandler.class);
                        updateIntent(intent3, action.getString(PushConstants.CALLBACK), extras, foreground, notId);
                        pIntent = PendingIntent.getBroadcast(this, uniquePendingIntentRequestCode, intent3, 134217728);
                    }
                    NotificationCompat.Action.Builder actionBuilder = new NotificationCompat.Action.Builder(getImageId(resources, action.optString(PushConstants.ICON, ""), packageName), action.getString(PushConstants.TITLE), pIntent);
                    if (inline) {
                        Log.d(LOG_TAG, "create remote input");
                        actionBuilder.addRemoteInput(new RemoteInput.Builder(PushConstants.INLINE_REPLY).setLabel(action.optString(PushConstants.INLINE_REPLY_LABEL, "Enter your reply here")).build());
                    }
                    NotificationCompat.Action wAction = actionBuilder.build();
                    wActions.add(actionBuilder.build());
                    if (inline) {
                        mBuilder.addAction(wAction);
                    } else {
                        NotificationCompat.Builder builder = mBuilder;
                        builder.addAction(getImageId(resources, action.optString(PushConstants.ICON, ""), packageName), action.getString(PushConstants.TITLE), pIntent);
                    }
                }
                mBuilder.extend(new NotificationCompat.WearableExtender().addActions(wActions));
                wActions.clear();
            } catch (JSONException e) {
            }
        }
    }

    private void setNotificationCount(Context context, Bundle extras, NotificationCompat.Builder mBuilder) {
        int count = extractBadgeCount(extras);
        if (count >= 0) {
            Log.d(LOG_TAG, "count =[" + count + "]");
            mBuilder.setNumber(count);
        }
    }

    private void setVisibility(Context context, Bundle extras, NotificationCompat.Builder mBuilder) {
        String visibilityStr = extras.getString(PushConstants.VISIBILITY);
        if (visibilityStr != null) {
            try {
                Integer visibility = Integer.valueOf(Integer.parseInt(visibilityStr));
                if (visibility.intValue() < -1 || visibility.intValue() > 1) {
                    Log.e(LOG_TAG, "Visibility parameter must be between -1 and 1");
                } else {
                    mBuilder.setVisibility(visibility.intValue());
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void setNotificationVibration(Bundle extras, Boolean vibrateOption, NotificationCompat.Builder mBuilder) {
        String vibrationPattern = extras.getString(PushConstants.VIBRATION_PATTERN);
        if (vibrationPattern != null) {
            String[] items = vibrationPattern.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
            long[] results = new long[items.length];
            for (int i = 0; i < items.length; i++) {
                try {
                    results[i] = Long.parseLong(items[i].trim());
                } catch (NumberFormatException e) {
                }
            }
            mBuilder.setVibrate(results);
        } else if (vibrateOption.booleanValue()) {
            mBuilder.setDefaults(2);
        }
    }

    private void setNotificationOngoing(Bundle extras, NotificationCompat.Builder mBuilder) {
        mBuilder.setOngoing(Boolean.parseBoolean(extras.getString(PushConstants.ONGOING, "false")));
    }

    private void setNotificationMessage(int notId, Bundle extras, NotificationCompat.Builder mBuilder) {
        String message = extras.getString(PushConstants.MESSAGE);
        String style = extras.getString(PushConstants.STYLE, PushConstants.STYLE_TEXT);
        if (PushConstants.STYLE_INBOX.equals(style)) {
            setNotification(notId, message);
            mBuilder.setContentText(fromHtml(message));
            ArrayList<String> messageList = messageMap.get(Integer.valueOf(notId));
            Integer sizeList = Integer.valueOf(messageList.size());
            if (sizeList.intValue() > 1) {
                String sizeListMessage = sizeList.toString();
                String stacking = sizeList + " more";
                if (extras.getString(PushConstants.SUMMARY_TEXT) != null) {
                    stacking = extras.getString(PushConstants.SUMMARY_TEXT).replace("%n%", sizeListMessage);
                }
                NotificationCompat.InboxStyle notificationInbox = new NotificationCompat.InboxStyle().setBigContentTitle(fromHtml(extras.getString(PushConstants.TITLE))).setSummaryText(fromHtml(stacking));
                for (int i = messageList.size() - 1; i >= 0; i--) {
                    notificationInbox.addLine(fromHtml(messageList.get(i)));
                }
                mBuilder.setStyle(notificationInbox);
                return;
            }
            NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
            if (message != null) {
                bigText.bigText(fromHtml(message));
                bigText.setBigContentTitle(fromHtml(extras.getString(PushConstants.TITLE)));
                mBuilder.setStyle(bigText);
            }
        } else if ("picture".equals(style)) {
            setNotification(notId, "");
            NotificationCompat.BigPictureStyle bigPicture = new NotificationCompat.BigPictureStyle();
            bigPicture.bigPicture(getBitmapFromURL(extras.getString("picture")));
            bigPicture.setBigContentTitle(fromHtml(extras.getString(PushConstants.TITLE)));
            bigPicture.setSummaryText(fromHtml(extras.getString(PushConstants.SUMMARY_TEXT)));
            mBuilder.setContentTitle(fromHtml(extras.getString(PushConstants.TITLE)));
            mBuilder.setContentText(fromHtml(message));
            mBuilder.setStyle(bigPicture);
        } else {
            setNotification(notId, "");
            NotificationCompat.BigTextStyle bigText2 = new NotificationCompat.BigTextStyle();
            if (message != null) {
                mBuilder.setContentText(fromHtml(message));
                bigText2.bigText(fromHtml(message));
                bigText2.setBigContentTitle(fromHtml(extras.getString(PushConstants.TITLE)));
                String summaryText = extras.getString(PushConstants.SUMMARY_TEXT);
                if (summaryText != null) {
                    bigText2.setSummaryText(fromHtml(summaryText));
                }
                mBuilder.setStyle(bigText2);
            }
        }
    }

    private void setNotificationSound(Context context, Bundle extras, NotificationCompat.Builder mBuilder) {
        String soundname = extras.getString(PushConstants.SOUNDNAME);
        if (soundname == null) {
            soundname = extras.getString(PushConstants.SOUND);
        }
        if (PushConstants.SOUND_RINGTONE.equals(soundname)) {
            mBuilder.setSound(Settings.System.DEFAULT_RINGTONE_URI);
        } else if (soundname == null || soundname.contentEquals(PushConstants.SOUND_DEFAULT)) {
            mBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        } else {
            Uri sound = Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + soundname);
            Log.d(LOG_TAG, sound.toString());
            mBuilder.setSound(sound);
        }
    }

    private void setNotificationLedColor(Bundle extras, NotificationCompat.Builder mBuilder) {
        String ledColor = extras.getString(PushConstants.LED_COLOR);
        if (ledColor != null) {
            String[] items = ledColor.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
            int[] results = new int[items.length];
            for (int i = 0; i < items.length; i++) {
                try {
                    results[i] = Integer.parseInt(items[i].trim());
                } catch (NumberFormatException e) {
                }
            }
            if (results.length == 4) {
                mBuilder.setLights(Color.argb(results[0], results[1], results[2], results[3]), 500, 500);
            } else {
                Log.e(LOG_TAG, "ledColor parameter must be an array of length == 4 (ARGB)");
            }
        }
    }

    private void setNotificationPriority(Bundle extras, NotificationCompat.Builder mBuilder) {
        String priorityStr = extras.getString(PushConstants.PRIORITY);
        if (priorityStr != null) {
            try {
                Integer priority = Integer.valueOf(Integer.parseInt(priorityStr));
                if (priority.intValue() < -2 || priority.intValue() > 2) {
                    Log.e(LOG_TAG, "Priority parameter must be between -2 and 2");
                } else {
                    mBuilder.setPriority(priority.intValue());
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        float radius;
        if (bitmap == null) {
            return null;
        }
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(SupportMenu.CATEGORY_MASK);
        float cx = (float) (bitmap.getWidth() / 2);
        float cy = (float) (bitmap.getHeight() / 2);
        if (cx < cy) {
            radius = cx;
        } else {
            radius = cy;
        }
        canvas.drawCircle(cx, cy, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();
        return output;
    }

    private void setNotificationLargeIcon(Bundle extras, String packageName, Resources resources, NotificationCompat.Builder mBuilder) {
        String gcmLargeIcon = extras.getString(PushConstants.IMAGE);
        String imageType = extras.getString(PushConstants.IMAGE_TYPE, PushConstants.IMAGE_TYPE_SQUARE);
        if (gcmLargeIcon != null && !"".equals(gcmLargeIcon)) {
            if (gcmLargeIcon.startsWith("http://") || gcmLargeIcon.startsWith("https://")) {
                Bitmap bitmap = getBitmapFromURL(gcmLargeIcon);
                if (PushConstants.IMAGE_TYPE_SQUARE.equalsIgnoreCase(imageType)) {
                    mBuilder.setLargeIcon(bitmap);
                } else {
                    mBuilder.setLargeIcon(getCircleBitmap(bitmap));
                }
                Log.d(LOG_TAG, "using remote large-icon from gcm");
                return;
            }
            try {
                Bitmap bitmap2 = BitmapFactory.decodeStream(getAssets().open(gcmLargeIcon));
                if (PushConstants.IMAGE_TYPE_SQUARE.equalsIgnoreCase(imageType)) {
                    mBuilder.setLargeIcon(bitmap2);
                } else {
                    mBuilder.setLargeIcon(getCircleBitmap(bitmap2));
                }
                Log.d(LOG_TAG, "using assets large-icon from gcm");
            } catch (IOException e) {
                int largeIconId = getImageId(resources, gcmLargeIcon, packageName);
                if (largeIconId != 0) {
                    mBuilder.setLargeIcon(BitmapFactory.decodeResource(resources, largeIconId));
                    Log.d(LOG_TAG, "using resources large-icon from gcm");
                    return;
                }
                Log.d(LOG_TAG, "Not setting large icon");
            }
        }
    }

    private int getImageId(Resources resources, String icon, String packageName) {
        int iconId = resources.getIdentifier(icon, PushConstants.DRAWABLE, packageName);
        if (iconId == 0) {
            return resources.getIdentifier(icon, "mipmap", packageName);
        }
        return iconId;
    }

    private void setNotificationSmallIcon(Context context, Bundle extras, String packageName, Resources resources, NotificationCompat.Builder mBuilder, String localIcon) {
        int iconId = 0;
        String icon = extras.getString(PushConstants.ICON);
        if (icon != null && !"".equals(icon)) {
            iconId = getImageId(resources, icon, packageName);
            Log.d(LOG_TAG, "using icon from plugin options");
        } else if (localIcon != null && !"".equals(localIcon)) {
            iconId = getImageId(resources, localIcon, packageName);
            Log.d(LOG_TAG, "using icon from plugin options");
        }
        if (iconId == 0) {
            Log.d(LOG_TAG, "no icon resource found - using application icon");
            iconId = context.getApplicationInfo().icon;
        }
        mBuilder.setSmallIcon(iconId);
    }

    private void setNotificationIconColor(String color, NotificationCompat.Builder mBuilder, String localIconColor) {
        int iconColor = 0;
        if (color != null && !"".equals(color)) {
            try {
                iconColor = Color.parseColor(color);
            } catch (IllegalArgumentException e) {
                Log.e(LOG_TAG, "couldn't parse color from android options");
            }
        } else if (localIconColor != null && !"".equals(localIconColor)) {
            try {
                iconColor = Color.parseColor(localIconColor);
            } catch (IllegalArgumentException e2) {
                Log.e(LOG_TAG, "couldn't parse color from android options");
            }
        }
        if (iconColor != 0) {
            mBuilder.setColor(iconColor);
        }
    }

    public Bitmap getBitmapFromURL(String strURL) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(strURL).openConnection();
            connection.setConnectTimeout(15000);
            connection.setDoInput(true);
            connection.connect();
            return BitmapFactory.decodeStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAppName(Context context) {
        return (String) context.getPackageManager().getApplicationLabel(context.getApplicationInfo());
    }

    private int parseInt(String value, Bundle extras) {
        try {
            return Integer.parseInt(extras.getString(value));
        } catch (NumberFormatException e) {
            Log.e(LOG_TAG, "Number format exception - Error parsing " + value + ": " + e.getMessage());
            return 0;
        } catch (Exception e2) {
            Log.e(LOG_TAG, "Number format exception - Error parsing " + value + ": " + e2.getMessage());
            return 0;
        }
    }

    private Spanned fromHtml(String source) {
        if (source != null) {
            return Html.fromHtml(source);
        }
        return null;
    }

    private boolean isAvailableSender(String from) {
        String savedSenderID = getApplicationContext().getSharedPreferences(PushConstants.COM_ADOBE_PHONEGAP_PUSH, 0).getString(PushConstants.SENDER_ID, "");
        Log.d(LOG_TAG, "sender id = " + savedSenderID);
        if (from.equals(savedSenderID) || from.startsWith("/topics/")) {
            return true;
        }
        return false;
    }
}
