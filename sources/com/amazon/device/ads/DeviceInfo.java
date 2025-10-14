package com.amazon.device.ads;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONObject;

class DeviceInfo {
    private static final String LOGTAG = DeviceInfo.class.getSimpleName();
    public static final String ORIENTATION_LANDSCAPE = "landscape";
    public static final String ORIENTATION_PORTRAIT = "portrait";
    public static final String ORIENTATION_UNKNOWN = "unknown";
    private static final String dt = "android";
    private static final String os = "Android";
    private boolean bad_mac;
    private boolean bad_serial;
    private boolean bad_udid;
    private String carrier;
    private String country;
    private final MobileAdsInfoStore infoStore;
    private Size landscapeScreenSize;
    private String language;
    private final MobileAdsLogger logger;
    private boolean macFetched;
    private String make;
    private String md5_mac;
    private String md5_serial;
    private String md5_udid;
    private String model;
    private String osVersion;
    private Size portraitScreenSize;
    private float scalingFactor;
    private String scalingFactorAsString;
    private boolean serialFetched;
    private String sha1_mac;
    private String sha1_serial;
    private String sha1_udid;
    private String udid;
    private boolean udidFetched;
    private UserAgentManager userAgentManager;

    public DeviceInfo(Context context, UserAgentManager userAgentManager2) {
        this(context, userAgentManager2, MobileAdsInfoStore.getInstance(), new MobileAdsLoggerFactory());
    }

    DeviceInfo(Context context, UserAgentManager userAgentManager2, MobileAdsInfoStore infoStore2, MobileAdsLoggerFactory loggerFactory) {
        this.make = Build.MANUFACTURER;
        this.model = Build.MODEL;
        this.osVersion = Build.VERSION.RELEASE;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        this.infoStore = infoStore2;
        setCountry();
        setCarrier(context);
        setLanguage();
        setScalingFactor(context);
        this.userAgentManager = userAgentManager2;
    }

    public void setUserAgentManager(UserAgentManager userAgentManager2) {
        this.userAgentManager = userAgentManager2;
    }

    private void setMacAddressIfNotFetched() {
        if (!this.macFetched) {
            setMacAddress();
        }
    }

    /* access modifiers changed from: protected */
    public void setMacAddress() {
        WifiManager wifiManager = (WifiManager) this.infoStore.getApplicationContext().getSystemService("wifi");
        WifiInfo wifiInfo = null;
        if (wifiManager != null) {
            try {
                wifiInfo = wifiManager.getConnectionInfo();
            } catch (SecurityException e) {
                this.logger.d("Unable to get Wifi connection information: %s", e);
            } catch (ExceptionInInitializerError e2) {
                this.logger.d("Unable to get Wifi connection information: %s", e2);
            }
        }
        if (wifiInfo == null) {
            this.sha1_mac = null;
        } else {
            String macAddress = wifiInfo.getMacAddress();
            if (macAddress == null || macAddress.length() == 0) {
                this.sha1_mac = null;
                this.bad_mac = true;
            } else if (!Pattern.compile("((([0-9a-fA-F]){1,2}[-:]){5}([0-9a-fA-F]){1,2})").matcher(macAddress).find()) {
                this.sha1_mac = null;
                this.bad_mac = true;
            } else {
                this.sha1_mac = WebUtils.getURLEncodedString(StringUtils.sha1(macAddress));
            }
        }
        this.macFetched = true;
    }

