package com.amazon.device.ads;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.amazon.device.ads.AdContainer;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdSize;
import com.amazon.device.ads.AdWebViewClient;
import com.amazon.device.ads.Metrics;
import com.amazon.device.ads.SDKEvent;
import com.amazon.device.ads.ThreadUtils;
import com.amazon.device.ads.WebRequest;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

class AdController implements Metrics.MetricsSubmitter {
    private static final String LOGTAG = AdController.class.getSimpleName();
    protected static final String MSG_PREPARE_AD_LOADING = "An ad is currently loading. Please wait for the ad to finish loading and showing before loading another ad.";
    protected static final String MSG_PREPARE_AD_READY_TO_SHOW = "An ad is ready to show. Please call showAd() to show the ad before loading another ad.";
    protected static final String MSG_PREPARE_AD_SHOWING = "An ad is currently showing. Please wait for the user to dismiss the ad before loading an ad.";
    protected static final String MSG_SHOW_AD_ANOTHER_SHOWING = "Another ad is currently showing. Please wait for the AdListener.onAdDismissed callback of the other ad.";
    protected static final String MSG_SHOW_AD_DESTROYED = "The ad cannot be shown because it has been destroyed. Create a new Ad object to load a new ad.";
    protected static final String MSG_SHOW_AD_DISMISSED = "The ad cannot be shown because it has already been displayed to the user. Please call loadAd(AdTargetingOptions) to load a new ad.";
    protected static final String MSG_SHOW_AD_EXPIRED = "This ad has expired. Please load another ad.";
    protected static final String MSG_SHOW_AD_LOADING = "The ad cannot be shown because it is still loading. Please wait for the AdListener.onAdLoaded() callback before showing the ad.";
    protected static final String MSG_SHOW_AD_READY_TO_LOAD = "The ad cannot be shown because it has not loaded successfully. Please call loadAd(AdTargetingOptions) to load an ad first.";
    protected static final String MSG_SHOW_AD_SHOWING = "The ad cannot be shown because it is already displayed on the screen. Please wait for the AdListener.onAdDismissed() callback and then load a new ad.";
    private Activity adActivity;
    private final AdCloser adCloser;
    private AdContainer adContainer;
    private final AdContainer.AdContainerFactory adContainerFactory;
    private AdControlAccessor adControlAccessor;
    private AdControlCallback adControlCallback;
    private AdData adData;
    private final AdHtmlPreprocessor adHtmlPreprocessor;
    private final AdSDKBridgeList adSdkBridgeList;
    private final AdSize adSize;
    private AdState adState;
    private final AdTimer adTimer;
    private final AdUrlLoader adUrlLoader;
    private final AdUtils2 adUtils;
    private int adWindowHeight;
    private int adWindowWidth;
    private final AndroidBuildInfo androidBuildInfo;
    private boolean backButtonOverridden;
    private final BridgeSelector bridgeSelector;
    private ConnectionInfo connectionInfo;
    private final Context context;
    private final DebugProperties debugProperties;
    private ViewGroup defaultParent;
    private boolean disableHardwareAccelerationRequest;
    private boolean forceDisableHardwareAcceleration;
    private final AtomicBoolean hasFinishedLoading;
    private final MobileAdsInfoStore infoStore;
    private boolean isModallyExpanded;
    private boolean isPrepared;
    private final AtomicBoolean isRendering;
    /* access modifiers changed from: private */
    public final MobileAdsLogger logger;
    private MetricsCollector metricsCollector;
    private boolean orientationFailureMetricRecorded;
    private int originalOrientation;
    private final PermissionChecker permissionChecker;
    private double scalingMultiplier;
    private final ArrayList<SDKEventListener> sdkEventListeners;
    private int timeout;
    protected final WebUtils2 webUtils;
    private boolean windowDimensionsSet;

    AdController(Context context2, AdSize adSize2) {
        this(context2, adSize2, new WebUtils2(), new MetricsCollector(), new MobileAdsLoggerFactory(), new AdUtils2(), new AdContainer.AdContainerFactory(), MobileAdsInfoStore.getInstance(), new PermissionChecker(), new AndroidBuildInfo(), BridgeSelector.getInstance(), new AdSDKBridgeList(), ThreadUtils.getThreadRunner(), new WebRequest.WebRequestFactory(), (AdHtmlPreprocessor) null, (AdUrlLoader) null, (AdCloser) null, new AdTimer(), DebugProperties.getInstance());
    }

    AdController(Context context2, AdSize adSize2, WebUtils2 webUtils2, MetricsCollector metricsCollector2, MobileAdsLoggerFactory loggerFactory, AdUtils2 adUtils2, AdContainer.AdContainerFactory adContainerFactory2, MobileAdsInfoStore infoStore2, PermissionChecker permissionChecker2, AndroidBuildInfo androidBuildInfo2, BridgeSelector bridgeSelector2, AdSDKBridgeList adSdkBridgeList2, ThreadUtils.ThreadRunner threadRunner, WebRequest.WebRequestFactory webRequestFactory, AdHtmlPreprocessor adHtmlPreprocessor2, AdUrlLoader adUrlLoader2, AdCloser adCloser2, AdTimer adTimer2, DebugProperties debugProperties2) {
        this(context2, adSize2, webUtils2, metricsCollector2, loggerFactory, adUtils2, adContainerFactory2, infoStore2, permissionChecker2, androidBuildInfo2, bridgeSelector2, adSdkBridgeList2, threadRunner, new AdWebViewClientFactory(webUtils2, loggerFactory, androidBuildInfo2), webRequestFactory, adHtmlPreprocessor2, adUrlLoader2, adCloser2, adTimer2, debugProperties2);
    }

