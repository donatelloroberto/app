package com.amazon.mas.cpt.ads;

import android.app.Activity;
import android.content.Context;
import com.amazon.cptplugins.AndroidResources;
import com.amazon.cptplugins.CrossPlatformTool;
import com.amazon.cptplugins.concurrent.CallbackHandler;
import com.amazon.cptplugins.concurrent.SdkCallbackListener;
import com.amazon.cptplugins.concurrent.SdkEventListener;

public interface AmazonMobileAdsJavaController extends CallbackHandler {
    String areAdsEqual(String str);

    String closeFloatingBannerAd(String str);

    String createFloatingBannerAd(String str);

    String createInterstitialAd(String str);

    String enableGeoLocation(String str);

    String enableLogging(String str);

    String enableTesting(String str);

    void fireAdCollapsed(Ad ad);

    void fireAdDismissed(Ad ad);

    void fireAdExpanded(Ad ad);

    void fireAdFailedToLoad(Ad ad);

    void fireAdLoaded(Ad ad);

    void fireAdResized(AdPosition adPosition);

    Context getContext();

    CrossPlatformTool getCrossPlatformTool();

    Activity getCurrentAndroidActivity();

    String isInterstitialAdReady(String str);

    String loadAndShowFloatingBannerAd(String str);

    String loadInterstitialAd(String str);

    String registerApplication(String str);

    void registerToJavaService();

    boolean runningOnUiThread();

    void setAndroidResources(AndroidResources androidResources);

    String setApplicationKey(String str);

    void setContext(Context context);

    void setSdkCallbackListener(SdkCallbackListener sdkCallbackListener);

    void setSdkEventListener(SdkEventListener sdkEventListener);

    String showInterstitialAd(String str);
}
