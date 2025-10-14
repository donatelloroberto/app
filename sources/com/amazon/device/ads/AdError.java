package com.amazon.device.ads;

public class AdError {
    private final ErrorCode code;
    private final String message;

    public enum ErrorCode {
        NETWORK_ERROR,
        NETWORK_TIMEOUT,
        NO_FILL,
        INTERNAL_ERROR,
        REQUEST_ERROR
    }

    AdError(ErrorCode code2, String msg) {
        this.code = code2;
        this.message = msg;
    }

    public ErrorCode getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
