package com.amazon.device.ads;

import com.amazon.device.ads.GooglePlayServices;

class AdvertisingIdentifier {
    private static final String GPS_ADVERTISING_IDENTIFIER_SETTING = "gpsAdId";
    private static final String LOGTAG = AdvertisingIdentifier.class.getSimpleName();
    private static final String TRANSITION_MIGRATE = "migrate";
    private static final String TRANSITION_RESET = "reset";
    private static final String TRANSITION_REVERT = "revert";
    private static final String TRANSITION_SETTING = "adIdTransistion";
    private final DebugProperties debugProperties;
    private GooglePlayServices.AdvertisingInfo gpsAdvertisingInfo;
    private final MobileAdsInfoStore infoStore;
    private final MobileAdsLogger logger;
    private final Settings settings;
    private boolean shouldSetCurrentAdvertisingIdentifier;

    public AdvertisingIdentifier() {
        this(Settings.getInstance(), MobileAdsInfoStore.getInstance(), new MobileAdsLoggerFactory(), DebugProperties.getInstance());
    }

    AdvertisingIdentifier(Settings settings2, MobileAdsInfoStore infoStore2, MobileAdsLoggerFactory loggerFactory, DebugProperties debugProperties2) {
        this.shouldSetCurrentAdvertisingIdentifier = true;
        this.settings = settings2;
        this.infoStore = infoStore2;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        this.debugProperties = debugProperties2;
    }

    /* access modifiers changed from: package-private */
    public String getAndClearTransition() {
        String transition = this.settings.getString(TRANSITION_SETTING, (String) null);
        this.settings.remove(TRANSITION_SETTING);
        return transition;
    }

    /* access modifiers changed from: package-private */
    public AdvertisingIdentifier setShouldSetCurrentAdvertisingIdentifier(boolean shouldSetCurrentAdvertisingIdentifier2) {
        this.shouldSetCurrentAdvertisingIdentifier = shouldSetCurrentAdvertisingIdentifier2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Info getAdvertisingIdentifierInfo() {
        if (ThreadUtils.isOnMainThread()) {
            this.logger.e("You must obtain the advertising indentifier information on a background thread.");
            return new Info(this.debugProperties).setCanDo(false);
        }
        fetchGooglePlayServicesAdvertisingIdentifierInfo();
        if (this.shouldSetCurrentAdvertisingIdentifier) {
            determineTransition();
        }
        Info info = new Info(this.debugProperties);
        if (getGPSAdvertisingInfo().hasAdvertisingIdentifier()) {
            Info unused = info.setAdvertisingIdentifier(getGPSAdvertisingInfo().getAdvertisingIdentifier());
            Info unused2 = info.setLimitAdTrackingEnabled(getGPSAdvertisingInfo().isLimitAdTrackingEnabled());
            if (this.shouldSetCurrentAdvertisingIdentifier) {
                setCurrentGPSAID(getGPSAdvertisingInfo().getAdvertisingIdentifier());
            }
        }
        RegistrationInfo registrationInfo = this.infoStore.getRegistrationInfo();
        if (registrationInfo.isAdIdCurrent(info)) {
            info.setSISDeviceIdentifier(registrationInfo.getAdId());
            return info;
        }
        registrationInfo.requestNewSISDeviceIdentifier();
        return info;
    }

    private void determineTransition() {
        String transition = null;
        if (isTransitionMigrated()) {
            transition = TRANSITION_MIGRATE;
        } else if (isTransitionReset()) {
            transition = TRANSITION_RESET;
        } else if (isTransitionReverted()) {
            transition = TRANSITION_REVERT;
        }
        if (transition != null) {
            setTransition(transition);
        } else {
            this.logger.d("No transition detected.");
        }
    }

    private void setTransition(String transition) {
        this.logger.d("Transition: %s", transition);
        this.settings.putString(TRANSITION_SETTING, transition);
    }

    /* access modifiers changed from: protected */
    public void fetchGooglePlayServicesAdvertisingIdentifierInfo() {
        this.gpsAdvertisingInfo = new GooglePlayServices().getAdvertisingIdentifierInfo();
    }

    /* access modifiers changed from: protected */
    public GooglePlayServices.AdvertisingInfo getGPSAdvertisingInfo() {
        return this.gpsAdvertisingInfo;
    }

    private boolean isTransitionMigrated() {
        return this.infoStore.getRegistrationInfo().hasAdId() && RegistrationInfo.isAdIdOriginatedFromNonAdvertisingIdentifier() && !hasCurrentGPSAID() && getGPSAdvertisingInfo().hasAdvertisingIdentifier();
    }

    private boolean isTransitionReset() {
        return hasCurrentGPSAID() && getGPSAdvertisingInfo().hasAdvertisingIdentifier() && !getCurrentGPSAID().equals(getGPSAdvertisingInfo().getAdvertisingIdentifier());
    }

    private boolean isTransitionReverted() {
        return hasCurrentGPSAID() && !getGPSAdvertisingInfo().hasAdvertisingIdentifier();
    }

    private void setCurrentGPSAID(String gpsaid) {
        this.settings.putString(GPS_ADVERTISING_IDENTIFIER_SETTING, gpsaid);
    }

    private String getCurrentGPSAID() {
        return this.settings.getString(GPS_ADVERTISING_IDENTIFIER_SETTING, "");
    }

    private boolean hasCurrentGPSAID() {
        return !StringUtils.isNullOrEmpty(getCurrentGPSAID());
    }

    static class Info {
        private String advertisingIdentifier;
        private boolean canDo;
        private final DebugProperties debugProperties;
        private boolean limitAdTrackingEnabled;
        private String sisDeviceIdentifier;

        private Info(DebugProperties debugProperties2) {
            this.debugProperties = debugProperties2;
            this.canDo = true;
        }

        /* access modifiers changed from: package-private */
        public boolean canDo() {
            return this.canDo;
        }

        /* access modifiers changed from: private */
        public Info setCanDo(boolean canDo2) {
            this.canDo = canDo2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public String getAdvertisingIdentifier() {
            return this.debugProperties.getDebugPropertyAsString(DebugProperties.DEBUG_IDFA, this.advertisingIdentifier);
        }

        /* access modifiers changed from: private */
        public Info setAdvertisingIdentifier(String advertisingIdentifier2) {
            this.advertisingIdentifier = advertisingIdentifier2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public boolean hasAdvertisingIdentifier() {
            return !StringUtils.isNullOrEmpty(getAdvertisingIdentifier());
        }

        /* access modifiers changed from: package-private */
        public boolean isLimitAdTrackingEnabled() {
            return this.debugProperties.getDebugPropertyAsBoolean(DebugProperties.DEBUG_OPT_OUT, Boolean.valueOf(this.limitAdTrackingEnabled)).booleanValue();
        }

        /* access modifiers changed from: private */
        public Info setLimitAdTrackingEnabled(boolean limitAdTrackingEnabled2) {
            this.limitAdTrackingEnabled = limitAdTrackingEnabled2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public String getSISDeviceIdentifier() {
            return this.debugProperties.getDebugPropertyAsString(DebugProperties.DEBUG_ADID, this.sisDeviceIdentifier);
        }

        /* access modifiers changed from: package-private */
        public boolean hasSISDeviceIdentifier() {
            return getSISDeviceIdentifier() != null;
        }

        /* access modifiers changed from: package-private */
        public Info setSISDeviceIdentifier(String sisDeviceIdentifier2) {
            this.sisDeviceIdentifier = sisDeviceIdentifier2;
            return this;
        }
    }
}
