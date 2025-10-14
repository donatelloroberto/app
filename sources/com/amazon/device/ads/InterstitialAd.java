package com.amazon.device.ads;

import android.app.Activity;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdProperties;
import com.amazon.device.ads.Metrics;
import java.util.concurrent.atomic.AtomicBoolean;

public class InterstitialAd implements Ad {
    protected static final String ACTION_INTERSTITIAL_DISMISSED = "dismissed";
    protected static final String ACTION_INTERSTITIAL_FINISHED_LOADING = "finished";
    protected static final String BROADCAST_ACTION = "action";
    protected static final String BROADCAST_CREATIVE = "creative";
    protected static final String BROADCAST_INTENT = "amazon.mobile.ads.interstitial";
    protected static final String BROADCAST_UNIQUE_IDENTIFIER_KEY = "uniqueIdentifier";
    private static final String LOGTAG = InterstitialAd.class.getSimpleName();
    protected static final String MSG_PREPARE_AD_DESTROYED = "This interstitial ad has been destroyed and can no longer be used. Create a new InterstitialAd object to load a new ad.";
    protected static final String MSG_PREPARE_AD_LOADING = "An interstitial ad is currently loading. Please wait for the ad to finish loading and showing before loading another ad.";
    protected static final String MSG_PREPARE_AD_READY_TO_SHOW = "An interstitial ad is ready to show. Please call showAd() to show the ad before loading another ad.";
    protected static final String MSG_PREPARE_AD_SHOWING = "An interstitial ad is currently showing. Please wait for the user to dismiss the ad before loading an ad.";
    protected static final String MSG_SHOW_AD_ANOTHER_SHOWING = "Another interstitial ad is currently showing. Please wait for the InterstitialAdListener.onAdDismissed callback of the other ad.";
    protected static final String MSG_SHOW_AD_DESTROYED = "The interstitial ad cannot be shown because it has been destroyed. Create a new InterstitialAd object to load a new ad.";
    protected static final String MSG_SHOW_AD_DISMISSED = "The interstitial ad cannot be shown because it has already been displayed to the user. Please call loadAd(AdTargetingOptions) to load a new ad.";
    protected static final String MSG_SHOW_AD_EXPIRED = "This interstitial ad has expired. Please load another ad.";
    protected static final String MSG_SHOW_AD_LOADING = "The interstitial ad cannot be shown because it is still loading. Please wait for the AdListener.onAdLoaded() callback before showing the ad.";
    protected static final String MSG_SHOW_AD_READY_TO_LOAD = "The interstitial ad cannot be shown because it has not loaded successfully. Please call loadAd(AdTargetingOptions) to load an ad first.";
    protected static final String MSG_SHOW_AD_SHOWING = "The interstitial ad cannot be shown because it is already displayed on the screen. Please wait for the InterstitialAdListener.onAdDismissed() callback and then load a new ad.";
    private static final AtomicBoolean isAdShowing = new AtomicBoolean(false);
    private final Activity activity;
    /* access modifiers changed from: private */
    public AdController adController;
    private final AdControllerFactory adControllerFactory;
    private AdListenerExecutor adListenerExecutor;
    private final AdListenerExecutorFactory adListenerExecutorFactory;
    private final AdLoadStarter adLoadStarter;
    private final AdRegistrationExecutor adRegistration;
    private final IntentBuilderFactory intentBuilderFactory;
    private boolean isInitialized;
    private boolean isThisAdShowing;
    private final MobileAdsLogger logger;
    private final MobileAdsLoggerFactory loggerFactory;
    /* access modifiers changed from: private */
    public final AtomicBoolean previousAdExpired;
    private int timeout;

    public InterstitialAd(Activity activity2) {
        this(activity2, new MobileAdsLoggerFactory(), new AdControllerFactory(), new IntentBuilderFactory(), AdRegistration.getAmazonAdRegistrationExecutor(), new AdLoadStarter());
    }

