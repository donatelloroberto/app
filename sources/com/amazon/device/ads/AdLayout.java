package com.amazon.device.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.Metrics;
import com.amazon.device.ads.MobileAdsLogger;
import java.util.Locale;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressLint({"InlinedApi"})
public class AdLayout extends FrameLayout implements Ad {
    private static final String CONTENT_DESCRIPTION_AD_LAYOUT = "adLayoutObject";
    public static final int DEFAULT_TIMEOUT = 20000;
    static final String LAYOUT_NOT_RUN_ERR_MSG = "Can't load an ad because the view size cannot be determined.";
    static final String LAYOUT_PARAMS_NULL_ERR_MSG = "Can't load an ad because layout parameters are blank. Use setLayoutParams() to specify dimensions for this AdLayout.";
    static final String LOADING_IN_PROGRESS_LOG_MSG = "Can't load an ad because ad loading is already in progress";
    private static final String LOGTAG = AdLayout.class.getSimpleName();
    private static ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(1);
    /* access modifiers changed from: private */
    public View activityRootView;
    /* access modifiers changed from: private */
    public AdController adController;
    private final AdControllerFactory adControllerFactory;
    private AdListenerExecutor adListenerExecutor;
    private final AdListenerExecutorFactory adListenerExecutorFactory;
    private final AdLoadStarter adLoadStarter;
    private final AdRegistrationExecutor adRegistration;
    private final AdSize adSize;
    private AdTargetingOptions adTargetingOptions;
    private boolean attached;
    private final Context context;
    /* access modifiers changed from: private */
    public Destroyable currentDestroyable;
    /* access modifiers changed from: private */
    public View currentView;
    private boolean hasRegisterBroadcastReciever;
    private boolean isDestroyed;
    /* access modifiers changed from: private */
    public boolean isInForeground;
    private boolean isInitialized;
    private boolean isParentViewMissingAtLoadTime;
    private int lastVisibility;
    private final MobileAdsLogger logger;
    private final MobileAdsLoggerFactory loggerFactory;
    private AtomicBoolean needsToLoadAdOnLayout;
    private BroadcastReceiver screenStateReceiver;
    private boolean shouldDisableWebViewHardwareAcceleration;

    static {
        threadPool.setKeepAliveTime(60, TimeUnit.SECONDS);
    }

    public AdLayout(Activity activity) {
        this(activity, AdSize.SIZE_AUTO);
    }

    public AdLayout(Activity activity, AdSize adSize2) {
        this(activity, adSize2, new MobileAdsLoggerFactory(), new AdControllerFactory(), AdRegistration.getAmazonAdRegistrationExecutor(), new AdLoadStarter());
    }

    AdLayout(Activity activity, AdSize adSize2, MobileAdsLoggerFactory loggerFactory2, AdControllerFactory adControllerFactory2, AdRegistrationExecutor adRegistration2, AdLoadStarter adLoadStarter2) {
        this(activity, adSize2, loggerFactory2, new AdListenerExecutorFactory(loggerFactory2), adControllerFactory2, adRegistration2, adLoadStarter2);
    }

    AdLayout(Activity activity, AdSize adSize2, MobileAdsLoggerFactory loggerFactory2, AdListenerExecutorFactory adListenerExecutorFactory2, AdControllerFactory adControllerFactory2, AdRegistrationExecutor adRegistration2, AdLoadStarter adLoadStarter2) {
        super(activity);
        this.hasRegisterBroadcastReciever = false;
        this.attached = false;
        this.lastVisibility = 8;
        this.needsToLoadAdOnLayout = new AtomicBoolean(false);
        this.isParentViewMissingAtLoadTime = false;
        this.activityRootView = null;
        this.adTargetingOptions = null;
        this.isDestroyed = false;
        this.isInitialized = false;
        this.context = activity;
        this.adSize = adSize2;
        this.loggerFactory = loggerFactory2;
        this.logger = this.loggerFactory.createMobileAdsLogger(LOGTAG);
        this.adListenerExecutorFactory = adListenerExecutorFactory2;
        this.adControllerFactory = adControllerFactory2;
        this.adRegistration = adRegistration2;
        this.adLoadStarter = adLoadStarter2;
    }

