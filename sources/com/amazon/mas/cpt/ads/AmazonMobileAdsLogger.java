package com.amazon.mas.cpt.ads;

import android.util.Log;

public class AmazonMobileAdsLogger {
    private static final String LOGTAG_PREFIX = "AmazonMobileAdsCPTPlugin ";

    public void e(String logtag, String message) {
        Log.e(LOGTAG_PREFIX + logtag, message);
    }

    public void d(String logtag, String message) {
        Log.d(LOGTAG_PREFIX + logtag, message);
    }
}
