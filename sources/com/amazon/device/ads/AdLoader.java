package com.amazon.device.ads;

import android.support.v4.app.NotificationCompat;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.Metrics;
import com.amazon.device.ads.MetricsCollector;
import com.amazon.device.ads.ThreadUtils;
import com.amazon.device.ads.WebRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

class AdLoader {
    public static final int AD_FAILED = -1;
    public static final int AD_LOAD_DEFERRED = 1;
    public static final int AD_READY_TO_LOAD = 0;
    public static final String DISABLED_APP_SERVER_MESSAGE = "DISABLED_APP";
    private static final String LOGTAG = AdLoader.class.getSimpleName();
    private final AdRequest adRequest;
    private final Assets assets;
    private MetricsCollector.CompositeMetricsCollector compositeMetricsCollector;
    private final DebugProperties debugProperties;
    private AdError error;
    private final MobileAdsInfoStore infoStore;
    private final MobileAdsLogger logger;
    private final Map<Integer, AdSlot> slots;
    private final SystemTime systemTime;
    private final ThreadUtils.ThreadRunner threadRunner;
    private int timeout;

    public AdLoader(AdRequest adRequest2, Map<Integer, AdSlot> slots2) {
        this(adRequest2, slots2, ThreadUtils.getThreadRunner(), new SystemTime(), Assets.getInstance(), MobileAdsInfoStore.getInstance(), new MobileAdsLoggerFactory(), DebugProperties.getInstance());
    }

    AdLoader(AdRequest adRequest2, Map<Integer, AdSlot> slots2, ThreadUtils.ThreadRunner threadRunner2, SystemTime systemTime2, Assets assets2, MobileAdsInfoStore infoStore2, MobileAdsLoggerFactory loggerFactory, DebugProperties debugProperties2) {
        this.timeout = 20000;
        this.error = null;
        this.compositeMetricsCollector = null;
        this.adRequest = adRequest2;
        this.slots = slots2;
        this.threadRunner = threadRunner2;
        this.systemTime = systemTime2;
        this.assets = assets2;
        this.infoStore = infoStore2;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        this.debugProperties = debugProperties2;
    }

    public void setTimeout(int timeout2) {
        this.timeout = timeout2;
    }

    public void beginFetchAd() {
        getCompositeMetricsCollector().stopMetric(Metrics.MetricType.AD_LOAD_LATENCY_LOADAD_TO_FETCH_THREAD_REQUEST_START);
        getCompositeMetricsCollector().startMetric(Metrics.MetricType.AD_LOAD_LATENCY_FETCH_THREAD_SPIN_UP);
        startFetchAdThread();
    }

    /* access modifiers changed from: protected */
    public void startFetchAdThread() {
        this.threadRunner.execute(new Runnable() {
            public void run() {
                AdLoader.this.fetchAd();
                AdLoader.this.beginFinalizeFetchAd();
            }
        }, ThreadUtils.ExecutionStyle.SCHEDULE, ThreadUtils.ExecutionThread.BACKGROUND_THREAD);
    }

    /* access modifiers changed from: private */
    public void beginFinalizeFetchAd() {
        this.threadRunner.execute(new Runnable() {
            public void run() {
                AdLoader.this.finalizeFetchAd();
            }
        }, ThreadUtils.ExecutionStyle.SCHEDULE, ThreadUtils.ExecutionThread.MAIN_THREAD);
    }