    public AdLayout(Context context2, AttributeSet attrs) {
        this(context2, attrs, new MobileAdsLoggerFactory(), new AdControllerFactory(), AdRegistration.getAmazonAdRegistrationExecutor(), new AdLoadStarter());
    }

    AdLayout(Context context2, AttributeSet attrs, MobileAdsLoggerFactory loggerFactory2, AdControllerFactory adControllerFactory2, AdRegistrationExecutor adRegistration2, AdLoadStarter adLoadStarter2) {
        this(context2, attrs, loggerFactory2, new AdListenerExecutorFactory(loggerFactory2), adControllerFactory2, adRegistration2, adLoadStarter2);
    }

    AdLayout(Context context2, AttributeSet attrs, MobileAdsLoggerFactory loggerFactory2, AdListenerExecutorFactory adListenerExecutorFactory2, AdControllerFactory adControllerFactory2, AdRegistrationExecutor adRegistration2, AdLoadStarter adLoadStarter2) {
        super(context2, attrs);
        this.hasRegisterBroadcastReciever = false;
        this.attached = false;
        this.lastVisibility = 8;
        this.needsToLoadAdOnLayout = new AtomicBoolean(false);
        this.isParentViewMissingAtLoadTime = false;
        this.activityRootView = null;
        this.adTargetingOptions = null;
        this.isDestroyed = false;
        this.isInitialized = false;
        this.context = context2;
        this.adSize = determineAdSize(attrs);
        this.loggerFactory = loggerFactory2;
        this.logger = this.loggerFactory.createMobileAdsLogger(LOGTAG);
        this.adListenerExecutorFactory = adListenerExecutorFactory2;
        this.adControllerFactory = adControllerFactory2;
        this.adRegistration = adRegistration2;
        this.adLoadStarter = adLoadStarter2;
    }

    public AdLayout(Context context2, AttributeSet attrs, int defStyle) {
        this(context2, attrs, defStyle, new MobileAdsLoggerFactory(), new AdControllerFactory(), AdRegistration.getAmazonAdRegistrationExecutor(), new AdLoadStarter());
    }

    AdLayout(Context context2, AttributeSet attrs, int defStyle, MobileAdsLoggerFactory loggerFactory2, AdControllerFactory adControllerFactory2, AdRegistrationExecutor adRegistration2, AdLoadStarter adLoadStarter2) {
        this(context2, attrs, defStyle, loggerFactory2, new AdListenerExecutorFactory(loggerFactory2), adControllerFactory2, adRegistration2, adLoadStarter2);
    }

    AdLayout(Context context2, AttributeSet attrs, int defStyle, MobileAdsLoggerFactory loggerFactory2, AdListenerExecutorFactory adListenerExecutorFactory2, AdControllerFactory adControllerFactory2, AdRegistrationExecutor adRegistration2, AdLoadStarter adLoadStarter2) {
        super(context2, attrs, defStyle);
        this.hasRegisterBroadcastReciever = false;
        this.attached = false;
        this.lastVisibility = 8;
        this.needsToLoadAdOnLayout = new AtomicBoolean(false);
        this.isParentViewMissingAtLoadTime = false;
        this.activityRootView = null;
        this.adTargetingOptions = null;
        this.isDestroyed = false;
        this.isInitialized = false;
        this.context = context2;
        this.adSize = determineAdSize(attrs);
        this.loggerFactory = loggerFactory2;
        this.logger = this.loggerFactory.createMobileAdsLogger(LOGTAG);
        this.adListenerExecutorFactory = adListenerExecutorFactory2;
        this.adControllerFactory = adControllerFactory2;
        this.adRegistration = adRegistration2;
        this.adLoadStarter = adLoadStarter2;
    }

