package com.amazon.cptplugins.exceptions.jsonutils;

public class AmazonError {
    private final String error;

    public AmazonError(Exception e) {
        this.error = e.toString();
    }

    public String getError() {
        return this.error;
    }
}
