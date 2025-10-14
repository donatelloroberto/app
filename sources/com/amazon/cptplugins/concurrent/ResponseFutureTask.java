package com.amazon.cptplugins.concurrent;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ResponseFutureTask<T> implements Future<T> {
    private final String requestId;
    private final ConcurrentMap<String, SynchronousQueue<T>> responseQueue;

    public ResponseFutureTask(String requestId2, ConcurrentMap<String, SynchronousQueue<T>> responseQueue2) {
        this.requestId = requestId2;
        this.responseQueue = responseQueue2;
    }

    public boolean isDone() {
        return !this.responseQueue.containsKey(this.requestId);
    }

    public T get() throws InterruptedException, ExecutionException {
        T result = ((SynchronousQueue) this.responseQueue.get(this.requestId)).take();
        this.responseQueue.remove(this.requestId);
        return result;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    public boolean isCancelled() {
        return false;
    }

    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        throw new UnsupportedOperationException();
    }
}