    private AdSize determineAdSize(AttributeSet attrs) {
        String adSizeAttributeValue = getAttributeValue(attrs, "http://schemas.android.com/apk/lib/com.amazon.device.ads", "adSize");
        if (adSizeAttributeValue == null && (adSizeAttributeValue = getAttributeValue(attrs, "http://schemas.android.com/apk/res/" + this.context.getPackageName(), "adSize")) != null) {
            this.logger.forceLog(MobileAdsLogger.Level.WARN, "DEPRECATED - Please use the XML namespace \"http://schemas.android.com/apk/lib/com.amazon.device.ads\" for specifying AdLayout properties.", new Object[0]);
            if (adSizeAttributeValue.toLowerCase(Locale.US).equals("custom")) {
                this.logger.forceLog(MobileAdsLogger.Level.ERROR, "Using \"custom\" or \"CUSTOM\" for the \"adSize\" property is no longer supported. Please specifiy a size or remove the property to use Auto Ad Size.", new Object[0]);
                throw new IllegalArgumentException("Using \"custom\" or \"CUSTOM\" for the \"adSize\" property is no longer supported. Please specifiy a size or remove the property to use Auto Ad Size.");
            }
        }
        return parseAdSize(adSizeAttributeValue);
    }

    /* access modifiers changed from: package-private */
    public MobileAdsLogger getLogger() {
        return this.logger;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldDisableWebViewHardwareAcceleration() {
        return this.shouldDisableWebViewHardwareAcceleration;
    }

    /* access modifiers changed from: package-private */
    public void setShouldDisableWebViewHardwareAcceleration(boolean shouldDisableWebViewHardwareAcceleration2) {
        this.shouldDisableWebViewHardwareAcceleration = shouldDisableWebViewHardwareAcceleration2;
        if (this.adController != null) {
            this.adController.requestDisableHardwareAcceleration(this.shouldDisableWebViewHardwareAcceleration);
        }
    }

    /* access modifiers changed from: package-private */
    public void initializeIfNecessary() {
        if (!isInitialized()) {
            long initStart = System.nanoTime();
            this.logger.d("Initializing AdLayout.");
            this.adRegistration.initializeAds(this.context);
            setContentDescription(CONTENT_DESCRIPTION_AD_LAYOUT);
            if (isInEditMode()) {
                TextView textView = new TextView(this.context);
                textView.setText("AdLayout");
                textView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                textView.setGravity(17);
                addView(textView);
                this.isInitialized = true;
                return;
            }
            this.isInForeground = getVisibility() == 0;
            setHorizontalScrollBarEnabled(false);
            setVerticalScrollBarEnabled(false);
            this.isInitialized = true;
            if (this.adListenerExecutor == null) {
                setListener((AdListener) null);
            }
            initializeAdController();
            if (isWebViewDatabaseNull()) {
                this.logger.forceLog(MobileAdsLogger.Level.ERROR, "Disabling ads. Local cache file is inaccessible so ads will fail if we try to create a WebView. Details of this Android bug found at: http://code.google.com/p/android/issues/detail?id=10789", new Object[0]);
                this.isInitialized = false;
                return;
            }
            this.adController.getMetricsCollector().startMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_LAYOUT_INITIALIZATION, initStart);
            this.adController.getMetricsCollector().stopMetric(Metrics.MetricType.AD_LAYOUT_INITIALIZATION);
        }
    }

    private void initializeAdController() {
        AdSize size;
        if (this.adController == null) {
            if (this.adSize == null) {
                size = AdSize.SIZE_AUTO;
            } else {
                size = this.adSize;
            }
            setAdController(createAdController(size, this.context));
            this.adController.requestDisableHardwareAcceleration(this.shouldDisableWebViewHardwareAcceleration);
        }
    }

    private void setAdController(AdController adController2) {
        this.adController = adController2;
        this.adController.setCallback(createAdControlCallback());
    }

    /* access modifiers changed from: private */
    public AdController getAdController() {
        initializeIfNecessary();
        if (this.adController == null) {
            initializeAdController();
        }
        return this.adController;
    }

    private static String getAttributeValue(AttributeSet attrs, String namespace, String name) {
        return attrs.getAttributeValue(namespace, name);
    }

