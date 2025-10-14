package com.amazon.device.ads;

import android.support.v4.app.NotificationCompat;
import com.amazon.device.ads.Configuration;
import com.amazon.device.ads.WebRequest;
import java.util.Map;
import org.json.JSONObject;

class SISRequestor {
    protected static final String API_LEVEL_ENDPOINT = "/api3";
    private final SISRequestorCallback sisRequestorCallback;
    private final SISRequest[] sisRequests;
    private final WebRequest.WebRequestFactory webRequestFactory;

    public SISRequestor(SISRequest... sisRequests2) {
        this((SISRequestorCallback) null, sisRequests2);
    }

    public SISRequestor(SISRequestorCallback sisRequestorCallback2, SISRequest... sisRequests2) {
        this(new WebRequest.WebRequestFactory(), sisRequestorCallback2, sisRequests2);
    }

    SISRequestor(WebRequest.WebRequestFactory webRequestFactory2, SISRequestorCallback sisRequestorCallback2, SISRequest... sisRequests2) {
        this.webRequestFactory = webRequestFactory2;
        this.sisRequestorCallback = sisRequestorCallback2;
        this.sisRequests = sisRequests2;
    }

    public void startCallSIS() {
        callSIS();
        SISRequestorCallback sisRequestorCallback2 = getSisRequestorCallback();
        if (sisRequestorCallback2 != null) {
            sisRequestorCallback2.onSISCallComplete();
        }
    }

    /* access modifiers changed from: protected */
    public void callSIS() {
        for (SISRequest sisRequest : this.sisRequests) {
            callSIS(sisRequest);
        }
    }

    /* access modifiers changed from: protected */
    public void callSIS(SISRequest sisRequest) {
        try {
            JSONObject jsonPayload = getWebRequest(sisRequest).makeCall().getResponseReader().readAsJSON();
            if (jsonPayload != null) {
                int rcode = JSONUtils.getIntegerFromJSON(jsonPayload, "rcode", 0);
                String msg = JSONUtils.getStringFromJSON(jsonPayload, NotificationCompat.CATEGORY_MESSAGE, "");
                if (rcode == 1) {
                    sisRequest.getLogger().i("Result - code: %d, msg: %s", Integer.valueOf(rcode), msg);
                    sisRequest.onResponseReceived(jsonPayload);
                    return;
                }
                sisRequest.getLogger().w("Result - code: %d, msg: %s", Integer.valueOf(rcode), msg);
            }
        } catch (WebRequest.WebRequestException e) {
        }
    }

    /* access modifiers changed from: protected */
    public WebRequest getWebRequest(SISRequest sisRequest) {
        WebRequest request = this.webRequestFactory.createWebRequest();
        request.setExternalLogTag(sisRequest.getLogTag());
        request.setHttpMethod(WebRequest.HttpMethod.POST);
        request.setHost(getHostname());
        request.setPath(getEndpoint(sisRequest));
        request.enableLog(true);
        if (sisRequest.getPostParameters() != null) {
            for (Map.Entry<String, String> postParameter : sisRequest.getPostParameters().entrySet()) {
                request.putPostParameter(postParameter.getKey(), postParameter.getValue());
            }
        }
        WebRequest.QueryStringParameters queryStringParameters = sisRequest.getQueryParameters();
        queryStringParameters.putUrlEncoded("appId", MobileAdsInfoStore.getInstance().getRegistrationInfo().getAppKey());
        queryStringParameters.putUrlEncoded("sdkVer", Version.getSDKVersion());
        request.setQueryStringParameters(queryStringParameters);
        request.setMetricsCollector(Metrics.getInstance().getMetricsCollector());
        request.setServiceCallLatencyMetric(sisRequest.getCallMetricType());
        return request;
    }

    protected static String getHostname() {
        int endpointIndex;
        String hostname = Configuration.getInstance().getString(Configuration.ConfigOption.SIS_URL);
        if (hostname == null || (endpointIndex = hostname.indexOf("/")) <= -1) {
            return hostname;
        }
        return hostname.substring(0, endpointIndex);
    }

    protected static String getEndpoint(SISRequest sisRequest) {
        String endpoint = Configuration.getInstance().getString(Configuration.ConfigOption.SIS_URL);
        if (endpoint != null) {
            int endpointIndex = endpoint.indexOf("/");
            if (endpointIndex > -1) {
                endpoint = endpoint.substring(endpointIndex);
            } else {
                endpoint = "";
            }
        }
        return endpoint + API_LEVEL_ENDPOINT + sisRequest.getPath();
    }

    /* access modifiers changed from: protected */
    public SISRequestorCallback getSisRequestorCallback() {
        return this.sisRequestorCallback;
    }

    static class SISRequestorFactory {
        SISRequestorFactory() {
        }

        public SISRequestor createSISRequestor(SISRequest... sisRequests) {
            return createSISRequestor((SISRequestorCallback) null, sisRequests);
        }

        public SISRequestor createSISRequestor(SISRequestorCallback sisRequestorCallback, SISRequest... sisRequests) {
            return new SISRequestor(sisRequestorCallback, sisRequests);
        }
    }
}
