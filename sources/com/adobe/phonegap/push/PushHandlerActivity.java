package com.adobe.phonegap.push;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.util.Log;

public class PushHandlerActivity extends Activity implements PushConstants {
    private static String LOG_TAG = "Push_HandlerActivity";

    public void onCreate(Bundle savedInstanceState) {
        FCMService gcm = new FCMService();
        Intent intent = getIntent();
        int notId = intent.getExtras().getInt(PushConstants.NOT_ID, 0);
        Log.d(LOG_TAG, "not id = " + notId);
        gcm.setNotification(notId, "");
        super.onCreate(savedInstanceState);
        Log.v(LOG_TAG, "onCreate");
        Log.d(LOG_TAG, "callback = " + getIntent().getExtras().getString(PushConstants.CALLBACK));
        boolean foreground = getIntent().getExtras().getBoolean(PushConstants.FOREGROUND, true);
        boolean startOnBackground = getIntent().getExtras().getBoolean(PushConstants.START_IN_BACKGROUND, false);
        boolean dismissed = getIntent().getExtras().getBoolean(PushConstants.DISMISSED, false);
        Log.d(LOG_TAG, "dismissed = " + dismissed);
        if (!startOnBackground) {
            ((NotificationManager) getSystemService("notification")).cancel(FCMService.getAppName(this), notId);
        }
        boolean isPushPluginActive = PushPlugin.isActive();
        boolean inline = processPushBundle(isPushPluginActive, intent);
        if (inline && Build.VERSION.SDK_INT < 24 && !startOnBackground) {
            foreground = true;
        }
        Log.d(LOG_TAG, "bringToForeground = " + foreground);
        finish();
        if (!dismissed) {
            Log.d(LOG_TAG, "isPushPluginActive = " + isPushPluginActive);
            if (!isPushPluginActive && foreground && inline) {
                Log.d(LOG_TAG, "forceMainActivityReload");
                forceMainActivityReload(false);
            } else if (startOnBackground) {
                Log.d(LOG_TAG, "startOnBackgroundTrue");
                forceMainActivityReload(true);
            } else {
                Log.d(LOG_TAG, "don't want main activity");
            }
        }
    }

    private boolean processPushBundle(boolean isPushPluginActive, Intent intent) {
        boolean z;
        Bundle extras = getIntent().getExtras();
        Bundle remoteInput = null;
        if (extras != null) {
            Bundle originalExtras = extras.getBundle(PushConstants.PUSH_BUNDLE);
            originalExtras.putBoolean(PushConstants.FOREGROUND, false);
            if (!isPushPluginActive) {
                z = true;
            } else {
                z = false;
            }
            originalExtras.putBoolean(PushConstants.COLDSTART, z);
            originalExtras.putBoolean(PushConstants.DISMISSED, extras.getBoolean(PushConstants.DISMISSED));
            originalExtras.putString(PushConstants.ACTION_CALLBACK, extras.getString(PushConstants.CALLBACK));
            originalExtras.remove(PushConstants.NO_CACHE);
            remoteInput = RemoteInput.getResultsFromIntent(intent);
            if (remoteInput != null) {
                String inputString = remoteInput.getCharSequence(PushConstants.INLINE_REPLY).toString();
                Log.d(LOG_TAG, "response: " + inputString);
                originalExtras.putString(PushConstants.INLINE_REPLY, inputString);
            }
            PushPlugin.sendExtras(originalExtras);
        }
        if (remoteInput == null) {
            return true;
        }
        return false;
    }

    private void forceMainActivityReload(boolean startOnBackground) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(getApplicationContext().getPackageName());
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle originalExtras = extras.getBundle(PushConstants.PUSH_BUNDLE);
            if (originalExtras != null) {
                launchIntent.putExtras(originalExtras);
            }
            launchIntent.addFlags(67108864);
            launchIntent.addFlags(4);
            launchIntent.putExtra(PushConstants.START_IN_BACKGROUND, startOnBackground);
        }
        startActivity(launchIntent);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        ((NotificationManager) getSystemService("notification")).cancelAll();
    }
}