    AdController(Context context2, AdSize adSize2, WebUtils2 webUtils2, MetricsCollector metricsCollector2, MobileAdsLoggerFactory loggerFactory, AdUtils2 adUtils2, AdContainer.AdContainerFactory adContainerFactory2, MobileAdsInfoStore infoStore2, PermissionChecker permissionChecker2, AndroidBuildInfo androidBuildInfo2, BridgeSelector bridgeSelector2, AdSDKBridgeList adSdkBridgeList2, ThreadUtils.ThreadRunner threadRunner, AdWebViewClientFactory adWebViewClientFactory, WebRequest.WebRequestFactory webRequestFactory, AdHtmlPreprocessor adHtmlPreprocessor2, AdUrlLoader adUrlLoader2, AdCloser adCloser2, AdTimer adTimer2, DebugProperties debugProperties2) {
        this.timeout = 20000;
        this.sdkEventListeners = new ArrayList<>();
        this.adWindowHeight = 0;
        this.adWindowWidth = 0;
        this.windowDimensionsSet = false;
        this.adState = AdState.READY_TO_LOAD;
        this.scalingMultiplier = 1.0d;
        this.isPrepared = false;
        this.defaultParent = null;
        this.isRendering = new AtomicBoolean(false);
        this.hasFinishedLoading = new AtomicBoolean(false);
        this.disableHardwareAccelerationRequest = false;
        this.forceDisableHardwareAcceleration = false;
        this.backButtonOverridden = false;
        this.isModallyExpanded = false;
        this.orientationFailureMetricRecorded = false;
        this.context = context2;
        this.adSize = adSize2;
        this.webUtils = webUtils2;
        this.metricsCollector = metricsCollector2;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        this.adUtils = adUtils2;
        this.adContainerFactory = adContainerFactory2;
        this.infoStore = infoStore2;
        this.permissionChecker = permissionChecker2;
        this.androidBuildInfo = androidBuildInfo2;
        this.bridgeSelector = bridgeSelector2;
        this.adTimer = adTimer2;
        this.debugProperties = debugProperties2;
        this.adSdkBridgeList = adSdkBridgeList2;
        if (adHtmlPreprocessor2 != null) {
            this.adHtmlPreprocessor = adHtmlPreprocessor2;
        } else {
            this.adHtmlPreprocessor = new AdHtmlPreprocessor(bridgeSelector2, this.adSdkBridgeList, getAdControlAccessor(), loggerFactory, adUtils2);
        }
        if (adUrlLoader2 != null) {
            this.adUrlLoader = adUrlLoader2;
        } else {
            ThreadUtils.ThreadRunner threadRunner2 = threadRunner;
            WebRequest.WebRequestFactory webRequestFactory2 = webRequestFactory;
            WebUtils2 webUtils22 = webUtils2;
            MobileAdsLoggerFactory mobileAdsLoggerFactory = loggerFactory;
            this.adUrlLoader = new AdUrlLoader(threadRunner2, adWebViewClientFactory.createAdWebViewClient(context2, this.adSdkBridgeList, getAdControlAccessor()), webRequestFactory2, getAdControlAccessor(), webUtils22, mobileAdsLoggerFactory, infoStore2.getDeviceInfo());
        }
        this.adUrlLoader.setAdWebViewClientListener(new AdControllerAdWebViewClientListener());
        if (adCloser2 != null) {
            this.adCloser = adCloser2;
        } else {
            this.adCloser = new AdCloser(this);
        }
    }

    /* access modifiers changed from: package-private */
    public AdContainer getAdContainer() {
        if (this.adContainer == null) {
            this.adContainer = createAdContainer();
            this.adContainer.disableHardwareAcceleration(shouldDisableHardwareAcceleration());
            this.adContainer.setAdWebViewClient(this.adUrlLoader.getAdWebViewClient());
        }
        return this.adContainer;
    }

    /* access modifiers changed from: package-private */
    public AdContainer createAdContainer() {
        return this.adContainerFactory.createAdContainer(this.context, this.adCloser);
    }

    /* access modifiers changed from: package-private */
    public AdControlCallback getAdControlCallback() {
        if (this.adControlCallback == null) {
            this.adControlCallback = new DefaultAdControlCallback();
        }
        return this.adControlCallback;
    }

    /* access modifiers changed from: package-private */
    public void setAdActivity(Activity activity) {
        this.adActivity = activity;
    }

    public void requestDisableHardwareAcceleration(boolean shouldDisable) {
        this.disableHardwareAccelerationRequest = shouldDisable;
        if (this.adContainer != null) {
            this.adContainer.disableHardwareAcceleration(shouldDisableHardwareAcceleration());
        }
    }

