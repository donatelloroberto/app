package com.amazon.device.ads;

import android.content.Context;
import android.view.ViewGroup;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdProperties;
import com.amazon.device.ads.Metrics;
import java.util.concurrent.atomic.AtomicBoolean;

public class ModelessInterstitialAd implements Ad {
    private static final String LOGTAG = ModelessInterstitialAd.class.getSimpleName();
    private static final int MIN_PIXELS = 380;
    private static final double MIN_SCREEN_COVERAGE_PERCENTAGE = 0.75d;
    private static final String PUBLISHER_KEYWORD = "modeless-interstitial";
    private AdController adController;
    private final AdControllerFactory adControllerFactory;
    private AdListenerExecutor adListenerExecutor;
    private final AdListenerExecutorFactory adListenerExecutorFactory;
    private final AdLoadStarter adLoadStarter;
    private AdProperties adProperties;
    private final AdRegistrationExecutor amazonAdRegistration;
    private final Context context;
    private final ViewGroup hostedViewGroup;
    private final MobileAdsLogger logger;
    private final MobileAdsLoggerFactory loggerFactory;
    private MetricsCollector metricsCollector;
    private final AtomicBoolean previousAdExpired;
    private int timeout;

    public ModelessInterstitialAd(ViewGroup hostedViewGroup2) {
        this(hostedViewGroup2, AdRegistration.getAmazonAdRegistrationExecutor(), new AdControllerFactory(), new MobileAdsLoggerFactory(), new AdLoadStarter());
    }

    ModelessInterstitialAd(ViewGroup hostedViewGroup2, AdRegistrationExecutor amazonAdRegistration2, AdControllerFactory adControllerFactory2, MobileAdsLoggerFactory loggerFactory2, AdLoadStarter adLoadStarter2) {
        this(hostedViewGroup2, amazonAdRegistration2, adControllerFactory2, loggerFactory2, new AdListenerExecutorFactory(loggerFactory2), adLoadStarter2);
    }

    ModelessInterstitialAd(ViewGroup hostedViewGroup2, AdRegistrationExecutor amazonAdRegistration2, AdControllerFactory adControllerFactory2, MobileAdsLoggerFactory loggerFactory2, AdListenerExecutorFactory adListenerExecutorFactory2, AdLoadStarter adLoadStarter2) {
        this.previousAdExpired = new AtomicBoolean(false);
        if (hostedViewGroup2 == null) {
            throw new IllegalArgumentException("The hostedViewGroup must not be null.");
        }
        this.hostedViewGroup = hostedViewGroup2;
        this.context = this.hostedViewGroup.getContext();
        this.amazonAdRegistration = amazonAdRegistration2;
        this.adControllerFactory = adControllerFactory2;
        this.loggerFactory = loggerFactory2;
        this.logger = this.loggerFactory.createMobileAdsLogger(LOGTAG);
        this.adListenerExecutorFactory = adListenerExecutorFactory2;
        this.adLoadStarter = adLoadStarter2;
        initialize();
    }

    public void setListener(AdListener adListener) {
        if (adListener == null) {
            adListener = new DefaultAdListener(LOGTAG);
        }
        this.adListenerExecutor = this.adListenerExecutorFactory.createAdListenerExecutor(adListener);
    }

    public boolean loadAd(AdTargetingOptions adTargetingOptions) {
        if (!isReadyToLoad()) {
            switch (this.adController.getAdState()) {
                case LOADING:
                case LOADED:
                case RENDERING:
                    this.logger.w("The modeless interstitial ad is already loading. Please wait for the loading operation to complete.");
                    break;
                case RENDERED:
                    this.logger.w("The modeless interstitial ad has already been loaded. Please call adShown once the ad is shown.");
                    break;
                case INVALID:
                    if (!this.adController.isExpired()) {
                        this.logger.e("The modeless interstitial ad could not be loaded because of an unknown issue with the web views.");
                        break;
                    } else {
                        this.adController.resetToReady();
                        return loadAd(adTargetingOptions);
                    }
                case DESTROYED:
                    this.logger.e("The modeless interstitial ad has been destroyed. Please create a new ModelessInterstitialAd.");
                    break;
            }
            this.metricsCollector.incrementMetric(Metrics.MetricType.AD_LOAD_FAILED);
            return false;
        }
        this.previousAdExpired.set(false);
        AdTargetingOptions modifiedTargetingOptions = adTargetingOptions == null ? new AdTargetingOptions() : adTargetingOptions.copy();
        modifiedTargetingOptions.addInternalPublisherKeyword(PUBLISHER_KEYWORD);
        submitMetrics();
        this.adLoadStarter.loadAds(this.timeout, modifiedTargetingOptions, new AdSlot(this.adController, modifiedTargetingOptions));
        return this.adController.getAndResetIsPrepared();
    }

    public boolean loadAd() {
        return loadAd((AdTargetingOptions) null);
    }

