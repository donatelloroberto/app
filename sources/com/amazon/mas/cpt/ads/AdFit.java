package com.amazon.mas.cpt.ads;

public enum AdFit {
    FIT_SCREEN_WIDTH("FIT_SCREEN_WIDTH"),
    FIT_AD_SIZE("FIT_AD_SIZE");
    
    private final String value;

    private AdFit(String value2) {
        this.value = value2;
    }

    public String getValue() {
        return this.value;
    }
}
