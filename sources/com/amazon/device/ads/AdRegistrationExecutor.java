package com.amazon.device.ads;

import android.content.Context;

class AdRegistrationExecutor {
    private final MobileAdsInfoStore infoStore;
    private volatile boolean isInitialized;
    private final MobileAdsLogger logger;
    private final MobileAdsLoggerFactory loggerFactory;
    private final PermissionChecker permissionChecker;
    private final Settings settings;

    public AdRegistrationExecutor(String logtag) {
        this(logtag, MobileAdsInfoStore.getInstance(), Settings.getInstance(), new MobileAdsLoggerFactory(), new PermissionChecker());
    }

    AdRegistrationExecutor(String logtag, MobileAdsInfoStore infoStore2, Settings settings2, MobileAdsLoggerFactory loggerFactory2, PermissionChecker permissionChecker2) {
        this.isInitialized = false;
        this.infoStore = infoStore2;
        this.settings = settings2;
        this.loggerFactory = loggerFactory2;
        this.logger = this.loggerFactory.createMobileAdsLogger(logtag);
        this.permissionChecker = permissionChecker2;
    }

    public void setAppKey(String appKey) throws IllegalArgumentException {
        this.infoStore.getRegistrationInfo().putAppKey(appKey);
    }

    public void enableLogging(boolean enable) {
        this.logger.enableLoggingWithSetterNotification(enable);
    }

    public void enableTesting(boolean enable) {
        this.settings.putTransientBoolean("testingEnabled", enable);
        this.logger.logSetterNotification("Test mode", Boolean.valueOf(enable));
    }

    public String getVersion() {
        return Version.getSDKVersion();
    }

    public void registerApp(Context context) {
        if (!this.permissionChecker.hasInternetPermission(context)) {
            this.logger.e("Network task cannot commence because the INTERNET permission is missing from the app's manifest.");
            return;
        }
        initializeAds(context);
        this.infoStore.register();
    }

    public void initializeAds(Context context) {
        if (!this.isInitialized) {
            this.infoStore.contextReceived(context);
            this.infoStore.getDeviceInfo().setUserAgentManager(new AdUserAgentManager());
            this.isInitialized = true;
        }
    }

    /* access modifiers changed from: package-private */
    public MobileAdsInfoStore getMobileAdsInfoStore() {
        return this.infoStore;
    }

    /* access modifiers changed from: package-private */
    public Settings getSettings() {
        return this.settings;
    }

    /* access modifiers changed from: package-private */
    public PermissionChecker getPermissionChecker() {
        return this.permissionChecker;
    }

    /* access modifiers changed from: package-private */
    public MobileAdsLogger getLogger() {
        return this.logger;
    }

    /* access modifiers changed from: package-private */
    public MobileAdsLoggerFactory getLoggerFactory() {
        return this.loggerFactory;
    }
}