    public boolean isLoading() {
        AdState adState = this.adController.getAdState();
        return adState.equals(AdState.LOADING) || adState.equals(AdState.LOADED) || adState.equals(AdState.RENDERING);
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout2) {
        this.timeout = timeout2;
    }

    public boolean adShown() {
        AdState adState = this.adController.getAdState();
        if (this.previousAdExpired.get() || (!adState.equals(AdState.HIDDEN) && this.adController.isExpired())) {
            this.logger.e("The ad is unable to be shown because it has expired.");
            this.metricsCollector.stopMetric(Metrics.MetricType.AD_LOADED_TO_AD_SHOW_TIME);
            this.metricsCollector.incrementMetric(Metrics.MetricType.EXPIRED_AD_CALL);
        } else if (adState.equals(AdState.LOADING)) {
            this.logger.w("The adShown call failed because the ad cannot be shown until it has completed loading.");
        } else if (adState.equals(AdState.SHOWING)) {
            this.logger.w("The adShown call failed because adShown was previously called on this ad.");
        } else if (adState.equals(AdState.RENDERED) || adState.equals(AdState.HIDDEN)) {
            if (adState.equals(AdState.RENDERED)) {
                this.metricsCollector.stopMetric(Metrics.MetricType.AD_LOADED_TO_AD_SHOW_TIME);
            }
            Position adPos = this.adController.getAdPosition();
            Size adSize = adPos.getSize();
            Size screenSize = this.adController.getScreenSize();
            if (!doesAdSizeHaveOneSideWithAtLeastMinPixels(adSize) || !isAdOnScreen(adPos, screenSize) || !doesAdSizeMeetRequiredScreenPercentage(adSize, screenSize)) {
                this.metricsCollector.incrementMetric(Metrics.MetricType.RENDER_REQUIREMENT_CHECK_FAILURE);
            } else {
                checkIfAdAspectRatioLessThanScreenAspectRatio(adSize, screenSize);
                if (this.adController.getAdState().equals(AdState.HIDDEN)) {
                    this.metricsCollector.incrementMetric(Metrics.MetricType.AD_COUNTER_RESHOWN);
                }
                setRenderedViewClickable(true);
                this.adController.adShown();
                this.metricsCollector.startMetric(Metrics.MetricType.AD_SHOW_DURATION);
                return true;
            }
        } else {
            this.logger.w("The adShown call failed because the ad is not in a state to be shown. The ad is currently in the %s state.", adState);
        }
        return false;
    }

    public void adHidden() {
        AdState adState = this.adController.getAdState();
        if (adState.equals(AdState.HIDDEN)) {
            this.logger.d("The ad is already hidden from view.");
        } else if (adState.equals(AdState.SHOWING)) {
            this.adController.getMetricsCollector().stopMetric(Metrics.MetricType.AD_SHOW_DURATION);
            setRenderedViewClickable(false);
            this.adController.adHidden();
        } else {
            this.logger.w("The ad must be shown before it can be hidden.");
        }
    }

    public void destroy() {
        this.logger.d("Destroying the Modeless Interstitial Ad");
        if (this.adController.getAdState().equals(AdState.SHOWING)) {
            adHidden();
        }
        submitMetrics();
        this.adController.destroy();
    }

    public boolean isReady() {
        return this.adController.getAdState().equals(AdState.RENDERED) && !this.adController.isExpired();
    }

    private void initialize() {
        this.amazonAdRegistration.initializeAds(this.context.getApplicationContext());
        setListener((AdListener) null);
        buildAdController();
    }

    private void buildAdController() {
        this.adController = this.adControllerFactory.buildAdController(this.context, AdSize.SIZE_MODELESS_INTERSTITIAL);
        this.adController.setCallback(new ModelessInterstitialAdControlCallback());
        this.metricsCollector = this.adController.getMetricsCollector();
        this.metricsCollector.setAdType(AdProperties.AdType.MODELESS_INTERSTITIAL);
        this.metricsCollector.incrementMetric(Metrics.MetricType.AD_IS_INTERSTITIAL);
    }

    /* access modifiers changed from: private */
    public void onAdFailedToLoadOrRender(AdError adError) {
        if (adError.getCode().equals(AdError.ErrorCode.NETWORK_TIMEOUT)) {
            submitMetrics();
            buildAdController();
        }
        this.adListenerExecutor.onAdFailedToLoad(this, adError);
    }

    /* access modifiers changed from: private */
    public void onAdFetched(AdProperties adProperties2) {
        this.adProperties = adProperties2;
        this.adController.render();
    }

    /* access modifiers changed from: private */
    public void onAdRendered() {
        this.hostedViewGroup.addView(this.adController.getView());
        setRenderedViewClickable(false);
        this.adListenerExecutor.onAdLoaded(this, this.adProperties);
    }

