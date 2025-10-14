package com.amazon.device.ads;

import android.content.Context;
import android.location.Location;
import com.amazon.device.ads.AdRequest;
import com.amazon.device.ads.Configuration;
import com.amazon.device.ads.Parsers;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

abstract class AAXParameter<T> {
    static final AAXParameter<String> ADVERTISING_IDENTIFIER = new AdvertisingIdentifierParameter();
    static final AAXParameter<String> APP_KEY = new AppKeyParameter();
    static final AAXParameter<String> CHANNEL = new StringParameter("c", DebugProperties.DEBUG_CHANNEL);
    static final AAXParameter<JSONObject> DEVICE_INFO = new DeviceInfoParameter();
    static final AAXParameter<Long> FLOOR_PRICE = new FloorPriceParameter();
    static final AAXParameter<String> GEOLOCATION = new GeoLocationParameter();
    /* access modifiers changed from: private */
    public static final String LOGTAG = AAXParameter.class.getSimpleName();
    static final AAXParameter<String> MAX_SIZE = new MaxSizeParameter();
    static final AAXParameter<String> MD5_UDID = new MD5UDIDParameter();
    static final AAXParameter<Boolean> OPT_OUT = new OptOutParameter();
    static final AAXParameter<JSONObject> PACKAGE_INFO = new PackageInfoParameter();
    static final AAXParameter<String> PAGE_TYPE = new StringParameter("pt", DebugProperties.DEBUG_PT);
    static final AAXParameter<JSONArray> PUBLISHER_ASINS = new JSONArrayParameter("pa", DebugProperties.DEBUG_PA);
    static final PublisherKeywordsParameter PUBLISHER_KEYWORDS = new PublisherKeywordsParameter();
    static final AAXParameter<String> SDK_VERSION = new SDKVersionParameter();
    static final AAXParameter<String> SHA1_UDID = new SHA1UDIDParameter();
    static final AAXParameter<String> SIS_DEVICE_IDENTIFIER = new SISDeviceIdentifierParameter();
    static final AAXParameter<String> SIZE = new SizeParameter();
    static final AAXParameter<String> SLOT = new SlotParameter();
    static final AAXParameter<JSONArray> SLOTS = new JSONArrayParameter("slots", DebugProperties.DEBUG_SLOTS);
    static final AAXParameter<Integer> SLOT_ID = new SlotIdParameter();
    static final AAXParameter<String> SLOT_POSITION = new StringParameter("sp", DebugProperties.DEBUG_SP);
    static final AAXParameter<JSONArray> SUPPORTED_MEDIA_TYPES = new SupportedMediaTypesParameter();
    static final AAXParameter<Boolean> TEST = new TestParameter();
    static final AAXParameter<String> USER_AGENT = new UserAgentParameter();
    static final AAXParameter<JSONObject> VIDEO_OPTIONS = new VideoOptionsParameter();
    private final String debugName;
    private final String name;

    /* access modifiers changed from: protected */
    public abstract T getFromDebugProperties();

    /* access modifiers changed from: protected */
    public abstract T parseFromString(String str);

    AAXParameter(String name2, String debugName2) {
        this.name = name2;
        this.debugName = debugName2;
    }

    /* access modifiers changed from: package-private */
    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: protected */
    public String getDebugName() {
        return this.debugName;
    }

    /* access modifiers changed from: protected */
    public boolean hasDebugPropertiesValue() {
        return DebugProperties.getInstance().containsDebugProperty(this.debugName);
    }

