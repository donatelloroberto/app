package com.amazon.cptplugins.concurrent;

import com.amazon.cptplugins.exceptions.jsonutils.AmazonError;
import com.google.gson.Gson;
import java.util.concurrent.Future;

public class Callback<T> implements Runnable {
    private static final Gson GSON = new Gson();
    private final CallbackHandler delegator;
    private final Future<T> future;
    private final String uuid;

    private interface CallbackResult<T> {
    }

    private static class CallbackResultSuccess<T> implements CallbackResult<T> {
        private final String callerId;
        private final T response;

        public CallbackResultSuccess(String uuid, T response2) {
            this.callerId = uuid;
            this.response = response2;
        }
    }

    private static class CallbackResultError implements CallbackResult<AmazonError> {
        private final String callerId;
        private final AmazonError response;

        public CallbackResultError(String uuid, AmazonError response2) {
            this.callerId = uuid;
            this.response = response2;
        }
    }

    public Callback(Future<T> future2, String uuid2, CallbackHandler delegator2) {
        this.future = future2;
        this.uuid = uuid2;
        this.delegator = delegator2;
    }

    public void run() {
        try {
            this.delegator.handleSdkCallback(GSON.toJson((Object) new CallbackResultSuccess(this.uuid, this.future.get())));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e2) {
            this.delegator.handleSdkCallback(GSON.toJson((Object) new CallbackResultError(this.uuid, new AmazonError(e2))));
        }
    }
}