    /* access modifiers changed from: private */
    public boolean isReadyToLoad() {
        AdState adState = this.adController.getAdState();
        return this.adController.isExpired() || adState.equals(AdState.READY_TO_LOAD) || adState.equals(AdState.HIDDEN);
    }

    /* access modifiers changed from: private */
    public void onAdRenderMetricsRecorded() {
        this.metricsCollector.startMetric(Metrics.MetricType.AD_LOADED_TO_AD_SHOW_TIME);
    }

    private void setRenderedViewClickable(boolean clickable) {
        this.adController.setAllowClicks(clickable);
    }

    /* access modifiers changed from: private */
    public void onAdExpired() {
        this.metricsCollector.incrementMetric(Metrics.MetricType.AD_EXPIRED_BEFORE_SHOWING);
        this.previousAdExpired.set(true);
        buildAdController();
        this.adListenerExecutor.onAdExpired(this);
    }

    private void submitMetrics() {
        if (!this.adController.getMetricsCollector().isMetricsCollectorEmpty()) {
            this.adController.submitAndResetMetrics();
        }
    }

    private boolean doesAdSizeHaveOneSideWithAtLeastMinPixels(Size adSize) {
        if (adSize.getHeight() >= MIN_PIXELS || adSize.getWidth() >= MIN_PIXELS) {
            return true;
        }
        this.logger.e("This ModelessInterstitialAd cannot fire impression pixels or receive clicks because the height %d and width %d does not meet the requirement of one side being at least %d device independent pixels.", Integer.valueOf(adSize.getHeight()), Integer.valueOf(adSize.getWidth()), Integer.valueOf(MIN_PIXELS));
        return false;
    }

    private boolean isAdOnScreen(Position adPos, Size screenSize) {
        if (adPos.getX() >= 0 && adPos.getX() + adPos.getSize().getWidth() <= screenSize.getWidth() && adPos.getY() >= 0 && adPos.getY() + adPos.getSize().getHeight() <= screenSize.getHeight()) {
            return true;
        }
        this.logger.e("This ModelessInterstitialAd cannot fire impression pixels or receive clicks because it does not meet the requirement of being fully on screen.");
        return false;
    }

    private boolean doesAdSizeMeetRequiredScreenPercentage(Size adSize, Size screenSize) {
        double screenPercentage = (((double) adSize.getHeight()) * ((double) adSize.getWidth())) / (((double) screenSize.getHeight()) * ((double) screenSize.getWidth()));
        if (screenPercentage >= MIN_SCREEN_COVERAGE_PERCENTAGE) {
            return true;
        }
        this.logger.e("This ModelessInterstitialAd cannot fire impression pixels or receive clicks because it has a screen coverage percentage of %f which does not meet the requirement of covering at least %d percent.", Double.valueOf(100.0d * screenPercentage), 75);
        return false;
    }

    private void checkIfAdAspectRatioLessThanScreenAspectRatio(Size adSize, Size screenSize) {
        boolean adAspectRatioLessThanScreenAspectRatio = true;
        float adSizeWidth = (float) adSize.getWidth();
        float adSizeHeight = (float) adSize.getHeight();
        float screenSizeWidth = (float) screenSize.getWidth();
        float screenSizeHeight = (float) screenSize.getHeight();
        if (adSizeWidth <= adSizeHeight) {
            if (adSizeWidth / adSizeHeight >= screenSizeWidth / screenSizeHeight) {
                adAspectRatioLessThanScreenAspectRatio = false;
            }
        } else if (adSizeHeight / adSizeWidth >= screenSizeHeight / screenSizeWidth) {
            adAspectRatioLessThanScreenAspectRatio = false;
        }
        if (adAspectRatioLessThanScreenAspectRatio) {
            this.metricsCollector.incrementMetric(Metrics.MetricType.AD_ASPECT_RATIO_LESS_THAN_SCREEN_ASPECT_RATIO);
            this.logger.w("For an optimal ad experience, the aspect ratio of the ModelessInterstitialAd should be greater than or equal to the aspect ratio of the screen.");
        }
    }

    private class ModelessInterstitialAdControlCallback implements AdControlCallback {
        private ModelessInterstitialAdControlCallback() {
        }

        public boolean isAdReady(boolean deferredLoad) {
            return ModelessInterstitialAd.this.isReadyToLoad();
        }

        public void onAdLoaded(AdProperties adProperties) {
            ModelessInterstitialAd.this.onAdFetched(adProperties);
        }

        public void onAdRendered() {
            ModelessInterstitialAd.this.onAdRendered();
        }

        public void postAdRendered() {
            ModelessInterstitialAd.this.onAdRenderMetricsRecorded();
        }

        public void onAdFailed(AdError adError) {
            ModelessInterstitialAd.this.onAdFailedToLoadOrRender(adError);
        }

        public void onAdEvent(AdEvent event) {
        }

        public int adClosing() {
            return 2;
        }

        public void onAdExpired() {
            ModelessInterstitialAd.this.onAdExpired();
        }
    }
}
