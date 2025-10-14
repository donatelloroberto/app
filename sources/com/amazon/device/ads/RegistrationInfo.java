package com.amazon.device.ads;

import com.amazon.device.ads.AdvertisingIdentifier;

class RegistrationInfo {
    private static final String ADID_ORIGIN_PREF_NAME = "amzn-ad-id-origin";
    private static final String ADID_PREF_NAME = "amzn-ad-id";
    private static final String NEW_SIS_DID_REQUESTED_SETTING = "newSISDIDRequested";
    private static final String NON_ADVERTISING_IDENTIFIER_ORIGIN = "non-advertising-identifier";
    private static final String THIRD_PARTY_APP_NAME = "app";
    private String appKey;
    private String appName = THIRD_PARTY_APP_NAME;

    public String getAppName() {
        return this.appName;
    }

    public void putAppName(String appName2) {
        this.appName = WebUtils.getURLEncodedString(appName2);
    }

    public String getAdId() {
        return DebugProperties.getInstance().getDebugPropertyAsString(DebugProperties.DEBUG_ADID, Settings.getInstance().getString(ADID_PREF_NAME, (String) null));
    }

    public boolean isAdIdCurrent(AdvertisingIdentifier.Info advertisingIdentifierInfo) {
        boolean isAdIdOriginatedFromNonAdvertisingIdentifier = isAdIdOriginatedFromNonAdvertisingIdentifier();
        if (!advertisingIdentifierInfo.hasAdvertisingIdentifier()) {
            return isAdIdOriginatedFromNonAdvertisingIdentifier;
        }
        if (isAdIdOriginatedFromNonAdvertisingIdentifier) {
            return false;
        }
        return advertisingIdentifierInfo.getAdvertisingIdentifier().equals(Settings.getInstance().getString(ADID_ORIGIN_PREF_NAME, (String) null));
    }

    public boolean hasAdId() {
        return !StringUtils.isNullOrEmpty(getAdId());
    }

    public void putAdId(String adId, AdvertisingIdentifier.Info advertisingIdentifierInfo) {
        Settings settings = Settings.getInstance();
        settings.putStringWithNoFlush(ADID_PREF_NAME, adId);
        setOrigin(advertisingIdentifierInfo);
        settings.putBooleanWithNoFlush(NEW_SIS_DID_REQUESTED_SETTING, false);
        settings.flush();
    }

    protected static void setOrigin(AdvertisingIdentifier.Info advertisingIdentifierInfo) {
        if (advertisingIdentifierInfo.hasAdvertisingIdentifier()) {
            Settings.getInstance().putStringWithNoFlush(ADID_ORIGIN_PREF_NAME, advertisingIdentifierInfo.getAdvertisingIdentifier());
        } else {
            Settings.getInstance().putStringWithNoFlush(ADID_ORIGIN_PREF_NAME, NON_ADVERTISING_IDENTIFIER_ORIGIN);
        }
    }

    public static boolean isAdIdOriginatedFromNonAdvertisingIdentifier() {
        String storedOrigin = Settings.getInstance().getString(ADID_ORIGIN_PREF_NAME, (String) null);
        return storedOrigin == null || NON_ADVERTISING_IDENTIFIER_ORIGIN.equals(storedOrigin);
    }

    public String getAppKey() {
        return DebugProperties.getInstance().getDebugPropertyAsString(DebugProperties.DEBUG_APPID, this.appKey);
    }

    public void putAppKey(String appKey2) {
        if (appKey2 == null || appKey2.length() == 0) {
            throw new IllegalArgumentException("Application Key must not be null or empty.");
        }
        this.appKey = WebUtils.getURLEncodedString(appKey2);
    }

    public void requestNewSISDeviceIdentifier() {
        Settings.getInstance().putBoolean(NEW_SIS_DID_REQUESTED_SETTING, true);
    }

    public boolean shouldGetNewSISDeviceIdentifer() {
        return Settings.getInstance().getBoolean(NEW_SIS_DID_REQUESTED_SETTING, false);
    }

    public boolean isRegisteredWithSIS() {
        return hasAdId();
    }

    public boolean shouldGetNewSISRegistration() {
        return !isRegisteredWithSIS();
    }
}
