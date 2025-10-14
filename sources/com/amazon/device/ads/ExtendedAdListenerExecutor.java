package com.amazon.device.ads;

import android.graphics.Rect;
import com.amazon.device.ads.ThreadUtils;

class ExtendedAdListenerExecutor extends AdListenerExecutor {
    private final ExtendedAdListener extendedAdListener;

    public ExtendedAdListenerExecutor(ExtendedAdListener adListener, MobileAdsLoggerFactory loggerFactory) {
        super(adListener, loggerFactory);
        this.extendedAdListener = adListener;
    }

    ExtendedAdListenerExecutor(ExtendedAdListener adListener, ThreadUtils.ThreadRunner threadRunner, MobileAdsLoggerFactory loggerFactory) {
        super(adListener, threadRunner, loggerFactory);
        this.extendedAdListener = adListener;
    }

    /* access modifiers changed from: protected */
    public ExtendedAdListener getAdListener() {
        return this.extendedAdListener;
    }

    public void onAdResized(final Ad ad, final Rect positionOnScreen) {
        execute(new Runnable() {
            public void run() {
                ExtendedAdListenerExecutor.this.getAdListener().onAdResized(ad, positionOnScreen);
            }
        });
    }

    public void onAdExpired(final Ad ad) {
        execute(new Runnable() {
            public void run() {
                ExtendedAdListenerExecutor.this.getAdListener().onAdExpired(ad);
            }
        });
    }
}
