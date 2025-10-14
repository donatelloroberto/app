package com.myvidster;

import android.os.Bundle;
import com.adobe.phonegap.push.PushConstants;
import org.apache.cordova.CordovaActivity;

public class MainActivity extends CordovaActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean(PushConstants.START_IN_BACKGROUND, false)) {
            moveTaskToBack(true);
        }
        loadUrl(this.launchUrl);
    }
}
