package com.amazon.mas.cpt.ads;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdTargetingOptions;
import com.amazon.device.ads.InterstitialAd;

public class AmazonMobileAdsImpl implements AmazonMobileAds {
    private static final String LOGTAG = AmazonMobileAdsImpl.class.getSimpleName();
    private static volatile AmazonMobileAdsJavaController javaController;
    private final AdTargetingOptions adTargetingOptions;
    private final RandomAccessAdCache floatingAdBottomCache;
    private final RandomAccessAdCache floatingAdTopCache;
    private FloatingBannerDisplay floatingDisplay;
    private final QueuedAdCache interstitialCache;
    private boolean isSlotValueSet;
    private final AmazonMobileAdsLogger logger;

    public AmazonMobileAdsImpl() {
        this(new QueuedAdCache(new InterstitialAdFactory()), new RandomAccessAdCache(new AdLayoutFactory()), new RandomAccessAdCache(new AdLayoutFactory()), new AmazonMobileAdsLogger());
    }

    AmazonMobileAdsImpl(QueuedAdCache interstitialCache2, RandomAccessAdCache floatingAdTopCache2, RandomAccessAdCache floatingAdBottomCache2, AmazonMobileAdsLogger logger2) {
        this.adTargetingOptions = new AdTargetingOptions();
        this.isSlotValueSet = false;
        this.interstitialCache = interstitialCache2;
        this.floatingAdTopCache = floatingAdTopCache2;
        this.floatingAdBottomCache = floatingAdBottomCache2;
        this.logger = logger2;
    }

    public static Activity getCurrentAndroidActivity() {
        return javaController.getCurrentAndroidActivity();
    }

    private void setSlotValue() {
        if (!this.isSlotValueSet) {
            this.adTargetingOptions.setAdvancedOption("slot", javaController.getCrossPlatformTool() + "AMZN");
            this.isSlotValueSet = true;
        }
    }

    public void setApplicationKey(ApplicationKey applicationKey) {
        AdRegistration.setAppKey(applicationKey.getStringValue());
    }

    public void registerApplication() {
        AdRegistration.registerApp(getCurrentAndroidActivity());
    }

    public void enableLogging(ShouldEnable shouldEnable) {
        AdRegistration.enableLogging(shouldEnable.getBooleanValue().booleanValue());
    }

    public void enableTesting(ShouldEnable shouldEnable) {
        AdRegistration.enableTesting(shouldEnable.getBooleanValue().booleanValue());
    }

    public void enableGeoLocation(ShouldEnable shouldEnable) {
        this.adTargetingOptions.enableGeoLocation(shouldEnable.getBooleanValue().booleanValue());
    }

    private FloatingBannerDisplay getFloatingBannerDisplay() {
        if (this.floatingDisplay == null) {
            this.floatingDisplay = new FloatingBannerDisplay(this.logger, javaController);
        }
        return this.floatingDisplay;
    }

    public Ad createFloatingBannerAd(Placement placement) {
        AdCache adCache;
        switch (placement.getDock()) {
            case TOP:
                adCache = this.floatingAdTopCache;
                break;
            case BOTTOM:
                adCache = this.floatingAdBottomCache;
                break;
            default:
                return null;
        }
        AdWrapper adWrapper = adCache.createAd(getCurrentAndroidActivity(), placement);
        getFloatingBannerDisplay().addFloatingAd(adWrapper, adCache);
        if (adWrapper.getSdkAd() instanceof View) {
            ((View) adWrapper.getSdkAd()).setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        }
        return adWrapper.getPluginAd();
    }

    public Ad createInterstitialAd() {
        AdWrapper adWrapper = this.interstitialCache.createAd(getCurrentAndroidActivity(), (Placement) null);
        adWrapper.getSdkAd().setListener(new AmazonMobileAdListener(javaController, this.interstitialCache, this.logger));
        return adWrapper.getPluginAd();
    }

    public LoadingStarted loadAndShowFloatingBannerAd(Ad ad) {
        setSlotValue();
        boolean loadingStarted = false;
        if (ad != null) {
            RandomAccessAdCache adCache = this.floatingAdTopCache;
            AdWrapper adWrapper = adCache.getAdWrapper(ad.getIdentifier());
            if (adWrapper == null) {
                adCache = this.floatingAdBottomCache;
                adWrapper = adCache.getAdWrapper(ad.getIdentifier());
            }
            if (adWrapper != null) {
                loadingStarted = adCache.loadAd(this.adTargetingOptions, adWrapper);
            }
        }
        return new LoadingStarted().withBooleanValue(Boolean.valueOf(loadingStarted));
    }

