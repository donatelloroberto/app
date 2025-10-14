package com.amazon.device.ads;

import com.amazon.device.ads.AdvertisingIdentifier;
import com.amazon.device.ads.Metrics;
import com.amazon.device.ads.PreferredMarketplaceRetriever;
import com.amazon.device.ads.ThreadUtils;
import com.amazon.device.ads.WebRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

class Configuration {
    private static final String AAX_MSDK_CONFIG_ENDPOINT = "/msdk/getConfig";
    private static final String AAX_PROD_US_HOSTNAME = "mads.amazon-adsystem.com";
    protected static final String CONFIG_APP_DEFINED_MARKETPLACE = "config-appDefinedMarketplace";
    protected static final String CONFIG_LASTFETCHTIME = "config-lastFetchTime";
    protected static final String CONFIG_TTL = "config-ttl";
    protected static final String CONFIG_VERSION_NAME = "configVersion";
    protected static final int CURRENT_CONFIG_VERSION = 3;
    private static final String LOGTAG = Configuration.class.getSimpleName();
    protected static final int MAX_CONFIG_TTL = 172800000;
    protected static final int MAX_NO_RETRY_TTL = 300000;
    private static Configuration instance = new Configuration();
    private final AdvertisingIdentifier advertisingIdentifier;
    private String appDefinedMarketplace;
    private final DebugProperties debugProperties;
    private final MobileAdsInfoStore infoStore;
    private boolean isAppDefinedMarketplaceSet;
    private final AtomicBoolean isFetching;
    private boolean isFirstParty;
    private Boolean lastTestModeValue;
    private final List<ConfigurationListener> listeners;
    private final MobileAdsLogger logger;
    private final Metrics metrics;
    private final PermissionChecker permissionChecker;
    private PreferredMarketplaceRetriever pfmRetriever;
    private final Settings settings;
    private final SystemTime systemTime;
    private final ThreadUtils.ThreadRunner threadRunner;
    private final WebRequest.WebRequestFactory webRequestFactory;

    interface ConfigurationListener {
        void onConfigurationFailure();

        void onConfigurationReady();
    }

    public static class ConfigOption {
        public static final ConfigOption AAX_HOSTNAME = new ConfigOption("config-aaxHostname", String.class, "aaxHostname", DebugProperties.DEBUG_AAX_AD_HOSTNAME);
        public static final ConfigOption AD_PREF_URL = new ConfigOption("config-adPrefURL", String.class, "adPrefURL", DebugProperties.DEBUG_AD_PREF_URL);
        public static final ConfigOption MADS_HOSTNAME = new ConfigOption("config-madsHostname", String.class, "madsHostname", DebugProperties.DEBUG_MADS_HOSTNAME, true);
        public static final ConfigOption SEND_GEO = new ConfigOption("config-sendGeo", Boolean.class, "sendGeo", DebugProperties.DEBUG_SEND_GEO);
        public static final ConfigOption SIS_DOMAIN = new ConfigOption("config-sisDomain", String.class, "sisDomain", DebugProperties.DEBUG_SIS_DOMAIN);
        public static final ConfigOption SIS_URL = new ConfigOption("config-sisURL", String.class, "sisURL", DebugProperties.DEBUG_SIS_URL);
        public static final ConfigOption TRUNCATE_LAT_LON = new ConfigOption("config-truncateLatLon", Boolean.class, "truncateLatLon", DebugProperties.DEBUG_TRUNCATE_LAT_LON);
        public static final ConfigOption[] configOptions = {AAX_HOSTNAME, SIS_URL, AD_PREF_URL, MADS_HOSTNAME, SIS_DOMAIN, SEND_GEO, TRUNCATE_LAT_LON};
        private final boolean allowEmpty;
        private final Class<?> dataType;
        private final String debugProperty;
        private final String responseKey;
        private final String settingsName;

        protected ConfigOption(String settingsName2, Class<?> dataType2, String responseKey2, String debugProperty2) {
            this(settingsName2, dataType2, responseKey2, debugProperty2, false);
        }

        protected ConfigOption(String settingsName2, Class<?> dataType2, String responseKey2, String debugProperty2, boolean allowEmpty2) {
            this.settingsName = settingsName2;
            this.responseKey = responseKey2;
            this.dataType = dataType2;
            this.debugProperty = debugProperty2;
            this.allowEmpty = allowEmpty2;
        }

        /* access modifiers changed from: package-private */
        public String getSettingsName() {
            return this.settingsName;
        }

        /* access modifiers changed from: package-private */
        public String getResponseKey() {
            return this.responseKey;
        }

        /* access modifiers changed from: package-private */
        public Class<?> getDataType() {
            return this.dataType;
        }

        /* access modifiers changed from: package-private */
        public String getDebugProperty() {
            return this.debugProperty;
        }

