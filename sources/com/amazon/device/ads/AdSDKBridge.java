package com.amazon.device.ads;

import com.amazon.device.ads.JavascriptInteractor;

interface AdSDKBridge {
    String getJavascript();

    JavascriptInteractor.Executor getJavascriptInteractorExecutor();

    String getName();

    SDKEventListener getSDKEventListener();

    boolean hasNativeExecution();
}
