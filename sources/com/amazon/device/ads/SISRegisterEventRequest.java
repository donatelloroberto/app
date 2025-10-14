package com.amazon.device.ads;

import com.amazon.device.ads.AdvertisingIdentifier;
import com.amazon.device.ads.Configuration;
import com.amazon.device.ads.Metrics;
import com.amazon.device.ads.WebRequest;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: SISRequests */
class SISRegisterEventRequest implements SISRequest {
    private static final Metrics.MetricType CALL_METRIC_TYPE = Metrics.MetricType.SIS_LATENCY_REGISTER_EVENT;
    private static final String LOGTAG = "SISRegisterEventRequest";
    private static final String PATH = "/register_event";
    private final AdvertisingIdentifier.Info advertisingIdentifierInfo;
    private final JSONArray appEvents;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    /* compiled from: SISRequests */
    enum SISRequestType {
        GENERATE_DID,
        UPDATE_DEVICE_INFO,
        REGISTER_EVENT
    }

    public SISRegisterEventRequest(AdvertisingIdentifier.Info advertisingIdentifierInfo2, JSONArray appEvents2) {
        this.advertisingIdentifierInfo = advertisingIdentifierInfo2;
        this.appEvents = appEvents2;
    }

    public String getLogTag() {
        return LOGTAG;
    }

    public Metrics.MetricType getCallMetricType() {
        return CALL_METRIC_TYPE;
    }

    public String getPath() {
        return PATH;
    }

    public WebRequest.QueryStringParameters getQueryParameters() {
        WebRequest.QueryStringParameters queryStringParameters = new WebRequest.QueryStringParameters();
        queryStringParameters.putUrlEncoded("adId", this.advertisingIdentifierInfo.getSISDeviceIdentifier());
        queryStringParameters.putUrlEncoded("dt", MobileAdsInfoStore.getInstance().getDeviceInfo().getDeviceType());
        RegistrationInfo registrationInfo = MobileAdsInfoStore.getInstance().getRegistrationInfo();
        queryStringParameters.putUrlEncoded("app", registrationInfo.getAppName());
        queryStringParameters.putUrlEncoded("appId", registrationInfo.getAppKey());
        queryStringParameters.putUrlEncoded("aud", Configuration.getInstance().getString(Configuration.ConfigOption.SIS_DOMAIN));
        return queryStringParameters;
    }

    public HashMap<String, String> getPostParameters() {
        HashMap<String, String> eventsMap = new HashMap<>();
        eventsMap.put("events", this.appEvents.toString());
        return eventsMap;
    }

    public void onResponseReceived(JSONObject payload) {
        int statusCode = JSONUtils.getIntegerFromJSON(payload, "rcode", 0);
        if (statusCode == 1) {
            this.logger.d("Application events registered successfully.");
            AppEventRegistrationHandler.getInstance().onAppEventsRegistered();
            return;
        }
        this.logger.d("Application events not registered. rcode:" + statusCode);
    }

    public MobileAdsLogger getLogger() {
        return this.logger;
    }

    /* compiled from: SISRequests */
    static class SISRequestFactory {
        SISRequestFactory() {
        }

        public SISDeviceRequest createDeviceRequest(SISRequestType requestType) {
            switch (requestType) {
                case GENERATE_DID:
                    return new SISGenerateDIDRequest();
                case UPDATE_DEVICE_INFO:
                    return new SISUpdateDeviceInfoRequest();
                default:
                    throw new IllegalArgumentException("SISRequestType " + requestType + " is not a SISDeviceRequest");
            }
        }

        public SISRegisterEventRequest createRegisterEventRequest(AdvertisingIdentifier.Info advertisingIdentifierInfo, JSONArray appEvents) {
            return new SISRegisterEventRequest(advertisingIdentifierInfo, appEvents);
        }
    }
}
