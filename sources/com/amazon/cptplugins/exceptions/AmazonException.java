package com.amazon.cptplugins.exceptions;

public class AmazonException extends RuntimeException {
    public AmazonException() {
    }

    public AmazonException(String s) {
        super(s);
    }

    public AmazonException(Throwable t) {
        super(t);
    }

    public AmazonException(String s, Throwable t) {
        super(s, t);
    }
}