        /* access modifiers changed from: package-private */
        public boolean getAllowEmpty() {
            return this.allowEmpty;
        }
    }

    protected Configuration() {
        this(new MobileAdsLoggerFactory(), new PermissionChecker(), new WebRequest.WebRequestFactory(), DebugProperties.getInstance(), Settings.getInstance(), MobileAdsInfoStore.getInstance(), new SystemTime(), Metrics.getInstance(), new AdvertisingIdentifier(), ThreadUtils.getThreadRunner());
    }

    Configuration(MobileAdsLoggerFactory loggerFactory, PermissionChecker permissionChecker2, WebRequest.WebRequestFactory webRequestFactory2, DebugProperties debugProperties2, Settings settings2, MobileAdsInfoStore infoStore2, SystemTime systemTime2, Metrics metrics2, AdvertisingIdentifier advertisingIdentifier2, ThreadUtils.ThreadRunner threadRunner2) {
        this.appDefinedMarketplace = null;
        this.isAppDefinedMarketplaceSet = false;
        this.listeners = new ArrayList(5);
        this.isFetching = new AtomicBoolean(false);
        this.lastTestModeValue = null;
        this.isFirstParty = false;
        this.pfmRetriever = new PreferredMarketplaceRetriever.NullPreferredMarketplaceRetriever();
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        this.permissionChecker = permissionChecker2;
        this.webRequestFactory = webRequestFactory2;
        this.debugProperties = debugProperties2;
        this.settings = settings2;
        this.infoStore = infoStore2;
        this.systemTime = systemTime2;
        this.metrics = metrics2;
        this.advertisingIdentifier = advertisingIdentifier2;
        this.threadRunner = threadRunner2;
    }

    public static final Configuration getInstance() {
        return instance;
    }

    public String getAppDefinedMarketplace() {
        return this.appDefinedMarketplace;
    }

    public void setAppDefinedMarketplace(String appDefinedMarketplace2) {
        this.appDefinedMarketplace = appDefinedMarketplace2;
        this.isAppDefinedMarketplaceSet = true;
    }

    public void setIsFirstParty(boolean isFirstParty2) {
        this.isFirstParty = isFirstParty2;
    }

    /* access modifiers changed from: package-private */
    public boolean isFirstParty() {
        return this.isFirstParty;
    }

    public boolean hasValue(ConfigOption configOption) {
        return !StringUtils.isNullOrWhiteSpace(getString(configOption));
    }

    public String getString(ConfigOption configOption) {
        String value = this.debugProperties.getDebugPropertyAsString(configOption.getDebugProperty(), (String) null);
        if (value == null) {
            return this.settings.getString(configOption.getSettingsName(), (String) null);
        }
        return value;
    }

    public boolean getBoolean(ConfigOption configOption) {
        return getBooleanWithDefault(configOption, false);
    }

    public boolean getBooleanWithDefault(ConfigOption configOption, boolean defaultValue) {
        if (this.debugProperties.containsDebugProperty(configOption.getDebugProperty())) {
            return this.debugProperties.getDebugPropertyAsBoolean(configOption.getDebugProperty(), Boolean.valueOf(defaultValue)).booleanValue();
        }
        return this.settings.getBoolean(configOption.getSettingsName(), defaultValue);
    }

