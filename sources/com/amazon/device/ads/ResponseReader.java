package com.amazon.device.ads;

import java.io.InputStream;
import org.json.JSONObject;

class ResponseReader {
    private static final String LOGTAG = ResponseReader.class.getSimpleName();
    private boolean enableLog = false;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    private final InputStream stream;

    ResponseReader(InputStream stream2) {
        this.stream = stream2;
    }

    public InputStream getInputStream() {
        return this.stream;
    }

    public void enableLog(boolean enabled) {
        this.enableLog = enabled;
    }

    public void setExternalLogTag(String externalLogTag) {
        if (externalLogTag == null) {
            this.logger.withLogTag(LOGTAG);
        } else {
            this.logger.withLogTag(LOGTAG + " " + externalLogTag);
        }
    }

    public String readAsString() {
        String response = StringUtils.readStringFromInputStream(this.stream);
        if (this.enableLog) {
            this.logger.d("Response Body: %s", response);
        }
        return response;
    }

    public JSONObject readAsJSON() {
        return JSONUtils.getJSONObjectFromString(readAsString());
    }
}
