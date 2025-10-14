package com.amazon.device.ads;

import android.graphics.Rect;
import com.amazon.device.ads.ThreadUtils;

class AdListenerExecutor {
    private static final String LOGTAG = AdListenerExecutor.class.getSimpleName();
    private final AdListener adListener;
    private final MobileAdsLogger logger;
    private final ThreadUtils.ThreadRunner threadRunner;

    public AdListenerExecutor(AdListener adListener2, MobileAdsLoggerFactory loggerFactory) {
        this(adListener2, ThreadUtils.getThreadRunner(), loggerFactory);
    }

    AdListenerExecutor(AdListener adListener2, ThreadUtils.ThreadRunner threadRunner2, MobileAdsLoggerFactory loggerFactory) {
        this.adListener = adListener2;
        this.threadRunner = threadRunner2;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
    }

    /* access modifiers changed from: protected */
    public AdListener getAdListener() {
        return this.adListener;
    }

    public void onAdLoaded(final Ad ad, final AdProperties adProperties) {
        execute(new Runnable() {
            public void run() {
                AdListenerExecutor.this.getAdListener().onAdLoaded(ad, adProperties);
            }
        });
    }

    public void onAdFailedToLoad(final Ad ad, final AdError adError) {
        execute(new Runnable() {
            public void run() {
                AdListenerExecutor.this.getAdListener().onAdFailedToLoad(ad, adError);
            }
        });
    }

    public void onAdExpanded(final Ad ad) {
        execute(new Runnable() {
            public void run() {
                AdListenerExecutor.this.getAdListener().onAdExpanded(ad);
            }
        });
    }

    public void onAdCollapsed(final Ad ad) {
        execute(new Runnable() {
            public void run() {
                AdListenerExecutor.this.getAdListener().onAdCollapsed(ad);
            }
        });
    }

    public void onAdDismissed(final Ad ad) {
        execute(new Runnable() {
            public void run() {
                AdListenerExecutor.this.getAdListener().onAdDismissed(ad);
            }
        });
    }

    public void onAdResized(Ad ad, Rect positionOnScreen) {
        this.logger.d("Ad listener called - Ad Resized.");
    }

    public void onAdExpired(Ad ad) {
        this.logger.d("Ad listener called - Ad Expired.");
    }

    public void onSpecialUrlClicked(Ad ad, String url) {
        this.logger.d("Ad listener called - Special Url Clicked.");
    }

    public ActionCode onAdReceived(Ad ad, AdData adData) {
        this.logger.d("Ad listener called - Ad Received.");
        return null;
    }

    public void onAdEvent(AdEvent adEvent) {
        this.logger.d("Ad listener called - Ad Event: " + adEvent);
    }

    /* access modifiers changed from: protected */
    public void execute(Runnable runnable) {
        this.threadRunner.execute(runnable, ThreadUtils.ExecutionStyle.SCHEDULE, ThreadUtils.ExecutionThread.MAIN_THREAD);
    }
}