    /* access modifiers changed from: package-private */
    public T getValue(ParameterData parameterData) {
        T value;
        if (hasDebugPropertiesValue()) {
            value = getFromDebugProperties();
        } else if (parameterData.advancedOptions.containsKey(this.name)) {
            value = parseFromString((String) parameterData.advancedOptions.remove(this.name));
        } else {
            value = getDerivedValue(parameterData);
        }
        T value2 = applyPostParameterProcessing(value, parameterData);
        if (!(value2 instanceof String) || !StringUtils.isNullOrWhiteSpace((String) value2)) {
            return value2;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public T getDerivedValue(ParameterData parameterData) {
        return null;
    }

    /* access modifiers changed from: protected */
    public T applyPostParameterProcessing(T value, ParameterData parameterData) {
        return value;
    }

    static class ParameterData {
        /* access modifiers changed from: private */
        public AdRequest adRequest;
        /* access modifiers changed from: private */
        public AdTargetingOptions adTargetingOptions;
        /* access modifiers changed from: private */
        public Map<String, String> advancedOptions;
        /* access modifiers changed from: private */
        public AdRequest.LOISlot loiSlot;
        /* access modifiers changed from: private */
        public Map<String, String> temporaryOptions = new HashMap();

        /* access modifiers changed from: package-private */
        public ParameterData setAdRequest(AdRequest adRequest2) {
            this.adRequest = adRequest2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public ParameterData setAdvancedOptions(Map<String, String> advancedOptions2) {
            this.advancedOptions = advancedOptions2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public ParameterData setLOISlot(AdRequest.LOISlot loiSlot2) {
            this.loiSlot = loiSlot2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public AdRequest getAdRequest() {
            return this.adRequest;
        }

        /* access modifiers changed from: package-private */
        public ParameterData setAdTargetingOptions(AdTargetingOptions adTargetingOptions2) {
            this.adTargetingOptions = adTargetingOptions2;
            return this;
        }
    }

    static class StringParameter extends AAXParameter<String> {
        StringParameter(String name, String debugProperty) {
            super(name, debugProperty);
        }

        /* access modifiers changed from: protected */
        public String parseFromString(String value) {
            return value;
        }

        /* access modifiers changed from: protected */
        public String getFromDebugProperties() {
            return DebugProperties.getInstance().getDebugPropertyAsString(getDebugName(), (String) null);
        }
    }

    static class BooleanParameter extends AAXParameter<Boolean> {
        BooleanParameter(String name, String debugProperty) {
            super(name, debugProperty);
        }

        /* access modifiers changed from: protected */
        public Boolean parseFromString(String value) {
            return Boolean.valueOf(Boolean.parseBoolean(value));
        }

        /* access modifiers changed from: protected */
        public Boolean getFromDebugProperties() {
            return DebugProperties.getInstance().getDebugPropertyAsBoolean(getDebugName(), (Boolean) null);
        }
    }

    static class IntegerParameter extends AAXParameter<Integer> {
        IntegerParameter(String name, String debugProperty) {
            super(name, debugProperty);
        }

        /* access modifiers changed from: protected */
        public Integer parseFromString(String value) {
            return Integer.valueOf(Integer.parseInt(value));
        }

        /* access modifiers changed from: protected */
        public Integer getFromDebugProperties() {
            return DebugProperties.getInstance().getDebugPropertyAsInteger(getDebugName(), (Integer) null);
        }
    }

    static class LongParameter extends AAXParameter<Long> {
        LongParameter(String name, String debugProperty) {
            super(name, debugProperty);
        }

        /* access modifiers changed from: protected */
        public Long parseFromString(String value) {
            return Long.valueOf(Long.parseLong(value));
        }

        /* access modifiers changed from: protected */
        public Long getFromDebugProperties() {
            return DebugProperties.getInstance().getDebugPropertyAsLong(getDebugName(), (Long) null);
        }
    }

    static class JSONArrayParameter extends AAXParameter<JSONArray> {
        private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(AAXParameter.LOGTAG);

        JSONArrayParameter(String name, String debugProperty) {
            super(name, debugProperty);
        }

        /* access modifiers changed from: protected */
        public JSONArray parseFromString(String value) {
            try {
                return new JSONArray(value);
            } catch (JSONException e) {
                this.logger.e("Unable to parse the following value into a JSONArray: %s", getName());
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public JSONArray getFromDebugProperties() {
            return parseFromString(DebugProperties.getInstance().getDebugPropertyAsString(getDebugName(), (String) null));
        }
    }

    static class JSONObjectParameter extends AAXParameter<JSONObject> {
        private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(AAXParameter.LOGTAG);

        JSONObjectParameter(String name, String debugProperty) {
            super(name, debugProperty);
        }

        /* access modifiers changed from: protected */
        public JSONObject parseFromString(String value) {
            try {
                return new JSONObject(value);
            } catch (JSONException e) {
                this.logger.e("Unable to parse the following value into a JSONObject: %s", getName());
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public JSONObject getFromDebugProperties() {
            return parseFromString(DebugProperties.getInstance().getDebugPropertyAsString(getDebugName(), (String) null));
        }
    }

    static class AppKeyParameter extends StringParameter {
        AppKeyParameter() {
            super("appId", DebugProperties.DEBUG_APPID);
        }

        /* access modifiers changed from: protected */
        public String getDerivedValue(ParameterData parameterData) {
            return MobileAdsInfoStore.getInstance().getRegistrationInfo().getAppKey();
        }
    }

    static class PublisherKeywordsParameter extends JSONArrayParameter {
        PublisherKeywordsParameter() {
            super("pk", DebugProperties.DEBUG_PK);
        }

        /* access modifiers changed from: protected */
        public JSONArray applyPostParameterProcessing(JSONArray value, ParameterData parameterData) {
            if (value == null) {
                value = new JSONArray();
            }
            Iterator i$ = parameterData.adTargetingOptions.getInternalPublisherKeywords().iterator();
            while (i$.hasNext()) {
                value.put(i$.next());
            }
            return value;
        }
    }

    static class UserAgentParameter extends StringParameter {
        UserAgentParameter() {
            super("ua", DebugProperties.DEBUG_UA);
        }

        /* access modifiers changed from: protected */
        public String getDerivedValue(ParameterData parameterData) {
            return MobileAdsInfoStore.getInstance().getDeviceInfo().getUserAgentString();
        }
    }

    static class SDKVersionParameter extends StringParameter {
        SDKVersionParameter() {
            super("adsdk", DebugProperties.DEBUG_VER);
        }

        /* access modifiers changed from: protected */
        public String getDerivedValue(ParameterData parameterData) {
            return Version.getSDKVersion();
        }
    }

    static class GeoLocationParameter extends StringParameter {
        private final Configuration configuration;
        private final Context context;

        GeoLocationParameter() {
            this(Configuration.getInstance(), MobileAdsInfoStore.getInstance().getApplicationContext());
        }

        GeoLocationParameter(Configuration configuration2, Context context2) {
            super("geoloc", DebugProperties.DEBUG_GEOLOC);
            this.configuration = configuration2;
            this.context = context2;
        }

        /* access modifiers changed from: protected */
        public String getDerivedValue(ParameterData parameterData) {
            Location location;
            if (!this.configuration.getBoolean(Configuration.ConfigOption.SEND_GEO) || !parameterData.getAdRequest().getAdTargetingOptions().isGeoLocationEnabled() || (location = new AdLocation(this.context).getLocation()) == null) {
                return null;
            }
            return location.getLatitude() + "," + location.getLongitude();
        }
    }

    static class DeviceInfoParameter extends JSONObjectParameter {
        DeviceInfoParameter() {
            super("dinfo", DebugProperties.DEBUG_DINFO);
        }

        /* access modifiers changed from: protected */
        public JSONObject getDerivedValue(ParameterData parameterData) {
            return MobileAdsInfoStore.getInstance().getDeviceInfo().toJsonObject(parameterData.adRequest.getOrientation());
        }
    }

    static class PackageInfoParameter extends JSONObjectParameter {
        PackageInfoParameter() {
            super("pkg", DebugProperties.DEBUG_PKG);
        }

        /* access modifiers changed from: protected */
        public JSONObject getDerivedValue(ParameterData parameterData) {
            return MobileAdsInfoStore.getInstance().getAppInfo().getPackageInfoJSON();
        }
    }

    static class TestParameter extends BooleanParameter {
        TestParameter() {
            super("isTest", DebugProperties.DEBUG_TEST);
        }

        /* access modifiers changed from: protected */
        public Boolean getDerivedValue(ParameterData parameterData) {
            return Settings.getInstance().getBoolean("testingEnabled", (Boolean) null);
        }
    }

    static class SISDeviceIdentifierParameter extends StringParameter {
        SISDeviceIdentifierParameter() {
            super("ad-id", DebugProperties.DEBUG_ADID);
        }

        /* access modifiers changed from: protected */
        public String getDerivedValue(ParameterData parameterData) {
            return parameterData.adRequest.getAdvertisingIdentifierInfo().getSISDeviceIdentifier();
        }
    }

    static class SHA1UDIDParameter extends StringParameter {
        SHA1UDIDParameter() {
            super("sha1_udid", DebugProperties.DEBUG_SHA1UDID);
        }

        /* access modifiers changed from: protected */
        public String getDerivedValue(ParameterData parameterData) {
            if (!parameterData.adRequest.getAdvertisingIdentifierInfo().hasAdvertisingIdentifier()) {
                return MobileAdsInfoStore.getInstance().getDeviceInfo().getUdidSha1();
            }
            return null;
        }
    }

    static class MD5UDIDParameter extends StringParameter {
        MD5UDIDParameter() {
            super("md5_udid", DebugProperties.DEBUG_MD5UDID);
        }

        /* access modifiers changed from: protected */
        public String getDerivedValue(ParameterData parameterData) {
            if (!parameterData.adRequest.getAdvertisingIdentifierInfo().hasAdvertisingIdentifier()) {
                return MobileAdsInfoStore.getInstance().getDeviceInfo().getUdidMd5();
            }
            return null;
        }
    }

    static class AdvertisingIdentifierParameter extends StringParameter {
        AdvertisingIdentifierParameter() {
            super("idfa", DebugProperties.DEBUG_IDFA);
        }

        /* access modifiers changed from: protected */
        public String getDerivedValue(ParameterData parameterData) {
            if (parameterData.adRequest.getAdvertisingIdentifierInfo().hasAdvertisingIdentifier()) {
                return parameterData.adRequest.getAdvertisingIdentifierInfo().getAdvertisingIdentifier();
            }
            return null;
        }
    }

    static class OptOutParameter extends BooleanParameter {
        OptOutParameter() {
            super("oo", DebugProperties.DEBUG_OPT_OUT);
        }

        /* access modifiers changed from: protected */
        public Boolean getDerivedValue(ParameterData parameterData) {
            if (parameterData.adRequest.getAdvertisingIdentifierInfo().hasAdvertisingIdentifier()) {
                return Boolean.valueOf(parameterData.adRequest.getAdvertisingIdentifierInfo().isLimitAdTrackingEnabled());
            }
            return null;
        }
    }

    static class SizeParameter extends StringParameter {
        SizeParameter() {
            super("sz", DebugProperties.DEBUG_SIZE);
        }

        /* access modifiers changed from: protected */
        public String getDerivedValue(ParameterData parameterData) {
            return parameterData.loiSlot.getAdSlot().getRequestedAdSize().toString();
        }
    }

    static class SlotParameter extends StringParameter {
        SlotParameter() {
            super("slot", DebugProperties.DEBUG_SLOT);
        }

        /* access modifiers changed from: protected */
        public String getDerivedValue(ParameterData parameterData) {
            return parameterData.adRequest.getOrientation();
        }
    }

    static class MaxSizeParameter extends StringParameter {
        MaxSizeParameter() {
            super("mxsz", DebugProperties.DEBUG_MXSZ);
        }

        /* access modifiers changed from: protected */
        public String getDerivedValue(ParameterData parameterData) {
            return parameterData.loiSlot.getAdSlot().getMaxSize();
        }
    }

    static class SlotIdParameter extends IntegerParameter {
        SlotIdParameter() {
            super("slotId", DebugProperties.DEBUG_SLOT_ID);
        }

        /* access modifiers changed from: protected */
        public Integer getDerivedValue(ParameterData parameterData) {
            return Integer.valueOf(parameterData.loiSlot.getAdSlot().getSlotNumber());
        }
    }

    static class FloorPriceParameter extends LongParameter {
        FloorPriceParameter() {
            super("ec", DebugProperties.DEBUG_ECPM);
        }

        /* access modifiers changed from: protected */
        public Long getDerivedValue(ParameterData parameterData) {
            if (parameterData.loiSlot.getAdTargetingOptions().hasFloorPrice()) {
                return Long.valueOf(parameterData.loiSlot.getAdTargetingOptions().getFloorPrice());
            }
            return null;
        }
    }

    static class SupportedMediaTypesParameter extends JSONArrayParameter {
        public SupportedMediaTypesParameter() {
            super("supportedMediaTypes", DebugProperties.DEBUG_SUPPORTED_MEDIA_TYPES);
        }

        /* access modifiers changed from: protected */
        public JSONArray getDerivedValue(ParameterData parameterData) {
            JSONArray value = new JSONArray();
            addDisplay(parameterData, value);
            addVideo(parameterData, value);
            return value;
        }

        private void addDisplay(ParameterData parameterData, JSONArray json) {
            boolean displayAdsEnabled = parameterData.loiSlot.getAdTargetingOptions().isDisplayAdsEnabled();
            if (parameterData.advancedOptions.containsKey("enableDisplayAds")) {
                displayAdsEnabled = Boolean.parseBoolean((String) parameterData.advancedOptions.remove("enableDisplayAds"));
            }
            if (displayAdsEnabled) {
                json.put("DISPLAY");
            }
        }

        private void addVideo(ParameterData parameterData, JSONArray json) {
            if (new VideoAdsEnabledChecker(parameterData).isVideoAdsEnabled()) {
                json.put("VIDEO");
            }
        }
    }

    static class VideoOptionsParameter extends JSONObjectParameter {
        private static final int MAXIMUM_DURATION_DEFAULT = 30000;
        private static final int MINIMUM_DURATION_DEFAULT = 0;

        public VideoOptionsParameter() {
            super("video", DebugProperties.DEBUG_VIDEO_OPTIONS);
        }

        /* access modifiers changed from: protected */
        public JSONObject getDerivedValue(ParameterData parameterData) {
            JSONObject value = null;
            if (new VideoAdsEnabledChecker(parameterData).isVideoAdsEnabled()) {
                value = new JSONObject();
                int minVideoAdDuration = 0;
                if (parameterData.advancedOptions.containsKey("minVideoAdDuration")) {
                    minVideoAdDuration = new Parsers.IntegerParser().setDefaultValue(0).setParseErrorLogTag(AAXParameter.LOGTAG).setParseErrorLogMessage("The minVideoAdDuration advanced option could not be parsed properly.").parse((String) parameterData.advancedOptions.remove("minVideoAdDuration"));
                }
                JSONUtils.put(value, "minAdDuration", minVideoAdDuration);
                int maxVideoAdDuration = MAXIMUM_DURATION_DEFAULT;
                if (parameterData.advancedOptions.containsKey("maxVideoAdDuration")) {
                    maxVideoAdDuration = new Parsers.IntegerParser().setDefaultValue(MAXIMUM_DURATION_DEFAULT).setParseErrorLogTag(AAXParameter.LOGTAG).setParseErrorLogMessage("The maxVideoAdDuration advanced option could not be parsed properly.").parse((String) parameterData.advancedOptions.remove("maxVideoAdDuration"));
                }
                JSONUtils.put(value, "maxAdDuration", maxVideoAdDuration);
            }
            return value;
        }
    }

    private static class VideoAdsEnabledChecker {
        private final ParameterData parameterData;

        public VideoAdsEnabledChecker(ParameterData parameterData2) {
            this.parameterData = parameterData2;
        }

        public boolean isVideoAdsEnabled() {
            if (!this.parameterData.loiSlot.getAdTargetingOptions().isVideoEnabledSettable()) {
                return false;
            }
            if (this.parameterData.advancedOptions.containsKey("enableVideoAds")) {
                String advancedOption = (String) this.parameterData.advancedOptions.remove("enableVideoAds");
                this.parameterData.temporaryOptions.put("enableVideoAds", advancedOption);
                return Boolean.parseBoolean(advancedOption);
            } else if (this.parameterData.temporaryOptions.containsKey("enableVideoAds")) {
                return Boolean.parseBoolean((String) this.parameterData.temporaryOptions.get("enableVideoAds"));
            } else {
                return this.parameterData.loiSlot.getAdTargetingOptions().isVideoAdsEnabled();
            }
        }
    }
}
