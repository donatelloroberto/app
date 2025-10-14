package com.amazon.device.ads;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.amazon.device.ads.AdActivity;
import com.amazon.device.ads.LayoutFactory;
import com.amazon.device.ads.ThreadUtils;
import com.amazon.device.ads.WebViewFactory;
import java.util.concurrent.atomic.AtomicBoolean;

class InAppBrowser implements AdActivity.IAdActivityAdapter {
    protected static final int BUTTON_SIZE_DP = 50;
    private static final String CONTENT_DESCRIPTION_BACK_BUTTON = "inAppBrowserBackButton";
    private static final String CONTENT_DESCRIPTION_BUTTON_LAYOUT = "inAppBrowserButtonLayout";
    private static final String CONTENT_DESCRIPTION_CLOSE_BUTTON = "inAppBrowserCloseButton";
    private static final String CONTENT_DESCRIPTION_FORWARD_BUTTON = "inAppBrowserForwardButton";
    private static final String CONTENT_DESCRIPTION_HORZ_RULE = "inAppBrowserHorizontalRule";
    private static final String CONTENT_DESCRIPTION_MAIN_LAYOUT = "inAppBrowserMainLayout";
    private static final String CONTENT_DESCRIPTION_OPEN_EXT_BRWSR_BUTTON = "inAppBrowserOpenExternalBrowserButton";
    private static final String CONTENT_DESCRIPTION_REFRESH_BUTTON = "inAppBrowserRefreshButton";
    private static final String CONTENT_DESCRIPTION_RELATIVE_LAYOUT = "inAppBrowserRelativeLayout";
    private static final String CONTENT_DESCRIPTION_WEB_VIEW = "inAppBrowserWebView";
    protected static final int HORIZONTAL_RULE_SIZE_DP = 3;
    protected static final String LOGTAG = InAppBrowser.class.getSimpleName();
    protected static final String SHOW_OPEN_EXTERNAL_BROWSER_BTN = "extra_open_btn";
    protected static final String URL_EXTRA = "extra_url";
    /* access modifiers changed from: private */
    public Activity activity;
    /* access modifiers changed from: private */
    public final Assets assets;
    /* access modifiers changed from: private */
    public ImageButton browserBackButton;
    /* access modifiers changed from: private */
    public ImageButton browserForwardButton;
    /* access modifiers changed from: private */
    public final AtomicBoolean buttonsCreated;
    /* access modifiers changed from: private */
    public ImageButton closeButton;
    private final WebViewFactory.MobileAdsCookieManager cookieManager;
    private final MobileAdsInfoStore infoStore;
    private final LayoutFactory layoutFactory;
    /* access modifiers changed from: private */
    public final MobileAdsLogger logger;
    /* access modifiers changed from: private */
    public ImageButton openExternalBrowserButton;
    /* access modifiers changed from: private */
    public ImageButton refreshButton;
    private final Settings settings;
    /* access modifiers changed from: private */
    public boolean showOpenExternalBrowserButton;
    private final ThreadUtils.ThreadRunner threadRunner;
    /* access modifiers changed from: private */
    public final WebUtils2 webUtils;
    /* access modifiers changed from: private */
    public WebView webView;
    private final WebViewFactory webViewFactory;

    InAppBrowser() {
        this(new WebUtils2(), WebViewFactory.getInstance(), new MobileAdsLoggerFactory(), MobileAdsInfoStore.getInstance(), Settings.getInstance(), Assets.getInstance(), new LayoutFactory(), new WebViewFactory.MobileAdsCookieManager(), ThreadUtils.getThreadRunner());
    }

    InAppBrowser(WebUtils2 webUtils2, WebViewFactory webViewFactory2, MobileAdsLoggerFactory loggerFactory, MobileAdsInfoStore infoStore2, Settings settings2, Assets assets2, LayoutFactory layoutFactory2, WebViewFactory.MobileAdsCookieManager cookieManager2, ThreadUtils.ThreadRunner threadRunner2) {
        this.buttonsCreated = new AtomicBoolean(false);
        this.webUtils = webUtils2;
        this.webViewFactory = webViewFactory2;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        this.infoStore = infoStore2;
        this.settings = settings2;
        this.assets = assets2;
        this.layoutFactory = layoutFactory2;
        this.cookieManager = cookieManager2;
        this.threadRunner = threadRunner2;
    }

    public void setActivity(Activity activity2) {
        this.activity = activity2;
    }

    public void onCreate() {
        this.activity.getWindow().requestFeature(2);
        this.activity.getWindow().setFeatureInt(2, -1);
        Intent intent = this.activity.getIntent();
        this.showOpenExternalBrowserButton = intent.getBooleanExtra(SHOW_OPEN_EXTERNAL_BROWSER_BTN, false);
        initialize(intent);
        initializeWebView(intent);
        enableCookies();
    }