    private static AdSize parseAdSize(String string) {
        AdSize adSize2 = AdSize.SIZE_AUTO;
        if (string == null) {
            return adSize2;
        }
        String string2 = string.toLowerCase(Locale.US);
        if (string2.equals("autonoscale")) {
            return AdSize.SIZE_AUTO_NO_SCALE;
        }
        if (string2.equals("auto")) {
            return adSize2;
        }
        String[] sizes = string2.split("x");
        int width = 0;
        int height = 0;
        if (sizes.length == 2) {
            width = NumberUtils.parseInt(sizes[0], 0);
            height = NumberUtils.parseInt(sizes[1], 0);
        }
        return new AdSize(width, height);
    }

    private AdController createAdController(AdSize size, Context context2) {
        return this.adControllerFactory.buildAdController(context2, size);
    }

    /* access modifiers changed from: package-private */
    public AdControlCallback createAdControlCallback() {
        return new AdLayoutAdControlCallback();
    }

    /* access modifiers changed from: package-private */
    public boolean isWebViewDatabaseNull() {
        return !getAdController().canShowViews();
    }

    /* access modifiers changed from: package-private */
    public AdData getAdData() {
        return getAdController().getAdData();
    }

    /* access modifiers changed from: package-private */
    public boolean isInitialized() {
        return this.isInitialized;
    }

