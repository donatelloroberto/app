package com.google.firebase.components;

import android.support.annotation.VisibleForTesting;
import com.google.firebase.inject.Provider;

/* compiled from: com.google.firebase:firebase-common@@16.1.0 */
class Lazy<T> implements Provider<T> {
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider<T> provider;

    Lazy(T instance2) {
        this.instance = instance2;
    }

    Lazy(Provider<T> provider2) {
        this.provider = provider2;
    }

    public T get() {
        Object result = this.instance;
        if (result == UNINITIALIZED) {
            synchronized (this) {
                result = this.instance;
                if (result == UNINITIALIZED) {
                    result = this.provider.get();
                    this.instance = result;
                    this.provider = null;
                }
            }
        }
        return result;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean isInitialized() {
        return this.instance != UNINITIALIZED;
    }
}
