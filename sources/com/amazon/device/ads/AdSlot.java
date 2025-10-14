package com.amazon.device.ads;

class AdSlot {
    private final AdController adController;
    private AdError adError;
    private final AdTargetingOptions adOptions;
    private boolean deferredLoad = false;
    private int slotNumber;

    AdSlot(AdController adController2, AdTargetingOptions adOptions2) {
        this.adController = adController2;
        if (adOptions2 == null) {
            this.adOptions = new AdTargetingOptions();
        } else {
            this.adOptions = adOptions2;
        }
    }

    public AdSlot setDeferredLoad(boolean deferredLoad2) {
        this.deferredLoad = deferredLoad2;
        return this;
    }

    public AdTargetingOptions getAdTargetingOptions() {
        return this.adOptions;
    }

    public AdSize getRequestedAdSize() {
        return this.adController.getAdSize();
    }

    /* access modifiers changed from: package-private */
    public void setSlotNumber(int slotNumber2) {
        this.slotNumber = slotNumber2;
    }

    /* access modifiers changed from: package-private */
    public int getSlotNumber() {
        return this.slotNumber;
    }

    /* access modifiers changed from: package-private */
    public void setAdError(AdError adError2) {
        this.adError = adError2;
    }

    /* access modifiers changed from: package-private */
    public AdError getAdError() {
        return this.adError;
    }

    /* access modifiers changed from: package-private */
    public String getMaxSize() {
        return this.adController.getMaxSize();
    }

    /* access modifiers changed from: package-private */
    public MetricsCollector getMetricsCollector() {
        return this.adController.getMetricsCollector();
    }

    /* access modifiers changed from: package-private */
    public void setAdData(AdData adData) {
        this.adController.setAdData(adData);
    }

    /* access modifiers changed from: package-private */
    public boolean isFetched() {
        return this.adController.getAdData() != null && this.adController.getAdData().getIsFetched();
    }

    /* access modifiers changed from: package-private */
    public void adFailed(AdError adError2) {
        this.adController.adFailed(adError2);
    }

    /* access modifiers changed from: package-private */
    public void initializeAd() {
        this.adController.initialize();
    }

    /* access modifiers changed from: package-private */
    public boolean prepareForAdLoad(long loadAdStartTime) {
        return this.adController.prepareForAdLoad(loadAdStartTime, this.deferredLoad);
    }

    /* access modifiers changed from: package-private */
    public boolean isValid() {
        return this.adController.isValid();
    }

    /* access modifiers changed from: package-private */
    public void setConnectionInfo(ConnectionInfo connectionInfo) {
        this.adController.setConnectionInfo(connectionInfo);
    }

    /* access modifiers changed from: package-private */
    public boolean canBeUsed() {
        return this.adController.canBeUsed();
    }
}
