package com.amazon.device.ads;

class GooglePlayServices {
    private static final String GPS_AVAILABLE_SETTING = "gps-available";
    private static final String LOGTAG = GooglePlayServices.class.getSimpleName();
    private final MobileAdsLogger logger;
    private final ReflectionUtils reflectionUtils;

    public GooglePlayServices() {
        this(new MobileAdsLoggerFactory(), new ReflectionUtils());
    }

    GooglePlayServices(MobileAdsLoggerFactory loggerFactory, ReflectionUtils reflectionUtils2) {
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        this.reflectionUtils = reflectionUtils2;
    }

    public AdvertisingInfo getAdvertisingIdentifierInfo() {
        if (!isGPSAvailable()) {
            this.logger.v("The Google Play Services Advertising Identifier feature is not available.");
            return AdvertisingInfo.createNotAvailable();
        } else if (isGPSAvailableSet() || this.reflectionUtils.isClassAvailable("com.google.android.gms.ads.identifier.AdvertisingIdClient")) {
            AdvertisingInfo advertisingInfo = createGooglePlayServicesAdapter().getAdvertisingIdentifierInfo();
            setGooglePlayServicesAvailable(advertisingInfo.isGPSAvailable());
            return advertisingInfo;
        } else {
            this.logger.v("The Google Play Services Advertising Identifier feature is not available.");
            setGooglePlayServicesAvailable(false);
            return AdvertisingInfo.createNotAvailable();
        }
    }

    /* access modifiers changed from: protected */
    public GooglePlayServicesAdapter createGooglePlayServicesAdapter() {
        return new GooglePlayServicesAdapter();
    }

    private boolean isGPSAvailable() {
        return Settings.getInstance().getBoolean(GPS_AVAILABLE_SETTING, true);
    }

    private boolean isGPSAvailableSet() {
        return Settings.getInstance().containsKey(GPS_AVAILABLE_SETTING);
    }

    private void setGooglePlayServicesAvailable(boolean available) {
        Settings.getInstance().putTransientBoolean(GPS_AVAILABLE_SETTING, available);
    }

    static class AdvertisingInfo {
        private String advertisingIdentifier;
        private boolean gpsAvailable = true;
        private boolean limitAdTrackingEnabled;

        protected AdvertisingInfo() {
        }

        static AdvertisingInfo createNotAvailable() {
            return new AdvertisingInfo().setGPSAvailable(false);
        }

        /* access modifiers changed from: package-private */
        public boolean isGPSAvailable() {
            return this.gpsAvailable;
        }

        private AdvertisingInfo setGPSAvailable(boolean gpsAvailable2) {
            this.gpsAvailable = gpsAvailable2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public String getAdvertisingIdentifier() {
            return this.advertisingIdentifier;
        }

        /* access modifiers changed from: package-private */
        public AdvertisingInfo setAdvertisingIdentifier(String advertisingIdentifier2) {
            this.advertisingIdentifier = advertisingIdentifier2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public boolean hasAdvertisingIdentifier() {
            return getAdvertisingIdentifier() != null;
        }

        /* access modifiers changed from: package-private */
        public boolean isLimitAdTrackingEnabled() {
            return this.limitAdTrackingEnabled;
        }

        /* access modifiers changed from: package-private */
        public AdvertisingInfo setLimitAdTrackingEnabled(boolean limitAdTrackingEnabled2) {
            this.limitAdTrackingEnabled = limitAdTrackingEnabled2;
            return this;
        }
    }
}