    /* access modifiers changed from: protected */
    public void fetchAd() {
        getCompositeMetricsCollector().stopMetric(Metrics.MetricType.AD_LOAD_LATENCY_FETCH_THREAD_SPIN_UP);
        getCompositeMetricsCollector().startMetric(Metrics.MetricType.AD_LOAD_LATENCY_FETCH_THREAD_START_TO_AAX_GET_AD_START);
        if (!this.assets.ensureAssetsCreated()) {
            this.error = new AdError(AdError.ErrorCode.REQUEST_ERROR, "Unable to create the assets needed to display ads");
            this.logger.e("Unable to create the assets needed to display ads");
            setErrorForAllSlots(this.error);
            return;
        }
        try {
            WebRequest.WebResponse response = fetchResponseFromNetwork();
            if (!response.isHttpStatusCodeOK()) {
                String msg = response.getHttpStatusCode() + " - " + response.getHttpStatus();
                this.error = new AdError(AdError.ErrorCode.NETWORK_ERROR, msg);
                this.logger.e(msg);
                setErrorForAllSlots(this.error);
                return;
            }
            JSONObject jsonResponse = response.getResponseReader().readAsJSON();
            if (jsonResponse == null) {
                this.error = new AdError(AdError.ErrorCode.INTERNAL_ERROR, "Unable to parse response");
                this.logger.e("Unable to parse response");
                setErrorForAllSlots(this.error);
                return;
            }
            parseResponse(jsonResponse);
            getCompositeMetricsCollector().stopMetric(Metrics.MetricType.AD_LOAD_LATENCY_AAX_GET_AD_END_TO_FETCH_THREAD_END);
            getCompositeMetricsCollector().startMetric(Metrics.MetricType.AD_LOAD_LATENCY_FINALIZE_FETCH_SPIN_UP);
        } catch (AdFetchException e) {
            this.error = e.getAdError();
            this.logger.e(e.getAdError().getMessage());
            setErrorForAllSlots(this.error);
        }
    }

    private WebRequest getAdRequest() throws AdFetchException {
        getCompositeMetricsCollector().startMetric(Metrics.MetricType.AD_LOAD_LATENCY_CREATE_AAX_GET_AD_URL);
        WebRequest request = this.adRequest.getWebRequest();
        getCompositeMetricsCollector().stopMetric(Metrics.MetricType.AD_LOAD_LATENCY_CREATE_AAX_GET_AD_URL);
        return request;
    }

