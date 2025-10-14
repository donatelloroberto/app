package com.amazon.device.ads;

import android.app.Activity;

class MRAIDAdSDKEventListener implements SDKEventListener {
    private static final String LOGTAG = MRAIDAdSDKEventListener.class.getSimpleName();
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    private MRAIDAdSDKBridge mraidAdSDKBridge;

    MRAIDAdSDKEventListener(MRAIDAdSDKBridge adBridge) {
        this.mraidAdSDKBridge = adBridge;
    }

    public void onSDKEvent(SDKEvent sdkEvent, AdControlAccessor adControlAccessor) {
        this.logger.d(sdkEvent.getEventType().toString());
        switch (sdkEvent.getEventType()) {
            case RENDERED:
                handleReadyEvent(adControlAccessor);
                return;
            case VISIBLE:
                handleVisibleEvent(adControlAccessor);
                return;
            case CLOSED:
                handleClosedEvent(adControlAccessor);
                return;
            case RESIZED:
                this.mraidAdSDKBridge.reportSizeChangeEvent();
                return;
            case HIDDEN:
            case DESTROYED:
                adControlAccessor.injectJavascript("mraidBridge.stateChange('hidden');");
                adControlAccessor.injectJavascript("mraidBridge.viewableChange('false');");
                return;
            case BRIDGE_ADDED:
                handleBridgeAddedEvent(sdkEvent, adControlAccessor);
                return;
            default:
                return;
        }
    }

    private void handleBridgeAddedEvent(SDKEvent sdkEvent, AdControlAccessor adControlAccessor) {
        String bridgeName = sdkEvent.getParameter(SDKEvent.BRIDGE_NAME);
        if (bridgeName != null && bridgeName.equals(this.mraidAdSDKBridge.getName())) {
            switch (adControlAccessor.getAdState()) {
                case EXPANDED:
                case SHOWING:
                    handleReadyEvent(adControlAccessor);
                    handleVisibleEvent(adControlAccessor);
                    return;
                case RENDERED:
                    handleReadyEvent(adControlAccessor);
                    return;
                default:
                    return;
            }
        }
    }

    private void handleReadyEvent(AdControlAccessor adControlAccessor) {
        adControlAccessor.injectJavascript("mraidBridge.ready();");
    }

    private void handleVisibleEvent(AdControlAccessor adControlAccessor) {
        adControlAccessor.setOriginalOrientation((Activity) adControlAccessor.getContext());
        Size size = adControlAccessor.getMaxSize();
        this.mraidAdSDKBridge.updateExpandProperties(size.getWidth(), size.getHeight());
        Position currentPosition = adControlAccessor.getCurrentPosition();
        this.mraidAdSDKBridge.updateDefaultPosition(currentPosition.getSize().getWidth(), currentPosition.getSize().getHeight(), currentPosition.getX(), currentPosition.getY());
        this.mraidAdSDKBridge.orientationPropertyChange();
        adControlAccessor.injectJavascript("mraidBridge.stateChange('default');");
        adControlAccessor.injectJavascript("mraidBridge.viewableChange('true');");
    }

    private void handleClosedEvent(AdControlAccessor adControlAccessor) {
        if (adControlAccessor.getAdState().equals(AdState.EXPANDED)) {
            this.mraidAdSDKBridge.collapseExpandedAd(adControlAccessor);
            if (((Activity) adControlAccessor.getContext()).getRequestedOrientation() != adControlAccessor.getOriginalOrientation()) {
                ((Activity) adControlAccessor.getContext()).setRequestedOrientation(adControlAccessor.getOriginalOrientation());
            }
        } else if (adControlAccessor.getAdState().equals(AdState.SHOWING)) {
            if (((Activity) adControlAccessor.getContext()).getRequestedOrientation() != adControlAccessor.getOriginalOrientation()) {
                ((Activity) adControlAccessor.getContext()).setRequestedOrientation(adControlAccessor.getOriginalOrientation());
            }
            adControlAccessor.injectJavascript("mraidBridge.stateChange('hidden');");
            adControlAccessor.injectJavascript("mraidBridge.viewableChange('false');");
        }
    }
}
