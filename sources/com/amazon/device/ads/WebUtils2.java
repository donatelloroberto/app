package com.amazon.device.ads;

import android.content.Context;
import java.net.URI;
import java.net.URISyntaxException;

/* compiled from: WebUtils */
class WebUtils2 {
    private final WebUtilsStatic webUtilsAdapter = new WebUtilsStatic();

    WebUtils2() {
    }

    /* access modifiers changed from: package-private */
    public boolean isUrlValid(String url) {
        try {
            new URI(url);
            return true;
        } catch (NullPointerException | URISyntaxException e) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean launchActivityForIntentLink(String url, Context context) {
        return this.webUtilsAdapter.launchActivityForIntentLink(url, context);
    }

    /* access modifiers changed from: package-private */
    public String getURLEncodedString(String s) {
        return this.webUtilsAdapter.getURLEncodedString(s);
    }

    /* access modifiers changed from: package-private */
    public String getURLDecodedString(String s) {
        return this.webUtilsAdapter.getURLDecodedString(s);
    }

    /* access modifiers changed from: package-private */
    public String getScheme(String url) {
        return this.webUtilsAdapter.getScheme(url);
    }

    /* access modifiers changed from: package-private */
    public String encloseHtml(String html, boolean isHTML5) {
        return this.webUtilsAdapter.encloseHtml(html, isHTML5);
    }

    /* access modifiers changed from: package-private */
    public void executeWebRequestInThread(String url, boolean disconnectEnabled) {
        this.webUtilsAdapter.executeWebRequestInThread(url, disconnectEnabled);
    }

    /* compiled from: WebUtils */
    private static class WebUtilsStatic {
        private WebUtilsStatic() {
        }

        /* access modifiers changed from: package-private */
        public boolean launchActivityForIntentLink(String url, Context context) {
            return WebUtils.launchActivityForIntentLink(url, context);
        }

        /* access modifiers changed from: package-private */
        public String getURLEncodedString(String s) {
            return WebUtils.getURLEncodedString(s);
        }

        /* access modifiers changed from: package-private */
        public String getURLDecodedString(String s) {
            return WebUtils.getURLDecodedString(s);
        }

        /* access modifiers changed from: package-private */
        public String getScheme(String url) {
            return WebUtils.getScheme(url);
        }

        /* access modifiers changed from: package-private */
        public String encloseHtml(String html, boolean isHTML5) {
            return WebUtils.encloseHtml(html, isHTML5);
        }

        /* access modifiers changed from: package-private */
        public void executeWebRequestInThread(String url, boolean disconnectEnabled) {
            WebUtils.executeWebRequestInThread(url, disconnectEnabled);
        }
    }
}