    private boolean shouldDisableHardwareAcceleration() {
        return this.forceDisableHardwareAcceleration || this.disableHardwareAccelerationRequest;
    }

    public AdControlAccessor getAdControlAccessor() {
        if (this.adControlAccessor == null) {
            this.adControlAccessor = new AdControlAccessor(this);
        }
        return this.adControlAccessor;
    }

    public MetricsCollector getMetricsCollector() {
        return this.metricsCollector;
    }

    public void resetMetricsCollector() {
        this.metricsCollector = new MetricsCollector();
    }

    public String getInstrumentationPixelUrl() {
        if (this.adData != null) {
            return this.adData.getInstrumentationPixelUrl();
        }
        return null;
    }

    public void setAdState(AdState adState2) {
        this.logger.d("Changing AdState from %s to %s", this.adState, adState2);
        this.adState = adState2;
    }

    public AdState getAdState() {
        return this.adState;
    }

    public boolean isVisible() {
        return AdState.SHOWING.equals(getAdState()) || AdState.EXPANDED.equals(getAdState());
    }

    public boolean isModal() {
        return getAdSize().isModal() || (AdState.EXPANDED.equals(getAdState()) && this.isModallyExpanded);
    }

    public void orientationChangeAttemptedWhenNotAllowed() {
        if (!this.orientationFailureMetricRecorded) {
            this.orientationFailureMetricRecorded = true;
            getMetricsCollector().incrementMetric(Metrics.MetricType.SET_ORIENTATION_FAILURE);
        }
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        if (this.adActivity == null) {
            return this.context;
        }
        return this.adActivity;
    }

    public void setOriginalOrientation(Activity activity) {
        this.originalOrientation = activity.getRequestedOrientation();
    }

    public int getOriginalOrientation() {
        return this.originalOrientation;
    }

    public boolean getAndResetIsPrepared() {
        boolean isPrepared2 = this.isPrepared;
        this.isPrepared = false;
        return isPrepared2;
    }

    public boolean isValid() {
        return !getAdState().equals(AdState.INVALID);
    }

    public AdData getAdData() {
        return this.adData;
    }

    public void setAdData(AdData adData2) {
        this.adData = adData2;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout2) {
        this.timeout = timeout2;
    }

    public AdSize getAdSize() {
        return this.adSize;
    }

    public int getWindowHeight() {
        return this.adWindowHeight;
    }

    public int getWindowWidth() {
        return this.adWindowWidth;
    }

    public void setWindowDimensions(int width, int height) {
        this.adWindowWidth = width;
        this.adWindowHeight = height;
        this.windowDimensionsSet = true;
    }

    /* access modifiers changed from: package-private */
    public void setViewDimensionsToAdDimensions() {
        if (this.adData != null) {
            int height = (int) (((double) this.adData.getHeight()) * getScalingMultiplier() * ((double) this.adUtils.getScalingFactorAsFloat()));
            if (height <= 0) {
                height = -1;
            }
            if (getAdSize().canUpscale()) {
                getAdContainer().setViewHeight(height);
                return;
            }
            getAdContainer().setViewLayoutParams((int) (((double) this.adData.getWidth()) * getScalingMultiplier() * ((double) this.adUtils.getScalingFactorAsFloat())), height, getAdSize().getGravity());
        }
    }

    public void setViewDimensionsToMatchParent() {
        getAdContainer().setViewLayoutParams(-1, -1, 17);
    }

    public boolean areWindowDimensionsSet() {
        return this.windowDimensionsSet;
    }

    public double getScalingMultiplier() {
        return this.scalingMultiplier;
    }

    public ConnectionInfo getConnectionInfo() {
        return this.connectionInfo;
    }

    public void setConnectionInfo(ConnectionInfo connectionInfo2) {
        this.connectionInfo = connectionInfo2;
    }

    public View getView() {
        return getAdContainer();
    }

    public Destroyable getDestroyable() {
        return getAdContainer();
    }

    public void stashView() {
        getAdContainer().stashView();
    }

    public boolean popView() {
        return getAdContainer().popView();
    }

    public int getViewWidth() {
        return getAdContainer().getWidth();
    }

    public int getViewHeight() {
        return getAdContainer().getHeight();
    }

    public String getMaxSize() {
        if (!getAdSize().isAuto()) {
            return null;
        }
        return AdSize.getAsSizeString(getWindowWidth(), getWindowHeight());
    }

    public String getScalingMultiplierDescription() {
        if (getScalingMultiplier() > 1.0d) {
            return "u";
        }
        if (getScalingMultiplier() >= 1.0d || getScalingMultiplier() <= 0.0d) {
            return "n";
        }
        return "d";
    }

    public void setCallback(AdControlCallback adControlCallback2) {
        this.adControlCallback = adControlCallback2;
    }

    public void addSDKEventListener(SDKEventListener listener) {
        this.logger.d("Add SDKEventListener %s", listener);
        this.sdkEventListeners.add(listener);
    }

    public void clearSDKEventListeners() {
        this.sdkEventListeners.clear();
    }

    public void resetToReady() {
        if (canBeUsed()) {
            this.adActivity = null;
            this.isPrepared = false;
            this.adTimer.cancelTimer();
            resetMetricsCollector();
            this.orientationFailureMetricRecorded = false;
            getAdContainer().destroy();
            this.adSdkBridgeList.clear();
            this.adData = null;
            setAdState(AdState.READY_TO_LOAD);
        }
    }

