package com.amazon.mas.cpt.ads;

public enum AdType {
    FLOATING("FLOATING"),
    INTERSTITIAL("INTERSTITIAL");
    
    private final String value;

    private AdType(String value2) {
        this.value = value2;
    }

    public String getValue() {
        return this.value;
    }
}
