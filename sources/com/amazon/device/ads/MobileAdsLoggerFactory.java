package com.amazon.device.ads;

class MobileAdsLoggerFactory {
    private static MobileAdsLoggerFactory loggerFactory;

    MobileAdsLoggerFactory() {
    }

    static void setLoggerFactory(MobileAdsLoggerFactory loggerFactory2) {
        loggerFactory = loggerFactory2;
    }

    public MobileAdsLogger createMobileAdsLogger(String logTag) {
        if (loggerFactory != null) {
            return loggerFactory.createMobileAdsLogger(logTag);
        }
        return createMobileAdsLogger(logTag, new LogcatLogger());
    }

    /* access modifiers changed from: package-private */
    public MobileAdsLogger createMobileAdsLogger(String logTag, Logger logger) {
        return new MobileAdsLogger(logger).withLogTag(logTag);
    }
}