    InterstitialAd(Activity activity2, MobileAdsLoggerFactory loggerFactory2, AdControllerFactory adControllerFactory2, IntentBuilderFactory intentBuilderFactory2, AdRegistrationExecutor adRegistration2, AdLoadStarter adLoadStarter2) {
        this(activity2, loggerFactory2, new AdListenerExecutorFactory(loggerFactory2), adControllerFactory2, intentBuilderFactory2, adRegistration2, adLoadStarter2);
    }

    InterstitialAd(Activity activity2, MobileAdsLoggerFactory loggerFactory2, AdListenerExecutorFactory adListenerExecutorFactory2, AdControllerFactory adControllerFactory2, IntentBuilderFactory intentBuilderFactory2, AdRegistrationExecutor adRegistration2, AdLoadStarter adLoadStarter2) {
        this.isThisAdShowing = false;
        this.timeout = 20000;
        this.isInitialized = false;
        this.previousAdExpired = new AtomicBoolean(false);
        if (activity2 == null) {
            throw new IllegalArgumentException("InterstitialAd requires a non-null Activity");
        }
        this.activity = activity2;
        this.loggerFactory = loggerFactory2;
        this.logger = this.loggerFactory.createMobileAdsLogger(LOGTAG);
        this.adListenerExecutorFactory = adListenerExecutorFactory2;
        this.adControllerFactory = adControllerFactory2;
        this.intentBuilderFactory = intentBuilderFactory2;
        this.adRegistration = adRegistration2;
        this.adLoadStarter = adLoadStarter2;
    }

    private void initializeIfNecessary() {
        if (!isInitialized()) {
            this.isInitialized = true;
            this.adRegistration.initializeAds(this.activity.getApplicationContext());
            if (this.adListenerExecutor == null) {
                setListener((AdListener) null);
            }
            initializeAdController();
            setAdditionalMetrics();
        }
    }

    private void initializeAdController() {
        setAdController(createAdController(this.activity));
    }

    private boolean isInitialized() {
        return this.isInitialized;
    }

    /* access modifiers changed from: private */
    public AdController getAdController() {
        initializeIfNecessary();
        if (this.adController == null) {
            initializeAdController();
        }
        return this.adController;
    }

    static void resetIsAdShowing() {
        isAdShowing.set(false);
    }

    public void setListener(AdListener adListener) {
        if (adListener == null) {
            adListener = new DefaultAdListener(LOGTAG);
        }
        this.adListenerExecutor = this.adListenerExecutorFactory.createAdListenerExecutor(adListener);
    }

    public boolean loadAd() {
        return loadAd((AdTargetingOptions) null);
    }

    public boolean loadAd(AdTargetingOptions options) {
        didAdActivityFail();
        if (!isReadyToLoad()) {
            switch (getAdController().getAdState()) {
                case RENDERED:
                    this.logger.w(MSG_PREPARE_AD_READY_TO_SHOW);
                    return false;
                case SHOWING:
                    this.logger.w(MSG_PREPARE_AD_SHOWING);
                    return false;
                case INVALID:
                    if (getAdController().isExpired()) {
                        getAdController().resetToReady();
                        return loadAd(options);
                    }
                    this.logger.e("An interstitial ad could not be loaded because of an unknown issue with the web views.");
                    return false;
                case DESTROYED:
                    this.logger.e("An interstitial ad could not be loaded because the view has been destroyed.");
                    return false;
                default:
                    this.logger.w(MSG_PREPARE_AD_LOADING);
                    return false;
            }
        } else {
            this.previousAdExpired.set(false);
            this.adLoadStarter.loadAds(getTimeout(), options, new AdSlot(getAdController(), options));
            return getAdController().getAndResetIsPrepared();
        }
    }

    /* access modifiers changed from: private */
    public MetricsCollector getMetricsCollector() {
        return getAdController().getMetricsCollector();
    }

    public static boolean isAdShowing() {
        return isAdShowing.get();
    }

    public boolean isLoading() {
        return getAdController().getAdState().equals(AdState.LOADING) || getAdController().getAdState().equals(AdState.LOADED) || getAdController().getAdState().equals(AdState.RENDERING);
    }

    public boolean isShowing() {
        return getAdController().getAdState().equals(AdState.SHOWING);
    }

