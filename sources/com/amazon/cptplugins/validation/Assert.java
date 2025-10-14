package com.amazon.cptplugins.validation;

public final class Assert {
    private Assert() {
    }

    public static <T> void notNull(T t, String message) {
        if (t == null) {
            throw new IllegalStateException("Unexpected null object: " + message);
        }
    }
}
