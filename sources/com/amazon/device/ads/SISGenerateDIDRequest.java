package com.amazon.device.ads;

import com.amazon.device.ads.Metrics;
import org.json.JSONObject;

/* compiled from: SISRequests */
class SISGenerateDIDRequest extends SISDeviceRequest {
    private static final Metrics.MetricType CALL_METRIC_TYPE = Metrics.MetricType.SIS_LATENCY_REGISTER;
    private static final String LOGTAG = SISGenerateDIDRequest.class.getSimpleName();
    private static final String PATH = "/generate_did";

    public SISGenerateDIDRequest() {
        setCallMetricType(CALL_METRIC_TYPE);
        setLogTag(LOGTAG);
        setPath(PATH);
    }

    public void onResponseReceived(JSONObject payload) {
        String adId = JSONUtils.getStringFromJSON(payload, "adId", "");
        if (adId.length() > 0) {
            MobileAdsInfoStore.getInstance().getRegistrationInfo().putAdId(adId, getAdvertisingIdentifierInfo());
        }
    }
}
