package com.amazon.mas.cpt.ads;

public interface AmazonMobileAds {
    IsEqual areAdsEqual(AdPair adPair);

    void closeFloatingBannerAd(Ad ad);

    Ad createFloatingBannerAd(Placement placement);

    Ad createInterstitialAd();

    void enableGeoLocation(ShouldEnable shouldEnable);

    void enableLogging(ShouldEnable shouldEnable);

    void enableTesting(ShouldEnable shouldEnable);

    IsReady isInterstitialAdReady();

    LoadingStarted loadAndShowFloatingBannerAd(Ad ad);

    LoadingStarted loadInterstitialAd();

    void registerApplication();

    void setAmazonMobileAdsJavaController(AmazonMobileAdsJavaController amazonMobileAdsJavaController);

    void setApplicationKey(ApplicationKey applicationKey);

    AdShown showInterstitialAd();
}
