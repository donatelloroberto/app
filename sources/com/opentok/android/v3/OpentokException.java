package com.opentok.android.v3;

import android.support.annotation.Keep;

@Keep
public class OpentokException extends Exception {
    private int errorCode;
    private Exception exception;

    public OpentokException(int errorCode2, String message) {
        super(message);
        this.errorCode = errorCode2;
        this.exception = null;
    }

    public OpentokException(int errorCode2, String message, Exception e) {
        super(message);
        this.errorCode = errorCode2;
        this.exception = e;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public Exception getException() {
        return this.exception;
    }
}
