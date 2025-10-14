package org.webrtc;

import android.content.Context;

public class ContextUtils {
    private static final String TAG = "ContextUtils";
    private static Context applicationContext;

    public static void initialize(Context applicationContext2) {
        if (applicationContext != null) {
            Logging.e(TAG, "Calling ContextUtils.initialize multiple times, this will crash in the future!");
        }
        if (applicationContext2 == null) {
            throw new RuntimeException("Application context cannot be null for ContextUtils.initialize.");
        }
        applicationContext = applicationContext2;
    }

    public static Context getApplicationContext() {
        return applicationContext;
    }
}
