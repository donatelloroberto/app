package com.amazon.device.ads;

import android.annotation.SuppressLint;
import com.amazon.device.ads.AAXParameter;
import com.amazon.device.ads.AdvertisingIdentifier;
import com.amazon.device.ads.Configuration;
import com.amazon.device.ads.Metrics;
import com.amazon.device.ads.WebRequest;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class AdRequest {
    private static final String AAX_ENDPOINT = "/e/msdk/ads";
    private static final String LOGTAG = AdRequest.class.getSimpleName();
    private static final AAXParameter<?>[] PARAMETERS = {AAXParameter.APP_KEY, AAXParameter.CHANNEL, AAXParameter.PUBLISHER_KEYWORDS, AAXParameter.PUBLISHER_ASINS, AAXParameter.USER_AGENT, AAXParameter.SDK_VERSION, AAXParameter.GEOLOCATION, AAXParameter.DEVICE_INFO, AAXParameter.PACKAGE_INFO, AAXParameter.TEST, AAXParameter.SIS_DEVICE_IDENTIFIER, AAXParameter.SHA1_UDID, AAXParameter.MD5_UDID, AAXParameter.ADVERTISING_IDENTIFIER, AAXParameter.OPT_OUT};
    private AdvertisingIdentifier.Info advertisingIdentifierInfo;
    private final Configuration configuration;
    private final ConnectionInfo connectionInfo;
    private final DebugProperties debugProperties;
    private String instrPixelUrl;
    private final JSONObjectBuilder jsonObjectBuilder;
    private final MobileAdsLogger logger;
    private final AdTargetingOptions opt;
    private final String orientation;
    protected final Map<Integer, LOISlot> slots;
    private final WebRequest.WebRequestFactory webRequestFactory;

    public AdRequest(AdTargetingOptions opt2) {
        this(opt2, new WebRequest.WebRequestFactory(), MobileAdsInfoStore.getInstance(), Configuration.getInstance(), DebugProperties.getInstance(), new MobileAdsLoggerFactory());
    }

    @SuppressLint({"UseSparseArrays"})
    AdRequest(AdTargetingOptions opt2, WebRequest.WebRequestFactory webRequestFactory2, MobileAdsInfoStore infoStore, Configuration configuration2, DebugProperties debugProperties2, MobileAdsLoggerFactory loggerFactory) {
        this.opt = opt2;
        this.webRequestFactory = webRequestFactory2;
        this.slots = new HashMap();
        this.orientation = infoStore.getDeviceInfo().getOrientation();
        this.connectionInfo = new ConnectionInfo(infoStore);
        this.configuration = configuration2;
        this.debugProperties = debugProperties2;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        Map<String, String> advancedOptions = this.opt.getCopyOfAdvancedOptions();
        this.jsonObjectBuilder = new JSONObjectBuilder(this.logger).setAAXParameters(PARAMETERS).setAdvancedOptions(advancedOptions).setParameterData(new AAXParameter.ParameterData().setAdTargetingOptions(this.opt).setAdvancedOptions(advancedOptions).setAdRequest(this));
    }

    public void setInstrumentationPixelURL(String instrPixelUrl2) {
        this.instrPixelUrl = instrPixelUrl2;
    }

    public String getInstrumentationPixelURL() {
        return this.instrPixelUrl;
    }

    /* access modifiers changed from: package-private */
    public AdTargetingOptions getAdTargetingOptions() {
        return this.opt;
    }

    /* access modifiers changed from: package-private */
    public String getOrientation() {
        return this.orientation;
    }

    /* access modifiers changed from: package-private */
    public AdvertisingIdentifier.Info getAdvertisingIdentifierInfo() {
        return this.advertisingIdentifierInfo;
    }

    /* access modifiers changed from: package-private */
    public AdRequest setAdvertisingIdentifierInfo(AdvertisingIdentifier.Info advertisingIdentifierInfo2) {
        this.advertisingIdentifierInfo = advertisingIdentifierInfo2;
        return this;
    }

    public void putSlot(AdSlot adSlot) {
        if (getAdvertisingIdentifierInfo().hasSISDeviceIdentifier()) {
            adSlot.getMetricsCollector().incrementMetric(Metrics.MetricType.AD_COUNTER_IDENTIFIED_DEVICE);
        }
        adSlot.setConnectionInfo(this.connectionInfo);
        this.slots.put(Integer.valueOf(adSlot.getSlotNumber()), new LOISlot(adSlot, this, this.logger));
    }

    /* access modifiers changed from: protected */
    public JSONArray getSlots() {
        JSONArray array = new JSONArray();
        for (LOISlot slot : this.slots.values()) {
            array.put(slot.getJSON());
        }
        return array;
    }

    public WebRequest getWebRequest() {
        WebRequest request = this.webRequestFactory.createWebRequest();
        request.setUseSecure(isSSLRequired() || request.getUseSecure());
        request.setExternalLogTag(LOGTAG);
        request.setHttpMethod(WebRequest.HttpMethod.POST);
        request.setHost(this.configuration.getString(Configuration.ConfigOption.AAX_HOSTNAME));
        request.setPath(AAX_ENDPOINT);
        request.enableLog(true);
        request.setContentType(WebRequest.CONTENT_TYPE_JSON);
        request.setDisconnectEnabled(false);
        setParametersInWebRequest(request);
        return request;
    }

    private boolean isSSLRequired() {
        return !Configuration.getInstance().getBoolean(Configuration.ConfigOption.TRUNCATE_LAT_LON) && Configuration.getInstance().getBoolean(Configuration.ConfigOption.SEND_GEO) && getAdTargetingOptions().isGeoLocationEnabled();
    }

    /* access modifiers changed from: protected */
    public void setParametersInWebRequest(WebRequest request) {
        this.jsonObjectBuilder.build();
        Object slots2 = AAXParameter.SLOTS.getValue(this.jsonObjectBuilder.getParameterData());
        if (slots2 == null) {
            slots2 = getSlots();
        }
        this.jsonObjectBuilder.putIntoJSON((AAXParameter<?>) AAXParameter.SLOTS, slots2);
        JSONObject json = this.jsonObjectBuilder.getJSON();
        String additionalTargetingParams = this.debugProperties.getDebugPropertyAsString(DebugProperties.DEBUG_AAX_AD_PARAMS, (String) null);
        if (!StringUtils.isNullOrEmpty(additionalTargetingParams)) {
            request.setAdditionalQueryParamsString(additionalTargetingParams);
        }
        setRequestBodyString(request, json);
    }

    /* access modifiers changed from: protected */
    public void setRequestBodyString(WebRequest request, JSONObject json) {
        request.setRequestBodyString(json.toString());
    }

    static class LOISlot {
        static final AAXParameter<?>[] PARAMETERS = {AAXParameter.SIZE, AAXParameter.PAGE_TYPE, AAXParameter.SLOT, AAXParameter.SLOT_POSITION, AAXParameter.MAX_SIZE, AAXParameter.SLOT_ID, AAXParameter.FLOOR_PRICE, AAXParameter.SUPPORTED_MEDIA_TYPES, AAXParameter.VIDEO_OPTIONS};
        private final AdSlot adSlot;
        private final JSONObjectBuilder jsonObjectBuilder;
        private final AdTargetingOptions opt;

        LOISlot(AdSlot adSlot2, AdRequest adRequest, MobileAdsLogger logger) {
            this.opt = adSlot2.getAdTargetingOptions();
            this.adSlot = adSlot2;
            Map<String, String> advancedOptions = this.opt.getCopyOfAdvancedOptions();
            this.jsonObjectBuilder = new JSONObjectBuilder(logger).setAAXParameters(PARAMETERS).setAdvancedOptions(advancedOptions).setParameterData(new AAXParameter.ParameterData().setAdTargetingOptions(this.opt).setAdvancedOptions(advancedOptions).setLOISlot(this).setAdRequest(adRequest));
        }

        /* access modifiers changed from: package-private */
        public AdTargetingOptions getAdTargetingOptions() {
            return this.opt;
        }

        /* access modifiers changed from: package-private */
        public JSONObject getJSON() {
            this.jsonObjectBuilder.build();
            return this.jsonObjectBuilder.getJSON();
        }

        /* access modifiers changed from: package-private */
        public AdSlot getAdSlot() {
            return this.adSlot;
        }
    }

    static class JSONObjectBuilder {
        private AAXParameter<?>[] aaxParameters;
        private Map<String, String> advancedOptions;
        private final JSONObject json = new JSONObject();
        private final MobileAdsLogger logger;
        private AAXParameter.ParameterData parameterData;

        JSONObjectBuilder(MobileAdsLogger logger2) {
            this.logger = logger2;
        }

        /* access modifiers changed from: package-private */
        public JSONObjectBuilder setAAXParameters(AAXParameter<?>[] aaxParameters2) {
            this.aaxParameters = aaxParameters2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public JSONObjectBuilder setAdvancedOptions(Map<String, String> advancedOptions2) {
            this.advancedOptions = advancedOptions2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public JSONObjectBuilder setParameterData(AAXParameter.ParameterData parameterData2) {
            this.parameterData = parameterData2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public AAXParameter.ParameterData getParameterData() {
            return this.parameterData;
        }

        /* access modifiers changed from: package-private */
        public JSONObject getJSON() {
            return this.json;
        }

        /* access modifiers changed from: package-private */
        public void build() {
            for (AAXParameter<?> parameter : this.aaxParameters) {
                putIntoJSON(parameter, parameter.getValue(this.parameterData));
            }
            if (this.advancedOptions != null) {
                for (Map.Entry<String, String> entry : this.advancedOptions.entrySet()) {
                    if (!StringUtils.isNullOrWhiteSpace(entry.getValue())) {
                        putIntoJSON(entry.getKey(), (Object) entry.getValue());
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void putIntoJSON(AAXParameter<?> parameter, Object value) {
            putIntoJSON(parameter.getName(), value);
        }

        /* access modifiers changed from: package-private */
        public void putIntoJSON(String key, Object value) {
            if (value != null) {
                try {
                    this.json.put(key, value);
                } catch (JSONException e) {
                    this.logger.d("Could not add parameter to JSON %s: %s", key, value);
                }
            }
        }
    }

    static class AdRequestBuilder {
        private AdTargetingOptions adTargetingOptions;
        private AdvertisingIdentifier.Info advertisingIdentifierInfo;

        AdRequestBuilder() {
        }

        public AdRequestBuilder withAdTargetingOptions(AdTargetingOptions adTargetingOptions2) {
            this.adTargetingOptions = adTargetingOptions2;
            return this;
        }

        public AdRequestBuilder withAdvertisingIdentifierInfo(AdvertisingIdentifier.Info advertisingIdentifierInfo2) {
            this.advertisingIdentifierInfo = advertisingIdentifierInfo2;
            return this;
        }

        public AdRequest build() {
            return new AdRequest(this.adTargetingOptions).setAdvertisingIdentifierInfo(this.advertisingIdentifierInfo);
        }
    }
}