    private void registerScreenStateBroadcastReceiver() {
        if (!this.hasRegisterBroadcastReciever) {
            this.hasRegisterBroadcastReciever = true;
            this.screenStateReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals("android.intent.action.SCREEN_OFF") && AdLayout.this.isInForeground) {
                        AdLayout.this.getAdController().closeAd();
                    }
                }
            };
            IntentFilter filter = new IntentFilter("android.intent.action.SCREEN_OFF");
            filter.addAction("android.intent.action.USER_PRESENT");
            this.context.getApplicationContext().registerReceiver(this.screenStateReceiver, filter);
        }
    }

    private void unregisterScreenStateBroadcastReceiver() {
        if (this.hasRegisterBroadcastReciever) {
            this.hasRegisterBroadcastReciever = false;
            this.context.getApplicationContext().unregisterReceiver(this.screenStateReceiver);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.attached = true;
            registerScreenStateBroadcastReceiver();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
        collapseAd();
        unregisterScreenStateBroadcastReceiver();
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int visibility) {
        if (this.attached && this.lastVisibility != visibility) {
            if (visibility != 0) {
                this.isInForeground = false;
                collapseAd();
                unregisterScreenStateBroadcastReceiver();
            } else if (visibility == 0) {
                this.isInForeground = true;
            }
        }
    }

    private void collapseAd() {
        if (getAdController().getAdState().equals(AdState.EXPANDED)) {
            ThreadUtils.scheduleOnMainThread(new Runnable() {
                public void run() {
                    if (AdLayout.this.getAdController().getAdState().equals(AdState.EXPANDED)) {
                        AdLayout.this.getAdController().closeAd();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (!this.isDestroyed) {
            int width = right - left;
            int height = bottom - top;
            super.onLayout(changed, left, top, right, bottom);
            if (!isInEditMode()) {
                getAdController().setWindowDimensions(width, height);
                if (getAndSetNeedsToLoadAdOnLayout(false)) {
                    startAdLoadUponLayout();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void startAdLoadUponLayout() {
        AdTargetingOptions options = this.adTargetingOptions;
        AdSlot adSlot = new AdSlot(getAdController(), options).setDeferredLoad(true);
        this.adLoadStarter.loadAds(getAdController().getTimeout(), options, adSlot);
        if (!getAndResetIsPrepared()) {
            onRequestError("Could not load ad on layout.");
        }
    }

    public int getTimeout() {
        if (getAdController() == null) {
            return -1;
        }
        return getAdController().getTimeout();
    }

    public void setTimeout(int timeout) {
        AdController adController2 = getAdController();
        if (adController2 != null) {
            adController2.setTimeout(timeout);
        }
    }

    public AdSize getAdSize() {
        AdController adController2 = getAdController();
        if (adController2 == null) {
            return null;
        }
        return adController2.getAdSize();
    }

    public boolean loadAd() {
        return loadAd(new AdTargetingOptions());
    }

    public boolean loadAd(AdTargetingOptions options) {
        if (options == null) {
            options = new AdTargetingOptions();
        }
        this.adTargetingOptions = options;
        if (getNeedsToLoadAdOnLayout()) {
            this.logger.e(LOADING_IN_PROGRESS_LOG_MSG);
            return false;
        }
        initializeIfNecessary();
        if (!isInitialized()) {
            this.logger.e("The ad could not be initialized properly.");
            return false;
        } else if (!isReadyToLoad()) {
            switch (getAdController().getAdState()) {
                case INVALID:
                    this.logger.e("An ad could not be loaded because of an unknown issue with web views.");
                    return false;
                case DESTROYED:
                    this.logger.e("An ad could not be loaded because the AdLayout has been destroyed.");
                    return false;
                case EXPANDED:
                    this.logger.e("An ad could not be loaded because another ad is currently expanded.");
                    return false;
                default:
                    this.logger.e(LOADING_IN_PROGRESS_LOG_MSG);
                    return false;
            }
        } else {
            this.adLoadStarter.loadAds(getAdController().getTimeout(), options, new AdSlot(getAdController(), options));
            if (getNeedsToLoadAdOnLayout()) {
                return true;
            }
            return getAndResetIsPrepared();
        }
    }

    private boolean isReadyToLoad() {
        return AdState.READY_TO_LOAD.equals(getAdController().getAdState()) || AdState.SHOWING.equals(getAdController().getAdState());
    }

    /* access modifiers changed from: package-private */
    public boolean prepareAd(boolean deferredLoad) {
        if (deferredLoad) {
            this.logger.d("Skipping ad layout preparation steps because the layout is already prepared.");
            return true;
        } else if (!isReadyToLoad()) {
            return false;
        } else {
            if (getNeedsToLoadAdOnLayout()) {
                this.logger.e(LOADING_IN_PROGRESS_LOG_MSG);
                return false;
            }
            if (getAdSize().isAuto()) {
                this.logger.d("Ad size to be determined automatically.");
            }
            setIsParentViewMissingAtLoadTime();
            if (getAdSize().isAuto() && getAdController().areWindowDimensionsSet()) {
                return true;
            }
            if (isLayoutRequested() && getAdSize().isAuto() && !isParentViewMissingAtLoadTime()) {
                deferAdLoadToLayoutEvent();
                return false;
            } else if (!isParentViewMissingAtLoadTime()) {
                return true;
            } else {
                this.logger.d("The ad's parent view is missing at load time.");
                return loadAdWhenParentViewMissing();
            }
        }
    }

    private boolean loadAdWhenParentViewMissing() {
        if (getLayoutParams() == null) {
            Metrics.getInstance().getMetricsCollector().incrementMetric(Metrics.MetricType.AD_FAILED_NULL_LAYOUT_PARAMS);
            onRequestError(LAYOUT_PARAMS_NULL_ERR_MSG);
            return false;
        } else if (AndroidTargetUtils.isAtLeastAndroidAPI(11)) {
            setActivityRootView();
            if (isActivityRootViewNull()) {
                onRequestError("Ad load failed because root view could not be obtained from the activity.");
                return false;
            } else if (isActivityRootViewLayoutRequested()) {
                this.logger.d("Activity root view layout is requested.");
                deferAdLoadToLayoutEvent();
                setOnLayoutChangeListenerForRoot();
                return false;
            } else {
                setFloatingWindowDimensions();
                return true;
            }
        } else {
            setFloatingWindowDimensions();
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public void adFailed(AdError adError) {
        getAdController().adFailed(adError);
    }

    /* access modifiers changed from: package-private */
    public void bypassAdRenderingProcess() {
        getAdController().setAdState(AdState.RENDERING);
        getAdController().adRendered("custom-render");
    }

    /* access modifiers changed from: package-private */
    public void adShown() {
        getAdController().adShown();
    }

    /* access modifiers changed from: package-private */
    public void setOnLayoutChangeListenerForRoot() {
        OnLayoutChangeListenerUtil.setOnLayoutChangeListenerForRoot(this);
    }

    private static class OnLayoutChangeListenerUtil {
        private OnLayoutChangeListenerUtil() {
        }

        @TargetApi(11)
        protected static void setOnLayoutChangeListenerForRoot(final AdLayout adLayout) {
            adLayout.activityRootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    if (adLayout.getAndSetNeedsToLoadAdOnLayout(false)) {
                        adLayout.setFloatingWindowDimensions();
                        adLayout.startAdLoadUponLayout();
                        adLayout.activityRootView.removeOnLayoutChangeListener(this);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void setFloatingWindowDimensions() {
        if (getLayoutParams().width == -1 || getLayoutParams().height == -1) {
            this.logger.d("The requested ad will scale based on the screen's dimensions because at least one AdLayout dimension is set to MATCH_PARENT but the AdLayout is currently missing a fixed-size parent view.");
        }
        getAdController().setWindowDimensions(resolveLayoutParamForFloatingAd(true), resolveLayoutParamForFloatingAd(false));
    }

    /* access modifiers changed from: package-private */
    public int resolveLayoutParamForFloatingAd(boolean isWidth) {
        int value;
        if (isWidth) {
            value = getLayoutParams().width;
        } else {
            value = getLayoutParams().height;
        }
        if (value == -1) {
            if (isActivityRootViewNull()) {
                return getRawScreenDimension(isWidth);
            }
            return getActivityRootViewDimension(isWidth);
        } else if (value == -2) {
            return 0;
        } else {
            return value;
        }
    }

    /* access modifiers changed from: package-private */
    public int getRawScreenDimension(boolean isWidth) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getMetrics(metrics);
        return isWidth ? metrics.widthPixels : metrics.heightPixels;
    }

    /* access modifiers changed from: package-private */
    public void deferAdLoadToLayoutEvent() {
        setNeedsToLoadAdOnLayout(true);
        scheduleTaskForCheckingIfLayoutHasRun();
    }

    /* access modifiers changed from: package-private */
    public void scheduleTaskForCheckingIfLayoutHasRun() {
        threadPool.schedule(new Runnable() {
            public void run() {
                AdLayout.this.failLoadIfLayoutHasNotRun();
            }
        }, (long) getTimeout(), TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: package-private */
    public void failLoadIfLayoutHasNotRun() {
        if (getAndSetNeedsToLoadAdOnLayout(false)) {
            Metrics.getInstance().getMetricsCollector().incrementMetric(Metrics.MetricType.AD_FAILED_LAYOUT_NOT_RUN);
            onRequestError(LAYOUT_NOT_RUN_ERR_MSG);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean getNeedsToLoadAdOnLayout() {
        return this.needsToLoadAdOnLayout.get();
    }

    /* access modifiers changed from: package-private */
    public void setNeedsToLoadAdOnLayout(boolean value) {
        this.needsToLoadAdOnLayout.set(value);
    }

    /* access modifiers changed from: package-private */
    public boolean getAndSetNeedsToLoadAdOnLayout(boolean value) {
        return this.needsToLoadAdOnLayout.getAndSet(value);
    }

    /* access modifiers changed from: package-private */
    public boolean getAndResetIsPrepared() {
        return getAdController().getAndResetIsPrepared();
    }

    private void onRequestError(String message) {
        getAdController().onRequestError(message);
    }

    /* access modifiers changed from: package-private */
    public boolean isParentViewMissingAtLoadTime() {
        return this.isParentViewMissingAtLoadTime;
    }

    /* access modifiers changed from: package-private */
    public void setIsParentViewMissingAtLoadTime() {
        this.isParentViewMissingAtLoadTime = getParent() == null;
    }

    /* access modifiers changed from: package-private */
    public void setIsParentViewMissingAtLoadTime(boolean parentViewMissingAtLoadTime) {
        this.isParentViewMissingAtLoadTime = parentViewMissingAtLoadTime;
    }

    /* access modifiers changed from: package-private */
    public View getActivityRootView() {
        return this.activityRootView;
    }

    /* access modifiers changed from: package-private */
    public void setActivityRootView() {
        this.activityRootView = ((Activity) this.context).getWindow().getDecorView().findViewById(16908290).getRootView();
    }

    /* access modifiers changed from: package-private */
    public boolean isActivityRootViewLayoutRequested() {
        return this.activityRootView.isLayoutRequested();
    }

    /* access modifiers changed from: package-private */
    public boolean isActivityRootViewNull() {
        return this.activityRootView == null;
    }

    /* access modifiers changed from: package-private */
    public int getActivityRootViewDimension(boolean isWidth) {
        return isWidth ? this.activityRootView.getWidth() : this.activityRootView.getHeight();
    }

    public void setListener(AdListener adListener) {
        if (adListener == null) {
            adListener = new DefaultAdListener(LOGTAG);
        }
        this.adListenerExecutor = this.adListenerExecutorFactory.createAdListenerExecutor(adListener);
    }

    /* access modifiers changed from: package-private */
    public AdListenerExecutor getAdListenerExecutor() {
        return this.adListenerExecutor;
    }

    public boolean isLoading() {
        if (getAdController() == null) {
            return false;
        }
        return getAdController().getAdState().equals(AdState.LOADING);
    }

    public boolean isAdLoading() {
        return isLoading();
    }

    public void destroy() {
        if (isInitialized()) {
            this.logger.d("Destroying the AdLayout");
            this.isDestroyed = true;
            unregisterScreenStateBroadcastReceiver();
            getAdController().destroy();
        }
    }

    class AdLayoutAdControlCallback implements AdControlCallback {
        private AdProperties properties;

        AdLayoutAdControlCallback() {
        }

        public boolean isAdReady(boolean deferredLoad) {
            return AdLayout.this.prepareAd(deferredLoad);
        }

        public void onAdLoaded(AdProperties adProperties) {
            this.properties = adProperties;
            AdLayout.this.getAdController().render();
        }

        @SuppressLint({"InlinedApi"})
        public void onAdRendered() {
            AdLayout.this.getAdController().getMetricsCollector().startMetric(Metrics.MetricType.AD_SHOW_LATENCY);
            if (AdLayout.this.currentView != null) {
                AdLayout.this.removeView(AdLayout.this.currentView);
            }
            if (AdLayout.this.currentDestroyable != null) {
                AdLayout.this.currentDestroyable.destroy();
            }
            View unused = AdLayout.this.currentView = AdLayout.this.getAdController().getView();
            Destroyable unused2 = AdLayout.this.currentDestroyable = AdLayout.this.getAdController().getDestroyable();
            AdLayout.this.addView(AdLayout.this.currentView, new FrameLayout.LayoutParams(-1, -1, 17));
            notifyAdShowing(this.properties);
        }

        /* access modifiers changed from: package-private */
        public void notifyAdShowing(AdProperties adProperties) {
            AdLayout.this.adShown();
            AdLayout.this.getAdListenerExecutor().onAdLoaded(AdLayout.this, adProperties);
        }

        public void onAdFailed(AdError adError) {
            if (AdError.ErrorCode.NETWORK_TIMEOUT.equals(adError.getCode())) {
                AdController unused = AdLayout.this.adController = null;
            }
            AdLayout.this.getAdListenerExecutor().onAdFailedToLoad(AdLayout.this, adError);
        }

        public void onAdEvent(AdEvent event) {
            handleAdEvent(event);
        }

        /* access modifiers changed from: package-private */
        public boolean handleAdEvent(AdEvent event) {
            switch (event.getAdEventType()) {
                case EXPANDED:
                    AdLayout.this.getAdListenerExecutor().onAdExpanded(AdLayout.this);
                    return true;
                case CLOSED:
                    AdLayout.this.getAdListenerExecutor().onAdCollapsed(AdLayout.this);
                    return true;
                case RESIZED:
                    AdLayout.this.getAdListenerExecutor().onAdResized(AdLayout.this, (Rect) event.getParameters().getParameter(AdEvent.POSITION_ON_SCREEN));
                    return true;
                default:
                    return false;
            }
        }

        public int adClosing() {
            if (AdLayout.this.getAdController().getAdState().equals(AdState.EXPANDED)) {
                return 0;
            }
            return 2;
        }

        public void postAdRendered() {
        }

        public void onAdExpired() {
        }
    }
}