    private void parseResponse(JSONObject jsonResponse) {
        long currentTime = this.systemTime.currentTimeMillis();
        String status = JSONUtils.getStringFromJSON(jsonResponse, NotificationCompat.CATEGORY_STATUS, (String) null);
        HashSet hashSet = new HashSet(this.slots.keySet());
        AdError adError = getAdError(jsonResponse);
        String errorCode = JSONUtils.getStringFromJSON(jsonResponse, "errorCode", "No Ad Received");
        this.adRequest.setInstrumentationPixelURL(JSONUtils.getStringFromJSON(jsonResponse, "instrPixelURL", (String) null));
        if (status != null && status.equals("ok")) {
            JSONArray adsArray = JSONUtils.getJSONArrayFromJSON(jsonResponse, "ads");
            for (int i = 0; i < adsArray.length(); i++) {
                JSONObject slot = JSONUtils.getJSONObjectFromJSONArray(adsArray, i);
                if (slot != null) {
                    int slotId = JSONUtils.getIntegerFromJSON(slot, "slotId", -1);
                    AdSlot adSlot = this.slots.get(Integer.valueOf(slotId));
                    if (adSlot != null) {
                        hashSet.remove(Integer.valueOf(slotId));
                        String adPixelUrl = JSONUtils.getStringFromJSON(slot, "instrPixelURL", this.adRequest.getInstrumentationPixelURL());
                        AdData adData = new AdData();
                        adData.setInstrumentationPixelUrl(adPixelUrl);
                        adData.setImpressionPixelUrl(JSONUtils.getStringFromJSON(slot, "impPixelURL", (String) null));
                        if (adSlot.getRequestedAdSize().isAuto()) {
                            adSlot.getMetricsCollector().incrementMetric(Metrics.MetricType.AD_COUNTER_AUTO_AD_SIZE);
                        }
                        String creative = JSONUtils.getStringFromJSON(slot, "html", "");
                        JSONArray creativeTypes = JSONUtils.getJSONArrayFromJSON(slot, "creativeTypes");
                        HashSet<AAXCreative> creativeSet = new HashSet<>();
                        if (creativeTypes != null) {
                            for (int j = 0; j < creativeTypes.length(); j++) {
                                int creativeType = JSONUtils.getIntegerFromJSONArray(creativeTypes, j, 0);
                                AAXCreative aaxCreative = AAXCreative.getCreativeType(creativeType);
                                if (aaxCreative != null) {
                                    creativeSet.add(aaxCreative);
                                } else {
                                    this.logger.w("%d is not a recognized creative type.", Integer.valueOf(creativeType));
                                }
                            }
                        }
                        if (!AAXCreative.containsPrimaryCreativeType(creativeSet)) {
                            adSlot.setAdError(new AdError(AdError.ErrorCode.INTERNAL_ERROR, "No valid creative types found"));
                            this.logger.e("No valid creative types found");
                        } else {
                            String size = JSONUtils.getStringFromJSON(slot, "size", "");
                            if (size != null && ((size.equals("9999x9999") || size.equals("interstitial")) && !creativeSet.contains(AAXCreative.INTERSTITIAL))) {
                                creativeSet.add(AAXCreative.INTERSTITIAL);
                            }
                            int adWidth = 0;
                            int adHeight = 0;
                            if (!creativeSet.contains(AAXCreative.INTERSTITIAL)) {
                                boolean sizeIsInvalid = false;
                                String[] sizes = size != null ? size.split("x") : null;
                                if (sizes == null || sizes.length != 2) {
                                    sizeIsInvalid = true;
                                } else {
                                    try {
                                        adWidth = Integer.parseInt(sizes[0]);
                                        adHeight = Integer.parseInt(sizes[1]);
                                    } catch (NumberFormatException e) {
                                        sizeIsInvalid = true;
                                    }
                                }
                                if (sizeIsInvalid) {
                                    adSlot.setAdError(new AdError(AdError.ErrorCode.INTERNAL_ERROR, "Server returned an invalid ad size"));
                                    this.logger.e("Server returned an invalid ad size");
                                }
                            }
                            long expiration = JSONUtils.getLongFromJSON(slot, "cacheTTL", -1);
                            if (expiration > -1) {
                                adData.setExpirationTimeMillis(currentTime + (1000 * expiration));
                            }
                            AdProperties adProperties = new AdProperties(creativeTypes);
                            adData.setHeight(adHeight);
                            adData.setWidth(adWidth);
                            adData.setCreative(creative);
                            adData.setCreativeTypes(creativeSet);
                            adData.setProperties(adProperties);
                            adData.setFetched(true);
                            adSlot.setAdData(adData);
                        }
                    }
                }
            }
        }
        Iterator i$ = hashSet.iterator();
        while (i$.hasNext()) {
            Integer slotId2 = (Integer) i$.next();
            this.slots.get(slotId2).setAdError(adError);
            AdData adData2 = new AdData();
            adData2.setInstrumentationPixelUrl(this.adRequest.getInstrumentationPixelURL());
            this.slots.get(slotId2).setAdData(adData2);
            this.logger.w("%s; code: %s", adError.getMessage(), errorCode);
        }
    }

    /* access modifiers changed from: protected */
    public AdError getAdError(JSONObject jsonResponse) {
        int noRetryTtlSeconds = retrieveNoRetryTtlSeconds(jsonResponse);
        this.infoStore.setNoRetryTtl(noRetryTtlSeconds);
        String errorMsg = JSONUtils.getStringFromJSON(jsonResponse, "errorMessage", "No Ad Received");
        this.infoStore.setIsAppDisabled(errorMsg.equalsIgnoreCase(DISABLED_APP_SERVER_MESSAGE));
        String msg = "Server Message: " + errorMsg;
        if (noRetryTtlSeconds > 0) {
            getCompositeMetricsCollector().publishMetricInMilliseconds(Metrics.MetricType.AD_NO_RETRY_TTL_RECEIVED, (long) (noRetryTtlSeconds * 1000));
        }
        if (noRetryTtlSeconds > 0 && !this.infoStore.getIsAppDisabled()) {
            return new AdError(AdError.ErrorCode.NO_FILL, msg + ". Try again in " + noRetryTtlSeconds + " seconds");
        } else if (errorMsg.equals("no results")) {
            return new AdError(AdError.ErrorCode.NO_FILL, msg);
        } else {
            return new AdError(AdError.ErrorCode.INTERNAL_ERROR, msg);
        }
    }

    private void setErrorForAllSlots(AdError error2) {
        for (AdSlot slot : this.slots.values()) {
            slot.setAdError(error2);
        }
    }

