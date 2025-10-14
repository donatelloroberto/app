package com.amazon.device.ads;

import android.content.Context;
import com.amazon.device.ads.ThreadUtils;

class AdUserAgentManager extends BasicUserAgentManager {
    private final ThreadUtils.ThreadRunner threadRunner;

    public AdUserAgentManager() {
        this(new ThreadUtils.ThreadRunner());
    }

    AdUserAgentManager(ThreadUtils.ThreadRunner threadRunner2) {
        this.threadRunner = threadRunner2;
    }

    /* access modifiers changed from: package-private */
    public void buildAndSetUserAgentString(final Context context) {
        this.threadRunner.execute(new Runnable() {
            public void run() {
                AdUserAgentManager.this.setUserAgentString(WebViewFactory.getInstance().createWebView(context).getSettings().getUserAgentString());
            }
        }, ThreadUtils.ExecutionStyle.RUN_ASAP, ThreadUtils.ExecutionThread.MAIN_THREAD);
    }
}
