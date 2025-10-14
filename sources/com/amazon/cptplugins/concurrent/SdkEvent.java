package com.amazon.cptplugins.concurrent;

public class SdkEvent<T> {
    private final String eventId;
    private final T response;

    public SdkEvent(String eventId2) {
        this.eventId = eventId2;
        this.response = null;
    }

    public SdkEvent(String eventId2, T response2) {
        this.eventId = eventId2;
        this.response = response2;
    }

    public String getEventId() {
        return this.eventId;
    }

    public T getResponse() {
        return this.response;
    }
}
