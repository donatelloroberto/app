package com.amazon.device.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewDatabase;

class WebViewFactory {
    private static WebViewFactory instance = new WebViewFactory();
    private final AndroidBuildInfo buildInfo;
    private final MobileAdsCookieManager cookieManager;
    private final DebugProperties debugProperties;
    private final MobileAdsInfoStore infoStore;
    private boolean isWebViewCheckedAndOk;
    private final MobileAdsLoggerFactory loggerFactory;
    private final WebViewConstructor webViewConstructor;
    private final WebViewDatabaseFactory webViewDatabaseFactory;

    protected WebViewFactory() {
        this(MobileAdsInfoStore.getInstance(), new MobileAdsLoggerFactory(), DebugProperties.getInstance(), new MobileAdsCookieManager(), new AndroidBuildInfo(), new WebViewDatabaseFactory(), new WebViewConstructor());
    }

    WebViewFactory(MobileAdsInfoStore infoStore2, MobileAdsLoggerFactory loggerFactory2, DebugProperties debugProperties2, MobileAdsCookieManager cookieManager2, AndroidBuildInfo buildInfo2, WebViewDatabaseFactory webViewDatabaseFactory2, WebViewConstructor webViewConstructor2) {
        this.isWebViewCheckedAndOk = false;
        this.infoStore = infoStore2;
        this.loggerFactory = loggerFactory2;
        this.debugProperties = debugProperties2;
        this.cookieManager = cookieManager2;
        this.buildInfo = buildInfo2;
        this.webViewDatabaseFactory = webViewDatabaseFactory2;
        this.webViewConstructor = webViewConstructor2;
        shouldDebugWebViews();
    }

    public static final WebViewFactory getInstance() {
        return instance;
    }

    public synchronized WebView createWebView(Context context) {
        WebView webView;
        webView = this.webViewConstructor.createWebView(context);
        this.infoStore.getDeviceInfo().setUserAgentString(webView.getSettings().getUserAgentString());
        webView.getSettings().setUserAgentString(this.infoStore.getDeviceInfo().getUserAgentString());
        this.cookieManager.createCookieSyncManager(context);
        updateAdIdCookie();
        return webView;
    }

    private void updateAdIdCookie() {
        if (this.cookieManager.isCookieSyncManagerCreated()) {
            String adId = this.infoStore.getRegistrationInfo().getAdId();
            if (adId == null) {
                adId = "";
            }
            this.cookieManager.setCookie("http://amazon-adsystem.com", "ad-id=" + adId + "; Domain=.amazon-adsystem.com");
        }
    }

    public boolean isWebViewOk(Context context) {
        if (AndroidTargetUtils.isAtOrBelowAndroidAPI(this.buildInfo, 8) && !this.isWebViewCheckedAndOk) {
            if (this.webViewDatabaseFactory.getWebViewDatabase(context) == null) {
                return false;
            }
            SQLiteDatabase cdb = null;
            try {
                SQLiteDatabase cdb2 = context.openOrCreateDatabase("webviewCache.db", 0, (SQLiteDatabase.CursorFactory) null);
                if (cdb2 != null) {
                    cdb2.close();
                }
                this.isWebViewCheckedAndOk = true;
            } catch (SQLiteException e) {
                boolean isDatabaseLocked = isDatabaseLocked(e);
                if (cdb == null) {
                    return isDatabaseLocked;
                }
                cdb.close();
                return isDatabaseLocked;
            } catch (Throwable th) {
                if (cdb != null) {
                    cdb.close();
                }
                throw th;
            }
        }
        return true;
    }

    private boolean isDatabaseLocked(SQLiteException e) {
        if (AndroidTargetUtils.isAtLeastAndroidAPI(this.buildInfo, 11)) {
            return AndroidTargetUtils.isInstanceOfSQLiteDatabaseLockedException(e);
        }
        return doesExceptionContainLockedDatabaseMessage(e);
    }

    private static boolean doesExceptionContainLockedDatabaseMessage(Exception e) {
        if (e == null || e.getMessage() == null) {
            return false;
        }
        return e.getMessage().contains("database is locked");
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public boolean setJavaScriptEnabledForWebView(boolean enable, WebView webView, String logtag) {
        try {
            webView.getSettings().setJavaScriptEnabled(enable);
            return true;
        } catch (NullPointerException e) {
            this.loggerFactory.createMobileAdsLogger(logtag).w("Could not set JavaScriptEnabled because a NullPointerException was encountered.");
            return false;
        }
    }

    private void shouldDebugWebViews() {
        if (this.debugProperties.getDebugPropertyAsBoolean(DebugProperties.DEBUG_WEBVIEWS, false).booleanValue()) {
            AndroidTargetUtils.enableWebViewDebugging(true);
        }
    }

    static class MobileAdsCookieManager {
        private boolean cookieSyncManagerCreated = false;

        MobileAdsCookieManager() {
        }

        public void createCookieSyncManager(Context context) {
            if (!this.cookieSyncManagerCreated) {
                CookieSyncManager.createInstance(context);
                this.cookieSyncManagerCreated = true;
            }
        }

        public void setCookie(String url, String cookie) {
            CookieManager.getInstance().setCookie(url, cookie);
        }

        public boolean isCookieSyncManagerCreated() {
            return this.cookieSyncManagerCreated;
        }

        public void startSync() {
            CookieSyncManager.getInstance().startSync();
        }

        public void stopSync() {
            CookieSyncManager.getInstance().stopSync();
        }
    }

    static class WebViewDatabaseFactory {
        WebViewDatabaseFactory() {
        }

        public WebViewDatabase getWebViewDatabase(Context context) {
            return WebViewDatabase.getInstance(context);
        }
    }

    static class WebViewConstructor {
        WebViewConstructor() {
        }

        public WebView createWebView(Context context) {
            return new WebView(context);
        }
    }
}
