package com.amazon.mas.cpt.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import com.amazon.cptplugins.AndroidResources;
import com.amazon.cptplugins.CrossPlatformTool;
import com.amazon.cptplugins.concurrent.SdkCallbackListener;
import com.amazon.cptplugins.concurrent.SdkEvent;
import com.amazon.cptplugins.concurrent.SdkEventListener;
import com.amazon.cptplugins.exceptions.jsonutils.AmazonError;
import com.amazon.cptplugins.json.JsonSerializer;
import com.amazon.cptplugins.json.JsonSerializerImpl;
import com.amazon.cptplugins.validation.Assert;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AmazonMobileAdsJavaControllerImpl implements AmazonMobileAdsJavaController {
    private static final int MAX_BLOCKED_CONCURRENT_ASYNC_CALLS = 10;
    private static final String TAG = "AmazonMobileAdsJavaControllerImpl";
    private static volatile AndroidResources androidResources = null;
    private final AmazonMobileAds amazonMobileAds = new AmazonMobileAdsImpl();
    private volatile Context context = null;
    private final ExecutorService executor;
    private final JsonSerializer json;
    private volatile SdkCallbackListener sdkCallbackListener = null;
    private volatile SdkEventListener sdkEventListener = null;

    public static AmazonMobileAdsJavaControllerImpl newInstance() {
        AmazonMobileAdsJavaControllerImpl instance = new AmazonMobileAdsJavaControllerImpl(new JsonSerializerImpl(), Executors.newFixedThreadPool(10));
        instance.amazonMobileAds.setAmazonMobileAdsJavaController(instance);
        return instance;
    }

    public static AmazonMobileAdsJavaControllerImpl newInstance(Context context2) {
        AmazonMobileAdsJavaControllerImpl instance = new AmazonMobileAdsJavaControllerImpl(new JsonSerializerImpl(), Executors.newFixedThreadPool(10));
        instance.setContext(context2);
        return instance;
    }

    private AmazonMobileAdsJavaControllerImpl(JsonSerializer json2, ExecutorService executor2) {
        this.json = json2;
        this.executor = executor2;
    }

    public void registerToJavaService() {
        this.amazonMobileAds.setAmazonMobileAdsJavaController(this);
    }

    public void setSdkEventListener(SdkEventListener listener) {
        this.sdkEventListener = listener;
    }

    public void setSdkCallbackListener(SdkCallbackListener listener) {
        this.sdkCallbackListener = listener;
    }

    public String setApplicationKey(String jsonMsg) {
        try {
            Type typeOfT = new TypeToken<ApplicationKey>() {
            }.getType();
            this.amazonMobileAds.setApplicationKey((ApplicationKey) this.json.fromJson(jsonMsg, typeOfT));
            return "{}";
        } catch (Exception e) {
            return this.json.toJson(new AmazonError(e));
        }
    }

    public String registerApplication(String jsonMsg) {
        try {
            this.amazonMobileAds.registerApplication();
            return "{}";
        } catch (Exception e) {
            return this.json.toJson(new AmazonError(e));
        }
    }

    public String enableLogging(String jsonMsg) {
        try {
            Type typeOfT = new TypeToken<ShouldEnable>() {
            }.getType();
            this.amazonMobileAds.enableLogging((ShouldEnable) this.json.fromJson(jsonMsg, typeOfT));
            return "{}";
        } catch (Exception e) {
            return this.json.toJson(new AmazonError(e));
        }
    }

    public String enableTesting(String jsonMsg) {
        try {
            Type typeOfT = new TypeToken<ShouldEnable>() {
            }.getType();
            this.amazonMobileAds.enableTesting((ShouldEnable) this.json.fromJson(jsonMsg, typeOfT));
            return "{}";
        } catch (Exception e) {
            return this.json.toJson(new AmazonError(e));
        }
    }

    public String enableGeoLocation(String jsonMsg) {
        try {
            Type typeOfT = new TypeToken<ShouldEnable>() {
            }.getType();
            this.amazonMobileAds.enableGeoLocation((ShouldEnable) this.json.fromJson(jsonMsg, typeOfT));
            return "{}";
        } catch (Exception e) {
            return this.json.toJson(new AmazonError(e));
        }
    }

    public String createFloatingBannerAd(String jsonMsg) {
        try {
            Type typeOfT = new TypeToken<Placement>() {
            }.getType();
            return this.json.toJson(this.amazonMobileAds.createFloatingBannerAd((Placement) this.json.fromJson(jsonMsg, typeOfT)));
        } catch (Exception e) {
            return this.json.toJson(new AmazonError(e));
        }
    }

    public String createInterstitialAd(String jsonMsg) {
        try {
            return this.json.toJson(this.amazonMobileAds.createInterstitialAd());
        } catch (Exception e) {
            return this.json.toJson(new AmazonError(e));
        }
    }

    public String loadAndShowFloatingBannerAd(String jsonMsg) {
        try {
            Type typeOfT = new TypeToken<Ad>() {
            }.getType();
            return this.json.toJson(this.amazonMobileAds.loadAndShowFloatingBannerAd((Ad) this.json.fromJson(jsonMsg, typeOfT)));
        } catch (Exception e) {
            return this.json.toJson(new AmazonError(e));
        }
    }

    public String loadInterstitialAd(String jsonMsg) {
        try {
            return this.json.toJson(this.amazonMobileAds.loadInterstitialAd());
        } catch (Exception e) {
            return this.json.toJson(new AmazonError(e));
        }
    }

    public String showInterstitialAd(String jsonMsg) {
        try {
            return this.json.toJson(this.amazonMobileAds.showInterstitialAd());
        } catch (Exception e) {
            return this.json.toJson(new AmazonError(e));
        }
    }

    public String closeFloatingBannerAd(String jsonMsg) {
        try {
            Type typeOfT = new TypeToken<Ad>() {
            }.getType();
            this.amazonMobileAds.closeFloatingBannerAd((Ad) this.json.fromJson(jsonMsg, typeOfT));
            return "{}";
        } catch (Exception e) {
            return this.json.toJson(new AmazonError(e));
        }
    }

    public String isInterstitialAdReady(String jsonMsg) {
        try {
            return this.json.toJson(this.amazonMobileAds.isInterstitialAdReady());
        } catch (Exception e) {
            return this.json.toJson(new AmazonError(e));
        }
    }

    public String areAdsEqual(String jsonMsg) {
        try {
            Type typeOfT = new TypeToken<AdPair>() {
            }.getType();
            return this.json.toJson(this.amazonMobileAds.areAdsEqual((AdPair) this.json.fromJson(jsonMsg, typeOfT)));
        } catch (Exception e) {
            return this.json.toJson(new AmazonError(e));
        }
    }

    public void fireAdCollapsed(Ad adObject) {
        String sdkEventJson = this.json.toJson(new SdkEvent<>("adCollapsed", adObject));
        if (this.sdkEventListener != null) {
            this.sdkEventListener.fireSdkEvent(sdkEventJson);
        }
    }

    public void fireAdDismissed(Ad adObject) {
        String sdkEventJson = this.json.toJson(new SdkEvent<>("adDismissed", adObject));
        if (this.sdkEventListener != null) {
            this.sdkEventListener.fireSdkEvent(sdkEventJson);
        }
    }

    public void fireAdExpanded(Ad adObject) {
        String sdkEventJson = this.json.toJson(new SdkEvent<>("adExpanded", adObject));
        if (this.sdkEventListener != null) {
            this.sdkEventListener.fireSdkEvent(sdkEventJson);
        }
    }

    public void fireAdFailedToLoad(Ad adObject) {
        String sdkEventJson = this.json.toJson(new SdkEvent<>("adFailedToLoad", adObject));
        if (this.sdkEventListener != null) {
            this.sdkEventListener.fireSdkEvent(sdkEventJson);
        }
    }

    public void fireAdLoaded(Ad adObject) {
        String sdkEventJson = this.json.toJson(new SdkEvent<>("adLoaded", adObject));
        if (this.sdkEventListener != null) {
            this.sdkEventListener.fireSdkEvent(sdkEventJson);
        }
    }

    public void fireAdResized(AdPosition adPosition) {
        String sdkEventJson = this.json.toJson(new SdkEvent<>("adResized", adPosition));
        if (this.sdkEventListener != null) {
            this.sdkEventListener.fireSdkEvent(sdkEventJson);
        }
    }

    public Activity getCurrentAndroidActivity() {
        return delegateGetCurrentAndroidActivity();
    }

    public void setAndroidResources(AndroidResources androidResources2) {
        androidResources = androidResources2;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context androidContext) {
        this.context = androidContext;
    }

    public void handleSdkCallback(String responseJSON) {
        if (this.sdkCallbackListener != null) {
            this.sdkCallbackListener.handleSdkCallback(responseJSON);
        }
    }

    public boolean runningOnUiThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private static Activity delegateGetCurrentAndroidActivity() {
        Assert.notNull(androidResources, "androidResoures");
        return androidResources.getCurrentAndroidActivity();
    }

    public CrossPlatformTool getCrossPlatformTool() {
        return androidResources.getCrossPlatformTool();
    }
}
