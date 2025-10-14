package com.amazon.device.ads;

import android.content.Context;

class BasicUserAgentManager implements UserAgentManager {
    private String userAgentStringWithSDKVersion;
    private String userAgentStringWithoutSDKVersion;

    BasicUserAgentManager() {
    }

    public String getUserAgentString() {
        return this.userAgentStringWithSDKVersion;
    }

    public void setUserAgentString(String newUserAgent) {
        if (newUserAgent != null && !newUserAgent.equals(this.userAgentStringWithoutSDKVersion)) {
            this.userAgentStringWithoutSDKVersion = newUserAgent;
            this.userAgentStringWithSDKVersion = newUserAgent + " " + Version.getUserAgentSDKVersion();
        }
    }

    public void populateUserAgentString(Context context) {
        if (this.userAgentStringWithSDKVersion != null) {
            return;
        }
        if (AndroidTargetUtils.isAtLeastAndroidAPI(7)) {
            setUserAgentString(System.getProperty("http.agent"));
        } else {
            buildAndSetUserAgentString(context);
        }
    }

    /* access modifiers changed from: package-private */
    public void buildAndSetUserAgentString(Context context) {
        setUserAgentString("");
    }
}
