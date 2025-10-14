package com.amazon.device.ads;

import android.annotation.SuppressLint;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdLoader;
import com.amazon.device.ads.AdRequest;
import com.amazon.device.ads.AdvertisingIdentifier;
import com.amazon.device.ads.ThreadUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class AdLoadStarter {
    private static final String LOGTAG = AdLoadStarter.class.getSimpleName();
    private final AdLoader.AdLoaderFactory adLoaderFactory;
    private final AdRequest.AdRequestBuilder adRequestBuilder;
    private final AdvertisingIdentifier advertisingIdentifier;
    private final Configuration configuration;
    /* access modifiers changed from: private */
    public final MobileAdsInfoStore infoStore;
    private final MobileAdsLogger logger;
    private final Settings settings;
    private final SystemTime systemTime;
    /* access modifiers changed from: private */
    public final ThreadUtils.ThreadRunner threadRunner;

    public AdLoadStarter() {
        this(new AdLoader.AdLoaderFactory(), new AdvertisingIdentifier(), ThreadUtils.getThreadRunner(), MobileAdsInfoStore.getInstance(), Settings.getInstance(), Configuration.getInstance(), new MobileAdsLoggerFactory(), new SystemTime(), new AdRequest.AdRequestBuilder());
    }

    AdLoadStarter(AdLoader.AdLoaderFactory adLoaderFactory2, AdvertisingIdentifier advertisingIdentifier2, ThreadUtils.ThreadRunner threadRunner2, MobileAdsInfoStore infoStore2, Settings settings2, Configuration configuration2, MobileAdsLoggerFactory loggerFactory, SystemTime systemTime2, AdRequest.AdRequestBuilder adRequestBuilder2) {
        this.adLoaderFactory = adLoaderFactory2;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        this.advertisingIdentifier = advertisingIdentifier2;
        this.infoStore = infoStore2;
        this.settings = settings2;
        this.configuration = configuration2;
        this.threadRunner = threadRunner2;
        this.systemTime = systemTime2;
        this.adRequestBuilder = adRequestBuilder2;
    }

    public void loadAds(int timeout, AdTargetingOptions requestOptions, AdSlot... adSlots) {
        if (!isNoRetry(adSlots)) {
            long loadAdStartTime = this.systemTime.nanoTime();
            final ArrayList<AdSlot> requestAdSlots = new ArrayList<>();
            for (AdSlot adSlot : adSlots) {
                if (adSlot.prepareForAdLoad(loadAdStartTime)) {
                    requestAdSlots.add(adSlot);
                }
            }
            final int i = timeout;
            final AdTargetingOptions adTargetingOptions = requestOptions;
            new StartUpWaiter(this.settings, this.configuration) {
                /* access modifiers changed from: protected */
                public void startUpReady() {
                    AdLoadStarter.this.infoStore.register();
                    AdLoadStarter.this.beginFetchAds(i, adTargetingOptions, requestAdSlots);
                }

                /* access modifiers changed from: protected */
                public void startUpFailed() {
                    AdLoadStarter.this.threadRunner.execute(new Runnable() {
                        public void run() {
                            AdLoadStarter.this.failAds(new AdError(AdError.ErrorCode.NETWORK_ERROR, "The configuration was unable to be loaded"), requestAdSlots);
                        }
                    }, ThreadUtils.ExecutionStyle.RUN_ASAP, ThreadUtils.ExecutionThread.MAIN_THREAD);
                }
            }.start();
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"UseSparseArrays"})
    public void beginFetchAds(int timeout, AdTargetingOptions requestOptions, List<AdSlot> adSlots) {
        AdvertisingIdentifier.Info advertisingIdentifierInfo = this.advertisingIdentifier.getAdvertisingIdentifierInfo();
        if (!advertisingIdentifierInfo.canDo()) {
            failAds(new AdError(AdError.ErrorCode.INTERNAL_ERROR, "An internal request was not made on a background thread."), adSlots);
            return;
        }
        if (requestOptions == null) {
            requestOptions = new AdTargetingOptions();
        }
        AdRequest request = this.adRequestBuilder.withAdTargetingOptions(requestOptions).withAdvertisingIdentifierInfo(advertisingIdentifierInfo).build();
        HashMap<Integer, AdSlot> goodAdSlots = new HashMap<>();
        int slotNumber = 1;
        for (AdSlot slot : adSlots) {
            if (slot.isValid()) {
                slot.setSlotNumber(slotNumber);
                goodAdSlots.put(Integer.valueOf(slotNumber), slot);
                request.putSlot(slot);
                slotNumber++;
            }
        }
        if (goodAdSlots.size() > 0) {
            AdLoader adLoader = this.adLoaderFactory.createAdLoader(request, goodAdSlots);
            adLoader.setTimeout(timeout);
            adLoader.beginFetchAd();
        }
    }

    /* access modifiers changed from: private */
    public void failAds(AdError adError, List<AdSlot> adSlots) {
        int adFailCount = 0;
        for (AdSlot slot : adSlots) {
            if (slot.getSlotNumber() != -1) {
                slot.adFailed(adError);
                adFailCount++;
            }
        }
        if (adFailCount > 0) {
            this.logger.e("%s; code: %s", adError.getMessage(), adError.getCode());
        }
    }

    private boolean isNoRetry(AdSlot[] adSlots) {
        String errorMessage;
        AdError.ErrorCode errorCode;
        int noRetryTtlRemainingMillis = this.infoStore.getNoRetryTtlRemainingMillis();
        if (noRetryTtlRemainingMillis <= 0) {
            return false;
        }
        int noRetryTtlRemainingSecs = noRetryTtlRemainingMillis / 1000;
        if (this.infoStore.getIsAppDisabled()) {
            errorMessage = "SDK Message: " + AdLoader.DISABLED_APP_SERVER_MESSAGE;
            errorCode = AdError.ErrorCode.INTERNAL_ERROR;
        } else {
            errorMessage = "SDK Message: " + "no results. Try again in " + noRetryTtlRemainingSecs + " seconds.";
            errorCode = AdError.ErrorCode.NO_FILL;
        }
        failAds(new AdError(errorCode, errorMessage), new ArrayList(Arrays.asList(adSlots)));
        return true;
    }
}
