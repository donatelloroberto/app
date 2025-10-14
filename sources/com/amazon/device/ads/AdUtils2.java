package com.amazon.device.ads;

import android.content.Context;

/* compiled from: AdUtils */
class AdUtils2 {
    private final AdUtilsStatic adUtilsAdapter = new AdUtilsStatic();

    AdUtils2() {
    }

    public boolean checkDefinedActivities(Context context) {
        return this.adUtilsAdapter.checkDefinedActivities(context);
    }

    public void setConnectionMetrics(ConnectionInfo connectionInfo, MetricsCollector metricsCollector) {
        this.adUtilsAdapter.setConnectionMetrics(connectionInfo, metricsCollector);
    }

    public double getViewportInitialScale(double defaultScale) {
        return this.adUtilsAdapter.getViewportInitialScale(defaultScale);
    }

    public double calculateScalingMultiplier(int absoluteAdWidth, int absoluteAdHeight, int absoluteWindowWidth, int absoluteWindowHeight) {
        return this.adUtilsAdapter.calculateScalingMultiplier(absoluteAdWidth, absoluteAdHeight, absoluteWindowWidth, absoluteWindowHeight);
    }

    public int pixelToDeviceIndependentPixel(int px) {
        return this.adUtilsAdapter.pixelToDeviceIndependentPixel(px);
    }

    public int deviceIndependentPixelToPixel(int dp) {
        return this.adUtilsAdapter.deviceIndependentPixelToPixel(dp);
    }

    public float getScalingFactorAsFloat() {
        return this.adUtilsAdapter.getScalingFactorAsFloat();
    }

    /* compiled from: AdUtils */
    private static class AdUtilsStatic {
        private AdUtilsStatic() {
        }

        /* access modifiers changed from: package-private */
        public boolean checkDefinedActivities(Context context) {
            return AdUtils.checkDefinedActivities(context);
        }

        /* access modifiers changed from: package-private */
        public void setConnectionMetrics(ConnectionInfo connectionInfo, MetricsCollector metricsCollector) {
            AdUtils.setConnectionMetrics(connectionInfo, metricsCollector);
        }

        /* access modifiers changed from: package-private */
        public double getViewportInitialScale(double defaultScale) {
            return AdUtils.getViewportInitialScale(defaultScale);
        }

        /* access modifiers changed from: package-private */
        public double calculateScalingMultiplier(int absoluteAdWidth, int absoluteAdHeight, int absoluteWindowWidth, int absoluteWindowHeight) {
            return AdUtils.calculateScalingMultiplier(absoluteAdWidth, absoluteAdHeight, absoluteWindowWidth, absoluteWindowHeight);
        }

        /* access modifiers changed from: package-private */
        public int pixelToDeviceIndependentPixel(int px) {
            return AdUtils.pixelToDeviceIndependentPixel(px);
        }

        /* access modifiers changed from: package-private */
        public int deviceIndependentPixelToPixel(int dp) {
            return AdUtils.deviceIndependentPixelToPixel(dp);
        }

        /* access modifiers changed from: package-private */
        public float getScalingFactorAsFloat() {
            return AdUtils.getScalingFactorAsFloat();
        }
    }
}
