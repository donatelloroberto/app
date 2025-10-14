package com.amazon.device.ads;

import com.amazon.device.ads.SDKEvent;
import java.util.concurrent.atomic.AtomicBoolean;

class AdCloser {
    private static final String LOGTAG = AdCloser.class.getSimpleName();
    private final AdController adController;
    private final AtomicBoolean isClosing;
    private final MobileAdsLogger logger;

    public AdCloser(AdController adController2) {
        this(adController2, new MobileAdsLoggerFactory());
    }

    AdCloser(AdController adController2, MobileAdsLoggerFactory loggerFactory) {
        this.isClosing = new AtomicBoolean(false);
        this.adController = adController2;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
    }

    public boolean closeAd() {
        this.logger.d("Ad is attempting to close.");
        boolean isClosed = false;
        if (!this.adController.getAdState().equals(AdState.READY_TO_LOAD) && !this.isClosing.getAndSet(true)) {
            boolean shouldClearAdActivity = false;
            boolean fireCloseEvent = false;
            switch (this.adController.getAdControlCallback().adClosing()) {
                case 0:
                    fireCloseEvent = true;
                    break;
                case 1:
                    shouldClearAdActivity = true;
                    fireCloseEvent = true;
                    break;
            }
            if (fireCloseEvent) {
                this.adController.fireSDKEvent(new SDKEvent(SDKEvent.SDKEventType.CLOSED));
                isClosed = true;
            }
            if (shouldClearAdActivity) {
                this.adController.resetToReady();
            }
            this.isClosing.set(false);
        }
        return isClosed;
    }
}
