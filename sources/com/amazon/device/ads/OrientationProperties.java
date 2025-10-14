package com.amazon.device.ads;

import java.util.Locale;

class OrientationProperties {
    private static final String FORMAT = "{\"allowOrientationChange\":%s,\"forceOrientation\":\"%s\"}";
    private Boolean allowOrientationChange = true;
    private ForceOrientation forceOrientation = ForceOrientation.NONE;

    OrientationProperties() {
    }

    public Boolean isAllowOrientationChange() {
        return this.allowOrientationChange;
    }

    public void setAllowOrientationChange(Boolean allowOrientationChange2) {
        this.allowOrientationChange = allowOrientationChange2;
    }

    public ForceOrientation getForceOrientation() {
        return this.forceOrientation;
    }

    public void setForceOrientation(ForceOrientation forceOrientation2) {
        this.forceOrientation = forceOrientation2;
    }

    public String toString() {
        return String.format(Locale.US, FORMAT, new Object[]{this.allowOrientationChange.toString(), this.forceOrientation.toString()});
    }
}
