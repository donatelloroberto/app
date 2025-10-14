package com.amazon.device.ads;

import com.amazon.device.ads.AdWebViewClient;
import com.amazon.device.ads.ThreadUtils;
import com.amazon.device.ads.WebRequest;

class AdUrlLoader {
    private static final String LOGTAG = AdUrlLoader.class.getSimpleName();
    /* access modifiers changed from: private */
    public final AdControlAccessor adControlAccessor;
    private final AdWebViewClient adWebViewClient;
    private final DeviceInfo deviceInfo;
    private final MobileAdsLogger logger;
    private final ThreadUtils.ThreadRunner threadRunner;
    private final WebRequest.WebRequestFactory webRequestFactory;
    private final WebUtils2 webUtils;

    public AdUrlLoader(ThreadUtils.ThreadRunner threadRunner2, AdWebViewClient adWebViewClient2, WebRequest.WebRequestFactory webRequestFactory2, AdControlAccessor adControlAccessor2, WebUtils2 webUtils2, MobileAdsLoggerFactory loggerFactory, DeviceInfo deviceInfo2) {
        this.threadRunner = threadRunner2;
        this.adWebViewClient = adWebViewClient2;
        this.webRequestFactory = webRequestFactory2;
        this.adControlAccessor = adControlAccessor2;
        this.webUtils = webUtils2;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        this.deviceInfo = deviceInfo2;
    }

    public void putUrlExecutorInAdWebViewClient(String scheme, AdWebViewClient.UrlExecutor executor) {
        this.adWebViewClient.putUrlExecutor(scheme, executor);
    }

    public void setAdWebViewClientListener(AdWebViewClient.AdWebViewClientListener adWebViewClientListener) {
        this.adWebViewClient.setListener(adWebViewClientListener);
    }

    public AdWebViewClient getAdWebViewClient() {
        return this.adWebViewClient;
    }

    public void loadUrl(final String url, final boolean shouldPreload, final PreloadCallback callback) {
        String scheme = this.webUtils.getScheme(url);
        if (scheme.equals("http") || scheme.equals("https")) {
            this.threadRunner.execute(new Runnable() {
                public void run() {
                    AdUrlLoader.this.loadUrlInThread(url, shouldPreload, callback);
                }
            }, ThreadUtils.ExecutionStyle.RUN_ASAP, ThreadUtils.ExecutionThread.BACKGROUND_THREAD);
        } else {
            openUrl(url);
        }
    }

    /* access modifiers changed from: private */
    public void loadUrlInThread(String url, boolean shouldPreload, PreloadCallback callback) {
        WebRequest webRequest = this.webRequestFactory.createWebRequest();
        webRequest.setExternalLogTag(LOGTAG);
        webRequest.enableLogUrl(true);
        webRequest.setUrlString(url);
        webRequest.putHeader("User-Agent", this.deviceInfo.getUserAgentString());
        WebRequest.WebResponse response = null;
        try {
            response = webRequest.makeCall();
        } catch (WebRequest.WebRequestException e) {
            this.logger.e("Could not load URL (%s) into AdContainer: %s", url, e.getMessage());
        }
        if (response != null) {
            final String body = response.getResponseReader().readAsString();
            if (body != null) {
                final String str = url;
                final boolean z = shouldPreload;
                final PreloadCallback preloadCallback = callback;
                this.threadRunner.execute(new Runnable() {
                    public void run() {
                        AdUrlLoader.this.adControlAccessor.loadHtml(str, body, z, preloadCallback);
                    }
                }, ThreadUtils.ExecutionStyle.RUN_ASAP, ThreadUtils.ExecutionThread.MAIN_THREAD);
                return;
            }
            this.logger.e("Could not load URL (%s) into AdContainer.", url);
        }
    }

    public void openUrl(String url) {
        this.adWebViewClient.openUrl(url);
    }
}
