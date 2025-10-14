package com.adobe.phonegap.push;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class PushInstanceIDListenerService extends FirebaseInstanceIdService implements PushConstants {
    public static final String LOG_TAG = "Push_InsIdService";

    public void onTokenRefresh() {
        Log.d(LOG_TAG, "Refreshed token: " + FirebaseInstanceId.getInstance().getToken());
    }
}
