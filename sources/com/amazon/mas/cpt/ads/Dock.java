package com.amazon.mas.cpt.ads;

public enum Dock {
    TOP("TOP"),
    BOTTOM("BOTTOM");
    
    private final String value;

    private Dock(String value2) {
        this.value = value2;
    }

    public String getValue() {
        return this.value;
    }
}
