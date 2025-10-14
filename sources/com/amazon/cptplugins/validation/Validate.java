package com.amazon.cptplugins.validation;

public final class Validate {
    private Validate() {
    }

    public static <T> T notNull(T t, String message) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("Unexpected null value. " + message);
    }
}