    private void reset() {
        if (canBeUsed()) {
            this.isPrepared = false;
            this.adTimer.cancelTimer();
            resetMetricsCollector();
            this.orientationFailureMetricRecorded = false;
            if (this.adContainer != null) {
                this.adContainer.destroy();
                this.adSdkBridgeList.clear();
                this.adContainer = null;
            }
            this.adData = null;
        }
    }

    public boolean canShowViews() {
        return getAdContainer().canShowViews();
    }

    public boolean prepareForAdLoad(long loadAdStartTime, boolean deferredLoad) {
        if (!canBeUsed()) {
            onRequestError("An ad could not be loaded because the view has been destroyed or was not created properly.");
            return false;
        } else if (!checkDefinedActivities()) {
            onRequestError("Ads cannot load unless \"com.amazon.device.ads.AdActivity\" is correctly declared as an activity in AndroidManifest.xml. Consult the online documentation for more info.");
            return false;
        } else if (!passesInternetPermissionCheck(this.context)) {
            onRequestError("Ads cannot load because the INTERNET permission is missing from the app's manifest.");
            return false;
        } else if (!isValidAppKey()) {
            onRequestError("Can't load an ad because Application Key has not been set. Did you forget to call AdRegistration.setAppKey( ... )?");
            return false;
        } else if (!getAdContainer().canShowViews()) {
            Metrics.getInstance().getMetricsCollector().incrementMetric(Metrics.MetricType.AD_FAILED_UNKNOWN_WEBVIEW_ISSUE);
            onRequestError("We will be unable to create a WebView for rendering an ad due to an unknown issue with the WebView.");
            return false;
        } else {
            if (!isReadyToLoad(deferredLoad)) {
                boolean failLoad = true;
                if (getAdState().equals(AdState.RENDERED)) {
                    if (isExpired()) {
                        failLoad = false;
                    } else {
                        this.logger.e(MSG_PREPARE_AD_READY_TO_SHOW);
                    }
                } else if (getAdState().equals(AdState.EXPANDED)) {
                    this.logger.e("An ad could not be loaded because another ad is currently expanded.");
                } else {
                    this.logger.e(MSG_PREPARE_AD_LOADING);
                }
                if (failLoad) {
                    return false;
                }
            }
            reset();
            getMetricsCollector().startMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_LATENCY_TOTAL, loadAdStartTime);
            getMetricsCollector().startMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_LATENCY_TOTAL_FAILURE, loadAdStartTime);
            getMetricsCollector().startMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_LATENCY_TOTAL_SUCCESS, loadAdStartTime);
            getMetricsCollector().startMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_LOAD_LATENCY_LOADAD_TO_FETCH_THREAD_REQUEST_START, loadAdStartTime);
            setAdState(AdState.LOADING);
            this.isRendering.set(false);
            setHasFinishedLoading(false);
            this.adTimer.restartTimer();
            this.adTimer.scheduleTask(new TimerTask() {
                public void run() {
                    AdController.this.onAdTimedOut();
                }
            }, (long) getTimeout());
            this.infoStore.getDeviceInfo().populateUserAgentString(this.context);
            this.isPrepared = true;
            return true;
        }
    }

    public void initialize() {
        if (canBeUsed()) {
            determineShouldForceDisableHardwareAcceleration();
            if (initializeAdContainer()) {
                calculateScalingMultiplier();
                Iterator<AAXCreative> it = this.adData.iterator();
                while (it.hasNext()) {
                    Set<AdSDKBridgeFactory> bridgeFactories = this.bridgeSelector.getBridgeFactories(it.next());
                    if (bridgeFactories != null) {
                        for (AdSDKBridgeFactory bridgeFactory : bridgeFactories) {
                            addAdSDKBridge(bridgeFactory.createAdSDKBridge(getAdControlAccessor()));
                        }
                    }
                }
                adLoaded();
            }
        }
    }

    private void addAdSDKBridge(AdSDKBridge adSdkbridge) {
        this.adSdkBridgeList.addBridge(adSdkbridge);
    }

    private void calculateScalingMultiplier() {
        if (!isInterstitial()) {
            float scalingDensity = this.infoStore.getDeviceInfo().getScalingFactorAsFloat();
            this.scalingMultiplier = this.adUtils.calculateScalingMultiplier((int) (((float) this.adData.getWidth()) * scalingDensity), (int) (((float) this.adData.getHeight()) * scalingDensity), getWindowWidth(), getWindowHeight());
            if (!getAdSize().canUpscale() && this.scalingMultiplier > 1.0d) {
                this.scalingMultiplier = 1.0d;
            }
            setViewDimensionsToAdDimensions();
            return;
        }
        this.scalingMultiplier = -1.0d;
    }

    private void determineShouldForceDisableHardwareAcceleration() {
        if ((AndroidTargetUtils.isAndroidAPI(this.androidBuildInfo, 14) || AndroidTargetUtils.isAndroidAPI(this.androidBuildInfo, 15)) && this.adData.getCreativeTypes().contains(AAXCreative.REQUIRES_TRANSPARENCY)) {
            this.forceDisableHardwareAcceleration = true;
        } else {
            this.forceDisableHardwareAcceleration = false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean initializeAdContainer() {
        try {
            getAdContainer().initialize();
            return true;
        } catch (IllegalStateException e) {
            adFailed(new AdError(AdError.ErrorCode.INTERNAL_ERROR, "An unknown error occurred when attempting to create the web view."));
            setAdState(AdState.INVALID);
            this.logger.e("An unknown error occurred when attempting to create the web view.");
            return false;
        }
    }

    public void render() {
        if (canBeUsed()) {
            setAdState(AdState.RENDERING);
            long renderStartTime = System.nanoTime();
            getMetricsCollector().stopMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_LOAD_LATENCY_FINALIZE_FETCH_START_TO_RENDER_START, renderStartTime);
            getMetricsCollector().startMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_LATENCY_RENDER, renderStartTime);
            this.isRendering.set(true);
            loadHtml("http://amazon-adsystem.amazon.com/", this.adData.getCreative());
        }
    }

    public void preloadHtml(String baseUrl, String html, PreloadCallback callback) {
        loadHtml(baseUrl, html, true, callback);
    }

    public void loadHtml(String baseUrl, String html) {
        loadHtml(baseUrl, html, false, (PreloadCallback) null);
    }

    public void loadHtml(String baseUrl, String html, boolean shouldPreload, PreloadCallback callback) {
        getAdContainer().removePreviousInterfaces();
        clearSDKEventListeners();
        getAdContainer().loadHtml(baseUrl, this.adHtmlPreprocessor.preprocessHtml(html, shouldPreload), shouldPreload, callback);
    }

    public void preloadUrl(String url, PreloadCallback callback) {
        this.adUrlLoader.loadUrl(url, true, callback);
    }

    public void loadUrl(String url) {
        this.adUrlLoader.loadUrl(url, false, (PreloadCallback) null);
    }

    public void openUrl(String url) {
        this.adUrlLoader.openUrl(url);
    }

    public void setExpanded(boolean isExpanded) {
        if (isExpanded) {
            setAdState(AdState.EXPANDED);
        } else {
            setAdState(AdState.SHOWING);
        }
    }

    public void injectJavascript(final String javascript, final boolean preload) {
        ThreadUtils.executeOnMainThread(new Runnable() {
            public void run() {
                AdController.this.getAdContainer().injectJavascript(javascript, preload);
            }
        });
    }

    public void destroy() {
        if (!canBeUsed()) {
            this.logger.e("The ad cannot be destroyed because it has already been destroyed.");
            return;
        }
        closeAd();
        this.adState = AdState.DESTROYED;
        if (this.adContainer != null) {
            getAdContainer().destroy();
            this.adSdkBridgeList.clear();
            this.adContainer = null;
        }
        this.isPrepared = false;
        this.metricsCollector = null;
        this.adData = null;
    }

    /* access modifiers changed from: protected */
    public boolean passesInternetPermissionCheck(Context context2) {
        return this.permissionChecker.hasInternetPermission(context2);
    }

    public void onRequestError(String message) {
        this.logger.e(message);
        adFailed(new AdError(AdError.ErrorCode.REQUEST_ERROR, message));
    }

    public boolean isExpired() {
        return this.adData != null && this.adData.isExpired();
    }

    public boolean canBeUsed() {
        return !AdState.DESTROYED.equals(getAdState()) && !AdState.INVALID.equals(getAdState());
    }

    private boolean isReadyToLoad(boolean deferredLoad) {
        return getAdControlCallback().isAdReady(deferredLoad);
    }

    public boolean startAdDrawing() {
        this.adTimer.cancelTimer();
        if (!AdState.RENDERED.equals(getAdState()) || !canExpireOrDraw(AdState.DRAWING)) {
            return false;
        }
        return true;
    }

    private synchronized boolean canExpireOrDraw(AdState newState) {
        boolean z;
        if (AdState.RENDERED.compareTo(getAdState()) >= 0) {
            setAdState(newState);
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public void adShown() {
        if (canBeUsed()) {
            getMetricsCollector().stopMetric(Metrics.MetricType.AD_SHOW_LATENCY);
            this.adTimer.cancelTimer();
            if (canFireImpressionPixel()) {
                this.webUtils.executeWebRequestInThread(getAdData().getImpressionPixelUrl(), false);
            }
            setAdState(AdState.SHOWING);
            if (!areWindowDimensionsSet()) {
                setWindowDimensions(getView().getWidth(), getView().getHeight());
            }
            fireSDKEvent(new SDKEvent(SDKEvent.SDKEventType.VISIBLE));
        }
    }

    private boolean canFireImpressionPixel() {
        return !getAdState().equals(AdState.HIDDEN);
    }

    public void adHidden() {
        setAdState(AdState.HIDDEN);
        fireSDKEvent(new SDKEvent(SDKEvent.SDKEventType.HIDDEN));
    }

    /* access modifiers changed from: package-private */
    public void onAdTimedOut() {
        if (this.debugProperties.getDebugPropertyAsBoolean(DebugProperties.DEBUG_CAN_TIMEOUT, true).booleanValue() && !getAndSetHasFinishedLoading(true)) {
            adFailedAfterTimerCheck(new AdError(AdError.ErrorCode.NETWORK_TIMEOUT, "Ad Load Timed Out"));
            setAdState(AdState.INVALID);
        }
    }

    /* access modifiers changed from: private */
    public void onAdExpired() {
        if (AdState.RENDERED.compareTo(getAdState()) >= 0 && canExpireOrDraw(AdState.INVALID)) {
            this.logger.d("Ad Has Expired");
            callOnAdExpired();
        }
    }

    private void callOnAdExpired() {
        ThreadUtils.scheduleOnMainThread(new Runnable() {
            public void run() {
                AdController.this.getAdControlCallback().onAdExpired();
                AdController.this.submitAndResetMetricsIfNecessary(true);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void adFailed(AdError error) {
        if (canBeUsed() && !getAndSetHasFinishedLoading(true)) {
            this.adTimer.cancelTimer();
            adFailedAfterTimerCheck(error);
            setAdState(AdState.READY_TO_LOAD);
        }
    }

    private void adFailedAfterTimerCheck(AdError error) {
        if (getMetricsCollector() == null || getMetricsCollector().isMetricsCollectorEmpty()) {
            adFailedBeforeAdMetricsStart(error);
        } else {
            adFailedAfterAdMetricsStart(error);
        }
    }

    private void adLoaded() {
        if (canBeUsed()) {
            setAdState(AdState.LOADED);
            callOnAdLoaded(this.adData.getProperties());
        }
    }

    /* access modifiers changed from: package-private */
    public void adFailedBeforeAdMetricsStart(AdError error) {
        callOnAdFailedToLoad(error, false);
    }

    /* access modifiers changed from: package-private */
    public void adFailedAfterAdMetricsStart(AdError error) {
        accumulateAdFailureMetrics(error);
        callOnAdFailedToLoad(error, true);
    }

    /* access modifiers changed from: package-private */
    public void accumulateAdFailureMetrics(AdError error) {
        long renderStopTime = System.nanoTime();
        getMetricsCollector().stopMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_LATENCY_TOTAL, renderStopTime);
        getMetricsCollector().stopMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_LOAD_LATENCY_FINALIZE_FETCH_START_TO_FAILURE, renderStopTime);
        getMetricsCollector().stopMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_LATENCY_TOTAL_FAILURE, renderStopTime);
        if (error != null && (AdError.ErrorCode.NO_FILL.equals(error.getCode()) || AdError.ErrorCode.NETWORK_ERROR.equals(error.getCode()) || AdError.ErrorCode.NETWORK_TIMEOUT.equals(error.getCode()) || AdError.ErrorCode.INTERNAL_ERROR.equals(error.getCode()))) {
            getMetricsCollector().incrementMetric(Metrics.MetricType.AD_LOAD_FAILED);
            if (error.getCode() == AdError.ErrorCode.NETWORK_TIMEOUT) {
                if (this.isRendering.get()) {
                    getMetricsCollector().incrementMetric(Metrics.MetricType.AD_LOAD_FAILED_ON_PRERENDERING_TIMEOUT);
                } else {
                    getMetricsCollector().incrementMetric(Metrics.MetricType.AD_LOAD_FAILED_ON_AAX_CALL_TIMEOUT);
                }
            }
        }
        getMetricsCollector().stopMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_LATENCY_RENDER_FAILED, renderStopTime);
        if (getAdState().equals(AdState.RENDERING)) {
            getMetricsCollector().incrementMetric(Metrics.MetricType.AD_COUNTER_RENDERING_FATAL);
        }
        setAdditionalMetrics();
    }

    public void adRendered(String url) {
        if (canBeUsed()) {
            this.logger.d("Ad Rendered");
            if (!getAdState().equals(AdState.RENDERING)) {
                this.logger.d("Ad State was not Rendering. It was " + getAdState());
            } else if (!getAndSetHasFinishedLoading(true)) {
                this.isRendering.set(false);
                this.adTimer.cancelTimer();
                startExpirationTimer();
                setAdState(AdState.RENDERED);
                callOnAdRendered();
                long renderStopTime = System.nanoTime();
                if (getMetricsCollector() != null) {
                    getMetricsCollector().stopMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_LATENCY_RENDER, renderStopTime);
                    getMetricsCollector().stopMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_LATENCY_TOTAL, renderStopTime);
                    getMetricsCollector().stopMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_LATENCY_TOTAL_SUCCESS, renderStopTime);
                    setAdditionalMetrics();
                    submitAndResetMetricsIfNecessary(true);
                }
                callPostAdRendered();
            }
            fireSDKEvent(new SDKEvent(SDKEvent.SDKEventType.RENDERED).setParameter(ImagesContract.URL, url));
        }
    }

    private void startExpirationTimer() {
        long timeToExpire = getAdData().getTimeToExpire();
        if (timeToExpire > 0) {
            this.adTimer.restartTimer();
            this.adTimer.scheduleTask(new TimerTask() {
                public void run() {
                    AdController.this.onAdExpired();
                }
            }, timeToExpire);
        }
    }

    /* access modifiers changed from: package-private */
    public void callOnAdFailedToLoad(final AdError error, final boolean shouldSubmitMetrics) {
        ThreadUtils.scheduleOnMainThread(new Runnable() {
            public void run() {
                AdController.this.getAdControlCallback().onAdFailed(error);
                AdController.this.submitAndResetMetricsIfNecessary(shouldSubmitMetrics);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void callOnAdLoaded(final AdProperties adProperties) {
        ThreadUtils.scheduleOnMainThread(new Runnable() {
            public void run() {
                if (AdController.this.canBeUsed()) {
                    AdController.this.getAdControlCallback().onAdLoaded(adProperties);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void callOnAdRendered() {
        ThreadUtils.scheduleOnMainThread(new Runnable() {
            public void run() {
                if (AdController.this.canBeUsed()) {
                    AdController.this.getAdControlCallback().onAdRendered();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void callPostAdRendered() {
        ThreadUtils.scheduleOnMainThread(new Runnable() {
            public void run() {
                if (AdController.this.canBeUsed()) {
                    AdController.this.getAdControlCallback().postAdRendered();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void callOnAdEvent(final AdEvent adEvent) {
        ThreadUtils.scheduleOnMainThread(new Runnable() {
            public void run() {
                if (AdController.this.canBeUsed()) {
                    AdController.this.getAdControlCallback().onAdEvent(adEvent);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void setHasFinishedLoading(boolean hasFinishedLoading2) {
        this.hasFinishedLoading.set(hasFinishedLoading2);
    }

    /* access modifiers changed from: package-private */
    public boolean getAndSetHasFinishedLoading(boolean hasFinishedLoading2) {
        return this.hasFinishedLoading.getAndSet(hasFinishedLoading2);
    }

    public void fireAdEvent(AdEvent event) {
        this.logger.d("Firing AdEvent of type %s", event.getAdEventType());
        callOnAdEvent(event);
    }

    public void fireSDKEvent(SDKEvent event) {
        this.logger.d("Firing SDK Event of type %s", event.getEventType());
        Iterator i$ = this.sdkEventListeners.iterator();
        while (i$.hasNext()) {
            i$.next().onSDKEvent(event, getAdControlAccessor());
        }
    }

    public boolean closeAd() {
        return this.adCloser.closeAd();
    }

    public void enableNativeCloseButton(boolean showImage, RelativePosition position) {
        getAdContainer().enableNativeCloseButton(showImage, position);
    }

    public void removeNativeCloseButton() {
        getAdContainer().removeNativeCloseButton();
    }

    public void showNativeCloseButtonImage(boolean showNativeCloseButtonImage) {
        getAdContainer().showNativeCloseButtonImage(showNativeCloseButtonImage);
    }

    /* access modifiers changed from: protected */
    public void setAdditionalMetrics() {
        this.adUtils.setConnectionMetrics(getConnectionInfo(), getMetricsCollector());
        if (getWindowHeight() == 0) {
            getMetricsCollector().incrementMetric(Metrics.MetricType.ADLAYOUT_HEIGHT_ZERO);
        }
        getMetricsCollector().setMetricString(Metrics.MetricType.VIEWPORT_SCALE, getScalingMultiplierDescription());
    }

    public void submitAndResetMetrics() {
        Metrics.getInstance().submitAndResetMetrics(this);
    }

    public void submitAndResetMetricsIfNecessary(boolean shouldSubmitMetrics) {
        if (shouldSubmitMetrics) {
            submitAndResetMetrics();
        }
    }

    public void moveViewToViewGroup(ViewGroup newViewGroup, ViewGroup.LayoutParams layoutParams, boolean isModal) {
        ViewGroup currentParent = getViewParent();
        if (this.defaultParent == null) {
            this.defaultParent = currentParent;
        }
        if (currentParent != null) {
            currentParent.removeView(getView());
        }
        setViewDimensionsToMatchParent();
        newViewGroup.addView(getView(), layoutParams);
        this.isModallyExpanded = isModal;
        setExpanded(true);
        if (this.isModallyExpanded) {
            captureBackButton();
        }
    }

    public void captureBackButton() {
        getAdContainer().listenForKey(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode != 4 || event.getRepeatCount() != 0) {
                    return false;
                }
                AdController.this.onBackButtonPress();
                return true;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean onBackButtonPress() {
        if (this.backButtonOverridden) {
            fireSDKEvent(new SDKEvent(SDKEvent.SDKEventType.BACK_BUTTON_PRESSED));
            return true;
        }
        closeAd();
        return false;
    }

    /* access modifiers changed from: package-private */
    public ViewGroup getViewParent() {
        return (ViewGroup) getView().getParent();
    }

    /* access modifiers changed from: package-private */
    public ViewGroup getViewParentIfExpanded() {
        if (this.defaultParent == null || this.defaultParent == getView().getParent()) {
            return null;
        }
        return getViewParent();
    }

    public void moveViewBackToParent(ViewGroup.LayoutParams params) {
        ViewGroup parent = (ViewGroup) getView().getParent();
        if (parent != null) {
            parent.removeView(getView());
        }
        setViewDimensionsToAdDimensions();
        if (this.defaultParent != null) {
            this.defaultParent.addView(getView(), params);
        }
        getAdContainer().listenForKey((View.OnKeyListener) null);
        setExpanded(false);
    }

    /* access modifiers changed from: package-private */
    public boolean checkDefinedActivities() {
        return this.adUtils.checkDefinedActivities(getContext().getApplicationContext());
    }

    /* access modifiers changed from: package-private */
    public boolean isValidAppKey() {
        return this.infoStore.getRegistrationInfo().getAppKey() != null;
    }

    /* access modifiers changed from: package-private */
    public Position getAdPosition() {
        int adWidth = getViewWidth();
        int adHeight = getViewHeight();
        if (adWidth == 0 && adHeight == 0) {
            adWidth = getWindowWidth();
            adHeight = getWindowHeight();
        }
        int width = this.adUtils.pixelToDeviceIndependentPixel(adWidth);
        int height = this.adUtils.pixelToDeviceIndependentPixel(adHeight);
        int[] onScreen = new int[2];
        getAdContainer().getLocationOnScreen(onScreen);
        View rootView = ((Activity) getContext()).findViewById(16908290);
        if (rootView == null) {
            this.logger.w("Could not find the activity's root view while determining ad position.");
            return null;
        }
        int[] rootViewPos = new int[2];
        rootView.getLocationOnScreen(rootViewPos);
        return new Position(new Size(width, height), this.adUtils.pixelToDeviceIndependentPixel(onScreen[0]), this.adUtils.pixelToDeviceIndependentPixel(onScreen[1] - rootViewPos[1]));
    }

    /* access modifiers changed from: package-private */
    public boolean isInterstitial() {
        if (AdSize.SizeType.INTERSTITIAL.equals(this.adSize.getSizeType())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public Size getMaxExpandableSize() {
        FrameLayout rootView = (FrameLayout) ((Activity) getContext()).findViewById(16908290);
        if (rootView == null) {
            this.logger.w("Could not find the activity's root view while determining max expandable size.");
            return null;
        }
        return new Size(this.adUtils.pixelToDeviceIndependentPixel(rootView.getWidth()), this.adUtils.pixelToDeviceIndependentPixel(rootView.getHeight()));
    }

    /* access modifiers changed from: package-private */
    public Size getScreenSize() {
        DisplayMetrics metrics = new DisplayMetrics();
        getMetrics(metrics);
        return new Size(this.adUtils.pixelToDeviceIndependentPixel(metrics.widthPixels), this.adUtils.pixelToDeviceIndependentPixel(metrics.heightPixels));
    }

    /* access modifiers changed from: package-private */
    public void getMetrics(DisplayMetrics metrics) {
        ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getMetrics(metrics);
    }

    /* access modifiers changed from: package-private */
    public void addJavascriptInterface(Object jsif, boolean shouldPreload, String interfaceName) {
        getAdContainer().addJavascriptInterface(jsif, shouldPreload, interfaceName);
    }

    /* access modifiers changed from: package-private */
    public void reload() {
        getAdContainer().reload();
    }

    /* access modifiers changed from: package-private */
    public void putUrlExecutorInAdWebViewClient(String scheme, AdWebViewClient.UrlExecutor executor) {
        this.adUrlLoader.putUrlExecutorInAdWebViewClient(scheme, executor);
    }

    public void overrideBackButton(boolean override) {
        this.backButtonOverridden = override;
    }

    public void setAllowClicks(boolean allowClicks) {
        getAdContainer().setAllowClicks(allowClicks);
    }

    class DefaultAdControlCallback implements AdControlCallback {
        DefaultAdControlCallback() {
        }

        public boolean isAdReady(boolean deferredLoad) {
            AdController.this.logger.d("DefaultAdControlCallback isAdReady called");
            return AdController.this.getAdState().equals(AdState.READY_TO_LOAD) || AdController.this.getAdState().equals(AdState.SHOWING);
        }

        public void onAdLoaded(AdProperties adProperties) {
            AdController.this.logger.d("DefaultAdControlCallback onAdLoaded called");
        }

        public void onAdRendered() {
            AdController.this.logger.d("DefaultAdControlCallback onAdRendered called");
        }

        public void postAdRendered() {
            AdController.this.logger.d("DefaultAdControlCallback postAdRendered called");
        }

        public void onAdFailed(AdError adError) {
            AdController.this.logger.d("DefaultAdControlCallback onAdFailed called");
        }

        public void onAdEvent(AdEvent event) {
            AdController.this.logger.d("DefaultAdControlCallback onAdEvent called");
        }

        public int adClosing() {
            AdController.this.logger.d("DefaultAdControlCallback adClosing called");
            return 1;
        }

        public void onAdExpired() {
            AdController.this.logger.d("DefaultAdControlCallback onAdExpired called");
        }
    }

    private class AdControllerAdWebViewClientListener implements AdWebViewClient.AdWebViewClientListener {
        private AdControllerAdWebViewClientListener() {
        }

        public void onPageFinished(WebView webView, String url) {
            if (AdController.this.getAdContainer().isCurrentView(webView)) {
                AdController.this.adRendered(url);
            }
        }

        public void onPageStarted(WebView view, String url) {
        }

        public void onLoadResource(WebView view, String url) {
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        }
    }
}