    @SuppressLint({"InlinedApi"})
    private void initialize(Intent intent) {
        DisplayMetrics metrics = new DisplayMetrics();
        getMetrics(metrics);
        float mDensity = metrics.density;
        int buttonHeight = (int) ((50.0f * mDensity) + 0.5f);
        int ruleSize = (int) ((3.0f * mDensity) + 0.5f);
        int buttonWidth = Math.min(metrics.widthPixels / (this.showOpenExternalBrowserButton ? 5 : 4), buttonHeight * 2);
        ViewGroup layout = this.layoutFactory.createLayout(this.activity, LayoutFactory.LayoutType.RELATIVE_LAYOUT, CONTENT_DESCRIPTION_BUTTON_LAYOUT);
        layout.setId(10280);
        RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(-1, buttonHeight + ruleSize);
        rp.addRule(12);
        layout.setLayoutParams(rp);
        layout.setBackgroundColor(-986896);
        this.threadRunner.executeAsyncTask(new LoadButtonsTask(intent, layout, buttonWidth, buttonHeight), new Void[0]);
        View rule = new View(this.activity);
        rule.setContentDescription(CONTENT_DESCRIPTION_HORZ_RULE);
        rule.setBackgroundColor(-3355444);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, ruleSize);
        params.addRule(10);
        rule.setLayoutParams(params);
        layout.addView(rule);
        this.webView = this.webViewFactory.createWebView(this.activity);
        this.webView.getSettings().setUserAgentString(this.infoStore.getDeviceInfo().getUserAgentString() + "-inAppBrowser");
        this.webView.setContentDescription(CONTENT_DESCRIPTION_WEB_VIEW);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(2, layout.getId());
        this.webView.setLayoutParams(layoutParams);
        ViewGroup rl = this.layoutFactory.createLayout(this.activity, LayoutFactory.LayoutType.RELATIVE_LAYOUT, CONTENT_DESCRIPTION_RELATIVE_LAYOUT);
        rl.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        rl.addView(this.webView);
        rl.addView(layout);
        LinearLayout mainll = (LinearLayout) this.layoutFactory.createLayout(this.activity, LayoutFactory.LayoutType.LINEAR_LAYOUT, CONTENT_DESCRIPTION_MAIN_LAYOUT);
        mainll.setOrientation(1);
        mainll.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        mainll.addView(rl);
        this.activity.setContentView(mainll);
    }

    /* access modifiers changed from: private */
    public ImageButton createButton(String src, int verb, int anchor, int buttonWidth, int buttonHeight) {
        ImageButton button = new ImageButton(this.activity);
        button.setImageBitmap(BitmapFactory.decodeFile(src));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
        params.addRule(verb, anchor);
        params.addRule(12);
        button.setLayoutParams(params);
        button.setBackgroundColor(0);
        button.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return button;
    }

    private void initializeWebView(Intent intent) {
        this.webViewFactory.setJavaScriptEnabledForWebView(true, this.webView, LOGTAG);
        this.webView.loadUrl(intent.getStringExtra(URL_EXTRA));
        this.webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                InAppBrowser.this.logger.w("InApp Browser error: %s", description);
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (StringUtils.isNullOrWhiteSpace(url)) {
                    return false;
                }
                String scheme = InAppBrowser.this.webUtils.getScheme(url);
                if (scheme.equals("http") || scheme.equals("https")) {
                    return false;
                }
                return InAppBrowser.this.webUtils.launchActivityForIntentLink(url, InAppBrowser.this.activity);
            }
        });
        this.webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                Activity activity = (Activity) view.getContext();
                activity.setTitle("Loading...");
                activity.setProgress(progress * 100);
                if (progress == 100) {
                    activity.setTitle(view.getUrl());
                }
                InAppBrowser.this.updateNavigationButtons(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public void initializeButtons(Intent intent) {
        this.browserBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (InAppBrowser.this.webView.canGoBack()) {
                    InAppBrowser.this.webView.goBack();
                }
            }
        });
        this.browserForwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (InAppBrowser.this.webView.canGoForward()) {
                    InAppBrowser.this.webView.goForward();
                }
            }
        });
        this.refreshButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InAppBrowser.this.webView.reload();
            }
        });
        this.closeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InAppBrowser.this.activity.finish();
            }
        });
        if (this.showOpenExternalBrowserButton) {
            final String originalUrl = intent.getStringExtra(URL_EXTRA);
            this.openExternalBrowserButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String currentUrl = InAppBrowser.this.webView.getUrl();
                    if (currentUrl == null) {
                        InAppBrowser.this.logger.w("The current URL is null. Reverting to the original URL for external browser.");
                        currentUrl = originalUrl;
                    }
                    InAppBrowser.this.webUtils.launchActivityForIntentLink(currentUrl, InAppBrowser.this.webView.getContext());
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void updateNavigationButtons(WebView view) {
        if (this.browserBackButton != null && this.browserForwardButton != null) {
            if (view.canGoBack()) {
                AndroidTargetUtils.setImageButtonAlpha(this.browserBackButton, 255);
            } else {
                AndroidTargetUtils.setImageButtonAlpha(this.browserBackButton, 102);
            }
            if (view.canGoForward()) {
                AndroidTargetUtils.setImageButtonAlpha(this.browserForwardButton, 255);
            } else {
                AndroidTargetUtils.setImageButtonAlpha(this.browserForwardButton, 102);
            }
        }
    }

    private void enableCookies() {
        this.cookieManager.createCookieSyncManager(this.activity);
        this.cookieManager.startSync();
    }

    /* access modifiers changed from: protected */
    public boolean getShouldPauseWebViewTimers() {
        return this.settings.getBoolean(Settings.SETTING_ENABLE_WEBVIEW_PAUSE_LOGIC, false);
    }

    /* access modifiers changed from: protected */
    public boolean canPauseWebViewTimers() {
        return this.webView != null && getShouldPauseWebViewTimers();
    }

    public void onPause() {
        this.logger.d("onPause");
        pauseWebView();
        if (canPauseWebViewTimers()) {
            this.webView.pauseTimers();
        }
        this.cookieManager.stopSync();
    }

    /* access modifiers changed from: package-private */
    public void pauseWebView() {
        this.webView.onPause();
    }

    /* access modifiers changed from: protected */
    public boolean canResumeWebViewTimers() {
        return this.webView != null && getShouldPauseWebViewTimers();
    }

    public void onResume() {
        this.logger.d("onResume");
        resumeWebView();
        if (canResumeWebViewTimers()) {
            this.webView.resumeTimers();
        }
        this.cookieManager.startSync();
    }

    /* access modifiers changed from: package-private */
    public void resumeWebView() {
        this.webView.onResume();
    }

    public void onStop() {
    }

    class LoadButtonsTask extends ThreadUtils.MobileAdsAsyncTask<Void, Void, Void> {
        private final int buttonHeight;
        private final int buttonWidth;
        private final Intent intent;
        private final ViewGroup layout;

        public LoadButtonsTask(Intent intent2, ViewGroup layout2, int buttonWidth2, int buttonHeight2) {
            this.intent = intent2;
            this.layout = layout2;
            this.buttonWidth = buttonWidth2;
            this.buttonHeight = buttonHeight2;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... params) {
            ImageButton unused = InAppBrowser.this.browserBackButton = InAppBrowser.this.createButton(InAppBrowser.this.assets.getFilePath(Assets.LEFT_ARROW), 9, -1, this.buttonWidth, this.buttonHeight);
            InAppBrowser.this.browserBackButton.setContentDescription(InAppBrowser.CONTENT_DESCRIPTION_BACK_BUTTON);
            InAppBrowser.this.browserBackButton.setId(10537);
            ImageButton unused2 = InAppBrowser.this.browserForwardButton = InAppBrowser.this.createButton(InAppBrowser.this.assets.getFilePath(Assets.RIGHT_ARROW), 1, InAppBrowser.this.browserBackButton.getId(), this.buttonWidth, this.buttonHeight);
            InAppBrowser.this.browserForwardButton.setContentDescription(InAppBrowser.CONTENT_DESCRIPTION_FORWARD_BUTTON);
            InAppBrowser.this.browserForwardButton.setId(10794);
            ImageButton unused3 = InAppBrowser.this.closeButton = InAppBrowser.this.createButton(InAppBrowser.this.assets.getFilePath(Assets.CLOSE), 11, -1, this.buttonWidth, this.buttonHeight);
            InAppBrowser.this.closeButton.setContentDescription(InAppBrowser.CONTENT_DESCRIPTION_CLOSE_BUTTON);
            if (InAppBrowser.this.showOpenExternalBrowserButton) {
                ImageButton unused4 = InAppBrowser.this.openExternalBrowserButton = InAppBrowser.this.createButton(InAppBrowser.this.assets.getFilePath(Assets.OPEN_EXTERNAL_BROWSER), 1, InAppBrowser.this.browserForwardButton.getId(), this.buttonWidth, this.buttonHeight);
                InAppBrowser.this.openExternalBrowserButton.setContentDescription(InAppBrowser.CONTENT_DESCRIPTION_OPEN_EXT_BRWSR_BUTTON);
                InAppBrowser.this.openExternalBrowserButton.setId(10795);
                ImageButton unused5 = InAppBrowser.this.refreshButton = InAppBrowser.this.createButton(InAppBrowser.this.assets.getFilePath(Assets.REFRESH), 1, InAppBrowser.this.openExternalBrowserButton.getId(), this.buttonWidth, this.buttonHeight);
            } else {
                ImageButton unused6 = InAppBrowser.this.refreshButton = InAppBrowser.this.createButton(InAppBrowser.this.assets.getFilePath(Assets.REFRESH), 1, InAppBrowser.this.browserForwardButton.getId(), this.buttonWidth, this.buttonHeight);
            }
            InAppBrowser.this.refreshButton.setContentDescription(InAppBrowser.CONTENT_DESCRIPTION_REFRESH_BUTTON);
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void param) {
            this.layout.addView(InAppBrowser.this.browserBackButton);
            this.layout.addView(InAppBrowser.this.browserForwardButton);
            this.layout.addView(InAppBrowser.this.refreshButton);
            this.layout.addView(InAppBrowser.this.closeButton);
            if (InAppBrowser.this.showOpenExternalBrowserButton) {
                this.layout.addView(InAppBrowser.this.openExternalBrowserButton);
            }
            InAppBrowser.this.initializeButtons(this.intent);
            InAppBrowser.this.buttonsCreated.set(true);
        }
    }

    public void preOnCreate() {
    }

    public static class InAppBrowserBuilder {
        private static final String LOGTAG = InAppBrowserBuilder.class.getSimpleName();
        private final Assets assets;
        private Context context;
        private final MobileAdsLogger logger;
        private boolean showOpenExternalBrowserButton;
        private String url;

        public InAppBrowserBuilder() {
            this(Assets.getInstance(), new MobileAdsLoggerFactory());
        }

        InAppBrowserBuilder(Assets assets2, MobileAdsLoggerFactory loggerFactory) {
            this.assets = assets2;
            this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        }

        public InAppBrowserBuilder withContext(Context context2) {
            this.context = context2;
            return this;
        }

        public InAppBrowserBuilder withUrl(String url2) {
            this.url = url2;
            return this;
        }

        public InAppBrowserBuilder withExternalBrowserButton() {
            this.showOpenExternalBrowserButton = true;
            return this;
        }

        public void show() {
            if (this.context == null) {
                throw new IllegalArgumentException("Context must not be null");
            } else if (StringUtils.isNullOrWhiteSpace(this.url)) {
                throw new IllegalArgumentException("Url must not be null or white space");
            } else if (!this.assets.ensureAssetsCreated()) {
                this.logger.e("Could not load application assets, failed to open URI: %s", this.url);
            } else {
                Intent intent = new Intent(this.context, AdActivity.class);
                intent.putExtra("adapter", InAppBrowser.class.getName());
                intent.putExtra(InAppBrowser.URL_EXTRA, this.url);
                intent.putExtra(InAppBrowser.SHOW_OPEN_EXTERNAL_BROWSER_BTN, this.showOpenExternalBrowserButton);
                intent.addFlags(268435456);
                this.context.startActivity(intent);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void getMetrics(DisplayMetrics metrics) {
        ((WindowManager) this.activity.getSystemService("window")).getDefaultDisplay().getMetrics(metrics);
    }

    public void onConfigurationChanged(Configuration configuration) {
        DisplayMetrics metrics = new DisplayMetrics();
        getMetrics(metrics);
        int buttonHeight = (int) ((50.0f * metrics.density) + 0.5f);
        int buttonWidth = Math.min(metrics.widthPixels / (this.showOpenExternalBrowserButton ? 5 : 4), buttonHeight * 2);
        this.logger.d("Width: " + metrics.widthPixels + " ButtonWidth: " + buttonWidth);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
        if (this.browserBackButton != null) {
            params.addRule(9);
            params.addRule(12);
            this.browserBackButton.setLayoutParams(params);
        }
        if (this.browserForwardButton != null) {
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
            params2.addRule(1, this.browserBackButton.getId());
            params2.addRule(12);
            this.browserForwardButton.setLayoutParams(params2);
        }
        if (this.closeButton != null) {
            RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
            params3.addRule(11);
            params3.addRule(12);
            this.closeButton.setLayoutParams(params3);
        }
        if (this.openExternalBrowserButton != null) {
            RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
            params4.addRule(1, this.browserForwardButton.getId());
            params4.addRule(12);
            this.openExternalBrowserButton.setLayoutParams(params4);
            if (this.refreshButton != null) {
                RelativeLayout.LayoutParams params5 = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
                params5.addRule(1, this.openExternalBrowserButton.getId());
                params5.addRule(12);
                this.refreshButton.setLayoutParams(params5);
            }
        } else if (this.refreshButton != null) {
            RelativeLayout.LayoutParams params6 = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
            params6.addRule(1, this.browserForwardButton.getId());
            params6.addRule(12);
            this.refreshButton.setLayoutParams(params6);
        }
    }

    public boolean onBackPressed() {
        return false;
    }
}