    /* access modifiers changed from: protected */
    public boolean shouldFetch() {
        if (hasAppDefinedMarketplaceChanged() || this.settings.getInt(CONFIG_VERSION_NAME, 0) != 3) {
            return true;
        }
        long currentTime = this.systemTime.currentTimeMillis();
        long lastFetchTime = this.settings.getLong(CONFIG_LASTFETCHTIME, 0);
        long ttl = this.settings.getLong(CONFIG_TTL, 172800000);
        if (lastFetchTime == 0) {
            this.logger.d("No configuration found. A new configuration will be retrieved.");
            return true;
        } else if (currentTime - lastFetchTime > ttl) {
            this.logger.d("The configuration has expired. A new configuration will be retrieved.");
            return true;
        } else if (this.lastTestModeValue != null && this.lastTestModeValue.booleanValue() != this.settings.getBoolean("testingEnabled", false)) {
            this.logger.d("The testing mode has changed. A new configuration will be retrieved.");
            return true;
        } else if (!this.debugProperties.getDebugPropertyAsBoolean(DebugProperties.DEBUG_SHOULD_FETCH_CONFIG, false).booleanValue()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean hasAppDefinedMarketplaceChanged() {
        String storedAppDefinedMarketplace = this.settings.getString(CONFIG_APP_DEFINED_MARKETPLACE, (String) null);
        if (!this.isAppDefinedMarketplaceSet) {
            return false;
        }
        this.isAppDefinedMarketplaceSet = false;
        if (this.appDefinedMarketplace != null && !this.appDefinedMarketplace.equals(storedAppDefinedMarketplace)) {
            this.settings.putLongWithNoFlush(CONFIG_LASTFETCHTIME, 0);
            this.settings.putStringWithNoFlush(CONFIG_APP_DEFINED_MARKETPLACE, this.appDefinedMarketplace);
            this.settings.flush();
            this.infoStore.getRegistrationInfo().requestNewSISDeviceIdentifier();
            this.logger.d("New application-defined marketplace set. A new configuration will be retrieved.");
            return true;
        } else if (storedAppDefinedMarketplace == null || this.appDefinedMarketplace != null) {
            return false;
        } else {
            this.settings.remove(CONFIG_APP_DEFINED_MARKETPLACE);
            this.infoStore.getRegistrationInfo().requestNewSISDeviceIdentifier();
            this.logger.d("Application-defined marketplace removed. A new configuration will be retrieved.");
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isFetching() {
        return this.isFetching.get();
    }

    /* access modifiers changed from: protected */
    public void setIsFetching(boolean isFetching2) {
        this.isFetching.set(isFetching2);
    }

    public synchronized void queueConfigurationListener(ConfigurationListener listener) {
        queueConfigurationListener(listener, true);
    }

    public synchronized void queueConfigurationListener(ConfigurationListener listener, boolean allowFetch) {
        if (isFetching()) {
            this.listeners.add(listener);
        } else if (shouldFetch()) {
            this.listeners.add(listener);
            if (allowFetch) {
                this.logger.d("Starting configuration fetching...");
                setIsFetching(true);
                beginFetch();
            }
        } else {
            listener.onConfigurationReady();
        }
    }

    /* access modifiers changed from: protected */
    public void beginFetch() {
        this.threadRunner.execute(new Runnable() {
            public void run() {
                Configuration.this.fetchConfigurationOnBackgroundThread();
            }
        }, ThreadUtils.ExecutionStyle.SCHEDULE, ThreadUtils.ExecutionThread.BACKGROUND_THREAD);
    }

    /* access modifiers changed from: protected */
    public synchronized void onFetchSuccess() {
        setIsFetching(false);
        for (ConfigurationListener listener : getAndClearListeners()) {
            listener.onConfigurationReady();
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void onFetchFailure() {
        this.metrics.getMetricsCollector().incrementMetric(Metrics.MetricType.AAX_CONFIG_DOWNLOAD_FAILED);
        setIsFetching(false);
        for (ConfigurationListener listener : getAndClearListeners()) {
            listener.onConfigurationFailure();
        }
    }

    /* access modifiers changed from: protected */
    public synchronized ConfigurationListener[] getAndClearListeners() {
        ConfigurationListener[] toCall;
        toCall = (ConfigurationListener[]) this.listeners.toArray(new ConfigurationListener[this.listeners.size()]);
        this.listeners.clear();
        return toCall;
    }

    /* access modifiers changed from: protected */
    public ConfigOption[] getConfigOptions() {
        return ConfigOption.configOptions;
    }

    /* access modifiers changed from: protected */
    public void setLastTestModeValue(boolean testMode) {
        this.lastTestModeValue = Boolean.valueOf(testMode);
    }

    /* access modifiers changed from: protected */
    public void fetchConfigurationOnBackgroundThread() {
        this.logger.d("In configuration fetcher background thread.");
        if (!this.permissionChecker.hasInternetPermission(this.infoStore.getApplicationContext())) {
            this.logger.e("Network task cannot commence because the INTERNET permission is missing from the app's manifest.");
            onFetchFailure();
            return;
        }
        AdvertisingIdentifier.Info advertisingIdentifierInfo = createAdvertisingIdentifierInfo();
        if (!advertisingIdentifierInfo.canDo()) {
            onFetchFailure();
            return;
        }
        try {
            JSONObject json = createWebRequest(advertisingIdentifierInfo).makeCall().getResponseReader().readAsJSON();
            try {
                for (ConfigOption configOption : getConfigOptions()) {
                    if (!json.isNull(configOption.getResponseKey())) {
                        writeSettingFromConfigOption(configOption, json);
                    } else if (!configOption.getAllowEmpty()) {
                        throw new Exception("The configuration value for " + configOption.getResponseKey() + " must be present and not null.");
                    } else {
                        this.settings.removeWithNoFlush(configOption.getSettingsName());
                    }
                }
                if (json.isNull("ttl")) {
                    throw new Exception("The configuration value must be present and not null.");
                }
                long ttl = NumberUtils.convertToMillisecondsFromSeconds((long) json.getInt("ttl"));
                if (ttl > 172800000) {
                    ttl = 172800000;
                }
                this.settings.putLongWithNoFlush(CONFIG_TTL, ttl);
                this.settings.putLongWithNoFlush(CONFIG_LASTFETCHTIME, this.systemTime.currentTimeMillis());
                this.settings.putIntWithNoFlush(CONFIG_VERSION_NAME, 3);
                this.settings.flush();
                this.logger.d("Configuration fetched and saved.");
                onFetchSuccess();
            } catch (JSONException e) {
                this.logger.e("Unable to parse JSON response: %s", e.getMessage());
                onFetchFailure();
            } catch (Exception e2) {
                this.logger.e("Unexpected error during parsing: %s", e2.getMessage());
                onFetchFailure();
            }
        } catch (WebRequest.WebRequestException e3) {
            onFetchFailure();
        }
    }

    private void writeSettingFromConfigOption(ConfigOption configOption, JSONObject json) throws Exception {
        if (configOption.getDataType().equals(String.class)) {
            String value = json.getString(configOption.getResponseKey());
            if (configOption.getAllowEmpty() || !StringUtils.isNullOrWhiteSpace(value)) {
                this.settings.putStringWithNoFlush(configOption.getSettingsName(), value);
                return;
            }
            throw new IllegalArgumentException("The configuration value must not be empty or contain only white spaces.");
        } else if (configOption.getDataType().equals(Boolean.class)) {
            this.settings.putBooleanWithNoFlush(configOption.getSettingsName(), json.getBoolean(configOption.getResponseKey()));
        } else {
            throw new IllegalArgumentException("Undefined configuration option type.");
        }
    }

    /* access modifiers changed from: protected */
    public WebRequest createWebRequest(AdvertisingIdentifier.Info advertisingIdentifierInfo) {
        WebRequest request = this.webRequestFactory.createJSONGetWebRequest();
        request.setExternalLogTag(LOGTAG);
        request.enableLog(true);
        request.setHost(this.debugProperties.getDebugPropertyAsString(DebugProperties.DEBUG_AAX_CONFIG_HOSTNAME, AAX_PROD_US_HOSTNAME));
        request.setPath(AAX_MSDK_CONFIG_ENDPOINT);
        request.setMetricsCollector(this.metrics.getMetricsCollector());
        request.setServiceCallLatencyMetric(Metrics.MetricType.AAX_CONFIG_DOWNLOAD_LATENCY);
        request.setUseSecure(this.debugProperties.getDebugPropertyAsBoolean(DebugProperties.DEBUG_AAX_CONFIG_USE_SECURE, true).booleanValue());
        RegistrationInfo registrationInfo = this.infoStore.getRegistrationInfo();
        DeviceInfo deviceInfo = this.infoStore.getDeviceInfo();
        request.putUnencodedQueryParameter("appId", registrationInfo.getAppKey());
        request.putUnencodedQueryParameter("dinfo", deviceInfo.getDInfoProperty().toString());
        request.putUnencodedQueryParameter("adId", advertisingIdentifierInfo.getSISDeviceIdentifier());
        request.putUnencodedQueryParameter("sdkVer", Version.getSDKVersion());
        request.putUnencodedQueryParameter("fp", Boolean.toString(this.isFirstParty));
        request.putUnencodedQueryParameter("mkt", this.settings.getString(CONFIG_APP_DEFINED_MARKETPLACE, (String) null));
        request.putUnencodedQueryParameter("pfm", getPreferredMarketplace());
        boolean testingEnabled = this.settings.getBoolean("testingEnabled", false);
        setLastTestModeValue(testingEnabled);
        if (testingEnabled) {
            request.putUnencodedQueryParameter("testMode", "true");
        }
        request.setAdditionalQueryParamsString(this.debugProperties.getDebugPropertyAsString(DebugProperties.DEBUG_AAX_CONFIG_PARAMS, (String) null));
        return request;
    }

    /* access modifiers changed from: package-private */
    public AdvertisingIdentifier.Info createAdvertisingIdentifierInfo() {
        boolean z = false;
        AdvertisingIdentifier advertisingIdentifier2 = this.advertisingIdentifier;
        if (this.settings.getInt(CONFIG_VERSION_NAME, 0) != 0) {
            z = true;
        }
        advertisingIdentifier2.setShouldSetCurrentAdvertisingIdentifier(z);
        return this.advertisingIdentifier.getAdvertisingIdentifierInfo();
    }

    public void setPreferredMarketplaceRetriever(PreferredMarketplaceRetriever pfmRetriever2) {
        this.pfmRetriever = pfmRetriever2;
    }

    /* access modifiers changed from: package-private */
    public PreferredMarketplaceRetriever getPreferredMarketplaceRetriever() {
        return this.pfmRetriever;
    }

    private String getPreferredMarketplace() {
        return this.pfmRetriever.retrievePreferredMarketplace(MobileAdsInfoStore.getInstance().getApplicationContext());
    }
}
