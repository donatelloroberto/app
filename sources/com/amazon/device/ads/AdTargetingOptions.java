package com.amazon.device.ads;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class AdTargetingOptions {
    private static final boolean DEFAULT_DISPLAY_ENABLED = true;
    private static final long DEFAULT_FLOOR_PRICE = 0;
    private static final boolean DEFAULT_GEOTARGETING_ENABLED = false;
    private static final String LOGTAG = AdTargetingOptions.class.getSimpleName();
    private final Map<String, String> advanced;
    private boolean displayEnabled;
    private boolean enableGeoTargeting;
    private long floorPrice;
    private final HashSet<String> internalPublisherKeywords;
    private final MobileAdsLogger logger;
    private boolean videoEnabled;
    private final boolean videoEnabledSettable;

    public AdTargetingOptions() {
        this(new AndroidBuildInfo(), new MobileAdsLoggerFactory());
    }

    AdTargetingOptions(AndroidBuildInfo androidBuildInfo, MobileAdsLoggerFactory loggerFactory) {
        this.floorPrice = 0;
        this.enableGeoTargeting = false;
        this.displayEnabled = DEFAULT_DISPLAY_ENABLED;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        this.advanced = new HashMap();
        this.videoEnabledSettable = isVideoEnabledSettable(androidBuildInfo);
        this.videoEnabled = this.videoEnabledSettable;
        this.internalPublisherKeywords = new HashSet<>();
    }

    /* access modifiers changed from: package-private */
    public AdTargetingOptions copy() {
        AdTargetingOptions copy = new AdTargetingOptions().enableGeoLocation(this.enableGeoTargeting).setFloorPrice(this.floorPrice).enableDisplayAds(this.displayEnabled);
        if (this.videoEnabledSettable) {
            copy.enableVideoAds(this.videoEnabled);
        }
        copy.advanced.putAll(this.advanced);
        copy.internalPublisherKeywords.addAll(this.internalPublisherKeywords);
        return copy;
    }

    /* access modifiers changed from: package-private */
    public HashMap<String, String> getCopyOfAdvancedOptions() {
        return new HashMap<>(this.advanced);
    }

    private static boolean isVideoEnabledSettable(AndroidBuildInfo androidBuildInfo) {
        return AndroidTargetUtils.isAtLeastAndroidAPI(androidBuildInfo, 14);
    }

    public AdTargetingOptions setFloorPrice(long floorPrice2) {
        this.floorPrice = floorPrice2;
        return this;
    }

    public long getFloorPrice() {
        return this.floorPrice;
    }

    /* access modifiers changed from: package-private */
    public boolean hasFloorPrice() {
        if (this.floorPrice > 0) {
            return DEFAULT_DISPLAY_ENABLED;
        }
        return false;
    }

    public boolean containsAdvancedOption(String key) {
        return this.advanced.containsKey(key);
    }

    public String getAdvancedOption(String key) {
        return this.advanced.get(key);
    }

    public AdTargetingOptions setAdvancedOption(String key, String value) {
        if (StringUtils.isNullOrWhiteSpace(key)) {
            throw new IllegalArgumentException("Option Key must not be null or empty string");
        }
        if (value != null) {
            this.advanced.put(key, value);
        } else {
            this.advanced.remove(key);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public AdTargetingOptions addInternalPublisherKeyword(String publisherKey) {
        if (!StringUtils.isNullOrWhiteSpace(publisherKey)) {
            this.internalPublisherKeywords.add(publisherKey);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public HashSet<String> getInternalPublisherKeywords() {
        return this.internalPublisherKeywords;
    }

    public AdTargetingOptions enableGeoLocation(boolean enable) {
        this.enableGeoTargeting = enable;
        return this;
    }

    public boolean isGeoLocationEnabled() {
        return this.enableGeoTargeting;
    }

    /* access modifiers changed from: package-private */
    public AdTargetingOptions enableDisplayAds(boolean enable) {
        this.displayEnabled = enable;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean isDisplayAdsEnabled() {
        return this.displayEnabled;
    }

    /* access modifiers changed from: package-private */
    public AdTargetingOptions enableVideoAds(boolean enable) {
        if (!this.videoEnabledSettable) {
            this.logger.w("Video is not allowed to be changed as this device does not support video.");
        } else {
            this.videoEnabled = enable;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean isVideoAdsEnabled() {
        return this.videoEnabled;
    }

    /* access modifiers changed from: package-private */
    public boolean isVideoEnabledSettable() {
        return this.videoEnabledSettable;
    }

    public AdTargetingOptions setAge(int age) {
        this.logger.d("setAge API has been deprecated.");
        return this;
    }

    public int getAge() {
        this.logger.d("getAge API has been deprecated.");
        return Integer.MIN_VALUE;
    }
}
