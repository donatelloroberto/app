package com.amazon.mas.cpt.ads;

public enum HorizontalAlign {
    LEFT("LEFT"),
    CENTER("CENTER"),
    RIGHT("RIGHT");
    
    private final String value;

    private HorizontalAlign(String value2) {
        this.value = value2;
    }

    public String getValue() {
        return this.value;
    }
}
