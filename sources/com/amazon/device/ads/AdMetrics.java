package com.amazon.device.ads;

import com.amazon.device.ads.Metrics;
import com.amazon.device.ads.MetricsCollector;
import com.amazon.device.ads.WebRequest;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class AdMetrics {
    public static final String LOGTAG = AdMetrics.class.getSimpleName();
    private MetricsCollector globalMetrics;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    private final Metrics.MetricsSubmitter submitter;
    private final WebRequest.WebRequestFactory webRequestFactory = new WebRequest.WebRequestFactory();

    public AdMetrics(Metrics.MetricsSubmitter submitter2) {
        this.submitter = submitter2;
    }

    private String getAaxUrlAndResetAdMetrics() {
        String url = this.submitter.getInstrumentationPixelUrl() + WebUtils.getURLEncodedString(getAaxJson());
        this.submitter.resetMetricsCollector();
        return url;
    }

    public WebRequest getAaxWebRequestAndResetAdMetrics() {
        WebRequest webRequest = this.webRequestFactory.createWebRequest();
        webRequest.setUrlString(getAaxUrlAndResetAdMetrics());
        return webRequest;
    }

    public boolean canSubmit() {
        String pixelUrl = this.submitter.getInstrumentationPixelUrl();
        if (pixelUrl == null || pixelUrl.equals("")) {
            return false;
        }
        String appKey = MobileAdsInfoStore.getInstance().getRegistrationInfo().getAppKey();
        if (appKey != null && !appKey.equals("123")) {
            return true;
        }
        this.logger.d("Not submitting metrics because the AppKey is either not set or set to a test key.");
        return false;
    }

    public void addGlobalMetrics(MetricsCollector globalMetrics2) {
        this.globalMetrics = globalMetrics2;
    }

    /* access modifiers changed from: protected */
    public String getAaxJson() {
        JSONObject json = new JSONObject();
        JSONUtils.put(json, "c", "msdk");
        JSONUtils.put(json, "v", Version.getRawSDKVersion());
        addMetricsToJSON(json, this.submitter.getMetricsCollector());
        addMetricsToJSON(json, this.globalMetrics);
        String jsonString = json.toString();
        return jsonString.substring(1, jsonString.length() - 1);
    }

    protected static void addMetricsToJSON(JSONObject json, MetricsCollector metricsCollector) {
        if (metricsCollector != null) {
            HashMap<Metrics.MetricType, Long> startedMetricHits = new HashMap<>();
            HashMap<Metrics.MetricType, Integer> incrementedMetricHits = new HashMap<>();
            String adTypeMetricTag = metricsCollector.getAdTypeMetricTag();
            if (adTypeMetricTag != null) {
                adTypeMetricTag = adTypeMetricTag + "_";
            }
            for (MetricsCollector.MetricHit metricHit : (MetricsCollector.MetricHit[]) metricsCollector.getMetricHits().toArray(new MetricsCollector.MetricHit[metricsCollector.getMetricHits().size()])) {
                String metricName = metricHit.metric.getAaxName();
                if (adTypeMetricTag != null && metricHit.metric.isAdTypeSpecific()) {
                    metricName = adTypeMetricTag + metricName;
                }
                if (metricHit instanceof MetricsCollector.MetricHitStartTime) {
                    startedMetricHits.put(metricHit.metric, Long.valueOf(((MetricsCollector.MetricHitStartTime) metricHit).startTime));
                } else if (metricHit instanceof MetricsCollector.MetricHitStopTime) {
                    MetricsCollector.MetricHitStopTime metricHitStopTime = (MetricsCollector.MetricHitStopTime) metricHit;
                    Long startTime = startedMetricHits.remove(metricHit.metric);
                    if (startTime != null) {
                        JSONUtils.put(json, metricName, (metricHitStopTime.stopTime + JSONUtils.getLongFromJSON(json, metricName, 0)) - startTime.longValue());
                    }
                } else if (metricHit instanceof MetricsCollector.MetricHitTotalTime) {
                    JSONUtils.put(json, metricName, ((MetricsCollector.MetricHitTotalTime) metricHit).totalTime);
                } else if (metricHit instanceof MetricsCollector.MetricHitIncrement) {
                    MetricsCollector.MetricHitIncrement metricHitIncrement = (MetricsCollector.MetricHitIncrement) metricHit;
                    Integer increment = incrementedMetricHits.get(metricHit.metric);
                    incrementedMetricHits.put(metricHit.metric, Integer.valueOf(increment == null ? metricHitIncrement.increment : increment.intValue() + metricHitIncrement.increment));
                } else if (metricHit instanceof MetricsCollector.MetricHitString) {
                    JSONUtils.put(json, metricName, ((MetricsCollector.MetricHitString) metricHit).text);
                }
            }
            for (Map.Entry<Metrics.MetricType, Integer> incrementedMetric : incrementedMetricHits.entrySet()) {
                String metricName2 = incrementedMetric.getKey().getAaxName();
                if (adTypeMetricTag != null && incrementedMetric.getKey().isAdTypeSpecific()) {
                    metricName2 = adTypeMetricTag + metricName2;
                }
                JSONUtils.put(json, metricName2, incrementedMetric.getValue().intValue());
            }
        }
    }
}
