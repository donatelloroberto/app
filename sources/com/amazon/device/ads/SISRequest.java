package com.amazon.device.ads;

import com.amazon.device.ads.Metrics;
import com.amazon.device.ads.WebRequest;
import java.util.HashMap;
import org.json.JSONObject;

/* compiled from: SISRequestor */
interface SISRequest {
    Metrics.MetricType getCallMetricType();

    String getLogTag();

    MobileAdsLogger getLogger();

    String getPath();

    HashMap<String, String> getPostParameters();

    WebRequest.QueryStringParameters getQueryParameters();

    void onResponseReceived(JSONObject jSONObject);
}
