package com.amazon.mas.cpt.ads;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.RelativeLayout;
import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdProperties;

public class FloatingBannerDisplay {
    private final String LOGTAG = FloatingBannerDisplay.class.getSimpleName();
    private Ad bottomAd;
    private RelativeLayout bottomLayout;
    private final AmazonMobileAdsJavaController controller;
    private final AmazonMobileAdsLogger logger;
    private RelativeLayout mainLayout;
    private Ad topAd;
    private RelativeLayout topLayout;

    public FloatingBannerDisplay(AmazonMobileAdsLogger logger2, AmazonMobileAdsJavaController controller2) {
        this.logger = logger2;
        this.controller = controller2;
    }

    public void addFloatingAd(AdWrapper adWrapper, AdCache adCache) {
        final AdWrapper adWrapper2 = adWrapper;
        final AdCache adCache2 = adCache;
        adWrapper.getSdkAd().setListener(new AmazonMobileAdListener(this.controller, adCache, this.logger) {
            public void onAdLoaded(Ad ad, AdProperties adProperties) {
                super.onAdLoaded(ad, adProperties);
                FloatingBannerDisplay.this.adLoaded(adWrapper2, adCache2);
            }
        });
    }

    /* access modifiers changed from: private */
    public void adLoaded(AdWrapper adWrapper, AdCache adCache) {
        if (adWrapper.getSdkAd() instanceof View) {
            Dock dock = adWrapper.getPlacement().getDock();
            Ad sdkAd = adWrapper.getSdkAd();
            Ad oldAd = getAd(dock);
            if (!sdkAd.equals(oldAd)) {
                showAd(adWrapper.getPlacement(), adWrapper);
                setAd(sdkAd, dock);
                if (oldAd != null) {
                    adCache.adClosed(adCache.getAdWrapper(oldAd));
                }
            }
            adCache.adShown(adWrapper);
        }
    }

    private Ad getAd(Dock dock) {
        switch (dock) {
            case TOP:
                return this.topAd;
            case BOTTOM:
                return this.bottomAd;
            default:
                return null;
        }
    }

    private void setAd(Ad ad, Dock dock) {
        switch (dock) {
            case TOP:
                this.topAd = ad;
                return;
            case BOTTOM:
                this.bottomAd = ad;
                return;
            default:
                return;
        }
    }

    private void showAd(final Placement placement, AdWrapper adWrapper) {
        Ad sdkAd = adWrapper.getSdkAd();
        if (sdkAd instanceof View) {
            final View adView = (View) sdkAd;
            runOnMainThread(new Runnable() {
                public void run() {
                    FloatingBannerDisplay.this.showAdOnMainThread(placement, adView);
                }
            });
            return;
        }
        this.logger.e(this.LOGTAG, "Only ad types that are Android Views can be displayed as Floating Banner Ads.");
    }

    /* access modifiers changed from: private */
    public void showAdOnMainThread(Placement placement, View adView) {
        if (this.mainLayout == null) {
            createMainLayoutOnMainThread();
        }
        switch (placement.getDock()) {
            case TOP:
                this.topLayout.removeAllViews();
                this.topLayout.addView(adView);
                return;
            case BOTTOM:
                this.bottomLayout.removeAllViews();
                this.bottomLayout.addView(adView);
                return;
            default:
                return;
        }
    }

    private void createMainLayoutOnMainThread() {
        Activity activity = this.controller.getCurrentAndroidActivity();
        this.mainLayout = new RelativeLayout(activity);
        this.topLayout = new RelativeLayout(activity);
        this.bottomLayout = new RelativeLayout(activity);
        RelativeLayout.LayoutParams mainParams = new RelativeLayout.LayoutParams(-1, -1);
        RelativeLayout.LayoutParams topParams = new RelativeLayout.LayoutParams(-1, -2);
        RelativeLayout.LayoutParams bottomParams = new RelativeLayout.LayoutParams(-1, -2);
        topParams.addRule(10);
        bottomParams.addRule(12);
        this.mainLayout.addView(this.topLayout, topParams);
        this.mainLayout.addView(this.bottomLayout, bottomParams);
        activity.addContentView(this.mainLayout, mainParams);
    }

    public void removeAd(Ad ad) {
        final RelativeLayout adLayoutToRemoveViews;
        if (ad instanceof View) {
            if (ad == this.topAd) {
                adLayoutToRemoveViews = this.topLayout;
                this.topAd = null;
            } else if (ad == this.bottomAd) {
                adLayoutToRemoveViews = this.bottomLayout;
                this.bottomAd = null;
            } else {
                adLayoutToRemoveViews = null;
                this.logger.d(this.LOGTAG, "Ad was not found in view");
            }
            if (adLayoutToRemoveViews != null) {
                runOnMainThread(new Runnable() {
                    public void run() {
                        FloatingBannerDisplay.this.removeLayoutViews(adLayoutToRemoveViews);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void removeLayoutViews(RelativeLayout layout) {
        layout.removeAllViews();
    }

    private boolean isOnMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    private void runOnMainThread(Runnable runnable) {
        if (isOnMainThread()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }
}
