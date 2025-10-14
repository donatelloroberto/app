package com.adobe.phonegap.push;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.util.Log;

public class BackgroundActionButtonHandler extends BroadcastReceiver implements PushConstants {
    private static String LOG_TAG = "Push_BGActionButton";

    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        Log.d(LOG_TAG, "BackgroundActionButtonHandler = " + extras);
        int notId = intent.getIntExtra(PushConstants.NOT_ID, 0);
        Log.d(LOG_TAG, "not id = " + notId);
        ((NotificationManager) context.getSystemService("notification")).cancel(FCMService.getAppName(context), notId);
        if (extras != null) {
            Bundle originalExtras = extras.getBundle(PushConstants.PUSH_BUNDLE);
            originalExtras.putBoolean(PushConstants.FOREGROUND, false);
            originalExtras.putBoolean(PushConstants.COLDSTART, false);
            originalExtras.putString(PushConstants.ACTION_CALLBACK, extras.getString(PushConstants.CALLBACK));
            Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
            if (remoteInput != null) {
                String inputString = remoteInput.getCharSequence(PushConstants.INLINE_REPLY).toString();
                Log.d(LOG_TAG, "response: " + inputString);
                originalExtras.putString(PushConstants.INLINE_REPLY, inputString);
            }
            PushPlugin.sendExtras(originalExtras);
        }
    }
}
