package com.amazon.device.ads;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.ViewGroup;
import com.amazon.device.ads.AdActivity;
import com.amazon.device.ads.SDKEvent;

@SuppressLint({"NewApi"})
class InterstitialAdActivityAdapter implements AdActivity.IAdActivityAdapter {
    private static final String LOGTAG = InterstitialAdActivityAdapter.class.getSimpleName();
    /* access modifiers changed from: private */
    public Activity activity = null;
    /* access modifiers changed from: private */
    public AdController adController;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    InterstitialAdActivityAdapter() {
    }

    public void setActivity(Activity activity2) {
        this.activity = activity2;
    }

    public void preOnCreate() {
        this.activity.requestWindowFeature(1);
        this.activity.getWindow().setFlags(1024, 1024);
        AndroidTargetUtils.hideActionAndStatusBars(this.activity);
    }

    public void onCreate() {
        AndroidTargetUtils.enableHardwareAcceleration(this.activity.getWindow());
        this.adController = getAdController();
        if (this.adController == null) {
            this.logger.e("Failed to show interstitial ad due to an error in the Activity.");
            InterstitialAd.resetIsAdShowing();
            this.activity.finish();
            return;
        }
        this.adController.setAdActivity(this.activity);
        this.adController.addSDKEventListener(new InterstitialAdSDKEventListener());
        ViewGroup parent = (ViewGroup) this.adController.getView().getParent();
        if (parent != null) {
            parent.removeView(this.adController.getView());
        }
        this.activity.setContentView(this.adController.getView());
        this.adController.adShown();
    }

    /* access modifiers changed from: package-private */
    public AdController getAdController() {
        return AdControllerFactory.getCachedAdController();
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStop() {
        if (this.activity.isFinishing() && this.adController != null) {
            this.adController.closeAd();
        }
    }

    /* access modifiers changed from: package-private */
    public Activity getActivity() {
        return this.activity;
    }

    class InterstitialAdSDKEventListener implements SDKEventListener {
        InterstitialAdSDKEventListener() {
        }

        public void onSDKEvent(SDKEvent sdkEvent, AdControlAccessor controller) {
            if (sdkEvent.getEventType().equals(SDKEvent.SDKEventType.CLOSED) && !InterstitialAdActivityAdapter.this.activity.isFinishing()) {
                AdController unused = InterstitialAdActivityAdapter.this.adController = null;
                InterstitialAdActivityAdapter.this.activity.finish();
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public boolean onBackPressed() {
        if (this.adController != null) {
            return this.adController.onBackButtonPress();
        }
        return false;
    }
}