    /* access modifiers changed from: package-private */
    public boolean isReadyToLoad() {
        return getAdController().getAdState().equals(AdState.READY_TO_LOAD);
    }

    /* access modifiers changed from: package-private */
    public boolean isReadyToShow() {
        return getAdController().getAdState().equals(AdState.RENDERED);
    }

    public boolean isReady() {
        return isReadyToShow() && !getAdController().isExpired();
    }

    /* access modifiers changed from: package-private */
    public boolean didAdActivityFail() {
        boolean isFailed = this.isThisAdShowing && !isAdShowing.get();
        if (isFailed) {
            getMetricsCollector().incrementMetric(Metrics.MetricType.INTERSTITIAL_AD_ACTIVITY_FAILED);
            getAdController().closeAd();
        }
        return isFailed;
    }

    public boolean showAd() {
        if (didAdActivityFail()) {
            this.logger.e("The ad could not be shown because it previously failed to show. Please load a new ad.");
            return false;
        } else if (this.previousAdExpired.get()) {
            this.logger.w(MSG_SHOW_AD_EXPIRED);
            return false;
        } else {
            long renderLatencyStartTime = System.nanoTime();
            if (!isReadyToShow()) {
                if (isReadyToLoad()) {
                    this.logger.w(MSG_SHOW_AD_READY_TO_LOAD);
                } else if (isLoading()) {
                    this.logger.w(MSG_SHOW_AD_LOADING);
                } else if (isShowing()) {
                    this.logger.w(MSG_SHOW_AD_SHOWING);
                } else {
                    this.logger.w("An interstitial ad is not ready to show.");
                }
                return false;
            } else if (getAdController().isExpired()) {
                this.logger.w(MSG_SHOW_AD_EXPIRED);
                return false;
            } else if (isAdShowing.getAndSet(true)) {
                this.logger.w(MSG_SHOW_AD_ANOTHER_SHOWING);
                return false;
            } else if (getAdController().startAdDrawing()) {
                this.isThisAdShowing = true;
                getMetricsCollector().stopMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_LOADED_TO_AD_SHOW_TIME, renderLatencyStartTime);
                getMetricsCollector().startMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_SHOW_DURATION, renderLatencyStartTime);
                AdControllerFactory.cacheAdController(getAdController());
                getMetricsCollector().startMetric(Metrics.MetricType.AD_SHOW_LATENCY);
                boolean activityShown = showAdInActivity();
                if (activityShown) {
                    return activityShown;
                }
                clearCachedAdController();
                getAdController().resetToReady();
                isAdShowing.set(false);
                this.isThisAdShowing = false;
                getMetricsCollector().stopMetric(Metrics.MetricType.AD_LATENCY_RENDER_FAILED);
                return activityShown;
            } else {
                this.logger.w("Interstitial ad could not be shown.");
                return false;
            }
        }
    }

    private void clearCachedAdController() {
        AdControllerFactory.removeCachedAdController();
    }

    /* access modifiers changed from: package-private */
    public boolean showAdInActivity() {
        boolean isSuccess = this.intentBuilderFactory.createIntentBuilder().withClass(AdActivity.class).withContext(this.activity.getApplicationContext()).withActivity(this.activity).withExtra("adapter", InterstitialAdActivityAdapter.class.getName()).fireIntent();
        if (!isSuccess) {
            this.logger.e("Failed to show the interstitial ad because AdActivity could not be found.");
        }
        return isSuccess;
    }

    /* access modifiers changed from: package-private */
    public AdController createAdController(Activity activity2) {
        return this.adControllerFactory.buildAdController(activity2, AdSize.SIZE_INTERSTITIAL);
    }

    private void setAdController(AdController adController2) {
        this.adController = adController2;
        adController2.setCallback(constructAdControlCallback());
    }

    /* access modifiers changed from: package-private */
    public AdControlCallback constructAdControlCallback() {
        return new InterstitialAdControlCallback();
    }

    /* access modifiers changed from: package-private */
    public void handleDismissed() {
        getMetricsCollector().stopMetric(Metrics.MetricType.AD_SHOW_DURATION);
        AdControllerFactory.removeCachedAdController();
        isAdShowing.set(false);
        this.isThisAdShowing = false;
        callOnAdDismissedOnMainThread();
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout2) {
        this.timeout = timeout2;
    }

    /* access modifiers changed from: private */
    public void callOnAdLoaded(AdProperties adProperties) {
        this.adListenerExecutor.onAdLoaded(this, adProperties);
    }

    /* access modifiers changed from: package-private */
    public void callOnAdLoadedOnMainThread(final AdProperties adProperties) {
        ThreadUtils.executeOnMainThread(new Runnable() {
            public void run() {
                InterstitialAd.this.callOnAdLoaded(adProperties);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void callOnAdFailedToLoad(AdError error) {
        this.adListenerExecutor.onAdFailedToLoad(this, error);
    }

    /* access modifiers changed from: package-private */
    public void callOnAdFailedOnMainThread(final AdError error) {
        ThreadUtils.executeOnMainThread(new Runnable() {
            public void run() {
                InterstitialAd.this.callOnAdFailedToLoad(error);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void callOnAdDismissed() {
        this.adListenerExecutor.onAdDismissed(this);
    }

    /* access modifiers changed from: package-private */
    public void callOnAdDismissedOnMainThread() {
        ThreadUtils.executeOnMainThread(new Runnable() {
            public void run() {
                InterstitialAd.this.callOnAdDismissed();
                InterstitialAd.this.submitAndResetMetrics();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void callOnAdExpiredOnMainThread() {
        ThreadUtils.executeOnMainThread(new Runnable() {
            public void run() {
                InterstitialAd.this.callOnAdExpired();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void callOnAdExpired() {
        this.adListenerExecutor.onAdExpired(this);
    }

    /* access modifiers changed from: package-private */
    public void submitAndResetMetrics() {
        if (getMetricsCollector() != null && !getMetricsCollector().isMetricsCollectorEmpty()) {
            setAdditionalMetrics();
            getAdController().submitAndResetMetricsIfNecessary(true);
        }
    }

    /* access modifiers changed from: private */
    public void setAdditionalMetrics() {
        getMetricsCollector().setAdType(AdProperties.AdType.INTERSTITIAL);
        getMetricsCollector().incrementMetric(Metrics.MetricType.AD_IS_INTERSTITIAL);
    }

    class InterstitialAdControlCallback implements AdControlCallback {
        private AdProperties adProperties;

        InterstitialAdControlCallback() {
        }

        public void onAdLoaded(AdProperties adProperties2) {
            this.adProperties = adProperties2;
            InterstitialAd.this.setAdditionalMetrics();
            InterstitialAd.this.getAdController().enableNativeCloseButton(true, RelativePosition.TOP_RIGHT);
            InterstitialAd.this.getAdController().render();
        }

        public void onAdRendered() {
            InterstitialAd.this.callOnAdLoadedOnMainThread(this.adProperties);
        }

        public void onAdFailed(AdError adError) {
            if (AdError.ErrorCode.NETWORK_TIMEOUT.equals(adError.getCode())) {
                AdController unused = InterstitialAd.this.adController = null;
            }
            InterstitialAd.this.callOnAdFailedOnMainThread(adError);
        }

        public void onAdEvent(AdEvent event) {
        }

        public boolean isAdReady(boolean deferredLoad) {
            return InterstitialAd.this.isReadyToLoad();
        }

        public int adClosing() {
            InterstitialAd.this.handleDismissed();
            return 1;
        }

        public void postAdRendered() {
            InterstitialAd.this.getMetricsCollector().startMetric(Metrics.MetricType.AD_LOADED_TO_AD_SHOW_TIME);
        }

        public void onAdExpired() {
            InterstitialAd.this.getMetricsCollector().incrementMetric(Metrics.MetricType.AD_EXPIRED_BEFORE_SHOWING);
            InterstitialAd.this.previousAdExpired.set(true);
            AdController unused = InterstitialAd.this.adController = null;
            InterstitialAd.this.callOnAdExpiredOnMainThread();
        }
    }
}
