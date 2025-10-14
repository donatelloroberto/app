package com.amazon.device.ads;

class Version {
    private static String buildVersion = "5.6.20";
    private static String devBuild = "(DEV)";
    private static String prefixVersion = "amznAdSDK-android-";
    private static String sdkVersion = null;
    private static String userAgentPrefixVersion = "AmazonAdSDK-Android/";
    private static String userAgentSDKVersion = null;

    Version() {
    }

    public static String getRawSDKVersion() {
        String rawVersion = buildVersion;
        if (rawVersion == null || rawVersion.equals("")) {
            return devBuild;
        }
        if (rawVersion.endsWith("x")) {
            return rawVersion + devBuild;
        }
        return rawVersion;
    }

    public static String getSDKVersion() {
        if (sdkVersion == null) {
            sdkVersion = prefixVersion + getRawSDKVersion();
        }
        return sdkVersion;
    }

    protected static void setSDKVersion(String newSdkVersion) {
        sdkVersion = newSdkVersion;
    }

    public static String getUserAgentSDKVersion() {
        if (userAgentSDKVersion == null) {
            userAgentSDKVersion = userAgentPrefixVersion + getRawSDKVersion();
        }
        return userAgentSDKVersion;
    }
}