    /* access modifiers changed from: protected */
    public int retrieveNoRetryTtlSeconds(JSONObject jsonResponse) {
        return this.debugProperties.getDebugPropertyAsInteger(DebugProperties.DEBUG_NORETRYTTL, Integer.valueOf(JSONUtils.getIntegerFromJSON(jsonResponse, "noretryTTL", 0))).intValue();
    }

    /* access modifiers changed from: protected */
    public void finalizeFetchAd() {
        for (Map.Entry<Integer, AdSlot> entry : this.slots.entrySet()) {
            AdSlot slot = entry.getValue();
            if (!slot.canBeUsed()) {
                this.logger.w("Ad object was destroyed before ad fetching could be finalized. Ad fetching has been aborted.");
            } else {
                slot.getMetricsCollector().stopMetric(Metrics.MetricType.AD_LOAD_LATENCY_FINALIZE_FETCH_SPIN_UP);
                if (!slot.isFetched()) {
                    slot.getMetricsCollector().startMetric(Metrics.MetricType.AD_LOAD_LATENCY_FINALIZE_FETCH_START_TO_FAILURE);
                    if (slot.getAdError() != null) {
                        slot.adFailed(slot.getAdError());
                    } else {
                        slot.adFailed(new AdError(AdError.ErrorCode.INTERNAL_ERROR, "Unknown error occurred."));
                    }
                } else {
                    slot.getMetricsCollector().startMetric(Metrics.MetricType.AD_LOAD_LATENCY_FINALIZE_FETCH_START_TO_RENDER_START);
                    slot.initializeAd();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public WebRequest.WebResponse fetchResponseFromNetwork() throws AdFetchException {
        AdError error2;
        WebRequest request = getAdRequest();
        request.setMetricsCollector(getCompositeMetricsCollector());
        request.setServiceCallLatencyMetric(Metrics.MetricType.AAX_LATENCY_GET_AD);
        request.setTimeout(this.timeout);
        request.setDisconnectEnabled(false);
        getCompositeMetricsCollector().stopMetric(Metrics.MetricType.AD_LOAD_LATENCY_FETCH_THREAD_START_TO_AAX_GET_AD_START);
        getCompositeMetricsCollector().incrementMetric(Metrics.MetricType.TLS_ENABLED);
        try {
            WebRequest.WebResponse response = request.makeCall();
            getCompositeMetricsCollector().startMetric(Metrics.MetricType.AD_LOAD_LATENCY_AAX_GET_AD_END_TO_FETCH_THREAD_END);
            return response;
        } catch (WebRequest.WebRequestException e) {
            if (e.getStatus() == WebRequest.WebRequestStatus.NETWORK_FAILURE) {
                error2 = new AdError(AdError.ErrorCode.NETWORK_ERROR, "Could not contact Ad Server");
            } else if (e.getStatus() == WebRequest.WebRequestStatus.NETWORK_TIMEOUT) {
                error2 = new AdError(AdError.ErrorCode.NETWORK_TIMEOUT, "Connection to Ad Server timed out");
            } else {
                error2 = new AdError(AdError.ErrorCode.INTERNAL_ERROR, e.getMessage());
            }
            throw new AdFetchException(error2);
        }
    }

    private MetricsCollector getCompositeMetricsCollector() {
        if (this.compositeMetricsCollector == null) {
            ArrayList<MetricsCollector> collectors = new ArrayList<>();
            for (Map.Entry<Integer, AdSlot> entry : this.slots.entrySet()) {
                collectors.add(entry.getValue().getMetricsCollector());
            }
            this.compositeMetricsCollector = new MetricsCollector.CompositeMetricsCollector(collectors);
        }
        return this.compositeMetricsCollector;
    }

    protected class AdFetchException extends Exception {
        private static final long serialVersionUID = 1;
        private final AdError adError;

        public AdFetchException(AdError adError2) {
            this.adError = adError2;
        }

        public AdFetchException(AdError adError2, Throwable throwable) {
            super(throwable);
            this.adError = adError2;
        }

        public AdError getAdError() {
            return this.adError;
        }
    }

    protected static class AdLoaderFactory {
        protected AdLoaderFactory() {
        }

        public AdLoader createAdLoader(AdRequest adRequest, Map<Integer, AdSlot> slots) {
            return new AdLoader(adRequest, slots);
        }
    }
}