    public LoadingStarted loadInterstitialAd() {
        setSlotValue();
        return new LoadingStarted().withBooleanValue(Boolean.valueOf(this.interstitialCache.loadNextAd(this.adTargetingOptions)));
    }

    public AdShown showInterstitialAd() {
        boolean adShown = false;
        AdWrapper adWrapper = this.interstitialCache.getNextAdForShowing();
        if (adWrapper != null) {
            adShown = ((InterstitialAd) adWrapper.getSdkAd()).showAd();
            if (adShown) {
                this.interstitialCache.adShown(adWrapper);
            } else {
                this.interstitialCache.adFailedToShow(adWrapper);
            }
        }
        return new AdShown().withBooleanValue(Boolean.valueOf(adShown));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0018, code lost:
        r0 = r5.floatingAdBottomCache;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void closeFloatingBannerAd(com.amazon.mas.cpt.ads.Ad r6) {
        /*
            r5 = this;
            if (r6 != 0) goto L_0x000c
            com.amazon.mas.cpt.ads.AmazonMobileAdsLogger r2 = r5.logger
            java.lang.String r3 = LOGTAG
            java.lang.String r4 = "Ad cannot be null"
            r2.d(r3, r4)
        L_0x000b:
            return
        L_0x000c:
            com.amazon.mas.cpt.ads.RandomAccessAdCache r0 = r5.floatingAdTopCache
            java.lang.Long r2 = r6.getIdentifier()
            com.amazon.mas.cpt.ads.AdWrapper r1 = r0.getAdWrapper((java.lang.Long) r2)
            if (r1 != 0) goto L_0x002e
            com.amazon.mas.cpt.ads.RandomAccessAdCache r0 = r5.floatingAdBottomCache
            java.lang.Long r2 = r6.getIdentifier()
            com.amazon.mas.cpt.ads.AdWrapper r1 = r0.getAdWrapper((java.lang.Long) r2)
            if (r1 != 0) goto L_0x002e
            com.amazon.mas.cpt.ads.AmazonMobileAdsLogger r2 = r5.logger
            java.lang.String r3 = LOGTAG
            java.lang.String r4 = "Ad was not found in cache"
            r2.d(r3, r4)
            goto L_0x000b
        L_0x002e:
            boolean r2 = r0.isAdShowing(r1)
            if (r2 == 0) goto L_0x0043
            r0.adClosed(r1)
            com.amazon.mas.cpt.ads.FloatingBannerDisplay r2 = r5.getFloatingBannerDisplay()
            com.amazon.device.ads.Ad r3 = r1.getSdkAd()
            r2.removeAd(r3)
            goto L_0x000b
        L_0x0043:
            com.amazon.mas.cpt.ads.AmazonMobileAdsLogger r2 = r5.logger
            java.lang.String r3 = LOGTAG
            java.lang.String r4 = "Ad cannot be closed because it is not currently showing."
            r2.d(r3, r4)
            goto L_0x000b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mas.cpt.ads.AmazonMobileAdsImpl.closeFloatingBannerAd(com.amazon.mas.cpt.ads.Ad):void");
    }

    public IsReady isInterstitialAdReady() {
        return new IsReady().withBooleanValue(Boolean.valueOf(this.interstitialCache.isAnAdReadyToShow()));
    }

    public IsEqual areAdsEqual(AdPair adPair) {
        Ad adOne = adPair.getAdOne();
        Ad adTwo = adPair.getAdTwo();
        boolean isEqual = false;
        if (adOne != null) {
            isEqual = adOne.equals(adTwo);
        }
        return new IsEqual().withBooleanValue(Boolean.valueOf(isEqual));
    }

    public void setAmazonMobileAdsJavaController(AmazonMobileAdsJavaController masCptAdsJavaController) {
        setJavaController(masCptAdsJavaController);
    }

    public static void setJavaController(AmazonMobileAdsJavaController testSimpleActivityServiceJavaController) {
        javaController = testSimpleActivityServiceJavaController;
    }
}
