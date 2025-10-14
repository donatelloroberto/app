package com.amazon.device.ads;

import com.amazon.device.ads.Metrics;
import com.amazon.device.ads.WebRequest;
import org.json.JSONObject;

/* compiled from: SISRequests */
class SISUpdateDeviceInfoRequest extends SISDeviceRequest {
    private static final Metrics.MetricType CALL_METRIC_TYPE = Metrics.MetricType.SIS_LATENCY_UPDATE_DEVICE_INFO;
    private static final String LOGTAG = "SISUpdateDeviceInfoRequest";
    private static final String PATH = "/update_dev_info";

    public SISUpdateDeviceInfoRequest() {
        setCallMetricType(CALL_METRIC_TYPE);
        setLogTag(LOGTAG);
        setPath(PATH);
    }

    public WebRequest.QueryStringParameters getQueryParameters() {
        String adId = DebugProperties.getInstance().getDebugPropertyAsString(DebugProperties.DEBUG_ADID, getAdvertisingIdentifierInfo().getSISDeviceIdentifier());
        WebRequest.QueryStringParameters baseQueryParameters = super.getQueryParameters();
        if (!StringUtils.isNullOrEmpty(adId)) {
            baseQueryParameters.putUrlEncoded("adId", adId);
        }
        return baseQueryParameters;
    }

    public void onResponseReceived(JSONObject payload) {
        String adId = JSONUtils.getStringFromJSON(payload, "adId", "");
        if (JSONUtils.getBooleanFromJSON(payload, "idChanged", false)) {
            Metrics.getInstance().getMetricsCollector().incrementMetric(Metrics.MetricType.SIS_COUNTER_IDENTIFIED_DEVICE_CHANGED);
        }
        if (adId.length() > 0) {
            MobileAdsInfoStore.getInstance().getRegistrationInfo().putAdId(adId, getAdvertisingIdentifierInfo());
        }
    }
}
