package com.amazon.device.ads;

class AdEvent {
    public static final String POSITION_ON_SCREEN = "positionOnScreen";
    private final AdEventType adEventType;
    private String customType;
    private final ParameterMap parameters = new ParameterMap();

    public enum AdEventType {
        EXPANDED,
        CLOSED,
        CLICKED,
        RESIZED,
        OTHER
    }

    public AdEvent(AdEventType adEventType2) {
        this.adEventType = adEventType2;
    }

    /* access modifiers changed from: package-private */
    public AdEventType getAdEventType() {
        return this.adEventType;
    }

    /* access modifiers changed from: package-private */
    public void setCustomType(String customType2) {
        this.customType = customType2;
    }

    public String getCustomType() {
        return this.customType;
    }

    /* access modifiers changed from: package-private */
    public AdEvent setParameter(String key, Object value) {
        this.parameters.setParameter(key, value);
        return this;
    }

    public ParameterMap getParameters() {
        return this.parameters;
    }
}
