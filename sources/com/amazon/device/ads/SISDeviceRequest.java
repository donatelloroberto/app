package com.amazon.device.ads;

import com.amazon.device.ads.AdvertisingIdentifier;
import com.amazon.device.ads.Configuration;
import com.amazon.device.ads.Metrics;
import com.amazon.device.ads.WebRequest;
import java.util.HashMap;

/* compiled from: SISRequests */
abstract class SISDeviceRequest implements SISRequest {
    private AdvertisingIdentifier advertisingIdentifier;
    private AdvertisingIdentifier.Info advertisingIdentifierInfo;
    private Metrics.MetricType callMetricType;
    private String logTag;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(this.logTag);
    private String path;

    SISDeviceRequest() {
    }

    public SISDeviceRequest setLogTag(String logTag2) {
        this.logTag = logTag2;
        this.logger.withLogTag(logTag2);
        return this;
    }

    public SISDeviceRequest setCallMetricType(Metrics.MetricType callMetricType2) {
        this.callMetricType = callMetricType2;
        return this;
    }

    public SISDeviceRequest setPath(String path2) {
        this.path = path2;
        return this;
    }

    public SISDeviceRequest setAdvertisingIdentifier(AdvertisingIdentifier advertisingIdentifier2) {
        this.advertisingIdentifier = advertisingIdentifier2;
        this.advertisingIdentifierInfo = this.advertisingIdentifier.getAdvertisingIdentifierInfo();
        return this;
    }

    public String getLogTag() {
        return this.logTag;
    }

    public Metrics.MetricType getCallMetricType() {
        return this.callMetricType;
    }

    public String getPath() {
        return this.path;
    }

    public WebRequest.QueryStringParameters getQueryParameters() {
        WebRequest.QueryStringParameters queryStringParameters = new WebRequest.QueryStringParameters();
        queryStringParameters.putUrlEncoded("dt", MobileAdsInfoStore.getInstance().getDeviceInfo().getDeviceType());
        queryStringParameters.putUrlEncoded("app", MobileAdsInfoStore.getInstance().getRegistrationInfo().getAppName());
        queryStringParameters.putUrlEncoded("aud", Configuration.getInstance().getString(Configuration.ConfigOption.SIS_DOMAIN));
        queryStringParameters.putUrlEncoded("ua", WebUtils.getURLEncodedString(MobileAdsInfoStore.getInstance().getDeviceInfo().getUserAgentString()));
        queryStringParameters.putUrlEncoded("dinfo", WebUtils.getURLEncodedString(getDInfoProperty()));
        queryStringParameters.putUrlEncoded("pkg", WebUtils.getURLEncodedString(MobileAdsInfoStore.getInstance().getAppInfo().getPackageInfoJSONString()));
        if (this.advertisingIdentifierInfo.hasAdvertisingIdentifier()) {
            queryStringParameters.putUrlEncoded("idfa", this.advertisingIdentifierInfo.getAdvertisingIdentifier());
            queryStringParameters.putUrlEncoded("oo", convertOptOutBooleanToStringInt(this.advertisingIdentifierInfo.isLimitAdTrackingEnabled()));
        } else {
            DeviceInfo deviceInfo = MobileAdsInfoStore.getInstance().getDeviceInfo();
            queryStringParameters.putUrlEncoded("sha1_mac", deviceInfo.getMacSha1());
            queryStringParameters.putUrlEncoded("sha1_serial", deviceInfo.getSerialSha1());
            queryStringParameters.putUrlEncoded("sha1_udid", deviceInfo.getUdidSha1());
            queryStringParameters.putUrlEncodedIfTrue("badMac", "true", deviceInfo.isMacBad());
            queryStringParameters.putUrlEncodedIfTrue("badSerial", "true", deviceInfo.isSerialBad());
            queryStringParameters.putUrlEncodedIfTrue("badUdid", "true", deviceInfo.isUdidBad());
        }
        String adIdTransition = this.advertisingIdentifier.getAndClearTransition();
        queryStringParameters.putUrlEncodedIfTrue("aidts", adIdTransition, adIdTransition != null);
        return queryStringParameters;
    }

    private static String convertOptOutBooleanToStringInt(boolean optOut) {
        return optOut ? "1" : "0";
    }

    /* access modifiers changed from: protected */
    public AdvertisingIdentifier.Info getAdvertisingIdentifierInfo() {
        return this.advertisingIdentifierInfo;
    }

    public static String getDInfoProperty() {
        DeviceInfo deviceInfo = MobileAdsInfoStore.getInstance().getDeviceInfo();
        return String.format("{\"make\":\"%s\",\"model\":\"%s\",\"os\":\"%s\",\"osVersion\":\"%s\"}", new Object[]{deviceInfo.getMake(), deviceInfo.getModel(), deviceInfo.getOS(), deviceInfo.getOSVersion()});
    }

    public HashMap<String, String> getPostParameters() {
        return null;
    }

    public MobileAdsLogger getLogger() {
        return this.logger;
    }
}
