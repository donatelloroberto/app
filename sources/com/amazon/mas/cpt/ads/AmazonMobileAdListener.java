package com.amazon.mas.cpt.ads;

import android.graphics.Rect;
import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdProperties;
import com.amazon.device.ads.DefaultAdListener;

class AmazonMobileAdListener extends DefaultAdListener {
    private static final String LOGTAG = AmazonMobileAdListener.class.getSimpleName();
    private final AdCache adCache;
    private final AmazonMobileAdsJavaController javaController;
    private final AmazonMobileAdsLogger logger;

    public AmazonMobileAdListener(AmazonMobileAdsJavaController javaController2, AdCache adCache2, AmazonMobileAdsLogger logger2) {
        this.javaController = javaController2;
        this.adCache = adCache2;
        this.logger = logger2;
    }

    public void onAdCollapsed(Ad ad) {
        AdWrapper adWrapper = this.adCache.getAdWrapper(ad);
        if (adWrapper != null) {
            this.javaController.fireAdCollapsed(adWrapper.getPluginAd());
        }
    }

    public void onAdDismissed(Ad ad) {
        AdWrapper adWrapper = this.adCache.getAdWrapper(ad);
        if (adWrapper != null) {
            this.adCache.adClosed(adWrapper);
            this.javaController.fireAdDismissed(adWrapper.getPluginAd());
        }
    }

    public void onAdExpanded(Ad ad) {
        AdWrapper adWrapper = this.adCache.getAdWrapper(ad);
        if (adWrapper != null) {
            this.javaController.fireAdExpanded(adWrapper.getPluginAd());
        }
    }

    public void onAdLoaded(Ad ad, AdProperties adProperties) {
        AdWrapper adWrapper = this.adCache.getAdWrapper(ad);
        if (adWrapper != null) {
            this.adCache.adLoaded(adWrapper);
            this.javaController.fireAdLoaded(adWrapper.getPluginAd());
        }
    }

    public void onAdFailedToLoad(Ad ad, AdError adError) {
        this.logger.e(LOGTAG, "Ad failed to load: " + adError.getCode() + " - " + adError.getMessage());
        AdWrapper adWrapper = this.adCache.getAdWrapper(ad);
        if (adWrapper != null) {
            this.adCache.adFailedToLoad(adWrapper);
            this.javaController.fireAdFailedToLoad(adWrapper.getPluginAd());
        }
    }

    public void onAdResized(Ad ad, Rect rectangle) {
        this.javaController.fireAdResized(new AdPosition().withXCoordinate(Integer.valueOf(rectangle.left)).withYCoordinate(Integer.valueOf(rectangle.top)).withWidth(Integer.valueOf(rectangle.width())).withHeight(Integer.valueOf(rectangle.height())));
    }

    public void onAdExpired(Ad ad) {
        this.logger.e(LOGTAG, "Ad expired");
        AdWrapper adWrapper = this.adCache.getAdWrapper(ad);
        if (adWrapper != null) {
            this.adCache.adExpired(adWrapper);
        }
    }
}