    private void setSerialIfNotFetched() {
        if (!this.serialFetched) {
            setSerial();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setSerial() {
        /*
            r6 = this;
            r5 = 1
            r2 = 0
            java.lang.Class<android.os.Build> r3 = android.os.Build.class
            java.lang.String r4 = "SERIAL"
            java.lang.reflect.Field r1 = r3.getDeclaredField(r4)     // Catch:{ Exception -> 0x0034 }
            java.lang.Class<android.os.Build> r3 = android.os.Build.class
            java.lang.Object r3 = r1.get(r3)     // Catch:{ Exception -> 0x0034 }
            r0 = r3
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x0034 }
            r2 = r0
        L_0x0014:
            if (r2 == 0) goto L_0x0024
            int r3 = r2.length()
            if (r3 == 0) goto L_0x0024
            java.lang.String r3 = "unknown"
            boolean r3 = r2.equalsIgnoreCase(r3)
            if (r3 == 0) goto L_0x0029
        L_0x0024:
            r6.bad_serial = r5
        L_0x0026:
            r6.serialFetched = r5
            return
        L_0x0029:
            java.lang.String r3 = com.amazon.device.ads.StringUtils.sha1(r2)
            java.lang.String r3 = com.amazon.device.ads.WebUtils.getURLEncodedString(r3)
            r6.sha1_serial = r3
            goto L_0x0026
        L_0x0034:
            r3 = move-exception
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.device.ads.DeviceInfo.setSerial():void");
    }

    private void setUdidIfNotFetched() {
        if (!this.udidFetched) {
            setUdid();
        }
    }

    private void setUdid() {
        String udid2 = Settings.Secure.getString(this.infoStore.getApplicationContext().getContentResolver(), "android_id");
        if (StringUtils.isNullOrEmpty(udid2) || udid2.equalsIgnoreCase("9774d56d682e549c")) {
            this.udid = null;
            this.sha1_udid = null;
            this.bad_udid = true;
        } else {
            this.udid = WebUtils.getURLEncodedString(udid2);
            this.sha1_udid = WebUtils.getURLEncodedString(StringUtils.sha1(udid2));
        }
        this.udidFetched = true;
    }

    private void setLanguage() {
        String language2 = Locale.getDefault().getLanguage();
        if (language2 == null || language2.length() <= 0) {
            language2 = null;
        }
        this.language = language2;
    }

    private void setCountry() {
        String country2 = Locale.getDefault().getCountry();
        if (country2 == null || country2.length() <= 0) {
            country2 = null;
        }
        this.country = country2;
    }

    private void setCarrier(Context context) {
        TelephonyManager tManager = (TelephonyManager) context.getSystemService("phone");
        if (tManager != null) {
            String carrier2 = tManager.getNetworkOperatorName();
            if (carrier2 == null || carrier2.length() <= 0) {
                carrier2 = null;
            }
            this.carrier = carrier2;
        }
    }

    private void setScalingFactor(Context context) {
        if (!this.make.equals("motorola") || !this.model.equals("MB502")) {
            DisplayMetrics metrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(metrics);
            this.scalingFactor = metrics.scaledDensity;
        } else {
            this.scalingFactor = 1.0f;
        }
        this.scalingFactorAsString = Float.toString(this.scalingFactor);
    }

    public String getDeviceType() {
        return "android";
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public String getOS() {
        return os;
    }

    public String getOSVersion() {
        return this.osVersion;
    }

    public String getMacSha1() {
        setMacAddressIfNotFetched();
        return this.sha1_mac;
    }

    public String getMacMd5() {
        setMacAddressIfNotFetched();
        return this.md5_mac;
    }

    public boolean isMacBad() {
        setMacAddressIfNotFetched();
        return this.bad_mac;
    }

    public String getSerialSha1() {
        setSerialIfNotFetched();
        return this.sha1_serial;
    }

    public String getSerialMd5() {
        setSerialIfNotFetched();
        return this.md5_serial;
    }

    public boolean isSerialBad() {
        setSerialIfNotFetched();
        return this.bad_serial;
    }

    public String getUdid() {
        setUdidIfNotFetched();
        return this.udid;
    }

    public String getUdidSha1() {
        setUdidIfNotFetched();
        return this.sha1_udid;
    }

    public String getUdidMd5() {
        setUdidIfNotFetched();
        return this.md5_udid;
    }

    public boolean isUdidBad() {
        setUdidIfNotFetched();
        return this.bad_udid;
    }

    public String getCarrier() {
        return this.carrier;
    }

    public String getCountry() {
        return this.country;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getScalingFactorAsString() {
        return this.scalingFactorAsString;
    }

    public float getScalingFactorAsFloat() {
        return this.scalingFactor;
    }

    public String getUserAgentString() {
        return this.userAgentManager.getUserAgentString();
    }

    public void setUserAgentString(String ua) {
        this.userAgentManager.setUserAgentString(ua);
    }

    public void populateUserAgentString(Context context) {
        this.userAgentManager.populateUserAgentString(context);
    }

    public String getOrientation() {
        switch (DisplayUtils.determineCanonicalScreenOrientation(this.infoStore.getApplicationContext())) {
            case 0:
            case 8:
                return ORIENTATION_LANDSCAPE;
            case 1:
            case 9:
                return ORIENTATION_PORTRAIT;
            default:
                return "unknown";
        }
    }

    public Size getScreenSize(String orientation) {
        if (orientation.equals(ORIENTATION_PORTRAIT) && this.portraitScreenSize != null) {
            return this.portraitScreenSize;
        }
        if (orientation.equals(ORIENTATION_LANDSCAPE) && this.landscapeScreenSize != null) {
            return this.landscapeScreenSize;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) this.infoStore.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(metrics);
        String screenSize = String.valueOf(metrics.widthPixels) + "x" + String.valueOf(metrics.heightPixels);
        if (orientation.equals(ORIENTATION_PORTRAIT)) {
            this.portraitScreenSize = new Size(screenSize);
            return this.portraitScreenSize;
        } else if (!orientation.equals(ORIENTATION_LANDSCAPE)) {
            return new Size(screenSize);
        } else {
            this.landscapeScreenSize = new Size(screenSize);
            return this.landscapeScreenSize;
        }
    }

    public JSONObject getDInfoProperty() {
        JSONObject json = new JSONObject();
        JSONUtils.put(json, "make", getMake());
        JSONUtils.put(json, "model", getModel());
        JSONUtils.put(json, "os", getOS());
        JSONUtils.put(json, "osVersion", getOSVersion());
        JSONUtils.put(json, "scalingFactor", getScalingFactorAsString());
        JSONUtils.put(json, "language", getLanguage());
        JSONUtils.put(json, "country", getCountry());
        JSONUtils.put(json, "carrier", getCarrier());
        return json;
    }

    public String toJsonString() {
        return toJsonObject(getOrientation()).toString();
    }

    /* access modifiers changed from: package-private */
    public JSONObject toJsonObject(String orientation) {
        JSONObject json = getDInfoProperty();
        JSONUtils.put(json, "orientation", orientation);
        JSONUtils.put(json, "screenSize", getScreenSize(orientation).toString());
        JSONUtils.put(json, "connectionType", new ConnectionInfo(this.infoStore).getConnectionType());
        return json;
    }
}
