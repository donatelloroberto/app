package com.amazon.cptplugins.concurrent;

public class CallbackResult<T> {
    private final String callerId;
    private final T response;

    public CallbackResult(String uuid, T response2) {
        this.callerId = uuid;
        this.response = response2;
    }

    public String getCallerId() {
        return this.callerId;
    }

    public T getResponse() {
        return this.response;
    }
}
