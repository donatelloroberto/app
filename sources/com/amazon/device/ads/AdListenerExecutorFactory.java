package com.amazon.device.ads;

class AdListenerExecutorFactory {
    private final MobileAdsLoggerFactory loggerFactory;

    public AdListenerExecutorFactory(MobileAdsLoggerFactory loggerFactory2) {
        this.loggerFactory = loggerFactory2;
    }

    /* access modifiers changed from: protected */
    public MobileAdsLoggerFactory getLoggerFactory() {
        return this.loggerFactory;
    }

    public AdListenerExecutor createAdListenerExecutor(AdListener adListener) {
        if (adListener instanceof ExtendedAdListener) {
            return new ExtendedAdListenerExecutor((ExtendedAdListener) adListener, this.loggerFactory);
        }
        return new AdListenerExecutor(adListener, this.loggerFactory);
    }
}
