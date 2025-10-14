package com.amazon.device.ads;

import android.content.Context;

class AdWebViewClientFactory {
    private final AndroidBuildInfo androidBuildInfo;
    private final MobileAdsLoggerFactory loggerFactory;
    private final WebUtils2 webUtils;

    public AdWebViewClientFactory(WebUtils2 webUtils2, MobileAdsLoggerFactory loggerFactory2, AndroidBuildInfo androidBuildInfo2) {
        this.webUtils = webUtils2;
        this.loggerFactory = loggerFactory2;
        this.androidBuildInfo = androidBuildInfo2;
    }

    public AdWebViewClient createAdWebViewClient(Context context, AdSDKBridgeList bridgeList, AdControlAccessor adControlAccessor) {
        return new AdWebViewClient(context, bridgeList, adControlAccessor, this.webUtils, this.loggerFactory, this.androidBuildInfo);
    }
}
